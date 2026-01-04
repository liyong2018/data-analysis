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
@RequestMapping("/api/tableH05")
public class TableH05Controller {

    @Autowired
    private TableH05Service service;

    @PostMapping
    public boolean save(@RequestBody TableH05 entity) {
        return service.saveOrUpdate(entity);
    }

    @GetMapping
    public List<TableH05> list() {
        return service.list();
    }

    @GetMapping("/latest")
    public TableH05 latest(@RequestParam String regionCode) {
        if (regionCode == null || regionCode.isEmpty()) return null;
        return service.getOne(new QueryWrapper<TableH05>()
                .eq("region_code", regionCode)
                .orderByDesc("report_date")
                .last("LIMIT 1"));
    }
}
