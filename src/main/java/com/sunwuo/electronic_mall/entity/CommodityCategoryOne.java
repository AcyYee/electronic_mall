package com.sunwuo.electronic_mall.entity;

public class CommodityCategoryOne {

    private Integer categoryOneId;

    private String categoryOneName;

    private String createTime;

    private String subName;

    private Integer sortNumber;

    private String imagePath;

    private String iconPath;

    private Integer storeId;

    private Integer isDelete;

    public Integer getCategoryOneId() {
        return categoryOneId;
    }

    public void setCategoryOneId(Integer categoryOneId) {
        this.categoryOneId = categoryOneId;
    }

    public String getCategoryOneName() {
        return categoryOneName;
    }

    public void setCategoryOneName(String categoryOneName) {
        this.categoryOneName = categoryOneName == null ? null : categoryOneName.trim();
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isEmpty() {
        return categoryOneName == null|| imagePath == null || iconPath == null;
    }

    @Override
    public String toString() {
        return "CommodityCategoryOne{" +
                "categoryOneId=" + categoryOneId +
                ", categoryOneName='" + categoryOneName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", storeId=" + storeId +
                ", isDelete=" + isDelete +
                '}';
    }
}