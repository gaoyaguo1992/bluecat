package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *我的订单记录业务对象
 */
public class MyOrderRecordInfoBO {
	/**
	 * 订单编号
	 */
	private Long orderId; 
	/**
	 * 订单创建时间
	 */
	private Date createTime; 
	/**
	 * 订单创建时间格式化
	 */
	private String createTimeFormat; 
	/**
	 * 充电器借出时间
	 */
	private Date borrowTime; 
	/**
	 * 充电器借出时间格式化
	 */
	private String borrowTimeFormat; 
	/**
	 * 充电器归还时间
	 */
	private Date backTime; 
	/**
	 * 充电器归还时间格式化
	 */
	private String backTimeFormat; 
	/**
	 * 订单状态
	 */
	private Integer orderStatus; 
	/**
	 * 订单状态中文描述
	 */
	private String orderStatusCn; 
	/**
	 * 租借时长
	 */
	private String lentTimeRange; 
	/**
	 * 预付金
	 */
	private String deposit; 
	/**
	 * 充电器编号
	 */
	private Long chargerId;
	/**
	 * 终端商户
	 */
	private String terminalMerName; 
	/**
	 * 终端地址
	 */
	private String terminalMerAddr; 
	/**
	 * 租金(实付款)
	 */
	private BigDecimal tradeAmount; 
	/**
	 * 已产生费用
	 */
	private BigDecimal expectTradeAmount;
	/**
	 * 是否显示结束使用按钮
	 */
	private Boolean overUseFlag;
	/**
	 * 客户号
	 */
	private Long custNo; 
	/**
	 * 区别于orderStatus,myOrderStatus是合并了orderStatus
	 */
	private Integer myOrderStatus; 

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeFormat() {
		return createTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getBorrowTimeFormat() {
		return borrowTimeFormat;
	}

	public void setBorrowTimeFormat(String borrowTimeFormat) {
		this.borrowTimeFormat = borrowTimeFormat;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getBackTimeFormat() {
		return backTimeFormat;
	}

	public void setBackTimeFormat(String backTimeFormat) {
		this.backTimeFormat = backTimeFormat;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusCn() {
		return orderStatusCn;
	}

	public void setOrderStatusCn(String orderStatusCn) {
		this.orderStatusCn = orderStatusCn;
	}

	public String getLentTimeRange() {
		return lentTimeRange;
	}

	public void setLentTimeRange(String lentTimeRange) {
		this.lentTimeRange = lentTimeRange;
	}

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public String getTerminalMerName() {
		return terminalMerName;
	}

	public void setTerminalMerName(String terminalMerName) {
		this.terminalMerName = terminalMerName;
	}

	public String getTerminalMerAddr() {
		return terminalMerAddr;
	}

	public void setTerminalMerAddr(String terminalMerAddr) {
		this.terminalMerAddr = terminalMerAddr;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public BigDecimal getExpectTradeAmount() {
		return expectTradeAmount;
	}

	public void setExpectTradeAmount(BigDecimal expectTradeAmount) {
		this.expectTradeAmount = expectTradeAmount;
	}

	public Boolean getOverUseFlag() {
		return overUseFlag;
	}

	public void setOverUseFlag(Boolean overUseFlag) {
		this.overUseFlag = overUseFlag;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Integer getMyOrderStatus() {
		return myOrderStatus;
	}

	public void setMyOrderStatus(Integer myOrderStatus) {
		this.myOrderStatus = myOrderStatus;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	
}
