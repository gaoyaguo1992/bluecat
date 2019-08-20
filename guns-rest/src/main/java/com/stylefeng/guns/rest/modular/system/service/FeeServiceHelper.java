package com.stylefeng.guns.rest.modular.system.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;

@Service
public class FeeServiceHelper {	
	
	/**
	 * 
	 * <p>如果是商户借充电器并且当前没有借过充电器，那么免费充电!</p>
	 * 
	 * @param custInfoModel 当前租借客户
	 * @param merchantIdOfCharger 设备上的MerchantId
	 * @param chargerPrice 判断前的充电费用
	 * @return
	 */
	public BigDecimal getChargerPriceWhenMerchantLentCharger(CustInfoModel custInfoModel, Long merchantIdOfCharger,
			BigDecimal chargerPrice, Long chargerId) {
		return chargerPrice;
	}

	
}
