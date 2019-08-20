package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantProfitDayModel extends MerchantProfitDayModelKey {
    private String checkMonth;

    private String checkYear;

    private Long merchantId;

    private Long ratioId;

    private String merchantName;

    private String merchantType;

    private Byte settleFeeType;

    private String settleFeeTypeCn;

    private Integer settlementCount;

    private BigDecimal totalAmount;

    private BigDecimal doneAmount;

    private BigDecimal unAmount;

    private Date ratioTime;

    private Date createTime;

    private Date updateTime;

    private Byte status;

    private String industryCategoryCn;

    private String merchantTypeCn;

    private Byte chargerType;

    private Long batchNo;

    private Byte isFreeze;

    public String getCheckMonth() {
        return checkMonth;
    }

    public void setCheckMonth(String checkMonth) {
        this.checkMonth = checkMonth == null ? null : checkMonth.trim();
    }

    public String getCheckYear() {
        return checkYear;
    }

    public void setCheckYear(String checkYear) {
        this.checkYear = checkYear == null ? null : checkYear.trim();
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getRatioId() {
        return ratioId;
    }

    public void setRatioId(Long ratioId) {
        this.ratioId = ratioId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType == null ? null : merchantType.trim();
    }

    public Byte getSettleFeeType() {
        return settleFeeType;
    }

    public void setSettleFeeType(Byte settleFeeType) {
        this.settleFeeType = settleFeeType;
    }

    public String getSettleFeeTypeCn() {
        return settleFeeTypeCn;
    }

    public void setSettleFeeTypeCn(String settleFeeTypeCn) {
        this.settleFeeTypeCn = settleFeeTypeCn == null ? null : settleFeeTypeCn.trim();
    }

    public Integer getSettlementCount() {
        return settlementCount;
    }

    public void setSettlementCount(Integer settlementCount) {
        this.settlementCount = settlementCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDoneAmount() {
        return doneAmount;
    }

    public void setDoneAmount(BigDecimal doneAmount) {
        this.doneAmount = doneAmount;
    }

    public BigDecimal getUnAmount() {
        return unAmount;
    }

    public void setUnAmount(BigDecimal unAmount) {
        this.unAmount = unAmount;
    }

    public Date getRatioTime() {
        return ratioTime;
    }

    public void setRatioTime(Date ratioTime) {
        this.ratioTime = ratioTime;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getIndustryCategoryCn() {
        return industryCategoryCn;
    }

    public void setIndustryCategoryCn(String industryCategoryCn) {
        this.industryCategoryCn = industryCategoryCn == null ? null : industryCategoryCn.trim();
    }

    public String getMerchantTypeCn() {
        return merchantTypeCn;
    }

    public void setMerchantTypeCn(String merchantTypeCn) {
        this.merchantTypeCn = merchantTypeCn == null ? null : merchantTypeCn.trim();
    }

    public Byte getChargerType() {
        return chargerType;
    }

    public void setChargerType(Byte chargerType) {
        this.chargerType = chargerType;
    }

    public Long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }

    public Byte getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Byte isFreeze) {
        this.isFreeze = isFreeze;
    }
}