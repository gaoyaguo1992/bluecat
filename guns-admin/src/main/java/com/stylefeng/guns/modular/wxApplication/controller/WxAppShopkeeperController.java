package com.stylefeng.guns.modular.wxApplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stylefeng.guns.modular.BaseController;

/**
 * 处理微信公众号相关业务控制器--铺货商
 *
 * @author fengshuonan
 * @Date 2019-01-20 02:37:34
 */
@Controller
@RequestMapping("/wxShopkeeper")
public class WxAppShopkeeperController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(WxAppShopkeeperController.class);
	/**
	 * 对应前端主业。。
	 */
	public static String PREFIXBASE = "wxShopkeeper/";
	
}
