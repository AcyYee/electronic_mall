package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    int deleteByIds(@Param("addressIds") Integer[] addressIds);

    void updateNotDefault(Integer userId);

    void updateIsDefault(Integer addressId);

    int findAddressCounts(Map<String, Object> map);

    List<UserAddress> findAddresses(Map<String, Object> map);
}