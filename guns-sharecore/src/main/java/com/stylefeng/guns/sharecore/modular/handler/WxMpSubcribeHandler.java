package com.stylefeng.guns.sharecore.modular.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.service.RegisterService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 
 * <P>关注微信公众号处理器</P>
 */
@Service
public class WxMpSubcribeHandler implements WxMpMessageHandler {
	
	@Autowired
	private RegisterService registerService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		logger.info("Mchgzh Subcribe...");
		String openId = wxMessage.getFromUserName();
		WxMpCustomMessage cmsg = new WxMpCustomMessage();
		try {
			// 关注公众号时获取openId
			cmsg.setToUser(openId);
			cmsg.setTitle("感谢关注，小二将给您提供星级服务");
			cmsg.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
			cmsg.setContent("感谢关注，小二将给您提供星级服务");
			// 获取用户信息
			WxMpUser wxMpUser = wxMpService.userInfo(openId, null);
			CustInfoModel custInfo =null;	
			// 根据unionId查询该用户是否存在 
			if (wxMpUser != null && wxMpUser.getUnionId() != null && !wxMpUser.getUnionId().isEmpty()) {
				custInfo = registerService.getCustInfoByUnionId(wxMpUser.getUnionId());
			}
			// 不存在的话则注册
			if (custInfo == null) {
				custInfo = registerService.registeCust(wxMpUser);
			// 存在的话更新	
			} else {
				custInfo.setNickName(wxMpUser.getNickname());
				custInfo.setSex(wxMpUser.getSex());
				custInfo.setLanguage(wxMpUser.getLanguage());
				custInfo.setCity(wxMpUser.getCity());
				custInfo.setProvince(wxMpUser.getProvince());
				custInfo.setCountry(wxMpUser.getCountry());
				custInfo.setHeadImgUrl(wxMpUser.getHeadImgUrl());
				custInfo.setUnionId(wxMpUser.getUnionId());
				custInfo.setWexinRemark(wxMpUser.getRemark());
				custInfo.setGroupId(wxMpUser.getGroupId());
				// 商户公众号openId
				custInfo.setOpenId(wxMpUser.getOpenId());
				registerService.updateCustInfo(custInfo);
			}
		} catch (Exception e) {
			logger.error("公众号平台关注异常,:{},异常堆栈信息:{}",openId,e);
		}
		try {
			logger.info("{}关注公众号,推送消息内容:{}",openId,cmsg.toJson());
			wxMpService.customMessageSend(cmsg);
		} catch (Exception e) {
			logger.error("关注商户公众号,发生异常,异常信息:{}");
		}
		return null;
	}

}
