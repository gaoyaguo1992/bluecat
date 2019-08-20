package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShareDeviceInfoModel {
    private Long id;

    private Integer deviceTypeId;

    private String deviceTypeName;

    private Integer deviceStatus;

    private Date onlineDatetime;

    private String onlineMerchantCn;

    private Long onlineMerchantId;

    private String onlineAddress;

    private String onlineCity;

    private String onlineZone;

    private String onlineProvince;

    private BigDecimal onlineXCoordinate;

    private BigDecimal onlineYCoordinate;

    private Integer feeTypeId;

    private String feeTypeName;

    private BigDecimal yfjAmount;

    private BigDecimal firstAmount;

    private Long firstMinutes;

    private BigDecimal amountPerHour;

    private BigDecimal amountMax24hour;

    private Integer fee1TypeId;

    private String fee1TypeName;

    private Integer fee1HourType;

    private BigDecimal fee1HourAmount;

    private Integer fee2TypeId;

    private String fee2TypeName;

    private Integer fee2HourType;

    private BigDecimal fee2HourAmount;

    private Integer fee3TypeId;

    private String fee3TypeName;

    private Integer fee3HourType;

    private BigDecimal fee3HourAmount;

    private BigDecimal platformRato;

    private Long agents1Id;

    private String agents1Cn;

    private BigDecimal agents1Rato;

    private Long agents2Id;

    private String agents2Cn;

    private BigDecimal agents2Rato;

    private Long agents3Id;

    private BigDecimal agents3Rato;

    private String agents3Cn;

    private Long shopkeeperId;

    private String shopkeeperCn;

    private BigDecimal shopkeeperRato;

    private Long allianceBusinessId;

    private String allianceBusinessCn;

    private BigDecimal allianceBusinessRate;

    private Long createId;

    private Date createDatetime;

    private Date updateDatetime;

    private String sqrUrl;

    private String remark;

    private Integer yfjRebackType;

    private String yfjRebackTypeName;

    private Date lastUsedTime;

    private Integer activationMode;

    private Integer passwordType;

    private String passwordMonth;

    private String passwordBatch;

    private String passwordKey;

    private String passwordYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName == null ? null : deviceTypeName.trim();
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Date getOnlineDatetime() {
        return onlineDatetime;
    }

    public void setOnlineDatetime(Date onlineDatetime) {
        this.onlineDatetime = onlineDatetime;
    }

    public String getOnlineMerchantCn() {
        return onlineMerchantCn;
    }

    public void setOnlineMerchantCn(String onlineMerchantCn) {
        this.onlineMerchantCn = onlineMerchantCn == null ? null : onlineMerchantCn.trim();
    }

    public Long getOnlineMerchantId() {
        return onlineMerchantId;
    }

    public void setOnlineMerchantId(Long onlineMerchantId) {
        this.onlineMerchantId = onlineMerchantId;
    }

    public String getOnlineAddress() {
        return onlineAddress;
    }

    public void setOnlineAddress(String onlineAddress) {
        this.onlineAddress = onlineAddress == null ? null : onlineAddress.trim();
    }

    public String getOnlineCity() {
        return onlineCity;
    }

    public void setOnlineCity(String onlineCity) {
        this.onlineCity = onlineCity == null ? null : onlineCity.trim();
    }

    public String getOnlineZone() {
        return onlineZone;
    }

    public void setOnlineZone(String onlineZone) {
        this.onlineZone = onlineZone == null ? null : onlineZone.trim();
    }

    public String getOnlineProvince() {
        return onlineProvince;
    }

    public void setOnlineProvince(String onlineProvince) {
        this.onlineProvince = onlineProvince == null ? null : onlineProvince.trim();
    }

    public BigDecimal getOnlineXCoordinate() {
        return onlineXCoordinate;
    }

    public void setOnlineXCoordinate(BigDecimal onlineXCoordinate) {
        this.onlineXCoordinate = onlineXCoordinate;
    }

    public BigDecimal getOnlineYCoordinate() {
        return onlineYCoordinate;
    }

    public void setOnlineYCoordinate(BigDecimal onlineYCoordinate) {
        this.onlineYCoordinate = onlineYCoordinate;
    }

    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeTypeName() {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName == null ? null : feeTypeName.trim();
    }

    public BigDecimal getYfjAmount() {
        return yfjAmount;
    }

    public void setYfjAmount(BigDecimal yfjAmount) {
        this.yfjAmount = yfjAmount;
    }

    public BigDecimal getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(BigDecimal firstAmount) {
        this.firstAmount = firstAmount;
    }

    public Long getFirstMinutes() {
        return firstMinutes;
    }

    public void setFirstMinutes(Long firstMinutes) {
        this.firstMinutes = firstMinutes;
    }

    public BigDecimal getAmountPerHour() {
        return amountPerHour;
    }

    public void setAmountPerHour(BigDecimal amountPerHour) {
        this.amountPerHour = amountPerHour;
    }

    public BigDecimal getAmountMax24hour() {
        return amountMax24hour;
    }

    public void setAmountMax24hour(BigDecimal amountMax24hour) {
        this.amountMax24hour = amountMax24hour;
    }

    public Integer getFee1TypeId() {
        return fee1TypeId;
    }

    public void setFee1TypeId(Integer fee1TypeId) {
        this.fee1TypeId = fee1TypeId;
    }

    public String getFee1TypeName() {
        return fee1TypeName;
    }

    public void setFee1TypeName(String fee1TypeName) {
        this.fee1TypeName = fee1TypeName == null ? null : fee1TypeName.trim();
    }

    public Integer getFee1HourType() {
        return fee1HourType;
    }

    public void setFee1HourType(Integer fee1HourType) {
        this.fee1HourType = fee1HourType;
    }

    public BigDecimal getFee1HourAmount() {
        return fee1HourAmount;
    }

    public void setFee1HourAmount(BigDecimal fee1HourAmount) {
        this.fee1HourAmount = fee1HourAmount;
    }

    public Integer getFee2TypeId() {
        return fee2TypeId;
    }

    public void setFee2TypeId(Integer fee2TypeId) {
        this.fee2TypeId = fee2TypeId;
    }

    public String getFee2TypeName() {
        return fee2TypeName;
    }

    public void setFee2TypeName(String fee2TypeName) {
        this.fee2TypeName = fee2TypeName == null ? null : fee2TypeName.trim();
    }

    public Integer getFee2HourType() {
        return fee2HourType;
    }

    public void setFee2HourType(Integer fee2HourType) {
        this.fee2HourType = fee2HourType;
    }

    public BigDecimal getFee2HourAmount() {
        return fee2HourAmount;
    }

    public void setFee2HourAmount(BigDecimal fee2HourAmount) {
        this.fee2HourAmount = fee2HourAmount;
    }

    public Integer getFee3TypeId() {
        return fee3TypeId;
    }

    public void setFee3TypeId(Integer fee3TypeId) {
        this.fee3TypeId = fee3TypeId;
    }

    public String getFee3TypeName() {
        return fee3TypeName;
    }

    public void setFee3TypeName(String fee3TypeName) {
        this.fee3TypeName = fee3TypeName == null ? null : fee3TypeName.trim();
    }

    public Integer getFee3HourType() {
        return fee3HourType;
    }

    public void setFee3HourType(Integer fee3HourType) {
        this.fee3HourType = fee3HourType;
    }

    public BigDecimal getFee3HourAmount() {
        return fee3HourAmount;
    }

    public void setFee3HourAmount(BigDecimal fee3HourAmount) {
        this.fee3HourAmount = fee3HourAmount;
    }

    public BigDecimal getPlatformRato() {
        return platformRato;
    }

    public void setPlatformRato(BigDecimal platformRato) {
        this.platformRato = platformRato;
    }

    public Long getAgents1Id() {
        return agents1Id;
    }

    public void setAgents1Id(Long agents1Id) {
        this.agents1Id = agents1Id;
    }

    public String getAgents1Cn() {
        return agents1Cn;
    }

    public void setAgents1Cn(String agents1Cn) {
        this.agents1Cn = agents1Cn == null ? null : agents1Cn.trim();
    }

    public BigDecimal getAgents1Rato() {
        return agents1Rato;
    }

    public void setAgents1Rato(BigDecimal agents1Rato) {
        this.agents1Rato = agents1Rato;
    }

    public Long getAgents2Id() {
        return agents2Id;
    }

    public void setAgents2Id(Long agents2Id) {
        this.agents2Id = agents2Id;
    }

    public String getAgents2Cn() {
        return agents2Cn;
    }

    public void setAgents2Cn(String agents2Cn) {
        this.agents2Cn = agents2Cn == null ? null : agents2Cn.trim();
    }

    public BigDecimal getAgents2Rato() {
        return agents2Rato;
    }

    public void setAgents2Rato(BigDecimal agents2Rato) {
        this.agents2Rato = agents2Rato;
    }

    public Long getAgents3Id() {
        return agents3Id;
    }

    public void setAgents3Id(Long agents3Id) {
        this.agents3Id = agents3Id;
    }

    public BigDecimal getAgents3Rato() {
        return agents3Rato;
    }

    public void setAgents3Rato(BigDecimal agents3Rato) {
        this.agents3Rato = agents3Rato;
    }

    public String getAgents3Cn() {
        return agents3Cn;
    }

    public void setAgents3Cn(String agents3Cn) {
        this.agents3Cn = agents3Cn == null ? null : agents3Cn.trim();
    }

    public Long getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Long shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public String getShopkeeperCn() {
        return shopkeeperCn;
    }

    public void setShopkeeperCn(String shopkeeperCn) {
        this.shopkeeperCn = shopkeeperCn == null ? null : shopkeeperCn.trim();
    }

    public BigDecimal getShopkeeperRato() {
        return shopkeeperRato;
    }

    public void setShopkeeperRato(BigDecimal shopkeeperRato) {
        this.shopkeeperRato = shopkeeperRato;
    }

    public Long getAllianceBusinessId() {
        return allianceBusinessId;
    }

    public void setAllianceBusinessId(Long allianceBusinessId) {
        this.allianceBusinessId = allianceBusinessId;
    }

    public String getAllianceBusinessCn() {
        return allianceBusinessCn;
    }

    public void setAllianceBusinessCn(String allianceBusinessCn) {
        this.allianceBusinessCn = allianceBusinessCn == null ? null : allianceBusinessCn.trim();
    }

    public BigDecimal getAllianceBusinessRate() {
        return allianceBusinessRate;
    }

    public void setAllianceBusinessRate(BigDecimal allianceBusinessRate) {
        this.allianceBusinessRate = allianceBusinessRate;
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

    public String getSqrUrl() {
        return sqrUrl;
    }

    public void setSqrUrl(String sqrUrl) {
        this.sqrUrl = sqrUrl == null ? null : sqrUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getYfjRebackType() {
        return yfjRebackType;
    }

    public void setYfjRebackType(Integer yfjRebackType) {
        this.yfjRebackType = yfjRebackType;
    }

    public String getYfjRebackTypeName() {
        return yfjRebackTypeName;
    }

    public void setYfjRebackTypeName(String yfjRebackTypeName) {
        this.yfjRebackTypeName = yfjRebackTypeName == null ? null : yfjRebackTypeName.trim();
    }

    public Date getLastUsedTime() {
        return lastUsedTime;
    }

    public void setLastUsedTime(Date lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public Integer getActivationMode() {
        return activationMode;
    }

    public void setActivationMode(Integer activationMode) {
        this.activationMode = activationMode;
    }

    public Integer getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(Integer passwordType) {
        this.passwordType = passwordType;
    }

    public String getPasswordMonth() {
        return passwordMonth;
    }

    public void setPasswordMonth(String passwordMonth) {
        this.passwordMonth = passwordMonth == null ? null : passwordMonth.trim();
    }

    public String getPasswordBatch() {
        return passwordBatch;
    }

    public void setPasswordBatch(String passwordBatch) {
        this.passwordBatch = passwordBatch == null ? null : passwordBatch.trim();
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey == null ? null : passwordKey.trim();
    }

    public String getPasswordYear() {
        return passwordYear;
    }

    public void setPasswordYear(String passwordYear) {
        this.passwordYear = passwordYear == null ? null : passwordYear.trim();
    }
}