package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.LogisticsInfo;
import org.apache.ibatis.annotations.Param;

public interface LogisticsInfoMapper {
    int deleteByPrimaryKey(Integer logistics);

    int insert(LogisticsInfo record);

    int insertSelective(LogisticsInfo record);

    LogisticsInfo selectByPrimaryKey(Integer logistics);

    int updateByPrimaryKeySelective(LogisticsInfo record);

    int updateByPrimaryKey(LogisticsInfo record);

    int receiveByPrimaryKey(@Param("logisticsId") Integer logisticsId,@Param("dateTime") String dateTime);

    void updateBodyByTag(@Param("logisticsTag") String logisticsTag,@Param("logisticsBody") String logisticsBody);

}