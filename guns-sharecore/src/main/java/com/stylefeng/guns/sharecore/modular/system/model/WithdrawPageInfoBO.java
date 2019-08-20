package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.List;


/**
 * 
 * alan li
 */
public class WithdrawPageInfoBO {
	private String result;
	private List<WithdrawTradeInfoBO> withdrawTradeInfoBOs;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<WithdrawTradeInfoBO> getWithdrawTradeInfoBOs() {
		return withdrawTradeInfoBOs;
	}
	public void setWithdrawTradeInfoBOs(List<WithdrawTradeInfoBO> withdrawTradeInfoBOs) {
		this.withdrawTradeInfoBOs = withdrawTradeInfoBOs;
	}
	 
	
}
