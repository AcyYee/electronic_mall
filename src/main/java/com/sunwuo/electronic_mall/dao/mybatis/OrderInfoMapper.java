package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.OrderInfo;
import com.sunwuo.electronic_mall.vo.OrderInfoModel;
import com.sunwuo.electronic_mall.vo.OrderInfoViewModel;
import com.sunwuo.electronic_mall.vo.StoreInfoCountModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author acy 屋大维
 */
public interface OrderInfoMapper {

    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int updatePayById(@Param("orderId") Integer orderId,@Param("payTime") String payTime);

    int updatePayByTag(@Param("orderTag") String orderTag,@Param("payTime") String payTime);

    OrderInfo selectByTag(String orderTag);

    int findInfoCount(Map<String, Object> map);

    List<OrderInfoModel> findINFOS(Map<String, Object> map);

    OrderInfoViewModel findViewModelByTag(String orderTag);

    OrderInfoViewModel findViewModelByPrimaryKey(Integer orderId);

    int deleteByIds(@Param("orderIds")Integer[] orderIds);

    int payManual(@Param("orderIds") Integer[] orderIds, @Param("dateTime") String dateTime);
}