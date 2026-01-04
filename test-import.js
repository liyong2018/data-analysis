const XLSX = require('xlsx');
const axios = require('axios');
const path = require('path');

const API_BASE = 'http://localhost:8081';

// Parse Excel file
function parseExcel(filePath) {
  const workbook = XLSX.readFile(filePath);
  const worksheet = workbook.Sheets[workbook.SheetNames[0]];
  const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 0 });

  console.log(`Total rows in Excel: ${jsonData.length}`);

  // Filter out header rows and empty rows
  const validRows = jsonData.filter(row =>
    row && row['区域'] && row['区域'] !== '区域' && row['区域'] !== '示例区域'
  );

  console.log(`Valid data rows: ${validRows.length}`);

  return validRows;
}

// Convert Excel row to API format
function convertToApiFormat(row, year) {
  const parseNumber = (val) => {
    if (val === undefined || val === null || val === '') return 0;
    const num = typeof val === 'number' ? val : parseFloat(String(val).replace(/,/g, ''));
    return isNaN(num) ? 0 : num;
  };

  const parseDateTime = (val) => {
    if (!val) return null;
    // If it's already a valid date string
    const str = String(val).trim();
    if (str.includes('T')) return str;
    if (str.includes(' ')) return str.replace(' ', 'T');
    return str + 'T00:00:00';
  };

  return {
    region: row['区域'] || '',
    disasterType: row['灾种'] || '',
    parentRegion: row['隶属区域'] || '',
    disasterTime: parseDateTime(row['灾害发生时间']),
    disasterYear: year || 2024,
    floodedTowns: parseNumber(row['受淹乡镇（街道）数量(个)']),
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
    otherLoss: parseNumber(row['其他损失(万元)'])
  };
}

// Main function
async function testImport() {
  const excelPath = path.join(__dirname, '组合查询表2024.xlsx');

  console.log('Reading Excel file:', excelPath);
  const rows = parseExcel(excelPath);

  if (rows.length === 0) {
    console.log('No valid data found in Excel file');
    return;
  }

  // Show sample data
  console.log('\nSample data (first 2 rows):');
  for (let i = 0; i < Math.min(2, rows.length); i++) {
    console.log(`Row ${i + 1}:`, {
      区域: rows[i]['区域'],
      灾种: rows[i]['灾种'],
      受灾人口: rows[i]['受灾人口(人)']
    });
  }

  // Convert to API format
  console.log('\nConverting to API format...');
  const apiData = rows.map(row => convertToApiFormat(row, 2024));

  // Show sample converted data
  console.log('\nSample converted data (first row):');
  console.log(JSON.stringify(apiData[0], null, 2));

  // Send to API (in batches of 100)
  const batchSize = 100;
  let successCount = 0;
  let failCount = 0;

  console.log(`\nSending ${apiData.length} records to API in batches of ${batchSize}...`);

  for (let i = 0; i < apiData.length; i += batchSize) {
    const batch = apiData.slice(i, i + batchSize);
    const batchNum = Math.floor(i / batchSize) + 1;
    const totalBatches = Math.ceil(apiData.length / batchSize);

    try {
      console.log(`Sending batch ${batchNum}/${totalBatches} (${batch.length} records)...`);
      const response = await axios.post(`${API_BASE}/api/disaster-statistics/batch`, batch, {
        headers: { 'Content-Type': 'application/json' }
      });
      console.log(`Batch ${batchNum} success: ${response.data}`);
      successCount += batch.length;
    } catch (error) {
      console.error(`Batch ${batchNum} failed:`, error.response?.data || error.message);
      failCount += batch.length;
    }
  }

  console.log(`\nImport complete! Success: ${successCount}, Failed: ${failCount}`);

  // Verify by querying the API
  console.log('\nVerifying import by querying API...');
  try {
    const response = await axios.get(`${API_BASE}/api/disaster-statistics/overview`);
    console.log('Overview:', response.data);
  } catch (error) {
    console.error('Failed to verify:', error.response?.data || error.message);
  }
}

// Run
testImport().catch(console.error);
