package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （一）经济损失统计汇总表
 */
@Data
@TableName("table_Z01")
public class TableZ01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 农村居民住宅用房经济损失 (万元) */
    private BigDecimal colZ01001;

    /** 城镇居民住宅用房经济损失 (万元) */
    private BigDecimal colZ01002;

    /** 居民家庭财产经济损失 (万元) */
    private BigDecimal colZ01003;

    /** 农林牧渔业经济损失 (万元) */
    private BigDecimal colZ01004;

    /** 工业经济损失 (万元) */
    private BigDecimal colZ01005;

    /** 服务业经济损失 (万元) */
    private BigDecimal colZ01006;

    /** 基础设施（交通运输）经济损失 (万元) */
    private BigDecimal colZ01007;

    /** 基础设施（通信）经济损失 (万元) */
    private BigDecimal colZ01008;

    /** 基础设施（能源）经济损失 (万元) */
    private BigDecimal colZ01009;

    /** 基础设施（水利）经济损失 (万元) */
    private BigDecimal colZ01010;

    /** 基础设施（市政）经济损失 (万元) */
    private BigDecimal colZ01011;

    /** 基础设施（农村地区生活设施）经济损失 (万元) */
    private BigDecimal colZ01012;

    /** 基础设施（地质灾害防治设施）经济损失 (万元) */
    private BigDecimal colZ01013;

    /** 公共服务（教育系统）经济损失 (万元) */
    private BigDecimal colZ01014;

    /** 公共服务（科技系统）经济损失 (万元) */
    private BigDecimal colZ01015;

    /** 公共服务（医疗卫生系统）经济损失 (万元) */
    private BigDecimal colZ01016;

    /** 公共服务（文化系统）经济损失 (万元) */
    private BigDecimal colZ01017;

    /** 公共服务（广播电视系统）经济损失 (万元) */
    private BigDecimal colZ01018;

    /** 公共服务（新闻出版系统）经济损失 (万元) */
    private BigDecimal colZ01019;

    /** 公共服务（体育）经济损失 (万元) */
    private BigDecimal colZ01020;

    /** 公共服务（社会保障与社会服务系统）经济损失 (万元) */
    private BigDecimal colZ01021;

    /** 公共服务（公安系统和国家综合性消防救援队伍）经济损失 (万元) */
    private BigDecimal colZ01022;

    /** 公共服务（社会管理系统）经济损失 (万元) */
    private BigDecimal colZ01023;

    /** 经济损失合计 (万元) */
    private BigDecimal colZ01024;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
