package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十三）公共服务（社会保障与社会服务系统）损失统计表
 */
@Data
@TableName("table_H08")
public class TableH08 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损县级及以上社会保障服务机构数量 (个) */
    private BigDecimal colH08001;

    /** 受损县级及以上社会保障服务机构经济损失 (万元) */
    private BigDecimal colH08002;

    /** 受损乡（镇、街道）社会（劳动）保障事务所数量 (个) */
    private BigDecimal colH08003;

    /** 受损乡（镇、街道）社会（劳动）保障事务所经济损失 (万元) */
    private BigDecimal colH08004;

    /** 受损社区（村）社会（劳动）保障工作站数量 (个) */
    private BigDecimal colH08005;

    /** 受损社区（村）社会（劳动）保障工作站经济损失 (万元) */
    private BigDecimal colH08006;

    /** 社会保障系统经济损失小计 (万元) */
    private BigDecimal colH08007;

    /** 受损县级及以上养老服务机构数量 (个) */
    private BigDecimal colH08008;

    /** 受损县级及以上养老服务机构经济损失 (万元) */
    private BigDecimal colH08009;

    /** 受损县级以下养老服务机构数量 (个) */
    private BigDecimal colH08010;

    /** 受损县级以下养老服务机构经济损失 (万元) */
    private BigDecimal colH08011;

    /** 受损县级及以上优抚安置服务机构数量 (个) */
    private BigDecimal colH08012;

    /** 受损县级及以上优抚安置服务机构经济损失 (万元) */
    private BigDecimal colH08013;

    /** 受损县级以下优抚安置服务机构数量 (个) */
    private BigDecimal colH08014;

    /** 受损县级以下优抚安置服务机构经济损失 (万元) */
    private BigDecimal colH08015;

    /** 受损县级及以上社会福利服务机构数量 (个) */
    private BigDecimal colH08016;

    /** 受损县级及以上社会福利服务机构经济损失 (万元) */
    private BigDecimal colH08017;

    /** 受损县级以下社会福利服务机构数量 (个) */
    private BigDecimal colH08018;

    /** 受损县级以下社会福利服务机构经济损失 (万元) */
    private BigDecimal colH08019;

    /** 受损县级及以上城乡社区服务机构数量 (个) */
    private BigDecimal colH08020;

    /** 受损县级及以上城乡社区服务机构经济损失 (万元) */
    private BigDecimal colH08021;

    /** 受损县级以下城乡社区服务机构数量 (个) */
    private BigDecimal colH08022;

    /** 受损县级以下城乡社区服务机构经济损失 (万元) */
    private BigDecimal colH08023;

    /** 受损县级及以上社会救助服务机构数量 (个) */
    private BigDecimal colH08024;

    /** 受损县级及以上社会救助服务机构经济损失 (万元) */
    private BigDecimal colH08025;

    /** 受损县级以下社会救助服务机构数量 (个) */
    private BigDecimal colH08026;

    /** 受损县级以下社会救助服务机构经济损失 (万元) */
    private BigDecimal colH08027;

    /** 受损县级及以上防灾减灾救灾服务机构数量 (个) */
    private BigDecimal colH08028;

    /** 受损县级及以上防灾减灾救灾服务机构经济损失 (万元) */
    private BigDecimal colH08029;

    /** 受损县级以下防灾减灾救灾服务机构数量 (个) */
    private BigDecimal colH08030;

    /** 受损县级以下防灾减灾救灾服务机构经济损失 (万元) */
    private BigDecimal colH08031;

    /** 受损其他社会服务机构数量 (个) */
    private BigDecimal colH08032;

    /** 受损其他社会服务系统经济损失 (万元) */
    private BigDecimal colH08033;

    /** 社会服务系统经济损失小计 (万元) */
    private BigDecimal colH08034;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH08035;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH08036;

    /** 四、公共服务（社会保障与社会服务系统）经济损失合计 (万元) */
    private BigDecimal colH08037;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
