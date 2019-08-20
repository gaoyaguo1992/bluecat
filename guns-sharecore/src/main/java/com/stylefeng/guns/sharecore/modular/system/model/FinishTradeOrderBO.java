package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;

/**
 * 结束交易订单BO..
 * @author Alan.huang
 *
 */
public class FinishTradeOrderBO {
	/**
	 * 使用的时间对应秒
	 */
	private Long haveUseTimesForSecond;
	/**
	 * 交易的金额
	 */
	private BigDecimal amount;
	/**
	 * 预付金额
	 */
	private BigDecimal yfj;
	/**
	 * 可提现预金
	 */
	private BigDecimal balance;
	/**
	 * 交易订单号
	 */
	private Long tradeId;
	/**
	 * 交易信息表
	 */
	private ShareTradeInfoModel shareTradeInfoModel;
	/**
	 * 交易对应的设备信息表
	 */
	private ShareTradeDeviceInfoModel shareTradeDeviceInfoModel;
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Long getHaveUseTimesForSecond() {
		return haveUseTimesForSecond;
	}
	public void setHaveUseTimesForSecond(Long haveUseTimesForSecond) {
		this.haveUseTimesForSecond = haveUseTimesForSecond;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getYfj() {
		return yfj;
	}
	public void setYfj(BigDecimal yfj) {
		this.yfj = yfj;
	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public ShareTradeInfoModel getShareTradeInfoModel() {
		return shareTradeInfoModel;
	}
	public void setShareTradeInfoModel(ShareTradeInfoModel shareTradeInfoModel) {
		this.shareTradeInfoModel = shareTradeInfoModel;
	}
	public ShareTradeDeviceInfoModel getShareTradeDeviceInfoModel() {
		return shareTradeDeviceInfoModel;
	}
	public void setShareTradeDeviceInfoModel(ShareTradeDeviceInfoModel shareTradeDeviceInfoModel) {
		this.shareTradeDeviceInfoModel = shareTradeDeviceInfoModel;
	}
}
