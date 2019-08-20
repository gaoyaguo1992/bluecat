package com.stylefeng.guns.sharecore.modular.system.model;

public enum CustAccountAmtSumEnum {
	SUMED(1,"已经统计"),
	NOME_SUM(2,"未统计");
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
	CustAccountAmtSumEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public static String getDesc(Integer code){
		String desc = "";
		for (CustAccountAmtSumEnum bn : values()) {
			if (code.intValue()==bn.getCode() ) {
				desc = bn.getName();
				break;
			}
		}
		return desc;
	}
}
