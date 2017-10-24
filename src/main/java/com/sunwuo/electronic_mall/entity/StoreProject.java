package com.sunwuo.electronic_mall.entity;

public class StoreProject {

	private Integer programId;

	private String appId;

	private String mchId;

	private String mchSecret;

	private String appSecret;

	private Integer storeId;

	private String createTime;

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchSecret() {
		return mchSecret;
	}

	public void setMchSecret(String mchSecret) {
		this.mchSecret = mchSecret;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
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

	public boolean notEmpty() {
		return appId == null || appSecret == null;
    }

    public boolean isEmpty() {
		return appId == null && appSecret == null;
    }
}
