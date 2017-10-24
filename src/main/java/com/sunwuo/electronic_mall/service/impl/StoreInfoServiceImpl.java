package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.AdminInfoMapper;
import com.sunwuo.electronic_mall.dao.mybatis.StoreInfoMapper;
import com.sunwuo.electronic_mall.dao.mybatis.StoreProjectMapper;
import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.entity.StoreInfo;
import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.service.StoreInfoService;

import java.util.List;

import com.sunwuo.electronic_mall.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<StoreProject> getStoreProject() {

        return storeProjectDao.storeProjectList();
    }

    @Override
    public StoreInfo storeLogin(StoreInfo storeInfo) {
        return storeInfoDao.storeLogin(storeInfo);
    }

    @Override
    public StoreProject getStoreInfoByAppid(String appid) {
        return storeProjectDao.getStoreInfoByAppid(appid);
    }



//    @Override
//    public int addStore(StoreInfo storeInfo) {
//        if(storeInfo==null||!storeInfo.isEmpty()){
//            return -1;
//        }
//        Integer result = storeInfoDao.insert(storeInfo);
//        if (result<1){
//            return -1;
//        }else {
//            return 1;
//        }
//    }
}
