package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.ResourcesInfoMapper;
import com.sunwuo.electronic_mall.entity.ResourcesInfo;
import com.sunwuo.electronic_mall.service.ResourcesInfoService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResourcesInfoServiceImpl implements ResourcesInfoService {

    @Autowired
    private ResourcesInfoMapper resourcesInfoDao;

    @Override
    public int addResourcesInfo(ResourcesInfo resourcesInfo) {
        if (resourcesInfo == null || resourcesInfo.notEmpty())
            return -1;
        resourcesInfo.setCreateTime(TimeUtil.getDateTime(1));
        if (resourcesInfoDao.insertSelective(resourcesInfo)>=1){
            return resourcesInfoDao.updateSortNumber(resourcesInfo.getResourcesId(),resourcesInfo.getResourcesId());
        }else {
            return 0;
        }
    }

    @Override
    public int updateResourcesInfo(ResourcesInfo resourcesInfo) {
        if (resourcesInfo == null || resourcesInfo.getResourcesId() == null || resourcesInfo.isEmpty())
            return -1;
        return resourcesInfoDao.updateByPrimaryKeySelective(resourcesInfo);
    }

    @Override
    public int deleteByIds(Integer[] resourcesIds) {
        if (resourcesIds == null || resourcesIds.length < 1)
            return -1;
        return resourcesInfoDao.deleteByIds(resourcesIds);
    }

    @Override
    public ResourcesInfo getResourcesInfo(Integer resourcesId) {
        if (resourcesId == null || resourcesId < 1)
            return null;
        return resourcesInfoDao.selectByPrimaryKey(resourcesId);
    }

    @Override
    public PageData getResourcesInfos(Integer categoryId, Integer pageIndex, Integer pageSize) {
        if (categoryId == null)
            return null;
        PageData pageData = new PageData();
        Map<String,Object> map = new HashMap<>();
        map.put("categoryId",categoryId);
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setRecordCount(resourcesInfoDao.findResourcesCount(map));
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(resourcesInfoDao.findResourceses(map));
            return pageData;
        }
        pageData.setModelData(resourcesInfoDao.findResourceses(map));
        return pageData;
    }

    @Override
    public int topResources(Integer resourcesId, Integer sortNumber) {
        if (resourcesId == null || sortNumber == null)
            return  -1;
        resourcesInfoDao.updateSortNumbers(sortNumber);
        return resourcesInfoDao.updateSortNumber(resourcesId,1);
    }

    @Override
    public int sortResources(Integer resourcesId, Integer sortNumber) {
        if (resourcesId == null || sortNumber == null)
            return  -1;
        return resourcesInfoDao.updateSortNumber(resourcesId,sortNumber);
    }
}
