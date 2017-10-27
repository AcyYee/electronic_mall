package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.OrderInfo;
import com.sunwuo.electronic_mall.service.OrderInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.OrderInfoViewModel;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author acy 屋大维
 */

@RestController
@RequestMapping("order/info")
public class OrderInfoController {

    private final OrderInfoService orderInfoService;

    @Autowired
    public OrderInfoController(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    /**
     * 单个支付
     * @param storeId 商店id
     * @param userId 用户id
     * @param specificationId 规格id
     * @param itemCount 数量
     * @param companyId 物流公司id
     * @param addressId 用户地址id
     * @param wxProvinceCode 用户省简称
     * @param wxAddressInfo 用户地址信息
     * @param orderRemark 订单备注
     * @return 返回支付数据
     */
    @RequestMapping("wx/addOne")
    public ResultObject wxAddOne(Integer storeId, Integer userId, Integer specificationId, Integer itemCount, Integer companyId, Integer addressId, String wxProvinceCode, String wxAddressInfo, String orderRemark, HttpServletRequest request){
        return orderInfoService.addOneItem(storeId,userId,specificationId, itemCount, companyId,addressId,wxProvinceCode, wxAddressInfo, orderRemark,request.getRemoteAddr());
    }

    /**
     * 购物车支付
     * @param storeId 商店id
     * @param userId 用户id
     * @param itemIds 订单项id数组
     * @param companyId 物流公司id
     * @param addressId 用户地址id
     * @param wxProvinceCode 用户省简称
     * @param wxAddressInfo 用户地址信息
     * @param orderRemark 订单备注
     * @return 返回支付数据
     */
    @RequestMapping("wx/addAll")
    public ResultObject wxAddAll(Integer storeId,Integer userId,Integer[] itemIds,Integer companyId, Integer addressId,String wxProvinceCode, String wxAddressInfo, String orderRemark, HttpServletRequest request){
        return orderInfoService.addAllItem(storeId, userId, itemIds, companyId, addressId, wxProvinceCode, wxAddressInfo, orderRemark, request.getRemoteAddr());
    }

    /**
     * 获取运费价格
     * @param specificationId 规格号
     * @param itemCount 数量
     * @param itemIds 订单项id数组
     * @param storeId 商铺id
     * @param addressId 地址id
     * @param companyId 物流公司id
     * @param wxProvinceCode 用户省简称
     * @param type 1 单个 2 购物车
     * @return 价格
     */
    @RequestMapping("wx/getMailPrice")
    public ResultObject wxGetMailPrice(Integer specificationId, Integer itemCount, Integer[] itemIds, Integer storeId, Integer addressId, String wxProvinceCode, Integer companyId, String wxAddressInfo, Integer type){
        return ResultObject.returnResultObject(1,orderInfoService.getMailPrice(specificationId, itemCount, itemIds, storeId, addressId, wxProvinceCode, companyId, wxAddressInfo, type));
    }

    @RequestMapping("wx/findOR")
    public ResultObject wxFindOR(Integer orderId, String orderTag, HttpServletResponse response){
        return findOR(orderId, orderTag, response);
    }

    @RequestMapping("wx/deletes")
    public ResultObject wxDeletes(Integer[] orderIds){
        return ResultObject.returnResultObject(orderInfoService.userDeletes(orderIds),null);
    }

    /**
     *  批量标记付款
     * @param orderIds 订单id数组
     * @param adminName 管理员名称
     * @param adminPassword 管理员密码
     * @return 返回处理结果
     *
     */
    @RequestMapping("payManual")
    public ResultObject payManual(Integer[] orderIds,String adminName,String adminPassword ,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(orderInfoService.payManual(orderIds,adminName,adminPassword),null);
    }

    @RequestMapping("wx/pay")
    public ResultObject wxPay(Integer orderId , HttpServletRequest request){
        return orderInfoService.wxPay(orderId,request.getRemoteAddr());
    }

    @RequestMapping("findOR")
    public ResultObject findOR(Integer orderId, String orderTag, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        OrderInfoViewModel orderInfo = orderInfoService.findInfoModel(orderId,orderTag);
        if (orderInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",orderInfo);
        }
    }

    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer orderId, String orderTag, HttpServletResponse response){
        return find(orderId, orderTag, response);
    }

    @RequestMapping("find")
    public ResultObject find(Integer orderId, String orderTag, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        OrderInfo orderInfo = orderInfoService.findInfo(orderId,orderTag);
        if (orderInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",orderInfo);
        }
    }

    @RequestMapping("wx/finds")
    public ResultObject wxFinds(Integer orderType, Integer storeId,@RequestParam(required = false) Integer userId, String startTime, String endTime, Integer timeType, Integer pageIndex, Integer pageSize, HttpServletResponse response){
        return finds(orderType, storeId, userId, startTime, endTime, timeType, 0, pageIndex, pageSize, response);
    }

    @RequestMapping("finds")
    public ResultObject finds(Integer orderType, Integer storeId, Integer userId, String startTime, String endTime, Integer timeType, @RequestParam(defaultValue = "0") Integer isDelete, Integer pageIndex, Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = orderInfoService.findInfoes(orderType,storeId,userId,startTime,endTime,timeType,isDelete,pageIndex,pageSize);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

}
