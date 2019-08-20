package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;


public class MyWalletBO extends BaseObject{
	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal rechargeBalance;
	private BigDecimal frozenBalance;
	private BigDecimal shaBalance;
	private BigDecimal rewardBalance;
	
	public BigDecimal getRechargeBalance() {
		return rechargeBalance;
	}
	public void setRechargeBalance(BigDecimal rechargeBalance) {
		this.rechargeBalance = rechargeBalance;
	}
	public BigDecimal getFrozenBalance() {
		return frozenBalance;
	}
	public void setFrozenBalance(BigDecimal frozenBalance) {
		this.frozenBalance = frozenBalance;
	}
	public BigDecimal getShaBalance() {
		return shaBalance;
	}
	public void setShaBalance(BigDecimal shaBalance) {
		this.shaBalance = shaBalance;
	}
	public BigDecimal getRewardBalance() {
		return rewardBalance;
	}
	public void setRewardBalance(BigDecimal rewardBalance) {
		this.rewardBalance = rewardBalance;
	}
}
