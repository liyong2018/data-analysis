package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二）人员受灾情况统计表
 */
@Data
@TableName("table_A01")
public class TableA01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受灾人口 (人) */
    private BigDecimal colA01001;

    /** 因灾死亡人口 (人) */
    private BigDecimal colA01002;

    /** 因灾失踪人口 (人) */
    private BigDecimal colA01003;

    /** 因灾受伤人口 (人) */
    private BigDecimal colA01004;

    /** 其中：因灾重伤人口 (人) */
    private BigDecimal colA01005;

    /** 饮水困难人口 (人) */
    private BigDecimal colA01006;

    /** 紧急转移安置人口 (人) */
    private BigDecimal colA01007;

    /** 其中：集中安置人口 (人) */
    private BigDecimal colA01008;

    /** 分散安置人口 (人) */
    private BigDecimal colA01009;

    /** 需紧急生活救助人口 (人) */
    private BigDecimal colA01010;

    /** 需过渡期生活救助人口 (人) */
    private BigDecimal colA01011;

    /** 其中：女性 (人) */
    private BigDecimal colA01012;

    /** 老人（60岁及以上） (人) */
    private BigDecimal colA01013;

    /** 儿童（18岁以下） (人) */
    private BigDecimal colA01014;

    /** 三无人员 (人) */
    private BigDecimal colA01015;

    /** 三孤人员 (人) */
    private BigDecimal colA01016;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
