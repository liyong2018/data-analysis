package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十六）公共服务（文化遗产）损失统计表
 */
@Data
@TableName("table_H11")
public class TableH11 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损全国重点文物保护单位数量 (处) */
    private BigDecimal colH11001;

    /** 受损省级重点文物保护单位数量 (处) */
    private BigDecimal colH11002;

    /** 受损市县级重点文物保护单位数量 (处) */
    private BigDecimal colH11003;

    /** 受损世界文化遗产数量 (处) */
    private BigDecimal colH11004;

    /** 受损尚未核定公布为文物保护单位的不可移动文物数量 (处) */
    private BigDecimal colH11005;

    /** 受损珍贵文物数量 (件套) */
    private BigDecimal colH11006;

    /** 受损一般文物数量 (件套) */
    private BigDecimal colH11007;

    /** 受损名城（含历史街区）数量 (处) */
    private BigDecimal colH11008;

    /** 受损名镇数量 (处) */
    private BigDecimal colH11009;

    /** 受损名村数量 (处) */
    private BigDecimal colH11010;

    /** 受损中国传统村落数量 (处) */
    private BigDecimal colH11011;

    /** 受损非物质文化遗产数量 (处) */
    private BigDecimal colH11012;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
