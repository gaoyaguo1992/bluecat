package com.stylefeng.guns.rest.modular.model;

import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;
import com.stylefeng.guns.sharecore.common.persistence.model.SysSessionInfo;

public class ResultCommandSysSessionInfo extends ResultCommand {
	/**
	 * 返回数据..
	 */
	private SysSessionInfo responseInfo;
	/**
	 * 返回列表 resultCommand..
	 */
	private List<SysSessionInfo> responseInfos;
	public SysSessionInfo getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(SysSessionInfo responseInfo) {
		this.responseInfo = responseInfo;
	}
	public List<SysSessionInfo> getResponseInfos() {
		return responseInfos;
	}
	public void setResponseInfos(List<SysSessionInfo> responseInfos) {
		this.responseInfos = responseInfos;
	}
}
