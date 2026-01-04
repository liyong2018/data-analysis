<template>
  <div class="table-container">
    <h2>（二十二）公共服务（体育）损失统计表</h2>
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
      <el-collapse-item title="一、受损体育机构" name="1">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="受损体育机构数量小计 (个)">
              <el-input-number v-model="form.colH07004" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受损体育机构经济损失 (万元)">
              <el-input-number v-model="form.colH07003" :precision="2" :step="0.1" size="large" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="left">分项明细</el-divider>
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损体育运动学校数量 (个)"><el-input-number v-model="form.colH07001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损体育训练基地数量 (个)"><el-input-number v-model="form.colH07002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="二、受损体育场地" name="2">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损体育场地数量 (个)"><el-input-number v-model="form.colH07005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损体育场地经济损失 (万元)"><el-input-number v-model="form.colH07006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="三、受损体育器材" name="3">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损体育器材数量 (个)"><el-input-number v-model="form.colH07007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损体育器材经济损失 (万元)"><el-input-number v-model="form.colH07008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="四、受损体育场馆" name="4">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="受损体育场馆数量小计 (个)">
              <el-input-number v-model="form.colH07018" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受损体育场馆观众座席数量小计 (座)">
              <el-input-number v-model="form.colH07019" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="受损体育场馆经济损失小计 (万元)">
              <el-input-number v-model="form.colH07020" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="left">分项明细</el-divider>
        <el-row :gutter="20">
          <el-col :span="8"><el-form-item label="受损体育场数量 (个)"><el-input-number v-model="form.colH07009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="受损体育场观众座席 (座)"><el-input-number v-model="form.colH07010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="受损体育场经济损失 (万元)"><el-input-number v-model="form.colH07011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8"><el-form-item label="受损体育馆数量 (个)"><el-input-number v-model="form.colH07012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="受损体育馆观众座席 (座)"><el-input-number v-model="form.colH07013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="受损体育馆经济损失 (万元)"><el-input-number v-model="form.colH07014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8"><el-form-item label="受损游泳馆数量 (个)"><el-input-number v-model="form.colH07015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="受损游泳馆观众座席 (座)"><el-input-number v-model="form.colH07016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="受损游泳馆经济损失 (万元)"><el-input-number v-model="form.colH07017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="五、倒损相关用房" name="5">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colH07021" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colH07022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="六、合计" name="6">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="受损公共服务（体育）经济损失合计 (万元)">
              <el-input-number v-model="form.colH07023" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            </el-form-item>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>
    <div style="margin-top: 20px; text-align: center;">
      <el-button type="primary" @click="onSubmit" size="large" style="width: 50%">提交</el-button>
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

const activeNames = ref(['1', '2', '3', '4', '5', '6'])
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
  colH07001: 0,
  colH07002: 0,
  colH07003: 0,
  colH07004: 0,
  colH07005: 0,
  colH07006: 0,
  colH07007: 0,
  colH07008: 0,
  colH07009: 0,
  colH07010: 0,
  colH07011: 0,
  colH07012: 0,
  colH07013: 0,
  colH07014: 0,
  colH07015: 0,
  colH07016: 0,
  colH07017: 0,
  colH07018: 0,
  colH07019: 0,
  colH07020: 0,
  colH07021: 0,
  colH07022: 0,
  colH07023: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH07',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colH07004 = (Number(form.colH07001) || 0) + (Number(form.colH07002) || 0)
  form.colH07018 = (Number(form.colH07009) || 0) + (Number(form.colH07012) || 0) + (Number(form.colH07015) || 0)
  form.colH07019 = (Number(form.colH07010) || 0) + (Number(form.colH07013) || 0) + (Number(form.colH07016) || 0)
  form.colH07020 = (Number(form.colH07011) || 0) + (Number(form.colH07014) || 0) + (Number(form.colH07017) || 0)
  form.colH07023 =
    (Number(form.colH07003) || 0) +
    (Number(form.colH07006) || 0) +
    (Number(form.colH07008) || 0) +
    (Number(form.colH07020) || 0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('H07', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH07', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
