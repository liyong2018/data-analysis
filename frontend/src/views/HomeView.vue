<template>
  <div class="home">
    <el-card class="welcome-card" shadow="never">
      <div class="welcome-title">数据录入系统</div>
      <div class="welcome-desc">使用左侧 A-Z 主菜单选择分类，在子菜单中选择对应 table 进行录入。</div>
      <div class="quick-actions">
        <router-link to="/tableZ01" class="quick-link">进入 Z01 经济损失统计</router-link>
        <router-link to="/disaster-statistics" class="quick-link">进入统计分析</router-link>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.home {
  max-width: 900px;
  margin: 0 auto;
}
.welcome-card {
  border-radius: 12px;
}
.welcome-title {
  font-size: 22px;
  font-weight: 800;
  color: #303133;
  margin-bottom: 8px;
}
.welcome-desc {
  font-size: 14px;
  color: #606266;
  line-height: 1.6;
}
.quick-actions {
  margin-top: 16px;
  display: flex;
  gap: 16px;
  flex-wrap: wrap;
}
.quick-link {
  color: #409eff;
  text-decoration: none;
  font-weight: 600;
}
</style>
