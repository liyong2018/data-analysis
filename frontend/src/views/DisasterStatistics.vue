<template>
  <div class="statistics-container">
    <h2>灾害损失统计分析</h2>

    <!-- 筛选条件 -->
    <el-card class="filter-card" shadow="never">
      <el-form :model="filterForm" inline>
        <el-form-item label="年份">
          <el-select v-model="filterForm.year" placeholder="全部" clearable style="width: 150px">
            <el-option v-for="year in filterOptions.years" :key="year" :label="year" :value="year" />
          </el-select>
        </el-form-item>
        <el-form-item label="灾种">
          <el-tree-select
            v-model="filterForm.disasterType"
            :data="filterOptions.disasterTypeTree"
            :props="treeSelectProps"
            placeholder="全部"
            clearable
            check-strictly
            style="width: 220px"
          />
        </el-form-item>
        <el-form-item label="区域">
          <el-tree-select
            v-model="filterForm.region"
            :data="filterOptions.regionTree"
            :props="treeSelectProps"
            placeholder="全部"
            clearable
            check-strictly
            style="width: 220px"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loadStatistics">查询</el-button>
          <el-button @click="resetFilter">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 统计概览 -->
    <el-card class="overview-card" shadow="never" v-loading="overviewLoading">
      <template #header>
        <div class="card-header">
          <span>统计概览</span>
          <el-button type="primary" size="small" @click="loadOverview">刷新</el-button>
        </div>
      </template>
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="stat-item">
            <div class="stat-value">{{ formatNumber(overviewData.totalCount) }}</div>
            <div class="stat-label">灾害记录数</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="stat-item">
            <div class="stat-value stat-danger">{{ formatNumber(overviewData.totalAffected) }}</div>
            <div class="stat-label">受灾总人口(人)</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="stat-item">
            <div class="stat-value stat-warning">{{ formatNumber(overviewData.totalDeaths) }}</div>
            <div class="stat-label">因灾死亡人口(人)</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="stat-item">
            <div class="stat-value stat-primary">{{ formatMoney(overviewData.totalLoss) }}</div>
            <div class="stat-label">直接经济损失(万元)</div>
          </div>
        </el-col>
      </el-row>
    </el-card>

    <!-- 按年份统计和按灾种统计（并排） -->
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card class="chart-card" shadow="never">
          <template #header>
            <div class="card-header">
              <span>按年份统计</span>
            </div>
          </template>
          <div ref="yearChartRef" style="height: 300px"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="chart-card" shadow="never">
          <template #header>
            <div class="card-header">
              <span>按灾种统计</span>
            </div>
          </template>
          <div ref="disasterTypeChartRef" style="height: 300px"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 按区域统计 -->
    <el-card class="chart-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span>按区域统计</span>
        </div>
      </template>
      <div ref="regionChartRef" style="height: 400px"></div>
    </el-card>

    <!-- 多维度统计数据表格 -->
    <el-card class="table-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span>多维度统计明细</span>
        </div>
      </template>
      <el-table :data="statisticsTableData" v-loading="tableLoading" stripe>
        <el-table-column prop="disaster_year" label="年份" width="100" />
        <el-table-column prop="disaster_type" label="灾种" width="150" />
        <el-table-column prop="region" label="区域" width="150" />
        <el-table-column prop="totalAffected" label="受灾人口(人)" width="120">
          <template #default="{ row }">
            {{ formatNumber(row.totalAffected) }}
          </template>
        </el-table-column>
        <el-table-column prop="totalDeaths" label="死亡人口(人)" width="120">
          <template #default="{ row }">
            {{ formatNumber(row.totalDeaths) }}
          </template>
        </el-table-column>
        <el-table-column prop="totalLoss" label="经济损失(万元)" width="140">
          <template #default="{ row }">
            {{ formatMoney(row.totalLoss) }}
          </template>
        </el-table-column>
        <el-table-column label="损失占比" width="120">
          <template #default="{ row }">
            {{ getLossPercentage(row.totalLoss) }}%
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, nextTick, computed } from 'vue'
import * as echarts from 'echarts'
import disasterApi, { type StatisticsData, type FilterOptions, type OverviewData } from '@/api/disasterStatistics'
import { ElMessage } from 'element-plus'

// 筛选表单
const filterForm = reactive({
  year: null as number | null,
  disasterType: '',
  region: ''
})

// 树形选择器配置
const treeSelectProps = {
  label: 'label',
  value: 'value',
  children: 'children'
}

// 筛选选项
const filterOptions = reactive<FilterOptions>({
  years: [],
  disasterTypes: [],
  regions: [],
  disasterTypeTree: [],
  regionTree: []
})

// 统计概览数据
const overviewData = reactive<OverviewData>({
  totalCount: 0,
  totalAffected: 0,
  totalDeaths: 0,
  totalLoss: 0,
  yearlyStats: [],
  disasterTypeStats: [],
  regionStats: []
})

// 统计表格数据
const statisticsTableData = ref<StatisticsData[]>([])

// 加载状态
const overviewLoading = ref(false)
const tableLoading = ref(false)

// 图表引用
const yearChartRef = ref<HTMLElement>()
const disasterTypeChartRef = ref<HTMLElement>()
const regionChartRef = ref<HTMLElement>()

// 图表实例
let yearChart: echarts.ECharts | null = null
let disasterTypeChart: echarts.ECharts | null = null
let regionChart: echarts.ECharts | null = null

// 格式化数字
const formatNumber = (num?: number) => {
  if (num === undefined || num === null) return '0'
  return num.toLocaleString('zh-CN')
}

// 格式化金额
const formatMoney = (num?: number) => {
  if (num === undefined || num === null) return '0'
  return num.toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })
}

// 计算损失占比
const getLossPercentage = (loss?: number) => {
  if (!loss || !overviewData.totalLoss) return '0.00'
  return ((loss / overviewData.totalLoss) * 100).toFixed(2)
}

// 加载筛选选项
const loadFilterOptions = async () => {
  try {
    const { data } = await disasterApi.getFilterOptions()
    filterOptions.years = data.years || []
    filterOptions.disasterTypes = data.disasterTypes || []
    filterOptions.regions = data.regions || []
    filterOptions.disasterTypeTree = data.disasterTypeTree || []
    filterOptions.regionTree = data.regionTree || []
  } catch (error) {
    ElMessage.error('加载筛选选项失败')
  }
}

// 加载统计概览（应用筛选条件）
const loadOverview = async () => {
  overviewLoading.value = true
  try {
    const { data } = await disasterApi.getOverview({
      year: filterForm.year || undefined,
      disasterType: filterForm.disasterType || undefined,
      region: filterForm.region || undefined
    })
    Object.assign(overviewData, data)

    // 更新图表
    await nextTick()
    initYearChart(data.yearlyStats || [])
    initDisasterTypeChart(data.disasterTypeStats || [])
    initRegionChart(data.regionStats || [])
  } catch (error) {
    ElMessage.error('加载统计概览失败')
  } finally {
    overviewLoading.value = false
  }
}

// 加载统计数据（同时刷新概览和表格）
const loadStatistics = async () => {
  tableLoading.value = true
  overviewLoading.value = true
  try {
    // 并行加载概览和表格数据
    const [overviewRes, tableRes] = await Promise.all([
      disasterApi.getOverview({
        year: filterForm.year || undefined,
        disasterType: filterForm.disasterType || undefined,
        region: filterForm.region || undefined
      }),
      disasterApi.multiDimensionStatistics({
        year: filterForm.year || undefined,
        disasterType: filterForm.disasterType || undefined,
        region: filterForm.region || undefined
      })
    ])

    // 更新概览数据
    Object.assign(overviewData, overviewRes.data)
    await nextTick()
    initYearChart(overviewRes.data.yearlyStats || [])
    initDisasterTypeChart(overviewRes.data.disasterTypeStats || [])
    initRegionChart(overviewRes.data.regionStats || [])

    // 更新表格数据
    statisticsTableData.value = tableRes.data
  } catch (error) {
    ElMessage.error('加载数据失败')
  } finally {
    tableLoading.value = false
    overviewLoading.value = false
  }
}

// 重置筛选
const resetFilter = () => {
  filterForm.year = null
  filterForm.disasterType = ''
  filterForm.region = ''
  loadStatistics()
}

// 初始化年份图表
const initYearChart = (data: StatisticsData[]) => {
  if (!yearChartRef.value) return

  if (yearChart) {
    yearChart.dispose()
  }

  yearChart = echarts.init(yearChartRef.value)

  const option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' }
    },
    legend: {
      data: ['受灾人口(人)', '死亡人口(人)', '经济损失(万元)']
    },
    xAxis: {
      type: 'category',
      data: data.map(d => d.year?.toString())
    },
    yAxis: [
      { type: 'value', name: '人口', position: 'left' },
      { type: 'value', name: '损失(万元)', position: 'right' }
    ],
    series: [
      {
        name: '受灾人口(人)',
        type: 'bar',
        data: data.map(d => d.totalAffected),
        itemStyle: { color: '#409EFF' }
      },
      {
        name: '死亡人口(人)',
        type: 'bar',
        data: data.map(d => d.totalDeaths),
        itemStyle: { color: '#F56C6C' }
      },
      {
        name: '经济损失(万元)',
        type: 'line',
        yAxisIndex: 1,
        data: data.map(d => d.totalLoss),
        itemStyle: { color: '#E6A23C' }
      }
    ]
  }

  yearChart.setOption(option)
}

// 初始化灾种图表
const initDisasterTypeChart = (data: StatisticsData[]) => {
  if (!disasterTypeChartRef.value) return

  if (disasterTypeChart) {
    disasterTypeChart.dispose()
  }

  disasterTypeChart = echarts.init(disasterTypeChartRef.value)

  const getDisasterTypeName = (item: StatisticsData) => {
    return item.disasterType || (item as any).disaster_type
  }

  const option = {
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      right: '10%',
      top: 'center'
    },
    series: [
      {
        name: '经济损失',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 20,
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: data.map(d => ({
          value: d.totalLoss,
          name: getDisasterTypeName(d)
        }))
      }
    ]
  }

  disasterTypeChart.setOption(option)
}

// 初始化区域图表
const initRegionChart = (data: StatisticsData[]) => {
  if (!regionChartRef.value) return

  if (regionChart) {
    regionChart.dispose()
  }

  regionChart = echarts.init(regionChartRef.value)

  const option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' }
    },
    legend: {
      data: ['受灾人口', '死亡人口', '经济损失']
    },
    xAxis: {
      type: 'value'
    },
    yAxis: {
      type: 'category',
      data: data.map(d => d.region),
      axisLabel: {
        interval: 0,
        rotate: 30
      }
    },
    series: [
      {
        name: '受灾人口',
        type: 'bar',
        stack: 'total',
        data: data.map(d => d.totalAffected),
        itemStyle: { color: '#409EFF' }
      },
      {
        name: '死亡人口',
        type: 'bar',
        stack: 'total',
        data: data.map(d => d.totalDeaths),
        itemStyle: { color: '#F56C6C' }
      },
      {
        name: '经济损失',
        type: 'bar',
        stack: 'total',
        data: data.map(d => d.totalLoss ? d.totalLoss / 10000 : 0),
        itemStyle: { color: '#E6A23C' }
      }
    ]
  }

  regionChart.setOption(option)
}

// 窗口大小变化时重绘图表
const resizeCharts = () => {
  yearChart?.resize()
  disasterTypeChart?.resize()
  regionChart?.resize()
}

onMounted(async () => {
  await loadFilterOptions()
  await loadOverview()
  await loadStatistics()

  window.addEventListener('resize', resizeCharts)
})
</script>

<style scoped>
.statistics-container {
  padding: 20px;
  max-width: 1400px;
  margin: 0 auto;
}

.filter-card {
  margin-bottom: 20px;
}

.overview-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.stat-item {
  text-align: center;
  padding: 10px;
}

.stat-value {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 8px;
}

.stat-value.stat-danger {
  color: #F56C6C;
}

.stat-value.stat-warning {
  color: #E6A23C;
}

.stat-value.stat-primary {
  color: #409EFF;
}

.stat-label {
  font-size: 14px;
  color: #909399;
}

.chart-card {
  margin-bottom: 20px;
}

.table-card {
  margin-bottom: 20px;
}
</style>
