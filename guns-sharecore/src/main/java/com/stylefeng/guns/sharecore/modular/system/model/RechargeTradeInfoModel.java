package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;


public class RechargeTradeInfoModel extends PageCommand{
	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;

	private String statusCn;

	private String tradeName;

	private Long custNo;

	private Date updateTime;

	private BigDecimal tradeAmount = BigDecimal.ZERO;

	private BigDecimal refundAmount = BigDecimal.ZERO;

	private Date bankRecvTime;

	private Date checkDate;

	private Integer reconStatus;

	private String reconStatusCn;

	private BigDecimal jifenAmt = BigDecimal.ZERO;

	private Long tradeId;

	private Date createTime;

	private Integer tradeType;
	
	private String tradeTypeCn;

	private String tradeInfoCreateStartDate;//交易开始日期
	
	private String tradeInfoCreateEndDate;//交易结束日期

	private Long outTradeNo;
	
	private String isCount;// 用于Mybatis判断的统计标记字段,“统计”代表统计,其它不统计。
	
	public Long getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(Long outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeInfoCreateStartDate() {
		return tradeInfoCreateStartDate;
	}

	public void setTradeInfoCreateStartDate(String tradeInfoCreateStartDate) {
		this.tradeInfoCreateStartDate = tradeInfoCreateStartDate;
	}

	public String getTradeInfoCreateEndDate() {
		return tradeInfoCreateEndDate;
	}

	public void setTradeInfoCreateEndDate(String tradeInfoCreateEndDate) {
		this.tradeInfoCreateEndDate = tradeInfoCreateEndDate;
	}

	public String getTradeTypeCn() {
		return tradeTypeCn;
	}

	public void setTradeTypeCn(String tradeTypeCn) {
		this.tradeTypeCn = tradeTypeCn;
	}

	public String getReconStatusCn() {
		return reconStatusCn;
	}

	public void setReconStatusCn(String reconStatusCn) {
		this.reconStatusCn = reconStatusCn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn == null ? null : statusCn.trim();
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName == null ? null : tradeName.trim();
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Date getBankRecvTime() {
		return bankRecvTime;
	}

	public void setBankRecvTime(Date bankRecvTime) {
		this.bankRecvTime = bankRecvTime;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public BigDecimal getJifenAmt() {
		return jifenAmt;
	}

	public void setJifenAmt(BigDecimal jifenAmt) {
		this.jifenAmt = jifenAmt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReconStatus() {
		return reconStatus;
	}

	public void setReconStatus(Integer reconStatus) {
		this.reconStatus = reconStatus;
	}

	/**
	 * @return the isCount
	 */
	public String getIsCount() {
		return isCount;
	}

	/**
	 * @param isCount the isCount to set
	 */
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

}