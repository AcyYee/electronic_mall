package com.sunwuo.electronic_mall.dao.mybatis;


import com.sunwuo.electronic_mall.entity.UserInfo;

public interface UserInfoMapper {

	int insertSelective(UserInfo userInfo);

	int updateByPrimaryKeySelective(UserInfo userInfo);

	UserInfo findByOpenid(String openid);

	UserInfo findById(int id);

    void updateDefaultAddress(Integer addressId);
}
