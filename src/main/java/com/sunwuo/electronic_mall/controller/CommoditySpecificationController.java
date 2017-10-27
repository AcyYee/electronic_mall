package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.CommoditySpecification;
import com.sunwuo.electronic_mall.service.CommoditySpecificationService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author acy 屋大维
 */
@RestController
@RequestMapping("commodity/specification")
public class CommoditySpecificationController {

    private final CommoditySpecificationService commoditySpecificationService;

    private Logger logger = LoggerFactory.getLogger(CommoditySpecificationController.class);

    @Autowired
    public CommoditySpecificationController(CommoditySpecificationService commoditySpecificationService) {
        this.commoditySpecificationService = commoditySpecificationService;
    }

    /**
     * 添加规格
     * @param commoditySpecification 规格信息
     * @return 返回信息
     */
    @RequestMapping("add")
    public ResultObject add(CommoditySpecification commoditySpecification, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println("你好");
        logger.info("你好");
        logger.info(commoditySpecification.toString());
        return ResultObject.returnResultObject(commoditySpecificationService.addSpecification(commoditySpecification),commoditySpecification);
    }

    /**
     * 更新规格信息
     * @param commoditySpecification 规格信息
     * @return 返回信息
     */
    @RequestMapping("update")
    public ResultObject update(CommoditySpecification commoditySpecification, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commoditySpecificationService.updateSpecification(commoditySpecification),commoditySpecification);
    }

    /**
     * 批量删除规格
     * @param specificationIds 需要批量规格id
     * @return 删除结果
     */
    @RequestMapping("deletes")
    public ResultObject deletes(int[] specificationIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commoditySpecificationService.deleteSpecifications(specificationIds),null);
    }

    /**
     * 获取规格
     * @param specificationId 规格id
     * @return 返回规格
     */
    @RequestMapping("find")
    public ResultObject find(Integer specificationId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        CommoditySpecification commoditySpecification = commoditySpecificationService.getSpecification(specificationId);
        if (commoditySpecification==null){
            return new ResultObject();
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",commoditySpecification);
        }
    }

    /**
     * 获取规格
     * @param commodityId 商品id
     * @param pageIndex 页码
     * @param pageSize 页数
     * @return 返回规格集合
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer commodityId, String searchString, Integer pageIndex, @RequestParam(defaultValue = "20") Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = commoditySpecificationService.getSpecifications(commodityId,searchString,pageIndex,pageSize);
        if (pageData == null){
            return new ResultObject();
        }else{
            return new ResultObject(100,"获取成功",pageData);
        }
    }

    /**
     * 置顶商品
     * @param specificationId 置顶规格id
     * @param sortNumber 置顶规格序号
     * @return 结果
     */
    @RequestMapping("top")
    public ResultObject top(Integer specificationId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commoditySpecificationService.topCommoditySpecification(specificationId,sortNumber),null);
    }

    /**
     * 商品序号
     * @param specificationId 规格id
     * @param sortNumber 序号
     * @return 结果
     */
    @RequestMapping("sort")
    public ResultObject sort(Integer specificationId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(commoditySpecificationService.sortCommoditySpecification(specificationId,sortNumber),null);
    }

}
