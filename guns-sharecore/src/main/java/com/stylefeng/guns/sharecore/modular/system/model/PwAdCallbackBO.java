package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

import me.chanjar.weixin.common.util.StringUtils;

/**
 * 密码回调
 *
 */
public class PwAdCallbackBO extends BaseObject{
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -1386421075388993775L;
	private String password;
	private String deviceType;
	private String yfjAmt;
	private String chargerPrice;
	private String adUrl;
	private String message;
	private String tradeType;
	private Integer backWay;
	/**
	 * 交易id
	 */
	private String tradeId;
	/**
	 * 设备编号
	 */
	private String deviceNo;
	/**
	 * 充电器id
	 */
	private String chargerId;
	/**
	 * 费用模式
	 */
	private String feeTypeId;
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(String feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
	public Integer getBackWay() {
		return backWay;
	}
	public void setBackWay(Integer backWay) {
		this.backWay = backWay;
	}	
	
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public PwAdCallbackBO(String password, String deviceType, String yfjAmt, String chargerPrice, String adUrl,
			String message) {
		super();
		this.password = password;
		this.deviceType = deviceType;
		if(StringUtils.isNotEmpty(yfjAmt)){
			BigDecimal yfjAmount = new BigDecimal(yfjAmt);
			this.yfjAmt = yfjAmount.intValue()+"";
		}
		
		if(StringUtils.isNotEmpty(yfjAmt)){
			BigDecimal chargerPriceAmount = new BigDecimal(chargerPrice);
			this.chargerPrice = chargerPriceAmount.intValue()+"";
		}
		this.adUrl = adUrl;
		this.message = message;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getYfjAmt() {
		return yfjAmt;
	}
	public void setYfjAmt(String yfjAmt) {
		BigDecimal yfjAmount = new BigDecimal(yfjAmt);
		this.yfjAmt = yfjAmount.intValue()+"";
	}
	public String getChargerPrice() {
		return chargerPrice;
	}
	public void setChargerPrice(String chargerPrice) {
		BigDecimal chargerPriceAmount = new BigDecimal(chargerPrice);
		this.chargerPrice = chargerPriceAmount.intValue()+"";		 
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getChargerId() {
		return chargerId;
	}
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}
}
