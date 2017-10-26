package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.service.OrderInfoService;
import com.sunwuo.electronic_mall.wxpay.WXpayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("pay")
public class PayController {

    @RequestMapping("success/back")
    public void success(HttpServletRequest request, HttpServletResponse response){
        try {
            WXpayUtil.notify(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
