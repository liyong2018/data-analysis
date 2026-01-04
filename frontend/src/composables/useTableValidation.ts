import { unref, watch } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { getTableConfig } from '@/config/tableValidationRules'

type AnyForm = Record<string, any>

export function useTableValidation(tableCode: string, formSource: any) {
  const config = getTableConfig(tableCode)
  const autoCalcRules = config?.autoCalcRules ?? []
  const compareRules = config?.compareRules ?? []

  const getForm = (): AnyForm => (unref(formSource) ?? {}) as AnyForm

  const isAutoCalcField = (field: string) => autoCalcRules.some(r => r.targetField === field)

  const getAutoCalcMessage = (field: string) => {
    const rule = autoCalcRules.find(r => r.targetField === field)
    return rule?.message
  }

  const applyAutoCalc = () => {
    const form = getForm()
    for (const rule of autoCalcRules) {
      if (rule.calcType === 'sum') {
        let sum = 0
        for (const src of rule.sourceFields) {
          sum += Number(form[src] ?? 0) || 0
        }
        form[rule.targetField] = sum
      }
    }
  }

  const validateCompareRules = () => {
    const form = getForm()
    for (const rule of compareRules) {
      const left = Number(form[rule.field] ?? 0) || 0

      // 支持 compareFields 多字段求和比较
      let right = 0
      if (rule.compareFields && rule.compareFields.length > 0) {
        for (const field of rule.compareFields) {
          right += Number(form[field] ?? 0) || 0
        }
      } else if (rule.compareField) {
        right = Number(form[rule.compareField] ?? 0) || 0
      }

      let ok = true
      if (rule.operator === '>=') ok = left >= right
      else if (rule.operator === '<=') ok = left <= right
      else if (rule.operator === '>') ok = left > right
      else if (rule.operator === '<') ok = left < right
      else if (rule.operator === '=') ok = left === right

      if (!ok) {
        ElMessage.error(rule.errorMessage)
        return false
      }
    }
    return true
  }

  const validateBeforeSubmit = async () => {
    applyAutoCalc()
    return validateCompareRules()
  }

  return {
    isAutoCalcField,
    getAutoCalcMessage,
    applyAutoCalc,
    validateBeforeSubmit,
  }
}

type LatestRecordOptions = {
  tableApiPath: string
  form: AnyForm
  regionKey?: string
  reportIdKey?: string
  reportDateKey?: string
  generateReportId: () => string
  getCurrentDateTime: () => string
  onLoaded?: (data: AnyForm) => void
  onNoData?: () => void
  onError?: () => void
}

export function useTableLatestRecord(options: LatestRecordOptions) {
  const {
    tableApiPath,
    form,
    regionKey = 'regionCode',
    reportIdKey = 'reportId',
    reportDateKey = 'reportDate',
    generateReportId,
    getCurrentDateTime,
    onLoaded,
    onNoData,
    onError,
  } = options

  const resetFormForRegion = (regionCode: string) => {
    const region = regionCode || ''
    form.id = undefined
    form[reportIdKey] = generateReportId()
    form[regionKey] = region
    form[reportDateKey] = getCurrentDateTime()
    for (const key of Object.keys(form)) {
      if (key.startsWith('col')) form[key] = 0
    }
  }

  const normalizeDateTime = (value: any) => {
    if (typeof value !== 'string') return value
    const withoutMillis = value.includes('.') ? (value.split('.')[0] ?? '') : value
    if (withoutMillis.includes(' ') && !withoutMillis.includes('T')) return withoutMillis.replace(' ', 'T')
    return withoutMillis
  }

  const fetchExistingData = async (regionCode: string) => {
    if (!regionCode) {
      resetFormForRegion('')
      return
    }

    try {
      const res = await axios.get(`${tableApiPath}/latest`, { params: { regionCode } })
      const data = res.data

      if (!data) {
        resetFormForRegion(regionCode)
        onNoData?.()
        return
      }

      for (const [key, value] of Object.entries(data)) {
        if (key in form) form[key] = key === reportDateKey ? normalizeDateTime(value) : value
      }

      ElMessage.success('已加载该区域最近一条记录')
      onLoaded?.(data as AnyForm)
    } catch {
      ElMessage.error('加载该区域历史数据失败')
      onError?.()
    }
  }

  watch(
    () => form[regionKey],
    regionCode => {
      void fetchExistingData(String(regionCode ?? ''))
    },
    { immediate: true }
  )

  return {
    resetFormForRegion,
    fetchExistingData,
  }
}
