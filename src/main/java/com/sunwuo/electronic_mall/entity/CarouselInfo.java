package com.sunwuo.electronic_mall.entity;

public class CarouselInfo {

    private Integer carouselId;

    private String imagePath;

    private Integer storeId;

    private String createTime;

    private Integer sortNumber;

    private String commodityName;

    private Integer commodityId;

    private Integer isDelete;

    private Integer carouselType;

    private String carouselPath;

    public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isEmpty(){
        return imagePath == null || (storeId == null && commodityId==null);
    }

    public Integer getCarouselType() {
        return carouselType;
    }

    public void setCarouselType(Integer carouselType) {
        this.carouselType = carouselType;
    }

    public String getCarouselPath() {
        return carouselPath;
    }

    public void setCarouselPath(String carouselPath) {
        this.carouselPath = carouselPath;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}