package com.sunwuo.electronic_mall.entity;

public class StoreReduced {

    private Integer reducedId;

    private Double shouldCount;

    private Double lessCount;

    private Integer isDelete;

    private Integer storeId;

    public Integer getReducedId() {
        return reducedId;
    }

    public void setReducedId(Integer reducedId) {
        this.reducedId = reducedId;
    }

    public Double getShouldCount() {
        return shouldCount;
    }

    public void setShouldCount(Double shouldCount) {
        this.shouldCount = shouldCount;
    }

    public Double getLessCount() {
        return lessCount;
    }

    public void setLessCount(Double lessCount) {
        this.lessCount = lessCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "StoreReduced{" +
                "reducedId=" + reducedId +
                ", shouldCount=" + shouldCount +
                ", lessCount=" + lessCount +
                ", isDelete=" + isDelete +
                ", storeId=" + storeId +
                '}';
    }

}