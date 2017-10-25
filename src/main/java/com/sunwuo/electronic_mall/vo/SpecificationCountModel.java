package com.sunwuo.electronic_mall.vo;

public class SpecificationCountModel {

    private Integer specificationId;

    private Double specificationPrice;

    private Double activityPrice;

    private Integer isDiscount;

    private Integer isActivity;

    private Double commodityDiscount;

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public Double getSpecificationPrice() {
        return specificationPrice;
    }

    public void setSpecificationPrice(Double specificationPrice) {
        this.specificationPrice = specificationPrice;
    }

    public Double getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Double activityPrice) {
        this.activityPrice = activityPrice;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Integer isActivity) {
        this.isActivity = isActivity;
    }

    public Double getCommodityDiscount() {
        return commodityDiscount;
    }

    public void setCommodityDiscount(Double commodityDiscount) {
        this.commodityDiscount = commodityDiscount;
    }
}
