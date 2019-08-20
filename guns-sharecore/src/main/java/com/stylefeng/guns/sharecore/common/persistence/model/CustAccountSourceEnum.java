package com.stylefeng.guns.sharecore.common.persistence.model;

public enum CustAccountSourceEnum {WEIXIN(1,"微信"),
	ZFB(2,"支付宝"),
	SERVICE_PT(3,"平台");
	private Integer code;
	private String name;
	public int getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	CustAccountSourceEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public static String getDesc(Integer code){
		String desc = "";
		for (CustAccountSourceEnum bn : values()) {
			if (code.intValue()==bn.getCode() ) {
				desc = bn.getName();
				break;
			}
		}
		return desc;
	}
}
