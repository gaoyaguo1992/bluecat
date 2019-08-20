package com.stylefeng.guns.sharecore.modular.system.dao;

import java.math.BigDecimal;
import java.util.HashMap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateBusinessDataBO;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateDeviceDataModel;

public interface CalculateDeviceDataModelBySelfMapper{
	/**
	 * 获取登陆用户的总运营数据
	 * 
	 * @param filter 登陆用户数据权限
	 * @return
	 */
	CalculateBusinessDataBO totalDeviceBusinessData(HashMap<String, Object> queryParam);

	/**
	 * 查条件中所有设备七天使用率
	 * 
	 * @param filter
	 * @return
	 */
	CalculateBusinessDataBO totalDeviceUsageTrend(HashMap<String, Object> queryParam);

	/**
	 * 查询条件中今天的交易金额
	 * 
	 * @param filter
	 * @return
	 */
	BigDecimal totalTradeAmountForToday(HashMap<String, Object> queryParam);

	/**
	 * 查询条件中今天的借出次数
	 * 
	 * @param queryParam
	 * @return
	 */
	Long totalBorrowForToday(HashMap<String, Object> queryParam);

	/**
	 * 查询条件中今天的归还次数
	 * 
	 * @param queryParam
	 * @return
	 */
	Long totalBackForToday(HashMap<String, Object> queryParam);

	/**
	 * 查条件中所有设备七天交易金额
	 * @param queryParam
	 * @return
	 */
	CalculateBusinessDataBO totalDeviceTradeAmount(HashMap<String, Object> queryParam);
}