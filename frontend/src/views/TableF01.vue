<template>
  <div class="table-container">
    <h2>（八）服务业损失统计表</h2>
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
        <el-collapse-item title="一、批发和零售业" name="1">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="批发和零售业经济损失小计 (万元)">
                <el-input-number v-model="form.colF01005" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损网点数量 (个)"><el-input-number v-model="form.colF01001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施数量 (台套)"><el-input-number v-model="form.colF01002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colF01003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损商品经济损失 (万元)"><el-input-number v-model="form.colF01004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、住宿和餐饮业" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="住宿和餐饮业经济损失小计 (万元)">
                <el-input-number v-model="form.colF01009" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损住宿和餐饮网点数量 (个)"><el-input-number v-model="form.colF01006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施数量 (台套)"><el-input-number v-model="form.colF01007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colF01008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、金融业" name="3">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="金融业经济损失小计 (万元)">
                <el-input-number v-model="form.colF01013" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损网点数量 (个)"><el-input-number v-model="form.colF01010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施数量 (台套)"><el-input-number v-model="form.colF01011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colF01012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、文化、体育和娱乐业" name="4">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="文化、体育和娱乐业经济损失小计 (万元)">
                <el-input-number v-model="form.colF01017" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损网点数量 (个)"><el-input-number v-model="form.colF01014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施数量 (台套)"><el-input-number v-model="form.colF01015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colF01016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、农林牧渔服务业" name="5">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损农林牧渔服务业经济损失 (万元)"><el-input-number v-model="form.colF01018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="六、其他服务业" name="6">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="其他服务业经济损失小计 (万元)">
                <el-input-number v-model="form.colF01023" :precision="2" :step="0.1" size="large" style="width: 100%" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left">分项明细</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="受损网点数量 (个)"><el-input-number v-model="form.colF01019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施数量 (台套)"><el-input-number v-model="form.colF01020" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损设备设施经济损失 (万元)"><el-input-number v-model="form.colF01021" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="受损商品经济损失 (万元)"><el-input-number v-model="form.colF01022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="七、倒损相关用房" name="7">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colF01024" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colF01025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="八、合计" name="8">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="服务业经济损失合计 (万元)">
                <el-input-number v-model="form.colF01026" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
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
  colF01001: 0,
  colF01002: 0,
  colF01003: 0,
  colF01004: 0,
  colF01005: 0,
  colF01006: 0,
  colF01007: 0,
  colF01008: 0,
  colF01009: 0,
  colF01010: 0,
  colF01011: 0,
  colF01012: 0,
  colF01013: 0,
  colF01014: 0,
  colF01015: 0,
  colF01016: 0,
  colF01017: 0,
  colF01018: 0,
  colF01019: 0,
  colF01020: 0,
  colF01021: 0,
  colF01022: 0,
  colF01023: 0,
  colF01024: 0,
  colF01025: 0,
  colF01026: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableF01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colF01026 = (Number(form.colF01005)||0)+(Number(form.colF01009)||0)+(Number(form.colF01013)||0)+(Number(form.colF01017)||0)+(Number(form.colF01018)||0)+(Number(form.colF01023)||0)
})

// 使用验证 composable（自动计算 + 比较验证）
const { validateBeforeSubmit } = useTableValidation('F01', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单（包含比较规则验证）
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableF01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
