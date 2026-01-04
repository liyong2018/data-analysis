package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （八）服务业损失统计表
 */
@Data
@TableName("table_F01")
public class TableF01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损网点数量 (个) */
    private BigDecimal colF01001;

    /** 受损设备设施数量 (台套) */
    private BigDecimal colF01002;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colF01003;

    /** 受损商品经济损失 (万元) */
    private BigDecimal colF01004;

    /** 批发和零售业经济损失小计 (万元) */
    private BigDecimal colF01005;

    /** 受损住宿和餐饮网点数量 (个) */
    private BigDecimal colF01006;

    /** 受损设备设施数量 (台套) */
    private BigDecimal colF01007;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colF01008;

    /** 住宿和餐饮业经济损失小计 (万元) */
    private BigDecimal colF01009;

    /** 受损网点数量 (个) */
    private BigDecimal colF01010;

    /** 受损设备设施数量 (台套) */
    private BigDecimal colF01011;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colF01012;

    /** 金融业经济损失小计 (万元) */
    private BigDecimal colF01013;

    /** 受损网点数量 (个) */
    private BigDecimal colF01014;

    /** 受损设备设施数量 (台套) */
    private BigDecimal colF01015;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colF01016;

    /** 文化、体育和娱乐业经济损失小计 (万元) */
    private BigDecimal colF01017;

    /** 受损农林牧渔服务业经济损失 (万元) */
    private BigDecimal colF01018;

    /** 受损网点数量 (个) */
    private BigDecimal colF01019;

    /** 受损设备设施数量 (台套) */
    private BigDecimal colF01020;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colF01021;

    /** 受损商品经济损失 (万元) */
    private BigDecimal colF01022;

    /** 其他服务业经济损失小计 (万元) */
    private BigDecimal colF01023;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colF01024;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colF01025;

    /** 八、服务业经济损失合计 (万元) */
    private BigDecimal colF01026;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
