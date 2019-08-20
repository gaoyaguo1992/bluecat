package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;


/**
 * 批量编辑费命令... 
 */
public class BatchEditFeeCommand extends BaseObject{
	/**
	 * 
	 */
	
	private Long agentsId;
	/**
	 * 要修改的设备id例表..
	 */
	private List<Long> deviceIds;
	/**
	 * 时间控制类型(选择1）
	 */
	private Long feeType1;
	/**
	 * 时间控制费用(选择1）
	 */
	private BigDecimal feeType1Money;
	/**
	 * 时间控制类型(选择2）
	 */
	private Long feeType2;
	/**
	 * 时间控制费用(选择2）
	 */
	private BigDecimal feeType2Money;
	/**
	 *时间控制类型(选择3）
	 */
	private Long feeType3;
	/**
	 * 时间控制费用(选择3）
	 */
	private BigDecimal feeType3Money;
	/**
	 * 充电费用类型时型
	 */
	private Long feeType;
	/**
	 * 预付金
	 */
	private BigDecimal yfj;
	/**
	 * 每小时费用
	 */
	private BigDecimal feePerHour;
	/**
	 * 24小时 费用
	 */
	private BigDecimal feePer24Hour;
	/**
	 * 前充电时长
	 */
	private Long firstHour;
	/**
	 * 前小时充电费用.
	 */
	private BigDecimal firstMoney;
	public Long getAgentsId() {
		return agentsId;
	}
	public void setAgentsId(Long agentsId) {
		this.agentsId = agentsId;
	}
	public List<Long> getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(List<Long> deviceIds) {
		this.deviceIds = deviceIds;
	}
	public Long getFeeType1() {
		return feeType1;
	}
	public void setFeeType1(Long feeType1) {
		this.feeType1 = feeType1;
	}
	public BigDecimal getFeeType1Money() {
		return feeType1Money;
	}
	public void setFeeType1Money(BigDecimal feeType1Money) {
		this.feeType1Money = feeType1Money;
	}
	public Long getFeeType2() {
		return feeType2;
	}
	public void setFeeType2(Long feeType2) {
		this.feeType2 = feeType2;
	}
	public BigDecimal getFeeType2Money() {
		return feeType2Money;
	}
	public void setFeeType2Money(BigDecimal feeType2Money) {
		this.feeType2Money = feeType2Money;
	}
	public Long getFeeType3() {
		return feeType3;
	}
	public void setFeeType3(Long feeType3) {
		this.feeType3 = feeType3;
	}
	public BigDecimal getFeeType3Money() {
		return feeType3Money;
	}
	public void setFeeType3Money(BigDecimal feeType3Money) {
		this.feeType3Money = feeType3Money;
	}
	public Long getFeeType() {
		return feeType;
	}
	public void setFeeType(Long feeType) {
		this.feeType = feeType;
	}
	public BigDecimal getYfj() {
		return yfj;
	}
	public void setYfj(BigDecimal yfj) {
		this.yfj = yfj;
	}
	public BigDecimal getFeePerHour() {
		return feePerHour;
	}
	public void setFeePerHour(BigDecimal feePerHour) {
		this.feePerHour = feePerHour;
	}
	public BigDecimal getFeePer24Hour() {
		return feePer24Hour;
	}
	public void setFeePer24Hour(BigDecimal feePer24Hour) {
		this.feePer24Hour = feePer24Hour;
	}
	public Long getFirstHour() {
		return firstHour;
	}
	public void setFirstHour(Long firstHour) {
		this.firstHour = firstHour;
	}
	public BigDecimal getFirstMoney() {
		return firstMoney;
	}
	public void setFirstMoney(BigDecimal firstMoney) {
		this.firstMoney = firstMoney;
	}
}
