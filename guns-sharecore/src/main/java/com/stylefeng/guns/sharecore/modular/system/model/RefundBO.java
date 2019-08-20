package com.stylefeng.guns.sharecore.modular.system.model;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 * @author
 *
 */
public class RefundBO extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3150070509353591669L;
	public static final int FAIL= 1;
	public static final int SUCCESS= 2;
	public static final int PARTIAL_SUCCESS= 4;
	public static final int PROCESSING= 3;
	private Set<Long> refundCapitalChangeIds =  new HashSet<Long>();
	//退款交易记录ID
	private Long refundId;
	
	//被退款的充值交易的总金额
	private BigDecimal totalAmt;
	
	//退款金额
	private BigDecimal refundAmt;
	
	//可退金额
	private BigDecimal remainRefundAmt;
	
	//退款weixin id
	private Long tradeOutNo;
	
	//原交易weixin id
	private Long originalTradeOutNo;
	
	private Integer channelType;
	
	private int returnRlst;
	
	public int isReturnRlst() {
		return returnRlst;
	}
	public void setReturnRlst(int returnRlst) {
		this.returnRlst = returnRlst;
	}
	public void setRefundCapitalChangeIds(Long refundCapitalChangeId) {
		this.refundCapitalChangeIds.add(refundCapitalChangeId);
	}
	//被退款的充值交易ID
	private Long tradeId;
	
	private Long custNo;

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public BigDecimal getRemainRefundAmt() {
		return remainRefundAmt;
	}

	public void setRemainRefundAmt(BigDecimal remainRefundAmt) {
		this.remainRefundAmt = remainRefundAmt;
	}

	public Long getTradeOutNo() {
		return tradeOutNo;
	}

	public void setTradeOutNo(Long tradeOutNo) {
		this.tradeOutNo = tradeOutNo;
	}

	public Long getOriginalTradeOutNo() {
		return originalTradeOutNo;
	}

	public void setOriginalTradeOutNo(Long originalTradeOutNo) {
		this.originalTradeOutNo = originalTradeOutNo;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Set<Long> getRefundCapitalChangeIds() {
		return refundCapitalChangeIds;
	}
	
}
