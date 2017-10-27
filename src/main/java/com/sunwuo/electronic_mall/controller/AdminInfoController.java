package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.service.AdminInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin/info")
public class AdminInfoController {

    private final AdminInfoService adminInfoService;

    @Autowired
    public AdminInfoController(AdminInfoService adminInfoService) {
        this.adminInfoService = adminInfoService;
    }

    /**
     * 后台管理员登录
     * @param adminName 管理员昵称
     * @param adminPassword 管理员密码
     * @return 返回登录结果
     */
    @RequestMapping("login")
    public ResultObject login(String adminName, String adminPassword, HttpSession session , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        AdminInfo adminInfo  = adminInfoService.login(adminName,adminPassword);
        if (adminInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据有空或者错误");
        }else if(adminInfo.getAdminType() == 1){
        	//管理员
            session.setAttribute("adminName",adminInfo.getAdminName());
            return new ResultObject(ResultObject.RESULT_SUCCESS,"登录成功",adminInfo);
        }else{
        	//店铺
            session.setAttribute("adminName",adminInfo.getAdminName());
            return new ResultObject(ResultObject.RESULT_SUCCESS,"登录成功",adminInfo);
        }
    }

//    @RequestMapping("")

    /**
     * 测试接口
     */
    @RequestMapping("testTX")
    public ResultObject testTX(){
        adminInfoService.testTX();
        return new ResultObject();
    }

}
