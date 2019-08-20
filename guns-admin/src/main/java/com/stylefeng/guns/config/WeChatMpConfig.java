package com.stylefeng.guns.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stylefeng.guns.config.properties.WechatAccountConfig;
import com.stylefeng.guns.modular.wxApplication.controller.WxAppController;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

/**
 * 微信配置文件..
 *
 */
@Configuration
public class WeChatMpConfig {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(WxAppController.class);
	/**
	 * 注册配置文件
	 */
	@Autowired
	private WechatAccountConfig wechatAccountConfig;

	/**
	 * 创建役信配置文件存储内..
	 * 
	 * @return
	 */
	@Bean
	public WxMpConfigStorage wxMpConfigStorage() {
		String msg=String.format(
				"微信配置文件并建Bean-wxMpConfigStorage--WeChatMpConfig.wxMpConfigStorage, getToken:%s,getAesKey:%s,getAppId:%s,getOauth2redirectUri:%s,getPartnerId:%s,getPartnerKey:%s,getSecret:%s",
				wechatAccountConfig.getToken(),wechatAccountConfig.getAesKey(),wechatAccountConfig.getAppId(),wechatAccountConfig.getOauth2redirectUri(),wechatAccountConfig.getPartnerId(),wechatAccountConfig.getPartnerKey(),wechatAccountConfig.getSecret());
		logger.info(msg);
		
		WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpConfigStorage.setToken(wechatAccountConfig.getToken());
		wxMpConfigStorage.setAesKey(wechatAccountConfig.getAesKey());
		wxMpConfigStorage.setAppId(wechatAccountConfig.getAppId());
		wxMpConfigStorage.setOauth2redirectUri(wechatAccountConfig.getOauth2redirectUri());
		wxMpConfigStorage.setPartnerId(wechatAccountConfig.getPartnerId());
		wxMpConfigStorage.setPartnerKey(wechatAccountConfig.getPartnerKey());
		wxMpConfigStorage.setSecret(wechatAccountConfig.getSecret());
		return wxMpConfigStorage;
	}
	/**
	 * 创建wxMp Service
	 * 
	 * @return
	 */
	@Bean
	public WxMpService wxMpService() {
		logger.info("微信配置文件并建Bean-wxMpService");
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}
	/**
	 * 创建 WxMpMessageRouter的bean
	 * @return
	 */
	@Bean
	public WxMpMessageRouter wxMpMessageRouter(){
		logger.info("微信配置文件并建Bean-wxMpMessageRouter");
		WxMpMessageRouter wxMpMessageRouter=new WxMpMessageRouter(wxMpService());
		return wxMpMessageRouter;
	}
	
}
