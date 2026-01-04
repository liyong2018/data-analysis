<template>
  <div class="table-container">
    <h2>（二十四）公共服务（公安系统和国家综合性消防救援队伍）损失统计表</h2>
    
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
        <el-collapse-item title="一、公安系统损失" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损公安派出所数量 (个)">
                <el-input-number v-model="form.colH09001" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损公安派出所经济损失 (万元)">
                <el-input-number v-model="form.colH09002" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损监管场所数量 (个)">
                <el-input-number v-model="form.colH09003" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损监管场所损失 (万元)">
                <el-input-number v-model="form.colH09004" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损公安检查站数量 (个)">
                <el-input-number v-model="form.colH09005" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损公安检查站经济损失 (万元)">
                <el-input-number v-model="form.colH09006" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损社区警务室数量 (个)">
                <el-input-number v-model="form.colH09007" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损社区警务室经济损失 (万元)">
                <el-input-number v-model="form.colH09008" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="公安系统经济损失小计 (万元)">
                <el-input-number v-model="form.colH09009" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、国家综合性消防救援队伍损失" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损消防救援站 (个)">
                <el-input-number v-model="form.colH09010" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损消防救援站损失 (万元)">
                <el-input-number v-model="form.colH09011" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损消防救援大队 (个)">
                <el-input-number v-model="form.colH09012" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损消防救援大队损失 (万元)">
                <el-input-number v-model="form.colH09013" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损消防救援支队 (个)">
                <el-input-number v-model="form.colH09014" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损消防救援支队损失 (万元)">
                <el-input-number v-model="form.colH09015" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防中队 (个)">
                <el-input-number v-model="form.colH09016" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防中队损失 (万元)">
                <el-input-number v-model="form.colH09017" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防大队 (个)">
                <el-input-number v-model="form.colH09018" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防大队损失 (万元)">
                <el-input-number v-model="form.colH09019" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防支队 (个)">
                <el-input-number v-model="form.colH09020" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防支队损失 (万元)">
                <el-input-number v-model="form.colH09021" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防机动队伍 (个)">
                <el-input-number v-model="form.colH09022" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损森林消防机动队伍损失 (万元)">
                <el-input-number v-model="form.colH09023" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="国家综合性消防救援队伍经济损失小计 (万元)">
                <el-input-number v-model="form.colH09024" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">
        <el-form-item label="四、公共服务（公安系统和国家综合性消防救援队伍）经济损失合计 (万元)">
          <el-input-number v-model="form.colH09027" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
        </el-form-item>
      </div>

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
  colH09001: 0,
  colH09002: 0,
  colH09003: 0,
  colH09004: 0,
  colH09005: 0,
  colH09006: 0,
  colH09007: 0,
  colH09008: 0,
  colH09009: 0,
  colH09010: 0,
  colH09011: 0,
  colH09012: 0,
  colH09013: 0,
  colH09014: 0,
  colH09015: 0,
  colH09016: 0,
  colH09017: 0,
  colH09018: 0,
  colH09019: 0,
  colH09020: 0,
  colH09021: 0,
  colH09022: 0,
  colH09023: 0,
  colH09024: 0,
  colH09025: 0,
  colH09027: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH09',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

const { validateBeforeSubmit, applyAutoCalc } = useTableValidation('H09', form)

// Auto-calculation using watchEffect
watchEffect(() => {
  applyAutoCalc()
})

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH09', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
