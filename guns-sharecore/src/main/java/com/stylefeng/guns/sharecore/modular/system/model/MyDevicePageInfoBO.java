package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.sharecore.common.persistence.model.MyDeviceAndChargerBO;
import com.stylefeng.guns.sharecore.common.persistence.model.MyDeviceInfoBO;


/**
 * 
 *公众号设备信息。。。
 */
public class MyDevicePageInfoBO {
	/**
	 * 返回结果
	 */
	private String result;
	/**
	 * 结果信息
	 */
	private String message;
	
	private List<MyDeviceInfoBO> myDeviceInfoBOs;
	private Map<Long, String> defaultZoneName;
	
	private MyDeviceAndChargerBO myTotalNum;
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<Long, String> getDefaultZoneName() {
		return defaultZoneName;
	}

	public void setDefaultZoneName(Map<Long, String> defaultZoneName) {
		this.defaultZoneName = defaultZoneName;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	public List<MyDeviceInfoBO> getMyDeviceInfoBOs() {
		return myDeviceInfoBOs;
	}
	public void setMyDeviceInfoBOs(List<MyDeviceInfoBO> myDeviceInfoBOs) {
		this.myDeviceInfoBOs = myDeviceInfoBOs;
	}

	public MyDeviceAndChargerBO getMyTotalNum() {
		return myTotalNum;
	}

	public void setMyTotalNum(MyDeviceAndChargerBO myTotalNum) {
		this.myTotalNum = myTotalNum;
	}
	
}
