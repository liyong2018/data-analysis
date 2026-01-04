<template>
  <div class="table-container">
    <h2>（十二）基础设施（水利）损失统计表</h2>
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
        <el-collapse-item title="一、水库与水电站" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损大中型水库水电站数量 (座)">
                <el-input-number v-model="form.colG04001" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损大中型水库水电站经济损失 (万元)">
                <el-input-number v-model="form.colG04002" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损小型水库水电站数量 (座)">
                <el-input-number v-model="form.colG04003" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损小型水库水电站经济损失 (万元)">
                <el-input-number v-model="form.colG04004" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、堤防与护岸" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损 1级和 2级堤防长度 (千米)">
                <el-input-number v-model="form.colG04005" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损 3级及以下堤防长度 (千米)">
                <el-input-number v-model="form.colG04006" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损堤防经济损失 (万元)">
                <el-input-number v-model="form.colG04007" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损护岸数量 (处)">
                <el-input-number v-model="form.colG04008" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损护岸经济损失 (万元)">
                <el-input-number v-model="form.colG04009" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、水闸与塘坝" name="3">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损水闸数量 (座)">
                <el-input-number v-model="form.colG04010" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损水闸经济损失 (万元)">
                <el-input-number v-model="form.colG04011" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损塘坝数量 (座)">
                <el-input-number v-model="form.colG04012" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损塘坝经济损失 (万元)">
                <el-input-number v-model="form.colG04013" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、灌排设施与机电设施" name="4">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损灌排设施数量 (处)">
                <el-input-number v-model="form.colG04014" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损灌排设施经济损失 (万元)">
                <el-input-number v-model="form.colG04015" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损机电井数量 (眼)">
                <el-input-number v-model="form.colG04016" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损机电井经济损失 (万元)">
                <el-input-number v-model="form.colG04017" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损机电泵站数量 (座)">
                <el-input-number v-model="form.colG04018" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损机电泵站经济损失 (万元)">
                <el-input-number v-model="form.colG04019" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、其他及合计" name="5">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="受损水文测站数量 (个)">
                <el-input-number v-model="form.colG04020" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="受损水文测站经济损失 (万元)">
                <el-input-number v-model="form.colG04021" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="防洪排灌设施经济损失小计 (万元)">
                <el-input-number v-model="form.colG04022" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="二、受损其他水利工程经济损失 (万元)">
                <el-input-number v-model="form.colG04023" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">
        <el-form-item label="三、基础设施（水利）经济损失合计 (万元)">
          <el-input-number v-model="form.colG04024" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
        </el-form-item>
      </div>

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
import { reactive, watchEffect, ref, computed } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import regionData from '@/assets/regions.json'
import { useTableLatestRecord, useTableValidation } from '@/composables/useTableValidation'

const activeNames = ref(['1', '2', '3', '4', '5'])

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
  colG04001: 0,
  colG04002: 0,
  colG04003: 0,
  colG04004: 0,
  colG04005: 0,
  colG04006: 0,
  colG04007: 0,
  colG04008: 0,
  colG04009: 0,
  colG04010: 0,
  colG04011: 0,
  colG04012: 0,
  colG04013: 0,
  colG04014: 0,
  colG04015: 0,
  colG04016: 0,
  colG04017: 0,
  colG04018: 0,
  colG04019: 0,
  colG04020: 0,
  colG04021: 0,
  colG04022: 0,
  colG04023: 0,
  colG04024: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableG04',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colG04024 = (Number(form.colG04022)||0)+(Number(form.colG04023)||0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('G04', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableG04', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
