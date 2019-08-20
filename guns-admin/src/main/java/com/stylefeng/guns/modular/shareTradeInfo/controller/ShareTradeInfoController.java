package com.stylefeng.guns.modular.shareTradeInfo.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.model.OperatorTypeEnum;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.shareTradeInfo.service.IShareTradeInfoService;
import com.stylefeng.guns.modular.system.model.ShareTradeInfo;
import com.stylefeng.guns.modular.system.warpper.ShareTradeInfoWarpper;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeFinishFlagEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.FinishTradeOrderBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModelFinishBo;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModelSearchBO;
import com.stylefeng.guns.sharecore.modular.system.model.TotalModel;
import com.stylefeng.guns.sharecore.modular.system.service.ShareTradeService;

/**
 * 交易订单控制器
 *
 * @author fengshuonan
 * @Date 2019-01-21 16:37:41
 */
@Controller
@RequestMapping("/shareTradeInfo")
public class ShareTradeInfoController extends BaseController {

	private String PREFIX = "/shareTradeInfo/shareTradeInfo/";

	@Autowired
	private IShareTradeInfoService shareTradeInfoService;
	/**
	 * 交易...
	 */
	@Autowired
	private ShareTradeInfoModelMapper shareTradeInfoModelMapper;
	/**
	 * 数据库操作.
	 */
	@Autowired
	private ShareTradeInfoModelBySelfMapper shareTradeInfoModelBySelfMapper;
	/**
	 * 交易驿应的配置...
	 */
	@Autowired
	private ShareTradeDeviceInfoModelMapper shareTradeDeviceInfoModelMapper;
	/**
	 * 结束订单
	 */
	@Autowired
	private ShareTradeService shareTradeService;
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(ShareTradeInfoController.class);

	/**
	 * 跳转到交易订单首页
	 */
	@RequestMapping("")
	public String index(Model model) {
		Date nowStart = new Date();
		Integer userId = ShiroKit.getUser().getId();
		nowStart = DateUtils.addDay(nowStart, OperatorTypeEnum.ADD.getCode(), 1);
		model.addAttribute("endDate", DateUtil.format(nowStart, "yyyy-MM-dd"));
		model.addAttribute("userId", userId);
		model.addAttribute("startDate",
				DateUtil.format(DateUtils.addDay(nowStart, OperatorTypeEnum.SUBTRACT.getCode(), 31), "yyyy-MM-dd"));

		return PREFIX + "shareTradeInfo.html";
	}

	/**
	 * 跳转到添加交易订单
	 */
	@RequestMapping("/shareTradeInfo_add")
	public String shareTradeInfoAdd() {
		return PREFIX + "shareTradeInfo_add.html";
	}

	/**
	 * 跳转到修改交易订单
	 */
	@RequestMapping("/shareTradeInfo_update/{shareTradeInfoId}")
	public String shareTradeInfoUpdate(@PathVariable Integer shareTradeInfoId, Model model) {
		ShareTradeInfo shareTradeInfo = shareTradeInfoService.selectById(shareTradeInfoId);
		model.addAttribute("item", shareTradeInfo);
		LogObjectHolder.me().set(shareTradeInfo);
		return PREFIX + "shareTradeInfo_edit.html";
	}

	/**
	 * 订单退款或结束
	 * 
	 * @param shareTradeInfoId
	 * @param model
	 * @return
	 */
	@RequestMapping("/shareTradeInfo_finishOrRefund/{shareTradeInfoId}")
	public String shareTradeInfoFinishOrRefund(@PathVariable Long shareTradeInfoId, Model model) {
		ShareTradeInfoModel shareTradeInfo = shareTradeInfoModelMapper.selectByPrimaryKey(shareTradeInfoId);

		model.addAttribute("item", shareTradeInfo);
		BigDecimal tradeAmount = new BigDecimal("0");// 交易金额
		Long useSeconds = new Long("0");// 使用时长..
		String useTimes = "";
		if (shareTradeInfo.getTradeStatus() != null
				&& shareTradeInfo.getTradeStatus().equals(ShareTradeStatusEnum.Finish.getCode())) {
			tradeAmount = shareTradeInfo.getTradeAmount();
			useSeconds = (shareTradeInfo.getBackDatetime().getTime()
					- shareTradeInfo.getBorrowDatetime().getTime()) / 1000;
		} else {
			ShareTradeDeviceInfoModel shareTradeDeviceInfoModel = shareTradeDeviceInfoModelMapper
					.selectByPrimaryKey(shareTradeInfoId);
			Map<String, Object> map = shareTradeService.calculationUseAmountAndUseTime(shareTradeInfo,
					shareTradeDeviceInfoModel);
			tradeAmount = (BigDecimal) map.get("useAmount");
			useSeconds = (Long) map.get("useTimesForSeconds");// 使作时间
		}
		model.addAttribute("tradeAmount", tradeAmount);
		model.addAttribute("useSeconds", useSeconds);
		// 处理时间
		useTimes = DateUtils.formatSeconds(useSeconds);
		model.addAttribute("useTimes", useTimes);
		LogObjectHolder.me().set(shareTradeInfo);
		return PREFIX + "shareTradeInfo_finishOrRefund.html";
	}

	/**
	 * 订单退款或结束
	 * 
	 * @param shareTradeInfoId
	 * @param model
	 * @return
	 */
	@RequestMapping("/shareTradeInfo_AdvanceRefund/{shareTradeInfoId}")
	public String shareTradeInfoAdvanceRefund(@PathVariable Long shareTradeInfoId, Model model) {
		ShareTradeInfoModel shareTradeInfo = shareTradeInfoModelMapper.selectByPrimaryKey(shareTradeInfoId);
		model.addAttribute("item", shareTradeInfo);
		BigDecimal tradeAmount = new BigDecimal("0");// 交易金额
		Long useSeconds = new Long("0");// 使用时长..
		String useTimes = "";
		if (shareTradeInfo!=null&&shareTradeInfo.getTradeStatus() != null
				&& shareTradeInfo.getTradeStatus().equals(ShareTradeStatusEnum.Finish.getCode())) {
			tradeAmount = shareTradeInfo.getTradeAmount();
			useSeconds = (shareTradeInfo.getBackDatetime().getTime()
					- shareTradeInfo.getBorrowDatetime().getTime()) / 1000;
		} else {
			ShareTradeDeviceInfoModel shareTradeDeviceInfoModel = shareTradeDeviceInfoModelMapper
					.selectByPrimaryKey(shareTradeInfoId);
			Map<String, Object> map = shareTradeService.calculationUseAmountAndUseTime(shareTradeInfo,
					shareTradeDeviceInfoModel);
			tradeAmount = (BigDecimal) map.get("useAmount");
			useSeconds = (Long) map.get("useTimesForSeconds");// 使作时间
		}
		model.addAttribute("tradeAmount", tradeAmount);
		model.addAttribute("useSeconds", useSeconds);
		// 处理时间
		useTimes = DateUtils.formatSeconds(useSeconds);
		model.addAttribute("useTimes", useTimes);
		LogObjectHolder.me().set(shareTradeInfo);
		return PREFIX + "shareTradeInfo_advanceRefund.html";
	}
	/**
	 * 导出交易Excel表格
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		Integer userId = ShiroKit.getUser().getId();
		String condition = request.getParameter("condition");// 查询条件..
		String conditionValue = request.getParameter("conditionValue");// 查询结果
		String backTimeStart = request.getParameter("backTimeStart");// 开始
		String backTimeEnd = request.getParameter("backTimeEnd");// 结束
		String borrowTimeStart = request.getParameter("borrowTimeStart");// 开始
		String borrowTimeEnd = request.getParameter("borrowTimeEnd");// 结束
		String condition1 = request.getParameter("condition1");// 查询条件1..
		String conditionValue1 = request.getParameter("conditionValue1");// 查询结果1
		String startDeviceNo = request.getParameter("startDeviceNo");// 开始设备号
		String endDeviceNo = request.getParameter("endDeviceNo");// 结束设备号
		// 根据条件查询到的交易数据
		HashMap<String, Object> pageFilter = new HashMap<>();
		HashMap<String, Object> resultMap = new HashMap<>();
		// 组装查询参数
		buildTradeInfoParam(userId, null, condition, conditionValue, backTimeStart, backTimeEnd, borrowTimeStart,
				borrowTimeEnd, condition1, conditionValue1, null, null, startDeviceNo, endDeviceNo, resultMap,
				pageFilter);
		//根据登陆用户的商户类型显示对应的商户信息
		int merchantType = resultMap.get("merchantType") == null ? -1 : (Integer) resultMap.get("merchantType");
		try {
			shareTradeInfoService.exportExcel(merchantType,response, pageFilter);
		} catch (Exception e) {
			logger.error("交易数据导出异常！error:", e);
		}
	}

	/**
	 * 获取交易订单列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Integer userId = ShiroKit.getUser().getId();
		String order = request.getParameter("order");
		String offsetStr = request.getParameter("offset");
		String limitStr = request.getParameter("limit"); // 每页多少行..
		String condition = request.getParameter("condition");// 查询条件..
		String conditionValue = request.getParameter("conditionValue");// 查询结果
		String backTimeStart = request.getParameter("backTimeStart");// 开始
		String backTimeEnd = request.getParameter("backTimeEnd");// 结束
		String borrowTimeStart = request.getParameter("borrowTimeStart");// 开始
		String borrowTimeEnd = request.getParameter("borrowTimeEnd");// 结束
		String startDeviceNo = request.getParameter("startDeviceNo");// 开始设备号
		String endDeviceNo = request.getParameter("endDeviceNo");// 结束设备号

		String condition1 = request.getParameter("condition1");// 查询条件1..
		String conditionValue1 = request.getParameter("conditionValue1");// 查询结果1
		Long offset = 0L;
		logger.info(String.format(
				"获取device列表 list userId:%d,order:%s,offsetStr:%s," + "limitStr:%s,condition:%s,conditionValue:%s",
				userId, order, offsetStr, limitStr, condition, conditionValue));
		try {
			offset = Long.parseLong(offsetStr);
		} catch (Exception e) {
		}
		Long limit = 14L;
		try {
			limit = Long.parseLong(limitStr);
		} catch (Exception e) {
			limit = 14L;
		}
		order = "trade.id desc";
		// 2. 初始他参数
		Map<String, Object> resultMap = new HashMap<>();
		HashMap<String, Object> pageFilter = new HashMap<>();
		// 组装查询参数
		buildTradeInfoParam(userId, order, condition, conditionValue, backTimeStart, backTimeEnd, borrowTimeStart,
				borrowTimeEnd, condition1, conditionValue1, offset, limit, startDeviceNo, endDeviceNo, resultMap,
				pageFilter);

		TotalModel total = shareTradeInfoModelBySelfMapper.countByConditionForPage(pageFilter);
		List<ShareTradeInfoModelSearchBO> list = shareTradeInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
		resultMap.put("rows", list);
		resultMap.put("total", total.getTotal());
		resultMap.put("totalAmount", total.getTotalAmount());
		resultMap.put("totalAgents1Amount", total.getTotalAgents1Amount());
		resultMap.put("totalAgents2Amount", total.getTotalAgents2Amount());
		resultMap.put("totalAgents3Amount", total.getTotalAgents3Amount());
		resultMap.put("totalAllianceBusinessAmount", total.getTotalAllianceBusinessAmount());
		resultMap.put("totalPlatFormAmount", total.getTotalPlatFormAmount());
		resultMap.put("totalShopkeeperAmount", total.getTotalShopkeeperAmount());
		resultMap.put("totalYFJAmount", total.getTotalYFJAmount());
		resultMap.put("userId", userId);
		
		// 过滤返回字段
		return new ShareTradeInfoWarpper(resultMap).warp();
	}

	/**
	 * 组装交易查询参数
	 * 
	 * @param userId
	 * @param order
	 * @param condition
	 * @param conditionValue
	 * @param backTimeStart
	 * @param backTimeEnd
	 * @param borrowTimeStart
	 * @param borrowTimeEnd
	 * @param condition1
	 * @param conditionValue1
	 * @param offset
	 * @param limit
	 * @param resultMap
	 * @param pageFilter
	 */
	private void buildTradeInfoParam(Integer userId, String order, String condition, String conditionValue,
			String backTimeStart, String backTimeEnd, String borrowTimeStart, String borrowTimeEnd, String condition1,
			String conditionValue1, Long offset, Long limit, String startDeviceNo, String endDeviceNo,
			Map<String, Object> resultMap, HashMap<String, Object> pageFilter) {
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);
		String pattern = "yyyy-MM-dd";
		if (StringUtils.isNotEmpty(startDeviceNo)) {
			pageFilter.put("startDeviceNo", Long.valueOf(startDeviceNo));
		}
		if (StringUtils.isNotEmpty(endDeviceNo)) {
			pageFilter.put("endDeviceNo", Long.valueOf(endDeviceNo));
		}
		if (borrowTimeStart != null && !borrowTimeStart.isEmpty() && DateUtil.isValidDate(borrowTimeStart, pattern)) {
			pageFilter.put("borrowTimeStart", DateUtil.parse(borrowTimeStart, pattern));
		}
		if (borrowTimeEnd != null && !borrowTimeEnd.isEmpty() && DateUtil.isValidDate(borrowTimeEnd, pattern)) {
			pageFilter.put("borrowTimeEnd", DateUtil.parse(borrowTimeEnd+" 23:59:59", DateUtil.NORMAL_FORMAT));
		}
		if (backTimeStart != null && !backTimeStart.isEmpty() && DateUtil.isValidDate(backTimeStart, pattern)) {
			pageFilter.put("backTimeStart", DateUtil.parse(backTimeStart, pattern));
		}
		if (backTimeEnd != null && !backTimeEnd.isEmpty() && DateUtil.isValidDate(backTimeEnd, pattern)) {
			pageFilter.put("backTimeEnd", DateUtil.parse(backTimeEnd+" 23:59:59", DateUtil.NORMAL_FORMAT));
		}
		// pageFilter.put("id", 10000001L);
		pageFilter.put("limit", limit);
		// 3.
		if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
			condition = condition.toLowerCase();
			try {
				if (condition.equalsIgnoreCase("tradeid")) {
					pageFilter.put("id", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("deviceid")) {
					pageFilter.put("deviceId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("chargerid")) {
					pageFilter.put("chargerId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("custno")) {
					pageFilter.put("custNo", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("merchanid")) { // 店铺id
					pageFilter.put("merchantId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("agents1id")) { // 一级
					pageFilter.put("agents1Id", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("agents2id")) { // 二级
					pageFilter.put("agents2Id", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("agents3id")) { // 三级
					pageFilter.put("agents3Id", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("shopkeeperid")) { // 铺货商
					pageFilter.put("shopkeeperId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("allianceBusinessid")) { // 加盟商id
					pageFilter.put("allianceBusinessId", Long.valueOf(conditionValue));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (condition1 != null && !condition1.isEmpty() && conditionValue1 != null && !conditionValue1.isEmpty()) {
			condition1 = condition1.toLowerCase();
			try {
				if (condition1.equalsIgnoreCase("merchant")) {
					pageFilter.put("merchant", conditionValue1);
				} else if (condition1.equalsIgnoreCase("agents1")) {
					pageFilter.put("agents1", conditionValue1);
				} else if (condition1.equalsIgnoreCase("agents2")) {
					pageFilter.put("agents2", conditionValue1);
				} else if (condition1.equalsIgnoreCase("agents3")) {
					pageFilter.put("agents3", conditionValue1);
				} else if (condition1.equalsIgnoreCase("shopkeeper")) { // 铺货商
					pageFilter.put("shopkeeper", conditionValue1);
				} else if (condition1.equalsIgnoreCase("allianceBusiness")) { // 一级
					pageFilter.put("allianceBusiness", conditionValue1);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// 权限 管理员有全部权限admin...
		if (userId != null && userId.intValue() != 1) {
			List<Integer> listForRole = ShiroKit.getUser().getRoleList();
			// 获取登陆用户商户类型
			resultMap.put("merchantType", ShiroKit.getUser().getMerchantType());
			if (listForRole == null || listForRole.size() <= 0) {
				pageFilter.put("noRight", "1");
			} else {
				List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
				if (list == null || list.size() <= 0) {
					pageFilter.put("noRight", "1");
				} else {
					String filter = "";
					for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
						if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG1
								.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("trade.agents1_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("trade.agents1_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						} else if (roleMerchantRefInfoModel.getMerchantType()
								.intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("trade.agents2_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("trade.agents2_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						} else if (roleMerchantRefInfoModel.getMerchantType()
								.intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("trade.agents3_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("trade.agents3_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.PUHUO_SHANG
								.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("trade.shopkeeper_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("trade.shopkeeper_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						} else if (roleMerchantRefInfoModel.getMerchantType()
								.intValue() == MerchantTypeEnum.JIA_MENG_SHANG.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("trade.alliance_business_id=%d",
											roleMerchantRefInfoModel.getMerchantId())
									: String.format("trade.alliance_business_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.TERMINAL
								.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("trade.merchant_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("trade.merchant_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						}
					}
					if (!filter.isEmpty()) {
						pageFilter.put("filter", String.format(" and (%s)", filter));
					}
				}
			}
		}
	}

	/**
	 * 新增交易订单
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(ShareTradeInfo shareTradeInfo) {
		// shareTradeInfoService.insert(shareTradeInfo);
		return SUCCESS_TIP;
	}

	/**
	 * 删除交易订单
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer shareTradeInfoId) {
		// shareTradeInfoService.deleteById(shareTradeInfoId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改交易订单
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(ShareTradeInfo shareTradeInfo) {
		// shareTradeInfoService.updateById(shareTradeInfo);
		return SUCCESS_TIP;
	}

	/**
	 * 交易订单详情
	 */
	@RequestMapping(value = "/detail/{shareTradeInfoId}")
	@ResponseBody
	public Object detail(@PathVariable("shareTradeInfoId") Integer shareTradeInfoId) {
		return shareTradeInfoService.selectById(shareTradeInfoId);
	}

	/**
	 * 订单结束或退款
	 */
	@RequestMapping(value = "/finishOrRefundOrder")
	@ResponseBody
	public Object finishOrRefundOrder(ShareTradeInfoModelFinishBo shareTradeInfoModelFinishBo) {
		logger.info("订单结束或退款.-finishOrRefundOrder{}", shareTradeInfoModelFinishBo);
		String msg = "";
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		// 批量生成...
		try {
			if (shareTradeInfoModelFinishBo == null || shareTradeInfoModelFinishBo.getId() == null) {
				result.setMessage("参数异常无法结束订单.！");
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			// 2. 判断参数是否正确...
			ShareTradeInfoModel tradeInfoModel = shareTradeInfoModelMapper
					.selectByPrimaryKey(shareTradeInfoModelFinishBo.getId());
			if (tradeInfoModel == null) {
				msg = String.format("交易订单(%d)的订单在系统中不在！", tradeInfoModel.getId());
				logger.info("订单结束或退款.-finishOrRefundOrder--msg %s", msg);
				result.setMessage(msg);
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			ShareTradeDeviceInfoModel tradeDeviceInfoModel = shareTradeDeviceInfoModelMapper
					.selectByPrimaryKey(tradeInfoModel.getId());
			if (tradeDeviceInfoModel == null) {
				msg = String.format("交易订单(%d)的订单在系统中不在！", tradeInfoModel.getId());
				logger.info("订单结束或退款.-finishOrRefundOrder--msg %s", msg);
				result.setMessage(msg);
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			// 订单已结束
			if (tradeInfoModel.getTradeStatus() != null
					&& tradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.Finish.getCode())) {
				// 判断是否重复退款
				msg = String.format("订单(%d)退款成功", tradeInfoModel.getId());
				if (tradeInfoModel.getBackTradeId() != null && !tradeInfoModel.getBackTradeId().equals(0L)) {
					msg = String.format("交易订单(%d)的已退款，无需重复退款！", tradeInfoModel.getId());
					logger.info("订单结束或退款.-finishOrRefundOrder--msg %s", msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}

				if (shareTradeInfoModelFinishBo.getTradeAmoutForBack() == null
						|| shareTradeInfoModelFinishBo.getTradeAmoutForBack().compareTo(new BigDecimal("0")) < 0
						|| shareTradeInfoModelFinishBo.getTradeAmoutForBack()
								.compareTo(tradeInfoModel.getTradeAmount()) > 0) {
					msg = String.format("输入的退款金额必须大于等于0小于等于订单交易金额(%d)，请输入正确的退款金额!", tradeInfoModel.getYfjAmount(),
							tradeInfoModel.getId());
					logger.info("finishOrRefundOrder===" + msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.SUCCESS);
					return result;
				}
				if (shareTradeInfoModelFinishBo.getRemarkForBack() == null
						|| shareTradeInfoModelFinishBo.getRemarkForBack().isEmpty()) {
					msg = "未输入订单退款备注，请输入订单退款备注!";
					logger.info("finishOrRefundOrder===" + msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.SUCCESS);
					return result;
				}
				// 3. 订单退款...
				FinishTradeOrderBO bo = shareTradeService.toRefundByTradeOrder(tradeInfoModel, tradeDeviceInfoModel,
						shareTradeInfoModelFinishBo.getTradeAmoutForBack(), true,
						shareTradeInfoModelFinishBo.getRemarkForBack(), userId != null ? userId.longValue() : 0L);

			} else {
				msg = "未输入订单退款备注，请输入订单退款备注!";
				// 订单未结束...
				msg = String.format("强制结束订单(%d)成功", tradeInfoModel.getId());
				if (shareTradeInfoModelFinishBo.getTradeAmoutForFinish() == null
						|| shareTradeInfoModelFinishBo.getTradeAmoutForFinish().compareTo(new BigDecimal("0")) < 0
						|| shareTradeInfoModelFinishBo.getTradeAmoutForFinish()
								.compareTo(tradeInfoModel.getYfjAmount()) > 0) {
					msg = String.format("输入的充电费用必须大于等于0小于等于订单预付金(%d)，请输入正确的充电费用!", tradeInfoModel.getYfjAmount(),
							tradeInfoModel.getId());
					logger.info("finishOrRefundOrder===" + msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.SUCCESS);
					return result;
				}
				if (shareTradeInfoModelFinishBo.getRemarkForFinish() == null
						|| shareTradeInfoModelFinishBo.getRemarkForFinish().isEmpty()) {
					msg = "未输入强制结束订单备注，请输入强制结束订单备注!";
					logger.info("finishOrRefundOrder===" + msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.SUCCESS);
					return result;
				}
				// 3. 处理订单....
				// 结束订单，并退款...
				Map<String, Object> mapForFee = shareTradeService.calculationUseAmountAndUseTime(tradeInfoModel,
						tradeDeviceInfoModel);
				BigDecimal calTradeAmount = new BigDecimal("0");
				if (mapForFee != null) {
					calTradeAmount = (BigDecimal) mapForFee.get("useAmount");
				}
				mapForFee.put("useAmount", shareTradeInfoModelFinishBo.getTradeAmoutForFinish());
				// 结束订单...
				FinishTradeOrderBO bo = shareTradeService.toBackByTradeOrder(tradeInfoModel, tradeDeviceInfoModel,
						mapForFee, ShareTradeFinishFlagEnum.SYSTEM_FORCE_FINISH, true,
						shareTradeInfoModelFinishBo.getRemarkForFinish(), calTradeAmount);
				logger.info("finishOrRefundOrder=shareTradeService.toBackByTradeId=={}", bo);
			}
			result.setMessage(msg);
			result.setResult(ResultCommandBaseObject.SUCCESS);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("批量设置分润失败--batchModify", e);
			result.setMessage(e.getMessage());
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}
	}

	/**
	 *高级退款
	 */
	@RequestMapping(value = "/advanceRefundOrder")
	@ResponseBody
	public Object advanceRefundOrder(ShareTradeInfoModelFinishBo shareTradeInfoModelFinishBo) {
		logger.info("高级退款.-advanceRefundOrder{}", shareTradeInfoModelFinishBo);
		String msg = "";
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		// 批量生成...
		try {
			if (shareTradeInfoModelFinishBo == null || shareTradeInfoModelFinishBo.getId() == null) {
				result.setMessage("参数异常无法结束订单.！");
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			// 2. 判断参数是否正确...
			ShareTradeInfoModel tradeInfoModel = shareTradeInfoModelMapper
					.selectByPrimaryKey(shareTradeInfoModelFinishBo.getId());
			if (tradeInfoModel == null) {
				msg = String.format("交易订单(%d)的订单在系统中不在！", tradeInfoModel.getId());
				logger.info("订单结束或退款.-finishOrRefundOrder--msg %s", msg);
				result.setMessage(msg);
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			ShareTradeDeviceInfoModel tradeDeviceInfoModel = shareTradeDeviceInfoModelMapper
					.selectByPrimaryKey(tradeInfoModel.getId());
			if (tradeDeviceInfoModel == null) {
				msg = String.format("交易订单(%d)的订单在系统中不在！", tradeInfoModel.getId());
				logger.info("订单结束或退款.-finishOrRefundOrder--msg %s", msg);
				result.setMessage(msg);
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			// 订单已结束
			if (tradeInfoModel.getTradeStatus() != null
					&& tradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.Finish.getCode())) {
				// 判断是否重复退款
				msg = String.format("订单(%d)退款成功", tradeInfoModel.getId());
				if (tradeInfoModel.getBackTradeId() != null && !tradeInfoModel.getBackTradeId().equals(0L)) {
					msg = String.format("交易订单(%d)的已退款，无需重复退款！", tradeInfoModel.getId());
					logger.info("订单结束或退款.-finishOrRefundOrder--msg %s", msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}

				if (shareTradeInfoModelFinishBo.getTradeAmoutForBack() == null
						|| shareTradeInfoModelFinishBo.getTradeAmoutForBack().compareTo(new BigDecimal("0")) < 0
						|| shareTradeInfoModelFinishBo.getTradeAmoutForBack()
								.compareTo(tradeInfoModel.getTradeAmount()) > 0) {
					msg = String.format("输入的退款金额必须大于等于0小于等于订单交易金额(%d)，请输入正确的退款金额!", tradeInfoModel.getYfjAmount(),
							tradeInfoModel.getId());
					logger.info("finishOrRefundOrder===" + msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}
				if (shareTradeInfoModelFinishBo.getRemarkForBack() == null
						|| shareTradeInfoModelFinishBo.getRemarkForBack().isEmpty()) {
					msg = "未输入订单退款备注，请输入订单退款备注!";
					logger.info("finishOrRefundOrder===" + msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}
				// 3. 订单退款...
				FinishTradeOrderBO bo = shareTradeService.toAdvanceRefundByTradeOrder(
												tradeInfoModel, 
												tradeDeviceInfoModel,
												shareTradeInfoModelFinishBo.getTradeAmoutForBack(), true,
												shareTradeInfoModelFinishBo.getRemarkForBack(), userId != null ? userId.longValue() : 0L);
			} else {
				msg = "未结束订单不可进行退款!";
				logger.info("finishOrRefundOrder===" + msg);
				result.setMessage(msg);
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			result.setMessage(msg);
			result.setResult(ResultCommandBaseObject.SUCCESS);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("批量设置分润失败--batchModify", e);
			result.setMessage(e.getMessage());
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}
	}
}
