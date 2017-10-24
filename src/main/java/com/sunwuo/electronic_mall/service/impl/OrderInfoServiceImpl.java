package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.OrderInfoMapper;
import com.sunwuo.electronic_mall.dao.mybatis.OrderItemMapper;
import com.sunwuo.electronic_mall.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;



}
