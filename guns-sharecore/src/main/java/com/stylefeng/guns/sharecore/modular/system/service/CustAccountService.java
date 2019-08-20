package com.stylefeng.guns.sharecore.modular.system.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.utils.SysUtil;
import com.stylefeng.guns.sharecore.modular.system.dao.CapitalChangeFlowModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.CustAccountModelMapper;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.CapitalChangeFlowModel;
import com.stylefeng.guns.sharecore.modular.system.model.CapitalChangeRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.CapitalFlowTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.CustAccountAmtSumEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeCapitalChangeRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawCapitalChangeRecordModel;


/**
 *账户服务处理类
 */
@Service
public class CustAccountService  extends BaseService{
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(CustAccountService.class);
	/**
	 * 客户账号
	 */
	@Autowired
	private CustAccountModelMapper custAccountModelMapper;
	
	@Value("${sharehelper.custNo}")
	private Long ptCustNo;
	/**
	 * 流程表...
	 */
	@Autowired
	private CapitalChangeFlowModelMapper capitalChangeFlowModelMapper;

	/**
	 * 资金转入...
	 */
	public void in(Long payerCustId, CapitalChangeRecordModel capChangeRecordModel)
			throws Exception {
		//判断账号
		if(ptCustNo.longValue()==payerCustId.longValue()){
			CustAccountModel ca = custAccountModelMapper.selectByCustNo(payerCustId,CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
			CapitalChangeFlowModel changeFlowModel = new CapitalChangeFlowModel();
			changeFlowModel.setAccountId(ca.getAccountId());
			changeFlowModel.setAvailableBalance(ca.getAvailableBalance().add(capChangeRecordModel.getAmount()));		
			changeFlowModel.setCapitalChangeId(capChangeRecordModel.getCapitalChangeId());
			changeFlowModel.setCapitalType(CapitalFlowTypeEnum.IN.getCode());
			changeFlowModel.setCreateTime(new Date());
			changeFlowModel.setCustNo(ca.getCustNo());
			changeFlowModel.setIsSumed(CustAccountAmtSumEnum.NOME_SUM.getCode());
			changeFlowModel.setTradeRecordId(capChangeRecordModel.getTradeRecordId());
			changeFlowModel.setFrozenBalance(ca.getFrozenBalance());
			changeFlowModel.setBalance(capChangeRecordModel.getAmount());
			changeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
			capitalChangeFlowModelMapper.insert(changeFlowModel);
		}else{
			CustAccountModel custAcntModel = custAccountModelMapper.selectByCustNoForUpdate(
						payerCustId,CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
			if(custAcntModel==null){
				logger.error("WX资金账户不存在:"+payerCustId);
				return;
			}
			boolean checkResult = SysUtil.checkDigest(custAcntModel, custAcntModel.getDataDigest());
			if (checkResult == false) {
				throw new ExgrainBizUncheckedException("资金账户被篡改");
			}
	
			CapitalChangeFlowModel changeFlowModel = new CapitalChangeFlowModel();
			changeFlowModel.setAccountId(custAcntModel.getAccountId());
			changeFlowModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(capChangeRecordModel.getAmount()));		
			changeFlowModel.setCapitalChangeId(capChangeRecordModel.getCapitalChangeId());
			changeFlowModel.setCapitalType(CapitalFlowTypeEnum.IN.getCode());
			changeFlowModel.setCreateTime(new Date());
			changeFlowModel.setCustNo(custAcntModel.getCustNo());
			changeFlowModel.setFrozenBalance(custAcntModel.getFrozenBalance());
			changeFlowModel.setBalance(capChangeRecordModel.getAmount());
			changeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
			changeFlowModel.setTradeRecordId(capChangeRecordModel.getTradeRecordId());
			changeFlowModel.setIsSumed(CustAccountAmtSumEnum.SUMED.getCode());
			capitalChangeFlowModelMapper.insert(changeFlowModel);
	
			custAcntModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(capChangeRecordModel.getAmount()));
			custAcntModel.setBalance(custAcntModel.getBalance().add(capChangeRecordModel.getAmount()));		
			custAcntModel.setUpdateTime(new Date());
			custAcntModel.setDataDigest(SysUtil.getDigest(custAcntModel));
			custAccountModelMapper.updateByPrimaryKey(custAcntModel);
			if(SysUtil.isNotBalance(custAcntModel)){
				throw new ExgrainBizUncheckedException("资金账户资金计算错误"+custAcntModel);
			}
		}
	}
	/**
	 * 充值 ..
	 */
	public void recharge(Long payerCustId, RechargeCapitalChangeRecordModel recCapitalChangeRecordModel)
			throws Exception {
		CapitalChangeRecordModel capChangeRecordModel = new CapitalChangeRecordModel();
		BeanUtils.copyProperties(recCapitalChangeRecordModel, capChangeRecordModel);
		in(payerCustId,capChangeRecordModel);
	}
	/**
	 * 用户提现...
	 */
	public void withdrawUnFreezeAndOut(Long custNo, WithdrawCapitalChangeRecordModel drawCapitalChangeRecordModel)
			throws Exception {
		CapitalChangeRecordModel changeRecordModel = new CapitalChangeRecordModel();
		BeanUtils.copyProperties(drawCapitalChangeRecordModel, changeRecordModel);
		unFreezeAndOut(custNo,changeRecordModel);
	}
	/**
	 * 解冻资金交扣款.。。
	 */
	public void unFreezeAndOut(Long custNo, CapitalChangeRecordModel changeRecordModel)
			throws Exception {
		CustAccountModel custAcntModel = custAccountModelMapper.selectByCustNoForUpdate(
										custNo,CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
		if(custAcntModel==null){
			logger.error("WX资金账户不存在:"+custNo);
			return;
		}
		boolean checkResult = SysUtil.checkDigest(custAcntModel, custAcntModel.getDataDigest());
		if (checkResult == false) {
			throw new ExgrainBizUncheckedException("资金账户被篡改");
		}

		if(changeRecordModel.getAmount().compareTo(custAcntModel.getFrozenBalance())==1){
			throw new ExgrainBizUncheckedException("可解冻冻结资金不足");
		}
		//1.解冻资金 .. changeRecordModel.getAmount()
		CapitalChangeFlowModel unfreezeChangeFlowModel = new CapitalChangeFlowModel();
		unfreezeChangeFlowModel.setAccountId(custAcntModel.getAccountId());		
		unfreezeChangeFlowModel.setCapitalType(CapitalFlowTypeEnum.UNFREEZED.getCode());
		unfreezeChangeFlowModel.setCreateTime(new Date());
		unfreezeChangeFlowModel.setCustNo(custNo);
		unfreezeChangeFlowModel.setCapitalChangeId(changeRecordModel.getCapitalChangeId());
		unfreezeChangeFlowModel.setBalance(changeRecordModel.getAmount());
		unfreezeChangeFlowModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(changeRecordModel.getAmount()));		
		unfreezeChangeFlowModel.setFrozenBalance(custAcntModel.getFrozenBalance().subtract(changeRecordModel.getAmount()));
		unfreezeChangeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
		unfreezeChangeFlowModel.setTradeRecordId(changeRecordModel.getTradeRecordId());
		capitalChangeFlowModelMapper.insert(unfreezeChangeFlowModel);

		custAcntModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(changeRecordModel.getAmount()));
		custAcntModel.setFrozenBalance(custAcntModel.getFrozenBalance().subtract(changeRecordModel.getAmount()));
		custAcntModel.setUpdateTime(new Date());
		custAcntModel.setDataDigest(SysUtil.getDigest(custAcntModel));
		custAccountModelMapper.updateByPrimaryKey(custAcntModel);
		
		//2. 扣款...changeRecordModel.getAmount()
		CapitalChangeFlowModel changeFlowModel = new CapitalChangeFlowModel();
		changeFlowModel.setAccountId(custAcntModel.getAccountId());
		changeFlowModel.setAvailableBalance(custAcntModel.getAvailableBalance().subtract(changeRecordModel.getAmount()));		
		changeFlowModel.setCapitalChangeId(changeRecordModel.getCapitalChangeId());
		changeFlowModel.setCapitalType(CapitalFlowTypeEnum.OUT.getCode());
		changeFlowModel.setCreateTime(new Date());
		changeFlowModel.setCustNo(custNo);
		changeFlowModel.setFrozenBalance(custAcntModel.getFrozenBalance());
		changeFlowModel.setBalance(changeRecordModel.getAmount());
		changeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
		changeFlowModel.setTradeRecordId(changeRecordModel.getTradeRecordId());
		capitalChangeFlowModelMapper.insert(changeFlowModel);

		custAcntModel.setAvailableBalance(custAcntModel.getAvailableBalance().subtract(changeRecordModel.getAmount()));
		custAcntModel.setBalance(custAcntModel.getBalance().subtract(changeRecordModel.getAmount()));		
		custAcntModel.setUpdateTime(new Date());
		custAcntModel.setDataDigest(SysUtil.getDigest(custAcntModel));
		custAccountModelMapper.updateByPrimaryKey(custAcntModel);
		
		if(SysUtil.isNotBalance(custAcntModel)){
			throw new ExgrainBizUncheckedException("资金账户资金计算错误"+custAcntModel);
		}
	}
	/**
	 * 冻结资金
	 */
	public void freeze(CustInfoModel payerCustInfo,  CapitalChangeRecordModel changeRecordModel)
			throws Exception {
		CustAccountModel custAcntModel = custAccountModelMapper.selectByCustNoForUpdate(
										payerCustInfo.getCustNo(),CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
		if(custAcntModel==null){
			logger.error("WX资金账户不存在:"+payerCustInfo);
			return;
		}
		boolean checkResult = SysUtil.checkDigest(custAcntModel, custAcntModel.getDataDigest());
		if (checkResult == false) {
			throw new ExgrainBizUncheckedException("资金账户被篡改");
		}

		if(changeRecordModel.getAmount().compareTo(custAcntModel.getAvailableBalance())==1){
			throw new ExgrainBizUncheckedException("可冻结资金不足,请先充值！");
		}
		CapitalChangeFlowModel changeFlowModel = new CapitalChangeFlowModel();
		changeFlowModel.setAccountId(custAcntModel.getAccountId());		
		changeFlowModel.setCapitalChangeId(changeRecordModel.getCapitalChangeId());
		changeFlowModel.setCapitalType(CapitalFlowTypeEnum.FREEZED.getCode());
		changeFlowModel.setCreateTime(new Date());
		changeFlowModel.setCustNo(payerCustInfo.getCustNo());
		changeFlowModel.setFrozenBalance(custAcntModel.getFrozenBalance().add(changeRecordModel.getAmount()));
		changeFlowModel.setBalance(changeRecordModel.getAmount());
		changeFlowModel.setAvailableBalance(custAcntModel.getAvailableBalance().subtract(changeRecordModel.getAmount()));	
		changeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
		changeFlowModel.setTradeRecordId(changeRecordModel.getTradeRecordId());
		capitalChangeFlowModelMapper.insert(changeFlowModel);

		custAcntModel.setAvailableBalance(custAcntModel.getAvailableBalance().subtract(changeRecordModel.getAmount()));
		custAcntModel.setFrozenBalance(custAcntModel.getFrozenBalance().add(changeRecordModel.getAmount()));		
		custAcntModel.setUpdateTime(new Date());
		custAcntModel.setDataDigest(SysUtil.getDigest(custAcntModel));
		custAccountModelMapper.updateByPrimaryKey(custAcntModel);
		if(SysUtil.isNotBalance(custAcntModel)){
			throw new ExgrainBizUncheckedException("资金账户资金计算错误"+custAcntModel);
		}
	}
	/**
	 * 解冻资金...
	 * @param payerCustInfo
	 * @throws Exception
	 */
	public void withdrawFreeze(CustInfoModel payerCustInfo, WithdrawCapitalChangeRecordModel withDrawChangerModel) throws Exception {
		CapitalChangeRecordModel changeRecordModel = new CapitalChangeRecordModel();
		BeanUtils.copyProperties(withDrawChangerModel, changeRecordModel);
		freeze(payerCustInfo, changeRecordModel);
	}
	/**
	 * 提现，转出
	 */
	public void withdrawUnfreeze(Long custNo, WithdrawCapitalChangeRecordModel drawCapitalChangeRecordModel)
			throws Exception {
		CapitalChangeRecordModel capChangeRecordModel =  new CapitalChangeRecordModel();
		BeanUtils.copyProperties(drawCapitalChangeRecordModel, capChangeRecordModel);
		unfreeze(custNo,capChangeRecordModel);
	}
	/**
	 * 资金解冻..
	 */
	public void unfreeze(Long custNo, CapitalChangeRecordModel capChangeRecordModel)
			throws Exception {
		CustAccountModel custAcntModel = custAccountModelMapper.selectByCustNoForUpdate(
									custNo,CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
		if(custAcntModel==null){
			logger.error("WX资金账户不存在:"+custNo);
			return;
		}
		if(capChangeRecordModel.getAmount().compareTo(custAcntModel.getFrozenBalance())==1){
			throw new ExgrainBizUncheckedException("可解冻冻结资金不足");
		}
		
		boolean checkResult = SysUtil.checkDigest(custAcntModel, custAcntModel.getDataDigest());
		if (checkResult == false) {
			throw new ExgrainBizUncheckedException("资金账户被篡改");
		}

		CapitalChangeFlowModel changeFlowModel = new CapitalChangeFlowModel();
		changeFlowModel.setAccountId(custAcntModel.getAccountId());		
		changeFlowModel.setCapitalChangeId(capChangeRecordModel.getCapitalChangeId());
		changeFlowModel.setCreateTime(new Date());
		changeFlowModel.setCustNo(custNo);
		changeFlowModel.setCapitalType(CapitalFlowTypeEnum.UNFREEZED.getCode());
		changeFlowModel.setBalance(capChangeRecordModel.getAmount());
		changeFlowModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(capChangeRecordModel.getAmount()));		
		changeFlowModel.setFrozenBalance(custAcntModel.getFrozenBalance().subtract(capChangeRecordModel.getAmount()));
		changeFlowModel.setTradeRecordId(capChangeRecordModel.getTradeRecordId());
		changeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
		capitalChangeFlowModelMapper.insert(changeFlowModel);

		custAcntModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(capChangeRecordModel.getAmount()));
		custAcntModel.setFrozenBalance(custAcntModel.getFrozenBalance().subtract(capChangeRecordModel.getAmount()));
		custAcntModel.setUpdateTime(new Date());
		custAcntModel.setDataDigest(SysUtil.getDigest(custAcntModel));
		custAccountModelMapper.updateByPrimaryKey(custAcntModel);
		if(SysUtil.isNotBalance(custAcntModel)){
			throw new ExgrainBizUncheckedException("资金账户资金计算错误"+custAcntModel);
		}
	}
	/**
	 * 解冻资金同时扣除余额.....此方法没有启用事务，请在处启用事务...
	 */
	public void unfreezeAndOut(Long custNo, CapitalChangeRecordModel capChangeRecordModel, CapitalChangeRecordModel out)
			throws Exception {
		CustAccountModel custAcntModel = custAccountModelMapper.selectByCustNoForUpdate(
									custNo,CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
		if(custAcntModel==null){
			logger.error("WX资金账户不存在:"+custNo);
			return;
		}
		if(capChangeRecordModel.getAmount().compareTo(custAcntModel.getFrozenBalance())==1){
			throw new ExgrainBizUncheckedException("可解冻冻结资金不足");
		}
		boolean checkResult = SysUtil.checkDigest(custAcntModel, custAcntModel.getDataDigest());
		if (checkResult == false) {
			throw new ExgrainBizUncheckedException("资金账户被篡改");
		}

		//1. 资金解冻..
		CapitalChangeFlowModel changeFlowModel = new CapitalChangeFlowModel();
		changeFlowModel.setAccountId(custAcntModel.getAccountId());		
		changeFlowModel.setCapitalChangeId(capChangeRecordModel.getCapitalChangeId());
		changeFlowModel.setCreateTime(new Date());
		changeFlowModel.setCustNo(custNo);
		changeFlowModel.setBalance(capChangeRecordModel.getAmount());
		changeFlowModel.setCapitalType(CapitalFlowTypeEnum.UNFREEZED.getCode());
		changeFlowModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(capChangeRecordModel.getAmount()));		
		changeFlowModel.setFrozenBalance(custAcntModel.getFrozenBalance().subtract(capChangeRecordModel.getAmount()));
		changeFlowModel.setTradeRecordId(capChangeRecordModel.getTradeRecordId());
		changeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
		capitalChangeFlowModelMapper.insert(changeFlowModel);
		//1.1 处理余额
		custAcntModel.setAvailableBalance(custAcntModel.getAvailableBalance().add(capChangeRecordModel.getAmount()));
		custAcntModel.setFrozenBalance(custAcntModel.getFrozenBalance().subtract(capChangeRecordModel.getAmount()));
		//2. 扣款。
		changeFlowModel = new CapitalChangeFlowModel();
		changeFlowModel.setCreateTime(new Date());
		changeFlowModel.setCapitalType(CapitalFlowTypeEnum.OUT.getCode());
		changeFlowModel.setAccountId(custAcntModel.getAccountId());	
		changeFlowModel.setCustNo(custNo);	
		changeFlowModel.setCapitalChangeId(out.getCapitalChangeId());
		changeFlowModel.setBalance(out.getAmount());
		changeFlowModel.setAvailableBalance(custAcntModel.getAvailableBalance().subtract(out.getAmount()));		
		changeFlowModel.setFlowId(seqService.getCapitalFlowSeq());
		changeFlowModel.setTradeRecordId(out.getTradeRecordId());
		capitalChangeFlowModelMapper.insert(changeFlowModel);

		custAcntModel.setAvailableBalance(custAcntModel.getAvailableBalance().subtract(out.getAmount()));
		custAcntModel.setBalance(custAcntModel.getBalance().subtract(out.getAmount()));
		
		//3. 更新人余额
		custAcntModel.setUpdateTime(new Date());
		custAcntModel.setDataDigest(SysUtil.getDigest(custAcntModel));
		custAccountModelMapper.updateByPrimaryKey(custAcntModel);
		
		if(SysUtil.isNotBalance(custAcntModel)){
			throw new ExgrainBizUncheckedException("资金账户资金计算错误"+custAcntModel);
		}
	}
}
