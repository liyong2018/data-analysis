package com.evaluate.analysis.controller;

import com.evaluate.analysis.entity.*;
import com.evaluate.analysis.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/tableG03")
public class TableG03Controller {

    @Autowired
    private TableG03Service service;

    @PostMapping
    public boolean save(@RequestBody TableG03 entity) {
        return service.saveOrUpdate(entity);
    }

    @GetMapping
    public List<TableG03> list() {
        return service.list();
    }

    @GetMapping("/latest")
    public TableG03 latest(@RequestParam String regionCode) {
        if (regionCode == null || regionCode.isEmpty()) return null;
        return service.getOne(new QueryWrapper<TableG03>()
                .eq("region_code", regionCode)
                .orderByDesc("report_date")
                .last("LIMIT 1"));
    }
}
