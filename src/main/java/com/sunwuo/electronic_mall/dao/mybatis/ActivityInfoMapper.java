package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.ActivityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActivityInfoMapper {

    int deleteByPrimaryKey(Integer activityId);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);

    int updateSortNumber(@Param("activityId") Integer activityId, @Param("sortNumber") Integer sortNumber);

    void updateSortNumbers(Integer sortNumber);

    int deleteByIds(@Param("activityIds") Integer[] activityIds);

    int updateIsActivities(@Param("activityIds") Integer[] activityIds);

    int findActivityCount();

    List<ActivityInfo> findActivities(Map<String, Object> map);

}
