package com.stylefeng.guns.modular.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.system.service.ChargerInfoHelperService;
import com.stylefeng.guns.modular.system.service.IChargerService;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareChargerTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerForBatchModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;

/**
 * device控制器
 *
 * @author fengshuonan
 * @Date 2019-01-20 02:37:34
 */
@Controller
@RequestMapping("/charger")
public class ChargerController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

	private String PREFIX = "/system/charger/";

	@Autowired
	private IChargerService chargerService;
	/**
	 * 充电器表数据操作...
	 */
	@Autowired
	private ShareChargerModelBySelfMapper shareChargerModelBySelfMapper;
	/**
	 * 充电器数据...
	 */
	@Autowired
	private ShareChargerModelMapper shareChargerModelMapper;
	/**
	 * 设备数据库...
	 */
	@Autowired
	private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
	/**
	 * 数据库操作
	 */
	@Autowired
	@Qualifier("common.SequenceService")
	protected SequenceService seqService;
	/**
	 * 可选设备类型
	 */
	@Value("${sharehelper.shareDeviceTypes}")
	private String shareDeviceTypes;
	/**
	 * 充电器设备助手类
	 */
	@Autowired
	@Qualifier("gunsadmin.ChargerInfoHelperService")
	protected ChargerInfoHelperService chargerInfoHelperService;

	/**
	 * 跳转到device首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "charger.html";
	}

	/**
	 * 初始化设备编辑变量...
	 * 
	 * @param model
	 */
	private void initChargerInfo(Model model, ShareChargerModel chargerModel) {
		String selectChargerTypeOptions = "";
		List<ShareChargerTypeEnum> list = ShareChargerTypeEnum.getShareChargerTypeEnumList();
		ShareChargerTypeEnum item;
		String tmp="";
		if (shareDeviceTypes != null && !shareDeviceTypes.isEmpty()) {
			shareDeviceTypes = String.format(",%s,", shareDeviceTypes);
		}
		for (int i = 0; i < list.size(); i++) {
			item = list.get(i);
			if (shareDeviceTypes != null && !shareDeviceTypes.isEmpty()) {
				tmp = String.format(",%d,", item.getCode());
				if (shareDeviceTypes.indexOf(tmp) == -1) {
					continue;
				}
			}
			
			if (chargerModel != null && chargerModel.getChargerTypeId() != null
					&& chargerModel.getChargerTypeId().equals(item.getCode())) {
				selectChargerTypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
						selectChargerTypeOptions, item.getCode(), item.getDesc());

			} else {
				selectChargerTypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectChargerTypeOptions,
						item.getCode(), item.getDesc());
			}
		}
		model.addAttribute("selectChargerTypeOptions", selectChargerTypeOptions);
	}

	/**
	 * 跳转到添加device
	 */
	@RequestMapping("/charger_add")
	public String chargerAdd(Model model) {
		// 1、初始化
		initChargerInfo(model, null);
		// 2、跳转界面....
		return PREFIX + "charger_add.html";
	}

	/**
	 * 批量生成充电器
	 */
	@RequestMapping("/charger_batch_add")
	public String chargerBatchAdd(Model model) {
		// 1、初始化
		initChargerInfo(model, null);
		// 2、跳转界面....
		return PREFIX + "charger_batch_add.html";
	}

	/**
	 * 批量生成充电器
	 */
	@RequestMapping("/charger_batch_binddevice")
	public String chargerBatchBinddevice(Model model) {
		// 1、初始化
		initChargerInfo(model, null);
		// 2、跳转界面....
		return PREFIX + "charger_batch_binddevice.html";
	}
	/**
	 * 跳转到修改device
	 */
	@RequestMapping("/charger_update/{chargerId}")
	public String chargerUpdate(@PathVariable Long chargerId, Model model) {
		// 1.初始化
		ShareChargerModel charger = shareChargerModelMapper
				.selectByPrimaryKey(chargerId == null ? 0L : chargerId);
		initChargerInfo(model, charger);
		//
		model.addAttribute("item", charger);
		LogObjectHolder.me().set(charger);
		return PREFIX + "charger_edit.html";
	}

	/**
	 * 获取charger列表
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
		Long offset = 0L;
		logger.info(String.format("获取charger列表 list userId:%d,order:%s,offsetStr:%s,limitStr:%s,condition:%s", userId,
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
		order = "chrg.id";
		// 2. 初始他参数
		ResultCommandForBSTable<ShareChargerModel> result = new ResultCommandForBSTable<>();
		result.setResult(ResultCommandForBSTable.SUCCESS);
		HashMap<String, Object> pageFilter = new HashMap<>();
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);
		
		pageFilter.put("limit", limit);
		

		if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
			condition = condition.toLowerCase();
			try {
				if (condition.equalsIgnoreCase("Id")) {
					pageFilter.put("id", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("chargerTypeId")) {
					pageFilter.put("chargerTypeId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("deviceNo")) {
					pageFilter.put("deviceNo", Long.valueOf(conditionValue));
				}
			} catch (Exception e) {
				// TODO: handle excepti
			}
		}
		// 权限 管理员有全部权限admin...
		if (userId != null && userId.intValue() != 1) {
			List<Integer> listForRole = ShiroKit.getUser().getRoleList();
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
									? String.format("dev.agents1_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("dev.agents1_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
											filter);
						} else if (roleMerchantRefInfoModel.getMerchantType()
								.intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("dev.agents2_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("dev.agents2_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
											filter);
						} else if (roleMerchantRefInfoModel.getMerchantType()
								.intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("dev.agents3_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("dev.agents3_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
											filter);
						} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.PUHUO_SHANG
								.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("dev.shopkeeper_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("dev.shopkeeper_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
											filter);
						} else if (roleMerchantRefInfoModel.getMerchantType()
								.intValue() == MerchantTypeEnum.JIA_MENG_SHANG.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("dev.alliance_business_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("dev.alliance_business_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.TERMINAL
								.getCode().intValue()) {
							filter = (filter.isEmpty())
									? String.format("dev.online_merchant_id=%d", roleMerchantRefInfoModel.getMerchantId())
									: String.format("dev.online_merchant_id=%d or %s",
											roleMerchantRefInfoModel.getMerchantId(), filter);
						}
					}
					if (!filter.isEmpty()) {
						pageFilter.put("filter", String.format(" and (%s)", filter));
					}
				}
			}
		}
		// 3.
		Long total = shareChargerModelBySelfMapper.countByConditionForPage(pageFilter);
		List<ShareChargerModel> list = shareChargerModelBySelfMapper.selectByConditionForPage(pageFilter);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	/**
	 * 初始化充数...
	 * 
	 * @param deviceInfo
	 * @param checkFee
	 *            是否判断费用
	 * @param checkMerch
	 *            是否判断商户
	 * @return
	 */
	public Boolean validateChargerInfoForSave(ShareChargerModel chargerModel,ResultCommandBaseObject<Object> result) {
		if (result == null) {
			result = new ResultCommandBaseObject<>();
		}
		if (chargerModel == null) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("请输入选择正确的充电设备！");
			return false;
		}
		if (chargerModel.getChargerTypeId() == null) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("充电器类型不能为空，请选择正确的充电器类型！");
			return false;
		}
		ShareChargerTypeEnum chargerTypeEnum=ShareChargerTypeEnum.getShareChargerTypeEnumByCode(chargerModel.getChargerTypeId());
		if(chargerTypeEnum==null){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("选择的充电器类型系统中不存在，请选择正确的充电器类型！");
			return false;
		}
		chargerModel.setChargerTypeName(chargerTypeEnum.getDesc());
		if(chargerModel.getDeviceId()!=null&&chargerModel.getDeviceId()>0){
			ShareDeviceInfoModel deviceInfo=shareDeviceInfoModelMapper.selectByPrimaryKey(chargerModel.getDeviceId());
			if(deviceInfo==null){
				String msg=String.format("你绑定的充电设备编号(%d)在系统中不存在，请绑定正确的充电设备编号！",chargerModel.getDeviceId());
				logger.info(msg);
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage(msg);
				return false;
			}
			//判断是否正确...
			if(!ShareDeviceTypeEnum.validateDeviceTypeIdWithChargerTypeId(
					deviceInfo.getDeviceTypeId(), chargerModel.getChargerTypeId())){
				String msg="你选择的充电器类型和绑定的设备类型不一至,请选择正确的设备类型！";
				logger.info(msg);
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage(msg);
				return false;
			}
		}
		if(chargerModel.getPwdIndex()==null||chargerModel.getPwdIndex()<0L){
			chargerModel.setPwdIndex(0L);
		}
		return true;
	}

	/**
	 * 新增device
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(ShareChargerModel charger) {
		Integer userId = ShiroKit.getUser().getId();
		logger.info(String.format("新增device---add,userId:%d",userId));
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		try {
			//0.  
			if(!validateChargerInfoForSave(charger,result)){
				return result;
			}
			//1. 得到id
			Long id=seqService.getShareChargerSeq(charger.getChargerTypeId());
			charger.setId(id);
			//2.
			Date date=new Date();
			charger.setCreateDateTime(date);
			charger.setCreateId(userId!=null?0L:userId.longValue());
			charger.setPwdIndex(0L);
			shareChargerModelMapper.insert(charger);
			
			result.setMessage("添加成功!");
			result.setResult(ResultCommandBaseObject.SUCCESS);
			return result;
		} catch (Exception e) {
			logger.error("新增device---add",e);
			// TODO: handle exception
			result.setMessage("添加失败!");
			result.setResult(ResultCommandBaseObject.SUCCESS);
			return result;
		}
	}

	/**
	 * 删除device
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer chargerId) {
		chargerService.deleteById(chargerId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改charger
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(ShareChargerModel charger) {
		Integer userId = ShiroKit.getUser().getId();
		logger.info(String.format("修改---update,userId:%d",userId));
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		try {
			//0.  
			if(!validateChargerInfoForSave(charger,result)){
				return result;
			}
			if(charger.getId()==null||charger.getId()<=0){
				result.setMessage("请选择要修改的充电器!");
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			//1. 得到id
			Date date=new Date();
			charger.setCreateDateTime(date);
			charger.setCreateId(userId!=null?0L:userId.longValue());
			charger.setRefactorIdx(null);
			shareChargerModelMapper.updateByPrimaryKeySelective(charger);

			result.setMessage("修改成功!");
			result.setResult(ResultCommandBaseObject.SUCCESS);
			return result; 
		} catch (Exception e) {
			logger.error("修改充电器---add",e);
			// TODO: handle exception
			result.setMessage("修改充电器失败!");
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}
	}

	/**
	 * 批量生成生成设备信息
	 */
	@RequestMapping(value = "/batchAddSubmit")
	@ResponseBody
	public Object batchAddSubmit(ShareChargerForBatchModel shareCharegerModel) {
		logger.info("批量生成生成设备信息-batchAddSubmit{}", shareCharegerModel);
		String msg = "";
		// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		Boolean validate = validateChargerInfoForSave(shareCharegerModel,  result);
		if (!validate) {
			return result;
		}
		shareCharegerModel.setCreateId(userId.longValue());
		shareCharegerModel.setUpdateDateTime(new Date());
		// 批量生成...
		try {
			Long startChargerId = chargerInfoHelperService.batchAddChargerInfo(shareCharegerModel, userId);
			msg = String.format("充电器(%d~%d)批量生成成功！", startChargerId, startChargerId + shareCharegerModel.getChargerQty()-1);
			logger.info(msg);
			result.setMessage(msg);
			result.setResult(ResultCommandBaseObject.SUCCESS);
			return result;
		} catch (Exception e) {
			logger.error("批量生成充电器失败-batchAddSubmit", e);
			// TODO: handle exception
			result.setMessage(e.getMessage());
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}
	}

	/**
	 * 批量绑定设备.
	 */
	@RequestMapping(value = "/batchBindDevice")
	@ResponseBody
	public Object batchBindDevice(ShareChargerForBatchModel chargerModel) {
		Integer userId = ShiroKit.getUser().getId();
		logger.info(String.format("批量绑定设备---update,userId:%d",userId));
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		try {
			String msg="";
			//0. 判断充电器...
			if(chargerModel==null||chargerModel.getDeviceId()==null){
				result.setMessage("请输入充电设备编号!");
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			ShareDeviceInfoModel deviceInfoModel=
					shareDeviceInfoModelMapper.selectByPrimaryKey(chargerModel.getDeviceId());
			if(deviceInfoModel==null){
				result.setMessage("请充电设备编号在系统中不存在!");
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			ShareChargerTypeEnum chargerTypeEnum=
					ShareChargerTypeEnum.getShareChargerTypeEnumByCode(deviceInfoModel.getDeviceTypeId());
			if(chargerTypeEnum==null){
				result.setMessage("绑定的设备类型未找到对应的充电器类型!");
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
			ShareChargerModel chargerTmpModel=new ShareChargerModel();
			chargerTmpModel.setUpdateDateTime(new Date());
			chargerTmpModel.setCreateId(userId==null?0L:userId.longValue());
			chargerTmpModel.setDeviceId(deviceInfoModel.getId());
			if (chargerModel.getBatchTypeForOperate() == 1) {
				//根多个乱的编辑。。。
				if (chargerModel.getChargerNoStr() == null || StringUtils.isEmpty(chargerModel.getChargerNoStr())) {
					logger.info(String.format("`批量商户-getChargerNoStr:%s", chargerModel.getChargerNoStr()));
					// TODO: handle exception
					result.setMessage("请输入要绑定商户的设备号，多个用逗号分隔！");
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}
				//2. 开始生
				String[] aryChargerNo=chargerModel.getChargerNoStr().split(",");
				ShareChargerModel tmpModel=null;
				String msgForList="";
				String msgChargerTypeForList="";
				Long chargerId=null;
				List<Long> listForChargerNo=new ArrayList<>();
				
				for(int i=0;i<aryChargerNo.length;i++){
					try {
						chargerId=Long.parseLong(aryChargerNo[i]);
						tmpModel=shareChargerModelMapper.selectByPrimaryKey(chargerId);
						if(tmpModel==null){
							msgForList=(msgForList.isEmpty())?
									aryChargerNo[i]:String.format("%s,%s", msgForList,aryChargerNo[i]);
						}else{
							if(tmpModel.getChargerTypeId()==null||chargerTypeEnum.getCode()!=tmpModel.getChargerTypeId().intValue()){
								msgChargerTypeForList=(msgChargerTypeForList.isEmpty())?
										aryChargerNo[i]:String.format("%s,%s", msgChargerTypeForList,aryChargerNo[i]);
							}else{
								listForChargerNo.add(tmpModel.getId());	
							}
						}
					} catch (Exception e) {
						msgForList=(msgForList.isEmpty())?
								aryChargerNo[i]:String.format("%s,%s", msgForList,aryChargerNo[i]);
					}
				}
				if(!msgForList.isEmpty()){
					msg=String.format("下例充电器编号在系统中不在存，请输入正确的充电器编号!\n\r%s", msgForList);
					logger.info(msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}
				if(!msgChargerTypeForList.isEmpty()){
					msg=String.format("下例充电器对应的类型和要绑定的设备类型不一至，请输入正确的充电器编号!\n\r%s", msgChargerTypeForList);
					logger.info(msg);
					result.setMessage(msg);
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}
				//开始
				shareChargerModelBySelfMapper.batchBindDeviceNoByChargerNoList(chargerTmpModel,listForChargerNo);
			}else{
				//根开始结束充电器编号批理绑定
				if (chargerModel.getStartChargerNo() == null || chargerModel.getEndChargerNo()==null) {	
					logger.info(String.format("`根开始结束充电器编号-getStartChargerNo:%d-%d"
							, chargerModel.getStartChargerNo(),chargerModel.getEndChargerNo()));
					// TODO: handle exception
					result.setMessage("请输入要绑定的开始和结束充电器编号！");
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}
				if (chargerModel.getStartChargerNo()>chargerModel.getEndChargerNo()) {	
					logger.info(String.format("`根开始结束充电器编号-getStartChargerNo:%d-%d"
							, chargerModel.getStartChargerNo(),chargerModel.getEndChargerNo()));
					// TODO: handle exception
					result.setMessage("输入开始充电器编号大于结束充电器编号,请输入正确的开始结束充电器编号！");
					result.setResult(ResultCommandBaseObject.FAILED);
					return result;
				}				
				//开始
				shareChargerModelBySelfMapper.batchBindDeviceNoByStartEndDeviceNo(chargerTmpModel,
										chargerModel.getStartChargerNo(),chargerModel.getEndChargerNo());
			}
			
			msg = "批量绑定设备编号成功！";
			logger.info(msg);
			result.setMessage(msg);
			result.setResult(ResultCommandBaseObject.SUCCESS);
			return result;
		} catch (Exception e) {
			logger.error("修改充电器---add",e);
			// TODO: handle exception
			result.setMessage("修改充电器失败!");
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}
	}
	/**
	 * 根据商户id查询商户
	 */
	@RequestMapping(value = "/selectDeviceTypeId")
	@ResponseBody
	public Object selectDeviceTypeId(HttpServletRequest request, HttpServletResponse response) {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		// return deviceInfoService.selectById(deviceInfoId);
		String deviceId = request.getParameter("deviceId");
		logger.info(String.format("selectDeviceTypeId-根据商户id查询商户deviceId-%s", deviceId));
		try {
			// 1. 获取用户头像
			Integer id = ShiroKit.getUser().getId(); 
			// 2. 商户类型id
			Long iDeviceId = Long.parseLong(deviceId);
			// 3. 查询商户id
			ShareDeviceInfoModel infoModel = shareDeviceInfoModelMapper.selectByPrimaryKey(iDeviceId);
			if (infoModel == null) {
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage(String.format("设备编号(%s)在系统中不存在!", iDeviceId));
			} else {
				result.setResponseInfo(infoModel);
				result.setResult(ResultCommandBaseObject.SUCCESS);
			}
			return result;
		} catch (Exception e) {
			logger.info("selectDeviceTypeId ", e);
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("查询设备异常！");
			return result;
		}
	}
	/**
	 * device详情
	 */
	@RequestMapping(value = "/detail/{chargerId}")
	@ResponseBody
	public Object detail(@PathVariable("chargerId") Integer chargerId) {
		return chargerService.selectById(chargerId);
	}
}
