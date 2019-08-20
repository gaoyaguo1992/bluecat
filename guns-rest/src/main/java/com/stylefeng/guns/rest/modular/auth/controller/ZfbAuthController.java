package com.stylefeng.guns.rest.modular.auth.controller;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.stylefeng.guns.rest.modular.model.ResultCommandString;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.service.RegisterService;
import com.stylefeng.guns.sharecore.common.service.SessionService;
import com.stylefeng.guns.sharecore.common.service.ZfbAuthHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付宝授权接口类
 */
@Controller
@RequestMapping("/auth")
public class ZfbAuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private RegisterService registerService;

    /**
     * session保存....
     */
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ZfbAuthHelper zfbAuthHelper;



    /**
     * 支付宝小程序授权获取的用户基本信息，注册到服务器
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/registerCust")
    @ResponseBody
    public ResultCommandString registerCust(HttpServletRequest request) throws Exception {
        ResultCommandString registerResult = new ResultCommandString();
        String avatar = request.getParameter("avatar");
        String nickName = request.getParameter("nickName");
        String gender = request.getParameter("gender");
        String province = request.getParameter("province");
        String city = request.getParameter("countryCode");
        String countryCode = request.getParameter("city");
        String userId = request.getParameter("userId");
        logger.info("方法registerCust入参：avatar:{},nickName:{},gender:{},province:{},city:{},countryCode:{}", avatar, nickName, gender, province, city, countryCode);
        if (StringUtils.isEmpty(userId)) {
            logger.warn("支付宝小程序注册客户账号失败UserId is null！");
            registerResult.setResult(ResultCommandString.FAILED);
            registerResult.setMessage("支付宝小程序注册客户账号失败，请联系客服！");
            return registerResult;
        }
        CustInfoModel custInfoModel = new CustInfoModel();
        custInfoModel.setHeadImgUrl(avatar);
        custInfoModel.setNickName(nickName);
        custInfoModel.setSex(gender);
        custInfoModel.setProvince(province);
        custInfoModel.setCity(city);
        custInfoModel.setCountry(countryCode);
        custInfoModel.setZfbUserId(userId);
        custInfoModel = registerService.registeShareCustFromZfb(custInfoModel);
        if (custInfoModel.getCustNo() == null) {
            logger.warn("支付宝小程序注册客户账号失败！");
            registerResult.setResult(ResultCommandString.FAILED);
            registerResult.setMessage("支付宝小程序注册客户账号失败，请联系客服！");
            return registerResult;
        }
        registerResult.setResult(ResultCommandString.SUCCESS);
        registerResult.setResponseInfo(custInfoModel);
        return registerResult;
    }

    /**
     * 支付小程序传入用户授权码获取客户信息。如果UserId对应的客户信息不存在，返回失败标识到客户端重新注册
     *
     * @return
     */
    @RequestMapping("/zfbLogin")
    @ResponseBody
    public ResultCommandString zfbLogin(HttpServletRequest request) throws Exception {
        ResultCommandString loginResult = new ResultCommandString();
        String authCode = request.getParameter("authCode");
        logger.info("方法zfbLogin入参：authCode:{}", authCode);
        if (StringUtils.isEmpty(authCode)) {
            logger.warn("authCode is null.....");
            loginResult.setResult(ResultCommandString.FAILED);
            loginResult.setMessage("authCode is null.....");
            return loginResult;
        }
        // 1. 服务端获取access_token、user_id
        AlipaySystemOauthTokenResponse response = zfbAuthHelper.getAccessToken(authCode);
        logger.info("服务端获取access_token、user_id,response:{}", response);
        if (response.isSuccess()) {
            logger.info("支付宝获取access_token - 调用成功");
            /**
             *  获取到用户信息后保存到数据
             *  1. 如果数据库不存在对用的 alipayUserId, 则注册
             *  2. 如果存在，则获取数据库中的信息再返回
             */
            String accessToken = response.getAccessToken();
            String alipayUserId = response.getUserId();
            logger.info("支付宝获取access_token:{},alipayUserId:{}", accessToken, alipayUserId);
            // 2. 查询该用户是否存在
            CustInfoModel custInfoModel = registerService.queryZfbCustInfoByUserId(alipayUserId);
            if (custInfoModel != null) {
                // 如果用户存在，直接返回给前端，表示登录成功
                loginResult.setResult(ResultCommandString.SUCCESS);
                loginResult.setResponseInfo(custInfoModel);
                return loginResult;
            } else {
                //用户不存在，客户端重新注册,返回获取到的userId,给小程序客户端注册
                loginResult.setResult(ResultCommandString.FAILED);
                loginResult.setResponseInfo(alipayUserId);
                loginResult.setMessage("custInfo is not exists!");
                logger.warn("custInfo is not exists!");
                return loginResult;
            }
//            // 如果用户不存在，则通过支付宝api获取用户的信息后，再注册用户到自己平台数据库
//            // 获取会员信息
//            AlipayUserInfoShareResponse aliUserInfo = zfbAuthHelper.getAliUserInfo(accessToken);
//            if (!aliUserInfo.isSuccess()) {
//                logger.warn("根据access_token获取会员信息 - 失败");
//                loginResult.setResult(ResultCommandString.FAILED);
//                loginResult.setMessage("根据access_token获取会员信息 - 失败");
//                loginResult.setResponseInfo(aliUserInfo);
//                return loginResult;
//            }
//            custInfoModel = registerService.registeShareCustFromZfb(aliUserInfo);
//            loginResult.setResult(ResultCommandString.SUCCESS);
//            // 3、生成session_3rd
//            UUID guid = UUID.randomUUID();
//            String sGuid = guid.toString().replace("-", "");
//            java.util.Date dt = new java.util.Date();
//            String session_3rd = String.format("sh%s%d", sGuid, dt.getTime());
//            SysSessionInfo sessionInfo = new SysSessionInfo();
//            sessionInfo.setId(session_3rd);
//            sessionInfo.setCreatetime(dt);
//            sessionInfo.setCustNo(custInfoModel.getCustNo());
//            sessionInfo.setZfbUserId(custInfoModel.getZfbUserId());
//            sessionInfo.setStatus(1);
//            sessionInfo.setUpdatetime(dt);
//            sessionInfo.setSessiontype(1);
//            sessionInfo.setJscode(accessToken); //支付宝accessToken
//            sessionService.insertSessionToApplication(sessionInfo);
//            loginResult.setResponseInfo(session_3rd + "_" + custInfoModel.getCustNo());
//            loginResult.setResponseInfo(custInfoModel);
//            return loginResult;

        }
        logger.warn("获取access_token - 调用失败");
        loginResult.setResult(ResultCommandString.FAILED);
        loginResult.setMessage("服务端获取access_token、user_id失败！");
        return loginResult;
    }

}
