package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.OrderInfo;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.OrderInfoViewModel;
import com.sunwuo.electronic_mall.vo.PageData;

/**
 * @author acy 屋大维
 */

public interface OrderInfoService {

    ResultObject addOneItem(Integer storeId,Integer userId,Integer specificationId, Integer itemCount,Integer companyId, Integer addressId,String wxProvinceCode, String wxAddressInfo, String orderRemark, String ip);

    ResultObject addAllItem(Integer storeId,Integer userId,Integer[] itemIds,Integer companyId, Integer addressId,String wxProvinceCode, String wxAddressInfo, String orderRemark, String ip);

    Double getMailPrice(Integer specificationId, Integer itemCount, Integer[] itemIds, Integer storeId, Integer addressId, String wxProvinceCode, Integer companyId, String wxAddressInfo, Integer type);

    OrderInfo findInfo(Integer orderId, String orderTag);

    PageData findInfoes(Integer orderType, Integer storeId, Integer userId, String startTime, String endTime, Integer timeType, Integer isDelete, Integer pageIndex, Integer pageSize);

    OrderInfoViewModel findInfoModel(Integer orderId, String orderTag);

    ResultObject wxPay(Integer orderId ,String ip);

    int userDeletes(Integer[] orderIds);

    int payManual(Integer[] orderIds, String adminName, String adminPassword);

}
