package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class ShareChargerForBatchModel extends ShareChargerModel {
   /*
    * 操作类型
    */
	private  int batchTypeForOperate;
	/**
	 * 开始的充电器编号
	 */
	private Long startChargerNo;
	/**
	 * 结束的充电器编号
	 */
	private Long endChargerNo;
	/**
	 * 充电器编号（多个用,分隔)
	 */
	private String chargerNoStr;
	/**
	 * 生成充电器数量
	 */
	private Long chargerQty;
	/**
	 * 生成类型.0:不生成设备编号,1:一个充电器生成一个设备编号,2:只生成一个设备编号
	 */
	private int generateType;
	
	public int getBatchTypeForOperate() {
		return batchTypeForOperate;
	}
	public void setBatchTypeForOperate(int batchTypeForOperate) {
		this.batchTypeForOperate = batchTypeForOperate;
	}
	public Long getStartChargerNo() {
		return startChargerNo;
	}
	public void setStartChargerNo(Long startChargerNo) {
		this.startChargerNo = startChargerNo;
	}
	public Long getEndChargerNo() {
		return endChargerNo;
	}
	public void setEndChargerNo(Long endChargerNo) {
		this.endChargerNo = endChargerNo;
	}
	public String getChargerNoStr() {
		return chargerNoStr;
	}
	public void setChargerNoStr(String chargerNoStr) {
		this.chargerNoStr = chargerNoStr;
	}
	public int getGenerateType() {
		return generateType;
	}
	public void setGenerateType(int generateType) {
		this.generateType = generateType;
	}
	public Long getChargerQty() {
		return chargerQty;
	}
	public void setChargerQty(Long chargerQty) {
		this.chargerQty = chargerQty;
	}
}