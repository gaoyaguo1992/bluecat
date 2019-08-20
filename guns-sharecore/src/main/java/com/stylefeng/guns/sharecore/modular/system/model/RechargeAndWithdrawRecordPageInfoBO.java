package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.List;

/**
 * 
 * <P>流水记录分页业务对象</P>
 * 
 */
public class RechargeAndWithdrawRecordPageInfoBO {
	/**
	 * 提现结果..
	 */
	private String result;
	/**
	 * 提现信息 。。
	 */
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private List<RechargeAndWithdrawRecordInfoBO> rechargeAndWithdrawRecordInfoBOs;
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public List<RechargeAndWithdrawRecordInfoBO> getRechargeAndWithdrawRecordInfoBOs() {
		return rechargeAndWithdrawRecordInfoBOs;
	}
	
	public void setRechargeAndWithdrawRecordInfoBOs(
			List<RechargeAndWithdrawRecordInfoBO> rechargeAndWithdrawRecordInfoBOs) {
		this.rechargeAndWithdrawRecordInfoBOs = rechargeAndWithdrawRecordInfoBOs;
	}
	
}
