package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.UserInfo;
import com.sunwuo.electronic_mall.service.UserInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping("wx/login")
    public ResultObject wxLogin(String js_code,Integer storeId){
        UserInfo userInfo = userInfoService.add(js_code,storeId);
        if (userInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"登录出错");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"登录成功",userInfo);
        }
    }

}
