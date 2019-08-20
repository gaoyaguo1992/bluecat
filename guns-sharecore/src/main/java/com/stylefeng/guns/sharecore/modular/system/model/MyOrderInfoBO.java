package com.stylefeng.guns.sharecore.modular.system.model;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 我的订单（分销系统-购买设备申请）记录分页BO
 *
 */
public class MyOrderInfoBO {
	
	/**
	 * 下单时间
	 */
	private Date orderCreateTime;
	
	/**
	 * 下单时间格式化
	 */
	private String orderCreateTimeFormat;
	
	/**
	 * 充电器类型
	 */
	private Integer chargerType;
	
	/**
	 * 充电器类型中文描述
	 */
	private String chargerTypeDesc;
	
	/**
	 * 交易泉
	 */
	private BigDecimal tradeSpringAmt;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/**
	 * 订单状态中文描述
	 */
	private String orderStatusCn;
	
	/**
	 * 确认收货标识
	 */
	private Integer confirmReceiveDev;
	
	/**
	 * 订单号
	 */
	private Long orderId;
	
	/**
	 * 充电器个数
	 */
	private Integer chargerNum;

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderCreateTimeFormat() {
		return orderCreateTimeFormat;
	}

	public void setOrderCreateTimeFormat(String orderCreateTimeFormat) {
		this.orderCreateTimeFormat = orderCreateTimeFormat;
	}

	public Integer getChargerType() {
		return chargerType;
	}

	public void setChargerType(Integer chargerType) {
		this.chargerType = chargerType;
	}

	public String getChargerTypeDesc() {
		return chargerTypeDesc;
	}

	public void setChargerTypeDesc(String chargerTypeDesc) {
		this.chargerTypeDesc = chargerTypeDesc;
	}

	public BigDecimal getTradeSpringAmt() {
		return tradeSpringAmt;
	}

	public void setTradeSpringAmt(BigDecimal tradeSpringAmt) {
		this.tradeSpringAmt = tradeSpringAmt;
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

	public Integer getConfirmReceiveDev() {
		return confirmReceiveDev;
	}

	public void setConfirmReceiveDev(Integer confirmReceiveDev) {
		this.confirmReceiveDev = confirmReceiveDev;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getChargerNum() {
		return chargerNum;
	}

	public void setChargerNum(Integer chargerNum) {
		this.chargerNum = chargerNum;
	}

}
