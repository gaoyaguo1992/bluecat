package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShareTradeInfoModel {
    private Long id;

    private Long deviceNo;

    private Long chargerId;

    private Long custId;

    private BigDecimal yfjAmount;

    private BigDecimal tradeAmount;

    private Integer tradeType;

    private String tradeName;

    private Date borrowDatetime;

    private Long useTimeSeconds;

    private Date backDatetime;

    private String tradeStatusName;

    private Integer tradeStatus;

    private BigDecimal xCoordinate;

    private BigDecimal yCoordinate;

    private String benefitStatusName;

    private Integer benefitStatus;

    private Date benefitDatetime;

    private BigDecimal platformAmount;

    private BigDecimal agents1Amount;

    private BigDecimal agents2Amount;

    private BigDecimal agents3Amount;

    private BigDecimal shopkeeperAmount;

    private BigDecimal allianceBusinessAmount;

    private Long backTradeId;

    private String tradeAddress;

    private String tradeZone;

    private String tradeCity;

    private String tradeProvince;

    private Long merchantId;

    private Long agents1Id;

    private Long agents2Id;

    private Long agents3Id;

    private Long shopkeeperId;

    private Long allianceBusinessId;

    private Long createId;

    private Date createDatetime;

    private Date updateDatetime;

    private Integer settleAccountsStatus;

    private String remark;

    private Integer tradeChannel;

    private String tradeChannelName;

    private Integer finishFlag;

    private String finishFlagName;

    private Integer platFormRefund;

    private BigDecimal refundAmount;

    private BigDecimal calTradeAmount;

    private Long resourceTradeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Long deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Long getChargerId() {
        return chargerId;
    }

    public void setChargerId(Long chargerId) {
        this.chargerId = chargerId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public BigDecimal getYfjAmount() {
        return yfjAmount;
    }

    public void setYfjAmount(BigDecimal yfjAmount) {
        this.yfjAmount = yfjAmount;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName == null ? null : tradeName.trim();
    }

    public Date getBorrowDatetime() {
        return borrowDatetime;
    }

    public void setBorrowDatetime(Date borrowDatetime) {
        this.borrowDatetime = borrowDatetime;
    }

    public Long getUseTimeSeconds() {
        return useTimeSeconds;
    }

    public void setUseTimeSeconds(Long useTimeSeconds) {
        this.useTimeSeconds = useTimeSeconds;
    }

    public Date getBackDatetime() {
        return backDatetime;
    }

    public void setBackDatetime(Date backDatetime) {
        this.backDatetime = backDatetime;
    }

    public String getTradeStatusName() {
        return tradeStatusName;
    }

    public void setTradeStatusName(String tradeStatusName) {
        this.tradeStatusName = tradeStatusName == null ? null : tradeStatusName.trim();
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public BigDecimal getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(BigDecimal xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public BigDecimal getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(BigDecimal yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getBenefitStatusName() {
        return benefitStatusName;
    }

    public void setBenefitStatusName(String benefitStatusName) {
        this.benefitStatusName = benefitStatusName == null ? null : benefitStatusName.trim();
    }

    public Integer getBenefitStatus() {
        return benefitStatus;
    }

    public void setBenefitStatus(Integer benefitStatus) {
        this.benefitStatus = benefitStatus;
    }

    public Date getBenefitDatetime() {
        return benefitDatetime;
    }

    public void setBenefitDatetime(Date benefitDatetime) {
        this.benefitDatetime = benefitDatetime;
    }

    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
    }

    public BigDecimal getAgents1Amount() {
        return agents1Amount;
    }

    public void setAgents1Amount(BigDecimal agents1Amount) {
        this.agents1Amount = agents1Amount;
    }

    public BigDecimal getAgents2Amount() {
        return agents2Amount;
    }

    public void setAgents2Amount(BigDecimal agents2Amount) {
        this.agents2Amount = agents2Amount;
    }

    public BigDecimal getAgents3Amount() {
        return agents3Amount;
    }

    public void setAgents3Amount(BigDecimal agents3Amount) {
        this.agents3Amount = agents3Amount;
    }

    public BigDecimal getShopkeeperAmount() {
        return shopkeeperAmount;
    }

    public void setShopkeeperAmount(BigDecimal shopkeeperAmount) {
        this.shopkeeperAmount = shopkeeperAmount;
    }

    public BigDecimal getAllianceBusinessAmount() {
        return allianceBusinessAmount;
    }

    public void setAllianceBusinessAmount(BigDecimal allianceBusinessAmount) {
        this.allianceBusinessAmount = allianceBusinessAmount;
    }

    public Long getBackTradeId() {
        return backTradeId;
    }

    public void setBackTradeId(Long backTradeId) {
        this.backTradeId = backTradeId;
    }

    public String getTradeAddress() {
        return tradeAddress;
    }

    public void setTradeAddress(String tradeAddress) {
        this.tradeAddress = tradeAddress == null ? null : tradeAddress.trim();
    }

    public String getTradeZone() {
        return tradeZone;
    }

    public void setTradeZone(String tradeZone) {
        this.tradeZone = tradeZone == null ? null : tradeZone.trim();
    }

    public String getTradeCity() {
        return tradeCity;
    }

    public void setTradeCity(String tradeCity) {
        this.tradeCity = tradeCity == null ? null : tradeCity.trim();
    }

    public String getTradeProvince() {
        return tradeProvince;
    }

    public void setTradeProvince(String tradeProvince) {
        this.tradeProvince = tradeProvince == null ? null : tradeProvince.trim();
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public Long getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Long shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public Long getAllianceBusinessId() {
        return allianceBusinessId;
    }

    public void setAllianceBusinessId(Long allianceBusinessId) {
        this.allianceBusinessId = allianceBusinessId;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getSettleAccountsStatus() {
        return settleAccountsStatus;
    }

    public void setSettleAccountsStatus(Integer settleAccountsStatus) {
        this.settleAccountsStatus = settleAccountsStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTradeChannel() {
        return tradeChannel;
    }

    public void setTradeChannel(Integer tradeChannel) {
        this.tradeChannel = tradeChannel;
    }

    public String getTradeChannelName() {
        return tradeChannelName;
    }

    public void setTradeChannelName(String tradeChannelName) {
        this.tradeChannelName = tradeChannelName == null ? null : tradeChannelName.trim();
    }

    public Integer getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(Integer finishFlag) {
        this.finishFlag = finishFlag;
    }

    public String getFinishFlagName() {
        return finishFlagName;
    }

    public void setFinishFlagName(String finishFlagName) {
        this.finishFlagName = finishFlagName == null ? null : finishFlagName.trim();
    }

    public Integer getPlatFormRefund() {
        return platFormRefund;
    }

    public void setPlatFormRefund(Integer platFormRefund) {
        this.platFormRefund = platFormRefund;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getCalTradeAmount() {
        return calTradeAmount;
    }

    public void setCalTradeAmount(BigDecimal calTradeAmount) {
        this.calTradeAmount = calTradeAmount;
    }

    public Long getResourceTradeId() {
        return resourceTradeId;
    }

    public void setResourceTradeId(Long resourceTradeId) {
        this.resourceTradeId = resourceTradeId;
    }
}