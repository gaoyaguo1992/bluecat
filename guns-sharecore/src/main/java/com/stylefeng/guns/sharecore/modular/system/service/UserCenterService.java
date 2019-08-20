package com.stylefeng.guns.sharecore.modular.system.service;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.core.util.DatetimeUtil;
import com.stylefeng.guns.sharecore.common.persistence.dao.NotifyMessageBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.*;
import com.stylefeng.guns.sharecore.common.service.ZfbAuthHelper;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ChannelCapitalRecordMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.CustAccountModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.*;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpRefundQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserCenterService {
    /**
     * 处理日志
     */
    private static final Logger logger = LoggerFactory.getLogger(UserCenterService.class);
    /**
     * 信息数据库操作类..
     */
    @Autowired
    private NotifyMessageBySelfMapper notifyMessageBySelfMapper;
    /**
     * 得到交易订单数..
     */
    @Autowired
    private ShareTradeInfoModelBySelfMapper shareTradeInfoModelBySelfMapper;

    @Autowired
    private CustAccountModelMapper custAccountModelMapper;
    /**
     * 交易服务类..
     */
    @Autowired
    private ShareTradeService shareTradeService;
    /**
     * 渠道提现...
     */
    @Autowired
    private ChannelCapitalRecordMapper channelCapitalRecordMapper;

    /**
     * 处理微信相关服务...
     */
    @Autowired
    protected WxMpService wxMpService;
    /**
     * 接口...
     */
    @Autowired
    protected WxAppApiService wxAppApiService;

    @Autowired
    private ZfbAuthHelper zfbAuthHelper;

    /**
     * 根据客户no得到客户个人中心需要的数据..
     *
     * @param custNo
     * @return
     */
    public Map<String, Object> getUserCenterInfo(Long custNo) {
        Map<String, Object> map = new HashMap<>();
        /**
         * 1.得到账户余额
         */
        CustAccountModel accountModel = custAccountModelMapper.selectByCustNo(custNo,
                CustAccountTypeEnum.RECHARGERACCOUNT.getCode());

        map.put("availableBalance", accountModel.getAvailableBalance());
        map.put("frozenBalance", accountModel.getFrozenBalance());
        // 得到订单数..
        /**
         * 2.得到订单数量..
         */
        Long orderCount = shareTradeInfoModelBySelfMapper.selectUnfinishedOrderCountByCustNo(custNo,
                ShareTradeStatusEnum.TradingUsing.getCode());
        map.put("orderCount", orderCount);
        return map;
    }

    /**
     * <p>
     * 修改消息状态
     * </p>
     *
     * @param id
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public Map<String, Long> getMyMessageCoutInfo(Long custNo, String id) {
        if (!StringUtils.isEmpty(id)) {
            NotifyMessage record = new NotifyMessage();
            record.setId(Long.valueOf(id));
            record.setStatus(1);
            notifyMessageBySelfMapper.updateByPrimaryKeySelective(record);
        }

        Map<String, Long> map = new HashMap<>();
        Long read = notifyMessageBySelfMapper.countMessageByStatus(custNo,
                MessageStatusTypeEnum.READ_MESSAGE.getCode());
        Long unRead = notifyMessageBySelfMapper.countMessageByStatus(custNo,
                MessageStatusTypeEnum.UNREAD_MESSAGE.getCode());
        map.put("read", read);
        map.put("unRead", unRead);
        return map;
    }

    /**
     * 得到账
     *
     * @param custInfoModel
     * @param code
     * @param start
     * @param count
     * @return
     */
    public ResultCommandBaseObject<Object> getMyMessage(CustInfoModel custInfoModel, Integer code, Integer start,
                                                        Integer count) {
        logger.info("custInfoModel:{},start:{},count:{}", custInfoModel, start, count);
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        if (custInfoModel == null) {
            logger.warn("获取不到您的账号信息！");
            throw new ExgrainBizUncheckedException("获取不到您的账号信息");
        }
        List<NotifyMessage> myMessages = notifyMessageBySelfMapper.listByCustNoAndPage(custInfoModel.getCustNo(), code,
                start, count);

        result.setResult(ResultCommand.SUCCESS);
        result.setResponseInfo(myMessages);
        return result;
    }

    /**
     * 读系统消息
     *
     * @param custNo
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public Map<String, Long> doReadMessage(Long custNo, String id) {
        if (!StringUtils.isEmpty(id)) {
            NotifyMessage record = new NotifyMessage();
            record.setId(Long.valueOf(id));
            record.setStatus(1);
            notifyMessageBySelfMapper.updateByPrimaryKeySelective(record);
        }
        Map<String, Long> map = new HashMap<>();
        Long read = notifyMessageBySelfMapper.countMessageByStatus(custNo,
                NotifyMessageStatusEnum.READ_MESSAGE.getCode());
        Long unRead = notifyMessageBySelfMapper.countMessageByStatus(custNo,
                NotifyMessageStatusEnum.UNREAD_MESSAGE.getCode());
        map.put("read", read);
        map.put("unRead", unRead);
        return map;
    }

    /**
     * <p>
     * 组装查询条件
     * </p>
     *
     * @param myOrderPagesQueryBO
     * @return
     */
    private HashMap<String, Object> myOrderDataPack(MyOrderPagesQueryBO myOrderPagesQueryBO) {

        // TODO 后续有其他限定条件的话请在paramMap中添加
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        List<Integer> tradeStatus = new ArrayList<Integer>();
        if (myOrderPagesQueryBO.getStatus() == null) {
            // 处理中
            tradeStatus.add(ShareTradeStatusEnum.TradingUsing.getCode());
            // 成功
            tradeStatus.add(ShareTradeStatusEnum.Finish.getCode());
        } else {
            // 根据用户选择的处理...
            tradeStatus.add(myOrderPagesQueryBO.getStatus());
        }
        // 将交易状态放入map中
        paramMap.put("tradeStatus", tradeStatus);
        //
        paramMap.put("status", myOrderPagesQueryBO.getStatus());

        /***********************************
         * 其他条件限定 开始
         **************************************/
        // 1.3将客户号放入map中
        paramMap.put("custNo", myOrderPagesQueryBO.getCustNo());

        // 1.4将分页起始结束位置放入map中
        paramMap.put("start", myOrderPagesQueryBO.getStart());
        paramMap.put("rows", myOrderPagesQueryBO.getRows());

        // 设置默认查询时间
        paramMap.put("beginDateTime", DatetimeUtil.yearMonthDaysAgo(90));
        paramMap.put("endDateTime", DatetimeUtil.todayYyyyMMdd());
        /***********************************
         * 其他条件限定 结束
         **************************************/

        return paramMap;
    }

    /**
     * <p>
     * 得到我的订单
     * </p>
     *
     * @param myOrderPagesQueryBO
     * @return
     */
    public MyOrderRecordPageInfoBO getMyOrdersWithPages(MyOrderPagesQueryBO myOrderPagesQueryBO) {
        MyOrderRecordPageInfoBO myOrderRecordPageInfoBO = new MyOrderRecordPageInfoBO();
        try {
            // 组装查询条件
            HashMap<String, Object> queryContent = myOrderDataPack(myOrderPagesQueryBO);
            List<ShareTradeInfoBySelfModel> myOrders = shareTradeInfoModelBySelfMapper
                    .selectOrdersForPageByFilter(queryContent);
            List<MyOrderRecordInfoBO> myOrderRecordInfoBOs = new ArrayList<MyOrderRecordInfoBO>();
            MyOrderRecordInfoBO myOrderRecordInfoBO = null;
            if (myOrders != null) {
                for (ShareTradeInfoBySelfModel tradeInfoModel : myOrders) {
                    try {
                        myOrderRecordInfoBO = new MyOrderRecordInfoBO();
                        orderStatusDataPack(tradeInfoModel, myOrderRecordInfoBO);
                        if (myOrderPagesQueryBO.getStatus() != null) {
                            if (myOrderRecordInfoBO.getMyOrderStatus().intValue() != myOrderPagesQueryBO.getStatus()
                                    .intValue()) {
                                continue;
                            }
                        }
                        myOrderRecordInfoBOs.add(myOrderRecordInfoBO);
                    } catch (Exception e) {
                        logger.info(
                                "tradeId:" + tradeInfoModel.getId() + ",交易类型:"
                                        + ShareTradeTypeEnum.getDesc(tradeInfoModel.getTradeType()) + ",数据异常，直接跳过不展示",
                                e);
                        continue;
                    }
                }
            }
            myOrderRecordPageInfoBO.setMyOrderRecordInfoBOs(myOrderRecordInfoBOs);
            myOrderRecordPageInfoBO.setResult("success");
            // return myOrderRecordPageInfoBO;
        } catch (Exception e) {
            logger.error("我的订单分页查询异常:{}", e);
            myOrderRecordPageInfoBO.setMessage("处理订单异常!");
            myOrderRecordPageInfoBO.setResult("fail");
        }

        return myOrderRecordPageInfoBO;
    }

    /**
     * <p>
     * 根据订单状态组装数据
     * </p>
     *
     * @param tradeInfoModel
     * @param myOrderRecordInfoBO
     */
    private void orderStatusDataPack(ShareTradeInfoBySelfModel tradeInfoModel, MyOrderRecordInfoBO myOrderRecordInfoBO) {
        /************************************ 公共数据 *********************************/
        // 订单编号
        myOrderRecordInfoBO.setOrderId(tradeInfoModel.getId());
        // 订单创建时间格式化
        if (tradeInfoModel.getBackDatetime() == null) {
            myOrderRecordInfoBO.setCreateTimeFormat("");
        } else {
            myOrderRecordInfoBO.setCreateTimeFormat(
                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(tradeInfoModel.getBackDatetime()));
            // 预付金(对于芝麻信用要提示免预付金)
        }
        if (tradeInfoModel.getYfjAmount() == null) {
            myOrderRecordInfoBO.setDeposit("");
        } else {
            myOrderRecordInfoBO.setDeposit(String.valueOf(tradeInfoModel.getYfjAmount()));
        }
        // 充电器编号
        myOrderRecordInfoBO.setChargerId(tradeInfoModel.getChargerId());
        // 终端商户
        myOrderRecordInfoBO.setTerminalMerName(tradeInfoModel.getTerminalMerName());
        // 终端地址
        myOrderRecordInfoBO.setTerminalMerAddr(tradeInfoModel.getTerminalMerAddr());
        // 默认不显示结束使用按钮
        myOrderRecordInfoBO.setOverUseFlag(Boolean.FALSE);
        // 客户号
        myOrderRecordInfoBO.setCustNo(tradeInfoModel.getCustId());
        // 对于交易状态为成功的订单
        if (tradeInfoModel.getTradeStatus() == null
                || tradeInfoModel.getTradeStatus().intValue() == ShareTradeStatusEnum.TradingUsing.getCode()) {
            // 计算费用..
            int maxTimeSeconds = 0;
            Boolean moreThan24Hours = false;
            BigDecimal chargingAmount = new BigDecimal("0");
            Map<String, Object> map = shareTradeService.calculationUseAmountAndUseTime(tradeInfoModel,
                    tradeInfoModel.getShareTradeDeviceInfoModel());
            // 计算使用时长...
            chargingAmount = (BigDecimal) map.get("useAmount");
            Long lngUseTimeSeconds = (Long) map.get("useTimesForSeconds");
            maxTimeSeconds = lngUseTimeSeconds.intValue();
            // 已产生费用
            myOrderRecordInfoBO.setExpectTradeAmount(chargingAmount);
            tradeInfoModel.setTradeAmount(chargingAmount);
            // 显示结束使用按钮
            if (moreThan24Hours) {
                myOrderRecordInfoBO.setOverUseFlag(Boolean.TRUE);
            }
            // 使用中
            myOrderRecordInfoBO.setMyOrderStatus(ShareTradeStatusEnum.TradingUsing.getCode());
            myOrderRecordInfoBO.setCreateTimeFormat(
                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(tradeInfoModel.getCreateDatetime()));
            // 租借时长
            Date backDate = DateUtils.getAddSecondDate(tradeInfoModel.getBorrowDatetime(), maxTimeSeconds);
            String days = DateUtils.daysTimeGap(tradeInfoModel.getBorrowDatetime(), backDate);
            myOrderRecordInfoBO.setLentTimeRange(days);
            // 实付款
            myOrderRecordInfoBO.setTradeAmount(tradeInfoModel.getTradeAmount());
        } else {// 已归还的订单
            // 租借时长
            String days = DateUtils.daysTimeGap(tradeInfoModel.getBorrowDatetime(), tradeInfoModel.getBackDatetime());
            myOrderRecordInfoBO.setLentTimeRange(days);
            // 实付款
            myOrderRecordInfoBO.setTradeAmount(tradeInfoModel.getTradeAmount());
            // 已归还
            if (tradeInfoModel.getTradeAmount() != null
                    && tradeInfoModel.getTradeAmount().compareTo(new BigDecimal("0")) < 0) {
                myOrderRecordInfoBO.setMyOrderStatus(ShareTradeStatusEnum.REFUND.getCode());
            } else {
                myOrderRecordInfoBO.setMyOrderStatus(ShareTradeStatusEnum.Finish.getCode());
            }
            // 订单归还时间格式化
            myOrderRecordInfoBO.setBackTimeFormat(
                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(tradeInfoModel.getBackDatetime()));
        }
    }

    /**
     * 得到提现记录
     *
     * @param rechargeAndWithdrawBO
     * @return
     */
    public RechargeAndWithdrawRecordPageInfoBO getWithdrawRecordsByPage(RechargeAndWithdrawBO rechargeAndWithdrawBO) {
        RechargeAndWithdrawRecordPageInfoBO rechargeAndWithdrawRecordPageInfoBO = new RechargeAndWithdrawRecordPageInfoBO();
        try {
            List<WithdrawTradeInfoModel> rechargeAndWithdrawTradeInfos = shareTradeInfoModelBySelfMapper
                    .selectWithdrawForPage(rechargeAndWithdrawBO);
            List<RechargeAndWithdrawRecordInfoBO> rechargeAndWithdrawRecordInfoBOs = new ArrayList<RechargeAndWithdrawRecordInfoBO>();
            if (rechargeAndWithdrawTradeInfos != null) {
                RechargeAndWithdrawRecordInfoBO rechargeAndWithdrawRecordInfoBO = null;
                for (WithdrawTradeInfoModel tradeInfoModel : rechargeAndWithdrawTradeInfos) {
                    rechargeAndWithdrawRecordInfoBO = new RechargeAndWithdrawRecordInfoBO();
                    // 时间
                    rechargeAndWithdrawRecordInfoBO.setCreateTimeFormat(
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(tradeInfoModel.getCreateTime()));
                    // 交易金额
                    rechargeAndWithdrawRecordInfoBO.setTradeAmount(tradeInfoModel.getTradeAmount());
                    // 交易名称
                    rechargeAndWithdrawRecordInfoBO.setTradeName(tradeInfoModel.getTradeName());
                    // 对于交易类型为提现的交易显示提现状态
                    // 根据交易ID查询渠道交易信息
                    List<WithdrawBO> withdrawInfoBOs = shareTradeInfoModelBySelfMapper
                            .getWithdrawItemList(tradeInfoModel.getTradeId());
                    for (WithdrawBO tim : withdrawInfoBOs) {
                        if (tim.getReconStatus() == null) {
                            tim.setBankStatus("处理中");
                        } else {
                            if (ReconStatusEnum.SUCCESS.getCode() == tim.getReconStatus()) {
                                tim.setBankStatus("成功");
                            } else if (ReconStatusEnum.PROCESSING.getCode() == tim.getReconStatus()) {
                                tim.setBankStatus("处理中");
                            } else {
                                tim.setBankStatus("失败");
                            }
                        }
                        String bankDesc = WeiXinBankEnum.getDesc(tim.getRefundBank());
                        if (!StringUtils.isEmpty(bankDesc)) {
                            tim.setRefundBank(bankDesc);
                        } else {
                            tim.setRefundBank(tim.getRefundBank());
                        }
                        if (withdrawInfoBOs.size() == 1) {
                            // 交易状态
                            rechargeAndWithdrawRecordInfoBO.setTradeStatusCn(tim.getBankStatus());
                        }
                    }
                    rechargeAndWithdrawRecordInfoBO.setWithdrawInfoBOs(withdrawInfoBOs);

                    rechargeAndWithdrawRecordInfoBOs.add(rechargeAndWithdrawRecordInfoBO);
                }
            }
            rechargeAndWithdrawRecordPageInfoBO.setRechargeAndWithdrawRecordInfoBOs(rechargeAndWithdrawRecordInfoBOs);
            rechargeAndWithdrawRecordPageInfoBO.setResult("success");
        } catch (Exception e) {
            logger.error("查询记录分页处理异常:{}", e);
            rechargeAndWithdrawRecordPageInfoBO.setResult("fail");
            rechargeAndWithdrawRecordPageInfoBO.setMessage("查询信息处理异常");
        }
        return rechargeAndWithdrawRecordPageInfoBO;
    }

    /**
     * <p>
     * 查看提现进度
     * </p>
     */
    public ResultCommandBaseObject<Object> getWithdrawalProcessInfo(String tradeOutId) {
        logger.info(String.format("查看提现进--getWithdrawalProcessInfo,tradeOutId:%s", tradeOutId));
        // 组装controller所需数据，并返回
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        WithdrawInfoBO withdrawInfoBO = new WithdrawInfoBO();
        ChannelCapitalRecord ccr = channelCapitalRecordMapper.selectByPrimaryKey(Long.valueOf(tradeOutId));
        withdrawInfoBO.setTradeRecordId(ccr.getTradeRecordId());
        withdrawInfoBO.setWithdrawAmt(ccr.getTotalFee());
        withdrawInfoBO.setCreateTimeFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ccr.getCreateTime()));
        WxMpRefundQueryResult rlst = null;
        // 1. 查询提现状 态..
        logger.info("查看提现进--withdrawInfoBO:{},ccr:{}", withdrawInfoBO, ccr);
        try {
            rlst = wxAppApiService.refundquery(tradeOutId);
            logger.info("查询结果{}", rlst);
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("查看提现进---", e);
        }
        if (rlst != null) {
            Integer reconStatus = refundCallBack(tradeOutId, rlst);
            withdrawInfoBO.setReconStatus(reconStatus);
            withdrawInfoBO.setRefundBank(rlst.getRefund_recv_accout_0());
        } else {
            withdrawInfoBO.setReconStatus(ReconStatusEnum.PROCESSING.getCode());
        }
        // 3. 返回查询状态..
        returnMap.put("withdrawProgress", withdrawInfoBO);
        result.setResult(ResultCommand.SUCCESS);
        result.setResponseInfo(returnMap);
        logger.info("查看提现进度,getWithdrawalProcessInfo:{}", result);
        return result;
    }

    /**
     * <p>
     * 查看提现进度--支付宝
     * </p>
     */
    public ResultCommandBaseObject<Object> getWithdrawalProcessInfoByZfb(String recordId) throws Exception {
        logger.info(String.format("查看提现进度--支付宝--getWithdrawalProcessInfo,recordId:%s", recordId));
        // 组装controller所需数据，并返回
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        WithdrawInfoBO withdrawInfoBO = new WithdrawInfoBO();
        ChannelCapitalRecord ccr = channelCapitalRecordMapper.selectByPrimaryKey(Long.valueOf(recordId));
        withdrawInfoBO.setTradeRecordId(ccr.getTradeRecordId());
        withdrawInfoBO.setWithdrawAmt(ccr.getTotalFee());
        withdrawInfoBO.setCreateTimeFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ccr.getCreateTime()));
        // 1. 查询提现状态..
        logger.info("查看提现进度--支付宝--withdrawInfoBO:{},ccr:{}", withdrawInfoBO, ccr);
        boolean isSuccess = zfbAuthHelper.alipayRefundQuery(ccr.getOutTradeNo(), ccr.getRecordId());
        logger.info("查询退款结果isSuccess:{}", isSuccess);
        if (isSuccess) {
            Integer reconStatus = refundCallBackByZfb(ccr.getRecordId());
            withdrawInfoBO.setReconStatus(reconStatus);
        } else {
            withdrawInfoBO.setReconStatus(ReconStatusEnum.PROCESSING.getCode());
        }
        // 3. 返回查询状态..
        returnMap.put("withdrawProgress", withdrawInfoBO);
        result.setResult(ResultCommand.SUCCESS);
        result.setResponseInfo(returnMap);
        logger.info("查看提现进度,getWithdrawalProcessInfo:{}", result);
        return result;
    }

    /**
     * 更新数据库...
     *
     * @param tradeOutId
     * @param rlst
     * @return
     */
    public Integer refundCallBack(String tradeOutId, WxMpRefundQueryResult rlst) {
        Integer reconStatus = null;
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
        // 2. 更新状态
        ChannelCapitalRecord record = new ChannelCapitalRecord();
        record.setRecordId(Long.valueOf(tradeOutId));
        record.setReconStatus(reconStatus);
        record.setRecvBank(rlst.getRefund_recv_accout_0());
        record.setUpdateTime(new Date());
        channelCapitalRecordMapper.updateByPrimaryKeySelective(record);
        return reconStatus;
    }

    public Integer refundCallBackByZfb(Long recordId) {
        // 更新状态
        ChannelCapitalRecord record = new ChannelCapitalRecord();
        record.setRecordId(recordId);
        record.setReconStatus(ReconStatusEnum.SUCCESS.getCode());
        record.setUpdateTime(new Date());
        channelCapitalRecordMapper.updateByPrimaryKeySelective(record);
        return ReconStatusEnum.SUCCESS.getCode();
    }
}
