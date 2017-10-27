package com.sunwuo.electronic_mall.dao.mybatis;


import java.util.List;
import java.util.Map;

import com.sunwuo.electronic_mall.entity.StoreInfo;
import com.sunwuo.electronic_mall.vo.StoreInfoCountModel;
import org.apache.ibatis.annotations.Param;

public interface StoreInfoMapper {

    int deleteByPrimaryKey(Integer storeId);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Integer storeId);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);

    StoreInfo storeLogin(StoreInfo storeInfo);

    StoreInfoCountModel findCountModelByPrimaryKey(Integer storeId);
    
    int findStoreCounts(Map<String, Object> map);

    List<StoreInfo> findStores(Map<String, Object> map);

    int deleteByIds(@Param("storeInfoIds") int[] storeInfoIds);

    int updateType(@Param("storeInfoIds")int[] storeInfoIds,@Param("storeType") Integer storeType);

}