<template>
  <div class="table-container">
    <h2>（十五）基础设施（地质灾害防治设施）损失统计表</h2>
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

const activeNames = ref(['1', '2', '3', '4'])
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
  colG07001: 0,
  colG07002: 0,
  colG07003: 0,
  colG07004: 0,
  colG07005: 0,
  colG07006: 0,
  colG07007: 0,
  colG07008: 0,
  colG07009: 0,
  colG07010: 0,
  colG07011: 0,
  colG07012: 0,
  colG07013: 0,
  colG07014: 0,
  colG07015: 0,
  colG07016: 0,
  colG07017: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableG07',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colG07017 = (Number(form.colG07001)||0)+(Number(form.colG07002)||0)+(Number(form.colG07003)||0)+(Number(form.colG07004)||0)+(Number(form.colG07005)||0)+(Number(form.colG07006)||0)+(Number(form.colG07007)||0)+(Number(form.colG07008)||0)+(Number(form.colG07009)||0)+(Number(form.colG07010)||0)+(Number(form.colG07011)||0)+(Number(form.colG07012)||0)+(Number(form.colG07013)||0)+(Number(form.colG07014)||0)+(Number(form.colG07015)||0)+(Number(form.colG07016)||0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('G07', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableG07', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
