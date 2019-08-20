package com.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-03-11
 */
@TableName("withdraw_apply_record")
public class WithdrawApplyRecord extends Model<WithdrawApplyRecord> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("apply_time")
    private Date applyTime;
    @TableField("apply_merchant_id")
    private String applyMerchantId;
    @TableField("pre_tax_amount")
    private BigDecimal preTaxAmount;
    @TableField("aft_tax_amount")
    private BigDecimal aftTaxAmount;
    @TableField("withdraw_status")
    private Integer withdrawStatus;
    @TableField("withdraw_status_cn")
    private String withdrawStatusCn;
    private String approver;
    @TableField("approve_time")
    private Date approveTime;
    @TableField("approve_status")
    private Integer approveStatus;
    @TableField("approve_status_cn")
    private String approveStatusCn;
    @TableField("approve_comment")
    private String approveComment;
    @TableField("should_pay_tax")
    private BigDecimal shouldPayTax;
    private String payer;
    @TableField("pay_time")
    private Date payTime;
    /**
     * 发票号码
     */
    @TableField("invoice_no")
    private String invoiceNo;
    /**
     * 手动扣除金额
     */
    @TableField("manual_deduction")
    private BigDecimal manualDeduction;
    /**
     * 本月第几次发起提现
     */
    @TableField("number_of_month")
    private Integer numberOfMonth;
    /**
     * 付款方式 1.自动付款 0 审核付款
     */
    @TableField("pay_way")
    private Integer payWay;
    /**
     * 是否实时分润
     */
    @TableField("is_real_time")
    private Integer isRealTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyMerchantId() {
        return applyMerchantId;
    }

    public void setApplyMerchantId(String applyMerchantId) {
        this.applyMerchantId = applyMerchantId;
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

    public Integer getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public String getWithdrawStatusCn() {
        return withdrawStatusCn;
    }

    public void setWithdrawStatusCn(String withdrawStatusCn) {
        this.withdrawStatusCn = withdrawStatusCn;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatusCn() {
        return approveStatusCn;
    }

    public void setApproveStatusCn(String approveStatusCn) {
        this.approveStatusCn = approveStatusCn;
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getManualDeduction() {
        return manualDeduction;
    }

    public void setManualDeduction(BigDecimal manualDeduction) {
        this.manualDeduction = manualDeduction;
    }

    public Integer getNumberOfMonth() {
        return numberOfMonth;
    }

    public void setNumberOfMonth(Integer numberOfMonth) {
        this.numberOfMonth = numberOfMonth;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getIsRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(Integer isRealTime) {
        this.isRealTime = isRealTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WithdrawApplyRecord{" +
        "id=" + id +
        ", applyTime=" + applyTime +
        ", applyMerchantId=" + applyMerchantId +
        ", preTaxAmount=" + preTaxAmount +
        ", aftTaxAmount=" + aftTaxAmount +
        ", withdrawStatus=" + withdrawStatus +
        ", withdrawStatusCn=" + withdrawStatusCn +
        ", approver=" + approver +
        ", approveTime=" + approveTime +
        ", approveStatus=" + approveStatus +
        ", approveStatusCn=" + approveStatusCn +
        ", approveComment=" + approveComment +
        ", shouldPayTax=" + shouldPayTax +
        ", payer=" + payer +
        ", payTime=" + payTime +
        ", invoiceNo=" + invoiceNo +
        ", manualDeduction=" + manualDeduction +
        ", numberOfMonth=" + numberOfMonth +
        ", payWay=" + payWay +
        ", isRealTime=" + isRealTime +
        "}";
    }
}
