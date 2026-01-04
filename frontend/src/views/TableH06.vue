<template>
  <div class="table-container">
    <h2>（二十一）公共服务（新闻出版系统）损失统计表</h2>
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
      <el-form-item label="受损新闻出版公共服务机构数量 (个)">
        <el-input-number v-model="form.colH06001" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损新闻出版公共服务机构经济损失 (万元)">
        <el-input-number v-model="form.colH06002" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损影剧院数量 (个)">
        <el-input-number v-model="form.colH06003" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损影剧院经济损失 (万元)">
        <el-input-number v-model="form.colH06004" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损农家书屋数量 (个)">
        <el-input-number v-model="form.colH06005" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损农家书屋经济损失 (万元)">
        <el-input-number v-model="form.colH06006" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="受损其他新闻出版系统经济损失 (万元)">
        <el-input-number v-model="form.colH06007" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="倒损相关用房面积 (平方米)">
        <el-input-number v-model="form.colH06008" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="倒损相关用房经济损失 (万元)">
        <el-input-number v-model="form.colH06009" :precision="2" :step="0.1" size="large" style="width: 100%" />
      </el-form-item>
      <el-form-item label="公共服务（新闻出版系统）经济损失合计 (万元)">
        <el-input-number v-model="form.colH06010" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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

const activeNames = ref(['1', '2', '3', '4', '5', '6'])
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
  colH06001: 0,
  colH06002: 0,
  colH06003: 0,
  colH06004: 0,
  colH06005: 0,
  colH06006: 0,
  colH06007: 0,
  colH06008: 0,
  colH06009: 0,
  colH06010: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH06',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colH06010 =
    (Number(form.colH06002) || 0) +
    (Number(form.colH06004) || 0) +
    (Number(form.colH06006) || 0) +
    (Number(form.colH06007) || 0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('H06', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH06', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
