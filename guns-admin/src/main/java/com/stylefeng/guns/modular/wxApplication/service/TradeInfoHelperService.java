package com.stylefeng.guns.modular.wxApplication.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.core.util.DatetimeUtil;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.DevTradeGatherInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantOrderPagesQueryBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantOrderRecordInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantOrderRecordPageInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTrdTotalInfo;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;
import com.stylefeng.guns.sharecore.modular.system.service.ShareTradeService;

/**
 *
 * 交易订单相关查询...
 *
 * @author alian li
 * @since 2019-01-20
 */
@Service("gunsadmin.TradeInfoHelperService")
public class TradeInfoHelperService extends BaseService {
	/**
	 * 写日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(TradeInfoHelperService.class);
	/**
	 * 操作..
	 */
	@Autowired
	private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
    /**
	 * 查询交易...
	 */
	@Autowired
	private ShareTradeInfoModelBySelfMapper shareTradeInfoModelBySelfMapper;

	@Autowired
	private ShareTradeInfoModelMapper shareTradeInfoModelMapper;
	/**
	 * 共享充电服务。。。
	 */
	@Autowired
	private ShareTradeService shareTradeService;
	/**
	 * 统计交易数据...
	 */
	@Autowired
	private DevTradeGatherInfoModelBySelfMapper devTradeGatherInfoModelBySelfMapper;

	/**
	 * 
	 * <p>
	 * 查询商户订单组装查询条件
	 * </p>
	 * 
	 * @param queryBO
	 * @param mi
	 * @return
	 */
	private HashMap<String, Object> getMerchantOrderDataPack(MerchantOrderPagesQueryBO queryBO, MerchantInfoModel mi) {

		// TODO 后续有其他限定条件的话请在paramMap中添加
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		/***********************************
		 * 判断当前商户类型
		 ***************************************/
		Long merchantId = mi.getId();
		Integer merchantType = mi.getMerchantType().intValue();
		if (merchantType.equals(MerchantTypeEnum.DAI_LI_SHANG1.getCode())) {
			// 顶级代理商
			paramMap.put("agents1Id", merchantId);
		} else if (merchantType.equals(MerchantTypeEnum.DAI_LI_SHANG2.getCode())) {
			// 1级
			paramMap.put("agents2Id", merchantId);
		} else if (merchantType.equals(MerchantTypeEnum.DAI_LI_SHANG3.getCode())) {
			// 1级
			paramMap.put("agents3Id", merchantId);
		} else if (merchantType.equals(MerchantTypeEnum.PUHUO_SHANG.getCode())) {
			// 铺货商
			paramMap.put("shopkeeperId", merchantId);
		} else if (merchantType.equals(MerchantTypeEnum.JIA_MENG_SHANG.getCode())) {
			// 加腽商
			paramMap.put("allianceBusinessId", merchantId);
		} else {
			// 终端商户
			paramMap.put("merchantId", merchantId);
		}
		if (queryBO.getStatus() != null) {
			paramMap.put("tradeStatus", queryBO.getStatus());
		}
		// 设备号
		if (queryBO.getDeviceId() != null) {
			paramMap.put("deviceId", queryBO.getDeviceId());
		}
		// 处理查询时间
		Date now = new Date();
		Date tomorrow = DatetimeUtil.addDayForDate(now, 1);
		if (queryBO.getEndDateTime() == null) {
			queryBO.setEndDateTime(DatetimeUtil.formatDate(tomorrow));
		}
		if (queryBO.getBeginDateTime() == null) {
			queryBO.setBeginDateTime(DatetimeUtil.formatDate(now));
		}

		Date date120 = DatetimeUtil
				.addDayForDate(DatetimeUtil.parse(queryBO.getBeginDateTime(), DatetimeUtil.STANDARD_DATE_PATTERN), 120);
		int a = DateUtils.compare_date(DatetimeUtil.formatDate(date120), queryBO.getEndDateTime(), "yyyy-MM-dd");
		if (a == -1) {
			throw new ExgrainBizUncheckedException("开始时间与结束时间之间不能大于120天!");
		}
		paramMap.put("beginDateTime", queryBO.getBeginDateTime());
		paramMap.put("endDateTime", queryBO.getEndDateTime());
		// 1.4将分页起始结束位置放入map中
		paramMap.put("start", queryBO.getStart());
		paramMap.put("rows", queryBO.getRows());

		return paramMap;
	}

	/**
	 * 
	 * <p>
	 * 根据订单状态组装数据
	 * </p>
	 * 
	 * @param tradeInfoModel
	 * @param myOrderRecordInfoBO
	 * @author
	 * @throws ParseException
     */
	private void merchantOrderStatusDataPack(ShareTradeInfoBySelfModel tradeInfoModel,
			MerchantOrderRecordInfoBO merchantOrderRecordInfoBO, MerchantInfoModel merchantInfoModel) {
		// 设备号
		merchantOrderRecordInfoBO.setDeviceId(tradeInfoModel.getDeviceNo());
		// 充电器号
		merchantOrderRecordInfoBO.setChargerId(tradeInfoModel.getChargerId());
		// 订单编号
		merchantOrderRecordInfoBO.setOrderId(tradeInfoModel.getId());
		// 订单创建时间格式化
		merchantOrderRecordInfoBO.setCreateTimeFormat(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tradeInfoModel.getCreateDatetime()));
		// 客户编号
		merchantOrderRecordInfoBO.setCustNo(tradeInfoModel.getCustId());
		// 交易名称
		merchantOrderRecordInfoBO
				.setTradeName(tradeInfoModel.getTradeName() == null ? "" : tradeInfoModel.getTradeName());
		// 归还时间去null
		merchantOrderRecordInfoBO.setBackTimeFormat("");
		// 交易金额去null
		merchantOrderRecordInfoBO.setTradeAmount("");
		// 预付金去null
		merchantOrderRecordInfoBO.setMyOrderStatusCn(tradeInfoModel.getTradeStatusName());
		merchantOrderRecordInfoBO
				.setDeposit((tradeInfoModel.getYfjAmount() == null) ? "" : tradeInfoModel.getYfjAmount().toString());
		if (tradeInfoModel.getTradeAmount() == null) {
			merchantOrderRecordInfoBO.setTradeAmount("￥0");
		} else {
			merchantOrderRecordInfoBO.setTradeAmount("￥" + tradeInfoModel.getTradeAmount().toString());
		}

		// 对于交易状态为成功的订单，目前只有直接付款交易，所以特殊处理
		if (tradeInfoModel.getTradeStatus().intValue() == ShareTradeStatusEnum.TradingUsing.getCode()) {

			Map<String, Object> resultMap = shareTradeService.calculationUseAmountAndUseTime(tradeInfoModel,
					tradeInfoModel.getShareTradeDeviceInfoModel());
			merchantOrderRecordInfoBO.setTradeAmount("￥" + resultMap.get("useAmount").toString());
			// 计算费用。。
			merchantOrderRecordInfoBO.setMyOrderStatus(ShareTradeStatusEnum.TradingUsing.getCode());
			merchantOrderRecordInfoBO.setMyOrderStatusCn(ShareTradeStatusEnum.TradingUsing.getDesc());
			// 使用中的订单
		} else if (tradeInfoModel.getTradeStatus().intValue() == ShareTradeStatusEnum.Finish.getCode()) {
			// 使用中
			merchantOrderRecordInfoBO.setMyOrderStatus(ShareTradeStatusEnum.Finish.getCode());
			merchantOrderRecordInfoBO.setMyOrderStatusCn(ShareTradeStatusEnum.Finish.getDesc());
			// 订单归还时间格式化
			try {
				merchantOrderRecordInfoBO.setBackTimeFormat(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tradeInfoModel.getBackDatetime()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			// 失败的订单
		} else {
			// 失败
			merchantOrderRecordInfoBO.setMyOrderStatus(ShareTradeStatusEnum.UNKNOW.getCode());
			merchantOrderRecordInfoBO.setMyOrderStatusCn(ShareTradeStatusEnum.UNKNOW.getDesc());
		}
		// 订单设备信息
		ShareDeviceInfoModel deviceInfoModel = shareDeviceInfoModelMapper
				.selectByPrimaryKey(tradeInfoModel.getDeviceNo());
		merchantOrderRecordInfoBO.setShareDeviceInfoModel(deviceInfoModel);
	}

	/**
	 * 获取商户订单信息
	 * 
	 * @param custModel
	 * @param merchantModel
	 * @param di
	 * @param latitude
	 * @param longitude
	 * @throws Exception
	 */
	public MerchantOrderRecordPageInfoBO merchantOrderPagesQuery(CustInfoModel custInfoModl,
			MerchantInfoModel merchantInfoModel, MerchantOrderPagesQueryBO merchantOrderPagesQueryBO) {
		MerchantOrderRecordPageInfoBO merchantOrderRecordPageInfoBO = new MerchantOrderRecordPageInfoBO();
		List<MerchantOrderRecordInfoBO> merchantOrderRecordInfoBOs = new ArrayList<MerchantOrderRecordInfoBO>();
		List<ShareTradeInfoBySelfModel> merchantOrders = new ArrayList<>();
		logger.info("获取商户的所有订单信息[service层][merchantOrderPagesQuery方法]入参{},{}", merchantOrderPagesQueryBO,
				merchantInfoModel);
		// 1. 查询数据库...
		// 组装查询条件
		HashMap<String, Object> queryContent = getMerchantOrderDataPack(merchantOrderPagesQueryBO, merchantInfoModel);
		merchantOrders = shareTradeInfoModelBySelfMapper.selectMerchantOrdersForPageByFilter(queryContent);
		ShareTrdTotalInfo tti = shareTradeInfoModelBySelfMapper.countMerchantOrdersForPageByFilter(queryContent);
		// 交易金额
		merchantOrderRecordPageInfoBO.setTradeTotalAmount(tti.getTotalTradeAmt());
		// 交易笔数=非冲正交易总笔数+冲正交易总笔数
		merchantOrderRecordPageInfoBO.setTradeTotalCount(tti.getTradeCount());
		// 2.
		try {
			MerchantOrderRecordInfoBO merchantOrderRecordInfoBO =null;
			//将查询的数据进行判断组装
			if (merchantOrders != null && merchantOrders.size() > 0) {
				for (ShareTradeInfoBySelfModel tradeInfoModel : merchantOrders) {
					try {
						merchantOrderRecordInfoBO = new MerchantOrderRecordInfoBO();
						merchantOrderStatusDataPack(tradeInfoModel, merchantOrderRecordInfoBO, merchantInfoModel);
						if (merchantOrderPagesQueryBO.getStatus() != null) {
							if (merchantOrderRecordInfoBO.getMyOrderStatus().intValue() != merchantOrderPagesQueryBO
									.getStatus().intValue()) {
								continue;
							}
						}
						merchantOrderRecordInfoBOs.add(merchantOrderRecordInfoBO);
					} catch (Exception e) {
						logger.warn("tradeId:" + tradeInfoModel.getId() + ",交易类型:数据异常，直接跳过不展示", e);
						continue;
					}
				}
			}
			// 当前商户具体信息
			merchantOrderRecordPageInfoBO.setMerchantInfoModel(merchantInfoModel);

			merchantOrderRecordPageInfoBO.setMerchantOrderRecordInfoBOs(merchantOrderRecordInfoBOs);
			merchantOrderRecordPageInfoBO.setResult("success");
		} catch (Exception e) {
			logger.error("商户的订单分页查询异常:{}", e);
			merchantOrderRecordPageInfoBO.setResult("fail");
		}
		logger.info("获取商户的所有订单信息[service层][merchantOrderPagesQuery方法]出参,merchantOrderRecordPageInfoBO:{}",
				merchantOrderRecordPageInfoBO);
		return merchantOrderRecordPageInfoBO;
	}



	/**
	 * 根据订单号获取订单详细信息
	 * 
	 * @param orderId
	 * @return
     */
	public MerchantOrderRecordInfoBO getShowOrderDetail(String orderId) {
		MerchantOrderRecordInfoBO orderBO = new MerchantOrderRecordInfoBO();
		Long orderIdLong = Long.valueOf(orderId);
		ShareTradeInfoModel shareTradeInfoModel = shareTradeInfoModelMapper.selectByPrimaryKey(orderIdLong);
		if (shareTradeInfoModel == null) {
			return null;
		}
		// 设备信息
		ShareDeviceInfoModel deviceInfo = shareDeviceInfoModelMapper
				.selectByPrimaryKey(shareTradeInfoModel.getDeviceNo());
		orderBO.setShareDeviceInfoModel(deviceInfo);
		// 封装交易信息
		orderBO.setOrderId(shareTradeInfoModel.getId());
		orderBO.setCustNo(shareTradeInfoModel.getCustId());
		orderBO.setDeviceId(shareTradeInfoModel.getDeviceNo());
		orderBO.setChargerId(shareTradeInfoModel.getChargerId());
		orderBO.setTradeAmount(
				shareTradeInfoModel.getTradeAmount() == null ? "0" : shareTradeInfoModel.getTradeAmount().toString());
		orderBO.setDeposit(
				shareTradeInfoModel.getYfjAmount() == null ? "0" : shareTradeInfoModel.getYfjAmount().toString());
		orderBO.setTradeName(shareTradeInfoModel.getTradeName());
		orderBO.setCreateTime(shareTradeInfoModel.getCreateDatetime());
		orderBO.setBackTime(shareTradeInfoModel.getBackDatetime());
		orderBO.setCreateTimeFormat(DateUtils.formatDate(shareTradeInfoModel.getCreateDatetime(), DateUtils.NORMAL_FORMAT));
		orderBO.setBackTimeFormat(DateUtils.formatDate(shareTradeInfoModel.getBackDatetime(), DateUtils.NORMAL_FORMAT));
		orderBO.setChargerId(shareTradeInfoModel.getChargerId());
		orderBO.setOrderStatus(shareTradeInfoModel.getTradeStatus());
		orderBO.setOrderStatusCn(shareTradeInfoModel.getTradeStatusName());
		return orderBO;
	}

}
