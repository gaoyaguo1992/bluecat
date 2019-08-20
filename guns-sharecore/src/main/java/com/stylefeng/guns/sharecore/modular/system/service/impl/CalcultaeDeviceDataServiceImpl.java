package com.stylefeng.guns.sharecore.modular.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.sharecore.modular.system.dao.CalculateDeviceDataModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.CalculateDeviceDataModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateBusinessDataBO;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateDeviceDataModel;
import com.stylefeng.guns.sharecore.modular.system.service.ICalcultaeDeviceDataService;

import me.chanjar.weixin.common.util.StringUtils;

@Service
public class CalcultaeDeviceDataServiceImpl extends
		ServiceImpl<CalculateDeviceDataModelMapper, CalculateDeviceDataModel> implements ICalcultaeDeviceDataService {

	@Autowired
	private CalculateDeviceDataModelBySelfMapper calculateDeviceDataModelBySelfMapper;

	/**
	 * 获取登陆用户的总运营数据
	 */
	@Override
	public Map<String, Object> totalDeviceBusinessData(HashMap<String, Object> queryParam) {
		Map<String, Object> resultMap = new HashMap<>();
		CalculateBusinessDataBO resultBO = calculateDeviceDataModelBySelfMapper.totalDeviceBusinessData(queryParam);
		// 今天的交易总金额，借出订单数，归还订单数
		// 以交易为维度做查询条件需要变字段名online_merchant_id 为 merchant_id
		String filter = queryParam.get("filter") == null ? null : (String) queryParam.get("filter");
		String queryParamForTradeTable = null;
		if (!StringUtils.isEmpty(filter)) {
			queryParamForTradeTable = filter.replace("online_merchant_id", "merchant_id");
		}
		queryParam.put("filter", queryParamForTradeTable);
		BigDecimal totalTradeAmountForToday = calculateDeviceDataModelBySelfMapper.totalTradeAmountForToday(queryParam);
		Long totalBorrowForToday = calculateDeviceDataModelBySelfMapper.totalBorrowForToday(queryParam);
		Long totalBackForToday = calculateDeviceDataModelBySelfMapper.totalBackForToday(queryParam);
		// 如果为null 值为0
		totalTradeAmountForToday = totalTradeAmountForToday == null ? new BigDecimal(0) : totalTradeAmountForToday;
		totalBorrowForToday = totalBorrowForToday == null ? 0L : totalBorrowForToday;
		totalBackForToday = totalBackForToday == null ? 0L : totalBackForToday;
		if (resultBO == null) {
			return resultMap;
		}
		// 今天之前的历史总交易金额
		BigDecimal beforeAmount = resultBO.getTotalAmount() == null ? new BigDecimal(0) : resultBO.getTotalAmount();
		// 今天之前的历史总结束次数
		Long historyFinishOrders = resultBO.getHistoryFinishOrders() == null ? 0L : resultBO.getHistoryFinishOrders();
		// 今天之前的历史总借出次数
		Long historyOrders = resultBO.getHistoryOrders() == null ? 0L : resultBO.getHistoryOrders();
		// 加上今天的交易金额
		resultBO.setTotalAmount(beforeAmount.add(totalTradeAmountForToday));
		resultBO.setHistoryFinishOrders(historyFinishOrders + totalBackForToday);
		resultBO.setHistoryOrders(historyOrders + totalBorrowForToday);
		resultMap.put("resultBO", resultBO);
		return resultMap;
	}

	/**
	 * 查条件中所有设备七天使用率
	 */
	@Override
	public Map<String, Object> totalDeviceUsageTrend(HashMap<String, Object> queryParam) {
		Map<String, Object> resultMap = new HashMap<>();
		CalculateBusinessDataBO resultBO = calculateDeviceDataModelBySelfMapper.totalDeviceUsageTrend(queryParam);
		// 最近七天时间封装到列表中，不包括今天，时间升序
		List<String> days = buildBefore7Days();
		// 把七天设备总使用率也封装到list中，升序
		List<BigDecimal> usageTrends = buildUsageTrends(resultBO);
		resultMap.put("days", days);
		resultMap.put("data", usageTrends);
		return resultMap;
	}

	/**
	 * 把七天设备总使用率也封装到list中，升序
	 * 
	 * @param resultBO
	 * @return
	 */
	private List<BigDecimal> buildUsageTrends(CalculateBusinessDataBO resultBO) {
		List<BigDecimal> usageTrends = new ArrayList<>();
		if (resultBO == null) {
			return usageTrends;
		}
		BigDecimal day7UsageTrend = resultBO.getDay7UsageTrend() == null ? new BigDecimal(0) : resultBO.getDay7UsageTrend().multiply(new BigDecimal(100));
		BigDecimal day6UsageTrend = resultBO.getDay6UsageTrend() == null ? new BigDecimal(0) : resultBO.getDay6UsageTrend().multiply(new BigDecimal(100));
		BigDecimal day5UsageTrend = resultBO.getDay5UsageTrend() == null ? new BigDecimal(0) : resultBO.getDay5UsageTrend().multiply(new BigDecimal(100));
		BigDecimal day4UsageTrend = resultBO.getDay4UsageTrend() == null ? new BigDecimal(0) : resultBO.getDay4UsageTrend().multiply(new BigDecimal(100));
		BigDecimal day3UsageTrend = resultBO.getDay3UsageTrend() == null ? new BigDecimal(0) : resultBO.getDay3UsageTrend().multiply(new BigDecimal(100));
		BigDecimal day2UsageTrend = resultBO.getDay2UsageTrend() == null ? new BigDecimal(0) : resultBO.getDay2UsageTrend().multiply(new BigDecimal(100));
		BigDecimal day1UsageTrend = resultBO.getDay1UsageTrend() == null ? new BigDecimal(0) : resultBO.getDay1UsageTrend().multiply(new BigDecimal(100));
		
		usageTrends.add(day7UsageTrend);
		usageTrends.add(day6UsageTrend);
		usageTrends.add(day5UsageTrend);
		usageTrends.add(day4UsageTrend);
		usageTrends.add(day3UsageTrend);
		usageTrends.add(day2UsageTrend);
		usageTrends.add(day1UsageTrend);
		return usageTrends;
	}

	/**
	 * 最近七天时间封装到列表中，不包括今天，时间升序
	 * 
	 * @return
	 */
	private List<String> buildBefore7Days() {
		List<String> days = new ArrayList<>();
		days.add(DateUtils.getOtherDate(new Date(), DateUtils.SETTLEMENT_FORMAT, -7));
		days.add(DateUtils.getOtherDate(new Date(), DateUtils.SETTLEMENT_FORMAT, -6));
		days.add(DateUtils.getOtherDate(new Date(), DateUtils.SETTLEMENT_FORMAT, -5));
		days.add(DateUtils.getOtherDate(new Date(), DateUtils.SETTLEMENT_FORMAT, -4));
		days.add(DateUtils.getOtherDate(new Date(), DateUtils.SETTLEMENT_FORMAT, -3));
		days.add(DateUtils.getOtherDate(new Date(), DateUtils.SETTLEMENT_FORMAT, -2));
		days.add(DateUtils.getOtherDate(new Date(), DateUtils.SETTLEMENT_FORMAT, -1));
		return days;
	}

	/**
	 * 登陆用户所有设备七天交易金额
	 */
	@Override
	public Map<String, Object> totalDeviceTradeAmount(HashMap<String, Object> queryParam) {
		Map<String, Object> resultMap = new HashMap<>();
		CalculateBusinessDataBO resultBO = calculateDeviceDataModelBySelfMapper.totalDeviceTradeAmount(queryParam);
		// 最近七天时间封装到列表中，不包括今天，时间升序
		List<String> days = buildBefore7Days();
		// 把七天设备总交易金额也封装到list中，升序
		List<BigDecimal> tradeAmounts = buildTradeAmount(resultBO);
		resultMap.put("days", days);
		resultMap.put("data", tradeAmounts);
		return resultMap;
	}

	private List<BigDecimal> buildTradeAmount(CalculateBusinessDataBO resultBO) {
		List<BigDecimal> tradeAmounts = new ArrayList<>();
		tradeAmounts.add(resultBO.getBeforeAmount7());
		tradeAmounts.add(resultBO.getBeforeAmount6());
		tradeAmounts.add(resultBO.getBeforeAmount5());
		tradeAmounts.add(resultBO.getBeforeAmount4());
		tradeAmounts.add(resultBO.getBeforeAmount3());
		tradeAmounts.add(resultBO.getBeforeAmount2());
		tradeAmounts.add(resultBO.getBeforeAmount1());
		return tradeAmounts;
	}

}
