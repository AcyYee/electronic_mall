package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.*;
import com.sunwuo.electronic_mall.entity.*;
import com.sunwuo.electronic_mall.service.OrderInfoService;
import com.sunwuo.electronic_mall.util.MD5Util;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.util.UUIDUtill;
import com.sunwuo.electronic_mall.vo.*;
import com.sunwuo.electronic_mall.wxpay.WXpayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author acy 屋大维
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    private final AdminInfoMapper adminInfoMapper;

    private final OrderInfoMapper orderInfoMapper;

    private final OrderItemMapper orderItemMapper;

    private final LogisticsInfoMapper logisticsInfoMapper;

    private final UserAddressMapper userAddressMapper;

    private final LogisticsModelMapper logisticsModelMapper;

    private final StoreInfoMapper storeInfoMapper;

    private final UserInfoMapper userInfoMapper;

    private final CommoditySpecificationMapper commoditySpecificationMapper;

    @Autowired
    public OrderInfoServiceImpl(OrderInfoMapper orderInfoMapper, OrderItemMapper orderItemMapper
            , LogisticsInfoMapper logisticsInfoMapper, UserAddressMapper userAddressMapper
            , LogisticsModelMapper logisticsModelMapper, UserInfoMapper userInfoMapper
            , CommoditySpecificationMapper commoditySpecificationMapper
            ,StoreInfoMapper storeInfoMapper,AdminInfoMapper adminInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
        this.orderItemMapper = orderItemMapper;
        this.logisticsInfoMapper = logisticsInfoMapper;
        this.userAddressMapper = userAddressMapper;
        this.logisticsModelMapper = logisticsModelMapper;
        this.userInfoMapper = userInfoMapper;
        this.commoditySpecificationMapper = commoditySpecificationMapper;
        this.storeInfoMapper = storeInfoMapper;
        this.adminInfoMapper = adminInfoMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultObject addOneItem(Integer storeId,Integer userId,Integer specificationId, Integer itemCount,Integer companyId, Integer addressId,String wxProvinceCode, String wxAddressInfo, String orderRemark, String ip) {
        boolean isOk = storeId == null || specificationId == null || itemCount == null ||(addressId== null && wxAddressInfo == null);
        //判断
        if (isOk){
            return ResultObject.returnResultObject(-1,null);
        }
        //获取信息
        SpecificationCountModel specificationCountModel = commoditySpecificationMapper.findCountByPrimaryKey(specificationId);
        StoreInfoCountModel storeInfoCountModel = storeInfoMapper.findCountModelByPrimaryKey(storeId);
        UserInfo userInfo = userInfoMapper.findById(userId);
        String createTime = TimeUtil.getDateTime(1);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        OrderItem orderItem = new OrderItem();
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        orderItem.setItemCount(itemCount);
        orderItem.setItemType(2);
        orderItem.setSpecificationId(specificationId);
        orderItem.setCreateTime(createTime);
        orderInfo.setCreateTime(createTime);
        orderInfo.setStoreId(storeId);
        logisticsInfo.setCreateTime(createTime);
        orderInfo.setCreateType(1);
        orderInfo.setOrderTag(UUIDUtill.getOrderTag(storeInfoCountModel.getStoreKey()));
        if (orderInfoMapper.insertSelective(orderInfo)>0) {
            //计算价格
            orderItem.setOrderId(orderInfo.getOrderId());
            if (specificationCountModel.getIsActivity() == 1) {
                orderItem.setItemPrice(orderItem.getItemCount() * specificationCountModel.getActivityPrice());
                orderItem.setItemWeight(specificationCountModel.getSpecificationWeight() * orderItem.getItemCount());
            } else {
                orderItem.setItemPrice(orderItem.getItemCount() * specificationCountModel.getSpecificationPrice());
                orderItem.setItemWeight(specificationCountModel.getSpecificationWeight() * orderItem.getItemCount());
            }
            if (specificationCountModel.getIsDiscount() == 1) {
                orderItem.setItemPrice(orderItem.getItemPrice() * specificationCountModel.getCommodityDiscount());
            }
            orderInfo.setOrderRemark(orderRemark);
            orderInfo.setAllowPrice(orderItem.getItemPrice());
            //运费计算开始
            logisticsInfo.setLogisticsWeight(orderItem.getItemWeight());
            //商铺无包邮或者金额小于包邮
            setMail(storeInfoCountModel, orderInfo, addressId, wxProvinceCode, companyId, logisticsInfo, wxAddressInfo);
            String orderPrice = String.valueOf(Math.round(orderInfo.getPayPrice() * 100));
            if (logisticsInfoMapper.insert(logisticsInfo) > 0) {
                if (orderItemMapper.insert(orderItem) > 0) {
                    return pay(orderInfo, storeInfoCountModel, orderPrice, userInfo, ip);
                } else {
                    return ResultObject.returnResultObject(0, 1);
                }
            } else {
                return ResultObject.returnResultObject(0, 2);
            }
        }else {
            return ResultObject.returnResultObject(0, 3);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultObject addAllItem(Integer storeId, Integer userId, Integer[] itemIds, Integer companyId, Integer addressId, String wxProvinceCode, String wxAddressInfo, String orderRemark, String ip) {
        boolean isOk = storeId == null || itemIds == null || itemIds.length < 1 ||(addressId== null && wxAddressInfo == null);
        //判断
        if (isOk){
            return ResultObject.returnResultObject(-1,null);
        }
        //获取信息
        StoreInfoCountModel storeInfoCountModel = storeInfoMapper.findCountModelByPrimaryKey(storeId);
        UserInfo userInfo = userInfoMapper.findById(userId);
        String createTime = TimeUtil.getDateTime(1);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        //计算价格 重量
        orderInfo.setCreateTime(createTime);
        logisticsInfo.setCreateTime(createTime);
        orderInfo.setCreateType(2);
        orderInfo.setStoreId(storeId);
        orderInfo.setOrderTag(UUIDUtill.getOrderTag(storeInfoCountModel.getStoreKey()));
        orderInfo.setOrderRemark(orderRemark);
        orderInfo.setAllowPrice(0.0);
        logisticsInfo.setLogisticsWeight(0);
        if (orderInfoMapper.insertSelective(orderInfo)>0) {
            for (Integer itemId : itemIds) {
                OrderItem orderItem = orderItemMapper.selectByPrimaryKey(itemId);
                SpecificationCountModel specificationCountModel = commoditySpecificationMapper.findCountByPrimaryKey(orderItem.getSpecificationId());
                if (specificationCountModel.getIsActivity() == 1) {
                    orderItem.setItemPrice(orderItem.getItemCount() * specificationCountModel.getActivityPrice());
                } else {
                    orderItem.setItemPrice(orderItem.getItemCount() * specificationCountModel.getSpecificationPrice());
                }
                orderItem.setItemWeight(orderItem.getItemCount() * specificationCountModel.getSpecificationWeight());
                if (specificationCountModel.getIsDiscount() == 1) {
                    orderItem.setItemPrice(orderItem.getItemPrice() * specificationCountModel.getCommodityDiscount());
                }
                orderInfo.setAllowPrice(orderInfo.getAllowPrice() + orderItem.getItemPrice());
                logisticsInfo.setLogisticsWeight(logisticsInfo.getLogisticsWeight() + orderItem.getItemWeight());
                orderItem.setShopCarId(0);
                orderItem.setOrderId(orderInfo.getOrderId());
                orderItemMapper.updateByPrimaryKeySelective(orderItem);
            }
            setMail(storeInfoCountModel, orderInfo, addressId, wxProvinceCode, companyId, logisticsInfo, wxAddressInfo);
            //运费计算结束>
            String orderPrice = String.valueOf(Math.round(orderInfo.getPayPrice() * 100));
            if (logisticsInfoMapper.insert(logisticsInfo) > 0) {
                return pay(orderInfo, storeInfoCountModel, orderPrice, userInfo, ip);
            } else {
                return ResultObject.returnResultObject(0, null);
            }
        }else {
            return ResultObject.returnResultObject(0, null);
        }
    }

    private ResultObject pay(OrderInfo orderInfo,StoreInfoCountModel storeInfoCountModel,String orderPrice,UserInfo userInfo,String ip){
        if (orderInfoMapper.updateByPrimaryKeySelective(orderInfo)>0) {
            //吊起支付
            Object result = WXpayUtil.payrequest(storeInfoCountModel.getStoreName() + "订单", orderInfo.getOrderTag()
                    , orderPrice, userInfo.getWxOpenid(), ip, UUIDUtill.getUUID(6), storeInfoCountModel.getAppId()
                    , storeInfoCountModel.getMchId(), storeInfoCountModel.getPayKey(),orderInfo.getOrderId(),(Map<String, String> map) -> orderInfoMapper.updatePayByTag(map.get("out_trade_no"), TimeUtil.getDateTime(1)) > 0);
            return ResultObject.returnResultObject(1, result);
        }else {
            return ResultObject.returnResultObject(0,4);
        }
    }

    private void setMail(StoreInfoCountModel storeInfoCountModel,OrderInfo orderInfo,Integer addressId,String wxProvinceCode,Integer companyId,LogisticsInfo logisticsInfo,String wxAddressInfo){
        //商铺无包邮或者金额小于包邮
        if (storeInfoCountModel.getMinMailPrice() == null || orderInfo.getAllowPrice()<storeInfoCountModel.getMinMailPrice()){
            //使用用户地址
            if (addressId != null){
                UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(),companyId);
                logisticsInfo.setModelId(logisticsModel.getModelId());
                orderInfo.setAddressId(addressId);
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
                orderInfo.setWxAddressInfo(wxAddressInfo);
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
            if(storeInfoCountModel.getMaxWeight() == null || logisticsInfo.getLogisticsWeight()<storeInfoCountModel.getMaxWeight()){
                //使用用户地址
                if (addressId != null){
                    UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(),companyId);
                    logisticsInfo.setModelId(logisticsModel.getModelId());
                    orderInfo.setAddressId(addressId);
                    logisticsInfo.setMailPrice(0.0);
                }
                //使用微信地址
                else{
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(wxProvinceCode,companyId);
                    logisticsInfo.setModelId(logisticsModel.getModelId());
                    logisticsInfo.setMailPrice(0.0);
                    orderInfo.setWxAddressInfo(wxAddressInfo);
                }
            }else {
                //使用用户地址
                if (addressId != null) {
                    UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(), companyId);
                    logisticsInfo.setModelId(logisticsModel.getModelId());
                    orderInfo.setAddressId(addressId);
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
                    orderInfo.setWxAddressInfo(wxAddressInfo);
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
        orderInfo.setPayPrice(orderInfo.getAllowPrice()+orderInfo.getMailPrice());
        orderInfo.setOrderType(1);
    }

    @Override
    public Double getMailPrice(Integer specificationId, Integer itemCount, Integer[] itemIds, Integer storeId, Integer addressId, String wxProvinceCode, Integer companyId, String wxAddressInfo , Integer type){
        Double payMoney = 0.0;
        Integer weight = 0;
        StoreInfoCountModel storeInfoCountModel = storeInfoMapper.findCountModelByPrimaryKey(storeId);
        //单个购买结算情况
        if (type == 1) {
            SpecificationCountModel specificationCountModel = commoditySpecificationMapper.findCountByPrimaryKey(specificationId);
            if (specificationCountModel.getIsActivity() == 1) {
                payMoney = itemCount * specificationCountModel.getActivityPrice();
            } else {
                payMoney = itemCount * specificationCountModel.getSpecificationPrice();
            }
            weight = itemCount*specificationCountModel.getSpecificationWeight();
            if (specificationCountModel.getIsDiscount() == 1) {
                payMoney = payMoney * specificationCountModel.getCommodityDiscount();
            }
        }
        //购物车结算情况
        else{
            for (Integer itemId:itemIds) {
                OrderItem orderItem = orderItemMapper.selectByPrimaryKey(itemId);
                SpecificationCountModel specificationCountModel = commoditySpecificationMapper.findCountByPrimaryKey(orderItem.getSpecificationId());
                if (specificationCountModel.getIsActivity() == 1) {
                    orderItem.setItemPrice(orderItem.getItemCount() * specificationCountModel.getActivityPrice());
                } else {
                    orderItem.setItemPrice(orderItem.getItemCount() * specificationCountModel.getSpecificationPrice());
                }
                orderItem.setItemWeight(orderItem.getItemCount()*specificationCountModel.getSpecificationWeight());
                if (specificationCountModel.getIsDiscount() == 1) {
                    orderItem.setItemPrice(orderItem.getItemPrice() * specificationCountModel.getCommodityDiscount());
                }
                payMoney = payMoney + orderItem.getItemPrice();
                weight = weight + orderItem.getItemWeight();
                orderItemMapper.updateByPrimaryKeySelective(orderItem);
            }
        }
        if (storeInfoCountModel.getMinMailPrice() == null || payMoney <storeInfoCountModel.getMinMailPrice()){
            //使用用户地址
            if (addressId != null){
                UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(),companyId);
                int beyondWeight = weight-logisticsModel.getFreeWeight();
                if (beyondWeight <= 0){
                    return logisticsModel.getMinPrice();
                }else{
                    return logisticsModel.getMinPrice()+logisticsModel.getBeyondPrice()*beyondWeight/logisticsModel.getBeyondWeight();
                }
            }
            //使用微信地址
            else{
                LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(wxProvinceCode,companyId);
                int beyondWeight = weight-logisticsModel.getFreeWeight();
                if (beyondWeight <= 0){
                    return logisticsModel.getMinPrice();
                }else{
                    return logisticsModel.getMinPrice()+logisticsModel.getBeyondPrice()*beyondWeight/logisticsModel.getBeyondWeight();
                }
            }
        }
        //商铺提供包邮
        else{
            //包邮无超重或未超重
            if(storeInfoCountModel.getMaxWeight() == null || weight<storeInfoCountModel.getMaxWeight()){
                return 0.0;
            }else {
                //使用用户地址
                if (addressId != null) {
                    UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(userAddress.getProvinceCode(), companyId);
                    int beyondWeight = weight - logisticsModel.getFreeWeight();
                    if (beyondWeight <= 0) {
                        return logisticsModel.getMinPrice()-storeInfoCountModel.getLessMailPrice();
                    } else {
                        return logisticsModel.getMinPrice() + logisticsModel.getBeyondPrice() * beyondWeight / logisticsModel.getBeyondWeight()-logisticsModel.getMinPrice()-storeInfoCountModel.getLessMailPrice();
                    }
                }
                //使用微信地址
                else {
                    LogisticsModel logisticsModel = logisticsModelMapper.findByProvinceCode(wxProvinceCode, companyId);
                    int beyondWeight = weight - logisticsModel.getFreeWeight();
                    if (beyondWeight <= 0) {
                        return logisticsModel.getMinPrice()-storeInfoCountModel.getLessMailPrice();
                    } else {
                        return logisticsModel.getMinPrice() + logisticsModel.getBeyondPrice() * beyondWeight / logisticsModel.getBeyondWeight()-storeInfoCountModel.getLessMailPrice();
                    }
                }
            }
        }
    }

    @Override
    public OrderInfo findInfo(Integer orderId, String orderTag) {
        if (orderId == null && orderTag == null){
            return null;
        }
        OrderInfo orderInfo;
        if (orderId == null || orderId<1){
            orderInfo = orderInfoMapper.selectByTag(orderTag);
        }else {
            orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        }
        return orderInfo;
    }

    @Override
    public PageData findInfoes(Integer orderType, Integer storeId, Integer userId, String startTime, String endTime, Integer timeType, Integer isDelete, Integer pageIndex, Integer pageSize) {
        boolean isOk = orderType == null && storeId == null && userId == null && (timeType == null || startTime == null || endTime == null);
        if (isOk){
            return null;
        }
        List<OrderInfoViewListModel> listModels = new ArrayList<>();
        PageData pageData = new PageData();
        Map<String ,Object> map = new HashMap<>(7);
        map.put("isDelete",isDelete);
        if (orderType != null){
            map.put("orderType",orderType);
        }
        if (storeId != null){
            map.put("storeId",storeId);
        }
        if (userId != null){
            map.put("userId",userId);
        }
        if (timeType != null && startTime != null && endTime != null) {
            map.put("timeType", timeType);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
        }
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(orderInfoMapper.findInfoCount(map));
            map.put("pageModel",pageModel);
            List<OrderInfoModel> orderInfos = orderInfoMapper.findINFOS(map);
            logger.info(map.toString());
            for (OrderInfoModel orderInfo : orderInfos){
                map.clear();
                map.put("orderId",orderInfo.getOrderId());
                listModels.add(new OrderInfoViewListModel(orderInfo,orderItemMapper.findItems(map)));
            }
            pageData.setPageModel(pageModel);
            pageData.setModelData(listModels);
        }else {
            List<OrderInfoModel> orderInfos = orderInfoMapper.findINFOS(map);
            logger.info(map.toString());
            for (OrderInfoModel orderInfo : orderInfos){
                map.clear();
                map.put("orderId",orderInfo.getOrderId());
                listModels.add(new OrderInfoViewListModel(orderInfo,orderItemMapper.findItems(map)));
            }
            pageData.setModelData(listModels);
        }
        return pageData;
    }

    @Override
    public OrderInfoViewModel findInfoModel(Integer orderId, String orderTag) {
        if (orderId == null && orderTag == null){
            return null;
        }
        Map<String,Object> map = new HashMap<>(1);

        OrderInfoViewModel orderInfoModel;
        if (orderId == null || orderId<1){
            orderInfoModel = orderInfoMapper.findViewModelByTag(orderTag);
        }else {
            orderInfoModel = orderInfoMapper.findViewModelByPrimaryKey(orderId);
        }
        map.put("orderId",orderInfoModel.getOrderId());
        orderInfoModel.setOrderItemModels(orderItemMapper.findItems(map));
        return orderInfoModel;
    }

    @Override
    public ResultObject wxPay(Integer orderId,String ip) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
        UserInfo userInfo = userInfoMapper.findById(orderInfo.getUserId());
        StoreInfoCountModel storeInfoCountModel = storeInfoMapper.findCountModelByPrimaryKey(orderInfo.getStoreId());
        String orderPrice = String.valueOf(Math.round(orderInfo.getPayPrice() * 100));
        Object result = WXpayUtil.payrequest(storeInfoCountModel.getStoreName() + "订单", orderInfo.getOrderTag()
                , orderPrice, userInfo.getWxOpenid(), ip, UUIDUtill.getUUID(6), storeInfoCountModel.getAppId()
                , storeInfoCountModel.getMchId(), storeInfoCountModel.getPayKey(),orderInfo.getOrderId(),(Map<String, String> map) -> orderInfoMapper.updatePayByTag(map.get("out_trade_no"), TimeUtil.getDateTime(1)) > 0);
        return ResultObject.returnResultObject(1, result);
    }

    @Override
    public int userDeletes(Integer[] orderIds) {
        if(orderIds == null || orderIds.length < 1){
            return -1;
        }
        return orderInfoMapper.deleteByIds(orderIds);
    }

    @Override
    public int payManual(Integer[] orderIds, String adminName, String adminPassword) {
        if (orderIds == null || orderIds.length < 1 || adminName == null || adminPassword == null){
            return -1;
        }
        if (adminInfoMapper.selectByNameAndPassword(adminName, MD5Util.encoderByMd5(adminPassword)) != null){
            return orderInfoMapper.payManual(orderIds,TimeUtil.getDateTime(1));
        }
        return 0;
    }
}
