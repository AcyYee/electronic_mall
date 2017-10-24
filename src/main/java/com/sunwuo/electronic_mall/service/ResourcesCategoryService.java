package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.ResourcesCategory;
import com.sunwuo.electronic_mall.vo.PageData;

public interface ResourcesCategoryService {
    
    int addResourcesCategory(ResourcesCategory resourcesCategory);

    int updateResourcesCategory(ResourcesCategory resourcesCategory);

    int deleteByIds(Integer[] categoryIds);

    ResourcesCategory getResourcesCategory(Integer categoryId);

    PageData getResourcesCategories(Integer categoryId, Integer pageIndex, Integer pageSize);

    int topResourcesCategory(Integer categoryId, Integer sortNumber);

    int sortResourcesCategory(Integer categoryId, Integer sortNumber);

}
