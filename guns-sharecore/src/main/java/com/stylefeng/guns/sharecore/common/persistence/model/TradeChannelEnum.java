package com.stylefeng.guns.sharecore.common.persistence.model;

/**
 * 交易渠道enum
 * @author Alan.huang
 */
public enum TradeChannelEnum {
	WX(1,"微信"),
	ZFB(2,"支付宝"),
	ZHIMA_CREDIT(3,"芝麻信用");
	
	private Integer code;
	
	private String desc;
	
	TradeChannelEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (TradeChannelEnum bn : values()) {
			if (code.intValue() == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}