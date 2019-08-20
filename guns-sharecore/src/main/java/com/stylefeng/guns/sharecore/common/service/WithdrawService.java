package com.stylefeng.guns.sharecore.common.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.modular.system.constants.ApproveStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.IsRealTimeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.PayWayEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.WithdrawStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawApplyRecordModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawApplyRecordModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoBo;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawApplyRecordModel;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;

/**
 * 
 *
 */
@Service("WithdrawService")
public class WithdrawService extends BaseService {

	/**
	 * 操作...记录对mapper..
	 */
	@Autowired
	private WithdrawApplyRecordModelMapper withdrawMapper;
	/**
	 * 提现mapper...
	 */
	@Autowired
	private WithdrawApplyRecordModelBySelfMapper withdrawMapperBySelfMapper;

	/**
	 * 查询商户提现记录
	 * 
	 * @param merchantId
	 * @return
	 */
	public List<WithdrawApplyRecordModel> queryMerchantWithdrawRecord(Long merchantId) {
		return withdrawMapper.queryMerchantWithdrawRecord(merchantId);
	}

	/**
	 * 创建提现记录...
	 * 
	 * @param preTaxAmount
	 * @param aftTaxAmount
	 * @param mib
	 * @return
	 */
	public Long createMerchantWithdrawRecord(BigDecimal preTaxAmount, BigDecimal aftTaxAmount, MerchantInfoBo mib,
			PayWayEnum paywayEnum) {
		// 1. 创建提现记录
		WithdrawApplyRecordModel recordModel = new WithdrawApplyRecordModel();
		// 提现状态，默认为处理中
		recordModel.setWithdrawStatus(WithdrawStatusEnum.HANDLING.getCode());
		recordModel.setWithdrawStatusCn(WithdrawStatusEnum.HANDLING.getDesc());
		recordModel.setApproveStatus(ApproveStatusEnum.PASS.getCode());
		recordModel.setApproveStatusCn(ApproveStatusEnum.PASS.getDesc());
		// 申请记录ID
		Long withdrawId = seqService.getMerchantWithdrawApplySeq();
		recordModel.setId(withdrawId);
		// 申请时间
		Date applyTime = new Date();
		recordModel.setApplyTime(applyTime);
		// 应缴税款
		recordModel.setShouldPayTax(preTaxAmount.subtract(aftTaxAmount));
		// 记录本次提现为第几次提现
		Integer num = withdrawMapperBySelfMapper.getWithDrawNumsThisMonth(mib.getId());
		recordModel.setNumberOfMonth(num + 1);
		// 付款方式
		recordModel.setPayWay((paywayEnum != null) ? paywayEnum.getCode() : PayWayEnum.UNKNOW_PAY.getCode());
		// 分润方式
		recordModel.setIsRealTime(IsRealTimeEnum.T_PLUS_0.getCode());
		// 申请商户ID
		recordModel.setApplyMerchantId(mib.getId());
		// 税前提现金额
		recordModel.setPreTaxAmount(preTaxAmount);
		// 税后提现金额
		recordModel.setAftTaxAmount(aftTaxAmount);
		// 处理备注...
		if (mib != null && mib.getRemark() != null && !mib.getRemark().isEmpty()) {
			recordModel.setApproveComment(mib.getRemark());
		}
		// 2. 保存
		withdrawMapper.insert(recordModel);
		return withdrawId;
	}
}
