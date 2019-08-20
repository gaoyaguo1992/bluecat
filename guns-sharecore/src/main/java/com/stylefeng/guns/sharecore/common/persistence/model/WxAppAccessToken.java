package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 包括敏感数据在内的完整用户信息的加密数据
 * @author lxy
 * 2017/9/15
 */
public class WxAppAccessToken extends WxAppBaseResult{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3936177342322647738L;
	
	/**
	 * access_token
	 */
	private String access_token;
	/**
	 * expires_in
	 */
	private String expires_in;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	
}
