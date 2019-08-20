package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

public class RefundTradeRefModelKey extends BaseObject{
    /**
	 * TODO
	 */
	private static final long serialVersionUID = -33257954084148359L;

	private Long refundId;

    private Long tradeId;

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

   
   
}