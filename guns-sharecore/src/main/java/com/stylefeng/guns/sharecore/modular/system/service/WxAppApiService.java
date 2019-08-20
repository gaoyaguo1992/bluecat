package com.stylefeng.guns.sharecore.modular.system.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.core.util.HttpRequestUtils;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.core.util.XmlUtil;
import com.stylefeng.guns.sharecore.common.persistence.model.ObjectUtil;
import com.stylefeng.guns.sharecore.common.persistence.model.ResponseHandler;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppBaseResult;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppConstant;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppPayCallBack;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppPayInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppSessionKeyModel;
import com.stylefeng.guns.sharecore.common.persistence.model.WxAppTemplate;

import me.chanjar.weixin.mp.bean.WxMpRefundQueryResult;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import net.sf.json.JSONObject;

@Service
public class WxAppApiService {
	/**
	 * 日志对象...
	 */
	private static final Logger logger = LoggerFactory.getLogger(WxAppApiService.class);
	
	
	@Value("${sharehelper.url}/wxapp/callBackForRecharge")
	private String callbackUrl = "";
	
	@Value("${sharehelper.created.ip}")
	private String billCreatedIp = "103.44.145.245"; 

	/**
	 * 微信小程序openId
	 */
	@Value("${sharehelper.wxapp.appid}")
	private String appId;
	/**
	 * 微信小程序appSecret..
	 */
	@Value("${sharehelper.wxapp.appsecret}")	
	private String appSecret;
	
	/**
	 * 商户号id
	 */
	@Value("${sharehelper.wxapp.mchid}")
	private String mchId;
	/**
	 * api 密码约...
	 */
	@Value("${sharehelper.wxapp.apisecret}")
	private String key;

	/**
	 * 缓存小程的Token...
	 */
	@Autowired
	private WxAppAccessTokenCacheService wxAppAccessTokenCacheService;
	
	
	/**
	 * code 换取 微信服务器的session_key相关信息。。。
	 */
	public WxAppSessionKeyModel getSessionKeyByJsCode(String jsCode) {
		// https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		String url = String.format("%s?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
				WxAppConstant.WXAPP_JSCODE2SESSION, appId, appSecret, jsCode);
		try {
			String sessionJson = HttpRequestUtils.httpPost(url, null);
			//logger.info(String.format("小程序GetSessionKeyByJsCode-sessionJson-%s!",sessionJson));		
			WxAppSessionKeyModel sessionModel=com.alibaba.fastjson.JSON.parseObject(sessionJson, WxAppSessionKeyModel.class);
			return sessionModel;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("小程序GetSessionKeyByJsCode!",e);			
			//
			return null;
		}
	}
	/**
	 * 根据公众号的openId, session_key得到unionId
	 */
	public WxMpUser getUnionIdByAccessKeyOpenId(String accessKey,String openId) {
		// https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 
		String url = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN",accessKey, openId);
		try {
			String sessionJson = HttpRequestUtils.httpGet(url);
			WxMpUser sessionModel=com.alibaba.fastjson.JSON.parseObject(sessionJson, WxMpUser.class);
			return sessionModel;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("小程序GetSessionKeyByJsCode!",e);			
			//
			return null;
		}
	}

	/**
	 * code 换取 微信服务器的session_key相关信息。。。
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	 */
	public String getAccessKeyByAppidSecret(String appid,String secret) {
		WxAppAccessTokenInfo tokenInfo = wxAppAccessTokenCacheService.getToken(appid);
		 
		if(tokenInfo!=null){
			return tokenInfo.getTokenStr();
		}else{
			logger.error("token not exit");
			return null;
		}
	}
	/**
	 * 微信小程序统一下单，返回prepay_id,如返回空则失败
	 * @param payInfo 返回对象值包括（appId,prepayId,key=api密钥）
	 * @author alan.li
	 * @return
	 */
	public WxAppPayInfoModel wxAppPrePay(WxAppPayInfoModel payInfo){
		Map<String,String> map = new HashMap<>();
		
		UUID guid = java.util.UUID.randomUUID();
		String nonceStr = guid.toString().replace("-", "");
		double totalFee = Double.valueOf(payInfo.getTotalFee());
		int totalFeeInt = (int) totalFee * 100;
		
		String signStr = String.format("appid=%s&body=%s&mch_id=%s&nonce_str=%s&notify_url=%s&openid=%s&out_trade_no=%s&spbill_create_ip=%s&total_fee=%s&trade_type=%s&key=%s", 
				appId,payInfo.getBody(),mchId,nonceStr,callbackUrl,payInfo.getOpenId(),payInfo.getOutTradeNo(),billCreatedIp,String.valueOf(totalFeeInt),"JSAPI",key);

		logger.info("=========signStr===========:"+signStr);
		
		String signMD5 = MD5Util.getMd5String(signStr);
		
		map.put("appid", appId);
		map.put("mch_id", mchId);
		map.put("nonce_str",nonceStr);//随机字符串不在于32位
		map.put("sign", signMD5.toUpperCase());//签名
		map.put("body", payInfo.getBody());
		map.put("out_trade_no", payInfo.getOutTradeNo());
		map.put("total_fee", totalFeeInt+"");
		map.put("spbill_create_ip", billCreatedIp);
		map.put("notify_url", callbackUrl);
		map.put("trade_type", "JSAPI");
		map.put("openid", payInfo.getOpenId());
		// 转换xml参数
		String requestXmlStr = XmlUtil.MapToXML(map);
		logger.info("统一下单参数xml:"+requestXmlStr);
		// 返回xml结果
		String responseXmlStr = HttpRequestUtils.post(WxAppConstant.WXAPP_PREPAY, requestXmlStr);
		logger.info("统一下单返回结果xml:"+responseXmlStr);
		Map<String, String> xmlResult = XmlUtil.xmlResult(responseXmlStr);
		if(CollectionUtils.isEmpty(xmlResult)){
			return null;
		}
		String returnCode = xmlResult.get("return_code");
		if(!"SUCCESS".equals(returnCode)){
			return null;
		}
		String resultCode = xmlResult.get("result_code");
		if(!"SUCCESS".equals(resultCode)){
			return null;
		}
		String prepayId = xmlResult.get("prepay_id");
		logger.info("prePayId : "+prepayId);
		WxAppPayInfoModel result = new WxAppPayInfoModel();
		result.setAppid(appId);
		result.setPrepayId(prepayId);
		result.setKey(key);
		result.setOutTradeNo(payInfo.getOutTradeNo());
		return result;
	}

	/**
	 * 微信查询退款
	 * @param payInfo 返回对象值包括（appId,prepayId,key=api密钥）
	 * @author alan.li
	 * @return
	 */
	public WxMpRefundQueryResult refundquery(String outRefundNo){
		Map<String,String> map = new HashMap<>();
		
		UUID guid = java.util.UUID.randomUUID();
		String nonceStr = guid.toString().replace("-", "");
		
		String signStr = String.format("appid=%s&mch_id=%s&nonce_str=%s&out_refund_no=%s&key=%s", appId,mchId,nonceStr,outRefundNo,key);
		String signMD5 = MD5Util.getMd5String(signStr);
		
		map.put("appid", appId);
		map.put("mch_id", mchId);
		map.put("nonce_str",nonceStr);//随机字符串不在于32位
		map.put("sign", signMD5.toUpperCase());//签名
		map.put("out_refund_no", outRefundNo);
		map.put("out_trade_no", "");
		map.put("refund_id", "");
		map.put("transaction_id", "");		
		// 转换xml参数
		String requestXmlStr = XmlUtil.MapToXML(map);
		logger.info("查询退款xml:"+requestXmlStr);
		// 返回xml结果
		String responseXmlStr = HttpRequestUtils.post(WxAppConstant.WXAPP_REFUNDQUERU, requestXmlStr);
		logger.info("查询退款返回结果xml:"+responseXmlStr);
		Map<String, String> xmlResult = XmlUtil.xmlResult(responseXmlStr);
		if(CollectionUtils.isEmpty(xmlResult)){
			return null;
		}
		String returnCode = xmlResult.get("return_code");
		if(!"SUCCESS".equals(returnCode)){
			return null;
		}

		WxMpRefundQueryResult result=new WxMpRefundQueryResult();
		
		String resultCode = xmlResult.get("result_code");
		result.setResult_code(resultCode);
		String err_code = xmlResult.get("err_code");
		result.setErr_code(err_code);
		String err_code_des= xmlResult.get("err_code_des");
		result.setErr_code_des(err_code_des);
		String appid= xmlResult.get("appid");
		result.setAppid(appid);
		String mch_id= xmlResult.get("mch_id");
		result.setMch_id(mch_id);
		String nonce_str= xmlResult.get("nonce_str");
		result.setNonce_str(nonce_str);
		String sign= xmlResult.get("sign");
		result.setSign(sign);
		String transaction_id= xmlResult.get("transaction_id");
		result.setTransaction_id(transaction_id);
		String out_trade_no= xmlResult.get("out_trade_no");
		result.setOut_trade_no(out_trade_no);
		String total_fee= xmlResult.get("total_fee");
		result.setTotal_fee(total_fee);
		String cash_fee= xmlResult.get("cash_fee");
		result.setCash_fee(cash_fee);
		String out_refund_no_0= xmlResult.get("out_refund_no_0");
		result.setOut_refund_no_0(out_refund_no_0);
		String refund_fee_0= xmlResult.get("refund_fee_0");
		result.setRefund_fee_0(refund_fee_0);
		String refund_recv_accout_0= xmlResult.get("refund_recv_accout_0");
		result.setRefund_recv_accout_0(refund_recv_accout_0);
		String refund_status_0= xmlResult.get("refund_status_0");
		result.setRefund_status_0(refund_status_0);
		return result;
	}
	
	/**
	 * 充值成功回调
	 * 
	 * @param returnMsg
	 * @return
     */
	public WxAppPayCallBack callBackRecharge(String returnXmlMsg) {
		// 将返回xml结果转换成map
		Map<String, String> xmlResult = XmlUtil.xmlResult(returnXmlMsg);
		// 组装回调成功返回的数据，更新充值信息
		WxAppPayCallBack callBackModel = new WxAppPayCallBack();
		callBackModel.setAppid(xmlResult.get("appid"));
		callBackModel.setOut_trade_no(xmlResult.get("out_trade_no"));
		callBackModel.setBank_type(xmlResult.get("bank_type"));
		callBackModel.setTransaction_id(xmlResult.get("transaction_id"));
		callBackModel.setReturn_msg(xmlResult.get("return_msg"));
		callBackModel.setTrade_type(xmlResult.get("trade_type"));
		callBackModel.setTime_end(xmlResult.get("time_end"));
		callBackModel.setMch_id(xmlResult.get("mch_id"));
		callBackModel.setResult_code(xmlResult.get("return_code"));
		callBackModel.setSign(xmlResult.get("sign"));
		callBackModel.setReturn_code(xmlResult.get("return_code"));
		
		return callBackModel;
		
	}

	/**
	 * 校验交易返回通知，签名
	 * @param request
	 * @param response
	 * @return
	 * @throws JDOMException 
	 * @throws IOException 
	 */
	public ResponseHandler checkSign(String xmlResult) throws IOException {
		ResponseHandler resHandler = new ResponseHandler(xmlResult);
		resHandler.setKey(key);
		resHandler.setUriEncoding("UTF-8");
		return resHandler;
	}
	
	/**
	 * 
	 * <p>小程序发送微信消息模板方法</p>
	 * @param template 小程序模板对象
	 * @return 发送成功返回true,否则返回false
	 * @author #{谢文非-13620991931}
	 */
	public boolean sendTemplate(WxAppTemplate template){
		if(template == null){
			logger.warn("小程序发送微信消息失败： 入参为空");
			return false;
		}
		//accessToken
		 String accessToken =  getAccessKeyByAppidSecret(appId,appSecret);
		 if(accessToken == null || StringUtils.isEmpty(accessToken)){
			 logger.warn("小程序发送微信消息失败-->wxAppAccessToken is empty");
			 return false;
		 }
		 
	 
		// 置空prepay_id保留formId(微信接口统一用form_id做参数)
		String prepayId = template.getPrepay_id();
		if (!StringUtils.isEmpty(prepayId)) {
			template.setPrepay_id(null);
			template.setForm_id(prepayId);
		}
		Map<String,Object> jsonMap = ObjectUtil.getNotNullFiledsInfo(template);
		JSONObject object = JSONObject.fromObject(jsonMap);
		String wxSendTemplateApiUrl = String.format("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=%s",accessToken);
		String sendResult = HttpRequestUtils.httpPost(wxSendTemplateApiUrl, object,false);
		WxAppBaseResult result = com.alibaba.fastjson.JSON.parseObject(sendResult,WxAppBaseResult.class);
		//发送消息成功
		if("ok".equals(result.getErrmsg())){
			return true;
		}
		logger.error("小程序发送微信消息失败： errorCode="+result.getErrcode() +"errorMsg="+result.getErrmsg());
		return false;
	}
	
	
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
