<template>
  <div class="table-container">
    <h2>（五）居民家庭财产损失统计表</h2>
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
        <el-collapse-item title="一、农村家庭财产损失" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="农村家庭财产损失小计 (万元)">
                <el-input-number v-model="form.colC01005" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="受灾家庭户数 (户)"><el-input-number v-model="form.colC01001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损生产性固定资产经济损失 (万元)"><el-input-number v-model="form.colC01002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损家庭耐用消费品经济损失 (万元)"><el-input-number v-model="form.colC01003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损其他财产损失 (万元)"><el-input-number v-model="form.colC01004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、城镇家庭财产损失" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="城镇家庭财产损失小计 (万元)">
                <el-input-number v-model="form.colC01010" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="受灾家庭户数 (户)"><el-input-number v-model="form.colC01006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损生产性固定资产经济损失 (万元)"><el-input-number v-model="form.colC01007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损家庭耐用消费品经济损失 (万元)"><el-input-number v-model="form.colC01008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="受损其他财产损失 (万元)"><el-input-number v-model="form.colC01009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、合计" name="3">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="三、居民家庭财产损失合计 (万元)">
                <el-input-number v-model="form.colC01011" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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
  colC01001: 0,
  colC01002: 0,
  colC01003: 0,
  colC01004: 0,
  colC01005: 0,
  colC01006: 0,
  colC01007: 0,
  colC01008: 0,
  colC01009: 0,
  colC01010: 0,
  colC01011: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableC01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  // 农村家庭财产损失小计
  form.colC01005 = (Number(form.colC01002)||0)+(Number(form.colC01003)||0)+(Number(form.colC01004)||0)
  form.colC01010 = (Number(form.colC01007)||0)+(Number(form.colC01008)||0)+(Number(form.colC01009)||0)
  form.colC01011 = (Number(form.colC01005)||0)+(Number(form.colC01010)||0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('C01', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableC01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
