package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.LogisticsCompany;
import com.sunwuo.electronic_mall.service.LogisticsCompanyService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("logistics/company")
public class LogisticsCompanyController {

    @Autowired
    private LogisticsCompanyService logisticsCompanyService;

    /**
     * 添加物流公司
     * @param logisticsCompany 物流公司信息
     * @return 返回添加后公司数据
     */
    @RequestMapping("add")
    public ResultObject add(LogisticsCompany logisticsCompany,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsCompanyService.addCompany(logisticsCompany),logisticsCompany);
    }

    /**
     * 修改物流公司
     * @param logisticsCompany 物流公司信息
     * @return 返回修改后公司数据
     */
    @RequestMapping("update")
    public ResultObject update(LogisticsCompany logisticsCompany,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsCompanyService.updateCompany(logisticsCompany),logisticsCompany);
    }

    /**
     * 批量删除公司
     * @param companyIds 公司id数组
     * @return 返回删除结果
     */
    @RequestMapping("deletes")
    public ResultObject deletes(Integer[] companyIds,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsCompanyService.deleteCompanies(companyIds),null);
    }

    /**
     * 获取物流公司信息
     * @param companyId 公司id
     * @return 返回公司信息
     */
    @RequestMapping("find")
    public ResultObject find(Integer companyId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        LogisticsCompany logisticsCompany = logisticsCompanyService.findCompany(companyId);
        if (logisticsCompany == null){
            return new ResultObject(ResultObject.RESULT_ERROR,"数据错误或者无此数据");
        }else {
            return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",logisticsCompany);
        }
    }

    /**
     * 获取物流公司信息
     * @param storeId 商铺 门店id
     * @param storeType 1为商铺 2为门店
     * @param searchString 模糊查询字符串
     * @param pageIndex 页码
     * @param pageSize 每页条数
     * @return 物流公司数据
     */
    @RequestMapping("finds")
    public ResultObject finds(Integer storeId, Integer storeType, String searchString, Integer pageIndex, @RequestParam(defaultValue = "20")Integer pageSize, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return new ResultObject(ResultObject.RESULT_SUCCESS,"获取成功",logisticsCompanyService.findCompanies(storeId,storeType,searchString,pageIndex,pageSize));
    }

    /**
     * 置顶物流公司
     * @param companyId 置顶物流公司id
     * @param sortNumber 置顶物流公司序号
     * @return 结果
     */
    @RequestMapping("top")
    public ResultObject top(Integer companyId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsCompanyService.topCompany(companyId,sortNumber),null);
    }

    /**
     * 物流公司序号
     * @param companyId 物流公司id
     * @param sortNumber 序号
     * @return 结果
     */
    @RequestMapping("sort")
    public ResultObject sort(Integer companyId ,Integer sortNumber,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return ResultObject.returnResultObject(logisticsCompanyService.sortCompany(companyId,sortNumber),null);
    }

}
