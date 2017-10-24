package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.ResourcesInfo;
import com.sunwuo.electronic_mall.vo.PageData;

public interface ResourcesInfoService {

    int addResourcesInfo(ResourcesInfo resourcesInfo);

    int updateResourcesInfo(ResourcesInfo resourcesInfo);

    int deleteByIds(Integer[] resourcesIds);

    ResourcesInfo getResourcesInfo(Integer resourcesId);

    PageData getResourcesInfos(Integer categoryId, Integer pageIndex, Integer pageSize);

    int topResources(Integer resourcesId, Integer sortNumber);

    int sortResources(Integer resourcesId, Integer sortNumber);

}
