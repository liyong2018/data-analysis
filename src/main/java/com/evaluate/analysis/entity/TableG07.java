package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十五）基础设施（地质灾害防治设施）损失统计表
 */
@Data
@TableName("table_G07")
public class TableG07 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损滑坡治理工程数量 (处) */
    private BigDecimal colG07001;

    /** 受损滑坡治理工程经济损失 (万元) */
    private BigDecimal colG07002;

    /** 受损崩塌治理工程数量 (处) */
    private BigDecimal colG07003;

    /** 受损崩塌治理工程经济损失 (万元) */
    private BigDecimal colG07004;

    /** 受损泥石流治理工程数量 (处) */
    private BigDecimal colG07005;

    /** 受损泥石流治理工程经济损失 (万元) */
    private BigDecimal colG07006;

    /** 受损地面塌陷治理工程数量 (处) */
    private BigDecimal colG07007;

    /** 受损地面塌陷治理工程经济损失 (万元) */
    private BigDecimal colG07008;

    /** 受损地裂缝治理工程数量 (处) */
    private BigDecimal colG07009;

    /** 受损地裂缝治理工程经济损失 (万元) */
    private BigDecimal colG07010;

    /** 受损地面沉降治理工程数量 (处) */
    private BigDecimal colG07011;

    /** 受损地面沉降治理工程经济损失 (万元) */
    private BigDecimal colG07012;

    /** 其他地质灾害防治设施毁损数量 (处) */
    private BigDecimal colG07013;

    /** 其他地质灾害防治设施毁损经济损失 (万元) */
    private BigDecimal colG07014;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colG07015;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colG07016;

    /** 基础设施（地质灾害防治设施）经济损失合计 (万元) */
    private BigDecimal colG07017;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
