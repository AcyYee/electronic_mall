package com.sunwuo.electronic_mall.entity;

public class LogisticsCompany {

    private Integer companyId;

    private String companyName;

    private String companyPhone;

    private String createTime;

    private String companyAddress;

    private Integer isDelete;

    private String companyPrincipal;

    private Integer sortNumber;

    private Integer storeId;

    private Integer storeType;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCompanyPrincipal() {
        return companyPrincipal;
    }

    public void setCompanyPrincipal(String companyPrincipal) {
        this.companyPrincipal = companyPrincipal == null ? null : companyPrincipal.trim();
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public boolean notEmpty(){
        return companyName == null || companyAddress == null || companyPhone == null || companyPrincipal == null || storeId == null || storeType == null;
    }

    public boolean isEmpty(){
        return companyName == null && companyAddress == null && companyPhone == null && companyPrincipal == null && storeId == null && storeType == null;
    }


    @Override
    public String toString() {
        return "LogisticsCompany{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", createTime='" + createTime + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", isDelete=" + isDelete +
                ", companyPrincipal='" + companyPrincipal + '\'' +
                ", sortNumber=" + sortNumber +
                ", storeId=" + storeId +
                ", storeType=" + storeType +
                '}';
    }
}