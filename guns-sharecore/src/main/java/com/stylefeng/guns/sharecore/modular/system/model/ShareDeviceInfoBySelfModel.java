package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceStatusEnum;

public class ShareDeviceInfoBySelfModel extends ShareDeviceInfoModel {
	/**
	 * 设备状态名
	 */
	private String deviceStatusCN;
	/**
	 * 费用描述
	 */
	private String feeDescription;

	public String getFeeDescription() {
		return feeDescription;
	}

	public void setFeeDescription(String feeDescription) {
		this.feeDescription = feeDescription;
	}

	public String getDeviceStatusCN() {
		return ShareDeviceStatusEnum.getDesc(getDeviceStatus());
	}
}