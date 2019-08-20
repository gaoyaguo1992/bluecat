package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 我的订单记录分页BO
 *
 */
public class MyOrderPageInfoBO {
	
	private String result;
	
	private List<MyOrderInfoBO> myOrderInfoBOs;
	
	/**
	 * 交易泉总和
	 */
	private BigDecimal tradeSpringAmtSum;
	
	/**
	 * 交易泉笔数
	 */
	private Integer tradeSpringCount;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<MyOrderInfoBO> getMyOrderInfoBOs() {
		return myOrderInfoBOs;
	}
	public void setMyOrderInfoBOs(List<MyOrderInfoBO> myOrderInfoBOs) {
		this.myOrderInfoBOs = myOrderInfoBOs;
	}
	public BigDecimal getTradeSpringAmtSum() {
		return tradeSpringAmtSum;
	}
	public void setTradeSpringAmtSum(BigDecimal tradeSpringAmtSum) {
		this.tradeSpringAmtSum = tradeSpringAmtSum;
	}
	public Integer getTradeSpringCount() {
		return tradeSpringCount;
	}
	public void setTradeSpringCount(Integer tradeSpringCount) {
		this.tradeSpringCount = tradeSpringCount;
	}
	
}
