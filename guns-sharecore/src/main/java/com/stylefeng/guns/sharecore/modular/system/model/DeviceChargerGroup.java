
package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 *设备充电器分组信息2
 */
public class DeviceChargerGroup extends BaseObject  {
	private static final long serialVersionUID = 123223393333L;
	/**
	 * 充电器数..
	 */
	private int countChargers;
	/**
	 * 充电器串...
	 */
	private String chargers;
	/**
	 * 设备数量...
	 */
	private Long deviceId;
	public int getCountChargers() {
		return countChargers;
	}
	public void setCountChargers(int countChargers) {
		this.countChargers = countChargers;
	}
	public String getChargers() {
		return chargers;
	}
	public void setChargers(String chargers) {
		this.chargers = chargers;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
}
