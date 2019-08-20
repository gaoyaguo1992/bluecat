package com.stylefeng.guns.sharecore.common.persistence.model;

public class MyDeviceAndChargerBO {
	
	private Integer devType;
	
	private String devDesc;
	
	private Integer devNum;
	
	private Integer chargerNum;

	public Integer getDevType() {
		return devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}

	public Integer getDevNum() {
		return devNum;
	}

	public void setDevNum(Integer devNum) {
		this.devNum = devNum;
	}

	public Integer getChargerNum() {
		return chargerNum;
	}

	public void setChargerNum(Integer chargerNum) {
		this.chargerNum = chargerNum;
	}

	public String getDevDesc() {
		return devDesc;
	}

	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}

}
