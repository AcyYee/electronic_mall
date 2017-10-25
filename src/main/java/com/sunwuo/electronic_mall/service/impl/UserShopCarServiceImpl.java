package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.UserShopCarMapper;
import com.sunwuo.electronic_mall.entity.UserShopCar;
import com.sunwuo.electronic_mall.service.UserShopCarService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userShopCarService")
public class UserShopCarServiceImpl implements UserShopCarService {

    @Autowired
    private UserShopCarMapper userShopCarMapper;

    @Override
    public UserShopCar getMyShopCard(Integer userId) {
        if (userId == null || userId < 1)
            return null;
        UserShopCar userShopCar = userShopCarMapper.selectByUser(userId);
        if (userShopCar == null){
            userShopCar = new UserShopCar();
            userShopCar.setUserId(userId);
            userShopCar.setCreateTime(TimeUtil.getDateTime(1));
            if(userShopCarMapper.insert(userShopCar)>0)
                return userShopCar;
            else {
                return null;
            }
        }else {
            return userShopCar;
        }
    }
}
