package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.CommoditySpecificationMapper;
import com.sunwuo.electronic_mall.entity.CommoditySpecification;
import com.sunwuo.electronic_mall.service.CommoditySpecificationService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("commoditySpecificationService")
public class CommoditySpecificationServiceImpl implements CommoditySpecificationService {

    @Autowired
    private CommoditySpecificationMapper commoditySpecificationDao;

    @Override
    public int addSpecification(CommoditySpecification commoditySpecification) {
        if (commoditySpecification == null || commoditySpecification.isEmpty()) {
            return -1;
        }
        commoditySpecification.setCreateTime(TimeUtil.getDateTime(1));
        if (commoditySpecificationDao.insertSelective(commoditySpecification)>0){
            return commoditySpecificationDao.updateSortNumber(commoditySpecification.getSpecificationId(),commoditySpecification.getSpecificationId());
        }else {
            return 0;
        }
    }

    @Override
    public int updateSpecification(CommoditySpecification commoditySpecification) {

        if (commoditySpecification == null ||commoditySpecification.getCommodityId()==null) {
            return -1;
        }
        return commoditySpecificationDao.updateByPrimaryKeySelective(commoditySpecification);
    }

    @Override
    public PageData getSpecifications(Integer commodityId, String searchString, Integer pageIndex, Integer pageSize) {
        PageData pageData = new PageData();
        if (commodityId == null) {
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("commodityId",commodityId);
        if(searchString != null) {
            map.put("searchString", searchString);
        }
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setRecordCount(commoditySpecificationDao.selectSpecificationCount(map));
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(commoditySpecificationDao.selectSpecifications(map));
            return pageData;
        }else {
            pageData.setModelData(commoditySpecificationDao.selectSpecifications(map));
            return pageData;
        }
    }

    @Override
    public CommoditySpecification getSpecification(Integer specificationId) {
        if (specificationId == null){
            return null;
        }
        return commoditySpecificationDao.selectByPrimaryKey(specificationId);
    }

    @Override
    public int deleteSpecifications(int[] specificationIds) {
        if (specificationIds == null || specificationIds.length <1) {
            return -1;
        }
        return commoditySpecificationDao.deleteByIds(specificationIds);
    }

    @Override
    public int topCommoditySpecification(Integer specificationId, Integer sortNumber) {
        if (specificationId == null || sortNumber==null) {
            return -1;
        }
        commoditySpecificationDao.updateSortNumbers(sortNumber);
        return commoditySpecificationDao.updateSortNumber(specificationId,1);

    }

    @Override
    public int sortCommoditySpecification(Integer specificationId, Integer sortNumber) {
        if (specificationId == null || sortNumber==null) {
            return -1;
        }
        return commoditySpecificationDao.updateSortNumber(specificationId, sortNumber);
    }
}
