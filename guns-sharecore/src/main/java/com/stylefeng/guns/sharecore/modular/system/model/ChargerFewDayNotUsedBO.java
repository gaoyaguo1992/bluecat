package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceStatusEnum;

/**
 * 用于显示七天，三十天未使用充电器信息
 * 
 * @author seven
 *
 */
public class ChargerFewDayNotUsedBO {

	private Long chargerId;

	private Long deviceId;

	private Integer deviceStatus;

	private String deviceStatusCn;

	private String deviceTypeName;

	private Date onlineDateTime;

	private Date lastUseTime;

	private String onlineMerchantCn; // 商户

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public Date getOnlineDateTime() {
		return onlineDateTime;
	}

	public void setOnlineDateTime(Date onlineDateTime) {
		this.onlineDateTime = onlineDateTime;
	}

	public Date getLastUseTime() {
		return lastUseTime;
	}

	public void setLastUseTime(Date lastUseTime) {
		this.lastUseTime = lastUseTime;
	}

	public String getOnlineMerchantCn() {
		return onlineMerchantCn;
	}

	public void setOnlineMerchantCn(String onlineMerchantCn) {
		this.onlineMerchantCn = onlineMerchantCn;
	}

	public String getDeviceStatusCn() {
		if (this.deviceStatus == null) {
			return "未知状态";
		}
		return ShareDeviceStatusEnum.getDesc(this.deviceStatus);
	}

	public void setDeviceStatusCn(String deviceStatusCn) {
		this.deviceStatusCn = deviceStatusCn;
	}

}
