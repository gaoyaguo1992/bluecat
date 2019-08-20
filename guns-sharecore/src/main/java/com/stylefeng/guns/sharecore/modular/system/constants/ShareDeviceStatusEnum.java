package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备类型
 * @author Alan.huang
 *
 */
public enum ShareDeviceStatusEnum {
	inactivation(11, "未激活"),
	activation(10, "已激活"),
	waitForConfirmationStatus(12, "等待终端店铺扫码确认");
	
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

	ShareDeviceStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到所以设备状态..
	 * @return
	 */
	public static List<ShareDeviceStatusEnum> getShareDeviceStatusEnumList(){
		List<ShareDeviceStatusEnum> list=new ArrayList<ShareDeviceStatusEnum>();
		for (ShareDeviceStatusEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareDeviceStatusEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
