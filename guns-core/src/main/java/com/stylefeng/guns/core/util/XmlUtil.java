package com.stylefeng.guns.core.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.mp.util.xml.WXPayUtil;


public class XmlUtil {
	public static void main(String[] args) {
		String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>"
				+ "<appid><![CDATA[wxd52402ee62771eaa]]></appid>" + "<mch_id><![CDATA[1487648472]]></mch_id>"
				+ "<nonce_str><![CDATA[YO5jgjPRsUJIr8Sa]]></nonce_str>"
				+ "<sign><![CDATA[F5B3811EA20ACAAE11F8B5FEB5FCC184]]></sign>"
				+ "<result_code><![CDATA[SUCCESS]]></result_code>"
				+ "<prepay_id><![CDATA[wx0420112194648710da2c342e4202411460]]></prepay_id>"
				+ "<trade_type><![CDATA[JSAPI]]></trade_type>" + "</xml>";
		
		logger.info(WXPayUtil.xmlToMap(xml)+"");
		logger.info(WXPayUtil.mapToXml(WXPayUtil.xmlToMap(xml)));
	}

	private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	public static Map<String, String> xmlResult(String strXML) {
		return WXPayUtil.xmlToMap(strXML);		 
	}

	/**
	 * 将Map数据转化为统一定单要求的xml数据
	 * 
	 * @param map
	 * @return
	 */
	public static String MapToXML(Map<String, String> map) {
		return WXPayUtil.mapToXml(map);	 
	}

}
