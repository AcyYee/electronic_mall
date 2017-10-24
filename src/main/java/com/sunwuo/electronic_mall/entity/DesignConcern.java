package com.sunwuo.electronic_mall.entity;

public class DesignConcern {

	private String id;
	private Integer designId;
	private Integer[] userId;
	public DesignConcern(Integer id2) {
		this.designId=id2;
	}
	public DesignConcern() {
	}
	public DesignConcern(Integer id2, Integer[] userId2) {
		this.designId=id2;
		this.userId=userId2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDesignId() {
		return designId;
	}
	public void setDesignId(Integer designId) {
		this.designId = designId;
	}
	public Integer[] getUserId() {
		return userId;
	}
	public void setUserId(Integer[] userId) {
		this.userId = userId;
	}
	
}
