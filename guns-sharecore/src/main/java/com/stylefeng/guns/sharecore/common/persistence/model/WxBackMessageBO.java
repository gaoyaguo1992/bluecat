package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
/**
 * 
 * <P>微信归还发消息对象</P>
 */
public class WxBackMessageBO {
	
	private String backAddr; //归还地址
	
	private String chargerId;//租借充电器ID
	
	private BigDecimal chargerAmount;//费用
	
	private BigDecimal yfjAmount; //租借预付金
	
	private String backDate; //归还时间
	
	private String usedTime; //使用时长
	
	private String remark; //备注
	
	private CustInfoModel custInfoModel; //接收者客户信息
	
	private Long tradeId;
	
	private int chargerType; //充电器类型
	
	private String shareModel; //租借模式
	

	public String getBackAddr() {
		return backAddr;
	}

	public void setBackAddr(String backAddr) {
		this.backAddr = backAddr;
	}

	public String getChargerId() {
		return chargerId;
	}

	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}

	public BigDecimal getChargerAmount() {
		return chargerAmount;
	}

	public void setChargerAmount(BigDecimal chargerAmount) {
		this.chargerAmount = chargerAmount;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CustInfoModel getCustInfoModel() {
		return custInfoModel;
	}

	public void setCustInfoModel(CustInfoModel custInfoModel) {
		this.custInfoModel = custInfoModel;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	public int getChargerType() {
		return chargerType;
	}

	public void setChargerType(int chargerType) {
		this.chargerType = chargerType;
	}


	public BigDecimal getYfjAmount() {
		return yfjAmount;
	}

	public void setYfjAmount(BigDecimal yfjAmount) {
		this.yfjAmount = yfjAmount;
	}

	public String getShareModel() {
		return shareModel;
	}

	public void setShareModel(String shareModel) {
		this.shareModel = shareModel;
	} 
	


}
