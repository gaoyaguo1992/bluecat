package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class TotalModel {
	/**
	 * 总次数
	 */
    private Long total;
    /**
     * 交易之各
     */
    private BigDecimal totalAmount;    
    /**
     * 预付金和
     */
    private BigDecimal totalYFJAmount;
    /**
     * 平台
     */
    private BigDecimal totalPlatFormAmount;
    /**
     * 一级代理
     */
    private BigDecimal totalAgents1Amount;
    /**
     *  二级代理
     */
    private BigDecimal totalAgents2Amount;
    /**
     * 三级代理
     */
    private BigDecimal totalAgents3Amount;
    /**
     * 加盟商
     */
    private BigDecimal totalAllianceBusinessAmount;
    /**
     * 铺货商
     */
    private BigDecimal totalShopkeeperAmount;
    
	public BigDecimal getTotalYFJAmount() {
		return totalYFJAmount;
	}

	public void setTotalYFJAmount(BigDecimal totalYFJAmount) {
		this.totalYFJAmount = totalYFJAmount;
	}

	public BigDecimal getTotalPlatFormAmount() {
		return totalPlatFormAmount;
	}

	public void setTotalPlatFormAmount(BigDecimal totalPlatFormAmount) {
		this.totalPlatFormAmount = totalPlatFormAmount;
	}

	public BigDecimal getTotalAgents1Amount() {
		return totalAgents1Amount;
	}

	public void setTotalAgents1Amount(BigDecimal totalAgents1Amount) {
		this.totalAgents1Amount = totalAgents1Amount;
	}

	public BigDecimal getTotalAgents2Amount() {
		return totalAgents2Amount;
	}

	public void setTotalAgents2Amount(BigDecimal totalAgents2Amount) {
		this.totalAgents2Amount = totalAgents2Amount;
	}

	public BigDecimal getTotalAgents3Amount() {
		return totalAgents3Amount;
	}

	public void setTotalAgents3Amount(BigDecimal totalAgents3Amount) {
		this.totalAgents3Amount = totalAgents3Amount;
	}

	public BigDecimal getTotalAllianceBusinessAmount() {
		return totalAllianceBusinessAmount;
	}

	public void setTotalAllianceBusinessAmount(BigDecimal totalAllianceBusinessAmount) {
		this.totalAllianceBusinessAmount = totalAllianceBusinessAmount;
	}

	public BigDecimal getTotalShopkeeperAmount() {
		return totalShopkeeperAmount;
	}

	public void setTotalShopkeeperAmount(BigDecimal totalShopkeeperAmount) {
		this.totalShopkeeperAmount = totalShopkeeperAmount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}