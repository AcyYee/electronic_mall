package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.OrderItem;
import com.sunwuo.electronic_mall.vo.OrderItemModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper {

    int insert(OrderItem record);

    OrderItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateCountByPrimaryKey(OrderItem record);

    int deleteByIds(@Param("itemIds") Integer[] itemIds);

    int findItemCounts(Map<String, Object> map);

    List<OrderItemModel> findItems(Map<String, Object> map);

    OrderItem findBySpecificationAndShopCar(@Param("specificationId") Integer specificationId,@Param("shopCarId") Integer shopCarId);

}