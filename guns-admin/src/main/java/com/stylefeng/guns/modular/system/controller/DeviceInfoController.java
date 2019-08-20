package com.stylefeng.guns.modular.system.controller;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.system.model.BatchBindRatoUserBO;
import com.stylefeng.guns.modular.system.service.DeviceInfoHelperService;
import com.stylefeng.guns.modular.system.service.IDeviceInfoService;
import com.stylefeng.guns.modular.wxApplication.service.MerchantInfoHelperService;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandForBSTable;
import com.stylefeng.guns.sharecore.common.utils.StringUtil;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceActivationModeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceYfjRebackTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ProvinceCityZoneInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.PlatformRatioDeviceIdsBO;
import com.stylefeng.guns.sharecore.modular.system.model.ProvinceCityZoneInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ProvinceCityZoneInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoForBatchModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoNumberTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModelExample.Criteria;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;

/**
 * device控制器
 *
 * @author fengshuonan
 * @Date 2019-01-20 02:28:52
 */
@Controller
@RequestMapping("/deviceInfo")
public class DeviceInfoController extends BaseController {
    /**
     * 处理日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

    private String PREFIX = "/system/deviceInfo/";
    /**
     * 得到设备信息..
     */
    @Autowired
    private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
    /**
     * 省市区数据库查询..
     */
    @Autowired
    private ProvinceCityZoneInfoModelMapper provinceCityZoneInfoModelMapper;
    /**
     * 得到设备信息..
     */
    @Autowired
    private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
    /**
     * 获到商户信息...
     */
    @Autowired
    private MerchantInfoModelMapper merchantInfoModelMapper;
    /**
     * 设备
     */
    @Autowired
    private IDeviceInfoService deviceInfoService;
    /**
     * 数据库操作
     */
    @Autowired
    @Qualifier("common.SequenceService")
    protected SequenceService seqService;
    /**
     * 设备管理服务
     */
    @Autowired
    @Qualifier("gunsadmin.DeviceInfoHelperService")
    protected DeviceInfoHelperService deviceInfoHelperService;
    /**
     * 激活设备模式(2:简单模式， 1：用户扫码方式)
     */
    @Value("${sharehelper.activationMode}")
    private String activationMode;
    /**
     * 可选设备类型
     */
    @Value("${sharehelper.shareDeviceTypes}")
    private String shareDeviceTypes;
    /**
     * 处理商户服务。。。
     */
    @Autowired
    @Qualifier("gunsadmin.MerchantInfoHelperService")
    private MerchantInfoHelperService merchantInfoHelperService;

    /**
     * 跳转到device首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "deviceInfo.html";
    }

    /**
     * 初始化设备编辑变量...
     *
     * @param model
     */
    private void initDeviceInfo(Model model, ShareDeviceInfoModel deviceInfo) {
        String selectDeviceTypeOptions = "";
        List<ShareDeviceTypeEnum> list = ShareDeviceTypeEnum.getShareDeviceTypeEnumList();
        ShareDeviceTypeEnum item;
        String tmp = "";
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
            if (deviceInfo != null && deviceInfo.getDeviceTypeId() != null
                    && deviceInfo.getDeviceTypeId().equals(item.getCode())) {
                selectDeviceTypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
                        selectDeviceTypeOptions, item.getCode(), item.getDesc());

            } else {
                selectDeviceTypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectDeviceTypeOptions,
                        item.getCode(), item.getDesc());
            }
        }
        model.addAttribute("selectDeviceTypeOptions", selectDeviceTypeOptions);
        // 设备状态
        String selectDeviceStatusOptions = "";
        List<ShareDeviceStatusEnum> listForDeviceStatus = ShareDeviceStatusEnum.getShareDeviceStatusEnumList();
        ShareDeviceStatusEnum itemForStatus;
        for (int i = 0; i < listForDeviceStatus.size(); i++) {
            itemForStatus = listForDeviceStatus.get(i);
            if (deviceInfo != null && deviceInfo.getDeviceStatus() != null
                    && deviceInfo.getDeviceStatus().equals(itemForStatus.getCode())) {
                selectDeviceStatusOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
                        selectDeviceStatusOptions, itemForStatus.getCode(), itemForStatus.getDesc());
            } else {
                selectDeviceStatusOptions = String.format("%s<option value=\"%d\">%s</option>",
                        selectDeviceStatusOptions, itemForStatus.getCode(), itemForStatus.getDesc());
            }
        }
        model.addAttribute("selectDeviceStatusOptions", selectDeviceStatusOptions);
        // 费用类型
        String selectFeeTypeOptions = "";
        List<ShareFeeTypeEnum> listForShareFeeTypeEnum = new ArrayList<>();
        listForShareFeeTypeEnum.add(ShareFeeTypeEnum.Prepayment);
        listForShareFeeTypeEnum.add(ShareFeeTypeEnum.PrepaymentHaveFirstAmount);
        ShareFeeTypeEnum itemForFeeType;
        for (int i = 0; i < listForShareFeeTypeEnum.size(); i++) {
            itemForFeeType = listForShareFeeTypeEnum.get(i);
            if (deviceInfo != null && deviceInfo.getFeeTypeId() != null
                    && deviceInfo.getFeeTypeId().equals(itemForFeeType.getCode())) {

                selectFeeTypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
                        selectFeeTypeOptions, itemForFeeType.getCode(), itemForFeeType.getDesc());
            } else {
                selectFeeTypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFeeTypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
            }
        }
        model.addAttribute("selectFeeTypeOptions", selectFeeTypeOptions);
        // 处理时间控制充电时间的订单...
        String selectFee1TypeOptions = "<option id=\"0\"> </option>";
        String selectFee2TypeOptions = selectFee1TypeOptions;
        String selectFee3TypeOptions = selectFee1TypeOptions;

        List<ShareFeeTypeEnum> listForShareFeeTypeEnumWithHour = ShareFeeTypeEnum.getShareFeeTypeByTimeHours();
        for (int i = 0; i < listForShareFeeTypeEnumWithHour.size(); i++) {
            itemForFeeType = listForShareFeeTypeEnumWithHour.get(i);
            // 选项1
            if (deviceInfo != null && deviceInfo.getFee1TypeId() != null
                    && deviceInfo.getFee1TypeId().equals(itemForFeeType.getCode())) {
                selectFee1TypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
                        selectFee1TypeOptions, itemForFeeType.getCode(), itemForFeeType.getDesc());
            } else {
                selectFee1TypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFee1TypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
            }
            // 选项2
            if (deviceInfo != null && deviceInfo.getFee2TypeId() != null
                    && deviceInfo.getFee2TypeId().equals(itemForFeeType.getCode())) {
                selectFee2TypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
                        selectFee2TypeOptions, itemForFeeType.getCode(), itemForFeeType.getDesc());
            } else {
                selectFee2TypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFee2TypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
            }
            // 选项3
            if (deviceInfo != null && deviceInfo.getFee3TypeId() != null
                    && deviceInfo.getFee3TypeId().equals(itemForFeeType.getCode())) {
                selectFee3TypeOptions = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
                        selectFee3TypeOptions, itemForFeeType.getCode(), itemForFeeType.getDesc());
            } else {
                selectFee3TypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFee3TypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
            }
        }
        // 简单模式
        List<ShareDeviceActivationModeEnum> listForActivationMode = ShareDeviceActivationModeEnum
                .getShareDeviceStatusEnumList();
        String shareDeviceActivationModeOptions = "";
        ShareDeviceActivationModeEnum itemForDeviceActivationModel;
        for (int i = 0; i < listForActivationMode.size(); i++) {
            itemForDeviceActivationModel = listForActivationMode.get(i);

            if (deviceInfo == null || deviceInfo.getActivationMode() == null) {
                if (activationMode != null
                        && activationMode.equals(String.format("%d", itemForDeviceActivationModel.getCode()))) {
                    shareDeviceActivationModeOptions = String.format(
                            "%s<option value=\"%d\" selected=\"selected\">%s</option>",
                            shareDeviceActivationModeOptions, itemForDeviceActivationModel.getCode(),
                            itemForDeviceActivationModel.getDesc());
                } else {
                    shareDeviceActivationModeOptions = String.format("%s<option value=\"%d\">%s</option>",
                            shareDeviceActivationModeOptions, itemForDeviceActivationModel.getCode(),
                            itemForDeviceActivationModel.getDesc());
                }
            } else {
                if (deviceInfo != null && deviceInfo.getActivationMode() != null
                        && deviceInfo.getActivationMode().equals(itemForDeviceActivationModel.getCode())) {
                    shareDeviceActivationModeOptions = String.format(
                            "%s<option value=\"%d\" selected=\"selected\">%s</option>",
                            shareDeviceActivationModeOptions, itemForDeviceActivationModel.getCode(),
                            itemForDeviceActivationModel.getDesc());
                } else {
                    shareDeviceActivationModeOptions = String.format("%s<option value=\"%d\">%s</option>",
                            shareDeviceActivationModeOptions, itemForDeviceActivationModel.getCode(),
                            itemForDeviceActivationModel.getDesc());
                }
            }
        }
        //设备编号模式
        List<ShareDeviceInfoNumberTypeEnum> listForDeviceInfoNumberTypeMode = 
        			ShareDeviceInfoNumberTypeEnum.getShareDeviceInfoNumberTypeEnumList();
        String shareDeviceInfoNumberTypeOptions = "";
        ShareDeviceInfoNumberTypeEnum itemForShareDeviceInfoNumberTypeEnum=null;
        for (int i = 0; i < listForDeviceInfoNumberTypeMode.size(); i++) {
        	itemForShareDeviceInfoNumberTypeEnum = listForDeviceInfoNumberTypeMode.get(i);
            if (deviceInfo == null || deviceInfo.getPasswordType() == null) {
                if (itemForShareDeviceInfoNumberTypeEnum != null
                        && itemForShareDeviceInfoNumberTypeEnum.equals(String.format("%d", ShareDeviceInfoNumberTypeEnum.GENERAL.getCode()))) {
                	shareDeviceInfoNumberTypeOptions = String.format(
                            "%s<option value=\"%d\" selected=\"selected\">%s</option>",
                            shareDeviceInfoNumberTypeOptions, itemForShareDeviceInfoNumberTypeEnum.getCode(),
                            itemForShareDeviceInfoNumberTypeEnum.getDesc());
                } else {
                	shareDeviceInfoNumberTypeOptions = String.format("%s<option value=\"%d\">%s</option>",
                			shareDeviceInfoNumberTypeOptions, itemForShareDeviceInfoNumberTypeEnum.getCode(),
                            itemForShareDeviceInfoNumberTypeEnum.getDesc());
                }
            } else {
                if (deviceInfo != null && deviceInfo.getPasswordKey() != null
                        && deviceInfo.getPasswordKey().equals(itemForShareDeviceInfoNumberTypeEnum.getCode())) {
                	shareDeviceInfoNumberTypeOptions = String.format(
                            "%s<option value=\"%d\" selected=\"selected\">%s</option>",
                            shareDeviceInfoNumberTypeOptions, itemForShareDeviceInfoNumberTypeEnum.getCode(),
                            itemForShareDeviceInfoNumberTypeEnum.getDesc());
                } else {
                	shareDeviceInfoNumberTypeOptions = String.format("%s<option value=\"%d\">%s</option>",
                			shareDeviceInfoNumberTypeOptions, itemForShareDeviceInfoNumberTypeEnum.getCode(),
                            itemForShareDeviceInfoNumberTypeEnum.getDesc());
                }
            }
        }
        
        
        Integer userId = ShiroKit.getUser().getId();
        if (userId == null) {
            userId = new Integer(0);
        }
        model.addAttribute("selectFee1TypeOptions", selectFee1TypeOptions);
        model.addAttribute("selectFee2TypeOptions", selectFee2TypeOptions);
        model.addAttribute("selectFee3TypeOptions", selectFee3TypeOptions);
        model.addAttribute("shareDeviceActivationModeOptions", shareDeviceActivationModeOptions);
        model.addAttribute("shareDeviceInfoNumberTypeOptions", shareDeviceInfoNumberTypeOptions);
        model.addAttribute("isAdmin", userId.intValue() == 1 ? 1 : 0);
        // 提现方式...
        List<ShareDeviceYfjRebackTypeEnum> listForShareDeviceYfjRebackTypeEnum = ShareDeviceYfjRebackTypeEnum
                .getShareDeviceYfjRebackTypeEnum();
        String selectYfjReBackTypes = "";
        ShareDeviceYfjRebackTypeEnum itemForReBackType;
        for (int i = 0; i < listForShareDeviceYfjRebackTypeEnum.size(); i++) {
            itemForReBackType = listForShareDeviceYfjRebackTypeEnum.get(i);
            // 选项1
            if (deviceInfo != null && deviceInfo.getYfjRebackType() != null
                    && deviceInfo.getYfjRebackType().equals(itemForReBackType.getCode())) {
                selectYfjReBackTypes = String.format("%s<option value=\"%d\" selected=\"selected\">%s</option>",
                        selectYfjReBackTypes, itemForReBackType.getCode(), itemForReBackType.getDesc());
            } else {
                selectYfjReBackTypes = String.format("%s<option value=\"%d\">%s</option>", selectYfjReBackTypes,
                        itemForReBackType.getCode(), itemForReBackType.getDesc());
            }
        }
        model.addAttribute("selectYfjReBackTypes", selectYfjReBackTypes);

        // 得到省:\
    }

    /**
     * 初始化options..
     *
     * @param model
     */
    private void initProvinceOptions(Model model) {
        ProvinceCityZoneInfoModelExample example = new ProvinceCityZoneInfoModelExample();
        com.stylefeng.guns.sharecore.modular.system.model.ProvinceCityZoneInfoModelExample.Criteria criteria = example
                .createCriteria();
        criteria.andParentidEqualTo(0L);
        criteria.andIdNotEqualTo(0L);
        List<ProvinceCityZoneInfoModel> list = provinceCityZoneInfoModelMapper.selectByExample(example);
        String provinceOptions = "";
        for (ProvinceCityZoneInfoModel provinceCityZoneInfoModel : list) {
            provinceOptions = String.format("%s<option value=\"%d\">%s</option>", provinceOptions,
                    provinceCityZoneInfoModel.getId(), provinceCityZoneInfoModel.getName());
        }
        model.addAttribute("provinceOptions", provinceOptions);
    }

    /**
     * 跳转到添加device
     */
    @RequestMapping("/deviceInfo_add")
    public String deviceInfoAdd(Model model) {
        // 设备类型
        initDeviceInfo(model, null);
        return PREFIX + "deviceInfo_add.html";
    }

    /**
     * 批量生成设备
     */
    @RequestMapping("/deviceInfo_batch_add")
    public String deviceInfoBatchAdd(Model model) {
        // 设备类型
        initDeviceInfo(model, null);
        return PREFIX + "deviceInfo_batch_add.html";
    }

    /**
     * 批量批定商户
     */
    @RequestMapping("/deviceInfo_batch_unbindmer")
    public String deviceInfoBatchUnbindmer(Model model) {
        // 设备类型
        initDeviceInfo(model, null);
        Integer userId = ShiroKit.getUser().getId();
        // 1.admin有所有的权限..admin...
        if (userId != null && userId.intValue() != 1) {
            return PREFIX + "deviceInfo_batch_unbindmer_user.html";
        } else {
            return PREFIX + "deviceInfo_batch_unbindmer.html";
        }
    }

    /**
     * 批量解除绑定商户
     */
    @RequestMapping("/deviceInfo_batch_bindmer")
    public String deviceInfoBatchBindmer(Model model) {
        // 设备类型
        initDeviceInfo(model, null);
        Integer userId = ShiroKit.getUser().getId();
        // 1.admin有所有的权限..admin...
        if (userId != null && userId.intValue() != 1) {
            return PREFIX + "deviceInfo_batch_bindmer_user.html";
        } else {
            return PREFIX + "deviceInfo_batch_bindmer.html";
        }
    }

    /**
     * 批量绑定费用
     */
    @RequestMapping("/deviceInfo_batch_bindfee")
    public String deviceInfoBatchBindfee(Model model) {
        // 设备类型
        initDeviceInfo(model, null);
        Integer userId = ShiroKit.getUser().getId();
        // 1.admin有所有的权限..admin...
        if (userId != null && userId.intValue() != 1) {
            return PREFIX + "deviceInfo_batch_bindfee_user.html";
        } else {
            return PREFIX + "deviceInfo_batch_bindfee.html";
        }
    }

    /**
     * 批量绑定分润
     */
    @RequestMapping("/deviceInfo_batch_bindrato")
    public String deviceInfoBatchBindrato(Model model) {
        // 设备类型
        initDeviceInfo(model, null);
        Integer userId = ShiroKit.getUser().getId();
        // 1.admin有所有的权限..admin...
        if (userId != null && userId.intValue() != 1) {
            return PREFIX + "deviceInfo_batch_bindrato_user.html";
        } else {
            return PREFIX + "deviceInfo_batch_bindrato.html";
        }
    }

    /**
     * 其它批量操作
     */
    @RequestMapping("/deviceInfo_batch_bindOther")
    public String deviceInfoBatchBindOther(Model model) {
        // 设备类型
        initDeviceInfo(model, null);
        initProvinceOptions(model);

        Integer userId = ShiroKit.getUser().getId();
        // 1.admin有所有的权限..admin...
        if (userId != null && userId.intValue() != 1) {
            return PREFIX + "deviceInfo_batch_bindOther_user.html";
        } else {
            return PREFIX + "deviceInfo_batch_bindOther.html";
        }
    }

    /**
     * 跳转到修改device
     */
    @RequestMapping("/deviceInfo_update/{deviceInfoId}")
    public String deviceInfoUpdate(@PathVariable Long deviceInfoId, Model model) {
        // 初始化编辑控件..
        ShareDeviceInfoModel deviceInfo = shareDeviceInfoModelMapper.selectByPrimaryKey(deviceInfoId);
        if (deviceInfo.getAgents1Id() != null && deviceInfo.getAgents1Id() == 0) {
            deviceInfo.setAgents1Id(null);
        }
        if (deviceInfo.getAgents2Id() != null && deviceInfo.getAgents2Id() == 0) {
            deviceInfo.setAgents2Id(null);
        }
        if (deviceInfo.getAgents3Id() != null && deviceInfo.getAgents3Id() == 0) {
            deviceInfo.setAgents3Id(null);
        }
        if (deviceInfo.getShopkeeperId() != null && deviceInfo.getShopkeeperId() == 0) {
            deviceInfo.setShopkeeperId(null);
        }
        if (deviceInfo.getAllianceBusinessId() != null && deviceInfo.getAllianceBusinessId() == 0) {
            deviceInfo.setAllianceBusinessId(null);
        }
        initDeviceInfo(model, deviceInfo);
        // 初始化选择省...
        initProvinceOptions(model);

        model.addAttribute("item", deviceInfo);
        LogObjectHolder.me().set(deviceInfo);

        Integer userId = ShiroKit.getUser().getId();
        // 1.admin有所有的权限..admin...
        if (userId != null && userId.intValue() != 1) {
            return PREFIX + "deviceInfo_edit_user.html";
        } else {
            return PREFIX + "deviceInfo_edit.html";
        }
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
        String startCreateDatetime = request.getParameter("startCreateDatetime");// 查询条件..
        String endCreateDatetime = request.getParameter("endCreateDatetime");// 查询结果
        String condition1 = request.getParameter("condition1");// 查询条件..
        String conditionValue1 = request.getParameter("conditionValue1");// 查询结果
        String startDeviceNo = request.getParameter("startDeviceNo");// 开始设备号
        String endDeviceNo = request.getParameter("endDeviceNo");// 结束设备号
        HashMap<String, Object> pageFilter = new HashMap<>();
        String pattern = "yyyy-MM-dd";
        if (startCreateDatetime != null && !startCreateDatetime.isEmpty()
                && DateUtil.isValidDate(startCreateDatetime, pattern)) {
            pageFilter.put("startCreateDatetime", DateUtil.parse(startCreateDatetime, pattern));
        }
        if (endCreateDatetime != null && !endCreateDatetime.isEmpty()
                && DateUtil.isValidDate(endCreateDatetime, pattern)) {
            pageFilter.put("endCreateDatetime", DateUtil.parse(endCreateDatetime, pattern));
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(startDeviceNo)) {
            pageFilter.put("startDeviceNo", Long.valueOf(startDeviceNo));
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(endDeviceNo)) {
            pageFilter.put("endDeviceNo", Long.valueOf(endDeviceNo));
        }
        // 如果所有查询条件都为null,默认查询新建设备一个月内的，避免操作不当导出过大的数据
        if (StringUtils.isEmpty(conditionValue) && StringUtils.isEmpty(startCreateDatetime)
                && StringUtils.isEmpty(endCreateDatetime) && StringUtils.isEmpty(conditionValue1)
                && StringUtils.isEmpty(endDeviceNo) && StringUtils.isEmpty(startDeviceNo)) {

            pageFilter.put("startCreateDatetime", DateUtil.getBeforeMonth(pattern));
            pageFilter.put("endCreateDatetime", DateUtil.getToday(pattern));
        }

        if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
            condition = condition.toLowerCase();
            try {
                if (condition.equalsIgnoreCase("Id")) {
                    pageFilter.put("id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("merchantId")) {
                    pageFilter.put("onlineMerchantId", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("agents1Id")) {
                    pageFilter.put("agents1Id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("agents2Id")) {
                    pageFilter.put("agents2Id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("agents3Id")) {
                    pageFilter.put("agents3Id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("shopkeeperId")) {
                    pageFilter.put("shopkeeperId", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("allianceBusinessId")) {
                    pageFilter.put("allianceBusinessId", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("deviceStatus")) {
                    pageFilter.put("deviceStatus", conditionValue);
                } else if (condition.equalsIgnoreCase("deviceTypeId")) {
                    pageFilter.put("deviceTypeId", Long.valueOf(conditionValue));
                }
            } catch (Exception e) {
            }
        }
        if (condition1 != null && !condition1.isEmpty() && conditionValue1 != null && !conditionValue1.isEmpty()) {
            condition1 = condition1.toLowerCase();
            try {
                if (condition1.equalsIgnoreCase("merchant")) {
                    pageFilter.put("merchant", conditionValue1);
                } else if (condition1.equalsIgnoreCase("merchantidtelno")) {
                    pageFilter.put("merchantIdTelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents1")) {
                    pageFilter.put("agents1", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents1telno")) {
                    pageFilter.put("agents1TelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents2")) {
                    pageFilter.put("agents2", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents2telno")) {
                    pageFilter.put("agents2TelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents3")) {
                    pageFilter.put("agents3", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents3telno")) {
                    pageFilter.put("agents3TelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("shopkeeper")) {
                    pageFilter.put("shopkeeper", conditionValue1);
                } else if (condition1.equalsIgnoreCase("shopkeepertelno")) {
                    pageFilter.put("shopkeeperTelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("allianceBusiness")) {
                    pageFilter.put("allianceBusiness", conditionValue1);
                } else if (condition1.equalsIgnoreCase("allianceBusinesstelno")) {
                    pageFilter.put("allianceBusinessTelNo", conditionValue1);
                }
            } catch (Exception e) {
            }
        }

        // 权限 管理员有全部权限admin...
        if (userId != null && userId.intValue() != 1) {
            initPageFilterForRight(pageFilter);
        }
        try {
            deviceInfoService.exportExcel(response, pageFilter);
        } catch (Exception e) {
            logger.error("设备数据导出异常！error:", e);
        }
    }

    /**
     * 获取device列表
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
        String startCreateDatetime = request.getParameter("startCreateDatetime");// 查询条件..
        String endCreateDatetime = request.getParameter("endCreateDatetime");// 查询结果
        String condition1 = request.getParameter("condition1");// 查询条件..
        String conditionValue1 = request.getParameter("conditionValue1");// 查询结果
        String startDeviceNo = request.getParameter("startDeviceNo");// 开始设备号
        String endDeviceNo = request.getParameter("endDeviceNo");// 结束设备号
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
        order = "id";
        // 2. 初始他参数
        ResultCommandForBSTable<ShareDeviceInfoBySelfModel> result = new ResultCommandForBSTable<>();
        result.setResult(ResultCommandForBSTable.SUCCESS);
        HashMap<String, Object> pageFilter = new HashMap<>();
        pageFilter.put("offset", offset);
        pageFilter.put("order", order);

        String pattern = "yyyy-MM-dd";
        if (startCreateDatetime != null && !startCreateDatetime.isEmpty()
                && DateUtil.isValidDate(startCreateDatetime, pattern)) {
            pageFilter.put("startCreateDatetime", DateUtil.parse(startCreateDatetime, pattern));
        }
        if (endCreateDatetime != null && !endCreateDatetime.isEmpty()
                && DateUtil.isValidDate(endCreateDatetime, pattern)) {
            pageFilter.put("endCreateDatetime", DateUtil.parse(endCreateDatetime, pattern));
        }

        // pageFilter.put("id", 10000001L);
        pageFilter.put("limit", limit);
        if (condition != null && !condition.isEmpty() && conditionValue != null && !conditionValue.isEmpty()) {
            condition = condition.toLowerCase();
            try {
                if (condition.equalsIgnoreCase("Id")) {
                    pageFilter.put("id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("merchantId")) {
                    pageFilter.put("onlineMerchantId", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("agents1Id")) {
                    pageFilter.put("agents1Id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("agents2Id")) {
                    pageFilter.put("agents2Id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("agents3Id")) {
                    pageFilter.put("agents3Id", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("shopkeeperId")) {
                    pageFilter.put("shopkeeperId", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("allianceBusinessId")) {
                    pageFilter.put("allianceBusinessId", Long.valueOf(conditionValue));
                } else if (condition.equalsIgnoreCase("deviceStatus")) {
                    pageFilter.put("deviceStatus", conditionValue);
                } else if (condition.equalsIgnoreCase("deviceTypeId")) {
                    pageFilter.put("deviceTypeId", Long.valueOf(conditionValue));
                }
            } catch (Exception e) {
                // TODO: handle Exception
            }
        }
        if (condition1 != null && !condition1.isEmpty() && conditionValue1 != null && !conditionValue1.isEmpty()) {
            condition1 = condition1.toLowerCase();
            try {
                if (condition1.equalsIgnoreCase("merchant")) {
                    pageFilter.put("merchant", conditionValue1);
                } else if (condition1.equalsIgnoreCase("merchantidtelno")) {
                    pageFilter.put("merchantIdTelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents1")) {
                    pageFilter.put("agents1", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents1telno")) {
                    pageFilter.put("agents1TelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents2")) {
                    pageFilter.put("agents2", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents2telno")) {
                    pageFilter.put("agents2TelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents3")) {
                    pageFilter.put("agents3", conditionValue1);
                } else if (condition1.equalsIgnoreCase("agents3telno")) {
                    pageFilter.put("agents3TelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("shopkeeper")) {
                    pageFilter.put("shopkeeper", conditionValue1);
                } else if (condition1.equalsIgnoreCase("shopkeepertelno")) {
                    pageFilter.put("shopkeeperTelNo", conditionValue1);
                } else if (condition1.equalsIgnoreCase("allianceBusiness")) {
                    pageFilter.put("allianceBusiness", conditionValue1);
                } else if (condition1.equalsIgnoreCase("allianceBusinesstelno")) {
                    pageFilter.put("allianceBusinessTelNo", conditionValue1);
                }
            } catch (Exception e) {
                // TODO: handle excepti
            }
        }

        // 权限 管理员有全部权限admin...
        if (userId != null && userId.intValue() != 1) {
            initPageFilterForRight(pageFilter);
        }
        // 3.
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(startDeviceNo)) {
            pageFilter.put("startDeviceNo", Long.valueOf(startDeviceNo));
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(endDeviceNo)) {
            pageFilter.put("endDeviceNo", Long.valueOf(endDeviceNo));
        }
        Long total = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
        List<ShareDeviceInfoBySelfModel> list = shareDeviceInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
        result.setRows(list);
        result.setTotal(total);
        return result;
    }

    /**
     * 新增device
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ShareDeviceInfoModel deviceInfo) {
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // 验检数据并对数据进行整理.
        Boolean validate = validateDeviceInfoForSave(deviceInfo, true, result);
        if (!validate) {
            return result;
        }
        Long id = seqService.getShareDeviceInfoSeq(deviceInfo.getDeviceTypeId());
        deviceInfo.setId(id);
        deviceInfo.setCreateDatetime(new Date());
        deviceInfo.setCreateId(userId.longValue());
        deviceInfo.setUpdateDatetime(new Date());
        if (deviceInfo.getActivationMode() == null) {
            try {
                deviceInfo.setActivationMode(new Integer(activationMode));
            } catch (Exception e) {
                // TODO: handle exception
                deviceInfo.setActivationMode(ShareDeviceActivationModeEnum.activation.getCode());
            }
        }
        if (deviceInfo.getYfjRebackType() == null) {
            deviceInfo.setYfjRebackType(ShareDeviceYfjRebackTypeEnum.RebackToBalance.getCode());
            deviceInfo.setYfjRebackTypeName(ShareDeviceYfjRebackTypeEnum.RebackToBalance.getDesc());
        }
        shareDeviceInfoModelMapper.insert(deviceInfo);
        result.setResponseInfo(id);
        result.setResult(ResultCommandBaseObject.SUCCESS);
        result.setMessage("充电设备添加成功！");
        return result;
    }

    /**
     * 删除device
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer deviceInfoId) {
        // deviceInfoService.deleteById(deviceInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 初始化充数...
     *
     * @param deviceInfo
     * @param checkFee   是否判断费用
     * @return
     */
    public Boolean validateDeviceInfoForSave(ShareDeviceInfoModel deviceInfo, Boolean checkFee,
                                             ResultCommandBaseObject<Object> result) {
        return deviceInfoHelperService.validateDeviceInfoForSave(deviceInfo, checkFee, result);
    }

    /**
     * 初始化share_device_info的过虑条件
     */
    private void initPageFilterForRight(HashMap<String, Object> pageFilter) {
        Integer userId = ShiroKit.getUser().getId();
        // 1.admin有所有的权限..admin...
        if (userId != null && userId.intValue() != 1) {
            // 2. 判断是否设置了角色
            List<Integer> listForRole = ShiroKit.getUser().getRoleList();
            if (listForRole == null || listForRole.size() <= 0) {
                pageFilter.put("filter", "and 1<>1"); // 没有权限
            } else {
                // 3.判断是否设置了数据权限..
                List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
                if (list == null || list.size() <= 0) {
                    pageFilter.put("filter", "and 1<>1"); // 没有权限
                } else {
                    String filter = "";
                    // 4. 解析数据权限
                    for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
                        if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG1
                                .getCode().intValue()) {
                            filter = (filter.isEmpty())
                                    ? String.format("agents1_id=%d", roleMerchantRefInfoModel.getMerchantId())
                                    : String.format("agents1_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
                                    filter);
                        } else if (roleMerchantRefInfoModel.getMerchantType()
                                .intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()) {
                            filter = (filter.isEmpty())
                                    ? String.format("agents2_id=%d", roleMerchantRefInfoModel.getMerchantId())
                                    : String.format("agents2_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
                                    filter);
                        } else if (roleMerchantRefInfoModel.getMerchantType()
                                .intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
                            filter = (filter.isEmpty())
                                    ? String.format("agents3_id=%d", roleMerchantRefInfoModel.getMerchantId())
                                    : String.format("agents3_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
                                    filter);
                        } else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.PUHUO_SHANG
                                .getCode().intValue()) {
                            filter = (filter.isEmpty())
                                    ? String.format("shopkeeper_id=%d", roleMerchantRefInfoModel.getMerchantId())
                                    : String.format("shopkeeper_id=%d or %s", roleMerchantRefInfoModel.getMerchantId(),
                                    filter);
                        } else if (roleMerchantRefInfoModel.getMerchantType()
                                .intValue() == MerchantTypeEnum.JIA_MENG_SHANG.getCode().intValue()) {
                            filter = (filter.isEmpty())
                                    ? String.format("alliance_business_id=%d", roleMerchantRefInfoModel.getMerchantId())
                                    : String.format("alliance_business_id=%d or %s",
                                    roleMerchantRefInfoModel.getMerchantId(), filter);
                        } else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.TERMINAL
                                .getCode().intValue()) {
                            filter = (filter.isEmpty())
                                    ? String.format("online_merchant_id=%d", roleMerchantRefInfoModel.getMerchantId())
                                    : String.format("online_merchant_id=%d or %s",
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
     * 修改device
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ShareDeviceInfoBO deviceInfo) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数 并判断接收的参数是否正确
            Integer userId = ShiroKit.getUser().getId();
            Boolean validate = validateDeviceInfoForSave(deviceInfo, true, result);
            if (!validate) {
                return result;
            }
            logger.info("修改device---update:{}", deviceInfo);
            if (deviceInfo.getId() == null) {
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("请选择正确的充电设备！");
                return false;
            }
            ShareDeviceInfoModel tmp = shareDeviceInfoModelMapper.selectByPrimaryKey(deviceInfo.getId());
            if (tmp == null) {
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage(String.format("充电设备(%d)在系统中不存在，请选择正确的充电设备！", deviceInfo.getId()));
                return result;
            }
            if (deviceInfoService.isBindMer(tmp, deviceInfo)) {
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("请先解绑" + deviceInfo.getId() + "设备上的商户再做绑定操作！");
                return result;
            }
            // 如果是激活，增加激活时间
            if (tmp.getDeviceStatus().intValue() != ShareDeviceStatusEnum.activation.getCode()
                    && deviceInfo.getDeviceStatus().intValue() == ShareDeviceStatusEnum.activation.getCode()) {
                deviceInfo.setOnlineDatetime(new Date());
            }
            if (deviceInfo.getAgents1Id() == null) {
                deviceInfo.setAgents1Id(0L);
            }
            if (deviceInfo.getAgents2Id() == null) {
                deviceInfo.setAgents2Id(0L);
            }
            if (deviceInfo.getAgents3Id() == null) {
                deviceInfo.setAgents3Id(0L);
            }
            if (deviceInfo.getShopkeeperId() == null) {
                deviceInfo.setShopkeeperId(0L);
            }
            if (deviceInfo.getAllianceBusinessId() == null) {
                deviceInfo.setAllianceBusinessId(0L);
            }

            deviceInfo.setCreateId(userId.longValue());
            deviceInfo.setUpdateDatetime(new Date());
            // 1.2.判断是否要绑定商户 如果绑定处理绑定商户业务
            if (tmp.getActivationMode() == null) {
                tmp.setActivationMode(new Integer(activationMode));
            }
            // 2. 判断是否有权限，如果没有
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                HashMap<String, Object> pageFilter = new HashMap<>();
                pageFilter.put("id", deviceInfo.getId());
                initPageFilterForRight(pageFilter);
                // 查询是否有数据...
                Long cnt = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
                if (cnt == null || cnt <= 0) {
                    logger.info("当前设备deviceInfo.getId():{},您无权对其进行修改操作，请联系管理员设置权限", deviceInfo.getId());
                    result.setMessage(String.format("设备(%d)您无权限修改，请联系管理员设置数据权限！", deviceInfo.getId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            }

            logger.info("修改device---tmp.getDeviceStatus():{},deviceInfo.getDeviceStatus():{}", tmp.getDeviceStatus(),
                    deviceInfo.getDeviceStatus());
            logger.info("修改device---tmp.getActivationMode():{},tmp.getActivationMode().intValue():{},",
                    tmp.getActivationMode(), tmp.getActivationMode().intValue());
            // 用户修改了设备状态..
            if (tmp.getDeviceStatus() != null && !tmp.getDeviceStatus().equals(deviceInfo.getDeviceStatus())) {
                if (tmp.getActivationMode() != null
                        && tmp.getActivationMode().intValue() == ShareDeviceActivationModeEnum.waitForConfirmationStatus
                        .getCode()
                        && deviceInfo.getDeviceStatus() != null
                        && deviceInfo.getDeviceStatus().intValue() == ShareDeviceStatusEnum.activation.getCode()) {
                    // 绑定商户
                    deviceInfoHelperService.bindMerchantForDevice(deviceInfo);
                }
            }
            // 2.2 处理分润的问题
            BigDecimal platformRato = new BigDecimal("100");
            Double dbTmp = null;
            if (deviceInfo.getAgents1Rato() != null) {
                dbTmp = Math.abs(deviceInfo.getAgents1Rato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getAgents2Rato() != null) {
                dbTmp = Math.abs(deviceInfo.getAgents2Rato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getAgents3Rato() != null) {
                dbTmp = Math.abs(deviceInfo.getAgents3Rato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getShopkeeperRato() != null) {
                dbTmp = Math.abs(deviceInfo.getShopkeeperRato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getAllianceBusinessRate() != null) {
                dbTmp = Math.abs(deviceInfo.getAllianceBusinessRate().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (platformRato.compareTo(new BigDecimal("0")) == -1
                    || platformRato.compareTo(new BigDecimal("100")) == 1) {
                result.setMessage("输入的所有商户分润绝对值之和不等于100,请输入正确的分润比例！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3.1 如果输入值，则全部要更新
            if (deviceInfo.getAgents1Rato() == null) {
                deviceInfo.setAgents1Rato(new BigDecimal("0"));
            }
            if (deviceInfo.getAgents2Rato() == null) {
                deviceInfo.setAgents2Rato(new BigDecimal("0"));
            }
            if (deviceInfo.getAgents3Rato() == null) {
                deviceInfo.setAgents3Rato(new BigDecimal("0"));
            }
            if (deviceInfo.getShopkeeperRato() == null) {
                deviceInfo.setShopkeeperRato(new BigDecimal("0"));
            }
            if (deviceInfo.getAllianceBusinessRate() == null) {
                deviceInfo.setAllianceBusinessRate(new BigDecimal("0"));
            }

            deviceInfo.setPlatformRato(platformRato);

            // 2. 修改设备信息...
            shareDeviceInfoModelBySelfMapper.update(deviceInfo);
//			shareDeviceInfoModelMapper.updateByPrimaryKeySelective(deviceInfo);
            result.setMessage(String.format("设备(%d)修改成功！", deviceInfo.getId()));
            result.setResult(ResultCommandBaseObject.SUCCESS);

        } catch (Exception e) {
            logger.error("设备修改失败,{}", e);
            // TODO: handle exception
            result.setMessage(String.format("设备(%d)修改失败！%s", deviceInfo.getId(), e.getMessage()));
            result.setResult(ResultCommandBaseObject.FAILED);
        }
        return result;
    }

    /**
     * device详情
     */
    @RequestMapping(value = "/detail/{deviceInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("deviceInfoId") Long deviceInfoId) {
        return shareDeviceInfoModelMapper.selectByPrimaryKey(deviceInfoId);
    }

    /**
     * 根据商户id查询商户
     */
    @RequestMapping(value = "/selectMerchant")
    @ResponseBody
    public Object selectMerchant(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // return deviceInfoService.selectById(deviceInfoId);
        String merchantId = request.getParameter("merchantId");
        String merchantTypeId = request.getParameter("merchantTypeId");
        logger.info(String.format("selectMerchant-根据商户id查询商户merchantId-%s", merchantId));
        try {
            // 1. 获取用户头像
            /*
             * Integer id = ShiroKit.getUser().getId(); User user =
             * userService.selectById(id);
             */
            // 2. 商户类型id
            Integer intMerchantTypeId = new Integer(merchantTypeId);
            // 3. 查询商户id
            MerchantInfoModel infoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.parseLong(merchantId));
            if (infoModel == null) {
                logger.info(String.format("您输入的终端店铺(%s)在系统中不存在，请输入正确的终端编号!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage(String.format("您输入的终端店铺(%s)在系统中不存在，请输入正确的终端编号!", merchantId));
            } else {
                if (infoModel.getMerchantType() != null
                        && !(infoModel.getMerchantType().intValue() == intMerchantTypeId.intValue())) {
                    String merchantTypeName = MerchantTypeEnum.getDesc(intMerchantTypeId);
                    String merchantTypeNameInput = (infoModel.getMerchantType() == null) ? ""
                            : MerchantTypeEnum.getDesc(infoModel.getMerchantType().intValue());
                    logger.info(String.format("您输入的商户类型(%s)不正确，请输入正确的%s编号!", merchantTypeNameInput, merchantTypeName));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    result.setMessage(
                            String.format("您输入的商户类型(%s)不正确，请输入正确的%s编号!", merchantTypeNameInput, merchantTypeName));
                } else {
                    result.setResponseInfo(infoModel.getName());
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

    /**
     * 根据商户id查询商户
     */
    @RequestMapping(value = "/getAllMerchantInfById")
    @ResponseBody
    public Object getAllMerchantInfById(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // return deviceInfoService.selectById(deviceInfoId);
        String merchantId = request.getParameter("merchantId");
        String merchantTypeId = request.getParameter("merchantTypeId");
        logger.info(String.format("selectMerchant-根据商户id查询商户merchantId-%s", merchantId));
        try {
            // 1. 获取用户头像
            /*
             * Integer id = ShiroKit.getUser().getId(); User user =
             * userService.selectById(id);
             */
            // 2. 商户类型id
            Integer intMerchantTypeId = new Integer(merchantTypeId);
            // 3. 查询商户id
            MerchantInfoModel infoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.parseLong(merchantId));
            if (infoModel == null) {
                logger.info(String.format("您输入的终端店铺(%s)在系统中不存在，请输入正确的终端编号!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage(String.format("您输入的终端店铺(%s)在系统中不存在，请输入正确的终端编号!", merchantId));
            } else {
                if (infoModel.getMerchantType() != null
                        && !(infoModel.getMerchantType().intValue() == intMerchantTypeId.intValue())) {
                    String merchantTypeName = MerchantTypeEnum.getDesc(intMerchantTypeId);
                    String merchantTypeNameInput = (infoModel.getMerchantType() == null) ? ""
                            : MerchantTypeEnum.getDesc(infoModel.getMerchantType().intValue());
                    logger.info(String.format("您输入的商户类型(%s)不正确，请输入正确的%s编号!", merchantTypeNameInput, merchantTypeName));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    result.setMessage(
                            String.format("您输入的商户类型(%s)不正确，请输入正确的%s编号!", merchantTypeNameInput, merchantTypeName));
                } else {
                    result.setResponseInfo(infoModel.getName());
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

    /**
     * 批量生成生成设备信息
     */
    @RequestMapping(value = "/batchAddSubmit")
    @ResponseBody
    public Object batchAddSubmit(ShareDeviceInfoForBatchModel deviceInfo) {
        logger.info("批量生成生成设备信息-batchAddSubmit{}", deviceInfo);
        String msg = "";
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        Boolean validate = validateDeviceInfoForSave(deviceInfo, false, result);
        if (!validate) {
            return result;
        }
        //表示是复杂密码模式
        if(deviceInfo!=null&&deviceInfo.getPasswordType()!=null
        		&&deviceInfo.getPasswordType().equals(ShareDeviceInfoNumberTypeEnum.YYMMBBXXXXX.getCode())){
        	String tmp="";
        	if(deviceInfo.getPasswordKey()==null||deviceInfo.getPasswordKey().isEmpty()){
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("请输入生成编号对应的密钥 ！");
                return result;
        	}
        	//1处理密码月
        	if(deviceInfo.getPasswordMonth()==null||deviceInfo.getPasswordMonth().isEmpty()){
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("请输入生成编号对应的月 ！");
                return result;
        	}
        	tmp=String.format("00%s", deviceInfo.getPasswordMonth());
            tmp=tmp.substring(tmp.length()-2);
            deviceInfo.setPasswordMonth(tmp);
        	//2处理密码年..
        	if(deviceInfo.getPasswordYear()==null||deviceInfo.getPasswordYear().isEmpty()){
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("请输入生成编号对应的年 ！");
                return result;
        	}
        	tmp=String.format("00%s", deviceInfo.getPasswordYear());
            tmp=tmp.substring(tmp.length()-2);
            deviceInfo.setPasswordYear(tmp);
        	//3处理批次
         	if(deviceInfo.getPasswordBatch()==null||deviceInfo.getPasswordBatch().isEmpty()){
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("请输入生成编号对应的批次号 ！");
                return result;
        	}
            Integer batch=null;
            try {
                batch=Integer.parseInt(deviceInfo.getPasswordBatch());				
			} catch (Exception e) {
				result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("生成编号对应的批次号必须是大于0小于10的整数 ！");
                return result;
			}
            int ibatch=batch.intValue();
            if(ibatch<1||ibatch>9){
				result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("生成编号对应的批次号必须是大于0小于10的整数 ！");
                return result;
            }  
            //保留二位
            tmp=String.format("00%d", ibatch);
            tmp=tmp.substring(tmp.length()-2);
            deviceInfo.setPasswordBatch(tmp);
        }
        //result.setResult(ResultCommandBaseObject.FAILED);
        //result.setMessage("请输入选择正确的充电设备！");
        //return false;
        
        deviceInfo.setCreateId(userId.longValue());
        deviceInfo.setUpdateDatetime(new Date());
        // 批量生成...
        try {
            if (deviceInfo.getActivationMode() == null) {
                try {
                    deviceInfo.setActivationMode(new Integer(activationMode));
                } catch (Exception e) {
                    // TODO: handle exception
                    deviceInfo.setActivationMode(ShareDeviceActivationModeEnum.activation.getCode());
                }
            }

            Long startDeviceId = deviceInfoHelperService.batchAddDeviceInfo(deviceInfo, userId);
            msg = String.format("设备(%d~%d)批量生成成功！", startDeviceId, startDeviceId + deviceInfo.getDeviceQty() - 1);
            logger.info(msg);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            logger.error("批量生成生成设备信息-batchAddSubmit", e);
            // TODO: handle exception
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 批量商户
     */
    @RequestMapping(value = "/batchBindMer")
    @ResponseBody
    public Object batchBindMer(ShareDeviceInfoForBatchModel deviceInfo) {
        logger.info("批量商户-batchBindMer{}", deviceInfo);
        String msg = "";
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // 批量生成...
        try {
            deviceInfo.setCreateId(userId.longValue());
            deviceInfo.setUpdateDatetime(new Date());

            String filter = null;
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                HashMap<String, Object> pageFilter = new HashMap<>();
                initPageFilterForRight(pageFilter);
                if (pageFilter.containsKey("filter")) {
                    filter = (String) pageFilter.get("filter");
                }
            }
            // 2.整理数据
            ShareDeviceInfoModel tmpShareDeviceInfoModel = new ShareDeviceInfoModel();
            if (deviceInfo.getOnlineMerchantId() != null) {
                tmpShareDeviceInfoModel.setOnlineMerchantId(deviceInfo.getOnlineMerchantId());
                tmpShareDeviceInfoModel.setOnlineMerchantCn(ToolUtil.unescapeHtml4(deviceInfo.getOnlineMerchantCn()));
            }
            if (deviceInfo.getAgents1Id() != null) {
                tmpShareDeviceInfoModel.setAgents1Id(deviceInfo.getAgents1Id());
                tmpShareDeviceInfoModel.setAgents1Cn(ToolUtil.unescapeHtml4(deviceInfo.getAgents1Cn()));
            }
            if (deviceInfo.getAgents2Id() != null) {
                tmpShareDeviceInfoModel.setAgents2Id(deviceInfo.getAgents2Id());
                tmpShareDeviceInfoModel.setAgents2Cn(ToolUtil.unescapeHtml4(deviceInfo.getAgents2Cn()));
            }
            if (deviceInfo.getAgents3Id() != null) {
                tmpShareDeviceInfoModel.setAgents3Id(deviceInfo.getAgents3Id());
                tmpShareDeviceInfoModel.setAgents3Cn(ToolUtil.unescapeHtml4(deviceInfo.getAgents3Cn()));
            }
            if (deviceInfo.getShopkeeperId() != null) {
                tmpShareDeviceInfoModel.setShopkeeperId(deviceInfo.getShopkeeperId());
                tmpShareDeviceInfoModel.setShopkeeperCn(ToolUtil.unescapeHtml4(deviceInfo.getShopkeeperCn()));
            }
            if (deviceInfo.getAllianceBusinessId() != null) {
                tmpShareDeviceInfoModel.setAllianceBusinessId(deviceInfo.getAllianceBusinessId());
                tmpShareDeviceInfoModel
                        .setAllianceBusinessCn(ToolUtil.unescapeHtml4(deviceInfo.getAllianceBusinessCn()));
            }
            if (deviceInfo.getOnlineMerchantId() == null && deviceInfo.getAgents1Id() == null
                    && deviceInfo.getAgents2Id() == null && deviceInfo.getAgents3Id() == null
                    && deviceInfo.getShopkeeperId() == null && deviceInfo.getAllianceBusinessId() == null) {
                result.setMessage("未输入任何要绑定的商户号，请输入要绑定的商户号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 处理分润
            if (deviceInfo.getAgents1Rato() != null || deviceInfo.getAgents2Rato() != null
                    || deviceInfo.getAgents3Rato() != null || deviceInfo.getShopkeeperRato() != null
                    || deviceInfo.getAllianceBusinessRate() != null) {
                BigDecimal platformRato = new BigDecimal("100");
                Double dbTmp = null;
                if (deviceInfo.getAgents1Rato() != null) {
                    dbTmp = Math.abs(deviceInfo.getAgents1Rato().doubleValue());
                    platformRato = platformRato.subtract(new BigDecimal(dbTmp));
                }
                if (deviceInfo.getAgents2Rato() != null) {
                    dbTmp = Math.abs(deviceInfo.getAgents2Rato().doubleValue());
                    platformRato = platformRato.subtract(new BigDecimal(dbTmp));
                }
                if (deviceInfo.getAgents3Rato() != null) {
                    dbTmp = Math.abs(deviceInfo.getAgents3Rato().doubleValue());
                    platformRato = platformRato.subtract(new BigDecimal(dbTmp));
                }
                if (deviceInfo.getShopkeeperRato() != null) {
                    dbTmp = Math.abs(deviceInfo.getShopkeeperRato().doubleValue());
                    platformRato = platformRato.subtract(new BigDecimal(dbTmp));
                }
                if (deviceInfo.getAllianceBusinessRate() != null) {
                    dbTmp = Math.abs(deviceInfo.getAllianceBusinessRate().doubleValue());
                    platformRato = platformRato.subtract(new BigDecimal(dbTmp));
                }
                if (platformRato.compareTo(new BigDecimal("0")) == -1
                        || platformRato.compareTo(new BigDecimal("100")) == 1) {
                    result.setMessage("输入的所有商户分润绝对值之和不等于100,请输入正确的分润比例！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 3.1 如果输入值，则全部要更新
                if (deviceInfo.getAgents1Rato() == null) {
                    deviceInfo.setAgents1Rato(new BigDecimal("0"));
                }
                if (deviceInfo.getAgents2Rato() == null) {
                    deviceInfo.setAgents2Rato(new BigDecimal("0"));
                }
                if (deviceInfo.getAgents3Rato() == null) {
                    deviceInfo.setAgents3Rato(new BigDecimal("0"));
                }
                if (deviceInfo.getShopkeeperRato() == null) {
                    deviceInfo.setShopkeeperRato(new BigDecimal("0"));
                }
                if (deviceInfo.getAllianceBusinessRate() == null) {
                    deviceInfo.setAllianceBusinessRate(new BigDecimal("0"));
                }

                tmpShareDeviceInfoModel.setPlatformRato(platformRato);
                tmpShareDeviceInfoModel.setAgents1Rato(deviceInfo.getAgents1Rato());
                tmpShareDeviceInfoModel.setAgents2Rato(deviceInfo.getAgents2Rato());
                tmpShareDeviceInfoModel.setAgents3Rato(deviceInfo.getAgents3Rato());
                tmpShareDeviceInfoModel.setShopkeeperRato(deviceInfo.getShopkeeperRato());
                tmpShareDeviceInfoModel.setAllianceBusinessRate(deviceInfo.getAllianceBusinessRate());
            }
            tmpShareDeviceInfoModel.setUpdateDatetime(new Date());
            tmpShareDeviceInfoModel.setCreateId(userId.longValue());
            //
            if (deviceInfo.getSelectDeviceNoType() == 1) {
                // 根多个乱的编辑。。。
                if (deviceInfo.getDeviceNoStr() == null || StringUtils.isEmpty(deviceInfo.getDeviceNoStr())) {
                    logger.info(String.format("`批量商户-getDeviceNoStr:%s", deviceInfo.getDeviceNoStr()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定商户的设备号，多个用逗号分隔！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 2. 开始生
                String deviceNoStr = deviceInfo.getDeviceNoStr();
                deviceNoStr = StringUtil.replaceBlank(deviceNoStr);
                String[] aryDeviceNo = deviceNoStr.split(",");
                String msgForList = "";
                String msgForDeviceType = "";
                List<Long> listForDeviceNo = new ArrayList<>();

                HashMap<String, Object> params = new HashMap<>();
                params.put("deviceIdList", aryDeviceNo);
                List<ShareDeviceInfoBySelfModel> allDevices = shareDeviceInfoModelBySelfMapper
                        .selectByConditionForPage(params);
                boolean isExists = Boolean.FALSE;
                for (int i = 0; i < aryDeviceNo.length; i++) {
                    isExists = Boolean.FALSE;
                    for (ShareDeviceInfoBySelfModel device : allDevices) {

                        if (aryDeviceNo[i].equals(String.valueOf(device.getId()))) {
                            //校验设备是否已经绑定了商户
                            if (deviceInfoService.isBindMer(device, tmpShareDeviceInfoModel)) {
                                result.setMessage("请先解绑" + device.getId() + "设备上的商户再做绑定操作！");
                                result.setResult(ResultCommandBaseObject.FAILED);
                                return result;
                            }
                            // 设备存在
                            isExists = Boolean.TRUE;
                            if (device.getDeviceTypeId() == null
                                    || device.getDeviceTypeId().equals(deviceInfo.getDeviceTypeId())) {
                                msgForDeviceType = (msgForDeviceType.isEmpty()) ? aryDeviceNo[i]
                                        : String.format("%s,%s", msgForDeviceType, aryDeviceNo[i]);
                            } else {
                                listForDeviceNo.add(device.getId());
                            }
                            break;
                        }
                    }
                    // 设备不存在
                    if (!isExists) {
                        msgForList = (msgForList.isEmpty()) ? aryDeviceNo[i]
                                : String.format("%s,%s", msgForList, aryDeviceNo[i]);
                    }

                }

                if (!msgForList.isEmpty()) {
                    msg = String.format("下例设备编号在系统中不存在，请输入正确的设备编号!\n\r%s", msgForList);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }

                if (!msgForDeviceType.isEmpty()) {
                    msg = String.format("你输入的下例充电器对应的充电器类型与要绑定的设备类型不一至!\n\r%s", msgForDeviceType);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                tmpShareDeviceInfoModel.setUpdateDatetime(new Date());
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("deviceIdList", listForDeviceNo);
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchBindMerByDeviceNoList(tmpShareDeviceInfoModel, listForDeviceNo,
                        filter);
            } else {
                // 根开始结束设备号批理绑定
                if (deviceInfo.getStartDeviceNo() == null || deviceInfo.getEndDeviceNo() == null) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定的开始和结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                if (deviceInfo.getStartDeviceNo() > deviceInfo.getEndDeviceNo()) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("输入开始设备号大于结束设备号,请输入正确的开始结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("start", deviceInfo.getStartDeviceNo());
                paramMap.put("end", deviceInfo.getEndDeviceNo());
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                boolean isValidateBindMer = deviceInfoService.isBindMer(deviceInfo.getStartDeviceNo(), deviceInfo.getEndDeviceNo(), tmpShareDeviceInfoModel);
                if (isValidateBindMer) {
                    result.setMessage("请先把这批设备的商户解绑，再做批量绑定操作！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchBindMerByStartEndDeviceNo(tmpShareDeviceInfoModel,
                        deviceInfo.getStartDeviceNo(), deviceInfo.getEndDeviceNo(), filter);
            }

            msg = "批量绑定商户成功！";
            logger.info(msg);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            logger.error("批量生成生成设备信息-batchAddSubmit", e);
            // TODO: handle exception
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * @param pageFilter
     */
    private ResultCommandBaseObject<Object> validateOppDeviceCount(HashMap<String, Object> pageFilter) {
        Long oppCount = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
        Long maxCount = 1000L;
        if (oppCount > maxCount) {
            ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
            result.setMessage(String.format("批量操作设备不能超过%s个", maxCount));
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
        return null;
    }

    /**
     * 批量解绑商户
     */
    @RequestMapping(value = "/batchUnBindMer")
    @ResponseBody
    public Object batchUnBindMer(ShareDeviceInfoForBatchModel deviceInfo) {
        logger.info("批量商户-batchBindMer{}", deviceInfo);
        String msg = "";
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // 批量生成...
        try {
            deviceInfo.setCreateId(userId.longValue());
            deviceInfo.setUpdateDatetime(new Date());
            // 2.整理数据
            ShareDeviceInfoModel tmpShareDeviceInfoModel = new ShareDeviceInfoModel();
            if (deviceInfo.getChkMerchantId() == 1) {
                tmpShareDeviceInfoModel.setOnlineMerchantId(0L);
                tmpShareDeviceInfoModel.setOnlineMerchantCn("");
            }
            if (deviceInfo.getChkAgents1Id() == 1) {
                tmpShareDeviceInfoModel.setAgents1Id(0L);
                tmpShareDeviceInfoModel.setAgents1Cn("");
            }
            if (deviceInfo.getChkAgents2Id() == 1) {
                tmpShareDeviceInfoModel.setAgents2Id(0L);
                tmpShareDeviceInfoModel.setAgents2Cn("");
            }
            if (deviceInfo.getChkAgents3Id() == 1) {
                tmpShareDeviceInfoModel.setAgents3Id(0L);
                tmpShareDeviceInfoModel.setAgents3Cn("");
            }
            if (deviceInfo.getChkShopkeeperId() == 1) {
                tmpShareDeviceInfoModel.setShopkeeperId(0L);
                tmpShareDeviceInfoModel.setShopkeeperCn("");
            }
            if (deviceInfo.getChkAllianceBusinessId() == 1) {
                tmpShareDeviceInfoModel.setAllianceBusinessId(0L);
                tmpShareDeviceInfoModel.setAllianceBusinessCn("");
            }
            if (deviceInfo.getChkMerchantId() != 1 && deviceInfo.getChkAgents1Id() != 1
                    && deviceInfo.getChkAgents2Id() != 1 && deviceInfo.getChkAgents3Id() != 1
                    && deviceInfo.getChkShopkeeperId() != 1 && deviceInfo.getChkAllianceBusinessId() != 1) {
                result.setMessage("请选择要解绑的商户类型！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 处理分润
            String filter = null;
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                HashMap<String, Object> pageFilter = new HashMap<>();
                initPageFilterForRight(pageFilter);
                if (pageFilter.containsKey("filter")) {
                    filter = (String) pageFilter.get("filter");
                }
            }

            tmpShareDeviceInfoModel.setUpdateDatetime(new Date());
            tmpShareDeviceInfoModel.setCreateId(userId.longValue());

            ShareDeviceInfoModelExample example = new ShareDeviceInfoModelExample();
            Criteria criteria = example.createCriteria();
            //
            if (deviceInfo.getSelectDeviceNoType() == 1) {
                // 根多个乱的编辑。。。
                if (deviceInfo.getDeviceNoStr() == null || StringUtils.isEmpty(deviceInfo.getDeviceNoStr())) {
                    logger.info(String.format("`批量商户-getDeviceNoStr:%s", deviceInfo.getDeviceNoStr()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定商户的设备号，多个用逗号分隔！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 2. 开始生
                String deviceNoStr = deviceInfo.getDeviceNoStr();
                deviceNoStr = StringUtil.replaceBlank(deviceNoStr);
                String[] aryDeviceNo = deviceNoStr.split(",");

                String msgForList = "";
                String msgForDeviceType = "";
                List<Long> listForDeviceNo = new ArrayList<>();

                HashMap<String, Object> params = new HashMap<>();
                params.put("deviceIdList", aryDeviceNo);
                List<ShareDeviceInfoBySelfModel> allDevices = shareDeviceInfoModelBySelfMapper
                        .selectByConditionForPage(params);
                boolean isExists = Boolean.FALSE;
                for (int i = 0; i < aryDeviceNo.length; i++) {
                    isExists = Boolean.FALSE;
                    for (ShareDeviceInfoBySelfModel device : allDevices) {
                        if (aryDeviceNo[i].equals(String.valueOf(device.getId()))) {
                            // 设备存在
                            isExists = Boolean.TRUE;
                            if (device.getDeviceTypeId() == null
                                    || device.getDeviceTypeId().equals(deviceInfo.getDeviceTypeId())) {
                                msgForDeviceType = (msgForDeviceType.isEmpty()) ? aryDeviceNo[i]
                                        : String.format("%s,%s", msgForDeviceType, aryDeviceNo[i]);
                            } else {
                                listForDeviceNo.add(device.getId());
                            }
                            break;
                        }
                    }
                    // 设备不存在
                    if (!isExists) {
                        msgForList = (msgForList.isEmpty()) ? aryDeviceNo[i]
                                : String.format("%s,%s", msgForList, aryDeviceNo[i]);
                    }

                }

                if (!msgForList.isEmpty()) {
                    msg = String.format("下例设备编号在系统中不存在，请输入正确的设备编号!\n\r%s", msgForList);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }

                if (!msgForDeviceType.isEmpty()) {
                    msg = String.format("你输入的下例充电器对应的充电器类型与要绑定的设备类型不一至!\n\r%s", msgForDeviceType);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }

                // 开始
                tmpShareDeviceInfoModel.setUpdateDatetime(new Date());
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("deviceIdList", listForDeviceNo);
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                shareDeviceInfoModelBySelfMapper.batchBindMerByDeviceNoList(tmpShareDeviceInfoModel, listForDeviceNo,
                        filter);
            } else if (deviceInfo.getSelectDeviceNoType() == 2) {
                // 根开始结束设备号批理绑定
                if (deviceInfo.getStartDeviceNo() == null || deviceInfo.getEndDeviceNo() == null) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定的开始和结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                if (deviceInfo.getStartDeviceNo() > deviceInfo.getEndDeviceNo()) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("输入开始设备号大于结束设备号,请输入正确的开始结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("start", deviceInfo.getStartDeviceNo());
                paramMap.put("end", deviceInfo.getEndDeviceNo());
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchBindMerByStartEndDeviceNo(tmpShareDeviceInfoModel,
                        deviceInfo.getStartDeviceNo(), deviceInfo.getEndDeviceNo(), filter);
            } else {
                // 根商户号绑定..
                if (deviceInfo.getOnlineMerchantId() == null || deviceInfo.getOnlineMerchantId() == 0) {

                    logger.info(String.format("`批量商户-getOnlineMerchantId:%s", deviceInfo.getOnlineMerchantId()));
                    // TODO: handle exception
                    result.setMessage("请输入终端商户号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                shareDeviceInfoModelBySelfMapper.batchModifyByOnlineMerchId(tmpShareDeviceInfoModel,
                        deviceInfo.getOnlineMerchantId(), filter);
            }

            msg = "批量绑定商户成功！";
            logger.info(msg);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            logger.error("批量生成生成设备信息-batchAddSubmit", e);
            // TODO: handle exception
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 批量绑定费用..
     */
    @RequestMapping(value = "/batchBindFee")
    @ResponseBody
    public Object batchBindFee(ShareDeviceInfoForBatchModel deviceInfo) {
        logger.info("批量绑定费用.-batchBindMer{}", deviceInfo);
        String msg = "";
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String filter = null;
        // 1.1增加权限过虑
        // 权限 管理员有全部权限admin...
        if (userId != null && userId.intValue() != 1) {
            HashMap<String, Object> pageFilter = new HashMap<>();
            initPageFilterForRight(pageFilter);
            if (pageFilter.containsKey("filter")) {
                filter = (String) pageFilter.get("filter");
            }
        }

        // 批量生成...
        try {
            // 1.整理数据
            if (deviceInfo.getDeviceTypeId() == null) {
                result.setMessage("请选择正确的设备类型！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Boolean validate = validateDeviceInfoForSave(deviceInfo, true, result);
            if (!validate) {
                return result;
            }
            ShareDeviceInfoModel tmpShareDeviceInfoModel = new ShareDeviceInfoModel();
            tmpShareDeviceInfoModel.setFeeTypeId(deviceInfo.getFeeTypeId());
            tmpShareDeviceInfoModel.setFeeTypeName(deviceInfo.getFeeTypeName());
            tmpShareDeviceInfoModel.setAmountMax24hour(deviceInfo.getAmountMax24hour());
            tmpShareDeviceInfoModel.setAmountPerHour(deviceInfo.getAmountPerHour());
            tmpShareDeviceInfoModel.setFirstAmount(deviceInfo.getFirstAmount());
            tmpShareDeviceInfoModel.setFirstMinutes(deviceInfo.getFirstMinutes());
            tmpShareDeviceInfoModel.setYfjAmount(deviceInfo.getYfjAmount());
            tmpShareDeviceInfoModel.setFee1HourAmount(deviceInfo.getFee1HourAmount());
            tmpShareDeviceInfoModel.setFee1HourType(deviceInfo.getFee1TypeId());
            tmpShareDeviceInfoModel.setFee1TypeId(deviceInfo.getFee1TypeId());
            tmpShareDeviceInfoModel.setFee1TypeName(deviceInfo.getFee1TypeName());
            tmpShareDeviceInfoModel.setFee2HourAmount(deviceInfo.getFee2HourAmount());
            tmpShareDeviceInfoModel.setFee2HourType(deviceInfo.getFee2TypeId());
            tmpShareDeviceInfoModel.setFee2TypeId(deviceInfo.getFee2TypeId());
            tmpShareDeviceInfoModel.setFee2TypeName(deviceInfo.getFee2TypeName());
            tmpShareDeviceInfoModel.setFee3HourAmount(deviceInfo.getFee3HourAmount());
            tmpShareDeviceInfoModel.setFee3HourType(deviceInfo.getFee3TypeId());
            tmpShareDeviceInfoModel.setFee3TypeId(deviceInfo.getFee3TypeId());
            tmpShareDeviceInfoModel.setFee3TypeName(deviceInfo.getFee3TypeName());

            ShareDeviceInfoModelExample example = new ShareDeviceInfoModelExample();
            Criteria criteria = example.createCriteria();
            List<ShareDeviceInfoModel> listFilter = null;
            if (deviceInfo.getSelectDeviceNoType() == 1) {
                // 根多个乱的编辑。。。
                if (deviceInfo.getDeviceNoStr() == null || StringUtils.isEmpty(deviceInfo.getDeviceNoStr())) {
                    logger.info(String.format("`批量绑定费用-batchBindFee-deviceInfo.getDeviceNoStr:%s",
                            deviceInfo.getDeviceNoStr()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定费用的设备号，多个用逗号分隔！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 2. 开始生
                String deviceNoStr = deviceInfo.getDeviceNoStr();
                deviceNoStr = StringUtil.replaceBlank(deviceNoStr);
                String[] aryDeviceNo = deviceNoStr.split(",");

                String msgForList = "", msgForDeviceType = "";
                List<Long> listForDeviceNo = new ArrayList<>();

                HashMap<String, Object> params = new HashMap<>();
                params.put("deviceIdList", aryDeviceNo);
                List<ShareDeviceInfoBySelfModel> allDevices = shareDeviceInfoModelBySelfMapper
                        .selectByConditionForPage(params);
                boolean isExists = Boolean.FALSE;
                for (int i = 0; i < aryDeviceNo.length; i++) {
                    isExists = Boolean.FALSE;
                    for (ShareDeviceInfoBySelfModel device : allDevices) {
                        if (aryDeviceNo[i].equals(String.valueOf(device.getId()))) {
                            // 设备存在
                            isExists = Boolean.TRUE;
                            if (device.getDeviceTypeId() == null
                                    || !device.getDeviceTypeId().equals(deviceInfo.getDeviceTypeId())) {
                                msgForDeviceType = (msgForDeviceType.isEmpty()) ? aryDeviceNo[i]
                                        : String.format("%s,%s", msgForDeviceType, aryDeviceNo[i]);
                            } else {
                                listForDeviceNo.add(device.getId());
                            }
                            break;
                        }
                    }
                    // 设备不存在
                    if (!isExists) {
                        msgForList = (msgForList.isEmpty()) ? aryDeviceNo[i]
                                : String.format("%s,%s", msgForList, aryDeviceNo[i]);
                    }

                }

                if (!msgForList.isEmpty()) {
                    msg = String.format("下例设备编号在系统中不在存，请输入正确的设备编号!\n\r%s", msgForList);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                if (!msgForDeviceType.isEmpty()) {
                    msg = String.format("你输入的设备与选择的设备类型不一至,无法批量设备，请按设备类型分批量设备!\n\r%s", msgForDeviceType);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("deviceIdList", listForDeviceNo);
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchBindFeeByDeviceNoList(tmpShareDeviceInfoModel, listForDeviceNo,
                        filter);
            } else if (deviceInfo.getSelectDeviceNoType() == 2) {
                // 根开始结束设备号批理绑定
                if (deviceInfo.getStartDeviceNo() == null || deviceInfo.getEndDeviceNo() == null) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定的开始和结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                if (deviceInfo.getStartDeviceNo() > deviceInfo.getEndDeviceNo()) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("输入开始设备号小于结束设备号,请输入正确的开始结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                criteria.andIdBetween(deviceInfo.getStartDeviceNo(), deviceInfo.getEndDeviceNo());
                criteria.andDeviceTypeIdNotEqualTo(deviceInfo.getDeviceTypeId().intValue());
                example.setOrderByClause("id limit 0,10");
                List<ShareDeviceInfoModel> tmpList = shareDeviceInfoModelMapper.selectByExample(example);
                if (tmpList != null && tmpList.size() > 0) {
                    logger.info(String.format(
                            "你输入的设备编号部分设备对应的设备类型不一至,无法批量设置，请按设备类型批量设置-getOnlineMerchantId:%d-getDeviceTypeId:%d",
                            deviceInfo.getOnlineMerchantId(), deviceInfo.getDeviceTypeId()));
                    // TODO: handle exception
                    result.setMessage("你输入的设备编号部分设备对应的设备类型不一至,请按设备类型分批量设置！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("start", deviceInfo.getStartDeviceNo());
                paramMap.put("end", deviceInfo.getEndDeviceNo());
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchBindFeeByStartEndDeviceNo(tmpShareDeviceInfoModel,
                        deviceInfo.getStartDeviceNo(), deviceInfo.getEndDeviceNo(), filter);
            } else {
                // 根商户号绑定..
                if (deviceInfo.getOnlineMerchantId() == null || deviceInfo.getOnlineMerchantId() == 0) {

                    logger.info(String.format("`批量商户-getOnlineMerchantId:%s", deviceInfo.getOnlineMerchantId()));
                    // TODO: handle exception
                    result.setMessage("请输入要终端商户号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                criteria.andOnlineMerchantIdEqualTo(deviceInfo.getOnlineMerchantId());
                criteria.andDeviceTypeIdNotEqualTo(deviceInfo.getDeviceTypeId().intValue());
                example.setOrderByClause("id limit 0,10");
                List<ShareDeviceInfoModel> tmpList = shareDeviceInfoModelMapper.selectByExample(example);
                if (tmpList != null && tmpList.size() > 0) {
                    logger.info(String.format(
                            "你输入的设备编号部分设备对应的设备类型不一至,无法批量设置，请按设备类型批量设置-getOnlineMerchantId:%d-getDeviceTypeId:%d",
                            deviceInfo.getOnlineMerchantId(), deviceInfo.getDeviceTypeId()));
                    // TODO: handle exception
                    result.setMessage("你输入的设备编号部分设备对应的设备类型不一至,请选择正确的设备类型批设置！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                shareDeviceInfoModelBySelfMapper.batchBindFeeByOnlineMerchId(tmpShareDeviceInfoModel,
                        deviceInfo.getOnlineMerchantId(), filter);
            }

            msg = "批量绑定费用成功！";
            logger.info(msg);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            logger.error("批量绑定费用失败--batchBindFee", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 批量绑定费用..
     */
    @RequestMapping(value = "/BatchBindOther")
    @ResponseBody
    public Object BatchBindOther(ShareDeviceInfoForBatchModel deviceInfo) {
        logger.info("批量绑定费用.-BatchBindOther{}", deviceInfo);
        String msg = "";
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        if (deviceInfo == null) {
            result.setMessage("请输入要批量修改的设备号！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
        Long merchantIdForUpdate = deviceInfo.getOnlineMerchantId();
        // 批量生成...
        try {
            // 1.1
            String filter = null;
            // 1.1增加权限过虑
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                HashMap<String, Object> pageFilter = new HashMap<>();
                initPageFilterForRight(pageFilter);
                if (pageFilter.containsKey("filter")) {
                    filter = (String) pageFilter.get("filter");
                }
            }
            ShareDeviceInfoModel tmpShareDeviceInfoModel = new ShareDeviceInfoModel();
            if (deviceInfo.getDeviceStatus() != null && deviceInfo.getDeviceStatus().intValue() != 0) {
                tmpShareDeviceInfoModel.setDeviceStatus(deviceInfo.getDeviceStatus());
                // 如果是激活，增加激活时间
                if (deviceInfo.getDeviceStatus().intValue() == ShareDeviceStatusEnum.activation.getCode()) {
                    deviceInfo.setOnlineDatetime(new Date());
                    tmpShareDeviceInfoModel.setOnlineDatetime(new Date());
                }
            }
            if (deviceInfo.getYfjRebackType() != null
                    && deviceInfo.getYfjRebackType().compareTo(new Integer("0")) == 1) {
                tmpShareDeviceInfoModel
                        .setYfjRebackTypeName(ShareDeviceYfjRebackTypeEnum.getDesc(deviceInfo.getYfjRebackType()));
                tmpShareDeviceInfoModel.setYfjRebackType(deviceInfo.getYfjRebackType());
            }
            if (deviceInfo.getRemark() != null && !deviceInfo.getRemark().isEmpty()) {
                tmpShareDeviceInfoModel.setRemark(deviceInfo.getRemark());
            }
            // 处理是否
            if (deviceInfo.getTerminalTelNo() != null && !deviceInfo.getTerminalTelNo().isEmpty()
                    && deviceInfo.getTerminalMerchantName() != null && !deviceInfo.getTerminalMerchantName().isEmpty()
                    && deviceInfo.getTerminalProvince() != null && !deviceInfo.getTerminalProvince().isEmpty()
                    && deviceInfo.getDeviceStatus() != null
                    && deviceInfo.getDeviceStatus().intValue() == ShareDeviceStatusEnum.activation.getCode()) {
                deviceInfo.setActivationMode(ShareDeviceActivationModeEnum.waitForConfirmationStatus.getCode());
                // 绑定商户
                deviceInfoHelperService.bindMerchantForDevice(deviceInfo);
                // 处理绑定
                tmpShareDeviceInfoModel.setOnlineAddress(deviceInfo.getOnlineAddress());
                tmpShareDeviceInfoModel.setOnlineCity(deviceInfo.getOnlineCity());
                tmpShareDeviceInfoModel.setOnlineProvince(deviceInfo.getOnlineProvince());
                tmpShareDeviceInfoModel.setOnlineZone(deviceInfo.getOnlineZone());
                tmpShareDeviceInfoModel.setOnlineDatetime(deviceInfo.getOnlineDatetime());
                tmpShareDeviceInfoModel.setOnlineMerchantId(deviceInfo.getOnlineMerchantId());
                tmpShareDeviceInfoModel.setOnlineMerchantCn(deviceInfo.getOnlineMerchantCn());
            }
            // 过虑条件

            if (deviceInfo.getSelectDeviceNoType() == 1) {
                // 根多个乱的编辑。。。
                if (deviceInfo.getDeviceNoStr() == null || StringUtils.isEmpty(deviceInfo.getDeviceNoStr())) {
                    logger.info(String.format("`批量绑定费用-batchBindFee-deviceInfo.getDeviceNoStr:%s",
                            deviceInfo.getDeviceNoStr()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定费用的设备号，多个用逗号分隔！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 2. 开始生
                String deviceNoStr = deviceInfo.getDeviceNoStr();
                deviceNoStr = StringUtil.replaceBlank(deviceNoStr);
                String[] aryDeviceNo = deviceNoStr.split(",");

                String msgForList = "";
                List<Long> listForDeviceNo = new ArrayList<>();

                HashMap<String, Object> params = new HashMap<>();
                params.put("deviceIdList", aryDeviceNo);
                List<ShareDeviceInfoBySelfModel> allDevices = shareDeviceInfoModelBySelfMapper
                        .selectByConditionForPage(params);
                boolean isExists = Boolean.FALSE;
                for (int i = 0; i < aryDeviceNo.length; i++) {
                    isExists = Boolean.FALSE;
                    for (ShareDeviceInfoBySelfModel device : allDevices) {
                        if (aryDeviceNo[i].equals(String.valueOf(device.getId()))) {
                            // 设备存在
                            isExists = Boolean.TRUE;
                            listForDeviceNo.add(device.getId());
                            break;
                        }
                    }
                    // 设备不存在
                    if (!isExists) {
                        msgForList = (msgForList.isEmpty()) ? aryDeviceNo[i]
                                : String.format("%s,%s", msgForList, aryDeviceNo[i]);
                    }

                }

                if (!msgForList.isEmpty()) {
                    msg = String.format("下例设备编号在系统中不在存，请输入正确的设备编号!\n\r%s", msgForList);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("deviceIdList", listForDeviceNo);
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchModifyByDeviceNoList(tmpShareDeviceInfoModel, listForDeviceNo,
                        filter);
            } else if (deviceInfo.getSelectDeviceNoType() == 2) {
                // 根开始结束设备号批理绑定
                if (deviceInfo.getStartDeviceNo() == null || deviceInfo.getEndDeviceNo() == null) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定的开始和结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                if (deviceInfo.getStartDeviceNo() > deviceInfo.getEndDeviceNo()) {
                    logger.info(String.format("`批量绑定费用-getDeviceNoStr:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("输入开始设备号小于结束设备号,请输入正确的开始结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("start", deviceInfo.getStartDeviceNo());
                paramMap.put("end", deviceInfo.getEndDeviceNo());
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchModifyByStartEndDeviceNo(tmpShareDeviceInfoModel,
                        deviceInfo.getStartDeviceNo(), deviceInfo.getEndDeviceNo(), filter);
            } else {
                // 根商户号绑定..
                if (merchantIdForUpdate == null || merchantIdForUpdate.equals(0L)) {

                    logger.info(String.format("`批量商户-getOnlineMerchantId:%s", deviceInfo.getOnlineMerchantId()));
                    // TODO: handle exception
                    result.setMessage("请输入终端商户号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                shareDeviceInfoModelBySelfMapper.batchModifyByOnlineMerchId(tmpShareDeviceInfoModel,
                        merchantIdForUpdate, filter);
            }

            msg = "批量修改成功！";
            logger.info(msg);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("批量修改成功--batchModify", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 得到设备的激活状态...
     */
    @RequestMapping(value = "/getDevicesActivationModeForBatch")
    @ResponseBody
    public Object getDeviceActivationModeForBatch(ShareDeviceInfoForBatchModel deviceInfo) {
        logger.info("得到设备的激活状态....-getDeviceActivationModeForBatch{}", deviceInfo);
        String msg = "";
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<ShareDeviceInfoModel> result = new ResultCommandBaseObject<>();
        // 批量生成...
        try {
            if (deviceInfo == null) {
                result.setMessage("请输入要批量修改的设备号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            HashMap<String, Object> pageFilter = new HashMap<>();
            if (deviceInfo.getSelectDeviceNoType() == 1) {
                // 根多个乱的编辑。。。
                if (deviceInfo.getDeviceNoStr() == null || StringUtils.isEmpty(deviceInfo.getDeviceNoStr())) {
                    logger.info(String.format("`批量绑定-getDeviceActivationModeForBatch-deviceInfo.getDeviceNoStr:%s",
                            deviceInfo.getDeviceNoStr()));
                    // TODO: handle exception
                    result.setMessage("请输入要批量修改的设备号，多个用逗号分隔！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 2. 开始生
                String[] aryDeviceNo = deviceInfo.getDeviceNoStr().split(",");
                String msgForList = "";
                List<Long> listForDeviceNo = new ArrayList<>();

                HashMap<String, Object> params = new HashMap<>();
                params.put("deviceIdList", aryDeviceNo);
                List<ShareDeviceInfoBySelfModel> allDevices = shareDeviceInfoModelBySelfMapper
                        .selectByConditionForPage(params);
                boolean isExists = Boolean.FALSE;
                for (int i = 0; i < aryDeviceNo.length; i++) {
                    isExists = Boolean.FALSE;
                    for (ShareDeviceInfoBySelfModel device : allDevices) {
                        if (aryDeviceNo[i].equals(String.valueOf(device.getId()))) {
                            // 设备存在
                            isExists = Boolean.TRUE;
                            listForDeviceNo.add(device.getId());
                            break;
                        }
                    }
                    // 设备不存在
                    if (!isExists) {
                        msgForList = (msgForList.isEmpty()) ? aryDeviceNo[i]
                                : String.format("%s,%s", msgForList, aryDeviceNo[i]);
                    }

                }
                if (!msgForList.isEmpty()) {
                    msg = String.format("下例设备编号在系统中不在存，请输入正确的设备编号!\n\r%s", msgForList);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                pageFilter.put("deviceIds", listForDeviceNo);
            } else if (deviceInfo.getSelectDeviceNoType() == 2) {
                // 根开始结束设备号批理绑定
                if (deviceInfo.getStartDeviceNo() == null || deviceInfo.getEndDeviceNo() == null) {
                    logger.info(String.format("`getDeviceActivationModeForBatch:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("请输入要绑定的开始和结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                if (deviceInfo.getStartDeviceNo() > deviceInfo.getEndDeviceNo()) {
                    logger.info(String.format("`getDeviceActivationModeForBatch:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("输入开始设备号小于结束设备号,请输入正确的开始结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 开始
                pageFilter.put("deviceStartId", deviceInfo.getStartDeviceNo());
                // 结束
                pageFilter.put("deviceEndId", deviceInfo.getEndDeviceNo());
            } else {
                // 根商户号绑定..
                if (deviceInfo.getOnlineMerchantId() == null || deviceInfo.getOnlineMerchantId() == 0) {

                    logger.info(String.format("`getDeviceActivationModeForBatch:%s", deviceInfo.getOnlineMerchantId()));
                    // TODO: handle exception
                    result.setMessage("请输入终端商户号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                pageFilter.put("onlineMerchantId", deviceInfo.getOnlineMerchantId());
            }
            pageFilter.put("limitSql", "LIMIT 10");
            pageFilter.put("filter", String.format(" and activation_mode=%d",
                    ShareDeviceActivationModeEnum.waitForConfirmationStatus.getCode()));
            List<ShareDeviceInfoModel> list = shareDeviceInfoModelBySelfMapper.getDevicesInfoByCondition(pageFilter);
            msg = "获取设备激活流程!！";
            logger.info(msg);
            if (list.size() > 0) {
                result.setResponseInfo(list.get(0));
            }
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("获取设备激活流程失败{}", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 非管理员批量绑定费用..
     */
    @RequestMapping(value = "/batchBindRatoUser")
    @ResponseBody
    public Object batchBindRatoUser(BatchBindRatoUserBO boParam) {
        logger.info("非管理员批量绑定费用.-BatchBindRatoUserBO{}", boParam);
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String msg = "";
        if (boParam == null) {
            result.setMessage("请先获取要批量设置分润的设备号！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
        // 1.接收参数
        String sJson = boParam.getDataList();
        if (sJson == null || sJson.isEmpty()) {
            result.setMessage("请先获取要批量设置分润的设备号！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
        List<PlatformRatioDeviceIdsBO> list = JSON.parseArray(sJson, PlatformRatioDeviceIdsBO.class);
        Integer userId = ShiroKit.getUser().getId();
        // 批量生成...
        try {
            // 1.1 分润设备号
            if (list == null) {
                result.setMessage("请先获取要批量设置分润的设备号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (list.size() <= 0) {
                result.setMessage("请先获取要批量设置分润的设备号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 1.2 更新..
            List<PlatformRatioDeviceIdsBO> listForUpdate = new ArrayList<>();
            PlatformRatioDeviceIdsBO bo = null;
            for (int i = 0; i < list.size(); i++) {
                bo = list.get(i);
                if (bo.getDeviceCount() > 0 && bo.getDeviceIds() != null && !bo.getDeviceIds().isEmpty()) {
                    listForUpdate.add(bo);
                }
            }
            //
            if (listForUpdate.size() <= 0) {
                result.setMessage("请先获取要批量设置分润的设备号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            BigDecimal sumBig = new BigDecimal("0");
            Double dbTmp = null;
            for (int i = 0; i < listForUpdate.size(); i++) {
                bo = listForUpdate.get(i);
                sumBig = new BigDecimal("0");
                if (bo.getPlatformRatio() != null) {
                    dbTmp = Math.abs(bo.getPlatformRatio().doubleValue());
                    sumBig = sumBig.add(new BigDecimal(dbTmp));
                }
                if (bo.getAgents1Rato() != null) {
                    dbTmp = Math.abs(bo.getAgents1Rato().doubleValue());
                    sumBig = sumBig.add(new BigDecimal(dbTmp));
                }
                if (bo.getAgents2Rato() != null) {
                    dbTmp = Math.abs(bo.getAgents2Rato().doubleValue());
                    sumBig = sumBig.add(new BigDecimal(dbTmp));
                }
                if (bo.getAgents3Rato() != null) {
                    dbTmp = Math.abs(bo.getAgents3Rato().doubleValue());
                    sumBig = sumBig.add(new BigDecimal(dbTmp));
                }
                if (bo.getShopkeeperRato() != null) {
                    dbTmp = Math.abs(bo.getShopkeeperRato().doubleValue());
                    sumBig = sumBig.add(new BigDecimal(dbTmp));
                }
                if (bo.getAllianceBusinessRate() != null) {
                    dbTmp = Math.abs(bo.getAllianceBusinessRate().doubleValue());
                    sumBig = sumBig.add(new BigDecimal(dbTmp));
                }
                if (sumBig.compareTo(new BigDecimal("100")) != 0) {
                    result.setMessage(String.format("所有商户分润绝对值之和必须等于100,对应平台分润为:%d", bo.getPlatformRatio()) + "%");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 如果有输入了分润，那就
                if (sumBig.compareTo(new BigDecimal("0")) > 0) {
                    if (bo.getPlatformRatio() == null) {
                        // 平台
                        bo.setPlatformRatio(new BigDecimal("0"));
                    }
                    if (bo.getAgents1Rato() == null) {
                        // 一级
                        bo.setAgents1Rato(new BigDecimal("0"));
                    }
                    if (bo.getAgents2Rato() == null) {
                        // 二级
                        bo.setAgents2Rato(new BigDecimal("0"));
                    }
                    if (bo.getAgents3Rato() == null) {
                        // 三级
                        bo.setAgents3Rato(new BigDecimal("0"));
                    }
                    if (bo.getShopkeeperRato() == null) {
                        // 铺货
                        bo.setShopkeeperRato(new BigDecimal("0"));
                    }
                    if (bo.getAllianceBusinessRate() == null) {
                        // 加盟
                        bo.setAllianceBusinessRate(new BigDecimal("0"));
                    }
                }
            }
            String filter = null;
            // 1.1增加权限过虑
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                HashMap<String, Object> pageFilter = new HashMap<>();
                initPageFilterForRight(pageFilter);
                if (pageFilter.containsKey("filter")) {
                    filter = (String) pageFilter.get("filter");
                }
            }

            ShareDeviceInfoModel tmpShareDeviceInfoModel = null;
            List<Long> listDeviceIds = null;
            String[] aryDevicesId = null;
            for (int i = 0; i < listForUpdate.size(); i++) {
                bo = listForUpdate.get(i);
                tmpShareDeviceInfoModel = new ShareDeviceInfoModel();
                tmpShareDeviceInfoModel.setPlatformRato(bo.getPlatformRatio());
                tmpShareDeviceInfoModel.setAgents1Rato(bo.getAgents1Rato());
                tmpShareDeviceInfoModel.setAgents2Rato(bo.getAgents2Rato());
                tmpShareDeviceInfoModel.setAgents3Rato(bo.getAgents3Rato());
                tmpShareDeviceInfoModel.setShopkeeperRato(bo.getShopkeeperRato());
                tmpShareDeviceInfoModel.setAllianceBusinessRate(bo.getAllianceBusinessRate());
                // bo.getDeviceIds()
                listDeviceIds = new ArrayList<>();
                aryDevicesId = bo.getDeviceIds().split(",");
                for (String tmp : aryDevicesId) {
                    if (tmp != null && !tmp.isEmpty()) {
                        listDeviceIds.add(Long.parseLong(tmp));
                    }
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("deviceIdList", listDeviceIds);
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                if (listDeviceIds.size() > 0) {
                    shareDeviceInfoModelBySelfMapper.batchRatioByDeviceNoListForUser(tmpShareDeviceInfoModel,
                            listDeviceIds, filter);
                }
            }
            msg = "批量设置分润成功！";
            logger.info(msg);
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
     * 批量绑定费用..
     */
    @RequestMapping(value = "/batchBindRato")
    @ResponseBody
    public Object batchBindRato(ShareDeviceInfoForBatchModel deviceInfo) {
        logger.info("批量绑定费用.-batchBindRato{}", deviceInfo);
        String msg = "";
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // 批量生成...
        try {
            if (deviceInfo == null) {
                result.setMessage("请输入要批量设置分润的设备号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (deviceInfo.getAgents1Rato() == null || deviceInfo.getAgents2Rato() == null
                    || deviceInfo.getAgents3Rato() == null || deviceInfo.getShopkeeperRato() == null
                    || deviceInfo.getAllianceBusinessRate() == null) {
                result.setMessage("请输入要批量设置分润的设备号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            BigDecimal platformRato = new BigDecimal("100");
            Double dbTmp = null;
            if (deviceInfo.getAgents1Rato() != null) {
                dbTmp = Math.abs(deviceInfo.getAgents1Rato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getAgents2Rato() != null) {
                dbTmp = Math.abs(deviceInfo.getAgents2Rato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getAgents3Rato() != null) {
                dbTmp = Math.abs(deviceInfo.getAgents3Rato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getShopkeeperRato() != null) {
                dbTmp = Math.abs(deviceInfo.getShopkeeperRato().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (deviceInfo.getAllianceBusinessRate() != null) {
                dbTmp = Math.abs(deviceInfo.getAllianceBusinessRate().doubleValue());
                platformRato = platformRato.subtract(new BigDecimal(dbTmp));
            }
            if (platformRato.compareTo(new BigDecimal("0")) == -1
                    || platformRato.compareTo(new BigDecimal("100")) == 1) {
                result.setMessage("输入的所有商户分润之和不等于100,请输入正确的分润比例！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 1.10如果为null表示没有输入，认为是0
            if (deviceInfo.getAgents1Rato() == null) {
                deviceInfo.setAgents1Rato(new BigDecimal("0"));
            }
            if (deviceInfo.getAgents2Rato() == null) {
                deviceInfo.setAgents2Rato(new BigDecimal("0"));
            }
            if (deviceInfo.getAgents3Rato() == null) {
                deviceInfo.setAgents3Rato(new BigDecimal("0"));
            }
            if (deviceInfo.getShopkeeperRato() == null) {
                deviceInfo.setShopkeeperRato(new BigDecimal("0"));
            }
            if (deviceInfo.getAllianceBusinessRate() == null) {
                deviceInfo.setAllianceBusinessRate(new BigDecimal("0"));
            }

            String filter = null;
            // 1.1增加权限过虑
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                HashMap<String, Object> pageFilter = new HashMap<>();
                initPageFilterForRight(pageFilter);
                if (pageFilter.containsKey("filter")) {
                    filter = (String) pageFilter.get("filter");
                }
            }

            ShareDeviceInfoModel tmpShareDeviceInfoModel = new ShareDeviceInfoModel();
            tmpShareDeviceInfoModel.setPlatformRato(platformRato);
            tmpShareDeviceInfoModel.setAgents1Rato(deviceInfo.getAgents1Rato());
            tmpShareDeviceInfoModel.setAgents2Rato(deviceInfo.getAgents2Rato());
            tmpShareDeviceInfoModel.setAgents3Rato(deviceInfo.getAgents3Rato());
            tmpShareDeviceInfoModel.setShopkeeperRato(deviceInfo.getShopkeeperRato());
            tmpShareDeviceInfoModel.setAllianceBusinessRate(deviceInfo.getAllianceBusinessRate());
            if (deviceInfo.getSelectDeviceNoType() == 1) {
                // 根多个乱的编辑。。。
                if (deviceInfo.getDeviceNoStr() == null || StringUtils.isEmpty(deviceInfo.getDeviceNoStr())) {
                    logger.info(String.format("请输入要批量设置分润的设备号-batchBindFee-deviceInfo.getDeviceNoStr:%s",
                            deviceInfo.getDeviceNoStr()));
                    // TODO: handle exception
                    result.setMessage("请输入要批量设置分润的设备号，多个用逗号分隔！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 2. 开始生
                String deviceNoStr = deviceInfo.getDeviceNoStr();
                deviceNoStr = StringUtil.replaceBlank(deviceNoStr);
                String[] aryDeviceNo = deviceNoStr.split(",");

                String msgForList = "";
                List<Long> listForDeviceNo = new ArrayList<>();

                HashMap<String, Object> params = new HashMap<>();
                params.put("deviceIdList", aryDeviceNo);
                List<ShareDeviceInfoBySelfModel> allDevices = shareDeviceInfoModelBySelfMapper
                        .selectByConditionForPage(params);
                boolean isExists = Boolean.FALSE;
                for (int i = 0; i < aryDeviceNo.length; i++) {
                    isExists = Boolean.FALSE;
                    for (ShareDeviceInfoBySelfModel device : allDevices) {
                        if (aryDeviceNo[i].equals(String.valueOf(device.getId()))) {
                            // 设备存在
                            isExists = Boolean.TRUE;

                            listForDeviceNo.add(device.getId());
                            break;
                        }
                    }
                    // 设备不存在
                    if (!isExists) {
                        msgForList = (msgForList.isEmpty()) ? aryDeviceNo[i]
                                : String.format("%s,%s", msgForList, aryDeviceNo[i]);
                    }

                }

                if (!msgForList.isEmpty()) {
                    msg = String.format("下例设备编号在系统中不在存，请输入正确的设备编号!\n\r%s", msgForList);
                    result.setMessage(msg);
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("deviceIdList", listForDeviceNo);
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchRatioByDeviceNoList(tmpShareDeviceInfoModel, listForDeviceNo,
                        filter);
            } else if (deviceInfo.getSelectDeviceNoType() == 2) {
                // 根开始结束设备号批理绑定
                if (deviceInfo.getStartDeviceNo() == null || deviceInfo.getEndDeviceNo() == null) {
                    logger.info(String.format("`批量设置分润-getStartDeviceNo:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("请输入要设置分润的开始和结束设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                if (deviceInfo.getStartDeviceNo() > deviceInfo.getEndDeviceNo()) {
                    logger.info(String.format("`批量设置分润-getStartDeviceNo:%d-%d", deviceInfo.getStartDeviceNo(),
                            deviceInfo.getEndDeviceNo()));
                    // TODO: handle exception
                    result.setMessage("输入开始设备号小于结束设备号,请输入正确的开始结否设备号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // 检查批量操作设备数量不能超过1000个
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("start", deviceInfo.getStartDeviceNo());
                paramMap.put("end", deviceInfo.getEndDeviceNo());
                paramMap.put("filter", filter);
                ResultCommandBaseObject<Object> validateResult = validateOppDeviceCount(paramMap);
                if (validateResult != null) {
                    return validateResult;
                }
                // 开始
                shareDeviceInfoModelBySelfMapper.batchRatioByStartEndDeviceNo(tmpShareDeviceInfoModel,
                        deviceInfo.getStartDeviceNo(), deviceInfo.getEndDeviceNo(), filter);
            } else {
                // 根商户号绑定..
                if (deviceInfo.getOnlineMerchantId() == null || deviceInfo.getOnlineMerchantId() == 0) {
                    logger.info(String.format("`批量终端商户号-getOnlineMerchantId:%s", deviceInfo.getOnlineMerchantId()));
                    // TODO: handle exception
                    result.setMessage("请输入终端商户号！");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                shareDeviceInfoModelBySelfMapper.batchRatioByOnlineMerchId(tmpShareDeviceInfoModel,
                        deviceInfo.getOnlineMerchantId(), filter);
            }
            msg = "批量设置分润成功！";
            logger.info(msg);
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
     * 得到省市区
     */
    @RequestMapping(value = "/getProvinceCityZondeOptions")
    @ResponseBody
    public Object getProvinceCityZondeOptions(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<ProvinceCityZoneInfoModel> result = new ResultCommandBaseObject<>();
        String msg = "";
        String parentId = request.getParameter("parentId");
        // 1.接收参数
        Integer userId = ShiroKit.getUser().getId();
        Long lngParentId = -1L;
        try {
            lngParentId = new Long(parentId);
        } catch (Exception e) {
            // TODO: handle exception
            lngParentId = -1L;
        }
        // 批量生成...
        try {
            ProvinceCityZoneInfoModelExample example = new ProvinceCityZoneInfoModelExample();
            com.stylefeng.guns.sharecore.modular.system.model.ProvinceCityZoneInfoModelExample.Criteria criteria = example
                    .createCriteria();
            criteria.andParentidEqualTo(lngParentId);
            criteria.andIdNotEqualTo(lngParentId);
            List<ProvinceCityZoneInfoModel> list = provinceCityZoneInfoModelMapper.selectByExample(example);
            msg = "获取省市区成功！";
            logger.info(msg);
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("获取省市区失败--getProvinceCityZondeOptions", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 根据商户电话，得到商户信息
     */
    @RequestMapping(value = "/getMerchantInfoByTelNo")
    @ResponseBody
    public Object getMerchantInfoByTelNo(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<MerchantInfoModel> result = new ResultCommandBaseObject<>();
        String msg = "";
        // 1.商户电话
        String merTelNo = request.getParameter("merTelNo");
        if (merTelNo == null || merTelNo.isEmpty()) {
            result.setMessage("请输入商户电话!");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
        //
        try {
            // 2.查询商户电话是否有对应的商户号
            MerchantInfoModelExample example = new MerchantInfoModelExample();
            com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModelExample.Criteria criteria = example
                    .createCriteria();
            criteria.andTelNoEqualTo(merTelNo);
            criteria.andMerchantTypeEqualTo(MerchantTypeEnum.TERMINAL.getCode().byteValue());
            List<MerchantInfoModel> list = merchantInfoModelMapper.selectByExample(example);
            msg = "";
            result.setMessage(msg);
            if (list.size() > 0) {
                result.setResponseInfo(list.get(0));
                // 3. 查询商户下是否绑定了设备..
                Long cnt = shareDeviceInfoModelBySelfMapper.getDevicesCountByMerchantId(list.get(0).getId());
                result.setMessage(cnt.toString());
            }
            result.setResponseInfos(list);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("根据商户电话，得到商户信息--getProvinceCityZondeOptions", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 根据商户id查询此商户下的所有设备所以分认比较..
     */
    @RequestMapping(value = "/searchDevicesByMerchantIdForRatio")
    @ResponseBody
    public Object searchDevicesByMerchantIdForRatio(HttpServletRequest request, HttpServletResponse response) {
        logger.info("根据商户id查询此商户下的所有设备所以分认比较...-searchDevicesByMerchantIdForRatio");
        String msg = "";
        // 1.接收参数
        // 商户号
        String onlineMerchantId = request.getParameter("onlineMerchantId");
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<PlatformRatioDeviceIdsBO> result = new ResultCommandBaseObject<>();
        try {
            if (onlineMerchantId == null || onlineMerchantId.isEmpty()) {
                result.setMessage("商户号不能空，请输入商户号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Long iOnlineMerchantId = 0L;
            try {
                iOnlineMerchantId = Long.parseLong(onlineMerchantId);
            } catch (Exception e) {
            }
            MerchantInfoModel merInfoModel = merchantInfoModelMapper.selectByPrimaryKey(iOnlineMerchantId);
            if (merInfoModel == null) {
                result.setMessage(String.format("输入的商户号(%d)在系统中不存在,请输入正确的商户号！", iOnlineMerchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merInfoModel.getMerchantType() == null
                    || !MerchantTypeEnum.TERMINAL.getCode().equals(merInfoModel.getMerchantType().intValue())) {
                result.setMessage(String.format("输入的商户号(%d)非终端商户,请输入正确的店铺商户号！", iOnlineMerchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 过虑条件...
            String filter = "";
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                initPageFilterForRight(pageFilter);
            }
            pageFilter.put("onlineMerchantId", iOnlineMerchantId);
            logger.info("根据商户id查询此商户下的所有设备所以分认比较--pageFilter-{}", pageFilter);
            // 得到所以的充电器...
            List<PlatformRatioDeviceIdsBO> list = shareDeviceInfoModelBySelfMapper
                    .selectDeviceIdsGroupbyPlatformRatio(pageFilter);
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("根据商户id查询此商户下的所有设备所以分认比较--searchDevicesByMerchantId", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 根据商户id查询此商户下的所有设备
     */
    @RequestMapping(value = "/searchDevicesByMerchantId")
    @ResponseBody
    public Object searchDevicesByMerchantId(HttpServletRequest request, HttpServletResponse response) {
        logger.info("根据商户id查询些商户下的所有设备.-searchDevicesByMerchantId");
        String msg = "";
        // 1.接收参数
        // 商户号
        String onlineMerchantId = request.getParameter("onlineMerchantId");
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<ShareDeviceInfoBySelfModel> result = new ResultCommandBaseObject<>();
        try {
            if (onlineMerchantId == null || onlineMerchantId.isEmpty()) {
                result.setMessage("商户号不能空，请输入商户号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Long iOnlineMerchantId = 0L;
            try {
                iOnlineMerchantId = Long.parseLong(onlineMerchantId);
            } catch (Exception e) {
            }
            MerchantInfoModel merInfoModel = merchantInfoModelMapper.selectByPrimaryKey(iOnlineMerchantId);
            if (merInfoModel == null) {
                result.setMessage(String.format("输入的商户号(%d)在系统中不存在,请输入正确的商户号！", iOnlineMerchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merInfoModel.getMerchantType() == null
                    || !MerchantTypeEnum.TERMINAL.getCode().equals(merInfoModel.getMerchantType().intValue())) {
                result.setMessage(String.format("输入的商户号(%d)非终端商户,请输入正确的店铺商户号！", iOnlineMerchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            // 2. 过虑条件...
            String filter = "";
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                initPageFilterForRight(pageFilter);
            }
            pageFilter.put("onlineMerchantId", iOnlineMerchantId);
            logger.info("根据商户id查询此商户下的所有设备--pageFilter-{}", pageFilter);
            // 得到所以的充电器...
            List<ShareDeviceInfoBySelfModel> list = shareDeviceInfoModelBySelfMapper
                    .selectByConditionForPage(pageFilter);
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("根据商户id查询此商户下的所有设备--searchDevicesByMerchantId", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 根据开始结束设置id查询的所有设备对应的分润比例
     */
    @RequestMapping(value = "/searchDevicesByStartAndEndRatio")
    @ResponseBody
    public Object searchDevicesByStartAndEndRatio(HttpServletRequest request, HttpServletResponse response) {
        logger.info("根据开始结束设置id查询的所有设备对应的分润比例.-searchDevicesByStartAndEndRatio");
        String msg = "";
        // 1.接收参数
        // 商户号
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<PlatformRatioDeviceIdsBO> result = new ResultCommandBaseObject<>();
        try {
            Long iStart = 0L, iEnd = 0L;
            try {
                iStart = Long.parseLong(start);
            } catch (Exception e) {
            }
            try {
                iEnd = Long.parseLong(end);
            } catch (Exception e) {
            }
            if (start == null || start.isEmpty() || iStart <= 0) {
                result.setMessage("开始的设备编号必须输入数值！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (end == null || end.isEmpty() || iEnd <= 0) {
                result.setMessage("开始的设备编号必须输入数值！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (iStart > iEnd) {
                result.setMessage("开始的设备编号必须小于结束的设备编号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 过虑条件...
            String filter = "";
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                initPageFilterForRight(pageFilter);
            }
            if (iStart > 0) {
                pageFilter.put("start", iStart);
            }
            if (iEnd > 0) {
                pageFilter.put("end", iEnd);
            }
            // 得到所以的充电器...
            Long icount = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
            if (icount > 300) {
                result.setMessage("一次最多同时只能批量设置300个设备,请分多次进行设置！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            logger.info("根据开始结束设置id查询的所有设备对应的分润比例--pageFilter-{}", pageFilter);
            List<PlatformRatioDeviceIdsBO> list = shareDeviceInfoModelBySelfMapper
                    .selectDeviceIdsGroupbyPlatformRatio(pageFilter);
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("根据开始结束设置id查询的所有设备对应的分润比例--searchDevicesByStartAndEndRatio", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 根据开始结束设置id查询的所有设备
     */
    @RequestMapping(value = "/searchDevicesByStartAndEnd")
    @ResponseBody
    public Object searchDevicesByStartAndEnd(HttpServletRequest request, HttpServletResponse response) {
        logger.info("根据商户id查询些商户下的所有设备.-searchDevicesByMerchantId");
        String msg = "";
        // 1.接收参数
        // 商户号
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<ShareDeviceInfoBySelfModel> result = new ResultCommandBaseObject<>();
        try {
            Long iStart = 0L, iEnd = 0L;
            try {
                iStart = Long.parseLong(start);
            } catch (Exception e) {
            }
            try {
                iEnd = Long.parseLong(end);
            } catch (Exception e) {
            }
            if (start == null || start.isEmpty() || iStart <= 0) {
                result.setMessage("开始的设备编号必须输入数值！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (end == null || end.isEmpty() || iEnd <= 0) {
                result.setMessage("开始的设备编号必须输入数值！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (iStart > iEnd) {
                result.setMessage("开始的设备编号必须小于结束的设备编号！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 过虑条件...
            String filter = "";
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                initPageFilterForRight(pageFilter);
            }
            if (iStart > 0) {
                pageFilter.put("start", iStart);
            }
            if (iEnd > 0) {
                pageFilter.put("end", iEnd);
            }
            logger.info("根据商户id查询此商户下的所有设备--pageFilter-{}", pageFilter);
            // 得到所以的充电器...
            Long icount = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
            if (icount > 300) {
                result.setMessage("一次最多同时只能批量设置300个设备,请分多次进行设置！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            List<ShareDeviceInfoBySelfModel> list = shareDeviceInfoModelBySelfMapper
                    .selectByConditionForPage(pageFilter);
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("根据开始结束设置id查询的所有设备--searchDevicesByStartAndEnd", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 根据设备列表进行设置
     */
    @RequestMapping(value = "/searchDevicesBydDeviceNoStr")
    @ResponseBody
    public Object searchDevicesBydDeviceNoStr(HttpServletRequest request, HttpServletResponse response) {
        logger.info("根据设备列表进行设置 .-searchDevicesBydDeviceNoStr");
        String msg = "";
        // 1.接收参数
        // 商户号
        String deviceNoStr = request.getParameter("deviceNoStr");
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<ShareDeviceInfoBySelfModel> result = new ResultCommandBaseObject<>();
        try {
            if (deviceNoStr == null || deviceNoStr.isEmpty()) {
                result.setMessage("请输入要批量设置的设备id,多个用逗号分隔！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            deviceNoStr = StringUtil.replaceBlank(deviceNoStr);
            List<Long> listFilter = new ArrayList<Long>();
            String[] aryDeviceNos = deviceNoStr.split(",");
            Long iDevice = 0L;
            for (String device : aryDeviceNos) {
                try {
                    iDevice = Long.parseLong(device);
                    listFilter.add(iDevice);
                } catch (Exception e) {
                }
            }
            if (listFilter.size() <= 0) {
                result.setMessage("请输入设备id列表不正确，请输入正确的设备id,多个用英文逗号分隔！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 过虑条件...
            String filter = "";
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                initPageFilterForRight(pageFilter);
            }
            pageFilter.put("deviceIdList", listFilter);
            logger.info("根据商户id查询此商户下的所有设备--pageFilter-{}", pageFilter);
            // 得到所以的充电器...
            Long icount = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
            if (icount > 300) {
                result.setMessage("一次最多同时只能批量设置300个设备,请分多次进行设置！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            List<ShareDeviceInfoBySelfModel> list = shareDeviceInfoModelBySelfMapper
                    .selectByConditionForPage(pageFilter);
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("根据开始结束设置id查询的所有设备--searchDevicesByStartAndEnd", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 根据设备列表进行设置 得到对应比例的分润
     */
    @RequestMapping(value = "/searchDevicesBydDeviceNoStrRatio")
    @ResponseBody
    public Object searchDevicesBydDeviceNoStrRatio(HttpServletRequest request, HttpServletResponse response) {
        logger.info("根据设备列表进行设置 得到对应比例的分润.-searchDevicesBydDeviceNoStrRatio");
        String msg = "";
        // 1.接收参数
        // 商户号
        String deviceNoStr = request.getParameter("deviceNoStr");
        Integer userId = ShiroKit.getUser().getId();
        ResultCommandBaseObject<PlatformRatioDeviceIdsBO> result = new ResultCommandBaseObject<>();
        try {
            if (deviceNoStr == null || deviceNoStr.isEmpty()) {
                result.setMessage("请输入要批量设置的设备id,多个用逗号分隔！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            deviceNoStr = StringUtil.replaceBlank(deviceNoStr);
            List<Long> listFilter = new ArrayList<Long>();
            String[] aryDeviceNos = deviceNoStr.split(",");
            Long iDevice = 0L;
            for (String device : aryDeviceNos) {
                try {
                    iDevice = Long.parseLong(device);
                    listFilter.add(iDevice);
                } catch (Exception e) {
                }
            }
            if (listFilter.size() <= 0) {
                result.setMessage("请输入设备id列表不正确，请输入正确的设备id,多个用英文逗号分隔！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 过虑条件...
            String filter = "";
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 权限 管理员有全部权限admin...
            if (userId != null && userId.intValue() != 1) {
                initPageFilterForRight(pageFilter);
            }
            pageFilter.put("deviceIdList", listFilter);
            logger.info("根据设备列表进行设置 得到对应比例的分润--pageFilter-{}", pageFilter);
            // 得到所以的充电器...
            Long icount = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
            if (icount > 300) {
                result.setMessage("一次最多同时只能批量设置300个设备,请分多次进行设置！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            shareDeviceInfoModelBySelfMapper.setGroupConcatMaxLen();
            List<PlatformRatioDeviceIdsBO> list = shareDeviceInfoModelBySelfMapper
                    .selectDeviceIdsGroupbyPlatformRatio(pageFilter);
            result.setResponseInfos(list);
            result.setMessage(msg);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("根据设备列表进行设置 得到对应比例的分润--searchDevicesBydDeviceNoStrRatio", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }
}
