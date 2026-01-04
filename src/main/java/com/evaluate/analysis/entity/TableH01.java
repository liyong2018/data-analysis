package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十六）公共服务（教育系统）损失统计表
 */
@Data
@TableName("table_H01")
public class TableH01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损高等教育学校数量 (个) */
    private BigDecimal colH01001;

    /** 受损高等教育学校经济损失 (万元) */
    private BigDecimal colH01002;

    /** 受损中等教育学校数量 (个) */
    private BigDecimal colH01003;

    /** 受损中等教育学校经济损失 (万元) */
    private BigDecimal colH01004;

    /** 受损初等教育学校数量 (个) */
    private BigDecimal colH01005;

    /** 受损初等教育学校经济损失 (万元) */
    private BigDecimal colH01006;

    /** 受损学前教育机构数量 (个) */
    private BigDecimal colH01007;

    /** 受损学前教育机构经济损失 (万元) */
    private BigDecimal colH01008;

    /** 受损特殊教育学校数量 (个) */
    private BigDecimal colH01009;

    /** 受损特殊教育学校经济损失 (万元) */
    private BigDecimal colH01010;

    /** 受损其他教育学校（机构）数量 (个) */
    private BigDecimal colH01011;

    /** 受损其他教育学校（机构）经济损失 (万元) */
    private BigDecimal colH01012;

    /** 校舍受损面积 (平方米) */
    private BigDecimal colH01013;

    /** 校舍经济损失 (万元) */
    private BigDecimal colH01014;

    /** 公共服务（教育系统）经济损失合计 (万元) */
    private BigDecimal colH01015;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
