package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;

/**
 * 运营数据封装类
 * @author seven
 *
 */
public class CalculateBusinessDataBO{
	//使用次数，总金额运行数据
	private Long historyOrders; //借出订单数
	private BigDecimal ordersCompareByDay; //借出订单日同比，昨天和前天数据比较 正为增长，负为下降
	private Long historyFinishOrders;//归还订单数
	private BigDecimal finishOrdersCompareByDay; //归还订单日同比，昨天和前天数据比较 正为增长，负为下降
	private BigDecimal totalAmount; //交易总金额
	private BigDecimal totalAmountCompareByDay; //交易金额日同比，昨天和前天数据比较 正为增长，负为下降
	
	//近七天设备使用率
	private BigDecimal day1UsageTrend; //最近第一天使用率
	private BigDecimal day2UsageTrend; //最近第二天使用率
	private BigDecimal day3UsageTrend; //最近第三天使用率
	private BigDecimal day4UsageTrend; //最近第四天使用率
	private BigDecimal day5UsageTrend; //最近第五天使用率
	private BigDecimal day6UsageTrend; //最近第六天使用率
	private BigDecimal day7UsageTrend; //最近第七天使用率
	
	//近七天交易金额
	private BigDecimal beforeAmount1;//最近第一天交易金额
	private BigDecimal beforeAmount2;//最近第二天交易金额
	private BigDecimal beforeAmount3;//最近第三天交易金额
	private BigDecimal beforeAmount4;//最近第四天交易金额
	private BigDecimal beforeAmount5;//最近第五天交易金额
	private BigDecimal beforeAmount6;//最近第六天交易金额
	private BigDecimal beforeAmount7;//最近第七天交易金额
	
	
	
	
	public BigDecimal getDay1UsageTrend() {
		return day1UsageTrend;
	}
	public void setDay1UsageTrend(BigDecimal day1UsageTrend) {
		this.day1UsageTrend = day1UsageTrend;
	}
	public BigDecimal getDay2UsageTrend() {
		return day2UsageTrend;
	}
	public void setDay2UsageTrend(BigDecimal day2UsageTrend) {
		this.day2UsageTrend = day2UsageTrend;
	}
	public BigDecimal getDay3UsageTrend() {
		return day3UsageTrend;
	}
	public void setDay3UsageTrend(BigDecimal day3UsageTrend) {
		this.day3UsageTrend = day3UsageTrend;
	}
	public BigDecimal getDay4UsageTrend() {
		return day4UsageTrend;
	}
	public void setDay4UsageTrend(BigDecimal day4UsageTrend) {
		this.day4UsageTrend = day4UsageTrend;
	}
	public BigDecimal getDay5UsageTrend() {
		return day5UsageTrend;
	}
	public void setDay5UsageTrend(BigDecimal day5UsageTrend) {
		this.day5UsageTrend = day5UsageTrend;
	}
	public BigDecimal getDay6UsageTrend() {
		return day6UsageTrend;
	}
	public void setDay6UsageTrend(BigDecimal day6UsageTrend) {
		this.day6UsageTrend = day6UsageTrend;
	}
	public BigDecimal getDay7UsageTrend() {
		return day7UsageTrend;
	}
	public void setDay7UsageTrend(BigDecimal day7UsageTrend) {
		this.day7UsageTrend = day7UsageTrend;
	}
	public Long getHistoryOrders() {
		return historyOrders;
	}
	public void setHistoryOrders(Long historyOrders) {
		this.historyOrders = historyOrders;
	}
	public BigDecimal getOrdersCompareByDay() {
		return ordersCompareByDay;
	}
	public void setOrdersCompareByDay(BigDecimal ordersCompareByDay) {
		this.ordersCompareByDay = ordersCompareByDay;
	}
	public Long getHistoryFinishOrders() {
		return historyFinishOrders;
	}
	public void setHistoryFinishOrders(Long historyFinishOrders) {
		this.historyFinishOrders = historyFinishOrders;
	}
	public BigDecimal getFinishOrdersCompareByDay() {
		return finishOrdersCompareByDay;
	}
	public void setFinishOrdersCompareByDay(BigDecimal finishOrdersCompareByDay) {
		this.finishOrdersCompareByDay = finishOrdersCompareByDay;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalAmountCompareByDay() {
		return totalAmountCompareByDay;
	}
	public void setTotalAmountCompareByDay(BigDecimal totalAmountCompareByDay) {
		this.totalAmountCompareByDay = totalAmountCompareByDay;
	}
	public BigDecimal getBeforeAmount1() {
		return beforeAmount1;
	}
	public void setBeforeAmount1(BigDecimal beforeAmount1) {
		this.beforeAmount1 = beforeAmount1;
	}
	public BigDecimal getBeforeAmount2() {
		return beforeAmount2;
	}
	public void setBeforeAmount2(BigDecimal beforeAmount2) {
		this.beforeAmount2 = beforeAmount2;
	}
	public BigDecimal getBeforeAmount3() {
		return beforeAmount3;
	}
	public void setBeforeAmount3(BigDecimal beforeAmount3) {
		this.beforeAmount3 = beforeAmount3;
	}
	public BigDecimal getBeforeAmount5() {
		return beforeAmount5;
	}
	public void setBeforeAmount5(BigDecimal beforeAmount5) {
		this.beforeAmount5 = beforeAmount5;
	}
	public BigDecimal getBeforeAmount4() {
		return beforeAmount4;
	}
	public void setBeforeAmount4(BigDecimal beforeAmount4) {
		this.beforeAmount4 = beforeAmount4;
	}
	public BigDecimal getBeforeAmount7() {
		return beforeAmount7;
	}
	public void setBeforeAmount7(BigDecimal beforeAmount7) {
		this.beforeAmount7 = beforeAmount7;
	}
	public BigDecimal getBeforeAmount6() {
		return beforeAmount6;
	}
	public void setBeforeAmount6(BigDecimal beforeAmount6) {
		this.beforeAmount6 = beforeAmount6;
	}
	
	
}
