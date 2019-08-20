package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;


/**
 * 批量编辑费命令... 
 */
public class BatchEditProfitCommand extends BaseObject{
	/**
	 * 
	 */
	
	private Long agentsId;
	/**
	 * 要修改的设备id例表..
	 */
	private List<Long> devicesIds;
	/**
	 * 主键
	 */
	private String  key;
	/**
	 * 平台分润
	 */
	private BigDecimal platformRatio;
	/**
	 * 一级
	 */
	private BigDecimal agents1Ratio;
	/**
	 * 二级代理分润
	 */
	private BigDecimal agents2Ratio;
	/**
	 * 三级代理分润
	 */
	private BigDecimal agents3Ratio;
	/**
	 * 铺货商
	 */
	private BigDecimal shopkeeperRatio;
	/**
	 * 加盟商
	 */
	private BigDecimal allianceBusinessRatio;
	
	public Long getAgentsId() {
		return agentsId;
	}
	public void setAgentsId(Long agentsId) {
		this.agentsId = agentsId;
	}
	public List<Long> getDevicesIds() {
		return devicesIds;
	}
	public void setDevicesIds(List<Long> devicesIds) {
		this.devicesIds = devicesIds;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BigDecimal getPlatformRatio() {
		return platformRatio;
	}
	public void setPlatformRatio(BigDecimal platformRatio) {
		this.platformRatio = platformRatio;
	}
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
}
