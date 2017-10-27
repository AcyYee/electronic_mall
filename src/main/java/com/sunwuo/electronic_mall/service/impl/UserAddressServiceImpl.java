package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.UserAddressMapper;
import com.sunwuo.electronic_mall.dao.mybatis.UserInfoMapper;
import com.sunwuo.electronic_mall.entity.UserAddress;
import com.sunwuo.electronic_mall.service.UserAddressService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressMapper userAddressMapper;

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserAddressServiceImpl(UserAddressMapper userAddressMapper, UserInfoMapper userInfoMapper) {
        this.userAddressMapper = userAddressMapper;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public int addAddress(UserAddress userAddress) {
        if (userAddress == null || userAddress.notEmpty()) {
            return -1;
        }
        userAddress.setCreateTime(TimeUtil.getDateTime(1));
        if(userAddressMapper.insert(userAddress)>=1){
            if (userAddress .getAddressType() == 1){
                userAddressMapper.updateNotDefault(userAddress.getUserId());
                userAddressMapper.updateIsDefault(userAddress.getAddressId());
            }
            return userInfoMapper.updateDefaultAddress(userAddress.getAddressId(),userAddress.getUserId());
        }
        return 0;
    }

    @Override
    public PageData findAddresses(Integer pageIndex, Integer pageSize, Integer userId) {
        if (userId == null){
            return null;
        }
        PageData pageData = new PageData();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(userAddressMapper.findAddressCounts(map));
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(userAddressMapper.findAddresses(map));
        }else {
            pageData.setModelData(userAddressMapper.findAddresses(map));
        }
        return pageData;
    }

    @Override
    public UserAddress findAddress(Integer addressId) {
        if (addressId == null || addressId < 1) {
            return null;
        } else {
            return userAddressMapper.selectByPrimaryKey(addressId);
        }
    }
    @Override
    public int updateAddress(UserAddress userAddress) {
        if (userAddress == null || userAddress.isEmpty()) {
            return -1;
        } else {
            if (userAddress .getAddressType() == 1){
                userInfoMapper.updateDefaultAddress(userAddress.getAddressId(),userAddress.getUserId());
                userAddressMapper.updateNotDefault(userAddress.getUserId());
                userAddressMapper.updateNotDefault(userAddress.getUserId());
            }
            return userAddressMapper.updateByPrimaryKeySelective(userAddress);
        }
    }

    @Override
    public int deleteAddresses(Integer[] addressIds) {
        if (addressIds == null || addressIds.length<1) {
            return -1;
        } else {
            return userAddressMapper.deleteByIds(addressIds);
        }
    }

}
