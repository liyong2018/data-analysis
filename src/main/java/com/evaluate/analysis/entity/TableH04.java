package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十九）公共服务（文化系统）损失统计表
 */
@Data
@TableName("table_H04")
public class TableH04 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损图书馆（档案馆）数量 (个) */
    private BigDecimal colH04001;

    /** 受损图书馆（档案馆）经济损失 (万元) */
    private BigDecimal colH04002;

    /** 受损博物馆数量 (个) */
    private BigDecimal colH04003;

    /** 受损博物馆经济损失 (万元) */
    private BigDecimal colH04004;

    /** 受损文化馆数量 (个) */
    private BigDecimal colH04005;

    /** 受损文化馆经济损失 (万元) */
    private BigDecimal colH04006;

    /** 受损剧场数量 (个) */
    private BigDecimal colH04007;

    /** 受损剧场经济损失 (万元) */
    private BigDecimal colH04008;

    /** 受损乡镇综合文化站数量 (个) */
    private BigDecimal colH04009;

    /** 受损乡镇综合文化站经济损失 (万元) */
    private BigDecimal colH04010;

    /** 受损社区图书室（文化室）数量 (个) */
    private BigDecimal colH04011;

    /** 受损社区图书室（文化室）经济损失 (万元) */
    private BigDecimal colH04012;

    /** 受损宗教活动场所及宗教院校数量 (个) */
    private BigDecimal colH04013;

    /** 受损宗教活动场所及宗教院校经济损失 (万元) */
    private BigDecimal colH04014;

    /** 受损其他文化系统经济损失 (万元) */
    private BigDecimal colH04015;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH04016;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH04017;

    /** 公共服务（文化系统）经济损失合计 (万元) */
    private BigDecimal colH04018;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
