package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十）基础设施（通信）损失统计表
 */
@Data
@TableName("table_G02")
public class TableG02 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损通信线路长度 (皮长公里) */
    private BigDecimal colG02001;

    /** 受损通信基站数量 (个) */
    private BigDecimal colG02002;

    /** 通信基础设施经济损失小计 (万元) */
    private BigDecimal colG02003;

    /** 受损邮政设备设施数量 (个) */
    private BigDecimal colG02004;

    /** 受损邮政设备设施经济损失 (万元) */
    private BigDecimal colG02005;

    /** 受损邮政枢纽数量 (个) */
    private BigDecimal colG02006;

    /** 受损邮政枢纽数量经济损失 (万元) */
    private BigDecimal colG02007;

    /** 邮政经济损失小计 (万元) */
    private BigDecimal colG02008;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colG02009;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colG02010;

    /** 四、其他通信基础设施经济损失 (万元) */
    private BigDecimal colG02011;

    /** 五、基础设施（通信）经济损失合计 (万元) */
    private BigDecimal colG02012;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
