package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.LogisticsInfo;

public interface LogisticsInfoMapper {
    int deleteByPrimaryKey(Integer logistics);

    int insert(LogisticsInfo record);

    int insertSelective(LogisticsInfo record);

    LogisticsInfo selectByPrimaryKey(Integer logistics);

    int updateByPrimaryKeySelective(LogisticsInfo record);

    int updateByPrimaryKey(LogisticsInfo record);
}