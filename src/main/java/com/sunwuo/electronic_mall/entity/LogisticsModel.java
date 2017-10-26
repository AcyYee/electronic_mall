package com.sunwuo.electronic_mall.entity;

/**
 *@author acy 屋大维
 */
public class LogisticsModel {

    private Integer modelId;

    private String deliveryArea;

    private Integer freeWeight;

    private String provinceCode;

    private Integer beyondWeight;

    private Double minPrice;

    private Double beyondPrice;

    private Integer isDelete;

    private String createTime;

    private Integer companyId;

    private String companyName;

    private Integer sortNumber;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea == null ? null : deliveryArea.trim();
    }

    public Integer getFreeWeight() {
        return freeWeight;
    }

    public void setFreeWeight(Integer freeWeight) {
        this.freeWeight = freeWeight;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getBeyondWeight() {
        return beyondWeight;
    }

    public void setBeyondWeight(Integer beyondWeight) {
        this.beyondWeight = beyondWeight;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getBeyondPrice() {
        return beyondPrice;
    }

    public void setBeyondPrice(Double beyondPrice) {
        this.beyondPrice = beyondPrice;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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
        this.companyName = companyName;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public boolean notEmpty() {
        return  deliveryArea == null || beyondPrice == null || beyondWeight == null || freeWeight == null || minPrice == null ;
    }

    public boolean isEmpty() {
        return  deliveryArea == null && beyondPrice == null && beyondWeight == null && freeWeight == null && minPrice == null ;
    }

}