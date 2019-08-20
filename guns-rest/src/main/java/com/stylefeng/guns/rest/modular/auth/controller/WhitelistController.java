package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.model.ResultCommandString;
import com.stylefeng.guns.sharecore.common.WXAppletUserInfo;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.*;
import com.stylefeng.guns.sharecore.common.service.RegisterService;
import com.stylefeng.guns.sharecore.common.service.SessionService;
import com.stylefeng.guns.sharecore.modular.system.service.ICallLogService;
import com.stylefeng.guns.sharecore.modular.system.service.ShareDeviceService;
import com.stylefeng.guns.sharecore.modular.system.service.WxAppApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * 处理所有不需要过虑处理的JSON Web Token（缩写 JWT）跨域认证解决方案的链接...
 * @author Alan.huang
 *
 */
@Controller
@RequestMapping("/whitelist")
public class WhitelistController {
	/**
	 * 日志操作对象.
	 */
	private static final Logger logger = LoggerFactory.getLogger(WhitelistController.class);
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
	
	@Autowired
	private ShareDeviceService shareDeviceService;
	
	/**
	 *客户信息操作类
	 */
	@Autowired
	private CustInfoBySelfMapper custInfoMapper;
	
	/**
	 * 注册服务...
	 */
	@Autowired
	private RegisterService registerService;
	/**
	 * session保存....
	 */
	@Autowired
	private SessionService sessionService;	

	/**
	 * 处理token...
	 */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 微信小程序登录..
     * @return
     */
    @RequestMapping("/wxappLogin")
    @ResponseBody
    public ResultCommandString wxappLogin(HttpServletRequest request, HttpServletResponse response) {       
    	//1 初始化
    	ResultCommandString result=new ResultCommandString();
    	Date date=new Date();
		Date start=new Date();			
		result.setResult(ResultCommand.SUCCESS);
		//2 获取传过来的参数..
		String jsCode = request.getParameter("code");
		String iv = request.getParameter("iv");
		String encryptedData = request.getParameter("encryptedData");
		HttpSession httpSession=request.getSession();
		//保存访问日志
		callLogService.saveCallLog(request, String.format("onLogn:%s,%s",iv,encryptedData),jsCode,httpSession.getId());
		date=new Date();		
		Long time=date.getTime()-start.getTime();
		logger.info(String.format("wxappLogin: date.getTime()=%d,onLogin.htm保存访问日志时长:%d",date.getTime(),time));
		if (jsCode == null || jsCode.isEmpty()) {
			result.setResult(ResultCommand.FAILED);
			result.setResponseInfo("微信服务器登录失败!");
			return result;
		}
		// 解密码得到unionId
		try {
			//登录微信服务器，得到openId, session_key...
			//1、微信见权...
			WxAppSessionKeyModel model = wxAppApiService.getSessionKeyByJsCode(jsCode);
			date=new Date();
			time=date.getTime()-start.getTime();
			logger.info(String.format("onLogin.htm wxAppApiService.GetSessionKeyByJsCode: date.getTime()=%d,onLogin.htm微信见权:%d",date.getTime(),time));			
			//1.1 登录一下表示超时，在次登录一次..			
			if (model == null) {	
				model = wxAppApiService.getSessionKeyByJsCode(jsCode);
			}
			if (model == null) {
				result.setResult(ResultCommand.FAILED);
				result.setResponseInfo("微信服务器登录失败!");
				return result;
			}
			date=new Date();
			time=date.getTime()-start.getTime();
			logger.info(String.format("onLogin.htm wxAppApiService.GetSessionKeyByJsCode end date.getTime()=%d,onLogin.htm微信二次见权:%d",date.getTime(),time));
			//第二次尝试登录
			if (model.getSession_key() == null || model.getSession_key().isEmpty() || model.getOpenId() == null
					|| model.getOpenId().isEmpty()) {
				model = wxAppApiService.getSessionKeyByJsCode(jsCode);
			}
			//
			if (model.getSession_key() == null || model.getSession_key().isEmpty() || model.getOpenId() == null
					|| model.getOpenId().isEmpty()) {
				result.setResult(ResultCommand.FAILED);
				result.setResponseInfo("微信服务器登录失败!");
				return result;
			}
			
			String sessionKey = model.getSession_key();
			WxAppUserInfoForEncrypted userInfoByEncryptedData = WXAppletUserInfo.getInstance()
					.getUserInfo(encryptedData, sessionKey, iv);

			// 2、生成账号..
			CustInfoModel custInfo = (userInfoByEncryptedData != null && userInfoByEncryptedData.getUnionId() != null
					&& !userInfoByEncryptedData.getUnionId().isEmpty())
							? registerService.getCustInfoByUnionId(userInfoByEncryptedData.getUnionId()) : null;
			if (custInfo == null) {
				custInfo = registerService.getCustInfoByWxAppOpenId(model.getOpenId());
			}
			/**
			 * 更新客户信息。。。。
			 */
			if (custInfo == null) {
				ShareWxMpUser wxMpUser = new ShareWxMpUser();
				wxMpUser.setWxAppOpenId(model.getOpenId());
				if (userInfoByEncryptedData != null) {
					wxMpUser.setCity(userInfoByEncryptedData.getCity());
					wxMpUser.setUnionId(userInfoByEncryptedData.getUnionId());
					wxMpUser.setCountry(userInfoByEncryptedData.getCountry());
					wxMpUser.setHeadImgUrl(userInfoByEncryptedData.getAvatarUrl());
					wxMpUser.setNickname(userInfoByEncryptedData.getNickName());
					wxMpUser.setProvince(userInfoByEncryptedData.getProvince());
					String sex = "";
					if(!StringUtils.isEmpty(userInfoByEncryptedData.getGender())) {
						if(userInfoByEncryptedData.getGender().equals("1")){
							sex = "男";
						}
						if(userInfoByEncryptedData.getGender().equals("2")){
							sex = "女";
						}
						if(userInfoByEncryptedData.getGender().equals("0")){
							sex = "未知";
						}
					}
					wxMpUser.setSex(sex);
				}
				custInfo = registerService.registeShareCust(wxMpUser);
			} else {
				Boolean updateFlag = false;
				if (custInfo.getWxAppOpenId()== null || !custInfo.getWxAppOpenId().equals(model.getOpenId())) {
					custInfo.setWxAppOpenId(model.getOpenId());
					updateFlag = true;
				}
				if (userInfoByEncryptedData != null) {
					if (custInfo.getCity() == null || !custInfo.getCity().equals(userInfoByEncryptedData.getCity())) {
						custInfo.setCity(userInfoByEncryptedData.getCity());
						updateFlag = true;
					}
					if (custInfo.getUnionId() == null
							|| !custInfo.getUnionId().equals(userInfoByEncryptedData.getUnionId())) {
						custInfo.setUnionId(userInfoByEncryptedData.getUnionId());
						updateFlag = true;
					}
					if (custInfo.getCountry() == null
							|| !custInfo.getCountry().equals(userInfoByEncryptedData.getCountry())) {
						custInfo.setCountry(userInfoByEncryptedData.getCountry());
						updateFlag = true;
					}
					if (custInfo.getHeadImgUrl() == null
							|| !custInfo.getHeadImgUrl().equals(userInfoByEncryptedData.getAvatarUrl())) {
						custInfo.setHeadImgUrl(userInfoByEncryptedData.getAvatarUrl());
						updateFlag = true;
					}
					if (custInfo.getNickName() == null
							|| !custInfo.getNickName().equals(userInfoByEncryptedData.getNickName())) {
						custInfo.setNickName(userInfoByEncryptedData.getNickName());
						updateFlag = true;
					}
					if (custInfo.getProvince() == null
							|| !custInfo.getProvince().equals(userInfoByEncryptedData.getProvince())) {
						custInfo.setProvince(userInfoByEncryptedData.getProvince());
						updateFlag = true;
					}
				}
				if (updateFlag) {
					custInfoMapper.updateByPrimaryKeySelective(custInfo);
				}
			}

			date=new Date();
			time=date.getTime()-start.getTime();
			logger.info(String.format("onLogin.htm 生成session_3rd: date.getTime()=%d,onLogin.htm保存用户信息到客户表:%d",date.getTime(),time));
			// 3、生成session_3rd
			final String randomKey = jwtTokenUtil.getRandomKey();			
	        final String token = jwtTokenUtil.generateToken(custInfo.getCustNo().toString(), randomKey);

	        date=new Date();
			time=date.getTime()-start.getTime();
			logger.info(String.format("onLogin.htm 生成session_3rd: date.getTime()=%d,onLogin.htm保存用户信息到客户表:%d",date.getTime(),time));
			// 3、生成session_3rd
			UUID guid = java.util.UUID.randomUUID();
			String sGuid = guid.toString().replace("-", "");
			java.util.Date dt = new java.util.Date();
			String session_3rd = String.format("sh%s%d", sGuid, dt.getTime());
			SysSessionInfo sessionInfo = new SysSessionInfo();
			sessionInfo.setId(session_3rd);
			sessionInfo.setCreatetime(dt);
			sessionInfo.setCustNo(custInfo.getCustNo());
			sessionInfo.setJscode(jsCode);
			sessionInfo.setOpenid(model.getOpenId());
			sessionInfo.setSessionId(String.format("%s_._%s", token,randomKey));
			sessionInfo.setSessionkey(model.getSession_key());
			sessionInfo.setStatus(1);
			sessionInfo.setUpdatetime(dt);
			sessionInfo.setSessiontype( 1);
			sessionInfo.setWxappopendid(model.getOpenId());
			sessionService.insertSessionToApplication(sessionInfo);

			date=new Date();
			time=date.getTime()-start.getTime();
			logger.info(String.format("onLogin.htm 生成session_3rd end: date.getTime()=%d,onLogin.htm登录结束:%d",date.getTime(),time));
			// return ....
			result.setResponseInfo(session_3rd + "_" + randomKey+"_"+custInfo.getCustNo());  	
			return result;
		}catch(Exception ee){
			logger.error("微信小程序登录..", ee);
			result.setResult(ResultCommand.FAILED);
			result.setMessage(ee.getMessage());
			return result;
		}
    }
    /**
     * 根据坐座，得到附近的设备列表..
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deviceForNear")
    @ResponseBody
    public List<MapMarkersBO> deviceForNear(HttpServletRequest request, HttpServletResponse response) {
    	//1.获取坐座数据
    	String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		logger.info("方法：nearDevice 入参    longitude:{},latitude:{}", longitude, latitude);
		//2. 保存访问日志
		callLogService.saveCallLog(request, String.format("deviceForNear:%s,%s",longitude,latitude),null,null);
		//3. 得到附近的设备信息
		List<MapMarkersBO> nearDeviceList = shareDeviceService.nearDevice(longitude, latitude);		
		return nearDeviceList;
    
    }
}
