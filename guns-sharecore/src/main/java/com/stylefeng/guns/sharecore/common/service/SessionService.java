package com.stylefeng.guns.sharecore.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.sharecore.common.persistence.dao.SysSessionInfoMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.SysSessionInfo;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;

@Service
public class SessionService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysSessionInfoMapper sysSessionInfoMapper;

	@Autowired
	private SessionServiceCache cache;
 
	/**
	 * 保存session
	 * 
	 * @param application
	 * @param sessionInfo
	 * @throws Exception
	 */
	public void insertSessionToApplication(SysSessionInfo sessionInfo) throws Exception {
		try {
			if(sessionInfo==null){
				logger.warn(" sessionInfo is null");
				return;
			}
			cache.putSession(sessionInfo);
			logger.info("保存sessionInfo:{}", sessionInfo);
		} catch (Exception e) {
			logger.error("保存sessionInfo出错", e);
		}
		insertSession(sessionInfo);
	}

	/**
	 * 得到sessionsession 同时把sesion更新到 application...
	 * 
	 * @param application
	 * @param sessionInfo
	 * @return
	 * @throws Exception
	 */
	public SysSessionInfo getSessionAndUpdateToApplication(String sessionKey, Long timeStamp) throws Exception {
		// 0 转换时间戳
		// 1 转换从内存中得到sessionInfo
		SysSessionInfo sysSessionInfo = null;
		try {
			sysSessionInfo = cache.getSession(sessionKey);

		} catch (Exception e) {
			logger.error("get sessionInfo出错", e);
			return null;
		}
		logger.info("sysSessionInfo:{}!",sysSessionInfo);
		if (sysSessionInfo != null) {
			sysSessionInfo.setTimeStampForLastTime(sysSessionInfo.getTimeStamp());
			sysSessionInfo.setTimeStamp(timeStamp);
			
			// 3. 保存到内存中
			cache.putSession(sysSessionInfo);
		}else{	
			logger.error("cannot find from cache and 从数据库查找!");
			sysSessionInfo = getSessionByPrimaryKey(sessionKey);
		}
				
		return sysSessionInfo;
	}
	/**
	 * 插入session
	 * 
	 * @param sessionInfo
     */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public void insertSession(SysSessionInfo sessionInfo) {
		sysSessionInfoMapper.insert(sessionInfo);
	}

	 
	/**
	 * 得到session对象
	 * 
	 * @param id
	 * @return
     */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public SysSessionInfo getSessionByPrimaryKey(String id) {
		return sysSessionInfoMapper.selectByPrimaryKey(id);
	}

}
