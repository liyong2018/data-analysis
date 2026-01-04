/**
 * 批量更新表格组件脚本
 * 自动更新所有 Table*.vue 文件以使用新的验证系统
 */

const fs = require('fs');
const path = require('path');

const viewsDir = path.join(__dirname, 'src/views');

// 需要更新的表格文件列表
const tablesToUpdate = [
  { file: 'TableB02.vue', code: 'B02', api: '/api/tableB02' },
  { file: 'TableF01.vue', code: 'F01', api: '/api/tableF01', hasCompare: true },
  { file: 'TableG01.vue', code: 'G01', api: '/api/tableG01' },
  { file: 'TableG02.vue', code: 'G02', api: '/api/tableG02' },
  { file: 'TableG03.vue', code: 'G03', api: '/api/tableG03' },
  { file: 'TableG04.vue', code: 'G04', api: '/api/tableG04' },
  { file: 'TableG05.vue', code: 'G05', api: '/api/tableG05' },
  { file: 'TableG06.vue', code: 'G06', api: '/api/tableG06' },
  { file: 'TableG07.vue', code: 'G07', api: '/api/tableG07' },
  { file: 'TableH01.vue', code: 'H01', api: '/api/tableH01' },
  { file: 'TableH02.vue', code: 'H02', api: '/api/tableH02' },
  { file: 'TableH03.vue', code: 'H03', api: '/api/tableH03' },
  { file: 'TableH04.vue', code: 'H04', api: '/api/tableH04' },
  { file: 'TableH05.vue', code: 'H05', api: '/api/tableH05' },
  { file: 'TableH06.vue', code: 'H06', api: '/api/tableH06' },
  { file: 'TableH07.vue', code: 'H07', api: '/api/tableH07' },
  { file: 'TableH08.vue', code: 'H08', api: '/api/tableH08' },
  { file: 'TableH09.vue', code: 'H09', api: '/api/tableH09' },
  { file: 'TableH10.vue', code: 'H10', api: '/api/tableH10' },
  { file: 'TableH11.vue', code: 'H11', api: '/api/tableH11' },
  { file: 'TableI01.vue', code: 'I01', api: '/api/tableI01', hasCompare: true },
  { file: 'TableJ01.vue', code: 'J01', api: '/api/tableJ01', hasCompare: true },
];

// 旧的正则表达式模式
const oldImportPattern = /import \{ reactive, watchEffect \} from 'vue'/;
const oldUseValidationPattern = /import \{ reactive \} from 'vue'\s*\/\/ Auto-calculation for parent fields\s*watchEffect\(\(\) => \{[^}]+\}\)/;

// 新的导入语句
const newImport = `import { reactive, computed } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import regionData from '@/assets/regions.json'
import { useTableValidation } from '@/composables/useTableValidation'`;

// 新的验证代码
const newValidationCode = (tableCode, hasCompare) => `// 使用验证 composable（自动计算${hasCompare ? ' + 比较验证' : ''}）
const { validateBeforeSubmit } = useTableValidation('${tableCode}', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单${hasCompare ? '（包含比较规则验证）' : ''}
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('${api}', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}`;

function updateTableFile(tableInfo) {
  const filePath = path.join(viewsDir, tableInfo.file);

  if (!fs.existsSync(filePath)) {
    console.log(`跳过不存在的文件: ${tableInfo.file}`);
    return;
  }

  let content = fs.readFileSync(filePath, 'utf8');

  // 检查是否已经更新过
  if (content.includes('useTableValidation')) {
    console.log(`跳过已更新的文件: ${tableInfo.file}`);
    return;
  }

  // 替换导入语句
  content = content.replace(
    /import \{ reactive, watchEffect \} from 'vue'/,
    "import { reactive, computed } from 'vue'"
  );

  // 添加新的导入
  content = content.replace(
    /import regionData from '@\/assets\/regions\.json'/,
    `import regionData from '@/assets/regions.json'\nimport { useTableValidation } from '@/composables/useTableValidation'`
  );

  // 替换 watchEffect 和 onSubmit 部分
  const watchEffectPattern = /\/\/ Auto-calculation for parent fields[\s\S]*?watchEffect\(\(\) => \{[\s\S]*?\}\)\s*const onSubmit = async \(\) => \{[\s\S]*?try \{[\s\S]*?\} catch \(error\) \{[\s\S]*?\}\s*\}/;

  const newCode = `// 使用验证 composable（自动计算${tableInfo.hasCompare ? ' + 比较验证' : ''}）
const { validateBeforeSubmit } = useTableValidation('${tableInfo.code}', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单${tableInfo.hasCompare ? '（包含比较规则验证）' : ''}
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('${tableInfo.api}', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}`;

  content = content.replace(watchEffectPattern, newCode);

  // 写回文件
  fs.writeFileSync(filePath, content, 'utf8');
  console.log(`✓ 已更新: ${tableInfo.file}`);
}

// 执行批量更新
console.log('开始批量更新表格文件...\n');

tablesToUpdate.forEach(tableInfo => {
  try {
    updateTableFile(tableInfo);
  } catch (error) {
    console.error(`✗ 更新失败: ${tableInfo.file}`, error.message);
  }
});

console.log('\n批量更新完成！');
