package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.LogisticsCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogisticsCompanyMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(LogisticsCompany record);

    int insertSelective(LogisticsCompany record);

    LogisticsCompany selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(LogisticsCompany record);

    int updateByPrimaryKey(LogisticsCompany record);

    int updateSortNumber(@Param("companyId") Integer companyId, @Param("sortNumber") Integer sortNumber);

    void updateSortNumbers(Integer sortNumber);

    List<LogisticsCompany> findCompanies(Map<String, Object> map);

    int findCompanyCount(Map<String, Object> map);

    int deleteByIds(@Param("companyIds") Integer[] companyIds);

}