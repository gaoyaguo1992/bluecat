package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
public class RefundTradeRefModel extends RefundTradeRefModelKey {
    /**
	 * TODO
	 */
	private static final long serialVersionUID = -9166210646325232798L;

	private Date createdDate;

    private BigDecimal refundAmount;

    private Long refundTradeOutNo;
    
    private Long rechargeTradeOutNo;
    
    private Long chcrOutTradeNo;
    
    private Long custNo;
    
    public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Long getRefundTradeOutNo() {
		return refundTradeOutNo;
	}

	public void setRefundTradeOutNo(Long refundTradeOutNo) {
		this.refundTradeOutNo = refundTradeOutNo;
	}

	 
	public Long getRechargeTradeOutNo() {
		return rechargeTradeOutNo;
	}

	public void setRechargeTradeOutNo(Long rechargeTradeOutNo) {
		this.rechargeTradeOutNo = rechargeTradeOutNo;
	}

	public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

	public Long getChcrOutTradeNo() {
		return chcrOutTradeNo;
	}

	public void setChcrOutTradeNo(Long chcrOutTradeNo) {
		this.chcrOutTradeNo = chcrOutTradeNo;
	}
}