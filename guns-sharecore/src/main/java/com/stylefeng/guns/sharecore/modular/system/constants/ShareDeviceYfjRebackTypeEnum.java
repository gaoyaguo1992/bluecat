package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备类型
 * @author Alan.huang
 *
 */
public enum ShareDeviceYfjRebackTypeEnum {
	RebackToBalance(2, "退回到账户余额"),
	RebackByRoad(1, "实时原路退回");
	
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

	ShareDeviceYfjRebackTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareDeviceYfjRebackTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
	/**
	 * 得到提现方式
	 * @return
	 */
	public static List<ShareDeviceYfjRebackTypeEnum>  getShareDeviceYfjRebackTypeEnum() {
		List<ShareDeviceYfjRebackTypeEnum> list=new ArrayList<>();
		for (ShareDeviceYfjRebackTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}
}
