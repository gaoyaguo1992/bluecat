package com.stylefeng.guns.rest.modular.model;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;

public  class ResultCommandMap extends ResultCommand {
	/**
	 * 返回数据..
	 */
	private Map<String,Object> responseInfo;
	/**
	 * 返回列表 resultCommand..
	 */
	private List<Map<String,Object>> responseInfos;
	public Map<String,Object> getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(Map<String,Object> responseInfo) {
		this.responseInfo = responseInfo;
	}
	public List<Map<String,Object>> getResponseInfos() {
		return responseInfos;
	}
	public void setResponseInfos(List<Map<String,Object>> responseInfos) {
		this.responseInfos = responseInfos;
	}
}