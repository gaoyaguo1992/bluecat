package com.stylefeng.guns.modular.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.system.dao.MerchantInfoMapper;
import com.stylefeng.guns.modular.system.model.WithdrawApplyRecord;
import com.stylefeng.guns.modular.system.service.IWithdrawApplyRecordService;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.common.utils.SysUtil;
import com.stylefeng.guns.sharecore.modular.system.constants.PayWayEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.CustAccountModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawApplyRecordModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustAccountAmtSumEnum;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoBo;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawApplyRecordDetailModel;
import com.stylefeng.guns.sharecore.modular.system.service.MerchantService;

/**
 * 商户提现订单控制器
 *
 * @author fengshuonan
 * @Date 2019-03-11 10:31:12
 */
@Controller
@RequestMapping("/withdrawApplyRecord")
public class WithdrawApplyRecordController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);


    private String PREFIX = "/system/withdrawApplyRecord/";

    @Autowired
    private IWithdrawApplyRecordService withdrawApplyRecordService;
    /**
     * 查询
     */
    @Autowired
    private WithdrawApplyRecordModelBySelfMapper withdrawApplyRecordModelBySelfMapper;
    /**
     * 商户mapper...
     */
    @Autowired
    private MerchantInfoModelMapper merchantInfoModelMapper;
    /**
     * 客户信息
     */
    @Autowired
    private CustInfoModelMapper custInfoModelMapper;
    /**
     * 客户账号..
     */
    @Autowired
    private CustAccountModelMapper custAccountModelMapper;
    /**
     * 商户mapper by self
     */
    @Autowired
    private MerchantInfoModelBySelfMapper merchantInfoModelBySelftMapper;
	/**
	 * 商户服务类
	 */
	@Autowired
	private MerchantService merchantService;
    /**
     * 跳转到商户提现订单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "withdrawApplyRecord.html";
    }

    /**
     * 跳转到添加商户提现订单
     */
    @RequestMapping("/withdrawApplyRecord_add")
    public String withdrawApplyRecordAdd() {
        return PREFIX + "withdrawApplyRecord_add.html";
    }

    /**
     * 跳转到修改商户提现订单
     */
    @RequestMapping("/withdrawApplyRecord_update/{withdrawApplyRecordId}")
    public String withdrawApplyRecordUpdate(@PathVariable Integer withdrawApplyRecordId, Model model) {
        /*WithdrawApplyRecord withdrawApplyRecord = withdrawApplyRecordService.selectById(withdrawApplyRecordId);
        model.addAttribute("item",withdrawApplyRecord);
        LogObjectHolder.me().set(withdrawApplyRecord);
        return PREFIX + "withdrawApplyRecord_edit.html";*/
    	
    	return null;
    }

    /**
     * 获取商户提现订单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response){
    	// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		String order = request.getParameter("order");
		String offsetStr = request.getParameter("offset");
		String limitStr = request.getParameter("limit"); // 每页多少行..
		String condition = request.getParameter("condition");// 查询条件..
		String conditionValue = request.getParameter("conditionValue");// 查询结果
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
		order = "app.id desc";
		// 2. 初始他参数
		ResultCommandForBSTable<WithdrawApplyRecordDetailModel> result = new ResultCommandForBSTable<>();
		result.setResult(ResultCommandForBSTable.SUCCESS);
		HashMap<String, Object> pageFilter = new HashMap<>();
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);
		pageFilter.put("limit", limit);
		
		if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
			condition = condition.toLowerCase();
			try {
				if (condition.equalsIgnoreCase("id")) {
					pageFilter.put("id", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("applyMerchantId")) {
					pageFilter.put("applyMerchantId", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("withdrawStatus")) {
					pageFilter.put("withdrawStatus",  Long.valueOf(conditionValue));
				}
			} catch (Exception e) {
				// TODO: handle excepti
			}
		}
		
		// 3.
		Long total = withdrawApplyRecordModelBySelfMapper.countByConditionForPage(pageFilter);
		List<WithdrawApplyRecordDetailModel> list = withdrawApplyRecordModelBySelfMapper.selectByConditionForPage(pageFilter);
		result.setRows(list);
		result.setTotal(total);
		return result;
    }

    /**
     * 新增商户提现订单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HttpServletRequest request, HttpServletResponse response) {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
    	String remark=request.getParameter("remark");
    	String aftTaxAmount=request.getParameter("aftTaxAmount");
    	String applyMerchantId=request.getParameter("applyMerchantId");
    	//1.接收参数    	
		Integer userId = ShiroKit.getUser().getId();
		logger.info("修改aftTaxAmount:{},remark:{},applyMerchantId:{}", aftTaxAmount,remark,applyMerchantId);
		if (!userId.equals(new Integer("1"))) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("只能系统管理员可能直接添加提现订单！");
			return false;
		}
		//
		if(aftTaxAmount==null||aftTaxAmount.equals("0")){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("输入的扣款金额不正确，请输入正确的扣金额！");
			return false;
		}
		BigDecimal bigAftTaxAmount=null;
		try {
			bigAftTaxAmount=new BigDecimal(aftTaxAmount);
		} catch (Exception e) {
			// TODO: handle exception
			bigAftTaxAmount=null;
		}
		
		if(bigAftTaxAmount==null){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("输入的扣款金额不正确，请输入正确的扣金额！");
			return false;
		}
		
		//2.判断输入的商户号是否正确..
		if(applyMerchantId==null||applyMerchantId.isEmpty()){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("只能系统管理员可能直接添加提现订单！");
			return false;
		}
		Long merchantId=0L;
		try {
			merchantId=Long.parseLong(applyMerchantId);
		} catch (Exception e) {
			merchantId=0L;
		}
		if(merchantId.equals(new Long(0))){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("输入的商户号id不正确，请输入正确的商户id！");
			return false;
		}
		MerchantInfoModel merchantInfoModel=merchantInfoModelMapper.selectByPrimaryKey(merchantId);
		if(merchantInfoModel==null){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("输入的商户号，在系统中不存在，请输入正确的商户id！");
			return false;
		}
		
		//3. 开始扣款...
		// 当前登录账
		//4.得到虚拟账户..
		CustInfoModel custInfoForSettle =
				custInfoModelMapper.selectMerchantCustInfoByMerchantId(merchantInfoModel.getId());
		if (custInfoForSettle == null) {
			result.setMessage("商户未创建虚拟账户，请联系管理员!");
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}

		// 根据客户号查看该客户对应的虚户信息
		CustAccountModel custAccountModel = custAccountModelMapper.selectAccountByCustNo(
				custInfoForSettle.getCustNo(), CustAccountTypeEnum.RECHARGERACCOUNT.getCode(),
				CustAccountAmtSumEnum.SUMED.getCode());
		if (custAccountModel == null) {
			result.setErrorCode(ResultCommandBaseObject.FAILED);
			result.setMessage("没有查到该客户的账户信息!");
			return result;
		}
		// 判断该客户账户中可用余额是否充足
		if (custAccountModel.getAvailableBalance().compareTo(bigAftTaxAmount) == -1) {
			result.setErrorCode(ResultCommandBaseObject.FAILED);
			result.setMessage("可提现余额不足!");
			return result;
		}

		MerchantInfoBo mib = new MerchantInfoBo();
		mib.setAddr(merchantInfoModel.getAddr());
		mib.setClientType(0);
		mib.setCreateTime(new Date());
		mib.setId(merchantInfoModel.getId());
		mib.setIdNumber(merchantInfoModel.getIdNumber());
		mib.setIndustryCategoryCn(merchantInfoModel.getIndustryCategoryCn());
		mib.setIndustryCategoryCode(
				merchantInfoModel.getIndustryCategoryCode()!= null ? merchantInfoModel.getIndustryCategoryCode().intValue() : 0);
		mib.setInvestMoney(merchantInfoModel.getInvestMoney());
		mib.setLegalRepresentative(merchantInfoModel.getLegalRepresentative());
		mib.setMerchantLevel(merchantInfoModel.getMerchantLevel() != null ? merchantInfoModel.getMerchantLevel().intValue() : 0);
		mib.setMerchantLevelCn(merchantInfoModel.getMerchantLevelCn());
		mib.setMerchantSubType(merchantInfoModel.getMerchantSubType() != null ? merchantInfoModel.getMerchantSubType().intValue() : 0);
		mib.setMerchantType(merchantInfoModel.getMerchantType() != null ? merchantInfoModel.getMerchantType().intValue() : 0);
		mib.setMerchantTypeCn(merchantInfoModel.getMerchantTypeCn());
		mib.setName(merchantInfoModel.getName());
		mib.setParentMerchantId(merchantInfoModel.getParentMerchantId());
		mib.setParentPersonName(merchantInfoModel.getPersonName());
		mib.setPersonName(merchantInfoModel.getPersonName());
		mib.setRemark(merchantInfoModel.getRemark());
		mib.setShopKeeperType(merchantInfoModel.getShopkeeperType() != null ? merchantInfoModel.getShopkeeperType().intValue() : 0);
		mib.setShopKeeperTypeCn(merchantInfoModel.getShopkeeperTypeCn());
		mib.setStatus(merchantInfoModel.getStatus() != null ? merchantInfoModel.getStatus().intValue() : 0);
		mib.setTelNo(merchantInfoModel.getTelNo());
		mib.setUniformSocialCreditCode(merchantInfoModel.getUniformSocialCreditCode());
		mib.setRemark(remark);
		try {
			String reqIp = SysUtil.getIpAddress(request);
			logger.info("商户提现申请请求主机IP：{}", reqIp);
			merchantService.executeMerchantWithdraw(bigAftTaxAmount, mib, reqIp, custInfoForSettle,false,PayWayEnum.IMMEDIATE_PAY);
			result.setResult(ResultCommandBaseObject.SUCCESS);
			result.setMessage("增加提现处理成功！");
			return result;
		} catch (Exception e) {
			logger.error("error", e);
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("增加提现处理失败！");
			return result;
		}
    }

    /**
     * 删除商户提现订单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer withdrawApplyRecordId) {
       /* withdrawApplyRecordService.deleteById(withdrawApplyRecordId);
        return SUCCESS_TIP;*/
    	return SUCCESS_TIP;
    }

    /**
     * 修改商户提现订单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WithdrawApplyRecord withdrawApplyRecord) {
        /*withdrawApplyRecordService.updateById(withdrawApplyRecord);
        return SUCCESS_TIP;*/

    	return SUCCESS_TIP;
    }

    /**
     * 商户提现订单详情
     */
    @RequestMapping(value = "/detail/{withdrawApplyRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("withdrawApplyRecordId") Integer withdrawApplyRecordId) {
        return withdrawApplyRecordService.selectById(withdrawApplyRecordId);
    }
	/**
	 * 根据商户id查询商户信息
	 */
	@RequestMapping(value = "/getMerchantInfoById")
	@ResponseBody
	public Object getMerchantInfoById(HttpServletRequest request, HttpServletResponse response) {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		String merchantId = request.getParameter("merchantId");
        logger.info(String.format("getMerchantInfoById-根据商户id查询商户信息-%s", merchantId));
		try {
			// 1. 获取用户头像
			// 2. 商户类型id
			Long lngMerchantId = new Long(merchantId);
			// 3. 查询商户id
			MerchantInfoModel infoModel = merchantInfoModelMapper.selectByPrimaryKey(lngMerchantId);
			if (infoModel == null) {
				logger.info(String.format("您输入的商户号(%s)在系统中不存在，请输入正确的商户号!", merchantId));
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage(String.format("您输入的商户号(%s)在系统中不存在，请输入正确的商户号!", merchantId));
			} else {
				if (infoModel.getMerchantType() != null
						&& (infoModel.getMerchantType().intValue() == MerchantTypeEnum.TERMINAL.getCode())) {
					logger.info(String.format("您输入的商户号(%s)是终端商户，请输入正确的商户号!", merchantId));
					result.setResult(ResultCommandBaseObject.FAILED);
					result.setMessage(String.format("您输入的商户号(%s)在系统中不存在，请输入正确的商户号!", merchantId));
				} else {
					result.setResponseInfo(infoModel);
					result.setResult(ResultCommandBaseObject.SUCCESS);
				}
			}
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(String.format("selectMerchant-根据商户id查询商户merchantId-%s", merchantId), e);
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("查询商户异常！");
			return result;
		}
	}
}
