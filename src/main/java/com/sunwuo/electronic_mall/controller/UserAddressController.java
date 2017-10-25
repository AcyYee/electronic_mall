package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.UserAddress;
import com.sunwuo.electronic_mall.service.UserAddressService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/address")
public class UserAddressController {

    private final UserAddressService userAddressService;

    @Autowired
    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @RequestMapping("wx/add")
    public ResultObject wxAdd(UserAddress userAddress){
        return ResultObject.returnResultObject(userAddressService.addAddress(userAddress),userAddress);
    }

    @RequestMapping("wx/update")
    public ResultObject wxUpdate(UserAddress userAddress){
        return ResultObject.returnResultObject(userAddressService.updateAddress(userAddress),userAddress);
    }

    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer addressId){
        UserAddress userAddress = userAddressService.findAddress(addressId);
        if (userAddress == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",userAddress);
        }
    }

    @RequestMapping("wx/deletes")
    public ResultObject wxDeletes(Integer[] addressIds){
        return ResultObject.returnResultObject(userAddressService.deleteAddresses(addressIds),null);
    }

    @RequestMapping("wx/finds")
    public ResultObject wxFinds(Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize , Integer userId){
        PageData pageData = userAddressService.findAddresses(pageIndex,pageSize,userId);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

}
