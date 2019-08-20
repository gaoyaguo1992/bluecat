package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 微信小程序sessionkey
 * @author lxy
 * 2017/9/15
 *
 */
public class WxAppSessionKeyModel {
	/**
	* 
	*/
	private static final long serialVersionUID = 3936177342322647738L;

	/**
	 * 用户唯一标识
	 */
	private String openId;
	/**
	 * 会话密钥
	 */
	private String session_key;
	/**
	 * 用户在开放平台的唯一标识符。本字段在满足一定条件的情况下才返回。具体参看UnionID机制说明
	 */
	private String unionId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String sessionKey) {
		this.session_key = sessionKey;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
}
