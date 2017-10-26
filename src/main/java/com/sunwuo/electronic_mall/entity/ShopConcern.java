package com.sunwuo.electronic_mall.entity;

public class ShopConcern {

	private String id;

	private Integer shopId;

	private Integer[] userId;

	public ShopConcern(Integer id2) {
		this.shopId=id2;
	}

	public ShopConcern() {
	}

	public ShopConcern(Integer id2, Integer[] userId2) {
		this.shopId=id2;
		this.userId=userId2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer[] getUserId() {
		return userId;
	}

	public void setUserId(Integer[] userId) {
		this.userId = userId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
}
