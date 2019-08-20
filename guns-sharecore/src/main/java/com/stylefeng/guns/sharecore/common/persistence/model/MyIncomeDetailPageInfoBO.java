package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 *
 */
public class MyIncomeDetailPageInfoBO extends BaseObject {
	
	private String result;
	
	private List<MyIncomeDetailInfoBO> myIncomeDetailInfoBOs;
	
	private Integer incomeCount;
	
	private BigDecimal incomeAmount;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<MyIncomeDetailInfoBO> getMyIncomeDetailInfoBOs() {
		return myIncomeDetailInfoBOs;
	}
	public void setMyIncomeDetailInfoBOs(List<MyIncomeDetailInfoBO> myIncomeDetailInfoBOs) {
		this.myIncomeDetailInfoBOs = myIncomeDetailInfoBOs;
	}
	public Integer getIncomeCount() {
		return incomeCount;
	}
	public void setIncomeCount(Integer incomeCount) {
		this.incomeCount = incomeCount;
	}
	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	
}
