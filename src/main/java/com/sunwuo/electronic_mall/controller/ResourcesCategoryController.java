package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.ResourcesCategory;
import com.sunwuo.electronic_mall.service.ResourcesCategoryService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("resources/category")
public class ResourcesCategoryController {

    @Autowired
    private ResourcesCategoryService resourcesCategoryService;

    /**
     * 添加资源分类数据
     * @param resourcesCategory 资源分类数据
     * @return 返回添加后的资源数据
     */
    @RequestMapping("add")
    public ResultObject add(ResourcesCategory resourcesCategory, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesCategoryService.addResourcesCategory(resourcesCategory),resourcesCategory);
    }

    /**
     * 更新资源分类数据
     * @param resourcesCategory 资源分类数据
     * @return 返回更新后的资源数据
     */
    @RequestMapping("update")
    public ResultObject update(ResourcesCategory resourcesCategory, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesCategoryService.updateResourcesCategory(resourcesCategory),resourcesCategory);
    }

    /**
     * 批量删除分类
     * @param categoryIds 分类id数组
     * @return 返回删除数据
     */
    @RequestMapping("deletes")
    public ResultObject deletes(Integer[] categoryIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesCategoryService.deleteByIds(categoryIds),null);
    }

    /**
     * 获取分类列表
     * @param categoryId 分类id
     * @return 返回分类列表信息
     */
    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer categoryId,HttpServletResponse response){
        return find(categoryId, response);
    }

    /**
     * 获取分类信息
     * @param categoryId 分类信息id
     * @return 返回分类数据
     */
    @RequestMapping("find")
    public ResultObject find(Integer categoryId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        ResourcesCategory resourcesCategory = resourcesCategoryService.getResourcesCategory(categoryId);
        if (resourcesCategory == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据不正确");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",resourcesCategory);
        }
    }

    /**
     * 微信获取分类列表
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return 返回分类列表信息
     */
    @RequestMapping("wx/finds")
    public ResultObject wxFind(Integer categoryId, Integer pageIndex , @RequestParam(defaultValue = "10") Integer pageSize, HttpServletResponse response){
        return finds(categoryId, pageIndex, pageSize, response);
    }

    /**
     * 获取分类列表
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return 返回分类列表信息
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer categoryId, Integer pageIndex , @RequestParam(defaultValue = "20") Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = resourcesCategoryService.getResourcesCategories(categoryId,pageIndex,pageSize);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据不正确");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

    /**
     * 置顶分类
     * @param categoryId 置顶分类id
     * @param sortNumber 置顶分类序号
     * @return 结果
     */
    @RequestMapping("top")
    public ResultObject top(Integer categoryId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesCategoryService.topResourcesCategory(categoryId,sortNumber),null);
    }

    /**
     * 数组分类序号
     * @param categoryId 分类id
     * @param sortNumber 序号
     * @return 结果
     */
    @RequestMapping("sort")
    public ResultObject sort(Integer categoryId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(resourcesCategoryService.sortResourcesCategory(categoryId,sortNumber),null);
    }


}
