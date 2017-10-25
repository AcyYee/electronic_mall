package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.UserAddress;
import com.sunwuo.electronic_mall.vo.PageData;

public interface UserAddressService {
    int addAddress(UserAddress userAddress);

    PageData findAddresses(Integer pageIndex, Integer pageSize, Integer userId);

    UserAddress findAddress(Integer addressId);

    int updateAddress(UserAddress userAddress);

    int deleteAddresses(Integer[] addressIds);
}
