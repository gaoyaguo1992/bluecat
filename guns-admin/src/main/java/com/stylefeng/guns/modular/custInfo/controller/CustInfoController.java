package com.stylefeng.guns.modular.custInfo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.custInfo.service.ICustInfoService;
import com.stylefeng.guns.modular.system.controller.DeviceInfoController;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfo;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.modular.system.dao.CustInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelKey;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;

/**
 * 用户信息控制器
 *
 * @author fengshuonan
 * @Date 2019-01-21 15:32:12
 */
@Controller
@RequestMapping("/custInfo")
public class CustInfoController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

    private String PREFIX = "/custInfo/custInfo/";

    @Autowired
    private ICustInfoService custInfoService;
    
    @Autowired
    private CustMerchantRefInfoModelMapper custMerchantRefInfoModelMapper;
    /**
     * 商户数据库操作
     */
    @Autowired
    private MerchantInfoModelMapper merchantInfoModelMapper;
    /**
     * 用户数据库操作..
     */
    @Autowired
    private CustInfoModelMapper custInfoModelMapper;
    
    @Autowired
    private CustInfoModelBySelfMapper custInfoModelBySelfMapper;
    /**
     * 跳转到用户信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "custInfo.html";
    }

    /**
     * 跳转到添加用户信息
     */
    @RequestMapping("/custInfo_add")
    public String custInfoAdd() {
        return PREFIX + "custInfo_add.html";
    }
    /**
     * 绑定商户..
     * @return
     */
    @RequestMapping("/bind_cust_merchant/{custInfoId}")
    public String bindCustMerchant(@PathVariable String custInfoId,Model model) {
    	model.addAttribute("custInfoId",custInfoId);
        return PREFIX + "bind_cust_merchant.html";
    }
    /**
     * 跳转到修改用户信息
     */
    @RequestMapping("/custInfo_update/{custInfoId}")
    public String custInfoUpdate(@PathVariable String custInfoId, Model model) {
        CustInfo custInfo = custInfoService.selectById(custInfoId);
        model.addAttribute("item",custInfo);
        LogObjectHolder.me().set(custInfo);
        return PREFIX + "custInfo_edit.html";
    }

    /**
     * 获取用户信息列表
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
		ResultCommandForBSTable<CustInfoModel> result = new ResultCommandForBSTable<>();
		result.setResult(ResultCommandForBSTable.SUCCESS);
		HashMap<String, Object> pageFilter = new HashMap<>();
		pageFilter.put("offset", offset);
		pageFilter.put("order", order);
		
		// pageFilter.put("id", 10000001L);
		pageFilter.put("limit", limit);
		
		if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
			condition = condition.toLowerCase();
			try {
				if (condition.equalsIgnoreCase("custno")) {
					pageFilter.put("custNo", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("nickname")) {
					pageFilter.put("nickName", conditionValue);
				} else if (condition.equalsIgnoreCase("merchantid")) {
					pageFilter.put("merchantId", Long.valueOf(conditionValue));
				}
			} catch (Exception e) {
				// TODO: handle excepti
			}
		}
		
		// 3.
		Long total = custInfoModelBySelfMapper.countByConditionForPage(pageFilter);
		List<CustInfoModel> list = custInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
		result.setRows(list);
		result.setTotal(total);
		return result;
    }

    /**
     * 获取用户信息列表
     */
    @RequestMapping(value = "/getCustRelMerlist")
    @ResponseBody
    public Object getCustRelMerlist(HttpServletRequest request, HttpServletResponse response) {
    	Integer userId = ShiroKit.getUser().getId();
		String custId = request.getParameter("custId");
		
		Long iCustId=0L;
		try {
			iCustId=Long.parseLong(custId);
		} catch (Exception e) {
			// TODO: handle exception
			iCustId=0L;
		}
		CustMerchantRefInfoModelExample example=new CustMerchantRefInfoModelExample();
		com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelExample.Criteria
			criteria= example.createCriteria();
		criteria.andCustNoEqualTo(iCustId);
        return custMerchantRefInfoModelMapper.selectByExample(example);
    }
    /**
     * 绑定商户    
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addMerchantSubmit")
    @ResponseBody
    public Object addMerchantSubmit(HttpServletRequest request, HttpServletResponse response) {
    	//1.接收参数
    	Integer userId = ShiroKit.getUser().getId();
		String custId = request.getParameter("custId");
		String merchantId = request.getParameter("merchantId");
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		Long iCustId=0L;
		//2. 处理参数
		logger.info(String.format("addMerchantSubmit--userId：%d,custId:%s,merchantId:%s",userId,custId,merchantId));
		try {
			iCustId=Long.parseLong(custId);
		} catch (Exception e) {
			// TODO: handle exception
			iCustId=0L;
		}
		Long iMerchantId=0L;
		try {
			iMerchantId=Long.parseLong(merchantId);
		} catch (Exception e) {
			// TODO: handle exception
			iMerchantId=0L;
		}
		//处理权限
		if(iCustId.equals(new Long("0"))){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("未选择用户，请选择用户！");
			return result;
		}
		if(iMerchantId.equals(new Long("0"))){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("未输入商户编号，请输入商户编号！");
			return result;
		}
		MerchantInfoModel merInfoModel=merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
		CustInfoModel custModel=custInfoModelMapper.selectByPrimaryKey(iCustId);
		if(merInfoModel==null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("商户(%s)在系统中不存在！",merchantId));
			return result;
		}
		if(custModel==null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("用户(%s)在系统中不存在！",custId));
			return result;
		}
		CustMerchantRefInfoModelKey modelKey=new CustMerchantRefInfoModelKey();
		modelKey.setCustNo(iCustId);
		modelKey.setMerchantId(iMerchantId);
		CustMerchantRefInfoModel refInfoModel= custMerchantRefInfoModelMapper.selectByPrimaryKey(modelKey);
		if(refInfoModel!=null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("客户(%s)与商户(%s)已绑，无需再次绑定！",custId,iMerchantId));
			return result;
		}
		//3. 保存...
		refInfoModel=new CustMerchantRefInfoModel();
		refInfoModel.setCreateId(userId.longValue());
		refInfoModel.setCreateTime(new Date());
		refInfoModel.setCustNo(iCustId);
		if(custModel.getCustType()!=null){
			refInfoModel.setCustType((byte) custModel.getCustType().intValue());
		}
		refInfoModel.setMerchantId(iMerchantId);
		refInfoModel.setMerchantName(merInfoModel.getName());
		if(merInfoModel.getMerchantType()!=null){
			refInfoModel.setMerchantType((byte) merInfoModel.getMerchantType().intValue());
		}
		refInfoModel.setMerchantTypeCn(merInfoModel.getMerchantTypeCn());
		refInfoModel.setIsDefault((byte) 0);
		custMerchantRefInfoModelMapper.insert(refInfoModel);
		//4. 返回..
		result.setResult(ResultCommandBaseObject.SUCCESS);
		result.setMessage(String.format("商户绑定成功！",custId));
		return result;
    }
    /**
     * 绑定商户    
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delMerchantSubmit")
    @ResponseBody
    public Object delMerchantSubmit(HttpServletRequest request, HttpServletResponse response) {
    	//1.接收参数
    	Integer userId = ShiroKit.getUser().getId();
		String custId = request.getParameter("custId");
		String merchantId = request.getParameter("merchantId");
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		Long iCustId=0L;
		//2. 处理参数
		logger.info(String.format("delMerchantSubmit--userId：%d,custId:%s,merchantId:%s",userId,custId,merchantId));
		try {
			iCustId=Long.parseLong(custId);
		} catch (Exception e) {
			// TODO: handle exception
			iCustId=0L;
		}
		Long iMerchantId=0L;
		try {
			iMerchantId=Long.parseLong(merchantId);
		} catch (Exception e) {
			// TODO: handle exception
			iMerchantId=0L;
		}
		//处理权限
		if(iCustId.equals(new Long("0"))){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("未选择用户，请选择用户！");
			return result;
		}
		if(iMerchantId.equals(new Long("0"))){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("未输入商户编号，请输入商户编号！");
			return result;
		}
		MerchantInfoModel merInfoModel=merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
		CustInfoModel custModel=custInfoModelMapper.selectByPrimaryKey(iCustId);
		if(merInfoModel==null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("商户(%s)在系统中不存在！",merchantId));
			return result;
		}
		if(custModel==null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("用户(%s)在系统中不存在！",custId));
			return result;
		}
		CustMerchantRefInfoModelKey modelKey=new CustMerchantRefInfoModelKey();
		modelKey.setCustNo(iCustId);
		modelKey.setMerchantId(iMerchantId);
		CustMerchantRefInfoModel refInfoModel= custMerchantRefInfoModelMapper.selectByPrimaryKey(modelKey);
		if(refInfoModel==null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("客户(%d)与商户(%d)已解除绑定，无需再次解除！",custId,iMerchantId));
			return result;
		}
		logger.info("商户解绑成功--delMerchantSubmit--userId：%d,custId:%s,merchantId:%s",userId,custId,merchantId);
		custMerchantRefInfoModelMapper.deleteByPrimaryKey(modelKey);
		//4. 返回..
		result.setResult(ResultCommandBaseObject.SUCCESS);
		result.setMessage(String.format("商户解绑成功！",custId));
		return result;
    }
    /**
     * 新增用户信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CustInfo custInfo) {
        //custInfoService.insert(custInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer custInfoId) {
        //custInfoService.deleteById(custInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CustInfo custInfo) {
        //custInfoService.updateById(custInfo);
        return SUCCESS_TIP;
    }

    /**
     * 用户信息详情
     */
    @RequestMapping(value = "/detail/{custInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("custInfoId") Integer custInfoId) {
        return custInfoService.selectById(custInfoId);
    }
}
