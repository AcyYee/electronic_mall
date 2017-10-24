package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.CarouselInfoMapper;
import com.sunwuo.electronic_mall.entity.CarouselInfo;
import com.sunwuo.electronic_mall.service.CarouselInfoService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarouselInfoServiceImpl implements CarouselInfoService{

    @Autowired
    private CarouselInfoMapper carouselInfoDao;

    @Override
    public int addCarouselInfo(CarouselInfo carouselInfo) {
        if(carouselInfo==null || carouselInfo.isEmpty())
            return -1;
        carouselInfo.setCreateTime(TimeUtil.getDateTime(1));
        if(carouselInfoDao.insertSelective(carouselInfo)>0){
            return carouselInfoDao.updateSortNumber(carouselInfo.getCarouselId(),carouselInfo.getCarouselId());
        }else
            return 0;
    }

    @Override
    public int updateCarouselInfo(CarouselInfo carouselInfo) {
        if (carouselInfo == null || carouselInfo.getCarouselId() == null){
            return -1;
        }
        return carouselInfoDao.updateByPrimaryKeySelective(carouselInfo);
    }

    @Override
    public int deleteByIds(int[] carouselIds) {

        if (carouselIds == null || carouselIds.length<1)
            return -1;
        return carouselInfoDao.deleteByIds(carouselIds);
    }

    @Override
    public PageData getCarouselInfos(Integer commodityId, Integer storeId, Integer carouselType,Integer pageIndex, Integer pageSize) {
        if (commodityId==null && storeId==null)
            return null;
        Map<String,Object> map = new HashMap<>();
        PageData pageData = new PageData();
        if (carouselType != null)
            map.put("carouselType",carouselType);
        if (commodityId != null)
            map.put("commodityId",commodityId);
        if (storeId != null)
            map.put("storeId",storeId);
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(carouselInfoDao.selectCarouselInfoCount(map));
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(carouselInfoDao.selectCarouselInfoes(map));
            return pageData;
        }
        pageData.setModelData(carouselInfoDao.selectCarouselInfoes(map));
        return pageData;
    }

    @Override
    public CarouselInfo getCarouselInfo(Integer carouselId) {
        if (carouselId == null || carouselId <= 0)
            return null;
        else {
            return carouselInfoDao.selectByPrimaryKey(carouselId);
        }
    }

    @Override
    public int topCommodityCarousel(Integer carouselId, Integer sortNumber) {
        if (carouselId == null || sortNumber == null)
            return -1;
        carouselInfoDao.updateSortNumbers(sortNumber);
        return carouselInfoDao.updateSortNumber(carouselId,sortNumber);
    }

    @Override
    public int sortCommodityCarousel(Integer carouselId, Integer sortNumber) {
        if (carouselId == null || sortNumber == null)
            return -1;
        return carouselInfoDao.updateSortNumber(carouselId,sortNumber);
    }

}
