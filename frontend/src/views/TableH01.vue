<template>
  <div class="table-container">
    <h2>（十六）公共服务（教育系统）损失统计表</h2>
    <el-form :model="form" label-position="top">
      <el-form-item label="报告ID">
        <el-input v-model="form.reportId" size="large" />
      </el-form-item>
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
      <el-form-item label="报告日期">
        <el-date-picker v-model="form.reportDate" type="datetime" value-format="YYYY-MM-DDTHH:mm:ss" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损高等教育学校数量 (个)">
        <el-input-number v-model="form.colH01001" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损高等教育学校经济损失 (万元)">
        <el-input-number v-model="form.colH01002" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损中等教育学校数量 (个)">
        <el-input-number v-model="form.colH01003" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损中等教育学校经济损失 (万元)">
        <el-input-number v-model="form.colH01004" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损初等教育学校数量 (个)">
        <el-input-number v-model="form.colH01005" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损初等教育学校经济损失 (万元)">
        <el-input-number v-model="form.colH01006" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损学前教育机构数量 (个)">
        <el-input-number v-model="form.colH01007" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损学前教育机构经济损失 (万元)">
        <el-input-number v-model="form.colH01008" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损特殊教育学校数量 (个)">
        <el-input-number v-model="form.colH01009" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损特殊教育学校经济损失 (万元)">
        <el-input-number v-model="form.colH01010" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损其他教育学校（机构）数量 (个)">
        <el-input-number v-model="form.colH01011" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损其他教育学校（机构）经济损失 (万元)">
        <el-input-number v-model="form.colH01012" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="校舍受损面积 (平方米)">
        <el-input-number v-model="form.colH01013" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="校舍经济损失 (万元)">
        <el-input-number v-model="form.colH01014" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="公共服务（教育系统）经济损失合计 (万元)">
        <el-input-number v-model="form.colH01015" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
      </el-form-item>
      <el-form-item>
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

const activeNames = ref(['1', '2', '3'])
import axios from 'axios'
import { ElMessage } from 'element-plus'
import regionData from '@/assets/regions.json'
import { useTableLatestRecord, useTableValidation } from '@/composables/useTableValidation'

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
  colH01001: 0,
  colH01002: 0,
  colH01003: 0,
  colH01004: 0,
  colH01005: 0,
  colH01006: 0,
  colH01007: 0,
  colH01008: 0,
  colH01009: 0,
  colH01010: 0,
  colH01011: 0,
  colH01012: 0,
  colH01013: 0,
  colH01014: 0,
  colH01015: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

const { validateBeforeSubmit, applyAutoCalc } = useTableValidation('H01', form)

// Auto-calculation using watchEffect
watchEffect(() => {
  applyAutoCalc()
})

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
