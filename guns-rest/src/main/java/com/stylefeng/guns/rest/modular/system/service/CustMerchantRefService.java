package com.stylefeng.guns.rest.modular.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.common.persistence.model.CustTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoBySelfModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
/**
 * 
 * <P>客户商户关联依赖服务类</P>
 */
@Service("custMerchantRefService")
public class CustMerchantRefService {
	
	private final Logger logger = LoggerFactory.getLogger(CustMerchantRefService.class);
	/**
	 * 商户数据库操作
	 */
	@Autowired
	private CustMerchantRefInfoModelMapper custMerchantRefInfoModelMapper;
	/**
	 * 商户操作对象...
	 */
	@Autowired
	private CustMerchantRefInfoBySelfModelMapper custMerchantRefInfoBySelfModelMapper;
	/**
	 * 
	 * <p>根据商户编号获取该商户对应的客户商户信息</p>
	 * @param merchantId
	 * @return
	 */
	public CustMerchantRefInfoModel getCustSettleMerchantInfo(Long merchantId){
		List<CustMerchantRefInfoModel> custMerchants = 
					custMerchantRefInfoBySelfModelMapper.selectByMerchantId(merchantId);
		if(custMerchants == null || custMerchants.size() == 0){
			return null;
		}else{
			for(CustMerchantRefInfoModel custMerchantRefInfoModel:custMerchants){
				// 获取客户类型为商户
				if(CustTypeEnum.MERCHANT.getCode() == custMerchantRefInfoModel.getCustType().intValue()){
					return custMerchantRefInfoModel;
				}
			}
			// 遍历后没有发现客户类型为商户，则直接返回null
			return null;
		}
	}
	/**
	 * 
	 * <p>判断当前用户是不时商户</p>
	 * @param custNo
	 * @return true--是商户
	 */
	public boolean isMerchant(Long custNo) {
		List<CustMerchantRefInfoModel> custMerchants = 
				custMerchantRefInfoBySelfModelMapper.selectByCustNo(custNo);
		if(custMerchants!=null && custMerchants.size()>0){
			for(CustMerchantRefInfoModel cmi : custMerchants ){
				//终端商户除外
				if(MerchantTypeEnum.TERMINAL.getCode().intValue() == cmi.getMerchantType()){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	
}
