package com.stylefeng.guns.modular.wxApplication.bo;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

public class RegisterTerminalByCodeBO extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5675443990710053048L;

	private String superMerchantId; //上级商户号。
	
	private String opType; //扫码注册的商户类型
	
	private String openId;
	
	private String custNo;
	
	private String startShopTime;
	
	private String endShopTime;
	
	private String province;
	
	private String city;
	
	private String zone;
	
	private String districtName;
	
	private String addr;
	
	private String name;
	
	private String latitude;
	
	private String longitude;
	
	private String telNo;
	
	private String storePhoneNo;
	
	private String personName;
	
	
	
	

	public String getSuperMerchantId() {
		return superMerchantId;
	}

	public void setSuperMerchantId(String superMerchantId) {
		this.superMerchantId = superMerchantId;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getStartShopTime() {
		return startShopTime;
	}

	public void setStartShopTime(String startShopTime) {
		this.startShopTime = startShopTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getStorePhoneNo() {
		return storePhoneNo;
	}

	public void setStorePhoneNo(String storePhoneNo) {
		this.storePhoneNo = storePhoneNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEndShopTime() {
		return endShopTime;
	}

	public void setEndShopTime(String endShopTime) {
		this.endShopTime = endShopTime;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
	

}
