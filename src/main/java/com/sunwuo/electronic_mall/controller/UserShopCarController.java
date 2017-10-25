package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.UserShopCar;
import com.sunwuo.electronic_mall.service.UserShopCarService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/shopCar")
public class UserShopCarController {

    @Autowired
    private UserShopCarService userShopCarService;

    /**
     * 获取购物车 user/shopCar/wx/find
     * @param userId 用户id
     * @return 返回购物车
     */
    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer userId){
        UserShopCar userShopCar = userShopCarService.getMyShopCard(userId);
        if (userShopCar == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",userShopCar);
        }
    }

}
