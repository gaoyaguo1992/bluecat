package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <P>
 *	custinfo
 * </P>
 * 
 * 
 */
public class CustInfoShowModel extends PageCommand {
	private static final long serialVersionUID = 1772610597037129717L;
	private Long custNo;
	private String nickName;
	private BigDecimal xCoordinate;
	private BigDecimal yCoordinate;
	private String addr;
	private String sex;
	private String telno;
	private String email;

	private BigDecimal balance;
	private BigDecimal frozenBalance;
	private BigDecimal availableBalance;
	
	private String merchantIds;
	
    private String custInfoCreateStartDate;
    private String custInfoCreateEndDate;
    
    private Date createTime;
    
    private String telValid;
    
    private Long merchantId;
    
    private Integer sweetHeartSum;
    
    private Long accountId;
    
    private Integer amtSource;
    
    private Integer accountType;
    
    private String wxAppOpenId;
    
    private Integer custType;
    
    private String custTypeCn;
    
	public String getCustTypeCn() {
		return custTypeCn;
	}
	public void setCustTypeCn(String custTypeCn) {
		this.custTypeCn = custTypeCn;
	}
	public Integer getCustType() {
		return custType;
	}
	public void setCustType(Integer custType) {
		this.custType = custType;
	}
	public String getTelValid() {
		return telValid;
	}
	public void setTelValid(String telValid) {
		this.telValid = telValid;
	}
	public String getCustInfoCreateStartDate() {
		return custInfoCreateStartDate;
	}
	public void setCustInfoCreateStartDate(String deviceCreateStartDate) {
		this.custInfoCreateStartDate = deviceCreateStartDate;
	}
	public String getCustInfoCreateEndDate() {
		return custInfoCreateEndDate;
	}
	public void setCustInfoCreateEndDate(String deviceCreateEndDate) {
		this.custInfoCreateEndDate = deviceCreateEndDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCustNo() {
		return custNo;
	}
	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
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
		return sweetHeartSum;
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
	public String getAmtSourceCn() {
		if(amtSource != null){
			return	CustAccountSourceEnum.getDesc(amtSource);
		}
		return null;
	}
	public Integer getAmtSource(){
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
	public String getAccountTypeCn() {
		if(accountType != null){
			return CustAccountTypeEnum.getDesc(accountType);
		}
		return null;
	}
	public String getWxAppOpenId() {
		return wxAppOpenId;
	}
	public void setWxAppOpenId(String wxAppOpenId) {
		this.wxAppOpenId = wxAppOpenId;
	}
	public String getMerchantIds() {
		return merchantIds;
	}
	public void setMerchantIds(String merchantIds) {
		this.merchantIds = merchantIds;
	}

}