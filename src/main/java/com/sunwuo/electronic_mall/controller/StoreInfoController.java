package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.AdminInfo;
import com.sunwuo.electronic_mall.entity.StoreInfo;
import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.service.StoreInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("store/info")
public class StoreInfoController {

    @Autowired
    private StoreInfoService storeInfoService;

    @RequestMapping("wx/get")
    public ResultObject wxGets(Integer storeId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return get(storeId,response);
    }

    @RequestMapping("get")
    public ResultObject get(@RequestParam(value = "storeId",defaultValue = "1") Integer storeId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        StoreInfo storeInfo =storeInfoService.getStore(storeId);
        if (storeInfo != null){
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",storeInfo);
        }else{
            return new ResultObject();
        }
    }

//    @PostMapping("add")
//    public ResultObject add(StoreInfo storeInfo){
//        int i = storeInfoService.addStore(storeInfo);
//        if (i == -1){
//            return new ResultObject(ResultObject.RESULT_ERROR,"上传数据有误",storeInfo);
//        }else if(i == 1){
//            return new ResultObject(ResultObject.RESULT_SUCCESS,"添加成功",storeInfo);
//        }else{
//            return new ResultObject();
//        }
//    }

	@RequestMapping("update")
    public ResultObject update(StoreInfo storeInfo, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        int i = storeInfoService.updateStore(storeInfo);
        if (i == -1){
            return new ResultObject(ResultObject.RESULT_ERROR,"上传数据有误",storeInfo);
        }else if(i == 1){
            return new ResultObject(ResultObject.RESULT_SUCCESS,"更新成功",storeInfo);
        }else{
            return new ResultObject();
        }
    }

//    @PostMapping("delete")
//    public ResultObject delete(int[] storeInfoId){
//        int i = storeInfoService.updateStore(storeInfo);
//        if (i == -1){
//            return new ResultObject(ResultObject.RESULT_ERROR,"上传数据有误",storeInfo);
//        }else if(i == 1){
//            return new ResultObject(ResultObject.RESULT_SUCCESS,"更新成功",storeInfo);
//        }else{
//            return new ResultObject();
//        }
//    }
    /**
     * 商铺入住
     * @param storeInfo
     * @param storeProject
     * @return
     */
    @RequestMapping("add")
    public ResultObject addStore(StoreInfo storeInfo, StoreProject storeProject, AdminInfo adminInfo , HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(storeInfoService.addStore(storeInfo,storeProject,adminInfo),storeInfo);
    }
    /**
     * 获取所有商铺信息
     * @return
     */
    @RequestMapping("getStoreInfo")
    public ResultObject showStoreInfo(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
    	return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",storeInfoService.getStoreProject());
    }
    /**
     * 根据appid查询商铺
     * @param appid
     * @return
     */
    @RequestMapping("getByAppid")
    public ResultObject showStoreInfoByAppid(String appid, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
    	return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",storeInfoService.getStoreInfoByAppid(appid));
    }


}
