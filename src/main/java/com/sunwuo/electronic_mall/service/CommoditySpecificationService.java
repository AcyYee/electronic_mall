package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.CommoditySpecification;
import com.sunwuo.electronic_mall.vo.PageData;

public interface CommoditySpecificationService {

    int addSpecification(CommoditySpecification commoditySpecification);

    int updateSpecification(CommoditySpecification commoditySpecification);

    PageData getSpecifications(Integer commodityId, String searchString, Integer pageIndex, Integer pageSize);

    CommoditySpecification getSpecification(Integer specificationId);

    int deleteSpecifications(int[] specificationIds);

    int topCommoditySpecification(Integer specificationId, Integer sortNumber);

    int sortCommoditySpecification(Integer specificationId, Integer sortNumber);
}
