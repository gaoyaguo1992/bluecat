package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;

/**
 * 设备分润信息...
 *
 */
public class ShareProfitBO {
	/**
	 * 代理商1分润
	 */
	private BigDecimal agents1Ratio;
	/**
	 * 代理商2分润
	 */
	private BigDecimal agents2Ratio;
	/**
	 * 代理商3分润
	 */
	private BigDecimal agents3Ratio;
	/**
	 * 铺货商分润
	 */
	private BigDecimal shopkeeperRatio;
	/**
	 * 加盟商分润
	 */
	private BigDecimal allianceBusinessRatio;
	/**
	 * key(实始化)
	 */
	private String key;
	/**
	 * 设备id列表..
	 */
	private List<Long> deviceIds;
	
		
	public BigDecimal getAgents1Ratio() {
		return agents1Ratio;
	}
	public void setAgents1Ratio(BigDecimal agents1Ratio) {
		this.agents1Ratio = agents1Ratio;
	}
	public BigDecimal getAgents2Ratio() {
		return agents2Ratio;
	}
	public void setAgents2Ratio(BigDecimal agents2Ratio) {
		this.agents2Ratio = agents2Ratio;
	}
	public BigDecimal getAgents3Ratio() {
		return agents3Ratio;
	}
	public void setAgents3Ratio(BigDecimal agents3Ratio) {
		this.agents3Ratio = agents3Ratio;
	}
	public BigDecimal getShopkeeperRatio() {
		return shopkeeperRatio;
	}
	public void setShopkeeperRatio(BigDecimal shopkeeperRatio) {
		this.shopkeeperRatio = shopkeeperRatio;
	}
	public BigDecimal getAllianceBusinessRatio() {
		return allianceBusinessRatio;
	}
	public void setAllianceBusinessRatio(BigDecimal allianceBusinessRatio) {
		this.allianceBusinessRatio = allianceBusinessRatio;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Long> getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(List<Long> deviceIds) {
		this.deviceIds = deviceIds;
	}
}
