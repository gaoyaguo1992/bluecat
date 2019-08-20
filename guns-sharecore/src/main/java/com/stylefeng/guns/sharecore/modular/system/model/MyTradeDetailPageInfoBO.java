package com.stylefeng.guns.sharecore.modular.system.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 *
 */
public class MyTradeDetailPageInfoBO {
	
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -2323788164605486013L;
	/**
	 * 返回成功，还是失败
	 */
	private String result;
	/**
	 * 返回信息
	 */
	private String message;
	
	private List<MyTradeDetailInfoBO> myTradeDetailInfoBOs;
	//交易次数
	private Integer tradeCount;
	//交易金额
	private BigDecimal tradeAmount;
	//历史交易总预付金额；
	private BigDecimal yfjAmount;
	//收益总金额
	private BigDecimal profitAmount;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<MyTradeDetailInfoBO> getMyTradeDetailInfoBOs() {
		if(myTradeDetailInfoBOs==null){
			myTradeDetailInfoBOs=new ArrayList<>();
		}
		return myTradeDetailInfoBOs;
	}
	public void setMyTradeDetailInfoBOs(List<MyTradeDetailInfoBO> myTradeDetailInfoBOs) {
		this.myTradeDetailInfoBOs = myTradeDetailInfoBOs;
	}
	public Integer getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
	}
	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public BigDecimal getYfjAmount() {
		return yfjAmount;
	}
	public void setYfjAmount(BigDecimal yfjAmount) {
		this.yfjAmount = yfjAmount;
	}
	public BigDecimal getProfitAmount() {
		return profitAmount;
	}
	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}
	
}
