package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 账户类型
 * @author Alan.huang
 *
 */
public enum CustAccountTypeEnum {
	RECHARGERACCOUNT(1,"充值金额"),
	FREEZEACCOUNT(2,"冻结账户"),
	INTEGRALACCOUNT(3,"交易积分");
	
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
	CustAccountTypeEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public static String getDesc(Integer code){
		String desc = "";
		for (CustAccountTypeEnum bn : values()) {
			if (code.intValue()==bn.getCode() ) {
				desc = bn.getName();
				break;
			}
		}
		return desc;
	}
}
