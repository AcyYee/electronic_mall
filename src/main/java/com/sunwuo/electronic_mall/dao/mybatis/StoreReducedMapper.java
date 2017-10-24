package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.StoreReduced;

public interface StoreReducedMapper {
    int deleteByPrimaryKey(Integer reducedId);

    int insert(StoreReduced record);

    int insertSelective(StoreReduced record);

    StoreReduced selectByPrimaryKey(Integer reducedId);

    int updateByPrimaryKeySelective(StoreReduced record);

    int updateByPrimaryKey(StoreReduced record);
}