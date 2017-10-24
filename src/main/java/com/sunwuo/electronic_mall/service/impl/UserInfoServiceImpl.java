package com.sunwuo.electronic_mall.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunwuo.electronic_mall.config.WXConfig;
import com.sunwuo.electronic_mall.dao.mybatis.StoreProjectMapper;
import com.sunwuo.electronic_mall.dao.mybatis.UserInfoMapper;
import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.entity.UserInfo;
import com.sunwuo.electronic_mall.service.UserInfoService;
import com.sunwuo.electronic_mall.util.HttpSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private StoreProjectMapper storeProjectMapper;

	@Override
	public UserInfo add(String js_code ,Integer storeId) {
		if (js_code == null || js_code.equals("")|| storeId == null){
			return null;
		}
		StoreProject storeProject = storeProjectMapper.findByInfoId(storeId);
		if (storeProject == null)
			return null;
		ObjectMapper mapper = new ObjectMapper();
		String url = WXConfig.SP_GET_OPENID;
		url = url.replaceFirst("APPID", storeProject.getAppId());
		url = url.replaceFirst("SECRET", storeProject.getAppSecret());
		url = url.replaceFirst("JSCODE", js_code);
		System.out.println(url);
		String json =  HttpSend.sendGet(url, "");

		UserInfo userInfo;
		try {
			HashMap map = mapper.readValue(json, HashMap.class);
			if (map.get("openid") != null) {
				String openid = (String) map.get("openid");
				userInfo = userInfoMapper.findByOpenid(openid);
				if (userInfo == null) {
					userInfo = new UserInfo();
					userInfo.setWxOpenid(openid);
					if (userInfoMapper.insertSelective(userInfo) > 0) {
						return userInfo;
					} else {
						return null;
					}
				}
				return userInfo;
			} else {
				System.out.println(map.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int update(UserInfo userinfo) {
		return userInfoMapper.updateByPrimaryKeySelective(userinfo);
	}

	@Override
	public List<UserInfo> find(UserInfo userinfo) {
		return null;
	}

	@Override
	public UserInfo findByOpenid(String openid) {
		return userInfoMapper.findByOpenid(openid);
	}

}
