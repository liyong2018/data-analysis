<template>
  <div class="table-container">
    <h2>（十一）基础设施（能源）损失统计表</h2>
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
        <el-collapse-item title="一、电力设施" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="（七）电力经济损失小计 (万元)">
                <el-input-number v-model="form.colG03020" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">输变电设施明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损变电设备容量 (千伏安)"><el-input-number v-model="form.colG03001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损线路长度 (千米)"><el-input-number v-model="form.colG03002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="经济损失 (万元)"><el-input-number v-model="form.colG03003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损变电设备容量 (千伏安)"><el-input-number v-model="form.colG03004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损线路长度 (千米)"><el-input-number v-model="form.colG03005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="经济损失 (万元)"><el-input-number v-model="form.colG03006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损变电设备容量 (千伏安)"><el-input-number v-model="form.colG03007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损线路长度 (千米)"><el-input-number v-model="form.colG03008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="经济损失 (万元)"><el-input-number v-model="form.colG03009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损变电设备容量 (千伏安)"><el-input-number v-model="form.colG03010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损线路长度 (千米)"><el-input-number v-model="form.colG03011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="经济损失 (万元)"><el-input-number v-model="form.colG03012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损变电设备容量 (千伏安)"><el-input-number v-model="form.colG03013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损线路长度 (千米)"><el-input-number v-model="form.colG03014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="经济损失 (万元)"><el-input-number v-model="form.colG03015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">发电设施明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="受损电厂数量 (个)"><el-input-number v-model="form.colG03016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损发电机组数量 (个)"><el-input-number v-model="form.colG03017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损电厂装机容量 (千瓦)"><el-input-number v-model="form.colG03018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损电厂经济损失 (万元)"><el-input-number v-model="form.colG03019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、煤油气设施" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="（四）煤油气经济损失小计 (万元)">
                <el-input-number v-model="form.colG03038" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">煤矿设施</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损煤矿数量 (处)"><el-input-number v-model="form.colG03021" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损煤矿规模 (万吨/年)"><el-input-number v-model="form.colG03022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损煤矿经济损失 (万元)"><el-input-number v-model="form.colG03023" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">油气设施</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损油井数量 (个)"><el-input-number v-model="form.colG03024" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损油井经济损失 (万元)"><el-input-number v-model="form.colG03025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损输油管道（包括保护设施）长度 (千米)"><el-input-number v-model="form.colG03026" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损输油管道（包括保护设施）经济损失 (万元)"><el-input-number v-model="form.colG03027" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损油库数量 (座)"><el-input-number v-model="form.colG03028" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损油库经济损失 (万元)"><el-input-number v-model="form.colG03029" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损天然气气井数量 (个)"><el-input-number v-model="form.colG03030" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损天然气气井经济损失 (万元)"><el-input-number v-model="form.colG03031" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损天然气管线长度 (千米)"><el-input-number v-model="form.colG03032" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损天然气管线经济损失 (万元)"><el-input-number v-model="form.colG03033" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损煤层气井数量 (个)"><el-input-number v-model="form.colG03034" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损煤层气井经济损失 (万元)"><el-input-number v-model="form.colG03035" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损煤层气管线长度 (千米)"><el-input-number v-model="form.colG03036" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损煤层气管线经济损失 (万元)"><el-input-number v-model="form.colG03037" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、倒损相关用房" name="3">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colG03039" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colG03040" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、合计" name="4">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="四、基础设施（能源）经济损失合计 (万元)">
                <el-input-number v-model="form.colG03041" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <el-form-item style="margin-top: 20px;">
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

const activeNames = ref(['1', '2', '3', '4'])

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
  colG03001: 0,
  colG03002: 0,
  colG03003: 0,
  colG03004: 0,
  colG03005: 0,
  colG03006: 0,
  colG03007: 0,
  colG03008: 0,
  colG03009: 0,
  colG03010: 0,
  colG03011: 0,
  colG03012: 0,
  colG03013: 0,
  colG03014: 0,
  colG03015: 0,
  colG03016: 0,
  colG03017: 0,
  colG03018: 0,
  colG03019: 0,
  colG03020: 0,
  colG03021: 0,
  colG03022: 0,
  colG03023: 0,
  colG03024: 0,
  colG03025: 0,
  colG03026: 0,
  colG03027: 0,
  colG03028: 0,
  colG03029: 0,
  colG03030: 0,
  colG03031: 0,
  colG03032: 0,
  colG03033: 0,
  colG03034: 0,
  colG03035: 0,
  colG03036: 0,
  colG03037: 0,
  colG03038: 0,
  colG03039: 0,
  colG03040: 0,
  colG03041: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableG03',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colG03041 = (Number(form.colG03020)||0)+(Number(form.colG03038)||0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('G03', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableG03', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
