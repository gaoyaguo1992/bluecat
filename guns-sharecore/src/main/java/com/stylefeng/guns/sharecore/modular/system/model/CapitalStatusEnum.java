package com.stylefeng.guns.sharecore.modular.system.model;

public enum CapitalStatusEnum {
	PROCESSING(10, "处理中"), 
	SUCCESS(11, "成功"), 
	FAILURE(12, "失败"),
	UNKNOW(0,"未知");
	
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

	CapitalStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (CapitalStatusEnum bn : values()) {
			if (code.intValue()==bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
