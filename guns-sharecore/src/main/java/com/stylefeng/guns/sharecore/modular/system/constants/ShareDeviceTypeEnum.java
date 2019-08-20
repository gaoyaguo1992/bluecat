package com.stylefeng.guns.sharecore.modular.system.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备类型
 * 
 * @author Alan.huang
 *
 */
public enum ShareDeviceTypeEnum {
	DE_10(10, "密码设备(CDQ10-01)", 1001), 
	DE_11(11, "密码设备(CDQ11-01)", 1002),
	DE_12(12, "密码设备(CDQ12-01)", 1001),
	DE_20(20, "密码设备(ZDQ20-01)", 1003),//海南
	DE_21(21, "密码设备(YDQC-01A)", 1004), //海南
	DE_30(30, "密码设备(CDQ30-01)", 1001), 
	DE_31(31, "密码设备(CDQ31-01)", 1002), 
	DE_32(32, "密码设备(CDQ32-01)", 1001), 
	DE_33(33, "密码设备(CDQ33-01)", 1005),
	DE_40(40, "祥充密码设备(CDQ40-01)", 1001);
	

	private Integer code;
	private String desc;
	private int passwordType;

	public int getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(int passwordType) {
		this.passwordType = passwordType;
	}

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

	ShareDeviceTypeEnum(Integer code, String desc, int passwordType) {
		this.code = code;
		this.desc = desc;
		this.passwordType = passwordType;
	}

	/**
	 * 得到所有的id..
	 * 
	 * @return
	 */
	public static List<ShareDeviceTypeEnum> getShareDeviceTypeEnumList() {
		List<ShareDeviceTypeEnum> list = new ArrayList<>();
		for (ShareDeviceTypeEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}

	/**
	 * 判断设备类型id及充电器类型id是否一直..
	 * 
	 * @param deviceTypeId
	 * @param chargerTypeId
	 * @return
	 */
	public static Boolean validateDeviceTypeIdWithChargerTypeId(Integer deviceTypeId, Integer chargerTypeId) {
		if (chargerTypeId == null) {
			return false;
		}
		if (ShareChargerTypeEnum.AD_10.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_10.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.AD_11.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_11.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.AD_12.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_12.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.SD_20.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_20.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.SD_21.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_21.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.ZD_30.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_30.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.ZD_31.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_31.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.ZD_32.getCode() == chargerTypeId.intValue()) {
			if (deviceTypeId == null || deviceTypeId.intValue() != ShareDeviceTypeEnum.DE_32.getCode()) {
				return false;
			}
		}
		if (ShareChargerTypeEnum.ZD_33.getCode() == chargerTypeId.intValue()) {
            return deviceTypeId != null && deviceTypeId.intValue() == ShareDeviceTypeEnum.DE_33.getCode();
		}
		if (ShareChargerTypeEnum.ZD_40.getCode() == chargerTypeId.intValue()) {
            return deviceTypeId != null && deviceTypeId.intValue() == ShareDeviceTypeEnum.DE_40.getCode();
		}
		return true;
	}

	/**
	 * 根据充电器类型返回得到设备类型对象..
	 * 
	 * @param deviceType
	 * @return
	 */
	public static ShareDeviceTypeEnum getShareDeviceTypeEnumByChargerTypeId(int chargerTypeId) {
		if (ShareChargerTypeEnum.AD_10.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_10;
		} else if (ShareChargerTypeEnum.AD_11.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_11;
		} else if (ShareChargerTypeEnum.AD_12.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_12;
		} else if (ShareChargerTypeEnum.SD_20.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_20;
		} else if (ShareChargerTypeEnum.SD_21.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_21;
		} else if (ShareChargerTypeEnum.ZD_30.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_30;
		} else if (ShareChargerTypeEnum.ZD_31.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_31;
		} else if (ShareChargerTypeEnum.ZD_32.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_32;
		}else if (ShareChargerTypeEnum.ZD_33.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_33;
		}else if (ShareChargerTypeEnum.ZD_40.getCode() == chargerTypeId) {
			return ShareDeviceTypeEnum.DE_40;
		}
		return ShareDeviceTypeEnum.DE_10;
	}

	/**
	 * 描述。。
	 * 
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareDeviceTypeEnum bn : values()) {
			if (null != code) {
				if (code.intValue() == bn.getCode()) {
					desc = bn.getDesc();
					break;
				}
			}
		}
		return desc;
	}

	/**
	 * 根据devicetypeid得到设备类型。。
	 * @param code
	 * @return
	 */
	public static ShareDeviceTypeEnum getShareDeviceTypeEnumByCode(Integer code) {

		for (ShareDeviceTypeEnum bn : values()) {
			if (null != code) {
				if (code.intValue() == bn.getCode()) {
					return bn;
				}
			}
		}
		return null;
	}
}
