package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.LogisticsModelMapper;
import com.sunwuo.electronic_mall.entity.LogisticsModel;
import com.sunwuo.electronic_mall.service.LogisticsModelService;
import com.sunwuo.electronic_mall.util.TimeUtil;
import com.sunwuo.electronic_mall.vo.PageData;
import com.sunwuo.electronic_mall.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("logisticsModelService")
public class LogisticsModelServiceImpl implements LogisticsModelService {

    @Autowired
    private LogisticsModelMapper logisticsModelDao;

    @Override
    public int addModel(LogisticsModel logisticsModel) {
        if (logisticsModel == null || logisticsModel.notEmpty())
            return -1;
        logisticsModel.setCreateTime(TimeUtil.getDateTime(1));
        if (logisticsModelDao.insertSelective(logisticsModel) > 0){
            return logisticsModelDao.updateSortNumber(logisticsModel.getModelId(),logisticsModel.getModelId());
        }else{
            return 0;
        }
    }

    @Override
    public PageData findModels(Integer companyId, Integer storeId, Integer storeType, Integer pageIndex, Integer pageSize) {
        if ((storeType == null || storeId == null || storeId < 1) && (companyId == null ||companyId <1))
            return null;
        Map<String,Object> map = new HashMap<>();
        PageData pageData = new PageData();
        if (companyId != null)
            map.put("companyId", storeType);
        if (storeId != null) {
            map.put("storeId", storeId);
            map.put("storeType", storeType);
        }
        if (pageIndex != null) {
            PageModel pageModel = new PageModel();
            pageModel.setPageSize(pageSize);
            pageModel.setPageIndex(pageIndex);
            int recordCount = logisticsModelDao.findModelCount(map);
            pageModel.setRecordCount(recordCount);
            map.put("pageModel",pageModel);
            pageData.setPageModel(pageModel);
            pageData.setModelData(logisticsModelDao.findModels(map));
        }
        pageData.setModelData(logisticsModelDao.findModels(map));
        return pageData;
    }

    @Override
    public int updateModel(LogisticsModel logisticsModel) {
        if (logisticsModel == null || logisticsModel.isEmpty())
            return -1;
        else
            return logisticsModelDao.updateByPrimaryKeySelective(logisticsModel);
    }

    @Override
    public int deleteModels(Integer[] modelIds) {
        if (modelIds == null || modelIds.length<=0)
            return -1;
        return logisticsModelDao.deleteByIds(modelIds);
    }

    @Override
    public LogisticsModel findModel(Integer modelId) {
        if (modelId == null || modelId<=0)
            return null;
        else
            return logisticsModelDao.selectByPrimaryKey(modelId);
    }

    @Override
    public int topModel(Integer modelId, Integer sortNumber) {
        if (modelId == null || sortNumber == null){
            return -1;
        }
        logisticsModelDao.updateSortNumbers(sortNumber);
        return logisticsModelDao.updateSortNumber(modelId,sortNumber);
    }

    @Override
    public int sortModel(Integer modelId, Integer sortNumber) {
        if (modelId == null || sortNumber == null){
            return -1;
        }
        return logisticsModelDao.updateSortNumber(modelId,sortNumber);
    }
}
