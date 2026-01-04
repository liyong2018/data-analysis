<template>
  <div class="table-container">
    <h2>（十七）公共服务（科技系统）损失统计表</h2>
    
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

    <el-form :model="form" label-position="top">
      <el-collapse v-model="activeNames">
        <el-collapse-item title="一、研究和试验系统经济损失" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="研究和试验系统经济损失小计 (万元)">
                <el-input-number v-model="form.colH02005" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损科研机构数量 (个)"><el-input-number v-model="form.colH02001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损专业技术服务机构数量 (个)"><el-input-number v-model="form.colH02002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损其他科研机构数量 (个)"><el-input-number v-model="form.colH02003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colH02004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、专业监测系统经济损失" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="（九）专业监测系统经济损失小计 (万元)">
                <el-input-number v-model="form.colH02030" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损气象监测站点数量 (个)"><el-input-number v-model="form.colH02006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损气象监测站点设备设施数量 (台套)"><el-input-number v-model="form.colH02007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损气象监测站点经济损失 (万元)"><el-input-number v-model="form.colH02008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损地震监测站点数量 (个)"><el-input-number v-model="form.colH02009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损地震监测站点设备设施数量 (台套)"><el-input-number v-model="form.colH02010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损地震监测站点经济损失 (万元)"><el-input-number v-model="form.colH02011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损海洋监测站点数量 (个)"><el-input-number v-model="form.colH02012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损海洋监测站点设备设施数量 (台套)"><el-input-number v-model="form.colH02013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损海洋监测站点经济损失 (万元)"><el-input-number v-model="form.colH02014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损测绘基准站点数量 (个)"><el-input-number v-model="form.colH02015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损测绘基准站点设备设施数量 (台套)"><el-input-number v-model="form.colH02016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损测绘基准站点经济损失 (万元)"><el-input-number v-model="form.colH02017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损环境保护监测站点数量 (个)"><el-input-number v-model="form.colH02018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损环境保护监测站点设备设施数量 (台套)"><el-input-number v-model="form.colH02019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损环境保护监测站点经济损失 (万元)"><el-input-number v-model="form.colH02020" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损地质勘查监测站点数量 (个)"><el-input-number v-model="form.colH02021" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损地质勘查监测站点设备设施数量 (台/套)"><el-input-number v-model="form.colH02022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损地质勘查监测站点经济损失 (万元)"><el-input-number v-model="form.colH02023" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损水文、水资源、防汛监测站点数量 (个)"><el-input-number v-model="form.colH02024" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损水文、水资源、防汛监测站点设备设施数量 (台套)"><el-input-number v-model="form.colH02025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损水文、水资源、防汛监测站点经济损失 (万元)"><el-input-number v-model="form.colH02026" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损林业生态监测站点数量 (个)"><el-input-number v-model="form.colH02027" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损林业生态监测站点设备设施数量 (台/套)"><el-input-number v-model="form.colH02028" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损林业生态监测站点经济损失 (万元)"><el-input-number v-model="form.colH02029" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、其他科技系统经济损失" name="3">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="三、其他科技系统经济损失 (万元)">
                <el-input-number v-model="form.colH02031" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、倒损相关用房" name="4">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colH02032" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colH02033" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、合计" name="5">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="五、公共服务（科技系统）经济损失合计 (万元)">
                <el-input-number v-model="form.colH02034" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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

const activeNames = ref(['1', '2', '3', '4', '5'])
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
  colH02001: 0,
  colH02002: 0,
  colH02003: 0,
  colH02004: 0,
  colH02005: 0,
  colH02006: 0,
  colH02007: 0,
  colH02008: 0,
  colH02009: 0,
  colH02010: 0,
  colH02011: 0,
  colH02012: 0,
  colH02013: 0,
  colH02014: 0,
  colH02015: 0,
  colH02016: 0,
  colH02017: 0,
  colH02018: 0,
  colH02019: 0,
  colH02020: 0,
  colH02021: 0,
  colH02022: 0,
  colH02023: 0,
  colH02024: 0,
  colH02025: 0,
  colH02026: 0,
  colH02027: 0,
  colH02028: 0,
  colH02029: 0,
  colH02030: 0,
  colH02031: 0,
  colH02032: 0,
  colH02033: 0,
  colH02034: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH02',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

const { validateBeforeSubmit, applyAutoCalc } = useTableValidation('H02', form)

// Auto-calculation using watchEffect
watchEffect(() => {
  applyAutoCalc()
})

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH02', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
