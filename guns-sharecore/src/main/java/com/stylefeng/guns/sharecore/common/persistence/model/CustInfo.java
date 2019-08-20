package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author alan li
 * @since 2019-01-01
 */
@TableName("cust_info")
public class CustInfo extends Model<CustInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("merchant_login_time")
    private Date merchantLoginTime;
    @TableField("merchant_id")
    private Long merchantId;
    @TableId("cust_no")
    private Long custNo;
    @TableField("cust_name")
    private String custName;
    @TableField("open_id")
    private String openId;
    @TableField("cust_type")
    private Integer custType;
    @TableField("tel_no")
    private String telNo;
    @TableField("tel_valid")
    private Integer telValid;
    private String email;
    private String addr;
    @TableField("post_code")
    private String postCode;
    private String remark;
    @TableField("reg_time")
    private Date regTime;
    @TableField("update_time")
    private Date updateTime;
    private BigDecimal latitude;
    private BigDecimal longitude;
    @TableField("map_precision")
    private BigDecimal mapPrecision;
    @TableField("nick_name")
    private String nickName;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headImgUrl;
    private String unionId;
    @TableField("wexin_remark")
    private String wexinRemark;
    private Integer groupId;
    private String sex;
    @TableField("STATUS")
    private Integer status;
    @TableField("lent_charger_id")
    private Long lentChargerId;
    @TableField("yun_data_id")
    private Integer yunDataId;
    @TableField("star_level")
    private Integer starLevel;
    /**
     * 爱心数
     */
    @TableField("sweet_heart_sum")
    private Long sweetHeartSum;
    /**
     * 微信小程序openId
     */
    @TableField("wxapp_open_id")
    private String wxappOpenId;
    /**
     * 客户来源,10:微信,11:支付宝
     */
    @TableField("cust_source_type")
    private Integer custSourceType;



    public Date getMerchantLoginTime() {
        return merchantLoginTime;
    }

    public void setMerchantLoginTime(Date merchantLoginTime) {
        this.merchantLoginTime = merchantLoginTime;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Integer getTelValid() {
        return telValid;
    }

    public void setTelValid(Integer telValid) {
        this.telValid = telValid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public BigDecimal getMapPrecision() {
        return mapPrecision;
    }

    public void setMapPrecision(BigDecimal mapPrecision) {
        this.mapPrecision = mapPrecision;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getWexinRemark() {
        return wexinRemark;
    }

    public void setWexinRemark(String wexinRemark) {
        this.wexinRemark = wexinRemark;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLentChargerId() {
        return lentChargerId;
    }

    public void setLentChargerId(Long lentChargerId) {
        this.lentChargerId = lentChargerId;
    }

    public Integer getYunDataId() {
        return yunDataId;
    }

    public void setYunDataId(Integer yunDataId) {
        this.yunDataId = yunDataId;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public Long getSweetHeartSum() {
        return sweetHeartSum;
    }

    public void setSweetHeartSum(Long sweetHeartSum) {
        this.sweetHeartSum = sweetHeartSum;
    }

    public String getWxappOpenId() {
        return wxappOpenId;
    }

    public void setWxappOpenId(String wxappOpenId) {
        this.wxappOpenId = wxappOpenId;
    }

    public Integer getCustSourceType() {
        return custSourceType;
    }

    public void setCustSourceType(Integer custSourceType) {
        this.custSourceType = custSourceType;
    }

    @Override
    protected Serializable pkVal() {
        return this.custNo;
    }

    @Override
    public String toString() {
        return "CustInfo{" +
        "merchantLoginTime=" + merchantLoginTime +
        ", merchantId=" + merchantId +
        ", custNo=" + custNo +
        ", custName=" + custName +
        ", openId=" + openId +
        ", custType=" + custType +
        ", telNo=" + telNo +
        ", telValid=" + telValid +
        ", email=" + email +
        ", addr=" + addr +
        ", postCode=" + postCode +
        ", remark=" + remark +
        ", regTime=" + regTime +
        ", updateTime=" + updateTime +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", mapPrecision=" + mapPrecision +
        ", nickName=" + nickName +
        ", language=" + language +
        ", city=" + city +
        ", province=" + province +
        ", country=" + country +
        ", headImgUrl=" + headImgUrl +
        ", unionId=" + unionId +
        ", wexinRemark=" + wexinRemark +
        ", groupId=" + groupId +
        ", sex=" + sex +
        ", status=" + status +
        ", lentChargerId=" + lentChargerId +
        ", yunDataId=" + yunDataId +
        ", starLevel=" + starLevel +
        ", sweetHeartSum=" + sweetHeartSum +
        ", wxappOpenId=" + wxappOpenId +
        ", custSourceType=" + custSourceType+
        "}";
    }
}
