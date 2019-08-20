package com.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-01-20
 */
@TableName("merchant_info")
public class MerchantInfo extends Model<MerchantInfo> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 店铺名称
     */
    private String name;
    private String addr;
    @TableField("person_name")
    private String personName;
    @TableField("tel_no")
    private String telNo;
    @TableField("create_time")
    private Date createTime;
    /**
     * --商户更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    @TableField("merchant_type")
    private Integer merchantType;
    @TableField("merchant_type_cn")
    private String merchantTypeCn;
    @TableField("parent_merchant_id")
    private Long parentMerchantId;
    @TableField("user_id")
    private String userId;
    @TableField("merchant_level")
    private Integer merchantLevel;
    @TableField("merchant_level_cn")
    private String merchantLevelCn;
    @TableField("STATUS")
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    @TableField("merchant_zone")
    private String merchantZone;
    @TableField("industry_category_code")
    private Integer industryCategoryCode;
    @TableField("industry_category_cn")
    private String industryCategoryCn;
    @TableField("contract_cycle")
    private Integer contractCycle;
    /**
     * --商户结算账户
     */
    @TableField("settlement_account")
    private String settlementAccount;
    /**
     * 历史交易数
     */
    @TableField("his_trade_count")
    private Integer hisTradeCount;
    @TableField("shopkeeper_type")
    private Integer shopkeeperType;
    /**
     * --铺货商类型中文,00:总公司,01:子公司,02:中介机构,03:地推团队
     */
    @TableField("shopkeeper_type_cn")
    private String shopkeeperTypeCn;
    @TableField("is_online_service")
    private Integer isOnlineService;
    /**
     * 商户历史交易总金额
     */
    @TableField("his_trade_amount")
    private BigDecimal hisTradeAmount;
    @TableField("invest_money")
    private BigDecimal investMoney;
    /**
     * 结算公众账号
     */
    @TableField("settlement_cust_no")
    private Long settlementCustNo;
    /**
     * 委托方类型,10:个人，11：企业
     */
    @TableField("client_type")
    private Integer clientType;
    /**
     * 法定代表人
     */
    @TableField("legal_representative")
    private String legalRepresentative;
    /**
     * 统一社会信用代码
     */
    @TableField("uniform_social_credit_code")
    private String uniformSocialCreditCode;
    /**
     * 身份证号码
     */
    @TableField("id_number")
    private String idNumber;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String zone;
    /**
     * 商户提现比例
     */
    @TableField("withdraw_scale")
    private Float withdrawScale;
    /**
     * 交易金额显示开关:10显示11不显示
     */
    @TableField("trade_amount_show_flag")
    private Integer tradeAmountShowFlag;
    /**
     * 电管推荐码
     */
    @TableField("share_qr_code_path")
    private String shareQrCodePath;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 收货人电话
     */
    @TableField("receiver_tel")
    private String receiverTel;
    /**
     * 收货人地址
     */
    @TableField("receiver_addr")
    private String receiverAddr;
    /**
     * 当前轮数（电主）
     */
    @TableField("current_round_num")
    private Integer currentRoundNum;
    /**
     * 总轮数（电主）
     */
    @TableField("total_round_num")
    private Integer totalRoundNum;
    @TableField("id_name")
    private String idName;
    /**
     * 提前分润标识,0:不提前,1:提前
     */
    @TableField("advance_profit_flag")
    private Integer advanceProfitFlag;
    /**
     * 电管累计缴纳的技术管理费用
     */
    @TableField("tec_fee_accumulate")
    private BigDecimal tecFeeAccumulate;
    /**
     * 商户子类型，10：自买自铺，11：自买不铺，12：，13：非
     */
    @TableField("merchant_sub_type")
    private Integer merchantSubType;
    /**
     * 商户子类型中文描述
     */
    @TableField("merchant_sub_type_cn")
    private String merchantSubTypeCn;
    /**
     * 营业开始时间(终端）
     */
    @TableField("start_shop_time")
    private String startShopTime;
    /**
     * 营业结束时间(终端）
     */
    @TableField("end_shop_time")
    private String endShopTime;
    /**
     * 评价星级,1-5颗星
     */
    @TableField("star_level")
    private Float starLevel;
    /**
     * 充电器周使用率
     */
    @TableField("week_use_scale")
    private BigDecimal weekUseScale;
    /**
     * 人均消费
     */
    @TableField("per_consume")
    private BigDecimal perConsume;
    /**
     * 店铺对外电话
     */
    @TableField("store_phone_no")
    private String storePhoneNo;
    /**
     * 店铺简介
     */
    private String profile;
    /**
     * 是否需要手机验证(目前仅用于代理商)
     */
    @TableField("is_phone_check")
    private Integer isPhoneCheck;
    @TableField("withdraw_way_id")
    private Long withdrawWayId;
    @TableField("room_count")
    private Integer roomCount;
    /**
     * 省id
     */
    @TableField("province_id")
    private Long provinceId;
    /**
     * 市id
     */
    @TableField("city_id")
    private Long cityId;
    /**
     * 区id
     */
    @TableField("zone_id")
    private Long zoneId;
    /**
     * 是否按旧上架流程上架 1是 2不是
     */
    @TableField("is_old_shelf_process")
    private Integer isOldShelfProcess;


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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public String getMerchantTypeCn() {
        return merchantTypeCn;
    }

    public void setMerchantTypeCn(String merchantTypeCn) {
        this.merchantTypeCn = merchantTypeCn;
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
        this.userId = userId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMerchantZone() {
        return merchantZone;
    }

    public void setMerchantZone(String merchantZone) {
        this.merchantZone = merchantZone;
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

    public Integer getContractCycle() {
        return contractCycle;
    }

    public void setContractCycle(Integer contractCycle) {
        this.contractCycle = contractCycle;
    }

    public String getSettlementAccount() {
        return settlementAccount;
    }

    public void setSettlementAccount(String settlementAccount) {
        this.settlementAccount = settlementAccount;
    }

    public Integer getHisTradeCount() {
        return hisTradeCount;
    }

    public void setHisTradeCount(Integer hisTradeCount) {
        this.hisTradeCount = hisTradeCount;
    }

    public Integer getShopkeeperType() {
        return shopkeeperType;
    }

    public void setShopkeeperType(Integer shopkeeperType) {
        this.shopkeeperType = shopkeeperType;
    }

    public String getShopkeeperTypeCn() {
        return shopkeeperTypeCn;
    }

    public void setShopkeeperTypeCn(String shopkeeperTypeCn) {
        this.shopkeeperTypeCn = shopkeeperTypeCn;
    }

    public Integer getIsOnlineService() {
        return isOnlineService;
    }

    public void setIsOnlineService(Integer isOnlineService) {
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public Float getWithdrawScale() {
        return withdrawScale;
    }

    public void setWithdrawScale(Float withdrawScale) {
        this.withdrawScale = withdrawScale;
    }

    public Integer getTradeAmountShowFlag() {
        return tradeAmountShowFlag;
    }

    public void setTradeAmountShowFlag(Integer tradeAmountShowFlag) {
        this.tradeAmountShowFlag = tradeAmountShowFlag;
    }

    public String getShareQrCodePath() {
        return shareQrCodePath;
    }

    public void setShareQrCodePath(String shareQrCodePath) {
        this.shareQrCodePath = shareQrCodePath;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
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
        this.idName = idName;
    }

    public Integer getAdvanceProfitFlag() {
        return advanceProfitFlag;
    }

    public void setAdvanceProfitFlag(Integer advanceProfitFlag) {
        this.advanceProfitFlag = advanceProfitFlag;
    }

    public BigDecimal getTecFeeAccumulate() {
        return tecFeeAccumulate;
    }

    public void setTecFeeAccumulate(BigDecimal tecFeeAccumulate) {
        this.tecFeeAccumulate = tecFeeAccumulate;
    }

    public Integer getMerchantSubType() {
        return merchantSubType;
    }

    public void setMerchantSubType(Integer merchantSubType) {
        this.merchantSubType = merchantSubType;
    }

    public String getMerchantSubTypeCn() {
        return merchantSubTypeCn;
    }

    public void setMerchantSubTypeCn(String merchantSubTypeCn) {
        this.merchantSubTypeCn = merchantSubTypeCn;
    }

    public String getStartShopTime() {
        return startShopTime;
    }

    public void setStartShopTime(String startShopTime) {
        this.startShopTime = startShopTime;
    }

    public String getEndShopTime() {
        return endShopTime;
    }

    public void setEndShopTime(String endShopTime) {
        this.endShopTime = endShopTime;
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
        this.storePhoneNo = storePhoneNo;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getIsPhoneCheck() {
        return isPhoneCheck;
    }

    public void setIsPhoneCheck(Integer isPhoneCheck) {
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

    public Integer getIsOldShelfProcess() {
        return isOldShelfProcess;
    }

    public void setIsOldShelfProcess(Integer isOldShelfProcess) {
        this.isOldShelfProcess = isOldShelfProcess;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MerchantInfo{" +
        "id=" + id +
        ", name=" + name +
        ", addr=" + addr +
        ", personName=" + personName +
        ", telNo=" + telNo +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", merchantType=" + merchantType +
        ", merchantTypeCn=" + merchantTypeCn +
        ", parentMerchantId=" + parentMerchantId +
        ", userId=" + userId +
        ", merchantLevel=" + merchantLevel +
        ", merchantLevelCn=" + merchantLevelCn +
        ", status=" + status +
        ", remark=" + remark +
        ", merchantZone=" + merchantZone +
        ", industryCategoryCode=" + industryCategoryCode +
        ", industryCategoryCn=" + industryCategoryCn +
        ", contractCycle=" + contractCycle +
        ", settlementAccount=" + settlementAccount +
        ", hisTradeCount=" + hisTradeCount +
        ", shopkeeperType=" + shopkeeperType +
        ", shopkeeperTypeCn=" + shopkeeperTypeCn +
        ", isOnlineService=" + isOnlineService +
        ", hisTradeAmount=" + hisTradeAmount +
        ", investMoney=" + investMoney +
        ", settlementCustNo=" + settlementCustNo +
        ", clientType=" + clientType +
        ", legalRepresentative=" + legalRepresentative +
        ", uniformSocialCreditCode=" + uniformSocialCreditCode +
        ", idNumber=" + idNumber +
        ", province=" + province +
        ", city=" + city +
        ", zone=" + zone +
        ", withdrawScale=" + withdrawScale +
        ", tradeAmountShowFlag=" + tradeAmountShowFlag +
        ", shareQrCodePath=" + shareQrCodePath +
        ", receiver=" + receiver +
        ", receiverTel=" + receiverTel +
        ", receiverAddr=" + receiverAddr +
        ", currentRoundNum=" + currentRoundNum +
        ", totalRoundNum=" + totalRoundNum +
        ", idName=" + idName +
        ", advanceProfitFlag=" + advanceProfitFlag +
        ", tecFeeAccumulate=" + tecFeeAccumulate +
        ", merchantSubType=" + merchantSubType +
        ", merchantSubTypeCn=" + merchantSubTypeCn +
        ", startShopTime=" + startShopTime +
        ", endShopTime=" + endShopTime +
        ", starLevel=" + starLevel +
        ", weekUseScale=" + weekUseScale +
        ", perConsume=" + perConsume +
        ", storePhoneNo=" + storePhoneNo +
        ", profile=" + profile +
        ", isPhoneCheck=" + isPhoneCheck +
        ", withdrawWayId=" + withdrawWayId +
        ", roomCount=" + roomCount +
        ", provinceId=" + provinceId +
        ", cityId=" + cityId +
        ", zoneId=" + zoneId +
        ", isOldShelfProcess=" + isOldShelfProcess +
        "}";
    }
}
