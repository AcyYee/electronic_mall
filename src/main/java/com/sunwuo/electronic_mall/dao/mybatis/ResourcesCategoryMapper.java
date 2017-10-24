package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.ResourcesCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourcesCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(ResourcesCategory record);

    int insertSelective(ResourcesCategory record);

    ResourcesCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(ResourcesCategory record);

    int updateByPrimaryKey(ResourcesCategory record);

    int updateSortNumber(@Param("categoryId") Integer categoryId, @Param("sortNumber") Integer sortNumber);

    List<ResourcesCategory> findCategories(Map<String, Object> map);

    int findCategoryCount();

    void updateSortNumbers(Integer sortNumber);

    int deleteByIds(@Param("categoryIds") Integer[] categoryIds);
}