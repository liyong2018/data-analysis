package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十二）基础设施（水利）损失统计表
 */
@Data
@TableName("table_G04")
public class TableG04 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损大中型水库水电站数量 (座) */
    private BigDecimal colG04001;

    /** 受损大中型水库水电站经济损失 (万元) */
    private BigDecimal colG04002;

    /** 受损小型水库水电站数量 (座) */
    private BigDecimal colG04003;

    /** 受损小型水库水电站经济损失 (万元) */
    private BigDecimal colG04004;

    /** 受损 1级和 2级堤防长度 (千米) */
    private BigDecimal colG04005;

    /** 受损 3级及以下堤防长度 (千米) */
    private BigDecimal colG04006;

    /** 受损堤防经济损失 (万元) */
    private BigDecimal colG04007;

    /** 受损护岸数量 (处) */
    private BigDecimal colG04008;

    /** 受损护岸经济损失 (万元) */
    private BigDecimal colG04009;

    /** 受损水闸数量 (座) */
    private BigDecimal colG04010;

    /** 受损水闸经济损失 (万元) */
    private BigDecimal colG04011;

    /** 受损塘坝数量 (座) */
    private BigDecimal colG04012;

    /** 受损塘坝经济损失 (万元) */
    private BigDecimal colG04013;

    /** 受损灌排设施数量 (处) */
    private BigDecimal colG04014;

    /** 受损灌排设施经济损失 (万元) */
    private BigDecimal colG04015;

    /** 受损机电井数量 (眼) */
    private BigDecimal colG04016;

    /** 受损机电井经济损失 (万元) */
    private BigDecimal colG04017;

    /** 受损机电泵站数量 (座) */
    private BigDecimal colG04018;

    /** 受损机电泵站经济损失 (万元) */
    private BigDecimal colG04019;

    /** 受损水文测站数量 (个) */
    private BigDecimal colG04020;

    /** 受损水文测站经济损失 (万元) */
    private BigDecimal colG04021;

    /** 防洪排灌设施经济损失小计 (万元) */
    private BigDecimal colG04022;

    /** 二、受损其他水利工程经济损失 (万元) */
    private BigDecimal colG04023;

    /** 三、基础设施（水利）经济损失合计 (万元) */
    private BigDecimal colG04024;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
