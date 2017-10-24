package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.entity.StoreProject;
import com.sunwuo.electronic_mall.service.StoreProjectService;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("store/project")
public class StoreProjectController {

    private final StoreProjectService storeProjectService;

    @Autowired
    public StoreProjectController(StoreProjectService storeProjectService) {
        this.storeProjectService = storeProjectService;
    }

    @RequestMapping("update")
    public ResultObject update(StoreProject storeProject){
        return ResultObject.returnResultObject(storeProjectService.updateProject(storeProject),storeProject);
    }

}
