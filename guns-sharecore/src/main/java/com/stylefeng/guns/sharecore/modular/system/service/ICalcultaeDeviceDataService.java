package com.stylefeng.guns.sharecore.modular.system.service;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateDeviceDataModel;

public interface ICalcultaeDeviceDataService extends IService<CalculateDeviceDataModel> {

	Map<String, Object> totalDeviceBusinessData(HashMap<String, Object> queryParam);

	Map<String, Object> totalDeviceUsageTrend(HashMap<String, Object> queryParam);

	Map<String, Object> totalDeviceTradeAmount(HashMap<String, Object> queryParam);

}
