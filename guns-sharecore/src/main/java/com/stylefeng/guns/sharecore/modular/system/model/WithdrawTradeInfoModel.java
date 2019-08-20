package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;


public class WithdrawTradeInfoModel extends PageCommand{
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

	private Date bankRecvTime;

	private Date checkDate;

	private Integer reconStatus;

	private BigDecimal jifenAmt = BigDecimal.ZERO;

	private Date createTime;

	private Long tradeId;

	private Integer tradeType;

	private String tradeTypeCn;
	
	private String reconStatusCn;
	
	private Integer tradeStatus;//交易状态
	
	private String tradeInfoCreateStartDate;//交易开始日期
	
	private String tradeInfoCreateEndDate;//交易结束日期
	
	private String returnCode;
	
	private String returnMsg;
	
	private String isCount;// 用于Mybatis判断的统计标记字段,“统计”代表统计,其它不统计。
	
	private Long manualTradeId; // 手动退款交易编号
	 
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
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

	public String getReconStatusCn() {
		return reconStatusCn;
	}

	public void setReconStatusCn(String reconStatusCn) {
		this.reconStatusCn = reconStatusCn;
	}

	public String getTradeTypeCn() {
		return tradeTypeCn;
	}

	public void setTradeTypeCn(String tradeTypeCn) {
		this.tradeTypeCn = tradeTypeCn;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getIsCount() {
		return isCount;
	}

	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

	public Long getManualTradeId() {
		return manualTradeId;
	}

	public void setManualTradeId(Long manualTradeId) {
		this.manualTradeId = manualTradeId;
	}

}