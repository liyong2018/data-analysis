package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十三）基础设施（市政）损失统计表
 */
@Data
@TableName("table_G05")
public class TableG05 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损道路长度 (千米) */
    private BigDecimal colG05001;

    /** 受损道路经济损失 (万元) */
    private BigDecimal colG05002;

    /** 受损桥梁长度 (千米) */
    private BigDecimal colG05003;

    /** 受损桥梁经济损失 (万元) */
    private BigDecimal colG05004;

    /** 受损隧道长度 (千米) */
    private BigDecimal colG05005;

    /** 受损隧道经济损失 (万元) */
    private BigDecimal colG05006;

    /** 市政道路交通经济损失小计 (万元) */
    private BigDecimal colG05007;

    /** 受损水厂数量 (个) */
    private BigDecimal colG05008;

    /** 受损水厂经济损失 (万元) */
    private BigDecimal colG05009;

    /** 受损供水管网长度 (千米) */
    private BigDecimal colG05010;

    /** 受损供水管网经济损失 (万元) */
    private BigDecimal colG05011;

    /** 市政供水经济损失小计 (万元) */
    private BigDecimal colG05012;

    /** 受损雨水管网长度 (千米) */
    private BigDecimal colG05013;

    /** 受损雨水管网经济损失 (万元) */
    private BigDecimal colG05014;

    /** 受损污水管网长度 (千米) */
    private BigDecimal colG05015;

    /** 受损污水管网经济损失 (万元) */
    private BigDecimal colG05016;

    /** 受损污水处理厂数量 (个) */
    private BigDecimal colG05017;

    /** 受损污水处理厂经济损失 (万元) */
    private BigDecimal colG05018;

    /** 受损再生水厂数量 (个) */
    private BigDecimal colG05019;

    /** 受损再生水厂经济损失 (万元) */
    private BigDecimal colG05020;

    /** 市政排水经济损失小计 (万元) */
    private BigDecimal colG05021;

    /** 受损燃气储气站数量 (个) */
    private BigDecimal colG05022;

    /** 受损燃气储气站经济损失 (万元) */
    private BigDecimal colG05023;

    /** 受损供气管网长度 (千米) */
    private BigDecimal colG05024;

    /** 受损供气管网经济损失 (万元) */
    private BigDecimal colG05025;

    /** 受损热源厂数量 (个) */
    private BigDecimal colG05026;

    /** 受损热源厂经济损失 (万元) */
    private BigDecimal colG05027;

    /** 受损供热管网长度 (千米) */
    private BigDecimal colG05028;

    /** 受损供热管网经济损失 (万元) */
    private BigDecimal colG05029;

    /** 市政供气供热经济损失小计 (万元) */
    private BigDecimal colG05030;

    /** 受损垃圾无害化处理设施数量 (个) */
    private BigDecimal colG05031;

    /** 受损垃圾无害化处理设施经济损失 (万元) */
    private BigDecimal colG05032;

    /** 受损垃圾转运设施数量 (个) */
    private BigDecimal colG05033;

    /** 受损垃圾转运设施经济损失 (万元) */
    private BigDecimal colG05034;

    /** 市政垃圾处理经济损失小计 (万元) */
    private BigDecimal colG05035;

    /** 受损城市绿地面积 (公顷) */
    private BigDecimal colG05036;

    /** 受损城市绿地经济损失 (万元) */
    private BigDecimal colG05037;

    /** 受损城市防洪经济损失 (万元) */
    private BigDecimal colG05038;

    /** 八、其他市政设施经济损失 (万元) */
    private BigDecimal colG05039;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colG05040;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colG05041;

    /** 十、基础设施（市政）经济损失合计 (万元) */
    private BigDecimal colG05042;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
