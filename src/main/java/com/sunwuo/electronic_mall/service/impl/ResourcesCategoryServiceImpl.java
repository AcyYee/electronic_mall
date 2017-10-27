package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.ResourcesCategoryMapper;
import com.sunwuo.electronic_mall.entity.ResourcesCategory;
import com.sunwuo.electronic_mall.service.ResourcesCategoryService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author acy 屋大维
 */
@Service
public class ResourcesCategoryServiceImpl implements ResourcesCategoryService {

    private final ResourcesCategoryMapper resourcesCategoryDao;

    @Autowired
    public ResourcesCategoryServiceImpl(ResourcesCategoryMapper resourcesCategoryDao) {
        this.resourcesCategoryDao = resourcesCategoryDao;
    }

    @Override
    public int addResourcesCategory(ResourcesCategory resourcesCategory) {
        if (resourcesCategory == null || resourcesCategory.notEmpty()) {
            return -1;
        }
        resourcesCategory.setCreateTime(TimeUtil.getDateTime(1));
        if (resourcesCategoryDao.insertSelective(resourcesCategory)>0){
            return resourcesCategoryDao.updateSortNumber(resourcesCategory.getCategoryId(),resourcesCategory.getCategoryId());
        }else {
            return 0;
        }
    }

    @Override
    public int updateResourcesCategory(ResourcesCategory resourcesCategory) {
        if (resourcesCategory == null || resourcesCategory.isEmpty()) {
            return -1;
        }
        return resourcesCategoryDao.updateByPrimaryKeySelective(resourcesCategory);
    }

    @Override
    public int deleteByIds(Integer[] categoryIds) {
        if (categoryIds == null || categoryIds.length<1) {
            return -1;
        }
        return resourcesCategoryDao.deleteByIds(categoryIds);
    }

    @Override
    public ResourcesCategory getResourcesCategory(Integer categoryId) {
        if (categoryId == null || categoryId < 0) {
            return null;
        }
        return resourcesCategoryDao.selectByPrimaryKey(categoryId);
    }

    @Override
    public PageData getResourcesCategories(Integer categoryId, Integer pageIndex, Integer pageSize) {
        PageData pageData = new PageData();
        Map<String,Object> map = new HashMap<>();
        if (pageIndex != null) {
            PageModel pageModel = new PageModel();
            pageModel.setRecordCount(resourcesCategoryDao.findCategoryCount());
            pageModel.setPageSize(pageSize);
            pageModel.setPageIndex(pageIndex);
            map.put("pageModel", pageModel);
            pageData.setModelData(resourcesCategoryDao.findCategories(map));
            pageData.setPageModel(pageModel);
            return pageData;
        }
        pageData.setModelData(resourcesCategoryDao.findCategories(map));
        return pageData;
    }

    @Override
    public int topResourcesCategory(Integer categoryId, Integer sortNumber) {
        if (categoryId == null || sortNumber == null) {
            return -1;
        }
        resourcesCategoryDao.updateSortNumbers(sortNumber);
        return resourcesCategoryDao.updateSortNumber(categoryId,1);
    }

    @Override
    public int sortResourcesCategory(Integer categoryId, Integer sortNumber) {
        if (categoryId == null || sortNumber == null) {
            return -1;
        }
        return resourcesCategoryDao.updateSortNumber(categoryId,sortNumber);
    }
}
