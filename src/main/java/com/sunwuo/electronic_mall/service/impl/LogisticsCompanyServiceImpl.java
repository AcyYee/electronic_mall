package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.LogisticsCompanyMapper;
import com.sunwuo.electronic_mall.entity.LogisticsCompany;
import com.sunwuo.electronic_mall.service.LogisticsCompanyService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("logisticsCompanyService")
public class LogisticsCompanyServiceImpl implements LogisticsCompanyService {

    @Autowired
    private LogisticsCompanyMapper logisticsCompanyDao;

    @Override
    public int addCompany(LogisticsCompany logisticsCompany) {
        if (logisticsCompany == null || logisticsCompany.notEmpty())
            return -1;
        logisticsCompany.setCreateTime(TimeUtil.getDateTime(1));
        if (logisticsCompanyDao.insertSelective(logisticsCompany) > 0){
            return logisticsCompanyDao.updateSortNumber(logisticsCompany.getCompanyId(),logisticsCompany.getCompanyId());
        }else{
            return 0;
        }
    }

    @Override
    public PageData findCompanies(Integer storeId, Integer storeType, String searchString, Integer pageIndex, Integer pageSize) {
        if (storeId == null || storeType == null)
            return null;
        Map<String,Object> map = new HashMap<>();
        map.put("storeId",storeId);
        map.put("storeType",storeType);
        PageData pageData = new PageData();
        if (searchString != null)
            map.put("searchString",searchString);
        if (pageIndex != null) {
            PageModel pageModel = new PageModel();
            pageModel.setPageSize(pageSize);
            pageModel.setPageIndex(pageIndex);
            int recordCount = logisticsCompanyDao.findCompanyCount(map);
            pageModel.setRecordCount(recordCount);
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(logisticsCompanyDao.findCompanies(map));
        }
        pageData.setModelData(logisticsCompanyDao.findCompanies(map));
        return pageData;
    }

    @Override
    public int updateCompany(LogisticsCompany logisticsCompany) {
        System.out.println(logisticsCompany);
        if (logisticsCompany == null || logisticsCompany.isEmpty())
            return -1;
        else
            return logisticsCompanyDao.updateByPrimaryKeySelective(logisticsCompany);
    }

    @Override
    public int deleteCompanies(Integer[] companyIds) {
        if (companyIds == null || companyIds.length<=0)
            return -1;
        return logisticsCompanyDao.deleteByIds(companyIds);
    }

    @Override
    public LogisticsCompany findCompany(Integer companyId) {
        if (companyId == null || companyId<=0)
            return null;
        else
            return logisticsCompanyDao.selectByPrimaryKey(companyId);
    }

    @Override
    public int topCompany(Integer companyId, Integer sortNumber) {
        if (companyId == null || sortNumber == null){
            return -1;
        }
        logisticsCompanyDao.updateSortNumbers(sortNumber);
        return logisticsCompanyDao.updateSortNumber(companyId,sortNumber);
    }

    @Override
    public int sortCompany(Integer companyId, Integer sortNumber) {
        if (companyId == null || sortNumber == null){
            return -1;
        }
        return logisticsCompanyDao.updateSortNumber(companyId,sortNumber);
    }

}
