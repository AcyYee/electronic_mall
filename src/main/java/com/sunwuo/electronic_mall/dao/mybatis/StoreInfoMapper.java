package com.sunwuo.electronic_mall.dao.mybatis;


import java.util.List;

import com.sunwuo.electronic_mall.entity.StoreInfo;

public interface StoreInfoMapper {
    int deleteByPrimaryKey(Integer storeId);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Integer storeId);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
    
    List<StoreInfo> storeInfoList();
    
    StoreInfo storeLogin(StoreInfo storeInfo);
}