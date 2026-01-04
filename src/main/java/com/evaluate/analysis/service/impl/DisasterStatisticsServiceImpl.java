package com.evaluate.analysis.service.impl;

import com.evaluate.analysis.entity.DisasterStatistics;
import com.evaluate.analysis.mapper.DisasterStatisticsMapper;
import com.evaluate.analysis.service.DisasterStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DisasterStatisticsServiceImpl extends ServiceImpl<DisasterStatisticsMapper, DisasterStatistics> implements DisasterStatisticsService {

    @Override
    public List<Map<String, Object>> statisticsByYear(Integer year, String disasterType, String region) {
        return baseMapper.statisticsByYear(year, disasterType, region);
    }

    @Override
    public List<Map<String, Object>> statisticsByDisasterType(Integer year, String disasterType, String region) {
        return baseMapper.statisticsByDisasterType(year, disasterType, region);
    }

    @Override
    public List<Map<String, Object>> statisticsByRegion(Integer year, String disasterType, String region) {
        return baseMapper.statisticsByRegion(year, disasterType, region);
    }

    @Override
    public List<Map<String, Object>> multiDimensionStatistics(Integer year, String disasterType, String region) {
        return baseMapper.multiDimensionStatistics(year, disasterType, region);
    }

    @Override
    public List<Integer> getAllYears() {
        return baseMapper.getAllYears();
    }

    @Override
    public List<String> getAllDisasterTypes() {
        return baseMapper.getAllDisasterTypes();
    }

    @Override
    public List<String> getAllRegions() {
        return baseMapper.getAllRegions();
    }

    @Override
    public List<Map<String, Object>> getRegionTree() {
        // 获取所有区域数据
        List<Map<String, Object>> regionData = baseMapper.getRegionHierarchy();

        // 构建省-市-县三级树形结构
        Map<String, Map<String, Set<String>>> treeMap = new TreeMap<>();

        for (Map<String, Object> row : regionData) {
            String region = (String) row.get("region");
            String parentRegion = (String) row.get("parent_region");

            if (region == null || region.trim().isEmpty()) continue;

            // 去除区域名称前导空格
            region = region.trim();

            // 解析 parentRegion（格式：四川省-成都市）
            String province = "";
            String city = "";

            if (parentRegion != null && !parentRegion.trim().isEmpty()) {
                String[] parts = parentRegion.split("-");
                if (parts.length >= 1) province = parts[0];
                if (parts.length >= 2) city = parts[1];
            }

            // 添加到树形结构
            treeMap.putIfAbsent(province.isEmpty() ? "未知省份" : province, new TreeMap<>());
            Map<String, Set<String>> cityMap = treeMap.get(province.isEmpty() ? "未知省份" : province);

            String cityKey = city.isEmpty() ? "直辖区县" : city;
            cityMap.putIfAbsent(cityKey, new TreeSet<>());
            cityMap.get(cityKey).add(region);
        }

        // 转换为前端需要的树形结构
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map.Entry<String, Map<String, Set<String>>> provinceEntry : treeMap.entrySet()) {
            Map<String, Object> provinceNode = new HashMap<>();
            provinceNode.put("value", provinceEntry.getKey());
            provinceNode.put("label", provinceEntry.getKey());
            provinceNode.put("level", "province");

            List<Map<String, Object>> children = new ArrayList<>();

            for (Map.Entry<String, Set<String>> cityEntry : provinceEntry.getValue().entrySet()) {
                Map<String, Object> cityNode = new HashMap<>();
                cityNode.put("value", cityEntry.getKey());
                cityNode.put("label", cityEntry.getKey());
                cityNode.put("level", "city");

                List<Map<String, Object>> counties = new ArrayList<>();
                for (String county : cityEntry.getValue()) {
                    Map<String, Object> countyNode = new HashMap<>();
                    countyNode.put("value", county);
                    countyNode.put("label", county);
                    countyNode.put("level", "county");
                    counties.add(countyNode);
                }

                cityNode.put("children", counties);
                children.add(cityNode);
            }

            provinceNode.put("children", children);
            result.add(provinceNode);
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> getDisasterTypeTree() {
        // 获取所有灾种数据
        List<String> disasterTypes = baseMapper.getDisasterTypeHierarchy();

        // 构建灾种树形结构（父类-子类）
        Map<String, Set<String>> treeMap = new TreeMap<>();

        for (String disasterType : disasterTypes) {
            if (disasterType == null || disasterType.trim().isEmpty()) continue;

            // 解析灾种（格式：地质灾害-泥石流、风雹灾害-冰雹）
            String parent = "";
            String child = "";

            if (disasterType.contains("-")) {
                String[] parts = disasterType.split("-");
                if (parts.length >= 1) parent = parts[0];
                if (parts.length >= 2) child = parts[1];
            } else {
                parent = disasterType;
                child = "";
            }

            // 添加到树形结构
            treeMap.putIfAbsent(parent, new TreeSet<>());
            if (!child.isEmpty()) {
                treeMap.get(parent).add(child);
            }
        }

        // 转换为前端需要的树形结构
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : treeMap.entrySet()) {
            Map<String, Object> parentNode = new HashMap<>();
            parentNode.put("value", entry.getKey());
            parentNode.put("label", entry.getKey());
            parentNode.put("level", "parent");

            List<Map<String, Object>> children = new ArrayList<>();
            for (String child : entry.getValue()) {
                Map<String, Object> childNode = new HashMap<>();
                childNode.put("value", entry.getKey() + "-" + child);
                childNode.put("label", child);
                childNode.put("level", "child");
                children.add(childNode);
            }

            // 如果没有子节点，使用父节点本身作为值
            if (children.isEmpty()) {
                parentNode.put("value", entry.getKey());
            } else {
                parentNode.put("children", children);
            }

            result.add(parentNode);
        }

        return result;
    }

    @Override
    public Map<String, Object> getOverviewStats(Integer year, String disasterType, String region) {
        return baseMapper.getOverviewStats(year, disasterType, region);
    }
}
