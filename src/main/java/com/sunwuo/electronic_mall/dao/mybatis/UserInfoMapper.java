package com.sunwuo.electronic_mall.dao.mybatis;


import com.sunwuo.electronic_mall.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

	int insertSelective(UserInfo userInfo);

	int updateByPrimaryKeySelective(UserInfo userInfo);

	UserInfo findByOpenid(String openid);

	UserInfo findById(int id);

    int updateDefaultAddress(@Param("addressId") Integer addressId,@Param("userId") Integer userId);
}
