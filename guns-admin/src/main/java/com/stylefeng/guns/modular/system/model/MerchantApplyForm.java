package com.stylefeng.guns.modular.system.model;

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
 * @since 2019-04-03
 */
@TableName("merchant_apply_form")
public class MerchantApplyForm extends Model<MerchantApplyForm> {

    private static final long serialVersionUID = 1L;

    /**
     * 商户id
     */
    private Long id;
    /**
     * 商户名称
     */
    @TableField("merchant_name")
    private String merchantName;
    /**
     * 联系人
     */
    @TableField("person_name")
    private String personName;
    /**
     * 联系电话
     */
    @TableField("tel_no")
    private String telNo;
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
     * 代理商区
     */
    @TableField("agents_zone")
    private String agentsZone;
    /**
     * 铺货渠道
     */
    @TableField("throw_dev_channel")
    private String throwDevChannel;
    /**
     * 申请类型
     */
    @TableField("apply_type")
    private Long applyType;
    /**
     * 申请名
     */
    @TableField("apply_type_cn")
    private String applyTypeCn;
    /**
     * 处理状态
     */
    @TableField("do_status")
    private Long doStatus;
    /**
     * 处理状态名
     */
    @TableField("do_status_cn")
    private String doStatusCn;
    /**
     * 客户id
     */
    @TableField("cust_id")
    private Long custId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public String getAgentsZone() {
        return agentsZone;
    }

    public void setAgentsZone(String agentsZone) {
        this.agentsZone = agentsZone;
    }

    public String getThrowDevChannel() {
        return throwDevChannel;
    }

    public void setThrowDevChannel(String throwDevChannel) {
        this.throwDevChannel = throwDevChannel;
    }

    public Long getApplyType() {
        return applyType;
    }

    public void setApplyType(Long applyType) {
        this.applyType = applyType;
    }

    public String getApplyTypeCn() {
        return applyTypeCn;
    }

    public void setApplyTypeCn(String applyTypeCn) {
        this.applyTypeCn = applyTypeCn;
    }

    public Long getDoStatus() {
        return doStatus;
    }

    public void setDoStatus(Long doStatus) {
        this.doStatus = doStatus;
    }

    public String getDoStatusCn() {
        return doStatusCn;
    }

    public void setDoStatusCn(String doStatusCn) {
        this.doStatusCn = doStatusCn;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MerchantApplyForm{" +
        "id=" + id +
        ", merchantName=" + merchantName +
        ", personName=" + personName +
        ", telNo=" + telNo +
        ", province=" + province +
        ", city=" + city +
        ", zone=" + zone +
        ", agentsZone=" + agentsZone +
        ", throwDevChannel=" + throwDevChannel +
        ", applyType=" + applyType +
        ", applyTypeCn=" + applyTypeCn +
        ", doStatus=" + doStatus +
        ", doStatusCn=" + doStatusCn +
        ", custId=" + custId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
