package com.stylefeng.guns.sharecore.common.persistence.model;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class ShareWxMpUser extends WxMpUser {
	/**
	 * 程序小程序openId
	 */
	private String wxAppOpenId;

	public String getWxAppOpenId() {
		return wxAppOpenId;
	}

	public void setWxAppOpenId(String wxAppOpenId) {
		this.wxAppOpenId = wxAppOpenId;
	}
}
