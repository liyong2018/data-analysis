<template>
  <div class="table-container">
    <h2>（二十三）公共服务（社会保障与社会服务系统）损失统计表</h2>
    
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
        <el-collapse-item title="一、社会保障系统损失" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损县级及以上社会保障服务机构数量 (个)">
                <el-input-number v-model="form.colH08001" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上社会保障服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08002" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损乡（镇、街道）社会（劳动）保障事务所数量 (个)">
                <el-input-number v-model="form.colH08003" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损乡（镇、街道）社会（劳动）保障事务所经济损失 (万元)">
                <el-input-number v-model="form.colH08004" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损社区（村）社会（劳动）保障工作站数量 (个)">
                <el-input-number v-model="form.colH08005" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损社区（村）社会（劳动）保障工作站经济损失 (万元)">
                <el-input-number v-model="form.colH08006" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="社会保障系统经济损失小计 (万元)">
                <el-input-number v-model="form.colH08007" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、社会服务系统损失" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损县级及以上养老服务机构数量 (个)">
                <el-input-number v-model="form.colH08008" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上养老服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08009" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下养老服务机构数量 (个)">
                <el-input-number v-model="form.colH08010" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下养老服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08011" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上优抚安置服务机构数量 (个)">
                <el-input-number v-model="form.colH08012" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上优抚安置服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08013" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下优抚安置服务机构数量 (个)">
                <el-input-number v-model="form.colH08014" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下优抚安置服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08015" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上社会福利服务机构数量 (个)">
                <el-input-number v-model="form.colH08016" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上社会福利服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08017" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下社会福利服务机构数量 (个)">
                <el-input-number v-model="form.colH08018" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下社会福利服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08019" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上城乡社区服务机构数量 (个)">
                <el-input-number v-model="form.colH08020" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上城乡社区服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08021" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下城乡社区服务机构数量 (个)">
                <el-input-number v-model="form.colH08022" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下城乡社区服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08023" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上社会救助服务机构数量 (个)">
                <el-input-number v-model="form.colH08024" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上社会救助服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08025" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下社会救助服务机构数量 (个)">
                <el-input-number v-model="form.colH08026" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下社会救助服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08027" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上防灾减灾救灾服务机构数量 (个)">
                <el-input-number v-model="form.colH08028" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级及以上防灾减灾救灾服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08029" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下防灾减灾救灾服务机构数量 (个)">
                <el-input-number v-model="form.colH08030" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损县级以下防灾减灾救灾服务机构经济损失 (万元)">
                <el-input-number v-model="form.colH08031" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损其他社会服务机构数量 (个)">
                <el-input-number v-model="form.colH08032" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损其他社会服务系统经济损失 (万元)">
                <el-input-number v-model="form.colH08033" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="倒损相关用房面积 (平方米)">
                <el-input-number v-model="form.colH08035" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="倒损相关用房经济损失 (万元)">
                <el-input-number v-model="form.colH08036" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="社会服务系统经济损失小计 (万元)">
                <el-input-number v-model="form.colH08034" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">
        <el-form-item label="四、公共服务（社会保障与社会服务系统）经济损失合计 (万元)">
          <el-input-number v-model="form.colH08037" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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

const activeNames = ref(['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'])
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
  colH08001: 0,
  colH08002: 0,
  colH08003: 0,
  colH08004: 0,
  colH08005: 0,
  colH08006: 0,
  colH08007: 0,
  colH08008: 0,
  colH08009: 0,
  colH08010: 0,
  colH08011: 0,
  colH08012: 0,
  colH08013: 0,
  colH08014: 0,
  colH08015: 0,
  colH08016: 0,
  colH08017: 0,
  colH08018: 0,
  colH08019: 0,
  colH08020: 0,
  colH08021: 0,
  colH08022: 0,
  colH08023: 0,
  colH08024: 0,
  colH08025: 0,
  colH08026: 0,
  colH08027: 0,
  colH08028: 0,
  colH08029: 0,
  colH08030: 0,
  colH08031: 0,
  colH08032: 0,
  colH08033: 0,
  colH08034: 0,
  colH08035: 0,
  colH08036: 0,
  colH08037: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH08',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colH08007 = (Number(form.colH08002) || 0) + (Number(form.colH08004) || 0) + (Number(form.colH08006) || 0)
  form.colH08034 =
    (Number(form.colH08009) || 0) +
    (Number(form.colH08011) || 0) +
    (Number(form.colH08013) || 0) +
    (Number(form.colH08015) || 0) +
    (Number(form.colH08017) || 0) +
    (Number(form.colH08019) || 0) +
    (Number(form.colH08021) || 0) +
    (Number(form.colH08023) || 0) +
    (Number(form.colH08025) || 0) +
    (Number(form.colH08027) || 0) +
    (Number(form.colH08029) || 0) +
    (Number(form.colH08031) || 0) +
    (Number(form.colH08033) || 0) +
    (Number(form.colH08036) || 0)
  form.colH08037 = (Number(form.colH08007) || 0) + (Number(form.colH08034) || 0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('H08', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH08', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
