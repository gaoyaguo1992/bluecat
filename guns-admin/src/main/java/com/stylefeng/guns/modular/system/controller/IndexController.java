package com.stylefeng.guns.modular.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateMerchantDataModel;
import com.stylefeng.guns.sharecore.modular.system.model.ChargerFewDayNotUsedBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceNoUseOfMerchantBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.ICalculateMerchantDataService;
import com.stylefeng.guns.sharecore.modular.system.service.ICalcultaeDeviceDataService;
import com.stylefeng.guns.sharecore.modular.system.service.ShareDeviceService;

/**
 * 后台首页
 * 
 * @author seven
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	private String PREFIX = "/system/indexPage/";

	@Autowired
	private ICalcultaeDeviceDataService calcultaeDeviceDataService;

	@Autowired
	private ShareDeviceService shareDeviceService;

	@Autowired
	private ICalculateMerchantDataService calculateMerchantDataService;

	// 未使用设备的信息
	@RequestMapping("/unusedDeviceInfo")
	@ResponseBody
	public Object unusedDeviceInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("方法unusedDeviceInfo入参。。。");
		HashMap<String, Object> filter = new HashMap<>();
		initFilterMapForRight(filter, "share_device_info.");
		Map<String, Object> resultMap = shareDeviceService.unusedDeviceInfo(filter);
		logger.info("方法unusedDeviceInfo出参。。。resultMap:{}", resultMap);
		return resultMap;
	}

	// 登陆用户所有设备七天使用率
	@RequestMapping("/totalDeviceUsageTrend")
	@ResponseBody
	public Object totalDeviceUsageTrend(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登陆用户数据权限
		Integer userId = ShiroKit.getUser().getId();
		logger.info("方法totalDeviceUsageTrend入参。。。userId:{}", userId);
		// 设备维度，登陆用户的数据权限
		HashMap<String, Object> queryParam = roleDataByDevice(userId, null);
		// 结果集格式："data":"使用率列表"，"days":"时间列表" 按时间升序
		Map<String, Object> resultMap = calcultaeDeviceDataService.totalDeviceUsageTrend(queryParam);
		logger.info("方法totalDeviceUsageTrend出参。。。resultMap:{}", resultMap);
		return resultMap;
	}

	// 登陆用户所有设备七天交易金额
	@RequestMapping("/totalDeviceTradeAmount")
	@ResponseBody
	public Object totalDeviceTradeAmount(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登陆用户数据权限
		Integer userId = ShiroKit.getUser().getId();
		logger.info("方法totalDeviceTradeAmount入参。。。userId:{}", userId);
		// 设备维度，登陆用户的数据权限
		HashMap<String, Object> queryParam = roleDataByDevice(userId, null);
		// 结果集格式："data":"交易金额列表"，"days":"时间列表" 按时间升序
		Map<String, Object> resultMap = calcultaeDeviceDataService.totalDeviceTradeAmount(queryParam);
		logger.info("方法totalDeviceTradeAmount出参。。。resultMap:{}", resultMap);
		return resultMap;
	}

	// 所有设备运营数据
	@RequestMapping("/totalDeviceBusinessData")
	@ResponseBody
	public Object totalDeviceBusinessData(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登陆用户数据权限
		Integer userId = ShiroKit.getUser().getId();
		logger.info("方法totalDeviceBusinessData入参。。。userId:{}", userId);
		// 设备维度，登陆用户的数据权限
		HashMap<String, Object> queryParam = roleDataByDevice(userId, null);
		// 借出订单数key：historyOrders;归还订单数key:historyFinishOrders;交易总金额key:totalAmount
		Map<String, Object> resultMap = calcultaeDeviceDataService.totalDeviceBusinessData(queryParam);
		logger.info("方法totalDeviceBusinessData出参。。。resultMap:{}", resultMap);
		return resultMap;
	}

	// 店铺七天设备使用率排行 type=1
	// 店铺充电器数量 type=2
	@RequestMapping("/listCalculateMerchantData/{type}")
	@ResponseBody
	public Object listCalculateMerchantData(@PathVariable(value = "type") int type, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ResultCommandForBSTable<CalculateMerchantDataModel> result = new ResultCommandForBSTable<>();
		result.setResult(ResultCommandForBSTable.FAILED);
		if (type != 1 && type != 2) {
			logger.warn("方法listCalculateMerchantData type 类型不合法");
			return result;
		}
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		String order = request.getParameter("order");
		String offsetStr = request.getParameter("offset");
		String limitStr = request.getParameter("limit"); // 每页多少行..
		Long offset = 0L;
		logger.info(String.format("获取店铺列表信息数据  userId:%d,order:%s,offsetStr:%s,limitStr:%s", userId, order, offsetStr,
				limitStr));
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
		order = null;
		// 2. 初始化参数
		HashMap<String, Object> pageFilter = new HashMap<>();
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);
		pageFilter.put("limit", limit);

		// 流程控制，注意break！！！！！！
		String filter = "", filterDevice = "";
		while (true) {
			// 权限
			if (userId != null && userId.intValue() == 1) {
				// 管理员有全部权限admin...
				break;
			}
			// 没有绑定数据权限1
			List<Integer> listForRole = ShiroKit.getUser().getRoleList();
			if (listForRole == null || listForRole.size() <= 0) {
				filter = "1<>1";
				pageFilter.put("filter", " and " + filter);
				break;
			}
			// 没有绑定数据权限2
			List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
			if (list == null || list.size() <= 0) {
				filter = "1<>1";
				pageFilter.put("filter", " and " + filter);
				break;
			}
			for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
				filter = (StringUtils.isEmpty(filter))
						? String.format("mi.id=%d or mi.parent_merchant_id=%d",
								roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId())
						: String.format("mi.id=%d or mi.parent_merchant_id=%d or %s",
								roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
								filter);
				// 以设备维度，查询商户所属关系
//				filterDevice = FilterUtils.buildDeviceFilter(filterDevice, roleMerchantRefInfoModel);
			}
			if (!StringUtils.isEmpty(filter)) {
				filter = String.format(" and ((%s) or mi.user_id=%d)", filter, userId);
				pageFilter.put("filter", filter);
			} else {
				filter = String.format(" and mi.user_id=%d", userId);
				pageFilter.put("filter", filter);
			}

			if (!StringUtils.isEmpty(filterDevice)) {
				pageFilter.put("filter", String.format("%s or (%s) ", filter, filterDevice));
			}
			break;
		}
		logger.info("获取店铺列表信息数据,查询条件pageFilter:{}", pageFilter);

		if (type == 1) {
			result = calculateMerchantDataService.list7daysUsageRate(pageFilter);
		} else {
			result = calculateMerchantDataService.listCalculateCharger(pageFilter);
		}
		result.setResult(ResultCommandForBSTable.SUCCESS);
		logger.info("方法listCalculateMerchantData出参。。。result:{}", result);
		return result;
	}

	// 登陆用户所有设备七天使用率
	@RequestMapping("/moreUsageTrendTopData")
	public String moreUsageTrendTopData(HttpServletRequest request, HttpServletResponse response, Model model) {
		return PREFIX + "usageTrend7Days.html";
	}

	// 登陆用户所有设备数量排名
	@RequestMapping("/moreDeviceCountTopData")
	public String moreDeviceCountTopData(HttpServletRequest request, HttpServletResponse response, Model model) {
		return PREFIX + "deviceCount.html";
	}

	// 有七天或30天未使用的设备信息
	@RequestMapping("/nousedChargerFewDaysUI/{days}")
	public String nousedChargerFewDaysUI(@PathVariable(value = "days") int days, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("days", days);
		return PREFIX + "nousedChargerFewDays.html";
	}

	// 连续七天三十天未使用充电器列表
	@RequestMapping("/nousedChargerFewDays/{days}")
	@ResponseBody
	public Object nousedChargerFewDays(@PathVariable(value = "days") int days,HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("方法nousedChargerFewDays入参。。。");
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		String order = request.getParameter("order");
		String offsetStr = request.getParameter("offset");
		String limitStr = request.getParameter("limit"); // 每页多少行
		Long offset = 0L;
		logger.info(String.format("获取连续七天未使用商家列表  userId:%d,order:%s,offsetStr:%s,limitStr:%s", userId, order,
				offsetStr, limitStr));
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
		order = null;
		// 2. 初始化参数
		HashMap<String, Object> pageFilter = new HashMap<>();
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);
		pageFilter.put("limit", limit);
		pageFilter.put("days", days);
		initFilterMapForRight(pageFilter, "share_device_info.");
		ResultCommandForBSTable<ChargerFewDayNotUsedBO> resultPage = calculateMerchantDataService
				.nousedChargerFewDays(pageFilter);
		logger.info("方法nousedChargerFewDays出参。。。resultPage:{}", resultPage);
		return resultPage;
	}

	// 连续七天未使用商家列表
	@RequestMapping("/nouseByMerchantOf7DaysUI")
	public String nouseByMerchantOf7DaysUI(HttpServletRequest request, HttpServletResponse response, Model model) {
		return PREFIX + "nouseByMerchantOf7Days.html";
	}

	// 连续七天未使用商家列表
	@RequestMapping("/nouseByMerchantOf7Days")
	@ResponseBody
	public Object nouseByMerchantOf7Days(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("方法nouseByMerchantOf7Days入参。。。");
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		String order = request.getParameter("order");
		String offsetStr = request.getParameter("offset");
		String limitStr = request.getParameter("limit"); // 每页多少行..
		Long offset = 0L;
		logger.info(String.format("获取连续七天未使用商家列表  userId:%d,order:%s,offsetStr:%s,limitStr:%s", userId, order,
				offsetStr, limitStr));
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
		order = null;
		// 2. 初始化参数
		HashMap<String, Object> pageFilter = new HashMap<>();
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);
		pageFilter.put("limit", limit);
		initFilterMapForRight(pageFilter, "share_device_info.");
		ResultCommandForBSTable<DeviceNoUseOfMerchantBO> resultPage = calculateMerchantDataService
				.nouseByMerchantOf7Days(pageFilter);
		logger.info("方法nouseByMerchantOf7Days出参。。。resultPage:{}", resultPage);
		return resultPage;
	}

	/**
	 * 以设备维度过滤登陆用户的数据权限
	 * 
	 * @param userId           用户id
	 * @param deviceTableAlias 设备表别名
	 * @return
	 */
	private HashMap<String, Object> roleDataByDevice(Integer userId, String deviceTableAlias) {

		HashMap<String, Object> pageFilter = new HashMap<>();
		deviceTableAlias = StringUtils.isEmpty(deviceTableAlias) ? "" : deviceTableAlias + ".";
		// 流程控制，注意break！！！！！！
		String filter = "";
		while (true) {
			if (userId != null && userId.intValue() == 1) {
				// 管理员有全部权限admin...
				break;
			}
			// 没有绑定数据权限1
			List<Integer> listForRole = ShiroKit.getUser().getRoleList();
			if (listForRole == null || listForRole.size() <= 0) {
				filter = "1<>1";
				pageFilter.put("filter", " and " + filter);
				break;
			}
			// 没有绑定数据权限2
			List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
			if (list == null || list.size() <= 0) {
				filter = "1<>1";
				pageFilter.put("filter", " and " + filter);
				break;
			}
			for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
				if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG1.getCode()
						.intValue()) {
					filter = (filter.isEmpty())
							? String.format("%sagents1_id=%d", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId())
							: String.format("%sagents1_id=%d or %s", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId(), filter);
				}
				if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode()
						.intValue()) {
					filter = (filter.isEmpty())
							? String.format("%sagents2_id=%d", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId())
							: String.format("%sagents2_id=%d or %s", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId(), filter);
				}
				if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode()
						.intValue()) {
					filter = (filter.isEmpty())
							? String.format("%sagents3_id=%d", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId())
							: String.format("%sagents3_id=%d or %s", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId(), filter);
				}
				if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.PUHUO_SHANG.getCode()
						.intValue()) {
					filter = (filter.isEmpty())
							? String.format("%sshopkeeper_id=%d", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId())
							: String.format("%sshopkeeper_id=%d or %s", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId(), filter);
				}
				if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.JIA_MENG_SHANG.getCode()
						.intValue()) {
					filter = (filter.isEmpty())
							? String.format("%salliance_business_id=%d", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId())
							: String.format("%salliance_business_id=%d or %s", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId(), filter);
				}
				if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.TERMINAL.getCode()
						.intValue()) {
					filter = (filter.isEmpty())
							? String.format("%sonline_merchant_id=%d", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId())
							: String.format("%sonline_merchant_id=%d or %s", deviceTableAlias,
									roleMerchantRefInfoModel.getMerchantId(), filter);
				}

			}

			if (!StringUtils.isEmpty(filter)) {
				pageFilter.put("filter", String.format(" and (%s)", filter));
			}
			break;
		}
		return pageFilter;
	}

}
