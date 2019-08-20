package com.stylefeng.guns.sharecore.common.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝授权工具类
 */
@Service
public class ZfbAuthHelper {

    private static final Logger logger = LoggerFactory.getLogger(ZfbAuthHelper.class);

    //支付宝授权获取accessToken 官方url
    @Value("${zfb.zfbServerUrl}")
    private String zfbServerUrl;

    //refreshToken
    @Value("${zfb.refreshToken}")
    private String refreshToken;

    //小程序应用私钥
    @Value("${zfb.privateKey}")
    private String privateKey;

    //小程序应用接收通知地址
    @Value("${zfb.prepayNotifyUrl}")
    private String prepayNotifyUrl;

    //支付宝公钥
    @Value("${zfb.alipayPublicKey}")
    private String alipayPublicKey;

    //小程序appId
    @Value("${zfb.appId}")
    private String appId;


    private AlipayClient alipayClient = null;

    /**
     * 获取AlipayClient 对象，此对象实现类支付宝已经做了线程安全，做单例提高效率
     *
     * @return
     */
    public AlipayClient singleAlipayClient() {
        if (alipayClient == null) {
            synchronized (ZfbAuthHelper.class) {
                if (alipayClient == null) {
                    alipayClient = new DefaultAlipayClient(zfbServerUrl, appId, privateKey, "json", "GBK", alipayPublicKey, "RSA2");
                    return alipayClient;
                }
            }
        }
        return alipayClient;
    }

    /**
     * 调用支付宝退款查询
     *
     * @param outTradeNo   创建交易传入的商户订单号
     * @param outRequestNo 请求退款接口时
     * @return
     * @throws Exception
     */
    public boolean alipayRefundQuery(Long outTradeNo, Long outRequestNo) throws Exception {
        logger.info("调用支付宝退款查询---outTradeNo:{},outRequestNo:{}", outTradeNo, outRequestNo);
        AlipayClient alipayClient = singleAlipayClient();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + outTradeNo + "\"," +
                "\"out_request_no\":\"" + outRequestNo + "\"" +
                "  }");
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        //如果该接口返回了查询数据，则代表退款成功，如果没有查询到则代表未退款成功

        logger.info("调用支付宝退款查询----查询结果:订单金额:{}", response.getTotalAmount());
        logger.info("调用支付宝退款查询----查询结果:退款金额:{}", response.getRefundAmount());
        logger.info("调用支付宝退款查询----查询结果:response.isSuccess:{}", response.isSuccess());
        logger.info("调用支付宝退款查询----查询结果response.getSubMsg:{}", response.getSubMsg());
        logger.info("调用支付宝退款查询----查询结果response.getSubCode:{}", response.getSubCode());
        if (StringUtils.isEmpty(response.getRefundAmount())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 调用支付查询订单
     *
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    public boolean alipayTradeQuery(Long outTradeNo) throws Exception {
        logger.info("调用支付查询订单----outTradeNo:{}", outTradeNo);
        //实例化客户端
        AlipayClient alipayClient = singleAlipayClient();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        String bizContent = "{" +
                "\"out_trade_no\":\"" + outTradeNo + "\"" +
                "}";
        logger.info("调用支付查询订单----bizContent:{}", bizContent);
        request.setBizContent(bizContent);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        logger.info("调用支付查询订单----查询结果:response.isSuccess:{}", response.isSuccess());
        logger.info("调用支付查询订单----查询结果response.getSubMsg:{}", response.getSubMsg());
        logger.info("调用支付查询订单----查询结果response.getSubCode:{}", response.getSubCode());
        return response.isSuccess();
    }

    /**
     * 调用支付宝退款
     *
     * @param outTradeNo   RechargerTradeInfo  tradeId
     * @param refundAmount 退款金额
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     * @return
     */
    public boolean alipayRefund(Long outTradeNo, BigDecimal refundAmount, Long outRequestNo) throws Exception {
        logger.info("调用支付宝退款入参：outTradeNo:{},refundAmount:{},outRequestNo:{}", outTradeNo, refundAmount, outRequestNo);
        boolean isExists = this.alipayTradeQuery(outTradeNo);
        if (!isExists) {
            logger.warn("调用支付宝退款-订单不存在！");
            return Boolean.FALSE;
        }
        //实例化客户端
        AlipayClient alipayClient = singleAlipayClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        String bizContent = "{" +
                "\"out_trade_no\":\"" + outTradeNo + "\"," +
                "\"refund_amount\":" + refundAmount + "," +
                "\"refund_reason\":\"正常退款\"," +
                "\"out_request_no\":\"" + outRequestNo + "\"" +
                "  }";
        logger.info("调用支付宝退款---bizContent:{}", bizContent);
        request.setBizContent(bizContent);
        request.getNotifyUrl();//异步通知退款结果
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        logger.info("调用支付宝退款---response.getSubMsg:{}", response.getSubMsg());
        logger.info("调用支付宝退款---response.getSubCode:{}", response.getSubCode());
        logger.info("调用支付宝退款---response.isSuccess:{}", response.isSuccess());
        return response.isSuccess();
    }

    /**
     * 请求支付宝获取tradeNo
     *
     * @param userId      支付宝的userId 2088702587677658
     * @param recordId  我们平台的交易号，需要唯一
     * @param totalAmount 充值金额
     * @return
     */
    public Map<String, Object> getPrepayTradeNo(String userId, Long recordId, BigDecimal totalAmount) {
        logger.info("请求支付宝获取tradeNo 入参：userId:{},recordId:{},totalAmount:{}", userId, recordId, totalAmount);
        Map<String, Object> resultMap = new HashMap<>();
        //实例化客户端
        AlipayClient alipayClient = singleAlipayClient();
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.create.
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。
        String bizContent = "{" +
                "\"out_trade_no\":\"" + recordId + "\"," +
                "\"total_amount\":" + totalAmount + "," +
                "\"subject\":\"Alipay\"," +
                "\"buyer_id\":\"" + userId + "\"" +
                "}";
        logger.info("请求支付宝获取TradeNO参数bizContent：", bizContent);
        request.setBizContent(bizContent);
        request.setNotifyUrl(prepayNotifyUrl);
        logger.info("请求支付宝获取TradeNO参数prepayNotifyUrl:{}", prepayNotifyUrl);
        try {
            //使用的是execute
            AlipayTradeCreateResponse response = alipayClient.execute(request);
            logger.info("请求支付宝获取tradeNo返回 response:{}", response);
            String trade_no = response.getTradeNo();//获取返回的tradeNO。
            String out_trade_no = response.getOutTradeNo(); //我们平台商户订单号
            resultMap.put("zfbTradeNo", trade_no);
            resultMap.put("outTradeNo", out_trade_no);
            return resultMap;
        } catch (AlipayApiException e) {
            logger.warn("请求支付宝获取tradeNo异常！", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据支付宝authCode 获取accessToken,userId
     *
     * @param authCode
     * @return
     */
    public AlipaySystemOauthTokenResponse getAccessToken(String authCode) throws AlipayApiException {
        AlipayClient alipayClient = singleAlipayClient();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);        // 4. 填入前端传入的授权码authCode
        request.setRefreshToken(refreshToken);    // 自定义TOken
        AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 异步返回结果的验签
     *
     * @param signType 算法类型（RSA2）
     * @return
     */
    public boolean validateParam(HttpServletRequest request, String signType) throws Exception {
        //1.获取请求所有参数
        Map<String, String> paramMap = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("GBK"), "GBK");
            paramMap.put(name, valueStr);
        }
        logger.info("异步返回结果的验签-所有参数paramMap:{}", paramMap);
        //2.调用支付宝验签工具
        /**
         @param params 参数列表(包括待验签参数和签名值sign) key-参数名称 value-参数值
         @param publicKey 验签公钥
         @param charset 验签字符集
         **/
        boolean isPass = AlipaySignature.rsaCheckV1(paramMap, alipayPublicKey, "utf-8", signType);
        logger.info("异步返回结果的验签-验签结果isPass:{}", isPass);
        return isPass;
    }
}
