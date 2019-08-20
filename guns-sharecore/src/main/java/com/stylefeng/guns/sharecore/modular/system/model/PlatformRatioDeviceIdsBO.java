package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceStatusEnum;

public class PlatformRatioDeviceIdsBO  {
	/**
	 * 得到对应平台分润的设备id列表（多个用逗号分隔)
	 */
	private String deviceIds;
	/**
	 * 平台分润比较
	 */
	private BigDecimal platformRatio;
	/**
	 * 顶级代理分润
	 */
	private BigDecimal agents1Rato;
	/**
	 * 一级代理分润
	 */
	private BigDecimal agents2Rato;
	/**
	 * 二级
	 */
	private BigDecimal agents3Rato;
	/**
	 * 铺货商
	 */
	private BigDecimal shopkeeperRato;
	/**
	 * 加盟商
	 */
	private BigDecimal allianceBusinessRate;
	/**
	 * 设备数量
	 */
	private Long deviceCount;
		
	public Long getDeviceCount() {
		return deviceCount;
	}
	public void setDeviceCount(Long deviceCount) {
		this.deviceCount = deviceCount;
	}
	public String getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}
	public BigDecimal getPlatformRatio() {
		return platformRatio;
	}
	public void setPlatformRatio(BigDecimal platformRatio) {
		this.platformRatio = platformRatio;
	}
	public BigDecimal getAgents1Rato() {
		return agents1Rato;
	}
	public void setAgents1Rato(BigDecimal agents1Rato) {
		this.agents1Rato = agents1Rato;
	}
	public BigDecimal getAgents2Rato() {
		return agents2Rato;
	}
	public void setAgents2Rato(BigDecimal agents2Rato) {
		this.agents2Rato = agents2Rato;
	}
	public BigDecimal getAgents3Rato() {
		return agents3Rato;
	}
	public void setAgents3Rato(BigDecimal agents3Rato) {
		this.agents3Rato = agents3Rato;
	}
	public BigDecimal getShopkeeperRato() {
		return shopkeeperRato;
	}
	public void setShopkeeperRato(BigDecimal shopkeeperRato) {
		this.shopkeeperRato = shopkeeperRato;
	}
	public BigDecimal getAllianceBusinessRate() {
		return allianceBusinessRate;
	}
	public void setAllianceBusinessRate(BigDecimal allianceBusinessRate) {
		this.allianceBusinessRate = allianceBusinessRate;
	}
}