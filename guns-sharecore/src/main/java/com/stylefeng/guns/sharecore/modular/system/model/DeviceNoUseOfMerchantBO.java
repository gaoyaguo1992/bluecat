package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class DeviceNoUseOfMerchantBO {
	
	private Long merchantId;
	
	private String name; //店铺名
	
	private String personName; //商户名
	
	private String addr; //地址
	
	private String telNo;
	
	private String industryCategoryCn;
	
	private Long totalUsedCount; //总使用次数
	
	private Date lastUsedTime; //最后使用时间
	
	
	

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Long getTotalUsedCount() {
		return totalUsedCount;
	}

	public void setTotalUsedCount(Long totalUsedCount) {
		this.totalUsedCount = totalUsedCount;
	}

	public Date getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(Date lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

	public String getIndustryCategoryCn() {
		return industryCategoryCn;
	}

	public void setIndustryCategoryCn(String industryCategoryCn) {
		this.industryCategoryCn = industryCategoryCn;
	}
	
	
	

}
