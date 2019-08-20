package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 结算状诚
 * @author Alan.huang
 *
 */
public enum ShareSettleAccountsStatusEnum {
	UnFinishSettleAccount(0, "未结账"),
	FinishSettleAccount(1,"已结账");
	
	
	
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

	ShareSettleAccountsStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareSettleAccountsStatusEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
