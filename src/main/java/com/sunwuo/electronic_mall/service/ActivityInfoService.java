package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.ActivityInfo;
import com.sunwuo.electronic_mall.vo.PageData;

public interface ActivityInfoService {

    int addActivity(ActivityInfo activityInfo);

    int updateActivity(ActivityInfo activityInfo);

    ActivityInfo findActivity(Integer activityId);

    int deleteActivities(Integer[] activityIds);

    PageData findActivities(Integer pageIndex, Integer pageSize);

    int isActivities(Integer[] activityIds);

}
