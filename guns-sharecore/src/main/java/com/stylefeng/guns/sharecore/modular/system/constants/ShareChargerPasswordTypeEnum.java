package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 充电器类型
 * @author Alan.huang
 *
 */
public enum ShareChargerPasswordTypeEnum {
	PW_10(1001, "5位数6位密码长度，15组密码不带时间 "),
	PW_11(1002, "5位数6位密码长度，15组密码带时间"),
	PW_20(1003, "4位数6位密码长度，15组密码不带时间"),
	PW_21(1004, "4位数6位密码长度，15组密码带时间 "),
	PW_30(1005, "5位数6位密码长度，15组密码支持带时间 ");
	
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

	ShareChargerPasswordTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 根据设备id得到设备类型对象..
	 * @param deviceType
	 * @return
	 */
	public static ShareChargerPasswordTypeEnum getShareChargerTypeEnumByDeviceTypeId(int deviceType){
		if(ShareChargerPasswordTypeEnum.PW_10.getCode()==deviceType){
			return ShareChargerPasswordTypeEnum.PW_10;
		}else if(ShareChargerPasswordTypeEnum.PW_11.getCode()==deviceType){
			return ShareChargerPasswordTypeEnum.PW_11;
		}else if(ShareChargerPasswordTypeEnum.PW_20.getCode()==deviceType){
			return ShareChargerPasswordTypeEnum.PW_20;
		}else if(ShareChargerPasswordTypeEnum.PW_21.getCode()==deviceType){
			return ShareChargerPasswordTypeEnum.PW_21;	
		}else if(ShareChargerPasswordTypeEnum.PW_30.getCode()==deviceType){
			return ShareChargerPasswordTypeEnum.PW_30;	
		}
		return ShareChargerPasswordTypeEnum.PW_10;
	}
	/**
	 * 得到充电器类型
	 * @param deviceType
	 * @return
	 */
	public static ShareChargerPasswordTypeEnum getShareChargerTypeEnumByCode(int code){
		for (ShareChargerPasswordTypeEnum bn : values()) {
			if (code==bn.getCode()) {
				return bn;
			}
		}
		return null;
	}
	
	/**
	 * 得到所有的id..
	 * 
	 * @return
	 */
	public static List<ShareChargerPasswordTypeEnum> getShareChargerTypeEnumList() {
		List<ShareChargerPasswordTypeEnum> list = new ArrayList<>();
		for (ShareChargerPasswordTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}

	/**
	 * 得到设备类型描述。。
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareChargerPasswordTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
