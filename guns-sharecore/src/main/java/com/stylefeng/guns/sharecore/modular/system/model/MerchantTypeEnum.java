/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 商户类型
 * @author Alan.huang
 *
 */
public enum MerchantTypeEnum {
	TERMINAL(10, "终端商户"),
	DAI_LI_SHANG1(11, "顶级代理商"),
	DAI_LI_SHANG2(12, "一代级理商"),
	DAI_LI_SHANG3(13, "二代级理商"),
	PUHUO_SHANG(14, "铺货商"),
	JIA_MENG_SHANG(15, "加盟商");
	//PLATFORM(80, "平台自营")
	
	
	private Integer code;
	private String desc;

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

	MerchantTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 得到商户类型列表..
	 * @param code
	 * @return
	 */
	public static List<MerchantTypeEnum> getMerchantTypeEnum() {
		List<MerchantTypeEnum> list=new ArrayList<>();
		for (MerchantTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}
	/**
	 * 得到商户类型
	 * @param code
	 * @return
	 */
	public static MerchantTypeEnum getMerchantTypeEnumByCode(Integer code) {
		for (MerchantTypeEnum bn : values()) {
			if (code.intValue()==bn.getCode()) {
				return bn;
			}
		}
		return null;
	}
	/**
	 * 得到商户描述..
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (MerchantTypeEnum bn : values()) {
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
