package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class ShareChargerModel {
    private Long id;

    private Long deviceId;

    private Integer chargerTypeId;

    private String chargerTypeName;

    private Long pwdIndex;

    private String pwds;

    private Long useCountTimesYesterday;

    private Long totalAmountYesterday;

    private String remark;

    private Long agents1Id;

    private Long agents2Id;

    private Long agents3Id;

    private Long pwdMode;

    private String refactorIdx;

    private Long createId;

    private Date createDateTime;

    private Date updateDateTime;

    private Date lastUseTime;

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

    public Integer getChargerTypeId() {
        return chargerTypeId;
    }

    public void setChargerTypeId(Integer chargerTypeId) {
        this.chargerTypeId = chargerTypeId;
    }

    public String getChargerTypeName() {
        return chargerTypeName;
    }

    public void setChargerTypeName(String chargerTypeName) {
        this.chargerTypeName = chargerTypeName == null ? null : chargerTypeName.trim();
    }

    public Long getPwdIndex() {
        return pwdIndex;
    }

    public void setPwdIndex(Long pwdIndex) {
        this.pwdIndex = pwdIndex;
    }

    public String getPwds() {
        return pwds;
    }

    public void setPwds(String pwds) {
        this.pwds = pwds == null ? null : pwds.trim();
    }

    public Long getUseCountTimesYesterday() {
        return useCountTimesYesterday;
    }

    public void setUseCountTimesYesterday(Long useCountTimesYesterday) {
        this.useCountTimesYesterday = useCountTimesYesterday;
    }

    public Long getTotalAmountYesterday() {
        return totalAmountYesterday;
    }

    public void setTotalAmountYesterday(Long totalAmountYesterday) {
        this.totalAmountYesterday = totalAmountYesterday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getAgents1Id() {
        return agents1Id;
    }

    public void setAgents1Id(Long agents1Id) {
        this.agents1Id = agents1Id;
    }

    public Long getAgents2Id() {
        return agents2Id;
    }

    public void setAgents2Id(Long agents2Id) {
        this.agents2Id = agents2Id;
    }

    public Long getAgents3Id() {
        return agents3Id;
    }

    public void setAgents3Id(Long agents3Id) {
        this.agents3Id = agents3Id;
    }

    public Long getPwdMode() {
        return pwdMode;
    }

    public void setPwdMode(Long pwdMode) {
        this.pwdMode = pwdMode;
    }

    public String getRefactorIdx() {
        return refactorIdx;
    }

    public void setRefactorIdx(String refactorIdx) {
        this.refactorIdx = refactorIdx == null ? null : refactorIdx.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Date getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
    }
}