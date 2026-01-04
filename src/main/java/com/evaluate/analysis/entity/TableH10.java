package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十五）公共服务（社会管理系统）损失统计表
 */
@Data
@TableName("table_H10")
public class TableH10 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损党政机关数量 (个) */
    private BigDecimal colH10001;

    /** 受损党政机关经济损失 (万元) */
    private BigDecimal colH10002;

    /** 受损群众团体、社会团体和其他成员组织数量 (个) */
    private BigDecimal colH10003;

    /** 受损群众团体、社会团体和其他成员组织经济损失 (万元) */
    private BigDecimal colH10004;

    /** 受损基层群众自治组织数量 (个) */
    private BigDecimal colH10005;

    /** 受损基层群众自治组织经济损失 (万元) */
    private BigDecimal colH10006;

    /** 受损国际组织数量 (个) */
    private BigDecimal colH10007;

    /** 受损国际组织经济损失 (万元) */
    private BigDecimal colH10008;

    /** 受损其他社会管理系统经济损失 (万元) */
    private BigDecimal colH10009;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH10010;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH10011;

    /** 公共服务（社会管理系统）经济损失合计 (万元) */
    private BigDecimal colH10012;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
