package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 充电器类型。。
 * @author Alan.huang
 *
 */
public enum ChargerTypeEnum {
	AC01_PW_01(101, "AC01-1", "密码充电器AC01-01"),//密码充电器AC01-01
	AC02_PW_01(201, "AC02-1", "按磨充电器AC02-01");//按磨充电器AC02-01
	private Integer code;
	private String desc;
	// 大类
	private String category;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	ChargerTypeEnum(Integer code, String desc, String category) {
		this.code = code;
		this.desc = desc;
		this.category = category;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ChargerTypeEnum bn : values()) {
			if (null != code && code.intValue()==bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
	
	public static String getDescAndCategory(Integer code) {
		String descCategory = "";
		for (ChargerTypeEnum bn : values()) {
			if (code != null && code.intValue()==bn.getCode().intValue()) {
				descCategory = bn.getDesc()+bn.getCategory();
				break;
			}
		}
		return descCategory;
	}
}