package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

public class ChildAgentsInfoBO extends BaseObject {

	// 商户Id
	private Long merchantId;
	
	// 商户父级ID
	private Long parentMerchantId;
	
	// 商户名称
	private String name;
	
	// 商户类型
	private Integer merchantType;
	
	// 商户等级
	private Integer merchantLevel;
	
	// 商户等级
	private String merchantLevelCn;
	
	// 新建时间
	private Date createTime;
	
	//投入本金
    private BigDecimal investMoney;
    
    //合同期限
	private Integer contractCycle;
    
    //客户编号
	private Long settlementCustNo;
	
	// 商户姓名
	private String personName;
	
	// 联系电话
	private String telNo;
	
	// 省
	private String province; 
	
	// 市
	private String city; 
	
	// 区
	private String zone; 
	
	// 省Id
	private Long provinceId; 
	
	// 市Id
	private Long cityId; 
	
	// 区Id
	private Long zoneId; 
	
	// 具体地址
	private String addr;

	//备注
	private String remark;
	
	//房间数量
	private Integer roomCount;
	
	//店铺属于的服务类别
	private String industryCategoryCn;
	
	//店铺属于的服务类别的id
	private String industryCategoryCode;
	
	//一级二级代理商是否绑定了设备
	private String isBindDevice;
	
	private Long deviceCount; //子商户设备数量

	public String getIsBindDevice() {
		return isBindDevice;
	}

	public void setIsBindDevice(String isBindDevice) {
		this.isBindDevice = isBindDevice;
	}

	public String getIndustryCategoryCn() {
		return industryCategoryCn;
	}

	public void setIndustryCategoryCn(String industryCategoryCn) {
		this.industryCategoryCn = industryCategoryCn;
	}

	public String getIndustryCategoryCode() {
		return industryCategoryCode;
	}

	public void setIndustryCategoryCode(String industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSettlementCustNo() {
		return settlementCustNo;
	}

	public void setSettlementCustNo(Long settlementCustNo) {
		this.settlementCustNo = settlementCustNo;
	}

	public BigDecimal getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}

	public Integer getContractCycle() {
		return contractCycle;
	}

	public void setContractCycle(Integer contractCycle) {
		this.contractCycle = contractCycle;
	}

	public Long getParentMerchantId() {
		return parentMerchantId;
	}

	public void setParentMerchantId(Long parentMerchantId) {
		this.parentMerchantId = parentMerchantId;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMerchantLevel() {
		return merchantLevel;
	}

	public void setMerchantLevel(Integer merchantLevel) {
		this.merchantLevel = merchantLevel;
	}

	public String getMerchantLevelCn() {
		return merchantLevelCn;
	}

	public void setMerchantLevelCn(String merchantLevelCn) {
		this.merchantLevelCn = merchantLevelCn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
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

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Long getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Long deviceCount) {
		this.deviceCount = deviceCount;
	}
}
