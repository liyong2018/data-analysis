package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十一）公共服务（新闻出版系统）损失统计表
 */
@Data
@TableName("table_H06")
public class TableH06 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损新闻出版公共服务机构数量 (个) */
    private BigDecimal colH06001;

    /** 受损新闻出版公共服务机构经济损失 (万元) */
    private BigDecimal colH06002;

    /** 受损影剧院数量 (个) */
    private BigDecimal colH06003;

    /** 受损影剧院经济损失 (万元) */
    private BigDecimal colH06004;

    /** 受损农家书屋数量 (个) */
    private BigDecimal colH06005;

    /** 受损农家书屋经济损失 (万元) */
    private BigDecimal colH06006;

    /** 受损其他新闻出版系统经济损失 (万元) */
    private BigDecimal colH06007;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH06008;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH06009;

    /** 公共服务（新闻出版系统）经济损失合计 (万元) */
    private BigDecimal colH06010;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
