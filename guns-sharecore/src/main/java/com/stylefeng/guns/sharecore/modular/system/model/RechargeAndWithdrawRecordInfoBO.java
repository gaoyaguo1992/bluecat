package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * <P>流水记录业务对象</P>
 */
public class RechargeAndWithdrawRecordInfoBO {
	/**
	 * 交易流水号
	 */
	private Long tradeId; 
	/*
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 格式化后的创建时间
	 */
	private String createTimeFormat;  
	/**
	 * 交易状态
	 */
	private Integer tradeStatus; 
	/**
	 * 交易状态中文描述
	 */
	private String tradeStatusCn; 
	/**
	 * 消费金额
	 */
	private BigDecimal tradeAmount; 
	/**
	 * 交易名称
	 */
	private String tradeName;
	/**
	 * 交易类型。
	 */
	private Integer tradeType;
	
	private List<WithdrawBO> withdrawInfoBOs = new ArrayList<>();

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeStatusCn() {
		return tradeStatusCn;
	}

	public void setTradeStatusCn(String tradeStatusCn) {
		this.tradeStatusCn = tradeStatusCn;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getCreateTimeFormat() {
		return createTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}

	public List<WithdrawBO> getWithdrawInfoBOs() {
		return withdrawInfoBOs;
	}

	public void setWithdrawInfoBOs(List<WithdrawBO> withdrawInfoBOs) {
		this.withdrawInfoBOs = withdrawInfoBOs;
	}
	
}
