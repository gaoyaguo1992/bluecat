
package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceActivationModeEnum;

/**
 * 
 * @author Alan.huang
 *
 */
public enum ShareDeviceInfoNumberTypeEnum {
	GENERAL(1, "通用模式"),
	YYMMBBXXXXX(2, "YYMMBBXXXXX模式");

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

	ShareDeviceInfoNumberTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到所以设备状态..
	 * @return
	 */
	public static List<ShareDeviceInfoNumberTypeEnum> getShareDeviceInfoNumberTypeEnumList(){
		List<ShareDeviceInfoNumberTypeEnum> list=new ArrayList<ShareDeviceInfoNumberTypeEnum>();
		for (ShareDeviceInfoNumberTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}
	/**
	 * 描述...
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareDeviceInfoNumberTypeEnum bn : values()) {
			if (null != code) {
				if (code.intValue()==bn.getCode()) {
					desc = bn.getDesc();
					break;
				}
			}
		}
		return desc;
	}
}
