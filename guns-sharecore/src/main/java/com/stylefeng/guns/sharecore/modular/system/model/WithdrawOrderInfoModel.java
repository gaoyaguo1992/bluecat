package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class WithdrawOrderInfoModel extends PageCommand {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3262533730428340386L;

	private Long tradeId;
	
	private Long backGprsDeviceNo;
	
	private Long borrowGprsDeviceNo;

	private Integer status;

	private String statusCn;

	private String tradeName;

	private Integer tradeType;

	private Long deviceId;

	private Long chargerId;

	private Long custNo;

	private Long oppCustNo;

	private Date createTime;

	private Date updateTime;

	private BigDecimal tradeAmount = BigDecimal.ZERO;
	
	private String tradeAmountStr;

	private BigDecimal yfjAmount = BigDecimal.ZERO;

	private BigDecimal latitude;
	private BigDecimal longitude;

	private BigDecimal jifenAmount = BigDecimal.ZERO;

	private String zjAddr;
	
	private String devAddr;
	
	private String shopName;
	
	private Date borrowTime;

	private Date backTime;

	private Date checkDate;

	private Integer feeZoneType;

	private Long refTradeId;

	private Long ratioId;

	private Integer pwdMode;

	private String feeInfo;

	private Long franchiseeId;
	private Long agentsId;
	private Long shopKeeperId;
	private Long zdId;

	private Long merchantId;

 

	/**
	 * 电管缴纳技术管理费比例
	 */
	private BigDecimal tecFeeScale;

	/**
	 * 交易渠道
	 */
	private Integer tradeChannel;

	private Integer sendMessageTimes; // 该交易已经发送过消息的次数

	private String operateComment;// 退款原因
	
	private String terminalMerAddr; //终端地址
	
	private String terminalMerName; //终端商户
	
	private Long firstLevelAgentsId; // 一级代理商编号
    
    private Long secondLevelAgentsId; // 二级代理商编号
    
    /**
	 * 登录商户id,用于商户(代理商，铺货商等)登录权限控制
	 */
	private Long loginMerId;
	
	/**
	 * 登录商户查询类型,用于商户(代理商，铺货商等)登录权限控制
	 */
	private String loginMerTypeQuery;
	
	private List<String> salesmanIdList;
	
	private String roleType;
	
	private String nickName;
	
	private Integer merchantType;

	private String merchantName;

	private String industryCategoryCn;
	
	private String agentsName;
	
	private Integer agentsSubType;
	
	private String agentsSubTypeCn;
	
	private String isCount;// 用于Mybatis判断的统计标记字段,“统计”代表统计,其它不统计
	
	private String merchantTypeQuery;

	private Long deviceIdStart;

	private Long deviceIdEnd;

	private String backTimeCreateStartDate;

	private String backTimeCreateEndDate;
	
	private Integer merchantLevel;
	
	private Integer yfjType;
	
	private String tradeInfoCreateStartDate;
	
	private String tradeInfoCreateEndDate;
	
	private String tradeChannelName;
	
	private Long deviceZoneId;
	
	private String province;
	
	private String city;
	
	private String zone;
	
	/**
	 * 设备类型
	 */
	private Integer devType;

	public Integer getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(Integer tradeChannel) {
		this.tradeChannel = tradeChannel;
	}

	 
	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}


	public Long getFranchiseeId() {
		return franchiseeId;
	}

	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public Long getAgentsId() {
		return agentsId;
	}

	public void setAgentsId(Long agentsId) {
		this.agentsId = agentsId;
	}

	public Long getShopKeeperId() {
		return shopKeeperId;
	}

	public void setShopKeeperId(Long shopKeeperId) {
		this.shopKeeperId = shopKeeperId;
	}

	public String getFeeInfo() {
		return feeInfo;
	}

	public void setFeeInfo(String feeInfo) {
		this.feeInfo = feeInfo;
	}

	// free used chargers
	private String usedOtherCharges;

	public String getUsedOtherCharges() {
		return usedOtherCharges;
	}

	public void setUsedOtherCharges(String usedOtherCharges) {
		this.usedOtherCharges = usedOtherCharges;
	}

	public Long getOppCustNo() {
		return oppCustNo;
	}

	public void setOppCustNo(Long oppCustNo) {
		this.oppCustNo = oppCustNo;
	}

	public BigDecimal getJifenAmount() {
		return jifenAmount;
	}

	public void setJifenAmount(BigDecimal jifenAmount) {
		this.jifenAmount = jifenAmount;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getYfjAmount() {
		return yfjAmount;
	}

	public void setYfjAmount(BigDecimal yfjAmount) {
		this.yfjAmount = yfjAmount;
	}

	public String getZjAddr() {
		return zjAddr;
	}

	public void setZjAddr(String zjAddr) {
		this.zjAddr = zjAddr;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Integer getFeeZoneType() {
		return feeZoneType;
	}

	public void setFeeZoneType(Integer feeZoneType) {
		this.feeZoneType = feeZoneType;
	}

	public Long getRefTradeId() {
		return refTradeId;
	}

	public void setRefTradeId(Long refTradeId) {
		this.refTradeId = refTradeId;
	}

	public Long getRatioId() {
		return ratioId;
	}

	public void setRatioId(Long ratioId) {
		this.ratioId = ratioId;
	}

	public Integer getPwdMode() {
		return pwdMode;
	}

	public void setPwdMode(Integer pwdMode) {
		this.pwdMode = pwdMode;
	}

	public BigDecimal getTecFeeScale() {
		return tecFeeScale;
	}

	public void setTecFeeScale(BigDecimal tecFeeScale) {
		this.tecFeeScale = tecFeeScale;
	}

	public Integer getSendMessageTimes() {
		return sendMessageTimes;
	}

	public void setSendMessageTimes(Integer sendMessageTimes) {
		this.sendMessageTimes = sendMessageTimes;
	}

	public String getOperateComment() {
		return operateComment;
	}

	public void setOperateComment(String operateComment) {
		this.operateComment = operateComment;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getTerminalMerAddr() {
		return terminalMerAddr;
	}

	public void setTerminalMerAddr(String terminalMerAddr) {
		this.terminalMerAddr = terminalMerAddr;
	}

	public String getTerminalMerName() {
		return terminalMerName;
	}

	public void setTerminalMerName(String terminalMerName) {
		this.terminalMerName = terminalMerName;
	}

	public Long getFirstLevelAgentsId() {
		return firstLevelAgentsId;
	}

	public void setFirstLevelAgentsId(Long firstLevelAgentsId) {
		this.firstLevelAgentsId = firstLevelAgentsId;
	}

	public Long getSecondLevelAgentsId() {
		return secondLevelAgentsId;
	}

	public void setSecondLevelAgentsId(Long secondLevelAgentsId) {
		this.secondLevelAgentsId = secondLevelAgentsId;
	}

	public String getTradeAmountStr() {
		return tradeAmountStr;
	}

	public void setTradeAmountStr(String tradeAmountStr) {
		this.tradeAmountStr = tradeAmountStr;
	}

	public Long getLoginMerId() {
		return loginMerId;
	}

	public void setLoginMerId(Long loginMerId) {
		this.loginMerId = loginMerId;
	}

	public String getLoginMerTypeQuery() {
		return loginMerTypeQuery;
	}

	public void setLoginMerTypeQuery(String loginMerTypeQuery) {
		this.loginMerTypeQuery = loginMerTypeQuery;
	}

	public List<String> getSalesmanIdList() {
		return salesmanIdList;
	}

	public void setSalesmanIdList(List<String> salesmanIdList) {
		this.salesmanIdList = salesmanIdList;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getIndustryCategoryCn() {
		return industryCategoryCn;
	}

	public void setIndustryCategoryCn(String industryCategoryCn) {
		this.industryCategoryCn = industryCategoryCn;
	}

	public String getAgentsName() {
		return agentsName;
	}

	public void setAgentsName(String agentsName) {
		this.agentsName = agentsName;
	}

	public Integer getAgentsSubType() {
		return agentsSubType;
	}

	public void setAgentsSubType(Integer agentsSubType) {
		this.agentsSubType = agentsSubType;
	}

	public String getAgentsSubTypeCn() {
		return agentsSubTypeCn;
	}

	public void setAgentsSubTypeCn(String agentsSubTypeCn) {
		this.agentsSubTypeCn = agentsSubTypeCn;
	}

	public String getIsCount() {
		return isCount;
	}

	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

	public String getMerchantTypeQuery() {
		return merchantTypeQuery;
	}

	public void setMerchantTypeQuery(String merchantTypeQuery) {
		this.merchantTypeQuery = merchantTypeQuery;
	}

	public Long getDeviceIdStart() {
		return deviceIdStart;
	}

	public void setDeviceIdStart(Long deviceIdStart) {
		this.deviceIdStart = deviceIdStart;
	}

	public Long getDeviceIdEnd() {
		return deviceIdEnd;
	}

	public void setDeviceIdEnd(Long deviceIdEnd) {
		this.deviceIdEnd = deviceIdEnd;
	}

	public String getBackTimeCreateStartDate() {
		return backTimeCreateStartDate;
	}

	public void setBackTimeCreateStartDate(String backTimeCreateStartDate) {
		this.backTimeCreateStartDate = backTimeCreateStartDate;
	}

	public String getBackTimeCreateEndDate() {
		return backTimeCreateEndDate;
	}

	public void setBackTimeCreateEndDate(String backTimeCreateEndDate) {
		this.backTimeCreateEndDate = backTimeCreateEndDate;
	}

	public Integer getMerchantLevel() {
		return merchantLevel;
	}

	public void setMerchantLevel(Integer merchantLevel) {
		this.merchantLevel = merchantLevel;
	}

	public Integer getYfjType() {
		return yfjType;
	}

	public void setYfjType(Integer yfjType) {
		this.yfjType = yfjType;
	}

	public String getTradeInfoCreateStartDate() {
		return tradeInfoCreateStartDate;
	}

	public void setTradeInfoCreateStartDate(String tradeInfoCreateStartDate) {
		this.tradeInfoCreateStartDate = tradeInfoCreateStartDate;
	}

	public String getTradeInfoCreateEndDate() {
		return tradeInfoCreateEndDate;
	}

	public void setTradeInfoCreateEndDate(String tradeInfoCreateEndDate) {
		this.tradeInfoCreateEndDate = tradeInfoCreateEndDate;
	}

	public String getTradeChannelName() {
		return tradeChannelName;
	}

	public void setTradeChannelName(String tradeChannelName) {
		this.tradeChannelName = tradeChannelName;
	}

	public Long getDeviceZoneId() {
		return deviceZoneId;
	}

	public void setDeviceZoneId(Long deviceZoneId) {
		this.deviceZoneId = deviceZoneId;
	}

	public Integer getDevType() {
		return devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
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

	public Long getBackGprsDeviceNo() {
		return backGprsDeviceNo;
	}

	public void setBackGprsDeviceNo(Long backGprsDeviceNo) {
		this.backGprsDeviceNo = backGprsDeviceNo;
	}

	public Long getBorrowGprsDeviceNo() {
		return borrowGprsDeviceNo;
	}

	public void setBorrowGprsDeviceNo(Long borrowGprsDeviceNo) {
		this.borrowGprsDeviceNo = borrowGprsDeviceNo;
	}

	public Long getZdId() {
		return zdId;
	}

	public void setZdId(Long zdId) {
		this.zdId = zdId;
	}

}