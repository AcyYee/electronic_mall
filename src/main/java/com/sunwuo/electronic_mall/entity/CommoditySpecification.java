package com.sunwuo.electronic_mall.entity;

/**
 * @author acy 屋大维
 */
public class CommoditySpecification {

    private Integer specificationId;

    private String specificationName;

    private Integer specificationCount;

    private Double specificationPrice;

    private Integer specificationWeight;

    private Double activityPrice;

    private String imagePath;

    private Integer commodityId;

    private String createTime;

    private Integer sortNumber;

    private Integer isDelete;

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName == null ? null : specificationName.trim();
    }

    public Integer getSpecificationCount() {
        return specificationCount;
    }

    public void setSpecificationCount(Integer specificationCount) {
        this.specificationCount = specificationCount;
    }

    public Double getSpecificationPrice() {
        return specificationPrice;
    }

    public void setSpecificationPrice(Double specificationPrice) {
        this.specificationPrice = specificationPrice;
    }

    public Integer getSpecificationWeight() {
        return specificationWeight;
    }

    public void setSpecificationWeight(Integer specificationWeight) {
        this.specificationWeight = specificationWeight;
    }

    public Double getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Double activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "CommoditySpecification{" +
                "specificationId=" + specificationId +
                ", specificationName='" + specificationName + '\'' +
                ", specificationCount=" + specificationCount +
                ", specificationPrice=" + specificationPrice +
                ", specificationWeight=" + specificationWeight +
                ", activityPrice=" + activityPrice +
                ", imagePath='" + imagePath + '\'' +
                ", commodityId=" + commodityId +
                ", createTime='" + createTime + '\'' +
                ", sortNumber=" + sortNumber +
                ", isDelete=" + isDelete +
                '}';
    }

    public boolean notEmpty() {
        return specificationName==null || specificationPrice == null ||  commodityId == null || specificationWeight == null;
    }
}