package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.service.OrderInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author acy 屋大维
 */

@RestController
@RequestMapping("order/info")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping("wx/addOne")
    public ResultObject wxAddOne(Integer storeId,Integer userId,Integer specificationId, Integer itemCount,Integer companyId,Integer addressId,String wxProvinceCode, String wxAddressInfo,String orderRemark){
        return orderInfoService.addOneItem(storeId,userId,specificationId, itemCount, companyId,addressId,wxProvinceCode, wxAddressInfo, orderRemark);
    }

}
