package com.stylefeng.guns.sharecore.modular.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.modular.custService.service.WeixinCustService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * @author alan li
 *
 */
@Service("kfInfoHandler")
public class WxCustServiceHandler implements WxMpMessageHandler{
	/**
	 * 
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 微信服务。。
	 */
	@Autowired
	private WeixinCustService weixinCustService;
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage msg, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {	
		if("text".equals(msg.getMsgType()) || "kf_close_session".equals(msg.getEvent()) ){
			return weixinCustService.recvMsg(msg, msg.getFromUserName(), msg.getToUserName());		
		}else{
			return null;
		}
	}

}
