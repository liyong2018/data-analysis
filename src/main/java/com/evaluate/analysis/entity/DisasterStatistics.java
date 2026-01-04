package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 灾害损失统计表
 */
@Data
@TableName("disaster_statistics")
public class DisasterStatistics {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 区域 */
    private String region;

    /** 灾种 */
    private String disasterType;

    /** 隶属区域 */
    private String parentRegion;

    /** 灾害发生时间 */
    private LocalDateTime disasterTime;

    /** 受灾年份 */
    private Integer disasterYear;

    /** 受淹乡镇（街道）数量(个) */
    private BigDecimal floodedTowns;

    /** 受灾人口(人) */
    private BigDecimal affectedPopulation;

    /** 因灾死亡人口(人) */
    private BigDecimal deaths;

    /** 因灾失踪人口(人) */
    private BigDecimal missing;

    /** 因灾受伤人口(人) */
    private BigDecimal injured;

    /** 其中：因灾重伤人口(人) */
    private BigDecimal seriouslyInjured;

    /** 紧急避险转移人口(人) */
    private BigDecimal emergencyTransfer;

    /** 紧急转移安置人口(累计值)(人) */
    private BigDecimal transferredPopulationCumulative;

    /** 紧急转移安置人口(实时值)(人) */
    private BigDecimal transferredPopulationRealtime;

    /** 其中：集中安置人口(累计值)(人) */
    private BigDecimal centralizedPopulationCumulative;

    /** 其中：集中安置人口(实时值)(人) */
    private BigDecimal centralizedPopulationRealtime;

    /** 分散安置人口(累计值)(人) */
    private BigDecimal dispersedPopulationCumulative;

    /** 分散安置人口(实时值)(人) */
    private BigDecimal dispersedPopulationRealtime;

    /** 集中安置点数量(累计值)(个) */
    private BigDecimal centralizedSitesCumulative;

    /** 集中安置点数量(实时值)(个) */
    private BigDecimal centralizedSitesRealtime;

    /** 需紧急生活救助人口(累计值)(人) */
    private BigDecimal emergencyReliefCumulative;

    /** 需紧急生活救助人口(实时值)(人) */
    private BigDecimal emergencyReliefRealtime;

    /** 需过渡期生活救助人口(人) */
    private BigDecimal transitionalRelief;

    /** 因旱需生活救助人口(累计值)(人) */
    private BigDecimal droughtReliefCumulative;

    /** 因旱需生活救助人口(实时值)(人) */
    private BigDecimal droughtReliefRealtime;

    /** 其中：因旱饮水困难需救助人口(累计值)(人) */
    private BigDecimal droughtDrinkingWaterCumulative;

    /** 其中：因旱饮水困难需救助人口(实时值)(人) */
    private BigDecimal droughtDrinkingWaterRealtime;

    /** 倒塌房屋间数(间) */
    private BigDecimal collapsedRooms;

    /** 其中：倒塌住房间数(间) */
    private BigDecimal collapsedHousingRooms;

    /** 倒塌住房户数(户) */
    private BigDecimal collapsedHousingHouseholds;

    /** 严重损坏房屋间数(间) */
    private BigDecimal seriouslyDamagedRooms;

    /** 其中：严重损坏住房间数(间) */
    private BigDecimal seriouslyDamagedHousingRooms;

    /** 严重损坏住房户数(户) */
    private BigDecimal seriouslyDamagedHousingHouseholds;

    /** 一般损坏房屋间数(间) */
    private BigDecimal generallyDamagedRooms;

    /** 其中：一般损坏住房间数(间) */
    private BigDecimal generallyDamagedHousingRooms;

    /** 一般损坏住房户数(户) */
    private BigDecimal generallyDamagedHousingHouseholds;

    /** 农作物受灾面积(公顷) */
    private BigDecimal cropsAffectedArea;

    /** 其中：粮食作物受灾面积(公顷) */
    private BigDecimal grainCropsAffectedArea;

    /** 农作物成灾面积(公顷) */
    private BigDecimal cropsDamagedArea;

    /** 其中：粮食作物成灾面积(公顷) */
    private BigDecimal grainCropsDamagedArea;

    /** 农作物绝收面积(公顷) */
    private BigDecimal cropsLostArea;

    /** 其中：粮食作物绝收面积(公顷) */
    private BigDecimal grainCropsLostArea;

    /** 草场受灾面积(公顷) */
    private BigDecimal grasslandAffectedArea;

    /** 林地受灾面积(公顷) */
    private BigDecimal forestAffectedArea;

    /** 因灾死亡牲畜(头只) */
    private BigDecimal livestockDeaths;

    /** 其中：因灾死亡大牲畜(头只) */
    private BigDecimal largeLivestockDeaths;

    /** 因灾死亡小牲畜(头只) */
    private BigDecimal smallLivestockDeaths;

    /** 因旱饮水困难牲畜(头只) */
    private BigDecimal droughtDrinkingWaterLivestock;

    /** 其中：因旱饮水困难大牲畜(头只) */
    private BigDecimal droughtDrinkingWaterLargeLivestock;

    /** 其中：因旱饮水困难小牲畜(头只) */
    private BigDecimal droughtDrinkingWaterSmallLivestock;

    /** 水产养殖受灾面积(公顷) */
    private BigDecimal aquacultureAffectedArea;

    /** 受损工业企业数量(个) */
    private BigDecimal damagedIndustrialEnterprises;

    /** 受损商贸网点数量(个) */
    private BigDecimal damagedCommercialOutlets;

    /** 受损公路长度(千米) */
    private BigDecimal damagedRoadLength;

    /** 受损通信线路长度(皮长公里) */
    private BigDecimal damagedCommunicationLineLength;

    /** 受损通信基站数量(个) */
    private BigDecimal damagedBaseStations;

    /** 受损电力线路长度(千米) */
    private BigDecimal damagedPowerLineLength;

    /** 受损输变电设备数量(台套) */
    private BigDecimal damagedPowerEquipment;

    /** 受损水库数量(个) */
    private BigDecimal damagedReservoirs;

    /** 受损堤防长度(千米) */
    private BigDecimal damagedDikeLength;

    /** 受损市政道路长度(千米) */
    private BigDecimal damagedMunicipalRoadLength;

    /** 受损市政供水管网长度(千米) */
    private BigDecimal damagedWaterPipeLength;

    /** 受损学校数量(个) */
    private BigDecimal damagedSchools;

    /** 受损医院和卫生机构数量(个) */
    private BigDecimal damagedHospitals;

    /** 直接经济损失(万元) */
    private BigDecimal directEconomicLoss;

    /** 其中：住房及居民家庭财产损失(万元) */
    private BigDecimal housingPropertyLoss;

    /** 农林牧渔业损失(万元) */
    private BigDecimal agricultureLoss;

    /** 工矿商贸业损失(万元) */
    private BigDecimal industryCommerceLoss;

    /** 基础设施损失(万元) */
    private BigDecimal infrastructureLoss;

    /** 公共服务损失(万元) */
    private BigDecimal publicServiceLoss;

    /** 其他损失(万元) */
    private BigDecimal otherLoss;

    /** 创建人 */
    private String createdBy;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新人 */
    private String updatedBy;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}
