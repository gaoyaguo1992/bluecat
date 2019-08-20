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
 * @since 2019-01-21
 */
@TableName("share_trade_info")
public class ShareTradeInfo extends Model<ShareTradeInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 交易id
     */
    private Long id;
    /**
     * 设备号
     */
    @TableField("device_no")
    private Long deviceNo;
    /**
     * 充电器id
     */
    @TableField("charger_id")
    private Long chargerId;
    /**
     * 客户编号
     */
    @TableField("cust_id")
    private Long custId;
    /**
     * 预付金
     */
    @TableField("yfj_amount")
    private BigDecimal yfjAmount;
    /**
     * 交易金额
     */
    @TableField("trade_amount")
    private BigDecimal tradeAmount;
    /**
     * 交易类型
     */
    @TableField("trade_type")
    private Integer tradeType;
    /**
     * 交易名称
     */
    @TableField("trade_name")
    private String tradeName;
    /**
     * 借出时间
     */
    @TableField("borrow_datetime")
    private Date borrowDatetime;
    /**
     * 归还时间
     */
    @TableField("back_datetime")
    private Date backDatetime;
    /**
     * 交易状态名
     */
    @TableField("trade_status_name")
    private String tradeStatusName;
    /**
     * 交易状态
     */
    @TableField("trade_status")
    private Integer tradeStatus;
    /**
     * 交易x
     */
    @TableField("x_coordinate")
    private BigDecimal xCoordinate;
    /**
     * 交易y
     */
    @TableField("y_coordinate")
    private BigDecimal yCoordinate;
    /**
     * 分润状态(0：未分润，1：已分)
     */
    @TableField("benefit_status")
    private Integer benefitStatus;
    /**
     * 分润时间
     */
    @TableField("benefit_datetime")
    private Date benefitDatetime;
    /**
     * 平台收入
     */
    @TableField("platform_amount")
    private BigDecimal platformAmount;
    /**
     * 代理商收入1
     */
    @TableField("agents1_amount")
    private BigDecimal agents1Amount;
    /**
     * 代理商收入2
     */
    @TableField("agents2_amount")
    private BigDecimal agents2Amount;
    /**
     * 代理商收入3
     */
    @TableField("agents3_amount")
    private BigDecimal agents3Amount;
    /**
     * 铺货商收入
     */
    @TableField("shopkeeper_amount")
    private BigDecimal shopkeeperAmount;
    /**
     * 加盟商收入
     */
    @TableField("alliance_business_amount")
    private BigDecimal allianceBusinessAmount;
    /**
     * 退款时对应的交易id
     */
    @TableField("back_trade_id")
    private Long backTradeId;
    /**
     * 交易地址
     */
    @TableField("trade_address")
    private String tradeAddress;
    /**
     * 交易区
     */
    @TableField("trade_zone")
    private String tradeZone;
    /**
     * 交易市
     */
    @TableField("trade_city")
    private String tradeCity;
    /**
     * 交易省
     */
    @TableField("trade_province")
    private String tradeProvince;
    /**
     * 交易商户id
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 代理商1id
     */
    @TableField("agents1_id")
    private Long agents1Id;
    /**
     * 代理商2id
     */
    @TableField("agents2_id")
    private Long agents2Id;
    /**
     * 代理商3id
     */
    @TableField("agents3_id")
    private Long agents3Id;
    /**
     * 铺货商id
     */
    @TableField("shopkeeper_id")
    private Long shopkeeperId;
    /**
     * 加盟商id
     */
    @TableField("alliance_business_id")
    private Long allianceBusinessId;
    /**
     * 交易创建者
     */
    @TableField("create_id")
    private Long createId;
    /**
     * 创建时间
     */
    @TableField("create_datetime")
    private Date createDatetime;
    /**
     * 修改时间
     */
    @TableField("update_datetime")
    private Date updateDatetime;
    /**
     * 结账状态
     */
    @TableField("settle_accounts_status")
    private Integer settleAccountsStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Long deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Long getChargerId() {
        return chargerId;
    }

    public void setChargerId(Long chargerId) {
        this.chargerId = chargerId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public BigDecimal getYfjAmount() {
        return yfjAmount;
    }

    public void setYfjAmount(BigDecimal yfjAmount) {
        this.yfjAmount = yfjAmount;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public Date getBorrowDatetime() {
        return borrowDatetime;
    }

    public void setBorrowDatetime(Date borrowDatetime) {
        this.borrowDatetime = borrowDatetime;
    }

    public Date getBackDatetime() {
        return backDatetime;
    }

    public void setBackDatetime(Date backDatetime) {
        this.backDatetime = backDatetime;
    }

    public String getTradeStatusName() {
        return tradeStatusName;
    }

    public void setTradeStatusName(String tradeStatusName) {
        this.tradeStatusName = tradeStatusName;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public BigDecimal getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(BigDecimal xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public BigDecimal getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(BigDecimal yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Integer getBenefitStatus() {
        return benefitStatus;
    }

    public void setBenefitStatus(Integer benefitStatus) {
        this.benefitStatus = benefitStatus;
    }

    public Date getBenefitDatetime() {
        return benefitDatetime;
    }

    public void setBenefitDatetime(Date benefitDatetime) {
        this.benefitDatetime = benefitDatetime;
    }

    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
    }

    public BigDecimal getAgents1Amount() {
        return agents1Amount;
    }

    public void setAgents1Amount(BigDecimal agents1Amount) {
        this.agents1Amount = agents1Amount;
    }

    public BigDecimal getAgents2Amount() {
        return agents2Amount;
    }

    public void setAgents2Amount(BigDecimal agents2Amount) {
        this.agents2Amount = agents2Amount;
    }

    public BigDecimal getAgents3Amount() {
        return agents3Amount;
    }

    public void setAgents3Amount(BigDecimal agents3Amount) {
        this.agents3Amount = agents3Amount;
    }

    public BigDecimal getShopkeeperAmount() {
        return shopkeeperAmount;
    }

    public void setShopkeeperAmount(BigDecimal shopkeeperAmount) {
        this.shopkeeperAmount = shopkeeperAmount;
    }

    public BigDecimal getAllianceBusinessAmount() {
        return allianceBusinessAmount;
    }

    public void setAllianceBusinessAmount(BigDecimal allianceBusinessAmount) {
        this.allianceBusinessAmount = allianceBusinessAmount;
    }

    public Long getBackTradeId() {
        return backTradeId;
    }

    public void setBackTradeId(Long backTradeId) {
        this.backTradeId = backTradeId;
    }

    public String getTradeAddress() {
        return tradeAddress;
    }

    public void setTradeAddress(String tradeAddress) {
        this.tradeAddress = tradeAddress;
    }

    public String getTradeZone() {
        return tradeZone;
    }

    public void setTradeZone(String tradeZone) {
        this.tradeZone = tradeZone;
    }

    public String getTradeCity() {
        return tradeCity;
    }

    public void setTradeCity(String tradeCity) {
        this.tradeCity = tradeCity;
    }

    public String getTradeProvince() {
        return tradeProvince;
    }

    public void setTradeProvince(String tradeProvince) {
        this.tradeProvince = tradeProvince;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getAgents1Id() {
        return agents1Id;
    }

    public void setAgents1Id(Long agents1Id) {
        this.agents1Id = agents1Id;
    }

    public Long getAgents2Id() {
        return agents2Id;
    }

    public void setAgents2Id(Long agents2Id) {
        this.agents2Id = agents2Id;
    }

    public Long getAgents3Id() {
        return agents3Id;
    }

    public void setAgents3Id(Long agents3Id) {
        this.agents3Id = agents3Id;
    }

    public Long getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Long shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public Long getAllianceBusinessId() {
        return allianceBusinessId;
    }

    public void setAllianceBusinessId(Long allianceBusinessId) {
        this.allianceBusinessId = allianceBusinessId;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getSettleAccountsStatus() {
        return settleAccountsStatus;
    }

    public void setSettleAccountsStatus(Integer settleAccountsStatus) {
        this.settleAccountsStatus = settleAccountsStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ShareTradeInfo{" +
        "id=" + id +
        ", deviceNo=" + deviceNo +
        ", chargerId=" + chargerId +
        ", custId=" + custId +
        ", yfjAmount=" + yfjAmount +
        ", tradeAmount=" + tradeAmount +
        ", tradeType=" + tradeType +
        ", tradeName=" + tradeName +
        ", borrowDatetime=" + borrowDatetime +
        ", backDatetime=" + backDatetime +
        ", tradeStatusName=" + tradeStatusName +
        ", tradeStatus=" + tradeStatus +
        ", xCoordinate=" + xCoordinate +
        ", yCoordinate=" + yCoordinate +
        ", benefitStatus=" + benefitStatus +
        ", benefitDatetime=" + benefitDatetime +
        ", platformAmount=" + platformAmount +
        ", agents1Amount=" + agents1Amount +
        ", agents2Amount=" + agents2Amount +
        ", agents3Amount=" + agents3Amount +
        ", shopkeeperAmount=" + shopkeeperAmount +
        ", allianceBusinessAmount=" + allianceBusinessAmount +
        ", backTradeId=" + backTradeId +
        ", tradeAddress=" + tradeAddress +
        ", tradeZone=" + tradeZone +
        ", tradeCity=" + tradeCity +
        ", tradeProvince=" + tradeProvince +
        ", merchantId=" + merchantId +
        ", agents1Id=" + agents1Id +
        ", agents2Id=" + agents2Id +
        ", agents3Id=" + agents3Id +
        ", shopkeeperId=" + shopkeeperId +
        ", allianceBusinessId=" + allianceBusinessId +
        ", createId=" + createId +
        ", createDatetime=" + createDatetime +
        ", updateDatetime=" + updateDatetime +
        ", settleAccountsStatus=" + settleAccountsStatus +
        "}";
    }
}
