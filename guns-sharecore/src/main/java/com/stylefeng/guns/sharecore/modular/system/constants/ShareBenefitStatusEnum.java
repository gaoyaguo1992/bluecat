package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 交易类型
 * @author Alan.huang
 *
 */
public enum ShareBenefitStatusEnum {
	UnFinishDistribution(0, "未分润"),
	FinishDistribution(1,"已分润");
	
	
	
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

	ShareBenefitStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareBenefitStatusEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
