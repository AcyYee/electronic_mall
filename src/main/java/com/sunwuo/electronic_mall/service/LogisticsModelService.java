package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.LogisticsModel;
import com.sunwuo.electronic_mall.vo.PageData;

public interface LogisticsModelService {

    int addModel(LogisticsModel logisticsModel);

    int updateModel(LogisticsModel logisticsModel);

    int deleteModels(Integer[] modelIds);

    LogisticsModel findModel(Integer modelId);

    PageData findModels(Integer companyId, Integer storeId, Integer storeType, Integer pageIndex, Integer pageSize);

    int topModel(Integer modelId, Integer sortNumber);

    int sortModel(Integer modelId, Integer sortNumber);
}
