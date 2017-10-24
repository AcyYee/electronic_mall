package com.sunwuo.electronic_mall.entity;

public class CommodityInfo {

    private Integer commodityId;

    private String commodityName;

    private String createTime;

    private Integer sortNumber;

    private String commodityPrice;

    private Integer categoryTwoId;

    private String imagePath;

    private String knowMore;

    private Integer isDelete;

    private String commodityRemark;

    private Double commodityDiscount;

    private String commodityValues;

    private Integer isDiscount;

    private Integer isSales;

    private Integer isActivity;

    private Integer commodityType;

    private Integer storeId;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getKnowMore() {
        return knowMore;
    }

    public void setKnowMore(String knowMore) {
        this.knowMore = knowMore;
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice == null ? null : commodityPrice.trim();
    }

    public Integer getCategoryTwoId() {
        return categoryTwoId;
    }

    public void setCategoryTwoId(Integer categoryTwoId) {
        this.categoryTwoId = categoryTwoId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCommodityRemark() {
        return commodityRemark;
    }

    public void setCommodityRemark(String commodityRemark) {
        this.commodityRemark = commodityRemark == null ? null : commodityRemark.trim();
    }

    public Double getCommodityDiscount() {
        return commodityDiscount;
    }

    public void setCommodityDiscount(Double commodityDiscount) {
        this.commodityDiscount = commodityDiscount;
    }

    public String getCommodityValues() {
        return commodityValues;
    }

    public void setCommodityValues(String commodityValues) {
        this.commodityValues = commodityValues == null ? null : commodityValues.trim();
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getIsSales() {
        return isSales;
    }

    public void setIsSales(Integer isSales) {
        this.isSales = isSales;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }

    public Integer getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Integer isActivity) {
        this.isActivity = isActivity;
    }

    public boolean isEmpty() {
        return commodityType == null && knowMore == null ||commodityName == null || commodityPrice == null || categoryTwoId==null || imagePath == null ||  storeId == null ;
    }

    @Override
    public String toString() {
        return "CommodityInfo{" +
                "commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", sortNumber=" + sortNumber +
                ", commodityPrice='" + commodityPrice + '\'' +
                ", categoryTwoId=" + categoryTwoId +
                ", imagePath='" + imagePath + '\'' +
                ", knowMore='" + knowMore + '\'' +
                ", isDelete=" + isDelete +
                ", commodityRemark='" + commodityRemark + '\'' +
                ", commodityDiscount=" + commodityDiscount +
                ", commodityValues='" + commodityValues + '\'' +
                ", isDiscount=" + isDiscount +
                ", storeId=" + storeId +
                '}';
    }

}