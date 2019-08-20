package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 充电器状态
 * @author Alan.huang
 *
 */
public enum ShareChargerStatusEnum {
	Unlent(10, "未借出"),
	Lent(11, "已借出"),
	Broken(12,"坏了"),
	Lose(13,"已丢失");
	
	
	
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

	ShareChargerStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareChargerStatusEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
