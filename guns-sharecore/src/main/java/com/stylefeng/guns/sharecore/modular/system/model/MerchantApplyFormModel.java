package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class MerchantApplyFormModel {
    private Long id;

    private String merchantName;

    private String personName;

    private String telNo;

    private String province;

    private String city;

    private String zone;

    private String agentsZone;

    private String throwDevChannel;

    private Long applyType;

    private String applyTypeCn;

    private Long doStatus;

    private String doStatusCn;

    private Long custId;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public String getAgentsZone() {
        return agentsZone;
    }

    public void setAgentsZone(String agentsZone) {
        this.agentsZone = agentsZone == null ? null : agentsZone.trim();
    }

    public String getThrowDevChannel() {
        return throwDevChannel;
    }

    public void setThrowDevChannel(String throwDevChannel) {
        this.throwDevChannel = throwDevChannel == null ? null : throwDevChannel.trim();
    }

    public Long getApplyType() {
        return applyType;
    }

    public void setApplyType(Long applyType) {
        this.applyType = applyType;
    }

    public String getApplyTypeCn() {
        return applyTypeCn;
    }

    public void setApplyTypeCn(String applyTypeCn) {
        this.applyTypeCn = applyTypeCn == null ? null : applyTypeCn.trim();
    }

    public Long getDoStatus() {
        return doStatus;
    }

    public void setDoStatus(Long doStatus) {
        this.doStatus = doStatus;
    }

    public String getDoStatusCn() {
        return doStatusCn;
    }

    public void setDoStatusCn(String doStatusCn) {
        this.doStatusCn = doStatusCn == null ? null : doStatusCn.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}