package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 */
public class WithdrawInfoBO {
	// 退款金额
	private BigDecimal withdrawAmt;
	// 银行到账状态
	private String bankStatus;
	// 退款时间
	private Date bankRecvTime;
	// 到账银行
	private String refundBank;
	// 实时退款
	private Long outTradeId;

	// 对账状态
	private Integer reconStatus;
	
	// 平台流水号
	private Long tradeRecordId;
	
	// 创建时间格式化
	private String createTimeFormat;

	public Integer getReconStatus() {
		return reconStatus;
	}

	public void setReconStatus(Integer reconStatus) {
		this.reconStatus = reconStatus;
	}

	 

	public BigDecimal getWithdrawAmt() {
		return withdrawAmt;
	}

	public void setWithdrawAmt(BigDecimal withdrawAmt) {
		this.withdrawAmt = withdrawAmt;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getRefundBank() {
		return refundBank;
	}

	public void setRefundBank(String refundBank) {
		this.refundBank = refundBank;
	}

	public Long getOutTradeId() {
		return outTradeId;
	}

	public void setOutTradeId(Long outTradeId) {
		this.outTradeId = outTradeId;
	}

	 

	public Date getBankRecvTime() {
		return bankRecvTime;
	}

	public void setBankRecvTime(Date bankRecvTime) {
		this.bankRecvTime = bankRecvTime;
	}

	public Long getTradeRecordId() {
		return tradeRecordId;
	}

	public void setTradeRecordId(Long tradeRecordId) {
		this.tradeRecordId = tradeRecordId;
	}

	public String getCreateTimeFormat() {
		return createTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}

}
