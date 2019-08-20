/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.List;

/**
 *商户状态
 */
public enum MerchantProfitFlagEnum {

	APPLYED(0, "实时分润"),
	REGISTED(1, "非实时分润");
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

	MerchantProfitFlagEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * @return
	 */
	public static List<MerchantProfitFlagEnum> getMerchantStatusEnum() {
		List<MerchantProfitFlagEnum> list=new ArrayList<MerchantProfitFlagEnum>();
		for (MerchantProfitFlagEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}
	/**
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (MerchantProfitFlagEnum bn : values()) {
			if (code != null && code.intValue() == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
