<template>
  <div class="table-container">
    <h2>（二）人员受灾情况统计表</h2>
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
        <el-collapse-item title="一、受灾人口情况" name="1">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="受灾人口 (人)">
                <el-input-number v-model="form.colA01001" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="饮水困难人口 (人)">
                <el-input-number v-model="form.colA01006" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="因灾死亡人口 (人)">
                <el-input-number v-model="form.colA01002" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="因灾失踪人口 (人)">
                <el-input-number v-model="form.colA01003" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="因灾受伤人口 (人)">
                <el-input-number v-model="form.colA01004" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-form-item label="其中：因灾重伤人口 (人)">
                <el-input-number v-model="form.colA01005" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、转移安置情况" name="2">
          <el-form-item label="紧急转移安置人口 (人)">
            <el-input-number v-model="form.colA01007" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
          </el-form-item>
          <el-divider content-position="left">安置方式</el-divider>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="其中：集中安置人口 (人)">
                <el-input-number v-model="form.colA01008" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="分散安置人口 (人)">
                <el-input-number v-model="form.colA01009" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、生活救助情况" name="3">
          <el-form-item label="需紧急生活救助人口 (人)">
            <el-input-number v-model="form.colA01010" :precision="2" :step="0.1" size="large" style="width: 100%" />
          </el-form-item>
          <el-divider></el-divider>
          <el-form-item label="需过渡期生活救助人口 (人)">
            <el-input-number v-model="form.colA01011" :precision="2" :step="0.1" size="large" style="width: 100%" />
          </el-form-item>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="其中：女性 (人)">
                <el-input-number v-model="form.colA01012" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="老人（60岁及以上） (人)">
                <el-input-number v-model="form.colA01013" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="儿童（18岁以下） (人)">
                <el-input-number v-model="form.colA01014" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、特殊人群" name="4">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="三无人员 (人)">
                <el-input-number v-model="form.colA01015" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="三孤人员 (人)">
                <el-input-number v-model="form.colA01016" :precision="2" :step="0.1" size="large" style="width: 100%" />
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
import { reactive, computed, ref } from 'vue'
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

const activeNames = ref(['1', '2', '3', '4'])

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
  colA01001: 0,
  colA01002: 0,
  colA01003: 0,
  colA01004: 0,
  colA01005: 0,
  colA01006: 0,
  colA01007: 0,
  colA01008: 0,
  colA01009: 0,
  colA01010: 0,
  colA01011: 0,
  colA01012: 0,
  colA01013: 0,
  colA01014: 0,
  colA01015: 0,
  colA01016: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableA01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// 使用验证 composable
const {
  validateBeforeSubmit
} = useTableValidation('A01', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableA01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
