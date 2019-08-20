package com.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备表
 * </p>
 *
 * @author alian li
 * @since 2019-01-20
 */
@TableName("share_device_info")
public class DeviceInfo extends Model<DeviceInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备id
     */
    private Long Id;
    /**
     * 设备类型
     */
    @TableField("device_type_id")
    private Integer deviceTypeId;
    /**
     * 设备类型名
     */
    @TableField("device_type_name")
    private String deviceTypeName;
    /**
     * 设备状态(0：未上线，1：上线)
     */
    @TableField("device_status")
    private Integer deviceStatus;
    /**
     * 上架时间
     */
    @TableField("online_datetime")
    private Date onlineDatetime;
    /**
     * 上架商户id
     */
    @TableField("online_merchant_id")
    private Long onlineMerchantId;
    /**
     * 上架地址
     */
    @TableField("online_address")
    private String onlineAddress;
    /**
     * 上架市
     */
    @TableField("online_city")
    private String onlineCity;
    /**
     * 上架区
     */
    @TableField("online_zone")
    private String onlineZone;
    /**
     * 上架省市区
     */
    @TableField("online_province")
    private String onlineProvince;
    /**
     * 上架x坐标
     */
    @TableField("online_x_coordinate")
    private BigDecimal onlineXCoordinate;
    /**
     * 上架y坐标
     */
    @TableField("online_y_coordinate")
    private BigDecimal onlineYCoordinate;
    /**
     * 费用类型
     */
    @TableField("fee_type_id")
    private Integer feeTypeId;
    /**
     * 费用类型名
     */
    @TableField("fee_type_name")
    private String feeTypeName;
    /**
     * 预付金
     */
    @TableField("yfj_amount")
    private BigDecimal yfjAmount;
    /**
     * 首先消费金额
     */
    @TableField("first_amount")
    private BigDecimal firstAmount;
    /**
     * 首先消费时长
     */
    @TableField("first_minutes")
    private Long firstMinutes;
    /**
     * 每小时多少钱
     */
    @TableField("amount_per_hour")
    private BigDecimal amountPerHour;
    /**
     * 24小时最多多少钱
     */
    @TableField("amount_max_24hour")
    private BigDecimal amountMax24hour;
    /**
     * 费用类型id
     */
    @TableField("fee1_type_Id")
    private Integer fee1TypeId;
    /**
     * 费用名1
     */
    @TableField("fee1_type_name")
    private String fee1TypeName;
    /**
     * 确定几小时间(选项一)
     */
    @TableField("fee1_hour_type")
    private Integer fee1HourType;
    /**
     * 多少钱(选项一)
     */
    @TableField("fee1_hour_amount")
    private BigDecimal fee1HourAmount;
    /**
     * 费用类型id
     */
    @TableField("fee2_type_id")
    private Integer fee2TypeId;
    /**
     * 费用名2
     */
    @TableField("fee2_type_name")
    private String fee2TypeName;
    /**
     * 确定几小时间(选项二)
     */
    @TableField("fee2_hour_type")
    private Integer fee2HourType;
    /**
     * 多少钱(选项二)
     */
    @TableField("fee2_hour_amount")
    private BigDecimal fee2HourAmount;
    /**
     * 费用类型id
     */
    @TableField("fee3_type_id")
    private Integer fee3TypeId;
    /**
     * 费用名3
     */
    @TableField("fee3_type_name")
    private String fee3TypeName;
    /**
     * 确定几小时间(选项三)
     */
    @TableField("fee3_hour_type")
    private Integer fee3HourType;
    /**
     * 多少钱(选项三)
     */
    @TableField("fee3_hour_amount")
    private BigDecimal fee3HourAmount;
    /**
     * 平台分润比例
     */
    @TableField("platform_rato")
    private BigDecimal platformRato;
    /**
     * 1级代理商id
     */
    @TableField("agents1_id")
    private Long agents1Id;
    /**
     * 1级代理商名
     */
    @TableField("agents1_cn")
    private String agents1Cn;
    /**
     * 1级代理商分润比例
     */
    @TableField("agents1_rato")
    private BigDecimal agents1Rato;
    /**
     * 2级代理商id
     */
    @TableField("agents2_id")
    private Long agents2Id;
    /**
     * 2级代理商名
     */
    @TableField("agents2_cn")
    private String agents2Cn;
    /**
     * 2级代理商分润比例
     */
    @TableField("agents2_rato")
    private BigDecimal agents2Rato;
    /**
     * 3级代理商id
     */
    @TableField("agents3_id")
    private Long agents3Id;
    /**
     * 3级代理商id
     */
    @TableField("agents3_rato")
    private BigDecimal agents3Rato;
    /**
     * 3级代理商id
     */
    @TableField("agents3_cn")
    private String agents3Cn;
    /**
     * 铺货商id
     */
    @TableField("shopkeeper_id")
    private Long shopkeeperId;
    /**
     * 铺货商名
     */
    @TableField("shopkeeper_cn")
    private String shopkeeperCn;
    /**
     * 铺货商名分润比例
     */
    @TableField("shopkeeper_rato")
    private BigDecimal shopkeeperRato;
    /**
     * 加盟商id
     */
    @TableField("alliance_business_id")
    private Long allianceBusinessId;
    /**
     * 加盟商名
     */
    @TableField("alliance_business_cn")
    private String allianceBusinessCn;
    /**
     * 加盟商分润比例
     */
    @TableField("alliance_business_rate")
    private BigDecimal allianceBusinessRate;
    /**
     * 创建者id
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
     * 二维码生成图片
     */
    @TableField("SQR_url")
    private String sqrUrl;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预付金额退回方式
     */
    @TableField("yfj_reback_type")
    private Integer yfjRebackType;


    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Date getOnlineDatetime() {
        return onlineDatetime;
    }

    public void setOnlineDatetime(Date onlineDatetime) {
        this.onlineDatetime = onlineDatetime;
    }

    public Long getOnlineMerchantId() {
        return onlineMerchantId;
    }

    public void setOnlineMerchantId(Long onlineMerchantId) {
        this.onlineMerchantId = onlineMerchantId;
    }

    public String getOnlineAddress() {
        return onlineAddress;
    }

    public void setOnlineAddress(String onlineAddress) {
        this.onlineAddress = onlineAddress;
    }

    public String getOnlineCity() {
        return onlineCity;
    }

    public void setOnlineCity(String onlineCity) {
        this.onlineCity = onlineCity;
    }

    public String getOnlineZone() {
        return onlineZone;
    }

    public void setOnlineZone(String onlineZone) {
        this.onlineZone = onlineZone;
    }

    public String getOnlineProvince() {
        return onlineProvince;
    }

    public void setOnlineProvince(String onlineProvince) {
        this.onlineProvince = onlineProvince;
    }

    public BigDecimal getOnlineXCoordinate() {
        return onlineXCoordinate;
    }

    public void setOnlineXCoordinate(BigDecimal onlineXCoordinate) {
        this.onlineXCoordinate = onlineXCoordinate;
    }

    public BigDecimal getOnlineYCoordinate() {
        return onlineYCoordinate;
    }

    public void setOnlineYCoordinate(BigDecimal onlineYCoordinate) {
        this.onlineYCoordinate = onlineYCoordinate;
    }

    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeTypeName() {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    public BigDecimal getYfjAmount() {
        return yfjAmount;
    }

    public void setYfjAmount(BigDecimal yfjAmount) {
        this.yfjAmount = yfjAmount;
    }

    public BigDecimal getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(BigDecimal firstAmount) {
        this.firstAmount = firstAmount;
    }

    public Long getFirstMinutes() {
        return firstMinutes;
    }

    public void setFirstMinutes(Long firstMinutes) {
        this.firstMinutes = firstMinutes;
    }

    public BigDecimal getAmountPerHour() {
        return amountPerHour;
    }

    public void setAmountPerHour(BigDecimal amountPerHour) {
        this.amountPerHour = amountPerHour;
    }

    public BigDecimal getAmountMax24hour() {
        return amountMax24hour;
    }

    public void setAmountMax24hour(BigDecimal amountMax24hour) {
        this.amountMax24hour = amountMax24hour;
    }

    public Integer getFee1TypeId() {
        return fee1TypeId;
    }

    public void setFee1TypeId(Integer fee1TypeId) {
        this.fee1TypeId = fee1TypeId;
    }

    public String getFee1TypeName() {
        return fee1TypeName;
    }

    public void setFee1TypeName(String fee1TypeName) {
        this.fee1TypeName = fee1TypeName;
    }

    public Integer getFee1HourType() {
        return fee1HourType;
    }

    public void setFee1HourType(Integer fee1HourType) {
        this.fee1HourType = fee1HourType;
    }

    public BigDecimal getFee1HourAmount() {
        return fee1HourAmount;
    }

    public void setFee1HourAmount(BigDecimal fee1HourAmount) {
        this.fee1HourAmount = fee1HourAmount;
    }

    public Integer getFee2TypeId() {
        return fee2TypeId;
    }

    public void setFee2TypeId(Integer fee2TypeId) {
        this.fee2TypeId = fee2TypeId;
    }

    public String getFee2TypeName() {
        return fee2TypeName;
    }

    public void setFee2TypeName(String fee2TypeName) {
        this.fee2TypeName = fee2TypeName;
    }

    public Integer getFee2HourType() {
        return fee2HourType;
    }

    public void setFee2HourType(Integer fee2HourType) {
        this.fee2HourType = fee2HourType;
    }

    public BigDecimal getFee2HourAmount() {
        return fee2HourAmount;
    }

    public void setFee2HourAmount(BigDecimal fee2HourAmount) {
        this.fee2HourAmount = fee2HourAmount;
    }

    public Integer getFee3TypeId() {
        return fee3TypeId;
    }

    public void setFee3TypeId(Integer fee3TypeId) {
        this.fee3TypeId = fee3TypeId;
    }

    public String getFee3TypeName() {
        return fee3TypeName;
    }

    public void setFee3TypeName(String fee3TypeName) {
        this.fee3TypeName = fee3TypeName;
    }

    public Integer getFee3HourType() {
        return fee3HourType;
    }

    public void setFee3HourType(Integer fee3HourType) {
        this.fee3HourType = fee3HourType;
    }

    public BigDecimal getFee3HourAmount() {
        return fee3HourAmount;
    }

    public void setFee3HourAmount(BigDecimal fee3HourAmount) {
        this.fee3HourAmount = fee3HourAmount;
    }

    public BigDecimal getPlatformRato() {
        return platformRato;
    }

    public void setPlatformRato(BigDecimal platformRato) {
        this.platformRato = platformRato;
    }

    public Long getAgents1Id() {
        return agents1Id;
    }

    public void setAgents1Id(Long agents1Id) {
        this.agents1Id = agents1Id;
    }

    public String getAgents1Cn() {
        return agents1Cn;
    }

    public void setAgents1Cn(String agents1Cn) {
        this.agents1Cn = agents1Cn;
    }

    public BigDecimal getAgents1Rato() {
        return agents1Rato;
    }

    public void setAgents1Rato(BigDecimal agents1Rato) {
        this.agents1Rato = agents1Rato;
    }

    public Long getAgents2Id() {
        return agents2Id;
    }

    public void setAgents2Id(Long agents2Id) {
        this.agents2Id = agents2Id;
    }

    public String getAgents2Cn() {
        return agents2Cn;
    }

    public void setAgents2Cn(String agents2Cn) {
        this.agents2Cn = agents2Cn;
    }

    public BigDecimal getAgents2Rato() {
        return agents2Rato;
    }

    public void setAgents2Rato(BigDecimal agents2Rato) {
        this.agents2Rato = agents2Rato;
    }

    public Long getAgents3Id() {
        return agents3Id;
    }

    public void setAgents3Id(Long agents3Id) {
        this.agents3Id = agents3Id;
    }

    public BigDecimal getAgents3Rato() {
        return agents3Rato;
    }

    public void setAgents3Rato(BigDecimal agents3Rato) {
        this.agents3Rato = agents3Rato;
    }

    public String getAgents3Cn() {
        return agents3Cn;
    }

    public void setAgents3Cn(String agents3Cn) {
        this.agents3Cn = agents3Cn;
    }

    public Long getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Long shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public String getShopkeeperCn() {
        return shopkeeperCn;
    }

    public void setShopkeeperCn(String shopkeeperCn) {
        this.shopkeeperCn = shopkeeperCn;
    }

    public BigDecimal getShopkeeperRato() {
        return shopkeeperRato;
    }

    public void setShopkeeperRato(BigDecimal shopkeeperRato) {
        this.shopkeeperRato = shopkeeperRato;
    }

    public Long getAllianceBusinessId() {
        return allianceBusinessId;
    }

    public void setAllianceBusinessId(Long allianceBusinessId) {
        this.allianceBusinessId = allianceBusinessId;
    }

    public String getAllianceBusinessCn() {
        return allianceBusinessCn;
    }

    public void setAllianceBusinessCn(String allianceBusinessCn) {
        this.allianceBusinessCn = allianceBusinessCn;
    }

    public BigDecimal getAllianceBusinessRate() {
        return allianceBusinessRate;
    }

    public void setAllianceBusinessRate(BigDecimal allianceBusinessRate) {
        this.allianceBusinessRate = allianceBusinessRate;
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

    public String getSqrUrl() {
        return sqrUrl;
    }

    public void setSqrUrl(String sqrUrl) {
        this.sqrUrl = sqrUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getYfjRebackType() {
        return yfjRebackType;
    }

    public void setYfjRebackType(Integer yfjRebackType) {
        this.yfjRebackType = yfjRebackType;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
        "Id=" + Id +
        ", deviceTypeId=" + deviceTypeId +
        ", deviceTypeName=" + deviceTypeName +
        ", deviceStatus=" + deviceStatus +
        ", onlineDatetime=" + onlineDatetime +
        ", onlineMerchantId=" + onlineMerchantId +
        ", onlineAddress=" + onlineAddress +
        ", onlineCity=" + onlineCity +
        ", onlineZone=" + onlineZone +
        ", onlineProvince=" + onlineProvince +
        ", onlineXCoordinate=" + onlineXCoordinate +
        ", onlineYCoordinate=" + onlineYCoordinate +
        ", feeTypeId=" + feeTypeId +
        ", feeTypeName=" + feeTypeName +
        ", yfjAmount=" + yfjAmount +
        ", firstAmount=" + firstAmount +
        ", firstMinutes=" + firstMinutes +
        ", amountPerHour=" + amountPerHour +
        ", amountMax24hour=" + amountMax24hour +
        ", fee1TypeId=" + fee1TypeId +
        ", fee1TypeName=" + fee1TypeName +
        ", fee1HourType=" + fee1HourType +
        ", fee1HourAmount=" + fee1HourAmount +
        ", fee2TypeId=" + fee2TypeId +
        ", fee2TypeName=" + fee2TypeName +
        ", fee2HourType=" + fee2HourType +
        ", fee2HourAmount=" + fee2HourAmount +
        ", fee3TypeId=" + fee3TypeId +
        ", fee3TypeName=" + fee3TypeName +
        ", fee3HourType=" + fee3HourType +
        ", fee3HourAmount=" + fee3HourAmount +
        ", platformRato=" + platformRato +
        ", agents1Id=" + agents1Id +
        ", agents1Cn=" + agents1Cn +
        ", agents1Rato=" + agents1Rato +
        ", agents2Id=" + agents2Id +
        ", agents2Cn=" + agents2Cn +
        ", agents2Rato=" + agents2Rato +
        ", agents3Id=" + agents3Id +
        ", agents3Rato=" + agents3Rato +
        ", agents3Cn=" + agents3Cn +
        ", shopkeeperId=" + shopkeeperId +
        ", shopkeeperCn=" + shopkeeperCn +
        ", shopkeeperRato=" + shopkeeperRato +
        ", allianceBusinessId=" + allianceBusinessId +
        ", allianceBusinessCn=" + allianceBusinessCn +
        ", allianceBusinessRate=" + allianceBusinessRate +
        ", createId=" + createId +
        ", createDatetime=" + createDatetime +
        ", updateDatetime=" + updateDatetime +
        ", sqrUrl=" + sqrUrl +
        ", remark=" + remark +
        ", yfjRebackType=" + yfjRebackType +
        "}";
    }
}
