package com.stylefeng.guns.sharecore.modular.system.model;


import java.util.Date;

public class CustCanwithdrawChargeInfoModel {
    private Long tradeId;

    private Long custNo;

    private Integer channelType;

    private Integer reconStatus;

    private Long outTradeNo;

    private Date tradeTime;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getReconStatus() {
        return reconStatus;
    }

    public void setReconStatus(Integer reconStatus) {
        this.reconStatus = reconStatus;
    }

    public Long getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(Long outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }
}