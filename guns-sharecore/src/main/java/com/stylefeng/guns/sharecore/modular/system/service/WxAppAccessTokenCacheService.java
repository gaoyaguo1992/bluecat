package com.stylefeng.guns.sharecore.modular.system.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
/**
 * 处理小程序的token...
 * @author Alan.huang
 *
 */
@Service("WxAppAccessTokenCacheService")
public class WxAppAccessTokenCacheService {
	@Cacheable(value="wxapp-tokens",key="'appId'+#appId")
	public WxAppAccessTokenInfo getToken(String appId) {
		 return null;
	}
	
	@CachePut(value="wxapp-tokens",key="'appId'+#token.getAppId()")
	public WxAppAccessTokenInfo putToken(WxAppAccessTokenInfo token) {
		 return token;
	}

	@CacheEvict(value="wxapp-tokens",key="'appId'+#appId")
	public void removeToken(String appId) {
	}
}
