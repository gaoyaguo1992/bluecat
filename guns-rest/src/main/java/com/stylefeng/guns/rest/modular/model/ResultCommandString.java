package com.stylefeng.guns.rest.modular.model;

import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;

public class ResultCommandString extends ResultCommand {
	/**
	 * 返回数据..
	 */
	private Object responseInfo;

	public Object getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(Object responseInfo) {
		this.responseInfo = responseInfo;
	}
}
