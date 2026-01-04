<template>
  <div class="table-container">
    <h2>（九）基础设施（交通运输）损失统计表</h2>
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
        <el-collapse-item title="一、公路" name="1">
          <el-divider content-position="left">国省干线</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损路基 (立方米/千米)"><el-input-number v-model="form.colG01001" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路基经济损失 (万元)"><el-input-number v-model="form.colG01002" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路面 (平方米/千米)"><el-input-number v-model="form.colG01003" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路面经济损失 (万元)"><el-input-number v-model="form.colG01004" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁 (延米/座)"><el-input-number v-model="form.colG01005" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁经济损失 (万元)"><el-input-number v-model="form.colG01006" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道 (延米/道)"><el-input-number v-model="form.colG01007" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道经济损失 (万元)"><el-input-number v-model="form.colG01008" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙数量 (立方米/处)"><el-input-number v-model="form.colG01009" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙经济损失 (万元)"><el-input-number v-model="form.colG01010" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="国省干线经济损失 (万元)"><el-input-number v-model="form.colG01011" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>

          <el-divider content-position="left">农村公路</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损路基 (立方米/千米)"><el-input-number v-model="form.colG01012" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路基经济损失 (万元)"><el-input-number v-model="form.colG01013" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路面 (平方米/千米)"><el-input-number v-model="form.colG01014" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路面经济损失 (万元)"><el-input-number v-model="form.colG01015" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁 (延米/座)"><el-input-number v-model="form.colG01016" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁经济损失 (万元)"><el-input-number v-model="form.colG01017" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道 (延米/道)"><el-input-number v-model="form.colG01018" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道经济损失 (万元)"><el-input-number v-model="form.colG01019" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙数量 (立方米/处)"><el-input-number v-model="form.colG01020" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙经济损失 (万元)"><el-input-number v-model="form.colG01021" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>

          <el-divider content-position="left">其他公路及场站</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="其他公路经济损失 (万元)"><el-input-number v-model="form.colG01022" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损客运站数量 (个)"><el-input-number v-model="form.colG01023" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="其中：受损等级站数量 (个)"><el-input-number v-model="form.colG01024" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损客运站经济损失 (万元)"><el-input-number v-model="form.colG01025" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损货运站数量 (个)"><el-input-number v-model="form.colG01026" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损货运站经济损失 (万元)"><el-input-number v-model="form.colG01027" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损服务区数量 (个)"><el-input-number v-model="form.colG01028" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损服务区经济损失 (万元)"><el-input-number v-model="form.colG01029" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="客（货）运站、服务区经济损失 (万元)"><el-input-number v-model="form.colG01030" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          
          <el-divider />
          <el-form-item label="（四）公路经济损失小计 (万元)">
            <el-input-number v-model="form.colG01031" :precision="2" :step="0.1" size="large" style="width: 100%" />
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="二、铁路" name="2">
          <el-divider content-position="left">高速铁路</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损路基 (立方米/千米)"><el-input-number v-model="form.colG01032" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路基经济损失 (万元)"><el-input-number v-model="form.colG01033" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁 (延米/座)"><el-input-number v-model="form.colG01034" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁经济损失 (万元)"><el-input-number v-model="form.colG01035" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损涵洞 (延米/座)"><el-input-number v-model="form.colG01036" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损涵洞经济损失 (万元)"><el-input-number v-model="form.colG01037" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道 (延米/道)"><el-input-number v-model="form.colG01038" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道经济损失 (万元)"><el-input-number v-model="form.colG01039" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙数量 (处)"><el-input-number v-model="form.colG01040" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙经济损失 (万元)"><el-input-number v-model="form.colG01041" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损供电线路经济损失 (万元)"><el-input-number v-model="form.colG01042" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损通信线路经济损失 (万元)"><el-input-number v-model="form.colG01043" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="高速铁路经济损失 (万元)"><el-input-number v-model="form.colG01044" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>

          <el-divider content-position="left">普通铁路</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损路基 (立方米/千米)"><el-input-number v-model="form.colG01045" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损路基经济损失 (万元)"><el-input-number v-model="form.colG01046" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁 (延米/座)"><el-input-number v-model="form.colG01047" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损桥梁经济损失 (万元)"><el-input-number v-model="form.colG01048" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损涵洞 (延米/座)"><el-input-number v-model="form.colG01049" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损涵洞经济损失 (万元)"><el-input-number v-model="form.colG01050" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道 (延米/道)"><el-input-number v-model="form.colG01051" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损隧道经济损失 (万元)"><el-input-number v-model="form.colG01052" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙数量 (处)"><el-input-number v-model="form.colG01053" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损护坡、驳岸、挡墙经济损失 (万元)"><el-input-number v-model="form.colG01054" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损供电线路经济损失 (万元)"><el-input-number v-model="form.colG01055" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损通信线路经济损失 (万元)"><el-input-number v-model="form.colG01056" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="普通铁路经济损失 (万元)"><el-input-number v-model="form.colG01057" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>

          <el-divider content-position="left">场站及车辆</el-divider>
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损客运站数量 (个)"><el-input-number v-model="form.colG01058" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损客运站经济损失 (万元)"><el-input-number v-model="form.colG01059" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损货运站数量 (个)"><el-input-number v-model="form.colG01060" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损货运站经济损失 (万元)"><el-input-number v-model="form.colG01061" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="客（货）运站经济损失 (万元)"><el-input-number v-model="form.colG01062" :precision="2" :step="0.1" size="large" style="width: 100%" disabled /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损高速列车车厢数量 (节)"><el-input-number v-model="form.colG01063" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损高速列车经济损失 (万元)"><el-input-number v-model="form.colG01064" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损普通客车车厢数量 (节)"><el-input-number v-model="form.colG01065" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损普通客车经济损失 (万元)"><el-input-number v-model="form.colG01066" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损普通货车车厢数量 (节)"><el-input-number v-model="form.colG01067" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损普通货车经济损失 (万元)"><el-input-number v-model="form.colG01068" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="运输工具经济损失 (万元)"><el-input-number v-model="form.colG01069" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          
          <el-divider />
          <el-form-item label="（五）铁路经济损失小计 (万元)">
            <el-input-number v-model="form.colG01070" :precision="2" :step="0.1" size="large" style="width: 100%" />
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="三、水运" name="3">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损航道长度 (千米)"><el-input-number v-model="form.colG01071" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损航道经济损失 (万元)"><el-input-number v-model="form.colG01072" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损船闸数量 (个)"><el-input-number v-model="form.colG01073" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损船闸经济损失 (万元)"><el-input-number v-model="form.colG01074" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损码头泊位数量 (个)"><el-input-number v-model="form.colG01075" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损码头泊位经济损失 (万元)"><el-input-number v-model="form.colG01076" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider />
          <el-form-item label="水运经济损失小计 (万元)">
            <el-input-number v-model="form.colG01077" :precision="2" :step="0.1" size="large" style="width: 100%" />
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="四、航空" name="4">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损机场数量 (个)"><el-input-number v-model="form.colG01078" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损机场经济损失 (万元)"><el-input-number v-model="form.colG01079" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损飞机数量 (架)"><el-input-number v-model="form.colG01080" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损飞机经济损失 (万元)"><el-input-number v-model="form.colG01081" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider />
          <el-form-item label="航空经济损失小计 (万元)">
            <el-input-number v-model="form.colG01082" :precision="2" :step="0.1" size="large" style="width: 100%" />
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="五、城市轨道交通" name="5">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损城市轨道交通车辆基地数量 (个)"><el-input-number v-model="form.colG01083" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市轨道交通车辆基地经济损失 (万元)"><el-input-number v-model="form.colG01084" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市轨道交通控制中心数量 (个)"><el-input-number v-model="form.colG01085" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市轨道交通控制中心经济损失 (万元)"><el-input-number v-model="form.colG01086" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市轨道交通车站数量 (个)"><el-input-number v-model="form.colG01087" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市轨道交通车站经济损失 (万元)"><el-input-number v-model="form.colG01088" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市轨道交通线路长度 (千米)"><el-input-number v-model="form.colG01089" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市轨道交通线路经济损失 (万元)"><el-input-number v-model="form.colG01090" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>

        <el-collapse-item title="六、城市交通运输" name="6">
          <el-row :gutter="20">
            <el-col :span="8"><el-form-item label="受损交通枢纽数量 (个)"><el-input-number v-model="form.colG01091" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损交通枢纽经济损失 (万元)"><el-input-number v-model="form.colG01092" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市公共汽车场站数量 (个)"><el-input-number v-model="form.colG01093" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损城市公共汽车场站经济损失 (万元)"><el-input-number v-model="form.colG01094" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损公共汽车数量 (个)"><el-input-number v-model="form.colG01095" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="8"><el-form-item label="受损公共汽车经济损失 (万元)"><el-input-number v-model="form.colG01096" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
          <el-divider />
          <el-form-item label="城市交通运输基础设施经济损失小计 (万元)">
            <el-input-number v-model="form.colG01097" :precision="2" :step="0.1" size="large" style="width: 100%" />
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item title="七、倒损相关用房" name="7">
          <el-row :gutter="20">
            <el-col :span="12"><el-form-item label="倒损相关用房面积 (平方米)"><el-input-number v-model="form.colG01098" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
            <el-col :span="12"><el-form-item label="倒损相关用房经济损失 (万元)"><el-input-number v-model="form.colG01099" :precision="2" :step="0.1" size="large" style="width: 100%" /></el-form-item></el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>

      <div style="margin-top: 20px;">
        <el-form-item label="七、基础设施（交通运输）经济损失合计 (万元)">
          <el-input-number v-model="form.colG01100" :precision="2" :step="0.1" size="large" style="width: 100%" disabled />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" size="large" style="width: 100%">提交</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<style scoped>
.table-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
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

const activeNames = ref(['1', '2', '3', '4', '5', '6', '7'])

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
  colG01001: 0,
  colG01002: 0,
  colG01003: 0,
  colG01004: 0,
  colG01005: 0,
  colG01006: 0,
  colG01007: 0,
  colG01008: 0,
  colG01009: 0,
  colG01010: 0,
  colG01011: 0,
  colG01012: 0,
  colG01013: 0,
  colG01014: 0,
  colG01015: 0,
  colG01016: 0,
  colG01017: 0,
  colG01018: 0,
  colG01019: 0,
  colG01020: 0,
  colG01021: 0,
  colG01022: 0,
  colG01023: 0,
  colG01024: 0,
  colG01025: 0,
  colG01026: 0,
  colG01027: 0,
  colG01028: 0,
  colG01029: 0,
  colG01030: 0,
  colG01031: 0,
  colG01032: 0,
  colG01033: 0,
  colG01034: 0,
  colG01035: 0,
  colG01036: 0,
  colG01037: 0,
  colG01038: 0,
  colG01039: 0,
  colG01040: 0,
  colG01041: 0,
  colG01042: 0,
  colG01043: 0,
  colG01044: 0,
  colG01045: 0,
  colG01046: 0,
  colG01047: 0,
  colG01048: 0,
  colG01049: 0,
  colG01050: 0,
  colG01051: 0,
  colG01052: 0,
  colG01053: 0,
  colG01054: 0,
  colG01055: 0,
  colG01056: 0,
  colG01057: 0,
  colG01058: 0,
  colG01059: 0,
  colG01060: 0,
  colG01061: 0,
  colG01062: 0,
  colG01063: 0,
  colG01064: 0,
  colG01065: 0,
  colG01066: 0,
  colG01067: 0,
  colG01068: 0,
  colG01069: 0,
  colG01070: 0,
  colG01071: 0,
  colG01072: 0,
  colG01073: 0,
  colG01074: 0,
  colG01075: 0,
  colG01076: 0,
  colG01077: 0,
  colG01078: 0,
  colG01079: 0,
  colG01080: 0,
  colG01081: 0,
  colG01082: 0,
  colG01083: 0,
  colG01084: 0,
  colG01085: 0,
  colG01086: 0,
  colG01087: 0,
  colG01088: 0,
  colG01089: 0,
  colG01090: 0,
  colG01091: 0,
  colG01092: 0,
  colG01093: 0,
  colG01094: 0,
  colG01095: 0,
  colG01096: 0,
  colG01097: 0,
  colG01098: 0,
  colG01099: 0,
  colG01100: 0,
})

useTableLatestRecord({
  tableApiPath: '/api/tableG01',
  form: form as any,
  generateReportId,
  getCurrentDateTime,
})

// Auto-calculation using watchEffect
watchEffect(() => {
  form.colG01100 = (Number(form.colG01031)||0)+(Number(form.colG01070)||0)+(Number(form.colG01077)||0)+(Number(form.colG01082)||0)+(Number(form.colG01090)||0)+(Number(form.colG01097)||0)+(Number(form.colG01099)||0)
})

// 使用验证 composable（自动计算）
const { validateBeforeSubmit } = useTableValidation('G01', computed(() => form) as any)

const onSubmit = async () => {
  // 验证表单
  const isValid = await validateBeforeSubmit()
  if (!isValid) return

  try {
    await axios.post('/api/tableG01', form)
    ElMessage.success('提交成功')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}
</script>
