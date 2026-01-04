package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （十八）公共服务（医疗卫生系统）损失统计表
 */
@Data
@TableName("table_H03")
public class TableH03 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损医院数量 (个) */
    private BigDecimal colH03001;

    /** 受损医院经济损失 (万元) */
    private BigDecimal colH03002;

    /** 受损基层医疗卫生机构数量 (个) */
    private BigDecimal colH03003;

    /** 受损基层医疗卫生机构经济损失 (万元) */
    private BigDecimal colH03004;

    /** 受损专业公共卫生机构数量 (个) */
    private BigDecimal colH03005;

    /** 受损专业公共卫生机构经济损失 (万元) */
    private BigDecimal colH03006;

    /** 受损其他医疗卫生机构数量 (个) */
    private BigDecimal colH03007;

    /** 受损其他医疗卫生机构经济损失 (万元) */
    private BigDecimal colH03008;

    /** 医疗卫生机构经济损失小计 (万元) */
    private BigDecimal colH03009;

    /** 受损食品药品监督管理机构数量 (个) */
    private BigDecimal colH03010;

    /** 受损食品药品监督管理机构经济损失 (万元) */
    private BigDecimal colH03011;

    /** 三、其他医疗卫生系统经济损失 (万元) */
    private BigDecimal colH03012;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colH03013;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colH03014;

    /** 五、公共服务（医疗卫生系统）经济损失合计 (万元) */
    private BigDecimal colH03015;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
