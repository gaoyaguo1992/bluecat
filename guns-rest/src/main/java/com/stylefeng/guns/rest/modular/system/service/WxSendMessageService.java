package com.stylefeng.guns.rest.modular.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.sharecore.common.persistence.model.WxBackMessageBO;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.NotifyMessageMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.NotifyMessageBO;
import com.stylefeng.guns.sharecore.modular.system.model.NotifyMessageModel;
import com.stylefeng.guns.sharecore.modular.system.model.NotifyMessageStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.NotifyMessageTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;

/**
 * 
 * <P>
 * 微信发消息服务类
 * </P>
 */
@Service
public class WxSendMessageService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected WxMpService wxMpService;

	@Autowired
	private NotifyMessageMapper notifyMessageMapper;

	@Autowired
	protected SequenceService seqService;

	@Autowired
	private MerchantInfoModelBySelfMapper merchantInfoModelBySelfMapper;


	/**
	 * 
	 * <p>
	 * 发送归还充电器成功消息
	 * </p>
	 * 
	 * @param message
	 */
	public void sendBackSuccessMsg(WxBackMessageBO message) {
		logger.info("发归还消息  WxBackMessageBO：{}", message);
		// 插入消息==============================
		NotifyMessageModel notifyMessage = new NotifyMessageModel();
		notifyMessage.setId(seqService.getNotifyMessageSeq());
		notifyMessage.setType(NotifyMessageTypeEnum.BAK_MESSAGE.getCode());
		notifyMessage.setStatus(NotifyMessageStatusEnum.UNREAD_MESSAGE.getCode());
		notifyMessage.setReceiveCustNo(message.getCustInfoModel().getCustNo());
		notifyMessage.setTradeId(message.getTradeId());
		notifyMessage.setCreateTime(new Date());
		notifyMessage.setTitle("归还充电器通知");
		notifyMessage.setContent("恭喜您，归还充电器成功啦！ 消费：" + message.getChargerAmount() + "元， 使用时长：" + message.getUsedTime()
				+ "  ," + String.format(message.getRemark(), "可点击【返回首页】", "左上角头像"));
		notifyMessageMapper.insertSelective(notifyMessage);
		// 插入消息==============================
	}

	/**
	 * 
	 * <p>
	 * 记录系统通知信息
	 * </p>
	 * 
	 * @param notifyMessageBO
	 */
	public void insertNotifyMessage(NotifyMessageBO notifyMessageBO) {
		try {
			NotifyMessageModel myMessage = new NotifyMessageModel();
			myMessage.setId(seqService.getNotifyMessageSeq());
			myMessage.setType(notifyMessageBO.getMessageType());
			myMessage.setStatus(NotifyMessageStatusEnum.UNREAD_MESSAGE.getCode());
			myMessage.setReceiveCustNo(notifyMessageBO.getCustInfoModel().getCustNo());
			myMessage.setTradeId(notifyMessageBO.getTradeId());
			myMessage.setCreateTime(new Date());
			myMessage.setTitle(notifyMessageBO.getTitle());
			myMessage.setContent(notifyMessageBO.getContent());
			notifyMessageMapper.insertSelective(myMessage);
		} catch (Exception e) {
			logger.warn("记录系统通知信息异常,异常信息:{}", e);
		}
	}
}
