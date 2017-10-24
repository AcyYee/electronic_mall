package com.sunwuo.electronic_mall.entity;

public class CommodityCategoryTwo {
    private Integer categoryTwoId;

    private String categoryTwoName;

    private Integer categoryOneId;

    private String createTime;

    private String subName;

    private Integer sortNumber;

    private String imagePath;

    private String iconPath;

    private Integer isDelete;

    private Integer shopId;

    private Integer categoryTwoType;

    public Integer getCategoryTwoId() {
        return categoryTwoId;
    }

    public void setCategoryTwoId(Integer categoryTwoId) {
        this.categoryTwoId = categoryTwoId;
    }

    public String getCategoryTwoName() {
        return categoryTwoName;
    }

    public void setCategoryTwoName(String categoryTwoName) {
        this.categoryTwoName = categoryTwoName == null ? null : categoryTwoName.trim();
    }

    public Integer getCategoryOneId() {
        return categoryOneId;
    }

    public void setCategoryOneId(Integer categoryOneId) {
        this.categoryOneId = categoryOneId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath == null ? null : iconPath.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCategoryTwoType() {
        return categoryTwoType;
    }

    public void setCategoryTwoType(Integer categoryTwoType) {
        this.categoryTwoType = categoryTwoType;
    }

    public boolean isEmpty() {
        return categoryTwoType == null && categoryTwoName == null || categoryOneId == null ;
    }

    @Override
    public String toString() {
        return "CommodityCategoryTwo{" +
                "categoryTwoId=" + categoryTwoId +
                ", categoryTwoName='" + categoryTwoName + '\'' +
                ", categoryOneId=" + categoryOneId +
                ", imagePath='" + imagePath + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", isDelete=" + isDelete +
                ", shopId=" + shopId +
                ", categoryTwoType=" + categoryTwoType +
                '}';
    }
}