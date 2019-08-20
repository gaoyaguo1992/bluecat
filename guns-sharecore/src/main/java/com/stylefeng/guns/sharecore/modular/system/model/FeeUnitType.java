package com.stylefeng.guns.sharecore.modular.system.model;

public enum FeeUnitType {
	CNY(10, "CNY");
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

	FeeUnitType(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (FeeUnitType bn : values()) {
			if (code.intValue() == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
