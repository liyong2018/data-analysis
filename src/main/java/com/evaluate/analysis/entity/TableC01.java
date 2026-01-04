package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （五）居民家庭财产损失统计表
 */
@Data
@TableName("table_C01")
public class TableC01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受灾家庭户数 (户) */
    private BigDecimal colC01001;

    /** 受损生产性固定资产经济损失 (万元) */
    private BigDecimal colC01002;

    /** 受损家庭耐用消费品经济损失 (万元) */
    private BigDecimal colC01003;

    /** 受损其他财产损失 (万元) */
    private BigDecimal colC01004;

    /** 农村家庭财产损失小计 (万元) */
    private BigDecimal colC01005;

    /** 受灾家庭户数 (户) */
    private BigDecimal colC01006;

    /** 受损生产性固定资产经济损失 (万元) */
    private BigDecimal colC01007;

    /** 受损家庭耐用消费品经济损失 (万元) */
    private BigDecimal colC01008;

    /** 受损其他财产损失 (万元) */
    private BigDecimal colC01009;

    /** 城镇家庭财产损失小计 (万元) */
    private BigDecimal colC01010;

    /** 三、居民家庭财产损失合计 (万元) */
    private BigDecimal colC01011;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
