package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 结算状诚
 * @author Alan.huang
 *
 */
public enum SettlementTypeEnum {
	//real time settlement
	REALTIMESETTLEMENT(0, "实时结算"),
	OFFLINESETTLEMENT(1,"线下结算"),
	MONTHSETTLEMENT(2,"月结算");
	
	
	
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

	SettlementTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (SettlementTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
