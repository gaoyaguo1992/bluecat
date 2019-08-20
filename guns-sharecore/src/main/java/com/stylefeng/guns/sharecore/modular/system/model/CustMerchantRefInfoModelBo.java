package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class CustMerchantRefInfoModelBo extends CustMerchantRefInfoModel {
	/**
	 * 上次登录标志
	 */
	private Integer lastLoginFlag;
	/**
	 * 
	 */
	private Integer index;

	public Integer getLastLoginFlag() {
		return lastLoginFlag;
	}

	public void setLastLoginFlag(Integer lastLoginFlag) {
		this.lastLoginFlag = lastLoginFlag;
	}
	
}