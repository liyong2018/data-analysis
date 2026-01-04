<template>
  <div class="table-container">
    <h2>（一）经济损失统计汇总表</h2>
    <el-form :model="form" label-position="top">
      <!-- 基础信息 -->
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
        <el-collapse-item title="一、居民住房与家庭财产" name="1">
          <el-form-item label="农村居民住宅用房经济损失 (万元)">
            <el-input-number v-model="form.colZ01001" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: B01055 (自动获取)</div>
          </el-form-item>
          <el-form-item label="城镇居民住宅用房经济损失 (万元)">
            <el-input-number v-model="form.colZ01002" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: B02073 (自动获取)</div>
          </el-form-item>
          <el-form-item label="居民家庭财产经济损失 (万元)">
            <el-input-number v-model="form.colZ01003" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: C01011 (自动获取)</div>
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="二、产业损失" name="2">
          <el-form-item label="农林牧渔业经济损失 (万元)">
            <el-input-number v-model="form.colZ01004" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: D01042 (自动获取)</div>
          </el-form-item>
          <el-form-item label="工业经济损失 (万元)">
            <el-input-number v-model="form.colZ01005" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: E01015 (自动获取)</div>
          </el-form-item>
          <el-form-item label="服务业经济损失 (万元)">
            <el-input-number v-model="form.colZ01006" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: F01026 (自动获取)</div>
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="三、基础设施损失" name="3">
          <el-form-item label="基础设施（交通运输）经济损失 (万元)">
            <el-input-number v-model="form.colZ01007" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: G01100 (自动获取)</div>
          </el-form-item>
          <el-form-item label="基础设施（通信）经济损失 (万元)">
            <el-input-number v-model="form.colZ01008" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: G02012 (自动获取)</div>
          </el-form-item>
          <el-form-item label="基础设施（能源）经济损失 (万元)">
            <el-input-number v-model="form.colZ01009" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: G03041 (自动获取)</div>
          </el-form-item>
          <el-form-item label="基础设施（水利）经济损失 (万元)">
            <el-input-number v-model="form.colZ01010" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: G04024 (自动获取)</div>
          </el-form-item>
          <el-form-item label="基础设施（市政）经济损失 (万元)">
            <el-input-number v-model="form.colZ01011" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: G05042 (自动获取)</div>
          </el-form-item>
          <el-form-item label="基础设施（农村地区生活设施）经济损失 (万元)">
            <el-input-number v-model="form.colZ01012" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: G06011 (自动获取)</div>
          </el-form-item>
          <el-form-item label="基础设施（地质灾害防治设施）经济损失 (万元)">
            <el-input-number v-model="form.colZ01013" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: G07017 (自动获取)</div>
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="四、公共服务系统损失" name="4">
          <el-form-item label="公共服务（教育系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01014" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H01015 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（科技系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01015" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H02034 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（医疗卫生系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01016" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H03014 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（文化系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01017" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H04016 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（广播电视系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01018" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H05014 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（新闻出版系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01019" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H06008 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（体育）经济损失 (万元)">
            <el-input-number v-model="form.colZ01020" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H07022 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（社会保障与社会服务系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01021" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H08037 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（公安系统和国家综合性消防救援队伍）经济损失 (万元)">
            <el-input-number v-model="form.colZ01022" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H09025 (自动获取)</div>
          </el-form-item>
          <el-form-item label="公共服务（社会管理系统）经济损失 (万元)">
            <el-input-number v-model="form.colZ01023" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            <div class="form-tip">关联数据源: H10010 (自动获取)</div>
          </el-form-item>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">
        <el-form-item label="经济损失合计 (万元)">
          <el-input-number v-model="form.colZ01024" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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
import { reactive, watchEffect, computed, watch, ref } from 'vue'
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
  colZ01001: 0,
  colZ01002: 0,
  colZ01003: 0,
  colZ01004: 0,
  colZ01005: 0,
  colZ01006: 0,
  colZ01007: 0,
  colZ01008: 0,
  colZ01009: 0,
  colZ01010: 0,
  colZ01011: 0,
  colZ01012: 0,
  colZ01013: 0,
  colZ01014: 0,
  colZ01015: 0,
  colZ01016: 0,
  colZ01017: 0,
  colZ01018: 0,
  colZ01019: 0,
  colZ01020: 0,
  colZ01021: 0,
  colZ01022: 0,
  colZ01023: 0,
  colZ01024: 0,
})

const fetchExternalData = async () => {
  if (!form.regionCode) return;
  try {
    const res = await axios.get(`/api/tableZ01/summary?regionCode=${form.regionCode}`);
    const data = res.data;
    if (data) {
      if (data.colZ01001 !== undefined) form.colZ01001 = data.colZ01001;
      if (data.colZ01002 !== undefined) form.colZ01002 = data.colZ01002;
      if (data.colZ01003 !== undefined) form.colZ01003 = data.colZ01003;
      if (data.colZ01004 !== undefined) form.colZ01004 = data.colZ01004;
      if (data.colZ01005 !== undefined) form.colZ01005 = data.colZ01005;
      if (data.colZ01006 !== undefined) form.colZ01006 = data.colZ01006;
      if (data.colZ01007 !== undefined) form.colZ01007 = data.colZ01007;
      if (data.colZ01008 !== undefined) form.colZ01008 = data.colZ01008;
      if (data.colZ01009 !== undefined) form.colZ01009 = data.colZ01009;
      if (data.colZ01010 !== undefined) form.colZ01010 = data.colZ01010;
      if (data.colZ01011 !== undefined) form.colZ01011 = data.colZ01011;
      if (data.colZ01012 !== undefined) form.colZ01012 = data.colZ01012;
      if (data.colZ01013 !== undefined) form.colZ01013 = data.colZ01013;
      if (data.colZ01014 !== undefined) form.colZ01014 = data.colZ01014;
      if (data.colZ01015 !== undefined) form.colZ01015 = data.colZ01015;
      if (data.colZ01016 !== undefined) form.colZ01016 = data.colZ01016;
      if (data.colZ01017 !== undefined) form.colZ01017 = data.colZ01017;
      if (data.colZ01018 !== undefined) form.colZ01018 = data.colZ01018;
      if (data.colZ01019 !== undefined) form.colZ01019 = data.colZ01019;
      if (data.colZ01020 !== undefined) form.colZ01020 = data.colZ01020;
      if (data.colZ01021 !== undefined) form.colZ01021 = data.colZ01021;
      if (data.colZ01022 !== undefined) form.colZ01022 = data.colZ01022;
      if (data.colZ01023 !== undefined) form.colZ01023 = data.colZ01023;
      ElMessage.success('已自动获取关联数据');
    }
  } catch (e) {
    console.error('Failed to fetch external data', e);
  }
}

const { validateBeforeSubmit, applyAutoCalc } = useTableValidation('Z01', computed(() => form) as any)

useTableLatestRecord({
  tableApiPath: '/api/tableZ01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
  onLoaded: () => {
    fetchExternalData()
  },
  onNoData: () => {
    fetchExternalData()
  }
})

// Watch regionCode changes to fetch external data
watch(() => form.regionCode, () => {
  fetchExternalData()
})

// Auto-calculation for parent fields
watchEffect(() => {
  applyAutoCalc()
})

const onSubmit = async () => {
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableZ01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
