package com.stylefeng.guns.rest.modular.system.service;

import com.stylefeng.guns.core.model.MapCommand;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.core.util.DecimalUtil;
import com.stylefeng.guns.core.util.MapUtil;
import com.stylefeng.guns.sharecore.common.persistence.model.*;
import com.stylefeng.guns.sharecore.common.service.ZfbAuthHelper;
import com.stylefeng.guns.sharecore.modular.system.constants.*;
import com.stylefeng.guns.sharecore.modular.system.dao.*;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.*;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;
import com.stylefeng.guns.sharecore.modular.system.service.CustAccountService;
import com.stylefeng.guns.sharecore.modular.system.service.RechargeTradeInfoService;
import com.stylefeng.guns.sharecore.modular.system.service.WxAppApiService;
import com.stylefeng.guns.sharecore.modular.system.utils.ChargerPwdDecoder;
import com.stylefeng.guns.sharecore.modular.system.utils.GeneratePwdCalacutorWithFactors;
import com.stylefeng.guns.sharecore.modular.system.utils.MyThreadFactory;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpOrderQueryResult;
import me.chanjar.weixin.mp.bean.WxMpRefundQueryResult;
import me.chanjar.weixin.mp.bean.result.WxMpPayCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title TradeInfoService
 * @描述 [交易中心服务类]
 */
@Service
public class TradeInfoService extends BaseService {

    /**
     * 日志处理类..
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    //小程序appId
    @Value("${zfb.appId}")
    private String zfbAppId;
    /**
     *
     */
    private static ThreadPoolExecutor chartInfoServiceExec = new ThreadPoolExecutor(5, 200, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(50), new MyThreadFactory("chartInfoService"),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    /**
     * 客户编号
     */
    @Value("${sharehelper.custNo}")
    private Long ptCustNo;

    @Autowired
    private ZfbAuthHelper zfbAuthHelper;
    /**
     * 商户id
     */
    @Value("${wechat.mchId}")
    private String mchId;
    /**
     * 微信小程序mchId
     */
    @Value("${wechat.appMchId}")
    private String wxMchId;
    /**
     * 小程序appid
     */
    @Value("${wechat.appId}")
    private String appId;
    /**
     * 小程序 secret
     */
    @Value("${wechat.secret}")
    private String key;

    /**
     * 小程序创建者ip
     */
    @Value("${sharehelper.created.ip}")
    private String billCreatedIp = "103.44.145.245";
    /**
     * 小程序回调url
     */
    @Value("${sharehelper.url}/wxapp/callback.htm")
    private String callbackUrl = "";
    /**
     * 小程序url
     */
    @Value("${sharehelper.url}")
    private String wexinwebUrl = "";

    @Value("${sharehelper.isDebug}")
    private String isDebug;

    @Autowired
    private RechargeTradeInfoModelMapper rechargeTradeInfoModelMapper;

    /**
     * 支付商户号
     */
    @Value("${sharehelper.merchantId}")
    private Long shareMerchantId;

    @Autowired
    private CustCanwithdrawChargeInfoModelMapper custCanwithdrawChargeInfoModelMapper;

    @Autowired
    private NotifyMessageMapper notifyMessageMapper;

    @Autowired
    private WithdrawOrderInfoModelMapper tradeInfoMapper;

    @Autowired
    private CapitalChangeRecordModelMapper capitalChangeRecordModelMapper;

    @Autowired
    private ChannelCapitalRecordMapper channelCapitalRecordMapper;

    @Autowired
    protected WxMpService wxMpService;

    @Autowired
    private CustAccountService custAccountService;
    /**
     * 实现微信小程序的所有相关api接口服务类....
     */
    @Autowired
    private WxAppApiService wxAppApiService;

    @Autowired
    private RechargeTradeInfoService rechargeTradeInfoService;

    /**
     * 处理共享充电器数据库操作表...
     */
    @Autowired
    private ShareChargerModelMapper shareChargerModelMapper;
    /**
     * 交易表
     */
    @Autowired
    private ShareTradeInfoModelMapper shareTradeInfoModelMapper;
    /**
     * 交易时的设备表
     */
    @Autowired
    private ShareTradeDeviceInfoModelMapper shareTradeDeviceInfoModelMapper;

    public String getWexinwebUrl() {
        return wexinwebUrl;
    }


    /**
     * 处理微信回调，修改订单...
     *
     * @param callbackResult
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public boolean handleChannelCapitailResultForWexinApp(WxMpPayCallback callbackResult) throws Exception {
        logger.info("callbackResult:" + callbackResult);
        if (StringUtils.isEmpty(callbackResult.getOut_trade_no())) {
            logger.error("Out_trade_no is null:" + callbackResult.getOut_trade_no());
            return false;
        }
        logger.info("callbackResult.getOut_trade_no()--{}", callbackResult);
        // 1. 获起订单
        String outTradeOut = callbackResult.getOut_trade_no();
        if (callbackResult.getOut_trade_no() != null && callbackResult.getOut_trade_no().indexOf("CCC") != -1) {
            outTradeOut = outTradeOut.substring(callbackResult.getOut_trade_no().indexOf("CCC") + 3,
                    callbackResult.getOut_trade_no().length());
            logger.info("outTradeOut---:" + outTradeOut);
        }
        // 2. 查询
        ChannelCapitalRecord chcrDB = channelCapitalRecordMapper
                .selectByPrimaryKeyFromMaster(Long.valueOf(outTradeOut));
        // 3. 判断..
        if (ChannelCapitalStatusEnum.PROCESSING.getCode() != chcrDB.getStatus()) {

            chcrDB.setBankType(callbackResult.getBank_type());
            chcrDB.setTransactionId(callbackResult.getTransaction_id());
            chcrDB.setResultDesc(callbackResult.getReturn_msg());
            chcrDB.setResultCode(ChannelCapitalStatusEnum.SUCCESS.getCode() + "");
            chcrDB.setTradeType(callbackResult.getTrade_type());
            if (null != callbackResult.getTime_end()) {
                Date timeEnd = DateUtils.getDateByFormat(DateUtils.LONG_FORMAT, callbackResult.getTime_end());
                chcrDB.setTimeEnd(timeEnd);
            }
            chcrDB.setMchId(callbackResult.getMch_id());
            chcrDB.setUpdateTime(new Date());
            channelCapitalRecordMapper.updateByPrimaryKeySelective(chcrDB);
            logger.info("已经充值处理成功更新渠道结果即可:" + chcrDB);
            return true;
        }

        ChannelCapitalRecord chcr = channelCapitalRecordMapper.selectByPrimaryKeyForUpdate(Long.valueOf(outTradeOut));
        RechargeCapitalChangeRecordModel ccr = rechargeTradeInfoService
                .getCapitalChangeRecordById(chcr.getCapitalChangeId());
        RechargeTradeInfoModel ti = rechargeTradeInfoService.getTradeInfoById(ccr.getTradeRecordId());
        // 4 .更新失败
        // channel status is processing
        if (RechargeResultEnum.FAILURE.getCode().equals(callbackResult.getReturn_code())) {
            chcr.setStatus(ChannelCapitalStatusEnum.FAILURE.getCode());
            chcr.setResultDesc(callbackResult.getReturn_msg());
            chcr.setResultCode(callbackResult.getReturn_code());
            chcr.setUpdateTime(new Date());

            ccr.setUpdateDate(new Date());
            ccr.setCapitalStatus(CapitalStatusEnum.FAILURE.getCode());
            ccr.setReturnMsg(callbackResult.getReturn_msg());
            ccr.setReturnCode(callbackResult.getReturn_code());

            ti.setStatus(TradeStatusEnum.FAILURE.getCode());
            ti.setStatusCn(TradeStatusEnum.FAILURE.getDesc());
            ti.setUpdateTime(new Date());
            rechargeTradeInfoService.updateTradeInfoAndCapitialChangeRecord(ti, ccr, chcr);
            return false;
        } else if (RechargeResultEnum.SUCCESS.getCode().equals(callbackResult.getReturn_code())
                && ChannelCapitalStatusEnum.PROCESSING.getCode() == chcr.getStatus()) {
            chcr.setBankType(callbackResult.getBank_type());
            chcr.setTransactionId(callbackResult.getTransaction_id());
            chcr.setStatus(ChannelCapitalStatusEnum.SUCCESS.getCode());
            chcr.setResultDesc(callbackResult.getReturn_msg());
            chcr.setResultCode(ChannelCapitalStatusEnum.SUCCESS.getCode() + "");
            chcr.setTradeType(callbackResult.getTrade_type());
            if (null != callbackResult.getTime_end()) {
                Date timeEnd = DateUtils.getDateByFormat(DateUtils.LONG_FORMAT, callbackResult.getTime_end());
                chcr.setTimeEnd(timeEnd);
            }
            chcr.setMchId(callbackResult.getMch_id());
            chcr.setUpdateTime(new Date());

            ccr.setUpdateDate(new Date());
            ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
            ccr.setReturnMsg(callbackResult.getReturn_msg());
            ccr.setReturnCode(callbackResult.getReturn_code());

            ti.setStatus(TradeStatusEnum.SUCCESS.getCode());
            ti.setStatusCn(TradeStatusEnum.SUCCESS.getDesc());
            ti.setUpdateTime(new Date());
            rechargeTradeInfoService.updateTradeInfoAndCapitialChangeRecord(ti, ccr, chcr);

            custAccountService.recharge(ti.getCustNo(), ccr);

            CustCanwithdrawChargeInfoModel cci = new CustCanwithdrawChargeInfoModel();
            cci.setChannelType(chcr.getChannelType());
            cci.setCustNo(ti.getCustNo());
            cci.setOutTradeNo(chcr.getOutTradeNo());
            cci.setTradeId(ti.getTradeId());
            cci.setTradeTime(chcr.getUpdateTime());
            custCanwithdrawChargeInfoModelMapper.insert(cci);
        }

        return true;

    }

    /**
     * 处理支付宝充值回调，修改订单...
     *
     * @param appIdBack   我们小程序appid
     * @param outTradeNo  交易渠道记录id
     * @param tradeStatus 支付宝返回的交易状态 （TRADE_SUCCESS 交易支付成功）
     * @param totalAmount 交易金额
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public boolean handleChannelCapitailResultForZfbApp(String appIdBack, String outTradeNo, String tradeStatus, String totalAmount,
                                                        String gmtClose, String selleEmail, String outBizNo) throws Exception {
        logger.info("处理支付宝充值回调，修改订单方法handleChannelCapitailResultForZfbApp入参:appId:{},outTradeNo:{},tradeStatus:{}," +
                "totalAmount:{},gmtClose:{},selleEmail:{},outBizNo:{}", appIdBack, outTradeNo, tradeStatus, totalAmount, gmtClose, selleEmail, outBizNo);
        logger.info("zfbAppId:", zfbAppId);
        if (StringUtils.isEmpty(outTradeNo)) {
            logger.warn("处理支付宝充值回调，修改订单方法 Out_trade_no is null！");
            return false;
        }
        if (StringUtils.isEmpty(appIdBack)) {
            logger.warn("处理支付宝充值回调，修改订单方法 appId is null！");
            return false;
        }
        if (StringUtils.isEmpty(tradeStatus)) {
            logger.warn("处理支付宝充值回调，修改订单方法 tradeStatus is null！");
            return false;
        }
        if (StringUtils.isEmpty(totalAmount)) {
            logger.warn("处理支付宝充值回调，修改订单方法 totalAmount is null！");
            return false;
        }
        if (!appIdBack.equals(zfbAppId)) {
            logger.warn("处理支付宝充值回调，修改订单方法 appId 不对应！");
            return false;
        }
        if (!tradeStatus.equals("TRADE_SUCCESS")) {
            logger.warn("处理支付宝充值回调，修改订单方法 支付宝交易状态不成功！");
            return false;
        }
        if (totalAmount.equals("0")) {
            logger.warn("处理支付宝充值回调，修改订单方法 支付宝交易为0！");
            return false;
        }

        // 2. 查询交易相关
        ChannelCapitalRecord chcrDB = channelCapitalRecordMapper
                .selectByPrimaryKeyFromMaster(Long.valueOf(outTradeNo));
//        ChannelCapitalRecord chcrDB = channelCapitalRecordMapper
//                .selectByOutTradeNoFromMaster(Long.valueOf(outTradeNo));
        if (chcrDB == null) {
            logger.warn("处理支付宝充值回调，修改订单方法 平台交易渠道记录为空！");
            return false;
        }
        RechargeTradeInfoModel tradeInfoModel = rechargeTradeInfoModelMapper.selectByPrimaryKey(chcrDB.getTradeRecordId());
        if (tradeInfoModel == null) {
            logger.warn("处理支付宝充值回调，修改订单方法 平台交易记录为空！");
            return false;
        }

        if (tradeInfoModel.getTradeAmount().compareTo(new BigDecimal(totalAmount)) != 0) {
            logger.warn("处理支付宝充值回调，修改订单方法 平台交易金额不一致！");
            return false;
        }

        // 3. 判断..
        if (ChannelCapitalStatusEnum.PROCESSING.getCode() != chcrDB.getStatus()) {
            chcrDB.setResultCode(ChannelCapitalStatusEnum.SUCCESS.getCode() + "");
            //交易结束时间
            if (null != gmtClose) {
                Date timeEnd = DateUtils.getDateByFormat(DateUtils.LONG_FORMAT, gmtClose);
                chcrDB.setTimeEnd(timeEnd);
            }
            chcrDB.setResultDesc("充值成功");
            chcrDB.setTransactionId(outBizNo);//支付宝返回业务id
            chcrDB.setMchId(selleEmail); //支付宝账号
            chcrDB.setTradeType(String.valueOf(tradeInfoModel.getTradeType()));
            chcrDB.setChannelType(ChannelTypeEnum.ZFB_APP_CHARGE.getCode());
            chcrDB.setUpdateTime(new Date());
            channelCapitalRecordMapper.updateByPrimaryKeySelective(chcrDB);
            logger.info("已经充值处理成功更新渠道结果即可:" + chcrDB);
            return true;
        }

        ChannelCapitalRecord chcr = channelCapitalRecordMapper.selectByPrimaryKeyForUpdate(Long.valueOf(outTradeNo));
        RechargeCapitalChangeRecordModel ccr = rechargeTradeInfoService
                .getCapitalChangeRecordById(chcr.getCapitalChangeId());
        RechargeTradeInfoModel ti = rechargeTradeInfoService.getTradeInfoById(ccr.getTradeRecordId());
        //4.交易成功
        if (ChannelCapitalStatusEnum.PROCESSING.getCode() == chcr.getStatus()) {
            chcr.setTransactionId(outBizNo);
            chcr.setStatus(ChannelCapitalStatusEnum.SUCCESS.getCode());
            chcr.setResultDesc("充值成功");
            chcr.setResultCode(ChannelCapitalStatusEnum.SUCCESS.getCode() + "");
            chcr.setTradeType(String.valueOf(tradeInfoModel.getTradeType()));
            chcrDB.setChannelType(ChannelTypeEnum.ZFB_APP_CHARGE.getCode());
            //交易结束时间
            if (null != gmtClose) {
                Date timeEnd = DateUtils.getDateByFormat(DateUtils.LONG_FORMAT, gmtClose);
                chcr.setTimeEnd(timeEnd);
            }
            chcr.setMchId(selleEmail); //支付宝账号
            chcr.setUpdateTime(new Date());
            ccr.setUpdateDate(new Date());
            ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
            ti.setStatus(TradeStatusEnum.SUCCESS.getCode());
            ti.setStatusCn(TradeStatusEnum.SUCCESS.getDesc());
            ti.setUpdateTime(new Date());
            rechargeTradeInfoService.updateTradeInfoAndCapitialChangeRecord(ti, ccr, chcr);

            custAccountService.recharge(ti.getCustNo(), ccr);

            CustCanwithdrawChargeInfoModel cci = new CustCanwithdrawChargeInfoModel();
            cci.setChannelType(chcr.getChannelType());
            cci.setCustNo(ti.getCustNo());
            cci.setOutTradeNo(Long.valueOf(outTradeNo)); //用于退款
            cci.setTradeId(ti.getTradeId());
            cci.setTradeTime(chcr.getUpdateTime());
            custCanwithdrawChargeInfoModelMapper.insert(cci);
        }

        return true;

    }

    /**
     * 生成预支付订单
     *
     * @param custInfoModel
     * @param needFee
     * @param chargeBody
     * @param channelType
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public WxAppPayInfoModel getPrepayIdForWxApp(CustInfoModel custInfoModel, Long needFee, String chargeBody,
                                                 int channelType) {
        // 1.生成共享平台的预支付订单。。
        Long outTradeNo = saveTradeInfoAndCapitialChangeRecordForRecharge(custInfoModel, new BigDecimal(needFee),
                chargeBody, channelType, ShareTradeTypeEnum.RECHARGE.getCode());
        // 2. 生成微信订单的预支付订单
        WxAppPayInfoModel payInfo = new WxAppPayInfoModel();
        payInfo.setBody("充值");
        payInfo.setTotalFee(String.valueOf(needFee));
        payInfo.setOutTradeNo(String.valueOf(outTradeNo));
        payInfo.setOpenId(custInfoModel.getWxAppOpenId());
        // 2.2 提交给微信生成订单...
        WxAppPayInfoModel prepayModel = wxAppApiService.wxAppPrePay(payInfo);
        return prepayModel;
    }

    /**
     * 用户交易之前，生成预支付订单..
     *
     * @return
     */
    private Long saveTradeInfoAndCapitialChangeRecordForRecharge(CustInfoModel payeeCustInfo, BigDecimal rechargeAmount,
                                                                 String tradeName, Integer channelType, Integer tradeType) {
        // 1.生成交易单号
        Long tradeId = seqService.getRechargeTradeRecordSeq();
        RechargeTradeInfoModel tradeIno = new RechargeTradeInfoModel();
        tradeIno.setTradeId(tradeId);
        tradeIno.setCheckDate(com.stylefeng.guns.core.util.DateUtils.getCheckDate());
        tradeIno.setStatus(TradeStatusEnum.PROCESSING.getCode());
        tradeIno.setStatusCn(TradeStatusEnum.PROCESSING.getDesc());
        tradeIno.setTradeType(tradeType);
        tradeIno.setCustNo(payeeCustInfo.getCustNo());
        tradeIno.setTradeName(tradeName);
        tradeIno.setTradeAmount(DecimalUtil.format(rechargeAmount));
        tradeIno.setCreateTime(new Date());
        // 2.生成交易流水
        RechargeCapitalChangeRecordModel ccr = new RechargeCapitalChangeRecordModel();
        ccr.setTradeRecordId(tradeId);
        ccr.setAmount(DecimalUtil.format(rechargeAmount));
        Long ccId = seqService.getRechargeCapitalChangeRecordSeq();
        ccr.setCapitalChangeId(ccId);
        ccr.setCapitalStatus(CapitalStatusEnum.PROCESSING.getCode());
        ccr.setCollectAccountId(payeeCustInfo.getCustAccountModel().getAccountId());
        ccr.setCollectCustNo(payeeCustInfo.getCustNo());
        ccr.setCreateTime(new Date());
        ccr.setFlowType(CapitalChangeTypeEnum.RECHARGE.getCode());
        // 3. 生成交易渠道订单
        ChannelCapitalRecord chcr = new ChannelCapitalRecord();
        Long cccSeq = seqService.getChannelCapitalChangeSeq();
        chcr.setStatus(ChannelCapitalStatusEnum.PROCESSING.getCode());
        chcr.setCreateTime(new Date());
        chcr.setChannelType(channelType);
        chcr.setFeeType(FeeUnitType.CNY.getCode());
        chcr.setOpenId(payeeCustInfo.getOpenId());
        chcr.setOutTradeNo(cccSeq);
        chcr.setRecordId(cccSeq);
        chcr.setTradeRecordId(ccr.getTradeRecordId());
        chcr.setTotalFee(rechargeAmount);
        chcr.setCapitalChangeId(ccId);
        chcr.setCustNo(tradeIno.getCustNo());
        // 4. 保存这三类订单.
        rechargeTradeInfoService.saveTradeInfoAndCapitialChangeRecord(tradeIno, ccr, chcr);
        return cccSeq;
    }

    /**
     * 用户交易之前，生成预支付订单---支付宝小程序
     *
     * @return
     */
    private Long saveTradeInfoAndCapitialChangeRecordForRechargeByZfb(CustInfoModel payeeCustInfo, BigDecimal rechargeAmount,
                                                                      String tradeName, Integer channelType, Integer tradeType) {
        // 1.生成交易单号
        Long tradeId = seqService.getRechargeTradeRecordSeq();
        RechargeTradeInfoModel tradeIno = new RechargeTradeInfoModel();
        tradeIno.setTradeId(tradeId);
        tradeIno.setCheckDate(com.stylefeng.guns.core.util.DateUtils.getCheckDate());
        tradeIno.setStatus(TradeStatusEnum.PROCESSING.getCode());
        tradeIno.setStatusCn(TradeStatusEnum.PROCESSING.getDesc());
        tradeIno.setTradeType(tradeType);
        tradeIno.setCustNo(payeeCustInfo.getCustNo());
        tradeIno.setTradeName(tradeName);
        tradeIno.setTradeAmount(DecimalUtil.format(rechargeAmount));
        tradeIno.setCreateTime(new Date());
        // 2.生成交易流水
        RechargeCapitalChangeRecordModel ccr = new RechargeCapitalChangeRecordModel();
        ccr.setTradeRecordId(tradeId);
        ccr.setAmount(DecimalUtil.format(rechargeAmount));
        Long ccId = seqService.getRechargeCapitalChangeRecordSeq();
        ccr.setCapitalChangeId(ccId);
        ccr.setCapitalStatus(CapitalStatusEnum.PROCESSING.getCode());
        ccr.setCollectAccountId(payeeCustInfo.getCustAccountModel().getAccountId());
        ccr.setCollectCustNo(payeeCustInfo.getCustNo());
        ccr.setCreateTime(new Date());
        ccr.setFlowType(CapitalChangeTypeEnum.RECHARGE.getCode());
        // 3. 生成交易渠道订单
        ChannelCapitalRecord chcr = new ChannelCapitalRecord();
        Long cccSeq = seqService.getChannelCapitalChangeSeq();
        chcr.setStatus(ChannelCapitalStatusEnum.PROCESSING.getCode());
        chcr.setCreateTime(new Date());
        chcr.setChannelType(channelType);
        chcr.setFeeType(FeeUnitType.CNY.getCode());
        chcr.setOpenId(payeeCustInfo.getZfbUserId());
        chcr.setOutTradeNo(tradeId);
        chcr.setRecordId(cccSeq);
        chcr.setTradeRecordId(ccr.getTradeRecordId());
        chcr.setTotalFee(rechargeAmount);
        chcr.setCapitalChangeId(ccId);
        chcr.setCustNo(tradeIno.getCustNo());
        // 4. 保存这三类订单.
        rechargeTradeInfoService.saveTradeInfoAndCapitialChangeRecord(tradeIno, ccr, chcr);
        return cccSeq;
    }

    /**
     * 查询并处理充值结果数据
     *
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public boolean handleChannelCapitailResult(Long outTradeNo, String result, String rstMsg) throws Exception {
        logger.info("handleChannelCapitailResult----:" + outTradeNo);
        if (null == outTradeNo) {
            logger.error("Out_trade_no is null:" + outTradeNo);
            return false;
        }
        // 1. 查询渠道记录
        ChannelCapitalRecord chcrDB = channelCapitalRecordMapper.selectByPrimaryKeyFromMaster(outTradeNo);
        if (ChannelCapitalStatusEnum.PROCESSING.getCode() != chcrDB.getStatus()) {
            logger.info("交易已经被处理--handleChannelCapitailResult:" + outTradeNo);
            return false;
        }
        // 2. 微信查询...
        WxMpOrderQueryResult rlst = null;
        try {
            if (ChannelTypeEnum.WEIXIN_GZH_CHARGE.getCode() == chcrDB.getChannelType().intValue()) {
                rlst = wxMpService.orderquery(outTradeNo.toString());
                logger.info("微信公众号充值交易【{}】查询结果【{}】", outTradeNo, rlst);
            }

            if (ChannelTypeEnum.WEIXIN_APP_CHARGE.getCode() == chcrDB.getChannelType().intValue()) {
                rlst = wxMpService.orderquery(outTradeNo.toString(), appId, wxMchId, key);
                logger.info("微信小程序appId:{}充值交易【{}】查询结果【{}】", appId, outTradeNo, rlst);
            }

            if (rlst == null) {
                return false;
            }
            if ("ORDERNOTEXIST".equals(rlst.getErr_code())) {
                result = ResultCommand.FAILED;
            }
            if ("NOTPAY".equals(rlst.getTrade_state())) {// no pay and pass
                // 5mins
                logger.warn("充值交易【{}】查询结果【{}】", outTradeNo, rlst.getTrade_state());
                return false;
            }
            if ("SUCCESS".equals(rlst.getTrade_state()) && "SUCCESS".equals(rlst.getResult_code())) {
                result = ResultCommand.SUCCESS;
            }
        } catch (Exception e) {
            logger.warn("查询充值结果异常!", e);
        }
        // 3. 查询平台订单状态..
        ChannelCapitalRecord chcr = channelCapitalRecordMapper.selectByPrimaryKeyForUpdate(outTradeNo);
        RechargeCapitalChangeRecordModel ccr = rechargeTradeInfoService
                .getCapitalChangeRecordById(chcr.getCapitalChangeId());
        RechargeTradeInfoModel ti = rechargeTradeInfoService.getTradeInfoById(ccr.getTradeRecordId());
        // 4. 修改订单状态..
        if (ResultCommand.SUCCESS.equals(result) && ChannelCapitalStatusEnum.PROCESSING.getCode() == chcr.getStatus()) {
            chcr.setStatus(ChannelCapitalStatusEnum.SUCCESS.getCode());
            chcr.setResultCode(rlst.getResult_code());
            chcr.setResultDesc(rlst.getReturn_msg());
            chcr.setUpdateTime(new Date());
            ccr.setUpdateDate(new Date());
            ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());

            ti.setStatus(TradeStatusEnum.SUCCESS.getCode());
            ti.setStatusCn(TradeStatusEnum.SUCCESS.getDesc());
            ti.setUpdateTime(new Date());

            rechargeTradeInfoService.updateTradeInfoAndCapitialChangeRecord(ti, ccr, chcr);
            // 处理账户余额...
            custAccountService.recharge(ti.getCustNo(), ccr);
            // 生成可提现表...
            CustCanwithdrawChargeInfoModel cci = new CustCanwithdrawChargeInfoModel();
            cci.setChannelType(chcr.getChannelType());
            cci.setCustNo(ti.getCustNo());
            cci.setOutTradeNo(chcr.getOutTradeNo());
            cci.setTradeId(ti.getTradeId());
            cci.setTradeTime(chcr.getUpdateTime());
            custCanwithdrawChargeInfoModelMapper.insert(cci);
        } else if (ResultCommand.FAILED.equals(result)) {
            chcr.setStatus(ChannelCapitalStatusEnum.FAILURE.getCode());
            chcr.setResultCode(rlst.getReturn_code());
            chcr.setResultDesc(rlst.getReturn_msg());
            chcr.setUpdateTime(new Date());
            ccr.setUpdateDate(new Date());
            ccr.setCapitalStatus(CapitalStatusEnum.FAILURE.getCode());
            ccr.setReturnMsg(rstMsg);
            ti.setStatus(TradeStatusEnum.FAILURE.getCode());
            ti.setStatusCn(rstMsg);
            ti.setUpdateTime(new Date());
            rechargeTradeInfoService.updateTradeInfoAndCapitialChangeRecord(ti, ccr, chcr);
            return false;
        }
        return true;
    }


    /**
     * 查询支付宝充值结果数据
     *
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public void handleChannelCapitailResultByZfb(Long outTradeNo) throws Exception {
        logger.info("handleChannelCapitailResultByZfb----:" + outTradeNo);
        // 1. 查询渠道记录
        ChannelCapitalRecord chcr = channelCapitalRecordMapper.selectByPrimaryKeyFromMaster(outTradeNo);
        if (ChannelCapitalStatusEnum.PROCESSING.getCode() == chcr.getStatus()) {
            logger.info("交易还在处理中--handleChannelCapitailResultByZfb:" + outTradeNo);
            throw new ExgrainBizUncheckedException("交易还在处理中,请稍后再扫码充电!");
        }
        if (ChannelCapitalStatusEnum.FAILURE.getCode() == chcr.getStatus()) {
            logger.info("交易还在处理中--handleChannelCapitailResultByZfb:" + outTradeNo);
            throw new ExgrainBizUncheckedException("交易失败!");
        }

    }

    /**
     * 根据充电器实体对象，返回充电器密码... 1.得到密码 2.更新充电器表中的密码序号...
     *
     * @param shareChargerModel
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public String getChargerPasswordByShareChargerModel(int feeTypeId, ShareChargerModel shareChargerModel,
    													ShareDeviceInfoModel shareDeviceInfoModel,double haveUseMinutes) {
        // 1.得到充电器密码，根据密码因子
        String pwds ="";
        if(shareDeviceInfoModel.getPasswordType().equals(ShareDeviceInfoNumberTypeEnum.YYMMBBXXXXX.getCode())){
        	pwds=ChargerPwdDecoder.getChargerPwdsForYYMMBB(shareDeviceInfoModel);
        }else{
        	pwds=ChargerPwdDecoder.getChargerPwds(shareChargerModel);
        }
        if (pwds == null || pwds.isEmpty()) {
            throw new ExgrainBizUncheckedException("设备未烧录密码或密码烧录失败，请联系管理员！");
        }

        logger.info(String.format("根据充电器实体对象，返回充电器密码...----pwds:%s", pwds));
        String[] pwdsAry = pwds.split(";");
        if (pwdsAry.length < 10) {
            throw new ExgrainBizUncheckedException("设备未烧录密码或密码烧录失败，请联系管理员！");
        }
        // 2.更新数据库密码..
        Long currentIndex = shareChargerModel.getPwdIndex();
        ShareChargerModel cm = new ShareChargerModel();
        cm.setId(shareChargerModel.getId());
        logger.info(String.format("根据充电器实体对象，返回充电器密码...currentIndex:%d--pwds:%s,pwdsAry.length:%d", currentIndex, pwds,
                pwdsAry.length));
        cm.setPwdIndex((currentIndex + 1) % pwdsAry.length);
        cm.setUpdateDateTime(new Date());
        shareChargerModelMapper.updateByPrimaryKeySelective(cm);
        // 3. 返回.
        String pwd = pwdsAry[currentIndex.intValue()];
        // 主处理按时间控制充电.获到按时间控制充电的密码
        ShareFeeTypeEnum shareFeeTypeEnum = ShareFeeTypeEnum.getShareFeeTypeEnumByCode(String.format("%d", feeTypeId));
        logger.info(String.format("根据充电器实体对象，返回充电器密码.feeTypeId:%d,currentIndex.intValue():%d", feeTypeId,
                currentIndex.intValue()));
        if (feeTypeId != 0 && feeTypeId < 25) {
            Long hours = shareFeeTypeEnum.getUseTimesForSecnod() / 3600;
            logger.info(String.format("根据充电器实体对象，返回充电器密码.feeTypeId:%d----hours:%d", feeTypeId, hours));
            if (haveUseMinutes > 0) {
                haveUseMinutes = haveUseMinutes / 60.0;
                haveUseMinutes = Math.ceil(haveUseMinutes);
                hours = (long) haveUseMinutes;
            }
            //如果大于15小时，就取15小时的密码
            if (hours.compareTo(15L) > 0) {
                hours = 15L;
            }
            logger.info(String.format("根据充电器实体对象，返回充电器密码.feeTypeId:%d----hours:%d", feeTypeId, hours));
            if(shareDeviceInfoModel.getPasswordType().equals(ShareDeviceInfoNumberTypeEnum.YYMMBBXXXXX.getCode())){
            	if(hours<=1){
            		pwd=pwd+"1";//1小时
            	}else if(hours>1 && hours<=3){
            		pwd=pwd+"2";//3小时
            	}else if(hours>3&&hours<=5){
            		pwd=pwd+"3";//5小时
            	}else{
            		pwd=pwd+"4";//12小时..
            	}
            }else{
	            // 计算不剩几小时
	            pwd = GeneratePwdCalacutorWithFactors.getPwdForTimesByHoursAndPwds(hours.intValue(), pwd,shareChargerModel.getChargerTypeId());
            }
        } else {
            if(shareDeviceInfoModel.getPasswordType().equals(ShareDeviceInfoNumberTypeEnum.YYMMBBXXXXX.getCode())){
            	pwd=pwd+"4";//12小时
            }else{
	            if (shareChargerModel.getChargerTypeId().equals(ShareChargerTypeEnum.ZD_33.getCode())) {
	                int[] aryHour = new int[]{13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
	                Random rd = new Random();
	                int iNext = rd.nextInt(11);
	                int hourTmp = aryHour[iNext];
	                pwd = GeneratePwdCalacutorWithFactors.getPwdForTimesByHoursAndPwds(hourTmp, pwd, shareChargerModel.getChargerTypeId());
	            }
	            //海南的
	            if (shareChargerModel.getChargerTypeId().equals(ShareChargerTypeEnum.SD_20.getCode())
	            		||shareChargerModel.getChargerTypeId().equals(ShareChargerTypeEnum.SD_21.getCode())) {
	            	pwd = GeneratePwdCalacutorWithFactors.getPwdForTimesByHoursAndPwds(15, pwd,shareChargerModel.getChargerTypeId());
	            }
            }
        }
        return pwd;
    }

    /**
     * 处理交易并返回密码..1、获取密码 2、生成交易订单,3. 发微信、小程序、公众号信息，4、生成使用率表...
     *
     * @param shareDeviceInfoModel
     * @param shareChargerModel
     * @param payerCustInfo
     * @param latitude
     * @param longitude
     * @param zjAddr
     * @param feeTypeId
     * @param payOutTradeNo
     * @param resultCmdObject
     * @return
     * @throws Exception
     */
    public Long getPasswordForRecharge(ShareDeviceInfoModel shareDeviceInfoModel, ShareChargerModel shareChargerModel,
                                       CustInfoModel payerCustInfo, String latitude, String longitude, String zjAddr, String feeTypeId,
                                       String payOutTradeNo, ResultCommandBaseObject<Object> resultCmdObject) throws Exception {
        ShareFeeTypeEnum feeTypeEnum = ShareFeeTypeEnum.getShareFeeTypeEnumByCode(feeTypeId);
        // 1. 修改充电器表中的密码序号..
        String pwd = getChargerPasswordByShareChargerModel(feeTypeEnum.getCode(), shareChargerModel,shareDeviceInfoModel, 0L);
        // 2. 处理 获到初始数据..
        Long tradeId = seqService.getShareTradeInfoSeq();
        ShareFeeBO feeBo = ShareFeeBO.getShareFeeBOByFeeTypeId(shareDeviceInfoModel, feeTypeEnum);
        // 生成订单
        generateOrderForRecharge(shareDeviceInfoModel, shareChargerModel, payerCustInfo, latitude, longitude, zjAddr,
                payOutTradeNo, tradeId, feeTypeEnum, feeBo, resultCmdObject, TradeChannelEnum.WX.getCode());

        // 4. 返回密码信息
        resultCmdObject.setMessage("您租借的充电器动态密码为:【" + pwd + "】.");
        resultCmdObject.setResult(pwd);

        logger.info("您租借的充电器动态密码为----{}", resultCmdObject);

        String payDetial = "";
        String wxAppRemarkStr = "如有疑问，可拨主电器上的客户热线电话。";
        if (feeTypeEnum.getCode() < 25) {
            // 表示指定充电时间的费用模式..
            payDetial = String.format("【预付金：%.2f元，充电%d小时】【%d小时后，订单自动结束】", feeBo.getTotalAmountForUseHour(),
                    feeBo.getUseHours(), feeBo.getUseHours());
        } else if (feeTypeEnum.getCode() == 25) {
            // 表示预付金的收费模式。。
            payDetial = String.format("【预付金：%.2f元】【每1小时，支付充电费用%.2f元收费，24小时封顶收费%.2f元】【使用费超出预付金，扣完预付金，订单自动结束】",
                    feeBo.getYfj(), feeBo.getAmountPerHour(), feeBo.getMaxAmountPer24Hours());
        } else {
            // 表示有首付金及首付使用时长的预付金模式..
            payDetial = String.format(
                    "【预付金：%.2f元】【前%d分钟，收费%.2f元，超出%d分钟，按每1小时，支付%.2f元收费，24小时封顶收费%.2f元】【使用费超出预付金，扣完预付金，订单自动结束】",
                    feeBo.getYfj(), feeBo.getFirstMinutes(), feeBo.getFirstAmount(), feeBo.getFirstMinutes(),
                    feeBo.getAmountPerHour(), feeBo.getMaxAmountPer24Hours());

        }

        // 5.3 插入平台消息==============================
        NotifyMessageModel message = new NotifyMessageModel();
        message.setId(seqService.getNotifyMessageSeq());
        message.setType(NotifyMessageTypeEnum.LENT_MESSAGE.getCode());
        message.setStatus(NotifyMessageStatusEnum.UNREAD_MESSAGE.getCode());
        message.setReceiveCustNo(payerCustInfo.getCustNo());
        message.setTradeId(tradeId);
        message.setCreateTime(new Date());
        message.setTitle("租借充电器通知");
        message.setContent(String.format("您租借充电器成功啦! 支付预付金：%.2f 元， 费用详情：%s", feeBo.getYfj(), payDetial));
        notifyMessageMapper.insertSelective(message);

        return tradeId;
    }

    /**
     * 处理交易并返回密码..1、获取密码 2、生成交易订单,3. 发微信、小程序、公众号信息，4、生成使用率表...
     *
     * @param shareDeviceInfoModel
     * @param shareChargerModel
     * @param payerCustInfo
     * @param latitude
     * @param longitude
     * @param zjAddr
     * @param feeTypeId
     * @param payOutTradeNo
     * @param resultCmdObject
     * @return
     * @throws Exception
     */
    public Long getPasswordForRechargeByZfb(ShareDeviceInfoModel shareDeviceInfoModel, ShareChargerModel shareChargerModel,
                                            CustInfoModel payerCustInfo, String latitude, String longitude, String zjAddr, String feeTypeId,
                                            String payOutTradeNo, ResultCommandBaseObject<Object> resultCmdObject) throws Exception {
        ShareFeeTypeEnum feeTypeEnum = ShareFeeTypeEnum.getShareFeeTypeEnumByCode(feeTypeId);
        // 1. 修改充电器表中的密码序号..
        String pwd = getChargerPasswordByShareChargerModel(feeTypeEnum.getCode(), shareChargerModel,shareDeviceInfoModel, 0L);
        // 2. 处理 获到初始数据..
        Long tradeId = seqService.getShareTradeInfoSeq();
        ShareFeeBO feeBo = ShareFeeBO.getShareFeeBOByFeeTypeId(shareDeviceInfoModel, feeTypeEnum);
        // 生成订单
        generateOrderForRecharge(shareDeviceInfoModel, shareChargerModel, payerCustInfo, latitude, longitude, zjAddr,
                payOutTradeNo, tradeId, feeTypeEnum, feeBo, resultCmdObject, TradeChannelEnum.ZFB.getCode());

        // 4. 返回密码信息
        resultCmdObject.setMessage("您租借的充电器动态密码为:【" + pwd + "】.");
        resultCmdObject.setResult(pwd);

        logger.info("您租借的充电器动态密码为----{}", resultCmdObject);

        String payDetial = "";
        String wxAppRemarkStr = "如有疑问，可拨主电器上的客户热线电话。";
        if (feeTypeEnum.getCode() < 25) {
            // 表示指定充电时间的费用模式..
            payDetial = String.format("【预付金：%.2f元，充电%d小时】【%d小时后，订单自动结束】", feeBo.getTotalAmountForUseHour(),
                    feeBo.getUseHours(), feeBo.getUseHours());
        } else if (feeTypeEnum.getCode() == 25) {
            // 表示预付金的收费模式。。
            payDetial = String.format("【预付金：%.2f元】【每1小时，支付充电费用%.2f元收费，24小时封顶收费%.2f元】【使用费超出预付金，扣完预付金，订单自动结束】",
                    feeBo.getYfj(), feeBo.getAmountPerHour(), feeBo.getMaxAmountPer24Hours());
        } else {
            // 表示有首付金及首付使用时长的预付金模式..
            payDetial = String.format(
                    "【预付金：%.2f元】【前%d分钟，收费%.2f元，超出%d分钟，按每1小时，支付%.2f元收费，24小时封顶收费%.2f元】【使用费超出预付金，扣完预付金，订单自动结束】",
                    feeBo.getYfj(), feeBo.getFirstMinutes(), feeBo.getFirstAmount(), feeBo.getFirstMinutes(),
                    feeBo.getAmountPerHour(), feeBo.getMaxAmountPer24Hours());

        }

        // 5.3 插入平台消息==============================
        NotifyMessageModel message = new NotifyMessageModel();
        message.setId(seqService.getNotifyMessageSeq());
        message.setType(NotifyMessageTypeEnum.LENT_MESSAGE.getCode());
        message.setStatus(NotifyMessageStatusEnum.UNREAD_MESSAGE.getCode());
        message.setReceiveCustNo(payerCustInfo.getCustNo());
        message.setTradeId(tradeId);
        message.setCreateTime(new Date());
        message.setTitle("租借充电器通知");
        message.setContent(String.format("您租借充电器成功啦! 支付预付金：%.2f 元， 费用详情：%s", feeBo.getYfj(), payDetial));
        notifyMessageMapper.insertSelective(message);

        return tradeId;
    }

    /**
     * 生成交易订单。 1.
     *
     * @param shareDeviceInfoModel
     * @param shareChargerModel
     * @param payerCustInfo
     * @param latitude
     * @param longitude
     * @param zjAddr
     * @param payOutTradeNo
     * @param resultCmdObject
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public ShareTradeInfoModel generateOrderForRecharge(ShareDeviceInfoModel shareDeviceInfoModel,
                                                        ShareChargerModel shareChargerModel, CustInfoModel payerCustInfo, String latitude, String longitude,
                                                        String zjAddr, String payOutTradeNo, Long tradeId, ShareFeeTypeEnum feeTypeEnum, ShareFeeBO feeBo,
                                                        ResultCommandBaseObject<Object> resultCmdObject, int tradeChannel) throws Exception {
        // 0. 得到初始数据...
        BigDecimal yfjAmt = feeBo.getYfj();
        ShareTradeInfoModel ti = new ShareTradeInfoModel();
        try {
            Date now = new Date();
            // 1. 冻结资金..
            if (BigDecimal.ZERO.compareTo(yfjAmt) != 0) {
                CapitalChangeRecordModel ccr = new CapitalChangeRecordModel();
                ccr.setTradeRecordId(tradeId);
                // 1.1 扣预付金
                ccr.setAmount(yfjAmt);
                Long capitalChangeSeq = seqService.getCapitalChangeSeq();
                ccr.setCapitalChangeId(capitalChangeSeq);
                ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
                ccr.setPayerAccountId(payerCustInfo.getCustAccountModel().getAccountId());
                ccr.setPayerCustNo(payerCustInfo.getCustNo());
                ccr.setCreateTime(now);
                ccr.setFlowType(CapitalChangeTypeEnum.FREEZED.getCode());
                capitalChangeRecordModelMapper.insert(ccr);
                // 1.2 冻结资金
                custAccountService.freeze(payerCustInfo, ccr);
            }
            // 2. 生成订单...
            // 2.1 生成主订单
            if (StringUtils.isNotEmpty(latitude) && StringUtils.isNotEmpty(longitude)) {
                ti.setxCoordinate(new BigDecimal(latitude));
                ti.setyCoordinate(new BigDecimal(longitude));
                // 通过经纬度获取省市区
                MapCommand mapCommand = MapUtil.getLocation1(longitude, latitude);
                if (mapCommand != null) {
                    ti.setTradeProvince(mapCommand.getProvince());
                    ti.setTradeCity(mapCommand.getCity());
                    ti.setTradeZone(mapCommand.getZone());
                }

            }
            Date borrowTime = new Date();
            ti.setAgents1Amount(new BigDecimal("0"));
            ti.setAgents1Id(shareDeviceInfoModel.getAgents1Id());
            ti.setAgents2Amount(new BigDecimal("0"));
            ti.setAgents2Id(shareDeviceInfoModel.getAgents2Id());
            ti.setAgents3Amount(new BigDecimal("0"));
            ti.setAgents3Id(shareDeviceInfoModel.getAgents3Id());
            ti.setAllianceBusinessAmount(new BigDecimal("0"));
            ti.setAllianceBusinessId(shareDeviceInfoModel.getAllianceBusinessId());
            ti.setBenefitStatus(ShareBenefitStatusEnum.UnFinishDistribution.getCode());
            ti.setBenefitStatusName(ShareBenefitStatusEnum.UnFinishDistribution.getDesc());
            ti.setBorrowDatetime(borrowTime);
            ti.setChargerId(shareChargerModel.getId());
            ti.setCreateDatetime(borrowTime);
            ti.setCreateId(payerCustInfo.getCustNo());
            ti.setCustId(payerCustInfo.getCustNo());
            ti.setDeviceNo(shareDeviceInfoModel.getId());
            ti.setId(tradeId);
            ti.setMerchantId(shareDeviceInfoModel.getOnlineMerchantId());
            ti.setPlatformAmount(new BigDecimal("0"));
            ti.setSettleAccountsStatus(ShareSettleAccountsStatusEnum.UnFinishSettleAccount.getCode());
            ti.setShopkeeperAmount(new BigDecimal("0"));
            ti.setShopkeeperId(shareDeviceInfoModel.getShopkeeperId());
            ti.setTradeAddress(zjAddr);
            ti.setTradeAmount(new BigDecimal("0"));
            ti.setTradeName(feeTypeEnum.getDesc());
            ti.setTradeStatus(ShareTradeStatusEnum.TradingUsing.getCode());
            ti.setTradeStatusName(ShareTradeStatusEnum.TradingUsing.getDesc());
            ti.setTradeType(feeTypeEnum.getCode());
            ti.setUpdateDatetime(borrowTime);
            ti.setYfjAmount(feeBo.getYfj());
            ti.setTradeChannel(tradeChannel);
            ti.setTradeChannelName(TradeChannelEnum.getDesc(tradeChannel));
            shareTradeInfoModelMapper.insert(ti);
            // 2.2 生成当时设备的设备信息表数据...
            ShareTradeDeviceInfoModel shareTradeDeviceInfoModel = new ShareTradeDeviceInfoModel();
            shareTradeDeviceInfoModel.setAgents1Cn(shareDeviceInfoModel.getAgents1Cn());
            shareTradeDeviceInfoModel.setAgents1Id(shareDeviceInfoModel.getAgents1Id());
            shareTradeDeviceInfoModel.setAgents1Rato(shareDeviceInfoModel.getAgents1Rato());
            shareTradeDeviceInfoModel.setAgents2Cn(shareDeviceInfoModel.getAgents2Cn());
            shareTradeDeviceInfoModel.setAgents2Id(shareDeviceInfoModel.getAgents2Id());
            shareTradeDeviceInfoModel.setAgents2Rato(shareDeviceInfoModel.getAgents2Rato());
            shareTradeDeviceInfoModel.setAgents3Cn(shareDeviceInfoModel.getAgents3Cn());
            shareTradeDeviceInfoModel.setAgents3Id(shareDeviceInfoModel.getAgents3Id());
            shareTradeDeviceInfoModel.setAgents3Rato(shareDeviceInfoModel.getAgents3Rato());
            shareTradeDeviceInfoModel.setAllianceBusinessCn(shareDeviceInfoModel.getAllianceBusinessCn());
            shareTradeDeviceInfoModel.setAllianceBusinessId(shareDeviceInfoModel.getAllianceBusinessId());
            shareTradeDeviceInfoModel.setAllianceBusinessRate(shareDeviceInfoModel.getAllianceBusinessRate());
            shareTradeDeviceInfoModel.setAmountMax24hour(shareDeviceInfoModel.getAmountMax24hour());
            shareTradeDeviceInfoModel.setAmountPerHour(shareDeviceInfoModel.getAmountPerHour());
            shareTradeDeviceInfoModel.setCreateDatetime(borrowTime);
            shareTradeDeviceInfoModel.setCreateId(payerCustInfo.getCustNo());
            shareTradeDeviceInfoModel.setDeviceId(shareDeviceInfoModel.getId());
            shareTradeDeviceInfoModel.setDeviceStatus(shareDeviceInfoModel.getDeviceStatus());
            shareTradeDeviceInfoModel.setDeviceTypeId(shareDeviceInfoModel.getDeviceTypeId());
            shareTradeDeviceInfoModel.setDeviceTypeName(shareDeviceInfoModel.getDeviceTypeName());
            shareTradeDeviceInfoModel.setFee1HourAmount(shareDeviceInfoModel.getFee1HourAmount());
            shareTradeDeviceInfoModel.setFee1HourType(shareDeviceInfoModel.getFee1HourType());
            shareTradeDeviceInfoModel.setFee1TypeId(shareDeviceInfoModel.getFee1TypeId());
            shareTradeDeviceInfoModel.setFee1TypeName(shareDeviceInfoModel.getFee1TypeName());
            shareTradeDeviceInfoModel.setFee2HourAmount(shareDeviceInfoModel.getFee2HourAmount());
            shareTradeDeviceInfoModel.setFee2TypeId(shareDeviceInfoModel.getFee2TypeId());
            shareTradeDeviceInfoModel.setFee2HourType(shareDeviceInfoModel.getFee2HourType());
            shareTradeDeviceInfoModel.setFee2TypeName(shareDeviceInfoModel.getFee2TypeName());
            shareTradeDeviceInfoModel.setFee3HourAount(shareDeviceInfoModel.getFee3HourAmount());
            shareTradeDeviceInfoModel.setFee3TypeId(shareDeviceInfoModel.getFee3TypeId());
            shareTradeDeviceInfoModel.setFee3HourType(shareDeviceInfoModel.getFee3HourType());
            shareTradeDeviceInfoModel.setFee3TypeName(shareDeviceInfoModel.getFee3TypeName());

            shareTradeDeviceInfoModel.setFeeTypeId(shareDeviceInfoModel.getFeeTypeId());
            shareTradeDeviceInfoModel.setFeeTypeName(shareDeviceInfoModel.getFeeTypeName());
            shareTradeDeviceInfoModel.setFirstAmount(shareDeviceInfoModel.getFirstAmount());
            shareTradeDeviceInfoModel.setFirstMinutes(shareDeviceInfoModel.getFirstMinutes());
            shareTradeDeviceInfoModel.setOnlineAddress(shareDeviceInfoModel.getOnlineAddress());
            shareTradeDeviceInfoModel.setOnlineDatetime(shareDeviceInfoModel.getOnlineDatetime());
            shareTradeDeviceInfoModel.setOnlineMerchantId(shareDeviceInfoModel.getOnlineMerchantId());
            shareTradeDeviceInfoModel.setOnlineMerchantCn(shareDeviceInfoModel.getOnlineMerchantCn());
            shareTradeDeviceInfoModel.setOnlineProvince(shareDeviceInfoModel.getOnlineProvince());
            shareTradeDeviceInfoModel.setOnlineXCoordinate(shareDeviceInfoModel.getOnlineXCoordinate());
            shareTradeDeviceInfoModel.setOnlineYCoordinate(shareDeviceInfoModel.getOnlineYCoordinate());
            shareTradeDeviceInfoModel.setPlatformRato(shareDeviceInfoModel.getPlatformRato());
            shareTradeDeviceInfoModel.setRemark(shareDeviceInfoModel.getRemark());
            shareTradeDeviceInfoModel.setShopkeeperCn(shareDeviceInfoModel.getShopkeeperCn());
            shareTradeDeviceInfoModel.setShopkeeperId(shareDeviceInfoModel.getShopkeeperId());
            shareTradeDeviceInfoModel.setShopkeeperRato(shareDeviceInfoModel.getShopkeeperRato());
            shareTradeDeviceInfoModel.setSqrUrl(shareDeviceInfoModel.getSqrUrl());
            shareTradeDeviceInfoModel.setTradeCustNo(payerCustInfo.getCustNo());
            shareTradeDeviceInfoModel.setTradeId(tradeId);
            shareTradeDeviceInfoModel.setTradeYfjAmount(feeBo.getYfj());
            shareTradeDeviceInfoModel.setUpdateDatetime(borrowTime);
            shareTradeDeviceInfoModel.setYfjAmount(feeBo.getYfj());
            shareTradeDeviceInfoModel.setTradeFeeTypeId(feeTypeEnum.getCode());
            shareTradeDeviceInfoModel.setYfjRebackType(shareDeviceInfoModel.getYfjRebackType());
            shareTradeDeviceInfoModelMapper.insert(shareTradeDeviceInfoModel);
            // 3. 设备处理。。
            ShareChargerModel chargerModel = new ShareChargerModel();
            chargerModel.setId(ti.getChargerId());
            chargerModel.setLastUseTime(borrowTime);
            shareChargerModelMapper.updateByPrimaryKeySelective(chargerModel);
            return ti;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("新建交易记录异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("新建交易记录异常", e);
            throw e;
        }
    }

    /**
     * 查询提现记录...
     *
     * @param startIndex
     * @param rows
     * @param custNo
     * @return
     */
    public WithdrawPageInfoBO getWithdrawHistory(int startIndex, int rows, Long custNo) {
        WithdrawPageInfoBO wpib = new WithdrawPageInfoBO();
        try {
            // 1.查询提现记录...
            List<WithdrawTradeInfoBO> wtis = tradeInfoMapper.getWithdrawTradeHistory(startIndex, rows, custNo);
            if (wtis != null) {
                // 2. 提现处理..
                for (WithdrawTradeInfoBO wti : wtis) {
                    List<WithdrawInfoBO> tims = tradeInfoMapper.getWithdrawHistory(wti.getTradeId());
                    if (tims != null) {
                        if (tims.size() == 0) {
                            wti.setTradeStatus(WithdrawTradeInfoBO.FAIL);
                            continue;
                        }
                        // 1,2,3,4 --退款成功，2--部分退款成功，3，退款失败，4--退款处理中
                        boolean refundSuccess = true;
                        boolean refundFail = true;
                        boolean refundProcessing = false;
                        Set<Integer> partialSucess = new HashSet<>();
                        for (WithdrawInfoBO tim : tims) {
                            if (tim.getReconStatus() == null) {
                                tim.setBankStatus("处理中");
                                refundProcessing = true;
                            } else {
                                if (ReconStatusEnum.SUCCESS.getCode() == tim.getReconStatus()) {
                                    tim.setBankStatus("成功");
                                    partialSucess.add(1);
                                    refundFail = false;
                                } else if (ReconStatusEnum.PROCESSING.getCode() == tim.getReconStatus()) {
                                    tim.setBankStatus("处理中");
                                    refundProcessing = true;

                                } else {
                                    tim.setBankStatus("失败");
                                    refundSuccess = false;
                                    partialSucess.add(2);
                                }
                            }

                            String bankDesc = WeiXinBankEnum.getDesc(tim.getRefundBank());
                            if (StringUtils.isNotEmpty(bankDesc)) {
                                tim.setRefundBank(bankDesc);
                            } else {
                                tim.setRefundBank(tim.getRefundBank());
                            }
                            wti.addWithdrawInfoBO(tim);
                        }

                        if (refundProcessing) {// 处理中
                            wti.setTradeStatus(WithdrawTradeInfoBO.PROCESSING);
                        } else {
                            if (refundSuccess) {
                                wti.setTradeStatus(WithdrawTradeInfoBO.SUCCESS);
                            }
                            if (refundFail) {
                                wti.setTradeStatus(WithdrawTradeInfoBO.FAIL);
                            }
                            if (partialSucess.size() > 1) {
                                wti.setTradeStatus(WithdrawTradeInfoBO.PARTIAL_SUCCESS);
                            }
                        }
                    }
                }
            }
            wpib.setWithdrawTradeInfoBOs(wtis);
            wpib.setResult("success");
        } catch (Exception e) {
            logger.error("", e);
            wpib.setResult("fail");
        }
        return wpib;
    }

    /**
     * 提现查询...
     *
     * @param refundOutId
     * @return
     * @throws Exception
     */
    public WithdrawInfoBO queryWithdrawResultByTradeOutId(String refundOutId) throws Exception {
        logger.info("queryWithdrawResultByTradeOutId查询入参{}", refundOutId);
        // 0. 整理参数
        WithdrawInfoBO withdrawInfoBO = new WithdrawInfoBO();
        ChannelCapitalRecord ccr = channelCapitalRecordMapper.selectByPrimaryKey(Long.valueOf(refundOutId));
        withdrawInfoBO.setTradeRecordId(ccr.getTradeRecordId());
        withdrawInfoBO.setWithdrawAmt(ccr.getTotalFee());
        withdrawInfoBO.setCreateTimeFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ccr.getCreateTime()));
        // 1. 提现查询...
        WxMpRefundQueryResult rlst = wxMpService.refundquery(refundOutId);
        logger.info("queryWithdrawResultByTradeOutId 查询结果{}", rlst);
        if (rlst != null) {
            Integer reconStatus = null;
            // 1.2 处理失败数据...
            if ("FAIL".equals(rlst.getResult_code())) {
                if ("REFUNDNOTEXIST".equals(rlst.getErr_code())) {
                    rlst.setRefund_recv_accout_0("退款记录不存在");
                    reconStatus = ReconStatusEnum.FAIL.getCode();
                }
            } else {
                if ("SUCCESS".equals(rlst.getRefund_status_0())) {
                    reconStatus = ReconStatusEnum.SUCCESS.getCode();
                }
                if ("FAIL".equals(rlst.getRefund_status_0())) {
                    reconStatus = ReconStatusEnum.FAIL.getCode();
                }
                if ("PROCESSING".equals(rlst.getRefund_status_0())) {
                    reconStatus = ReconStatusEnum.PROCESSING.getCode();
                }
                if ("CHANGE".equals(rlst.getRefund_status_0())) {
                    reconStatus = ReconStatusEnum.CHANGE.getCode();
                }
            }
            // 1.3 更新数据库。。
            ChannelCapitalRecord record = new ChannelCapitalRecord();
            record.setRecordId(Long.valueOf(refundOutId));
            record.setReconStatus(reconStatus);
            record.setRecvBank(rlst.getRefund_recv_accout_0());
            record.setUpdateTime(new Date());
            channelCapitalRecordMapper.updateByPrimaryKeySelective(record);
            // 3. 更新状态..
            withdrawInfoBO.setReconStatus(reconStatus);
            withdrawInfoBO.setRefundBank(rlst.getRefund_recv_accout_0());
            return withdrawInfoBO;
        } else {
            withdrawInfoBO.setReconStatus(ReconStatusEnum.PROCESSING.getCode());
            return withdrawInfoBO;
        }
    }

    /**
     * 获取支付宝预支付id
     * 平台交易渠道记录Id
     *
     * @param custInfoModel
     * @param needFee
     * @param chargeBody
     * @param channelType
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public Map<String, Object> getPrepayIdForZfb(CustInfoModel custInfoModel, BigDecimal needFee, String chargeBody, Integer channelType) {
        // 1.生成我们平台的交易渠道记录Id。。
        Long recordId = saveTradeInfoAndCapitialChangeRecordForRechargeByZfb(custInfoModel, needFee,
                chargeBody, channelType, ShareTradeTypeEnum.RECHARGE.getCode());
        // 2. 获取支付宝的预支付订单
        Map<String, Object> resultMap = zfbAuthHelper.getPrepayTradeNo(custInfoModel.getZfbUserId(), recordId, needFee);
        return resultMap;
    }
}
