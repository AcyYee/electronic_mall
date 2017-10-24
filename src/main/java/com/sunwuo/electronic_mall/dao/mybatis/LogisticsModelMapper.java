package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.LogisticsModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogisticsModelMapper {
    int deleteByPrimaryKey(Integer modelId);

    int insert(LogisticsModel record);

    int insertSelective(LogisticsModel record);

    LogisticsModel selectByPrimaryKey(Integer modelId);

    int updateByPrimaryKeySelective(LogisticsModel record);

    int updateByPrimaryKey(LogisticsModel record);

    int updateSortNumber(@Param("modelId") Integer modelId, @Param("sortNumber") Integer sortNumber);

    int findModelCount(Map<String, Object> map);

    List<LogisticsModel> findModels(Map<String, Object> map);

    int deleteByIds(@Param("modelIds") Integer[] modelIds);

    void updateSortNumbers(Integer sortNumber);
}