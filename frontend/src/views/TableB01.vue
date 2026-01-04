<template>
  <div class="table-container">
    <h2>（三）农村居民住房受损情况统计表</h2>
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
        <el-collapse-item title="一、倒塌住房" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="倒塌住房总户数 (户)">
                <el-input-number v-model="form.colB01016" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="倒塌住房总间数 (间)">
                <el-input-number v-model="form.colB01017" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="倒塌住房经济损失小计 (万元)">
                <el-input-number v-model="form.colB01018" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-divider content-position="left">分项明细</el-divider>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="钢结构户数 (户)">
                <el-input-number v-model="form.colB01001" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢结构间数 (间)">
                <el-input-number v-model="form.colB01002" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢结构经济损失 (万元)">
                <el-input-number v-model="form.colB01003" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构户数 (户)">
                <el-input-number v-model="form.colB01004" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构间数 (间)">
                <el-input-number v-model="form.colB01005" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构经济损失 (万元)">
                <el-input-number v-model="form.colB01006" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="砌体结构户数 (户)">
                <el-input-number v-model="form.colB01007" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="砌体结构间数 (间)">
                <el-input-number v-model="form.colB01008" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="砌体结构经济损失 (万元)">
                <el-input-number v-model="form.colB01009" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="木（竹）结构户数 (户)">
                <el-input-number v-model="form.colB01010" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="木（竹）结构间数 (间)">
                <el-input-number v-model="form.colB01011" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="木（竹）结构经济损失 (万元)">
                <el-input-number v-model="form.colB01012" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="其他结构户数 (户)">
                <el-input-number v-model="form.colB01013" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="其他结构间数 (间)">
                <el-input-number v-model="form.colB01014" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="其他结构经济损失 (万元)">
                <el-input-number v-model="form.colB01015" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、严重损坏住房" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="严重损坏住房总户数 (户)">
                <el-input-number v-model="form.colB01034" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="严重损坏住房总间数 (间)">
                <el-input-number v-model="form.colB01035" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="严重损坏住房经济损失小计 (万元)">
                <el-input-number v-model="form.colB01036" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>

          <el-divider content-position="left">分项明细</el-divider>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="钢结构户数 (户)">
                <el-input-number v-model="form.colB01019" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢结构间数 (间)">
                <el-input-number v-model="form.colB01020" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢结构经济损失 (万元)">
                <el-input-number v-model="form.colB01021" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构户数 (户)">
                <el-input-number v-model="form.colB01022" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构间数 (间)">
                <el-input-number v-model="form.colB01023" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构经济损失 (万元)">
                <el-input-number v-model="form.colB01024" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="砌体结构户数 (户)">
                <el-input-number v-model="form.colB01025" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="砌体结构间数 (间)">
                <el-input-number v-model="form.colB01026" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="砌体结构经济损失 (万元)">
                <el-input-number v-model="form.colB01027" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="木（竹）结构户数 (户)">
                <el-input-number v-model="form.colB01028" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="木（竹）结构间数 (间)">
                <el-input-number v-model="form.colB01029" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="木（竹）结构经济损失 (万元)">
                <el-input-number v-model="form.colB01030" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="其他结构户数 (户)">
                <el-input-number v-model="form.colB01031" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="其他结构间数 (间)">
                <el-input-number v-model="form.colB01032" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="其他结构经济损失 (万元)">
                <el-input-number v-model="form.colB01033" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、一般损坏住房" name="3">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="一般损坏住房总户数 (户)">
                <el-input-number v-model="form.colB01052" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="一般损坏住房总间数 (间)">
                <el-input-number v-model="form.colB01053" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="一般损坏住房经济损失小计 (万元)">
                <el-input-number v-model="form.colB01054" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>

          <el-divider content-position="left">分项明细</el-divider>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="钢结构户数 (户)">
                <el-input-number v-model="form.colB01037" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢结构间数 (间)">
                <el-input-number v-model="form.colB01038" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢结构经济损失 (万元)">
                <el-input-number v-model="form.colB01039" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构户数 (户)">
                <el-input-number v-model="form.colB01040" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构间数 (间)">
                <el-input-number v-model="form.colB01041" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="钢筋混凝土结构经济损失 (万元)">
                <el-input-number v-model="form.colB01042" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="砌体结构户数 (户)">
                <el-input-number v-model="form.colB01043" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="砌体结构间数 (间)">
                <el-input-number v-model="form.colB01044" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="砌体结构经济损失 (万元)">
                <el-input-number v-model="form.colB01045" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="木（竹）结构户数 (户)">
                <el-input-number v-model="form.colB01046" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="木（竹）结构间数 (间)">
                <el-input-number v-model="form.colB01047" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="木（竹）结构经济损失 (万元)">
                <el-input-number v-model="form.colB01048" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="其他结构户数 (户)">
                <el-input-number v-model="form.colB01049" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="其他结构间数 (间)">
                <el-input-number v-model="form.colB01050" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="其他结构经济损失 (万元)">
                <el-input-number v-model="form.colB01051" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">
        <el-form-item label="四、农村居民住房经济损失合计 (万元)">
          <el-input-number v-model="form.colB01055" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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

const activeNames = ref(['1', '2', '3'])

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
  id: undefined as number | undefined,
  reportId: generateReportId(),
  regionCode: '',
  reportDate: getCurrentDateTime(),
  colB01001: 0,
  colB01002: 0,
  colB01003: 0,
  colB01004: 0,
  colB01005: 0,
  colB01006: 0,
  colB01007: 0,
  colB01008: 0,
  colB01009: 0,
  colB01010: 0,
  colB01011: 0,
  colB01012: 0,
  colB01013: 0,
  colB01014: 0,
  colB01015: 0,
  colB01016: 0,
  colB01017: 0,
  colB01018: 0,
  colB01019: 0,
  colB01020: 0,
  colB01021: 0,
  colB01022: 0,
  colB01023: 0,
  colB01024: 0,
  colB01025: 0,
  colB01026: 0,
  colB01027: 0,
  colB01028: 0,
  colB01029: 0,
  colB01030: 0,
  colB01031: 0,
  colB01032: 0,
  colB01033: 0,
  colB01034: 0,
  colB01035: 0,
  colB01036: 0,
  colB01037: 0,
  colB01038: 0,
  colB01039: 0,
  colB01040: 0,
  colB01041: 0,
  colB01042: 0,
  colB01043: 0,
  colB01044: 0,
  colB01045: 0,
  colB01046: 0,
  colB01047: 0,
  colB01048: 0,
  colB01049: 0,
  colB01050: 0,
  colB01051: 0,
  colB01052: 0,
  colB01053: 0,
  colB01054: 0,
  colB01055: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableB01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('B01', computed(() => form) as any)

// 自动计算汇总字段（根据文档逻辑关系）
watchEffect(() => {
  // 一、倒塌住房小计
  form.colB01016 = (Number(form.colB01001) || 0) + (Number(form.colB01004) || 0) + (Number(form.colB01007) || 0) + (Number(form.colB01010) || 0) + (Number(form.colB01013) || 0)
  form.colB01017 = (Number(form.colB01002) || 0) + (Number(form.colB01005) || 0) + (Number(form.colB01008) || 0) + (Number(form.colB01011) || 0) + (Number(form.colB01014) || 0)
  form.colB01018 = (Number(form.colB01003) || 0) + (Number(form.colB01006) || 0) + (Number(form.colB01009) || 0) + (Number(form.colB01012) || 0) + (Number(form.colB01015) || 0)

  // 二、严重损坏住房小计
  form.colB01034 = (Number(form.colB01019) || 0) + (Number(form.colB01022) || 0) + (Number(form.colB01025) || 0) + (Number(form.colB01028) || 0) + (Number(form.colB01031) || 0)
  form.colB01035 = (Number(form.colB01020) || 0) + (Number(form.colB01023) || 0) + (Number(form.colB01026) || 0) + (Number(form.colB01029) || 0) + (Number(form.colB01032) || 0)
  form.colB01036 = (Number(form.colB01021) || 0) + (Number(form.colB01024) || 0) + (Number(form.colB01027) || 0) + (Number(form.colB01030) || 0) + (Number(form.colB01033) || 0)

  // 三、一般损坏住房小计
  form.colB01052 = (Number(form.colB01037) || 0) + (Number(form.colB01040) || 0) + (Number(form.colB01043) || 0) + (Number(form.colB01046) || 0) + (Number(form.colB01049) || 0)
  form.colB01053 = (Number(form.colB01038) || 0) + (Number(form.colB01041) || 0) + (Number(form.colB01044) || 0) + (Number(form.colB01047) || 0) + (Number(form.colB01050) || 0)
  form.colB01054 = (Number(form.colB01039) || 0) + (Number(form.colB01042) || 0) + (Number(form.colB01045) || 0) + (Number(form.colB01048) || 0) + (Number(form.colB01051) || 0)

  // 四、农村居民住房经济损失合计
  form.colB01055 = (Number(form.colB01018) || 0) + (Number(form.colB01036) || 0) + (Number(form.colB01054) || 0)
})

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableB01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
