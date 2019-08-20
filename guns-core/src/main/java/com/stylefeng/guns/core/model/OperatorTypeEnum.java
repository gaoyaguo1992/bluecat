package com.stylefeng.guns.core.model;


/**
 * 
 * <P>算数运算枚举类</P>
 */
public enum OperatorTypeEnum {

	ADD(10, "加"), 
	SUBTRACT(11, "减");
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

	OperatorTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (OperatorTypeEnum bn : values()) {
			if (code.intValue() == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
