package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.List;

/**
 * 
 * <P>我的订单记录分页业务对象</P>
 * 
 */
public class MyOrderRecordPageInfoBO {
	/**
	 * 返回的结果状态..
	 */
	private String result;
	/**
	 * 异常信息..
	 */
	private String message;
	/**
	 * 查询的订单内容..
	 */
	private List<MyOrderRecordInfoBO> myOrderRecordInfoBOs;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public List<MyOrderRecordInfoBO> getMyOrderRecordInfoBOs() {
		return myOrderRecordInfoBOs;
	}

	public void setMyOrderRecordInfoBOs(List<MyOrderRecordInfoBO> myOrderRecordInfoBOs) {
		this.myOrderRecordInfoBOs = myOrderRecordInfoBOs;
	}
	
}
