package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备激活模式
 * @author Alan.huang
 *
 */
public enum ShareDeviceActivationModeEnum {
	activation(2, "简单模式"),
	waitForConfirmationStatus(1, "商户扫码确认模式");
	
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

	ShareDeviceActivationModeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到所以设备状态..
	 * @return
	 */
	public static List<ShareDeviceActivationModeEnum> getShareDeviceStatusEnumList(){
		List<ShareDeviceActivationModeEnum> list=new ArrayList<ShareDeviceActivationModeEnum>();
		for (ShareDeviceActivationModeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareDeviceActivationModeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
