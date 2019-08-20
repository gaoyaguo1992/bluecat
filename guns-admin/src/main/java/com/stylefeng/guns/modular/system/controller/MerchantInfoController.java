package com.stylefeng.guns.modular.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.system.service.IMerchantInfoService;
import com.stylefeng.guns.modular.wxApplication.service.MerchantInfoHelperService;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.IndustryCategoryEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelKey;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantClientTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantProfitFlagEnum;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;
import com.stylefeng.guns.sharecore.modular.system.utils.FilterUtils;

/**
 * merchantInfo控制器
 *
 * @author fengshuonan
 * @Date 2019-01-20 03:09:36
 */
@Controller
@RequestMapping("/merchantInfo")
public class MerchantInfoController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

	private String PREFIX = "/system/merchantInfo/";

	@Autowired
	private IMerchantInfoService merchantInfoService;
	/**
	 * 数据库操作
	 */
	@Autowired
	private MerchantInfoModelMapper merchantInfoModelMapper;
	/**
	 * 数据库操作...
	 */
	@Autowired
	private MerchantInfoModelBySelfMapper merchantInfoModelBySelfMapper;
	/**
	 * 客户信息...
	 */
	@Autowired
	private CustInfoModelMapper custInfoModelMapper;

	@Autowired
	@Qualifier("common.SequenceService")
	protected SequenceService seqService;

	@Autowired
	@Qualifier("gunsadmin.MerchantInfoHelperService")
	private MerchantInfoHelperService merchantInfoHelperService;
	/**
	 * 客户商户关系表..
	 */
	@Autowired
	private CustMerchantRefInfoModelMapper custMerchantRefInfoModelMapper;

	/**
	 * 跳转到merchantInfo首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "merchantInfo.html";
	}

	/**
	 * 初始化设备编辑变量...
	 * 
	 * @param model
	 */
	private void initInfo(Model model, MerchantInfoModel merchantInfo) {
		String selectMerchantTypeOptions = "";
		List<MerchantTypeEnum> list = MerchantTypeEnum.getMerchantTypeEnum();
		MerchantTypeEnum item;
		for (int i = 0; i < list.size(); i++) {
			item = list.get(i);
			if (merchantInfo != null && merchantInfo.getMerchantType() != null
					&& merchantInfo.getMerchantType().equals(item.getCode())) {
				selectMerchantTypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
						selectMerchantTypeOptions, item.getCode(), item.getDesc());

			} else {
				selectMerchantTypeOptions = String.format("%s<option value=\"%d\">%s</option>",
						selectMerchantTypeOptions, item.getCode(), item.getDesc());
			}
		}
		model.addAttribute("selectMerchantTypeOptions", selectMerchantTypeOptions);

		String selectIndustryCodeOptions = "";
		List<IndustryCategoryEnum> listForIndustry = IndustryCategoryEnum.getIndustryCategoryEnumForList();
		IndustryCategoryEnum itemForIndustry;
		for (int i = 0; i < listForIndustry.size(); i++) {
			itemForIndustry = listForIndustry.get(i);
			if (merchantInfo != null && merchantInfo.getIndustryCategoryCode() != null
					&& merchantInfo.getIndustryCategoryCode().equals(itemForIndustry.getCode())) {
				selectIndustryCodeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
						selectIndustryCodeOptions, itemForIndustry.getCode(), itemForIndustry.getDesc());
			} else {
				selectIndustryCodeOptions = String.format("%s<option value=\"%d\">%s</option>",
						selectIndustryCodeOptions, itemForIndustry.getCode(), itemForIndustry.getDesc());
			}
		}
		model.addAttribute("selectIndustryCodeOptions", selectIndustryCodeOptions);

		String selectMerchantStatusOptions = "";
		List<MerchantStatusEnum> listForStatus = MerchantStatusEnum.getMerchantStatusEnum();
		MerchantStatusEnum itemForMerchantstatus;
		for (int i = 0; i < listForStatus.size(); i++) {
			itemForMerchantstatus = listForStatus.get(i);
			if (merchantInfo != null && merchantInfo.getStatus() != null
					&& merchantInfo.getStatus().equals(itemForMerchantstatus.getCode())) {
				selectMerchantStatusOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
						selectMerchantStatusOptions, itemForMerchantstatus.getCode(), itemForMerchantstatus.getDesc());
			} else {
				selectMerchantStatusOptions = String.format("%s<option value=\"%d\">%s</option>",
						selectMerchantStatusOptions, itemForMerchantstatus.getCode(), itemForMerchantstatus.getDesc());
			}
		}
		model.addAttribute("selectMerchantStatusOptions", selectMerchantStatusOptions);

		String selectClientTypeOptions = "";
		List<MerchantClientTypeEnum> listForClientType = MerchantClientTypeEnum.getMerchantClientTypeEnum();
		MerchantClientTypeEnum itemForMerchantClientTypeEnum;
		for (int i = 0; i < listForClientType.size(); i++) {
			itemForMerchantClientTypeEnum = listForClientType.get(i);
			if (merchantInfo != null && merchantInfo.getClientType() != null
					&& merchantInfo.getClientType().equals(itemForMerchantClientTypeEnum.getCode())) {
				selectClientTypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
						selectClientTypeOptions, itemForMerchantClientTypeEnum.getCode(),
						itemForMerchantClientTypeEnum.getDesc());
			} else {
				selectClientTypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectClientTypeOptions,
						itemForMerchantClientTypeEnum.getCode(), itemForMerchantClientTypeEnum.getDesc());
			}
		}
		model.addAttribute("selectClientTypeOptions", selectClientTypeOptions);

		String selectProfitFlagOptions = "";
		List<MerchantProfitFlagEnum> listForProfitFlag = MerchantProfitFlagEnum.getMerchantStatusEnum();
		MerchantProfitFlagEnum itemForMerchantProfitFlagEnum;
		for (int i = 0; i < listForProfitFlag.size(); i++) {
			itemForMerchantProfitFlagEnum = listForProfitFlag.get(i);
			if (merchantInfo != null && merchantInfo.getAdvanceProfitFlag() != null
					&& merchantInfo.getAdvanceProfitFlag().equals(itemForMerchantProfitFlagEnum.getCode())) {
				selectProfitFlagOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
						selectProfitFlagOptions, itemForMerchantProfitFlagEnum.getCode(),
						itemForMerchantProfitFlagEnum.getDesc());
			} else {
				selectProfitFlagOptions = String.format("%s<option value=\"%d\">%s</option>", selectProfitFlagOptions,
						itemForMerchantProfitFlagEnum.getCode(), itemForMerchantProfitFlagEnum.getDesc());
			}
		}
		model.addAttribute("selectProfitFlagOptions", selectProfitFlagOptions);
		// 提现方式。。
		model.addAttribute("selectMerWithdrawOptions", "<option value=\"1\">实时提现实时到账</option>");

	}

	/**
	 * 跳转到添加merchantInfo
	 */
	@RequestMapping("/merchantInfo_add")
	public String merchantInfoAdd(Model model) {
		// 初始他信息..
		initInfo(model, null);
		// 得到父商户
		String parentMerchantId = "";
		Integer userId = ShiroKit.getUser().getId();
		if (!userId.equals(new Integer(1))) {
			List<Integer> listForRole = ShiroKit.getUser().getRoleList();
			if (listForRole != null && listForRole.size() >= 0) {
				List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
				for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
					if (roleMerchantRefInfoModel.getMerchantType() != null && roleMerchantRefInfoModel.getMerchantType()
							.equals(MerchantTypeEnum.DAI_LI_SHANG1.getCode().byteValue())) {
						parentMerchantId = String.format("%d", roleMerchantRefInfoModel.getMerchantId());
						break;
					}
				}
			}
		}
		model.addAttribute("parentMerchantId", parentMerchantId);

		return PREFIX + "merchantInfo_add.html";
	}

	/**
	 * 跳转到修改merchantInfo
	 */
	@RequestMapping("/merchantInfo_update/{merchantInfoId}")
	public String merchantInfoUpdate(@PathVariable Long merchantInfoId, Model model) {
		MerchantInfoModel merchantInfo = merchantInfoModelMapper.selectByPrimaryKey(merchantInfoId);
		initInfo(model, merchantInfo);
		model.addAttribute("item", merchantInfo);
		LogObjectHolder.me().set(merchantInfo);
		return PREFIX + "merchantInfo_edit.html";
	}

	/**
	 * 导出交易Excel表格
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		String condition = request.getParameter("condition");// 查询条件..
		String conditionValue = request.getParameter("conditionValue");// 查询结果
		HashMap<String, Object> pageFilter = new HashMap<>();

		if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
			condition = condition.toLowerCase();
			try {
				if (condition.equalsIgnoreCase("merchantId")) {
					pageFilter.put("merchantId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("merchantName")) {
					pageFilter.put("merchantName", conditionValue);
				} else if (condition.equalsIgnoreCase("personName")) {
					pageFilter.put("personName", conditionValue);
				} else if (condition.equalsIgnoreCase("telNo")) {
					pageFilter.put("telNo", conditionValue);
				} else if (condition.equalsIgnoreCase("merchantType")) {
					pageFilter.put("merchantType", conditionValue);
				}
			} catch (Exception e) {
			}
		}
		// 2处理 权限 管理员有全部权限admin...
		if (userId != null && userId.intValue() != 1) {
			String filter = "", filterDevice = "";
			List<Integer> listForRole = ShiroKit.getUser().getRoleList();
			if (listForRole == null || listForRole.size() <= 0) {
				filter = "1<>1";
			} else {
				List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
				if (list == null || list.size() <= 0) {
					filter = "1<>1";
				} else {
					for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
						filter = (filter.isEmpty()) ? String.format("mi.id=%d or mi.parent_merchant_id=%d",
								roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId())
								: String.format("mi.id=%d or mi.parent_merchant_id=%d or %s",
										roleMerchantRefInfoModel.getMerchantId(),
										roleMerchantRefInfoModel.getMerchantId(), filter);
						// 以设备维度，查询商户所属关系
						filterDevice = FilterUtils.buildDeviceFilter(filterDevice, roleMerchantRefInfoModel);

					}
				}
			}

			if (!filter.isEmpty()) {
				filter = String.format(" and ((%s) or mi.user_id=%d)", filter, userId);
				pageFilter.put("filter", filter);
			} else {
				filter = String.format(" and mi.user_id=%d", userId);
				pageFilter.put("filter", filter);
			}

			if (!filterDevice.isEmpty()) {
				pageFilter.put("filter", String.format("%s or (%s) ", filter, filterDevice));
			}
		}
		// 3.导出
		try {
			merchantInfoService.exportExcel(response, pageFilter);
		} catch (Exception e) {
			logger.error("设备数据导出异常！error:", e);
		}
	}

	/**
	 * 获取merchantInfo列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		String order = request.getParameter("order");
		String offsetStr = request.getParameter("offset");
		String limitStr = request.getParameter("limit"); // 每页多少行..
		String condition = request.getParameter("condition");// 查询条件..
		String conditionValue = request.getParameter("conditionValue");// 查询结果
		String merchantTypeId = request.getParameter("merchantTypeId");// 商户类型id

		Long offset = 0L;
		logger.info(String.format("获取device列表 list userId:%d,order:%s,offsetStr:%s,limitStr:%s,condition:%s", userId,
				order, offsetStr, limitStr, condition));
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
		// 2. 初始他参数
		ResultCommandForBSTable<MerchantInfoModel> result = new ResultCommandForBSTable<>();
		result.setResult(ResultCommandForBSTable.SUCCESS);
		HashMap<String, Object> pageFilter = new HashMap<>();
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);

		// pageFilter.put("id", 10000001L);
		pageFilter.put("limit", limit);

		if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
			condition = condition.toLowerCase();
			try {
				if (condition.equalsIgnoreCase("merchantId")) {
					pageFilter.put("merchantId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("merchantName")) {
					pageFilter.put("merchantName", conditionValue);
				} else if (condition.equalsIgnoreCase("personName")) {
					pageFilter.put("personName", conditionValue);
				} else if (condition.equalsIgnoreCase("telNo")) {
					pageFilter.put("telNo", conditionValue);
				} else if (condition.equalsIgnoreCase("merchantType")) {
					pageFilter.put("merchantType", conditionValue);
				} else if (condition.equalsIgnoreCase("parentMerchantId")) {
					pageFilter.put("parentMerchantId", conditionValue);
				}
			} catch (Exception e) {
				// TODO: handle excepti
			}
		}
		//按照商户类型来过虑
		if(merchantTypeId!=null&&!merchantTypeId.isEmpty()&&!merchantTypeId.equals("0")){
			pageFilter.put("merchantTypeId", merchantTypeId);
		}
		// 处理 权限 管理员有全部权限admin...
		if (userId != null && userId.intValue() != 1) {
			String filter = "", filterDevice = "";
			List<Integer> listForRole = ShiroKit.getUser().getRoleList();
			if (listForRole == null || listForRole.size() <= 0) {
				filter = "1<>1";
			} else {
				List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
				if (list == null || list.size() <= 0) {
					filter = "1<>1";
				} else {
					for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
						filter = (filter.isEmpty()) ? String.format("mi.id=%d or mi.parent_merchant_id=%d",
								roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId())
								: String.format("mi.id=%d or mi.parent_merchant_id=%d or %s",
										roleMerchantRefInfoModel.getMerchantId(),
										roleMerchantRefInfoModel.getMerchantId(), filter);
						// 以设备维度，查询商户所属关系
						filterDevice = FilterUtils.buildDeviceFilter(filterDevice, roleMerchantRefInfoModel);

					}
				}
			}

			if (!filter.isEmpty()) {
				filter = (!filterDevice.isEmpty())?						
							String.format(" and ((%s) or mi.user_id=%d or %s)", filter, userId,filterDevice):
							String.format(" and ((%s) or mi.user_id=%d)", filter, userId);
				pageFilter.put("filter", filter);
			} else {
				filter = String.format(" and mi.user_id=%d", userId);
				filter = (!filterDevice.isEmpty())?						
						String.format(" and (mi.user_id=%d or %s)", userId,filterDevice):
						String.format(" and mi.user_id=%d", userId);
				pageFilter.put("filter", filter);
			}
		}
		// 3.
		Long total = merchantInfoModelBySelfMapper.countByConditionForPage(pageFilter);
		List<MerchantInfoModel> list = merchantInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	/**
	 * 初始化保存..
	 * 
	 * @param deviceInfo
	 * @param checkFee   是否判断费用
	 * @param checkMerch 是否判断商户
	 * @return
	 */
	public Boolean validateInfoForSave(MerchantInfoModel merchantInfoModel, ResultCommandBaseObject<Object> result) {
		if (result == null) {
			result = new ResultCommandBaseObject<>();
		}
		if (merchantInfoModel == null) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("请输入商户信息！");
			return false;
		}
		if (merchantInfoModel.getMerchantType() == null) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("商户类型不能为空，请选择输入正确的商户类型！");
			return false;
		}
		if (merchantInfoModel.getIndustryCategoryCode() == null) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("商户所属行业不能为空，请选择正确的商户所属行业！");
			return false;
		}
		// 商户类型
		merchantInfoModel.setMerchantTypeCn(MerchantTypeEnum.getDesc(merchantInfoModel.getMerchantType().intValue()));
		merchantInfoModel.setIndustryCategoryCn(
				IndustryCategoryEnum.getDesc(merchantInfoModel.getIndustryCategoryCode().intValue()));
		// 父商户号不能为空...
		if (merchantInfoModel.getParentMerchantId() != null) {
			MerchantInfoModel merchantInfoTmp = merchantInfoModelMapper
					.selectByPrimaryKey(merchantInfoModel.getParentMerchantId());
			if (merchantInfoTmp == null) {
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage("输入的父商户号在系统中不存在...！");
				return false;
			}
		}
		//
		if (merchantInfoModel.getSettlementAccount() != null && !merchantInfoModel.getSettlementAccount().isEmpty()) {
			Long custNo = 0L;
			try {
				custNo = Long.parseLong(merchantInfoModel.getSettlementAccount());
			} catch (Exception e) {
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage("您输入的客户编号不正确，请输入正确的客户编号！");
				return false;
			}
			CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(custNo);
			if (custInfoModel == null) {
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage("您输入的客户编号在系统中不存在，请输入正确的客户编号！");
				return false;
			}
		}

		return true;
	}

	/**
	 * 新增merchantInfo
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(MerchantInfoModel merchantInfo) {
		Integer userId = ShiroKit.getUser().getId();
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		// 验检数据并对数据进行整理.
		Boolean validate = validateInfoForSave(merchantInfo, result);
		if (!validate) {
			return result;
		}
		try {

			merchantInfo.setCreateTime(new Date());
			merchantInfo.setUpdateTime(new Date());
			merchantInfo.setUserId(String.format("%d", userId));
			merchantInfo = merchantInfoHelperService.registerMerchantInfo(merchantInfo);

			result.setResponseInfo(merchantInfo.getId());
			result.setResult(ResultCommandBaseObject.SUCCESS);
			result.setMessage("商户添加成功！");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("商户添加失败！%s", e.getMessage()));
			return result;
		}
	}

	/**
	 * 删除merchantInfo
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer merchantInfoId) {
		merchantInfoService.deleteById(merchantInfoId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改merchantInfo
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(MerchantInfoModel merchantInfo) {
		Integer userId = ShiroKit.getUser().getId();
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		// 1. 验检数据并对数据进行整理.
		Boolean validate = validateInfoForSave(merchantInfo, result);
		if (!validate) {
			return result;
		}
		if (merchantInfo.getId() == null) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("您选择的商户在系统中不存！");
		}
		// 2. 更新数据库
		merchantInfo.setUserId(String.format("%d", userId));
		merchantInfo.setCreateTime(new Date());
		merchantInfo.setUpdateTime(new Date());
		merchantInfoModelMapper.updateByPrimaryKey(merchantInfo);
		// 2.1判断是否要建立关联关系..
		if (merchantInfo.getSettlementAccount() != null && !merchantInfo.getSettlementAccount().isEmpty()) {
			Long custNo = 0L;
			try {
				custNo = Long.parseLong(merchantInfo.getSettlementAccount());
			} catch (Exception e) {
				// TODO: handle exception
			}
			CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(custNo);

			CustMerchantRefInfoModelKey custMerRefInfoModelKey = new CustMerchantRefInfoModelKey();
			custMerRefInfoModelKey.setCustNo(custNo);
			custMerRefInfoModelKey.setMerchantId(merchantInfo.getId());
			// 2.2 客户商户关系表..
			CustMerchantRefInfoModel custMerchantRefModel = custMerchantRefInfoModelMapper
					.selectByPrimaryKey(custMerRefInfoModelKey);
			if (custMerchantRefModel == null) {
				// 2.2.1 商户客户关系...
				CustMerchantRefInfoModel custMerchantRefModel1 = new CustMerchantRefInfoModel();
				custMerchantRefModel1.setCreateId(userId.longValue());
				custMerchantRefModel1.setCreateTime(new Date());
				custMerchantRefModel1.setCustNo(custNo);
				custMerchantRefModel1.setCustType((custInfoModel != null && custInfoModel.getCustType() != null)
						? custInfoModel.getCustType().byteValue()
						: new Integer(CustTypeEnum.CUST.getCode()).byteValue());
				custMerchantRefModel1.setIsDefault((byte) 0);
				custMerchantRefModel1.setMerchantId(merchantInfo.getId());
				custMerchantRefModel1.setMerchantName(merchantInfo.getName());
				custMerchantRefModel1.setMerchantType(merchantInfo.getMerchantType());
				custMerchantRefModel1.setMerchantTypeCn(merchantInfo.getMerchantTypeCn());
				custMerchantRefInfoModelMapper.insert(custMerchantRefModel1);
			}
		}

		// 3. 提交信息
		result.setResponseInfo(merchantInfo.getId());
		result.setResult(ResultCommandBaseObject.SUCCESS);
		result.setMessage("商户修改成功！");
		return result;
	}

	/**
	 * merchantInfo详情
	 */
	@RequestMapping(value = "/detail/{merchantInfoId}")
	@ResponseBody
	public Object detail(@PathVariable("merchantInfoId") Integer merchantInfoId) {
		return merchantInfoService.selectById(merchantInfoId);
	}

	/**
	 * 得到商户信息，根据商户id
	 */
	@RequestMapping(value = "/getMerchantInfoById")
	@ResponseBody
	public Object getMerchantInfoById(HttpServletRequest request, HttpServletResponse response) {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		// return deviceInfoService.selectById(deviceInfoId);
		String merchantId = request.getParameter("merchantId");
		logger.info(String.format("得到商户信息，根据商户idmerchantId-%s", merchantId));
		try {
			// 1. 获取用户头像

			// 2. 商户类型id
			Long lMerchantId = new Long(merchantId);
			// 3. 查询商户id
			MerchantInfoModel infoModel = merchantInfoModelMapper.selectByPrimaryKey(lMerchantId.longValue());
			if (infoModel == null) {
				logger.info(String.format("您输入的商户(%s)在系统中不存在，请输入正确的商户编号!", merchantId));
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage(String.format("您输入的商户(%s)在系统中不存在，请输入正确的商户编号!", merchantId));
			} else {
				result.setResponseInfo(infoModel);
				result.setResult(ResultCommandBaseObject.SUCCESS);
			}
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(String.format("selectMerchant-根据商户id查询商户merchantId-%s", merchantId), e);
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("查询商户异常！");
			return result;
		}
	}
}
