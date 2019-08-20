/**
 * 查询条件
 */
package com.stylefeng.guns.sharecore.modular.system.model;
/**
 * 查询利润...
 * @author Alan.huang
 *
 */
public class MerchantProfitQueryBO extends StartRowsPageBO {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = -1780292929296877312L;
	
	private Long merchantId;
	
	private String merchantType;
	
	private String beginDateTime;
	
	private String endDateTime;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	
}
