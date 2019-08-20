package com.stylefeng.guns.sharecore.modular.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.sharecore.common.persistence.dao.SeqModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.ChargerTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.DeviceTypeEnum;

/**
 * 处理序列
 * 
 * @author Alan.huang
 *
 */
@Service("common.SequenceService")
public class SequenceService {
	@Autowired
	private SeqModelMapper seqMapper;


	
	/**
	 * 
	 * <p>
	 * 微信小游戏，充值交易序列号
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getWxGameGoodPayTradeSeq() {
		Long seq = getSeq(SEQINFO.GAME_GOOD_PAY_TRADE_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * 
	 * <p>
	 * 白名单Id序列
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getWhiteListSeq() {
		Long seq = getSeq(SEQINFO.FAULT_IMG_SEQ);

		return seq;
		// return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getCustFormIdSeq() {
		Long seq = getSeq(SEQINFO.CUST_FORMID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 故障图片名 11位 广告图片名
	 * 
	 * @param seqName
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getAdImgSeq() {
		Long seq = getSeq(SEQINFO.AD_IMG_SEQ);
		return composeSeq(seq);
	}

	private Long getSeq(String seqName) {

		return Long.valueOf(seqMapper.selectSeqByName(seqName));
	}
	/**
	 * 空出指定数据的序号..
	 * @param seqName
	 * @param number
	 * @return
	 */
	private Long getSeqByNumber(String seqName,Long number) {
		Long endIndex=Long.valueOf(seqMapper.selectSeqByNameAndNumber(seqName, number));
		return endIndex-number+1;
	}

	/**
	 * 11位 故障图片名
	 * 
	 * @param seqName
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getFaultImgSeq() {
		Long seq = getSeq(SEQINFO.FAULT_IMG_SEQ);
		return composeSeq(seq);
	}


	/**
	 * 11位 终端图片序列
	 * 
	 * @param seqName
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getTerminalSingleImgSeq() {
		Long seq = getSeq(SEQINFO.TERMINAL_SINGLE_IMG_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChargerManagerSingleImgSeq() {
		Long seq = getSeq(SEQINFO.CHARGER_MANAGER_SINGLE_IMG_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 终端图片序列
	 * 
	 * @param seqName
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getTerminalImgSeq() {
		Long seq = getSeq(SEQINFO.TERMINAL_IMG_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 faultReport id
	 * 
	 * @param seqName
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getFaultSeq() {
		Long seq = getSeq(SEQINFO.FAULT_NO_SEQ);
		return composeSeq(seq);
	}

	private Long composeSeq(Long seq) {
			return seq;
	}

	/**
	 * 11位 custNo
	 * 
	 * @param seqName
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getCustNoSeq() {
			Long custNo = getSeq(SEQINFO.CUST_NO_SEQ);
			custNo=1000000000L+custNo;
			return custNo;
	}

	/**
	 * 11位 account No
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getAccountNoSeq() {
		Long seq = getSeq(SEQINFO.ACCOUNT_NO_SEQ);
		return composeSeq(seq);
	}

	private String parseSeq(String seqStr, int length) {
		int seqStrLength = seqStr.length();
		if (seqStrLength < length) {
			for (int i = 0; i < length - seqStrLength; i++) {
				seqStr = "0" + seqStr;
			}
		} else {
			seqStr = seqStr.substring(seqStrLength - length, seqStrLength);
		}
		return seqStr;
	}

	/**
	 * 11位 user id seq
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getUserIdSeq() {
		Long seq = getSeq(SEQINFO.USER_ID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * AC01充电器类型对应的设备id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getPWCDGDeviceSeq() {
		String dateStr = DateUtil.getCurrentDate(DateUtil.YYMM);
		String seqStr = String.valueOf(getSeq(SEQINFO.PW_CDG_SEQ));
		seqStr = parseSeq(seqStr, 8);
		return Long.valueOf(dateStr + DeviceTypeEnum.AC01_PW_CDG.getCode() + seqStr);
	}
	/**
	 * 报表序列
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChartSeq() {
		Long seq = getSeq(SEQINFO.CHART_SEQ);
		return composeSeq(seq);
	}
	/**
	 * 得到ACPWCDG设备...
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getACPWCDGDeviceSeq() {
		String dateStr = DateUtil.getCurrentDate(DateUtil.YYMM);
		String seqStr = String.valueOf(getSeq(SEQINFO.AC_PW_CDG_SEQ));
		seqStr = parseSeq(seqStr, 8);
		return Long.valueOf(dateStr + DeviceTypeEnum.AC01_PW_CDG.getCode() + seqStr);
	}
	/**
	 * 得到AC02 设备密码id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getAC02PWCDGDeviceSeq() {
		String dateStr = DateUtil.getCurrentDate(DateUtil.YYMM);
		String seqStr = String.valueOf(getSeq(SEQINFO.AC02_PW_CDG_SEQ));
		seqStr = parseSeq(seqStr, 8);
		return Long.valueOf(dateStr + DeviceTypeEnum.AC02_PW_CDG.getCode() + seqStr);
	}

	/**
	 * 11位 user id seq
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getDeviceZoneSeq() {
		Long seq = getSeq(SEQINFO.DEVICE_ZONE_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 user id seq
	 * 得到充电器id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getPWCDBChargerSeq() {
		String dateStr = DateUtil.getCurrentDate("yyMM");
		String seqStr = String.valueOf(getSeq(SEQINFO.PW_CDB_SEQ));
		seqStr = parseSeq(seqStr, 7);
		return Long.valueOf(dateStr + ChargerTypeEnum.AC01_PW_01.getCode() + seqStr);
	}


	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getAutoResponeSeq() {
		Long seq = getSeq(SEQINFO.AUTO_RESPONE_ID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 user id seq
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getCardIdSeq() {
		Long seq = getSeq(SEQINFO.CARD_ID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 得到商户号..
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getMerchantIdSeq(Integer merchantType) {
		Long seq = getSeq(SEQINFO.MERCHANT_ID_SEQ);
		Long lng=1000000000L*merchantType;
		Long id=lng+seq;
		return composeSeq(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGongYingMerchantIdSeq() {
		Long seq = getSeq(SEQINFO.GONG_YING_MERCHANT_ID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 account No
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getTradeIdSeq() {
		Long seq = getSeq(SEQINFO.TRADE_ID_SEQ);
		seq=seq+1000000000L;
		return composeSeq(seq);
	}

	/**
	 * 11位 account No
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getUserScanTradeIdSeq() {
		Long seq = getSeq(SEQINFO.CUST_SCAN_TRADE_ID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 account No
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getRefundIdSeq() {
		Long seq = getSeq(SEQINFO.TRADE_ID_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getCapitalChangeSeq() {
		Long seq = getSeq(SEQINFO.CAPTITAL_CHANGE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getCapitalFlowSeq() {
		Long seq = getSeq(SEQINFO.CAPITAL_FLOW_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChannelCapitalChangeSeq() {
		Long seq = getSeq(SEQINFO.CHANNEL_CAPITAL_FLOW_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 user id seq
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getDeviceMerchantSeq() {
		Long seq = getSeq(SEQINFO.DEVICE_MERCHNAT_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getSMSRecordSeq() {
		Long seq = getSeq(SEQINFO.DEVICE_MERCHNAT_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getFeeSeq() {
		Long seq = getSeq(SEQINFO.FEE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getADSeq() {
		Long seq = getSeq(SEQINFO.AD_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getMerchantADSeq() {
		Long seq = getSeq(SEQINFO.MCH_AD_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getWxBillSeq() {
		Long seq = getSeq(SEQINFO.BILL_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getMaaiSeq() {
		Long seq = getSeq(SEQINFO.MAAI_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long geCustFeeUsageSeq() {
		Long seq = getSeq(SEQINFO.CUST_FEE_USAGE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getZoneModelSeq() {
		Long seq = getSeq(SEQINFO.CUST_FEE_USAGE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getEvaluateSeq() {
		Long seq = getSeq(SEQINFO.EVALUATE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getSysLogSeq() {
		Long seq = getSeq(SEQINFO.SYS_LOG_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 11位 user id seq
	 * 
	 * @return
	 */

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getDailiMerchantIdSeq() {
		Long seq = getSeq(SEQINFO.MERCHANT_ID_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getPuhuoMerchantIdSeq() {
		Long seq = getSeq(SEQINFO.MERCHANT_ID_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getJiamengMerchantIdSeq() {
		Long seq = getSeq(SEQINFO.MERCHANT_ID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 
	 * <p>
	 * 产品ID
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getProductIdSeq() {
		Long seq = getSeq(SEQINFO.PRODUCT_ID_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 
	 * <p>
	 * 商户结算账户ID
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)

	public Long getSettlementAccountIdSeq() {
		Long seq = getSeq(SEQINFO.SETTLEMENT_ACCOUNT_ID_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getMerchantProfitDaySeq() {
		Long seq = getSeq(SEQINFO.MERCHANT_PROFIT_DAY);
		return composeSeq(seq);
	}


	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getMerchantProfitHistorySeq() {
		Long seq = getSeq(SEQINFO.MERCHANT_PROFIT_HISTORY);
		return composeSeq(seq);
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getTaskRunningSeq() {
		Long seq = getSeq(SEQINFO.TASK_RUNNING_RECORD_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getSalemanTradeSeq() {
		Long seq = getSeq(SEQINFO.SALEMAN_TRADE_RECORD_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getMerchantWithdrawApplySeq() {
		Long seq = getSeq(SEQINFO.MERCHANT_WITHDRAW_APPLY);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getRewardWithdrawApplySeq() {
		Long seq = getSeq(SEQINFO.REWARD_WITHDRAW_APPLY);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getLoveCircleMsgSeq() {
		Long seq = getSeq(SEQINFO.LOVE_CIRCLE_MSG);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getLoveCircleCommentsMsgSeq() {
		Long seq = getSeq(SEQINFO.LOVE_CIRCLE_COMMENTS_MSG);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getSysOperationInfoSeq() {
		Long seq = getSeq(SEQINFO.SYS_OPERATION_INFO);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getBuyDeviceApplySeq() {
		Long seq = getSeq(SEQINFO.BUY_DEVICE_APPLY_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getOfflineMerSeq() {
		Long seq = getSeq(SEQINFO.OFFLINE_MER_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 电主轮数序列
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChargerHostRoundSeq() {
		Long seq = getSeq(SEQINFO.CHARGER_HOST_ROUND_ID_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getUserSeq() {
		Long seq = getSeq(SEQINFO.USER_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getSystemUpgradeSeq() {
		Long seq = getSeq(SEQINFO.SYSTEM_UPGRADE_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 
	 * <p>
	 * 生成提示消息序列
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getNotifyMessageSeq() {
		Long seq = getSeq(SEQINFO.NOTIFY_MESSAGE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChargerHostRuleDetailIdSeq() {
		Long seq = getSeq(SEQINFO.CHARGER_HOST_RULE_DETAIL_ID_SEQ);
		return composeSeq(seq);
	}



	/**
	 * 
	 * <p>
	 * 优惠券序列号
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getTerminalMerchantCommentSeq() {
		Long seq = getSeq(SEQINFO.TERMINAL_MERCH_COMMENT_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getManualTradeHandleRecordSeq() {
		Long seq = getSeq(SEQINFO.MANUAL_TRADE_HANDLE_RECORD_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getRechargeTradeRecordSeq() {
		Long seq = getSeq(SEQINFO.RECHARGE_TRADE_ID_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getWithdrawTradeRecordSeq() {
		Long seq = getSeq(SEQINFO.WITHDRAW_TRADE_ID_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getWithdrawCapitalChangeRecordSeq() {
		Long seq = getSeq(SEQINFO.WITHDRAW_CAPTITAL_CHANGE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getRechargeCapitalChangeRecordSeq() {
		Long seq = getSeq(SEQINFO.RECHARGE_CAPTITAL_CHANGE_SEQ);
		return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameUserSeq() {
		Long seq = getSeq(SEQINFO.GAME_USER_SEQ);

		return seq;
		// return composeSeq(seq);return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameUserAccountSeq() {
		Long seq = getSeq(SEQINFO.GAME_USERACCOUNT_SEQ);

		return seq;
		// return composeSeq(seq);return composeSeq(seq);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameShareTradeInfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_SHARE_TRADE_SEQ);

		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 游戏运行实例
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameGuessSizeRuninfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_GUESS_SIZE_RUNINFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 转出金币
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameAccountTradeInfoOutSeqSeq() {
		Long seq = getSeq(SEQINFO.ACCOUNT_TRADE_INFO_OUT_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 转入金币
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameAccountTradeInfoInSeqSeq() {
		Long seq = getSeq(SEQINFO.ACCOUNT_TRADE_INFO_IN_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 猜大小
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameGuessSizeTradeinfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_GUESS_SIZE_TRADEINFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 猜星星
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameGuessStarTradeinfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_GUESS_STAR_TRADEINFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 抽奖
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameLotteryTradeinfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_LOTTERY_TRADEINFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * star
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameGuessStarLoginInfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_GUESS_STAR_LOGININFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * size
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameGuessSizeLoginInfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_GUESS_SIZE_LOGININFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 抽奖
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGoodGiftExchangeInfoSeq() {
		Long seq = getSeq(SEQINFO.GOOD_GIFT_EXCHANGE_INFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * <p>
	 * 押星星金币
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getGameGuessStarRuninfoSeq() {
		Long seq = getSeq(SEQINFO.GAME_GUESS_STAR_RUNINFO_SEQ);
		return seq;
		// return composeSeq(seq);
	}

	/**
	 * 培训资料ID 
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getTraningFileSeq() {
		Long seq = getSeq(SEQINFO.TRANING_FILE_UPLOAD);
		return composeSeq(seq);
	}

	/**
	 * 充电器主人绑定表ID 
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChargerCustBandingSeq() {
		Long seq = getSeq(SEQINFO.CHARGER_CUST_BANDING_INFO);
		return composeSeq(seq);
	}

	/**
	 * 充电器交易主数据..
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChargerMasterTradeSeq() {
		Long seq = getSeq(SEQINFO.CHARGER_MASTER_TRADE_SEQ);
		return composeSeq(seq);
	}

	/**
	 * 提现方式的id 
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getChargerOPerationInfoSeq() {
		Long seq = getSeq(SEQINFO.CHARGER_OPERATION_INFO);
		return composeSeq(seq);
	}
	/**
	 * 得到共享充电器表主键..
	 * 反回格式为..191116*********一共15位
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getShareChargerSeq(int chargerTypeId) {
		Long seq = getSeq(String.format("%s_%d", SEQINFO.SHARE_CHARGER_SEQ,chargerTypeId));
		Long lng=100000000L*chargerTypeId;
		Long id=lng+seq;
		return id;
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getShareChargerSeqByNumber(int chargerTypeId,Long number) {
		Long seq = getSeqByNumber(String.format("%s_%d", SEQINFO.SHARE_CHARGER_SEQ,chargerTypeId),number);
		Long lng=100000000L*chargerTypeId;
		Long id=lng+seq;
		return id;
	}
	/**
	 * 得到共享充电器设备表主键
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getShareDeviceInfoSeq(Integer deviceTypeId) {
		Long seq = getSeq(String.format("%s_%d",SEQINFO.SHARE_DEVICE_INFO_SEQ,deviceTypeId));
		if(deviceTypeId==null){
			deviceTypeId=new Integer("10");
		}
		seq=deviceTypeId*100000000L+seq;
		return composeSeq(seq);
	}
	/**
	 * 得到共享充电器设备表主键
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getShareDeviceInfoSeqByNumber(Integer deviceTypeId,Long number) {
		Long seq = getSeqByNumber(String.format("%s_%d",SEQINFO.SHARE_DEVICE_INFO_SEQ,deviceTypeId),number);
		if(deviceTypeId==null){
			deviceTypeId=new Integer("10");
		}
		seq=deviceTypeId*100000000L+seq;
		return composeSeq(seq);
	}
	/**
	 * 得到共享充电器设备表主键
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getShareDeviceInfoSeqByNumberForYYMMBB(Long seqIndex,Long number) {
		String seqName=String.format("share_device_info_seq_%d",seqIndex);
		Long seq = getSeqByNumber(seqName,number);
		//生成id
    	Long startIndex=seqIndex*100000+seq;
    	
		return startIndex;
	}
	/**
	 * 得到共享充电器交易表主鍵。
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getShareTradeInfoSeq() {
		Long seq = getSeq(SEQINFO.SHARE_TRADE_INFO_SEQ);
		seq=1000000000L+seq;
		return composeSeq(seq);
	}
	/**
	 * 得到共享充电器交易表主鍵。
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getAdvanceShareTradeInfoSeq() {
		Long seq = getSeq(SEQINFO.SHARE_TRADE_INFO_SEQ);
		seq=8000000000L+seq;
		return composeSeq(seq);
	}
	/**
	 * 得到商户申请表
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public Long getMerchantApplyFormSeq() {
		Long seq = getSeq(SEQINFO.MERCHANT_APPLY_FORM_SEQ);
		seq=1000000000L+seq;
		return composeSeq(seq);
	}
	
	/**
	 * set names utf8mb4
	 */
	public void setNamesUtf8mb4(){
		seqMapper.setNamesUtf8mb4();
	}
	
	
}
