/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.List;

/**
 *商户状态
 */
public enum MerchantClientTypeEnum {
	// 10:个人，11：企业
	Personal(10, "个人"),
	Enterprise(11, "企业");
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

	MerchantClientTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到商户状态
	 * @return
	 */
	public static List<MerchantClientTypeEnum> getMerchantClientTypeEnum() {
		List<MerchantClientTypeEnum> list=new ArrayList<MerchantClientTypeEnum>();
		for (MerchantClientTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}
	/**
	 * 得到商户描述
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (MerchantClientTypeEnum bn : values()) {
			if (code != null && code.intValue() == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
