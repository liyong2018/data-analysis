/**
 * 表格验证逻辑更新脚本
 * 用于批量更新表格组件以使用新的验证系统
 */

const tables = [
  'TableC01', 'TableD01', 'TableE01', 'TableF01',
  'TableG01', 'TableG02', 'TableG03', 'TableG04', 'TableG05', 'TableG06', 'TableG07',
  'TableH01', 'TableH02', 'TableH03', 'TableH04', 'TableH05', 'TableH06', 'TableH07',
  'TableH08', 'TableH09', 'TableH10', 'TableH11',
  'TableI01', 'TableJ01', 'TableZ01'
]

// 每个表格对应的字段代码前缀
const tablePrefixes = {
  'TableC01': 'colC01',
  'TableD01': 'colD01',
  'TableE01': 'colE01',
  'TableF01': 'colF01',
  'TableG01': 'colG01',
  'TableG02': 'colG02',
  'TableG03': 'colG03',
  'TableG04': 'colG04',
  'TableG05': 'colG05',
  'TableG06': 'colG06',
  'TableG07': 'colG07',
  'TableH01': 'colH01',
  'TableH02': 'colH02',
  'TableH03': 'colH03',
  'TableH04': 'colH04',
  'TableH05': 'colH05',
  'TableH06': 'colH06',
  'TableH07': 'colH07',
  'TableH08': 'colH08',
  'TableH09': 'colH09',
  'TableH10': 'colH10',
  'TableH11': 'colH11',
  'TableI01': 'colI01',
  'TableJ01': 'colJ01',
  'TableZ01': 'colZ01'
}

// 获取表格代码（如 TableA01 -> A01）
const getTableCode = (tableName) => tableName.replace('Table', '')

// 生成替换脚本
const generateReplacement = (tableName) => {
  const tableCode = getTableCode(tableName)
  const prefix = tablePrefixes[tableName]

  return `
// 在 ${tableName}.vue 中进行以下替换:

// 1. 替换 import 语句
// 原来的: import { reactive, watchEffect } from 'vue'
// 替换为: import { reactive, computed } from 'vue'
// 并添加: import { useTableValidation } from '@/composables/useTableValidation'

// 2. 删除 watchEffect 部分，替换为:
// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('${tableCode}', computed(() => form) as any)

// 3. 修改 onSubmit 函数:
const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/${tableName}', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
`
}

// 打印所有表格的更新说明
console.log('=== 表格验证逻辑更新说明 ===\n')

tables.forEach(table => {
  console.log(generateReplacement(table))
  console.log('---\n')
})
