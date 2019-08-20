package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;

public class WxAppConstant {
	public static String MARKERS_ICONPATH = "/images/icon/mark.png"; //附近设备地图上小图标
	
	public static String MARKERS_CHUZU_ICONPATH = "/images/icon/chuzumack.png"; //附近设备地图上小图标(出租)
	
	public static String MARKERS_ZUJIE_ICONPATH = "/images/icon/zujie.png"; //附近设备地图上小图标(租借)
	
	public static double MARKERS_WIDTH = 20; //附近设备地图上小图标宽
	
	public static double MARKERS_HEIGHT = 25;//附近设备地图上小图标高
	
	/*
	 * 微信小程序对应的接口地址...
	 * https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
	 */
	public static String WXAPP_JSCODE2SESSION="https://api.weixin.qq.com/sns/jscode2session";
	
	
	/**
	 * 默认latitude
	 */
	public static BigDecimal DEFAULT_LATITUDE=new BigDecimal(22.54309900);
	/**
	 * 默认longitude
	 */
	public static BigDecimal DEFAULT_LONGITUDE=new BigDecimal(114.05786800);
	/**
	 * 微信小程序统一下单接口地址
	 */
	public static String WXAPP_PREPAY="https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 查询退款
	 */
	public static String WXAPP_REFUNDQUERU="https://api.mch.weixin.qq.com/pay/refundquery";
	
	
	
	public static String WXAPP_PAGE_DT_ZZ = "0"; //单体扫他人设备"转借"页面
	
	public static String WXAPP_PAGE_DT_PWD = "1"; //单体扫自己设备显示密码页面
	
	public static String WXAPP_PAGE_DT_ZJ = "2"; //单体扫出厂设备提示页面
	
	public static String WXAPP_PAGE_PWD_ZJ_TS = "3"; //密码设备租借设备提示所需费用页面代码
	
	public static String WXAPP_PAGE_PWD_ZJ_SELECT = "4"; //密码设备租借设备选择结束或继续使用页面代码
	
	public static String WXAPP_PAGE_PWD_ZJ_PASSWORD = "5"; //密码设备租借设备密码页面代码
	
	public static String WXAPP_PAGE_PWD_ZJ_FEE = "6"; //密码设备租借设备费用页面代码
	
	public static String WXAPP_PAGE_PWD_ZJ_NOPWD = "7"; //密码设备预付金，双二维码租借提示扫底座归还页面代码
	
	public static String WXAPP_PAGE_DT_331FEED = "8"; //单体结束使用费用详细页面
	
	public static String WXAPP_PAGE_DT_1TO6_ZJ = "9"; //一拖六"租借"页面
	
	public static String WXAPP_PAGE_DT_1TO6_PWD = "10"; //一拖六扫自己设备显示密码页面
	
	public static String WXAPP_PAGE_DT_1TO6_ZJSUCCESS = "11"; //一拖六扫 租借成功	
	
	public static String LENT_FROM_DEVICE = "1"; //一拖六扫底座 租借
	
	public static String LENT_FROM_CHARGER = "2"; //一拖六扫充电器 租借
	
	public static String WXAPP_PAGE_MEMBER_ZJ = "12"; //会员租借设备我要充页面代码

	public static String WXAPP_PAGE_DT_AGGREGATEPAY = "13"; //聚合支付跳转
	
	
}
