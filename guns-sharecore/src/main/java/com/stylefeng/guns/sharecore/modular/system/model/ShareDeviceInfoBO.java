package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 批理信息处理
 * @author Alan.huang
 *
 */
public class ShareDeviceInfoBO extends ShareDeviceInfoModel {
	/**
	 * 终端商户联系电话
	 */
	private String terminalTelNo;
	/**
	 * 终端商户Id
	 */
	private Long terminalMerchantId;
	/**
	 * 终端商户名
	 */
	private String terminalMerchantName;
	/**
	 * 终商户联系人
	 */
	private String terminalPersonName;
	/**
	 * 省
	 */
	private String terminalProvinceId;
	/**
	 * 省
	 */
	private String terminalProvince;
	/**
	 * 市
	 */
	private String terminalCityId;
	/**
	 * 市
	 */
	private String terminalCity;
	/**
	 * 区
	 */
	private String terminalZoneId;
	/**
	 * 区
	 */
	private String terminalZone;
	/**
	 * 地址
	 */
	private String terminalMerchantAddress;
	/**
	 * 描述
	 */
	private String terminalRemark;
	/**
	 * 设备编号类型
	 */
    private Integer passwordType;
    /**
     * 设备编号对应月
     */
    private String passwordMonth;
    /**
     * 设备编号对应批次
     */
    private String passwordBatch;
    /**
     * 设备key..
     */
    private String passwordKey;
    /**
     * 设备编号对应年
     */
    private String passwordYear;

    
	public Integer getPasswordType() {
		return passwordType;
	}
	public void setPasswordType(Integer passwordType) {
		this.passwordType = passwordType;
	}
	public String getPasswordMonth() {
		return passwordMonth;
	}
	public void setPasswordMonth(String passwordMonth) {
		this.passwordMonth = passwordMonth;
	}
	public String getPasswordBatch() {
		return passwordBatch;
	}
	public void setPasswordBatch(String passwordBatch) {
		this.passwordBatch = passwordBatch;
	}
	public String getPasswordKey() {
		return passwordKey;
	}
	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}
	public String getPasswordYear() {
		return passwordYear;
	}
	public void setPasswordYear(String passwordYear) {
		this.passwordYear = passwordYear;
	}
	public String getTerminalTelNo() {
		return terminalTelNo;
	}
	public void setTerminalTelNo(String terminalTelNo) {
		this.terminalTelNo = terminalTelNo;
	}
	public Long getTerminalMerchantId() {
		return terminalMerchantId;
	}
	public void setTerminalMerchantId(Long terminalMerchantId) {
		this.terminalMerchantId = terminalMerchantId;
	}
	public String getTerminalMerchantName() {
		return terminalMerchantName;
	}
	public void setTerminalMerchantName(String terminalMerchantName) {
		this.terminalMerchantName = terminalMerchantName;
	}
	public String getTerminalPersonName() {
		return terminalPersonName;
	}
	public void setTerminalPersonName(String terminalPersonName) {
		this.terminalPersonName = terminalPersonName;
	}
	public String getTerminalProvinceId() {
		return terminalProvinceId;
	}
	public void setTerminalProvinceId(String terminalProvinceId) {
		this.terminalProvinceId = terminalProvinceId;
	}
	public String getTerminalProvince() {
		return terminalProvince;
	}
	public void setTerminalProvince(String terminalProvince) {
		this.terminalProvince = terminalProvince;
	}
	public String getTerminalCityId() {
		return terminalCityId;
	}
	public void setTerminalCityId(String terminalCityId) {
		this.terminalCityId = terminalCityId;
	}
	public String getTerminalCity() {
		return terminalCity;
	}
	public void setTerminalCity(String terminalCity) {
		this.terminalCity = terminalCity;
	}
	public String getTerminalZoneId() {
		return terminalZoneId;
	}
	public void setTerminalZoneId(String terminalZoneId) {
		this.terminalZoneId = terminalZoneId;
	}
	public String getTerminalZone() {
		return terminalZone;
	}
	public void setTerminalZone(String terminalZone) {
		this.terminalZone = terminalZone;
	}
	public String getTerminalMerchantAddress() {
		return terminalMerchantAddress;
	}
	public void setTerminalMerchantAddress(String terminalMerchantAddress) {
		this.terminalMerchantAddress = terminalMerchantAddress;
	}
	public String getTerminalRemark() {
		return terminalRemark;
	}
	public void setTerminalRemark(String terminalRemark) {
		this.terminalRemark = terminalRemark;
	}
}