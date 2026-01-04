<template>
  <div class="table-container">
    <h2>（二十六）公共服务（文化遗产）损失统计表</h2>
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
        <el-collapse-item title="一、重点文物保护单位" name="1">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损全国重点文物保护单位数量 (处)"><el-input-number v-model="form.colH11001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损省级重点文物保护单位数量 (处)"><el-input-number v-model="form.colH11002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损市县级重点文物保护单位数量 (处)"><el-input-number v-model="form.colH11003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损世界文化遗产数量 (处)"><el-input-number v-model="form.colH11004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损尚未核定公布为文物保护单位的不可移动文物数量 (处)"><el-input-number v-model="form.colH11005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、可移动文物" name="2">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损珍贵文物数量 (件套)"><el-input-number v-model="form.colH11006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损一般文物数量 (件套)"><el-input-number v-model="form.colH11007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、历史文化名城名镇名村" name="3">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损名城（含历史街区）数量 (处)"><el-input-number v-model="form.colH11008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损名镇数量 (处)"><el-input-number v-model="form.colH11009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损名村数量 (处)"><el-input-number v-model="form.colH11010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损中国传统村落数量 (处)"><el-input-number v-model="form.colH11011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、非物质文化遗产" name="4">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损非物质文化遗产数量 (处)"><el-input-number v-model="form.colH11012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
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
  colH11001: 0,
  colH11002: 0,
  colH11003: 0,
  colH11004: 0,
  colH11005: 0,
  colH11006: 0,
  colH11007: 0,
  colH11008: 0,
  colH11009: 0,
  colH11010: 0,
  colH11011: 0,
  colH11012: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH11',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

const { validateBeforeSubmit } = useTableValidation('H11', computed(() => form) as any)

const onSubmit = async () => {
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH11', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
