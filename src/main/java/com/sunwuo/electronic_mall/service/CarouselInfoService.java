package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.CarouselInfo;
import com.sunwuo.electronic_mall.vo.PageData;

public interface CarouselInfoService {

    int addCarouselInfo(CarouselInfo carouselInfo);

    int updateCarouselInfo(CarouselInfo carouselInfo);

    int deleteByIds(int[] carouselIds);

    PageData getCarouselInfos(Integer commodityId, Integer storeId, Integer carouselType, Integer pageIndex, Integer pageSize);

    CarouselInfo getCarouselInfo(Integer carouselId);

    int topCommodityCarousel(Integer carouselId, Integer sortNumber);

    int sortCommodityCarousel(Integer carouselId, Integer sortNumber);

}
