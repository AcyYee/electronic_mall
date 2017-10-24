package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.CommodityInfo;
import com.sunwuo.electronic_mall.service.CommodityInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 关于商品信息的所有接口
 * @author acy
 */

@RestController
@RequestMapping("commodity/info")
public class CommodityInfoController {

    @Autowired
    private CommodityInfoService commodityInfoService;

    /**
     * 添加商品数据信息
     * @param commodityInfo 商品数据
     * @return 返回添加后的商品信息数据
     */
    @RequestMapping("add")
    public ResultObject add(CommodityInfo commodityInfo, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityInfoService.addCommodityInfo(commodityInfo),commodityInfo);
    }

    /**
     *
     * @param commodityInfo 商品数据
     * @return 返回更新后的商品数据
     */
    @RequestMapping("update")
    public ResultObject update(CommodityInfo commodityInfo,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityInfoService.updateCommodityInfo(commodityInfo),commodityInfo);
    }

    /**
     * 微信获取商品列表
     * @param pageIndex 页面
     * @param pageSize  每页数据量
     * @param commodityType 类型
     * @param storeId 商城 或者 门店id
     * @param categoryTwoId 二级分类id
     * @param searchString 搜索字符
     * @return 返回集合
     */
    @RequestMapping("wx/finds")
    public ResultObject wxFinds(Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize , Integer commodityType , Integer storeId , Integer categoryTwoId, String searchString, @RequestParam(defaultValue = "1") Integer isSales, HttpServletResponse response){
        return finds(pageIndex, pageSize, commodityType, storeId, categoryTwoId, searchString, isSales, response);
    }

    /**
     * 获取商品列表
     * @param pageIndex 页面
     * @param pageSize  每页数据量
     * @param commodityType 类型
     * @param storeId 商城 或者 门店id
     * @param categoryTwoId 二级分类id
     * @param searchString 搜索字符
     * @return 返回集合
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer pageIndex, @RequestParam(defaultValue = "20") Integer pageSize , Integer commodityType , Integer storeId , Integer categoryTwoId, String searchString, Integer isSales, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = commodityInfoService.findByIds(pageIndex,pageSize,commodityType, storeId,categoryTwoId,searchString,isSales);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据不正确");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

    /**
     * 获取单个商品
     * @param commodityId 商品id
     * @return 结果
     */
    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer commodityId,HttpServletResponse response){
        return find(commodityId, response);
    }

    /**
     * 获取单个商品
     * @param commodityId 商品id
     * @return 结果
     */
    @RequestMapping("find")
    public ResultObject find(Integer commodityId,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        CommodityInfo commodityInfo = commodityInfoService.findById(commodityId);
        if (commodityInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"id为空或数据不存在");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",commodityInfo);
        }
    }

    /**
     * 批量删除商品
     * @param commodityIds 需要删除的商品id
     * @return 结果
     */
    @RequestMapping("deletes")
    public ResultObject deletes(int[] commodityIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityInfoService.deletesCommodityInfo(commodityIds),null);
    }

    /**
     * 置顶商品
     * @param commodityId 置顶商品id
     * @param sortNumber 置顶商品序号
     * @return 结果
     */
    @RequestMapping("top")
    public ResultObject top(Integer commodityId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityInfoService.topCommodityInfo(commodityId,sortNumber),null);
    }

    /**
     * 商品序号
     * @param commodityId 商品id
     * @param sortNumber 序号
     * @return 结果
     */
    @RequestMapping("sort")
    public ResultObject sort(Integer commodityId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityInfoService.sortCommodityInfo(commodityId,sortNumber),null);
    }

    /**
     * 批量开启关闭打折
     * @param commodityIds 需要开启关闭的商品id
     * @param isDiscount 1为开启0为关闭
     * @return 结果
     */
    @RequestMapping("discounts")
    public ResultObject discounts(int[] commodityIds,Integer isDiscount,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityInfoService.discounts(commodityIds,isDiscount),null);
    }

    /**
     * 批量开启销售
     * @param commodityIds 需要在销售的商品id
     * @param isSales 1为开启0为关闭
     * @return 结果
     */
    @RequestMapping("sales")
    public ResultObject sales(int[] commodityIds,Integer isSales,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commodityInfoService.sales(commodityIds,isSales),null);
    }

}
