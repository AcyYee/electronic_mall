package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.CommodityInfoMapper;
import com.sunwuo.electronic_mall.entity.CommodityInfo;
import com.sunwuo.electronic_mall.service.CommodityInfoService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("commodityInfoService")
public class CommodityInfoServiceImpl implements CommodityInfoService{

    private final CommodityInfoMapper commodityInfoDao;

    @Autowired
    public CommodityInfoServiceImpl(CommodityInfoMapper commodityInfoDao) {
        this.commodityInfoDao = commodityInfoDao;
    }

    public int addCommodityInfo(CommodityInfo commodityInfo) {
        System.out.println(commodityInfo);
        if (commodityInfo == null || commodityInfo.isEmpty())
            return -1;
        commodityInfo.setCreateTime(TimeUtil.getDateTime(1));
        commodityInfo.setCommodityType(1);
        if (commodityInfoDao.insertSelective(commodityInfo)>0){
            return commodityInfoDao.updateSortNumber(commodityInfo.getCommodityId(),commodityInfo.getCommodityId());
        }else
            return 0;
    }

    public int updateCommodityInfo(CommodityInfo commodityInfo) {
        if (commodityInfo == null || commodityInfo.getCommodityId() == null){
            return -1;
        }
        return commodityInfoDao.updateByPrimaryKeySelective(commodityInfo);
    }

    public int deletesCommodityInfo(int[] commodityIds) {
        if (commodityIds == null || commodityIds.length==0)
            return -1;
        else
            return commodityInfoDao.deleteByIds(commodityIds);
    }

    public CommodityInfo findById(Integer commodityId) {
        if (commodityId == null){
            return null;
        }else {
            return commodityInfoDao.selectByPrimaryKey(commodityId);
        }
    }

    public PageData findByIds(Integer pageIndex, Integer pageSize, Integer commodityType, Integer storeId, Integer categoryTwoId, String searchString, Integer isSales) {
        if (commodityType == null || storeId == null && categoryTwoId == null && searchString == null){
            return null;
        }
        PageData pageData = new PageData();
        Map<String,Object> map = new HashMap<>();
        map.put("commodityType",commodityType);
        if (searchString != null)
            map.put("searchString",searchString);
        if (storeId != null)
            map.put("storeId",storeId);
        if (categoryTwoId != null)
            map.put("categoryTwoId",categoryTwoId);
        if (isSales != null)
            map.put("isSales",isSales);
        if (pageIndex != null) {
            PageModel pageModel = new PageModel();
            pageModel.setPageSize(pageSize);
            pageModel.setPageIndex(pageIndex);
            int recordCount = commodityInfoDao.selectCommodityCount(map);
            pageModel.setRecordCount(recordCount);
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(commodityInfoDao.selectCommodities(map));
        }
        pageData.setModelData(commodityInfoDao.selectCommodities(map));
        return pageData;
    }

    @Override
    public int topCommodityInfo(Integer commodityId, Integer sortNumber) {
        if (commodityId == null||sortNumber == null){
            return  -1;
        }
        commodityInfoDao.updateSortNumbers(sortNumber);
        return commodityInfoDao.updateSortNumber(commodityId,1);
    }

    @Override
    public int sortCommodityInfo(Integer commodityId, Integer sortNumber) {
        if (commodityId == null||sortNumber == null){
            return  -1;
        }
        return commodityInfoDao.updateSortNumber(commodityId,sortNumber);
    }

    @Override
    public int discounts(int[] commodityIds, Integer isDiscount) {
        if (commodityIds == null ||commodityIds.length<1 || isDiscount == null)
        {
            return -1;
        }
        return commodityInfoDao.discounts(commodityIds,isDiscount);
    }

    @Override
    public int sales(int[] commodityIds, Integer isSales) {
        if (commodityIds == null ||commodityIds.length<1 || isSales == null)
        {
            return -1;
        }
        return commodityInfoDao.sales(commodityIds, isSales);
    }

}
