<template>
  <div class="import-container">
    <h2>灾害损失统计数据导入</h2>

    <el-card class="import-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span>导入步骤</span>
        </div>
      </template>

      <!-- 步骤1：选择年份 -->
      <div class="step-section">
        <el-steps :active="currentStep" finish-status="success">
          <el-step title="选择受灾年份" />
          <el-step title="上传Excel文件" />
          <el-step title="预览数据" />
          <el-step title="确认导入" />
        </el-steps>
      </div>

      <!-- 步骤1：选择年份 -->
      <div v-if="currentStep === 0" class="step-content">
        <el-form :model="importForm" label-width="120px" style="max-width: 500px; margin: 40px auto;">
          <el-form-item label="选择受灾年份" required>
            <el-date-picker
              v-model="importForm.disasterYear"
              type="year"
              placeholder="选择年份"
              format="YYYY"
              value-format="YYYY"
              style="width: 100%"
            />
          </el-form-item>
          <el-form-item>
            <el-alert
              title="提示"
              type="info"
              :closable="false"
              show-icon
            >
              请选择要导入数据的受灾年份，系统将自动创建时间、更新时间为当前时间
            </el-alert>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="large" @click="nextStep" :disabled="!importForm.disasterYear">
              下一步
            </el-button>
          </el-form-item>
        </el-form>
      </div>

      <!-- 步骤2：上传Excel -->
      <div v-if="currentStep === 1" class="step-content">
        <div class="upload-section">
          <el-upload
            ref="uploadRef"
            class="upload-demo"
            drag
            :auto-upload="false"
            :on-change="handleFileChange"
            :limit="1"
            accept=".xlsx,.xls,.csv"
          >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              拖拽文件到此处 或 <em>点击上传</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                支持 .xlsx、.xls、.csv 格式的文件，文件大小不超过10MB
              </div>
            </template>
          </el-upload>

          <div v-if="uploadedFile" class="file-info">
            <el-icon><document /></el-icon>
            <span>{{ uploadedFile.name }}</span>
            <el-button link type="danger" @click="removeFile">删除</el-button>
          </div>

          <div class="template-download">
            <el-divider>没有模板？</el-divider>
            <el-button type="primary" link @click="downloadTemplate">
              <el-icon><download /></el-icon>
              下载Excel模板
            </el-button>
          </div>

          <div class="step-actions">
            <el-button @click="prevStep">上一步</el-button>
            <el-button type="primary" @click="loadPreviewData" :disabled="!uploadedFile">
              下一步
            </el-button>
          </div>
        </div>
      </div>

      <!-- 步骤3：预览数据 -->
      <div v-if="currentStep === 2" class="step-content">
        <div class="preview-section">
          <div class="preview-info">
            <el-alert
              :title="`共 ${previewData.length} 条数据待导入`"
              type="success"
              :closable="false"
              show-icon
            />
          </div>

          <el-table
            :data="previewData.slice(0, 20)"
            border
            stripe
            max-height="500"
            style="width: 100%"
          >
            <el-table-column prop="region" label="区域" width="120" fixed />
            <el-table-column prop="disasterType" label="灾种" width="150" />
            <el-table-column prop="parentRegion" label="隶属区域" width="150" />
            <el-table-column prop="disasterTime" label="灾害发生时间" width="160" />
            <el-table-column prop="affectedPopulation" label="受灾人口" width="100">
              <template #default="{ row }">
                {{ formatNumber(row.affectedPopulation) }}
              </template>
            </el-table-column>
            <el-table-column prop="deaths" label="死亡人口" width="100">
              <template #default="{ row }">
                {{ formatNumber(row.deaths) }}
              </template>
            </el-table-column>
            <el-table-column prop="directEconomicLoss" label="经济损失(万元)" width="120">
              <template #default="{ row }">
                {{ formatMoney(row.directEconomicLoss) }}
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100">
              <template #default="{ row }">
                <el-tag :type="row.status === 'valid' ? 'success' : 'danger'">
                  {{ row.status === 'valid' ? '有效' : '无效' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="error" label="错误信息" min-width="200" />
          </el-table>

          <div v-if="previewData.length > 20" class="more-info">
            <el-text type="info">仅显示前20条数据，导入时将处理全部数据</el-text>
          </div>

          <div class="validation-summary">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-statistic title="总记录数" :value="previewData.length" />
              </el-col>
              <el-col :span="8">
                <el-statistic title="有效记录" :value="validCount" />
              </el-col>
              <el-col :span="8">
                <el-statistic title="无效记录" :value="invalidCount" />
              </el-col>
            </el-row>
          </div>

          <div class="step-actions">
            <el-button @click="prevStep">上一步</el-button>
            <el-button type="primary" @click="confirmImport" :disabled="validCount === 0">
              确认导入 ({{ validCount }}条)
            </el-button>
          </div>
        </div>
      </div>

      <!-- 步骤4：导入结果 -->
      <div v-if="currentStep === 3" class="step-content">
        <div class="result-section">
          <el-result
            :icon="importResult.success ? 'success' : 'error'"
            :title="importResult.success ? '导入成功' : '导入失败'"
            :sub-title="importResult.message"
          >
            <template #extra>
              <div v-if="importResult.success" class="result-details">
                <el-descriptions :column="2" border>
                  <el-descriptions-item label="成功导入">
                    {{ importResult.successCount }} 条
                  </el-descriptions-item>
                  <el-descriptions-item label="失败记录">
                    {{ importResult.failCount }} 条
                  </el-descriptions-item>
                  <el-descriptions-item label="受灾年份">
                    {{ importForm.disasterYear }} 年
                  </el-descriptions-item>
                  <el-descriptions-item label="导入时间">
                    {{ importResult.importTime }}
                  </el-descriptions-item>
                </el-descriptions>

                <div v-if="importResult.errors && importResult.errors.length > 0" class="error-list">
                  <el-divider content-position="left">失败记录详情</el-divider>
                  <el-collapse v-model="activeErrorCollapse" accordion>
                    <el-collapse-item
                      v-for="(error, index) in importResult.errors"
                      :key="index"
                      :title="`第 ${error.row} 行：${error.error}`"
                    >
                      <div class="error-detail">
                        <p><strong>区域：</strong>{{ error.region }}</p>
                        <p><strong>灾种：</strong>{{ error.disasterType }}</p>
                        <p><strong>错误：</strong>{{ error.error }}</p>
                      </div>
                    </el-collapse-item>
                  </el-collapse>
                </div>
              </div>

              <el-space>
                <el-button type="primary" @click="goToStatistics">查看统计数据</el-button>
                <el-button @click="resetImport">继续导入</el-button>
              </el-space>
            </template>
          </el-result>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type UploadFile } from 'element-plus'
import { Document, Download, UploadFilled } from '@element-plus/icons-vue'
import * as XLSX from 'xlsx'
import disasterApi, { type DisasterStatistics } from '@/api/disasterStatistics'

const router = useRouter()

// 当前步骤
const currentStep = ref(0)

// 导入表单
const importForm = reactive({
  disasterYear: null as number | null
})

// 上传的文件
const uploadedFile = ref<File | null>(null)
const workbook = ref<XLSX.WorkBook | null>(null)

// 预览数据
interface PreviewRow {
  [key: string]: any
  region: string
  disasterType: string
  parentRegion: string
  disasterTime: string
  affectedPopulation?: number
  deaths?: number
  missing?: number
  injured?: number
  seriouslyInjured?: number
  emergencyTransfer?: number
  transferredPopulationCumulative?: number
  transferredPopulationRealtime?: number
  // ... 其他字段
  directEconomicLoss?: number
  status: 'valid' | 'invalid'
  error?: string
  rawData?: any
}

const previewData = ref<PreviewRow[]>([])
const activeErrorCollapse = ref('')

// 导入结果
const importResult = reactive({
  success: false,
  message: '',
  successCount: 0,
  failCount: 0,
  importTime: '',
  errors: [] as Array<{ row: number; region: string; disasterType: string; error: string }>
})

// 计算有效/无效记录数
const validCount = computed(() => previewData.value.filter(r => r.status === 'valid').length)
const invalidCount = computed(() => previewData.value.filter(r => r.status === 'invalid').length)

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

// 下一步
const nextStep = () => {
  currentStep.value++
}

// 上一步
const prevStep = () => {
  currentStep.value--
}

// 处理文件变化
const handleFileChange = (file: UploadFile) => {
  const rawFile = file.raw
  if (!rawFile) return
  uploadedFile.value = rawFile

  const reader = new FileReader()
  reader.onload = (e) => {
    try {
      const data = new Uint8Array(e.target?.result as ArrayBuffer)
      workbook.value = XLSX.read(data, { type: 'array' })
    } catch (error) {
      ElMessage.error('文件解析失败，请检查文件格式')
      uploadedFile.value = null
      workbook.value = null
    }
  }
  reader.readAsArrayBuffer(rawFile)
}

// 删除文件
const removeFile = () => {
  uploadedFile.value = null
  workbook.value = null
}

// 下载模板
const downloadTemplate = () => {
  // 创建模板数据
  const template = [
    {
      '区域': '示例区域',
      '灾种': '洪涝灾害',
      '隶属区域': '四川省-成都市',
      '灾害发生时间': '2024-08-08 06:42:47',
      '受淹乡镇（街道）数量(个)': 0,
      '受灾人口(人)': 171,
      '因灾死亡人口(人)': 0,
      '因灾失踪人口(人)': 0,
      '因灾受伤人口(人)': 0,
      '其中：因灾重伤人口(人)': 0,
      '紧急避险转移人口(人)': 0,
      '紧急转移安置人口(累计值)(人)': 171,
      '紧急转移安置人口(实时值)(人)': 0,
      '其中：集中安置人口(累计值)(人)': 0,
      '其中：集中安置人口(实时值)(人)': 0,
      '分散安置人口(累计值)(人)': 0,
      '分散安置人口(实时值)(人)': 0,
      '集中安置点数量(累计值)(个)': 0,
      '集中安置点数量(实时值)(个)': 0,
      '需紧急生活救助人口(累计值)(人)': 0,
      '需紧急生活救助人口(实时值)(人)': 0,
      '需过渡期生活救助人口(人)': 0,
      '因旱需生活救助人口(累计值)(人)': 0,
      '因旱需生活救助人口(实时值)(人)': 0,
      '其中：因旱饮水困难需救助人口(累计值)(人)': 0,
      '其中：因旱饮水困难需救助人口(实时值)(人)': 0,
      '倒塌房屋间数(间)': 0,
      '其中：倒塌住房间数(间)': 0,
      '倒塌住房户数(户)': 0,
      '严重损坏房屋间数(间)': 0,
      '其中：严重损坏住房间数(间)': 0,
      '严重损坏住房户数(户)': 0,
      '一般损坏房屋间数(间)': 0,
      '其中：一般损坏住房间数(间)': 0,
      '一般损坏住房户数(户)': 0,
      '农作物受灾面积(公顷)': 0,
      '其中：粮食作物受灾面积(公顷)': 0,
      '农作物成灾面积(公顷)': 0,
      '其中：粮食作物成灾面积(公顷)': 0,
      '农作物绝收面积(公顷)': 0,
      '其中：粮食作物绝收面积(公顷)': 0,
      '草场受灾面积(公顷)': 0,
      '林地受灾面积(公顷)': 0,
      '因灾死亡牲畜(头只)': 0,
      '其中：因灾死亡大牲畜(头只)': 0,
      '因灾死亡小牲畜(头只)': 0,
      '因旱饮水困难牲畜(头只)': 0,
      '其中：因旱饮水困难大牲畜(头只)': 0,
      '其中：因旱饮水困难小牲畜(头只)': 0,
      '水产养殖受灾面积(公顷)': 0,
      '受损工业企业数量(个)': 0,
      '受损商贸网点数量(个)': 0,
      '受损公路长度(千米)': 0,
      '受损通信线路长度(皮长公里)': 0,
      '受损通信基站数量(个)': 0,
      '受损电力线路长度(千米)': 0,
      '受损输变电设备数量(台套)': 0,
      '受损水库数量(个)': 0,
      '受损堤防长度(千米)': 0,
      '受损市政道路长度(千米)': 0,
      '受损市政供水管网长度(千米)': 0,
      '受损学校数量(个)': 0,
      '受损医院和卫生机构数量(个)': 0,
      '直接经济损失(万元)': 0,
      '其中：住房及居民家庭财产损失(万元)': 0,
      '农林牧渔业损失(万元)': 0,
      '工矿商贸业损失(万元)': 0,
      '基础设施损失(万元)': 0,
      '公共服务损失(万元)': 0,
      '其他损失(万元)': 0
    }
  ]

  const ws = XLSX.utils.json_to_sheet(template)
  const wb = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(wb, ws, '灾害损失统计模板')
  XLSX.writeFile(wb, '灾害损失统计导入模板.xlsx')
}

// 预览数据
const loadPreviewData = () => {
  if (!workbook.value) {
    ElMessage.error('请先上传文件')
    return
  }

  try {
    if (!workbook.value?.SheetNames?.length) {
      ElMessage.error('Excel文件为空或格式错误')
      return
    }

    const sheetName = workbook.value.SheetNames[0]!
    const worksheet = workbook.value.Sheets[sheetName]
    if (!worksheet) {
      ElMessage.error('无法读取工作表内容')
      return
    }
    // 使用 header: 0 获取第一行作为表头
    const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 0 }) as any[]

    // 过滤掉空行和标题行
    const validRows = jsonData.filter(row =>
      row && row['区域'] && row['区域'] !== '区域' && row['区域'] !== '示例区域'
    )

    // 处理数据
    const processed: PreviewRow[] = validRows.map((row, index) => {
      const result: PreviewRow = {
        region: row['区域'] || '',
        disasterType: row['灾种'] || '',
        parentRegion: row['隶属区域'] || '',
        disasterTime: row['灾害发生时间'] || '',
        affectedPopulation: parseNumber(row['受灾人口(人)']),
        deaths: parseNumber(row['因灾死亡人口(人)']),
        missing: parseNumber(row['因灾失踪人口(人)']),
        injured: parseNumber(row['因灾受伤人口(人)']),
        seriouslyInjured: parseNumber(row['其中：因灾重伤人口(人)']),
        emergencyTransfer: parseNumber(row['紧急避险转移人口(人)']),
        transferredPopulationCumulative: parseNumber(row['紧急转移安置人口(累计值)(人)']),
        transferredPopulationRealtime: parseNumber(row['紧急转移安置人口(实时值)(人)']),
        centralizedPopulationCumulative: parseNumber(row['其中：集中安置人口(累计值)(人)']),
        centralizedPopulationRealtime: parseNumber(row['其中：集中安置人口(实时值)(人)']),
        dispersedPopulationCumulative: parseNumber(row['分散安置人口(累计值)(人)']),
        dispersedPopulationRealtime: parseNumber(row['分散安置人口(实时值)(人)']),
        centralizedSitesCumulative: parseNumber(row['集中安置点数量(累计值)(个)']),
        centralizedSitesRealtime: parseNumber(row['集中安置点数量(实时值)(个)']),
        emergencyReliefCumulative: parseNumber(row['需紧急生活救助人口(累计值)(人)']),
        emergencyReliefRealtime: parseNumber(row['需紧急生活救助人口(实时值)(人)']),
        transitionalRelief: parseNumber(row['需过渡期生活救助人口(人)']),
        droughtReliefCumulative: parseNumber(row['因旱需生活救助人口(累计值)(人)']),
        droughtReliefRealtime: parseNumber(row['因旱需生活救助人口(实时值)(人)']),
        droughtDrinkingWaterCumulative: parseNumber(row['其中：因旱饮水困难需救助人口(累计值)(人)']),
        droughtDrinkingWaterRealtime: parseNumber(row['其中：因旱饮水困难需救助人口(实时值)(人)']),
        collapsedRooms: parseNumber(row['倒塌房屋间数(间)']),
        collapsedHousingRooms: parseNumber(row['其中：倒塌住房间数(间)']),
        collapsedHousingHouseholds: parseNumber(row['倒塌住房户数(户)']),
        seriouslyDamagedRooms: parseNumber(row['严重损坏房屋间数(间)']),
        seriouslyDamagedHousingRooms: parseNumber(row['其中：严重损坏住房间数(间)']),
        seriouslyDamagedHousingHouseholds: parseNumber(row['严重损坏住房户数(户)']),
        generallyDamagedRooms: parseNumber(row['一般损坏房屋间数(间)']),
        generallyDamagedHousingRooms: parseNumber(row['其中：一般损坏住房间数(间)']),
        generallyDamagedHousingHouseholds: parseNumber(row['一般损坏住房户数(户)']),
        cropsAffectedArea: parseNumber(row['农作物受灾面积(公顷)']),
        grainCropsAffectedArea: parseNumber(row['其中：粮食作物受灾面积(公顷)']),
        cropsDamagedArea: parseNumber(row['农作物成灾面积(公顷)']),
        grainCropsDamagedArea: parseNumber(row['其中：粮食作物成灾面积(公顷)']),
        cropsLostArea: parseNumber(row['农作物绝收面积(公顷)']),
        grainCropsLostArea: parseNumber(row['其中：粮食作物绝收面积(公顷)']),
        grasslandAffectedArea: parseNumber(row['草场受灾面积(公顷)']),
        forestAffectedArea: parseNumber(row['林地受灾面积(公顷)']),
        livestockDeaths: parseNumber(row['因灾死亡牲畜(头只)']),
        largeLivestockDeaths: parseNumber(row['其中：因灾死亡大牲畜(头只)']),
        smallLivestockDeaths: parseNumber(row['因灾死亡小牲畜(头只)']),
        droughtDrinkingWaterLivestock: parseNumber(row['因旱饮水困难牲畜(头只)']),
        droughtDrinkingWaterLargeLivestock: parseNumber(row['其中：因旱饮水困难大牲畜(头只)']),
        droughtDrinkingWaterSmallLivestock: parseNumber(row['其中：因旱饮水困难小牲畜(头只)']),
        aquacultureAffectedArea: parseNumber(row['水产养殖受灾面积(公顷)']),
        damagedIndustrialEnterprises: parseNumber(row['受损工业企业数量(个)']),
        damagedCommercialOutlets: parseNumber(row['受损商贸网点数量(个)']),
        damagedRoadLength: parseNumber(row['受损公路长度(千米)']),
        damagedCommunicationLineLength: parseNumber(row['受损通信线路长度(皮长公里)']),
        damagedBaseStations: parseNumber(row['受损通信基站数量(个)']),
        damagedPowerLineLength: parseNumber(row['受损电力线路长度(千米)']),
        damagedPowerEquipment: parseNumber(row['受损输变电设备数量(台套)']),
        damagedReservoirs: parseNumber(row['受损水库数量(个)']),
        damagedDikeLength: parseNumber(row['受损堤防长度(千米)']),
        damagedMunicipalRoadLength: parseNumber(row['受损市政道路长度(千米)']),
        damagedWaterPipeLength: parseNumber(row['受损市政供水管网长度(千米)']),
        damagedSchools: parseNumber(row['受损学校数量(个)']),
        damagedHospitals: parseNumber(row['受损医院和卫生机构数量(个)']),
        directEconomicLoss: parseNumber(row['直接经济损失(万元)']),
        housingPropertyLoss: parseNumber(row['其中：住房及居民家庭财产损失(万元)']),
        agricultureLoss: parseNumber(row['农林牧渔业损失(万元)']),
        industryCommerceLoss: parseNumber(row['工矿商贸业损失(万元)']),
        infrastructureLoss: parseNumber(row['基础设施损失(万元)']),
        publicServiceLoss: parseNumber(row['公共服务损失(万元)']),
        otherLoss: parseNumber(row['其他损失(万元)']),
        rawData: row,
        status: 'valid' as 'valid' | 'invalid',
        error: ''
      }

      // 验证数据
      const validation = validateRow(result)
      if (!validation.valid) {
        result.status = 'invalid'
        result.error = validation.error
      }

      return result
    })

    previewData.value = processed
    nextStep()
  } catch (error) {
    ElMessage.error('文件解析失败，请检查文件格式')
  }
}

// 解析数字
const parseNumber = (value: any): number | undefined => {
  if (value === null || value === undefined || value === '') {
    return undefined
  }
  const num = Number(value)
  return isNaN(num) ? undefined : num
}

// 验证单行数据
const validateRow = (row: PreviewRow): { valid: boolean; error?: string } => {
  if (!row.region) {
    return { valid: false, error: '区域不能为空' }
  }
  if (!row.disasterType) {
    return { valid: false, error: '灾种不能为空' }
  }
  if (!row.disasterTime) {
    return { valid: false, error: '灾害发生时间不能为空' }
  }
  return { valid: true }
}

// 确认导入
const confirmImport = async () => {
  const validData = previewData.value.filter(r => r.status === 'valid')

  if (validData.length === 0) {
    ElMessage.error('没有有效数据可以导入')
    return
  }

  // 格式化日期为 ISO 格式
  const formatDateTime = (dateStr: string | undefined): string | null => {
    if (!dateStr || dateStr === '--' || dateStr === '') return null
    const str = String(dateStr).trim()
    // 如果已经是 ISO 格式，直接返回
    if (str.includes('T')) return str
    // 如果包含空格，替换为 T
    if (str.includes(' ')) return str.replace(' ', 'T')
    // 否则添加时间部分
    return str + 'T00:00:00'
  }

  try {
    // 转换数据格式
    const importData = validData.map(row => ({
      region: row.region,
      disasterType: row.disasterType,
      parentRegion: row.parentRegion,
      disasterTime: formatDateTime(row.disasterTime),
      disasterYear: importForm.disasterYear,
      floodedTowns: row.floodedTowns,
      affectedPopulation: row.affectedPopulation,
      deaths: row.deaths,
      missing: row.missing,
      injured: row.injured,
      seriouslyInjured: row.seriouslyInjured,
      emergencyTransfer: row.emergencyTransfer,
      transferredPopulationCumulative: row.transferredPopulationCumulative,
      transferredPopulationRealtime: row.transferredPopulationRealtime,
      centralizedPopulationCumulative: row.centralizedPopulationCumulative,
      centralizedPopulationRealtime: row.centralizedPopulationRealtime,
      dispersedPopulationCumulative: row.dispersedPopulationCumulative,
      dispersedPopulationRealtime: row.dispersedPopulationRealtime,
      centralizedSitesCumulative: row.centralizedSitesCumulative,
      centralizedSitesRealtime: row.centralizedSitesRealtime,
      emergencyReliefCumulative: row.emergencyReliefCumulative,
      emergencyReliefRealtime: row.emergencyReliefRealtime,
      transitionalRelief: row.transitionalRelief,
      droughtReliefCumulative: row.droughtReliefCumulative,
      droughtReliefRealtime: row.droughtReliefRealtime,
      droughtDrinkingWaterCumulative: row.droughtDrinkingWaterCumulative,
      droughtDrinkingWaterRealtime: row.droughtDrinkingWaterRealtime,
      collapsedRooms: row.collapsedRooms,
      collapsedHousingRooms: row.collapsedHousingRooms,
      collapsedHousingHouseholds: row.collapsedHousingHouseholds,
      seriouslyDamagedRooms: row.seriouslyDamagedRooms,
      seriouslyDamagedHousingRooms: row.seriouslyDamagedHousingRooms,
      seriouslyDamagedHousingHouseholds: row.seriouslyDamagedHousingHouseholds,
      generallyDamagedRooms: row.generallyDamagedRooms,
      generallyDamagedHousingRooms: row.generallyDamagedHousingRooms,
      generallyDamagedHousingHouseholds: row.generallyDamagedHousingHouseholds,
      cropsAffectedArea: row.cropsAffectedArea,
      grainCropsAffectedArea: row.grainCropsAffectedArea,
      cropsDamagedArea: row.cropsDamagedArea,
      grainCropsDamagedArea: row.grainCropsDamagedArea,
      cropsLostArea: row.cropsLostArea,
      grainCropsLostArea: row.grainCropsLostArea,
      grasslandAffectedArea: row.grasslandAffectedArea,
      forestAffectedArea: row.forestAffectedArea,
      livestockDeaths: row.livestockDeaths,
      largeLivestockDeaths: row.largeLivestockDeaths,
      smallLivestockDeaths: row.smallLivestockDeaths,
      droughtDrinkingWaterLivestock: row.droughtDrinkingWaterLivestock,
      droughtDrinkingWaterLargeLivestock: row.droughtDrinkingWaterLargeLivestock,
      droughtDrinkingWaterSmallLivestock: row.droughtDrinkingWaterSmallLivestock,
      aquacultureAffectedArea: row.aquacultureAffectedArea,
      damagedIndustrialEnterprises: row.damagedIndustrialEnterprises,
      damagedCommercialOutlets: row.damagedCommercialOutlets,
      damagedRoadLength: row.damagedRoadLength,
      damagedCommunicationLineLength: row.damagedCommunicationLineLength,
      damagedBaseStations: row.damagedBaseStations,
      damagedPowerLineLength: row.damagedPowerLineLength,
      damagedPowerEquipment: row.damagedPowerEquipment,
      damagedReservoirs: row.damagedReservoirs,
      damagedDikeLength: row.damagedDikeLength,
      damagedMunicipalRoadLength: row.damagedMunicipalRoadLength,
      damagedWaterPipeLength: row.damagedWaterPipeLength,
      damagedSchools: row.damagedSchools,
      damagedHospitals: row.damagedHospitals,
      directEconomicLoss: row.directEconomicLoss,
      housingPropertyLoss: row.housingPropertyLoss,
      agricultureLoss: row.agricultureLoss,
      industryCommerceLoss: row.industryCommerceLoss,
      infrastructureLoss: row.infrastructureLoss,
      publicServiceLoss: row.publicServiceLoss,
      otherLoss: row.otherLoss
    }))

    // 调用批量保存接口
    await disasterApi.saveBatch(importData as unknown as DisasterStatistics[])

    // 导入成功
    importResult.success = true
    importResult.message = '数据导入成功'
    importResult.successCount = validData.length
    importResult.failCount = previewData.value.length - validData.length
    importResult.importTime = new Date().toLocaleString('zh-CN')

    nextStep()
  } catch (error) {
    importResult.success = false
    importResult.message = '导入失败：' + (error as any).message
    nextStep()
  }
}

// 重置导入
const resetImport = () => {
  currentStep.value = 0
  importForm.disasterYear = null
  uploadedFile.value = null
  workbook.value = null
  previewData.value = []
  Object.assign(importResult, {
    success: false,
    message: '',
    successCount: 0,
    failCount: 0,
    importTime: '',
    errors: []
  })
}

// 查看统计数据
const goToStatistics = () => {
  router.push('/disaster-statistics')
}
</script>

<style scoped>
.import-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.import-card {
  min-height: 600px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.step-section {
  padding: 40px 20px;
}

.step-content {
  padding: 40px 20px;
}

.upload-section {
  max-width: 600px;
  margin: 0 auto;
}

.upload-demo {
  margin-bottom: 20px;
}

.file-info {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px;
  background: #f5f7fa;
  border-radius: 4px;
  margin-bottom: 20px;
}

.template-download {
  text-align: center;
  margin-bottom: 20px;
}

.preview-section,
.result-section {
  padding: 20px;
}

.preview-info {
  margin-bottom: 20px;
}

.more-info {
  margin-top: 10px;
}

.validation-summary {
  margin: 20px 0;
}

.step-actions {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 40px;
}

.result-details {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
}

.error-list {
  margin-top: 20px;
}

.error-detail p {
  margin: 5px 0;
}
</style>
