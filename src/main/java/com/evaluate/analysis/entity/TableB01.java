package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （三）农村居民住房受损情况统计表
 */
@Data
@TableName("table_B01")
public class TableB01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 钢结构户数 (户) */
    private BigDecimal colB01001;

    /** 钢结构间数 (间) */
    private BigDecimal colB01002;

    /** 钢结构经济损失 (万元) */
    private BigDecimal colB01003;

    /** 钢筋混凝土结构户数 (户) */
    private BigDecimal colB01004;

    /** 钢筋混凝土结构间数 (间) */
    private BigDecimal colB01005;

    /** 钢筋混凝土结构经济损失 (万元) */
    private BigDecimal colB01006;

    /** 砌体结构户数 (户) */
    private BigDecimal colB01007;

    /** 砌体结构间数 (间) */
    private BigDecimal colB01008;

    /** 砌体结构经济损失 (万元) */
    private BigDecimal colB01009;

    /** 木（竹）结构户数 (户) */
    private BigDecimal colB01010;

    /** 木（竹）结构间数 (间) */
    private BigDecimal colB01011;

    /** 木（竹）结构经济损失 (万元) */
    private BigDecimal colB01012;

    /** 其他结构户数 (户) */
    private BigDecimal colB01013;

    /** 其他结构间数 (间) */
    private BigDecimal colB01014;

    /** 其他结构经济损失 (万元) */
    private BigDecimal colB01015;

    /** 倒塌住房总户数 (户) */
    private BigDecimal colB01016;

    /** 倒塌住房总间数 (间) */
    private BigDecimal colB01017;

    /** 倒塌住房经济损失小计 (万元) */
    private BigDecimal colB01018;

    /** 钢结构户数 (户) */
    private BigDecimal colB01019;

    /** 钢结构间数 (间) */
    private BigDecimal colB01020;

    /** 钢结构经济损失 (万元) */
    private BigDecimal colB01021;

    /** 钢筋混凝土结构户数 (户) */
    private BigDecimal colB01022;

    /** 钢筋混凝土结构间数 (间) */
    private BigDecimal colB01023;

    /** 钢筋混凝土结构经济损失 (万元) */
    private BigDecimal colB01024;

    /** 砌体结构户数 (户) */
    private BigDecimal colB01025;

    /** 砌体结构间数 (间) */
    private BigDecimal colB01026;

    /** 砌体结构经济损失 (万元) */
    private BigDecimal colB01027;

    /** 木（竹）结构户数 (户) */
    private BigDecimal colB01028;

    /** 木（竹）结构间数 (间) */
    private BigDecimal colB01029;

    /** 木（竹）结构经济损失 (万元) */
    private BigDecimal colB01030;

    /** 其他结构户数 (户) */
    private BigDecimal colB01031;

    /** 其他结构间数 (间) */
    private BigDecimal colB01032;

    /** 其他结构经济损失 (万元) */
    private BigDecimal colB01033;

    /** 严重损坏住房总户数 (户) */
    private BigDecimal colB01034;

    /** 严重损坏住房总间数 (间) */
    private BigDecimal colB01035;

    /** 严重损坏住房经济损失小计 (万元) */
    private BigDecimal colB01036;

    /** 钢结构户数 (户) */
    private BigDecimal colB01037;

    /** 钢结构间数 (间) */
    private BigDecimal colB01038;

    /** 钢结构经济损失 (万元) */
    private BigDecimal colB01039;

    /** 钢筋混凝土结构户数 (户) */
    private BigDecimal colB01040;

    /** 钢筋混凝土结构间数 (间) */
    private BigDecimal colB01041;

    /** 钢筋混凝土结构经济损失 (万元) */
    private BigDecimal colB01042;

    /** 砌体结构户数 (户) */
    private BigDecimal colB01043;

    /** 砌体结构间数 (间) */
    private BigDecimal colB01044;

    /** 砌体结构经济损失 (万元) */
    private BigDecimal colB01045;

    /** 木（竹）结构户数 (户) */
    private BigDecimal colB01046;

    /** 木（竹）结构间数 (间) */
    private BigDecimal colB01047;

    /** 木（竹）结构经济损失 (万元) */
    private BigDecimal colB01048;

    /** 其他结构户数 (户) */
    private BigDecimal colB01049;

    /** 其他结构间数 (间) */
    private BigDecimal colB01050;

    /** 其他结构经济损失 (万元) */
    private BigDecimal colB01051;

    /** 一般损坏住房总户数 (户) */
    private BigDecimal colB01052;

    /** 一般损坏住房总间数 (间) */
    private BigDecimal colB01053;

    /** 一般损坏住房经济损失小计 (万元) */
    private BigDecimal colB01054;

    /** 四、农村居民住房经济损失合计 (万元) */
    private BigDecimal colB01055;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
