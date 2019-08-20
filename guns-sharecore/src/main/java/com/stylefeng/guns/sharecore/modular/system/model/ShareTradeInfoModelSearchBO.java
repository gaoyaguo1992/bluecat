package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 查询bo, 增加商户客户等信息..
 * @author Alan.huang
 *
 */
public class ShareTradeInfoModelSearchBO extends ShareTradeInfoModel {
	/**
	 * 服商名称
	 */
	private String merchantName;
	/**
	 * 商户地址
	 */
	private String merchantAddr;
	/**
	 * 商户联系人
	 */
	private String merchantPersonName;
	/**
	 * 联系电话
	 */
	private String merchantTelNo;
	/**
	 * 代理商名
	 */
	private String agents1Name;
	/*
	 * 代理商地址
	 */
	private String agents1Addr;
	/**
	 * 代理商联系人
	 */
	private String agents1PersonName;
	/**
	 * 代理商联系人电话
	 */
	private String agents1TelNo;
	/**
	 * 代理商名
	 */
	private String agents2Name;
	/*
	 * 代理商地址
	 */
	private String agents2Addr;
	/**
	 * 代理商联系人
	 */
	private String agents2PersonName;
	/**
	 * 代理商联系人电话
	 */
	private String agents2TelNo;
	/**
	 * 代理商名
	 */
	private String agents3Name;
	/**
	 * 代理商地址
	 */
	private String agents3Addr;
	/**
	 * 代理商联系人
	 */
	private String agents3PersonName;
	/**
	 * 代理商联系人电话
	 */
	private String agents3TelNo;
	/**
	 * 铺货名
	 */
	private String shopkeeperName;
	/**
	 * 铺货商地址
	 */
	private String shopkeeperAddr;
	/**
	 * 铺货商联系人
	 */
	private String shopkeeperPersonName;
	/**
	 * 铺货商联系人电话
	 */
	private String shopkeeperTelNo;
	/**
	 * 加盟商
	 */
	private String allianceBussinessName;
	/**
	 * 加盟商地址
	 */
	private String allianceBussinessAddr;
	/**
	 * 加盟商联系人
	 */
	private String allianceBussinessPersonName;
	/**
	 * 加盟商联系人电话
	 */
	private String allianceBussinessTelNo;
	/**
	 * 客户尼称
	 */
	private String nickName;
	/**
	 * 客户头象
	 */
	private String headImgUrl;
	/**
	 * 城市
	 */
	private String city;


	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantAddr() {
		return merchantAddr;
	}
	public void setMerchantAddr(String merchantAddr) {
		this.merchantAddr = merchantAddr;
	}
	public String getMerchantPersonName() {
		return merchantPersonName;
	}
	public void setMerchantPersonName(String merchantPersonName) {
		this.merchantPersonName = merchantPersonName;
	}
	public String getMerchantTelNo() {
		return merchantTelNo;
	}
	public void setMerchantTelNo(String merchantTelNo) {
		this.merchantTelNo = merchantTelNo;
	}
	public String getAgents1Name() {
		return agents1Name;
	}
	public void setAgents1Name(String agents1Name) {
		this.agents1Name = agents1Name;
	}
	public String getAgents1Addr() {
		return agents1Addr;
	}
	public void setAgents1Addr(String agents1Addr) {
		this.agents1Addr = agents1Addr;
	}
	public String getAgents1PersonName() {
		return agents1PersonName;
	}
	public void setAgents1PersonName(String agents1PersonName) {
		this.agents1PersonName = agents1PersonName;
	}
	public String getAgents1TelNo() {
		return agents1TelNo;
	}
	public void setAgents1TelNo(String agents1TelNo) {
		this.agents1TelNo = agents1TelNo;
	}
	public String getAgents2Name() {
		return agents2Name;
	}
	public void setAgents2Name(String agents2Name) {
		this.agents2Name = agents2Name;
	}
	public String getAgents2Addr() {
		return agents2Addr;
	}
	public void setAgents2Addr(String agents2Addr) {
		this.agents2Addr = agents2Addr;
	}
	public String getAgents2PersonName() {
		return agents2PersonName;
	}
	public void setAgents2PersonName(String agents2PersonName) {
		this.agents2PersonName = agents2PersonName;
	}
	public String getAgents2TelNo() {
		return agents2TelNo;
	}
	public void setAgents2TelNo(String agents2TelNo) {
		this.agents2TelNo = agents2TelNo;
	}
	public String getAgents3Name() {
		return agents3Name;
	}
	public void setAgents3Name(String agents3Name) {
		this.agents3Name = agents3Name;
	}
	public String getAgents3Addr() {
		return agents3Addr;
	}
	public void setAgents3Addr(String agents3Addr) {
		this.agents3Addr = agents3Addr;
	}
	public String getAgents3PersonName() {
		return agents3PersonName;
	}
	public void setAgents3PersonName(String agents3PersonName) {
		this.agents3PersonName = agents3PersonName;
	}
	public String getAgents3TelNo() {
		return agents3TelNo;
	}
	public void setAgents3TelNo(String agents3TelNo) {
		this.agents3TelNo = agents3TelNo;
	}
	public String getShopkeeperName() {
		return shopkeeperName;
	}
	public void setShopkeeperName(String shopkeeperName) {
		this.shopkeeperName = shopkeeperName;
	}
	public String getShopkeeperAddr() {
		return shopkeeperAddr;
	}
	public void setShopkeeperAddr(String shopkeeperAddr) {
		this.shopkeeperAddr = shopkeeperAddr;
	}
	public String getShopkeeperPersonName() {
		return shopkeeperPersonName;
	}
	public void setShopkeeperPersonName(String shopkeeperPersonName) {
		this.shopkeeperPersonName = shopkeeperPersonName;
	}
	public String getShopkeeperTelNo() {
		return shopkeeperTelNo;
	}
	public void setShopkeeperTelNo(String shopkeeperTelNo) {
		this.shopkeeperTelNo = shopkeeperTelNo;
	}
	public String getAllianceBussinessName() {
		return allianceBussinessName;
	}
	public void setAllianceBussinessName(String allianceBussinessName) {
		this.allianceBussinessName = allianceBussinessName;
	}
	public String getAllianceBussinessAddr() {
		return allianceBussinessAddr;
	}
	public void setAllianceBussinessAddr(String allianceBussinessAddr) {
		this.allianceBussinessAddr = allianceBussinessAddr;
	}
	public String getAllianceBussinessPersonName() {
		return allianceBussinessPersonName;
	}
	public void setAllianceBussinessPersonName(String allianceBussinessPersonName) {
		this.allianceBussinessPersonName = allianceBussinessPersonName;
	}
	public String getAllianceBussinessTelNo() {
		return allianceBussinessTelNo;
	}
	public void setAllianceBussinessTelNo(String allianceBussinessTelNo) {
		this.allianceBussinessTelNo = allianceBussinessTelNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}	
}