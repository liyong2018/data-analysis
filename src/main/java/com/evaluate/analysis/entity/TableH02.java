package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十七）公共服务（科技系统）损失统计表
 */
@Data
@TableName("table_H02")
public class TableH02 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损科研机构数量 (个) */
    private BigDecimal colH02001;

    /** 受损专业技术服务机构数量 (个) */
    private BigDecimal colH02002;

    /** 受损其他科研机构数量 (个) */
    private BigDecimal colH02003;

    /** 受损设备设施经济损失 (万元) */
    private BigDecimal colH02004;

    /** 研究和试验系统经济损失小计 (万元) */
    private BigDecimal colH02005;

    /** 受损气象监测站点数量 (个) */
    private BigDecimal colH02006;

    /** 受损气象监测站点设备设施数量 (台套) */
    private BigDecimal colH02007;

    /** 受损气象监测站点经济损失 (万元) */
    private BigDecimal colH02008;

    /** 受损地震监测站点数量 (个) */
    private BigDecimal colH02009;

    /** 受损地震监测站点设备设施数量 (台套) */
    private BigDecimal colH02010;

    /** 受损地震监测站点经济损失 (万元) */
    private BigDecimal colH02011;

    /** 受损海洋监测站点数量 (个) */
    private BigDecimal colH02012;

    /** 受损海洋监测站点设备设施数量 (台套) */
    private BigDecimal colH02013;

    /** 受损海洋监测站点经济损失 (万元) */
    private BigDecimal colH02014;

    /** 受损测绘基准站点数量 (个) */
    private BigDecimal colH02015;

    /** 受损测绘基准站点设备设施数量 (台套) */
    private BigDecimal colH02016;

    /** 受损测绘基准站点经济损失 (万元) */
    private BigDecimal colH02017;

    /** 受损环境保护监测站点数量 (个) */
    private BigDecimal colH02018;

    /** 受损环境保护监测站点设备设施数量 (台套) */
    private BigDecimal colH02019;

    /** 受损环境保护监测站点经济损失 (万元) */
    private BigDecimal colH02020;

    /** 受损地质勘查监测站点数量 (个) */
    private BigDecimal colH02021;

    /** 受损地质勘查监测站点设备设施数量 (台/套) */
    private BigDecimal colH02022;

    /** 受损地质勘查监测站点经济损失 (万元) */
    private BigDecimal colH02023;

    /** 受损水文、水资源、防汛监测站点数量 (个) */
    private BigDecimal colH02024;

    /** 受损水文、水资源、防汛监测站点设备设施数量 (台套) */
    private BigDecimal colH02025;

    /** 受损水文、水资源、防汛监测站点经济损失 (万元) */
    private BigDecimal colH02026;

    /** 受损林业生态监测站点数量 (个) */
    private BigDecimal colH02027;

    /** 受损林业生态监测站点设备设施数量 (台/套) */
    private BigDecimal colH02028;

    /** 受损林业生态监测站点经济损失 (万元) */
    private BigDecimal colH02029;

    /** （九）专业监测系统经济损失小计 (万元) */
    private BigDecimal colH02030;

    /** 三、其他科技系统经济损失 (万元) */
    private BigDecimal colH02031;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH02032;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH02033;

    /** 五、公共服务（科技系统）经济损失合计 (万元) */
    private BigDecimal colH02034;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
