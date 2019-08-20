package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class MerWithdrawMetadataModel {
    private Long id;

    private String name;

    private Byte merWithdrawType;

    private String merWithdrawTypeCn;

    private Byte maxWithdrawTimes;

    private Byte canWithdrawStartDay;

    private Byte canWithdrawEndDay;

    private Byte isDefault;

    private Byte payWay;

    private String payWayCn;

    private Date createTime;

    private Date updateTime;

    private Byte isRealTime;

    private String isRealTimeCn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getMerWithdrawType() {
        return merWithdrawType;
    }

    public void setMerWithdrawType(Byte merWithdrawType) {
        this.merWithdrawType = merWithdrawType;
    }

    public String getMerWithdrawTypeCn() {
        return merWithdrawTypeCn;
    }

    public void setMerWithdrawTypeCn(String merWithdrawTypeCn) {
        this.merWithdrawTypeCn = merWithdrawTypeCn == null ? null : merWithdrawTypeCn.trim();
    }

    public Byte getMaxWithdrawTimes() {
        return maxWithdrawTimes;
    }

    public void setMaxWithdrawTimes(Byte maxWithdrawTimes) {
        this.maxWithdrawTimes = maxWithdrawTimes;
    }

    public Byte getCanWithdrawStartDay() {
        return canWithdrawStartDay;
    }

    public void setCanWithdrawStartDay(Byte canWithdrawStartDay) {
        this.canWithdrawStartDay = canWithdrawStartDay;
    }

    public Byte getCanWithdrawEndDay() {
        return canWithdrawEndDay;
    }

    public void setCanWithdrawEndDay(Byte canWithdrawEndDay) {
        this.canWithdrawEndDay = canWithdrawEndDay;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Byte getPayWay() {
        return payWay;
    }

    public void setPayWay(Byte payWay) {
        this.payWay = payWay;
    }

    public String getPayWayCn() {
        return payWayCn;
    }

    public void setPayWayCn(String payWayCn) {
        this.payWayCn = payWayCn == null ? null : payWayCn.trim();
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

    public Byte getIsRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(Byte isRealTime) {
        this.isRealTime = isRealTime;
    }

    public String getIsRealTimeCn() {
        return isRealTimeCn;
    }

    public void setIsRealTimeCn(String isRealTimeCn) {
        this.isRealTimeCn = isRealTimeCn == null ? null : isRealTimeCn.trim();
    }
}