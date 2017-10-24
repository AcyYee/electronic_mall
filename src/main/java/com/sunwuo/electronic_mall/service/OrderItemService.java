package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.OrderItem;
import com.sunwuo.electronic_mall.vo.PageData;

public interface OrderItemService {

    int addOrderItem(OrderItem orderItem);

    int updateOrderItem(OrderItem orderItem);

    int deleteOrderItems(Integer[] itemIds);

    PageData findOrderItems(Integer pageIndex, Integer pageSize, Integer orderId, Integer shopCarId);

    OrderItem findOrderItem(Integer itemId);

}
