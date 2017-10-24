package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.CommodityCategoryOneMapper;
import com.sunwuo.electronic_mall.dao.mybatis.CommodityCategoryTwoMapper;
import com.sunwuo.electronic_mall.entity.CommodityCategoryOne;
import com.sunwuo.electronic_mall.entity.CommodityCategoryTwo;
import com.sunwuo.electronic_mall.service.CommodityCategoryService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("commodityCategoryService")
public class CommodityCategoryServiceImpl implements CommodityCategoryService {

    @Autowired
    private CommodityCategoryOneMapper commodityCategoryOneDao;

    @Autowired
    private CommodityCategoryTwoMapper commodityCategoryTwoDao;

    @Override
    public PageData getCategoryOnes(Integer pageIndex, Integer pageSize, Integer storeId, String searchString) {
        PageData pageData = new PageData();
        Map<String,Object> map = new HashMap<>();
        map.put("storeId",storeId);
        if (searchString!= null){
            map.put("searchString",searchString);
        }
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(commodityCategoryOneDao.findCategoryOneCount(map));
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(commodityCategoryOneDao.findCategoryOnes(map));
            return pageData;
        }
        pageData.setModelData(commodityCategoryOneDao.findCategoryOnes(map));
        return pageData;
    }

    @Override
    public int insertCategoryOne(CommodityCategoryOne categoryOne) {
        if (categoryOne == null ||categoryOne.isEmpty()){
            return -1;
        }
        categoryOne.setCreateTime(TimeUtil.getDateTime(TimeUtil.TO_SEC));
        if (commodityCategoryOneDao.insert(categoryOne)>0){
            return commodityCategoryOneDao.updateSortNumber(categoryOne.getCategoryOneId(),categoryOne.getCategoryOneId());
        }else {
            return 0;
        }

    }

    @Override
    public int deleteCategoryOnes(int[] categoryOneIds) {
        if (categoryOneIds == null || categoryOneIds.length<1){
            return -1;
        }
        return commodityCategoryOneDao.deleteByIds(categoryOneIds);
    }

    @Override
    public int updateCategoryOne(CommodityCategoryOne categoryOne) {
        if (categoryOne == null||categoryOne.getCategoryOneId()==null){
            return -1;
        }
        return commodityCategoryOneDao.updateByPrimaryKeySelective(categoryOne);
    }

    @Override
    public PageData getCategoryTwos(Integer pageIndex, Integer pageSize, String searchString,Integer categoryOneId) {
        if (categoryOneId == null )
            return null;
        PageData pageData = new PageData();
        Map<String,Object> map = new HashMap<>();
        map.put("categoryOneId",categoryOneId);
        if (searchString!= null){
            map.put("searchString",searchString);
        }
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(commodityCategoryTwoDao.findCategoryTwoCount(map));
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(commodityCategoryTwoDao.findCategoryTwos(map));
            return pageData;
        }
        pageData.setModelData(commodityCategoryTwoDao.findCategoryTwos(map));
        return pageData;
    }

    @Override
    public int insertCategoryTwo(CommodityCategoryTwo categoryTwo) {
        if (categoryTwo == null ||categoryTwo.isEmpty()){
            return -1;
        }
        categoryTwo.setCreateTime(TimeUtil.getDateTime(TimeUtil.TO_SEC));
        if (commodityCategoryTwoDao.insertCategoryTwo(categoryTwo)>0){
            return commodityCategoryTwoDao.updateSortNumber(categoryTwo.getCategoryTwoId(),categoryTwo.getCategoryTwoId());
        }else{
            return 0;
        }
    }

    @Override
    public int deleteCategoryTwos(int[] categoryTwoIds) {
        if (categoryTwoIds == null){
            return -1;
        }
        return commodityCategoryTwoDao.deleteByIds(categoryTwoIds);
    }

    @Override
    public int updateCategoryTwo(CommodityCategoryTwo categoryTwo) {
        if (categoryTwo == null||categoryTwo.getCategoryTwoId()==null){
            return -1;
        }
        return commodityCategoryTwoDao.update(categoryTwo);
    }

    @Override
    public int topTwo(Integer categoryTwoId, Integer sortNumber) {
        if (categoryTwoId == null){
            return  -1;
        }
        commodityCategoryTwoDao.updateSortNumbers(sortNumber);
        return commodityCategoryTwoDao.updateSortNumber(categoryTwoId,1);
    }

    @Override
    public int sortTwo(Integer categoryTwoId, Integer sortNumber) {
        if(categoryTwoId ==null || sortNumber == null){
            return -1;
        }
        return commodityCategoryTwoDao.updateSortNumber(categoryTwoId,sortNumber);
    }

    @Override
    public int sortOne(Integer categoryOneId, Integer sortNumber) {
        if(categoryOneId ==null || sortNumber == null){
            return -1;
        }
        return commodityCategoryOneDao.updateSortNumber(categoryOneId,sortNumber);
    }

    @Override
    public int topOne(Integer categoryOneId, Integer sortNumber) {
        if (categoryOneId == null||sortNumber == null){
            return  -1;
        }
        commodityCategoryOneDao.updateSortNumbers(sortNumber);
        return commodityCategoryOneDao.updateSortNumber(categoryOneId,1);
    }
}
