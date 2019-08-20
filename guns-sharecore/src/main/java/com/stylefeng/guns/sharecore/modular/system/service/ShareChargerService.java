package com.stylefeng.guns.sharecore.modular.system.service;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.sharecore.modular.system.constants.ShareChargerTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.ChargerPwdsRefactors;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.utils.GeneratePwdCalacutorWithFactors;

@Service
public class ShareChargerService extends BaseService {

	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(ShareChargerService.class);
	
	/**
	 * 充电器管理mybitse..
	 */
	@Autowired
	private ShareChargerModelMapper shareChargerModelMapper;
	
	
	/**
	 * 生成密码原因...
	 * @param chargerId
	 * @return
	 * @throws Exception
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3)
	public  ChargerPwdsRefactors getChargerRefactorIdx(Long chargerId) throws Exception {
 		logger.info("查询密码因子是否正确. chargerId:【{}】", chargerId);
		//1.查询密码因子是否正确..
 		ShareChargerModel cm=shareChargerModelMapper.selectByPrimaryKey(chargerId);
		if (cm == null) {
			throw new Exception("充电器不存在");
		}
		if (StringUtils.isNotEmpty(cm.getRefactorIdx())) {
			throw new Exception("充电器密码已经写入");
		}
		//2. 计算新的密码原因
	    StringBuilder factorIndex = new StringBuilder();
		Random rdm= new Random();
		factorIndex.append(rdm.nextInt(80)+",");
		factorIndex.append(rdm.nextInt(80)+",");
		factorIndex.append(rdm.nextInt(80)+",");
		factorIndex.append(rdm.nextInt(80)+",");
		factorIndex.append(rdm.nextInt(80)+",");
		factorIndex.append(rdm.nextInt(80)+",");
		factorIndex.append(rdm.nextInt(80)+",");
		factorIndex.append(rdm.nextInt(80));
		String factorsIdx=factorIndex.toString();
 		logger.info(String.format("计算新的密码原因. chargerId:【%s】factorsIdx:[%s] ", chargerId,factorsIdx));
		//3. 生成密码..
		String chargerIdStr = chargerId.toString();
		String chargerIdSuffix = chargerIdStr.substring(chargerIdStr.length() - 8);
		//计算密码...
		String pwds = GeneratePwdCalacutorWithFactors.calculate(chargerIdSuffix, factorsIdx,
				(cm!=null&&cm.getChargerTypeId()!=null)? cm.getChargerTypeId().intValue():ShareChargerTypeEnum.AD_10.getCode());
 		logger.info("计算新的密码:【{}】,chargerPwds:【{}】", chargerId, pwds);
 		String refactors=GeneratePwdCalacutorWithFactors.getFactorHexStr(factorsIdx);
 		ChargerPwdsRefactors chargerPwdsRefactors=new   ChargerPwdsRefactors();
 		chargerPwdsRefactors.setChargerPwds(pwds);
 		chargerPwdsRefactors.setFactorsIdx(factorsIdx);
 		chargerPwdsRefactors.setRefactors(refactors);
		return chargerPwdsRefactors;
	}
	
}
