package com.evaluate.analysis.service;

import com.evaluate.analysis.entity.DisasterStatistics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface DisasterStatisticsService extends IService<DisasterStatistics> {

    /**
     * 按年份统计（带筛选条件）
     */
    List<Map<String, Object>> statisticsByYear(Integer year, String disasterType, String region);

    /**
     * 按灾种统计（带筛选条件）
     */
    List<Map<String, Object>> statisticsByDisasterType(Integer year, String disasterType, String region);

    /**
     * 按区域统计（带筛选条件）
     */
    List<Map<String, Object>> statisticsByRegion(Integer year, String disasterType, String region);

    /**
     * 多维度统计
     */
    List<Map<String, Object>> multiDimensionStatistics(Integer year, String disasterType, String region);

    /**
     * 获取所有年份列表
     */
    List<Integer> getAllYears();

    /**
     * 获取所有灾种列表
     */
    List<String> getAllDisasterTypes();

    /**
     * 获取所有区域列表
     */
    List<String> getAllRegions();

    /**
     * 获取区域树形结构
     */
    List<Map<String, Object>> getRegionTree();

    /**
     * 获取灾种树形结构
     */
    List<Map<String, Object>> getDisasterTypeTree();

    /**
     * 获取概览统计（带筛选条件）
     */
    Map<String, Object> getOverviewStats(Integer year, String disasterType, String region);
}
