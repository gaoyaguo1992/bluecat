package com.stylefeng.guns.rest.modular.system.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.SybaseCallMetaDataProvider;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.modular.model.ResultCommandSysSessionInfo;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CallLog;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;
import com.stylefeng.guns.sharecore.common.persistence.model.SysSessionInfo;
import com.stylefeng.guns.sharecore.common.service.SessionService;
import com.stylefeng.guns.sharecore.common.utils.SysUtil;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;
import com.stylefeng.guns.sharecore.modular.system.service.ICallLogService;
import com.stylefeng.guns.sharecore.modular.system.service.WxAppApiService;

/**
 * 处理微信小程序中的服务相关逻辑....
 * @author lxy *
 * 2017/9/13
 */
@Service("WxAppLoginService")
public class WxAppLoginService extends BaseService  {
	private static final Logger logger = LoggerFactory.getLogger(WxAppLoginService.class);
	
	/**
	 * 保存日志
	 */
	@Resource
	private ICallLogService callLogService; 
	/**
	 * 实现微信小程序的所有相关api接口服务类....
	 */
	@Autowired
	private WxAppApiService wxAppApiService;
	
	/**
	 *客户信息操作类
	 */
	@Autowired
	private CustInfoBySelfMapper custInfoMapper;
	
	/** 
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 
     *  
     * @param request 
     * @return 
     * @throws IOException 
     */  
    public  static String getIpAddress(HttpServletRequest request) {  
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
    	try {	       
	        return SysUtil.getIpAddress(request);
			
		} catch (Exception e) {
		}
    	return "";
    }  
	/**
	 * session服务类
	 */
	@Autowired
	private SessionService sessionService;
	/**
	 * 调用接口时，通这此方法确定是否凭证有效...
	 * 
	 * @param request:
	 *            request请求...
	 * @param paramValues:
	 *            接口方法参数结果串,
	 *            如约定A方法有param1,param2,param3参数值分别为A,1,B那边paramValues为:A1B
	 * @param keyCode:md5参数...
	 * @param 有效登录凭证...
	 * @return ResultCommand 返回responseInfo中包含SysSessionInfo内容..
	 */
	public ResultCommandSysSessionInfo checkSessionForWxApp(HttpServletRequest request, String paramValues,
			String keyCode, String session3rd) {
		ResultCommandSysSessionInfo result = new ResultCommandSysSessionInfo();
		result.setResult(ResultCommand.SUCCESS);
		//0. 判断用户登录回调日志...
		saveCallLog(request, paramValues, keyCode, session3rd);

		if (session3rd == null || session3rd.isEmpty()) {
			logger.error("系统未登录，请退出重新登录!");
			result.setResult(ResultCommand.FAILED);
			result.setMessage("系统未登录，请退出重新登录!");
			return result;
		}
		//1. 提交时间stamp
		String timeStampStr = request.getParameter("timestamp") == null ? "0" : request.getParameter("timestamp");// headerUrl
		Long timeStamp=0L;
		SysSessionInfo sessionInfo = null;
		try {
			//sessionInfo = sessionService.getSession(request.getServletContext(), session3rd);
			try {
				timeStamp=Long.valueOf(timeStampStr);
			} catch (Exception e) {
				// TODO: handle exception
			}
			sessionInfo=sessionService.getSessionAndUpdateToApplication( session3rd, timeStamp);
		} catch (Exception e) {
			logger.error("系统未登录，请退出重新登录",e);
		}
		if (sessionInfo == null) {
			logger.error("sessionInfo is null");
			result.setResult(ResultCommand.FAILED);
			result.setMessage("系统未登录，请退出重新登录!");
			return result;
		}
		
		String code = sessionInfo.getJscode();
		if (code == null || code.isEmpty()) {
			logger.error("session code is null");
			result.setResult(ResultCommand.FAILED);
			result.setMessage("系统授权失败，请重新登录!");
			return result;
		}
		//2. 参数被篡改...
		String md5Encode = MD5Util.getMd5String(String.format("%s%s", paramValues == null ? "" : paramValues, code));
		if (!md5Encode.equalsIgnoreCase(keyCode)) {
			logger.error("系统参数不正确");
			result.setResult(ResultCommand.FAILED);
			result.setMessage("系统参数不正确，请再次尝试一下!");
			return result;
		}

		result.setResponseInfo(sessionInfo);
		//为了数据安全，打码
		/*sessionInfo.setJscode("");
		sessionInfo.setSessionkey("");*/
		return result;
	}
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
			String ip=getIpAddress(request);
			String url= request.getRequestURI();
			
			CallLog callLog=new CallLog();
			callLog.setCreateTime(new Date());
			callLog.setCustNo(keyCode);
			callLog.setIp(ip);
			callLog.setUrl(url);
			callLog.setSessionId(session3rd);
			callLog.setParams(paramValues);
			callLogService.insert(callLog);			
		} catch (Exception e) {
			logger.error("sysCallLogMapper insert error",e);
		}
	}
}