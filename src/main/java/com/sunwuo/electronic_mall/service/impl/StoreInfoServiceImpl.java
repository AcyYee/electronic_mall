package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.AdminInfoMapper;
import com.sunwuo.electronic_mall.dao.mybatis.StoreInfoMapper;
import com.sunwuo.electronic_mall.dao.mybatis.StoreProjectMapper;
import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.entity.StoreInfo;
import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.service.StoreInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunwuo.electronic_mall.util.MD5Util;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author acy 屋大维
 */
@Service("storeInfoService")
public class StoreInfoServiceImpl implements StoreInfoService {

    @Autowired
    private StoreInfoMapper storeInfoDao;

    @Autowired
    private StoreProjectMapper storeProjectDao;

    @Autowired
    private AdminInfoMapper adminInfoDao;

    @Override
    public StoreInfo getStore(Integer storeId) {
        if (storeId == null){
            return null;
        }
        return storeInfoDao.selectByPrimaryKey(storeId);
    }

    @Override
    public int updateStore(StoreInfo storeInfo) {
        if(storeInfo.getStoreId() == null){
            return -1;
        }
        return storeInfoDao.updateByPrimaryKeySelective(storeInfo);
    }

    @Override
    public int addStore(StoreInfo storeInfo, StoreProject storeProject, AdminInfo adminInfo) {
        if(storeInfo == null || storeProject == null || adminInfo == null || storeInfo.notEmpty() || storeProject.notEmpty() || adminInfo.notEmpty()){
            return -1;
        }else {
            String createTime = TimeUtil.getDateTime(1);
            storeInfo.setCreateTime(createTime);
            storeProject.setCreateTime(createTime);
            adminInfo.setCreateTime(createTime);
            adminInfo.setAdminPassword(MD5Util.encoderByMd5(adminInfo.getAdminPassword()));
            if (storeInfoDao.insertSelective(storeInfo)>0){
                storeProject.setStoreId(storeInfo.getStoreId());
                adminInfo.setStoreId(storeInfo.getStoreId());
                if (storeProjectDao.insertStoreProject(storeProject)>0){
                    return adminInfoDao.insertSelective(adminInfo);
                }
            }
        }
        return 0;
    }

    /**
     * 获取所有入住的商铺信息
     */
    @Override
    public StoreProject getStoreInfoByAppid(String appid) {
        if (appid == null || "".equals(appid)){
            return null;
        }
        return storeProjectDao.getStoreInfoByAppid(appid);
    }

    @Override
    public PageData findStores(Integer storeType, Integer pageIndex, Integer pageSize) {
        PageData pageData = new PageData();
        Map<String, Object> map = new HashMap<>(2);
        if (storeType != null){
            map.put("storeType",storeType);
        }if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(storeInfoDao.findStoreCounts(map));
            pageData.setPageModel(pageModel);
            map.put("pageModel",pageModel);
            pageData.setModelData(storeInfoDao.findStores(map));
        }else {
            pageData.setModelData(storeInfoDao.findStores(map));
        }
        return pageData;
    }

    @Override
    public int deleteStoreINFOS(int[] storeInfoIds) {
        if (storeInfoIds == null || storeInfoIds.length < 1){
            return -1;
        }
        return storeInfoDao.deleteByIds(storeInfoIds);
    }

    @Override
    public int updateType(int[] storeInfoIds, Integer storeType) {
        if (storeInfoIds == null || storeInfoIds.length < 1){
            return -1;
        }
        return storeInfoDao.updateType(storeInfoIds,storeType);
    }

}
