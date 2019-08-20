package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;


public class ChannelCapitalRecord extends BaseObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7624177945476788940L;

	private Long recordId;

    private Long outTradeNo;
    
    private Long custNo;
    
    private String mchId;

    private String transactionId; //对应支付宝小程序业务ID 用于退款申请

    private Date timeEnd;

    private BigDecimal totalFee;

    private String bankType;

    private String tradeType;

    private Integer channelType;
    
    private String resultDesc;
    
    private String resultCode;

    private Integer feeType;

    private Date updateTime;

    private Date createTime;

    private String openId;

    private Integer status;

    private Long capitalChangeId;
    
    private Integer reconStatus;
    
    private String recvBank;
    
    private Long tradeRecordId;
    
    public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Long getTradeRecordId() {
		return tradeRecordId;
	}

	public void setTradeRecordId(Long tradeRecordId) {
		this.tradeRecordId = tradeRecordId;
	}
	
    public String getRecvBank() {
		return recvBank;
	}

	public void setRecvBank(String recvBank) {
		this.recvBank = recvBank;
	}

	public Integer getReconStatus() {
		return reconStatus;
	}

	public void setReconStatus(Integer reconStatus) {
		this.reconStatus = reconStatus;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(Long outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCapitalChangeId() {
		return capitalChangeId;
	}

	public void setCapitalChangeId(Long capitalChangeId) {
		this.capitalChangeId = capitalChangeId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	
}