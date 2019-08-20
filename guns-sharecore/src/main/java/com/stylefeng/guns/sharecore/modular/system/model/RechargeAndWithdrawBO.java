package com.stylefeng.guns.sharecore.modular.system.model;


/**
 * 
 * <P>流水记录查询业务对象</P>
 */
public class RechargeAndWithdrawBO extends StartRowsPageBO {
	
	private static final long serialVersionUID = 5669091931541962742L;

	private Long custNo;

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

}
