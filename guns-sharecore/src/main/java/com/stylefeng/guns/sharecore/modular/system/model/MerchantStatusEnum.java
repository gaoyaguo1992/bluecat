/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.List;

/**
 *商户状态
 */
public enum MerchantStatusEnum {

	REGISTED(21, "已注册"), 
	APPLYED(20, "已申请"),
	REGISTED_OUT(22, "已注销");
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

	MerchantStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到商户状态
	 * @return
	 */
	public static List<MerchantStatusEnum> getMerchantStatusEnum() {
		List<MerchantStatusEnum> list=new ArrayList<MerchantStatusEnum>();
		for (MerchantStatusEnum bn : values()) {
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
		for (MerchantStatusEnum bn : values()) {
			if (code != null && code.intValue() == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
