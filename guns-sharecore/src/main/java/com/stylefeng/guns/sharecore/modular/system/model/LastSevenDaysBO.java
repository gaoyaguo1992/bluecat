package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class LastSevenDaysBO {
	/**
	 * 充电器编号
	 */
	private Long chargerId;
	
	/**
	 * 设备编号
	 */
	private Long deviceId;
	
	/**
	 * 充电器最后一次使用时的时间
	 */
	private Date lastUsedTime;	
	/**
	 * 业务员ID
	 */
	private String salesmanId;
	
	/**
	 * (大于等于)3天未使用的设备数量
	 */
	private Integer devNum;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 店铺地址
	 */
	private String storeAddress;
	/**
	 * 电话
	 */
	private String telNo;
	/**
	 * 铺货商ID
	 */
	private Long shopkeeperId;
	/**
	 * 代理商1
	 */
	private String  agents1Cn;
	/**
	 * 代理商1id
	 */
	private Long    agents1Id;
	/**
	 * 代理商2
	 */
	private String  agents2Cn;
	/**
	 * 代理商2id
	 */
	private Long agents2Id;
	/**
	 * 代理商3
	 */
	private String  agents3Cn;
	/**
	 * 代理商3id
	 */
	private Long agents3Id;
	/**
	 * 加盟商
	 */
	private String  allianceBusinessCn;
	/**
	 * 加盟商Id
	 */
	private Long   allianceBusinessId;
	/**
	 * 铺货商
	 */
	private String shopkeeperCn;
	/**
	 * 店铺id
	 */
	private Long onlineMerchantId;
	/**
	 * 店铺.
	 */
	private String onlineMerchantCn;
	
	
	public String getShopkeeperCn() {
		return shopkeeperCn;
	}

	public void setShopkeeperCn(String shopkeeperCn) {
		this.shopkeeperCn = shopkeeperCn;
	}

	public Long getOnlineMerchantId() {
		return onlineMerchantId;
	}

	public void setOnlineMerchantId(Long onlineMerchantId) {
		this.onlineMerchantId = onlineMerchantId;
	}

	public String getOnlineMerchantCn() {
		return onlineMerchantCn;
	}

	public void setOnlineMerchantCn(String onlineMerchantCn) {
		this.onlineMerchantCn = onlineMerchantCn;
	}

	public String getAgents1Cn() {
		return agents1Cn;
	}

	public void setAgents1Cn(String agents1Cn) {
		this.agents1Cn = agents1Cn;
	}

	public Long getAgents1Id() {
		return agents1Id;
	}

	public void setAgents1Id(Long agents1Id) {
		this.agents1Id = agents1Id;
	}

	public String getAgents2Cn() {
		return agents2Cn;
	}

	public void setAgents2Cn(String agents2Cn) {
		this.agents2Cn = agents2Cn;
	}

	public Long getAgents2Id() {
		return agents2Id;
	}

	public void setAgents2Id(Long agents2Id) {
		this.agents2Id = agents2Id;
	}

	public String getAgents3Cn() {
		return agents3Cn;
	}

	public void setAgents3Cn(String agents3Cn) {
		this.agents3Cn = agents3Cn;
	}

	public Long getAgents3Id() {
		return agents3Id;
	}

	public void setAgents3Id(Long agents3Id) {
		this.agents3Id = agents3Id;
	}

	public String getAllianceBusinessCn() {
		return allianceBusinessCn;
	}

	public void setAllianceBusinessCn(String allianceBusinessCn) {
		this.allianceBusinessCn = allianceBusinessCn;
	}

	public Long getAllianceBusinessId() {
		return allianceBusinessId;
	}

	public void setAllianceBusinessId(Long allianceBusinessId) {
		this.allianceBusinessId = allianceBusinessId;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

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

	public Date getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(Date lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

	public Long getShopkeeperId() {
		return shopkeeperId;
	}

	public void setShopkeeperId(Long shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Integer getDevNum() {
		return devNum;
	}

	public void setDevNum(Integer devNum) {
		this.devNum = devNum;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
}
