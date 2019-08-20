package com.stylefeng.guns.rest.modular.system.controller;

import com.stylefeng.guns.rest.modular.system.service.ScanDeviceService;
import com.stylefeng.guns.rest.modular.system.service.TradeInfoService;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustAccountBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.*;
import com.stylefeng.guns.sharecore.common.service.RegisterService;
import com.stylefeng.guns.sharecore.common.service.ZfbAuthHelper;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.FinishTradeOrderBO;
import com.stylefeng.guns.sharecore.modular.system.model.MyOrderPagesQueryBO;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeAndWithdrawBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.ShareTradeService;
import com.stylefeng.guns.sharecore.modular.system.service.UserCenterService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝小程序相关接口
 */
@Controller
@RequestMapping("/zfb")
public class ZfbAppController {

    /**
     * 日志对象...
     */
    private static final Logger logger = LoggerFactory.getLogger(ZfbAppController.class);

    /**
     * 用户消息处理..
     */
    @Autowired
    private UserCenterService userCenterService;
    /**
     * 客户注册服务。。
     */
    @Autowired
    private RegisterService registService;

    /**
     * 客户余额账户...
     */
    @Autowired
    private CustAccountBySelfMapper custAccountBySelfMapper;

    /**
     * 得到客户信息.
     */
    @Autowired
    private CustInfoBySelfMapper custInfoBySelfMapper;
    /**
     * 结束订单
     */
    @Autowired
    private ShareTradeService shareTradeService;

    /**
     * 设备服务类..
     */
    @Autowired
    private ScanDeviceService scanDeviceService;

    /**
     * 交易信息..
     */
    @Autowired
    private ShareTradeInfoModelMapper shareTradeInfoModelMapper;

    /**
     * 支付
     */
    @Autowired
    private TradeInfoService tradeInfoService;

    @Autowired
    private ZfbAuthHelper zfbAuthHelper;


    /**
     * 接收支付宝通知---支付宝充值成功回调
     *
     * @param request
     * @param response
     */
    @RequestMapping("/receiveNotify")
    @ResponseBody
    public String receiveNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.接收支付宝通知参数
//      String tradeNo = request.getParameter("trade_no");// 支付宝交易凭证号
        String signType = request.getParameter("sign_type");// 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
        String appId = request.getParameter("app_id");// 支付宝分配给开发者的应用Id
        String outTradeNo = request.getParameter("out_trade_no");// 我们平台商户  ChannelCapitalRecord id
        String tradeStatus = request.getParameter("trade_status");// 交易目前所处的状态（TRADE_SUCCESS，交易支付成功）
        String totalAmount = request.getParameter("total_amount");// 本次交易支付的订单金额，单位为人民币（元）

        String gmtClose = request.getParameter("gmt_close");// 交易结束时间
        String selleEmail = request.getParameter("seller_email");// 卖家支付宝账号
        String outBizNo = request.getParameter("out_biz_no");// 商户业务ID，主要是退款通知中返回退款申请的流水号
        //验签支付宝返回数据，不合法直接返回
        boolean isPass = zfbAuthHelper.validateParam(request, signType);
        if (!isPass) {
            return "error";
        }
        //更改订单状态,业务校验
        boolean isSuccess = tradeInfoService.handleChannelCapitailResultForZfbApp(appId, outTradeNo, tradeStatus, totalAmount, gmtClose, selleEmail, outBizNo);
        return "success";
        /**
         * 程序执行完后必须打印输出“success”（不包含引号）。如果商户反馈给支付宝的字符不是success这7个字符，
         * 支付宝服务器会不断重发通知，直到超过24小时22分钟。
         * 一般情况下，25小时以内完成8次通知（通知的间隔频率一般是：4m,10m,10m,1h,2h,6h,15h）；
         */

    }

    /**
     * 支付宝充值-生成预支付订单trade_no.
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getPrepayForCust")
    @ResponseBody
    public Object getPrepayForCust(HttpServletRequest request, HttpServletResponse response) {
        String needFee = request.getParameter("needFee");// 差多少钱..
        String feeTypeId = request.getParameter("feeTypeId");// 费用类型 id
        String custNo = request.getParameter("custNo");// 费用对应的客户id
        logger.info(String.format("生成预支付订单,needFee:%s,feeTypeId:%s,custNo:%s", needFee,
                feeTypeId, custNo));
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();

        if (StringUtils.isEmpty(custNo)) {
            result.setResult(ResultCommand.FAILED);
            result.setMessage("获取不到客户号，无法支付, 请重新登录!");
            return result;
        }
        try {
            // 1. 查询客户信息..
            CustInfoModel custInfoModel = custInfoBySelfMapper
                    .selectCustInfoAndCustAccountInfoByPrimaryKey(Long.valueOf(custNo));
            if (custInfoModel == null) {
                result.setResult(ResultCommand.FAILED);
                result.setMessage("客户信息不存在, 请重新授权注册登录!");
                return result;
            }
            // 2、判断客户信息
            Integer iFeeTypeId = new Integer("0");
            if (feeTypeId != null) {
                try {
                    iFeeTypeId = Integer.parseInt(feeTypeId);
                } catch (Exception e) {
                    logger.warn("生成预支付订单,费用类型转换失败!", e);
                    result.setResult(ResultCommand.FAILED);
                    result.setMessage("生成预支付订单,费用类型转换失败!");
                    return result;
                }
            }
            String feeTypeDesc = ShareFeeTypeEnum.getDesc(iFeeTypeId);

            Map<String, Object> resultMap = tradeInfoService.getPrepayIdForZfb(custInfoModel, new BigDecimal(needFee),
                    feeTypeDesc, iFeeTypeId);
            if (!CollectionUtils.isEmpty(resultMap) && resultMap.get("zfbTradeNo") != null) {
                result.setResult(ResultCommand.SUCCESS);
            } else {
                result.setResult(ResultCommand.FAILED);
            }
            logger.info("支付宝充值预支付信息resultMap:{}", resultMap);
            result.setResponseInfo(resultMap);
            return result;

        } catch (ExgrainBizUncheckedException e) {
            logger.error("充值系统异常!", e.getMessage());
            result.setMessage(e.getMessage());
            result.setResult(ResultCommand.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("充值系统异常!", e);
            result.setErrorCode(ResultCommand.FAILED);
            result.setMessage("充值异常！");
            return result;
        }
    }


    /**
     * 结束充电...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/finishRecharge")
    @ResponseBody
    public Object finishRecharge(HttpServletRequest request, HttpServletResponse response) {
        // 1. 得到参数..
        String custNo = request.getParameter("custNo");// 客户id
        String chargerId = request.getParameter("chargerId");// 用户扫的充电器id
        String tradeId = request.getParameter("tradeId");// 用户扫的充电器id
        logger.info(String.format("再次扫二维码结束充电...,custNo:%s,chargerId:%s.tradeId:%s", custNo, chargerId, tradeId));
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();

        // 2. 检验数据合法性
        if (StringUtils.isEmpty(custNo)) {
            result.setResult(ResultCommand.FAILED);
            result.setMessage("获取不到客户号，无法结束订单, 请重新登录!");
            return result;
        }
        // 3. 查询订单信息..
        Long lngTradeId = 0L;
        try {
            lngTradeId = Long.parseLong(tradeId);
        } catch (Exception e) {
            result.setResult(ResultCommand.FAILED);
            result.setMessage("交易订单信息和系统中不一至，请重新登录!");
            return result;
        }
        ShareTradeInfoModel tradeInfoModel = shareTradeInfoModelMapper.selectByPrimaryKey(lngTradeId);
        if (tradeInfoModel == null) {
            result.setResult(ResultCommand.FAILED);
            result.setMessage("订单系统中不存，无法结束订单!");
            logger.info(
                    String.format("订单系统中不存，无法结束订单.,custNo:%s,chargerId:%s.tradeId:%s", custNo, chargerId, lngTradeId));
            return result;
        }
        if (tradeInfoModel.getTradeStatus() != null
                && tradeInfoModel.getTradeStatus().intValue() == ShareTradeStatusEnum.Finish.getCode()) {
            result.setResult(ResultCommand.FAILED);
            result.setMessage("交易订单已结束，无需再次扫码结束订单!");
            logger.info(String.format("交易订单已结束，无需再次扫码结束订单.,custNo:%s,chargerId:%s.tradeId:%s,tradeStatus:%d", custNo,
                    chargerId, tradeId, tradeInfoModel.getTradeStatus()));
            return result;
        }
        if (tradeInfoModel.getCustId() != null && !tradeInfoModel.getCustId().equals(Long.valueOf(custNo))) {
            result.setResult(ResultCommand.FAILED);
            result.setMessage("交易订单用户信息和当前登录账号不一至,无法结束订单!");
            return result;
        }
        // 4. 结束订单。。
        // 根据交易id结束订单...
        try {
            FinishTradeOrderBO finishTradeOrderBO = shareTradeService.toBackByTradeId(lngTradeId);
            result.setResponseInfo(finishTradeOrderBO);
            result.setResult(ResultCommand.SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("再次扫二维码结束充电...,custNo:%s,chargerId:%s.tradeId:%s", custNo, chargerId, tradeId),
                    e);
            result.setResult(ResultCommand.FAILED);
            result.setMessage("结束订单失败!");
        }
        return result;
    }

    /**
     * 得到充电器密码...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getPasswordForRecharge")
    @ResponseBody
    public Object getPasswordForRecharge(HttpServletRequest request, HttpServletResponse response) {
        String custNo = request.getParameter("custNo");// 客户id
        String feeTypeId = request.getParameter("feeTypeId");// 费用类型 id
        String chargerId = request.getParameter("chargerId");// 用户扫的充电器id
        String outTradeNo = request.getParameter("outTradeNo");// 支付的交易订单号
        String needFee = request.getParameter("needFee");// 预付金
        String chargeRslt = request.getParameter("chargeRslt");// 充值 返回
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String zjAddr = request.getParameter("zjAddr");

        logger.info(String.format(
                "得到充电器密码,custNo:%s,feeTypeId:%s,chargerId:%s.outTradeNo:%s,needFee:%s,chargeRslt:%s, latitude:%s,longitude:%s, zjAddr:%s",
                custNo, feeTypeId, chargerId, outTradeNo, needFee, chargeRslt, latitude, longitude, zjAddr));
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();
        if (StringUtils.isEmpty(custNo)) {
            result.setResult(ResultCommand.FAILED);
            result.setMessage("获取不到客户号，无法获取密码, 请重新登录!");
            return result;
        }
        try {
            // 1. 查询客户信息..
            CustInfoModel custInfoModel = custInfoBySelfMapper
                    .selectCustInfoAndCustAccountInfoByPrimaryKey(Long.valueOf(custNo));
            if (custInfoModel == null) {
                result.setResult(ResultCommand.FAILED);
                result.setMessage("客户信息不存在, 请重新授权注册登录!");
                return result;
            }
            // 2. 扣费、生成交易订单、获取密码、结订单
            result = scanDeviceService.getPasswordForRechargeByZfb(custInfoModel.getCustNo(), chargerId, feeTypeId,
                    outTradeNo, needFee, chargeRslt, latitude, longitude, zjAddr);// getPwdWithYFJ
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("方法getPasswordForRecharge，获取密码自定义异常", e.getMessage());
            result.setMessage(e.getMessage());
            result.setResult(ResultCommand.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("方法getPasswordForRecharge，获取密码系统异常", e);
            result.setMessage("系统出错，请稍后重试！");
            result.setResult(ResultCommand.FAILED);
            return result;
        }
    }


    /**
     * 扫码充电
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/scanDevice")
    @ResponseBody
    public Object scanDevice(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();
        Map<String, Object> map = new HashMap<>();
        String scanResult = request.getParameter("scanResult");// 充电器Id
        String custNoStr = request.getParameter("custNo");//客户号
        String latitude = com.alibaba.druid.util.StringUtils.isEmpty(request.getParameter("currLatitude")) ? ""
                : request.getParameter("currLatitude"); // 当前扫码纬度
        String longitude = com.alibaba.druid.util.StringUtils.isEmpty(request.getParameter("currLongitude")) ? ""
                : request.getParameter("currLongitude"); // 当前反码经度

        logger.info(" 方法：scanDevice 当前纬度{},当前经度{}", latitude, longitude);

        logger.info("方法：scanDevice 入参    custNo:{},scanResult:{}", custNoStr, scanResult);
        try {
            // 检验数据合法性
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("获取不到客户编号，扫码出错！");
                result.setMessage("获取不到客户编号，扫码出错！");
                result.setResult(ResultCommand.FAILED);
                return result;
            }
            map = scanDeviceService.scanDeviceByZfb(scanResult, Long.valueOf(custNoStr));
            result.setResponseInfo(map);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
            result.setMessage(e.getMessage());
            result.setResult(ResultCommand.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("扫码系统出错：", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommand.FAILED);
            return result;
        }

    }

    /**
     * 支付宝提现处理。。
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doZfbAppWithraw")
    @ResponseBody
    public Object doZfbAppWithraw(HttpServletRequest request, HttpServletResponse response) {
        String withdrawAmount = request.getParameter("withdrawAmount");
        String custNoStr = request.getParameter("custNo");
        ResultCommandBaseObject<Object> resultCommand = new ResultCommandBaseObject<Object>();
        try {
            logger.info(String.format("支付宝提现处理方法doZfbAppWithraw入参：withdrawAmount:{},custNoStr:{}", withdrawAmount,
                    custNoStr));
            // 1. 检验数据合法性
            if (StringUtils.isEmpty(withdrawAmount) || "0".equals(withdrawAmount)) {
                resultCommand.setResult(ResultCommandBaseObject.FAILED);
                resultCommand.setMessage("提现金额不合法！");
                return resultCommand;
            }
            if (StringUtils.isEmpty(custNoStr)) {
                resultCommand.setResult(ResultCommandBaseObject.FAILED);
                resultCommand.setMessage("获取不到客户编号，提现失败！");
                return resultCommand;
            }
            // 2. 查询客户信息..
            CustInfoModel infoModel = custInfoBySelfMapper
                    .selectCustInfoAndCustAccountInfoByPrimaryKey(Long.valueOf(custNoStr));
            // 3. 查询反回订单
            return shareTradeService.doZfbAppWithraw(withdrawAmount, infoModel);
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
            resultCommand.setResult(ResultCommandBaseObject.FAILED);
            resultCommand.setMessage(e.getMessage());
            return resultCommand;
        } catch (Exception e) {
            logger.error("支付宝小程序提现异常！", e);
        }
        return null;
    }


    /**
     * 支付宝小程序我的订单列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getMyOrderWithPages")
    @ResponseBody
    public Object getMyOrderWithPages(HttpServletRequest request, HttpServletResponse response) {
        String custNoStr = request.getParameter("custNo");// 客户号
        String sStart = request.getParameter("start"); // 开始
        String sRows = request.getParameter("rows");
        String sStatus = request.getParameter("status");

        try {
            logger.info(String.format(
                    "支付宝小程序我的订单列表getMyOrderWithPages-custNoStr:%s, sStart:%s,sRows:%s,sStatus:%s",
                    custNoStr, sStart, sRows, sStatus));
            ResultCommandBaseObject<Object> resultCommand = new ResultCommandBaseObject<>();
            // 1.检验数据合法性
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("支付宝小程序我的订单列表g---- custNoStr is null");
                return null;
            }

            // 2. 构造查询条件..
            Integer status = (sStatus != null && !com.alibaba.druid.util.StringUtils.isEmpty(sStatus)) ? new Integer(sStatus) : null;
            Integer rows = (sRows != null && !com.alibaba.druid.util.StringUtils.isEmpty(sRows)) ? new Integer(sRows) : new Integer("0");
            Integer start = (sStart != null && !com.alibaba.druid.util.StringUtils.isEmpty(sStart)) ? new Integer(sStart) : new Integer("0");
            MyOrderPagesQueryBO myOrderPagesQueryBO = new MyOrderPagesQueryBO();
            myOrderPagesQueryBO.setCustNo(Long.valueOf(custNoStr));
            myOrderPagesQueryBO.setRows(rows);
            myOrderPagesQueryBO.setStart(start);
            myOrderPagesQueryBO.setStatus(status);
            // 3. 查询反回订单
            return userCenterService.getMyOrdersWithPages(myOrderPagesQueryBO);
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.error("支付宝小程序我的订单列表异常！", e);
        }
        return null;
    }

    /**
     * 得到提现记录处理相关信息。。。
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getWithdrawalProcessInfo")
    @ResponseBody
    public Object getWithdrawalProcessInfo(HttpServletRequest request, HttpServletResponse response) {
        String recordId = request.getParameter("recordId");
        try {
            logger.info(String.format("得到提现记录处理相关信息。。。....getWithdrawalProcessInfo-recordId:%s,",
                    recordId));
            // 1. 查询提现状 态..
            return userCenterService.getWithdrawalProcessInfoByZfb(recordId);
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.error("得到提现记录处理相关信息。。！", e);
        }
        return null;
    }

    /**
     * 得到提现记录..
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getWithdrawRecordsByPage")
    @ResponseBody
    public Object getWithdrawRecordsByPage(HttpServletRequest request, HttpServletResponse response) {
        String custNoStr = request.getParameter("custNo");// 客户号
        String sStart = request.getParameter("start"); // 开始
        String sRows = request.getParameter("rows"); // 一页行数
        try {
            logger.info(String.format("得到提现记录....getWithdrawRecordsByPage-custNo:%s, sStart:%s,sRows:%s",
                    custNoStr, sStart, sRows));
            ResultCommandBaseObject<Object> resultCommand = new ResultCommandBaseObject<>();
            // 1. 检验数据合法性
            // 检验数据合法性
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("得到提现记录....getWithdrawRecordsByPage-- custNoStr is null");
                return null;
            }
            // 账号信息
            CustInfoModel custInfoModel = registService.getCustInfoByCustNo(Long.valueOf(custNoStr));

            if (custInfoModel == null) {
                resultCommand.setResult(ResultCommand.FAILED);
                resultCommand.setMessage(String.format("账户不存在!请重新登陆。"));
                return resultCommand;
            }
            // 2. 构造查询条件..
            Integer rows = (sRows != null && !com.alibaba.druid.util.StringUtils.isEmpty(sRows)) ? new Integer(sRows) : new Integer("0");
            Integer start = (sStart != null && !com.alibaba.druid.util.StringUtils.isEmpty(sStart)) ? new Integer(sStart) : new Integer("0");
            RechargeAndWithdrawBO rechargeAndWithdrawBO = new RechargeAndWithdrawBO();
            rechargeAndWithdrawBO.setCustNo(Long.valueOf(custNoStr));
            rechargeAndWithdrawBO.setRows(rows);
            rechargeAndWithdrawBO.setStart(start);
            // 3. 查询反回订单
            return userCenterService.getWithdrawRecordsByPage(rechargeAndWithdrawBO);
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.error("得到提现记录异常！", e);
        }
        return null;
    }

    /**
     * <p>
     * 用户读消息
     * </p>
     *
     * @param request
     * @param response
     */
    @RequestMapping("/doReadMessage")
    @ResponseBody
    public Object doReadMessage(HttpServletRequest request, HttpServletResponse response) {
        String custNoStr = request.getParameter("custNo");
        String id = request.getParameter("id");
        try {
            logger.info(String.format("用户读消息doReadMessage入参:custNoStr:{},id:{}", custNoStr, id));
            // 检验数据合法性
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("用户读消息doReadMessage---- custNoStr is null");
                return null;
            }
            Map<String, Long> map = userCenterService.doReadMessage(Long.valueOf(custNoStr), id);
            return map;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.error("用户读消息doReadMessage！", e);
        }
        return null;
    }

    /**
     * 分页得到信息列表。。。
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getMyMessages")
    @ResponseBody
    public Object getMyMessages(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();

        String custNoStr = request.getParameter("custNo");
        String startStr = request.getParameter("start");
        String countStr = request.getParameter("count");
        String codeStr = request.getParameter("code");
        logger.info("方法：myMessages 入参    custNoStr:{},startStr:{},countStr:{}", custNoStr, startStr, countStr);
        try {
            // 检验数据合法性
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("获取我的信息数量方法getMyMessageCountInfo---- custNoStr is null");
                return null;
            }
            // 账号信息
            CustInfoModel custInfoModel = registService.getCustInfoByCustNo(Long.valueOf(custNoStr));
            result = userCenterService.getMyMessage(custInfoModel, Integer.parseInt(codeStr),
                    Integer.parseInt(startStr), Integer.parseInt(countStr));
            result.setResult(ResultCommand.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
            result.setMessage(e.getMessage());
            result.setResult(ResultCommand.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("获取我的消息出错，请稍后重试！", e);
            result.setMessage("获取我的消息出错，请稍后重试！");
            result.setResult(ResultCommand.FAILED);
            return result;
        }
    }

    /**
     * 获取我的信息数量信息..
     *
     * @return
     */
    @RequestMapping("getMyMessageCountInfo")
    @ResponseBody
    public Map<String, Long> getMyMessageCountInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String custNoStr = request.getParameter("custNo");
            String id = request.getParameter("id");
            logger.info("获取我的信息数量方法getMyMessageCountInfo入参：custNoStr:{},id:{}", custNoStr, id);
            // 检验数据合法性
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("获取我的信息数量方法getMyMessageCountInfo---- custNoStr is null");
                return null;
            }
            /**
             * 返回所有的用户未读信息
             */
            Map<String, Long> map = userCenterService.getMyMessageCoutInfo(Long.valueOf(custNoStr), id);
            logger.info("获取我的信息数量方法getMyMessageCountInfo出参：map{}", map);
            return map;
        } catch (Exception e) {
            logger.error("获取我的信息数量方法getMyMessageCountInfo异常！", e);
            return null;
        }
    }

    /**
     * <p>
     * 得到我的钱包信息
     * </p>
     *
     * @param request
     * @param response
     */
    @RequestMapping("/geMyWallet")
    @ResponseBody
    public Object geMyWallet(HttpServletRequest request, HttpServletResponse response) {
        String custNoStr = request.getParameter("custNo");
        ResultCommandBaseObject<Object> resultCommand = new ResultCommandBaseObject<Object>();
        try {
            logger.info(String.format("得到我的钱包信息方法geMyWallet入参custNoStr:{}", custNoStr));
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("custNoStr is null");
                resultCommand.setResult(ResultCommandBaseObject.FAILED);
                resultCommand.setMessage("获取不到客户编号！");
                return resultCommand;
            }
            // 账号信息
            CustAccountModel accountModel = custAccountBySelfMapper.selectByCustNo(Long.valueOf(custNoStr),
                    CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
            // 账户余额..
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("custNo", custNoStr);
            map.put("availableBalance", accountModel.getAvailableBalance());
            map.put("frozenBalance", accountModel.getFrozenBalance());
            resultCommand.setResult(ResultCommandBaseObject.SUCCESS);
            resultCommand.setResponseInfo(map);
            logger.info(String.format("得到我的钱包信息方法geMyWallet出参accountModel:{}", accountModel));
            return resultCommand;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
            resultCommand.setResult(ResultCommandBaseObject.FAILED);
            resultCommand.setMessage(e.getMessage());
            return resultCommand;
        } catch (Exception e) {
            logger.error("得到我的钱包信息异常！", e);
            resultCommand.setResult(ResultCommandBaseObject.FAILED);
            resultCommand.setMessage("钱包服务器异常！");
            return resultCommand;
        }
    }

    /**
     * 得到用户中心需要的相关信息..
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getUserCenterInfo")
    @ResponseBody
    public Object getUserCenterInfo(HttpServletRequest request, HttpServletResponse response) {
        // 1. 得到参数..
        String custNoStr = request.getParameter("custNo");// custNo...
        logger.info(String.format(" 得到用户中心需要的相关信息入参：.....custNoStr:{}", custNoStr));
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();
        // 2. 检验数据合法性
        if (StringUtils.isEmpty(custNoStr)) {
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("获取客户编号失败！");
            return result;
        }
        Long custNo = Long.valueOf(custNoStr);
        Map<String, Object> map = userCenterService.getUserCenterInfo(custNo);
        result.setResponseInfo(map);
        result.setResult(ResultCommandBaseObject.SUCCESS);
        return result;
    }

    /**
     * 获取我的信息数量信息..
     *
     * @return
     */
    @RequestMapping("getMyMessageCoutInfo")
    @ResponseBody
    public Map<String, Long> getMyMessageCoutInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String custNoStr = request.getParameter("custNo");
            String id = request.getParameter("id");
            logger.info("获取我的信息数量信息方法getMyMessageCoutInfo入参custNo:{},id:{}", custNoStr, id);
            // 检验数据合法性
            if (StringUtils.isEmpty(custNoStr)) {
                logger.warn("custNoStr is null!");
                return null;
            }
            Long custNo = Long.valueOf(custNoStr);
            CustInfoModel custInfoModel = registService.getCustInfoByCustNo(custNo);
            if (custInfoModel == null) {
                logger.warn("custInfoModel is null!");
                return null;
            }
            /**
             * 返回所有的用户未读信息
             */
            Map<String, Long> map = userCenterService.getMyMessageCoutInfo(custNo, id);
            logger.info("获取我的信息数量信息方法getMyMessageCoutInfo入参map:{}", map);
            return map;
        } catch (Exception e) {
            logger.error("获取我的信息数量信息异常", e);
        }
        return null;
    }


}
