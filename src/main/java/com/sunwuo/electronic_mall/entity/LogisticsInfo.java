package com.sunwuo.electronic_mall.entity;

public class LogisticsInfo {

    private Integer logisticsId;

    private Integer modelId;

    private Integer orderId;

    private String logisticsTag;

    private String createTime;

    private Double mailPrice;

    private String logisticsBody;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getLogisticsTag() {
        return logisticsTag;
    }

    public void setLogisticsTag(String logisticsTag) {
        this.logisticsTag = logisticsTag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getMailPrice() {
        return mailPrice;
    }

    public void setMailPrice(Double mailPrice) {
        this.mailPrice = mailPrice;
    }

    public String getLogisticsBody() {
        return logisticsBody;
    }

    public void setLogisticsBody(String logisticsBody) {
        this.logisticsBody = logisticsBody;
    }

}