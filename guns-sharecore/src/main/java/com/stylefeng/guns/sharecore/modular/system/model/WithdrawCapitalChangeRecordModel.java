package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawCapitalChangeRecordModel   {
    private Long tradeRecordId;
    
    private Long capitalChangeId;
    
    private Long payerCustNo;

    private Long payerAccountId;

    private Long collectCustNo;

    private Long collectAccountId;

    private BigDecimal amount;

    private Integer flowType;

    private Integer capitalStatus;

    private String returnCode;

    private String returnMsg;

    private Date updateDate;

    private Date createTime;
    
    public Long getCapitalChangeId() {
		return capitalChangeId;
	}

	public void setCapitalChangeId(Long capitalChangeId) {
		this.capitalChangeId = capitalChangeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTradeRecordId() {
        return tradeRecordId;
    }

    public void setTradeRecordId(Long tradeRecordId) {
        this.tradeRecordId = tradeRecordId;
    }

    public Long getPayerCustNo() {
        return payerCustNo;
    }

    public void setPayerCustNo(Long payerCustNo) {
        this.payerCustNo = payerCustNo;
    }

    public Long getPayerAccountId() {
        return payerAccountId;
    }

    public void setPayerAccountId(Long payerAccountId) {
        this.payerAccountId = payerAccountId;
    }

    public Long getCollectCustNo() {
        return collectCustNo;
    }

    public void setCollectCustNo(Long collectCustNo) {
        this.collectCustNo = collectCustNo;
    }

    public Long getCollectAccountId() {
        return collectAccountId;
    }

    public void setCollectAccountId(Long collectAccountId) {
        this.collectAccountId = collectAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    

    public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	public Integer getCapitalStatus() {
		return capitalStatus;
	}

	public void setCapitalStatus(Integer capitalStatus) {
		this.capitalStatus = capitalStatus;
	}

	public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}