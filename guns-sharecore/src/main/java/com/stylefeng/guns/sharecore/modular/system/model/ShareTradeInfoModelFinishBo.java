package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShareTradeInfoModelFinishBo extends ShareTradeInfoModel {
	/**
	 * 结束订单扣款金额
	 */
    private BigDecimal tradeAmoutForFinish;
    /**
     * 结束订单备注
     */
    private String remarkForFinish;
    /**
     * 退款金额
     */
    private BigDecimal tradeAmoutForBack;
    /**
     * 退款备注
     */
    private String remarkForBack;
	public BigDecimal getTradeAmoutForFinish() {
		return tradeAmoutForFinish;
	}
	public void setTradeAmoutForFinish(BigDecimal tradeAmoutForFinish) {
		this.tradeAmoutForFinish = tradeAmoutForFinish;
	}
	public String getRemarkForFinish() {
		return remarkForFinish;
	}
	public void setRemarkForFinish(String remarkForFinish) {
		this.remarkForFinish = remarkForFinish;
	}
	public BigDecimal getTradeAmoutForBack() {
		return tradeAmoutForBack;
	}
	public void setTradeAmoutForBack(BigDecimal tradeAmoutForBack) {
		this.tradeAmoutForBack = tradeAmoutForBack;
	}
	public String getRemarkForBack() {
		return remarkForBack;
	}
	public void setRemarkForBack(String remarkForBack) {
		this.remarkForBack = remarkForBack;
	}
}