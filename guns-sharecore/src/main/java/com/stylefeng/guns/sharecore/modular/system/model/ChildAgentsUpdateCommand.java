package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * <P>子商户修改command</P>
 * 
 * @author
 */
public class ChildAgentsUpdateCommand extends PageCommand {


	/**
	 * TODO
	 */
	private static final long serialVersionUID = 3130761572125936345L;

		// 商户Id
		private Long merchantId;
		
		//商户类型
		private Integer merchantType;

		// 商户名称
		private String name;
		
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
		
		private String selectIndustry;
		
		//房间数量
		private Integer roomCount;
		
		private String industryCategoryCn;
		//店铺属于的服务类别的id
		private String industryCategoryCode;
		
		public Integer getMerchantType() {
			return merchantType;
		}
	
		public void setMerchantType(Integer merchantType) {
			this.merchantType = merchantType;
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
		
		public String getSelectIndustry() {
			return selectIndustry;
		}

		public void setSelectIndustry(String selectIndustry) {
			this.selectIndustry = selectIndustry;
		}

		public Integer getRoomCount() {
			return roomCount;
		}

		public void setRoomCount(Integer roomCount) {
			this.roomCount = roomCount;
		}

		//是否修改省市区 1：修改   0：不修改
		private Integer modifyFlag;
		
		public Integer getModifyFlag() {
			return modifyFlag;
		}

		public void setModifyFlag(Integer modifyFlag) {
			this.modifyFlag = modifyFlag;
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
	}
