package com.evaluate.analysis.controller;

import com.evaluate.analysis.entity.DisasterStatistics;
import com.evaluate.analysis.service.DisasterStatisticsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/disaster-statistics")
public class DisasterStatisticsController {

    @Autowired
    private DisasterStatisticsService service;

    /**
     * 保存或更新
     */
    @PostMapping
    public boolean save(@RequestBody DisasterStatistics entity) {
        return service.saveOrUpdate(entity);
    }

    /**
     * 批量保存
     */
    @PostMapping("/batch")
    public boolean saveBatch(@RequestBody List<DisasterStatistics> entities) {
        return service.saveBatch(entities);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.removeById(id);
    }

    /**
     * 获取列表
     */
    @GetMapping
    public List<DisasterStatistics> list() {
        return service.list();
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Page<DisasterStatistics> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String disasterType,
            @RequestParam(required = false) Integer year) {
        Page<DisasterStatistics> page = new Page<>(current, size);
        QueryWrapper<DisasterStatistics> wrapper = new QueryWrapper<>();

        if (region != null && !region.isEmpty()) {
            wrapper.eq("region", region);
        }
        if (disasterType != null && !disasterType.isEmpty()) {
            wrapper.eq("disaster_type", disasterType);
        }
        if (year != null) {
            wrapper.eq("disaster_year", year);
        }

        wrapper.orderByDesc("disaster_time");
        return service.page(page, wrapper);
    }

    /**
     * 按年份统计（带筛选条件）
     */
    @GetMapping("/statistics/by-year")
    public List<Map<String, Object>> statisticsByYear(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String disasterType,
            @RequestParam(required = false) String region) {
        return service.statisticsByYear(year, disasterType, region);
    }

    /**
     * 按灾种统计（带筛选条件）
     */
    @GetMapping("/statistics/by-disaster-type")
    public List<Map<String, Object>> statisticsByDisasterType(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String disasterType,
            @RequestParam(required = false) String region) {
        return service.statisticsByDisasterType(year, disasterType, region);
    }

    /**
     * 按区域统计（带筛选条件）
     */
    @GetMapping("/statistics/by-region")
    public List<Map<String, Object>> statisticsByRegion(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String disasterType,
            @RequestParam(required = false) String region) {
        return service.statisticsByRegion(year, disasterType, region);
    }

    /**
     * 多维度统计
     */
    @GetMapping("/statistics/multi-dimension")
    public List<Map<String, Object>> multiDimensionStatistics(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String disasterType,
            @RequestParam(required = false) String region) {
        return service.multiDimensionStatistics(year, disasterType, region);
    }

    /**
     * 获取筛选选项
     */
    @GetMapping("/filter-options")
    public Map<String, Object> getFilterOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("years", service.getAllYears());
        options.put("disasterTypes", service.getAllDisasterTypes());
        options.put("disasterTypeTree", service.getDisasterTypeTree());
        options.put("regions", service.getAllRegions());
        options.put("regionTree", service.getRegionTree());
        return options;
    }

    /**
     * 获取区域树形结构
     */
    @GetMapping("/region-tree")
    public List<Map<String, Object>> getRegionTree() {
        return service.getRegionTree();
    }

    /**
     * 获取灾种树形结构
     */
    @GetMapping("/disaster-type-tree")
    public List<Map<String, Object>> getDisasterTypeTree() {
        return service.getDisasterTypeTree();
    }

    /**
     * 获取统计概览（带筛选条件）
     */
    @GetMapping("/overview")
    public Map<String, Object> getOverview(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String disasterType,
            @RequestParam(required = false) String region) {
        Map<String, Object> overview = new HashMap<>();

        // 获取概览统计（支持层级筛选）
        Map<String, Object> stats = service.getOverviewStats(year, disasterType, region);
        overview.putAll(stats);

        // 各年份对比（应用筛选条件）
        overview.put("yearlyStats", service.statisticsByYear(year, disasterType, region));

        // 各灾种对比（应用筛选条件）
        overview.put("disasterTypeStats", service.statisticsByDisasterType(year, disasterType, region));

        // 各区域对比（应用筛选条件）
        overview.put("regionStats", service.statisticsByRegion(year, disasterType, region));

        return overview;
    }
}
