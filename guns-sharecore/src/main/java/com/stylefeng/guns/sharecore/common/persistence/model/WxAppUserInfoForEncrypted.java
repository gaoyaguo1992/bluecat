package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 包括敏感数据在内的完整用户信息的加密数据
 * @author lxy
 * 2017/9/15
 */
public class WxAppUserInfoForEncrypted {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3936177342322647738L;
	
	/**
	 * 用户唯一标识
	 */
	private String openId;
	/**
	 * nickName
	 */
	private String nickName;
	/**
	 * gender
	 */
	private String gender;
	/**
	 * 用户城市
	 */
	private String city;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 头象
	 */
	private String avatarUrl;
	/**
	 * union Id
	 */
	private String unionId;
	/**
	 * 
	 */
	private WxAppUserInfoForMark watermark;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public WxAppUserInfoForMark getWatermark() {
		return watermark;
	}
	public void setWatermark(WxAppUserInfoForMark watermark) {
		this.watermark = watermark;
	}
}
