package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.AdminInfoMapper;
import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.service.AdminInfoService;
import com.sunwuo.electronic_mall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    private final AdminInfoMapper adminInfoDao;

    @Autowired
    public AdminInfoServiceImpl(AdminInfoMapper adminInfoDao) {
        this.adminInfoDao = adminInfoDao;
    }

    @Override
    public AdminInfo login(String adminName, String adminPassword) {
        if (adminName == null || adminPassword == null) {
            return null;
        }
        else {
            return adminInfoDao.selectByNameAndPassword(adminName, MD5Util.encoderByMd5(adminPassword));
        }
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void testTX() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdminId(34);
        adminInfo.setAdminName("111");
        adminInfo.setAdminPassword("111");
        adminInfo.setStoreId(4);
        adminInfoDao.insert(adminInfo);
        AdminInfo adminInfo1 = new AdminInfo();
        adminInfo1.setAdminPassword("222");
        adminInfo1.setAdminName("222");
        adminInfoDao.insert(adminInfo1);
    }

}
