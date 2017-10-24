package com.sunwuo.electronic_mall.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfo {
    private Integer userId;

    private String createTime;

    private String wxOpenid;

    private Integer isDesign;

    private Integer isShop;

    private Integer isRegist;

    private Byte flagDesign; //0未申请      1普通待付款  2普通通过  3加V待付款  4加V通过

    private Byte flagShop; //0未申请   1待付款  2待审核  3通过

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public Integer getIsDesign() {
        return isDesign;
    }

    public void setIsDesign(Integer isDesign) {
        this.isDesign = isDesign;
    }

    public Integer getIsShop() {
        return isShop;
    }

    public void setIsShop(Integer isShop) {
        this.isShop = isShop;
    }

    public Integer getIsRegist() {
        return isRegist;
    }

    public void setIsRegist(Integer isRegist) {
        this.isRegist = isRegist;
    }

    public Byte getFlagDesign() {
        return flagDesign;
    }

    public void setFlagDesign(Byte flagDesign) {
        this.flagDesign = flagDesign;
    }

    public Byte getFlagShop() {
        return flagShop;
    }

    public void setFlagShop(Byte flagShop) {
        this.flagShop = flagShop;
    }
}