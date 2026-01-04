<template>
  <div class="table-container">
    <h2>（二十七）资源与环境损失统计表</h2>
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
        <el-collapse-item title="一、土地资源毁坏" name="1">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="毁坏耕地面积 (公顷)"><el-input-number v-model="form.colI01001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="毁坏林地面积 (公顷)"><el-input-number v-model="form.colI01002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="毁坏草地面积 (公顷)"><el-input-number v-model="form.colI01003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、矿山资源毁坏" name="2">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="毁坏非煤矿山资源数量 (处)"><el-input-number v-model="form.colI01004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="毁坏非煤矿山资源面积 (万平方米)"><el-input-number v-model="form.colI01005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、自然保护地受损" name="3">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损国家级自然保护地数量 (个)"><el-input-number v-model="form.colI01006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损地方级自然保护地数量 (个)"><el-input-number v-model="form.colI01007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、野生动物伤亡" name="4">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="野生动物伤亡数量 (头只)"><el-input-number v-model="form.colI01008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="其中：国家重点保护野生动物伤亡数量 (头只)"><el-input-number v-model="form.colI01009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、环境污染" name="5">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="地表水污染面积 (公顷)"><el-input-number v-model="form.colI01010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="土壤污染面积 (公顷)"><el-input-number v-model="form.colI01011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; text-align: center;">
        <el-button type="primary" @click="onSubmit" size="large" style="width: 200px">提交</el-button>
      </div>
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
import { reactive, computed, ref } from 'vue'

const activeNames = ref(['1', '2', '3', '4', '5'])
import axios from 'axios'
import { ElMessage } from 'element-plus'
import regionData from '@/assets/regions.json'
import { useTableValidation, useTableLatestRecord } from '@/composables/useTableValidation'

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
  colI01001: 0,
  colI01002: 0,
  colI01003: 0,
  colI01004: 0,
  colI01005: 0,
  colI01006: 0,
  colI01007: 0,
  colI01008: 0,
  colI01009: 0,
  colI01010: 0,
  colI01011: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableI01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

const { validateBeforeSubmit } = useTableValidation('I01', computed(() => form) as any)

const onSubmit = async () => {
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableI01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
