package com.stylefeng.guns.rest.modular.model;

import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;

public class ResultCommandStringArray extends ResultCommand {
	/**
	 * 返回数据信息
	 */
	private String[] responseInfos;

	public String[] getResponseInfos() {
		return responseInfos;
	}

	public void setResponseInfos(String[] responseInfos) {
		this.responseInfos = responseInfos;
	}
}
