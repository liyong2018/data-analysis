<script setup lang="ts">
import { computed, ref } from 'vue'
import { useRoute } from 'vue-router'
import { Search } from '@element-plus/icons-vue'

type MenuItem = {
  id: string
  label: string
  path: string
}

const route = useRoute()
const filterText = ref('')

const groupNames: Record<string, string> = {
  A: 'A 人员受灾情况',
  B: 'B 住房受损情况',
  C: 'C 居民家庭财产损失',
  D: 'D 农林牧渔业损失',
  E: 'E 工业损失',
  F: 'F 服务业损失',
  G: 'G 基础设施',
  H: 'H 公共服务',
  I: 'I 资源与环境损失',
  J: 'J 基础指标',
  Z: 'Z 经济损失统计',
  S: 'S 统计分析'
}

const tableItems: MenuItem[] = [
  { id: 'A01', label: 'A01 人员受灾情况', path: '/tableA01' },
  { id: 'B01', label: 'B01 农村居民住房受损情况', path: '/tableB01' },
  { id: 'B02', label: 'B02 城镇居民住房受损情况', path: '/tableB02' },
  { id: 'C01', label: 'C01 居民家庭财产损失', path: '/tableC01' },
  { id: 'D01', label: 'D01 农林牧渔业损失', path: '/tableD01' },
  { id: 'E01', label: 'E01 工业损失', path: '/tableE01' },
  { id: 'F01', label: 'F01 服务业损失', path: '/tableF01' },
  { id: 'G01', label: 'G01 交通运输损失', path: '/tableG01' },
  { id: 'G02', label: 'G02 通信损失', path: '/tableG02' },
  { id: 'G03', label: 'G03 能源损失', path: '/tableG03' },
  { id: 'G04', label: 'G04 水利损失', path: '/tableG04' },
  { id: 'G05', label: 'G05 市政损失', path: '/tableG05' },
  { id: 'G06', label: 'G06 农村地区生活设施损失', path: '/tableG06' },
  { id: 'G07', label: 'G07 地质灾害防治设施损失', path: '/tableG07' },
  { id: 'H01', label: 'H01 教育系统损失', path: '/tableH01' },
  { id: 'H02', label: 'H02 科技系统损失', path: '/tableH02' },
  { id: 'H03', label: 'H03 医疗卫生系统损失', path: '/tableH03' },
  { id: 'H04', label: 'H04 文化系统损失', path: '/tableH04' },
  { id: 'H05', label: 'H05 广播电视系统损失', path: '/tableH05' },
  { id: 'H06', label: 'H06 新闻出版系统损失', path: '/tableH06' },
  { id: 'H07', label: 'H07 体育损失', path: '/tableH07' },
  { id: 'H08', label: 'H08 社会保障与社会服务系统损失', path: '/tableH08' },
  { id: 'H09', label: 'H09 公安系统和国家综合性消防救援队伍损失', path: '/tableH09' },
  { id: 'H10', label: 'H10 社会管理系统损失', path: '/tableH10' },
  { id: 'H11', label: 'H11 文化遗产损失', path: '/tableH11' },
  { id: 'I01', label: 'I01 资源与环境损失', path: '/tableI01' },
  { id: 'J01', label: 'J01 基础指标', path: '/tableJ01' },
  { id: 'Z01', label: 'Z01 经济损失统计', path: '/tableZ01' },
]

const extraItems: MenuItem[] = [
  { id: 'S01', label: '统计分析', path: '/disaster-statistics' },
  { id: 'S02', label: '数据导入', path: '/disaster-statistics-import' },
]

const menuGroups = computed(() => {
  const groups = new Map<string, MenuItem[]>()
  const filter = filterText.value.trim().toLowerCase()

  const processItem = (item: MenuItem, groupKey: string) => {
    if (!filter || item.label.toLowerCase().includes(filter) || item.id.toLowerCase().includes(filter)) {
      const list = groups.get(groupKey) ?? []
      list.push(item)
      groups.set(groupKey, list)
    }
  }

  for (const item of tableItems) {
    const letter = item.id.slice(0, 1).toUpperCase()
    processItem(item, letter)
  }

  for (const item of extraItems) {
    const letter = 'S'
    processItem(item, letter)
  }

  return Array.from(groups.entries())
    .sort((a, b) => a[0].localeCompare(b[0]))
    .map(([letter, items]) => ({
      letter,
      label: groupNames[letter] || letter,
      items: items.slice().sort((a, b) => a.id.localeCompare(b.id)),
    }))
})

const activePath = computed(() => route.path)

const openedGroups = computed(() => {
  if (filterText.value) {
    // 搜索时展开所有有结果的组
    return menuGroups.value.map(g => g.letter)
  }
  const m = /^\/table([A-Za-z])/.exec(route.path)
  if (m?.[1]) return [m[1].toUpperCase()]
  if (route.path.startsWith('/disaster-statistics')) return ['S']
  return []
})
</script>

<template>
  <el-container class="app-shell">
    <el-aside class="app-aside">
      <div class="aside-brand">灾害损失录入</div>
      <div class="aside-search">
        <el-input
          v-model="filterText"
          placeholder="输入关键词过滤菜单"
          :prefix-icon="Search"
          clearable
        />
      </div>
      <el-scrollbar class="aside-scroll">
        <el-menu :default-active="activePath" :default-openeds="openedGroups" router unique-opened>
          <el-menu-item index="/">首页</el-menu-item>
          <el-sub-menu v-for="group in menuGroups" :key="group.letter" :index="group.letter">
            <template #title>{{ group.label }}</template>
            <el-menu-item v-for="item in group.items" :key="item.path" :index="item.path">
              {{ item.label }}
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header class="app-header">
        <div class="header-title">自然灾害损失统计调查制度 - 数据录入系统</div>
      </el-header>
      <el-main class="app-main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.app-shell {
  height: 100vh;
}
.app-aside {
  width: 280px;
  border-right: 1px solid #ebeef5;
  background: #ffffff;
  display: flex;
  flex-direction: column;
}
.aside-brand {
  height: 56px;
  display: flex;
  align-items: center;
  padding: 0 16px;
  font-weight: 700;
  color: #303133;
  border-bottom: 1px solid #ebeef5;
}
.aside-search {
  padding: 12px 16px;
  border-bottom: 1px solid #ebeef5;
}
.aside-scroll {
  flex: 1;
}
.app-header {
  height: 56px;
  display: flex;
  align-items: center;
  padding: 0 16px;
  border-bottom: 1px solid #ebeef5;
  background: #ffffff;
}
.header-title {
  font-size: 16px;
  font-weight: 700;
  color: #303133;
}
.app-main {
  padding: 16px;
  background: #f5f7fa;
}
</style>
