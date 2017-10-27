package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.LogisticsInfo;
import com.sunwuo.electronic_mall.service.LogisticsInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author acy 屋大维
 */
@RestController
@RequestMapping("logistics/info")
public class LogisticsInfoController {

    private final LogisticsInfoService logisticsInfoService;

    @Autowired
    public LogisticsInfoController(LogisticsInfoService logisticsInfoService) {
        this.logisticsInfoService = logisticsInfoService;
    }

    @RequestMapping("reply")
    public ResultObject reply(LogisticsInfo logisticsInfo, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsInfoService.reply(logisticsInfo),logisticsInfo);
    }

    @RequestMapping("wx/receive")
    public ResultObject wxReceive(Integer logisticsId){
        return ResultObject.returnResultObject(logisticsInfoService.receive(logisticsId),null);
    }

    @RequestMapping("wx/findInfo")
    public ResultObject wxFind(String companyCode ,String logisticsTag){
        String info = logisticsInfoService.findInfo(companyCode,logisticsTag);
        if (info == null || "".equals(info)){
            return new ResultObject(ResultObject.RESULT_ERROR,"获取失败");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",info);
        }
    }

}
