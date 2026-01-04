-- 灾害损失统计表
CREATE TABLE IF NOT EXISTS `disaster_statistics` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `region` VARCHAR(100) NOT NULL COMMENT '区域',
    `disaster_type` VARCHAR(50) NOT NULL COMMENT '灾种',
    `parent_region` VARCHAR(100) DEFAULT NULL COMMENT '隶属区域',
    `disaster_time` DATETIME DEFAULT NULL COMMENT '灾害发生时间',
    `disaster_year` INT NOT NULL COMMENT '受灾年份',

    -- 受灾情况
    `flooded_towns` DECIMAL(10,2) DEFAULT 0 COMMENT '受淹乡镇（街道）数量(个)',
    `affected_population` DECIMAL(12,2) DEFAULT 0 COMMENT '受灾人口(人)',
    `deaths` DECIMAL(10,2) DEFAULT 0 COMMENT '因灾死亡人口(人)',
    `missing` DECIMAL(10,2) DEFAULT 0 COMMENT '因灾失踪人口(人)',
    `injured` DECIMAL(10,2) DEFAULT 0 COMMENT '因灾受伤人口(人)',
    `seriously_injured` DECIMAL(10,2) DEFAULT 0 COMMENT '其中：因灾重伤人口(人)',

    -- 转移安置
    `emergency_transfer` DECIMAL(12,2) DEFAULT 0 COMMENT '紧急避险转移人口(人)',
    `transferred_population_cumulative` DECIMAL(12,2) DEFAULT 0 COMMENT '紧急转移安置人口(累计值)(人)',
    `transferred_population_realtime` DECIMAL(12,2) DEFAULT 0 COMMENT '紧急转移安置人口(实时值)(人)',
    `centralized_population_cumulative` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：集中安置人口(累计值)(人)',
    `centralized_population_realtime` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：集中安置人口(实时值)(人)',
    `dispersed_population_cumulative` DECIMAL(12,2) DEFAULT 0 COMMENT '分散安置人口(累计值)(人)',
    `dispersed_population_realtime` DECIMAL(12,2) DEFAULT 0 COMMENT '分散安置人口(实时值)(人)',
    `centralized_sites_cumulative` DECIMAL(10,2) DEFAULT 0 COMMENT '集中安置点数量(累计值)(个)',
    `centralized_sites_realtime` DECIMAL(10,2) DEFAULT 0 COMMENT '集中安置点数量(实时值)(个)',

    -- 生活救助
    `emergency_relief_cumulative` DECIMAL(12,2) DEFAULT 0 COMMENT '需紧急生活救助人口(累计值)(人)',
    `emergency_relief_realtime` DECIMAL(12,2) DEFAULT 0 COMMENT '需紧急生活救助人口(实时值)(人)',
    `transitional_relief` DECIMAL(12,2) DEFAULT 0 COMMENT '需过渡期生活救助人口(人)',
    `drought_relief_cumulative` DECIMAL(12,2) DEFAULT 0 COMMENT '因旱需生活救助人口(累计值)(人)',
    `drought_relief_realtime` DECIMAL(12,2) DEFAULT 0 COMMENT '因旱需生活救助人口(实时值)(人)',
    `drought_drinking_water_cumulative` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：因旱饮水困难需救助人口(累计值)(人)',
    `drought_drinking_water_realtime` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：因旱饮水困难需救助人口(实时值)(人)',

    -- 房屋损坏
    `collapsed_rooms` DECIMAL(12,2) DEFAULT 0 COMMENT '倒塌房屋间数(间)',
    `collapsed_housing_rooms` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：倒塌住房间数(间)',
    `collapsed_housing_households` DECIMAL(10,2) DEFAULT 0 COMMENT '倒塌住房户数(户)',
    `seriously_damaged_rooms` DECIMAL(12,2) DEFAULT 0 COMMENT '严重损坏房屋间数(间)',
    `seriously_damaged_housing_rooms` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：严重损坏住房间数(间)',
    `seriously_damaged_housing_households` DECIMAL(10,2) DEFAULT 0 COMMENT '严重损坏住房户数(户)',
    `generally_damaged_rooms` DECIMAL(12,2) DEFAULT 0 COMMENT '一般损坏房屋间数(间)',
    `generally_damaged_housing_rooms` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：一般损坏住房间数(间)',
    `generally_damaged_housing_households` DECIMAL(10,2) DEFAULT 0 COMMENT '一般损坏住房户数(户)',

    -- 农业损失
    `crops_affected_area` DECIMAL(14,2) DEFAULT 0 COMMENT '农作物受灾面积(公顷)',
    `grain_crops_affected_area` DECIMAL(14,2) DEFAULT 0 COMMENT '其中：粮食作物受灾面积(公顷)',
    `crops_damaged_area` DECIMAL(14,2) DEFAULT 0 COMMENT '农作物成灾面积(公顷)',
    `grain_crops_damaged_area` DECIMAL(14,2) DEFAULT 0 COMMENT '其中：粮食作物成灾面积(公顷)',
    `crops_lost_area` DECIMAL(14,2) DEFAULT 0 COMMENT '农作物绝收面积(公顷)',
    `grain_crops_lost_area` DECIMAL(14,2) DEFAULT 0 COMMENT '其中：粮食作物绝收面积(公顷)',
    `grassland_affected_area` DECIMAL(14,2) DEFAULT 0 COMMENT '草场受灾面积(公顷)',
    `forest_affected_area` DECIMAL(14,2) DEFAULT 0 COMMENT '林地受灾面积(公顷)',

    -- 牲畜损失
    `livestock_deaths` DECIMAL(12,2) DEFAULT 0 COMMENT '因灾死亡牲畜(头只)',
    `large_livestock_deaths` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：因灾死亡大牲畜(头只)',
    `small_livestock_deaths` DECIMAL(12,2) DEFAULT 0 COMMENT '因灾死亡小牲畜(头只)',
    `drought_drinking_water_livestock` DECIMAL(12,2) DEFAULT 0 COMMENT '因旱饮水困难牲畜(头只)',
    `drought_drinking_water_large_livestock` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：因旱饮水困难大牲畜(头只)',
    `drought_drinking_water_small_livestock` DECIMAL(12,2) DEFAULT 0 COMMENT '其中：因旱饮水困难小牲畜(头只)',
    `aquaculture_affected_area` DECIMAL(14,2) DEFAULT 0 COMMENT '水产养殖受灾面积(公顷)',

    -- 工商业损失
    `damaged_industrial_enterprises` DECIMAL(10,2) DEFAULT 0 COMMENT '受损工业企业数量(个)',
    `damaged_commercial_outlets` DECIMAL(10,2) DEFAULT 0 COMMENT '受损商贸网点数量(个)',

    -- 基础设施损失
    `damaged_road_length` DECIMAL(14,2) DEFAULT 0 COMMENT '受损公路长度(千米)',
    `damaged_communication_line_length` DECIMAL(14,2) DEFAULT 0 COMMENT '受损通信线路长度(皮长公里)',
    `damaged_base_stations` DECIMAL(10,2) DEFAULT 0 COMMENT '受损通信基站数量(个)',
    `damaged_power_line_length` DECIMAL(14,2) DEFAULT 0 COMMENT '受损电力线路长度(千米)',
    `damaged_power_equipment` DECIMAL(10,2) DEFAULT 0 COMMENT '受损输变电设备数量(台套)',
    `damaged_reservoirs` DECIMAL(10,2) DEFAULT 0 COMMENT '受损水库数量(个)',
    `damaged_dike_length` DECIMAL(14,2) DEFAULT 0 COMMENT '受损堤防长度(千米)',
    `damaged_municipal_road_length` DECIMAL(14,2) DEFAULT 0 COMMENT '受损市政道路长度(千米)',
    `damaged_water_pipe_length` DECIMAL(14,2) DEFAULT 0 COMMENT '受损市政供水管网长度(千米)',

    -- 公共服务损失
    `damaged_schools` DECIMAL(10,2) DEFAULT 0 COMMENT '受损学校数量(个)',
    `damaged_hospitals` DECIMAL(10,2) DEFAULT 0 COMMENT '受损医院和卫生机构数量(个)',

    -- 经济损失
    `direct_economic_loss` DECIMAL(18,2) DEFAULT 0 COMMENT '直接经济损失(万元)',
    `housing_property_loss` DECIMAL(18,2) DEFAULT 0 COMMENT '其中：住房及居民家庭财产损失(万元)',
    `agriculture_loss` DECIMAL(18,2) DEFAULT 0 COMMENT '农林牧渔业损失(万元)',
    `industry_commerce_loss` DECIMAL(18,2) DEFAULT 0 COMMENT '工矿商贸业损失(万元)',
    `infrastructure_loss` DECIMAL(18,2) DEFAULT 0 COMMENT '基础设施损失(万元)',
    `public_service_loss` DECIMAL(18,2) DEFAULT 0 COMMENT '公共服务损失(万元)',
    `other_loss` DECIMAL(18,2) DEFAULT 0 COMMENT '其他损失(万元)',

    -- 审计字段
    `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '更新人',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    PRIMARY KEY (`id`),
    KEY `idx_region` (`region`),
    KEY `idx_disaster_type` (`disaster_type`),
    KEY `idx_disaster_year` (`disaster_year`),
    KEY `idx_disaster_time` (`disaster_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='灾害损失统计表';

-- 插入示例数据
INSERT INTO `disaster_statistics` (
    `region`, `disaster_type`, `parent_region`, `disaster_time`, `disaster_year`,
    `flooded_towns`, `affected_population`
) VALUES
('龙泉驿区', '洪涝灾害-其他', '四川省-成都市', '2024-08-08 06:42:47', 2024, 0, 171),
('龙泉驿区', '洪涝灾害-其他', '四川省-成都市', '2024-08-04 07:58:49', 2024, 0, 171);
