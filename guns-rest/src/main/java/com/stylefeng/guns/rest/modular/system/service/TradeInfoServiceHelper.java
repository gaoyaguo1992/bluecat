package com.stylefeng.guns.rest.modular.system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.core.util.DecimalUtil;
import com.stylefeng.guns.rest.modular.constant.InvokeResult;
import com.stylefeng.guns.sharecore.common.persistence.model.CapitalChangeTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.CapitalChangeRecordModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ChannelCapitalRecordMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.RefundTradeRefModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawOrderInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.CapitalChangeRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.CapitalStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ChannelCapitalRecord;
import com.stylefeng.guns.sharecore.modular.system.model.ChannelCapitalStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.FeeUnitType;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.RefundBO;
import com.stylefeng.guns.sharecore.modular.system.model.RefundTradeRefModel;
import com.stylefeng.guns.sharecore.modular.system.model.TradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawCapitalChangeRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawOrderInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.CustAccountService;
import com.stylefeng.guns.sharecore.modular.system.service.RechargeTradeInfoService;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;
import com.stylefeng.guns.sharecore.modular.system.service.WithdrawTradeInfoService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpRefundMsgResult;

/**
 *
 */
@Service
public class TradeInfoServiceHelper {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 共享助手客户号
	 */
	@Value("${sharehelper.custNo}")
	private Long ptCustNo;
			
	@Autowired
	protected WxMpService wxMpService;
	
	@Autowired
	private WithdrawOrderInfoModelMapper tradeInfoMapper;
	
	@Autowired
	private CapitalChangeRecordModelMapper capitalChangeRecordModelMapper;

	@Autowired
	private ChannelCapitalRecordMapper channelCapitalRecordMapper;

	@Autowired
	protected SequenceService seqService;

	@Autowired
	private RefundTradeRefModelMapper rtrMapper;
 
	@Autowired	
	private RechargeTradeInfoService rechargeTradeInfoService;
	
	@Autowired	
	private WithdrawTradeInfoService withdrawTradeInfoService;
	/**
	 * 客户账户服务器..
	 */
	@Autowired
	private CustAccountService custAccountService;
 	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public void saveTradeInfoAndCapitialChangeRecord(
			WithdrawOrderInfoModel tradeInfoModel, CapitalChangeRecordModel ccr) {	
		tradeInfoMapper.insert(tradeInfoModel);
		capitalChangeRecordModelMapper.insert(ccr);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void saveTradeInfoAndCapitialChangeRecord(
			WithdrawOrderInfoModel tradeInfoModel, CapitalChangeRecordModel... ccrs) {
		
		tradeInfoMapper.insert(tradeInfoModel);
		for (CapitalChangeRecordModel ccr : ccrs) {
			if (null != ccr) {
				capitalChangeRecordModelMapper.insert(ccr);
			}
		}

	}
 
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public void saveTradeInfo(WithdrawOrderInfoModel tradeInfoModel) {
		tradeInfoMapper.insert(tradeInfoModel);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public void updateTradeInfoStatusWhenFail(WithdrawOrderInfoModel tradeInfoModel) {
		tradeInfoMapper.updateByPrimaryKeySelective(tradeInfoModel);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public void updateTradeInfoByPrimaryKeySelective(
			WithdrawOrderInfoModel tradeInfoModel) {
		tradeInfoMapper.updateByPrimaryKeySelective(tradeInfoModel);
	}


	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public boolean handleRefundResult(Map<RefundBO, WxMpRefundMsgResult> refundResults) throws Exception {
		boolean handleResult = false;
		boolean isAllRefund = true;
		Long tradeRefundId =null;
		for (RefundBO refundBO : refundResults.keySet()) {
			WxMpRefundMsgResult result = refundResults.get(refundBO);
			if (result==null|| result.getResult_code().equals(InvokeResult.FAIL)
					|| result.getResult_code().equals(InvokeResult.UNKOWN)) {
				isAllRefund = false;
			}
			tradeRefundId = refundBO.getRefundId();
			ChannelCapitalRecord chcr = channelCapitalRecordMapper
					.selectByPrimaryKeyForUpdate(refundBO.getTradeOutNo());
			if (ChannelCapitalStatusEnum.PROCESSING.getCode()!=chcr.getStatus()) {
				continue;
			}
			WithdrawCapitalChangeRecordModel ccr = withdrawTradeInfoService
					.getCapitalChangeRecordById(chcr.getCapitalChangeId());

			if (InvokeResult.SUCCESS.equals(result.getReturn_code())) {
				// custInfoModelMapper.selectByPrimaryKey(refundBO.getCustNo());
				// channel status is processing
				if (InvokeResult.FAIL.equals(result.getResult_code())) {
					chcr.setStatus(ChannelCapitalStatusEnum.FAILURE.getCode());
					chcr.setResultDesc(result.getErr_code_des());
					chcr.setResultCode(result.getErr_code());
					chcr.setUpdateTime(new Date());					
					channelCapitalRecordMapper
							.updateByPrimaryKeySelective(chcr);

					ccr.setUpdateDate(new Date());
					ccr.setCapitalStatus(CapitalStatusEnum.FAILURE.getCode());
					ccr.setReturnMsg(result.getErr_code_des());
					ccr.setReturnCode(result.getErr_code());
					 

					custAccountService.withdrawUnfreeze(refundBO.getCustNo(), ccr);

					

					RechargeTradeInfoModel orgti = rechargeTradeInfoService
							.getTradeInfoById(refundBO.getTradeId());
					if (orgti != null) {
						logger.info("orgti:" + orgti);
						orgti.setUpdateTime(new Date());
						orgti.setRefundAmount(orgti.getRefundAmount().subtract(
								refundBO.getRefundAmt()));
						rechargeTradeInfoService.updateTradeInfoByPrimaryKeySelective(orgti);
					}
					withdrawTradeInfoService.updateCapitialChangeRecord(ccr,chcr); 

				} else if (InvokeResult.SUCCESS.equals(result.getResult_code())) {
					chcr.setStatus(ChannelCapitalStatusEnum.SUCCESS.getCode());
					chcr.setResultDesc(result.getErr_code_des());
					chcr.setResultCode(result.getErr_code());
					chcr.setMchId(result.getMch_id());
					// chcr.setChannelType(result.getRefund_channel());
					chcr.setTransactionId(result.getTransaction_id());
					chcr.setUpdateTime(new Date());
					
				 

					ccr.setUpdateDate(new Date());
					ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
					ccr.setReturnMsg(result.getErr_code_des());
					ccr.setReturnCode(result.getErr_code());
					withdrawTradeInfoService.updateCapitialChangeRecord(ccr,chcr); 
					custAccountService
							.withdrawUnFreezeAndOut(refundBO.getCustNo(), ccr);		
				} else if (InvokeResult.UNKOWN.equals(result.getResult_code())) {
					 
					chcr.setResultDesc(result.getErr_code_des());
					chcr.setResultCode(result.getErr_code());
					chcr.setUpdateTime(new Date());
					 
					ccr.setUpdateDate(new Date());
				 
					ccr.setReturnMsg(result.getErr_code_des());
					ccr.setReturnCode(result.getErr_code());
					withdrawTradeInfoService.updateCapitialChangeRecord(ccr,chcr); 
				}
			}

		}

		if (isAllRefund) {
			WithdrawTradeInfoModel ti = withdrawTradeInfoService.getTradeInfoById(tradeRefundId);
			ti.setStatus(TradeStatusEnum.SUCCESS.getCode());
			ti.setStatusCn(TradeStatusEnum.SUCCESS.getDesc());
			ti.setUpdateTime(new Date());
			withdrawTradeInfoService.updateTradeInfo(ti);
			handleResult = true;
		}

		return handleResult;
	}
	/**
	 * 得到可提现的记录...
	 * @param cusNo
	 * @return
	 */
	public List<RefundBO> getRefundBOByCustNo(Long cusNo){
		List<RefundBO> tis =new ArrayList<>();
		//1.处理可提现记录..
		try{
			rechargeTradeInfoService.insertRefundRecordsIfHaveNotRefundByCustNo(cusNo);
		}catch(Exception e){
			logger.error("保存异常:",e);
		}
		//2.获取提现交易..
		// get can refund trades
		tis = rechargeTradeInfoService.getRefundAmts(cusNo);
		return tis;
	}
	/**
	 * 充值
	 * 
	 * @param msg
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public List<RefundBO> saveTradeInfoAndCapitialChangeRecordForRefund(
			CustInfoModel payerCustInfo, BigDecimal refundAmount, int channelType,
			String tradeName) throws Exception {
		//1.获取提现交易..
		// get can refund trades
		List<RefundBO> tis = getRefundBOByCustNo(payerCustInfo.getCustNo());
		if (tis == null || tis.size() == 0) {
			logger.warn("没有可以退款的交易,或者已经全部退完!");
			return null;
		}
		//2. 计算提现金额。。
		BigDecimal canRefundAmt = BigDecimal.ZERO;
		for (RefundBO ti : tis) {
			canRefundAmt = canRefundAmt
					.add(ti.getRemainRefundAmt() == null ? BigDecimal.ZERO : ti
							.getRemainRefundAmt());
		}

		if (canRefundAmt.subtract(refundAmount).compareTo(BigDecimal.ZERO) == -1) {
			logger.error("【"+payerCustInfo.getCustNo()+"】可退款的金额不够！");
			return null;
		}
		//3.处理提现逻辑..
		Map<Long, RefundBO> needToLockTrades = new HashMap<Long, RefundBO>();

		// 当前已经统计的所有交易能退的钱
		BigDecimal refundAmtOfTrades = BigDecimal.ZERO;
		for (RefundBO ti : tis) {
			// 除了已经统计的交易能退钱外,还要退的钱
			BigDecimal needMoreMoney = refundAmount.subtract(refundAmtOfTrades);

			refundAmtOfTrades = refundAmtOfTrades.add(ti.getRemainRefundAmt());

			RefundBO refundBO = new RefundBO();
			refundBO.setTradeOutNo(ti.getTradeOutNo());
			refundBO.setChannelType(ti.getChannelType());
			refundBO.setTradeId(ti.getTradeId());
			needToLockTrades.put(ti.getTradeId(), refundBO);
			if (refundAmtOfTrades.compareTo(refundAmount) == -1) {
				refundBO.setRefundAmt(ti.getRemainRefundAmt());
			} else {
				if (refundAmtOfTrades.subtract(refundAmount).equals(
						BigDecimal.ZERO)) {
					refundBO.setRefundAmt(ti.getRemainRefundAmt());
					break;
				} else {
					refundBO.setRefundAmt(needMoreMoney);
					break;
				}
			}
		}

		Long refundId = seqService.getWithdrawTradeRecordSeq();
		WithdrawTradeInfoModel refundInfo = new WithdrawTradeInfoModel();
		refundInfo.setTradeId(refundId);
		refundInfo.setStatus(TradeStatusEnum.PROCESSING.getCode());
		refundInfo.setStatusCn(TradeStatusEnum.PROCESSING.getDesc());		 
		refundInfo.setCheckDate(com.stylefeng.guns.core.util.DateUtils.getCheckDate());
		refundInfo.setCustNo(payerCustInfo.getCustNo());		
		refundInfo.setTradeName(tradeName);
		refundInfo.setTradeAmount(DecimalUtil.format(refundAmount));		 
		refundInfo.setCreateTime(new Date());
		refundInfo.setTradeType(ShareTradeTypeEnum.WITHRAW.getCode());
		List<RechargeTradeInfoModel> tisupdate = rechargeTradeInfoService
				.refundTradesByRefundAmtForUpdate(needToLockTrades.keySet());
		List<RefundBO> result = new ArrayList<RefundBO>();
		for (RechargeTradeInfoModel ti : tisupdate) {
			RefundBO refundInfoBO = needToLockTrades.get(ti.getTradeId());
			
			if(ti.getRefundAmount().add(
					refundInfoBO.getRefundAmt()).compareTo(ti.getTradeAmount())==1){
				throw new ExgrainBizUncheckedException("退款的金额不够或者重现重复退款!");
			}
			ti.setRefundAmount(ti.getRefundAmount().add(
					refundInfoBO.getRefundAmt()));
			ti.setUpdateTime(new Date());
			rechargeTradeInfoService.updateTradeInfoByPrimaryKeySelective(ti);

			WithdrawCapitalChangeRecordModel ccr = new WithdrawCapitalChangeRecordModel();
			ccr.setTradeRecordId(refundId);
			ccr.setAmount(refundInfoBO.getRefundAmt());
			Long ccId = seqService.getWithdrawCapitalChangeRecordSeq();
			ccr.setCapitalChangeId(ccId);
			ccr.setCapitalStatus(CapitalStatusEnum.PROCESSING.getCode());

			ccr.setPayerAccountId(payerCustInfo.getCustAccountModel()
					.getAccountId());
			ccr.setPayerCustNo(payerCustInfo.getCustNo());
			ccr.setCreateTime(new Date());
			ccr.setFlowType(CapitalChangeTypeEnum.WITHDRAW.getCode());

			ChannelCapitalRecord chcr = new ChannelCapitalRecord();
			chcr.setStatus(ChannelCapitalStatusEnum.PROCESSING.getCode());
			chcr.setCreateTime(new Date());
			chcr.setChannelType(channelType);
			chcr.setFeeType(FeeUnitType.CNY.getCode());
			chcr.setOpenId(payerCustInfo.getOpenId());
			Long cccSeq = seqService.getChannelCapitalChangeSeq();
			chcr.setOutTradeNo(cccSeq);
			chcr.setRecordId(cccSeq);
			chcr.setTradeRecordId(ccr.getTradeRecordId());
			chcr.setTotalFee(refundInfoBO.getRefundAmt());
			chcr.setCapitalChangeId(ccId);
			chcr.setCustNo(ti.getCustNo());
			RefundBO refundBO = new RefundBO();
			refundBO.setTotalAmt(ti.getTradeAmount());
			refundBO.setRefundCapitalChangeIds(ccId);
			refundBO.setTradeOutNo(cccSeq);
			refundBO.setRefundAmt(refundInfoBO.getRefundAmt());
			refundBO.setCustNo(payerCustInfo.getCustNo());
			refundBO.setChannelType(refundInfoBO.getChannelType());
			refundBO.setOriginalTradeOutNo(refundInfoBO.getTradeOutNo());
			refundBO.setRefundId(refundId);
			refundBO.setTradeId(refundInfoBO.getTradeId());
			result.add(refundBO);	
			
			RefundTradeRefModel rtr = new RefundTradeRefModel();
			rtr.setCreatedDate(new Date());
			rtr.setRefundTradeOutNo(cccSeq);
			rtr.setRechargeTradeOutNo(refundInfoBO.getTradeOutNo());
			rtr.setRefundAmount(refundInfoBO.getRefundAmt());
			rtr.setRefundId(refundId);
			rtr.setTradeId(ti.getTradeId());
			rtr.setCustNo(payerCustInfo.getCustNo());			
			rtrMapper.insert(rtr);
			custAccountService.withdrawFreeze(payerCustInfo, ccr);
			 
			withdrawTradeInfoService.saveCapitialChangeRecord(ccr, chcr);
		}
		withdrawTradeInfoService.saveTradeInfo(refundInfo);
		return result;
	}
	/**
	 * 保存交易流程表..
	 * @param tradeInfoModel
	 * @param ccr
	 * @param chcr
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public void saveTradeInfoAndCapitialChangeRecord(
			WithdrawOrderInfoModel tradeInfoModel, CapitalChangeRecordModel ccr,
			ChannelCapitalRecord chcr) {		
		saveTradeInfoAndCapitialChangeRecord(tradeInfoModel, ccr);
		channelCapitalRecordMapper.insertSelective(chcr);
	}
}
