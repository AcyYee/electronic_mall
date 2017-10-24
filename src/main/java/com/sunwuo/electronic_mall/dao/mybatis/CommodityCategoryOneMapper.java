package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.CommodityCategoryOne;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityCategoryOneMapper {

    int deleteByPrimaryKey(Integer categoryOneId);

    int insert(CommodityCategoryOne record);

    int insertSelective(CommodityCategoryOne record);

    CommodityCategoryOne selectByPrimaryKey(Integer categoryOneId);

    int updateByPrimaryKeySelective(CommodityCategoryOne record);

    int updateByPrimaryKey(CommodityCategoryOne record);

    int findCategoryOneCount(Map<String, Object> map);

    List<CommodityCategoryOne> findCategoryOnes(Map<String, Object> map);

    int deleteByIds(@Param("categoryOneIds") int[] categoryOneIds);

    int updateSortNumber(@Param("categoryOneId") Integer categoryOneId, @Param("sortNumber") Integer sortNumber);

    void updateSortNumbers(Integer sortNumber);

}