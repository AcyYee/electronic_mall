package com.sunwuo.electronic_mall.vo;

import com.sunwuo.electronic_mall.entity.OrderItem;

import java.util.List;

public class OrderInfoViewListModel {

    private OrderInfoModel orderInfo;

    private List<OrderItemModel> orderItems;

    public OrderInfoViewListModel(OrderInfoModel orderInfo, List<OrderItemModel> items) {
        this.orderInfo = orderInfo;
        this.orderItems = items;
    }

    public OrderInfoModel getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoModel orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderInfoViewListModel{" +
                "orderInfo=" + orderInfo +
                ", orderItems=" + orderItems +
                '}';
    }
}
