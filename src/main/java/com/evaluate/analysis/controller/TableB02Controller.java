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
@RequestMapping("/api/tableB02")
public class TableB02Controller {

    @Autowired
    private TableB02Service service;

    @PostMapping
    public boolean save(@RequestBody TableB02 entity) {
        return service.saveOrUpdate(entity);
    }

    @GetMapping
    public List<TableB02> list() {
        return service.list();
    }

    @GetMapping("/latest")
    public TableB02 latest(@RequestParam String regionCode) {
        if (regionCode == null || regionCode.isEmpty()) return null;
        return service.getOne(new QueryWrapper<TableB02>()
                .eq("region_code", regionCode)
                .orderByDesc("report_date")
                .last("LIMIT 1"));
    }
}
