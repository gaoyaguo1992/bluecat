package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 * 注册信息
 *
 */
public class ChargerBeyondSevenDaysNotUseRequestCommand extends BaseObject {

	private static final long serialVersionUID = 3321190375219227L;
	
	/**
	 * 多少天未使用
	 */
	private Integer howLongNotUse;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 店铺地址
	 */
	private String storeAddress;
		
	/**
	 * 客户号
	 */
	private Long custNo;
	
	/**
	 * 分页查询起始位置
	 */
	private Integer start;
	
	/**
	 * 分页查询一次查询多少条记录
	 */
	private Integer rows;
	
	
	/**
	 * 商户ID
	 */
	private Long merchantId;
	/*
	 * 商户类型
	 */
	private Long merchantTypeId;
	/**
	 * 代理商id1
	 */
	private Long agents1Id;
	/**
	 * 代理商id2
	 */
	private Long agents2Id;
	/**
	 * 代理商id3
	 */
	private Long agents3Id;
	/**
	 * 铺货商
	 */
	private Long shopkeeperId;
	/**
	 * 加盟商
	 */
	private Long allianceBusinessId;
	
	public Long getAgents1Id() {
		return agents1Id;
	}

	public void setAgents1Id(Long agents1Id) {
		this.agents1Id = agents1Id;
	}

	public Long getAgents2Id() {
		return agents2Id;
	}

	public void setAgents2Id(Long agents2Id) {
		this.agents2Id = agents2Id;
	}

	public Long getAgents3Id() {
		return agents3Id;
	}

	public void setAgents3Id(Long agents3Id) {
		this.agents3Id = agents3Id;
	}

	public Long getShopkeeperId() {
		return shopkeeperId;
	}

	public void setShopkeeperId(Long shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
	}

	public Long getAllianceBusinessId() {
		return allianceBusinessId;
	}

	public void setAllianceBusinessId(Long allianceBusinessId) {
		this.allianceBusinessId = allianceBusinessId;
	}

	public Integer getHowLongNotUse() {
		return howLongNotUse;
	}

	public void setHowLongNotUse(Integer howLongNotUse) {
		this.howLongNotUse = howLongNotUse;
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

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMerchantTypeId() {
		return merchantTypeId;
	}

	public void setMerchantTypeId(Long merchantTypeId) {
		this.merchantTypeId = merchantTypeId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
	
}
