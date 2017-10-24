package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.CommoditySpecificationMapper;
import com.sunwuo.electronic_mall.dao.mybatis.OrderItemMapper;
import com.sunwuo.electronic_mall.entity.CommoditySpecification;
import com.sunwuo.electronic_mall.entity.OrderItem;
import com.sunwuo.electronic_mall.service.OrderItemService;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemDao;

    @Autowired
    private CommoditySpecificationMapper commoditySpecificationMapper;

    @Override
    public int addOrderItem(OrderItem orderItem) {
        if (orderItem == null || orderItem.notEmpty()){
            return -1;
        }
        OrderItem temp = orderItemDao.findBySpecificationAndShopCar(orderItem.getSpecificationId(),orderItem.getShopCarId());
        CommoditySpecification commoditySpecification = commoditySpecificationMapper.selectByPrimaryKey(orderItem.getSpecificationId());
        if (temp == null){
            orderItem.setItemType(1);
            orderItem.setItemPrice(orderItem.getItemCount()*commoditySpecification.getSpecificationPrice());
            return orderItemDao.insert(orderItem);
        }else {
            temp.setItemCount(orderItem.getItemCount()+temp.getItemCount());
            temp.setItemPrice(temp.getItemPrice()+orderItem.getItemCount()*commoditySpecification.getSpecificationPrice());
            orderItem = temp;
            return orderItemDao.updateByPrimaryKeySelective(orderItem);
        }
    }

    @Override
    public int updateOrderItem(OrderItem orderItem) {
        if (orderItem == null || orderItem.getItemId() == null || orderItem.isEmpty()){
            return -1;
        }
        CommoditySpecification commoditySpecification = commoditySpecificationMapper.selectByPrimaryKey(orderItem.getSpecificationId());
        orderItem.setItemPrice(orderItem.getItemCount()*commoditySpecification.getSpecificationPrice());
        return orderItemDao.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public int deleteOrderItems(Integer[] itemIds) {
        if(itemIds == null || itemIds.length <1){
            return -1;
        }
        return orderItemDao.deleteByIds(itemIds);
    }

    @Override
    public PageData findOrderItems(Integer pageIndex, Integer pageSize, Integer orderId, Integer shopCarId) {
        if(orderId == null && shopCarId == null) {
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        PageData pageData = new PageData();
        if (orderId == null){
            map.put("shopCarId",shopCarId);
        }else {
            map.put("orderId",orderId);
        }
        if (pageIndex != null){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(orderItemDao.findItemCounts(map));
            pageData.setPageModel(pageModel);
            pageData.setModelData(orderItemDao.findItems(map));
        }else{
            pageData.setModelData(orderItemDao.findItems(map));
        }
        return pageData;
    }

    @Override
    public OrderItem findOrderItem(Integer itemId) {
        if (itemId == null || itemId <1)
            return null;
        else
            return orderItemDao.selectByPrimaryKey(itemId);
    }

}
