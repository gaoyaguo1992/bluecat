package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShareTradeInfoBySelfModel extends ShareTradeInfoModel{
	/**
	 * 该交易已经发送过消息的次数
	 */
	private Integer sendMessageTimes; 
	/**
	 *  退款原因
	 */
	private String operateComment;
	/**
	 * 终端地址
	 */
	private String terminalMerAddr;
	/**
	 * 交易时对应的
	 */
	private ShareTradeDeviceInfoModel  shareTradeDeviceInfoModel;
	/**
	 * 终端商户
	 */
	private String terminalMerName;
	/**
	 * 终端联系人
	 */
	private String terminalPersonName;
	/**
	 * 终端联系电话
	 */
	private String terminalTelNo;
	
	public String getTerminalPersonName() {
		return terminalPersonName;
	}
	public void setTerminalPersonName(String terminalPersonName) {
		this.terminalPersonName = terminalPersonName;
	}
	public String getTerminalTelNo() {
		return terminalTelNo;
	}
	public void setTerminalTelNo(String terminalTelNo) {
		this.terminalTelNo = terminalTelNo;
	}
	public Integer getSendMessageTimes() {
		return sendMessageTimes;
	}
	public void setSendMessageTimes(Integer sendMessageTimes) {
		this.sendMessageTimes = sendMessageTimes;
	}
	public String getOperateComment() {
		return operateComment;
	}
	public void setOperateComment(String operateComment) {
		this.operateComment = operateComment;
	}
	public String getTerminalMerAddr() {
		return terminalMerAddr;
	}
	public void setTerminalMerAddr(String terminalMerAddr) {
		this.terminalMerAddr = terminalMerAddr;
	}
	public String getTerminalMerName() {
		return terminalMerName;
	}
	public void setTerminalMerName(String terminalMerName) {
		this.terminalMerName = terminalMerName;
	}
	public ShareTradeDeviceInfoModel getShareTradeDeviceInfoModel() {
		return shareTradeDeviceInfoModel;
	}
	public void setShareTradeDeviceInfoModel(ShareTradeDeviceInfoModel shareTradeDeviceInfoModel) {
		this.shareTradeDeviceInfoModel = shareTradeDeviceInfoModel;
	} 
	
}