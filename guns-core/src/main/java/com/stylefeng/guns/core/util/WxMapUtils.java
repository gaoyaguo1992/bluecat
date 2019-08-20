package com.stylefeng.guns.core.util;

import java.util.HashMap;
import java.util.Map;

public class WxMapUtils {
	// 地球平均半径米
	private static final double EARTH_RADIUS_M = 6371004;
	
	// 地球平均半径千米
	private static final double EARTH_RADIUS_KM = 6371;
	
	public static String MIN_LONGITUDESTR = "minlng"; //最小经度范围
	public static String MAX_LONGITUDESTR = "maxlng";//最大经度范围
	
	public static String MIN_LATITUDESTR = "minlat";//最小纬度范围
	public static String MAX_LATITUDESTR = "maxlat";//最大纬度范围
	
	public static Double DEVICE_DEFAULT_DIS = 1.0; //默认查询0.7千米范围设备
	public static Double MERCHANT_DEFAULT_DIS = 2.0;//默认查询2千米范围商户
	
	public static Double ORDER_DEFAULT_DIS = 10.0; //默认查询10千米范围设备
	
	/**
	 * 最大显示最近网点数...
	 */
	public static Long MAX_RETURN_NEARDEVICE_COUNT=2000L;
	
	public static String DEFAULT_LONGITUDESTR = "114.057868"; //客户端默认的经度
	
	public static String DEFAULT_LATITUDESTR = "22.543099"; //客户端默认的纬度
	
	
	
	
	/**
	 * 传入距离范围，经纬度，求附近四个点区域的经纬度坐标
	 * 
	 * @param dis
	 *            区域的距离单位千米
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @return map包含四个区域经纬度的key，minlng最小经度，maxlng最大经度，maxlat最大纬度，minlat最小纬度
	 */
	public static Map<String, Double> getMapAroundLocation(double dis, double longitude, double latitude) {
		Map<String, Double> aroundLocation = new HashMap<>();
		// 先计算查询点的经纬度范围
		double dlng = 2 * Math.asin(Math.sin(dis / (2 * EARTH_RADIUS_KM)) / Math.cos(latitude * Math.PI / 180));
		dlng = dlng * 180 / Math.PI;// 角度转为弧度
		double dlat = dis / EARTH_RADIUS_KM;
		dlat = dlat * 180 / Math.PI;
		double minlat = latitude - dlat;
		double maxlat = latitude + dlat;
		double minlng = longitude - dlng;
		double maxlng = longitude + dlng;
		aroundLocation.put(MIN_LONGITUDESTR, minlng);
		aroundLocation.put(MAX_LONGITUDESTR, maxlng);
		aroundLocation.put(MAX_LATITUDESTR, maxlat);
		aroundLocation.put(MIN_LATITUDESTR, minlat);
		return aroundLocation;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS_M;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	// 把经纬度转为度（°）
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
}
