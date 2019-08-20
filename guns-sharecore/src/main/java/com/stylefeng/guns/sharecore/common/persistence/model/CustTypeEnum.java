package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 客户类型enum
 * @author Alan.huang
 *
 */
public enum CustTypeEnum {
	CUST(1,"客户"),
	MERCHANT(2,"商户"),
	SERVICE_PT(3,"服务平台");
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
	CustTypeEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public static String getDesc(Integer code){
		String desc = "";
		for (CustTypeEnum bn : values()) {
			if (code.intValue()==bn.getCode() ) {
				desc = bn.getName();
				break;
			}
		}
		return desc;
	}
}

