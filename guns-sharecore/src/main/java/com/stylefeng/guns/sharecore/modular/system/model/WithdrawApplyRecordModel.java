package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.constants.ApproveStatusEnum;


public class WithdrawApplyRecordModel {
    /**
     * 申请客户
     */
    private Long applyCustNo;

	/**
	 * 申请单ID
	 */
    private Long id;

    /**
     * 申请时间
     */
    private Date applyTime;
    
    /**
     * 格式化申请时间
     */
    private String applyTimeFormat;

    /**
     * 申请商户ID
     */
    private Long applyMerchantId;
    
    /**
     * 顶级代理商名称
     */
    private String topAgentsCn;
    
    /**
     * 顶级代理商ID
     */
    private String topAgentsId;

    /**
     * 税前提现金额
     */
    private BigDecimal preTaxAmount;

    /**
     * 税后提现金额
     */
    private BigDecimal aftTaxAmount;

    /**
     * 提现状态
     */
    private Integer withdrawStatus;

    /**
     * 审核人
     */
    private String approver;

    /**
     * 审核时间
     */
    private Date approveTime;

    /**
     * 审核状态
     */
    private Integer approveStatus;

    /**
     * 审核备注内容
     */
    private String approveComment;
    
    /**
     * 提现状态中文
     */
    private String withdrawStatusCn;
    
    /**
     * 审核状态中文
     */
    private String approveStatusCn;
    
    /**
     * 应缴税款
     */
    private BigDecimal shouldPayTax;
    
    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款时间
     */
    private Date payTime;
    
    private String personName;
    
    private BigDecimal investMoney;

    private String applicationStartDate;
    
    private String applicationEndDate;
    
    private String reviewStartDate;
    
    private String reviewEndDate;
    
    private String payTimeStartDate;
    
    private String payTimeEndDate;
    
    /**
     * 商户类型
     */
    private Integer merchantType;
    
    /**
     * 商户子类型
     */
    private Integer merchantSubType;
    
    /**
     * 经营模式
     */
    private Integer manageModelType;
    
    /**
     * 发票号码
     */
    private String invoiceNo;
    
    /**
     * 商户类型中文描述
     */
    private String merchantTypeCn;
    
    /**
     * 商户子类型中文描述
     */
    private String merchantSubTypeCn;
    /**
     * 商户联系人账号
     */
    private String personTelNo;
    /**
     * 商户名称
     */
    private String merchantName;
    
    /**
     * 经营模式中文描述
     */
    private String manageModelTypeCn;
    
    private BigDecimal manualDeduction;
    
    private Integer numberOfMonth;
    
    private String startDateOfThisMonth; //本月开始时间
    
    private String endDateOfThisMonth;//本月结束时间
    
    private Integer payWay;// 付款方式
    
    private List<Long> withdrawWayIds;//付款方式查询使用
    
    private String payWayCn;
    
    private String agentsInfo; //顶级代理id和名字
    
    private String hasImgsInfos; //已经上传了信息
    
    private Integer isRealTime; //分润方式
    
    private String isRealTimeCn;//
    
    public String getPersonTelNo() {
		return personTelNo;
	}

	public void setPersonTelNo(String personTelNo) {
		this.personTelNo = personTelNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getNumberOfMonth() {
		return numberOfMonth;
	}

	public void setNumberOfMonth(Integer numberOfMonth) {
		this.numberOfMonth = numberOfMonth;
	}

	public BigDecimal getManualDeduction() {
		return manualDeduction;
	}

	public void setManualDeduction(BigDecimal manualDeduction) {
		this.manualDeduction = manualDeduction;
	}

	public String getWithdrawStatusCn() {
		return withdrawStatusCn;
	}

	public void setWithdrawStatusCn(String withdrawStatusCn) {
		this.withdrawStatusCn = withdrawStatusCn;
	}

	public Long getApplyCustNo() {
		return applyCustNo;
	}

	public void setApplyCustNo(Long applyCustNo) {
		this.applyCustNo = applyCustNo;
	}

	public String getPayTimeStartDate() {
		return payTimeStartDate;
	}

	public void setPayTimeStartDate(String payTimeStartDate) {
		this.payTimeStartDate = payTimeStartDate;
	}

	public String getPayTimeEndDate() {
		return payTimeEndDate;
	}

	public void setPayTimeEndDate(String payTimeEndDate) {
		this.payTimeEndDate = payTimeEndDate;
	}

	public String getApplicationStartDate() {
		return applicationStartDate;
	}

	public void setApplicationStartDate(String applicationStartDate) {
		this.applicationStartDate = applicationStartDate;
	}

	public String getApplicationEndDate() {
		return applicationEndDate;
	}

	public void setApplicationEndDate(String applicationEndDate) {
		this.applicationEndDate = applicationEndDate;
	}

	public String getReviewStartDate() {
		return reviewStartDate;
	}

	public void setReviewStartDate(String reviewStartDate) {
		this.reviewStartDate = reviewStartDate;
	}

	public String getReviewEndDate() {
		return reviewEndDate;
	}

	public void setReviewEndDate(String reviewEndDate) {
		this.reviewEndDate = reviewEndDate;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public BigDecimal getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}


    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getApplyMerchantId() {
        return applyMerchantId;
    }

    public void setApplyMerchantId(Long applyMerchantId) {
        this.applyMerchantId = applyMerchantId ;
    }

    public BigDecimal getPreTaxAmount() {
        return preTaxAmount;
    }

    public void setPreTaxAmount(BigDecimal preTaxAmount) {
        this.preTaxAmount = preTaxAmount;
    }

    public BigDecimal getAftTaxAmount() {
        return aftTaxAmount;
    }

    public void setAftTaxAmount(BigDecimal aftTaxAmount) {
        this.aftTaxAmount = aftTaxAmount;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment == null ? null : approveComment.trim();
    }

	public Integer getWithdrawStatus() {
		return withdrawStatus;
	}

	public void setWithdrawStatus(Integer withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveStatusCn() {
		if(approveStatus != null){
			return ApproveStatusEnum.getDesc(approveStatus);
		}
		return approveStatusCn;
	}

	public void setApproveStatusCn(String approveStatusCn) {
		this.approveStatusCn = approveStatusCn;
	}

	public String getApplyTimeFormat() {
		return applyTimeFormat;
	}

	public void setApplyTimeFormat(String applyTimeFormat) {
		this.applyTimeFormat = applyTimeFormat;
	}

	public BigDecimal getShouldPayTax() {
		return shouldPayTax;
	}

	public void setShouldPayTax(BigDecimal shouldPayTax) {
		this.shouldPayTax = shouldPayTax;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public Integer getManageModelType() {
		return manageModelType;
	}

	public void setManageModelType(Integer manageModelType) {
		this.manageModelType = manageModelType;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getMerchantTypeCn() {
		return merchantTypeCn;
	}

	public void setMerchantTypeCn(String merchantTypeCn) {
		this.merchantTypeCn = merchantTypeCn;
	}

	public String getMerchantSubTypeCn() {
		return merchantSubTypeCn;
	}

	public void setMerchantSubTypeCn(String merchantSubTypeCn) {
		this.merchantSubTypeCn = merchantSubTypeCn;
	}

	public String getManageModelTypeCn() {
		return manageModelTypeCn;
	}

	public void setManageModelTypeCn(String manageModelTypeCn) {
		this.manageModelTypeCn = manageModelTypeCn;
	}

	public Integer getMerchantSubType() {
		return merchantSubType;
	}

	public void setMerchantSubType(Integer merchantSubType) {
		this.merchantSubType = merchantSubType;
	}

	public String getStartDateOfThisMonth() {
		return startDateOfThisMonth;
	}

	public void setStartDateOfThisMonth(String startDateOfThisMonth) {
		this.startDateOfThisMonth = startDateOfThisMonth;
	}

	public String getEndDateOfThisMonth() {
		return endDateOfThisMonth;
	}

	public void setEndDateOfThisMonth(String endDateOfThisMonth) {
		this.endDateOfThisMonth = endDateOfThisMonth;
	}

	public String getTopAgentsCn() {
		return topAgentsCn;
	}

	public void setTopAgentsCn(String topAgentsCn) {
		this.topAgentsCn = topAgentsCn;
	}

	public String getTopAgentsId() {
		return topAgentsId;
	}

	public void setTopAgentsId(String topAgentsId) {
		this.topAgentsId = topAgentsId;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public List<Long> getWithdrawWayIds() {
		return withdrawWayIds;
	}

	public void setWithdrawWayIds(List<Long> withdrawWayIds) {
		this.withdrawWayIds = withdrawWayIds;
	}

	public String getPayWayCn() {
		return payWayCn;
	}

	public void setPayWayCn(String payWayCn) {
		this.payWayCn = payWayCn;
	}

	public String getAgentsInfo() {
		return agentsInfo;
	}

	public void setAgentsInfo(String agentsInfo) {
		this.agentsInfo = agentsInfo;
	}

	public String getHasImgsInfos() {
		return hasImgsInfos;
	}

	public void setHasImgsInfos(String hasImgsInfos) {
		this.hasImgsInfos = hasImgsInfos;
	}

	public Integer getIsRealTime() {
		return isRealTime;
	}

	public void setIsRealTime(Integer isRealTime) {
		this.isRealTime = isRealTime;
	}

	public String getIsRealTimeCn() {
		return isRealTimeCn;
	}

	public void setIsRealTimeCn(String isRealTimeCn) {
		this.isRealTimeCn = isRealTimeCn;
	}

	
}