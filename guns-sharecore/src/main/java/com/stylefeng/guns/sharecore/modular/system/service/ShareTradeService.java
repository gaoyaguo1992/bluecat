package com.stylefeng.guns.sharecore.modular.system.service;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.core.util.DecimalUtil;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.*;
import com.stylefeng.guns.sharecore.common.service.ZfbAuthHelper;
import com.stylefeng.guns.sharecore.modular.system.constants.*;
import com.stylefeng.guns.sharecore.modular.system.dao.*;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.*;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModelExample.Criteria;
import com.stylefeng.guns.sharecore.modular.system.utils.MyThreadFactory;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpRefundMsg;
import me.chanjar.weixin.mp.bean.WxMpRefundMsgResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.*;

@Service
public class ShareTradeService extends BaseService {

    /**
     * 日志处理类。。
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 提现
     */
    private static ThreadPoolExecutor exec = new ThreadPoolExecutor(5, 200, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(100), new MyThreadFactory("shareTradeForRefundService"),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    /**
     * 查询交易信息
     */
    @Autowired
    private ShareTradeInfoModelMapper shareTradeInfoModelMapper;
    /**
     * 交易时对应的设备表配置...
     */
    @Autowired
    private ShareTradeDeviceInfoModelMapper shareTradeDeviceInfoModelMapper;
    /**
     *
     */
    @Autowired
    private ShareTradeInfoModelBySelfMapper shareTradeInfoModelBySelfMapper;
    /**
     * 查询充电器对象
     */
    @Autowired
    private ShareChargerModelMapper shareChargerModelMapper;
    /**
     * 查询设备
     */
    @Autowired
    private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
    /**
     * 交易助手类
     */
    @Autowired
    private RechargeTradeInfoService rechargeTradeInfoService;

    /**
     * 提现交易表..
     */
    @Autowired
    private RefundTradeRefModelMapper refundTradeRefModelMapper;
    /**
     * 账户管理..
     */
    @Autowired
    private CustAccountService custAccountService;
    /**
     * 提现服务类..
     */
    @Autowired
    private WithdrawTradeInfoService withdrawTradeInfoService;
    /**
     * 调用微信提现接口提现..
     */
    @Autowired
    protected WxMpService wxMpService;
    /**
     * 提现交易记录处理
     */
    @Autowired
    private ChannelCapitalRecordMapper channelCapitalRecordMapper;

    /**
     * 提现对应的模板信息。。
     */
    @Value("${sharehelper.url}")
    private String rootUrl;
    /**
     * 处理信息处理类..
     */
    @Autowired
    private NotifyMessageMapper notifyMessageMapper;
    /**
     * 获到客户信息..
     */
    @Autowired
    private CustInfoModelMapper custInfoModelMapper;
    /**
     * 商户数据库操作类
     */
    @Autowired
    private MerchantInfoModelMapper merchantInfoModelMapper;
    /**
     * 账户信息。。。
     */
    @Autowired
    private CustAccountModelMapper custAccountModelMapper;
    /**
     * 交易表..
     */
    @Autowired
    private CapitalChangeRecordModelMapper capitalChangeRecordModelMapper;
    /**
     * 分润 按时间..
     */
    @Autowired
    private MerchantProfitDayModelBySelfMapper merchantProfitDayModelBySelfMapper;

    /**
     * 进程池
     */
    private static ThreadPoolExecutor threadTradeInforServer = new ThreadPoolExecutor(5, 200, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(50), new MyThreadFactory("shareTradeHelpperService"),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Autowired
    private ZfbAuthHelper zfbAuthHelper;


    /**
     * <p>
     * 得到当前用户所有正在处理的充电器
     * </p>
     *
     * @return 返回t
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public List<ShareTradeInfoModel> getUsingTradeOrderList(Long custNo, Long chargerId) {
        ShareTradeInfoModelExample example = new ShareTradeInfoModelExample();
        Criteria criteria = example.createCriteria();
        criteria.andCustIdEqualTo(custNo);
        criteria.andChargerIdEqualTo(chargerId);
        criteria.andTradeStatusEqualTo(ShareTradeStatusEnum.TradingUsing.getCode());
        example.setOrderByClause("borrow_datetime DESC");
        return shareTradeInfoModelMapper.selectByExample(example);
    }

    /**
     * <p>
     * 根据充电器id返回充电器绑定对应的设备信息及充电器信息
     * </p>
     *
     * @return 返回查询到的绑定的设备
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public ShareDeviceInfoModel getShareDeviceInfoByDeviceId(Long deviceId) {
        // 查询充电器信息..
        return shareDeviceInfoModelMapper.selectByPrimaryKey(deviceId);
    }

    /**
     * 根据交易订单及交易时的配置计算使用时间，使用费用, 及是否超期..
     *
     * @param shareTradeInfoModel
     * @return 返回的是map ..对应如下字段
     * <p>
     * resultMap.put("useAmount", useAmount);
     * resultMap.put("useTimesForSeconds", haveUsedSeconds);//使作时间
     * resultMap.put("isOverdue", isOverdue);//是否超期
     */
    public Map<String, Object> calculationUseAmountAndUseTime(ShareTradeInfoModel shareTradeInfoModel,
                                                              ShareTradeDeviceInfoModel shareTradeDeviceInfoModel) {
        // 计算费用...
        logger.info(String.format("计算使用费, tradeid:%d", shareTradeInfoModel.getId()));

        logger.info(String.format("计算使用费,shareTradeInfoModel:%s;shareTradeDeviceInfoModel:%s",
                JSON.toJSONString(shareTradeInfoModel), JSON.toJSONString(shareTradeDeviceInfoModel)));

        Map<String, Object> resultMap = new HashMap<>();
        //
        Date borrowDateTime = shareTradeInfoModel.getBorrowDatetime();
        Date currentTime = new Date();
        Long haveUsedSeconds = ((currentTime.getTime() - borrowDateTime.getTime()) / 1000);
        BigDecimal usedSeconds = new BigDecimal(haveUsedSeconds);
        // 计算使用分钟 ,向下取整
        double usedMinutes = usedSeconds.divide(new BigDecimal(60), RoundingMode.CEILING).doubleValue();
        double haveUseMinutes = 0;
        // 计算
        Boolean isOverdue = false;

        BigDecimal useAmount = new BigDecimal("0");
        if (shareTradeInfoModel.getTradeType() != null) {
            // 1小时充电
            if (ShareTradeTypeEnum.Designated_Time_1.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                haveUseMinutes = 60 - usedMinutes;
                isOverdue = usedMinutes >= 60;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_1.getCode());
            }
            // 2小时充电
            if (ShareTradeTypeEnum.Designated_Time_2.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 2;
                haveUseMinutes = 60 * 2 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_2.getCode());
            }
            // 3小时充电
            if (ShareTradeTypeEnum.Designated_Time_3.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 3;
                haveUseMinutes = 60 * 3 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_3.getCode());
            }
            // 4小时充电
            if (ShareTradeTypeEnum.Designated_Time_4.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 4;
                haveUseMinutes = 60 * 4 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_4.getCode());
            }
            // 5小时充电
            if (ShareTradeTypeEnum.Designated_Time_5.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 5;
                haveUseMinutes = 60 * 5 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_5.getCode());
            }
            // 6小时充电
            if (ShareTradeTypeEnum.Designated_Time_6.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 6;
                haveUseMinutes = 60 * 6 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_6.getCode());
            }
            // 7小时充电
            if (ShareTradeTypeEnum.Designated_Time_7.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 7;
                haveUseMinutes = 60 * 7 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_7.getCode());
            }
            // 8小时充电
            if (ShareTradeTypeEnum.Designated_Time_8.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 8;
                haveUseMinutes = 60 * 8 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_8.getCode());
            }
            // 9小时充电
            if (ShareTradeTypeEnum.Designated_Time_9.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 9;
                haveUseMinutes = 60 * 9 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_9.getCode());
            }
            // 10小时充电
            if (ShareTradeTypeEnum.Designated_Time_10.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 10;
                haveUseMinutes = 60 * 10 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_10.getCode());
            }
            // 11小时充电
            if (ShareTradeTypeEnum.Designated_Time_11.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 11;
                haveUseMinutes = 60 * 11 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_11.getCode());
            }
            // 12小时充电
            if (ShareTradeTypeEnum.Designated_Time_12.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 12;
                haveUseMinutes = 60 * 12 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_12.getCode());
            }
            // 13小时充电
            if (ShareTradeTypeEnum.Designated_Time_13.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 13;
                haveUseMinutes = 60 * 13 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_13.getCode());
            }
            // 14小时充电
            if (ShareTradeTypeEnum.Designated_Time_14.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 14;
                haveUseMinutes = 60 * 14 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_14.getCode());
            }
            // 15小时充电
            if (ShareTradeTypeEnum.Designated_Time_15.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 15;
                haveUseMinutes = 60 * 15 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_15.getCode());
            }
            // 16小时充电
            if (ShareTradeTypeEnum.Designated_Time_16.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 16;
                haveUseMinutes = 60 * 16 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_16.getCode());
            }
            // 17小时充电
            if (ShareTradeTypeEnum.Designated_Time_17.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 17;
                haveUseMinutes = 60 * 17 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_17.getCode());
            }
            // 18小时充电
            if (ShareTradeTypeEnum.Designated_Time_18.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 18;
                haveUseMinutes = 60 * 18 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_18.getCode());
            }
            // 19小时充电
            if (ShareTradeTypeEnum.Designated_Time_19.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 19;
                haveUseMinutes = 60 * 19 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_19.getCode());
            }
            // 20小时充电
            if (ShareTradeTypeEnum.Designated_Time_20.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 20;
                haveUseMinutes = 60 * 20 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_20.getCode());
            }
            // 21小时充电
            if (ShareTradeTypeEnum.Designated_Time_21.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 21;
                haveUseMinutes = 60 * 21 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_21.getCode());
            }
            // 22小时充电
            if (ShareTradeTypeEnum.Designated_Time_22.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 22;
                haveUseMinutes = 60 * 22 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_22.getCode());
            }
            // 23小时充电
            if (ShareTradeTypeEnum.Designated_Time_23.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 23;
                haveUseMinutes = 60 * 23 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_23.getCode());
            }
            // 24小时充电
            if (ShareTradeTypeEnum.Designated_Time_24.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                isOverdue = usedMinutes >= 60 * 24;
                haveUseMinutes = 60 * 24 - usedMinutes;
                useAmount = getAmountForDesignatedTime(shareTradeDeviceInfoModel,
                        ShareTradeTypeEnum.Designated_Time_24.getCode());
            }
            // 交预付预金
            if (ShareTradeTypeEnum.Prepayment.getCode() == shareTradeInfoModel.getTradeType().intValue()) {
                useAmount = getAmountForPrepayment(shareTradeDeviceInfoModel, usedMinutes);
                haveUseMinutes = 0;
                int compareTo = useAmount.compareTo(shareTradeInfoModel.getYfjAmount());
                isOverdue = compareTo > 0;
                if (isOverdue) {
                    // 计算时间
                    isOverdue = usedMinutes >= 60 * 24;
                }
            }
            // 有首付款的预付金额
            if (ShareTradeTypeEnum.PrepaymentHaveFirstAmount.getCode() == shareTradeInfoModel.getTradeType()
                    .intValue()) {
                haveUseMinutes = 0;
                useAmount = getAmountForPrepaymentFirstAmount(shareTradeDeviceInfoModel, usedMinutes);
                int compareTo = useAmount.compareTo(shareTradeInfoModel.getYfjAmount());
                isOverdue = compareTo > 0;
                if (isOverdue) {
                    // 计算时间
                    isOverdue = usedMinutes >= 60 * 24;
                }
            }
        }
        // 3. 和用户交的预付金进行比较..
        if (shareTradeInfoModel.getYfjAmount() == null) {
            useAmount = new BigDecimal("0");
        } else {
            // 计算出来的钱大于预付金。。
            if (useAmount.compareTo(shareTradeInfoModel.getYfjAmount()) > 0) {
                useAmount = shareTradeInfoModel.getYfjAmount();
            }
        }
        resultMap.put("useAmount", useAmount);
        resultMap.put("useTimesForSeconds", haveUsedSeconds);// 使作时间
        resultMap.put("isOverdue", isOverdue);// 是否超期
        resultMap.put("haveUseMinutes", haveUseMinutes);// 还能使用多长时间

        return resultMap;

    }


    /**
     * 得到所有按小时控制的费用
     *
     * @param shareDeviceInfoModel
     * @param feeTypeId
     * @return
     */
    private BigDecimal getAmountForDesignatedTime(ShareTradeDeviceInfoModel shareDeviceInfoModel, int feeTypeId) {
        if (feeTypeId == shareDeviceInfoModel.getFee1TypeId()) {
            return shareDeviceInfoModel.getFee1HourAmount();
        }
        if (feeTypeId == shareDeviceInfoModel.getFee2TypeId()) {
            return shareDeviceInfoModel.getFee2HourAmount();
        }
        if (feeTypeId == shareDeviceInfoModel.getFee3TypeId()) {
            return shareDeviceInfoModel.getFee3HourAount();
        }
        return new BigDecimal("0");
    }

    /**
     * 计算按预付金时的交易金额...
     *
     * @param shareDeviceInfoModel
     * @return
     */
    private BigDecimal getAmountForPrepayment(ShareTradeDeviceInfoModel shareDeviceInfoModel, double usedMinutes) {
        // 0.按小时计算的费用...
        BigDecimal minutesForAmount = new BigDecimal(usedMinutes);
        BigDecimal usedAmount = new BigDecimal("0");
        // 1.判断是否设置了每天最高消费
        if (shareDeviceInfoModel.getAmountMax24hour() != null) {
            Double daysForAmount = minutesForAmount.divide(new BigDecimal("1440"), RoundingMode.DOWN).doubleValue();
            BigDecimal daysForAmountBigDecimal = new BigDecimal(daysForAmount);
            minutesForAmount = minutesForAmount.remainder(new BigDecimal("1440"));
            usedAmount = daysForAmountBigDecimal.multiply(shareDeviceInfoModel.getAmountMax24hour());
        }
        if (shareDeviceInfoModel.getAmountPerHour() != null) {
            BigDecimal hourForAmount = minutesForAmount.divide(new BigDecimal("60"), RoundingMode.CEILING);
            usedAmount = usedAmount.add(hourForAmount.multiply(shareDeviceInfoModel.getAmountPerHour()));
        }
        // 2. 计算24小时的费用..
        if(shareDeviceInfoModel.getAmountMax24hour() != null
        		&&usedAmount.compareTo(shareDeviceInfoModel.getAmountMax24hour())>0){
        	usedAmount=shareDeviceInfoModel.getAmountMax24hour();
        }
        return usedAmount;
    }

    /**
     * 计算有首付款的预付金时的交易金额...
     *
     * @param shareDeviceInfoModel
     * @return
     */
    private BigDecimal getAmountForPrepaymentFirstAmount(ShareTradeDeviceInfoModel shareDeviceInfoModel,
                                                         double usedMinutes) {
        // 1.按小时计算的费用...
        BigDecimal usedMinutesForAmount = new BigDecimal(usedMinutes);
        BigDecimal usedAmount = new BigDecimal("0");
        // 1.1 计算首付款
        BigDecimal firstAmount = shareDeviceInfoModel.getFirstAmount();
        Long firstMinutes = shareDeviceInfoModel.getFirstMinutes();
        if (firstAmount == null) {
            firstAmount = new BigDecimal("0");
        }
        if (firstMinutes == null) {
            firstMinutes = new Long("0");
        }
        BigDecimal firstMinutesDe = new BigDecimal(firstMinutes);
        usedMinutesForAmount = usedMinutesForAmount.subtract(firstMinutesDe);
        usedAmount = firstAmount;
        if (usedMinutesForAmount.compareTo(new BigDecimal("0")) > 0) {
            logger.info(String.format(
                    "getAmountForPrepaymentFirstAmount --计算费用 firstMinutesDe：%.2f, usedMinutesForAmount:%.2f,usedAmount:%.2f",
                    firstMinutesDe, usedMinutesForAmount, usedAmount));
            // 2.判断是否设置了每天最高消费
            if (shareDeviceInfoModel.getAmountMax24hour() != null) {
                // 2.1计算天
                Double daysForAmount = usedMinutesForAmount.divide(new BigDecimal("1440"), RoundingMode.DOWN)
                        .doubleValue();
                BigDecimal daysForAmountBigDecimal = new BigDecimal(daysForAmount);
                usedAmount = usedAmount
                        .add(daysForAmountBigDecimal.multiply(shareDeviceInfoModel.getAmountMax24hour()));
                // 2.2 计算天外的时间
                usedMinutesForAmount = usedMinutesForAmount.remainder(new BigDecimal("1440"));
            }
            // 3.判断每小时收费金额
            if (shareDeviceInfoModel.getAmountPerHour() != null) {
                BigDecimal hourForAmount = usedMinutesForAmount.divide(new BigDecimal("60"), RoundingMode.CEILING);
                usedAmount = usedAmount.add(hourForAmount.multiply(shareDeviceInfoModel.getAmountPerHour()));
            }
        }

        // 4. 计算24小时的费用..如果计算出来的费用大于24小时，直取24小时...
        if(shareDeviceInfoModel.getAmountMax24hour() != null
        		&&usedAmount.compareTo(shareDeviceInfoModel.getAmountMax24hour())>0){
        	usedAmount=shareDeviceInfoModel.getAmountMax24hour();
        }
        logger.info(String.format(
                "getAmountForPrepaymentFirstAmount --计算费用 firstMinutesDe：%.2f, usedMinutesForAmount:%.2f,usedAmount:%.2f",
                firstMinutesDe, usedMinutesForAmount, usedAmount));
        return usedAmount;
    }

    /**
     * 订单退款...
     *
     * @param shareTradeInfoModel
     * @param shareTradeDeviceInfoModel
     * @param tradeAmountForRefund
     * @param isSendMsg
     * @param remarkForRefund
     * @return
     * @throws Exception
     */
    public FinishTradeOrderBO toRefundByTradeOrder(ShareTradeInfoModel shareTradeInfoModel,
                                                   ShareTradeDeviceInfoModel shareTradeDeviceInfoModel, BigDecimal tradeAmountForRefund, Boolean isSendMsg,
                                                   String remarkForRefund, Long userId) throws Exception {
        String msg = "";
        // 0. 配置信息 。。
        if (shareTradeDeviceInfoModel == null || shareTradeInfoModel == null) {
            throw new ExgrainBizUncheckedException("参数不正确，退款失败!");
        }
        // 1. 判断是否可以退款
        if (shareTradeInfoModel.getTradeStatus() == null
                || !shareTradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.Finish.getCode())) {
            logger.info("FinishTradeOrderBO--当前信息未结束，请结束订单在退款--{}", shareTradeInfoModel);
            throw new ExgrainBizUncheckedException("当前信息未结束，请结束订单后再退款!");
        }
        if (tradeAmountForRefund.compareTo(shareTradeInfoModel.getTradeAmount()) > 0) {
            msg = String.format("当前订单可退款金额(%.2f)小于您输入退款金额(%.2f)，请退款失败!", shareTradeInfoModel.getTradeAmount(),
                    tradeAmountForRefund);
            logger.info("FinishTradeOrderBO--当前订单可退款金额小于您输入退款金额，请退款失败" + msg);
            throw new ExgrainBizUncheckedException(msg);
        }
        // 2. 生成退款订单..
        Date now = new Date();
        tradeAmountForRefund = tradeAmountForRefund.multiply(new BigDecimal("-1"));

        ShareTradeInfoModel shareTradeInfoReback = new ShareTradeInfoModel();
        BeanUtils.copyProperties(shareTradeInfoModel, shareTradeInfoReback);

        shareTradeInfoReback.setId(seqService.getShareTradeInfoSeq());
        shareTradeInfoReback.setBackDatetime(now);
        shareTradeInfoReback.setBackTradeId(null);
        shareTradeInfoReback.setBenefitDatetime(now);
        shareTradeInfoReback.setBenefitStatus(ShareBenefitStatusEnum.UnFinishDistribution.getCode());
        shareTradeInfoReback.setBenefitStatusName(ShareBenefitStatusEnum.UnFinishDistribution.getDesc());
        shareTradeInfoReback.setBorrowDatetime(now);
        shareTradeInfoReback.setCalTradeAmount(tradeAmountForRefund);
        shareTradeInfoReback.setCreateDatetime(now);
        shareTradeInfoReback.setCreateId(userId);
        shareTradeInfoReback.setFinishFlag(ShareTradeFinishFlagEnum.SYSTEM_FORCE_FINISH.getCode());
        shareTradeInfoReback.setFinishFlagName(ShareTradeFinishFlagEnum.SYSTEM_FORCE_FINISH.getDesc());
        shareTradeInfoReback.setPlatFormRefund(0);
        shareTradeInfoReback.setRefundAmount(new BigDecimal("0"));
        shareTradeInfoReback.setRemark(remarkForRefund);
        shareTradeInfoReback.setSettleAccountsStatus(ShareSettleAccountsStatusEnum.UnFinishSettleAccount.getCode());
        shareTradeInfoReback.setTradeAmount(tradeAmountForRefund);
        shareTradeInfoReback.setTradeChannel(TradeChannelEnum.WX.getCode());
        shareTradeInfoReback.setTradeChannelName(TradeChannelEnum.WX.getDesc());
        shareTradeInfoReback.setTradeName(ShareTradeTypeEnum.Order_Refund.getDesc());
        shareTradeInfoReback.setTradeStatus(ShareTradeStatusEnum.REFUNDSUCCESS.getCode());
        shareTradeInfoReback.setTradeStatusName(ShareTradeStatusEnum.REFUNDSUCCESS.getDesc());
        shareTradeInfoReback.setTradeType(ShareTradeTypeEnum.Order_Refund.getCode());
        shareTradeInfoReback.setUpdateDatetime(now);
        shareTradeInfoReback.setUseTimeSeconds(null);
        shareTradeInfoReback.setYfjAmount(null);
        shareTradeInfoReback.setResourceTradeId(shareTradeInfoModel.getId());
        // 处理分润
        // 处理交易表中分润
        shareTradeInfoReback
                .setPlatformAmount((shareTradeDeviceInfoModel.getPlatformRato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getPlatformRato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 处理平如分润.....
        shareTradeInfoReback.setAllianceBusinessAmount(
                (shareTradeDeviceInfoModel.getAllianceBusinessRate() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAllianceBusinessRate()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 加盟商
        shareTradeInfoReback
                .setShopkeeperAmount((shareTradeDeviceInfoModel.getShopkeeperRato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getShopkeeperRato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 铺货商
        shareTradeInfoReback
                .setAgents3Amount((shareTradeDeviceInfoModel.getAgents3Rato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAgents3Rato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 一级
        shareTradeInfoReback
                .setAgents2Amount((shareTradeDeviceInfoModel.getAgents2Rato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAgents2Rato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 二级
        shareTradeInfoReback
                .setAgents1Amount((shareTradeDeviceInfoModel.getAgents1Rato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAgents1Rato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 三级

        // 处理关联表...
        ShareTradeDeviceInfoModel shareDeviceInfoModelReback = new ShareTradeDeviceInfoModel();
        BeanUtils.copyProperties(shareTradeDeviceInfoModel, shareDeviceInfoModelReback);
        shareDeviceInfoModelReback.setTradeId(shareTradeInfoReback.getId());

        // 4. 用户退款金额转到客户账号中
        CustInfoModel custInfoModel = custInfoModelMapper
                .selectCustInfoAndCustAccountInfoByPrimaryKey(shareTradeInfoModel.getCustId());
        Long capitalChangeSeq = seqService.getCapitalChangeSeq();

        CapitalChangeRecordModel in = new CapitalChangeRecordModel();
        in.setTradeRecordId(shareTradeInfoReback.getId());
        in.setAmount(tradeAmountForRefund.multiply(new BigDecimal("-1")));
        in.setCapitalChangeId(capitalChangeSeq);
        in.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
        in.setPayerAccountId(custInfoModel.getCustAccountModel().getAccountId());
        in.setPayerCustNo(custInfoModel.getCustNo());
        in.setCreateTime(new Date());
        in.setFlowType(CapitalChangeTypeEnum.TRANSFER_IN.getCode());
        // 5. 更新当前信息
        shareTradeInfoModel.setRefundAmount(tradeAmountForRefund.multiply(new BigDecimal("-1")));
        shareTradeInfoModel.setBackTradeId(shareTradeInfoReback.getId());
        shareTradeInfoModel.setPlatFormRefund(1);
        // 6. 数据库操作。。
        // 保存订单
        toRefundByTradeOrderForSave(shareTradeInfoReback, shareDeviceInfoModelReback, shareTradeInfoModel,
                custInfoModel, in);
        // 6.1 处理退款分润
        Callable<String> callToBackByBenefit = new Callable<String>() {
            @Override
            public String call() {
                try {
                    logger.info(" 开始计算分润，对应tradeId:{}", shareTradeInfoReback.getId());
                    // 提行提现...
                    tradeBenefitByTradeId(shareTradeInfoReback.getId());
                    logger.info(" 结束计算分润，对应tradeId:{}", shareTradeInfoReback.getId());
                } catch (Exception e) {
                    logger.info(String.format(" 失败了，失败计算分润，对应tradeId:%d", shareTradeInfoReback.getId()), e);
                    e.printStackTrace();
                }
                return "";
            }
        };
        threadTradeInforServer.submit(callToBackByBenefit);
        // 6. 异步发信息..

        // 7. 返回
        // 交易订单..
        FinishTradeOrderBO result = new FinishTradeOrderBO();
        result.setShareTradeDeviceInfoModel(shareDeviceInfoModelReback);
        // 交易信息
        result.setShareTradeInfoModel(shareTradeInfoModel);
        result.setAmount(shareTradeInfoReback.getTradeAmount());
        result.setYfj(new BigDecimal("0"));
        result.setTradeId(shareTradeInfoReback.getId());
        return result;
    }

    /**
     * 高级退款...
     *
     * @param shareTradeInfoModel
     * @param shareTradeDeviceInfoModel
     * @param tradeAmountForRefund
     * @param isSendMsg
     * @param remarkForRefund
     * @return
     * @throws Exception
     */
    public FinishTradeOrderBO toAdvanceRefundByTradeOrder(ShareTradeInfoModel shareTradeInfoModel,
                                                   ShareTradeDeviceInfoModel shareTradeDeviceInfoModel, 
                                                   BigDecimal tradeAmountForRefund, Boolean isSendMsg,
                                                   String remarkForRefund, Long userId) throws Exception {
        String msg = "";
        // 0. 配置信息 。。
        if (shareTradeDeviceInfoModel == null || shareTradeInfoModel == null) {
            throw new ExgrainBizUncheckedException("参数不正确，退款失败!");
        }
        // 1. 判断是否可以退款
        if (shareTradeInfoModel.getTradeStatus() == null
                || !shareTradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.Finish.getCode())) {
            logger.info("FinishTradeOrderBO--当前信息未结束，请结束订单在退款--{}", shareTradeInfoModel);
            throw new ExgrainBizUncheckedException("当前信息未结束，请结束订单后再退款!");
        }
        if (tradeAmountForRefund.compareTo(shareTradeInfoModel.getTradeAmount()) > 0) {
            msg = String.format("当前订单可退款金额(%.2f)小于您输入退款金额(%.2f)，请退款失败!", shareTradeInfoModel.getTradeAmount(),
                    tradeAmountForRefund);
            logger.info("FinishTradeOrderBO--当前订单可退款金额小于您输入退款金额，请退款失败" + msg);
            throw new ExgrainBizUncheckedException(msg);
        }
        // 2. 生成退款订单..
        Date now = new Date();
        tradeAmountForRefund = tradeAmountForRefund.multiply(new BigDecimal("-1"));

        ShareTradeInfoModel shareTradeInfoReback = new ShareTradeInfoModel();
        BeanUtils.copyProperties(shareTradeInfoModel, shareTradeInfoReback);

        shareTradeInfoReback.setId(seqService.getAdvanceShareTradeInfoSeq());
        shareTradeInfoReback.setBackDatetime(now);
        shareTradeInfoReback.setBackTradeId(null);
        shareTradeInfoReback.setBenefitDatetime(now);
        shareTradeInfoReback.setBenefitStatus(ShareBenefitStatusEnum.UnFinishDistribution.getCode());
        shareTradeInfoReback.setBenefitStatusName(ShareBenefitStatusEnum.UnFinishDistribution.getDesc());
        shareTradeInfoReback.setBorrowDatetime(now);
        shareTradeInfoReback.setCalTradeAmount(tradeAmountForRefund);
        shareTradeInfoReback.setCreateDatetime(now);
        shareTradeInfoReback.setCreateId(userId);
        shareTradeInfoReback.setFinishFlag(ShareTradeFinishFlagEnum.SYSTEM_FORCE_FINISH.getCode());
        shareTradeInfoReback.setFinishFlagName(ShareTradeFinishFlagEnum.SYSTEM_FORCE_FINISH.getDesc());
        shareTradeInfoReback.setPlatFormRefund(0);
        shareTradeInfoReback.setRefundAmount(new BigDecimal("0"));
        shareTradeInfoReback.setRemark(remarkForRefund);
        shareTradeInfoReback.setSettleAccountsStatus(ShareSettleAccountsStatusEnum.UnFinishSettleAccount.getCode());
        shareTradeInfoReback.setTradeAmount(tradeAmountForRefund);
        shareTradeInfoReback.setTradeChannel(TradeChannelEnum.WX.getCode());
        shareTradeInfoReback.setTradeChannelName(TradeChannelEnum.WX.getDesc());
        shareTradeInfoReback.setTradeName(ShareTradeTypeEnum.Order_Refund.getDesc());
        shareTradeInfoReback.setTradeStatus(ShareTradeStatusEnum.REFUNDSUCCESS.getCode());
        shareTradeInfoReback.setTradeStatusName(ShareTradeStatusEnum.REFUNDSUCCESS.getDesc());
        shareTradeInfoReback.setTradeType(ShareTradeTypeEnum.Order_Refund.getCode());
        shareTradeInfoReback.setUpdateDatetime(now);
        shareTradeInfoReback.setUseTimeSeconds(null);
        shareTradeInfoReback.setYfjAmount(null);
        shareTradeInfoReback.setResourceTradeId(shareTradeInfoModel.getId());
        // 处理分润
        // 处理交易表中分润
        shareTradeInfoReback
                .setPlatformAmount((shareTradeDeviceInfoModel.getPlatformRato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getPlatformRato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 处理平如分润.....
        shareTradeInfoReback.setAllianceBusinessAmount(
                (shareTradeDeviceInfoModel.getAllianceBusinessRate() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAllianceBusinessRate()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 加盟商
        shareTradeInfoReback
                .setShopkeeperAmount((shareTradeDeviceInfoModel.getShopkeeperRato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getShopkeeperRato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 铺货商
        shareTradeInfoReback
                .setAgents3Amount((shareTradeDeviceInfoModel.getAgents3Rato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAgents3Rato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 一级
        shareTradeInfoReback
                .setAgents2Amount((shareTradeDeviceInfoModel.getAgents2Rato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAgents2Rato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 二级
        shareTradeInfoReback
                .setAgents1Amount((shareTradeDeviceInfoModel.getAgents1Rato() != null) ? tradeAmountForRefund
                        .multiply(shareTradeDeviceInfoModel.getAgents1Rato()).divide(new BigDecimal("100"))
                        : new BigDecimal("0"));// 三级

        // 处理关联表...
        ShareTradeDeviceInfoModel shareDeviceInfoModelReback = new ShareTradeDeviceInfoModel();
        BeanUtils.copyProperties(shareTradeDeviceInfoModel, shareDeviceInfoModelReback);
        shareDeviceInfoModelReback.setTradeId(shareTradeInfoReback.getId());

        // 4. 用户退款金额转到客户账号中
        CustInfoModel custInfoModel = custInfoModelMapper
                .selectCustInfoAndCustAccountInfoByPrimaryKey(shareTradeInfoModel.getCustId());
        Long capitalChangeSeq = seqService.getCapitalChangeSeq();

        CapitalChangeRecordModel in = new CapitalChangeRecordModel();
        in.setTradeRecordId(shareTradeInfoReback.getId());
        in.setAmount(tradeAmountForRefund.multiply(new BigDecimal("-1")));
        in.setCapitalChangeId(capitalChangeSeq);
        in.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
        in.setPayerAccountId(custInfoModel.getCustAccountModel().getAccountId());
        in.setPayerCustNo(custInfoModel.getCustNo());
        in.setCreateTime(new Date());
        in.setFlowType(CapitalChangeTypeEnum.TRANSFER_IN.getCode());
        // 5. 更新当前信息
        shareTradeInfoModel.setRefundAmount(tradeAmountForRefund.multiply(new BigDecimal("-1")));
        shareTradeInfoModel.setBackTradeId(shareTradeInfoReback.getId());
        shareTradeInfoModel.setPlatFormRefund(1);
        // 6. 数据库操作。。
        // 保存订单
        toAdvanceRefundByTradeOrderForSave(shareTradeInfoReback, shareDeviceInfoModelReback, shareTradeInfoModel, custInfoModel);
        // 6.1 处理退款分润
        Callable<String> callToBackByBenefit = new Callable<String>() {
            @Override
            public String call() {
                try {
                    logger.info(" 开始计算分润，对应tradeId:{}", shareTradeInfoReback.getId());
                    // 提行提现...
                    tradeBenefitByTradeId(shareTradeInfoReback.getId());
                    logger.info(" 结束计算分润，对应tradeId:{}", shareTradeInfoReback.getId());
                } catch (Exception e) {
                    logger.info(String.format(" 失败了，失败计算分润，对应tradeId:%d", shareTradeInfoReback.getId()), e);
                    e.printStackTrace();
                }
                return "";
            }
        };
        threadTradeInforServer.submit(callToBackByBenefit);
        // 6. 异步发信息..

        // 7. 返回
        // 交易订单..
        FinishTradeOrderBO result = new FinishTradeOrderBO();
        result.setShareTradeDeviceInfoModel(shareDeviceInfoModelReback);
        // 交易信息
        result.setShareTradeInfoModel(shareTradeInfoModel);
        result.setAmount(shareTradeInfoReback.getTradeAmount());
        result.setYfj(new BigDecimal("0"));
        result.setTradeId(shareTradeInfoReback.getId());
        return result;
    }

    /**
     * 订单退款数据库操作...
     *
     * @param shareTradeInfoReback
     * @param shareDeviceInfoModelReback
     * @param shareTradeInfoModel
     * @param custInfoModel
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
    public void toRefundByTradeOrderForSave(ShareTradeInfoModel shareTradeInfoReback,
                                            ShareTradeDeviceInfoModel shareDeviceInfoModelReback, ShareTradeInfoModel shareTradeInfoModel,
                                            CustInfoModel custInfoModel, CapitalChangeRecordModel in) throws Exception {
        // 1.保存退款订单
        shareTradeInfoModelMapper.insert(shareTradeInfoReback);
        shareTradeDeviceInfoModelMapper.insert(shareDeviceInfoModelReback);
        // 2. 订单修改..
        shareTradeInfoModelMapper.updateByPrimaryKey(shareTradeInfoModel);
        // 3. 转账保存账户流水
        capitalChangeRecordModelMapper.insert(in);
        // 3.2 处理账户转账..
        custAccountService.in(custInfoModel.getCustNo(), in);
    }

    /**
     * 订单高级退款数据库操作...
     *
     * @param shareTradeInfoReback
     * @param shareDeviceInfoModelReback
     * @param shareTradeInfoModel
     * @param custInfoModel
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
    public void toAdvanceRefundByTradeOrderForSave(ShareTradeInfoModel shareTradeInfoReback,
                                            ShareTradeDeviceInfoModel shareDeviceInfoModelReback, 
                                            ShareTradeInfoModel shareTradeInfoModel,
                                            CustInfoModel custInfoModel) throws Exception {
        // 1.保存退款订单
        shareTradeInfoModelMapper.insert(shareTradeInfoReback);
        shareTradeDeviceInfoModelMapper.insert(shareDeviceInfoModelReback);
        // 2. 订单修改..
        shareTradeInfoModelMapper.updateByPrimaryKey(shareTradeInfoModel);
    }
    
    /**
     * 根据交易id归还单个充电器处理..
     *
     * @throws Exception
     */
    public FinishTradeOrderBO toBackByTradeId(Long tradeId) throws Exception {
        logger.info(String.format(" 根据交易id归还单个充电器处理.. toBackByTradeId: %d ", tradeId));
        // 0. 获取交易相关对的项id...
        ShareTradeInfoModel shareTradeInfoModel = shareTradeInfoModelMapper.selectByPrimaryKey(tradeId);// 查询交易表..
        ShareTradeDeviceInfoModel shareTradeDeviceInfoModel = shareTradeDeviceInfoModelMapper
                .selectByPrimaryKey(tradeId); // 查询交易表对应的设备信息。。
        // 1. 判断订单是否已归还...
        if (shareTradeInfoModel == null || shareTradeInfoModel.getTradeStatus() == null
                || !(shareTradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.TradingUsing.getCode()))) {
            throw new ExgrainBizUncheckedException("订单已归还，无需重复归还!");
        }

        if (shareTradeInfoModel != null && shareTradeDeviceInfoModel != null) {

            Map<String, Object> mapForuser = calculationUseAmountAndUseTime(shareTradeInfoModel,
                    shareTradeDeviceInfoModel);
            // 1.处理结束订单逻辑
            return toBackByTradeOrder(shareTradeInfoModel, shareTradeDeviceInfoModel, mapForuser,
                    ShareTradeFinishFlagEnum.USER_FINISH, true, "用户扫码结束", null);
        }
        return new FinishTradeOrderBO();
    }

    /**
     * 结束订单...
     *
     * @param shareTradeInfoModel
     * @param shareTradeDeviceInfoModel
     * @param calUseAmountAndUseTime
     * @param isSendMsg                 是否发送信息...
     * @param calTradeAmount            系统计算出来的费用
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 6, rollbackFor = Exception.class)
    public FinishTradeOrderBO toBackByTradeOrder(ShareTradeInfoModel shareTradeInfoModel,
                                                 ShareTradeDeviceInfoModel shareTradeDeviceInfoModel, Map<String, Object> calUseAmountAndUseTime,
                                                 ShareTradeFinishFlagEnum finishFlagEnum, Boolean isSendMsg, String remarkForFinish,
                                                 BigDecimal calTradeAmount) throws Exception {
        logger.info(
                " 根据交易id归还单个充电器处理.shareTradeInfoModel. shareTradeInfoModel:{} ,shareTradeDeviceInfoModel:{},calUseAmountAndUseTime:{}",
                shareTradeInfoModel, shareTradeDeviceInfoModel, calUseAmountAndUseTime);
        FinishTradeOrderBO result = new FinishTradeOrderBO();
        // 0. 判断是否已归还了...
        if (shareTradeInfoModel.getTradeStatus() == null
                || !(shareTradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.TradingUsing.getCode()))) {
            throw new ExgrainBizUncheckedException("订单已归还，无需重复归还");
        }
        // 1. 再次判断...
        if (shareTradeInfoModel != null && shareTradeDeviceInfoModel != null) {
            Long tradeId = shareTradeInfoModel.getId();
            // 0.1
            CustInfoModel custInfoModel = custInfoModelMapper
                    .selectCustInfoAndCustAccountInfoByPrimaryKey(shareTradeInfoModel.getCustId());
            logger.info(String.format(" 根据交易id归还单个充电器处理.. toBackByTradeId : %d 计算费用", tradeId));
            // 1. 计算费用和使用时间
            Map<String, Object> mapForuser = null;
            if (calUseAmountAndUseTime == null) {
                mapForuser = calculationUseAmountAndUseTime(shareTradeInfoModel, shareTradeDeviceInfoModel);
            } else {
                mapForuser = calUseAmountAndUseTime;
            }
            Date now = new Date();
            BigDecimal useAmount = (BigDecimal) mapForuser.get("useAmount");
            Long useTimesForSeconds = (Long) mapForuser.get("useTimesForSeconds");
            if (useTimesForSeconds == null) {
                useTimesForSeconds = (now.getTime() - shareTradeInfoModel.getBorrowDatetime().getTime()) / 1000;
            }

            //转换使用时长   时分秒
            String useTimeForSecondsStr = DateUtils.secondToTime(useTimesForSeconds);
            logger.info(String.format(
                    " 根据交易id归还单个充电器处理.. toBackByTradeId 计算费用和使用时间:tradeId:%d,useAmount: %.2f,useTimesForSeconds:%d",
                    tradeId, useAmount, useTimesForSeconds));
            if (useAmount == null) {
                useAmount = new BigDecimal("0");
            }
            // 计算使用分钟
            BigDecimal yfj = shareTradeInfoModel.getYfjAmount();
            BigDecimal balance = yfj.subtract(useAmount); // 余额
            if (calTradeAmount == null) {
                calTradeAmount = useAmount;
            }
            // 2. 处理交易表
            // 2.1 处理交易相关字段
            ShareTradeInfoModel shareTradeInfoModelForUpdate = new ShareTradeInfoModel();
            shareTradeInfoModelForUpdate.setId(shareTradeInfoModel.getId());
            shareTradeInfoModelForUpdate.setTradeAmount(useAmount);
            shareTradeInfoModelForUpdate.setCalTradeAmount(calTradeAmount);
            shareTradeInfoModelForUpdate.setBackDatetime(now);
            if (finishFlagEnum == null) {
                finishFlagEnum = ShareTradeFinishFlagEnum.UNKOWN;
            }
            shareTradeInfoModelForUpdate.setFinishFlag(finishFlagEnum.getCode());
            shareTradeInfoModelForUpdate.setFinishFlagName(finishFlagEnum.getDesc());
            shareTradeInfoModelForUpdate.setTradeStatus(ShareTradeStatusEnum.Finish.getCode());
            shareTradeInfoModelForUpdate.setTradeStatusName(ShareTradeStatusEnum.Finish.getDesc());
            shareTradeInfoModelForUpdate.setRemark(remarkForFinish);
            // 2.2 处理交易表中分润
            shareTradeInfoModelForUpdate.setPlatformAmount((shareTradeDeviceInfoModel.getPlatformRato() != null)
                    ? useAmount.multiply(shareTradeDeviceInfoModel.getPlatformRato()).divide(new BigDecimal("100"))
                    : new BigDecimal("0"));// 处理平如分润.....
            shareTradeInfoModelForUpdate
                    .setAllianceBusinessAmount((shareTradeDeviceInfoModel.getAllianceBusinessRate() != null) ? useAmount
                            .multiply(shareTradeDeviceInfoModel.getAllianceBusinessRate()).divide(new BigDecimal("100"))
                            : new BigDecimal("0"));// 加盟商
            shareTradeInfoModelForUpdate.setShopkeeperAmount((shareTradeDeviceInfoModel.getShopkeeperRato() != null)
                    ? useAmount.multiply(shareTradeDeviceInfoModel.getShopkeeperRato()).divide(new BigDecimal("100"))
                    : new BigDecimal("0"));// 铺货商
            shareTradeInfoModelForUpdate.setAgents3Amount((shareTradeDeviceInfoModel.getAgents3Rato() != null)
                    ? useAmount.multiply(shareTradeDeviceInfoModel.getAgents3Rato()).divide(new BigDecimal("100"))
                    : new BigDecimal("0"));// 一级
            shareTradeInfoModelForUpdate.setAgents2Amount((shareTradeDeviceInfoModel.getAgents2Rato() != null)
                    ? useAmount.multiply(shareTradeDeviceInfoModel.getAgents2Rato()).divide(new BigDecimal("100"))
                    : new BigDecimal("0"));// 二级
            shareTradeInfoModelForUpdate.setAgents1Amount((shareTradeDeviceInfoModel.getAgents1Rato() != null)
                    ? useAmount.multiply(shareTradeDeviceInfoModel.getAgents1Rato()).divide(new BigDecimal("100"))
                    : new BigDecimal("0"));// 三级
            logger.info(String.format(" 根据交易id归还单个充电器处理.. toBackByTradeId %d:, %.2f  计算费用", tradeId, useAmount));
            // 更新交易表...
            shareTradeInfoModelMapper.updateByPrimaryKeySelective(shareTradeInfoModelForUpdate);
            // 3. 处理用户余额.
            // 3.1 退回到余额中..
            CapitalChangeRecordModel ccr = new CapitalChangeRecordModel();
            ccr.setTradeRecordId(tradeId);
            // 3.1.1 扣预付金
            ccr.setAmount(shareTradeInfoModel.getYfjAmount());
            Long capitalChangeSeq = seqService.getCapitalChangeSeq();
            ccr.setCapitalChangeId(capitalChangeSeq);
            ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
            ccr.setPayerAccountId(custInfoModel.getCustAccountModel().getAccountId());
            ccr.setPayerCustNo(custInfoModel.getCustNo());
            ccr.setCreateTime(new Date());
            ccr.setFlowType(CapitalChangeTypeEnum.UNFREEZED.getCode());
            capitalChangeRecordModelMapper.insert(ccr);

            CapitalChangeRecordModel out = new CapitalChangeRecordModel();
            out.setTradeRecordId(tradeId);
            // 3.1.2 扣预付金
            out.setAmount(useAmount);
            capitalChangeSeq = seqService.getCapitalChangeSeq();
            out.setCapitalChangeId(capitalChangeSeq);
            out.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
            out.setPayerAccountId(custInfoModel.getCustAccountModel().getAccountId());
            out.setPayerCustNo(custInfoModel.getCustNo());
            out.setCreateTime(new Date());
            out.setFlowType(CapitalChangeTypeEnum.PAY.getCode());
            capitalChangeRecordModelMapper.insert(out);
            // 处理账户信息，解冻资金，同时扣费
            custAccountService.unfreezeAndOut(custInfoModel.getCustNo(), ccr, out);

            // 3.2 如原路退回在进行提现
            CustInfoModel custInfoMode = custInfoModelMapper.selectByPrimaryKey(shareTradeInfoModel.getCustId());
            CustAccountModel custAccountModel = custAccountModelMapper.selectByCustNo(shareTradeInfoModel.getCustId(),
                    CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
            if (shareTradeDeviceInfoModel.getYfjRebackType() != null && (ShareDeviceYfjRebackTypeEnum.RebackByRoad
                    .getCode() == shareTradeDeviceInfoModel.getYfjRebackType())) {
                logger.info(String.format(" 根据交易id归还单个充电器处理.. 处理useAmount:%.2f :getYfjRebackType: %d 计算费用", useAmount,
                        shareTradeDeviceInfoModel.getYfjRebackType()));
                // 提现...
                if (custAccountModel != null && custAccountModel.getAvailableBalance() != null
                        && custAccountModel.getAvailableBalance().compareTo(new BigDecimal("0")) > -1) {

                    Callable<String> callToBackByTradeId = new Callable<String>() {
                        @Override
                        public String call() {
                            try {
                                logger.info(String.format(" 提现...custNO:%d, availableBalance:%.2f",
                                        custInfoMode.getCustNo(), custAccountModel.getAvailableBalance()));
                                // 提行提现...
                                refund(custInfoMode, custAccountModel.getAvailableBalance(),
                                        CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
                            } catch (WxErrorException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return "";
                        }
                    };
                    threadTradeInforServer.submit(callToBackByTradeId);
                }
                logger.info(String.format(" 根据交易id归还单个充电器处理.. 提现结束%d :getYfjRebackType: %d 计算费用",
                        custInfoMode.getCustNo(), shareTradeDeviceInfoModel.getYfjRebackType()));
            }
            // 3.3 发送消息
            if (isSendMsg) {
                // 发送信息..
                logger.info(String.format(" 根据交易id归还单个充电器处理.. 提现结束:tradeId: %d 发送消息isSendMsg:%s",
                        shareTradeDeviceInfoModel.getTradeId(), isSendMsg.toString()));
                NotifyMessageModel message = new NotifyMessageModel();
                message.setId(seqService.getNotifyMessageSeq());
                message.setType(NotifyMessageTypeEnum.BAK_MESSAGE.getCode());
                message.setStatus(NotifyMessageStatusEnum.UNREAD_MESSAGE.getCode());
                message.setReceiveCustNo(custInfoMode.getCustNo());
                message.setTradeId(tradeId);
                message.setCreateTime(new Date());
                message.setTitle("归还充电器通知");
                message.setContent(String.format("充电器成功归还啦！使用时长%s，共消费：%.2f元，未消费完的资金将会退回到账户余额。", useTimeForSecondsStr, useAmount));
                notifyMessageMapper.insertSelective(message);
            }
            // 4. 进行商户分润..
            Callable<String> callToBackByBenefit = new Callable<String>() {
                @Override
                public String call() {
                    try {
                        logger.info(" 开始计算分润，对应tradeId:{}", shareTradeInfoModelForUpdate.getId());
                        // 提行提现...
                        tradeBenefitByTradeId(shareTradeInfoModelForUpdate.getId());
                        logger.info(" 结束计算分润，对应tradeId:{}", shareTradeInfoModelForUpdate.getId());
                    } catch (Exception e) {
                        logger.info(String.format(" 失败了，失败计算分润，对应tradeId:%d", shareTradeInfoModelForUpdate.getId()), e);
                        e.printStackTrace();
                    }
                    return "";
                }
            };
            threadTradeInforServer.submit(callToBackByBenefit);
            // 5.重新得到返回数据
            shareTradeInfoModel = shareTradeInfoModelMapper.selectByPrimaryKey(tradeId);// 查询交易表..
            shareTradeDeviceInfoModel = shareTradeDeviceInfoModelMapper.selectByPrimaryKey(tradeId); // 查询交易表对应的设备信息
            // 交易订单..
            result.setShareTradeDeviceInfoModel(shareTradeDeviceInfoModel);
            // 交易信息
            result.setShareTradeInfoModel(shareTradeInfoModel);
            result.setAmount(useAmount);
            result.setYfj(yfj);
            result.setBalance(balance);
            result.setHaveUseTimesForSecond(useTimesForSeconds);
            result.setTradeId(tradeId);
        }
        return result;
    }

    /**
     * 交易分润...
     *
     * @param tradeId
     * @throws Exception
     */
    public void tradeBenefitByTradeId(Long tradeId) throws Exception {
        logger.info("tradeBenefitByTradeId---交易分润...tradeId:{}", tradeId);
        // 1. 查询交易是否存在..
        ShareTradeInfoModel tradeInfoModel = shareTradeInfoModelMapper.selectByPrimaryKey(tradeId);
        ShareTradeDeviceInfoModel deviceInfoModel = shareTradeDeviceInfoModelMapper.selectByPrimaryKey(tradeId);
        // 2.判断是否可以进行分润
        if (tradeInfoModel == null || deviceInfoModel == null) {
            throw new ExgrainBizUncheckedException("交易订单不存在..");
        }
        if (tradeInfoModel.getBenefitStatus() != null
                && !tradeInfoModel.getBenefitStatus().equals(ShareBenefitStatusEnum.UnFinishDistribution.getCode())) {
            throw new ExgrainBizUncheckedException("交易已分润无需重复分润");
        }
        ShareChargerModel chargerMode = shareChargerModelMapper.selectByPrimaryKey(tradeInfoModel.getChargerId());
        // 已结束的订单才可以进行分润...
        if (tradeInfoModel.getTradeStatus() == null
                || !(tradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.REFUNDSUCCESS.getCode())
                || tradeInfoModel.getTradeStatus().equals(ShareTradeStatusEnum.Finish.getCode()))) {
            throw new ExgrainBizUncheckedException("订单未结束，不可以进行分润..");
        }
        // 3.调分润的方法并开启事务，进行分润运算..
        List<MerchantProfitDayModel> listProfitDay = new ArrayList<>();
        List<CustAccountModel> listAccountModel = new ArrayList<>();
        Date now = tradeInfoModel.getBackDatetime();
        if (now == null) {
            now = new Date();
        }
        // 3.1顶级代理商
        if (tradeInfoModel.getAgents1Id() != null) {
            generalProfiteData(chargerMode, tradeInfoModel.getAgents1Amount(), listProfitDay, listAccountModel,
                    tradeInfoModel.getAgents1Id(), now, tradeInfoModel.getId());
        }
        // 3.2一级代理商
        if (tradeInfoModel.getAgents2Id() != null) {
            generalProfiteData(chargerMode, tradeInfoModel.getAgents2Amount(), listProfitDay, listAccountModel,
                    tradeInfoModel.getAgents2Id(), now, tradeInfoModel.getId());
        }
        // 3.3二级代理商
        if (tradeInfoModel.getAgents3Id() != null) {
            generalProfiteData(chargerMode, tradeInfoModel.getAgents3Amount(), listProfitDay, listAccountModel,
                    tradeInfoModel.getAgents3Id(), now, tradeInfoModel.getId());
        }
        // 3.4铺货商
        if (tradeInfoModel.getShopkeeperId() != null) {
            generalProfiteData(chargerMode, tradeInfoModel.getShopkeeperAmount(), listProfitDay, listAccountModel,
                    tradeInfoModel.getShopkeeperId(), now, tradeInfoModel.getId());
        }
        // 3.5加盟商
        if (tradeInfoModel.getAllianceBusinessId() != null) {
            generalProfiteData(chargerMode, tradeInfoModel.getAllianceBusinessAmount(), listProfitDay, listAccountModel,
                    tradeInfoModel.getAllianceBusinessId(), now, tradeInfoModel.getId());
        }
        // 保存数据...
        logger.info("tradeBenefitByTradeId---交易分润.saveTradeBenefitWithTradescational..tradeId:{},listProfitDay{}",
                tradeId, listProfitDay);
        saveTradeBenefitWithTradescational(listProfitDay, listAccountModel, tradeInfoModel, deviceInfoModel);
    }

    /**
     * 整理分润数据...
     *
     * @param chargerMode
     * @param listProfitDay
     * @param listAccountModel
     * @param merchantId
     * @param checkDate
     */
    private void generalProfiteData(ShareChargerModel chargerMode, BigDecimal profitMoney,
                                    List<MerchantProfitDayModel> listProfitDay, List<CustAccountModel> listAccountModel, Long merchantId,
                                    Date checkDate, Long batchNo) {
        MerchantProfitDayModel merchantProfitDayModel;
        CustInfoModel custInfoModel;
        MerchantInfoModel merchantInfoModel;
        String checkDateStr = DateUtils.formatDate(checkDate, "yyyy-MM-dd");
        Date checkDateForDay = DateUtil.parse(checkDateStr, "yyyy-MM-dd");
        String checkDateForMonth = DateUtils.formatDate(checkDate, "yyyy-MM");
        String checkDateForYear = DateUtils.formatDate(checkDate, "yyyy");
        Long seqId = null;
        // 1.商户是否正确
        merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(merchantId);
        if (merchantInfoModel == null) {
            return;
        }
        // 2.商户是否绑定了对应的客户...
        custInfoModel = custInfoModelMapper.selectMerchantCustInfoByMerchantId(merchantId);
        if (custInfoModel == null) {
            return;
        }
        // 3. 商户是否创建了余额账户
        CustAccountModel accountModel = custAccountModelMapper.selectByCustNoAndAccountType(custInfoModel.getCustNo(),
                CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
        if (accountModel == null) {
            return;
        }
        //3.1 判断 是否重复计算.
        for (MerchantProfitDayModel tmpModel : listProfitDay) {
            //重复不要在次计算...
            if (tmpModel != null && tmpModel.getBatchNo() != null && tmpModel.getBatchNo().equals(batchNo)
                    && tmpModel.getMerchantId() != null && tmpModel.getMerchantId().equals(merchantId)) {
                return;
            }
        }

        // 4.整理日
        merchantProfitDayModel = new MerchantProfitDayModel();
        seqId = seqService.getMerchantProfitDaySeq();
        merchantProfitDayModel.setId(seqId);
        merchantProfitDayModel.setMerchantId(merchantId);
        merchantProfitDayModel.setChargerType(
                chargerMode.getChargerTypeId() == null ? 0 : chargerMode.getChargerTypeId().byteValue());
        // 日
        merchantProfitDayModel.setCheckDate(checkDateForDay);
        // 月
        merchantProfitDayModel.setCheckMonth(checkDateForMonth);
        // 年
        merchantProfitDayModel.setCheckYear(checkDateForYear);
        merchantProfitDayModel.setCreateTime(new Date());
        merchantProfitDayModel.setDoneAmount(profitMoney);
        merchantProfitDayModel.setMerchantId(merchantInfoModel.getId());
        merchantProfitDayModel.setMerchantName(merchantInfoModel.getName());
        merchantProfitDayModel.setMerchantType(String.format("%d", merchantInfoModel.getMerchantType()));
        merchantProfitDayModel.setMerchantTypeCn(merchantInfoModel.getMerchantTypeCn());
        merchantProfitDayModel.setRatioId(0L);
        merchantProfitDayModel.setSettleFeeType((byte) SettlementTypeEnum.REALTIMESETTLEMENT.getCode());
        merchantProfitDayModel.setSettleFeeTypeCn(SettlementTypeEnum.REALTIMESETTLEMENT.getDesc());
        merchantProfitDayModel.setSettlementCount(new Integer(1));
        merchantProfitDayModel.setStatus((byte) 1);
        merchantProfitDayModel.setTotalAmount(profitMoney);
        merchantProfitDayModel.setUnAmount(new BigDecimal("0"));
        merchantProfitDayModel.setUpdateTime(new Date());
        merchantProfitDayModel.setBatchNo(batchNo);
        listProfitDay.add(merchantProfitDayModel);
        // 金额
        CustAccountModel accountModelParam = new CustAccountModel();
        accountModelParam.setCustNo(accountModel.getCustNo());
        accountModelParam.setAccountId(accountModel.getAccountId());
        accountModelParam.setAvailableBalance(profitMoney);
        listAccountModel.add(accountModelParam);
    }

    /**
     * 开启分润事务..
     *
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 6, rollbackFor = Exception.class)
    public void saveTradeBenefitWithTradescational(List<MerchantProfitDayModel> listProfitDay,
                                                   List<CustAccountModel> listAccountModel, ShareTradeInfoModel tradeInfoModel,
                                                   ShareTradeDeviceInfoModel deviceInfoModel) throws Exception {

        Date now = new Date();
        logger.info("saveTradeBenefitWithTradescational.listProfitDay:{},listAccountModel:{}", listProfitDay,
                listAccountModel);
        if (listAccountModel.size() > 0) {
            // 1. 保存list
            merchantProfitDayModelBySelfMapper.insertBatch(listProfitDay);
            // 2. 转账..
            CapitalChangeRecordModel ccr = null;
            Long capitalChangeSeq;
            for (CustAccountModel custAccountModel : listAccountModel) {
                ccr = new CapitalChangeRecordModel();
                ccr.setTradeRecordId(tradeInfoModel.getId());
                ccr.setAmount(custAccountModel.getAvailableBalance());
                capitalChangeSeq = seqService.getCapitalChangeSeq();
                ccr.setCapitalChangeId(capitalChangeSeq);
                ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
                ccr.setPayerAccountId(custAccountModel.getAccountId());
                ccr.setPayerCustNo(custAccountModel.getCustNo());
                ccr.setCreateTime(now);
                ccr.setFlowType(CapitalChangeTypeEnum.UNFREEZED.getCode());
                capitalChangeRecordModelMapper.insert(ccr);
                custAccountService.in(custAccountModel.getCustNo(), ccr);
            }
        }
        // 3.修改交易表..
        ShareTradeInfoModel tmpModel = shareTradeInfoModelBySelfMapper
                .getShareTradeInfoModelByIdForUpdate(tradeInfoModel.getId());
        if (tmpModel == null || (tmpModel.getBenefitStatus() != null
                && tmpModel.getBenefitStatus().intValue() == ShareBenefitStatusEnum.FinishDistribution.getCode())) {
            logger.info(String.format("saveTradeBenefitWithTradescational当前交易(%d)已分润，无需重复分润!", tmpModel.getId()));
            throw new ExgrainBizUncheckedException(String.format("当前交易(%d)已分润，无需重复分润!", tmpModel.getId()));
        }
        ShareTradeInfoModel tradeInfoModelForUpdate = new ShareTradeInfoModel();
        tradeInfoModelForUpdate.setId(tradeInfoModel.getId());
        tradeInfoModelForUpdate.setBenefitDatetime(now);
        tradeInfoModelForUpdate.setBenefitStatus(ShareBenefitStatusEnum.FinishDistribution.getCode());
        tradeInfoModelForUpdate.setBenefitStatusName(ShareBenefitStatusEnum.FinishDistribution.getDesc());
        tradeInfoModelForUpdate.setUpdateDatetime(now);
        shareTradeInfoModelMapper.updateByPrimaryKeySelective(tradeInfoModelForUpdate);
    }

    /**
     * 处理用户提现的所有相关业务逻辑。。
     *
     * @param custInfoModel
     * @param refundAmount
     * @param channelType
     * @return
     * @throws Exception
     * @throws WxErrorException
     */
    public String refundAdnSendMsg(CustInfoModel custInfoModel, BigDecimal refundAmount, int channelType)
            throws WxErrorException, Exception {
        // 0 提现处理
        Map<RefundBO, WxMpRefundMsgResult> result = refund(custInfoModel, refundAmount, channelType);
        try {
            // 1.得现完。。
            logger.info(String.format("处理用户提现的所有相关业务逻辑。。,refundAdnSendMsg:refundAmount-%.2f, custInfoModel:%s",
                    refundAmount, custInfoModel != null ? JSON.toJSONString(custInfoModel) : ""));
            CapitalAccountInfoBO cai = new CapitalAccountInfoBO();
            cai.setResult("fail");
            cai.setOpenId(custInfoModel.getOpenId());
            cai.setNickName(custInfoModel.getNickName());
            cai.setHeadImgUrl(custInfoModel.getHeadImgUrl());
            cai.setBalance(custInfoModel.getCustAccountModel().getBalance().toString());
            cai.setAvailableBalance(custInfoModel.getCustAccountModel().getAvailableBalance().toString());
            cai.setBalance(custInfoModel.getCustAccountModel().getBalance().toString());
            cai.setAvailableBalance(custInfoModel.getCustAccountModel().getAvailableBalance().toString());
            Long refundId = null;
            if (result != null && result.size() > 0) {
                BigDecimal refundedAmt = BigDecimal.ZERO;
                for (WxMpRefundMsgResult wrm : result.values()) {
                    if (InvokeResult.SUCCESS.equals(wrm.getResult_code())) {
                        refundedAmt = refundedAmt
                                .add(new BigDecimal(wrm.getRefund_fee()).divide(new BigDecimal("100")));
                    }
                    refundId = result.keySet().iterator().next().getRefundId();
                }
                // 2. 发送提现的系统消息..
                sendWXWithdrawMsg(cai, custInfoModel.getOpenId(), refundAmount, custInfoModel, refundId, refundedAmt);
            } else {
                logger.info("处理用户提现的所有相关业务逻辑 失败");
                cai.setResult("fail");
                cai.setMsg("提现失败,请联系客服!");
            }
            return cai.getResult();

        } catch (Exception e) {
            logger.info("提现发送令版失败.-refundAdnSendMsg=.", e);
            // TODO: handle exception
            return ResultCommandBaseObject.FAILED;
        }
    }

    /**
     * 处理用户提现的所有相关业务逻辑。。
     *
     * @param custInfoModel
     * @param refundAmount
     * @param channelType
     * @return
     * @throws Exception
     * @throws WxErrorException
     */
    public String refundAdnSendMsgByZfb(CustInfoModel custInfoModel, BigDecimal refundAmount, int channelType)
            throws WxErrorException, Exception {
        // 0 提现处理
        Map<RefundBO, Boolean> result = refundByZfb(custInfoModel, refundAmount, channelType);
        logger.info("支付宝refundAdnSendMsgByZfb提现结果：result:{}", result);
        try {
            // 1.得现完。。
            logger.info(String.format("处理用户提现的所有相关业务逻辑。。,refundAdnSendMsg:refundAmount-%.2f, custInfoModel:%s",
                    refundAmount, custInfoModel != null ? JSON.toJSONString(custInfoModel) : ""));
            CapitalAccountInfoBO cai = new CapitalAccountInfoBO();
            cai.setResult("fail");
            cai.setOpenId(custInfoModel.getZfbUserId());
            cai.setNickName(custInfoModel.getNickName());
            cai.setHeadImgUrl(custInfoModel.getHeadImgUrl());
            cai.setBalance(custInfoModel.getCustAccountModel().getBalance().toString());
            cai.setAvailableBalance(custInfoModel.getCustAccountModel().getAvailableBalance().toString());
            cai.setBalance(custInfoModel.getCustAccountModel().getBalance().toString());
            cai.setAvailableBalance(custInfoModel.getCustAccountModel().getAvailableBalance().toString());
            Long refundId = null;
            if (result != null && result.size() > 0) {
                BigDecimal refundedAmt = BigDecimal.ZERO;
                for (Boolean isSuccess : result.values()) {
                    if (isSuccess) {
                        refundedAmt = refundedAmt
                                .add(result.keySet().iterator().next().getRefundAmt());
                    }
                    refundId = result.keySet().iterator().next().getRefundId();
                }
                // 2. 发送提现的系统消息..
                sendWXWithdrawMsg(cai, custInfoModel.getOpenId(), refundAmount, custInfoModel, refundId, refundedAmt);
            } else {
                logger.warn("处理用户支付宝提现的所有相关业务逻辑 失败");
                cai.setResult("fail");
                cai.setMsg("提现失败,请联系客服!");
            }
            return cai.getResult();

        } catch (Exception e) {
            logger.info("提现发送令版失败.-refundAdnSendMsg=.", e);
            return ResultCommandBaseObject.FAILED;
        }
    }

    /**
     * 发送信息。。
     *
     * @param cai
     * @param openId
     * @param withdrawAmount
     * @param custInfoModel
     * @param refundId
     * @param refundedAmt
     */
    private void sendWXWithdrawMsg(final CapitalAccountInfoBO cai, final String openId, final BigDecimal withdrawAmount,
                                   final CustInfoModel custInfoModel, Long refundId, BigDecimal refundedAmt) {

        String wxAppRemark = "";// 消息备注
        if (refundedAmt.compareTo(withdrawAmount) == 0) {
            wxAppRemark = "您的提现申请已成功！" + "\r\n交易标识号为：【" + refundId + "】,\r\n提现金额:【" + withdrawAmount
                    + "元】。\r\n提现金额将按您的充值路径退回，请关注您的充值账号，也可进入个人中心查看相关信息。\r\n【银行卡提现预计到账时间为1到5个工作日】";
        } else if (refundedAmt.compareTo(BigDecimal.ZERO) != 0) {
            wxAppRemark = "你好，提现部分申请成功!";

        } else {
            wxAppRemark = "提现失败,请联系客服!";
        }
        // 插入消息==============================
        NotifyMessageModel message = new NotifyMessageModel();
        message.setId(seqService.getNotifyMessageSeq());
        message.setType(NotifyMessageTypeEnum.WITHDRAW_MESSAGE.getCode());
        message.setStatus(NotifyMessageStatusEnum.UNREAD_MESSAGE.getCode());
        message.setReceiveCustNo(custInfoModel.getCustNo());
        message.setTradeId(refundId);
        message.setCreateTime(new Date());
        message.setTitle("余额提现申请");
        message.setContent(wxAppRemark);
        notifyMessageMapper.insertSelective(message);
        // 插入消息==============================
    }

    /**
     * 处理用户发起提现的逻辑处理...
     *
     * @param custInfoModel
     * @param refundAmount
     * @param channelType
     * @return
     * @throws WxErrorException
     * @throws Exception
     */
    public Map<RefundBO, WxMpRefundMsgResult> refund(CustInfoModel custInfoModel, BigDecimal refundAmount,
                                                     int channelType) throws WxErrorException, Exception {
        try {
            logger.info("客户{}开始退款，金额为:{}", custInfoModel.getCustTsfAmtAccountModel().getAccountId(), refundAmount);
            // 1 生成提现记录..
            List<RefundBO> refundTrades = saveTradeInfoAndCapitialChangeRecordForRefund(custInfoModel, refundAmount,
                    channelType, ShareTradeTypeEnum.WITHRAW.getDesc());
            if (null == refundTrades || refundTrades.size() == 0) {
                return null;
            }
            // 2. 根据生成的提现记录一条一条退款..
            StringBuffer refundRlst = new StringBuffer();
            Map<RefundBO, WxMpRefundMsgResult> refundResults = new HashMap<RefundBO, WxMpRefundMsgResult>();
            WxMpRefundMsg wmr = null;
            WxMpRefundMsgResult result = null;
            // 3.1 循环退款
            for (RefundBO refundBO : refundTrades) {
                wmr = new WxMpRefundMsg();
                wmr.setOut_refund_no(refundBO.getTradeOutNo().toString());
                wmr.setOut_trade_no(refundBO.getOriginalTradeOutNo().toString());
                wmr.setRefund_fee(refundBO.getRefundAmt().toString());
                wmr.setTotal_fee(refundBO.getTotalAmt().toString());
                // notify_url

                try {
                    Map<String, String> refundMap = wmr.toMap(wmr);
                    String notify_url = String.format("%s/wxapp/callBackForRefund", rootUrl);
                    refundMap.put("notify_url", notify_url);
                    result = wxMpService.payRefund(refundMap);
                    logger.info("微信退款结果部分-------result:{}", result);
                    if ("ORDERNOTEXIST".equals(result.getErr_code())) {
                        wmr.setOut_trade_no("CCC" + wmr.getOut_trade_no());
                        refundMap = wmr.toMap(wmr);
                        refundMap.put("notify_url", notify_url);
                        result = wxMpService.payRefund(refundMap);
                    }
                    if ("NOTENOUGH".equals(result.getErr_code())) {
                        wmr.setRefund_account("REFUND_SOURCE_RECHARGE_FUNDS");
                        refundMap = wmr.toMap(wmr);
                        refundMap.put("notify_url", notify_url);
                        result = wxMpService.payRefund(refundMap);
                    }
                } catch (WxErrorException e) {
                    logger.info(String.format("微信退款结果部分失败Out_trade_no:%d,Out_trade_no:%d,Refund_fee:%.2f",
                            refundBO.getTradeOutNo(), refundBO.getOriginalTradeOutNo(), refundBO.getRefundAmt()), e);
                    result = new WxMpRefundMsgResult();
                    result.setReturn_code(InvokeResult.UNKOWN);
                    result.setReturn_msg(e.getMessage());
                    result.setResult_code(InvokeResult.UNKOWN);
                    result.setErr_code_des(e.getMessage());
                    result.setErr_code(InvokeResult.UNKOWN);
                } catch (Exception e) {
                    logger.info(String.format("微信退款结果部分失败Out_trade_no:%d,Out_trade_no:%d,Refund_fee:%.2f",
                            refundBO.getTradeOutNo(), refundBO.getOriginalTradeOutNo(), refundBO.getRefundAmt()), e);
                    result = new WxMpRefundMsgResult();
                    result.setReturn_code(InvokeResult.UNKOWN);
                    result.setReturn_msg(e.getMessage());
                    result.setResult_code(InvokeResult.UNKOWN);
                    result.setErr_code_des(e.getMessage());
                    result.setErr_code(InvokeResult.UNKOWN);
                }
                refundResults.put(refundBO, result);
                if (InvokeResult.FAIL.equals(result.getResult_code())) {
                    refundRlst.append(result.getErr_code() + "" + result.getErr_code_des() + "\r\n");
                }
                logger.info("微信退款结果" + result + "Out_trade_no:" + refundBO.getTradeOutNo().toString() + "Out_trade_no:"
                        + refundBO.getOriginalTradeOutNo().toString());
            }
            // 4. 处理提现后的结果
            try {
                logger.info("微信退款结果 成功，refundResults.size():{},refundResults:{}", refundResults.size(), refundResults);
                handleRefundResult(refundResults);
            } catch (Exception e) {
                // TODO: handle exception
                logger.info("微信退款结果 失败 处理提现后的结果 {}", refundResults, e);
            }
            return refundResults;

        } catch (Exception e) {
            // TODO: handle exception
            logger.info("微信退款结果 失败 处理提现后的结果 ----------refund------", e);
            throw e;
        }
    }

    /**
     * 处理支付业务小程序用户发起提现的逻辑处理...
     *
     * @param custInfoModel
     * @param refundAmount
     * @param channelType
     * @throws Exception
     */
    public Map<RefundBO, Boolean> refundByZfb(CustInfoModel custInfoModel, BigDecimal refundAmount,
                                              int channelType) throws Exception {
        try {
            logger.info("支付宝小程序客户{}开始退款，金额为:{}", custInfoModel.getCustTsfAmtAccountModel().getAccountId(), refundAmount);
            // 1 生成提现记录..
            List<RefundBO> refundTrades = saveTradeInfoAndCapitialChangeRecordForRefundByZfb(custInfoModel, refundAmount,
                    channelType, ShareTradeTypeEnum.WITHRAW.getDesc());
            if (null == refundTrades || refundTrades.size() == 0) {
                return null;
            }
            // 2. 根据生成的提现记录一条一条退款..
            Map<RefundBO, Boolean> refundResults = new HashMap<RefundBO, Boolean>();
            boolean result = false;
            // 3.1 循环退款
            for (RefundBO refundBO : refundTrades) {
                try {
                    result = zfbAuthHelper.alipayRefund(refundBO.getOriginalTradeOutNo(), refundBO.getRefundAmt(), refundBO.getTradeOutNo());
                    //充值总金额refundBO.getTotalAmt().toString();
                } catch (Exception e) {
                    logger.error(String.format("支付宝小程序退款异常Out_trade_no:%d,OriginalTradeOutNo:%d,Refund_fee:%.2f",
                            refundBO.getTradeOutNo(), refundBO.getOriginalTradeOutNo(), refundBO.getRefundAmt()), e);
                }
                refundResults.put(refundBO, result);
                logger.info("支付宝小程序退款结果" + result + "Out_trade_no:" + refundBO.getTradeOutNo().toString() + "OriginalTradeOutNo:"
                        + refundBO.getOriginalTradeOutNo().toString() + "Refund_fee:" + refundBO.getRefundAmt());
            }
            // 4. 处理提现后的结果
            try {
                logger.info("支付宝小程序退款成功，refundResults.size():{},refundResults:{}", refundResults.size(), refundResults);
                handleRefundResultByZfb(refundResults);
            } catch (Exception e) {
                logger.error("支付宝小程序退款失败 处理提现后的结果 {}", refundResults, e);
            }
            return refundResults;

        } catch (Exception e) {
            logger.error("支付宝小程序退款失败 处理提现后的结果 ", e);
            throw e;
        }
    }

    /**
     * 处理提现后的返回结果，并对期进行处理...
     *
     * @param refundResults
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 5, rollbackFor = Exception.class)
    public boolean handleRefundResult(Map<RefundBO, WxMpRefundMsgResult> refundResults) throws Exception {
        boolean handleResult = false;
        boolean isAllRefund = true;
        Long tradeRefundId = null;
        ChannelCapitalRecord chcr = null;
        WithdrawCapitalChangeRecordModel ccr = null;
        RechargeTradeInfoModel orgti = null;
        // 1. 循环处理每一条交易明细
        for (RefundBO refundBO : refundResults.keySet()) {
            WxMpRefundMsgResult result = refundResults.get(refundBO);
            if (result == null || result.getResult_code().equals(InvokeResult.FAIL)
                    || result.getResult_code().equals(InvokeResult.UNKOWN)) {
                isAllRefund = false;
            }
            tradeRefundId = refundBO.getRefundId();
            chcr = channelCapitalRecordMapper.selectByPrimaryKeyForUpdate(refundBO.getTradeOutNo());
            if (ChannelCapitalStatusEnum.PROCESSING.getCode() != chcr.getStatus()) {
                continue;
            }
            ccr = withdrawTradeInfoService.getCapitalChangeRecordById(chcr.getCapitalChangeId());
            // 2. 提现状态处理
            if (InvokeResult.FAIL.equals(result.getResult_code())) {
                chcr.setStatus(ChannelCapitalStatusEnum.FAILURE.getCode());
                chcr.setResultDesc(result.getErr_code_des());
                chcr.setResultCode(result.getErr_code());
                chcr.setUpdateTime(new Date());
                channelCapitalRecordMapper.updateByPrimaryKeySelective(chcr);
                // 2.1 解冻资金
                ccr.setUpdateDate(new Date());
                ccr.setCapitalStatus(CapitalStatusEnum.FAILURE.getCode());
                ccr.setReturnMsg(result.getErr_code_des());
                ccr.setReturnCode(result.getErr_code());
                custAccountService.withdrawUnfreeze(refundBO.getCustNo(), ccr);
                // 2.2 处理交易...
                orgti = rechargeTradeInfoService.getTradeInfoById(refundBO.getTradeId());
                if (orgti != null) {
                    logger.info("orgti:" + orgti);
                    orgti.setUpdateTime(new Date());
                    orgti.setRefundAmount(orgti.getRefundAmount().subtract(refundBO.getRefundAmt()));
                    rechargeTradeInfoService.updateTradeInfoByPrimaryKeySelective(orgti);
                }
                withdrawTradeInfoService.updateCapitialChangeRecord(ccr, chcr);
            } else if (InvokeResult.SUCCESS.equals(result.getResult_code())) {
                // 2.1 修改状态
                chcr.setStatus(ChannelCapitalStatusEnum.SUCCESS.getCode());
                chcr.setResultDesc(result.getErr_code_des());
                chcr.setResultCode(result.getErr_code());
                chcr.setMchId(result.getMch_id());
                // chcr.setChannelType(result.getRefund_channel());
                chcr.setTransactionId(result.getTransaction_id());
                chcr.setUpdateTime(new Date());

                ccr.setUpdateDate(new Date());
                ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
                ccr.setReturnMsg(result.getErr_code_des());
                ccr.setReturnCode(result.getErr_code());
                withdrawTradeInfoService.updateCapitialChangeRecord(ccr, chcr);
                // 2.2 处理解冻资金
                custAccountService.withdrawUnFreezeAndOut(refundBO.getCustNo(), ccr);
            } else if (InvokeResult.UNKOWN.equals(result.getResult_code())) {
                // 2.1 修改状态
                chcr.setResultDesc(result.getErr_code_des());
                chcr.setResultCode(result.getErr_code());
                ccr.setCapitalStatus(CapitalStatusEnum.UNKNOW.getCode());
                chcr.setUpdateTime(new Date());
                ccr.setUpdateDate(new Date());
                ccr.setReturnMsg(result.getErr_code_des());
                ccr.setReturnCode(result.getErr_code());
                ccr.setCapitalStatus(CapitalStatusEnum.UNKNOW.getCode());
                withdrawTradeInfoService.updateCapitialChangeRecord(ccr, chcr);
                // 2.2 解冻资金
                custAccountService.withdrawUnfreeze(refundBO.getCustNo(), ccr);
            }
        }
        // 处理是否全部成功...
        if (isAllRefund) {
            WithdrawTradeInfoModel ti = withdrawTradeInfoService.getTradeInfoById(tradeRefundId);
            ti.setStatus(TradeStatusEnum.SUCCESS.getCode());
            ti.setStatusCn(TradeStatusEnum.SUCCESS.getDesc());
            ti.setUpdateTime(new Date());
            withdrawTradeInfoService.updateTradeInfo(ti);
            handleResult = true;
        }

        return handleResult;
    }

    /**
     * 处理提现后的返回结果，并对期进行处理...支付宝小程序
     *
     * @param refundResults
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 5, rollbackFor = Exception.class)
    public boolean handleRefundResultByZfb(Map<RefundBO, Boolean> refundResults) throws Exception {
        boolean handleResult = false;
        boolean isAllRefund = true;
        Long tradeRefundId = null;
        ChannelCapitalRecord chcr = null;
        WithdrawCapitalChangeRecordModel ccr = null;
        RechargeTradeInfoModel orgti = null;
        // 1. 循环处理每一条交易明细
        for (RefundBO refundBO : refundResults.keySet()) {
            boolean result = refundResults.get(refundBO);
            if (!result) {
                isAllRefund = false; //只要有一个退款失败，视为不全部成功
            }
            tradeRefundId = refundBO.getRefundId();
            chcr = channelCapitalRecordMapper.selectByPrimaryKeyForUpdate(refundBO.getTradeOutNo());
            if (ChannelCapitalStatusEnum.PROCESSING.getCode() != chcr.getStatus()) {
                continue;
            }
            ccr = withdrawTradeInfoService.getCapitalChangeRecordById(chcr.getCapitalChangeId());
            // 2. 提现状态处理
            if (!result) {
                chcr.setStatus(ChannelCapitalStatusEnum.FAILURE.getCode());
                chcr.setUpdateTime(new Date());
                channelCapitalRecordMapper.updateByPrimaryKeySelective(chcr);
                // 2.1 解冻资金
                ccr.setUpdateDate(new Date());
                ccr.setCapitalStatus(CapitalStatusEnum.FAILURE.getCode());
                custAccountService.withdrawUnfreeze(refundBO.getCustNo(), ccr);
                // 2.2 处理交易...
                orgti = rechargeTradeInfoService.getTradeInfoById(refundBO.getTradeId());
                if (orgti != null) {
                    logger.info("orgti:" + orgti);
                    orgti.setUpdateTime(new Date());
                    orgti.setRefundAmount(orgti.getRefundAmount().subtract(refundBO.getRefundAmt()));
                    rechargeTradeInfoService.updateTradeInfoByPrimaryKeySelective(orgti);
                }
                withdrawTradeInfoService.updateCapitialChangeRecord(ccr, chcr);
            } else {
                // 2.1 修改状态
                chcr.setStatus(ChannelCapitalStatusEnum.SUCCESS.getCode());
//                chcr.setMchId(result.getMch_id());
                // chcr.setChannelType(result.getRefund_channel());
//                chcr.setTransactionId(result.getTransaction_id());
                chcr.setUpdateTime(new Date());

                ccr.setUpdateDate(new Date());
                ccr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
                withdrawTradeInfoService.updateCapitialChangeRecord(ccr, chcr);
                // 2.2 处理解冻资金
                custAccountService.withdrawUnFreezeAndOut(refundBO.getCustNo(), ccr);
            }
        }
        // 处理是否全部成功...
        if (isAllRefund) {
            WithdrawTradeInfoModel ti = withdrawTradeInfoService.getTradeInfoById(tradeRefundId);
            ti.setStatus(TradeStatusEnum.SUCCESS.getCode());
            ti.setStatusCn(TradeStatusEnum.SUCCESS.getDesc());
            ti.setUpdateTime(new Date());
            withdrawTradeInfoService.updateTradeInfo(ti);
            handleResult = true;
        }

        return handleResult;
    }

    /**
     * 得到可提现的记录...
     *
     * @param cusNo
     * @return
     */
    public List<RefundBO> getRefundBOByCustNo(Long cusNo) {
        List<RefundBO> tis = new ArrayList<>();
        // 1.处理可提现记录..
        try {
            rechargeTradeInfoService.insertRefundRecordsIfHaveNotRefundByCustNo(cusNo);
        } catch (Exception e) {
            logger.error("保存异常:", e);
        }
        // 2.获取提现交易..
        // get can refund trades
        tis = rechargeTradeInfoService.getRefundAmts(cusNo);
        return tis;
    }

    /**
     * 保存得现订单...
     *
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
    public List<RefundBO> saveTradeInfoAndCapitialChangeRecordForRefund(CustInfoModel payerCustInfo,
                                                                        BigDecimal refundAmount, int channelType, String tradeName) throws Exception {
        logger.info(String.format(
                "--saveTradeInfoAndCapitialChangeRecordForRefund 没有可以退款的交易,或者已经全部退完!,refundAmount:%.2f,channelType:%d,tradeName:%s,payerCustInfo.getCustNo:%d",
                refundAmount, channelType, tradeName, payerCustInfo.getCustNo()));
        // 1、得到所有当前客户可退款的交易列表
        List<RefundBO> tis = getRefundBOByCustNo(payerCustInfo.getCustNo());
        if (tis == null || tis.size() == 0) {
            logger.warn("没有可以退款的交易,或者已经全部退完!");
            return null;
        }
        // 2.判断是否有足过的退记录.
        BigDecimal canRefundAmt = BigDecimal.ZERO;
        for (RefundBO ti : tis) {
            canRefundAmt = canRefundAmt
                    .add(ti.getRemainRefundAmt() == null ? BigDecimal.ZERO : ti.getRemainRefundAmt());
        }

        if (canRefundAmt.subtract(refundAmount).compareTo(BigDecimal.ZERO) == -1) {
            logger.error("【" + payerCustInfo.getCustNo() + "】可退款的金额不够！");
            return null;
        }
        // 3. 统计所有可以提现的记录..
        Map<Long, RefundBO> needToLockTrades = new HashMap<Long, RefundBO>();
        // 当前已经统计的所有交易能退的钱
        BigDecimal refundAmtOfTrades = BigDecimal.ZERO;
        for (RefundBO ti : tis) {
            // 除了已经统计的交易能退钱外,还要退的钱
            BigDecimal needMoreMoney = refundAmount.subtract(refundAmtOfTrades);
            refundAmtOfTrades = refundAmtOfTrades.add(ti.getRemainRefundAmt());
            RefundBO refundBO = new RefundBO();
            refundBO.setTradeOutNo(ti.getTradeOutNo());
            refundBO.setChannelType(ti.getChannelType());
            refundBO.setTradeId(ti.getTradeId());
            needToLockTrades.put(ti.getTradeId(), refundBO);
            if (refundAmtOfTrades.compareTo(refundAmount) == -1) {
                refundBO.setRefundAmt(ti.getRemainRefundAmt());
            } else {
                if (refundAmtOfTrades.subtract(refundAmount).equals(BigDecimal.ZERO)) {
                    refundBO.setRefundAmt(ti.getRemainRefundAmt());
                    break;
                } else {
                    refundBO.setRefundAmt(needMoreMoney);
                    break;
                }
            }
        }
        // 3.1 生成提现交易订单..
        Long refundId = seqService.getWithdrawTradeRecordSeq();
        WithdrawTradeInfoModel refundInfo = new WithdrawTradeInfoModel();
        refundInfo.setTradeId(refundId);
        refundInfo.setStatus(TradeStatusEnum.PROCESSING.getCode());
        refundInfo.setStatusCn(TradeStatusEnum.PROCESSING.getDesc());
        refundInfo.setCheckDate(com.stylefeng.guns.core.util.DateUtils.getCheckDate());
        refundInfo.setCustNo(payerCustInfo.getCustNo());
        refundInfo.setTradeName(tradeName);
        refundInfo.setTradeAmount(DecimalUtil.format(refundAmount));
        refundInfo.setCreateTime(new Date());
        refundInfo.setTradeType(ShareTradeTypeEnum.WITHRAW.getCode());
        List<RechargeTradeInfoModel> tisupdate = rechargeTradeInfoService
                .refundTradesByRefundAmtForUpdate(needToLockTrades.keySet());
        List<RefundBO> result = new ArrayList<RefundBO>();
        // 3.2 生成交易明细..
        RefundBO refundInfoBO = null;
        WithdrawCapitalChangeRecordModel ccr = null;
        ChannelCapitalRecord chcr = null;
        Long ccId = 0L, cccSeq = 0L;
        RefundBO refundBO = null;
        RefundTradeRefModel rtr = null;
        for (RechargeTradeInfoModel ti : tisupdate) {
            refundInfoBO = needToLockTrades.get(ti.getTradeId());
            if (ti.getRefundAmount().add(refundInfoBO.getRefundAmt()).compareTo(ti.getTradeAmount()) == 1) {
                throw new ExgrainBizUncheckedException("退款的金额不够或者重复退款!");
            }
            ti.setRefundAmount(ti.getRefundAmount().add(refundInfoBO.getRefundAmt()));
            ti.setUpdateTime(new Date());
            // 3.2.1 修改充值订单表中的 可提现金额。。
            rechargeTradeInfoService.updateTradeInfoByPrimaryKeySelective(ti);

            // 3.2.2 处理资金解冻的问题.. 生成提现流水订单
            ccr = new WithdrawCapitalChangeRecordModel();
            ccr.setTradeRecordId(refundId);
            ccr.setAmount(refundInfoBO.getRefundAmt());
            ccId = seqService.getWithdrawCapitalChangeRecordSeq();
            ccr.setCapitalChangeId(ccId);
            ccr.setCapitalStatus(CapitalStatusEnum.PROCESSING.getCode());
            ccr.setPayerAccountId(payerCustInfo.getCustAccountModel().getAccountId());
            ccr.setPayerCustNo(payerCustInfo.getCustNo());
            ccr.setCreateTime(new Date());
            ccr.setFlowType(CapitalChangeTypeEnum.WITHDRAW.getCode());
            // 3.2.3 生成账户流水订单
            chcr = new ChannelCapitalRecord();
            chcr.setStatus(ChannelCapitalStatusEnum.PROCESSING.getCode());
            chcr.setCreateTime(new Date());
            chcr.setChannelType(channelType);
            chcr.setFeeType(FeeUnitType.CNY.getCode());
            chcr.setOpenId(payerCustInfo.getOpenId());
            cccSeq = seqService.getChannelCapitalChangeSeq();
            chcr.setOutTradeNo(cccSeq);
            chcr.setRecordId(cccSeq);
            chcr.setTradeRecordId(ccr.getTradeRecordId());
            chcr.setTotalFee(refundInfoBO.getRefundAmt());
            chcr.setCapitalChangeId(ccId);
            chcr.setCustNo(ti.getCustNo());
            chcr.setTradeType(String.format("%d", ShareTradeTypeEnum.Order_Refund.getCode()));
            // 3.2.3 提现明细..
            refundBO = new RefundBO();
            refundBO.setTotalAmt(ti.getTradeAmount());
            refundBO.setRefundCapitalChangeIds(ccId);
            refundBO.setTradeOutNo(cccSeq);
            refundBO.setRefundAmt(refundInfoBO.getRefundAmt());
            refundBO.setCustNo(payerCustInfo.getCustNo());
            refundBO.setChannelType(refundInfoBO.getChannelType());
            refundBO.setOriginalTradeOutNo(refundInfoBO.getTradeOutNo());
            refundBO.setRefundId(refundId);
            refundBO.setTradeId(refundInfoBO.getTradeId());
            result.add(refundBO);
            //
            rtr = new RefundTradeRefModel();
            rtr.setCreatedDate(new Date());
            rtr.setRefundTradeOutNo(cccSeq);
            rtr.setRechargeTradeOutNo(refundInfoBO.getTradeOutNo());
            rtr.setRefundAmount(refundInfoBO.getRefundAmt());
            rtr.setRefundId(refundId);
            rtr.setTradeId(ti.getTradeId());
            rtr.setCustNo(payerCustInfo.getCustNo());
            // 3.2.4 提现明细订单
            refundTradeRefModelMapper.insert(rtr);
            // 3.2.5 解冻用户资金
            custAccountService.withdrawFreeze(payerCustInfo, ccr);
            // 3.2.6 保存提现流程...
            withdrawTradeInfoService.saveCapitialChangeRecord(ccr, chcr);
        }
        // 4.保存提现订单..
        withdrawTradeInfoService.saveTradeInfo(refundInfo);
        return result;
    }


    /**
     * 保存得现订单...支付宝
     *
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
    public List<RefundBO> saveTradeInfoAndCapitialChangeRecordForRefundByZfb(CustInfoModel payerCustInfo,
                                                                             BigDecimal refundAmount, int channelType, String tradeName) throws Exception {
        logger.info(String.format(
                "--saveTradeInfoAndCapitialChangeRecordForRefund 没有可以退款的交易,或者已经全部退完!,refundAmount:%.2f,channelType:%d,tradeName:%s,payerCustInfo.getCustNo:%d",
                refundAmount, channelType, tradeName, payerCustInfo.getCustNo()));
        // 1、得到所有当前客户可退款的交易列表
        List<RefundBO> tis = getRefundBOByCustNo(payerCustInfo.getCustNo());
        if (tis == null || tis.size() == 0) {
            logger.warn("没有可以退款的交易,或者已经全部退完!");
            return null;
        }
        // 2.判断是否有足过的退记录.
        BigDecimal canRefundAmt = BigDecimal.ZERO;
        for (RefundBO ti : tis) {
            canRefundAmt = canRefundAmt
                    .add(ti.getRemainRefundAmt() == null ? BigDecimal.ZERO : ti.getRemainRefundAmt());
        }

        if (canRefundAmt.subtract(refundAmount).compareTo(BigDecimal.ZERO) == -1) {
            logger.error("【" + payerCustInfo.getCustNo() + "】可退款的金额不够！");
            return null;
        }
        // 3. 统计所有可以提现的记录..
        Map<Long, RefundBO> needToLockTrades = new HashMap<Long, RefundBO>();
        // 当前已经统计的所有交易能退的钱
        BigDecimal refundAmtOfTrades = BigDecimal.ZERO;
        for (RefundBO ti : tis) {
            // 除了已经统计的交易能退钱外,还要退的钱
            BigDecimal needMoreMoney = refundAmount.subtract(refundAmtOfTrades);
            refundAmtOfTrades = refundAmtOfTrades.add(ti.getRemainRefundAmt());
            RefundBO refundBO = new RefundBO();
            refundBO.setTradeOutNo(ti.getTradeOutNo());
            refundBO.setChannelType(ti.getChannelType());
            refundBO.setTradeId(ti.getTradeId());
            needToLockTrades.put(ti.getTradeId(), refundBO);
            if (refundAmtOfTrades.compareTo(refundAmount) == -1) {
                refundBO.setRefundAmt(ti.getRemainRefundAmt());
            } else {
                if (refundAmtOfTrades.subtract(refundAmount).equals(BigDecimal.ZERO)) {
                    refundBO.setRefundAmt(ti.getRemainRefundAmt());
                    break;
                } else {
                    refundBO.setRefundAmt(needMoreMoney);
                    break;
                }
            }
        }
        // 3.1 生成提现交易订单..
        Long refundId = seqService.getWithdrawTradeRecordSeq();
        WithdrawTradeInfoModel refundInfo = new WithdrawTradeInfoModel();
        refundInfo.setTradeId(refundId);
        refundInfo.setStatus(TradeStatusEnum.PROCESSING.getCode());
        refundInfo.setStatusCn(TradeStatusEnum.PROCESSING.getDesc());
        refundInfo.setCheckDate(com.stylefeng.guns.core.util.DateUtils.getCheckDate());
        refundInfo.setCustNo(payerCustInfo.getCustNo());
        refundInfo.setTradeName(tradeName);
        refundInfo.setTradeAmount(DecimalUtil.format(refundAmount));
        refundInfo.setCreateTime(new Date());
        refundInfo.setTradeType(ShareTradeTypeEnum.WITHRAW.getCode());
        List<RechargeTradeInfoModel> tisupdate = rechargeTradeInfoService
                .refundTradesByRefundAmtForUpdate(needToLockTrades.keySet());
        List<RefundBO> result = new ArrayList<RefundBO>();
        // 3.2 生成交易明细..
        RefundBO refundInfoBO = null;
        WithdrawCapitalChangeRecordModel ccr = null;
        ChannelCapitalRecord chcr = null;
        Long ccId = 0L, cccSeq = 0L;
        RefundBO refundBO = null;
        RefundTradeRefModel rtr = null;
        for (RechargeTradeInfoModel ti : tisupdate) {
            refundInfoBO = needToLockTrades.get(ti.getTradeId());
            if (ti.getRefundAmount().add(refundInfoBO.getRefundAmt()).compareTo(ti.getTradeAmount()) == 1) {
                throw new ExgrainBizUncheckedException("退款的金额不够或者重复退款!");
            }
            ti.setRefundAmount(ti.getRefundAmount().add(refundInfoBO.getRefundAmt()));
            ti.setUpdateTime(new Date());
            // 3.2.1 修改充值订单表中的 可提现金额。。
            rechargeTradeInfoService.updateTradeInfoByPrimaryKeySelective(ti);

            // 3.2.2 处理资金解冻的问题.. 生成提现流水订单
            ccr = new WithdrawCapitalChangeRecordModel();
            ccr.setTradeRecordId(refundId);
            ccr.setAmount(refundInfoBO.getRefundAmt());
            ccId = seqService.getWithdrawCapitalChangeRecordSeq();
            ccr.setCapitalChangeId(ccId);
            ccr.setCapitalStatus(CapitalStatusEnum.PROCESSING.getCode());
            ccr.setPayerAccountId(payerCustInfo.getCustAccountModel().getAccountId());
            ccr.setPayerCustNo(payerCustInfo.getCustNo());
            ccr.setCreateTime(new Date());
            ccr.setFlowType(CapitalChangeTypeEnum.WITHDRAW.getCode());
            // 3.2.3 生成账户流水订单
            chcr = new ChannelCapitalRecord();
            chcr.setStatus(ChannelCapitalStatusEnum.PROCESSING.getCode());
            chcr.setCreateTime(new Date());
            chcr.setChannelType(channelType);
            chcr.setFeeType(FeeUnitType.CNY.getCode());
            chcr.setOpenId(payerCustInfo.getOpenId());
            cccSeq = seqService.getChannelCapitalChangeSeq();
            chcr.setOutTradeNo(refundInfoBO.getTradeOutNo());
            chcr.setRecordId(cccSeq);
            chcr.setTradeRecordId(ccr.getTradeRecordId());
            chcr.setTotalFee(refundInfoBO.getRefundAmt());
            chcr.setCapitalChangeId(ccId);
            chcr.setCustNo(ti.getCustNo());
            chcr.setTradeType(String.format("%d", ShareTradeTypeEnum.Order_Refund.getCode()));
            // 3.2.3 提现明细..
            refundBO = new RefundBO();
            refundBO.setTotalAmt(ti.getTradeAmount());
            refundBO.setRefundCapitalChangeIds(ccId);
            refundBO.setTradeOutNo(cccSeq);
            refundBO.setRefundAmt(refundInfoBO.getRefundAmt());
            refundBO.setCustNo(payerCustInfo.getCustNo());
            refundBO.setChannelType(refundInfoBO.getChannelType());
            refundBO.setOriginalTradeOutNo(refundInfoBO.getTradeOutNo());
            refundBO.setRefundId(refundId);
            refundBO.setTradeId(refundInfoBO.getTradeId());
            result.add(refundBO);
            //
            rtr = new RefundTradeRefModel();
            rtr.setCreatedDate(new Date());
            rtr.setRefundTradeOutNo(cccSeq);
            rtr.setRechargeTradeOutNo(refundInfoBO.getTradeOutNo());
            rtr.setRefundAmount(refundInfoBO.getRefundAmt());
            rtr.setRefundId(refundId);
            rtr.setTradeId(ti.getTradeId());
            rtr.setCustNo(payerCustInfo.getCustNo());
            // 3.2.4 提现明细订单
            refundTradeRefModelMapper.insert(rtr);
            // 3.2.5 解冻用户资金
            custAccountService.withdrawFreeze(payerCustInfo, ccr);
            // 3.2.6 保存提现流程...
            withdrawTradeInfoService.saveCapitialChangeRecord(ccr, chcr);
        }
        // 4.保存提现订单..
        withdrawTradeInfoService.saveTradeInfo(refundInfo);
        return result;
    }

    /**
     * 处理微信提现...即退款..
     *
     * @param withdrawAmount
     * @param custInfoModel
     * @param channelType
     * @return
     */
    public Object doWeiXinAppWithraw(final String withdrawAmount, final CustInfoModel custInfoModel, int channelType) {
        logger.info("处理微信提现...即退款..doWeiXinAppWithraw " + custInfoModel);
        // 1. 初始提现明细.及变量.
        final CapitalAccountInfoBO cai = new CapitalAccountInfoBO();
        cai.setResult("fail");
        cai.setOpenId(custInfoModel.getOpenId());
        cai.setNickName(custInfoModel.getNickName());
        cai.setHeadImgUrl(custInfoModel.getHeadImgUrl());
        cai.setBalance(custInfoModel.getCustAccountModel().getBalance().toString());
        cai.setAvailableBalance(custInfoModel.getCustAccountModel().getAvailableBalance().toString());
        BigDecimal deWithdrawAmount = new BigDecimal(withdrawAmount);
        // 2. 提现账户..
        if (custInfoModel.getCustAccountModel().getAvailableBalance().compareTo(new BigDecimal(withdrawAmount)) == -1) {
            cai.setMsg("提现余额不足!");
            return cai;
        } else {
            // 3. 判断是否可以提现，有足过的退款记录...
            // 3.1.获取提现交易..
            // get can refund trades
            List<RefundBO> tis = getRefundBOByCustNo(custInfoModel.getCustNo());
            if (tis == null || tis.size() == 0) {
                logger.warn("处理微信提现...即退款--- 没有可以退款的交易,或者已经全部退完!");
                cai.setMsg("没有足过可退款的交易订单，请确认是否已经提交了退款申请！");
                cai.setResult("fail");
                return cai;
            }
            // 3.2. 计算提现金额。。
            BigDecimal canRefundAmt = BigDecimal.ZERO;
            for (RefundBO ti : tis) {
                canRefundAmt = canRefundAmt
                        .add(ti.getRemainRefundAmt() == null ? BigDecimal.ZERO : ti.getRemainRefundAmt());
            }
            if (canRefundAmt.subtract(deWithdrawAmount).compareTo(BigDecimal.ZERO) == -1) {
                String msg = String.format("您【%d】的充值订单中的可退款的金额不足！", custInfoModel.getCustNo());
                logger.error(msg);
                cai.setMsg(msg);
                cai.setResult("fail");
                return cai;
            }
            // 3.3 处理提现
            cai.setResult("success");
            // 1. 处理提现逻辑
            Callable<String> call = new Callable<String>() {
                public String call() {
                    try {
                        // 1. 提现..并发送信息,,
                        return refundAdnSendMsg(custInfoModel, deWithdrawAmount,
                                ChannelTypeEnum.WEIXIN_APP_WITHDRAW.getCode());
                    } catch (Exception ee) {
                        logger.error("提现失败", ee);
                        return "";
                    }
                }
            };
            // 2. 异步提现处理...
            try {
                Future<String> task = exec.submit(call);
                logger.info("处理微信提现...即退款.. active count:{},TaskCount:{},CompletedTaskCount:{}", exec.getActiveCount(),
                        exec.getTaskCount(), exec.getCompletedTaskCount());
                String rslt = null;
                try {
                    rslt = task.get(8, TimeUnit.SECONDS);
                } catch (Exception e) {
                    logger.error("sumbitTask error", e);
                }
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("提现发信息...sumbitTask", e);
                // return "";
            }
            cai.setMsg("提现申请成功!");
        }
        return cai;
    }

    /**
     * 处理支付宝小程序提现，支付宝退款
     *
     * @param withdrawAmount 提现金额
     * @param custInfoModel  客户账户信息
     * @return
     */
    public Object doZfbAppWithraw(final String withdrawAmount, final CustInfoModel custInfoModel) {
        logger.info("处理支付宝小程序提现方法doZfbAppWithraw入参：withdrawAmount:{},custInfoModel:{} ,channelType:{}", withdrawAmount, custInfoModel);
        // 1. 初始提现明细.及变量.
        final CapitalAccountInfoBO cai = new CapitalAccountInfoBO();
        cai.setResult("fail");
        cai.setOpenId(custInfoModel.getZfbUserId());
        cai.setNickName(custInfoModel.getNickName());
        cai.setHeadImgUrl(custInfoModel.getHeadImgUrl());
        cai.setBalance(custInfoModel.getCustAccountModel().getBalance().toString());
        cai.setAvailableBalance(custInfoModel.getCustAccountModel().getAvailableBalance().toString());
        BigDecimal deWithdrawAmount = new BigDecimal(withdrawAmount);
        // 2. 提现账户..
        if (custInfoModel.getCustAccountModel().getAvailableBalance().compareTo(deWithdrawAmount) == -1) {
            logger.warn("支付宝提现余额不足!");
            cai.setMsg("提现余额不足!");
            return cai;
        }
        // 3. 判断是否可以提现，有足过的退款记录...
        // 3.1.获取提现交易..
        // get can refund trades
        List<RefundBO> tis = getRefundBOByCustNo(custInfoModel.getCustNo());
        if (tis == null || tis.size() == 0) {
            logger.warn("处理支付宝小程序提现...即退款--- 没有可以退款的交易,或者已经全部退完!");
            cai.setMsg("没有足够可退款的交易订单，请确认是否已经提交了退款申请！");
            cai.setResult("fail");
            return cai;
        }
        logger.info("准备提现的记录List<RefundBO>：{}", tis);
        // 3.2. 计算提现金额。。
        BigDecimal canRefundAmt = BigDecimal.ZERO;
        for (RefundBO ti : tis) {
            canRefundAmt = canRefundAmt
                    .add(ti.getRemainRefundAmt() == null ? BigDecimal.ZERO : ti.getRemainRefundAmt());
        }
        if (canRefundAmt.subtract(deWithdrawAmount).compareTo(BigDecimal.ZERO) == -1) {
            String msg = String.format("您【%d】的充值订单中的可退款的金额不足！", custInfoModel.getCustNo());
            logger.warn(msg);
            cai.setMsg(msg);
            cai.setResult("fail");
            return cai;
        }
        // 3.3 处理提现
        cai.setResult("success");
        // 1. 处理提现逻辑
        Callable<String> call = new Callable<String>() {
            public String call() {
                try {
                    // 1. 提现..并发送信息,,b
                    return refundAdnSendMsgByZfb(custInfoModel, deWithdrawAmount,
                            ChannelTypeEnum.ZFB_APP_WITHDRAW.getCode());
                } catch (Exception ee) {
                    logger.error("提现失败", ee);
                    return "";
                }
            }
        };
        // 2. 异步提现处理...
        try {
            Future<String> task = exec.submit(call);
            logger.info("处理支付宝提现...即退款.. active count:{},TaskCount:{},CompletedTaskCount:{}", exec.getActiveCount(),
                    exec.getTaskCount(), exec.getCompletedTaskCount());
            String rslt = null;
            try {
                rslt = task.get(8, TimeUnit.SECONDS);
            } catch (Exception e) {
                logger.error("sumbitTask error", e);
            }
        } catch (Exception e) {
            logger.error("提现发信息...sumbitTask", e);
            // return "";
        }
        cai.setMsg("提现申请成功!");
        return cai;
    }

}