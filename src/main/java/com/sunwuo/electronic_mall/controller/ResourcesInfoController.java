package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.ResourcesInfo;
import com.sunwuo.electronic_mall.service.ResourcesInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("resources/info")
public class ResourcesInfoController {
    @Autowired
    private ResourcesInfoService resourcesInfoService;

    /**
     * 添加资源信息数据
     * @param resourcesInfo 资源信息数据
     * @return 返回添加后的资源信息数据
     */
    @RequestMapping("add")
    public ResultObject add(ResourcesInfo resourcesInfo, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesInfoService.addResourcesInfo(resourcesInfo),resourcesInfo);
    }

    /**
     * 更新资源信息数据
     * @param resourcesInfo 资源信息数据
     * @return 返回更新后的资源信息数据
     */
    @RequestMapping("update")
    public ResultObject update(ResourcesInfo resourcesInfo, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesInfoService.updateResourcesInfo(resourcesInfo),resourcesInfo);
    }

    /**
     * 批量删除资源信息
     * @param resourcesIds 资源信息id 数组
     * @return 返回处理结果
     */
    @RequestMapping("deletes")
    public ResultObject deletes(Integer[] resourcesIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesInfoService.deleteByIds(resourcesIds),null);
    }

    /**
     * 微信 获取资源信息
     * @param resourcesId 资源信息id
     * @return 资源信息
     */
    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer resourcesId,HttpServletResponse response){
        return find(resourcesId, response);
    }

    /**
     * 获取资源信息
     * @param resourcesId 资源信息id
     * @return 资源信息
     */
    @RequestMapping("find")
    public ResultObject find(Integer resourcesId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        ResourcesInfo resourcesInfo = resourcesInfoService.getResourcesInfo(resourcesId);
        if (resourcesInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据不正确");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",resourcesInfo);
        }
    }

    /**
     * 微信获取资源信息列表
     * @param categoryId 分类id
     * @param pageIndex 页码
     * @param pageSize 页数据量
     * @return 返回资源信息列表
     */
    @RequestMapping("wx/finds")
    public ResultObject wxFind(Integer categoryId, Integer pageIndex , @RequestParam(defaultValue = "10") Integer pageSize, HttpServletResponse response){
        return finds(categoryId, pageIndex, pageSize, response);
    }
    /**
     * 微信获取资源信息列表
     * @param categoryId 分类id
     * @param pageIndex 页码
     * @param pageSize 页数据量
     * @return 返回资源信息列表
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer categoryId, Integer pageIndex , @RequestParam(defaultValue = "20") Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = resourcesInfoService.getResourcesInfos(categoryId,pageIndex,pageSize);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据不正确");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

    /**
     * 置顶资源信息
     * @param resourcesId 置顶资源信息id
     * @param sortNumber 置顶资源信息序号
     * @return 结果
     */
    @RequestMapping("top")
    public ResultObject top(Integer resourcesId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesInfoService.topResources(resourcesId,sortNumber),null);
    }

    /**
     * 资源信息序号
     * @param resourcesId 资源信息id
     * @param sortNumber 序号
     * @return 结果
     */
    @RequestMapping("sort")
    public ResultObject sort(Integer resourcesId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesInfoService.sortResources(resourcesId,sortNumber),null);
    }

}
