package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.UserShopCar;

public interface UserShopCarMapper {

    int insert(UserShopCar record);

    UserShopCar selectByUser(Integer userId);

}