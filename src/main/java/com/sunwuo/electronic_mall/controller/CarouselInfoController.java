package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.CarouselInfo;
import com.sunwuo.electronic_mall.service.CarouselInfoService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("carousel/info")
public class CarouselInfoController {

    private final CarouselInfoService carouselInfoService;

    @Autowired
    public CarouselInfoController(CarouselInfoService carouselInfoService) {
        this.carouselInfoService = carouselInfoService;
    }

    /**
     * 添加轮播图
     * @param carouselInfo 轮播图信息
     * @return 返回轮播图添加后的信息
     */
    @RequestMapping("add")
    public ResultObject add(CarouselInfo carouselInfo,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(carouselInfoService.addCarouselInfo(carouselInfo),carouselInfo);
    }

    /**
     * 更新轮播图信息
     * @param carouselInfo 轮播图信息
     * @return 返回更新后的轮播图信息
     */
    @RequestMapping("update")
    public ResultObject update(CarouselInfo carouselInfo,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(carouselInfoService.updateCarouselInfo(carouselInfo),carouselInfo);
    }

    /**
     * 批量删除轮播图信息
     * @param carouselIds 轮播图信息id数组
     * @return 返回删除的轮播图信息数
     */
    @RequestMapping("deletes")
    public ResultObject deletes(int[] carouselIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(carouselInfoService.deleteByIds(carouselIds),null);
    }

    /**
     * 微信获取轮播图信息
     * @param carouselId 轮播图信息id
     * @return 返回轮播图信息
     */
    @RequestMapping("wx/find")
    public ResultObject wxFind(Integer carouselId,HttpServletResponse response){
        return find(carouselId, response);
    }

    /**
     * 微信获取轮播图信息
     * @param carouselId 轮播图信息id
     * @return 返回轮播图信息
     */
    @RequestMapping("find")
    public ResultObject find(Integer carouselId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        CarouselInfo carouselInfo = carouselInfoService.getCarouselInfo(carouselId);
        if (carouselInfo == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据不正确");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",carouselInfo);
        }
    }

    /**
     * 微信获取轮播图信息列表
     * @param commodityId 商品id
     * @param storeId 商铺id
     * @param carouselType 轮播图类型
     * @param pageIndex 页数
     * @param pageSize 每页数量
     * @return 返回数据
     */
    @RequestMapping("wx/finds")
    public ResultObject wxFind(Integer commodityId, Integer storeId, Integer carouselType, Integer pageIndex , @RequestParam(defaultValue = "10") Integer pageSize, HttpServletResponse response){
        return finds(commodityId, storeId,carouselType, pageIndex, pageSize, response);
    }

    /**
     * 获取轮播图信息列表
     * @param commodityId 商品id
     * @param storeId 商铺id
     * @param carouselType 轮播图类型
     * @param pageIndex 页数
     * @param pageSize 每页数量
     * @return 返回数据
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer commodityId, Integer storeId, Integer carouselType, Integer pageIndex , @RequestParam(defaultValue = "20") Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = carouselInfoService.getCarouselInfos(commodityId,storeId,carouselType,pageIndex,pageSize);
        if (pageData == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据不正确");
        }else{
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",pageData);
        }
    }

    /**
     * 置顶商品
     * @param carouselId 置顶规格id
     * @param sortNumber 置顶规格序号
     * @return 结果
     */
    @RequestMapping("top")
    public ResultObject top(Integer carouselId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(carouselInfoService.topCommodityCarousel(carouselId,sortNumber),null);
    }

    /**
     * 商品序号
     * @param carouselId 规格id
     * @param sortNumber 序号
     * @return 结果
     */
    @RequestMapping("sort")
    public ResultObject sort(Integer carouselId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(carouselInfoService.sortCommodityCarousel(carouselId,sortNumber),null);
    }

}
