package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.entity.StoreInfo;
import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.service.StoreInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author acy 屋大维
 */
@RestController
@RequestMapping("store/info")
public class StoreInfoController {

    private final StoreInfoService storeInfoService;

    @Autowired
    public StoreInfoController(StoreInfoService storeInfoService) {
        this.storeInfoService = storeInfoService;
    }

    @RequestMapping("wx/find")
    public ResultObject wxGets(Integer storeId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return get(storeId,response);
    }

    @RequestMapping("find")
    public ResultObject get(@RequestParam(value = "storeId",defaultValue = "1") Integer storeId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        StoreInfo storeInfo =storeInfoService.getStore(storeId);
        if (storeInfo != null){
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",storeInfo);
        }else{
            return new ResultObject();
        }
    }

    @RequestMapping("finds")
    public ResultObject finds(Integer storeType, HttpServletResponse response,Integer pageIndex,@RequestParam(defaultValue = "10") Integer pageSize){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = storeInfoService.findStores(storeType,pageIndex,pageSize);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

	@RequestMapping("update")
    public ResultObject update(StoreInfo storeInfo, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(storeInfoService.updateStore(storeInfo),storeInfo);
    }

    @RequestMapping("deletes")
    public ResultObject delete(int[] storeInfoIds,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(storeInfoService.deleteStoreINFOS(storeInfoIds),null);
    }

    @RequestMapping("ups")
    public ResultObject ups(int[] storeInfoIds,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(storeInfoService.updateType(storeInfoIds,1),null);
    }

    @RequestMapping("downs")
    public ResultObject downs(int[] storeInfoIds,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(storeInfoService.updateType(storeInfoIds,0),null);
    }

    /**
     * 商铺入住
     * @param storeInfo 商店信息
     * @param storeProject 商店程序信息
     * @param adminInfo 管理员信息
     * @return 成功信息
     */
    @RequestMapping("add")
    public ResultObject addStore(StoreInfo storeInfo, StoreProject storeProject, AdminInfo adminInfo , HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(storeInfoService.addStore(storeInfo,storeProject,adminInfo),storeInfo);
    }

    /**
     * 根据appid查询商铺
     * @param appid 需要的appid
     * @return 返回店铺信息
     */
    @RequestMapping("getByAppid")
    public ResultObject showStoreInfoByAppid(String appid, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
    	return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",storeInfoService.getStoreInfoByAppid(appid));
    }

}
