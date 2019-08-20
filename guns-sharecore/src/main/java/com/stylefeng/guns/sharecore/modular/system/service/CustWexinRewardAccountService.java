package com.stylefeng.guns.sharecore.modular.system.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 *
 */
@Service("common.CustRewardAccountService")
public class CustWexinRewardAccountService extends BaseService {
	private static final Logger logger = LoggerFactory.getLogger(CustAccountService.class);
	@Value("${sharehelper.custNo}")
	private Long ptCustNo;

	@Autowired
	private CapitalChangeFlowModelMapper capitalChangeFlowModelMapper;

	@Autowired
	private CustAccountModelMapper custAccountModelMapper;

	public void out(CustInfoModel payerCustInfo, CapitalChangeRecordModel ccr) throws Exception {
		CustAccountModel ca = custAccountModelMapper.selectByCustNoForUpdate(payerCustInfo.getCustNo(),
				CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
		if (ca == null) {
			logger.error("资金账户不存在:" + payerCustInfo);
			return;
		}
		boolean checkResult = SysUtil.checkDigest(ca, ca.getDataDigest());
		if (checkResult == false) {
			throw new ExgrainBizUncheckedException("资金账户被篡改");
		}

		if (ccr.getAmount().compareTo(ca.getAvailableBalance()) == 1) {
			throw new ExgrainBizUncheckedException("可出资金不足");
		}

		CapitalChangeFlowModel ccf = new CapitalChangeFlowModel();
		ccf.setAccountId(ca.getAccountId());
		ccf.setAvailableBalance(ca.getAvailableBalance().subtract(ccr.getAmount()));
		ccf.setCapitalChangeId(ccr.getCapitalChangeId());
		ccf.setCapitalType(CapitalFlowTypeEnum.OUT.getCode());
		ccf.setCreateTime(new Date());
		ccf.setCustNo(payerCustInfo.getCustNo());
		ccf.setFrozenBalance(ca.getFrozenBalance());
		ccf.setBalance(ccr.getAmount());
		ccf.setFlowId(seqService.getCapitalFlowSeq());
		ccf.setTradeRecordId(ccr.getTradeRecordId());
		capitalChangeFlowModelMapper.insert(ccf);

		ca.setAvailableBalance(ca.getAvailableBalance().subtract(ccr.getAmount()));
		ca.setBalance(ca.getBalance().subtract(ccr.getAmount()));
		ca.setUpdateTime(new Date());
		ca.setDataDigest(SysUtil.getDigest(ca));
		custAccountModelMapper.updateByPrimaryKey(ca);
		if (SysUtil.isNotBalance(ca)) {
			throw new ExgrainBizUncheckedException("资金账户资金计算错误" + ca);
		}
	}

}
