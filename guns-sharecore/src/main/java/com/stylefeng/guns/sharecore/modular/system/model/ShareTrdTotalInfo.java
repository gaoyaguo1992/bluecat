package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
public class ShareTrdTotalInfo {
	/**
	 * 交易次数
	 */
	private int tradeCount;
	
	private int custCount;
	private BigDecimal adIncome = BigDecimal.ZERO;
	private BigDecimal scanIncome= BigDecimal.ZERO;
	private BigDecimal shangChenIncome= BigDecimal.ZERO;
	private BigDecimal otherincome= BigDecimal.ZERO;
	private int tradeCountToday;
	private int custCountToday;
	private BigDecimal totalTradeAmt= BigDecimal.ZERO;
	private Date checkDate;
	private String checkDateFormat;
	
	/**
	 * 昨日交易数
	 */
	private int yesterdayTradeCount;
	
	/**
	 * 昨日总收益 
	 */
	private BigDecimal yesterdayTradeIncome;
	
	/**
	 * 历史交易数
	 */
	private int hisTradeCount;
	
	/**
	 * 历史总收益
	 */
	private BigDecimal hisTradeIncome;
	
	/**
	 * 今天交易总笔数
	 */
	private int todayTradeCount;
	
	/**
	 * 今天交易总金额
	 */
	private BigDecimal todayTradeAmount;
	
	/**
	 * 历史交易总金额
	 */
	private BigDecimal hisTradeAmount;
	
	private BigDecimal frozenAmount;//冻结金额
	
	private BigDecimal hisYfjAmount;//历史交易总预付金额；
	/**
	 * 预付金之和..	
	 */
	private BigDecimal yfjAmount;
	/**
	 * 平台收入之和
	 */
	private BigDecimal platformAmount;
	/**
	 * 一级代理商之和
	 */
	private BigDecimal agents1Amount;
	/**
	 * 二级代理商之和
	 */
	private BigDecimal agents2Amount;
	/**
	 * 三级代理商之和
	 */
	private BigDecimal agents3Amount;
	/**
	 * 铺货商
	 */
	private BigDecimal shopkeeperAmount;
	/**
	 * 加盟商..
	 */
	private BigDecimal allianceBusinessAmount;
	
		
	public BigDecimal getYfjAmount() {
		return yfjAmount;
	}
	public void setYfjAmount(BigDecimal yfjAmount) {
		this.yfjAmount = yfjAmount;
	}
	public BigDecimal getPlatformAmount() {
		return platformAmount;
	}
	public void setPlatformAmount(BigDecimal platformAmount) {
		this.platformAmount = platformAmount;
	}
	public BigDecimal getAgents1Amount() {
		return agents1Amount;
	}
	public void setAgents1Amount(BigDecimal agents1Amount) {
		this.agents1Amount = agents1Amount;
	}
	public BigDecimal getAgents2Amount() {
		return agents2Amount;
	}
	public void setAgents2Amount(BigDecimal agents2Amount) {
		this.agents2Amount = agents2Amount;
	}
	public BigDecimal getAgents3Amount() {
		return agents3Amount;
	}
	public void setAgents3Amount(BigDecimal agents3Amount) {
		this.agents3Amount = agents3Amount;
	}
	public BigDecimal getShopkeeperAmount() {
		return shopkeeperAmount;
	}
	public void setShopkeeperAmount(BigDecimal shopkeeperAmount) {
		this.shopkeeperAmount = shopkeeperAmount;
	}
	public BigDecimal getAllianceBusinessAmount() {
		return allianceBusinessAmount;
	}
	public void setAllianceBusinessAmount(BigDecimal allianceBusinessAmount) {
		this.allianceBusinessAmount = allianceBusinessAmount;
	}
	public int getCustCount() {
		return custCount;
	}
	public void setCustCount(int custCount) {
		this.custCount = custCount;
	}
	public int getTradeCountToday() {
		return tradeCountToday;
	}
	public void setTradeCountToday(int tradeCountToday) {
		this.tradeCountToday = tradeCountToday;
	}
	public int getCustCountToday() {
		return custCountToday;
	}
	public void setCustCountToday(int custCountToday) {
		this.custCountToday = custCountToday;
	}
	public BigDecimal getTotalTradeAmt() {
		return totalTradeAmt;
	}
	public void setTotalTradeAmt(BigDecimal totalTradeAmt) {
		this.totalTradeAmt = totalTradeAmt;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public BigDecimal getAdIncome() {
		return adIncome;
	}
	public void setAdIncome(BigDecimal adIncome) {
		this.adIncome = adIncome;
	}
	public BigDecimal getScanIncome() {
		return scanIncome;
	}
	public void setScanIncome(BigDecimal scanIncome) {
		this.scanIncome = scanIncome;
	}
	public BigDecimal getShangChenIncome() {
		return shangChenIncome;
	}
	public void setShangChenIncome(BigDecimal shangChenIncome) {
		this.shangChenIncome = shangChenIncome;
	}
	public BigDecimal getOtherincome() {
		return otherincome;
	}
	public void setOtherincome(BigDecimal otherincome) {
		this.otherincome = otherincome;
	}
	public int getYesterdayTradeCount() {
		return yesterdayTradeCount;
	}
	public void setYesterdayTradeCount(int yesterdayTradeCount) {
		this.yesterdayTradeCount = yesterdayTradeCount;
	}
	public BigDecimal getYesterdayTradeIncome() {
		return yesterdayTradeIncome;
	}
	public void setYesterdayTradeIncome(BigDecimal yesterdayTradeIncome) {
		this.yesterdayTradeIncome = yesterdayTradeIncome;
	}
	public int getHisTradeCount() {
		return hisTradeCount;
	}
	public void setHisTradeCount(int hisTradeCount) {
		this.hisTradeCount = hisTradeCount;
	}
	public BigDecimal getHisTradeIncome() {
		return hisTradeIncome;
	}
	public void setHisTradeIncome(BigDecimal hisTradeIncome) {
		this.hisTradeIncome = hisTradeIncome;
	}
	public int getTodayTradeCount() {
		return todayTradeCount;
	}
	public void setTodayTradeCount(int todayTradeCount) {
		this.todayTradeCount = todayTradeCount;
	}
	public BigDecimal getTodayTradeAmount() {
		return todayTradeAmount;
	}
	public void setTodayTradeAmount(BigDecimal todayTradeAmount) {
		this.todayTradeAmount = todayTradeAmount;
	}
	public BigDecimal getHisTradeAmount() {
		return hisTradeAmount;
	}
	public void setHisTradeAmount(BigDecimal hisTradeAmount) {
		this.hisTradeAmount = hisTradeAmount;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckDateFormat() {
		return checkDateFormat;
	}
	public void setCheckDateFormat(String checkDateFormat) {
		this.checkDateFormat = checkDateFormat;
	}
	public BigDecimal getHisYfjAmount() {
		return hisYfjAmount;
	}
	public void setHisYfjAmount(BigDecimal hisYfjAmount) {
		this.hisYfjAmount = hisYfjAmount;
	}
	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	
}
