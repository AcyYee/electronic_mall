package com.sunwuo.electronic_mall.service;

import com.sunwuo.electronic_mall.entity.AdminInfo;

public interface AdminInfoService {

    AdminInfo login(String adminName, String adminPassword);

    void testTX();
}
