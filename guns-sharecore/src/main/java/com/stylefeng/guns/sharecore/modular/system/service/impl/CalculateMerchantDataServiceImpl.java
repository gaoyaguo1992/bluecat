package com.stylefeng.guns.sharecore.modular.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.modular.system.dao.CalculateMerchantDataModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateMerchantDataModel;
import com.stylefeng.guns.sharecore.modular.system.model.ChargerFewDayNotUsedBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceNoUseOfMerchantBO;
import com.stylefeng.guns.sharecore.modular.system.service.ICalculateMerchantDataService;

@Service
public class CalculateMerchantDataServiceImpl
		extends ServiceImpl<CalculateMerchantDataModelMapper, CalculateMerchantDataModel>
		implements ICalculateMerchantDataService {

	@Autowired
	private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
	@Autowired
	private CalculateMerchantDataModelMapper calculateMerchantDataModelMapper;

	// 获取店铺七天使用率数据
	@Override
	public ResultCommandForBSTable<CalculateMerchantDataModel> list7daysUsageRate(HashMap<String, Object> pageFilter) {
		ResultCommandForBSTable<CalculateMerchantDataModel> result = new ResultCommandForBSTable<CalculateMerchantDataModel>();
		Long total = calculateMerchantDataModelMapper.countCalculateMerchantData(pageFilter);
		// 7天使用率和充电宝数量获取方法一样，只区别于排序方式
		pageFilter.put("orderBySelf", "device_7days_usage_rate");
		List<CalculateMerchantDataModel> resultList = calculateMerchantDataModelMapper
				.listCalculateMerchantData(pageFilter);
		result.setRows(resultList);
		result.setTotal(total);
		return result;
	}

	// 获取店铺充电器数量
	@Override
	public ResultCommandForBSTable<CalculateMerchantDataModel> listCalculateCharger(
			HashMap<String, Object> pageFilter) {
		ResultCommandForBSTable<CalculateMerchantDataModel> result = new ResultCommandForBSTable<CalculateMerchantDataModel>();
		Long total = calculateMerchantDataModelMapper.countCalculateMerchantData(pageFilter);
		// 7天使用率和充电宝数量获取方法一样，只区别于排序方式
		pageFilter.put("orderBySelf", "device_qty");
		List<CalculateMerchantDataModel> resultList = calculateMerchantDataModelMapper
				.listCalculateMerchantData(pageFilter);
		result.setRows(resultList);
		result.setTotal(total);
		return result;
	}

	/**
	 * 连续七天未使用商家列表
	 */
	@Override
	public ResultCommandForBSTable<DeviceNoUseOfMerchantBO> nouseByMerchantOf7Days(HashMap<String, Object> pageFilter) {
		ResultCommandForBSTable<DeviceNoUseOfMerchantBO> result = new ResultCommandForBSTable<DeviceNoUseOfMerchantBO>();
		Long noUseMerchantNumFor7 = shareDeviceInfoModelBySelfMapper.countMerchantBeyondDaysNotUseCharger(pageFilter);
		List<DeviceNoUseOfMerchantBO> resultList = calculateMerchantDataModelMapper.nouseByMerchantOf7Days(pageFilter);
		result.setRows(resultList);
		result.setTotal(noUseMerchantNumFor7);
		return result;
	}

	/**
	 * 七天或三十天未使用的充电器信息列表
	 */
	@Override
	public ResultCommandForBSTable<ChargerFewDayNotUsedBO> nousedChargerFewDays(HashMap<String, Object> pageFilter) {
		ResultCommandForBSTable<ChargerFewDayNotUsedBO> result = new ResultCommandForBSTable<ChargerFewDayNotUsedBO>();
		Long count = shareDeviceInfoModelBySelfMapper.countBeyondDaysNotUseCharger(pageFilter);
		List<ChargerFewDayNotUsedBO> resultList = shareDeviceInfoModelBySelfMapper.nousedChargerFewDays(pageFilter);
		result.setRows(resultList);
		result.setTotal(count);
		return result;
	}

}
