package com.stylefeng.guns.modular.wxApplication.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.core.model.OperatorTypeEnum;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.sharecore.modular.system.dao.DevTradeGatherInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.TaskRunInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.TaskRunInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.ShareTradeService;

/**
 *
 * 处理设备助手..
 *
 * @author alian li
 * @since 2019-01-20
 */
@Component
public class TaskJobsService {
	/**
	 * 写日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(TaskJobsService.class);

	/**
	 * 处理交易服务....
	 */
	@Autowired
	private ShareTradeService shareTradeService;

	
	/**
	 * 交易表对象..
	 */
	@Autowired
	private ShareTradeInfoModelBySelfMapper shareTradeInfoModelBySelfMapper;
	/**
	 * 处理数据操作类
	 */
	@Autowired
	private TaskRunInfoModelMapper taskRunInfoModelMapper;
	/**
	 *统计类。。。
	 */
	@Autowired
	private DevTradeGatherInfoModelBySelfMapper devTradeGatherInfoModelBySelfMapper;
	/**
	 * 交易处理。。。
	 */
	@Autowired
	@Qualifier("gunsadmin.TradeInfoHelperService")
	private TradeInfoHelperService tradeInfoHelperService;
	
	/**
	 * 定时任务.. 处理结束订单... //表示方法执行完成后1小时
	 */
	//@Scheduled(fixedDelay = 3600000)
	@Scheduled(cron="0 1 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * *")
	public void finishOrderPerHour() {
		Date now = new Date();
		logger.info("=======finishOrderPerHour开始执行结束订单任务，结束超期订单.. 表示方法执行完成后一小时执行一次.Date:{}.....", now);
		try {
			List<Long> listTradeIds = shareTradeInfoModelBySelfMapper.getMustBackTradeOrders();
			for (Long tradeId : listTradeIds) {
				try {
					shareTradeService.toBackByTradeId(tradeId);
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("=========finishOrderPerHour执行归还订单任务，归还订单失败Date:{}.tradeId:{}....", now,tradeId,e);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("==========finishOrderPerHour执行订单结束订单任务结束....表示方法执行完成后一小时执行一次.", e);
		}
		now = new Date();
		logger.info("======finishOrderPerHour执行订单结束订单任务结束....表示方法执行完成后一小时执行一次....Date:{}.....", now);
	}
	/**
	 * 统计根设备来统计交易数据，
	 */
	//@Scheduled(cron="0 1 5 * * *")
	@Scheduled(cron="0 1 7 * * *")
	public void callTradeDataForDevice() {
		Date now = new Date();
		logger.info("===========开始callTradeDataForDevice统计根设备来统计交易数据.Date:{}.....", now);
		try {
			//计算时间...
			Date calDate= now;// DateUtils.addDay(now, OperatorTypeEnum.SUBTRACT.getCode(), 1);
			logger.info("===========处理callTradeDataForDevice统计根设备来统计交易数据.Date:{}.....", calDate);
			//1.初始化设备.
			devTradeGatherInfoModelBySelfMapper.initCalculateDeviceData();
			logger.info("===========处理initCalculateDeviceData 1.初始化设备..Date:{}.....", calDate);
			//2. 统计设备订单
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt1(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt2(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt3(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt4(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt5(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt6(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalculateDeviceDataOrdersCnt7(calDate);
			logger.info("===========处理updateCalculateDeviceData 2. 统计设备订单..Date:{}.....", calDate);
			//3. 统计完成订单
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish1(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish2(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish3(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish4(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish5(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish6(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForFinish7(calDate);
			logger.info("===========处理updateCalDeviceDataForFinish 3. 统计完成订单..Date:{}.....", calDate);
			//4. 完成
			devTradeGatherInfoModelBySelfMapper.updateCalDeviceDataForUpdateDate(calDate);
			logger.info("===========处理updateCalDeviceDataForUpdateDate 4. 完成..Date:{}.....", calDate);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========callTradeDataForDevice统计根设备来统计交易数据.异常......", e);
		}
		now = new Date();
		logger.info("========结束callTradeDataForDevice统计根设备来统计交易数据....Date:{}.....", now);
	}
	/**
	 * 统计商户交易数据
	 */
	//@Scheduled(cron="0 3 0 * * *")
	@Scheduled(cron="0 1 6 * * *")
	public void callTradeDataForMerchant() {
		Date now = new Date();
		logger.info("===========开始callTradeDataForMerchant统计根设备来统计交易数据.Date:{}.....", now);
		try {
			//计算时间...
			Date calDate= now; //DateUtils.addDay(now, OperatorTypeEnum.SUBTRACT.getCode(), 1);
			logger.info("===========处理callTradeDataForMerchant统计根设备来统计交易数据.Date:{}.....", calDate);
			//1.初始化商户.
			devTradeGatherInfoModelBySelfMapper.initCalculateMerchantData();
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtForUpdateDateQty(calDate);
			logger.info("===========处理initCalculateMerchantData 1.初始化设备..Date:{}.....", calDate);
			//2. 统计商户一级
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAg1ForUpdateDateAmount(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAg1ForUpdateDateCNT7(calDate);			
			logger.info("===========处理updateCalMerDtAg1ForUpdateDate 2. 统计商户一级..Date:{}.....", calDate);
			//3. 统计商户二级
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAg2ForUpdateDateAmount(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAg2ForUpdateDateCNT7(calDate);
			logger.info("===========处理updateCalMerDtAg2ForUpdateDate 3. 统计商户二级..Date:{}.....", calDate);
			//4. 统计商户三级
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAg3ForUpdateDateAmount(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAg3ForUpdateDateCNT7(calDate);
			logger.info("===========处理updateCalMerDtAg3ForUpdateDate 4. 统计商户三级..Date:{}.....", calDate);
			//5. 统计商户铺货
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtKeeperForUpdateDateAmount(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtKeeperForUpdateDateCNT7(calDate);
			logger.info("===========处理updateCalMerDtKeeperForUpdateDate 5.统计商户铺货..Date:{}.....", calDate);
			//6. 统计商户加盟
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAlliForUpdateDateAmount(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtAlliForUpdateDateCNT7(calDate);
			logger.info("===========处理updateCalMerDtAlliForUpdateDate 6. 统计商户加盟..Date:{}.....", calDate);
			//7. 统计商户店铺
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtMerForUpdateDateAmount(calDate);
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtMerForUpdateDateCNT7(calDate);
			logger.info("===========处理updateCalMerDtMerForUpdateDate 7. 统计商户店铺..Date:{}.....", calDate);
			//8. 使率..
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtFor7daysUsageRate();
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtFor7daysUsageRate0();
			logger.info("===========处理updateCalMerDtFor7daysUsageRate updateCalMerDtFor7daysUsageRate0 8. 使率...Date:{}.....", calDate);
			//8. 完成
			devTradeGatherInfoModelBySelfMapper.updateCalMerDtForUpdateDate(calDate);
			logger.info("===========处理updateCalMerDtForUpdateDate 7. 完成..Date:{}.....", calDate);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("===========异常callTradeDataForMerchant统计根设备来统计交易数据.异常......", e);
		}
		now = new Date();
		logger.info("===========结束callTradeDataForMerchant统计根设备来统计交易数据....Date:{}.....", now);
	}
	
	/**
	 * 对交易数据向代理商分润
	 */
	@Scheduled(cron="0 10 5 * * *")
	public void benefitTradeData() {
		Date now = new Date();
		logger.info("===========开始benefitTradeData进行分润.Date:{}.....", now);
		try {
			List<Long> listTradeIds = shareTradeInfoModelBySelfMapper.getMustBenefitTradeOrders();
			//测试...			
			//shareTradeService.tradeBenefitByTradeId(1000000028L);
			for (Long tradeId : listTradeIds) {
				try {
					shareTradeService.tradeBenefitByTradeId(tradeId);
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("=========finishOrderPerHour执行归还订单任务，归还订单失败Date:{}.tradeId:{}....", now,tradeId,e);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("========分润异常benefitTradeData进行分润......", e);
		}
		now = new Date();
		logger.info("========结束开始benefitTradeData进行分润...Date:{}.....", now);
	}
}
