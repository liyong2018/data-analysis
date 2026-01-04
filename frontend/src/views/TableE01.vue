<template>
  <div class="table-container">
    <h2>（七）工业损失统计表</h2>
    <el-form :model="form" label-position="top">
      <el-card class="box-card" style="margin-bottom: 20px;">
        <template #header>
          <div class="card-header">
            <span>基础信息</span>
          </div>
        </template>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="报告ID">
              <el-input v-model="form.reportId" size="large" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="行政区划代码">
              <el-tree-select
                v-model="form.regionCode"
                :data="regionOptions"
                :props="{ label: 'label', value: 'code', children: 'children' }"
                placeholder="请选择行政区划"
                size="large"
                style="width: 100%"
                filterable
                check-strictly
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报告日期">
              <el-date-picker v-model="form.reportDate" type="datetime" value-format="YYYY-MM-DDTHH:mm:ss" size="large" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-card>

      <el-collapse v-model="activeNames">
        <el-collapse-item title="一、规模以上工业经济损失" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="规模以上工业经济损失小计 (万元)">
                <el-input-number v-model="form.colE01007" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损工业企业数量 (个)"><el-input-number v-model="form.colE01001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损厂房、仓库面积 (平方米)"><el-input-number v-model="form.colE01002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损厂房、仓库经济损失 (万元)"><el-input-number v-model="form.colE01003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施数量 (台套)"><el-input-number v-model="form.colE01004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colE01005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损原材料、半成品、产成品经济损失 (万元)"><el-input-number v-model="form.colE01006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、规模以下工业经济损失" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="规模以下工业经济损失小计 (万元)">
                <el-input-number v-model="form.colE01014" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损工业企业数量 (个)"><el-input-number v-model="form.colE01008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损厂房、仓库面积 (平方米)"><el-input-number v-model="form.colE01009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损厂房、仓库经济损失 (万元)"><el-input-number v-model="form.colE01010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施数量 (台套)"><el-input-number v-model="form.colE01011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colE01012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损原材料、半成品、产成品经济损失 (万元)"><el-input-number v-model="form.colE01013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、合计" name="3">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="工业经济损失合计 (万元)">
                <el-input-number v-model="form.colE01015" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <el-form-item style="margin-top: 20px">
        <el-button type="primary" @click="onSubmit" size="large" style="width: 100%">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.table-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}
.form-tip {
  font-size: 12px;
  color: #909399;
  line-height: 1.5;
}
</style>

<script setup lang="ts">
import { reactive, computed, watchEffect, ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import regionData from '@/assets/regions.json'
import { useTableLatestRecord, useTableValidation } from '@/composables/useTableValidation'

const activeNames = ref(['1', '2', '3'])

// Recursively format region data to include code in label for display
const formatRegions = (data: any[]): any[] => {
  return data.map(item => {
    const newItem = { ...item }
    if (!item.children || item.children.length === 0) {
      newItem.label = `${item.name} - ${item.code}`
    } else {
      newItem.label = item.name
      newItem.children = formatRegions(item.children)
    }
    return newItem
  })
}

const regionOptions = formatRegions(regionData)

const generateReportId = () => {
  const now = new Date()
  const year = now.getFullYear()
  const month = String(now.getMonth() + 1).padStart(2, '0')
  const day = String(now.getDate()).padStart(2, '0')
  const random = String(Math.floor(Math.random() * 1000)).padStart(3, '0')
  return `${year}${month}${day}${random}`
}

const getCurrentDateTime = () => {
  const now = new Date()
  const year = now.getFullYear()
  const month = String(now.getMonth() + 1).padStart(2, '0')
  const day = String(now.getDate()).padStart(2, '0')
  const hour = String(now.getHours()).padStart(2, '0')
  const minute = String(now.getMinutes()).padStart(2, '0')
  const second = String(now.getSeconds()).padStart(2, '0')
  return `${year}-${month}-${day}T${hour}:${minute}:${second}`
}

const form = reactive({
  reportId: generateReportId(),
  regionCode: '',
  reportDate: getCurrentDateTime(),
  colE01001: 0,
  colE01002: 0,
  colE01003: 0,
  colE01004: 0,
  colE01005: 0,
  colE01006: 0,
  colE01007: 0,
  colE01008: 0,
  colE01009: 0,
  colE01010: 0,
  colE01011: 0,
  colE01012: 0,
  colE01013: 0,
  colE01014: 0,
  colE01015: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableE01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colE01015 = (Number(form.colE01007)||0)+(Number(form.colE01014)||0)
})

// 使用验证 composable（自动计算 + 比较验证）
const { validateBeforeSubmit } = useTableValidation('E01', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单（包含比较规则验证）
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableE01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
