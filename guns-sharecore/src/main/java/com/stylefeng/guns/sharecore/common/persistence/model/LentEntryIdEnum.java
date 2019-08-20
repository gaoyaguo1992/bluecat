package com.stylefeng.guns.sharecore.common.persistence.model;
//租借实体..
public enum LentEntryIdEnum {
	
	ZFB(1,"支付宝生活窗"),
	ZFB_APP(2,"支付宝小程序"),
	WX(3,"微信公众号"),
	WX_APP(4,"微信小程序"),
	MCH_WX(5,"商户公众号");
	
	private Integer code;
	private String desc;

	public int getCode() {
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

	LentEntryIdEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		if(code ==null) {
			return desc;
		}
		for (LentEntryIdEnum bn : values()) {
			if (code.intValue()==bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
