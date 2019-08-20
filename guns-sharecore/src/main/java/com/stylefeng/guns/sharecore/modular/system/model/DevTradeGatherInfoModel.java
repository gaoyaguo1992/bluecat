package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class DevTradeGatherInfoModel {
    private Long id;

    private Long deviceId;

    private BigDecimal hisTradeAmount;

    private Integer hisTradeCount;

    private BigDecimal yesTradeAmount;

    private Integer yesTradeCount;

    private BigDecimal unitPrice;

    private Date createTime;

    private Date updateTime;

    private Date countTime;

    private Byte isRepair;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public BigDecimal getHisTradeAmount() {
        return hisTradeAmount;
    }

    public void setHisTradeAmount(BigDecimal hisTradeAmount) {
        this.hisTradeAmount = hisTradeAmount;
    }

    public Integer getHisTradeCount() {
        return hisTradeCount;
    }

    public void setHisTradeCount(Integer hisTradeCount) {
        this.hisTradeCount = hisTradeCount;
    }

    public BigDecimal getYesTradeAmount() {
        return yesTradeAmount;
    }

    public void setYesTradeAmount(BigDecimal yesTradeAmount) {
        this.yesTradeAmount = yesTradeAmount;
    }

    public Integer getYesTradeCount() {
        return yesTradeCount;
    }

    public void setYesTradeCount(Integer yesTradeCount) {
        this.yesTradeCount = yesTradeCount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCountTime() {
        return countTime;
    }

    public void setCountTime(Date countTime) {
        this.countTime = countTime;
    }

    public Byte getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Byte isRepair) {
        this.isRepair = isRepair;
    }
}