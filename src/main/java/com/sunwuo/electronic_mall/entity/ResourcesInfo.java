package com.sunwuo.electronic_mall.entity;

public class ResourcesInfo {

    private Integer resourcesId;

    private String imagePath;

    private String resourcesPath;

    private String createTime;

    private Integer categoryId;

    private Integer sortNumber;

    private String resourcesTitle;

    private String resourcesRemark;

    private Integer resourcesType;

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath == null ? null : resourcesPath.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getResourcesTitle() {
        return resourcesTitle;
    }

    public void setResourcesTitle(String resourcesTitle) {
        this.resourcesTitle = resourcesTitle == null ? null : resourcesTitle.trim();
    }

    public String getResourcesRemark() {
        return resourcesRemark;
    }

    public void setResourcesRemark(String resourcesRemark) {
        this.resourcesRemark = resourcesRemark == null ? null : resourcesRemark.trim();
    }

    public Integer getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(Integer resourcesType) {
        this.resourcesType = resourcesType;
    }

    public boolean notEmpty() {
        return imagePath == null || categoryId == null || resourcesPath == null || resourcesTitle == null;
    }

    public boolean isEmpty() {
        return imagePath == null && categoryId == null && resourcesPath == null && resourcesTitle == null;
    }
}