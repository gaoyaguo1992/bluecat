package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantInfoBo {
	private static final long serialVersionUID = -686390055920984527L;

	private Long id;

    private String name;

    private String addr;

    private String personName;

    private String telNo;

    private Date createTime;

    private Integer merchantType;

    private Long parentMerchantId;
    
    private String parentPersonName;

    private Integer industryCategoryCode;
    
    private String industryCategoryCn;

    private Integer merchantLevel;

    private String merchantTypeCn;

    private String merchantLevelCn;
    
    private Long deviceNo;

    private Integer status;
    
    private String openId;
    
     
    private String remark;
    
    private Integer shopKeeperType;
    
    private String shopKeeperTypeCn;
    
    private Integer devNum;//设备数
    
    private Integer profitRatio;//分润比例
    
    private String idNumber;//身份证号码
    
    private Integer clientType; // 委托方类型
    
    private String legalRepresentative; // 法定代表人
    
    private String uniformSocialCreditCode; // 统一社会信用代码
    
    private Integer serviceType; // 线上线下分润服务类型
    
    private BigDecimal investMoney; // 投入资金
    
    private Integer merchantSubType; // 商户子类型
    
    private Integer payWay; // 付款方式
    
    private Integer isRealTime; // 实时分润
    
    public Integer getDevNum() {
		return devNum;
	}

	public void setDevNum(Integer devNum) {
		this.devNum = devNum;
	}

	public Integer getProfitRatio() {
		return profitRatio;
	}

	public void setProfitRatio(Integer profitRatio) {
		this.profitRatio = profitRatio;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public Long getParentMerchantId() {
		return parentMerchantId;
	}

	public void setParentMerchantId(Long parentMerchantId) {
		this.parentMerchantId = parentMerchantId;
	}

	public String getParentPersonName() {
		return parentPersonName;
	}

	public void setParentPersonName(String parentPersonName) {
		this.parentPersonName = parentPersonName;
	}

	public Integer getIndustryCategoryCode() {
		return industryCategoryCode;
	}

	public void setIndustryCategoryCode(Integer industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}

	public String getIndustryCategoryCn() {
		return industryCategoryCn;
	}

	public void setIndustryCategoryCn(String industryCategoryCn) {
		this.industryCategoryCn = industryCategoryCn;
	}

	public Integer getMerchantLevel() {
		return merchantLevel;
	}

	public void setMerchantLevel(Integer merchantLevel) {
		this.merchantLevel = merchantLevel;
	}

	public String getMerchantTypeCn() {
		return merchantTypeCn;
	}

	public void setMerchantTypeCn(String merchantTypeCn) {
		this.merchantTypeCn = merchantTypeCn;
	}

	public String getMerchantLevelCn() {
		return merchantLevelCn;
	}

	public void setMerchantLevelCn(String merchantLevelCn) {
		this.merchantLevelCn = merchantLevelCn;
	}

	public Long getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(Long deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

 
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getShopKeeperType() {
		return shopKeeperType;
	}

	public void setShopKeeperType(Integer shopKeeperType) {
		this.shopKeeperType = shopKeeperType;
	}

	public String getShopKeeperTypeCn() {
		return shopKeeperTypeCn;
	}

	public void setShopKeeperTypeCn(String shopKeeperTypeCn) {
		this.shopKeeperTypeCn = shopKeeperTypeCn;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getUniformSocialCreditCode() {
		return uniformSocialCreditCode;
	}

	public void setUniformSocialCreditCode(String uniformSocialCreditCode) {
		this.uniformSocialCreditCode = uniformSocialCreditCode;
	}

	public BigDecimal getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}

	public Integer getMerchantSubType() {
		return merchantSubType;
	}

	public void setMerchantSubType(Integer merchantSubType) {
		this.merchantSubType = merchantSubType;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public Integer getIsRealTime() {
		return isRealTime;
	}

	public void setIsRealTime(Integer isRealTime) {
		this.isRealTime = isRealTime;
	}

}