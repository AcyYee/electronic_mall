package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.ActivityInfoMapper;
import com.sunwuo.electronic_mall.dao.mybatis.CommodityInfoMapper;
import com.sunwuo.electronic_mall.entity.ActivityInfo;
import com.sunwuo.electronic_mall.service.ActivityInfoService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivityInfoServiceImpl implements ActivityInfoService{

    @Autowired
    private ActivityInfoMapper activityInfoDao;

    @Autowired
    private CommodityInfoMapper commodityInfoDao;

    @Override
    public int addActivity(ActivityInfo activityInfo) {
        if (activityInfo == null || activityInfo.notEmpty())
            return -1;
        activityInfo.setCreateTime(TimeUtil.getDateTime(1));
        if (activityInfoDao.insertSelective(activityInfo)>0){
            if (activityInfo.getIsActivity() != null && activityInfo.getIsActivity() == 1){
                commodityInfoDao.updateIsActivity(activityInfo.getCommodityId(),1);
            }
            return activityInfoDao.updateSortNumber(activityInfo.getActivityId(),activityInfo.getActivityId());
        }else {
            return 0;
        }
    }

    @Override
    public int updateActivity(ActivityInfo activityInfo) {
        if (activityInfo == null || activityInfo.isEmpty())
            return -1;
        if (activityInfo.getIsActivity() != null && activityInfo.getIsActivity() == 1){
            commodityInfoDao.updateIsActivity(activityInfo.getCommodityId(),1);
        }
        return activityInfoDao.updateByPrimaryKeySelective(activityInfo);
    }

    @Override
    public ActivityInfo findActivity(Integer activityId) {
        if (activityId == null || activityId<1)
            return null;
        else {
            return activityInfoDao.selectByPrimaryKey(activityId);
        }
    }

    @Override
    public int deleteActivities(Integer[] activityIds) {
        if (activityIds == null || activityIds.length<1)
            return -1;
        else{
            commodityInfoDao.updateIsActivities(activityIds,0);
            return activityInfoDao.deleteByIds(activityIds);
        }
    }

    @Override
    public PageData findActivities(Integer pageIndex, Integer pageSize) {
        PageData pageData = new PageData();
        Map<String,Object> map = new HashMap<>();
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(activityInfoDao.findActivityCount());
            pageData.setPageModel(pageModel);
            map.put("pageModel",pageModel);
            pageData.setModelData(activityInfoDao.findActivities(map));
            return pageData;
        }else{
            pageData.setModelData(activityInfoDao.findActivities(map));
            return pageData;
        }
    }

    @Override
    public int isActivities(Integer[] activityIds) {
        return activityInfoDao.updateIsActivities(activityIds);
    }

}
