package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;


/**
 * 批量费用...
 * 
 */
public class BatchEditFeeRuleCommand extends BaseObject{
	/**
	 * 批理费用编辑
	 */
	private List<BatchEditFeeCommand> feeRuleCommand;
	/**
	 * 费用json str
	 */
	private String feeRuleCommandJSONStr;
	/**
	 * 商户id..
	 */
	private Long merId;
	/**
	 * 对应的客户id
	 */
	private Long custNo;
	
	public String getFeeRuleCommandJSONStr() {
		return feeRuleCommandJSONStr;
	}
	public void setFeeRuleCommandJSONStr(String feeRuleCommandJSONStr) {
		this.feeRuleCommandJSONStr = feeRuleCommandJSONStr;
	}
	public List<BatchEditFeeCommand> getFeeRuleCommand() {
		return feeRuleCommand;
	}
	public void setFeeRuleCommand(List<BatchEditFeeCommand> feeRuleCommand) {
		this.feeRuleCommand = feeRuleCommand;
	}
	public Long getMerId() {
		return merId;
	}
	public void setMerId(Long merId) {
		this.merId = merId;
	}
	public Long getCustNo() {
		return custNo;
	}
	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}
}
