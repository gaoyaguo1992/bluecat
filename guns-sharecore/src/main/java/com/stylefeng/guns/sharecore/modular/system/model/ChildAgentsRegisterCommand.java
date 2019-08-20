package com.stylefeng.guns.sharecore.modular.system.model;


/**
 * 
 * <P>子商户注册command</P>
 * 
 * @author
 */
public class ChildAgentsRegisterCommand extends PageCommand {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 3193489853094300371L;

	//父级商户Id
	private Long parentMerchantId;
	
	// 商户姓名
	private String personName;

	// 联系电话
	private String telNo;
	
	// 客户编号
	private Long custNo;

	// 商户等级
	private Integer merchantLevel;

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

	public Long getParentMerchantId() {
		return parentMerchantId;
	}

	public void setParentMerchantId(Long parentMerchantId) {
		this.parentMerchantId = parentMerchantId;
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

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}


	public Integer getMerchantLevel() {
		return merchantLevel;
	}

	public void setMerchantLevel(Integer merchantLevel) {
		this.merchantLevel = merchantLevel;
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
}
