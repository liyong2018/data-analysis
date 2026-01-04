<template>
  <div class="table-container">
    <h2>（六）农林牧渔业损失统计表</h2>
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
        <el-collapse-item title="一、农业损失" name="1">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="农作物受灾面积 (公顷)"><el-input-number v-model="form.colD01001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="农作物成灾面积 (公顷)"><el-input-number v-model="form.colD01002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="农作物绝收面积 (公顷)"><el-input-number v-model="form.colD01003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="农作物经济损失 (万元)"><el-input-number v-model="form.colD01004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损农业生产大棚面积 (公顷)"><el-input-number v-model="form.colD01005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损农业生产大棚经济损失 (万元)"><el-input-number v-model="form.colD01006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="农业经济损失小计 (万元)"><el-input-number v-model="form.colD01007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、林业损失" name="2">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="森林草原受灾面积 (公顷)"><el-input-number v-model="form.colD01008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损林木蓄积量 (万立方米)"><el-input-number v-model="form.colD01009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="森林经济损失 (万元)"><el-input-number v-model="form.colD01010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="灌木林地和疏林地受灾面积 (公顷)"><el-input-number v-model="form.colD01011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="灌木林地和疏林地经济损失 (万元)"><el-input-number v-model="form.colD01012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="未成林造林地受灾面积 (公顷)"><el-input-number v-model="form.colD01013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="未成林造林地经济损失 (万元)"><el-input-number v-model="form.colD01014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="苗圃良种受灾面积 (公顷)"><el-input-number v-model="form.colD01015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="苗圃良种经济损失 (万元)"><el-input-number v-model="form.colD01016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损野生动植物驯养繁殖基地（场）数量 (个)"><el-input-number v-model="form.colD01017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损野生动植物驯养繁殖基地（场）经济损失 (万元)"><el-input-number v-model="form.colD01018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="林区基础设施经济损失 (万元)"><el-input-number v-model="form.colD01019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="林业经济损失小计 (万元)"><el-input-number v-model="form.colD01020" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、畜牧业损失" name="3">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="死亡大牲畜数量 (头只)"><el-input-number v-model="form.colD01021" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="死亡小牲畜数量 (头只)"><el-input-number v-model="form.colD01022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="死亡家禽数量 (只)"><el-input-number v-model="form.colD01023" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="死亡畜禽经济损失 (万元)"><el-input-number v-model="form.colD01024" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="倒塌损坏畜禽圈舍面积 (平方米)"><el-input-number v-model="form.colD01025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="倒塌损坏畜禽圈舍经济损失 (万元)"><el-input-number v-model="form.colD01026" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损饲草料数量 (吨)"><el-input-number v-model="form.colD01027" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损饲草料经济损失 (万元)"><el-input-number v-model="form.colD01028" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="畜牧业经济损失小计 (万元)"><el-input-number v-model="form.colD01029" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、渔业损失" name="4">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受灾水产养殖面积 (公顷)"><el-input-number v-model="form.colD01030" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="水产品损失量 (万吨)"><el-input-number v-model="form.colD01031" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="水产品经济损失 (万元)"><el-input-number v-model="form.colD01032" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="水产种苗经济损失 (万元)"><el-input-number v-model="form.colD01033" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损养殖设施经济损失 (万元)"><el-input-number v-model="form.colD01034" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="渔船损失数量 (艘)"><el-input-number v-model="form.colD01035" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损渔船鱼港经济损失 (万元)"><el-input-number v-model="form.colD01036" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="渔业经济损失小计 (万元)"><el-input-number v-model="form.colD01037" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、农机及设施损失" name="5">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损农业机械数量 (台套)"><el-input-number v-model="form.colD01038" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损农业机械经济损失 (万元)"><el-input-number v-model="form.colD01039" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colD01040" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colD01041" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px;">
        <el-form-item label="七、农林牧渔业经济损失合计 (万元)">
          <el-input-number v-model="form.colD01042" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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
  colD01001: 0,
  colD01002: 0,
  colD01003: 0,
  colD01004: 0,
  colD01005: 0,
  colD01006: 0,
  colD01007: 0,
  colD01008: 0,
  colD01009: 0,
  colD01010: 0,
  colD01011: 0,
  colD01012: 0,
  colD01013: 0,
  colD01014: 0,
  colD01015: 0,
  colD01016: 0,
  colD01017: 0,
  colD01018: 0,
  colD01019: 0,
  colD01020: 0,
  colD01021: 0,
  colD01022: 0,
  colD01023: 0,
  colD01024: 0,
  colD01025: 0,
  colD01026: 0,
  colD01027: 0,
  colD01028: 0,
  colD01029: 0,
  colD01030: 0,
  colD01031: 0,
  colD01032: 0,
  colD01033: 0,
  colD01034: 0,
  colD01035: 0,
  colD01036: 0,
  colD01037: 0,
  colD01038: 0,
  colD01039: 0,
  colD01040: 0,
  colD01041: 0,
  colD01042: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableD01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colD01042 = (Number(form.colD01007)||0)+(Number(form.colD01020)||0)+(Number(form.colD01029)||0)+(Number(form.colD01037)||0)+(Number(form.colD01039)||0)
})

// 使用验证 composable（自动计算 + 比较验证）
const { validateBeforeSubmit } = useTableValidation('D01', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单（包含比较规则验证）
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableD01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
