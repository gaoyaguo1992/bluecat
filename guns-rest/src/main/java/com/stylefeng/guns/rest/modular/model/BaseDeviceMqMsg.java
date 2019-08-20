package com.stylefeng.guns.rest.modular.model;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 *
 */
public class BaseDeviceMqMsg extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2576167343888942410L;
	public static final String mqSend = "_SEND";
	public static final String mqReceive = "_RECV";

	public static final String SUCCESS = "1";
	public static final String FAIL = "0";
	public static final String PROCESSING = "2";
	
	//must be unique each invoke
	private String invokeSN;

	private String deviceId;

	public String getInvokeSN() {
		return invokeSN;
	}

	public void setInvokeSN(String invokeSN) {
		this.invokeSN = invokeSN;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
