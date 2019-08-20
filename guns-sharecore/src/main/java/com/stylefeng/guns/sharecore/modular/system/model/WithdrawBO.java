package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * <P>单条提现记录</P>
 */
public class WithdrawBO {
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

	private Long recordId; //channel_capital_record  recordId

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}


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

}
