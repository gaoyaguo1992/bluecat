package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备类型
 * @author Alan.huang
 *
 */
public enum MerchantApplyFormStatusEnum {
	noDo(0, "未处理"),
	done(1, "已联系");
	
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

	MerchantApplyFormStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到所以设备状态..
	 * @return
	 */
	public static List<MerchantApplyFormStatusEnum> getStatusEnumList(){
		List<MerchantApplyFormStatusEnum> list=new ArrayList<MerchantApplyFormStatusEnum>();
		for (MerchantApplyFormStatusEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (MerchantApplyFormStatusEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
