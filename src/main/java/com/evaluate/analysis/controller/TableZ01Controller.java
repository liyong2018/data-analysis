package com.evaluate.analysis.controller;

import com.evaluate.analysis.entity.*;
import com.evaluate.analysis.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/tableZ01")
public class TableZ01Controller {

    @Autowired
    private TableZ01Service service;

    @Autowired
    private TableB01Service serviceB01;
    @Autowired
    private TableB02Service serviceB02;
    @Autowired
    private TableD01Service serviceD01;
    @Autowired
    private TableC01Service serviceC01;
    @Autowired
    private TableF01Service serviceF01;
    @Autowired
    private TableH10Service serviceH10;
    @Autowired
    private TableE01Service serviceE01;
    @Autowired
    private TableG02Service serviceG02;
    @Autowired
    private TableH01Service serviceH01;
    @Autowired
    private TableG01Service serviceG01;
    @Autowired
    private TableG04Service serviceG04;
    @Autowired
    private TableH03Service serviceH03;
    @Autowired
    private TableG03Service serviceG03;
    @Autowired
    private TableH02Service serviceH02;
    @Autowired
    private TableG06Service serviceG06;
    @Autowired
    private TableH05Service serviceH05;
    @Autowired
    private TableG05Service serviceG05;
    @Autowired
    private TableH04Service serviceH04;
    @Autowired
    private TableH07Service serviceH07;
    @Autowired
    private TableG07Service serviceG07;
    @Autowired
    private TableH06Service serviceH06;
    @Autowired
    private TableH09Service serviceH09;
    @Autowired
    private TableH08Service serviceH08;

    @PostMapping
    public boolean save(@RequestBody TableZ01 entity) {
        return service.saveOrUpdate(entity);
    }

    @GetMapping
    public List<TableZ01> list() {
        return service.list();
    }

    @GetMapping("/latest")
    public TableZ01 latest(@RequestParam String regionCode) {
        if (regionCode == null || regionCode.isEmpty()) return null;
        return service.getOne(new QueryWrapper<TableZ01>()
                .eq("region_code", regionCode)
                .orderByDesc("report_date")
                .last("LIMIT 1"));
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary(@RequestParam String regionCode) {
        Map<String, Object> result = new HashMap<>();
        if (regionCode == null || regionCode.isEmpty()) return result;

        TableB01 entityB01 = serviceB01.getOne(new QueryWrapper<TableB01>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityB01 != null) {
            result.put("colZ01001", entityB01.getColB01055());
        }
        TableB02 entityB02 = serviceB02.getOne(new QueryWrapper<TableB02>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityB02 != null) {
            result.put("colZ01002", entityB02.getColB02073());
        }
        TableD01 entityD01 = serviceD01.getOne(new QueryWrapper<TableD01>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityD01 != null) {
            result.put("colZ01004", entityD01.getColD01042());
        }
        TableC01 entityC01 = serviceC01.getOne(new QueryWrapper<TableC01>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityC01 != null) {
            result.put("colZ01003", entityC01.getColC01011());
        }
        TableF01 entityF01 = serviceF01.getOne(new QueryWrapper<TableF01>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityF01 != null) {
            result.put("colZ01006", entityF01.getColF01026());
        }
        TableH10 entityH10 = serviceH10.getOne(new QueryWrapper<TableH10>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH10 != null) {
            result.put("colZ01023", entityH10.getColH10010());
        }
        TableE01 entityE01 = serviceE01.getOne(new QueryWrapper<TableE01>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityE01 != null) {
            result.put("colZ01005", entityE01.getColE01015());
        }
        
        // Recalculate G02 (Communication)
        TableG02 entityG02 = serviceG02.getOne(new QueryWrapper<TableG02>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityG02 != null) {
            BigDecimal sum = safeAdd(entityG02.getColG02003(), entityG02.getColG02008(), entityG02.getColG02011());
            result.put("colZ01008", sum.compareTo(BigDecimal.ZERO) > 0 ? sum : entityG02.getColG02012());
        }

        TableH01 entityH01 = serviceH01.getOne(new QueryWrapper<TableH01>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH01 != null) {
            result.put("colZ01014", entityH01.getColH01015());
        }

        // Recalculate G01 (Transportation)
        TableG01 entityG01 = serviceG01.getOne(new QueryWrapper<TableG01>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityG01 != null) {
            BigDecimal sum = safeAdd(entityG01.getColG01031(), entityG01.getColG01070(), entityG01.getColG01077(), entityG01.getColG01082(), entityG01.getColG01097());
            result.put("colZ01007", sum.compareTo(BigDecimal.ZERO) > 0 ? sum : entityG01.getColG01100());
        }

        TableG04 entityG04 = serviceG04.getOne(new QueryWrapper<TableG04>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityG04 != null) {
            result.put("colZ01010", entityG04.getColG04024());
        }
        TableH03 entityH03 = serviceH03.getOne(new QueryWrapper<TableH03>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH03 != null) {
            result.put("colZ01016", entityH03.getColH03014());
        }

        // Recalculate G03 (Energy)
        TableG03 entityG03 = serviceG03.getOne(new QueryWrapper<TableG03>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityG03 != null) {
            BigDecimal sum = safeAdd(entityG03.getColG03020(), entityG03.getColG03038());
            result.put("colZ01009", sum.compareTo(BigDecimal.ZERO) > 0 ? sum : entityG03.getColG03041());
        }

        TableH02 entityH02 = serviceH02.getOne(new QueryWrapper<TableH02>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH02 != null) {
            result.put("colZ01015", entityH02.getColH02034());
        }
        TableG06 entityG06 = serviceG06.getOne(new QueryWrapper<TableG06>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityG06 != null) {
            result.put("colZ01012", entityG06.getColG06011());
        }
        TableH05 entityH05 = serviceH05.getOne(new QueryWrapper<TableH05>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH05 != null) {
            result.put("colZ01018", entityH05.getColH05014());
        }
        TableG05 entityG05 = serviceG05.getOne(new QueryWrapper<TableG05>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityG05 != null) {
            result.put("colZ01011", entityG05.getColG05042());
        }
        TableH04 entityH04 = serviceH04.getOne(new QueryWrapper<TableH04>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH04 != null) {
            result.put("colZ01017", entityH04.getColH04016());
        }
        TableH07 entityH07 = serviceH07.getOne(new QueryWrapper<TableH07>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH07 != null) {
            result.put("colZ01020", entityH07.getColH07022());
        }
        TableG07 entityG07 = serviceG07.getOne(new QueryWrapper<TableG07>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityG07 != null) {
            result.put("colZ01013", entityG07.getColG07017());
        }
        TableH06 entityH06 = serviceH06.getOne(new QueryWrapper<TableH06>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH06 != null) {
            result.put("colZ01019", entityH06.getColH06008());
        }
        TableH09 entityH09 = serviceH09.getOne(new QueryWrapper<TableH09>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH09 != null) {
            result.put("colZ01022", entityH09.getColH09025());
        }
        TableH08 entityH08 = serviceH08.getOne(new QueryWrapper<TableH08>().eq("region_code", regionCode).orderByDesc("report_date").last("LIMIT 1"));
        if (entityH08 != null) {
            result.put("colZ01021", entityH08.getColH08037());
        }
        return result;
    }

    private BigDecimal safeAdd(BigDecimal... values) {
        BigDecimal sum = BigDecimal.ZERO;
        if (values != null) {
            for (BigDecimal val : values) {
                if (val != null) {
                    sum = sum.add(val);
                }
            }
        }
        return sum;
    }
}
