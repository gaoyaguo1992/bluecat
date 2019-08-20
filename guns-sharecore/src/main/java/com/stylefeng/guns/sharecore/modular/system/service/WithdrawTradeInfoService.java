package com.stylefeng.guns.sharecore.modular.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.sharecore.modular.system.dao.ChannelCapitalRecordMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawCapitalChangeRecordModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawTradeInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.ChannelCapitalRecord;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawCapitalChangeRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawTradeInfoModel;



/**
 * <P>处理用户提现相关业务</P>
 * 
 */
@Service("WithdrawTradeInfoService")
public class WithdrawTradeInfoService {
	@Autowired
	private ChannelCapitalRecordMapper channelCapitalRecordMapper;

	@Autowired
	private WithdrawCapitalChangeRecordModelMapper withdrawCapitalChangeRecordModelMapper;

	@Autowired
	private WithdrawTradeInfoModelMapper withdrawTradeInfoModelMapper;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void saveCapitialChangeRecord(WithdrawCapitalChangeRecordModel ccr, ChannelCapitalRecord chcr) {
		withdrawCapitalChangeRecordModelMapper.insert(ccr);
		channelCapitalRecordMapper.insertSelective(chcr);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void saveTradeInfo(WithdrawTradeInfoModel tradeInfoModel) {
		withdrawTradeInfoModelMapper.insert(tradeInfoModel);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void updateCapitialChangeRecord(WithdrawCapitalChangeRecordModel ccr, ChannelCapitalRecord chcr) {

		withdrawCapitalChangeRecordModelMapper.updateByPrimaryKeySelective(ccr);
		channelCapitalRecordMapper.updateByPrimaryKeySelective(chcr);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
	public void updateTradeInfo(WithdrawTradeInfoModel tradeInfoModel) {
		withdrawTradeInfoModelMapper.updateByPrimaryKeySelective(tradeInfoModel);

	}

	public WithdrawCapitalChangeRecordModel getCapitalChangeRecordById(Long capitalChangeId) {
		return withdrawCapitalChangeRecordModelMapper.selectByPrimaryKey(capitalChangeId);
	}

	public WithdrawTradeInfoModel getTradeInfoById(Long tradeId) {
		return withdrawTradeInfoModelMapper.selectByPrimaryKey(tradeId);
	}

}
