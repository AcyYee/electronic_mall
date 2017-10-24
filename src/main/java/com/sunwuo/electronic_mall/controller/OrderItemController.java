package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.OrderItem;
import com.sunwuo.electronic_mall.service.OrderItemService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order/item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping("wx/add")
    public ResultObject add(OrderItem orderItem){
        return ResultObject.returnResultObject(orderItemService.addOrderItem(orderItem),orderItem);
    }

    @RequestMapping("wx/update")
    public ResultObject update(OrderItem orderItem){
        return ResultObject.returnResultObject(orderItemService.updateOrderItem(orderItem),orderItem);
    }

    @RequestMapping("wx/deletes")
    public ResultObject deletes(Integer[] itemIds){
        return ResultObject.returnResultObject(orderItemService.deleteOrderItems(itemIds),null);
    }

    @RequestMapping("wx/finds")
    public ResultObject finds(Integer pageIndex, Integer pageSize ,Integer orderId,Integer shopCarId){
        PageData pageData = orderItemService.findOrderItems(pageIndex,pageSize,orderId,shopCarId);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

    @RequestMapping("wx/find")
    public ResultObject find(Integer itemId){
        OrderItem orderItem = orderItemService.findOrderItem(itemId);
        if (orderItem == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误或无数据");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",orderItem);
        }
    }

}
