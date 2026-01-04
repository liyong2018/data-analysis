/**
 * 灾害统计相关 API
 */
import axios from 'axios'

export interface DisasterStatistics {
  id?: number
  region: string
  disasterType: string
  parentRegion: string
  disasterTime: string
  disasterYear: number
  floodedTowns?: number
  affectedPopulation?: number
  deaths?: number
  missing?: number
  injured?: number
  seriouslyInjured?: number
  emergencyTransfer?: number
  transferredPopulationCumulative?: number
  transferredPopulationRealtime?: number
  centralizedPopulationCumulative?: number
  centralizedPopulationRealtime?: number
  dispersedPopulationCumulative?: number
  dispersedPopulationRealtime?: number
  centralizedSitesCumulative?: number
  centralizedSitesRealtime?: number
  emergencyReliefCumulative?: number
  emergencyReliefRealtime?: number
  transitionalRelief?: number
  droughtReliefCumulative?: number
  droughtReliefRealtime?: number
  droughtDrinkingWaterCumulative?: number
  droughtDrinkingWaterRealtime?: number
  collapsedRooms?: number
  collapsedHousingRooms?: number
  collapsedHousingHouseholds?: number
  seriouslyDamagedRooms?: number
  seriouslyDamagedHousingRooms?: number
  seriouslyDamagedHousingHouseholds?: number
  generallyDamagedRooms?: number
  generallyDamagedHousingRooms?: number
  generallyDamagedHousingHouseholds?: number
  cropsAffectedArea?: number
  grainCropsAffectedArea?: number
  cropsDamagedArea?: number
  grainCropsDamagedArea?: number
  cropsLostArea?: number
  grainCropsLostArea?: number
  grasslandAffectedArea?: number
  forestAffectedArea?: number
  livestockDeaths?: number
  largeLivestockDeaths?: number
  smallLivestockDeaths?: number
  droughtDrinkingWaterLivestock?: number
  droughtDrinkingWaterLargeLivestock?: number
  droughtDrinkingWaterSmallLivestock?: number
  aquacultureAffectedArea?: number
  damagedIndustrialEnterprises?: number
  damagedCommercialOutlets?: number
  damagedRoadLength?: number
  damagedCommunicationLineLength?: number
  damagedBaseStations?: number
  damagedPowerLineLength?: number
  damagedPowerEquipment?: number
  damagedReservoirs?: number
  damagedDikeLength?: number
  damagedMunicipalRoadLength?: number
  damagedWaterPipeLength?: number
  damagedSchools?: number
  damagedHospitals?: number
  directEconomicLoss?: number
  housingPropertyLoss?: number
  agricultureLoss?: number
  industryCommerceLoss?: number
  infrastructureLoss?: number
  publicServiceLoss?: number
  otherLoss?: number
  createdBy?: string
  createdAt?: string
  updatedBy?: string
  updatedAt?: string
}

export interface StatisticsData {
  year?: number
  disasterType?: string
  region?: string
  totalAffected?: number
  totalDeaths?: number
  totalLoss?: number
  count?: number
}

export interface FilterOptions {
  years: number[]
  disasterTypes: string[]
  regions: string[]
  disasterTypeTree: TreeNode[]
  regionTree: TreeNode[]
}

export interface TreeNode {
  value: string
  label: string
  level?: string
  children?: TreeNode[]
}

export interface OverviewData {
  totalCount: number
  totalAffected: number
  totalDeaths: number
  totalLoss: number
  yearlyStats: StatisticsData[]
  disasterTypeStats: StatisticsData[]
  regionStats: StatisticsData[]
}

const api = {
  // 保存或更新
  save(data: DisasterStatistics) {
    return axios.post('/api/disaster-statistics', data)
  },

  // 批量保存
  saveBatch(data: DisasterStatistics[]) {
    return axios.post('/api/disaster-statistics/batch', data)
  },

  // 删除
  delete(id: number) {
    return axios.delete(`/api/disaster-statistics/${id}`)
  },

  // 获取列表
  list() {
    return axios.get<DisasterStatistics[]>('/api/disaster-statistics')
  },

  // 分页查询
  page(params: {
    current?: number
    size?: number
    region?: string
    disasterType?: string
    year?: number
  }) {
    return axios.get('/api/disaster-statistics/page', { params })
  },

  // 按年份统计
  statisticsByYear() {
    return axios.get<StatisticsData[]>('/api/disaster-statistics/statistics/by-year')
  },

  // 按灾种统计
  statisticsByDisasterType() {
    return axios.get<StatisticsData[]>('/api/disaster-statistics/statistics/by-disaster-type')
  },

  // 按区域统计
  statisticsByRegion() {
    return axios.get<StatisticsData[]>('/api/disaster-statistics/statistics/by-region')
  },

  // 多维度统计
  multiDimensionStatistics(params: {
    year?: number
    disasterType?: string
    region?: string
  }) {
    return axios.get<StatisticsData[]>('/api/disaster-statistics/statistics/multi-dimension', { params })
  },

  // 获取筛选选项
  getFilterOptions() {
    return axios.get<FilterOptions>('/api/disaster-statistics/filter-options')
  },

  // 获取统计概览（支持筛选条件）
  getOverview(params?: {
    year?: number
    disasterType?: string
    region?: string
  }) {
    return axios.get<OverviewData>('/api/disaster-statistics/overview', { params })
  }
}

export default api
