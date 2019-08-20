package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;

public class CustInfoModel extends PageCommand {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4791610842323969362L;

	private Long custNo;

	private String custName;

	private  String zfbUserId;

	private String openId;

	private Integer custType;

	private String custTypeCn;

	private String telNo;

	private String email;

	private String addr;

	private String postCode;

	private String remark;

	private List<CustAccountModel> custAccounts;

	private List<CustMerchantRefInfoModel> custMerchants;

	private Date regTime;

	private Date updateTime;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private BigDecimal mapPrecision;

	private String telValid;
	/**
	 * 归还模式，主要用于单体，当为1:如果是331租借人扫二维码也可能归还.. 0:只能自己扫..
	 */
	private Integer lentFlag;

	protected String nickName;
	protected String sex;
	protected String language;
	protected String city;
	protected String province;
	protected String country;
	protected String headImgUrl;

	protected String unionId;

	protected String wexinRemark;
	protected Integer groupId;

	private String loginId;

	private Long lentChargerId;

	private int yunDataId;

	private int starLevel;

	private BigDecimal balance;
	private BigDecimal frozenBalance;
	private BigDecimal availableBalance;
	private Integer sweetHeartSum;// 爱心数
	private int rank;// 排名
	private Long accountId;

	private Integer amtSource;

	private Integer accountType;
	/**
	 * 微信小程序OpenId...
	 */
	private String wxAppOpenId;

	// 用户二维码图片URL放在oss上
	private String userEWMUrl;

	// 客户来源
	private Integer custSourceType;

	// 令牌
	private String token;

	private String userStatus;
	// 是否通过实名认证
	private String isCertified;

	// 是否是学生
	private String isStudentCertified;

	private Integer memberLevel;

	private String memberLevelCn;
	/**
	 * 对应的商户号
	 */
	private Long merchantId;
	
	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 
	 * <p>
	 * 获取客户sha账户
	 * </p>
	 * 
	 * @return
	 */
	public CustAccountModel getCustShaAccountModel() {
		return null;
	}

	/**
	 * 
	 * <p>
	 * 获取客户充值账户
	 * </p>
	 * 
	 * @return
	 */
	public CustAccountModel getCustAccountModel() {
		if (null != custAccounts) {
			for (CustAccountModel cam : custAccounts) {
				if (CustAccountTypeEnum.RECHARGERACCOUNT.getCode() == cam.getAccountType()) {
					return cam;
				}
			}
		}
		return null;
	}

	public String getZfbUserId() {
		return zfbUserId;
	}

	public void setZfbUserId(String zfbUserId) {
		this.zfbUserId = zfbUserId;
	}

	/**
	 * 
	 * <p>
	 * 获取客户积分账户
	 * </p>
	 * 
	 * @return
	 */
	public CustAccountModel getCustJinFenAccountModel() {
		if (null != custAccounts) {
			for (CustAccountModel cam : custAccounts) {
				if (CustAccountTypeEnum.INTEGRALACCOUNT.getCode() == cam.getAccountType()) {
					return cam;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * 获取客户赏金账户
	 * </p>
	 * 
	 * @return
	 */
	public CustAccountModel getCustTsfAmtAccountModel() {
		if (null != custAccounts) {
			for (CustAccountModel cam : custAccounts) {
				if (CustAccountTypeEnum.RECHARGERACCOUNT.getCode() == cam.getAccountType()) {
					return cam;
				}
			}
		}
		return null;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMemberLevelCn() {
		return memberLevelCn;
	}

	public void setMemberLevelCn(String memberLevelCn) {
		this.memberLevelCn = memberLevelCn;
	}

	public String getCustTypeCn() {
		return custTypeCn;
	}

	public void setCustTypeCn(String custTypeCn) {
		this.custTypeCn = custTypeCn;
	}

	public String getUserEWMUrl() {
		return userEWMUrl;
	}

	public void setUserEWMUrl(String userEWMUrl) {
		this.userEWMUrl = userEWMUrl;
	}

	public String getWxAppOpenId() {
		return wxAppOpenId;
	}

	public void setWxAppOpenId(String wxAppOpenId) {
		this.wxAppOpenId = wxAppOpenId;
	}

	public Integer getLentFlag() {
		return lentFlag;
	}

	public void setLentFlag(Integer lentFlag) {
		this.lentFlag = lentFlag;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getYunDataId() {
		return yunDataId;
	}

	public void setYunDataId(int yunDataId) {
		this.yunDataId = yunDataId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickname) {
		this.nickName = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public void setWexinRemark(String weixinRemark) {
		this.wexinRemark = weixinRemark;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

	public void setMapPrecision(BigDecimal precision) {
		this.mapPrecision = precision;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName == null ? null : custName.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
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
		this.telNo = telNo == null ? null : telNo.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode == null ? null : postCode.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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

	public List<CustAccountModel> getCustAccounts() {
		return custAccounts;
	}

	public void setCustAccounts(List<CustAccountModel> custAccounts) {
		this.custAccounts = custAccounts;
	}

	public String getTelValid() {
		return telValid;
	}

	public void setTelValid(String telValid) {
		this.telValid = telValid;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Long getLentChargerId() {
		return lentChargerId;
	}

	public void setLentChargerId(Long lentChargerId) {
		this.lentChargerId = lentChargerId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(BigDecimal frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Integer getSweetHeartSum() {
		return sweetHeartSum == null ? 0 : sweetHeartSum;
	}

	public void setSweetHeartSum(Integer sweetHeartSum) {
		this.sweetHeartSum = sweetHeartSum;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getAmtSource() {
		return amtSource;
	}

	public void setAmtSource(Integer amtSource) {
		this.amtSource = amtSource;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getCustSourceType() {
		return custSourceType;
	}

	public void setCustSourceType(Integer custSourceType) {
		this.custSourceType = custSourceType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getIsCertified() {
		return isCertified;
	}

	public void setIsCertified(String isCertified) {
		this.isCertified = isCertified;
	}

	public String getIsStudentCertified() {
		return isStudentCertified;
	}

	public void setIsStudentCertified(String isStudentCertified) {
		this.isStudentCertified = isStudentCertified;
	}

	public List<CustMerchantRefInfoModel> getCustMerchants() {
		return custMerchants;
	}

	public void setCustMerchants(List<CustMerchantRefInfoModel> custMerchants) {
		this.custMerchants = custMerchants;
	}

}