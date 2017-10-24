package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.CommodityCategoryOne;
import com.sunwuo.electronic_mall.entity.CommodityCategoryTwo;
import com.sunwuo.electronic_mall.service.CommodityCategoryService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 负责一级二级分类的控制器
 * Created by acy on 2017/6/6.
 */
@RestController
@RequestMapping("commodity/category")
public class CommodityCategoryController {

    @Autowired
    private CommodityCategoryService commodityCategoryService;

    /**
     * 微信端获取所有一级分类
     * @return 一级分类集
     */

    @RequestMapping("wx/getOnes")
    public ResultObject wxGetOnes(HttpServletResponse response, Integer pageIndex, @RequestParam(defaultValue = "20")Integer pageSize , @RequestParam(defaultValue = "1") Integer storeId, String searchString) {
        return getOnes(response,pageIndex,pageSize,storeId,searchString);
    }

    /**
     * 后台获取所有一级分类
     * @return 一级分类集
     */
    @RequestMapping("getOnes")
    public ResultObject getOnes(HttpServletResponse response, Integer pageIndex, @RequestParam(defaultValue = "20")Integer pageSize , @RequestParam(defaultValue = "1") Integer storeId, String searchString){
        response.setHeader("Access-Control-Allow-Origin","*");
        return new ResultObject (ResultObject.RESULT_SUCCESS,"获取成功", commodityCategoryService.getCategoryOnes(pageIndex,pageSize,storeId,searchString));
    }

    /**
     * 后台添加一级分类
     * @return 添加完成的一级分类
     */
    @RequestMapping("addOne")
    public ResultObject addOne(HttpServletResponse response, CommodityCategoryOne categoryOne){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.insertCategoryOne(categoryOne),categoryOne);
    }

    /**
     * 后台批量删除一级分类
     * @param categoryOneIds 需要删除的数组
     * @return 删除结果
     */
    @RequestMapping("deleteOnes")
    public ResultObject deleteOnes(int[] categoryOneIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(categoryOneIds);
        return ResultObject.returnResultObject(commodityCategoryService.deleteCategoryOnes(categoryOneIds),null);
    }

    /**
     * 更新一级分类
     * @param categoryOne 需要更新的一级分类
     * @return 返回更新结果
     */
    @RequestMapping("updateOne")
    public ResultObject updateOne(CommodityCategoryOne categoryOne,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.updateCategoryOne(categoryOne) ,categoryOne);
    }

    /**
     * 微信端获取所有二级分类
     * @return 二级分类集
     */
    @RequestMapping("wx/getTwos")
    public ResultObject wxGetTwos(HttpServletResponse response, Integer  pageIndex, @RequestParam(defaultValue = "20")Integer pageSize, String searchString , Integer categoryOneId){
        return getTwos(response,pageIndex,pageSize,searchString,categoryOneId);
    }

    /**
     * 后台获取所有二级分类
     * @return 二级分类集
     */
    @RequestMapping("getTwos")
    public ResultObject getTwos(HttpServletResponse response, Integer pageIndex, @RequestParam(defaultValue = "20")Integer pageSize, String searchString , Integer categoryOneId){
        response.setHeader("Access-Control-Allow-Origin","*");
        return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功", commodityCategoryService.getCategoryTwos(pageIndex,pageSize,searchString,categoryOneId));
    }

    /**
     * 后台添加二级分类
     * @return 添加完成的二级分类
     */
    @RequestMapping("addTwo")
    public ResultObject addTwo(CommodityCategoryTwo categoryTwo,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.insertCategoryTwo(categoryTwo),categoryTwo);
    }

    /**
     * 后台批量删除二级分类
     * @param categoryTwoIds 需要删除的数组
     * @return 删除结果
     */
    @RequestMapping("deleteTwos")
    public ResultObject deleteTwos(int[] categoryTwoIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.deleteCategoryTwos(categoryTwoIds),null);
    }

    /**
     * 排序置换
     */
    @RequestMapping("sortOne")
    public ResultObject sortOne(Integer categoryOneId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.sortOne(categoryOneId,sortNumber),null);
    }

    /**
     * 排序置顶
     */
    @RequestMapping("topOne")
    public ResultObject topOne(Integer categoryOneId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.topOne(categoryOneId,sortNumber),null);
    }

    @RequestMapping("sortTwo")
    public ResultObject sortTwo(Integer categoryTwoId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.sortTwo(categoryTwoId,sortNumber),null);
    }

    /**
     * 排序置换
     */
    @RequestMapping("topTwo")
    public ResultObject topTwo(Integer categoryTwoId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.topTwo(categoryTwoId,sortNumber),null);
    }

    /**
     * 更新二级分类
     * @param categoryTwo 需要更新的二级分类
     * @return 返回更新结果
     */
    @RequestMapping("updateTwo")
    public ResultObject updateTwo(CommodityCategoryTwo categoryTwo , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityCategoryService.updateCategoryTwo(categoryTwo),categoryTwo);
    }

}
