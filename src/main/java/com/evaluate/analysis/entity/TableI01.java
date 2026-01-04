package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十七）资源与环境损失统计表
 */
@Data
@TableName("table_I01")
public class TableI01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 毁坏耕地面积 (公顷) */
    private BigDecimal colI01001;

    /** 毁坏林地面积 (公顷) */
    private BigDecimal colI01002;

    /** 毁坏草地面积 (公顷) */
    private BigDecimal colI01003;

    /** 毁坏非煤矿山资源数量 (处) */
    private BigDecimal colI01004;

    /** 毁坏非煤矿山资源面积 (万平方米) */
    private BigDecimal colI01005;

    /** 受损国家级自然保护地数量 (个) */
    private BigDecimal colI01006;

    /** 受损地方级自然保护地数量 (个) */
    private BigDecimal colI01007;

    /** 野生动物伤亡数量 (头只) */
    private BigDecimal colI01008;

    /** 其中：国家重点保护野生动物伤亡数量 (头只) */
    private BigDecimal colI01009;

    /** 地表水污染面积 (公顷) */
    private BigDecimal colI01010;

    /** 土壤污染面积 (公顷) */
    private BigDecimal colI01011;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
