package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.AdminInfoMapper;
import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    AdminInfoMapper adminInfoDao;

    @Override
    public AdminInfo login(String adminName, String adminPassword) {
        if (adminName == null || adminPassword == null)
            return null;
        else {
            return adminInfoDao.selectByNameAndPassword(adminName, adminPassword);
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
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
