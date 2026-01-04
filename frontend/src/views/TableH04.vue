<template>
  <div class="table-container">
    <h2>（十九）公共服务（文化系统）损失统计表</h2>
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
      <el-collapse-item title="一、受损图书馆（档案馆）" name="1">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损图书馆（档案馆）数量 (个)"><el-input-number v-model="form.colH04001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损图书馆（档案馆）经济损失 (万元)"><el-input-number v-model="form.colH04002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="二、受损博物馆" name="2">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损博物馆数量 (个)"><el-input-number v-model="form.colH04003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损博物馆经济损失 (万元)"><el-input-number v-model="form.colH04004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="三、受损文化馆" name="3">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损文化馆数量 (个)"><el-input-number v-model="form.colH04005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损文化馆经济损失 (万元)"><el-input-number v-model="form.colH04006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="四、受损剧场" name="4">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损剧场数量 (个)"><el-input-number v-model="form.colH04007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损剧场经济损失 (万元)"><el-input-number v-model="form.colH04008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="五、受损乡镇综合文化站" name="5">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损乡镇综合文化站数量 (个)"><el-input-number v-model="form.colH04009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损乡镇综合文化站经济损失 (万元)"><el-input-number v-model="form.colH04010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="六、受损社区图书室（文化室）" name="6">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损社区图书室（文化室）数量 (个)"><el-input-number v-model="form.colH04011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损社区图书室（文化室）经济损失 (万元)"><el-input-number v-model="form.colH04012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="七、受损宗教活动场所及宗教院校" name="7">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损宗教活动场所及宗教院校数量 (个)"><el-input-number v-model="form.colH04013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="受损宗教活动场所及宗教院校经济损失 (万元)"><el-input-number v-model="form.colH04014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="八、其他" name="8">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="受损其他文化系统经济损失 (万元)"><el-input-number v-model="form.colH04015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="九、倒损相关用房" name="9">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colH04016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colH04017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
        </el-row>
      </el-collapse-item>

      <el-collapse-item title="十、合计" name="10">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="公共服务（文化系统）经济损失合计 (万元)">
              <el-input-number v-model="form.colH04018" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
            </el-form-item>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>

    <div style="margin-top: 20px; text-align: center;">
      <el-button type="primary" @click="onSubmit" size="large" style="width: 200px;">提交</el-button>
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
  colH04001: 0,
  colH04002: 0,
  colH04003: 0,
  colH04004: 0,
  colH04005: 0,
  colH04006: 0,
  colH04007: 0,
  colH04008: 0,
  colH04009: 0,
  colH04010: 0,
  colH04011: 0,
  colH04012: 0,
  colH04013: 0,
  colH04014: 0,
  colH04015: 0,
  colH04016: 0,
  colH04017: 0,
  colH04018: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableH04',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colH04018 =
    (Number(form.colH04002) || 0) +
    (Number(form.colH04004) || 0) +
    (Number(form.colH04006) || 0) +
    (Number(form.colH04008) || 0) +
    (Number(form.colH04010) || 0) +
    (Number(form.colH04012) || 0) +
    (Number(form.colH04014) || 0) +
    (Number(form.colH04015) || 0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('H04', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableH04', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
