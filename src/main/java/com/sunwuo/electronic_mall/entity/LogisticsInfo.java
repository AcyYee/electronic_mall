package com.sunwuo.electronic_mall.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LogisticsInfo {
    private Integer logistics;

    private Integer modelId;

    private Integer logisticsWeight;

    private String logisticsTag;

    private String createTime;

    private String isDelete;

    private String updateTime;

    private Double mailPrice;

    private String logisticsBody;

    private String replyTime;

    private String receiveTime;

    private Integer logisticsType;

    public Integer getLogistics() {
        return logistics;
    }

    public void setLogistics(Integer logistics) {
        this.logistics = logistics;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getLogisticsWeight() {
        return logisticsWeight;
    }

    public void setLogisticsWeight(Integer logisticsWeight) {
        this.logisticsWeight = logisticsWeight;
    }

    public String getLogisticsTag() {
        return logisticsTag;
    }

    public void setLogisticsTag(String logisticsTag) {
        this.logisticsTag = logisticsTag == null ? null : logisticsTag.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
        this.logisticsBody = logisticsBody == null ? null : logisticsBody.trim();
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getLogisticsType() {
        return logisticsType;
    }

    public void setLogisticsType(Integer logisticsType) {
        this.logisticsType = logisticsType;
    }
}