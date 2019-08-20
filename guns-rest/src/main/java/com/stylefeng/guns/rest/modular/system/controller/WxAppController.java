package com.stylefeng.guns.rest.modular.system.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.core.util.AESUtilForWX;
import com.stylefeng.guns.core.util.XmlUtil;
import com.stylefeng.guns.rest.modular.model.ResultCommandSysSessionInfo;
import com.stylefeng.guns.rest.modular.system.service.ScanDeviceService;
import com.stylefeng.guns.rest.modular.system.service.TradeInfoService;
import com.stylefeng.guns.rest.modular.system.service.WxAppLoginService;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustAccountBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.ResponseHandler;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.persistence.model.SysSessionInfo;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppPayCallBack;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppPayInfoModel;
import com.stylefeng.guns.sharecore.common.service.RegisterService;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.ChannelTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.FinishTradeOrderBO;
import com.stylefeng.guns.sharecore.modular.system.model.MyOrderPagesQueryBO;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeAndWithdrawBO;
import com.stylefeng.guns.sharecore.modular.system.model.ReconStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawPageInfoBO;
import com.stylefeng.guns.sharecore.modular.system.service.ShareTradeService;
import com.stylefeng.guns.sharecore.modular.system.service.UserCenterService;
import com.stylefeng.guns.sharecore.modular.system.service.WxAppApiService;

import me.chanjar.weixin.mp.bean.WxMpRefundQueryResult;

/**
 * 
 * 微信小程序所有相关服务类..
 *
 * @author Alan.huang
 * @date 2019-01-01 16:02 wxapp
 */
@Controller
@RequestMapping("/wxapp")
public class WxAppController {
	/**
	 * 日志对象...
	 */
	private static final Logger logger = LoggerFactory.getLogger(WxAppController.class);
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
	 * 小程序登录相关类及代码
	 */
	@Autowired
	private WxAppLoginService wxAppLoginService;
	/**
	 * 设备服务类..
	 */
	@Autowired
	private ScanDeviceService scanDeviceService;
	/**
	 * 支付
	 */
	@Autowired
	private TradeInfoService tradeInfoService;
	/**
	 * 交易信息..
	 */
	@Autowired
	private ShareTradeInfoModelMapper shareTradeInfoModelMapper;
	/**
	 * 结束订单
	 */
	@Autowired
	private ShareTradeService shareTradeService;
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
	 * wx api服务
	 */
	@Autowired
	private WxAppApiService wxAppApiService;
	/**
	 * api 密码约...
	 */
	@Value("${sharehelper.wxapp.apisecret}")
	private String key;

	/**
	 * 处理流量转换为string
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	/**
	 * /**
	 * 
	 * 提现微信处理结果回调
	 * 
	 * @return
     */
	@RequestMapping("/callBackForRefund")
	@ResponseBody
	public void callBackForRefund(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("提现微信处理结果回调：callBackForRefund");
			// 返回码
			String xmlRes = inputStream2String(request.getInputStream());
			logger.info("提现微信处理结果回调：callBackForRefund 方法入参：xmlResult:{}", xmlRes);
			// 校验签名
			ResponseHandler resHandler = wxAppApiService.checkSign(xmlRes);

			// 判断签名是否正确
			if (resHandler != null) {
				String result_code = resHandler.getParameter("result_code");
				String appid = resHandler.getParameter("appid");
				String mch_id = resHandler.getParameter("mch_id");
				String nonce_str = resHandler.getParameter("nonce_str");
				String req_info = resHandler.getParameter("req_info");

				if ("SUCCESS".equals(resHandler.getParameter("result_code"))) {
					/*
					 * 解密步骤如下： （1）对加密串A做base64解码，得到加密串B （2）对商户key做md5，得到32位小写key* (
					 * key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置 )
					 * （3）用key*对加密串B做AES-256-ECB解密（PKCS7Padding）
					 */
					if (req_info != null && !req_info.isEmpty()) {
						String wxXml = AESUtilForWX.decryptDataForWxRefund(req_info, key);
						logger.info("提现微信处理结果回调：callBackForRefund --wxXml方法入参：wxXml:{}", wxXml);
						/*
						 * <root> <out_refund_no><![CDATA[131811191610442717309]]></ out_refund_no>
						 * <out_trade_no><![CDATA[71106718111915575302817]]></ out_trade_no>
						 * <refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS ]]></refund_account>
						 * <refund_fee><![CDATA[3960]]></refund_fee>
						 * <refund_id><![CDATA[50000408942018111907145868882]]>< /refund_id>
						 * <refund_recv_accout><![CDATA[支付用户零钱]]></ refund_recv_accout>
						 * <refund_request_source><![CDATA[API]]></ refund_request_source>
						 * <refund_status><![CDATA[SUCCESS]]></refund_status>
						 * <settlement_refund_fee><![CDATA[3960]]></ settlement_refund_fee>
						 * <settlement_total_fee><![CDATA[3960]]></ settlement_total_fee>
						 * <success_time><![CDATA[2018-11-19 16:24:13]]></success_time>
						 * <total_fee><![CDATA[3960]]></total_fee>
						 * <transaction_id><![CDATA[4200000215201811190261405420 ]]></transaction_id>
						 * </root>
						 */
						Map<String, String> xmlResult = XmlUtil.xmlResult(wxXml);
						WxMpRefundQueryResult result = new WxMpRefundQueryResult();
						result.setResult_code(result_code);
						result.setErr_code("");
						result.setErr_code_des("");
						result.setAppid(appid);
						result.setMch_id(mch_id);
						result.setNonce_str(nonce_str);
						result.setSign("");
						String transaction_id = xmlResult.get("transaction_id");
						result.setTransaction_id(transaction_id);
						String out_trade_no = xmlResult.get("out_trade_no");
						result.setOut_trade_no(out_trade_no);
						String total_fee = xmlResult.get("total_fee");
						result.setTotal_fee(total_fee);
						String out_refund_no_0 = xmlResult.get("out_refund_no");
						result.setOut_refund_no_0(out_refund_no_0);
						String refund_fee_0 = xmlResult.get("refund_fee");
						result.setRefund_fee_0(refund_fee_0);
						String refund_recv_accout_0 = xmlResult.get("refund_recv_accout");
						result.setRefund_recv_accout_0(refund_recv_accout_0);
						String refund_status_0 = xmlResult.get("refund_status");
						result.setRefund_status_0(refund_status_0);
						// 调服 out_refund_no_0
						userCenterService.refundCallBack(out_refund_no_0, result);
						logger.info("提现微信处理结果回调：callBackForRefund --result:{}", result);
					}
				} else {
					logger.error("退款回调处理,{}", resHandler);
				}
			} else {
				logger.error("通知签名验证失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("微信支付成功回调失败，callBackForRecharge--", e);
		}
	}

	/**
	 * /**
	 * 
	 * 充值成功回调
	 * 
	 * @return
     */
	@RequestMapping("/callBackForRecharge")
	@ResponseBody
	public void callBackForRecharge(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("开始处理支付回调：callBackForRecharge");
			// 返回码
			String xmlResult = inputStream2String(request.getInputStream());
			Map<String, String> mapResult = XmlUtil.xmlResult(xmlResult);
			logger.info("小程序 充值成功回调：callBackRecharge 方法入参：returnCode:{},returnMsg:{},xmlResult:{}",
					mapResult.get("return_code"), mapResult.get("return_msg"), xmlResult);
			// 校验签名
			ResponseHandler resHandler = wxAppApiService.checkSign(xmlResult);

			// 判断签名是否正确
			if (resHandler.isTenpaySign()) {
				String resXml = "";
				if ("SUCCESS".equals(resHandler.getParameter("result_code"))) {
					// 这里是支付成功
					////////// 执行自己的业务逻辑////////////////
					WxAppPayCallBack wxAppPayCallBack = wxAppApiService.callBackRecharge(xmlResult);
					// 处理成功交易
					boolean isSuccess = tradeInfoService.handleChannelCapitailResultForWexinApp(wxAppPayCallBack);

					if (isSuccess) {

						resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
								+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
					} else {
						logger.error("支付失败,错误信息：" + resHandler.getParameter("err_code"));
						resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
								+ "<return_msg><![CDATA[FAILED]]></return_msg>" + "</xml> ";
					}
					////////// 执行自己的业务逻辑////////////////
					// 同步返回给微信参数
					// resHandler.sendToCFT("SUCCESS");
					// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.

				} else {
					logger.error("支付失败,错误信息：" + resHandler.getParameter("err_code"));
					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[FAILED]]></return_msg>" + "</xml> ";
				}
				// 处理业务完毕
				// ------------------------------
				BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
				try {
					out.write(resXml.getBytes());
				} catch (Exception e) {
					logger.error("", e);
				} finally {
					try {
						out.flush();
						out.close();
					} catch (Exception e) {
						logger.error("", e);
					}
				}

			} else {
				logger.error("通知签名验证失败");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("微信支付成功回调失败，callBackForRecharge--", e);
		}

	}

	/**
	 * 调用接口时，通这此方法确定是否凭证有效...
	 * 
	 * @param request: request请求...
	 * @param paramValues: 接口方法参数结果串,
	 *        如约定A方法有param1,param2,param3参数值分别为A,1,B那边paramValues为:A1B
	 * @param keyCode:md5参数...
	 * @param 有效登录凭证...
	 * @return ResultCommand 返回responseInfo中包含SysSessionInfo内容..
	 */
	protected ResultCommandSysSessionInfo checkSessionForWxApp(HttpServletRequest request, String paramValues,
			String keyCode, String session3rd) {
		return wxAppLoginService.checkSessionForWxApp(request, paramValues, keyCode, session3rd);
	}

	/**
	 * 获取我的信息数量信息..
	 * 
	 * @param simpleObject
	 * @return
	 */
	@RequestMapping("getMyMessageCoutInfo")
	@ResponseBody
	public Map<String, Long> getMyMessageCoutInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String session3rd = request.getParameter("session3rd");
			String keycode = request.getParameter("keycode");
			String id = request.getParameter("id");
			// 检验数据合法性
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, id, keycode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				logger.warn(checkSession.getMessage());
				return null;
			}
			// 账号信息
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			String wxAppOpenId = sysSessionInfo.getWxappopendid();
			CustInfoModel custInfoModel = registService.getCustInfoByWxAppOpenId(wxAppOpenId);
			/**
			 * 返回所有的用户未读信息
			 */
			Map<String, Long> map = userCenterService.getMyMessageCoutInfo(custInfoModel.getCustNo(), id);
			// new ModelAndView()
			return map;
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
		}
		return null;
	}

	/**
	 * 扫码充电
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/scanDevice")
	@ResponseBody
	public Object scanDevice(HttpServletRequest request, HttpServletResponse response) {
		String scanResult = request.getParameter("scanResult");// 充电器..
		String keyCode = request.getParameter("keyCode");// 验证码...
		String session3rd = request.getParameter("session3rd");//
		String latitude = StringUtils.isEmpty(request.getParameter("currLatitude")) ? ""
				: request.getParameter("currLatitude"); // 当前扫码纬度
		String longitude = StringUtils.isEmpty(request.getParameter("currLongitude")) ? ""
				: request.getParameter("currLongitude"); // 当前反码经度

		logger.info("当前纬度{},当前经度{}", latitude, longitude);
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();
		logger.info("方法：scanDevice 入参    keyCode:{},session3rd:{},scanResult:{},params:{}", keyCode, session3rd,
				scanResult);
		try {
			// 检验数据合法性
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, scanResult, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {

				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			Map<String, Object> map = scanDeviceService.scanDevice(scanResult, sysSessionInfo);
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
	 * 生成预支付订单.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getPrepayForCust")
	@ResponseBody
	public Object getPrepayForCust(HttpServletRequest request, HttpServletResponse response) {
		String keyCode = request.getParameter("keyCode");// 验证码...
		String session3rd = request.getParameter("session3rd");//

		String needFee = request.getParameter("needFee");// 差多少钱..
		String feeTypeId = request.getParameter("feeTypeId");// 费用类型 id
		String custNo = request.getParameter("custNo");// 费用对应的客户id
		logger.info(String.format("生成预支付订单,needFee:%s,feeTypeId:%s,custNo:%s.keyCode:%s, session3rd:%s", needFee,
				feeTypeId, custNo, keyCode, session3rd));
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();

		try {
			// 检验数据合法性
			String paramValues = String.format("%s%s%s", needFee, feeTypeId, custNo);
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {

				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			String openId = sysSessionInfo.getOpenid();
			// 2、判断客户信息
			CustInfoModel custInfoModel = registService.getCustInfoByWxAppOpenId(openId);
			custInfoModel.setOpenId(custInfoModel.getOpenId());

			Integer iFeeTypeId = new Integer("0");
			if (feeTypeId != null) {
				try {
					iFeeTypeId = Integer.parseInt(feeTypeId);
				} catch (Exception e) {
					logger.error("生成预支付订单,费用类型转换失败,", e);
				}
			}
			String feeTypeDesc = ShareFeeTypeEnum.getDesc(iFeeTypeId);

			WxAppPayInfoModel payInfo = tradeInfoService.getPrepayIdForWxApp(custInfoModel, Long.valueOf(needFee),
					feeTypeDesc, iFeeTypeId);

			logger.info("充值预支付信息{}", payInfo);
			result.setResponseInfo(payInfo);
			result.setResult(ResultCommand.SUCCESS);
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
	 * 得到充电器密码...
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getPasswordForRecharge")
	@ResponseBody
	public Object getPasswordForRecharge(HttpServletRequest request, HttpServletResponse response) {
		// 1. 得到参数..
		String keyCode = request.getParameter("keyCode");// 验证码...
		String session3rd = request.getParameter("session3rd");//

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
		/*
		// 2. 检验数据合法性
		String paramValues = String.format("%s%s%s%s%s%s%s%s%s", custNo, feeTypeId, chargerId, outTradeNo, needFee,
				chargeRslt, latitude, longitude, zjAddr);
		ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
		if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {

			return checkSession;
		}
		SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
		String openId = sysSessionInfo.getOpenid();

		if (sysSessionInfo != null && !sysSessionInfo.getCustNo().equals(Long.parseLong(custNo))) {
			result.setResult(ResultCommand.FAILED);
			result.setMessage("交易账户和当前登录账户不一至，无法获取密码, 请重新登录!");
			return result;
		}*/
		String openId="ot0XX5aaFeV2m6aBmlpfKOS26rDU";
		Long custNoTmp=1000100055L;
		try {
			// 3、判断客户信息
			CustInfoModel custInfoModel = registService.getCustInfoByWxAppOpenId(openId);
			custInfoModel.setOpenId(custInfoModel.getOpenId());
			// 4. 扣费、生成交易订单、获取密码、结订单
			/*result = scanDeviceService.getPasswordForRecharge(request, sysSessionInfo.getCustNo(), chargerId, feeTypeId,
					outTradeNo, needFee, chargeRslt, latitude, longitude, zjAddr);*/
			result = scanDeviceService.getPasswordForRecharge(request, custNoTmp, chargerId, feeTypeId,
												outTradeNo, needFee, chargeRslt, latitude, longitude, zjAddr);// 
			return result;
		} catch (ExgrainBizUncheckedException e) {
			logger.error("getPasswordForRecharge系统出错，请稍后重试-->", e);
			result.setMessage(e.getMessage());
			result.setResult(ResultCommand.FAILED);
			return result;
		} catch (Exception e) {
			logger.error("getPasswordForRecharge系统出错，请稍后重试!", e);
			result.setMessage("系统出错，请稍后重试！");
			result.setResult(ResultCommand.FAILED);
			return result;
		}
	}

	/**
	 * 再次扫二维码结束充电...
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/finishRecharge")
	@ResponseBody
	public Object finishRecharge(HttpServletRequest request, HttpServletResponse response) {
		// 1. 得到参数..
		String keyCode = request.getParameter("keyCode");// 验证码...
		String session3rd = request.getParameter("session3rd");//
		String custNo = request.getParameter("custNo");// 客户id
		String chargerId = request.getParameter("chargerId");// 用户扫的充电器id
		String tradeId = request.getParameter("tradeId");// 用户扫的充电器id

		logger.info(String.format("再次扫二维码结束充电...,custNo:%s,chargerId:%s.tradeId:%s", custNo, chargerId, tradeId));
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();

		// 2. 检验数据合法性
		String paramValues = String.format("%s%s%s", chargerId, custNo, tradeId);
		ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
		if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
			return checkSession;
		}
		SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
		if (sysSessionInfo != null && !sysSessionInfo.getCustNo().equals(Long.parseLong(custNo))) {
			result.setResult(ResultCommand.FAILED);
			result.setMessage("与登录账户信息不一至，无法结束订单, 请重新登录!");
			return result;
		}
		// 3. 查询订单信息..
		Long lngTradeId = 0L;
		try {
			lngTradeId = Long.parseLong(tradeId);
		} catch (Exception e) {
			// TODO: handle exception
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
		if (tradeInfoModel.getCustId() != null && !tradeInfoModel.getCustId().equals(sysSessionInfo.getCustNo())) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(String.format("再次扫二维码结束充电...,custNo:%s,chargerId:%s.tradeId:%s", custNo, chargerId, tradeId),
					e);
			result.setResult(ResultCommand.FAILED);
			result.setMessage("结束订单失败!");
		}
		return result;
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
		String keyCode = request.getParameter("keyCode");// 验证码...
		String session3rd = request.getParameter("session3rd");//

		logger.info(String.format(" 得到用户中心需要的相关信息.....,keyCode:%s,session3rd:%s", keyCode, session3rd));
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();

		// 2. 检验数据合法性
		String paramValues = "";
		ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
		if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
			return checkSession;
		}
		SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();

		Map<String, Object> map = userCenterService.getUserCenterInfo(sysSessionInfo.getCustNo());
		result.setResponseInfo(map);
		result.setResult(ResultCommandBaseObject.SUCCESS);
		return result;
	}

	/**
	 * 得到信息。。。
	 * 
	 * @param request
	 * @param response
	 * @return
     */
	@RequestMapping("/getMyMessages")
	@ResponseBody
	public Object getMyMessages(HttpServletRequest request, HttpServletResponse response) {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<Object>();

		String session3rd = request.getParameter("session3rd");
		String startStr = request.getParameter("start");
		String countStr = request.getParameter("count");
		String keyCode = request.getParameter("keyCode");
		String codeStr = request.getParameter("code");
		logger.info("方法：myMessages 入参    session3rd:{}", session3rd);
		try {
			// 检验数据合法性
			String paramValues = String.format("%s%s%s", startStr, countStr, codeStr);
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			// 账号信息
			String wxAppOpenId = sysSessionInfo.getWxappopendid();
			CustInfoModel custInfoModel = registService.getCustInfoByWxAppOpenId(wxAppOpenId);
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
	 * <p>
	 * 用户读消息，修改消息状态为已读
	 * </p>
	 * 
	 * @param request
	 * @param response
     */
	@RequestMapping("/doReadMessage")
	@ResponseBody
	public Object doReadMessage(HttpServletRequest request, HttpServletResponse response) {
		String session3rd = request.getParameter("session3rd");
		String keyCode = request.getParameter("keycode");
		String id = request.getParameter("id");
		try {
			logger.info(String.format("用户读消息，修改消息状态为已读doReadMessage-session3rd:%s, id:%s,keyCode:%s", session3rd, id,
					keyCode));
			// 检验数据合法性
			String paramValues = id;
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			// 账号信息
			String wxAppOpenId = sysSessionInfo.getWxappopendid();
			CustInfoModel custInfoModel = registService.getCustInfoByWxAppOpenId(wxAppOpenId);
			//
			Map<String, Long> map = userCenterService.doReadMessage(custInfoModel.getCustNo(), id);
			return map;
		} catch (ExgrainBizUncheckedException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
		}
		return null;
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
		String session3rd = request.getParameter("session3rd");
		String keyCode = request.getParameter("keycode");
		try {
			logger.info(String.format("用户读消息，修改消息状态为已读geMyWallet-session3rd:%s, keyCode:%s", session3rd, keyCode));
			// 检验数据合法性
			String paramValues = "";
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			ResultCommandBaseObject<Object> resultCommand = new ResultCommandBaseObject<>();
			// 账号信息
			CustAccountModel accountModel = custAccountBySelfMapper.selectByCustNo(sysSessionInfo.getCustNo(),
					CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
			// 账户余额..
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", sysSessionInfo.getCustNo());
			map.put("availableBalance", accountModel.getAvailableBalance());
			map.put("frozenBalance", accountModel.getFrozenBalance());

			resultCommand.setResult(ResultCommandBaseObject.SUCCESS);
			resultCommand.setResponseInfo(map);
			return resultCommand;
		} catch (ExgrainBizUncheckedException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
		}
		return null;
	}

	/**
	 * 得到我的订单，并把数据进行分页处理...
	 * 
	 * @param request
	 * @param response
	 * @return
     */
	@RequestMapping("/getMyOrderWithPages")
	@ResponseBody
	public Object getMyOrderWithPages(HttpServletRequest request, HttpServletResponse response) {
		String session3rd = request.getParameter("session3rd");
		String keyCode = request.getParameter("keycode");
		String sCustNo = request.getParameter("custNo");// 客户号
		String sStart = request.getParameter("start"); // 开始
		String sRows = request.getParameter("rows");
		String sStatus = request.getParameter("status");

		try {
			logger.info(String.format(
					"得到我的订单，并把数据进行分页处理...getMyOrderWithPages-sCustNo:%s, sStart:%s,sRows:%s,sStatus:%s,keyCode:%s",
					sCustNo, sStart, sRows, sStatus, keyCode));
			// 1. 检验数据合法性
			String paramValues = sCustNo;
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			ResultCommandBaseObject<Object> resultCommand = new ResultCommandBaseObject<>();
			Long custNo = Long.parseLong(sCustNo);
			if (sysSessionInfo.getCustNo() == null || !sysSessionInfo.getCustNo().equals(custNo)) {
				resultCommand.setResult(ResultCommand.FAILED);
				resultCommand.setMessage(String.format("客户编号(%d)与系统登录的编号不一至，无权获取订单信息", sCustNo));
				return resultCommand;
			}
			// 2. 构造查询条件..
			Integer status = (sStatus != null && !StringUtils.isEmpty(sStatus)) ? new Integer(sStatus) : null;
			Integer rows = (sRows != null && !StringUtils.isEmpty(sRows)) ? new Integer(sRows) : new Integer("0");
			Integer start = (sStart != null && !StringUtils.isEmpty(sStart)) ? new Integer(sStart) : new Integer("0");
			MyOrderPagesQueryBO myOrderPagesQueryBO = new MyOrderPagesQueryBO();
			myOrderPagesQueryBO.setCustNo(custNo);
			myOrderPagesQueryBO.setRows(rows);
			myOrderPagesQueryBO.setStart(start);
			myOrderPagesQueryBO.setStatus(status);
			// 3. 查询反回订单
			return userCenterService.getMyOrdersWithPages(myOrderPagesQueryBO);
		} catch (ExgrainBizUncheckedException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
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
		String session3rd = request.getParameter("session3rd");
		String keyCode = request.getParameter("keyCode");
		String sCustNo = request.getParameter("custNo");// 客户号
		String sStart = request.getParameter("start"); // 开始
		String sRows = request.getParameter("rows"); // 一页行数
		try {
			logger.info(String.format("得到提现记录....getWithdrawRecordsByPage-keyCode:%s,custNo:%s, sStart:%s,sRows:%s",
					keyCode, sCustNo, sStart, sRows));
			// 1. 检验数据合法性
			String paramValues = String.format("%s%s%s", sCustNo, sStart, sRows);

			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			ResultCommandBaseObject<Object> resultCommand = new ResultCommandBaseObject<>();
			Long custNo = Long.parseLong(sCustNo);
			if (sysSessionInfo.getCustNo() == null || !sysSessionInfo.getCustNo().equals(custNo)) {
				resultCommand.setResult(ResultCommand.FAILED);
				resultCommand.setMessage(String.format("客户编号(%d)与系统登录的编号不一至，无权获到提现记录信息", sCustNo));
				return resultCommand;
			}
			// 2. 构造查询条件..
			Integer rows = (sRows != null && !StringUtils.isEmpty(sRows)) ? new Integer(sRows) : new Integer("0");
			Integer start = (sStart != null && !StringUtils.isEmpty(sStart)) ? new Integer(sStart) : new Integer("0");
			RechargeAndWithdrawBO rechargeAndWithdrawBO = new RechargeAndWithdrawBO();
			rechargeAndWithdrawBO.setCustNo(custNo);
			rechargeAndWithdrawBO.setRows(rows);
			rechargeAndWithdrawBO.setStart(start);
			// 3. 查询反回订单
			return userCenterService.getWithdrawRecordsByPage(rechargeAndWithdrawBO);
		} catch (ExgrainBizUncheckedException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
		}
		return null;
	}

	/**
	 * 查询提现记录， 得到提现明细
	 * 
	 * @param request
	 * @param response
	 * @param tradeOutId
	 * @return
     */
	@RequestMapping("/queryWithdrawResultByTradeOutId")
	@ResponseBody
	public Object queryWithdrawResultByTradeOutId(HttpServletRequest request, HttpServletResponse response,
			String tradeOutId) {
		try {
			WithdrawInfoBO result = tradeInfoService.queryWithdrawResultByTradeOutId(tradeOutId);
			return result;
		} catch (Exception e) {
			logger.error("系统异常!", e);
			WithdrawInfoBO result = new WithdrawInfoBO();
			result.setReconStatus(ReconStatusEnum.PROCESSING.getCode());
			return result;
		}
	}

	/**
	 * 得到提现记录明细
	 * 
	 * @param request
	 * @param response
	 * @return
     */
	@RequestMapping("/getWithrawHistory")
	@ResponseBody
	public Object getWithrawHistory(HttpServletRequest request, HttpServletResponse response) {
		try {
			String session3rd = request.getParameter("session3rd");
			String start = request.getParameter("start");
			String rows = request.getParameter("rows");
			String keyCode = request.getParameter("keyCode");
			String paramValues = String.format("%s%s", start, rows);
			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}

			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			CustInfoModel custInfoModel = registService.getCustInfoByCustNo(sysSessionInfo.getCustNo());
			// 提现记录..
			WithdrawPageInfoBO result = tradeInfoService.getWithdrawHistory(Integer.parseInt(start),
					Integer.parseInt(rows), custInfoModel.getCustNo());
			return result;
		} catch (Exception e) {
			logger.error("系统异常!", e);
			WithdrawPageInfoBO result = new WithdrawPageInfoBO();
			result.setResult("error");
			return result;
		}

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
		String session3rd = request.getParameter("session3rd");
		String keyCode = request.getParameter("keycode");
		String tradeOutId = request.getParameter("tradeOutId");// 客户号
		try {
			logger.info(String.format("得到提现记录处理相关信息。。。....getWithdrawalProcessInfo-keyCode:%s,tradeOutId:%s,", keyCode,
					tradeOutId));
			// 1. 检验数据合法性
			String paramValues = String.format("%s", tradeOutId);

			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}
			// 1. 查询提现状 态..
			return userCenterService.getWithdrawalProcessInfo(tradeOutId);
		} catch (ExgrainBizUncheckedException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("得到提现记录处理相关信息。。！", e);
		}
		return null;
	}

	/**
	 * 提现处理。。
	 * 
	 * @param request
	 * @param response
	 * @return
     */
	@RequestMapping("/doWeiXinAppWithraw")
	@ResponseBody
	public Object doWeiXinAppWithraw(HttpServletRequest request, HttpServletResponse response) {
		String session3rd = request.getParameter("session3rd");
		String keyCode = request.getParameter("keycode");
		String withdrawAmount = request.getParameter("withdrawAmount");// 客户号
		try {
			logger.info(String.format("提现处理。。....doWeiXinAppWithraw-keyCode:%s,withdrawAmount:%s,", keyCode,
					withdrawAmount));
			// 1. 检验数据合法性
			String paramValues = String.format("%s", withdrawAmount);

			ResultCommandSysSessionInfo checkSession = checkSessionForWxApp(request, paramValues, keyCode, session3rd);
			if (!ResultCommand.SUCCESS.equals(checkSession.getResult())) {
				return checkSession;
			}
			SysSessionInfo sysSessionInfo = checkSession.getResponseInfo();
			// 2. 查询客户信息..
			CustInfoModel infoModel = custInfoBySelfMapper
					.selectCustInfoAndCustAccountInfoByPrimaryKey(sysSessionInfo.getCustNo());
			// 3. 查询反回订单

			return shareTradeService.doWeiXinAppWithraw(withdrawAmount, infoModel,
					ChannelTypeEnum.WEIXIN_APP_CHARGE.getCode());
		} catch (ExgrainBizUncheckedException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("修改消息状态异常！", e);
		}
		return null;
	}
}
