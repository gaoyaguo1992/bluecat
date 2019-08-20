package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.List;
/**
 * 商户订单查询...
 * @author Alan.huang
 *
 */
public class MerchantOrderRecordPageInfoBO {

	private String result;
	
	private List<MerchantOrderRecordInfoBO> merchantOrderRecordInfoBOs;
	
	private BigDecimal tradeTotalAmount;//交易总金额
	
	private Integer tradeTotalCount;//交易总数量
	
	private MerchantInfoModel  merchantInfoModel ; //查询者本人的商户信息
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public List<MerchantOrderRecordInfoBO> getMerchantOrderRecordInfoBOs() {
		return merchantOrderRecordInfoBOs;
	}

	public void setMerchantOrderRecordInfoBOs(List<MerchantOrderRecordInfoBO> merchantOrderRecordInfoBOs) {
		this.merchantOrderRecordInfoBOs = merchantOrderRecordInfoBOs;
	}

	public BigDecimal getTradeTotalAmount() {
		return tradeTotalAmount;
	}

	public void setTradeTotalAmount(BigDecimal tradeTotalAmount) {
		this.tradeTotalAmount = tradeTotalAmount;
	}

	public Integer getTradeTotalCount() {
		return tradeTotalCount;
	}

	public void setTradeTotalCount(Integer tradeTotalCount) {
		this.tradeTotalCount = tradeTotalCount;
	}

	public MerchantInfoModel getMerchantInfoModel() {
		return merchantInfoModel;
	}

	public void setMerchantInfoModel(MerchantInfoModel merchantInfoModel) {
		this.merchantInfoModel = merchantInfoModel;
	}
	
}
