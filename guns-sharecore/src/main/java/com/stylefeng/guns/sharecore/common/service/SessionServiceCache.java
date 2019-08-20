package com.stylefeng.guns.sharecore.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.common.persistence.model.SysSessionInfo;
/**
 * 配置缓存
 * @author Alan.huang
 *
 */
@Service
public class SessionServiceCache {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	 
	/**
	 * 保存session
	 * 
	 * @param application
	 * @param sessionInfo
     */
	@CachePut(cacheNames= "myCache",key="'sessionInfo'+#sessionInfo.getId()")
	public SysSessionInfo putSession(SysSessionInfo sessionInfo) {
		return sessionInfo;
	}

	/**
	 * 得到sessionsession 同时把sesion更新到 application...
	 * 
	 * @param application
	 * @param sessionInfo
	 * @return
     */
	@Cacheable(cacheNames= "myCache",key="'sessionInfo'+#id")
	public SysSessionInfo getSession(String id) {
		logger.info("session id not exits{}",id);
		return null;
	} 
}
