package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



public class CapitalChangeRecordModel extends PageCommand {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3290174026364916736L;

	private String payerCustName;

	private String payeeCustName;

	private List<CapitalChangeFlowModel> capitalChangeFlowModels;

	private Long capitalChangeId;

	private Long tradeRecordId;

	private Long payerCustNo;

	private Long payerAccountId;

	private Long collectCustNo;

	private Long collectAccountId;

	private BigDecimal amount;
	/**
	 * 余额中扣款金额
	 */
	private BigDecimal payAmountByBalance;
	
	private Integer flowType;

	private Integer capitalStatus;

	private String returnCode;

	private String returnMsg;

	private Date createTime;

	private Date updateDate;
	//用于日志记录
	private Long LogId;
	
	public BigDecimal getPayAmountByBalance() {
		return payAmountByBalance;
	}

	public void setPayAmountByBalance(BigDecimal payAmountByBalance) {
		this.payAmountByBalance = payAmountByBalance;
	}

	public Long getLogId() {
		return LogId;
	}

	public void setLogId(Long logId) {
		LogId = logId;
	}

	public String getPayerCustName() {
		return payerCustName;
	}

	public void setPayerCustName(String payerCustName) {
		this.payerCustName = payerCustName;
	}

	public String getPayeeCustName() {
		return payeeCustName;
	}

	public void setPayeeCustName(String payeeCustName) {
		this.payeeCustName = payeeCustName;
	}

	public List<CapitalChangeFlowModel> getCapitalChangeFlowModels() {
		return capitalChangeFlowModels;
	}

	public void setCapitalChangeFlowModels(List<CapitalChangeFlowModel> capitalChangeFlowModels) {
		this.capitalChangeFlowModels = capitalChangeFlowModels;
	}

	public Long getCapitalChangeId() {
		return capitalChangeId;
	}

	public void setCapitalChangeId(Long capitalChangeId) {
		this.capitalChangeId = capitalChangeId;
	}

	public Long getTradeRecordId() {
		return tradeRecordId;
	}

	public void setTradeRecordId(Long tradeRecordId) {
		this.tradeRecordId = tradeRecordId;
	}

	public Long getPayerCustNo() {
		return payerCustNo;
	}

	public void setPayerCustNo(Long payerCustNo) {
		this.payerCustNo = payerCustNo;
	}

	

	public Long getCollectCustNo() {
		return collectCustNo;
	}

	public void setCollectCustNo(Long collectCustNo) {
		this.collectCustNo = collectCustNo;
	}

	

	public Long getPayerAccountId() {
		return payerAccountId;
	}

	public void setPayerAccountId(Long payerAccountId) {
		this.payerAccountId = payerAccountId;
	}

	public Long getCollectAccountId() {
		return collectAccountId;
	}

	public void setCollectAccountId(Long collectAccountId) {
		this.collectAccountId = collectAccountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	public Integer getCapitalStatus() {
		return capitalStatus;
	}

	public void setCapitalStatus(Integer capitalStatus) {
		this.capitalStatus = capitalStatus;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}