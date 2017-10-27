package com.sunwuo.electronic_mall.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunwuo.electronic_mall.dao.mybatis.LogisticsInfoMapper;
import com.sunwuo.electronic_mall.entity.LogisticsInfo;
import com.sunwuo.electronic_mall.service.LogisticsInfoService;
import com.sunwuo.electronic_mall.util.LogisticsUtil;
import com.sunwuo.electronic_mall.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author acy 屋大维
 */
@Service
public class LogisticsInfoServiceImpl implements LogisticsInfoService {

    private final LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    public LogisticsInfoServiceImpl(LogisticsInfoMapper logisticsInfoMapper) {
        this.logisticsInfoMapper = logisticsInfoMapper;
    }

    @Override
    public int reply(LogisticsInfo logisticsInfo) {
        if (logisticsInfo == null || logisticsInfo.isEmpty()){
            return -1;
        }
        logisticsInfo.setReplyTime(TimeUtil.getDateTime(1));
        logisticsInfo.setLogisticsType(2);
        return logisticsInfoMapper.updateByPrimaryKeySelective(logisticsInfo);
    }

    @Override
    public int receive(Integer logisticsId) {
        if (logisticsId == null || logisticsId <1){
            return -1;
        }
        return logisticsInfoMapper.receiveByPrimaryKey(logisticsId,TimeUtil.getDateTime(1));
    }

    @Override
    public String findInfo(String companyCode ,String logisticsTag) {
        if (logisticsTag == null || companyCode == null|| "".equals(logisticsTag)||"".equals(companyCode)){
            return null;
        }else {
            try {
                String result = LogisticsUtil.getOrderTracesByJson(companyCode,logisticsTag);
                ObjectMapper mapper = new ObjectMapper();
                HashMap map = mapper.readValue(result, HashMap.class);
                String logisticsBody = (String) map.get("Traces");
                logisticsInfoMapper.updateBodyByTag(logisticsTag,logisticsBody);
                return logisticsBody;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
