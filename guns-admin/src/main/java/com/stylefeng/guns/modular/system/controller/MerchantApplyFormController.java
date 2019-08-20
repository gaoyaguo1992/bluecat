package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.modular.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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
import com.stylefeng.guns.modular.system.model.MerchantApplyForm;
import com.stylefeng.guns.modular.system.service.IMerchantApplyFormService;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.modular.system.constants.MerchantApplyFormStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantApplyFormModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantApplyFormModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBySelfModel;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-04-03 18:24:36
 */
@Controller
@RequestMapping("/merchantApplyForm")
public class MerchantApplyFormController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(MerchantApplyFormController.class);

    private String PREFIX = "/system/merchantApplyForm/";

    @Autowired
    private IMerchantApplyFormService merchantApplyFormService;
    /**
     * 获到用户申请信息
     */
    @Autowired
    private MerchantApplyFormModelBySelfMapper  merchantApplyFormModelBySelfMapper;
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "merchantApplyForm.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/merchantApplyForm_add")
    public String merchantApplyFormAdd() {
        return PREFIX + "merchantApplyForm_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/merchantApplyForm_update/{merchantApplyFormId}")
    public String merchantApplyFormUpdate(@PathVariable Integer merchantApplyFormId, Model model) {
        MerchantApplyForm merchantApplyForm = merchantApplyFormService.selectById(merchantApplyFormId);
        model.addAttribute("item",merchantApplyForm);
        String selectDoStatusOptions="";
        List<MerchantApplyFormStatusEnum> list= MerchantApplyFormStatusEnum.getStatusEnumList();
   		MerchantApplyFormStatusEnum item;
		for (int i = 0; i < list.size(); i++) {
			item = list.get(i);
			if (merchantApplyForm != null && merchantApplyForm.getDoStatus() != null
					&& merchantApplyForm.getDoStatus().equals(item.getCode())) {
				selectDoStatusOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
						selectDoStatusOptions, item.getCode(), item.getDesc());

			} else {
				selectDoStatusOptions = String.format("%s<option value=\"%d\">%s</option>", selectDoStatusOptions,
						item.getCode(), item.getDesc());
			}
		}
        model.addAttribute("selectDoStatusOptions",selectDoStatusOptions);
        LogObjectHolder.me().set(merchantApplyForm);
        return PREFIX + "merchantApplyForm_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse respons) {
    	// 1.接收参数
		Integer userId = ShiroKit.getUser().getId();
		String order = request.getParameter("order");
		String offsetStr = request.getParameter("offset");
		String limitStr = request.getParameter("limit"); // 每页多少行..
		String condition = request.getParameter("condition");// 查询条件..
		String conditionValue = request.getParameter("conditionValue");// 查询结果

		Long offset = 0L;
		logger.info(String.format("获取申请商户列表 list userId:%d,order:%s,offsetStr:%s,limitStr:%s,conditionValue:%s", userId,
				order, offsetStr, limitStr, conditionValue));
		logger.info(String.format("获取申请商户列表 list userId:%d,order:%s,offsetStr:%s,limitStr:%s,condition:%s", userId,
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
		order = "id desc";
		// 2. 初始他参数
		ResultCommandForBSTable<MerchantApplyFormModel> result = new ResultCommandForBSTable<>();
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
				} else if (condition.equalsIgnoreCase("merchantName")) {
					pageFilter.put("merchantName", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("personname")) {
					pageFilter.put("personName", Long.valueOf(conditionValue));
				} else if (condition.equalsIgnoreCase("telNo")) {
					pageFilter.put("telNo", Long.valueOf(conditionValue));
				} 
			} catch (Exception e) {
				// TODO: handle Exception
			}
		}
		// 3.
		
		Long total = merchantApplyFormModelBySelfMapper.countByConditionForPage(pageFilter);
		List<MerchantApplyFormModel> list = merchantApplyFormModelBySelfMapper.selectByConditionForPage(pageFilter);
		result.setRows(list);
		result.setTotal(total);
		return result;
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MerchantApplyForm merchantApplyForm) {
        merchantApplyFormService.insert(merchantApplyForm);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer merchantApplyFormId) {
        merchantApplyFormService.deleteById(merchantApplyFormId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MerchantApplyForm merchantApplyForm) {
    	if(merchantApplyForm.getDoStatus()!=null){
    	   String doStatusCn=MerchantApplyFormStatusEnum.getDesc(merchantApplyForm.getDoStatus().intValue());
    	   merchantApplyForm.setDoStatusCn(doStatusCn);
    	}
        merchantApplyFormService.updateById(merchantApplyForm);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{merchantApplyFormId}")
    @ResponseBody
    public Object detail(@PathVariable("merchantApplyFormId") Integer merchantApplyFormId) {
        return merchantApplyFormService.selectById(merchantApplyFormId);
    }
}
