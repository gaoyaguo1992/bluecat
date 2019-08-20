package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;

/**
 * 小程序地图上，附近设备BO,为markers而生
 * @author Alan.huang
 *
 */
public class MapMarkersBO extends com.stylefeng.guns.sharecore.common.persistence.model.BaseObject{

	private static final long serialVersionUID = -643891874366854311L;
	/**
	 * 设备id
	 */
	private Long id;
	/**
	 * 小图标位置
	 */
	private String iconPath; 
	/**
	 * 纬度
	 */
	private BigDecimal latitude; //
	
	private BigDecimal longitude; //经度
	/**
	 * 查询条件中传来的纬度
	 */
	private BigDecimal latitudeForSearch; //查询条件中传来的纬度
	/**
	 * 查询条件中传来的经度
	 */
	private BigDecimal longitudeForSearch;
	/**
	 * 高度	
	 */
	private double width;
	/**
	 * 高度
	 */
	private double height;
	/**
	 * 商店名字
	 */
	private String shopName; 
	/**
	 *商店地址 
	 */
	private String shopAddr; 
	/**
	 * 商店电话
	 */
	private String phone; 
	/**
	 * 订单类型
	 */
	private String orderType; 
	
	/**
	 * 得到查询条件中传来的纬度
	 */
	public BigDecimal getLatitudeForSearch() {
		return latitudeForSearch;
	}
	/**
	 * 设置查询条件中传来的纬度
	 * @return
	 */
	public void setLatitudeForSearch(BigDecimal latitudeForSearch) {
		this.latitudeForSearch = latitudeForSearch;
	}
	/**
	 * 得到查询条件中传来的经度
	 */
	public BigDecimal getLongitudeForSearch() {
		return longitudeForSearch;
	}
	/**
	 * 设置查询条件中传来的经度
	 */
	public void setLongitudeForSearch(BigDecimal longitudeForSearch) {
		this.longitudeForSearch = longitudeForSearch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
