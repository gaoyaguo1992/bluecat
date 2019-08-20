package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;


/**
 * 
 * <P>退款或手动归还入口返回结果的业务对象</P>
 */
public class WithdrawOrBackHanddleEntryResultBO extends BaseObject{
	
	private static final long serialVersionUID = -5482309173464863554L;

	/**
	 * 交易编号
	 */
	private Long tradeId;
	
	/**
	 * 预付金
	 */
	private BigDecimal yfjAmount;
	
	/**
	 * 充电时间
	 */
	private String usedTime;
	
	/**
	 * 已产生充电费用、已支付充电费用
	 */
	private BigDecimal tradeAmount;
	
	/**
	 * 客户编号
	 */
	private Long custNo;
	
	/**
	 * 充电描述(费用描述)
	 */
	private String feeDesc;
	
	private String newBorrowTime;
	
	private Date borrowTime;
	
	private String borrowTimeStr;
	
	private Long chargerId;
	
	private Date backTime;
	
	private Integer status;
	
	private Integer tradeType;
	
	private String feeInfo;

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public BigDecimal getYfjAmount() {
		return yfjAmount;
	}

	public void setYfjAmount(BigDecimal yfjAmount) {
		this.yfjAmount = yfjAmount;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public String getFeeDesc() {
		return feeDesc;
	}

	public void setFeeDesc(String feeDesc) {
		this.feeDesc = feeDesc;
	}

	public String getNewBorrowTime() {
		return newBorrowTime;
	}

	public void setNewBorrowTime(String newBorrowTime) {
		this.newBorrowTime = newBorrowTime;
	}

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public String getFeeInfo() {
		return feeInfo;
	}

	public void setFeeInfo(String feeInfo) {
		this.feeInfo = feeInfo;
	}

	public String getBorrowTimeStr() {
		return borrowTimeStr;
	}

	public void setBorrowTimeStr(String borrowTimeStr) {
		this.borrowTimeStr = borrowTimeStr;
	}
	
}
