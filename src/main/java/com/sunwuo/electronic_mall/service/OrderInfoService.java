package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.util.ResultObject;

/**
 * @author acy 屋大维
 */

public interface OrderInfoService {

    ResultObject addOneItem(Integer storeId,Integer userId,Integer specificationId, Integer itemCount,Integer companyId,Integer addressId,String wxProvinceCode, String wxAddressInfo,String orderRemark);

}
