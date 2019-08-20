package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;

/**
 * 
 * <P>系统通知消息业务对象</P>
 */
public class NotifyMessageBO {
	
	private String zjAddr; //租借地址
	
	private String chargerId;//租借充电器ID
	
	private String zjAmount;//租借金额
	
	private String currentDate; //租借时间
	
	private Long tradeId; //租借所对应的交易ID
	
	private String payDetial; //消费详情
	
	private int chargerType; //租借充电器类型
	
	private int tradeType; //交易类型
	
	private CustInfoModel custInfoModel; //接收者客户信息
	
	private String remark; 
	
	private Integer messageType;// 消息类型
	
	private String title; //消息标题
	
	private String content; //消息内容

	public String getZjAddr() {
		return zjAddr;
	}

	public void setZjAddr(String zjAddr) {
		this.zjAddr = zjAddr;
	}

	public String getChargerId() {
		return chargerId;
	}

	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}

	public String getZjAmount() {
		return zjAmount;
	}

	public void setZjAmount(String zjAmount) {
		this.zjAmount = zjAmount;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getPayDetial() {
		return payDetial;
	}

	public void setPayDetial(String payDetial) {
		this.payDetial = payDetial;
	}

	public int getChargerType() {
		return chargerType;
	}

	public void setChargerType(int chargerType) {
		this.chargerType = chargerType;
	}

	public CustInfoModel getCustInfoModel() {
		return custInfoModel;
	}

	public void setCustInfoModel(CustInfoModel custInfoModel) {
		this.custInfoModel = custInfoModel;
	}


	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

}
