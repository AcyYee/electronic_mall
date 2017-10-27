package com.sunwuo.electronic_mall.service;


import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.entity.StoreInfo;
import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.vo.PageData;

public interface StoreInfoService {

    StoreInfo getStore(Integer storeId);

    int updateStore(StoreInfo storeInfo);

	int addStore(StoreInfo storeInfo, StoreProject storeProject, AdminInfo adminInfo);

	StoreProject getStoreInfoByAppid(String appid);

    PageData findStores(Integer storeType, Integer pageIndex, Integer pageSize);

	int deleteStoreINFOS(int[] storeInfoIds);

	int updateType(int[] storeInfoIds, Integer storeType);

}
