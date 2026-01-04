package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （六）农林牧渔业损失统计表
 */
@Data
@TableName("table_D01")
public class TableD01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 农作物受灾面积 (公顷) */
    private BigDecimal colD01001;

    /** 农作物成灾面积 (公顷) */
    private BigDecimal colD01002;

    /** 农作物绝收面积 (公顷) */
    private BigDecimal colD01003;

    /** 农作物经济损失 (万元) */
    private BigDecimal colD01004;

    /** 受损农业生产大棚面积 (公顷) */
    private BigDecimal colD01005;

    /** 受损农业生产大棚经济损失 (万元) */
    private BigDecimal colD01006;

    /** 农业经济损失小计 (万元) */
    private BigDecimal colD01007;

    /** 森林草原受灾面积 (公顷) */
    private BigDecimal colD01008;

    /** 受损林木蓄积量 (万立方米) */
    private BigDecimal colD01009;

    /** 森林经济损失 (万元) */
    private BigDecimal colD01010;

    /** 灌木林地和疏林地受灾面积 (公顷) */
    private BigDecimal colD01011;

    /** 灌木林地和疏林地经济损失 (万元) */
    private BigDecimal colD01012;

    /** 未成林造林地受灾面积 (公顷) */
    private BigDecimal colD01013;

    /** 未成林造林地经济损失 (万元) */
    private BigDecimal colD01014;

    /** 苗圃良种受灾面积 (公顷) */
    private BigDecimal colD01015;

    /** 苗圃良种经济损失 (万元) */
    private BigDecimal colD01016;

    /** 受损野生动植物驯养繁殖基地（场）数量 (个) */
    private BigDecimal colD01017;

    /** 受损野生动植物驯养繁殖基地（场）经济损失 (万元) */
    private BigDecimal colD01018;

    /** 林区基础设施经济损失 (万元) */
    private BigDecimal colD01019;

    /** 林业经济损失小计 (万元) */
    private BigDecimal colD01020;

    /** 死亡大牲畜数量 (头只) */
    private BigDecimal colD01021;

    /** 死亡小牲畜数量 (头只) */
    private BigDecimal colD01022;

    /** 死亡家禽数量 (只) */
    private BigDecimal colD01023;

    /** 死亡畜禽经济损失 (万元) */
    private BigDecimal colD01024;

    /** 倒塌损坏畜禽圈舍面积 (平方米) */
    private BigDecimal colD01025;

    /** 倒塌损坏畜禽圈舍经济损失 (万元) */
    private BigDecimal colD01026;

    /** 受损饲草料数量 (吨) */
    private BigDecimal colD01027;

    /** 受损饲草料经济损失 (万元) */
    private BigDecimal colD01028;

    /** 畜牧业经济损失小计 (万元) */
    private BigDecimal colD01029;

    /** 受灾水产养殖面积 (公顷) */
    private BigDecimal colD01030;

    /** 水产品损失量 (万吨) */
    private BigDecimal colD01031;

    /** 水产品经济损失 (万元) */
    private BigDecimal colD01032;

    /** 水产种苗经济损失 (万元) */
    private BigDecimal colD01033;

    /** 受损养殖设施经济损失 (万元) */
    private BigDecimal colD01034;

    /** 渔船损失数量 (艘) */
    private BigDecimal colD01035;

    /** 受损渔船鱼港经济损失 (万元) */
    private BigDecimal colD01036;

    /** 渔业经济损失小计 (万元) */
    private BigDecimal colD01037;

    /** 受损农业机械数量 (台套) */
    private BigDecimal colD01038;

    /** 受损农业机械经济损失 (万元) */
    private BigDecimal colD01039;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colD01040;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colD01041;

    /** 七、农林牧渔业经济损失合计 (万元) */
    private BigDecimal colD01042;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
