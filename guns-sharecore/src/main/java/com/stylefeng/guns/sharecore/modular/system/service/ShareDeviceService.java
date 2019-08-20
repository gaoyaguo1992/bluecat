package com.stylefeng.guns.sharecore.modular.system.service;

import java.math.BigDecimal;
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

import com.stylefeng.guns.core.util.DatetimeUtil;
import com.stylefeng.guns.sharecore.common.persistence.model.ChargerBeyondSevenDaysNotUseInfoBO;
import com.stylefeng.guns.sharecore.common.persistence.model.ChargerBeyondSevenDaysNotUsePageInfoBO;
import com.stylefeng.guns.sharecore.common.persistence.model.MapMarkersBO;
import com.stylefeng.guns.sharecore.common.persistence.model.MyDeviceAndChargerBO;
import com.stylefeng.guns.sharecore.common.persistence.model.MyDeviceInfoBO;
import com.stylefeng.guns.sharecore.common.persistence.model.MyIncomeDetailInfoBO;
import com.stylefeng.guns.sharecore.common.persistence.model.MyIncomeDetailPageInfoBO;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.DevTradeGatherInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawOrderInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.BatchEditFeeCommand;
import com.stylefeng.guns.sharecore.modular.system.model.BatchEditProfitCommand;
import com.stylefeng.guns.sharecore.modular.system.model.ChargerBeyondSevenDaysNotUseRequestCommand;
import com.stylefeng.guns.sharecore.modular.system.model.DevTradeGatherInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceChargerGroup;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceTotalBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceTradeInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceUsageTrendBO;
import com.stylefeng.guns.sharecore.modular.system.model.FeeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.LastSevenDaysBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantProfitQueryBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.MyDevicePageInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.MyTradeDetailInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.MyTradeDetailPageInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTrdTotalInfo;

import me.chanjar.weixin.common.util.StringUtils;

/**
 * 处理设备相关信息的服务..
 * 
 * @author Alan.huang
 *
 */
@Service
public class ShareDeviceService extends com.stylefeng.guns.sharecore.modular.system.service.BaseService {
	/**
	 * 日志处理类。。
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 处理费用
	 */
	@Autowired
	private ShareFeeService shareFeeService;
	/**
	 * 交易表查询...
	 */
	@Autowired
	private WithdrawOrderInfoModelMapper tradeInfoModelMapper;

	/**
	 * 设备信息处理类
	 */
	@Autowired
	private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;

	/**
	 * 昨天晚天交易数据...
	 */
	@Autowired
	private DevTradeGatherInfoModelBySelfMapper devTradeGatherInfoModelBySelfMapper;
	/**
	 * 充电器处理...
	 */
	@Autowired
	private ShareChargerModelBySelfMapper shareChargerModelBySelfMapper;
	/**
	 * 交易数据操作对象
	 */
	@Autowired
	private ShareTradeInfoModelBySelfMapper shareTradeInfoModelBySelfMapper;

	/**
	 * 根据过虑条件查询设备信息。。。
	 * 
	 * @param dtb
	 * @return
	 */
	public MyDevicePageInfoBO getMyDevice(DeviceTotalBO dtb) {
		MyDevicePageInfoBO mdpib = new MyDevicePageInfoBO();
		try {
			MyDeviceAndChargerBO myTotalNum = null;
			mdpib.setMyTotalNum(myTotalNum);
			// 1.1 处理过虑条件..
			HashMap<String, Object> pageFilter = new HashMap<>();
			if (dtb != null && dtb.getDeviceId() != null && !dtb.getDeviceId().equals(new Integer("0"))) {
				pageFilter.put("id", dtb.getDeviceId());
			}
			if (dtb != null && dtb.getStatus() != null && !dtb.getStatus().equals(new Integer("0"))) {
				pageFilter.put("deviceStatus", dtb.getStatus());
			}

			if (dtb.getMerchantType().equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
				// 代理商顶级
				pageFilter.put("agents1Id", dtb.getAgentsId());
			} else if (dtb.getMerchantType().equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
				// 代理商1
				pageFilter.put("agents2Id", dtb.getFirstLevelAgentsId());
			} else if (dtb.getMerchantType().equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
				// 代理商2
				pageFilter.put("agents3Id", dtb.getSecondLevelAgentsId());
			} else if (dtb.getMerchantType().equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
				// 铺货商。。。
				pageFilter.put("shopkeeperId", dtb.getShopKeeperId());
			} else if (dtb.getMerchantType().equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
				// 加盟商
				pageFilter.put("allianceBusinessId", dtb.getFranchiseeId());
			} else if (dtb.getMerchantType().equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
				// 终端...
				pageFilter.put("onlineMerchantId", dtb.getMerchantId());
			}
			pageFilter.put("offset", dtb.getStartIndex());
			pageFilter.put("limit", dtb.getRows());
			pageFilter.put("order", "create_datetime");
			logger.info("查询设备...{}", pageFilter);
			// 2 获取商户名下所有设备
			List<MyDeviceInfoBO> list = new ArrayList<>();
			List<ShareDeviceInfoBySelfModel> dms = shareDeviceInfoModelBySelfMapper
					.selectByConditionForPage(pageFilter);
			if (null == dms || dms.size() <= 0) {
				myTotalNum = new MyDeviceAndChargerBO();
				myTotalNum.setDevNum(0);
				myTotalNum.setChargerNum(0);
				mdpib.setMyTotalNum(myTotalNum);
				mdpib.setMyDeviceInfoBOs(list);
				mdpib.setResult("success");
			} else {
				myTotalNum = new MyDeviceAndChargerBO();
				Long deviceNum = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
				Long chargerNum = shareDeviceInfoModelBySelfMapper.countDeviceChargersByConditionForPage(pageFilter);
				myTotalNum.setChargerNum(chargerNum.intValue());
				myTotalNum.setDevNum(deviceNum.intValue());
				mdpib.setMyTotalNum(myTotalNum);

				// 根据什么商户类型查询
				String queryMerchant = "", feeDes = "";
				DevTradeGatherInfoModel devTradeGatherInfoModel;
				MyDeviceInfoBO myDeviceInfoBo = null;
				DeviceChargerGroup deviceChargerGroup = null;
				Long queryMerchantId = 0L;
				Integer yesterdayNum, beforeYesterday;
				for (ShareDeviceInfoModel bo : dms) {
					myDeviceInfoBo = new MyDeviceInfoBO();
					myDeviceInfoBo.setOppMerchantId(dtb.getQueryMerchantId());
					list.add(myDeviceInfoBo);
					myDeviceInfoBo.setAddr(bo.getOnlineAddress());
					myDeviceInfoBo.setCity(bo.getOnlineCity());
					myDeviceInfoBo.setDevDesc(bo.getDeviceTypeName());
					myDeviceInfoBo.setDevType(bo.getDeviceTypeId());
					myDeviceInfoBo.setId(bo.getId());
					myDeviceInfoBo.setProvince(bo.getOnlineProvince());
					myDeviceInfoBo.setStatus(bo.getDeviceStatus());
					myDeviceInfoBo.setStatusCn(ShareDeviceStatusEnum.getDesc(bo.getDeviceStatus()));
					myDeviceInfoBo.setZone(bo.getOnlineZone());

					myDeviceInfoBo.setMerchantId(bo.getOnlineMerchantId());
					myDeviceInfoBo.setMerchantCn(bo.getOnlineMerchantCn());
					myDeviceInfoBo.setAgents1Cn(bo.getAgents1Cn());
					myDeviceInfoBo.setAgents1Id(bo.getAgents1Id());
					myDeviceInfoBo.setAgents2Cn(bo.getAgents2Cn());
					myDeviceInfoBo.setAgents2Id(bo.getAgents2Id());
					myDeviceInfoBo.setAgents3Cn(bo.getAgents3Cn());
					myDeviceInfoBo.setAgents3Id(bo.getAgents3Id());
					myDeviceInfoBo.setShopkeeperCn(bo.getShopkeeperCn());
					myDeviceInfoBo.setShopkeeperId(bo.getShopkeeperId());
					myDeviceInfoBo.setAllianceBusinessCn(bo.getAllianceBusinessCn());
					myDeviceInfoBo.setAllianceBusinessId(bo.getAllianceBusinessId());

					// 2 获取该设备的历史交易次数
					// 2.1昨天之前的交易数据
					devTradeGatherInfoModel =
							devTradeGatherInfoModelBySelfMapper.selectByDeviceId(bo.getId());
					// 2.2当前的交易数据
					Integer countByToday = shareTradeInfoModelBySelfMapper.queryCurrentCountByDeviceId(bo.getId());
					countByToday = countByToday == null ? 0 : countByToday;
					Integer hisTradeCount = devTradeGatherInfoModel == null ? 0 : devTradeGatherInfoModel.getHisTradeCount();
					hisTradeCount = hisTradeCount == null ? 0 : hisTradeCount;
					long tradeNum = countByToday + hisTradeCount;
					myDeviceInfoBo.setTradeNum(tradeNum);
					// 3 获取设备对应的收费信息 // 是否可设置费用
					feeDes = shareFeeService.getFeeDescByDeviceInfoModel(bo);
					myDeviceInfoBo.setFlag(true);
					myDeviceInfoBo.setDeviceFeeDesc(feeDes);
					// 4. 查询设备充电器数据..
					deviceChargerGroup = shareChargerModelBySelfMapper.getDeviceChargersGroupByDeviceNo(bo.getId());
					if (deviceChargerGroup != null) {
						myDeviceInfoBo.setChargerNum(deviceChargerGroup.getCountChargers());
						myDeviceInfoBo.setChargingStr(deviceChargerGroup.getChargers());
					}
					// 5. 非终端商户显示使用率趋势
					if (!dtb.getMerchantType().equals(MerchantTypeEnum.TERMINAL.getCode())) {
						// 交易要按不同商户类型查询
						// 要查询的商户编号
						queryMerchantId = 0L;
						// 1.要查询代理商的使用率数据
						if (dtb.getAgentsId() != null) {
							queryMerchantId = dtb.getAgentsId();
							queryMerchant = "topAgentsQuery";
							// 2.要查询一级代理商的使用率数据
						} else if (dtb.getFirstLevelAgentsId() != null) {
							queryMerchantId = dtb.getFirstLevelAgentsId();
							queryMerchant = "firstLevelAgentsQuery";
							// 3.要查询二级代理商的使用率数据
						} else if (dtb.getSecondLevelAgentsId() != null) {
							queryMerchantId = dtb.getSecondLevelAgentsId();
							queryMerchant = "secondLevelAgentsQuery";
							// 4.要查询铺货商的使用率数据
						} else if (dtb.getShopKeeperId() != null) {
							queryMerchantId = dtb.getShopKeeperId();
							queryMerchant = "shopkeeperQuery";
							// 5.要查询加盟商的使用率数据
						} else if (dtb.getFranchiseeId() != null) {
							queryMerchantId = dtb.getFranchiseeId();
							queryMerchant = "franchiseeQuery";
						}

						yesterdayNum = tradeInfoModelMapper.getTradeNumByDate(DatetimeUtil.yesterdayYyyyMMdd(),
								bo.getId(), queryMerchant, queryMerchantId);
						beforeYesterday = tradeInfoModelMapper.getTradeNumByDate(DatetimeUtil.beforeYesterdayYyyyMMdd(),
								bo.getId(), queryMerchant, queryMerchantId);
						if (yesterdayNum > beforeYesterday) {// 上升
							myDeviceInfoBo.setTrend(new Integer(1));
						} else if (yesterdayNum < beforeYesterday) {// 下降
							myDeviceInfoBo.setTrend(new Integer(2));
						} else {// 相等
							myDeviceInfoBo.setTrend(new Integer(0));
						}
					}
				}
				mdpib.setMyDeviceInfoBOs(list);
				mdpib.setResult("success");
			}

		} catch (Exception e) {
			logger.error("", e);
			mdpib.setResult("fail");
		}
		return mdpib;
	}

	/**
	 * 交易数据......
	 * 
	 * @param mpq
	 * @return
     */
	public MyTradeDetailPageInfoBO myTradeDatas(MerchantProfitQueryBO mpq, MerchantInfoModel model) {
		logger.info("查询商户收益信息分页显示入参:{}", mpq);
		// 1. 整理参数
		Long merchantId = mpq.getMerchantId();
		HashMap<String, Object> paramMap = new HashMap<>();
        String merchantType = String.valueOf(model.getMerchantType());
		if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
			// 当前商户是代理商
			paramMap.put("agents1Id", merchantId);
		} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
			// 当前商户是加盟商
			paramMap.put("allianceBusinessId", merchantId);
		} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
			// 当前商户是终端商户
			paramMap.put("merchantId", merchantId);
		} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
			// 当前商户是铺货商
			paramMap.put("shopkeeperId", merchantId);
		} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
			// 一级代理商
			paramMap.put("agents2Id", merchantId);
		} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
			// 二级代理商
			paramMap.put("agents3Id", merchantId);
		}
		paramMap.put("start", mpq.getStart());
		paramMap.put("rows", mpq.getRows());
		paramMap.put("beginBackDateTime", mpq.getBeginDateTime());
		paramMap.put("endBackDateTime", mpq.getEndDateTime());
		logger.info("查询商户收益信息实际入参:{}", paramMap);
		// 2. 数据库查询数据.
		List<ShareTradeInfoBySelfModel> list = shareTradeInfoModelBySelfMapper
				.selectMerchantOrdersForPageByFilter(paramMap);
		ShareTrdTotalInfo ShareTrdTotalInfo = shareTradeInfoModelBySelfMapper.countMerchantOrdersForPageByFilter(paramMap);
		// 3. 返回...
		MyTradeDetailPageInfoBO result = new MyTradeDetailPageInfoBO();
		result.setResult(ResultCommandBaseObject.SUCCESS);
		if (ShareTrdTotalInfo != null) {
			result.setTradeAmount(ShareTrdTotalInfo.getTotalTradeAmt());
			result.setTradeCount(ShareTrdTotalInfo.getTradeCount());
			if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
				// 顶级代理商
				result.setProfitAmount(ShareTrdTotalInfo.getAgents1Amount());
			} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
				// 一级代理商
				result.setProfitAmount(ShareTrdTotalInfo.getAgents2Amount());
			} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
				// 二级代理商
				result.setProfitAmount(ShareTrdTotalInfo.getAgents3Amount());
			} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
				// 当前商户是加盟商
				result.setProfitAmount(ShareTrdTotalInfo.getAllianceBusinessAmount());
			} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
				// 当前商户是终端商户
				result.setProfitAmount(ShareTrdTotalInfo.getTotalTradeAmt());
			} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
				// 当前商户是铺货商
				result.setProfitAmount(ShareTrdTotalInfo.getShopkeeperAmount());
			}
		}
		// 3.2 处理明细...
		if (list != null && list.size() > 0) {
			List<MyTradeDetailInfoBO> detailList = result.getMyTradeDetailInfoBOs();
			MyTradeDetailInfoBO bo = null;
			for (ShareTradeInfoBySelfModel td : list) {
				bo = new MyTradeDetailInfoBO();
				detailList.add(bo);
				bo.setAgentsId(td.getAgents1Id());
				bo.setAmount(td.getTradeAmount());
				bo.setBackTime(td.getBackDatetime());
				bo.setBackTimeFromat(DatetimeUtil.formatDateTime(td.getBackDatetime()));
				bo.setBorrowTime(td.getBorrowDatetime());
				bo.setBorrowTimeFormat(DatetimeUtil.formatDateTime(td.getBorrowDatetime()));
				bo.setChargerId(td.getChargerId());
				bo.setCreateTime(td.getCreateDatetime());
				bo.setCreateTimeFormat(DatetimeUtil.formatDate(td.getCreateDatetime()));
				bo.setCustNo(td.getCustId());
				bo.setDeviceId(td.getDeviceNo());
				bo.setFirstLevelAgentsId(td.getAgents2Id());
				bo.setFranchiseeId(td.getAllianceBusinessId());
				bo.setMerchantId(td.getMerchantId());
				bo.setOperateComment(td.getRemark());
				bo.setRefTradeId(td.getBackTradeId());
				bo.setSecondLevelAgentsId(td.getAgents3Id());
				bo.setShopkeeperId(td.getShopkeeperId());
				bo.setTradeAmount(td.getTradeAmount() != null ? td.getTradeAmount().toString() : "");
				bo.setTradeId(td.getId());
				bo.setTradeName(td.getTradeName());
				bo.setTradeStatus(td.getTradeStatus());
				bo.setTradeStatusCn(td.getTradeStatusName());
				bo.setTradeTime(bo.getBorrowTime());
				bo.setTradeTimeFromat(bo.getBorrowTimeFormat());
				bo.setTradeType(td.getTradeType());
				bo.setUpdateTime(td.getUpdateDatetime());
				bo.setUpdateTimeFormat(DatetimeUtil.formatDateTime(td.getUpdateDatetime()));
				bo.setYfjAmount(td.getYfjAmount());
				bo.setAgents1Id(td.getAgents1Id());
				bo.setAgents2Id(td.getAgents2Id());
				bo.setAgents3Id(td.getAgents3Id());
				bo.setShopkeeperId(td.getShopkeeperId());
				bo.setAllianceBusinessId(td.getAllianceBusinessId());
				bo.setMerchantCn(td.getTerminalMerName()); //terminalMerName
				if (td.getShareTradeDeviceInfoModel() != null) {
					bo.setAgents1Cn(td.getShareTradeDeviceInfoModel().getAgents1Cn());
					bo.setAgents2Cn(td.getShareTradeDeviceInfoModel().getAgents2Cn());
					bo.setAgents3Cn(td.getShareTradeDeviceInfoModel().getAgents3Cn());
					bo.setShopkeeperCn(td.getShareTradeDeviceInfoModel().getShopkeeperCn());
					bo.setAllianceBusinessCn(td.getShareTradeDeviceInfoModel().getAllianceBusinessCn());
				}

				// 计算分润.
				if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
					// 顶级代理商
					bo.setProfitAmountStr(String.format("￥%.2f", td.getAgents1Amount()));
				} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
					// 一级代理商
					bo.setProfitAmountStr(String.format("￥%.2f", td.getAgents2Amount()));
				} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
					// 二级代理商
					bo.setProfitAmountStr(String.format("￥%.2f", td.getAgents3Amount()));
				} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
					// 当前商户是加盟商
					bo.setProfitAmountStr(String.format("￥%.2f", td.getAllianceBusinessAmount()));
				} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
					// 当前商户是终端商户
					bo.setProfitAmountStr("00");
				} else if (merchantType.equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
					// 当前商户是铺货商
					bo.setProfitAmountStr(String.format("￥%.2f", td.getShopkeeperAmount()));
				}
			}
		}
		return result;

	}

	/**
	 * 查询数据..
	 * 
	 * @param command
	 * @return
	 */
	public ChargerBeyondSevenDaysNotUsePageInfoBO getBeyondThreeDaysNotUseChargers(
			ChargerBeyondSevenDaysNotUseRequestCommand command) {
		ChargerBeyondSevenDaysNotUsePageInfoBO cbtdnupib = new ChargerBeyondSevenDaysNotUsePageInfoBO();
		try {
			// 1. 获到数据
			// 1.1 查询数据列表
			List<LastSevenDaysBO> list = shareChargerModelBySelfMapper.chargerLastSevenDaysInfoQuery(command);
			// 1.2 查询汇总数据...
			LastSevenDaysBO ltdb = shareChargerModelBySelfMapper.chargerLastSevenDaysNumQuery(command);
			// 2. 创建列表..
			List<ChargerBeyondSevenDaysNotUseInfoBO> boList = new ArrayList<ChargerBeyondSevenDaysNotUseInfoBO>();
			ChargerBeyondSevenDaysNotUseInfoBO bo = null;
			int dayRange = 0;
			for (LastSevenDaysBO daysBO : list) {
				bo = new ChargerBeyondSevenDaysNotUseInfoBO();
				bo.setDeviceId(daysBO.getDeviceId());
				bo.setChargerId(daysBO.getChargerId());
				bo.setLastUsedTimeFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(daysBO.getLastUsedTime()));
				dayRange = (int) ((new Date().getTime() - daysBO.getLastUsedTime().getTime()) / (1000 * 3600 * 24));
				bo.setHowLongNotUse(dayRange);
				bo.setStoreName(daysBO.getStoreName());
				bo.setStoreAddress(daysBO.getStoreAddress());
				bo.setTelNo(daysBO.getTelNo());
				bo.setAgents1Cn(daysBO.getAgents1Cn());
				bo.setAgents1Id(daysBO.getAgents1Id());
				bo.setAgents2Cn(daysBO.getAgents2Cn());
				bo.setAgents2Id(daysBO.getAgents2Id());
				bo.setAgents3Cn(daysBO.getAgents3Cn());
				bo.setAgents3Id(daysBO.getAgents3Id());
				bo.setShopkeeperCn(daysBO.getShopkeeperCn());
				bo.setShopkeeperId(daysBO.getShopkeeperId());
				bo.setAllianceBusinessCn(daysBO.getAllianceBusinessCn());
				bo.setAllianceBusinessId(daysBO.getAllianceBusinessId());
				bo.setOnlineMerchantId(daysBO.getOnlineMerchantId());
				//daysBO.getOnlineMerchantId()
				boList.add(bo);
			}
			cbtdnupib.setChargerNum(ltdb.getDevNum());
			cbtdnupib.setChargerBeyondThreeDaysNotUseInfoBOs(boList);
			//
			cbtdnupib.setResult("success");
		} catch (Exception e) {
			logger.error("", e);
			cbtdnupib.setResult("fail");
			cbtdnupib.setMessage("处理失败！");
		}
		return cbtdnupib;
	}

	/**
	 * 查询我的收益明细
	 * 
	 * @param dtb
	 * @return
	 */
	public MyIncomeDetailPageInfoBO myIncomeDatas(DeviceTradeInfoBO dtb, MerchantInfoModel merchantInfoModel) {
		MyIncomeDetailPageInfoBO midpib = new MyIncomeDetailPageInfoBO();
		try {
			List<MyIncomeDetailInfoBO> midib = new ArrayList<MyIncomeDetailInfoBO>();
			ShareTrdTotalInfo tti = tradeInfoModelMapper.getMerIncomeCountAndAmtNew(dtb);
			
			List<MyIncomeDetailInfoBO> tims = tradeInfoModelMapper.getMyIncomeDetailNew(dtb);
			if (tims != null) {
				MyIncomeDetailInfoBO bo = null;
				for (MyIncomeDetailInfoBO tim : tims) {
					bo = new MyIncomeDetailInfoBO();
					bo.setIncomeDateTimeFormat(new SimpleDateFormat("yyyy-MM-dd").format(tim.getIncomeDateTime()));
					bo.setIncomeAmount(
							tim.getIncomeAmount() == null ? BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP)
									: tim.getIncomeAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
					bo.setDoneAmount(tim.getDoneAmount());
					bo.setIncomeAmountStr(bo.getIncomeAmount().toString());
					midib.add(bo);
				}
			}
			midpib.setIncomeCount(tti.getHisTradeCount());
			midpib.setIncomeAmount(
					tti.getHisTradeAmount() == null ? BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP)
							: tti.getHisTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
			midpib.setMyIncomeDetailInfoBOs(midib);
			midpib.setResult("success");
		} catch (Exception e) {
			logger.error("", e);
			midpib.setResult("fail");
		}
		return midpib;
	}

	/**
	 * 批量修改费用...
	 * 
	 * @param list
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 25, rollbackFor = Exception.class)
	public void batchEditFee(List<BatchEditFeeCommand> list, String filter) {
		Long feeType = 0L;
		// shareDeviceInfoModelBySelfMapper.batchBindFeeByDeviceNoList(record,
		// list);
		ShareDeviceInfoModel deviceInfoModel = new ShareDeviceInfoModel();
		FeeTypeEnum feeTypeEnum = null;
		for (BatchEditFeeCommand batchEditFeeCommand : list) {
			// 初始
			feeType = batchEditFeeCommand.getFeeType();
			if (feeType == null || feeType == 0) {
				deviceInfoModel = new ShareDeviceInfoModel();
				deviceInfoModel.setFeeTypeName(null);
				deviceInfoModel.setFeeTypeId(null);
				deviceInfoModel.setFee1HourAmount(batchEditFeeCommand.getFeeType1Money());
				if (batchEditFeeCommand.getFeeType1() == null) {
					deviceInfoModel.setFee1TypeId(FeeTypeEnum.UNKNOWN.getId());
				} else {
					deviceInfoModel.setFee1TypeId(batchEditFeeCommand.getFeeType1().intValue());
					feeTypeEnum = FeeTypeEnum.getFeetypeEnum(batchEditFeeCommand.getFeeType1().intValue());
					if (feeTypeEnum != null) {
						deviceInfoModel.setFee1HourType(feeTypeEnum.getId());
						deviceInfoModel.setFee1TypeName(feeTypeEnum.getName());
					}
				}
				deviceInfoModel.setFee2HourAmount(batchEditFeeCommand.getFeeType2Money());
				if (batchEditFeeCommand.getFeeType2() == null) {
					deviceInfoModel.setFee2TypeId(FeeTypeEnum.UNKNOWN.getId());
				} else {
					deviceInfoModel.setFee2TypeId(batchEditFeeCommand.getFeeType2().intValue());
					feeTypeEnum = FeeTypeEnum.getFeetypeEnum(batchEditFeeCommand.getFeeType2().intValue());
					if (feeTypeEnum != null) {
						deviceInfoModel.setFee2HourType(feeTypeEnum.getId());
						deviceInfoModel.setFee2TypeName(feeTypeEnum.getName());
					}
				}
				deviceInfoModel.setFee3HourAmount(batchEditFeeCommand.getFeeType3Money());
				if (batchEditFeeCommand.getFeeType3() == null) {
					deviceInfoModel.setFee3TypeId(FeeTypeEnum.UNKNOWN.getId());
				} else {
					deviceInfoModel.setFee3TypeId(batchEditFeeCommand.getFeeType3().intValue());
					feeTypeEnum = FeeTypeEnum.getFeetypeEnum(batchEditFeeCommand.getFeeType3().intValue());
					if (feeTypeEnum != null) {
						deviceInfoModel.setFee3HourType(feeTypeEnum.getId());
						deviceInfoModel.setFee3TypeName(feeTypeEnum.getName());
					}
				}
			} else {
				deviceInfoModel = new ShareDeviceInfoModel();
				deviceInfoModel.setFeeTypeId(batchEditFeeCommand.getFeeType().intValue());
				deviceInfoModel.setYfjAmount(batchEditFeeCommand.getYfj());
				feeTypeEnum = FeeTypeEnum.getFeetypeEnum(feeType.intValue());
				if (feeTypeEnum != null) {
					deviceInfoModel.setFeeTypeName(feeTypeEnum.getName());
				}
				deviceInfoModel.setFirstAmount(batchEditFeeCommand.getFirstMoney());
				if (batchEditFeeCommand.getFirstHour() != null) {
					deviceInfoModel.setFirstMinutes(batchEditFeeCommand.getFirstHour() * 60L);
				}
				deviceInfoModel.setAmountPerHour(batchEditFeeCommand.getFeePerHour());
				deviceInfoModel.setAmountMax24hour(batchEditFeeCommand.getFeePer24Hour());
				deviceInfoModel.setFee1TypeId(FeeTypeEnum.UNKNOWN.getId());
				deviceInfoModel.setFee2TypeId(FeeTypeEnum.UNKNOWN.getId());
				deviceInfoModel.setFee3TypeId(FeeTypeEnum.UNKNOWN.getId());
			}
			// 2. 批量修改
			shareDeviceInfoModelBySelfMapper.batchBindFeeByDeviceNoListNoValidate(deviceInfoModel,
					batchEditFeeCommand.getDeviceIds(), filter);
		}
	}

	/**
	 * 批量输入分润... 平台分润不可修改。。。
	 * 
	 * @param list
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 25, rollbackFor = Exception.class)
	public void batchEditProfit(List<BatchEditProfitCommand> list, String filter) {
		ShareDeviceInfoModel deviceInfoModel = new ShareDeviceInfoModel();
		String[] keyAry;
		int iSizeForKeyAry = 0;
		for (BatchEditProfitCommand batchEditFeeCommand : list) {
			if (batchEditFeeCommand.getDevicesIds().size() <= 0) {
				continue;
			}
			keyAry = batchEditFeeCommand.getKey().split("_");
			iSizeForKeyAry=keyAry.length;
					
			// 初始
			deviceInfoModel = new ShareDeviceInfoModel();
			BigDecimal zeroRatio = new BigDecimal(0);
			BigDecimal agents1Ratio = batchEditFeeCommand.getAgents1Ratio() == null ? zeroRatio
					: batchEditFeeCommand.getAgents1Ratio();
			BigDecimal agents2Ratio = batchEditFeeCommand.getAgents2Ratio() == null ? zeroRatio
					: batchEditFeeCommand.getAgents2Ratio();
			BigDecimal agents3Ratio = batchEditFeeCommand.getAgents3Ratio() == null ? zeroRatio
					: batchEditFeeCommand.getAgents3Ratio();
			BigDecimal shopkeeperRatio = batchEditFeeCommand.getShopkeeperRatio() == null ? zeroRatio
					: batchEditFeeCommand.getShopkeeperRatio();
			BigDecimal allianceBusinessRatio = batchEditFeeCommand.getAllianceBusinessRatio() == null ? zeroRatio
					: batchEditFeeCommand.getAllianceBusinessRatio();
			deviceInfoModel.setPlatformRato(null);//平台不能修改..			
			deviceInfoModel.setAgents1Rato((iSizeForKeyAry>=6)?agents1Ratio:null);//顶代修改
			deviceInfoModel.setAgents2Rato((iSizeForKeyAry>=5)?agents2Ratio:null);//一级修改
			deviceInfoModel.setAgents3Rato((iSizeForKeyAry>=4)?agents3Ratio:null);//二级改
			deviceInfoModel.setShopkeeperRato((iSizeForKeyAry>=3)?shopkeeperRatio:null);//铺货商改
			deviceInfoModel.setAllianceBusinessRate(allianceBusinessRatio);
			deviceInfoModel.setUpdateDatetime(new Date());
			// 2. 批量修改
			shareDeviceInfoModelBySelfMapper.batchRatioByDeviceNoList(deviceInfoModel,
					batchEditFeeCommand.getDevicesIds(), filter);
		}
	}

	/**
	 * 批量设备上下架。。batchEditDeviceStatus
	 * 
	 * @param list
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 25, rollbackFor = Exception.class)
	public void batchEditDeviceStatus(ShareDeviceInfoModel deviceInfoModel, List<Long> list, String filter) {
		// 1.
		deviceInfoModel.setUpdateDatetime(new Date());
		// 2. 批量修改
		shareDeviceInfoModelBySelfMapper.batchModifyByDeviceNoList(deviceInfoModel, list, filter);

	}

	/**
	 * 根据设备号获取近七天设备使用次数
	 * 
	 * @param deviceIdStr
	 * @return
	 */
	public Map<String, Object> getUsageTrendData(String deviceIdStr) {
		Map<String, Object> resultMap = new HashMap<>();
		List<Long> deviceids = new ArrayList<Long>();
		if (!StringUtils.isBlank(deviceIdStr)) {
			deviceids.add(Long.valueOf(deviceIdStr));
		}
		List<DeviceUsageTrendBO> resultList = shareDeviceInfoModelBySelfMapper.getUsageTrendData(deviceids);
		// 组装map
		List<String> dates = new ArrayList<>();
		List<String> counts = new ArrayList<>();

		for (DeviceUsageTrendBO list : resultList) {
			dates.add(list.getDateStr());
			counts.add(list.getCount());
		}
		resultMap.put("dates", dates);
		resultMap.put("counts", counts);
		return resultMap;
	}

	/**
	 * 得到附近商家铺货充电器信息，个人出租充电器信息，个人租借充电器信息
	 * 
	 * @param longitudeStr
	 * @param latitudeStr
	 * @return
	 */
	public List<MapMarkersBO> nearDevice(String longitudeStr, String latitudeStr) {
		// TODO
		return null;
	}

	/**
	 * 根据条件查询未使用的设备情况
	 * 
	 * @param filter
	 * @return
	 */
	public Map<String, Object> unusedDeviceInfo(HashMap<String, Object> filter) {
		if (filter == null) {
			return null;
		}
		Map<String, Object> resultMap = new HashMap<>();
		// 七天未使用的充电器数量
		filter.put("days", 7);
		Long noUseChargerNumFor7 = shareDeviceInfoModelBySelfMapper.countBeyondDaysNotUseCharger(filter);
		// 有七天未使用充电器的商户数量
		Long noUseMerchantNumFor7 = shareDeviceInfoModelBySelfMapper.countMerchantBeyondDaysNotUseCharger(filter);
		// 一个月未使用的充电器数量
		filter.put("days", 30);
		Long noUseChargerNumFor30 = shareDeviceInfoModelBySelfMapper.countBeyondDaysNotUseCharger(filter);

		resultMap.put("noUseMerchantNumFor7", noUseMerchantNumFor7);
		resultMap.put("noUseChargerNumFor7", noUseChargerNumFor7);
		resultMap.put("noUseChargerNumFor30", noUseChargerNumFor30);

		return resultMap;
	}

}
