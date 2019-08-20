
package com.stylefeng.guns.sharecore.modular.custService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;


/**
 * <P>微信客服功能</P>
 */
@Service("weixinCustService")
public class WeixinCustService {
	private static final Logger logger = LoggerFactory.getLogger(WeixinCustService.class);
	@Autowired
	protected WxMpService wxMpService;

	/**
	 * 发送关注信息
	 */
	public WxMpXmlOutMessage recvMsg(WxMpXmlMessage wmxMsg, String fromOpenId, String toOpenId) throws WxErrorException {
		StringBuffer resultResponeContent = new StringBuffer();
		WxMpCustomMessage wcm = new WxMpCustomMessage();
		wcm.setToUser(fromOpenId);
		wcm.setMsgType("text");
		 if("kf_close_session".equals(wmxMsg.getEvent())){
			 	wcm.setContent("您的会话已经结束，欢迎再次咨询~");
				wxMpService.customMessageSend(wcm);
				return null;
		 }
		 newDefaultResponeMessage(resultResponeContent, fromOpenId, wcm);
		return null;
	}
	/**
	 * 发送关注信息
	 */
	private void newDefaultResponeMessage(StringBuffer resultResponeContent, String fromOpenId, WxMpCustomMessage wcm)
			throws WxErrorException {
		String welcomeMsg = "非常感谢您使用共享充电服务";		
		wcm.setContent(welcomeMsg);
		wcm.setToUser(fromOpenId);
		wcm.setMsgType("text");
		wxMpService.customMessageSend(wcm);
	}
}
