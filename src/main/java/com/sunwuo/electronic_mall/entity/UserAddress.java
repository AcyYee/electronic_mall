package com.sunwuo.electronic_mall.entity;

/**
 * @author acy 屋大维
 */
public class UserAddress {

    private Integer addressId;

    private Integer userId;

    private Integer isDelete;

    private String provinceCode;

    private String addressContent;

    private String addressPhone;

    private String addressPerson;

    private String addressMunicipal;

    private Integer addressType;

    private String addressProvince;

    private String addressCounty;

    private Integer sortNumber;

    private String createTime;

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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty == null ? null : addressCounty.trim();
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean notEmpty() {
        return addressContent == null || userId == null || addressPhone == null
                || addressPerson == null || addressMunicipal == null|| addressType == null
                || addressProvince == null || addressCounty == null;
    }

    public boolean isEmpty() {
        return addressContent == null && userId == null && addressPhone == null
                && addressPerson == null && addressMunicipal == null && addressType == null
                && addressProvince == null && addressCounty == null;
    }
}