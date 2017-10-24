package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.CommodityCategoryOne;
import com.sunwuo.electronic_mall.entity.CommodityCategoryTwo;
import com.sunwuo.electronic_mall.vo.PageData;

public interface CommodityCategoryService {

    PageData getCategoryOnes(Integer pageIndex, Integer pageSize, Integer storeId, String searchString);

    int insertCategoryOne(CommodityCategoryOne categoryOne);

    int deleteCategoryOnes(int[] categoryIds);

    int updateCategoryOne(CommodityCategoryOne categoryOne);

    PageData getCategoryTwos(Integer pageIndex, Integer pageSize, String searchString, Integer categoryOneId);

    int insertCategoryTwo(CommodityCategoryTwo categoryTwo);

    int deleteCategoryTwos(int[] categoryTwoIds);

    int updateCategoryTwo(CommodityCategoryTwo categoryTwo);

    int topTwo(Integer categoryTwoId, Integer sortNumber);

    int sortTwo(Integer categoryTwoId, Integer sortNumber);

    int topOne(Integer categoryTwoId, Integer sortNumber);

    int sortOne(Integer categoryOneId, Integer sortNumber);
}
