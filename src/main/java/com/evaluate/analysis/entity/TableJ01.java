package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （二十八）基础指标统计表
 */
@Data
@TableName("table_J01")
public class TableJ01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 总人口 (人) */
    private BigDecimal colJ01001;

    /** 城镇人口 (人) */
    private BigDecimal colJ01002;

    /** 农村人口 (人) */
    private BigDecimal colJ01003;

    /** 女性人口 (人) */
    private BigDecimal colJ01004;

    /** 18 岁以下人口 (人) */
    private BigDecimal colJ01005;

    /** 60 岁及以上人口 (人) */
    private BigDecimal colJ01006;

    /** 总户数 (户) */
    private BigDecimal colJ01007;

    /** 城镇户数 (户) */
    private BigDecimal colJ01008;

    /** 农村居民家庭住房间数 (间/人) */
    private BigDecimal colJ01009;

    /** 农村居民家庭住房结构：钢结构 (间/人) */
    private BigDecimal colJ01010;

    /** 农村居民家庭住房结构：钢筋混凝土结构 (间/人) */
    private BigDecimal colJ01011;

    /** 农村居民家庭住房结构：砌体结构 (间/人) */
    private BigDecimal colJ01012;

    /** 农村居民家庭住房结构：木（竹）结构 (间/人) */
    private BigDecimal colJ01013;

    /** 农村居民家庭住房结构：其他结构 (间/人) */
    private BigDecimal colJ01014;

    /** 农村居民家庭住房价值 (元/间) */
    private BigDecimal colJ01015;

    /** 城镇居民家庭住房面积 (平方米/人) */
    private BigDecimal colJ01016;

    /** 城镇居民家庭住房结构：钢结构 (平方米/人) */
    private BigDecimal colJ01017;

    /** 城镇居民家庭住房结构：钢筋混凝土结构 (平方米/人) */
    private BigDecimal colJ01018;

    /** 城镇居民家庭住房结构：砌体结构 (平方米/人) */
    private BigDecimal colJ01019;

    /** 城镇居民家庭住房结构：木（竹）结构 (平方米/人) */
    private BigDecimal colJ01020;

    /** 城镇居民家庭住房结构：其他结构 (平方米/人) */
    private BigDecimal colJ01021;

    /** 城镇居民家庭住房价值 (元/平方米) */
    private BigDecimal colJ01022;

    /** 常用耕地面积 (公顷) */
    private BigDecimal colJ01023;

    /** 粮食单位面积产量 (千克/公顷) */
    private BigDecimal colJ01024;

    /** 大牲畜数量 (头只) */
    private BigDecimal colJ01025;

    /** 水产品产量 (吨) */
    private BigDecimal colJ01026;

    /** 主要农业机械拥有量 (台) */
    private BigDecimal colJ01027;

    /** 农林牧渔业产值 (万元) */
    private BigDecimal colJ01028;

    /** 规模以上工业：企业单位数 (个) */
    private BigDecimal colJ01029;

    /** 规模以上工业：工业总产值 (万元) */
    private BigDecimal colJ01030;

    /** 规模以上工业：资产总计 (万元) */
    private BigDecimal colJ01031;

    /** 规模以上工业：固定资产净值 (万元) */
    private BigDecimal colJ01032;

    /** 规模以上工业：全部从业人员年平均人数 (人) */
    private BigDecimal colJ01033;

    /** 规模以下工业：资产总计 (万元) */
    private BigDecimal colJ01034;

    /** 规模以下工业：固定资产净值 (万元) */
    private BigDecimal colJ01035;

    /** 规模以下工业：全部从业人员年平均人数 (人) */
    private BigDecimal colJ01036;

    /** 第三产业增加值 (亿元) */
    private BigDecimal colJ01037;

    /** 批发和零售业资产总计 (亿元) */
    private BigDecimal colJ01038;

    /** 住宿和餐饮业资产总计 (亿元) */
    private BigDecimal colJ01039;

    /** 金融业资产总计 (亿元) */
    private BigDecimal colJ01040;

    /** 房地产业资产总计 (亿元) */
    private BigDecimal colJ01041;

    /** 其他产业资产总计 (亿元) */
    private BigDecimal colJ01042;

    /** 教育经费 (万元) */
    private BigDecimal colJ01043;

    /** 高等教育学校数量 (个) */
    private BigDecimal colJ01044;

    /** 高等教育学校教职工数量 (人) */
    private BigDecimal colJ01045;

    /** 高等教育学校在校学生数量 (人) */
    private BigDecimal colJ01046;

    /** 中等教育学校数量 (个) */
    private BigDecimal colJ01047;

    /** 中等教育学校教职工数量 (人) */
    private BigDecimal colJ01048;

    /** 中等教育学校在校学生数量 (人) */
    private BigDecimal colJ01049;

    /** 初等教育学校数量 (个) */
    private BigDecimal colJ01050;

    /** 初等教育学校教职工数量 (人) */
    private BigDecimal colJ01051;

    /** 初等教育学校在校学生数量 (人) */
    private BigDecimal colJ01052;

    /** 学前教育机构数量 (个) */
    private BigDecimal colJ01053;

    /** 学前教育机构教职工数量 (人) */
    private BigDecimal colJ01054;

    /** 学前教育机构在校学生数量 (人) */
    private BigDecimal colJ01055;

    /** 特殊教育学校数量 (个) */
    private BigDecimal colJ01056;

    /** 特殊教育学校教职工数量 (人) */
    private BigDecimal colJ01057;

    /** 特殊教育学校在校学生数量 (人) */
    private BigDecimal colJ01058;

    /** 研究与实验发展人员数量 (人) */
    private BigDecimal colJ01059;

    /** 研究与实验发展经费 (万元) */
    private BigDecimal colJ01060;

    /** 卫生机构数量 (个) */
    private BigDecimal colJ01061;

    /** 卫生人员与卫生技术人员总数 (人) */
    private BigDecimal colJ01062;

    /** 医疗卫生机构床位数 (张) */
    private BigDecimal colJ01063;

    /** 社会服务机构床位数 (张) */
    private BigDecimal colJ01064;

    /** 文化机构（博物馆、图书馆、文化馆等）数量 (个) */
    private BigDecimal colJ01065;

    /** 广电技术台站（机）数量 (座/部) */
    private BigDecimal colJ01066;

    /** 体育场馆数量 (个) */
    private BigDecimal colJ01067;

    /** 体育训练基地数量 (个) */
    private BigDecimal colJ01068;

    /** 不可移动文物数量 (处) */
    private BigDecimal colJ01069;

    /** 公路里程 (千米) */
    private BigDecimal colJ01070;

    /** 高速公路里程 (千米) */
    private BigDecimal colJ01071;

    /** 公路客运量 (万人) */
    private BigDecimal colJ01072;

    /** 公路货运量 (万吨) */
    private BigDecimal colJ01073;

    /** 铁路营业里程 (千米) */
    private BigDecimal colJ01074;

    /** 铁路客运量 (万人) */
    private BigDecimal colJ01075;

    /** 铁路货运量 (万吨) */
    private BigDecimal colJ01076;

    /** 内河航道里程 (千米) */
    private BigDecimal colJ01077;

    /** 民航航线里程 (千米) */
    private BigDecimal colJ01078;

    /** 电信业务总量 (亿元) */
    private BigDecimal colJ01079;

    /** 邮政业务总量 (亿元) */
    private BigDecimal colJ01080;

    /** 电力装机容量 (万千瓦) */
    private BigDecimal colJ01081;

    /** 电力生产量 (亿千瓦时) */
    private BigDecimal colJ01082;

    /** 电力消费量 (亿千瓦时) */
    private BigDecimal colJ01083;

    /** 石油可供量 (万吨) */
    private BigDecimal colJ01084;

    /** 石油消费量 (万吨) */
    private BigDecimal colJ01085;

    /** 煤炭可供量 (万吨) */
    private BigDecimal colJ01086;

    /** 煤炭生产量 (万吨) */
    private BigDecimal colJ01087;

    /** 煤炭消费量 (万吨) */
    private BigDecimal colJ01088;

    /** 天然气可供量 (亿立方米) */
    private BigDecimal colJ01089;

    /** 天然气生产量 (亿立方米) */
    private BigDecimal colJ01090;

    /** 天然气消费量 (亿立方米) */
    private BigDecimal colJ01091;

    /** 煤层气可供量 (亿立方米) */
    private BigDecimal colJ01092;

    /** 煤层气生产量 (亿立方米) */
    private BigDecimal colJ01093;

    /** 煤层气消费量 (亿立方米) */
    private BigDecimal colJ01094;

    /** 水库数 (座) */
    private BigDecimal colJ01095;

    /** 水库总容量 (万立方米) */
    private BigDecimal colJ01096;

    /** 除涝面积 (公顷) */
    private BigDecimal colJ01097;

    /** 堤防保护面积 (公顷) */
    private BigDecimal colJ01098;

    /** 灌区有效灌溉面积 (公顷) */
    private BigDecimal colJ01099;

    /** 年末运营公交车量 (辆) */
    private BigDecimal colJ01100;

    /** 城市道路长度 (千米) */
    private BigDecimal colJ01101;

    /** 城市供热总量 (万吉焦) */
    private BigDecimal colJ01102;

    /** 城市供热管道长度 (千米) */
    private BigDecimal colJ01103;

    /** 城市供水综合生产能力 (万立方米/日) */
    private BigDecimal colJ01104;

    /** 城市排水管道长度 (千米) */
    private BigDecimal colJ01105;

    /** 城市污水日处理能力 (万立方米) */
    private BigDecimal colJ01106;

    /** 城市生活垃圾日处理能力 (吨/日) */
    private BigDecimal colJ01107;

    /** 城市绿化面积 (公顷) */
    private BigDecimal colJ01108;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
