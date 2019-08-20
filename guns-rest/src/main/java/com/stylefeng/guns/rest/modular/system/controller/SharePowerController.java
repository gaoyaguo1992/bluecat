package com.stylefeng.guns.rest.modular.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 *用户扫码充电，对应的二维码的链接...
 *
 * @author Alan.huang
 * @date 2019-01-01 16:02 wxapp
 */
@Controller
@RequestMapping("/share")
public class SharePowerController {
	/**
	 * 日志对象...
	 */
	private static final Logger logger = LoggerFactory.getLogger(SharePowerController.class);
	
	/**
	 * 充电器二维码对应的链接的逻辑..
	 * 
	 * @param simpleObject
	 * @return
	 */
	@RequestMapping("rh")
	@ResponseBody
	public Map<String, Object> rh(HttpServletRequest request, HttpServletResponse response){
		logger.info("充电器二维码对应的链接的逻辑..");
		try {
			Map<String, Object> map = new HashMap<>();
			String id=request.getParameter("id");
			// new ModelAndView()
			map.put("id", id);
			map.put("message", "请微信或支付宝扫码二维码！");
			return map;
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
		}
		return null;
	}
	/**
	 * 底座对应的二维码的链接处理逻辑...
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("bk")
	@ResponseBody
	public Map<String, Object> bk(HttpServletRequest request, HttpServletResponse response){
		logger.info("底座对应的二维码的链接处理逻辑.");
		try {
			/**
			 * 返回所有的用户未读信息
			 */
			Map<String, Object> map = new HashMap<>();
			// new ModelAndView()
			return map;
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
		}
		return null;
	}
}
