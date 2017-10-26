package com.sunwuo.electronic_mall.vo;

public class StoreInfoCountModel {

    private String storeName;

    private String mchId;

    private String appId;

    private String storeKey;

    private String payKey;

    private Double storeDiscount;

    private Double minMailPrice;

    private Double lessMailPrice;

    private Integer isDiscount;

    private Integer maxWeight;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public Double getStoreDiscount() {
        return storeDiscount;
    }

    public void setStoreDiscount(Double storeDiscount) {
        this.storeDiscount = storeDiscount;
    }

    public Double getMinMailPrice() {
        return minMailPrice;
    }

    public void setMinMailPrice(Double minMailPrice) {
        this.minMailPrice = minMailPrice;
    }

    public Double getLessMailPrice() {
        return lessMailPrice;
    }

    public void setLessMailPrice(Double lessMailPrice) {
        this.lessMailPrice = lessMailPrice;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(String storeKey) {
        this.storeKey = storeKey;
    }
}
