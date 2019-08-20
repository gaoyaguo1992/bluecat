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
 * @author alian li
 * @since 2019-01-20
 */
@TableName("share_charger")
public class Charger extends Model<Charger> {

    private static final long serialVersionUID = 1L;

    /**
     * 充电器id
     */
    private Long id;
    /**
     * 设备id
     */
    @TableField("device_id")
    private Long deviceId;
    /**
     * 设备类型id
     */
    @TableField("charger_type_id")
    private Integer chargerTypeId;
    /**
     * 充电器类型名
     */
    @TableField("charger_type_name")
    private String chargerTypeName;
    /**
     * 密码系号
     */
    @TableField("pwd_index")
    private Long pwdIndex;
    /**
     * 密码
     */
    private String pwds;
    /**
     * 到昨天为止使用次数
     */
    @TableField("use_count_times_yesterday")
    private BigDecimal useCountTimesYesterday;
    /**
     * 到昨天为止收入金额
     */
    @TableField("total_amount_yesterday")
    private BigDecimal totalAmountYesterday;
    /**
     * 备注
     */
    private String remark;
    /**
     * 1级代理商
     */
    @TableField("agents1_id")
    private Long agents1Id;
    /**
     * 2级代理商
     */
    @TableField("agents2_id")
    private Long agents2Id;
    /**
     * 3级代理商
     */
    @TableField("agents3_id")
    private Long agents3Id;
    /**
     * 密码模式
     */
    @TableField("pwd_mode")
    private Long pwdMode;
    /**
     * 密码原子
     */
    @TableField("refactor_idx")
    private String refactorIdx;
    /**
     * 创建者
     */
    @TableField("create_id")
    private Long createId;
    /**
     * 创建时间
     */
    @TableField("create_date_time")
    private Date createDateTime;
    /**
     * 更新时间
     */
    @TableField("update_date_time")
    private Date updateDateTime;
    /**
     * 最近使用时间
     */
    @TableField("last_use_time")
    private Date lastUseTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getChargerTypeId() {
        return chargerTypeId;
    }

    public void setChargerTypeId(Integer chargerTypeId) {
        this.chargerTypeId = chargerTypeId;
    }

    public String getChargerTypeName() {
        return chargerTypeName;
    }

    public void setChargerTypeName(String chargerTypeName) {
        this.chargerTypeName = chargerTypeName;
    }

    public Long getPwdIndex() {
        return pwdIndex;
    }

    public void setPwdIndex(Long pwdIndex) {
        this.pwdIndex = pwdIndex;
    }

    public String getPwds() {
        return pwds;
    }

    public void setPwds(String pwds) {
        this.pwds = pwds;
    }

    public BigDecimal getUseCountTimesYesterday() {
        return useCountTimesYesterday;
    }

    public void setUseCountTimesYesterday(BigDecimal useCountTimesYesterday) {
        this.useCountTimesYesterday = useCountTimesYesterday;
    }

    public BigDecimal getTotalAmountYesterday() {
        return totalAmountYesterday;
    }

    public void setTotalAmountYesterday(BigDecimal totalAmountYesterday) {
        this.totalAmountYesterday = totalAmountYesterday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getPwdMode() {
        return pwdMode;
    }

    public void setPwdMode(Long pwdMode) {
        this.pwdMode = pwdMode;
    }

    public String getRefactorIdx() {
        return refactorIdx;
    }

    public void setRefactorIdx(String refactorIdx) {
        this.refactorIdx = refactorIdx;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Date getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Charger{" +
        "id=" + id +
        ", deviceId=" + deviceId +
        ", chargerTypeId=" + chargerTypeId +
        ", chargerTypeName=" + chargerTypeName +
        ", pwdIndex=" + pwdIndex +
        ", pwds=" + pwds +
        ", useCountTimesYesterday=" + useCountTimesYesterday +
        ", totalAmountYesterday=" + totalAmountYesterday +
        ", remark=" + remark +
        ", agents1Id=" + agents1Id +
        ", agents2Id=" + agents2Id +
        ", agents3Id=" + agents3Id +
        ", pwdMode=" + pwdMode +
        ", refactorIdx=" + refactorIdx +
        ", createId=" + createId +
        ", createDateTime=" + createDateTime +
        ", updateDateTime=" + updateDateTime +
        ", lastUseTime=" + lastUseTime +
        "}";
    }
}
