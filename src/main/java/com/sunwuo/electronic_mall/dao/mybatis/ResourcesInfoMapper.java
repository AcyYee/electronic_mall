package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.ResourcesInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourcesInfoMapper {
    int deleteByPrimaryKey(Integer resourcesId);

    int insert(ResourcesInfo record);

    int insertSelective(ResourcesInfo record);

    ResourcesInfo selectByPrimaryKey(Integer resourcesId);

    int updateByPrimaryKeySelective(ResourcesInfo record);

    int updateByPrimaryKey(ResourcesInfo record);

    int updateSortNumber(@Param("resourcesId") Integer resourcesId, @Param("sortNumber") Integer sortNumber);

    int deleteByIds(@Param("resourcesIds") Integer[] resourcesIds);

    List<ResourcesInfo> findResourceses(Map<String, Object> map);

    int findResourcesCount(Map<String, Object> map);

    void updateSortNumbers(Integer sortNumber);

}