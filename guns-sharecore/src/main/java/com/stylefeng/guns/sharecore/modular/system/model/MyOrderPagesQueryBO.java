package com.stylefeng.guns.sharecore.modular.system.model;

/**
 * 
 * 我的订单分页查询业务对象
 * 
 */
public class MyOrderPagesQueryBO extends StartRowsPageBO {
	
	private static final long serialVersionUID = 5909344545541962742L;
	/**
	 * 客户号
	 */
	private Long custNo;
	/**
	 * 订单状态
	 */
	private Integer status;
	
	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
