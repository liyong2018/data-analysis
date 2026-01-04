<template>
  <div class="table-container">
    <h2>（十三）基础设施（市政）损失统计表</h2>
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
        <el-collapse-item title="一、市政道路交通" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="市政道路交通经济损失小计 (万元)">
                <el-input-number v-model="form.colG05007" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损道路长度 (千米)"><el-input-number v-model="form.colG05001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损道路经济损失 (万元)"><el-input-number v-model="form.colG05002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损桥梁长度 (千米)"><el-input-number v-model="form.colG05003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损桥梁经济损失 (万元)"><el-input-number v-model="form.colG05004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损隧道长度 (千米)"><el-input-number v-model="form.colG05005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损隧道经济损失 (万元)"><el-input-number v-model="form.colG05006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、市政供水" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="市政供水经济损失小计 (万元)">
                <el-input-number v-model="form.colG05012" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损水厂数量 (个)"><el-input-number v-model="form.colG05008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损水厂经济损失 (万元)"><el-input-number v-model="form.colG05009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损供水管网长度 (千米)"><el-input-number v-model="form.colG05010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损供水管网经济损失 (万元)"><el-input-number v-model="form.colG05011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、市政排水" name="3">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="市政排水经济损失小计 (万元)">
                <el-input-number v-model="form.colG05021" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损雨水管网长度 (千米)"><el-input-number v-model="form.colG05013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损雨水管网经济损失 (万元)"><el-input-number v-model="form.colG05014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损污水管网长度 (千米)"><el-input-number v-model="form.colG05015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损污水管网经济损失 (万元)"><el-input-number v-model="form.colG05016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损污水处理厂数量 (个)"><el-input-number v-model="form.colG05017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损污水处理厂经济损失 (万元)"><el-input-number v-model="form.colG05018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损再生水厂数量 (个)"><el-input-number v-model="form.colG05019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损再生水厂经济损失 (万元)"><el-input-number v-model="form.colG05020" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、市政供气供热" name="4">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="市政供气供热经济损失小计 (万元)">
                <el-input-number v-model="form.colG05030" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损燃气储气站数量 (个)"><el-input-number v-model="form.colG05022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损燃气储气站经济损失 (万元)"><el-input-number v-model="form.colG05023" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损供气管网长度 (千米)"><el-input-number v-model="form.colG05024" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损供气管网经济损失 (万元)"><el-input-number v-model="form.colG05025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损热源厂数量 (个)"><el-input-number v-model="form.colG05026" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损热源厂经济损失 (万元)"><el-input-number v-model="form.colG05027" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损供热管网长度 (千米)"><el-input-number v-model="form.colG05028" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损供热管网经济损失 (万元)"><el-input-number v-model="form.colG05029" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、市政垃圾处理" name="5">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="市政垃圾处理经济损失小计 (万元)">
                <el-input-number v-model="form.colG05035" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损垃圾无害化处理设施数量 (个)"><el-input-number v-model="form.colG05031" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损垃圾无害化处理设施经济损失 (万元)"><el-input-number v-model="form.colG05032" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损垃圾转运设施数量 (个)"><el-input-number v-model="form.colG05033" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损垃圾转运设施经济损失 (万元)"><el-input-number v-model="form.colG05034" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="六、其他市政设施" name="6">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损城市绿地面积 (公顷)"><el-input-number v-model="form.colG05036" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损城市绿地经济损失 (万元)"><el-input-number v-model="form.colG05037" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损城市防洪经济损失 (万元)"><el-input-number v-model="form.colG05038" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="八、其他市政设施经济损失 (万元)"><el-input-number v-model="form.colG05039" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="七、倒损相关用房" name="7">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colG05040" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colG05041" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="八、合计" name="8">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="基础设施（市政）经济损失合计 (万元)">
                <el-input-number v-model="form.colG05042" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <el-form-item style="margin-top: 20px">
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

const activeNames = ref(['1', '2', '3', '4', '5', '6', '7', '8'])

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
  colG05001: 0,
  colG05002: 0,
  colG05003: 0,
  colG05004: 0,
  colG05005: 0,
  colG05006: 0,
  colG05007: 0,
  colG05008: 0,
  colG05009: 0,
  colG05010: 0,
  colG05011: 0,
  colG05012: 0,
  colG05013: 0,
  colG05014: 0,
  colG05015: 0,
  colG05016: 0,
  colG05017: 0,
  colG05018: 0,
  colG05019: 0,
  colG05020: 0,
  colG05021: 0,
  colG05022: 0,
  colG05023: 0,
  colG05024: 0,
  colG05025: 0,
  colG05026: 0,
  colG05027: 0,
  colG05028: 0,
  colG05029: 0,
  colG05030: 0,
  colG05031: 0,
  colG05032: 0,
  colG05033: 0,
  colG05034: 0,
  colG05035: 0,
  colG05036: 0,
  colG05037: 0,
  colG05038: 0,
  colG05039: 0,
  colG05040: 0,
  colG05041: 0,
  colG05042: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableG05',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colG05042 = (Number(form.colG05007)||0)+(Number(form.colG05012)||0)+(Number(form.colG05021)||0)+(Number(form.colG05030)||0)+(Number(form.colG05035)||0)+(Number(form.colG05037)||0)+(Number(form.colG05038)||0)+(Number(form.colG05039)||0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('G05', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableG05', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
