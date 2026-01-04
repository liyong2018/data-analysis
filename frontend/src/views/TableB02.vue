<template>
  <div class="table-container">
    <h2>（四）城镇居民住房受损情况统计表</h2>
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
            <el-col :span="6"><el-form-item label="倒塌住房总户数 (户)"><el-input-number v-model="form.colB02021" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="倒塌住房总间数 (间)"><el-input-number v-model="form.colB02022" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="倒塌住房总面积 (m²)"><el-input-number v-model="form.colB02023" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="倒塌住房经济损失小计 (万元)"><el-input-number v-model="form.colB02024" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <!-- Steel -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="钢结构户数"><el-input-number v-model="form.colB02001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构间数"><el-input-number v-model="form.colB02002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构面积"><el-input-number v-model="form.colB02003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构损失"><el-input-number v-model="form.colB02004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Reinforced Concrete -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="钢筋混凝土户数"><el-input-number v-model="form.colB02005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土间数"><el-input-number v-model="form.colB02006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土面积"><el-input-number v-model="form.colB02007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土损失"><el-input-number v-model="form.colB02008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Masonry -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="砌体结构户数"><el-input-number v-model="form.colB02009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构间数"><el-input-number v-model="form.colB02010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构面积"><el-input-number v-model="form.colB02011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构损失"><el-input-number v-model="form.colB02012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Wood/Bamboo -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="木(竹)结构户数"><el-input-number v-model="form.colB02013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构间数"><el-input-number v-model="form.colB02014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构面积"><el-input-number v-model="form.colB02015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构损失"><el-input-number v-model="form.colB02016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Other -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="其他结构户数"><el-input-number v-model="form.colB02017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构间数"><el-input-number v-model="form.colB02018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构面积"><el-input-number v-model="form.colB02019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构损失"><el-input-number v-model="form.colB02020" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、严重损坏住房" name="2">
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="严重损坏总户数"><el-input-number v-model="form.colB02045" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="严重损坏总间数"><el-input-number v-model="form.colB02046" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="严重损坏总面积"><el-input-number v-model="form.colB02047" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="严重损坏损失小计"><el-input-number v-model="form.colB02048" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
           <!-- Steel -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="钢结构户数"><el-input-number v-model="form.colB02025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构间数"><el-input-number v-model="form.colB02026" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构面积"><el-input-number v-model="form.colB02027" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构损失"><el-input-number v-model="form.colB02028" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Reinforced Concrete -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="钢筋混凝土户数"><el-input-number v-model="form.colB02029" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土间数"><el-input-number v-model="form.colB02030" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土面积"><el-input-number v-model="form.colB02031" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土损失"><el-input-number v-model="form.colB02032" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Masonry -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="砌体结构户数"><el-input-number v-model="form.colB02033" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构间数"><el-input-number v-model="form.colB02034" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构面积"><el-input-number v-model="form.colB02035" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构损失"><el-input-number v-model="form.colB02036" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Wood/Bamboo -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="木(竹)结构户数"><el-input-number v-model="form.colB02037" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构间数"><el-input-number v-model="form.colB02038" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构面积"><el-input-number v-model="form.colB02039" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构损失"><el-input-number v-model="form.colB02040" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Other -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="其他结构户数"><el-input-number v-model="form.colB02041" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构间数"><el-input-number v-model="form.colB02042" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构面积"><el-input-number v-model="form.colB02043" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构损失"><el-input-number v-model="form.colB02044" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、一般损坏住房" name="3">
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="一般损坏总户数"><el-input-number v-model="form.colB02069" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="一般损坏总间数"><el-input-number v-model="form.colB02070" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="一般损坏总面积"><el-input-number v-model="form.colB02071" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="一般损坏损失小计"><el-input-number v-model="form.colB02072" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <!-- Steel -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="钢结构户数"><el-input-number v-model="form.colB02049" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构间数"><el-input-number v-model="form.colB02050" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构面积"><el-input-number v-model="form.colB02051" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢结构损失"><el-input-number v-model="form.colB02052" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Reinforced Concrete -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="钢筋混凝土户数"><el-input-number v-model="form.colB02053" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土间数"><el-input-number v-model="form.colB02054" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土面积"><el-input-number v-model="form.colB02055" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="钢筋混凝土损失"><el-input-number v-model="form.colB02056" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Masonry -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="砌体结构户数"><el-input-number v-model="form.colB02057" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构间数"><el-input-number v-model="form.colB02058" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构面积"><el-input-number v-model="form.colB02059" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="砌体结构损失"><el-input-number v-model="form.colB02060" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Wood/Bamboo -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="木(竹)结构户数"><el-input-number v-model="form.colB02061" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构间数"><el-input-number v-model="form.colB02062" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构面积"><el-input-number v-model="form.colB02063" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="木(竹)结构损失"><el-input-number v-model="form.colB02064" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <!-- Other -->
          <el-row :gutter="20">
            <el-col :span="6"><el-form-item label="其他结构户数"><el-input-number v-model="form.colB02065" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构间数"><el-input-number v-model="form.colB02066" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构面积"><el-input-number v-model="form.colB02067" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="6"><el-form-item label="其他结构损失"><el-input-number v-model="form.colB02068" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">
        <el-form-item label="四、城镇居民住房经济损失合计 (万元)">
          <el-input-number v-model="form.colB02073" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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
  reportId: generateReportId(),
  regionCode: '',
  reportDate: getCurrentDateTime(),
  colB02001: 0,
  colB02002: 0,
  colB02003: 0,
  colB02004: 0,
  colB02005: 0,
  colB02006: 0,
  colB02007: 0,
  colB02008: 0,
  colB02009: 0,
  colB02010: 0,
  colB02011: 0,
  colB02012: 0,
  colB02013: 0,
  colB02014: 0,
  colB02015: 0,
  colB02016: 0,
  colB02017: 0,
  colB02018: 0,
  colB02019: 0,
  colB02020: 0,
  colB02021: 0,
  colB02022: 0,
  colB02023: 0,
  colB02024: 0,
  colB02025: 0,
  colB02026: 0,
  colB02027: 0,
  colB02028: 0,
  colB02029: 0,
  colB02030: 0,
  colB02031: 0,
  colB02032: 0,
  colB02033: 0,
  colB02034: 0,
  colB02035: 0,
  colB02036: 0,
  colB02037: 0,
  colB02038: 0,
  colB02039: 0,
  colB02040: 0,
  colB02041: 0,
  colB02042: 0,
  colB02043: 0,
  colB02044: 0,
  colB02045: 0,
  colB02046: 0,
  colB02047: 0,
  colB02048: 0,
  colB02049: 0,
  colB02050: 0,
  colB02051: 0,
  colB02052: 0,
  colB02053: 0,
  colB02054: 0,
  colB02055: 0,
  colB02056: 0,
  colB02057: 0,
  colB02058: 0,
  colB02059: 0,
  colB02060: 0,
  colB02061: 0,
  colB02062: 0,
  colB02063: 0,
  colB02064: 0,
  colB02065: 0,
  colB02066: 0,
  colB02067: 0,
  colB02068: 0,
  colB02069: 0,
  colB02070: 0,
  colB02071: 0,
  colB02072: 0,
  colB02073: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableB02',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  // 倒塌住房小计
  form.colB02021 = (Number(form.colB02001)||0)+(Number(form.colB02005)||0)+(Number(form.colB02009)||0)+(Number(form.colB02013)||0)+(Number(form.colB02017)||0)
  form.colB02022 = (Number(form.colB02002)||0)+(Number(form.colB02006)||0)+(Number(form.colB02010)||0)+(Number(form.colB02014)||0)+(Number(form.colB02018)||0)
  form.colB02023 = (Number(form.colB02003)||0)+(Number(form.colB02007)||0)+(Number(form.colB02011)||0)+(Number(form.colB02015)||0)+(Number(form.colB02019)||0)
  form.colB02024 = (Number(form.colB02004)||0)+(Number(form.colB02008)||0)+(Number(form.colB02012)||0)+(Number(form.colB02016)||0)+(Number(form.colB02020)||0)
  // 严重损坏住房小计
  form.colB02045 = (Number(form.colB02025)||0)+(Number(form.colB02029)||0)+(Number(form.colB02033)||0)+(Number(form.colB02037)||0)+(Number(form.colB02041)||0)
  form.colB02046 = (Number(form.colB02026)||0)+(Number(form.colB02030)||0)+(Number(form.colB02034)||0)+(Number(form.colB02038)||0)+(Number(form.colB02042)||0)
  form.colB02047 = (Number(form.colB02027)||0)+(Number(form.colB02031)||0)+(Number(form.colB02035)||0)+(Number(form.colB02039)||0)+(Number(form.colB02043)||0)
  form.colB02048 = (Number(form.colB02028)||0)+(Number(form.colB02032)||0)+(Number(form.colB02036)||0)+(Number(form.colB02040)||0)+(Number(form.colB02044)||0)
  // 一般损坏住房小计
  form.colB02069 = (Number(form.colB02049)||0)+(Number(form.colB02053)||0)+(Number(form.colB02057)||0)+(Number(form.colB02061)||0)+(Number(form.colB02065)||0)
  form.colB02070 = (Number(form.colB02050)||0)+(Number(form.colB02054)||0)+(Number(form.colB02058)||0)+(Number(form.colB02062)||0)+(Number(form.colB02066)||0)
  form.colB02071 = (Number(form.colB02051)||0)+(Number(form.colB02055)||0)+(Number(form.colB02059)||0)+(Number(form.colB02063)||0)+(Number(form.colB02067)||0)
  form.colB02072 = (Number(form.colB02052)||0)+(Number(form.colB02056)||0)+(Number(form.colB02060)||0)+(Number(form.colB02064)||0)+(Number(form.colB02068)||0)
  // 城镇居民住房经济损失合计
  form.colB02073 = (Number(form.colB02024)||0)+(Number(form.colB02048)||0)+(Number(form.colB02072)||0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('B02', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableB02', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
