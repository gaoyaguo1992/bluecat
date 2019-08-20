package com.stylefeng.guns.core.model;

import java.util.List;

public class AllProvinceCityZoneModel {

	
	private List<RegionInfoModel> provinces; //省   一级行政区
	
	private List<RegionInfoModel> citys;//市  二级行政区
	
	private List<RegionInfoModel> zones;//区  三级行政区

	public List<RegionInfoModel> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<RegionInfoModel> provinces) {
		this.provinces = provinces;
	}

	public List<RegionInfoModel> getCitys() {
		return citys;
	}

	public void setCitys(List<RegionInfoModel> citys) {
		this.citys = citys;
	}

	public List<RegionInfoModel> getZones() {
		return zones;
	}

	public void setZones(List<RegionInfoModel> zones) {
		this.zones = zones;
	}

	
	
	
}
