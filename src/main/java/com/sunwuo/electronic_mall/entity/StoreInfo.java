package com.sunwuo.electronic_mall.entity;

/**
 * @author acy 屋大维
 */
public class StoreInfo {

    private Integer storeId;

    private String storeName;

    private String createTime;

    private Double storeDiscount;

    private Integer isDiscount;

    private Integer isDelete;

    private Double storeTurnover;

    private String storeLocation;

    private String backgroundImage;

    private String storeImage;

    private Integer maxWeight;

    private Double minMailPrice;

    private Double lessMailPrice;

    private Integer isBusiness;

    private String storeRemark;
    
    private String storeAdmin;
    
    private String storeKey;

    public String getStoreAdmin() {
		return storeAdmin;
	}

	public void setStoreAdmin(String storeAdmin) {
		this.storeAdmin = storeAdmin;
	}

	public String getStoreKey() {
		return storeKey;
	}

	public void setStoreKey(String storeKey) {
		this.storeKey = storeKey;
	}

	public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getStoreDiscount() {
        return storeDiscount;
    }

    public void setStoreDiscount(Double storeDiscount) {
        this.storeDiscount = storeDiscount;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Double getStoreTurnover() {
        return storeTurnover;
    }

    public void setStoreTurnover(Double storeTurnover) {
        this.storeTurnover = storeTurnover;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation == null ? null : storeLocation.trim();
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage == null ? null : backgroundImage.trim();
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage == null ? null : storeImage.trim();
    }

    public Integer getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(Integer isBusiness) {
        this.isBusiness = isBusiness;
    }

    public String getStoreRemark() {
        return storeRemark;
    }

    public void setStoreRemark(String storeRemark) {
        this.storeRemark = storeRemark == null ? null : storeRemark.trim();
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
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

    public boolean isEmpty() {
        return storeName == null;
    }

    public boolean notEmpty() {
        return storeName == null;
    }
}