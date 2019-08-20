package com.stylefeng.guns.rest.modular.model;

import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.MapMarkersBO;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;

public class ResultCommandMapMarkerModel extends ResultCommand {
	/**
	 * 返回数据..
	 */
	private MapMarkersBO responseInfo;
	/**
	 * 返回列表 resultCommand..
	 */
	private List<MapMarkersBO> responseInfos;

	public MapMarkersBO getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(MapMarkersBO responseInfo) {
		this.responseInfo = responseInfo;
	}

	public List<MapMarkersBO> getResponseInfos() {
		return responseInfos;
	}

	public void setResponseInfos(List<MapMarkersBO> responseInfos) {
		this.responseInfos = responseInfos;
	}
}
