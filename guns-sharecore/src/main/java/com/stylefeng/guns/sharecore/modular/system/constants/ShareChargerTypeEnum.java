package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 充电器类型
 * @author Alan.huang
 *
 */
public enum ShareChargerTypeEnum {
	AD_10(10, "密码充电器(CDQ10-01)"),
	AD_11(11, "密码充电器(CDQ11-01)"),
	AD_12(12, "密码充电器(CDQ12-01)"),
	SD_20(20, "密码充电器(ZDQ20-01)"),
	SD_21(21, "密码充电器(YDQC-01A)"),
	ZD_30(30, "密码充电器(CDQ30-01)"),
	ZD_31(31, "密码充电器(CDQ31-01)"),
	ZD_32(32, "密码充电器(CDQ32-01)"),
	ZD_33(33, "密码充电器(CDQ33-01)"),
	ZD_40(40, "祥充密码充电器(CDQ40-01)");
	
	
	
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

	ShareChargerTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 根据设备id得到设备类型对象..
	 * @param deviceType
	 * @return
	 */
	public static ShareChargerTypeEnum getShareChargerTypeEnumByDeviceTypeId(int deviceType){
		if(ShareDeviceTypeEnum.DE_10.getCode()==deviceType){
			return ShareChargerTypeEnum.AD_10;
		}else if(ShareDeviceTypeEnum.DE_11.getCode()==deviceType){
			return ShareChargerTypeEnum.AD_11;
		}else if(ShareDeviceTypeEnum.DE_12.getCode()==deviceType){
			return ShareChargerTypeEnum.AD_12;
		}else if(ShareDeviceTypeEnum.DE_20.getCode()==deviceType){
			return ShareChargerTypeEnum.SD_20;
		}else if(ShareDeviceTypeEnum.DE_21.getCode()==deviceType){
			return ShareChargerTypeEnum.SD_21;	
		}else if(ShareDeviceTypeEnum.DE_30.getCode()==deviceType){
			return ShareChargerTypeEnum.ZD_30;	
		}else if(ShareDeviceTypeEnum.DE_31.getCode()==deviceType){
			return ShareChargerTypeEnum.ZD_31;	
		}else if(ShareDeviceTypeEnum.DE_32.getCode()==deviceType){
			return ShareChargerTypeEnum.ZD_32;	
		}else if(ShareDeviceTypeEnum.DE_33.getCode()==deviceType){
			return ShareChargerTypeEnum.ZD_33;	
		}else if(ShareDeviceTypeEnum.DE_40.getCode()==deviceType){
			return ShareChargerTypeEnum.ZD_40;	
		}
		return ShareChargerTypeEnum.AD_10;
	}
	/**
	 * 根据设备id得到设备类型对象..
	 * @param deviceType
	 * @return
	 */
	public static ShareDeviceTypeEnum getShareDeviceTypeEnumByChargerTypeId(int shareChargerType){
		if(ShareChargerTypeEnum.AD_10.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_10;
		}else if(ShareChargerTypeEnum.AD_11.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_11;
		}else if(ShareChargerTypeEnum.AD_12.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_12;
		}else if(ShareChargerTypeEnum.SD_20.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_20;
		}else if(ShareChargerTypeEnum.SD_21.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_21;	
		}else if(ShareChargerTypeEnum.ZD_30.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_30;	
		}else if(ShareChargerTypeEnum.ZD_31.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_31;	
		}else if(ShareChargerTypeEnum.ZD_32.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_32;	
		}else if(ShareChargerTypeEnum.ZD_33.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_33;	
		}else if(ShareChargerTypeEnum.ZD_40.getCode()==shareChargerType){
			return ShareDeviceTypeEnum.DE_40;	
		}
		return ShareDeviceTypeEnum.DE_10;
	}
	/**
	 * 得到充电器类型
	 * @param deviceType
	 * @return
	 */
	public static ShareChargerTypeEnum getShareChargerTypeEnumByCode(int code){
		for (ShareChargerTypeEnum bn : values()) {
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
	public static List<ShareChargerTypeEnum> getShareChargerTypeEnumList() {
		List<ShareChargerTypeEnum> list = new ArrayList<>();
		for (ShareChargerTypeEnum bn : values()) {
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
		for (ShareChargerTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
