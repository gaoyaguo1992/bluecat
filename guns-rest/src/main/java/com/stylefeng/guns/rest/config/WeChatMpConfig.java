package com.stylefeng.guns.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stylefeng.guns.rest.config.properties.WechatAccountConfig;
import com.stylefeng.guns.sharecore.common.WxMpInMemoryConfigStorageExtend;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

/**
 * 微信配置文件..
 *
 */
@Configuration
public class WeChatMpConfig {
	/**
	 * 注册配置文件
	 */
	@Autowired
	private WechatAccountConfig wechatAccountConfig;

	/**
	 * 创建wxMp Service
	 * 
	 * @return
	 */
	@Bean
	public WxMpService wxMpService() {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}

	/**
	 * 创建役信配置文件存储内..
	 * 
	 * @return
	 */
	@Bean
	public WxMpConfigStorage wxMpConfigStorage() {
		WxMpInMemoryConfigStorageExtend wxMpConfigStorage = new WxMpInMemoryConfigStorageExtend();
		wxMpConfigStorage.setAccessToken(wechatAccountConfig.getToken());
		wxMpConfigStorage.setAesKey(wechatAccountConfig.getAesKey());
		wxMpConfigStorage.setAppId(wechatAccountConfig.getAppId());
		wxMpConfigStorage.setOauth2redirectUri(wechatAccountConfig.getOauth2redirectUri());
		wxMpConfigStorage.setPartnerId(wechatAccountConfig.getPartnerId());
		wxMpConfigStorage.setPartnerKey(wechatAccountConfig.getPartnerKey());
		wxMpConfigStorage.setSecret(wechatAccountConfig.getSecret());
		return wxMpConfigStorage;
	}
}
