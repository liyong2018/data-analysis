<template>
  <div class="table-container">
    <h2>（二十五）公共服务（社会管理系统）损失统计表</h2>
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
        <el-collapse-item title="一、党政机关" name="1">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损党政机关数量 (个)"><el-input-number v-model="form.colH10001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损党政机关经济损失 (万元)"><el-input-number v-model="form.colH10002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、群众团体、社会团体和其他成员组织" name="2">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损群众团体、社会团体和其他成员组织数量 (个)"><el-input-number v-model="form.colH10003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损群众团体、社会团体和其他成员组织经济损失 (万元)"><el-input-number v-model="form.colH10004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、基层群众自治组织" name="3">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损基层群众自治组织数量 (个)"><el-input-number v-model="form.colH10005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损基层群众自治组织经济损失 (万元)"><el-input-number v-model="form.colH10006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、国际组织" name="4">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损国际组织数量 (个)"><el-input-number v-model="form.colH10007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损国际组织经济损失 (万元)"><el-input-number v-model="form.colH10008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、其他社会管理系统" name="5">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损其他社会管理系统经济损失 (万元)"><el-input-number v-model="form.colH10009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="六、倒损相关用房" name="6">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colH10010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colH10011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="七、合计" name="7">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="公共服务（社会管理系统）经济损失合计 (万元)"><el-input-number v-model="form.colH10012" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
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
import { reactive, computed, watchEffect, ref } from 'vue'

const activeNames = ref(['1', '2', '3', '4', '5', '6', '7'])
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
  colH10001: 0,
  colH10002: 0,
  colH10003: 0,
  colH10004: 0,
  colH10005: 0,
  colH10006: 0,
  colH10007: 0,
  colH10008: 0,
  colH10009: 0,
  colH10010: 0,
  colH10011: 0,
  colH10012: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH10',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

const { validateBeforeSubmit, applyAutoCalc } = useTableValidation('H10', form)

// Auto-calculation using watchEffect
watchEffect(() => {
  applyAutoCalc()
})

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH10', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
