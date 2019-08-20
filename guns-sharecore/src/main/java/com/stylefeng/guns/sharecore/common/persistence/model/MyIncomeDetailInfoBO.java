package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 收益明细BO
 *
 */
public class MyIncomeDetailInfoBO {
	
	// 收益金额
	private BigDecimal incomeAmount;
	
	private String incomeAmountStr;
	
	// 可结算金额
	private BigDecimal doneAmount;
	
	// 收益类型
	private String incomeTypeDesc;
	
	// 收益描述
	private String incomeDesc;
	
	// 商户ID
	private Long merchantId;
	
	// 分润详情
	private String ratio;
	
	// 收益时间
	private Date incomeDateTime;
	
	// 收益时间格式化
	private String incomeDateTimeFormat;
	
	// 产品ID
	private Long ratioId;
	
	// 代理商分润明细
	private String agentsRatio;
	
	// 加盟商分润明细
	private String franchiseeRatio;
	
	// 铺货商分润明细
	private String shopkeeperRatio;
	
	// 铺货商地推分润明细
	private String shopkeeperAgencyRatio;
	
	// 铺货商店铺分润明细
	private String shopkeeperOperatorRatio;
	
	// 分润明细
	private String shareRatio;
	
	// 铺货商子公司分润明细
	private String shopkeeperSubRatio;

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public BigDecimal getDoneAmount() {
		return doneAmount;
	}

	public void setDoneAmount(BigDecimal doneAmount) {
		this.doneAmount = doneAmount;
	}

	public String getIncomeDesc() {
		return incomeDesc;
	}

	public void setIncomeDesc(String incomeDesc) {
		this.incomeDesc = incomeDesc;
	}

	public Date getIncomeDateTime() {
		return incomeDateTime;
	}

	public void setIncomeDateTime(Date incomeDateTime) {
		this.incomeDateTime = incomeDateTime;
	}

	public String getIncomeDateTimeFormat() {
		return incomeDateTimeFormat;
	}

	public void setIncomeDateTimeFormat(String incomeDateTimeFormat) {
		this.incomeDateTimeFormat = incomeDateTimeFormat;
	}

	public String getIncomeTypeDesc() {
		return incomeTypeDesc;
	}

	public void setIncomeTypeDesc(String incomeTypeDesc) {
		this.incomeTypeDesc = incomeTypeDesc;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public Long getRatioId() {
		return ratioId;
	}

	public void setRatioId(Long ratioId) {
		this.ratioId = ratioId;
	}

	public String getAgentsRatio() {
		return agentsRatio;
	}

	public void setAgentsRatio(String agentsRatio) {
		this.agentsRatio = agentsRatio;
	}

	public String getShopkeeperRatio() {
		return shopkeeperRatio;
	}

	public void setShopkeeperRatio(String shopkeeperRatio) {
		this.shopkeeperRatio = shopkeeperRatio;
	}

	public String getShopkeeperAgencyRatio() {
		return shopkeeperAgencyRatio;
	}

	public void setShopkeeperAgencyRatio(String shopkeeperAgencyRatio) {
		this.shopkeeperAgencyRatio = shopkeeperAgencyRatio;
	}

	public String getShopkeeperOperatorRatio() {
		return shopkeeperOperatorRatio;
	}

	public void setShopkeeperOperatorRatio(String shopkeeperOperatorRatio) {
		this.shopkeeperOperatorRatio = shopkeeperOperatorRatio;
	}


	public String getShopkeeperSubRatio() {
		return shopkeeperSubRatio;
	}

	public void setShopkeeperSubRatio(String shopkeeperSubRatio) {
		this.shopkeeperSubRatio = shopkeeperSubRatio;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getFranchiseeRatio() {
		return franchiseeRatio;
	}

	public void setFranchiseeRatio(String franchiseeRatio) {
		this.franchiseeRatio = franchiseeRatio;
	}

	public String getIncomeAmountStr() {
		return incomeAmountStr;
	}

	public void setIncomeAmountStr(String incomeAmountStr) {
		this.incomeAmountStr = incomeAmountStr;
	}

	public String getShareRatio() {
		return shareRatio;
	}

	public void setShareRatio(String shareRatio) {
		this.shareRatio = shareRatio;
	}
	
}
