package com.sunwuo.electronic_mall.service.impl;

import com.sunwuo.electronic_mall.dao.mybatis.StoreProjectMapper;
import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.service.StoreProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("storeProjectService")
public class StoreProjectServiceImpl implements StoreProjectService {

    private final StoreProjectMapper storeProjectMapper;

    @Autowired
    public StoreProjectServiceImpl(StoreProjectMapper storeProjectMapper) {
        this.storeProjectMapper = storeProjectMapper;
    }

    @Override
    public int updateProject(StoreProject storeProject) {
        if (storeProject == null || storeProject.getProgramId() == null || storeProject.isEmpty()) {
            return -1;
        }
        return storeProjectMapper.updateProject(storeProject);
    }
}
