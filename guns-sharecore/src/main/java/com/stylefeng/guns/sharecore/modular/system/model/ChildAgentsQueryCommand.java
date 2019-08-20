package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.List;



/**
 * 
 * <P>子商户查询command</P>
 * 
 * @author
 */
public class ChildAgentsQueryCommand extends PageCommand {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1950773146755342110L;

	// 父商户Id
	private Long parentMerchantId;
	
	// 商户Id
	private Long merchantId;

	// 商户姓名
	private String personName;

	// 商户类型 (11, "代理商"),	(16, "终端商户"),(14,"铺货商")
	private Integer merchantType;
	
	// 联系电话
	private String telNo;
	
	//是否修改省市区 1：修改  0：不修改
	private Integer modifyFlag;
	
	//所有查询符合条件的merchantId 最后查询商户表信息
	private List<Long> merchantIds;
	
	
	public List<Long> getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(List<Long> merchantIds) {
		this.merchantIds = merchantIds;
	}

	public Integer getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(Integer modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Long getParentMerchantId() {
		return parentMerchantId;
	}

	public void setParentMerchantId(Long parentMerchantId) {
		this.parentMerchantId = parentMerchantId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	
}
