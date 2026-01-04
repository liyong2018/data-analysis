package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （七）工业损失统计表
 */
@Data
@TableName("table_E01")
public class TableE01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损工业企业数量 (个) */
    private BigDecimal colE01001;

    /** 倒损厂房、仓库面积 (平方米) */
    private BigDecimal colE01002;

    /** 倒损厂房、仓库经济损失 (万元) */
    private BigDecimal colE01003;

    /** 受损设备设施数量 (台套) */
    private BigDecimal colE01004;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colE01005;

    /** 受损原材料、半成品、产成品经济损失 (万元) */
    private BigDecimal colE01006;

    /** 规模以上工业经济损失小计 (万元) */
    private BigDecimal colE01007;

    /** 受损工业企业数量 (个) */
    private BigDecimal colE01008;

    /** 倒损厂房、仓库面积 (平方米) */
    private BigDecimal colE01009;

    /** 倒损厂房、仓库经济损失 (万元) */
    private BigDecimal colE01010;

    /** 受损设备设施数量 (台套) */
    private BigDecimal colE01011;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colE01012;

    /** 受损原材料、半成品、产成品经济损失 (万元) */
    private BigDecimal colE01013;

    /** 规模以下工业经济损失小计 (万元) */
    private BigDecimal colE01014;

    /** 三、工业经济损失合计 (万元) */
    private BigDecimal colE01015;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
