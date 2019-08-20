package com.stylefeng.guns.sharecore.common.persistence.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * 设备类型
 * @author Alan.huang
 */
public enum DeviceTypeEnum {
	AC01_PW_CDG(10, "XNQC-01", "密码充电器",Arrays.asList(1)),
	AC02_PW_CDG(20, "XNQC-02", "按摩充电器",Arrays.asList(101)),
	AC03_PW_CDG(30, "XNQC-03", "GPRS充电器",Arrays.asList(301));
	
	private Integer code;
	private String desc;
	// 大类
	private String category;
	// 该设备类型可支持的充电器类型
	private List<Integer> chargerTypes;

	public Integer getCode() {
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

	public String getCategory() {
		return category;
	}
	
	public static String getCategory(Integer code) {
		String category = "";
		for (DeviceTypeEnum bn : values()) {
			if (code != null && code.intValue()==bn.getCode()) {
				category = bn.getCategory();
				break;
			}
		}
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	DeviceTypeEnum(Integer code, String desc, String category, List<Integer> chargerTypes) {
		this.code = code;
		this.desc = desc;
		this.category = category;
		this.chargerTypes = chargerTypes;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (DeviceTypeEnum bn : values()) {
			if (code != null && code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
	
	public static Integer getCode(String desc) {
		Integer code = new Integer(0);
		for (DeviceTypeEnum bn : values()) {
			if (desc != null && desc.equals(bn.getDesc())) {
				code = bn.getCode();
				break;
			}
		}
		return code;
	}
	
	public static String getDescAndCategory(Integer code) {
		String descCategory = "";
		for (DeviceTypeEnum bn : values()) {
			if (code != null && code.intValue() == bn.getCode()) {
				descCategory = bn.getDesc() + bn.getCategory();
				break;
			}
		}
		return descCategory;
	}

	public static List<Integer> getDevTypeMatchChargerTypes(Integer devType) {
		List<Integer> chargerTypes =new ArrayList<Integer>();
		for (DeviceTypeEnum bn : values()) {
			if (devType!=null && devType.intValue() == bn.getCode().intValue()) {
				chargerTypes = bn.getChargerTypes();
				break;
			}
		}
		return chargerTypes;
	}

	public void setChargerTypes(List<Integer> chargerTypes) {
		this.chargerTypes = chargerTypes;
	}

	public List<Integer> getChargerTypes() {
		return chargerTypes;
	}
}
