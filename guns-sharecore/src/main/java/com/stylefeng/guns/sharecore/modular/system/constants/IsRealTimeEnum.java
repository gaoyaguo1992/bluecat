package com.stylefeng.guns.sharecore.modular.system.constants;

public enum IsRealTimeEnum {

	
	T_PLUS_1(10,"T+1次日分润"),
	T_PLUS_0(11,"T+0实时分润")
	;
	private Integer code;
	
	private String desc;
	
	IsRealTimeEnum(Integer code, String desc) {
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
		for (IsRealTimeEnum isRealTimeEnum : values()) {
			if (code.equals(isRealTimeEnum.getCode())) {
				desc = isRealTimeEnum.getDesc();
				break;
			}
		}
		return desc;
	}
}
