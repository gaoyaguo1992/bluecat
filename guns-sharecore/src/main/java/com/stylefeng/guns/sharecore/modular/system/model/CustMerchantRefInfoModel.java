package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class CustMerchantRefInfoModel extends CustMerchantRefInfoModelKey {
    private Byte custType;

    private String merchantName;

    private Byte merchantType;

    private String merchantTypeCn;

    private Byte isDefault;

    private Date createTime;

    private Long createId;

    public Byte getCustType() {
        return custType;
    }

    public void setCustType(Byte custType) {
        this.custType = custType;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Byte getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Byte merchantType) {
        this.merchantType = merchantType;
    }

    public String getMerchantTypeCn() {
        return merchantTypeCn;
    }

    public void setMerchantTypeCn(String merchantTypeCn) {
        this.merchantTypeCn = merchantTypeCn == null ? null : merchantTypeCn.trim();
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }
}