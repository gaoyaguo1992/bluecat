package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class CapitalChangeFlowModel extends PageCommand{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5889602660970203184L;


	private String capitalTypeName;

    
    /** for bo**/
    private String capitalChangeFlowCreateStartDate;
    
    private String capitalChangeFlowCreateEndDate;

    private BigDecimal totalAmount;
    
    private Long tradeRecordId;
    
    private String tradeName;
    
    private String tradeAmount;
    
    private Integer tradeStatus;
    
    private String tradeStatusName;
    
    private Long merchantId;
 
    private Integer flowType;
	
	private String flowTypeCn;
	
    private String payerCustName;
    
    private String payeeCustName;
	
    private Long flowId;

    private Long capitalChangeId;

    private Integer capitalType;

    private Long custNo;

    private Long accountId;

    private BigDecimal balance; 

    private BigDecimal frozenBalance;

    private BigDecimal availableBalance;

    private Date createTime;
	
    private Integer isSumed = 1;
    
    
	public Integer getIsSumed() {
		return isSumed;
	}

	public void setIsSumed(Integer isSumed) {
		this.isSumed = isSumed;
	}

	public Long getTradeRecordId() {
		return tradeRecordId;
	}

	public void setTradeRecordId(Long tradeRecordId) {
		this.tradeRecordId = tradeRecordId;
	}

	public Long getCapitalChangeId() {
		return capitalChangeId;
	}

	public void setCapitalChangeId(Long capitalChangeId) {
		this.capitalChangeId = capitalChangeId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getCapitalTypeName() {
		if(capitalType!=null){
			return CapitalFlowTypeEnum.getDesc(capitalType);
		}
		return capitalTypeName;
	}

	public void setCapitalTypeName(String capitalTypeName) {
		this.capitalTypeName = capitalTypeName;
	}

	public String getCapitalChangeFlowCreateStartDate() {
		return capitalChangeFlowCreateStartDate;
	}

	public void setCapitalChangeFlowCreateStartDate(String capitalChangeFlowCreateStartDate) {
		this.capitalChangeFlowCreateStartDate = capitalChangeFlowCreateStartDate;
	}

	public String getCapitalChangeFlowCreateEndDate() {
		return capitalChangeFlowCreateEndDate;
	}

	public void setCapitalChangeFlowCreateEndDate(String capitalChangeFlowCreateEndDate) {
		this.capitalChangeFlowCreateEndDate = capitalChangeFlowCreateEndDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}


	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeStatusName() {
		if(null!= tradeStatus){
			return TradeStatusEnum.getDesc(tradeStatus);
		}
		return tradeStatusName;
	}

	public void setTradeStatusName(String tradeStatusName) {
		this.tradeStatusName = tradeStatusName;
	}

	

	public void setFlowTypeCn(String flowTypeCn) {
		this.flowTypeCn = flowTypeCn;
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



	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public Integer getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(Integer capitalType) {
		this.capitalType = capitalType;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getFlowTypeCn() {
		return flowTypeCn;
	}
    
    
}