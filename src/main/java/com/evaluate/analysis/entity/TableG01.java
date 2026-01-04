package com.evaluate.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * （九）基础设施（交通运输）损失统计表
 */
@Data
@TableName("table_G01")
public class TableG01 {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 报告ID */
    private String reportId;

    /** 行政区划代码 */
    private String regionCode;

    /** 报告日期 */
    private LocalDateTime reportDate;

    /** 受损路基 (立方米/千米) */
    private BigDecimal colG01001;

    /** 受损路基经济损失 (万元) */
    private BigDecimal colG01002;

    /** 受损路面 (平方米/千米) */
    private BigDecimal colG01003;

    /** 受损路面经济损失 (万元) */
    private BigDecimal colG01004;

    /** 受损桥梁 (延米/座) */
    private BigDecimal colG01005;

    /** 受损桥梁经济损失 (万元) */
    private BigDecimal colG01006;

    /** 受损隧道 (延米/道) */
    private BigDecimal colG01007;

    /** 受损隧道经济损失 (万元) */
    private BigDecimal colG01008;

    /** 受损护坡、驳岸、挡墙数量 (立方米/处) */
    private BigDecimal colG01009;

    /** 受损护坡、驳岸、挡墙经济损失 (万元) */
    private BigDecimal colG01010;

    /** 国省干线经济损失 (万元) */
    private BigDecimal colG01011;

    /** 受损路基 (立方米/千米) */
    private BigDecimal colG01012;

    /** 受损路基经济损失 (万元) */
    private BigDecimal colG01013;

    /** 受损路面 (平方米/千米) */
    private BigDecimal colG01014;

    /** 受损路面经济损失 (万元) */
    private BigDecimal colG01015;

    /** 受损桥梁 (延米/座) */
    private BigDecimal colG01016;

    /** 受损桥梁经济损失 (万元) */
    private BigDecimal colG01017;

    /** 受损隧道 (延米/道) */
    private BigDecimal colG01018;

    /** 受损隧道经济损失 (万元) */
    private BigDecimal colG01019;

    /** 受损护坡、驳岸、挡墙数量 (立方米/处) */
    private BigDecimal colG01020;

    /** 受损护坡、驳岸、挡墙经济损失 (万元) */
    private BigDecimal colG01021;

    /** 其他公路经济损失 (万元) */
    private BigDecimal colG01022;

    /** 受损客运站数量 (个) */
    private BigDecimal colG01023;

    /** 其中：受损等级站数量 (个) */
    private BigDecimal colG01024;

    /** 受损客运站经济损失 (万元) */
    private BigDecimal colG01025;

    /** 受损货运站数量 (个) */
    private BigDecimal colG01026;

    /** 受损货运站经济损失 (万元) */
    private BigDecimal colG01027;

    /** 受损服务区数量 (个) */
    private BigDecimal colG01028;

    /** 受损服务区经济损失 (万元) */
    private BigDecimal colG01029;

    /** 客（货）运站、服务区经济损失 (万元) */
    private BigDecimal colG01030;

    /** （四）公路经济损失小计 (万元) */
    private BigDecimal colG01031;

    /** 受损路基 (立方米/千米) */
    private BigDecimal colG01032;

    /** 受损路基经济损失 (万元) */
    private BigDecimal colG01033;

    /** 受损桥梁 (延米/座) */
    private BigDecimal colG01034;

    /** 受损桥梁经济损失 (万元) */
    private BigDecimal colG01035;

    /** 受损涵洞 (延米/座) */
    private BigDecimal colG01036;

    /** 受损涵洞经济损失 (万元) */
    private BigDecimal colG01037;

    /** 受损隧道 (延米/道) */
    private BigDecimal colG01038;

    /** 受损隧道经济损失 (万元) */
    private BigDecimal colG01039;

    /** 受损护坡、驳岸、挡墙数量 (处) */
    private BigDecimal colG01040;

    /** 受损护坡、驳岸、挡墙经济损失 (万元) */
    private BigDecimal colG01041;

    /** 受损供电线路经济损失 (万元) */
    private BigDecimal colG01042;

    /** 受损通信线路经济损失 (万元) */
    private BigDecimal colG01043;

    /** 高速铁路经济损失 (万元) */
    private BigDecimal colG01044;

    /** 受损路基 (立方米/千米) */
    private BigDecimal colG01045;

    /** 受损路基经济损失 (万元) */
    private BigDecimal colG01046;

    /** 受损桥梁 (延米/座) */
    private BigDecimal colG01047;

    /** 受损桥梁经济损失 (万元) */
    private BigDecimal colG01048;

    /** 受损涵洞 (延米/座) */
    private BigDecimal colG01049;

    /** 受损涵洞经济损失 (万元) */
    private BigDecimal colG01050;

    /** 受损隧道 (延米/道) */
    private BigDecimal colG01051;

    /** 受损隧道经济损失 (万元) */
    private BigDecimal colG01052;

    /** 受损护坡、驳岸、挡墙数量 (处) */
    private BigDecimal colG01053;

    /** 受损护坡、驳岸、挡墙经济损失 (万元) */
    private BigDecimal colG01054;

    /** 受损供电线路经济损失 (万元) */
    private BigDecimal colG01055;

    /** 受损通信线路经济损失 (万元) */
    private BigDecimal colG01056;

    /** 普通铁路经济损失 (万元) */
    private BigDecimal colG01057;

    /** 受损客运站数量 (个) */
    private BigDecimal colG01058;

    /** 受损客运站经济损失 (万元) */
    private BigDecimal colG01059;

    /** 受损货运站数量 (个) */
    private BigDecimal colG01060;

    /** 受损货运站经济损失 (万元) */
    private BigDecimal colG01061;

    /** 客（货）运站经济损失 (万元) */
    private BigDecimal colG01062;

    /** 受损高速列车车厢数量 (节) */
    private BigDecimal colG01063;

    /** 受损高速列车经济损失 (万元) */
    private BigDecimal colG01064;

    /** 受损普通客车车厢数量 (节) */
    private BigDecimal colG01065;

    /** 受损普通客车经济损失 (万元) */
    private BigDecimal colG01066;

    /** 受损普通货车车厢数量 (节) */
    private BigDecimal colG01067;

    /** 受损普通货车经济损失 (万元) */
    private BigDecimal colG01068;

    /** 运输工具经济损失 (万元) */
    private BigDecimal colG01069;

    /** （五）铁路经济损失小计 (万元) */
    private BigDecimal colG01070;

    /** 受损航道长度 (千米) */
    private BigDecimal colG01071;

    /** 受损航道经济损失 (万元) */
    private BigDecimal colG01072;

    /** 受损船闸数量 (个) */
    private BigDecimal colG01073;

    /** 受损船闸经济损失 (万元) */
    private BigDecimal colG01074;

    /** 受损码头泊位数量 (个) */
    private BigDecimal colG01075;

    /** 受损码头泊位经济损失 (万元) */
    private BigDecimal colG01076;

    /** 水运经济损失小计 (万元) */
    private BigDecimal colG01077;

    /** 受损机场数量 (个) */
    private BigDecimal colG01078;

    /** 受损机场经济损失 (万元) */
    private BigDecimal colG01079;

    /** 受损飞机数量 (架) */
    private BigDecimal colG01080;

    /** 受损飞机经济损失 (万元) */
    private BigDecimal colG01081;

    /** 航空经济损失小计 (万元) */
    private BigDecimal colG01082;

    /** 受损城市轨道交通车辆基地数量 (个) */
    private BigDecimal colG01083;

    /** 受损城市轨道交通车辆基地经济损失 (万元) */
    private BigDecimal colG01084;

    /** 受损城市轨道交通控制中心数量 (个) */
    private BigDecimal colG01085;

    /** 受损城市轨道交通控制中心经济损失 (万元) */
    private BigDecimal colG01086;

    /** 受损城市轨道交通车站数量 (个) */
    private BigDecimal colG01087;

    /** 受损城市轨道交通车站经济损失 (万元) */
    private BigDecimal colG01088;

    /** 受损城市轨道交通线路长度 (千米) */
    private BigDecimal colG01089;

    /** 受损城市轨道交通线路经济损失 (万元) */
    private BigDecimal colG01090;

    /** 受损交通枢纽数量 (个) */
    private BigDecimal colG01091;

    /** 受损交通枢纽经济损失 (万元) */
    private BigDecimal colG01092;

    /** 受损城市公共汽车场站数量 (个) */
    private BigDecimal colG01093;

    /** 受损城市公共汽车场站经济损失 (万元) */
    private BigDecimal colG01094;

    /** 受损公共汽车数量 (个) */
    private BigDecimal colG01095;

    /** 受损公共汽车经济损失 (万元) */
    private BigDecimal colG01096;

    /** 城市交通运输基础设施经济损失小计 (万元) */
    private BigDecimal colG01097;

    /** 倒损相关用房面积 (平方米) */
    private BigDecimal colG01098;

    /** 倒损相关用房经济损失 (万元) */
    private BigDecimal colG01099;

    /** 七、基础设施（交通运输）经济损失合计 (万元) */
    private BigDecimal colG01100;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
