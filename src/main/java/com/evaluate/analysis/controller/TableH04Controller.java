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
@RequestMapping("/api/tableH04")
public class TableH04Controller {

    @Autowired
    private TableH04Service service;

    @PostMapping
    public boolean save(@RequestBody TableH04 entity) {
        return service.saveOrUpdate(entity);
    }

    @GetMapping
    public List<TableH04> list() {
        return service.list();
    }

    @GetMapping("/latest")
    public TableH04 latest(@RequestParam String regionCode) {
        if (regionCode == null || regionCode.isEmpty()) return null;
        return service.getOne(new QueryWrapper<TableH04>()
                .eq("region_code", regionCode)
                .orderByDesc("report_date")
                .last("LIMIT 1"));
    }
}
