package com.stylefeng.guns.sharecore.modular.system.model;

/**
 *
 */
public enum TradeStatusEnum {
	PROCESSING(30, "处理中"), 
	SUCCESS(31, "成功"),
	FAILURE(32, "失败"),	 
	EXCEPTION(99,"异常")	;
	
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

	TradeStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (TradeStatusEnum bn : values()) {
			if (code!=null&&code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
