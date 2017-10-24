package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.LogisticsModel;
import com.sunwuo.electronic_mall.service.LogisticsModelService;
import com.sunwuo.electronic_mall.util.ResultObject;
import com.sunwuo.electronic_mall.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("logistics/model")
public class LogisticsModelController {

    @Autowired
    private LogisticsModelService logisticsModelService;

    /**
     * 添加物流模板
     * @param logisticsModel 物流模板信息
     * @return 返回添加后模板数据
     */
    @RequestMapping("add")
    public ResultObject add(LogisticsModel logisticsModel,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsModelService.addModel(logisticsModel),logisticsModel);
    }

    /**
     * 修改物流模板
     * @param logisticsModel 物流模板信息
     * @return 返回修改后模板数据
     */
    @RequestMapping("update")
    public ResultObject update(LogisticsModel logisticsModel,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsModelService.updateModel(logisticsModel),logisticsModel);
    }

    /**
     * 批量删除模板
     * @param modelIds 模板id数组
     * @return 返回删除结果
     */
    @RequestMapping("deletes")
    public ResultObject deletes(Integer[] modelIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsModelService.deleteModels(modelIds),null);
    }

    /**
     * 获取模板信息
     * @param modelId 模板id
     * @return 返回模板信息
     */
    @RequestMapping("find")
    public ResultObject find(Integer modelId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        LogisticsModel logisticsModel = logisticsModelService.findModel(modelId);
        if (logisticsModel == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误或者无此数据");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",logisticsModel);
        }
    }

    /**
     * 获取模板信息列表
     * @param companyId 公司id
     * @param storeId 商铺 门店 id
     * @param storeType 是的类型 1 商铺 2 门店
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return 返回模板信息列表
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer companyId, Integer storeId, Integer storeType, Integer pageIndex, @RequestParam(defaultValue = "20")Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",logisticsModelService.findModels(companyId,storeId,storeType,pageIndex,pageSize));
    }

    /**
     * 置顶物流模型
     * @param modelId 置顶物流模型id
     * @param sortNumber 置顶物流模型序号
     * @return 结果
     */
    @RequestMapping("top")
    public ResultObject top(Integer modelId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsModelService.topModel(modelId,sortNumber),null);
    }

    /**
     * 物流模型序号
     * @param modelId 物流模型id
     * @param sortNumber 序号
     * @return 结果
     */
    @RequestMapping("sort")
    public ResultObject sort(Integer modelId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        PageData pageData = new PageData();
        return ResultObject.returnResultObject(logisticsModelService.sortModel(modelId,sortNumber),null);
    }

}
