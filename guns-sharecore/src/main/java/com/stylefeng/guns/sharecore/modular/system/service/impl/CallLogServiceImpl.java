package com.stylefeng.guns.sharecore.modular.system.service.impl;

import com.stylefeng.guns.sharecore.common.persistence.model.CallLog;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.sharecore.common.persistence.dao.CallLogMapper;
import com.stylefeng.guns.sharecore.modular.system.service.ICallLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author alan li
 * @since 2018-12-30
 */
@Service
public class CallLogServiceImpl extends ServiceImpl<CallLogMapper, CallLog> implements ICallLogService {

	/**
	 * 日志操作对象.
	 */
	private static final Logger logger = LoggerFactory.getLogger(CallLogServiceImpl.class);
	/**
	 * 操作日志...
	 */
	@Resource
	private CallLogMapper callLogMapper;
	/**
	 * <p>保存登录信息</p>
	 * @param request
	 * @param paramValues
	 * @param keyCode
	 * @param session3rd
	 * @author #{xxxxxx-136***********}
	 */
	public void saveCallLog(HttpServletRequest request, String paramValues, String keyCode, String session3rd) {
		try {			
			String ip=ToolUtil.getIpAddress(request);
			String url= request.getRequestURI();
			CallLog logModel=new CallLog();
			logModel.setCreateTime(new Date());
			logModel.setCustNo(keyCode);
			logModel.setIp(ip);
			logModel.setUrl(url);
			logModel.setSessionId(session3rd);
			logModel.setParams(paramValues);
			callLogMapper.insert(logModel);			
		} catch (Exception e) {
			logger.error("sysCallLogMapper insert error",e);
		}
	}

}
