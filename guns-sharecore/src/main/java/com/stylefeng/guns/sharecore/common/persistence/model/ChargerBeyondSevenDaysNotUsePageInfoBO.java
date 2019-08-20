package com.stylefeng.guns.sharecore.common.persistence.model;

import java.util.List;

/**
 * 充电器分页信息。。。
 */
public class ChargerBeyondSevenDaysNotUsePageInfoBO {
	/**
	 * 成功：sucess, 失败:fail
	 */
	private String result;
	/**
	 * 失败原因...
	 */
	private String message;
	/**
	 * 查询的充电器信息
	 */
	private List<ChargerBeyondSevenDaysNotUseInfoBO> chargerBeyondThreeDaysNotUseInfoBOs;
	/**
	 * 充电器数量...
	 */
	private Integer chargerNum;
	
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
	public List<ChargerBeyondSevenDaysNotUseInfoBO> getChargerBeyondThreeDaysNotUseInfoBOs() {
		return chargerBeyondThreeDaysNotUseInfoBOs;
	}
	public void setChargerBeyondThreeDaysNotUseInfoBOs(
			List<ChargerBeyondSevenDaysNotUseInfoBO> chargerBeyondThreeDaysNotUseInfoBOs) {
		this.chargerBeyondThreeDaysNotUseInfoBOs = chargerBeyondThreeDaysNotUseInfoBOs;
	}
	public Integer getChargerNum() {
		return chargerNum;
	}
	public void setChargerNum(Integer chargerNum) {
		this.chargerNum = chargerNum;
	}
}
