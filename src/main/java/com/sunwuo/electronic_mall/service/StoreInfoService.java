package com.sunwuo.electronic_mall.service;

import java.util.List;

import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.entity.StoreInfo;
import com.sunwuo.electronic_mall.entity.StoreProject;

public interface StoreInfoService {

    StoreInfo getStore(Integer storeId);

    int updateStore(StoreInfo storeInfo);

	int addStore(StoreInfo storeInfo, StoreProject storeProject, AdminInfo adminInfo);

	List<StoreProject> getStoreProject();

	StoreInfo storeLogin(StoreInfo storeInfo);

	StoreProject getStoreInfoByAppid(String appid);

}
