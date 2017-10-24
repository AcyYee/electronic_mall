package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.ActivityInfo;
import com.sunwuo.electronic_mall.service.ActivityInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("activity/info")
public class ActivityInfoController {

    @Autowired
    private ActivityInfoService activityInfoService;

    /**
     * 添加活动
     * @param activityInfo 活动信息
     * @return 返回添加后的活动
     */
    @RequestMapping("add")
    public ResultObject add(ActivityInfo activityInfo, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(activityInfoService.addActivity(activityInfo),activityInfo);
    }

    /**
     * 更新活动信息
     * @param activityInfo 活动信息
     * @return 返回更新后的活动
     */
    @RequestMapping("update")
    public ResultObject update(ActivityInfo activityInfo, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(activityInfoService.updateActivity(activityInfo),activityInfo);
    }

    /**
     * 批量删除活动
     * @param activityIds 活动id
     * @return 返回删除的活动数
     */
    @RequestMapping("deletes")
    public ResultObject deletes(Integer[] activityIds, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(activityInfoService.deleteActivities(activityIds),null);
    }

    /**
     *  批量改变活动状态 0 未开启活动 1开启活动
     * @param activityIds 活动id数组
     * @return 返回开启结果
     */
    @RequestMapping("isActivities")
    public ResultObject isActivities(Integer[] activityIds, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(activityInfoService.isActivities(activityIds),null);
    }

    /**
     * 微信获取活动信息
     * @param activityId 活动id
     * @return 返回活动信息
     */
    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer activityId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return find(activityId,response);
    }

    /**
     * 获取活动信息
     * @param activityId 活动id
     * @return 返回活动信息
     */
    @RequestMapping("find")
    public ResultObject find(Integer activityId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        ActivityInfo activityInfo = activityInfoService.findActivity(activityId);
        if (activityInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"没有此数据或数据传输失败");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取数据",activityInfo);
        }
    }

    /**
     * 获取活动列表
     * @param pageIndex 页数
     * @param pageSize 每页数量
     * @return 返回数据
     */
    @RequestMapping("wx/finds")
    public ResultObject wxFinds(Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return finds(pageIndex, pageSize,response);
    }

    /**
     * 获取活动列表
     * @param pageIndex 页数
     * @param pageSize 每页数量
     * @return 返回数据
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer pageIndex, @RequestParam(defaultValue = "20") Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = activityInfoService.findActivities(pageIndex,pageSize);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"没有此数据或数据传输失败");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取数据",pageData);
        }
    }

}
