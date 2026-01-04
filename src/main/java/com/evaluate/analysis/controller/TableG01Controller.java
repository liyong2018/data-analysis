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
@RequestMapping("/api/tableG01")
public class TableG01Controller {

    @Autowired
    private TableG01Service service;

    @PostMapping
    public boolean save(@RequestBody TableG01 entity) {
        return service.saveOrUpdate(entity);
    }

    @GetMapping
    public List<TableG01> list() {
        return service.list();
    }

    @GetMapping("/latest")
    public TableG01 latest(@RequestParam String regionCode) {
        if (regionCode == null || regionCode.isEmpty()) return null;
        return service.getOne(new QueryWrapper<TableG01>()
                .eq("region_code", regionCode)
                .orderByDesc("report_date")
                .last("LIMIT 1"));
    }
}
