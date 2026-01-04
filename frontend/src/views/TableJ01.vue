<template>
  <div class="table-container">
    <h2>（二十八）基础指标统计表</h2>
    <el-form :model="form" label-position="top">
      <!-- 基础信息 -->
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
        <el-collapse-item title="一、人口与家庭" name="1">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="总人口 (人)"><el-input-number v-model="form.colJ01001" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城镇人口 (人)"><el-input-number v-model="form.colJ01002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="农村人口 (人)"><el-input-number v-model="form.colJ01003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="女性人口 (人)"><el-input-number v-model="form.colJ01004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="18 岁以下人口 (人)"><el-input-number v-model="form.colJ01005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="60 岁及以上人口 (人)"><el-input-number v-model="form.colJ01006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="总户数 (户)"><el-input-number v-model="form.colJ01007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城镇户数 (户)"><el-input-number v-model="form.colJ01008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="二、住房情况" name="2">
          <el-divider content-position="left">农村居民住房</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="农村居民家庭住房间数 (间/人)"><el-input-number v-model="form.colJ01009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="农村居民家庭住房价值 (元/间)"><el-input-number v-model="form.colJ01015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="钢结构 (间/人)"><el-input-number v-model="form.colJ01010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="钢筋混凝土结构 (间/人)"><el-input-number v-model="form.colJ01011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="砌体结构 (间/人)"><el-input-number v-model="form.colJ01012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="木（竹）结构 (间/人)"><el-input-number v-model="form.colJ01013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="其他结构 (间/人)"><el-input-number v-model="form.colJ01014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">城镇居民住房</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="城镇居民家庭住房面积 (平方米/人)"><el-input-number v-model="form.colJ01016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="城镇居民家庭住房价值 (元/平方米)"><el-input-number v-model="form.colJ01022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="钢结构 (平方米/人)"><el-input-number v-model="form.colJ01017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="钢筋混凝土结构 (平方米/人)"><el-input-number v-model="form.colJ01018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="砌体结构 (平方米/人)"><el-input-number v-model="form.colJ01019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="木（竹）结构 (平方米/人)"><el-input-number v-model="form.colJ01020" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="其他结构 (平方米/人)"><el-input-number v-model="form.colJ01021" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="三、农业与农村经济" name="3">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="常用耕地面积 (公顷)"><el-input-number v-model="form.colJ01023" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="粮食单位面积产量 (千克/公顷)"><el-input-number v-model="form.colJ01024" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="大牲畜数量 (头只)"><el-input-number v-model="form.colJ01025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="水产品产量 (吨)"><el-input-number v-model="form.colJ01026" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="主要农业机械拥有量 (台)"><el-input-number v-model="form.colJ01027" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="农林牧渔业产值 (万元)"><el-input-number v-model="form.colJ01028" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="四、工业经济" name="4">
          <el-divider content-position="left">规模以上工业</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="企业单位数 (个)"><el-input-number v-model="form.colJ01029" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="工业总产值 (万元)"><el-input-number v-model="form.colJ01030" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="资产总计 (万元)"><el-input-number v-model="form.colJ01031" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="固定资产净值 (万元)"><el-input-number v-model="form.colJ01032" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="从业人员年平均人数 (人)"><el-input-number v-model="form.colJ01033" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">规模以下工业</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="资产总计 (万元)"><el-input-number v-model="form.colJ01034" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="固定资产净值 (万元)"><el-input-number v-model="form.colJ01035" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="从业人员年平均人数 (人)"><el-input-number v-model="form.colJ01036" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="五、服务业" name="5">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="第三产业增加值 (亿元)"><el-input-number v-model="form.colJ01037" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="批发和零售业资产总计 (亿元)"><el-input-number v-model="form.colJ01038" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="住宿和餐饮业资产总计 (亿元)"><el-input-number v-model="form.colJ01039" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="金融业资产总计 (亿元)"><el-input-number v-model="form.colJ01040" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="房地产业资产总计 (亿元)"><el-input-number v-model="form.colJ01041" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="其他产业资产总计 (亿元)"><el-input-number v-model="form.colJ01042" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="六、教育事业" name="6">
          <el-row :gutter="20">
            <el-col :span="24"><el-form-item label="教育经费 (万元)"><el-input-number v-model="form.colJ01043" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">高等教育</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="学校数量 (个)"><el-input-number v-model="form.colJ01044" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="教职工数量 (人)"><el-input-number v-model="form.colJ01045" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="在校学生数量 (人)"><el-input-number v-model="form.colJ01046" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">中等教育</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="学校数量 (个)"><el-input-number v-model="form.colJ01047" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="教职工数量 (人)"><el-input-number v-model="form.colJ01048" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="在校学生数量 (人)"><el-input-number v-model="form.colJ01049" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">初等教育</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="学校数量 (个)"><el-input-number v-model="form.colJ01050" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="教职工数量 (人)"><el-input-number v-model="form.colJ01051" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="在校学生数量 (人)"><el-input-number v-model="form.colJ01052" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">学前教育</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="机构数量 (个)"><el-input-number v-model="form.colJ01053" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="教职工数量 (人)"><el-input-number v-model="form.colJ01054" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="在校学生数量 (人)"><el-input-number v-model="form.colJ01055" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">特殊教育</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="学校数量 (个)"><el-input-number v-model="form.colJ01056" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="教职工数量 (人)"><el-input-number v-model="form.colJ01057" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="在校学生数量 (人)"><el-input-number v-model="form.colJ01058" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="七、科技与卫生" name="7">
          <el-divider content-position="left">科技</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="研究与实验发展人员数量 (人)"><el-input-number v-model="form.colJ01059" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="研究与实验发展经费 (万元)"><el-input-number v-model="form.colJ01060" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">卫生</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="卫生机构数量 (个)"><el-input-number v-model="form.colJ01061" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="卫生人员与卫生技术人员总数 (人)"><el-input-number v-model="form.colJ01062" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="医疗卫生机构床位数 (张)"><el-input-number v-model="form.colJ01063" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="社会服务机构床位数 (张)"><el-input-number v-model="form.colJ01064" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="八、文化与体育" name="8">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="文化机构（博物馆、图书馆、文化馆等）数量 (个)"><el-input-number v-model="form.colJ01065" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="广电技术台站（机）数量 (座/部)"><el-input-number v-model="form.colJ01066" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="体育场馆数量 (个)"><el-input-number v-model="form.colJ01067" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="体育训练基地数量 (个)"><el-input-number v-model="form.colJ01068" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="不可移动文物数量 (处)"><el-input-number v-model="form.colJ01069" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="九、交通运输" name="9">
          <el-divider content-position="left">公路</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="公路里程 (千米)"><el-input-number v-model="form.colJ01070" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="高速公路里程 (千米)"><el-input-number v-model="form.colJ01071" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="公路客运量 (万人)"><el-input-number v-model="form.colJ01072" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="公路货运量 (万吨)"><el-input-number v-model="form.colJ01073" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">铁路</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="铁路营业里程 (千米)"><el-input-number v-model="form.colJ01074" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="铁路客运量 (万人)"><el-input-number v-model="form.colJ01075" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="铁路货运量 (万吨)"><el-input-number v-model="form.colJ01076" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">水路与航空</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="内河航道里程 (千米)"><el-input-number v-model="form.colJ01077" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="民航航线里程 (千米)"><el-input-number v-model="form.colJ01078" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="十、邮电通信" name="10">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="电信业务总量 (亿元)"><el-input-number v-model="form.colJ01079" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="邮政业务总量 (亿元)"><el-input-number v-model="form.colJ01080" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="十一、能源" name="11">
          <el-divider content-position="left">电力</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="电力装机容量 (万千瓦)"><el-input-number v-model="form.colJ01081" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="电力生产量 (亿千瓦时)"><el-input-number v-model="form.colJ01082" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="电力消费量 (亿千瓦时)"><el-input-number v-model="form.colJ01083" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">石油</el-divider>
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="石油可供量 (万吨)"><el-input-number v-model="form.colJ01084" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="石油消费量 (万吨)"><el-input-number v-model="form.colJ01085" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">煤炭</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="煤炭可供量 (万吨)"><el-input-number v-model="form.colJ01086" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="煤炭生产量 (万吨)"><el-input-number v-model="form.colJ01087" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="煤炭消费量 (万吨)"><el-input-number v-model="form.colJ01088" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">天然气</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="天然气可供量 (亿立方米)"><el-input-number v-model="form.colJ01089" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="天然气生产量 (亿立方米)"><el-input-number v-model="form.colJ01090" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="天然气消费量 (亿立方米)"><el-input-number v-model="form.colJ01091" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider content-position="left">煤层气</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="煤层气可供量 (亿立方米)"><el-input-number v-model="form.colJ01092" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="煤层气生产量 (亿立方米)"><el-input-number v-model="form.colJ01093" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="煤层气消费量 (亿立方米)"><el-input-number v-model="form.colJ01094" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="十二、水利设施" name="12">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="水库数 (座)"><el-input-number v-model="form.colJ01095" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="水库总容量 (万立方米)"><el-input-number v-model="form.colJ01096" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="除涝面积 (公顷)"><el-input-number v-model="form.colJ01097" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="堤防保护面积 (公顷)"><el-input-number v-model="form.colJ01098" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="灌区有效灌溉面积 (公顷)"><el-input-number v-model="form.colJ01099" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="十三、市政设施" name="13">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="年末运营公交车量 (辆)"><el-input-number v-model="form.colJ01100" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市道路长度 (千米)"><el-input-number v-model="form.colJ01101" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市供热总量 (万吉焦)"><el-input-number v-model="form.colJ01102" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市供热管道长度 (千米)"><el-input-number v-model="form.colJ01103" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市供水综合生产能力 (万立方米/日)"><el-input-number v-model="form.colJ01104" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市排水管道长度 (千米)"><el-input-number v-model="form.colJ01105" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市污水日处理能力 (万立方米)"><el-input-number v-model="form.colJ01106" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市生活垃圾日处理能力 (吨/日)"><el-input-number v-model="form.colJ01107" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="城市绿化面积 (公顷)"><el-input-number v-model="form.colJ01108" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <el-form-item style="margin-top: 20px;">
        <el-button type="primary" @click="onSubmit" size="large" style="width: 100%">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.table-container {
  padding: 20px;
  max-width: 1200px;
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
import { useTableValidation, useTableLatestRecord } from '@/composables/useTableValidation'

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

const activeNames = ref(['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13'])

const form = reactive({
  reportId: generateReportId(),
  regionCode: '',
  reportDate: getCurrentDateTime(),
  colJ01001: 0,
  colJ01002: 0,
  colJ01003: 0,
  colJ01004: 0,
  colJ01005: 0,
  colJ01006: 0,
  colJ01007: 0,
  colJ01008: 0,
  colJ01009: 0,
  colJ01010: 0,
  colJ01011: 0,
  colJ01012: 0,
  colJ01013: 0,
  colJ01014: 0,
  colJ01015: 0,
  colJ01016: 0,
  colJ01017: 0,
  colJ01018: 0,
  colJ01019: 0,
  colJ01020: 0,
  colJ01021: 0,
  colJ01022: 0,
  colJ01023: 0,
  colJ01024: 0,
  colJ01025: 0,
  colJ01026: 0,
  colJ01027: 0,
  colJ01028: 0,
  colJ01029: 0,
  colJ01030: 0,
  colJ01031: 0,
  colJ01032: 0,
  colJ01033: 0,
  colJ01034: 0,
  colJ01035: 0,
  colJ01036: 0,
  colJ01037: 0,
  colJ01038: 0,
  colJ01039: 0,
  colJ01040: 0,
  colJ01041: 0,
  colJ01042: 0,
  colJ01043: 0,
  colJ01044: 0,
  colJ01045: 0,
  colJ01046: 0,
  colJ01047: 0,
  colJ01048: 0,
  colJ01049: 0,
  colJ01050: 0,
  colJ01051: 0,
  colJ01052: 0,
  colJ01053: 0,
  colJ01054: 0,
  colJ01055: 0,
  colJ01056: 0,
  colJ01057: 0,
  colJ01058: 0,
  colJ01059: 0,
  colJ01060: 0,
  colJ01061: 0,
  colJ01062: 0,
  colJ01063: 0,
  colJ01064: 0,
  colJ01065: 0,
  colJ01066: 0,
  colJ01067: 0,
  colJ01068: 0,
  colJ01069: 0,
  colJ01070: 0,
  colJ01071: 0,
  colJ01072: 0,
  colJ01073: 0,
  colJ01074: 0,
  colJ01075: 0,
  colJ01076: 0,
  colJ01077: 0,
  colJ01078: 0,
  colJ01079: 0,
  colJ01080: 0,
  colJ01081: 0,
  colJ01082: 0,
  colJ01083: 0,
  colJ01084: 0,
  colJ01085: 0,
  colJ01086: 0,
  colJ01087: 0,
  colJ01088: 0,
  colJ01089: 0,
  colJ01090: 0,
  colJ01091: 0,
  colJ01092: 0,
  colJ01093: 0,
  colJ01094: 0,
  colJ01095: 0,
  colJ01096: 0,
  colJ01097: 0,
  colJ01098: 0,
  colJ01099: 0,
  colJ01100: 0,
  colJ01101: 0,
  colJ01102: 0,
  colJ01103: 0,
  colJ01104: 0,
  colJ01105: 0,
  colJ01106: 0,
  colJ01107: 0,
  colJ01108: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableJ01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

const { validateBeforeSubmit, applyAutoCalc } = useTableValidation('J01', computed(() => form) as any)

watchEffect(() => {
  applyAutoCalc()
})

const onSubmit = async () => {
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableJ01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
