package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class MerchantOrderRecordInfoBO {


	private Long orderId; //订单编号
	
	private Date createTime; //订单创建时间
	
	private String createTimeFormat; //订单创建时间格式化
	
	private Date backTime; //充电器归还时间
	
	private String backTimeFormat; //充电器归还时间格式化
	
	private Integer orderStatus; //订单状态
	
	private String orderStatusCn; //订单状态中文描述
	
	private String deposit; //预付金
	
	private String tradeAmount; //租金(实付款)
	
	private Long custNo; //客户号
	
	private Integer myOrderStatus; //区别于orderStatus,myOrderStatus是合并了orderStatus
	
	private String tradeName;//交易名称
	
	private String myOrderStatusCn;
	
	private Long chargerId;
	
	private Long deviceId;
	
	private ShareDeviceInfoModel shareDeviceInfoModel;//订单所属设备信息

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

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

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
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

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getMyOrderStatusCn() {
		return myOrderStatusCn;
	}

	public void setMyOrderStatusCn(String myOrderStatusCn) {
		this.myOrderStatusCn = myOrderStatusCn;
	}

	public ShareDeviceInfoModel getShareDeviceInfoModel() {
		return shareDeviceInfoModel;
	}

	public void setShareDeviceInfoModel(ShareDeviceInfoModel shareDeviceInfoModel) {
		this.shareDeviceInfoModel = shareDeviceInfoModel;
	}
}