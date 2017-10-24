package com.sunwuo.electronic_mall.dao.mybatis;

import java.util.List;

import com.sunwuo.electronic_mall.entity.StoreProject;

public interface StoreProjectMapper {

	int insertStoreProject(StoreProject storeProject);

	List<StoreProject> storeProjectList();
	
	StoreProject getStoreInfoByAppid(String appId);

    int updateProject(StoreProject storeProject);

    StoreProject findByInfoId(Integer storeId);
}
