package com.stylefeng.guns.sharecore.common.persistence.model;

public class CustMerchantBO extends BaseObject{

	private static final long serialVersionUID = -7324044429402193409L;
	
	/**
	 * 客户号
	 */
	private Long custNo;
	
	/**
	 * 客户openId
	 */
	private String opendId;
	
	/**
	 * 客户昵称
	 */
	private String nickName;
	
	/**
	 * 客户手机号
	 */
	private String custTel;
	
	/**
	 * 商户号
	 */
	private Long merchantId;
	
	/**
	 * 商户姓名
	 */
	private String merPersonName;
	
	/**
	 * 商户手机号
	 */
	private String merTel;
	
	private String wxAppOpenId; //微信小程序openId

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public String getOpendId() {
		return opendId;
	}

	public void setOpendId(String opendId) {
		this.opendId = opendId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerPersonName() {
		return merPersonName;
	}

	public void setMerPersonName(String merPersonName) {
		this.merPersonName = merPersonName;
	}

	public String getMerTel() {
		return merTel;
	}

	public void setMerTel(String merTel) {
		this.merTel = merTel;
	}

	public String getWxAppOpenId() {
		return wxAppOpenId;
	}

	public void setWxAppOpenId(String wxAppOpenId) {
		this.wxAppOpenId = wxAppOpenId;
	}
}