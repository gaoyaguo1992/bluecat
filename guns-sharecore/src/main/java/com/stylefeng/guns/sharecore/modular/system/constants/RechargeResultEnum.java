package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 充电状态 
 * @author Alan.huang
 *
 */
public enum RechargeResultEnum {
	 SUCCESS("SUCCESS", "成功"), FAILURE("FAIL", "失败");
	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	RechargeResultEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(String code) {
		String desc = "";
		for (RechargeResultEnum bn : values()) {
			if (code.equals(bn.getCode())) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
