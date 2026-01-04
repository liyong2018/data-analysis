package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十）公共服务（广播电视系统）损失统计表
 */
@Data
@TableName("table_H05")
public class TableH05 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损广播电视台数量 (个) */
    private BigDecimal colH05001;

    /** 受损广播电视台经济损失 (万元) */
    private BigDecimal colH05002;

    /** 受损无线广播电视发射（监测）台数量 (个) */
    private BigDecimal colH05003;

    /** 受损无线广播电视发射（监测）台经济损失 (万元) */
    private BigDecimal colH05004;

    /** 受损广播电视传输覆盖网络经济损失 (万元) */
    private BigDecimal colH05005;

    /** 受损广播电视有线前端经济损失 (万元) */
    private BigDecimal colH05006;

    /** 受损乡镇广播电视站播出设备经济损失 (万元) */
    private BigDecimal colH05007;

    /** 受损乡镇广播电视站传输设备经济损失 (万元) */
    private BigDecimal colH05008;

    /** 受损广播电视村村通设施经济损失 (万元) */
    private BigDecimal colH05009;

    /** 受损其他广播电视公共服务机构数量 (个) */
    private BigDecimal colH05010;

    /** 受损其他广播电视公共服务机构经济损失 (万元) */
    private BigDecimal colH05011;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH05012;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH05013;

    /** 公共服务（广播电视系统）经济损失合计 (万元) */
    private BigDecimal colH05014;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
