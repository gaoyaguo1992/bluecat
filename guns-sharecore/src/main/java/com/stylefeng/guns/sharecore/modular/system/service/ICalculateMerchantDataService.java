package com.stylefeng.guns.sharecore.modular.system.service;

import java.util.HashMap;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateMerchantDataModel;
import com.stylefeng.guns.sharecore.modular.system.model.ChargerFewDayNotUsedBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceNoUseOfMerchantBO;

public interface ICalculateMerchantDataService  extends IService<CalculateMerchantDataModel>{

	ResultCommandForBSTable<CalculateMerchantDataModel> list7daysUsageRate(HashMap<String, Object> pageFilter);
	
	ResultCommandForBSTable<CalculateMerchantDataModel> listCalculateCharger(HashMap<String, Object> pageFilter);

	ResultCommandForBSTable<DeviceNoUseOfMerchantBO> nouseByMerchantOf7Days(HashMap<String, Object> pageFilter);

	ResultCommandForBSTable<ChargerFewDayNotUsedBO> nousedChargerFewDays(HashMap<String, Object> pageFilter);

}
