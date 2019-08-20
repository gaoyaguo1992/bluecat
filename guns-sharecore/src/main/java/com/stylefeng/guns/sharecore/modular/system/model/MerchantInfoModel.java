package com.stylefeng.guns.sharecore.modular.system.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;
import com.stylefeng.guns.sharecore.common.utils.ExcelField;

public class MerchantInfoModel implements Serializable {

	private CustAccountModel custAccount;

	@ExcelField(title = "商户ID", align = 2, sort = 1)
	private Long id;

	@ExcelField(title = "商户名", align = 2, sort = 2)
	private String name;

	@ExcelField(title = "商户地址", align = 2, sort = 3)
	private String addr;

	@ExcelField(title = "联系人", align = 2, sort = 4)
	private String personName;

	@ExcelField(title = "联系电话", align = 2, sort = 5)
	private String telNo;

	private Date createTime;

	private Date updateTime;

	private Byte merchantType;
	
	@ExcelField(title = "商户类型", align = 2, sort = 6)
	private String merchantTypeCn;

	@ExcelField(title = "上级商户ID", align = 2, sort = 7)
	private Long parentMerchantId;

	private String userId;

	private Byte merchantLevel;

	private String merchantLevelCn;

	private Byte status;

	private String remark;

	private String merchantZone;

	private Byte industryCategoryCode;

	@ExcelField(title = "行业类型", align = 2, sort = 9)
	private String industryCategoryCn;

	private Byte contractCycle;

	@ExcelField(title = "结算客户号", align = 2, sort = 8)
	private String settlementAccount;

	private Integer hisTradeCount;

	private Byte shopkeeperType;

	private String shopkeeperTypeCn;

	private Byte isOnlineService;

	private BigDecimal hisTradeAmount;

	private BigDecimal investMoney;

	private Long settlementCustNo;

	private Byte clientType;

	private String legalRepresentative;

	private String uniformSocialCreditCode;

	private String idNumber;

	private String province;

	private String city;

	private String zone;

	private Float withdrawScale;

	private Byte tradeAmountShowFlag;

	private String shareQrCodePath;

	private String receiver;

	private String receiverTel;

	private String receiverAddr;

	private Integer currentRoundNum;

	private Integer totalRoundNum;

	private String idName;

	private Byte advanceProfitFlag;

	private BigDecimal tecFeeAccumulate;

	private Byte merchantSubType;

	private String merchantSubTypeCn;

	@ExcelField(title = "营业开始时间", align = 2, sort = 10)
	private String startShopTime;

	@ExcelField(title = "营业结束时间", align = 2, sort = 11)
	private String endShopTime;

	private Float starLevel;

	private BigDecimal weekUseScale;

	private BigDecimal perConsume;

	private String storePhoneNo;

	private String profile;

	private Byte isPhoneCheck;

	private Long withdrawWayId;

	private Integer roomCount;

	private Long provinceId;

	private Long cityId;

	private Long zoneId;

	private Byte isOldShelfProcess;

	private BigDecimal latitudeX;

	private BigDecimal longitudeY;

	public String getTelNoFormat() {
		if (StringUtils.isEmpty(telNo)) {
			return "";
		}
		if (telNo.length() < 11) {
			return telNo;
		}
		String telNoHead = this.telNo.substring(0, 3);
		String telNoTail = this.telNo.substring(7, 11);
		return telNoHead + "****" + telNoTail;
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
		this.name = name == null ? null : name.trim();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName == null ? null : personName.trim();
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo == null ? null : telNo.trim();
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

	public Byte getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Byte merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantTypeCn() {
		return merchantTypeCn;
	}

	public void setMerchantTypeCn(String merchantTypeCn) {
		this.merchantTypeCn = merchantTypeCn == null ? null : merchantTypeCn.trim();
	}

	public Long getParentMerchantId() {
		return parentMerchantId;
	}

	public void setParentMerchantId(Long parentMerchantId) {
		this.parentMerchantId = parentMerchantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Byte getMerchantLevel() {
		return merchantLevel;
	}

	public void setMerchantLevel(Byte merchantLevel) {
		this.merchantLevel = merchantLevel;
	}

	public String getMerchantLevelCn() {
		return merchantLevelCn;
	}

	public void setMerchantLevelCn(String merchantLevelCn) {
		this.merchantLevelCn = merchantLevelCn == null ? null : merchantLevelCn.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getMerchantZone() {
		return merchantZone;
	}

	public void setMerchantZone(String merchantZone) {
		this.merchantZone = merchantZone == null ? null : merchantZone.trim();
	}

	public Byte getIndustryCategoryCode() {
		return industryCategoryCode;
	}

	public void setIndustryCategoryCode(Byte industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}

	public String getIndustryCategoryCn() {
		return industryCategoryCn;
	}

	public void setIndustryCategoryCn(String industryCategoryCn) {
		this.industryCategoryCn = industryCategoryCn == null ? null : industryCategoryCn.trim();
	}

	public Byte getContractCycle() {
		return contractCycle;
	}

	public void setContractCycle(Byte contractCycle) {
		this.contractCycle = contractCycle;
	}

	public String getSettlementAccount() {
		return settlementAccount;
	}

	public void setSettlementAccount(String settlementAccount) {
		this.settlementAccount = settlementAccount == null ? null : settlementAccount.trim();
	}

	public Integer getHisTradeCount() {
		return hisTradeCount;
	}

	public void setHisTradeCount(Integer hisTradeCount) {
		this.hisTradeCount = hisTradeCount;
	}

	public Byte getShopkeeperType() {
		return shopkeeperType;
	}

	public void setShopkeeperType(Byte shopkeeperType) {
		this.shopkeeperType = shopkeeperType;
	}

	public String getShopkeeperTypeCn() {
		return shopkeeperTypeCn;
	}

	public void setShopkeeperTypeCn(String shopkeeperTypeCn) {
		this.shopkeeperTypeCn = shopkeeperTypeCn == null ? null : shopkeeperTypeCn.trim();
	}

	public Byte getIsOnlineService() {
		return isOnlineService;
	}

	public void setIsOnlineService(Byte isOnlineService) {
		this.isOnlineService = isOnlineService;
	}

	public BigDecimal getHisTradeAmount() {
		return hisTradeAmount;
	}

	public void setHisTradeAmount(BigDecimal hisTradeAmount) {
		this.hisTradeAmount = hisTradeAmount;
	}

	public BigDecimal getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}

	public Long getSettlementCustNo() {
		return settlementCustNo;
	}

	public void setSettlementCustNo(Long settlementCustNo) {
		this.settlementCustNo = settlementCustNo;
	}

	public Byte getClientType() {
		return clientType;
	}

	public void setClientType(Byte clientType) {
		this.clientType = clientType;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
	}

	public String getUniformSocialCreditCode() {
		return uniformSocialCreditCode;
	}

	public void setUniformSocialCreditCode(String uniformSocialCreditCode) {
		this.uniformSocialCreditCode = uniformSocialCreditCode == null ? null : uniformSocialCreditCode.trim();
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber == null ? null : idNumber.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone == null ? null : zone.trim();
	}

	public Float getWithdrawScale() {
		return withdrawScale;
	}

	public void setWithdrawScale(Float withdrawScale) {
		this.withdrawScale = withdrawScale;
	}

	public Byte getTradeAmountShowFlag() {
		return tradeAmountShowFlag;
	}

	public void setTradeAmountShowFlag(Byte tradeAmountShowFlag) {
		this.tradeAmountShowFlag = tradeAmountShowFlag;
	}

	public String getShareQrCodePath() {
		return shareQrCodePath;
	}

	public void setShareQrCodePath(String shareQrCodePath) {
		this.shareQrCodePath = shareQrCodePath == null ? null : shareQrCodePath.trim();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver == null ? null : receiver.trim();
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel == null ? null : receiverTel.trim();
	}

	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr == null ? null : receiverAddr.trim();
	}

	public Integer getCurrentRoundNum() {
		return currentRoundNum;
	}

	public void setCurrentRoundNum(Integer currentRoundNum) {
		this.currentRoundNum = currentRoundNum;
	}

	public Integer getTotalRoundNum() {
		return totalRoundNum;
	}

	public void setTotalRoundNum(Integer totalRoundNum) {
		this.totalRoundNum = totalRoundNum;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName == null ? null : idName.trim();
	}

	public Byte getAdvanceProfitFlag() {
		return advanceProfitFlag;
	}

	public void setAdvanceProfitFlag(Byte advanceProfitFlag) {
		this.advanceProfitFlag = advanceProfitFlag;
	}

	public BigDecimal getTecFeeAccumulate() {
		return tecFeeAccumulate;
	}

	public void setTecFeeAccumulate(BigDecimal tecFeeAccumulate) {
		this.tecFeeAccumulate = tecFeeAccumulate;
	}

	public Byte getMerchantSubType() {
		return merchantSubType;
	}

	public void setMerchantSubType(Byte merchantSubType) {
		this.merchantSubType = merchantSubType;
	}

	public String getMerchantSubTypeCn() {
		return merchantSubTypeCn;
	}

	public void setMerchantSubTypeCn(String merchantSubTypeCn) {
		this.merchantSubTypeCn = merchantSubTypeCn == null ? null : merchantSubTypeCn.trim();
	}

	public String getStartShopTime() {
		return startShopTime;
	}

	public void setStartShopTime(String startShopTime) {
		this.startShopTime = startShopTime == null ? null : startShopTime.trim();
	}

	public String getEndShopTime() {
		return endShopTime;
	}

	public void setEndShopTime(String endShopTime) {
		this.endShopTime = endShopTime == null ? null : endShopTime.trim();
	}

	public Float getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Float starLevel) {
		this.starLevel = starLevel;
	}

	public BigDecimal getWeekUseScale() {
		return weekUseScale;
	}

	public void setWeekUseScale(BigDecimal weekUseScale) {
		this.weekUseScale = weekUseScale;
	}

	public BigDecimal getPerConsume() {
		return perConsume;
	}

	public void setPerConsume(BigDecimal perConsume) {
		this.perConsume = perConsume;
	}

	public String getStorePhoneNo() {
		return storePhoneNo;
	}

	public void setStorePhoneNo(String storePhoneNo) {
		this.storePhoneNo = storePhoneNo == null ? null : storePhoneNo.trim();
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile == null ? null : profile.trim();
	}

	public Byte getIsPhoneCheck() {
		return isPhoneCheck;
	}

	public void setIsPhoneCheck(Byte isPhoneCheck) {
		this.isPhoneCheck = isPhoneCheck;
	}

	public Long getWithdrawWayId() {
		return withdrawWayId;
	}

	public void setWithdrawWayId(Long withdrawWayId) {
		this.withdrawWayId = withdrawWayId;
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

	public Byte getIsOldShelfProcess() {
		return isOldShelfProcess;
	}

	public void setIsOldShelfProcess(Byte isOldShelfProcess) {
		this.isOldShelfProcess = isOldShelfProcess;
	}

	public BigDecimal getLatitudeX() {
		return latitudeX;
	}

	public void setLatitudeX(BigDecimal latitudeX) {
		this.latitudeX = latitudeX;
	}

	public BigDecimal getLongitudeY() {
		return longitudeY;
	}

	public void setLongitudeY(BigDecimal longitudeY) {
		this.longitudeY = longitudeY;
	}

	public CustAccountModel getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(CustAccountModel custAccount) {
		this.custAccount = custAccount;
	}

}