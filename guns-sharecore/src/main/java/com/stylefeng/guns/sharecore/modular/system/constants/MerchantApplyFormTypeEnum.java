package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备类型
 * @author Alan.huang
 *
 */
public enum MerchantApplyFormTypeEnum {
	APPLYAGENT(0, "申请代理"),
	APPLYCOOPERATE (1, "申请合作");
	
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

	MerchantApplyFormTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到所以设备状态..
	 * @return
	 */
	public static List<MerchantApplyFormTypeEnum> getShareDeviceStatusEnumList(){
		List<MerchantApplyFormTypeEnum> list=new ArrayList<MerchantApplyFormTypeEnum>();
		for (MerchantApplyFormTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (MerchantApplyFormTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
