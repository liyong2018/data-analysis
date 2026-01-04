package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十四）公共服务（公安系统和国家综合性消防救援队伍）损失统计表
 */
@Data
@TableName("table_H09")
public class TableH09 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损公安派出所数量 (个) */
    private BigDecimal colH09001;

    /** 受损公安派出所经济损失 (万元) */
    private BigDecimal colH09002;

    /** 受损监管场所数量 (个) */
    private BigDecimal colH09003;

    /** 受损监管场所损失 (万元) */
    private BigDecimal colH09004;

    /** 受损公安检查站数量 (个) */
    private BigDecimal colH09005;

    /** 受损公安检查站经济损失 (万元) */
    private BigDecimal colH09006;

    /** 受损社区警务室数量 (个) */
    private BigDecimal colH09007;

    /** 受损社区警务室经济损失 (万元) */
    private BigDecimal colH09008;

    /** 公安系统经济损失小计 (万元) */
    private BigDecimal colH09009;

    /** 受损消防救援站 (个) */
    private BigDecimal colH09010;

    /** 受损消防救援站损失 (万元) */
    private BigDecimal colH09011;

    /** 受损消防救援大队 (个) */
    private BigDecimal colH09012;

    /** 受损消防救援大队损失 (万元) */
    private BigDecimal colH09013;

    /** 受损消防救援支队 (个) */
    private BigDecimal colH09014;

    /** 受损消防救援支队损失 (万元) */
    private BigDecimal colH09015;

    /** 受损森林消防中队 (个) */
    private BigDecimal colH09016;

    /** 受损森林消防中队损失 (万元) */
    private BigDecimal colH09017;

    /** 受损森林消防大队 (个) */
    private BigDecimal colH09018;

    /** 受损森林消防大队损失 (万元) */
    private BigDecimal colH09019;

    /** 受损森林消防支队 (个) */
    private BigDecimal colH09020;

    /** 受损森林消防支队损失 (万元) */
    private BigDecimal colH09021;

    /** 受损森林消防机动队伍 (个) */
    private BigDecimal colH09022;

    /** 受损森林消防机动队伍损失 (万元) */
    private BigDecimal colH09023;

    /** 国家综合性消防救援队伍经济损失小计 (万元) */
    private BigDecimal colH09024;

    /** 公安系统和国家综合性消防救援队伍经济损失小计 (万元) */
    private BigDecimal colH09025;

    /** 其他公共服务（公安系统和国家综合性消防救援队伍）经济损失 (万元) */
    private BigDecimal colH09026;

    /** 四、公共服务（公安系统和国家综合性消防救援队伍）经济损失合计 (万元) */
    private BigDecimal colH09027;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
