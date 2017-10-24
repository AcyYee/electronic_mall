package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

	/**
	 * 用户第一次登陆注册
	 * @return
	 */
	UserInfo add(String js_code ,Integer storeId);
	/**
	 * 更新用户基本信息
	 * @param userinfo
	 * @return
	 */
	int update(UserInfo userinfo);
	/**
	 * 查询用户
	 * @param userinfo
	 * @return
	 */
	List<UserInfo> find(UserInfo userinfo);
	/**
	 * 按openid查询用户
	 * @param openid
	 * @return
	 */
	UserInfo findByOpenid(String openid);
}
