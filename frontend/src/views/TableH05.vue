<template>
  <div class="table-container">
    <h2>（二十）公共服务（广播电视系统）损失统计表</h2>
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
      <el-collapse-item title="一、受损广播电视台" name="1">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损广播电视台数量 (个)"><el-input-number v-model="form.colH05001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损广播电视台经济损失 (万元)"><el-input-number v-model="form.colH05002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="二、受损无线广播电视发射（监测）台" name="2">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损无线广播电视发射（监测）台数量 (个)"><el-input-number v-model="form.colH05003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损无线广播电视发射（监测）台经济损失 (万元)"><el-input-number v-model="form.colH05004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="三、受损广播电视传输覆盖网络" name="3">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损广播电视传输覆盖网络经济损失 (万元)"><el-input-number v-model="form.colH05005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损广播电视有线前端经济损失 (万元)"><el-input-number v-model="form.colH05006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="四、受损乡镇广播电视站" name="4">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损乡镇广播电视站播出设备经济损失 (万元)"><el-input-number v-model="form.colH05007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损乡镇广播电视站传输设备经济损失 (万元)"><el-input-number v-model="form.colH05008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="五、受损广播电视村村通设施" name="5">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损广播电视村村通设施经济损失 (万元)"><el-input-number v-model="form.colH05009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="六、其他广播电视公共服务机构" name="6">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损其他广播电视公共服务机构数量 (个)"><el-input-number v-model="form.colH05010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损其他广播电视公共服务机构经济损失 (万元)"><el-input-number v-model="form.colH05011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="七、倒损相关用房" name="7">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colH05012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colH05013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="八、合计" name="8">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="公共服务（广播电视系统）经济损失合计 (万元)">
              <el-input-number v-model="form.colH05014" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            </el-form-item>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>

    <div style="margin-top: 20px; text-align: center;">
      <el-button type="primary" @click="onSubmit" size="large" style="width: 200px;">提交</el-button>
    </div>

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

const activeNames = ref(['1', '2', '3', '4', '5', '6', '7', '8'])
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
  colH05001: 0,
  colH05002: 0,
  colH05003: 0,
  colH05004: 0,
  colH05005: 0,
  colH05006: 0,
  colH05007: 0,
  colH05008: 0,
  colH05009: 0,
  colH05010: 0,
  colH05011: 0,
  colH05012: 0,
  colH05013: 0,
  colH05014: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH05',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colH05014 =
    (Number(form.colH05002) || 0) +
    (Number(form.colH05004) || 0) +
    (Number(form.colH05005) || 0) +
    (Number(form.colH05006) || 0) +
    (Number(form.colH05007) || 0) +
    (Number(form.colH05008) || 0) +
    (Number(form.colH05009) || 0) +
    (Number(form.colH05011) || 0) +
    (Number(form.colH05013) || 0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('H05', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH05', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
