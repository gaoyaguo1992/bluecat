package com.stylefeng.guns.rest.modular.system.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.rest.modular.constant.WxAppPageIndex;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.persistence.model.SysSessionInfo;
import com.stylefeng.guns.sharecore.common.persistence.model.TradeChannelEnum;
import com.stylefeng.guns.sharecore.common.service.RegisterService;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.CustAccountModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.PwAdCallbackBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareFeeBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;
import com.stylefeng.guns.sharecore.modular.system.service.IRealTimeStatisticsTradeService;
import com.stylefeng.guns.sharecore.modular.system.service.ShareFeeService;
import com.stylefeng.guns.sharecore.modular.system.service.ShareTradeService;
import com.stylefeng.guns.sharecore.modular.system.utils.MyThreadFactory;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 扫码充电类...
 *
 * @author Alan.huang
 */
@Service
public class ScanDeviceService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(ScanDeviceService.class);
    /**
     * 客户号
     */
    @Value("${sharehelper.custNo}")
    private String custNo;
    /**
     * 注册类
     */
    @Autowired
    private RegisterService registerService;
    /**
     * 微信服务类..
     */
    @Autowired
    protected WxMpService wxMpService;
    /**
     * 交易关系类
     */
    @Autowired
    private TradeInfoService tradeInfoService;

    /**
     * 扫码充电器..
     */
    @Autowired
    private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
    /**
     * 共享充电器表..
     */
    @Autowired
    private ShareChargerModelMapper shareChargerModelMapper;
    /**
     * 实时分润
     */
    @Autowired
    @Qualifier("realTimeStatisticsTradeService")
    private IRealTimeStatisticsTradeService realTimeStatisticsTradeServiceImpl;
    /**
     * 共享交易服务类..
     */
    @Autowired
    private ShareTradeService shareTradeService;
    /**
     * 共享交易时对应的设备类型
     */
    @Autowired
    private ShareTradeDeviceInfoModelMapper shareTradeDeviceInfoModelMapper;
    /**
     * 客户账户信息..
     */
    @Autowired
    private CustAccountModelMapper custAccountModelMapper;
    /**
     * 共享费用服务类
     */
    @Autowired
    private ShareFeeService shareFeeService;
    /**
     * 处理交易订单的服务类...
     */
    private static ThreadPoolExecutor threadTradeInforServer = new ThreadPoolExecutor(5, 200, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(50), new MyThreadFactory("shareTradeService"),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    /**
     * 扫码充电器
     *
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public Map<String, Object> scanDevice(String chargerId, SysSessionInfo sysSessionInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        // 默认不弹框
        resultMap.put("tradeChannel", TradeChannelEnum.WX.getCode());
        chargerId = chargerId.replace("sh", "");
        ShareChargerModel shareChargerModel =
                shareChargerModelMapper.selectByPrimaryKey(Long.valueOf(chargerId));
        //1、 判断chargerId 状态是否正确 如果状态不正确，会抛异常。。
        ShareDeviceInfoModel shareDeviceInfoModel =
                judgetChargerForScanRecharge(shareChargerModel, chargerId);
        //2. 判断当前用户是已经租借了充电器，并且一至，如果一至，得到充电器密码并返回..
        List<ShareTradeInfoModel> list = shareTradeService.getUsingTradeOrderList(
                sysSessionInfo.getCustNo(), shareChargerModel.getId());
        if (list != null && list.size() > 0) {
            //如果有未归还的订单， 判断是否超期，有没有归还的订单，提示用户归还，
            ShareTradeInfoModel shareTradeInfoModel = list.get(0);
            ShareTradeDeviceInfoModel shareDeviceInfoModelForTrade =
                    shareTradeDeviceInfoModelMapper.selectByPrimaryKey(shareTradeInfoModel.getId());
            Map<String, Object> map = shareTradeService.calculationUseAmountAndUseTime(
                    shareTradeInfoModel, shareDeviceInfoModelForTrade);
            Boolean isExpire = (Boolean) map.get("isOverdue"); //是否超期
            Long haveUsedSeconds = (Long) map.get("useTimesForSeconds"); //使用时长.
            double haveUseMinutes = (double) map.get("haveUseMinutes"); //还能使用时长.
            BigDecimal useAmount = (BigDecimal) map.get("useAmount");//使用费用..
            //超期处理归，然后
            if (isExpire) {
                //2.1、先归还的操作， 另外开通一个进程处理
                Callable<String> callToBackByTradeId = new Callable<String>() {
                    @Override
                    public String call() {
                        try {
                            //根据交易id结束订单...
                            shareTradeService.toBackByTradeId(shareTradeInfoModel.getId());
                            return "success";
                        } catch (Exception e) {
                            logger.warn("shareTradeService.toBackByTradeId 调结束订单失败...", e);
                            return "success";
                        }
                    }
                };
                threadTradeInforServer.submit(callToBackByTradeId);
                //2.2.跳转到我要充电的界面..
                resultMap.put("pageIndex", WxAppPageIndex.MYRECHARGERPAGE);
            } else {
                //2.1 得到充电器的密码
                logger.info("2.1 得到充电器的密码---{}", shareTradeInfoModel);
                String pwd = tradeInfoService.getChargerPasswordByShareChargerModel(shareTradeInfoModel.getTradeType()
                													, shareChargerModel,shareDeviceInfoModel, haveUseMinutes);
                logger.info("2.1 得到充电器的密码---{},pwd:{}", shareTradeInfoModel, pwd);
                //2.2、跳转到，我要继续充电或结束充电的界面..
                resultMap.put("haveUsedSeconds", haveUsedSeconds);//使用时长
                resultMap.put("useAmount", useAmount);//使用费用..
                resultMap.put("shareTradeInfoModel", shareTradeInfoModel);//交锡信息
                resultMap.put("shareDeviceInfoModelForTrade", shareDeviceInfoModelForTrade);//交易时的设备信息..
                resultMap.put("pwd", pwd);
                resultMap.put("pageIndex", WxAppPageIndex.CONTINUEANDFINISHEDPAGE);
            }
        } else {
            resultMap.put("pageIndex", WxAppPageIndex.MYRECHARGERPAGE);
        }
        //3.处理跳转到我们要充电的界面参数..
        //3.1 用户账户人余额..
        CustAccountModel custAccountModel =
                custAccountModelMapper.selectByCustNo(sysSessionInfo.getCustNo(), CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
        resultMap.put("balance", custAccountModel.getAvailableBalance());
        //3.2 充电器费用..
        //处理信息泄密
        shareChargerModel.setPwdIndex(0L);
        shareChargerModel.setPwds("");
        shareChargerModel.setPwdMode(0L);

        resultMap.put("shareChargerModel", shareChargerModel);//充电器相关信息..
        resultMap.put("shareDeviceInfoModel", shareDeviceInfoModel);
        List<ShareFeeBO> listForFee = shareFeeService.getFeeListByDeviceInfoModel(shareDeviceInfoModel);
        resultMap.put("listForFee", listForFee);
        resultMap.put("custNo", sysSessionInfo.getCustNo());
        //FeeModel feeModel= JSON.parseObject("",FeeModel.class);
        return resultMap;
    }

    /**
     * 判断充电器状态是否可以扫码充电，获取密码..
     *
     * @param scanResult
     * @return
     */
    private ShareDeviceInfoModel judgetChargerForScanRecharge(ShareChargerModel shareChargerModel, String chargerIdQR) {
        // 1. 充电器id
        //1.1获取充电器信息 及设备信息。。。
        if (shareChargerModel == null) {
            logger.warn(String.format("您扫描的充电器编号在系统中不存在，请确定扫描的充电器正确(%s)!", chargerIdQR));
            throw new ExgrainBizUncheckedException(
                    String.format("您扫描的充电器编号(%s)在系统中不存在，请确定扫描的二维码正确!", chargerIdQR));
        }
        //1.2判断是否绑定了设备..
        if (shareChargerModel.getDeviceId() == null || shareChargerModel.getDeviceId().longValue() == 0) {
            logger.warn(String.format("您扫描的充电器(%d)没有绑定设备!", shareChargerModel.getId()));
            throw new ExgrainBizUncheckedException(
                    String.format("您扫描的充电器(%d)没有绑定设备,请联系客服启用!", shareChargerModel.getId()));
        }
        //1.2.1设备未烧录密码因子，请烧录密码因子...
        if (shareChargerModel.getRefactorIdx() == null || shareChargerModel.getRefactorIdx().isEmpty()) {
            logger.warn(String.format("您扫描的充电器(%d)没有烧录密码，请先烧录密码!", shareChargerModel.getId()));
            throw new ExgrainBizUncheckedException(
                    String.format("您扫描的充电器(%d)没有烧录密码，请联系客服烧录密码!", shareChargerModel.getId()));
        }

        //1.3判断绑定了设备是否正
        ShareDeviceInfoModel shareDeviceInfoModel = shareTradeService.getShareDeviceInfoByDeviceId(shareChargerModel.getDeviceId());
        if (shareDeviceInfoModel == null) {
            logger.warn(String.format("您扫描的充电器(%d)没有绑定设备!", shareChargerModel.getId()));
            throw new ExgrainBizUncheckedException(
                    String.format("您扫描的充电器(%d)没有绑定设备,请联系客服启用!", shareChargerModel.getId()));
        }
        // 2. 判断是否已经上架..

        if (shareDeviceInfoModel.getDeviceStatus() != ShareDeviceStatusEnum.activation.getCode()) {
            String msg = (shareDeviceInfoModel.getDeviceStatus() == ShareDeviceStatusEnum.waitForConfirmationStatus.getCode()) ?
                    "当前设备需要商户扫码确定才可使用，请联系管理员确认激活!" : "您扫描充电器未激活，不可使用!";
            logger.warn(msg);
            throw new ExgrainBizUncheckedException(msg);
        }
        // 3. 是否绑定了费用...
        if (shareDeviceInfoModel.getFeeTypeId() == null) {
            logger.warn(String.format("您扫描的充电器%d没有配置对应的费用规则!", shareDeviceInfoModel.getId()));
            throw new ExgrainBizUncheckedException(
                    String.format("您扫描的充电器(%d)未设置费用，请联系客服!", shareDeviceInfoModel.getId()));
        }
        // 3.1 预付费
        if (shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Prepayment.getCode()
                && (shareDeviceInfoModel.getAmountPerHour() == null || shareDeviceInfoModel.getYfjAmount() == null)) {
            logger.warn(String.format("您扫描的充电器%d没有配置对应的费用规则,!", shareDeviceInfoModel.getId()));
            throw new ExgrainBizUncheckedException(
                    String.format("您扫描的充电器(%d)未设置预付金及小时费用，请联系客服!", shareDeviceInfoModel.getId()));
        }
        // 3.2时间
        if ((shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_1.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_2.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_3.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_4.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_5.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_6.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_7.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_8.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_9.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_10.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_11.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_12.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_13.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_14.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_15.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_16.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_17.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_18.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_19.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_20.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_21.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_22.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_23.getCode() ||
                shareDeviceInfoModel.getFeeTypeId() == ShareFeeTypeEnum.Designated_Time_24.getCode())
                && (shareDeviceInfoModel.getFee1HourType() == null || shareDeviceInfoModel.getFee1HourAmount() == null)) {
            logger.warn(String.format("您扫描的充电器%d没有配置对应的费用规则,!", shareDeviceInfoModel.getId()));
            throw new ExgrainBizUncheckedException(
                    String.format("您扫描的充电器(%d)未设置费用，请联系客服!", shareDeviceInfoModel.getId()));
        }
        return shareDeviceInfoModel;
    }


    /**
     * 扣费、生成交易订单、获取密码
     *
     * @param request
     * @param custNo
     * @param chargerId
     * @param feeTypeId
     * @param needFee
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
    public ResultCommandBaseObject<Object> getPasswordForRecharge(HttpServletRequest request, Long custNo, String chargerId,
                                                                  String feeTypeId, String payOutTradeNo, String needFee, String chargeRslt, String latitude, String longitude, String zjAddr) throws Exception {
        //1. 处理参数
        logger.info("用户获取密码开始{}", chargerId);
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        Map<String, Object> map = new HashMap<>();

        //2. 检查参数合法性
        // checkParams(useCharger);
        CustInfoModel custInfoModel = registerService.getCustInfoByCustNo(custNo);
        if (custInfoModel == null) {
            logger.warn("系统不存在此账户!");
            throw new ExgrainBizUncheckedException("系统不存在您的账户,请重新扫描租借!");
        }
        //3、处理充值回调
        if (StringUtils.isNotEmpty(payOutTradeNo) && StringUtils.isNotEmpty(needFee)) {
            // 处理充值.......................................
            try {
                tradeInfoService.handleChannelCapitailResult(Long.valueOf(payOutTradeNo), "success", "get_brand_wcpay_request:ok");
            } catch (Exception e) {
                logger.error("微信小程序获取密码时处理充值回调异常:{}", e);
            }
        }
        //4. 处理交易..1、获取密码 2、冻结用户资金， 3、生成交易订单，4、生成使用率表...
        logger.info("处理交易..1、获取密码 2、冻结用户资金， 3、生成交易订单，4、生成使用率表");
        Long chargerIdLong = 0L;
        try {
            chargerIdLong = Long.parseLong(chargerId);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("转换输入的充电器编码失败:{}", e);
            throw new ExgrainBizUncheckedException(String.format("获取的充电器编号(%s)失败，请确定充电器编号正确!", chargerId));
        }
        ShareChargerModel chargerModel = shareChargerModelMapper.selectByPrimaryKey(chargerIdLong);
        if (chargerModel == null) {
            logger.info(String.format("数据库中未找到对应的充电器-%d", chargerIdLong));
            throw new ExgrainBizUncheckedException(String.format("获取的充电器编号(%s)失败，请确定充电器编号正确!", chargerId));
        }
        if (chargerModel.getDeviceId() == null || chargerModel.getDeviceId() <= 0) {
            logger.info(String.format("充电器-%d,未绑定对应的充电设备柜，无法获取费用", chargerIdLong));
            throw new ExgrainBizUncheckedException(
                    String.format("充电器(%d)未绑定充电设备，无法获取收费模式，请联系管理员!", chargerId));
        }
        //4.2 处理设备类型
        ShareDeviceInfoModel deviceInfoModel = shareDeviceInfoModelMapper.selectByPrimaryKey(chargerModel.getDeviceId());
        if (deviceInfoModel == null) {
            logger.info(String.format("充电器-%d在绑定充电设备不存在或者不可用", chargerIdLong));
            throw new ExgrainBizUncheckedException(String.format(
                    "充电器(%s)绑定的充电设备柜(%d)不存在或不可用，请联系管理员!", chargerId, chargerModel.getDeviceId()));
        }
        //生成订单..
        Long tradeId = tradeInfoService.getPasswordForRecharge(deviceInfoModel, chargerModel,
                custInfoModel, latitude, longitude, zjAddr, feeTypeId, payOutTradeNo, result);

        PwAdCallbackBO pac = new PwAdCallbackBO(result.getResult(), deviceInfoModel.getDeviceTypeName(),
                needFee, "0", "", result.getMessage());
        pac.setTradeId(tradeId.toString());
        pac.setFeeTypeId(feeTypeId);
        pac.setDeviceNo(deviceInfoModel.getId().toString());
        pac.setChargerId(chargerId);
        map.put("map", pac);
        //5. 返回..
        String pwd = result.getResult();
        map.put("pwd", pwd);
        map.put("tradeId", tradeId);
        result.setResult(ResultCommandBaseObject.SUCCESS);
        result.setResponseInfo(map);
        return result;
    }


    /**获取充电器密码，支付宝小程序渠道
     * @param custNo
     * @param chargerId
     * @param feeTypeId
     * @param payOutTradeNo
     * @param needFee
     * @param chargeRslt
     * @param latitude
     * @param longitude
     * @param zjAddr
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
    public ResultCommandBaseObject<Object> getPasswordForRechargeByZfb(Long custNo, String chargerId,
                                                                       String feeTypeId, String payOutTradeNo, String needFee, String chargeRslt, String latitude, String longitude, String zjAddr) throws Exception {
        //1. 处理参数
        logger.info("用户获取密码开始{}", chargerId);
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        Map<String, Object> map = new HashMap<>();

        //2. 检查参数合法性
        // checkParams(useCharger);
        CustInfoModel custInfoModel = registerService.getCustInfoByCustNo(custNo);
        if (custInfoModel == null) {
            logger.warn("系统不存在此账户!");
            throw new ExgrainBizUncheckedException("系统不存在您的账户,请重新扫描租借!");
        }
        //3、处理充值回调
        if (StringUtils.isNotEmpty(payOutTradeNo) && StringUtils.isNotEmpty(needFee)) {
            try {
                tradeInfoService.handleChannelCapitailResultByZfb(Long.valueOf(payOutTradeNo));
            } catch (ExgrainBizUncheckedException e) {
                throw new ExgrainBizUncheckedException(e.getMessage());
            }catch (Exception e) {
                logger.error("支付宝小程序获取密码时处理充值回调系统异常:{}", e);
                throw new ExgrainBizUncheckedException("充值回调系统异常,请联系客服!");
            }
        }
        //4. 处理交易..1、获取密码 2、冻结用户资金， 3、生成交易订单，4、生成使用率表...
        logger.info("处理交易..1、获取密码 2、冻结用户资金， 3、生成交易订单，4、生成使用率表");
        Long chargerIdLong = 0L;
        try {
            chargerIdLong = Long.parseLong(chargerId);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("转换输入的充电器编码失败:{}", e);
            throw new ExgrainBizUncheckedException(String.format("获取的充电器编号(%s)失败，请确定充电器编号正确!", chargerId));
        }
        ShareChargerModel chargerModel = shareChargerModelMapper.selectByPrimaryKey(chargerIdLong);
        if (chargerModel == null) {
            logger.info(String.format("数据库中未找到对应的充电器-%d", chargerIdLong));
            throw new ExgrainBizUncheckedException(String.format("获取的充电器编号(%s)失败，请确定充电器编号正确!", chargerId));
        }
        if (chargerModel.getDeviceId() == null || chargerModel.getDeviceId() <= 0) {
            logger.info(String.format("充电器-%d,未绑定对应的充电设备柜，无法获取费用", chargerIdLong));
            throw new ExgrainBizUncheckedException(
                    String.format("充电器(%d)未绑定充电设备，无法获取收费模式，请联系管理员!", chargerId));
        }
        //4.2 处理设备类型
        ShareDeviceInfoModel deviceInfoModel = shareDeviceInfoModelMapper.selectByPrimaryKey(chargerModel.getDeviceId());
        if (deviceInfoModel == null) {
            logger.info(String.format("充电器-%d在绑定充电设备不存在或者不可用", chargerIdLong));
            throw new ExgrainBizUncheckedException(String.format(
                    "充电器(%s)绑定的充电设备柜(%d)不存在或不可用，请联系管理员!", chargerId, chargerModel.getDeviceId()));
        }
        //生成订单..
        Long tradeId = tradeInfoService.getPasswordForRechargeByZfb(deviceInfoModel, chargerModel,
                custInfoModel, latitude, longitude, zjAddr, feeTypeId, payOutTradeNo, result);

        PwAdCallbackBO pac = new PwAdCallbackBO(result.getResult(), deviceInfoModel.getDeviceTypeName(),
                needFee, "0", "", result.getMessage());
        pac.setTradeId(tradeId.toString());
        pac.setFeeTypeId(feeTypeId);
        pac.setDeviceNo(deviceInfoModel.getId().toString());
        pac.setChargerId(chargerId);
        map.put("map", pac);
        //5. 返回..
        String pwd = result.getResult();
        map.put("pwd", pwd);
        map.put("tradeId", tradeId);
        result.setResult(ResultCommandBaseObject.SUCCESS);
        result.setResponseInfo(map);
        return result;
    }

    /**
     * 用户用支付宝小程序扫码
     *
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public Map<String, Object> scanDeviceByZfb(String chargerId, Long custNo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        // 默认不弹框
        resultMap.put("tradeChannel", TradeChannelEnum.ZFB.getCode());
        chargerId = chargerId.replace("sh", "");
        ShareChargerModel shareChargerModel =
                shareChargerModelMapper.selectByPrimaryKey(Long.valueOf(chargerId));
        //1、 判断chargerId 状态是否正确 如果状态不正确，会抛异常。。
        ShareDeviceInfoModel shareDeviceInfoModel =
                judgetChargerForScanRecharge(shareChargerModel, chargerId);
        //2. 判断当前用户是已经租借了充电器，并且一至，如果一至，得到充电器密码并返回..
        List<ShareTradeInfoModel> list = shareTradeService.getUsingTradeOrderList(
                custNo, shareChargerModel.getId());
        if (list != null && list.size() > 0) {
            //如果有未归还的订单， 判断是否超期，有没有归还的订单，提示用户归还，
            ShareTradeInfoModel shareTradeInfoModel = list.get(0);
            ShareTradeDeviceInfoModel shareDeviceInfoModelForTrade =
                    shareTradeDeviceInfoModelMapper.selectByPrimaryKey(shareTradeInfoModel.getId());
            Map<String, Object> map = shareTradeService.calculationUseAmountAndUseTime(
                    shareTradeInfoModel, shareDeviceInfoModelForTrade);
            Boolean isExpire = (Boolean) map.get("isOverdue"); //是否超期
            Long haveUsedSeconds = (Long) map.get("useTimesForSeconds"); //使用时长.
            double haveUseMinutes = (double) map.get("haveUseMinutes"); //还能使用时长.
            BigDecimal useAmount = (BigDecimal) map.get("useAmount");//使用费用..
            //超期处理归，然后
            if (isExpire) {
                //2.1、先归还的操作， 另外开通一个进程处理
                Callable<String> callToBackByTradeId = new Callable<String>() {
                    @Override
                    public String call() {
                        try {
                            //根据交易id结束订单...
                            shareTradeService.toBackByTradeId(shareTradeInfoModel.getId());
                            return "success";
                        } catch (Exception e) {
                            logger.warn("shareTradeService.toBackByTradeId 调结束订单失败...", e);
                            return "success";
                        }
                    }
                };
                threadTradeInforServer.submit(callToBackByTradeId);
                //2.2.跳转到我要充电的界面..
                resultMap.put("pageIndex", WxAppPageIndex.MYRECHARGERPAGE);
            } else {
                //2.1 得到充电器的密码
                logger.info("2.1 得到充电器的密码---{}", shareTradeInfoModel);
                String pwd = tradeInfoService.getChargerPasswordByShareChargerModel(shareTradeInfoModel.getTradeType()
                												, shareChargerModel,shareDeviceInfoModel, haveUseMinutes);
                logger.info("2.1 得到充电器的密码---{},pwd:{}", shareTradeInfoModel, pwd);
                //2.2、跳转到，我要继续充电或结束充电的界面..
                resultMap.put("haveUsedSeconds", haveUsedSeconds);//使用时长
                resultMap.put("useAmount", useAmount);//使用费用..
                resultMap.put("shareTradeInfoModel", shareTradeInfoModel);//交锡信息
                resultMap.put("shareDeviceInfoModelForTrade", shareDeviceInfoModelForTrade);//交易时的设备信息..
                resultMap.put("pwd", pwd);
                resultMap.put("pageIndex", WxAppPageIndex.CONTINUEANDFINISHEDPAGE);
            }
        } else {
            resultMap.put("pageIndex", WxAppPageIndex.MYRECHARGERPAGE);
        }
        //3.处理跳转到我们要充电的界面参数..
        //3.1 用户账户人余额..
        CustAccountModel custAccountModel =
                custAccountModelMapper.selectByCustNo(custNo, CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
        resultMap.put("balance", custAccountModel.getAvailableBalance());
        //3.2 充电器费用..
        //处理信息泄密
        shareChargerModel.setPwdIndex(0L);
        shareChargerModel.setPwds("");
        shareChargerModel.setPwdMode(0L);

        resultMap.put("shareChargerModel", shareChargerModel);//充电器相关信息..
        resultMap.put("shareDeviceInfoModel", shareDeviceInfoModel);
        List<ShareFeeBO> listForFee = shareFeeService.getFeeListByDeviceInfoModel(shareDeviceInfoModel);
        resultMap.put("listForFee", listForFee);
        resultMap.put("custNo", custNo);
        return resultMap;
    }

}