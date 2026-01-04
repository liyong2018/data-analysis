package com.evaluate.analysis.mapper;

import com.evaluate.analysis.entity.DisasterStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DisasterStatisticsMapper extends BaseMapper<DisasterStatistics> {

    /**
     * 按年份统计（带筛选条件和层级区域筛选）
     * 支持省级筛选（统计各市数据）和市级筛选（统计各县数据）
     */
    @Select("<script>" +
            "SELECT disaster_year as year, " +
            "SUM(affected_population) as totalAffected, " +
            "SUM(deaths) as totalDeaths, " +
            "SUM(direct_economic_loss) as totalLoss " +
            "FROM disaster_statistics " +
            "WHERE 1=1 " +
            "<if test='year != null'>AND disaster_year = #{year}</if> " +
            "<if test='disasterType != null and disasterType != \"\"'>AND disaster_type = #{disasterType}</if> " +
            "<if test='region != null and region != \"\"'>" +
            "  AND (" +
            "    region = #{region} " +
            "    OR parent_region = CONCAT(#{region}, '-', region) " +
            "    OR parent_region LIKE CONCAT(#{region}, '-%') " +
            "    OR parent_region LIKE CONCAT('%-', #{region})" +
            "  )" +
            "</if> " +
            "GROUP BY disaster_year " +
            "ORDER BY disaster_year DESC" +
            "</script>")
    List<Map<String, Object>> statisticsByYear(@Param("year") Integer year,
                                                @Param("disasterType") String disasterType,
                                                @Param("region") String region);

    /**
     * 按灾种统计（带筛选条件和层级区域筛选）
     */
    @Select("<script>" +
            "SELECT disaster_type as disasterType, " +
            "COUNT(*) as count, " +
            "SUM(affected_population) as totalAffected, " +
            "SUM(deaths) as totalDeaths, " +
            "SUM(direct_economic_loss) as totalLoss " +
            "FROM disaster_statistics " +
            "WHERE 1=1 " +
            "<if test='year != null'>AND disaster_year = #{year}</if> " +
            "<if test='disasterType != null and disasterType != \"\"'>AND disaster_type = #{disasterType}</if> " +
            "<if test='region != null and region != \"\"'>" +
            "  AND (" +
            "    region = #{region} " +
            "    OR parent_region = CONCAT(#{region}, '-', region) " +
            "    OR parent_region LIKE CONCAT(#{region}, '-%') " +
            "    OR parent_region LIKE CONCAT('%-', #{region})" +
            "  )" +
            "</if> " +
            "GROUP BY disaster_type " +
            "ORDER BY totalLoss DESC" +
            "</script>")
    List<Map<String, Object>> statisticsByDisasterType(@Param("year") Integer year,
                                                         @Param("disasterType") String disasterType,
                                                         @Param("region") String region);

    /**
     * 按区域统计（带筛选条件和层级区域筛选）
     */
    @Select("<script>" +
            "SELECT region, " +
            "COUNT(*) as count, " +
            "SUM(affected_population) as totalAffected, " +
            "SUM(deaths) as totalDeaths, " +
            "SUM(direct_economic_loss) as totalLoss " +
            "FROM disaster_statistics " +
            "WHERE 1=1 " +
            "<if test='year != null'>AND disaster_year = #{year}</if> " +
            "<if test='disasterType != null and disasterType != \"\"'>AND disaster_type = #{disasterType}</if> " +
            "<if test='region != null and region != \"\"'>" +
            "  AND (" +
            "    region = #{region} " +
            "    OR parent_region = CONCAT(#{region}, '-', region) " +
            "    OR parent_region LIKE CONCAT(#{region}, '-%') " +
            "    OR parent_region LIKE CONCAT('%-', #{region})" +
            "  )" +
            "</if> " +
            "GROUP BY region " +
            "ORDER BY totalLoss DESC" +
            "</script>")
    List<Map<String, Object>> statisticsByRegion(@Param("year") Integer year,
                                                   @Param("disasterType") String disasterType,
                                                   @Param("region") String region);

    /**
     * 多维度统计（带筛选条件和层级区域筛选）
     */
    @Select("<script>" +
            "SELECT " +
            "disaster_year, disaster_type, region, " +
            "SUM(affected_population) as totalAffected, " +
            "SUM(deaths) as totalDeaths, " +
            "SUM(direct_economic_loss) as totalLoss " +
            "FROM disaster_statistics " +
            "WHERE 1=1 " +
            "<if test='year != null'>AND disaster_year = #{year}</if> " +
            "<if test='disasterType != null and disasterType != \"\"'>AND disaster_type = #{disasterType}</if> " +
            "<if test='region != null and region != \"\"'>" +
            "  AND (" +
            "    region = #{region} " +
            "    OR parent_region = CONCAT(#{region}, '-', region) " +
            "    OR parent_region LIKE CONCAT(#{region}, '-%') " +
            "    OR parent_region LIKE CONCAT('%-', #{region})" +
            "  )" +
            "</if> " +
            "GROUP BY disaster_year, disaster_type, region " +
            "ORDER BY disaster_year DESC, totalLoss DESC" +
            "</script>")
    List<Map<String, Object>> multiDimensionStatistics(@Param("year") Integer year,
                                                        @Param("disasterType") String disasterType,
                                                        @Param("region") String region);

    /**
     * 获取所有年份列表
     */
    @Select("SELECT DISTINCT disaster_year FROM disaster_statistics ORDER BY disaster_year DESC")
    List<Integer> getAllYears();

    /**
     * 获取所有灾种列表
     */
    @Select("SELECT DISTINCT disaster_type FROM disaster_statistics ORDER BY disaster_type")
    List<String> getAllDisasterTypes();

    /**
     * 获取灾种树形结构
     */
    @Select("SELECT DISTINCT disaster_type FROM disaster_statistics WHERE disaster_type IS NOT NULL ORDER BY disaster_type")
    List<String> getDisasterTypeHierarchy();

    /**
     * 获取所有区域列表
     */
    @Select("SELECT DISTINCT region FROM disaster_statistics ORDER BY region")
    List<String> getAllRegions();

    /**
     * 获取区域层级数据（用于构建树形结构）
     */
    @Select("SELECT DISTINCT region, parent_region FROM disaster_statistics WHERE region IS NOT NULL ORDER BY region")
    List<Map<String, Object>> getRegionHierarchy();

    /**
     * 获取概览统计（带筛选条件和层级区域筛选）
     */
    @Select("<script>" +
            "SELECT " +
            "COUNT(*) as totalCount, " +
            "SUM(affected_population) as totalAffected, " +
            "SUM(deaths) as totalDeaths, " +
            "SUM(direct_economic_loss) as totalLoss " +
            "FROM disaster_statistics " +
            "WHERE 1=1 " +
            "<if test='year != null'>AND disaster_year = #{year}</if> " +
            "<if test='disasterType != null and disasterType != \"\"'>AND disaster_type = #{disasterType}</if> " +
            "<if test='region != null and region != \"\"'>" +
            "  AND (" +
            "    region = #{region} " +
            "    OR parent_region = CONCAT(#{region}, '-', region) " +
            "    OR parent_region LIKE CONCAT(#{region}, '-%') " +
            "    OR parent_region LIKE CONCAT('%-', #{region})" +
            "  )" +
            "</if>" +
            "</script>")
    Map<String, Object> getOverviewStats(@Param("year") Integer year,
                                          @Param("disasterType") String disasterType,
                                          @Param("region") String region);
}
