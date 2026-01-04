package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十四）基础设施（农村地区生活设施）损失统计表
 */
@Data
@TableName("table_G06")
public class TableG06 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损村内道路经济损失 (万元) */
    private BigDecimal colG06001;

    /** 受损供水设备设施经济损失 (万元) */
    private BigDecimal colG06002;

    /** 受损排水设备设施经济损失 (万元) */
    private BigDecimal colG06003;

    /** 受损供电设备设施经济损失 (万元) */
    private BigDecimal colG06004;

    /** 受损供气设备设施经济损失 (万元) */
    private BigDecimal colG06005;

    /** 受损供热设备设施经济损失 (万元) */
    private BigDecimal colG06006;

    /** 受损垃圾处理设备设施经济损失 (万元) */
    private BigDecimal colG06007;

    /** 受损农村地区其他生活设施损失 (万元) */
    private BigDecimal colG06008;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colG06009;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colG06010;

    /** 基础设施（农村地区生活设施）经济损失合计 (万元) */
    private BigDecimal colG06011;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
