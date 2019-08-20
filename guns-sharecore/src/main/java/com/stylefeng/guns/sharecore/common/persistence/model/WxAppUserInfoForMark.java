package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 包括敏感数据在内的完整用户信息的加密数据
 * @author lxy
 * 2017/9/15
 */
public class WxAppUserInfoForMark {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3936177342322647738L;
	
	/**
	 * 用户唯一标识
	 */
	private String appid;
	/**
	 * nickName
	 */
	private Long timestamp;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
