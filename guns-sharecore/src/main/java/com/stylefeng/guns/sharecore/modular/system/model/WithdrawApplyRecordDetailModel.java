package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.constants.ApproveStatusEnum;


public class WithdrawApplyRecordDetailModel  extends WithdrawApplyRecordModel  {
	/**
	 * 商户名称
	 */
    private String merchantName;
    /**
     * 联系人名称
     */
    private String personName;
    /**
     * 联系人电话..
     */
    private String personTelNo;
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonTelNo() {
		return personTelNo;
	}
	public void setPersonTelNo(String personTelNo) {
		this.personTelNo = personTelNo;
	}
}