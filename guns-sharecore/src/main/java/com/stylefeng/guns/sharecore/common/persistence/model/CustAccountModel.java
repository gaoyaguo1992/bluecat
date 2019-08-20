package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustAccountModel  extends PageCommand{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9110528189334338678L;

	private Long accountId;

	private Long custNo;

	private BigDecimal balance = BigDecimal.ZERO;

	private BigDecimal frozenBalance = BigDecimal.ZERO;

	private BigDecimal availableBalance = BigDecimal.ZERO;
	
	private BigDecimal jifenBalance = BigDecimal.ZERO;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	private String dataDigest;
	
	private Integer accountType;
	
	private Integer amtSource;
	
	//对应商户字段
	private Long merchantId;
	
	private Integer totalRoundNum;

	private Integer currentRoundNum;
	
	public Integer getTotalRoundNum() {
		return totalRoundNum;
	}

	public void setTotalRoundNum(Integer totalRoundNum) {
		this.totalRoundNum = totalRoundNum;
	}

	public Integer getCurrentRoundNum() {
		return currentRoundNum;
	}

	public void setCurrentRoundNum(Integer currentRoundNum) {
		this.currentRoundNum = currentRoundNum;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getAmtSource() {
		return amtSource;
	}

	public void setAmtSource(Integer amtSource) {
		this.amtSource = amtSource;
	}

	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(BigDecimal frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getDataDigest() {
		return dataDigest;
	}

	public void setDataDigest(String dataDigest) {
		this.dataDigest = dataDigest == null ? null : dataDigest.trim();
	}

	public BigDecimal getJifenBalance() {
		return jifenBalance;
	}

	public void setJifenBalance(BigDecimal jifenBalance) {
		this.jifenBalance = jifenBalance;
	}
	
}