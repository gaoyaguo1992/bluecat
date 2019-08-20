package com.stylefeng.guns.sharecore.modular.system.service;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 * 小程序处理token...
 * @author Alan.huang
 *
 */
public class WxAppAccessTokenInfo extends BaseObject {
	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;
	private String appId;
	private String tokenStr; 
	private String refreshTime;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTokenStr() {
		return tokenStr;
	}
	public void setTokenStr(String tokenStr) {
		this.tokenStr = tokenStr;
	}
	public String getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
}