
package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;


public class CustAccountBO extends BaseObject{

	private static final long serialVersionUID = -6006554076456444634L;
	
	private BigDecimal availableBalance;
	
	private Long custNo;

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}
	
}