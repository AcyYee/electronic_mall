package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.LogisticsInfo;

/**
 * @author acy 屋大维
 */
public interface LogisticsInfoService {

    int reply(LogisticsInfo logisticsInfo);

    int receive(Integer logisticsId);

    String findInfo(String companyCode ,String logisticsTag);

}
