package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 * 我的订单请求command
 */
public class MyOrderRequestCommand extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3214271162443356519L;

	/**
	 * 分页查询起始位置
	 */
	private Integer start;
	
	/**
	 * 分页查询一次查询多少条记录
	 */
	private Integer rows;

	private String beginDateTime ;
	
	private String endDateTime ;
	
	private Integer orderStatus;

	private Long merchantId;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
