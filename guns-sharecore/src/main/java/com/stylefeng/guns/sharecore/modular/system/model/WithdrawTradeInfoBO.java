package com.stylefeng.guns.sharecore.modular.system.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *提现处理 。
 */
public class WithdrawTradeInfoBO {
	public final static Integer SUCCESS = 1;
	public final static Integer PARTIAL_SUCCESS = 2;
	public final static Integer PROCESSING = 4;
	public final static Integer FAIL = 3;
	// 退款金额
	private BigDecimal withdrawTotalAmt;

	// 退款交易编号
	private Long tradeId;
	//退款状态 1--退款成功，2--部分退款成功，3，退款失败，4--退款处理中
	private Integer tradeStatus;
	//退款时间
	private Date tradeTime;
	
	private Integer tradeType; //交易类型
	
	private List<WithdrawInfoBO> withdrawInfoBOs = new ArrayList<>();
	
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public BigDecimal getWithdrawTotalAmt() {
		return withdrawTotalAmt;
	}

	public void setWithdrawTotalAmt(BigDecimal withdrawTotalAmt) {
		this.withdrawTotalAmt = withdrawTotalAmt;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public List<WithdrawInfoBO> getWithdrawInfoBOs() {
		return withdrawInfoBOs;
	}

	public void addWithdrawInfoBO(WithdrawInfoBO withdrawInfoBO) {
		this.withdrawInfoBOs.add(withdrawInfoBO);
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

}
