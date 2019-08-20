/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.model;

/**
 *
 */
public enum CapitalFlowTypeEnum {
	FREEZED(11, "冻结"),
	UNFREEZED(12, "解冻"), 
	OUT(13, "出金"),
	IN(17, "入金");
	
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

	CapitalFlowTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (CapitalFlowTypeEnum bn : values()) {
			if (code.intValue() == bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
