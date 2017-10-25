package com.sunwuo.electronic_mall.entity;

import java.util.Date;

public class UserAddress {
    private Integer addressId;

    private Integer userId;

    private Integer isDelete;

    private String addressContent;

    private String addressPhone;

    private String addressPerson;

    private String addressMunicipal;

    private Integer addressType;

    private String addressProvince;

    private String adrressCounty;

    private Integer sortNumber;

    private Date createTime;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getAddressContent() {
        return addressContent;
    }

    public void setAddressContent(String addressContent) {
        this.addressContent = addressContent == null ? null : addressContent.trim();
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone == null ? null : addressPhone.trim();
    }

    public String getAddressPerson() {
        return addressPerson;
    }

    public void setAddressPerson(String addressPerson) {
        this.addressPerson = addressPerson == null ? null : addressPerson.trim();
    }

    public String getAddressMunicipal() {
        return addressMunicipal;
    }

    public void setAddressMunicipal(String addressMunicipal) {
        this.addressMunicipal = addressMunicipal == null ? null : addressMunicipal.trim();
    }

    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince == null ? null : addressProvince.trim();
    }

    public String getAdrressCounty() {
        return adrressCounty;
    }

    public void setAdrressCounty(String adrressCounty) {
        this.adrressCounty = adrressCounty == null ? null : adrressCounty.trim();
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}