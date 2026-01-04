package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （四）城镇居民住房受损情况统计表
 */
@Data
@TableName("table_B02")
public class TableB02 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 钢结构户数 (户) */
    private BigDecimal colB02001;

    /** 钢结构间数 (间) */
    private BigDecimal colB02002;

    /** 钢结构面积 (平方米) */
    private BigDecimal colB02003;

    /** 钢结构经济损失 (万元) */
    private BigDecimal colB02004;

    /** 钢筋混凝土结构户数 (户) */
    private BigDecimal colB02005;

    /** 钢筋混凝土结构间数 (间) */
    private BigDecimal colB02006;

    /** 钢筋混凝土结构面积 (平方米) */
    private BigDecimal colB02007;

    /** 钢筋混凝土结构经济损失 (万元) */
    private BigDecimal colB02008;

    /** 砌体结构户数 (户) */
    private BigDecimal colB02009;

    /** 砌体结构间数 (间) */
    private BigDecimal colB02010;

    /** 砌体结构面积 (平方米) */
    private BigDecimal colB02011;

    /** 砌体结构经济损失 (万元) */
    private BigDecimal colB02012;

    /** 木（竹）结构户数 (户) */
    private BigDecimal colB02013;

    /** 木（竹）结构间数 (间) */
    private BigDecimal colB02014;

    /** 木（竹）结构面积 (平方米) */
    private BigDecimal colB02015;

    /** 木（竹）结构经济损失 (万元) */
    private BigDecimal colB02016;

    /** 其他结构户数 (户) */
    private BigDecimal colB02017;

    /** 其他结构间数 (间) */
    private BigDecimal colB02018;

    /** 其他结构面积 (平方米) */
    private BigDecimal colB02019;

    /** 其他结构经济损失 (万元) */
    private BigDecimal colB02020;

    /** 倒塌住房总户数 (户) */
    private BigDecimal colB02021;

    /** 倒塌住房总间数 (间) */
    private BigDecimal colB02022;

    /** 倒塌住房总面积 (平方米) */
    private BigDecimal colB02023;

    /** 倒塌住房经济损失小计 (万元) */
    private BigDecimal colB02024;

    /** 钢结构户数 (户) */
    private BigDecimal colB02025;

    /** 钢结构间数 (间) */
    private BigDecimal colB02026;

    /** 钢结构面积 (平方米) */
    private BigDecimal colB02027;

    /** 钢结构经济损失 (万元) */
    private BigDecimal colB02028;

    /** 钢筋混凝土结构户数 (户) */
    private BigDecimal colB02029;

    /** 钢筋混凝土结构间数 (间) */
    private BigDecimal colB02030;

    /** 钢筋混凝土结构面积 (平方米) */
    private BigDecimal colB02031;

    /** 钢筋混凝土结构经济损失 (万元) */
    private BigDecimal colB02032;

    /** 砌体结构户数 (户) */
    private BigDecimal colB02033;

    /** 砌体结构间数 (间) */
    private BigDecimal colB02034;

    /** 砌体结构面积 (平方米) */
    private BigDecimal colB02035;

    /** 砌体结构经济损失 (万元) */
    private BigDecimal colB02036;

    /** 木（竹）结构户数 (户) */
    private BigDecimal colB02037;

    /** 木（竹）结构间数 (间) */
    private BigDecimal colB02038;

    /** 木（竹）结构面积 (平方米) */
    private BigDecimal colB02039;

    /** 木（竹）结构经济损失 (万元) */
    private BigDecimal colB02040;

    /** 其他结构户数 (户) */
    private BigDecimal colB02041;

    /** 其他结构间数 (间) */
    private BigDecimal colB02042;

    /** 其他结构面积 (平方米) */
    private BigDecimal colB02043;

    /** 其他结构经济损失 (万元) */
    private BigDecimal colB02044;

    /** 严重损坏住房总户数 (户) */
    private BigDecimal colB02045;

    /** 严重损坏住房总间数 (间) */
    private BigDecimal colB02046;

    /** 严重损坏住房总面积 (平方米) */
    private BigDecimal colB02047;

    /** 严重损坏住房经济损失小计 (万元) */
    private BigDecimal colB02048;

    /** 钢结构户数 (户) */
    private BigDecimal colB02049;

    /** 钢结构间数 (间) */
    private BigDecimal colB02050;

    /** 钢结构面积 (平方米) */
    private BigDecimal colB02051;

    /** 钢结构经济损失 (万元) */
    private BigDecimal colB02052;

    /** 钢筋混凝土结构户数 (户) */
    private BigDecimal colB02053;

    /** 钢筋混凝土结构间数 (间) */
    private BigDecimal colB02054;

    /** 钢筋混凝土结构面积 (平方米) */
    private BigDecimal colB02055;

    /** 钢筋混凝土结构经济损失 (万元) */
    private BigDecimal colB02056;

    /** 砌体结构户数 (户) */
    private BigDecimal colB02057;

    /** 砌体结构间数 (间) */
    private BigDecimal colB02058;

    /** 砌体结构面积 (平方米) */
    private BigDecimal colB02059;

    /** 砌体结构经济损失 (万元) */
    private BigDecimal colB02060;

    /** 木（竹）结构户数 (户) */
    private BigDecimal colB02061;

    /** 木（竹）结构间数 (间) */
    private BigDecimal colB02062;

    /** 木（竹）结构面积 (平方米) */
    private BigDecimal colB02063;

    /** 木（竹）结构经济损失 (万元) */
    private BigDecimal colB02064;

    /** 其他结构户数 (户) */
    private BigDecimal colB02065;

    /** 其他结构间数 (间) */
    private BigDecimal colB02066;

    /** 其他结构面积 (平方米) */
    private BigDecimal colB02067;

    /** 其他结构经济损失 (万元) */
    private BigDecimal colB02068;

    /** 一般损坏住房总户数 (户) */
    private BigDecimal colB02069;

    /** 一般损坏住房总间数 (间) */
    private BigDecimal colB02070;

    /** 一般损坏住房总面积 (平方米) */
    private BigDecimal colB02071;

    /** 一般损坏住房经济损失小计 (万元) */
    private BigDecimal colB02072;

    /** 四、城镇居民住房经济损失合计 (万元) */
    private BigDecimal colB02073;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
