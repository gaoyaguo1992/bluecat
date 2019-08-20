package com.stylefeng.guns.sharecore.modular.system.model;

public class MerchantOrderPagesQueryBO extends StartRowsPageBO {
	
	private static final long serialVersionUID = 1269221931541962742L;

	private Integer queryStatus; 
	/**
	 * 订单状态
	 */
	private Integer status; 
	/**
	 * 开始时间
	 */
	private String beginDateTime;
	/**
	 * 结束时间
	 */
	private String endDateTime;
	/**
	 * 商户编号
	 */
	private Long merchantId;
	
	private Long deviceId;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(Integer queryStatus) {
		this.queryStatus = queryStatus;
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

}

