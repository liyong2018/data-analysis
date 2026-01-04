package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十一）基础设施（能源）损失统计表
 */
@Data
@TableName("table_G03")
public class TableG03 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损变电设备容量 (千伏安) */
    private BigDecimal colG03001;

    /** 受损线路长度 (千米) */
    private BigDecimal colG03002;

    /** 经济损失 (万元) */
    private BigDecimal colG03003;

    /** 受损变电设备容量 (千伏安) */
    private BigDecimal colG03004;

    /** 受损线路长度 (千米) */
    private BigDecimal colG03005;

    /** 经济损失 (万元) */
    private BigDecimal colG03006;

    /** 受损变电设备容量 (千伏安) */
    private BigDecimal colG03007;

    /** 受损线路长度 (千米) */
    private BigDecimal colG03008;

    /** 经济损失 (万元) */
    private BigDecimal colG03009;

    /** 受损变电设备容量 (千伏安) */
    private BigDecimal colG03010;

    /** 受损线路长度 (千米) */
    private BigDecimal colG03011;

    /** 经济损失 (万元) */
    private BigDecimal colG03012;

    /** 受损变电设备容量 (千伏安) */
    private BigDecimal colG03013;

    /** 受损线路长度 (千米) */
    private BigDecimal colG03014;

    /** 经济损失 (万元) */
    private BigDecimal colG03015;

    /** 受损电厂数量 (个) */
    private BigDecimal colG03016;

    /** 受损发电机组数量 (个) */
    private BigDecimal colG03017;

    /** 受损电厂装机容量 (千瓦) */
    private BigDecimal colG03018;

    /** 受损电厂经济损失 (万元) */
    private BigDecimal colG03019;

    /** （七）电力经济损失小计 (万元) */
    private BigDecimal colG03020;

    /** 受损煤矿数量 (处) */
    private BigDecimal colG03021;

    /** 受损煤矿规模 (万吨/年) */
    private BigDecimal colG03022;

    /** 受损煤矿经济损失 (万元) */
    private BigDecimal colG03023;

    /** 受损油井数量 (个) */
    private BigDecimal colG03024;

    /** 受损油井经济损失 (万元) */
    private BigDecimal colG03025;

    /** 受损输油管道（包括保护设施）长度 (千米) */
    private BigDecimal colG03026;

    /** 受损输油管道（包括保护设施）经济损失 (万元) */
    private BigDecimal colG03027;

    /** 受损油库数量 (座) */
    private BigDecimal colG03028;

    /** 受损油库经济损失 (万元) */
    private BigDecimal colG03029;

    /** 受损天然气气井数量 (个) */
    private BigDecimal colG03030;

    /** 受损天然气气井经济损失 (万元) */
    private BigDecimal colG03031;

    /** 受损天然气管线长度 (千米) */
    private BigDecimal colG03032;

    /** 受损天然气管线经济损失 (万元) */
    private BigDecimal colG03033;

    /** 受损煤层气井数量 (个) */
    private BigDecimal colG03034;

    /** 受损煤层气井经济损失 (万元) */
    private BigDecimal colG03035;

    /** 受损煤层气管线长度 (千米) */
    private BigDecimal colG03036;

    /** 受损煤层气管线经济损失 (万元) */
    private BigDecimal colG03037;

    /** （四）煤油气经济损失小计 (万元) */
    private BigDecimal colG03038;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colG03039;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colG03040;

    /** 四、基础设施（能源）经济损失合计 (万元) */
    private BigDecimal colG03041;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
