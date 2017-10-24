package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper {

    int insert(OrderItem record);

    OrderItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    int deleteByIds(@Param("itemIds") Integer[] itemIds);

    int findItemCounts(Map<String, Object> map);

    List<OrderItem> findItems(Map<String, Object> map);

    OrderItem findBySpecificationAndShopCar(Integer specificationId, Integer shopCarId);

}