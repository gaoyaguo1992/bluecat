package com.stylefeng.guns.sharecore.modular.system.constants;


public enum PayWayEnum {

	AUTO_PAY(11,"自动付款"),
	AUDIT_PAY(10,"审核付款"),
	IMMEDIATE_PAY(12,"直接扣款付款"),
	UNKNOW_PAY(-1,"未知付款");
    private Integer code;
	
	private String desc;
	
	PayWayEnum(Integer code, String desc) {
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
		for (PayWayEnum payWayEnum : values()) {
			if (code.equals(payWayEnum.getCode())) {
				desc = payWayEnum.getDesc();
				break;
			}
		}
		return desc;
	}
}
