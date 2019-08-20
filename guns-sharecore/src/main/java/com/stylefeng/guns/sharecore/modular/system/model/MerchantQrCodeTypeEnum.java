/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过扫商户二维码，添加商户的类型
 * @author Alan.huang
 *
 */
public enum MerchantQrCodeTypeEnum {
	ADD_DAI_LI_SHANG2(1, "添加一级代理商"),
	ADD_DAI_LI_SHANG3(2, "添加二级代理商"),
	ADD_PUHUO_SHANG(3, "添加铺货商"),
	ADD_JIA_MENG_SHANG(4, "添加加盟商"),
	ADD_TERMINAL(5,"添加终端商户");
	
	
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

	MerchantQrCodeTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 得到商户类型列表..
	 * @param code
	 * @return
	 */
	public static List<MerchantQrCodeTypeEnum> getMerchantTypeEnum() {
		List<MerchantQrCodeTypeEnum> list=new ArrayList<>();
		for (MerchantQrCodeTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}
	/**
	 * 得到商户类型
	 * @param code
	 * @return
	 */
	public static MerchantQrCodeTypeEnum getMerchantTypeEnumByCode(Integer code) {
		for (MerchantQrCodeTypeEnum bn : values()) {
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
		for (MerchantQrCodeTypeEnum bn : values()) {
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
