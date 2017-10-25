package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.UserAddress;
import org.apache.ibatis.annotations.Param;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    int deleteByIds(@Param("addressIds") int[] addressIds);

    int updateSortNumber(@Param("addressIds") Integer addressIds, @Param("sortNumber") Integer sortNumber);

    void updateSortNumbers(Integer sortNumber);
}