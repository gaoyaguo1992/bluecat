package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

/**
 * 
 *
 */
public class FrozenCapitaInfoBO extends StartRowsPageBO {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1223232L;

	private Long merchantId;
	
	/**
	 * 根据开始时间查询交易明细
	 */
	private String beginDateTime;

	/**
	 * 根据结束时间查询交易明细
	 */
	private String endDateTime;
	
	private Date beginDate;
	
	private Date endDate;
	
	private Long custNo;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}
	
}
