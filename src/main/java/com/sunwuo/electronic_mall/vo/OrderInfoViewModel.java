package com.sunwuo.electronic_mall.vo;

import java.util.List;

/**
 * @author acy 屋大维
 */
public class OrderInfoViewModel {

    private Integer orderId;

    private String orderTag;

    private Double mailPrice;

    private Double payPrice;

    private String createTime;

    private String payTime;

    private Integer orderType;

    private String wxAddressInfo;

    private Integer storeId;

    private Integer modelId;

    private Integer logisticsWeight;

    private String orderRemark;

    private Integer logisticsId;

    private String logisticsTag;

    private String updateTime;

    private String replyTime;

    private String receiveTime;

    private Integer logisticsType;

    private Integer companyId;

    private String companyName;

    private String companyCode;

    private List<OrderItemModel> orderItemModels;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderTag() {
        return orderTag;
    }

    public void setOrderTag(String orderTag) {
        this.orderTag = orderTag;
    }

    public Double getMailPrice() {
        return mailPrice;
    }

    public void setMailPrice(Double mailPrice) {
        this.mailPrice = mailPrice;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getWxAddressInfo() {
        return wxAddressInfo;
    }

    public void setWxAddressInfo(String wxAddressInfo) {
        this.wxAddressInfo = wxAddressInfo;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
        this.logisticsTag = logisticsTag;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public List<OrderItemModel> getOrderItemModels() {
        return orderItemModels;
    }

    public void setOrderItemModels(List<OrderItemModel> orderItemModels) {
        this.orderItemModels = orderItemModels;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    @Override
    public String toString() {
        return "OrderInfoViewModel{" +
                "orderId=" + orderId +
                ", orderTag='" + orderTag + '\'' +
                ", mailPrice=" + mailPrice +
                ", payPrice=" + payPrice +
                ", createTime='" + createTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", orderType=" + orderType +
                ", wxAddressInfo='" + wxAddressInfo + '\'' +
                ", storeId=" + storeId +
                ", modelId=" + modelId +
                ", logisticsWeight=" + logisticsWeight +
                ", orderRemark='" + orderRemark + '\'' +
                ", logisticsId=" + logisticsId +
                ", logisticsTag='" + logisticsTag + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", replyTime='" + replyTime + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", logisticsType=" + logisticsType +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", orderItemModels=" + orderItemModels +
                '}';
    }
}
