package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十二）公共服务（体育）损失统计表
 */
@Data
@TableName("table_H07")
public class TableH07 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损体育运动学校数量 (个) */
    private BigDecimal colH07001;

    /** 受损体育训练基地数量 (个) */
    private BigDecimal colH07002;

    /** 受损体育机构经济损失 (万元) */
    private BigDecimal colH07003;

    /** 受损体育机构数量小计 (个) */
    private BigDecimal colH07004;

    /** 受损体育场地数量 (个) */
    private BigDecimal colH07005;

    /** 受损体育场地经济损失 (万元) */
    private BigDecimal colH07006;

    /** 受损体育器材数量 (个) */
    private BigDecimal colH07007;

    /** 受损体育器材经济损失 (万元) */
    private BigDecimal colH07008;

    /** 受损体育场数量 (个) */
    private BigDecimal colH07009;

    /** 受损体育场观众座席 (座) */
    private BigDecimal colH07010;

    /** 受损体育场经济损失 (万元) */
    private BigDecimal colH07011;

    /** 受损体育馆数量 (个) */
    private BigDecimal colH07012;

    /** 受损体育馆观众座席 (座) */
    private BigDecimal colH07013;

    /** 受损体育馆经济损失 (万元) */
    private BigDecimal colH07014;

    /** 受损游泳馆数量 (个) */
    private BigDecimal colH07015;

    /** 受损游泳馆观众座席 (座) */
    private BigDecimal colH07016;

    /** 受损游泳馆经济损失 (万元) */
    private BigDecimal colH07017;

    /** 受损体育场馆数量小计 (个) */
    private BigDecimal colH07018;

    /** 受损体育场馆观众座席数量小计 (座) */
    private BigDecimal colH07019;

    /** 受损体育场馆经济损失小计 (万元) */
    private BigDecimal colH07020;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH07021;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH07022;

    /** 六、受损公共服务（体育）经济损失合计 (万元) */
    private BigDecimal colH07023;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
