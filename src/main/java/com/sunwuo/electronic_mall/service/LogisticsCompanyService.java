package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.LogisticsCompany;
import com.sunwuo.electronic_mall.vo.PageData;

public interface LogisticsCompanyService {

    int addCompany(LogisticsCompany logisticsCompany);

    PageData findCompanies(Integer storeId, Integer storeType, String searchString, Integer pageIndex, Integer pageSize);

    int updateCompany(LogisticsCompany logisticsCompany);

    int deleteCompanies(Integer[] companyIds);

    LogisticsCompany findCompany(Integer companyId);

    int topCompany(Integer companyId, Integer sortNumber);

    int sortCompany(Integer companyId, Integer sortNumber);
}
