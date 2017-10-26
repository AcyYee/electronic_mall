package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.*;
import com.sunwuo.electronic_mall.entity.*;
import com.sunwuo.electronic_mall.service.OrderInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.util.UUIDUtill;
import com.sunwuo.electronic_mall.vo.SpecificationCountModel;
import com.sunwuo.electronic_mall.vo.StoreInfoCountModel;
import com.sunwuo.electronic_mall.wxpay.WXpayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author acy 屋大维
 */

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private LogisticsModelMapper logisticsModelMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CommoditySpecificationMapper commoditySpecificationMapper;

    @Override
    public ResultObject addOneItem(Integer storeId,Integer userId,Integer specificationId, Integer itemCount,Integer companyId, Integer addressId,String wxProvinceCode, String wxAddressInfo, String orderRemark) {
        boolean isOk = storeId == null || specificationId == null || itemCount == null ||(addressId== null && wxAddressInfo == null);
        //判断
        if (isOk){
            return ResultObject.returnResultObject(-1,null);
        }
        //获取信息
        SpecificationCountModel specificationCountModel = commoditySpecificationMapper.findCountByPrimaryKey(specificationId);
        StoreInfoCountModel storeInfoCountModel = orderInfoMapper.findCountModelByPrimaryKey(storeId);
        UserInfo userInfo = userInfoMapper.findById(userId);
        String createTime = TimeUtil.getDateTime(1);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        OrderItem orderItem = new OrderItem();
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        orderItem.setItemCount(itemCount);
        orderItem.setItemType(2);
        orderItem.setSpecificationId(specificationId);
        //计算价格
        if (specificationCountModel.getIsActivity() == 1){
            orderItem.setItemPrice(orderItem.getItemCount()*specificationCountModel.getActivityPrice());
            orderItem.setItemWeight(specificationCountModel.getSpecificationWeight()*orderItem.getItemCount());
        }else {
            orderItem.setItemPrice(orderItem.getItemCount()*specificationCountModel.getSpecificationPrice());
            orderItem.setItemWeight(specificationCountModel.getSpecificationWeight()*orderItem.getItemCount());
        }
        if (specificationCountModel.getIsDiscount() == 1){
            orderItem.setItemPrice(orderItem.getItemPrice()*specificationCountModel.getCommodityDiscount());
        }
        orderItem.setCreateTime(createTime);
        orderInfo.setCreateTime(createTime);
        logisticsInfo.setCreateTime(createTime);
        orderInfo.setCreateType(1);
        orderInfo.setOrderTag(UUIDUtill.getOrderTag(storeInfoCountModel.getStoreKey()));
        orderInfo.setOrderRemark(orderRemark);
        orderInfo.setAllowPrice(orderItem.getItemPrice());
        //运费计算开始
        logisticsInfo.setLogisticsWeight(orderItem.getItemWeight());
        //商铺无包邮或者金额小于包邮
        if (storeInfoCountModel.getMinMailPrice() == null || orderInfo.getAllowPrice()<storeInfoCountModel.getMinMailPrice()){
            //使用用户地址
            if (addressId != null){
                UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(),companyId);
                logisticsInfo.setModelId(logisticsModel.getModelId());
                int beyondWeight = logisticsInfo.getLogisticsWeight()-logisticsModel.getFreeWeight();
                if (beyondWeight <= 0){
                    logisticsInfo.setMailPrice(logisticsModel.getMinPrice());
                }else{
                    logisticsInfo.setMailPrice(logisticsModel.getMinPrice()+logisticsModel.getBeyondPrice()*beyondWeight/logisticsModel.getBeyondWeight());
                }
            }
            //使用微信地址
            else{
                LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(wxProvinceCode,companyId);
                logisticsInfo.setModelId(logisticsModel.getModelId());
                int beyondWeight = logisticsInfo.getLogisticsWeight()-logisticsModel.getFreeWeight();
                if (beyondWeight <= 0){
                    logisticsInfo.setMailPrice(logisticsModel.getMinPrice());
                }else{
                    logisticsInfo.setMailPrice(logisticsModel.getMinPrice()+logisticsModel.getBeyondPrice()*beyondWeight/logisticsModel.getBeyondWeight());
                }
            }
        }
        //商铺提供包邮
        else{
            //包邮无超重或未超重
            if(logisticsInfo.getLogisticsWeight() == null || logisticsInfo.getLogisticsWeight()<storeInfoCountModel.getMaxWeight()){
                //使用用户地址
                if (addressId != null){
                    UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(),companyId);
                    logisticsInfo.setModelId(logisticsModel.getModelId());
                    logisticsInfo.setMailPrice(0.0);
                }
                //使用微信地址
                else{
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(wxProvinceCode,companyId);
                    logisticsInfo.setModelId(logisticsModel.getModelId());
                    logisticsInfo.setMailPrice(0.0);
                }
            }else {
                //使用用户地址
                if (addressId != null) {
                    UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(), companyId);
                    logisticsInfo.setModelId(logisticsModel.getModelId());
                    int beyondWeight = logisticsInfo.getLogisticsWeight() - logisticsModel.getFreeWeight();
                    if (beyondWeight <= 0) {
                        logisticsInfo.setMailPrice(logisticsModel.getMinPrice()-storeInfoCountModel.getLessMailPrice());
                    } else {
                        logisticsInfo.setMailPrice(logisticsModel.getMinPrice() + logisticsModel.getBeyondPrice() * beyondWeight / logisticsModel.getBeyondWeight()-logisticsModel.getMinPrice()-storeInfoCountModel.getLessMailPrice());
                    }
                }
                //使用微信地址
                else {
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(wxProvinceCode, companyId);
                    logisticsInfo.setModelId(logisticsModel.getModelId());
                    int beyondWeight = logisticsInfo.getLogisticsWeight() - logisticsModel.getFreeWeight();
                    if (beyondWeight <= 0) {
                        logisticsInfo.setMailPrice(logisticsModel.getMinPrice()-storeInfoCountModel.getLessMailPrice());
                    } else {
                        logisticsInfo.setMailPrice(logisticsModel.getMinPrice() + logisticsModel.getBeyondPrice() * beyondWeight / logisticsModel.getBeyondWeight()-storeInfoCountModel.getLessMailPrice());
                    }
                }
            }
        }
        orderInfo.setMailPrice(logisticsInfo.getMailPrice());
        logisticsInfo.setLogisticsType(1);
        //运费计算结束>
        orderInfo.setPayPrice(orderInfo.getAllowPrice()+orderInfo.getMailPrice());
        if(logisticsInfoMapper.insert(logisticsInfo)>0){
            if(orderItemMapper.insert(orderItem)>0) {
                //吊起支付
                Object result = WXpayUtil.payrequest(storeInfoCountModel.getStoreName()+"订单", orderInfo.getOrderTag(), "", userInfo.getWxOpenid(), "", "",storeInfoCountModel.getAppId() , storeInfoCountModel.getMchId(), storeInfoCountModel.getPayKey(), new WXpayUtil.NotifyImple() {
                    //回调
                    @Override
                    public boolean notifcation(Map<String, String> map) {
                        return false;
                    }
                });
                return ResultObject.returnResultObject(1,result);
            }else {
                return ResultObject.returnResultObject(0,null);
            }
        }else {
            return ResultObject.returnResultObject(0,null);
        }
    }

}
