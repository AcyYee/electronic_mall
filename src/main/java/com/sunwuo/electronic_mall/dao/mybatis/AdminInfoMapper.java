package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.AdminInfo;
import org.apache.ibatis.annotations.Param;

public interface AdminInfoMapper {

    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    AdminInfo selectByNameAndPassword(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);

}