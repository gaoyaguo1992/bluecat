package com.stylefeng.guns.sharecore.modular.system.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.sharecore.modular.system.dao.ChannelCapitalRecordMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.RechargeCapitalChangeRecordModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.RechargeTradeInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.ChannelCapitalRecord;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeCapitalChangeRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.RefundBO;

/**
 * <P>TODO</P>
 */
@Service("rechargeTradeInfoService")
public class RechargeTradeInfoService {
	@Autowired
	private ChannelCapitalRecordMapper channelCapitalRecordMapper;
	
	@Autowired
	private RechargeCapitalChangeRecordModelMapper rechargeCapitalChangeRecordModelMapper;
	
	@Autowired
	private RechargeTradeInfoModelMapper rechargeTradeInfoModelMapper;
	
	/**
	 * 保存充电订单
	 * @param tradeInfoModel
	 * @param ccr
	 * @param chcr
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void saveTradeInfoAndCapitialChangeRecord(
			RechargeTradeInfoModel tradeInfoModel, RechargeCapitalChangeRecordModel ccr,
			ChannelCapitalRecord chcr) {	
		//1.充电订单
		rechargeTradeInfoModelMapper.insert(tradeInfoModel);
		//2.充值流水
		rechargeCapitalChangeRecordModelMapper.insert(ccr);
		//3.支付渠道订单
		channelCapitalRecordMapper.insertSelective(chcr);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void updateTradeInfoAndCapitialChangeRecord(
			RechargeTradeInfoModel tradeInfoModel, RechargeCapitalChangeRecordModel ccr,
			ChannelCapitalRecord chcr) {		
		rechargeTradeInfoModelMapper.updateByPrimaryKeySelective(tradeInfoModel);
		rechargeCapitalChangeRecordModelMapper.updateByPrimaryKeySelective(ccr);
		if(null != chcr){
			channelCapitalRecordMapper.updateByPrimaryKeySelective(chcr);
		}
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void updateTradeInfoByPrimaryKeySelective(RechargeTradeInfoModel tradeInfoModel){
		rechargeTradeInfoModelMapper.updateByPrimaryKeySelective(tradeInfoModel);
	}

	
	
	public RechargeCapitalChangeRecordModel getCapitalChangeRecordById(Long capitalChangeId ){
		return rechargeCapitalChangeRecordModelMapper.selectByPrimaryKey(capitalChangeId);
	}
	
	public RechargeTradeInfoModel getTradeInfoById(Long tradeId ){
		return rechargeTradeInfoModelMapper.selectByPrimaryKey(tradeId);
	}
	
	public List<RefundBO> getRefundAmts(Long custNo){
		return rechargeTradeInfoModelMapper.getRefundAmts(custNo);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public void insertRefundRecordsIfHaveNotRefundByCustNo(Long custNo){
		 rechargeTradeInfoModelMapper.insertRefundRecordsIfHaveNotRefundByCustNo(custNo);
	}
	
	public List<RechargeTradeInfoModel> refundTradesByRefundAmtForUpdate(Set<Long> tradeIds){
		return rechargeTradeInfoModelMapper.refundTradesByRefundAmtForUpdate(tradeIds);
    }
	
}
