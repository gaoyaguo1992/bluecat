package com.stylefeng.guns.sharecore.modular.system.model;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 批理生成设备信息。。
 * @author Alan.huang
 *
 */
public class ShareDeviceInfoForBatchModel extends ShareDeviceInfoBO {
	/**
	 * 要生成的设备数量
	 */
	private Long deviceQty;
	/**
	 * 是否同时批量生成充电器
	 */
	private Boolean batchSetCharger;
	/**
	 * 是否同时批量设备费用
	 */
	private Boolean batchSetFee;
	/**
	 * 是否批量设置商户
	 */
	private Boolean batchSetMerch;
	/**
	 * 批量生成每个设备对应的充电器数量
	 */
	private Long batchChargerQty;
	/**
	 * 开始的设备编号
	 */
	private Long startDeviceNo;
	/**
	 * 结束的设备编号
	 */
	private Long endDeviceNo;
	/**
	 * 设备号（多个用,分隔)
	 */
	private String deviceNoStr;
	/**
	 * 选择处理的设备类型
	 */
	private Long selectDeviceNoType;
	/**
	 * 选中代理商 1
	 */
	private int chkAgents1Id;
	/**
	 * 先中代理商2
	 */
	private int chkAgents2Id;
	/**
	 * 先中代理商3
	 */
	private int chkAgents3Id;
	/**
	 * 铺货商
	 */
	private int chkShopkeeperId;
	/**
	 * 加盟商 
	 */
	private int chkAllianceBusinessId;
	/**
	 * 店铺id
	 */
	private int chkMerchantId;
	
	
	public int getChkMerchantId() {
		return chkMerchantId;
	}
	public void setChkMerchantId(int chkMerchantId) {
		this.chkMerchantId = chkMerchantId;
	}
	public int getChkAgents1Id() {
		return chkAgents1Id;
	}
	public void setChkAgents1Id(int chkAgents1Id) {
		this.chkAgents1Id = chkAgents1Id;
	}
	public int getChkAgents2Id() {
		return chkAgents2Id;
	}
	public void setChkAgents2Id(int chkAgents2Id) {
		this.chkAgents2Id = chkAgents2Id;
	}
	public int getChkAgents3Id() {
		return chkAgents3Id;
	}
	public void setChkAgents3Id(int chkAgents3Id) {
		this.chkAgents3Id = chkAgents3Id;
	}
	public int getChkShopkeeperId() {
		return chkShopkeeperId;
	}
	public void setChkShopkeeperId(int chkShopkeeperId) {
		this.chkShopkeeperId = chkShopkeeperId;
	}
	public int getChkAllianceBusinessId() {
		return chkAllianceBusinessId;
	}
	public void setChkAllianceBusinessId(int chkAllianceBusinessId) {
		this.chkAllianceBusinessId = chkAllianceBusinessId;
	}
	/**
	 * 批量生成充电器备注 。
	 */
	private String batchChargerRemark;
	public Long getDeviceQty() {
		return deviceQty;
	}
	public void setDeviceQty(Long deviceQty) {
		this.deviceQty = deviceQty;
	}
	public Boolean getBatchSetCharger() {
		return batchSetCharger;
	}
	public void setBatchSetCharger(Boolean batchSetCharger) {
		this.batchSetCharger = batchSetCharger;
	}
	public Boolean getBatchSetFee() {
		return batchSetFee;
	}
	public void setBatchSetFee(Boolean batchSetFee) {
		this.batchSetFee = batchSetFee;
	}
	public Boolean getBatchSetMerch() {
		return batchSetMerch;
	}
	public void setBatchSetMerch(Boolean batchSetMerch) {
		this.batchSetMerch = batchSetMerch;
	}
	public Long getBatchChargerQty() {
		return batchChargerQty;
	}
	public void setBatchChargerQty(Long batchChargerQty) {
		this.batchChargerQty = batchChargerQty;
	}
	public String getBatchChargerRemark() {
		return batchChargerRemark;
	}
	public void setBatchChargerRemark(String batchChargerRemark) {
		this.batchChargerRemark = batchChargerRemark;
	}
	public Long getStartDeviceNo() {
		return startDeviceNo;
	}
	public void setStartDeviceNo(Long startDeviceNo) {
		this.startDeviceNo = startDeviceNo;
	}
	public Long getEndDeviceNo() {
		return endDeviceNo;
	}
	public void setEndDeviceNo(Long endDeviceNo) {
		this.endDeviceNo = endDeviceNo;
	}
	public String getDeviceNoStr() {
		return deviceNoStr;
	}
	public void setDeviceNoStr(String deviceNoStr) {
		this.deviceNoStr = deviceNoStr;
	}
	public Long getSelectDeviceNoType() {
		return selectDeviceNoType;
	}
	public void setSelectDeviceNoType(Long selectDeviceNoType) {
		this.selectDeviceNoType = selectDeviceNoType;
	}
}