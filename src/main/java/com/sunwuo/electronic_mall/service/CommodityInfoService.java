package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.CommodityInfo;
import com.sunwuo.electronic_mall.vo.PageData;

public interface CommodityInfoService {

    int addCommodityInfo(CommodityInfo commodityInfo);

    int updateCommodityInfo(CommodityInfo commodityInfo);

    int deletesCommodityInfo(int[] commodityIds);

    CommodityInfo findById(Integer commodityId);

    PageData findByIds(Integer pageIndex, Integer pageSize, Integer commodityType, Integer storeId, Integer categoryTwoId, String searchString, Integer isSales);

    int topCommodityInfo(Integer commodityId, Integer sortNumber);

    int sortCommodityInfo(Integer commodityId, Integer sortNumber);

    int discounts(int[] commodityIds, Integer isDiscount);

    int sales(int[] commodityIds, Integer isSales);

}
