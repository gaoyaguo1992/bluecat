package com.stylefeng.guns.modular.wxApplication.controller;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.core.util.DatetimeUtil;
import com.stylefeng.guns.modular.system.service.DeviceInfoHelperService;
import com.stylefeng.guns.modular.system.service.IDeviceInfoService;
import com.stylefeng.guns.modular.system.service.IMerchantInfoService;
import com.stylefeng.guns.modular.wxApplication.bo.RegisterTerminalByCodeBO;
import com.stylefeng.guns.modular.wxApplication.service.MerchantInfoHelperService;
import com.stylefeng.guns.modular.wxApplication.service.TradeInfoHelperService;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.*;
import com.stylefeng.guns.sharecore.common.service.WithdrawService;
import com.stylefeng.guns.sharecore.common.utils.SysUtil;
import com.stylefeng.guns.sharecore.modular.system.constants.*;
import com.stylefeng.guns.sharecore.modular.system.dao.*;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;
import com.stylefeng.guns.sharecore.modular.system.model.*;
import com.stylefeng.guns.sharecore.modular.system.service.MerchantService;
import com.stylefeng.guns.sharecore.modular.system.service.ShareDeviceService;
import com.stylefeng.guns.sharecore.modular.system.service.ShareFeeService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理微信公众号相关业务控制器
 *
 * @author fengshuonan
 * @Date 2019-01-20 02:37:34
 */
@Controller
@RequestMapping("/wx")
public class WxAppController extends WxAppBaseController {
   

    /**
     * 公目录...
     */
    @Value("${sharehelper.url}")
    private String rootUrl = "";
    /**
     * 处理日志
     */
    private static final Logger logger = LoggerFactory.getLogger(WxAppController.class);
    /**
     * 共享充电器.mapper..
     */
    @Autowired
    private ShareChargerModelBySelfMapper shareChargerModelBySelfMapper;
    /**
     * 共享充电器mapper
     */
    @Autowired
    private ShareChargerModelMapper shareChargerModelMapper;
    /**
     * 处理设备mapper
     */
    @Autowired
    private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
    /**
     * 设备数据库操作..
     */
    @Autowired
    private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
    /**
     * 商户助手类...
     */
    @Autowired
    @Qualifier("gunsadmin.MerchantInfoHelperService")
    private MerchantInfoHelperService merchantInfoHelperService;
    /**
     * 商户数据库操作...
     */
    @Autowired
    private MerchantInfoModelMapper merchantInfoModelMapper;
    /**
     * 交易助手类..
     */
    @Autowired
    private TradeInfoHelperService tradeInfoHelperService;
    /**
     * 设备信息服务。。。
     */
    @Autowired
    private ShareDeviceService shareDeviceService;

    /**
     * 昨天晚天交易数据...
     */
    @Autowired
    private DevTradeGatherInfoModelBySelfMapper devTradeGatherInfoModelBySelfMapper;
    /**
     * 设备服务类
     */
    @Autowired
    private DeviceInfoHelperService deviceInfoHelperService;
    /**
     * 设备
     */
    @Autowired
    private IDeviceInfoService deviceInfoService;
    /**
     * 处理费用
     */
    @Autowired
    private ShareFeeService shareFeeService;
    /**
     * 客户操作mapper...
     */
    @Autowired
    private CustInfoModelMapper custInfoModelMapper;
    /**
     * 客户账户
     */
    @Autowired
    private CustAccountModelMapper custAccountModelMapper;
    /**
     * 分润操作类..
     */
    @Autowired
    private MerchantProfitDayModelBySelfMapper merchantProfitDayModelBySelfMapper;
    /**
     *
     */
    @Autowired
    private WithdrawApplyRecordModelMapper withdrawApplyRecordModelMapper;
    /**
     * 提现..
     */
    @Autowired
    private MerWithdrawMetadataModelMapper merWithdrawMetadataModelMapper;
    /**
     * 提现处理类
     */
    @Autowired
    private WithdrawService withdrawService;
    /**
     * 商户服务类
     */
    @Autowired
    private MerchantService merchantService;
    /**
     * 代理商申请
     */
    @Autowired
    private MerchantApplyFormModelMapper merchantApplyFormModelMapper;

    @Autowired
    private IMerchantInfoService merchantInfoService;

    /**
     * 省市区数据库查询..
     */
    @Autowired
    private ProvinceCityZoneInfoModelMapper provinceCityZoneInfoModelMapper;

    /**
     * <p>
     * 微信JS-SDK获取signature签名以及config配置
     * </p>
     *
     * @param request
     * @return
     */
    @RequestMapping("/getWxJsapiSignatureInfo")
    @ResponseBody
    public Object getWxJsapiSignatureInfo(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        result.setResult(ResultCommandBaseObject.FAILED);
        try {
            String url = request.getParameter("url");
            logger.info("获取signature签名以及config配置入参{}", url);
            WxJsapiSignature ws = wxMpService.createJsapiSignature(url);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            result.setResponseInfo(ws);
            return result;
        } catch (Exception e) {
            logger.error("获取signature签名以及config配置失败", e);
            result.setMessage("获取signature签名以及config配置失败");
            return result;
        }
    }

    /**
     * 处理微信公众号回调处理..
     */
    @RequestMapping("/index")
    @ResponseBody
    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("处理微信公众号回调处理..--");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            // 1、获到参数..
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String signature = request.getParameter("signature");
            String nonce = request.getParameter("nonce");
            String timestamp = request.getParameter("timestamp");
            String msg = String.format("处理微信公众号回调处理.index.-code:%s,state:%s,signature:%s,nonce:%s,timestamp:%s-", code,
                    state, signature, nonce, timestamp);
            logger.info(msg);
            // 2. 验证签名是否正确...
            if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
                // 消息签名不正确，说明不是公众平台发过来的消息
                logger.info("处理微信公众号回调处理.index.------非法请求");
                response.getWriter().println("非法请求");
                return;
            }

            // 3.判断是否是每一次公众号配置认证...
            String echostr = request.getParameter("echostr");
            if (echostr != null && StringUtils.isNotBlank(echostr)) {
                logger.info("处理微信公众号回调处理.index.------非法请求");
                // 说明是一个仅仅用来验证的请求，回显echostr
                response.getWriter().println(echostr);
                return;
            }
            // 3. 处理其它信息
            String encrypt_type = request.getParameter("encrypt_type");
            String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
                    : request.getParameter("encrypt_type");
            // 3.1 处理未加密的信息
            if ("raw".equals(encryptType)) {
                // 明文传输的消息
                WxMpXmlMessage imsg = WxMpXmlMessage.fromXml(request.getInputStream());
                router.rule().async(true).event(WxConsts.EVT_SUBSCRIBE).handler(wxMpSubcribeHandler).end().rule()
                        .async(false).handler(wxCustServiceHandler).end();
                WxMpXmlOutMessage wxout = router.route(imsg);
                if (wxout != null) {
                    response.getWriter().write(wxout.toXml());
                }
                return;
            }
            // 3.2 处理加密码信息...
            if ("aes".equals(encryptType)) {
                // 是aes加密的消息
                String msgSignature = request.getParameter("msg_signature");
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage,
                        timestamp, nonce, msgSignature);
                router.rule().async(true).event(WxConsts.EVT_SUBSCRIBE).handler(wxMpSubcribeHandler).end().rule()
                        .async(false).handler(wxCustServiceHandler).end();
                WxMpXmlOutMessage wxout = router.route(inMessage);
                if (wxout != null) {
                    response.getWriter().write(wxout.toXml());
                }
                return;
            }
            logger.info(String.format("处理微信公众号回调处理.index.------encrypt_type:%s", encrypt_type));
            response.getWriter().println("不可识别的加密类型");
            return;
        } catch (Exception e) {
            logger.error("处理微信公众号回调处理.index. 系统异常!", e);
        }
    }

    /**
     * 微信公众号跳转...
     */
    @RequestMapping("/registerApplyJump")
    public Object registerApplyJump(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 设备类型
        logger.info("微信公众号跳转.registerApplyJump.");
        String urlForBusiness = "/wx/registerApplyJump?flag=1";
        try {
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "registerApplyJump");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String openId = request.getParameter("openId");
            String url = this.shareRootUrl + "/wx/registerApplyJump";
            Boolean splitChar = false;
            if (openId != null) {
                if (splitChar) {
                    url = url + "?openId=" + openId;
                    splitChar = true;
                } else {
                    url = url + "&openId=" + openId;
                }
            }
            if (code != null) {
                if (splitChar) {
                    url = url + "?code=" + code;
                    splitChar = true;
                } else {
                    url = url + "&code=" + code;
                }
            }
            if (state != null) {
                if (splitChar) {
                    url = url + "?state=" + state;
                    splitChar = true;
                } else {
                    url = url + "&state=" + state;
                }
            }
            try {
                WxJsapiSignature ws = wxMpService.createJsapiSignature(url);
                model.addAttribute("ws", ws);
            } catch (Exception e) {
                logger.info("微信公众号跳转。url:{}", e);
                // TODO: handle exception
                model.addAttribute("ws", new WxJsapiSignature());
            }
            // 2. 微信授权成功..得到对应的unionid和openid..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            openId = mapUserInfo.get("openId");
            // 得到客户信息
            CustInfoModel custInfoModel = (unionId == null || unionId.isEmpty()) ? registerService.getCustInfo(openId)
                    : registerService.getCustInfoByUnionId(unionId);
            if (custInfoModel == null) {
                model.addAttribute("content", "您未关注公众号，请先关注公众号!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            logger.info("微信公众号跳转.registerApplyJump..unionId:{},openId{},custInfoModel:{}", unionId, openId,
                    custInfoModel);
            // 得到商户
            List<CustMerchantRefInfoModel> custMerRefForList = custMerchantRefInfoBySelfModelMapper
                    .selectByCustNo(custInfoModel.getCustNo());
            // 3.根据用户是否有商户来确定是否跳转商户注册界面...
            String industryCategoryCodeFrHtmlOptions = getIndustryCategoryCodeForHtmlOptions(0);
            model.addAttribute("industryCategoryCodeFrHtmlOptions", industryCategoryCodeFrHtmlOptions);
            model.addAttribute("reqUrlRoot", rootUrl);
            if (custMerRefForList == null || custMerRefForList.size() <= 0) {
                // 3.1 新用户跳转到注册终端店铺的界面...
                model.addAttribute("custInfoModel", custInfoModel);
                model.addAttribute("custNO", custInfoModel.getCustNo());
                model.addAttribute("unionId", unionId);
                model.addAttribute("openId", openId);
                model.addAttribute("selectedMerchantType", MerchantTypeEnum.TERMINAL.getCode());
                return getPrefix()+  WxAppTerminalController.PREFIXBASE + "register1.html";
            } else {
                // 3.2 已经注册过相关的商户， 找到默认的商户跳转过去...
                CustMerchantRefInfoModel item = custMerRefForList.get(0);
                MerchantInfoModel merchantInfo = item != null
                        ? merchantInfoModelMapper.selectByPrimaryKey(item.getMerchantId()) : new MerchantInfoModel();
                //
                return jumpToMerchantMainPage(model, custInfoModel, custMerRefForList, merchantInfo);
            }
        } catch (Exception e) {
            logger.error("微信公众号跳转.registerApplyJump.", e);
            // TODO: handle exception
            model.addAttribute("content", "您未关注公众号，请先关注公众号!");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 终端绑定设备。。。
     */
    @RequestMapping("/terminalBindDevices")
    public Object terminalBindDevices(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 设备类型
        logger.info("微信公众号跳转终端绑定设备。.terminalBindDevices.");
        String urlForBusiness = "/wx/terminalBindDevices?flag=1";
        try {
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "registerApplyJump");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String openId = request.getParameter("openId");
            String url = this.shareRootUrl + "/wx/terminalBindDevices";
            Boolean splitChar = false;
            if (openId != null) {
                if (splitChar) {
                    url = url + "?openId=" + openId;
                    splitChar = true;
                } else {
                    url = url + "&openId=" + openId;
                }
            }
            if (code != null) {
                if (splitChar) {
                    url = url + "?code=" + code;
                    splitChar = true;
                } else {
                    url = url + "&code=" + code;
                }
            }
            if (state != null) {
                if (splitChar) {
                    url = url + "?state=" + state;
                    splitChar = true;
                } else {
                    url = url + "&state=" + state;
                }
            }
            try {
                WxJsapiSignature ws = wxMpService.createJsapiSignature(url);
                model.addAttribute("ws", ws);
            } catch (Exception e) {
                logger.info("微信公众号跳转。url:{}", e);
                // TODO: handle exception
                model.addAttribute("ws", new WxJsapiSignature());
            }
            // 2. 微信授权成功..得到对应的unionid和openid..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            openId = mapUserInfo.get("openId");
            // 得到客户信息
            CustInfoModel custInfoModel = (unionId == null || unionId.isEmpty()) ? registerService.getCustInfo(openId)
                    : registerService.getCustInfoByUnionId(unionId);
            if (custInfoModel == null) {
                model.addAttribute("content", "您未关注公众号，请先关注公众号!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            logger.info("微信公众号跳转.terminalBindDevices..unionId:{},openId{},custInfoModel:{}", unionId, openId,
                    custInfoModel);
            // 3.根据用户是否有商户来确定是否跳转商户注册界面...
            String industryCategoryCodeFrHtmlOptions = getIndustryCategoryCodeForHtmlOptions(0);
            model.addAttribute("industryCategoryCodeFrHtmlOptions", industryCategoryCodeFrHtmlOptions);
            model.addAttribute("reqUrlRoot", rootUrl);

            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("custNO", custInfoModel.getCustNo());
            model.addAttribute("unionId", unionId);
            model.addAttribute("openId", openId);
            model.addAttribute("selectedMerchantType", MerchantTypeEnum.TERMINAL.getCode());
            return getPrefix() + "common/terminalBindDeices.html";

        } catch (Exception e) {
            logger.error("微信公众号跳转.terminalBindDevices.", e);
            // TODO: handle exception
            model.addAttribute("content", "您未关注公众号，请先关注公众号!");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 终端绑定设备。。。
     */
    @RequestMapping("/applyMerchantForm")
    public Object applyMerchantForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 设备类型
        logger.info("微信公众号跳转终端绑定设备。.terminalBindDevices.");
        String type = request.getParameter("type");
        String urlForBusiness = "/wx/applyMerchantForm?flag=1&type=" + type;
        try {
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "applyMerchantForm");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String openId = request.getParameter("openId");
            String url = this.shareRootUrl + "/wx/applyMerchantForm";
            Boolean splitChar = false;
            if (openId != null) {
                if (splitChar) {
                    url = url + "?openId=" + openId;
                    splitChar = true;
                } else {
                    url = url + "&openId=" + openId;
                }
            }
            if (code != null) {
                if (splitChar) {
                    url = url + "?code=" + code;
                    splitChar = true;
                } else {
                    url = url + "&code=" + code;
                }
            }
            if (state != null) {
                if (splitChar) {
                    url = url + "?state=" + state;
                    splitChar = true;
                } else {
                    url = url + "&state=" + state;
                }
            }
            if (type != null) {
                if (splitChar) {
                    url = url + "?type=" + type;
                    splitChar = true;
                } else {
                    url = url + "&type=" + type;
                }
            }
            try {
                WxJsapiSignature ws = wxMpService.createJsapiSignature(url);
                model.addAttribute("ws", ws);
            } catch (Exception e) {
                logger.info("微信公众号跳转。url:{}", e);
                // TODO: handle exception
                model.addAttribute("ws", new WxJsapiSignature());
            }
            // 2. 微信授权成功..得到对应的unionid和openid..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            openId = mapUserInfo.get("openId");
            // 得到客户信息
            CustInfoModel custInfoModel = (unionId == null || unionId.isEmpty()) ? registerService.getCustInfo(openId)
                    : registerService.getCustInfoByUnionId(unionId);
            if (custInfoModel == null) {
                model.addAttribute("content", "您未关注公众号，请先关注公众号!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            logger.info("微信公众号跳转.applyMerchantForm..unionId:{},openId{},custInfoModel:{},type:{}", unionId, openId,
                    custInfoModel, type);
            // 3.根据用户是否有商户来确定是否跳转商户注册界面...
            String industryCategoryCodeFrHtmlOptions = getIndustryCategoryCodeForHtmlOptions(0);
            model.addAttribute("industryCategoryCodeFrHtmlOptions", industryCategoryCodeFrHtmlOptions);
            model.addAttribute("reqUrlRoot", rootUrl);

            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("custNO", custInfoModel.getCustNo());
            model.addAttribute("unionId", unionId);
            model.addAttribute("openId", openId);
            if (type == null || type.equals("1")) {
                // 代理商合作
                return getPrefix() + "common/applyAgent.html";
            } else {
                // 申请商家合作
                return getPrefix() + "common/applyMerchantCooperation.html";
            }
        } catch (Exception e) {
            logger.error("微信公众号跳转.applyMerchantForm.", e);
            // TODO: handle exception
            model.addAttribute("content", "您未关注公众号，请先关注公众号!");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 处理扫码充电器二维码..并返回充电器二维码 注册
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doScanRlstForRegister")
    @ResponseBody
    public Object doScanRlstForRegister(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        result.setResult(ResultCommandBaseObject.SUCCESS);
        try {
            // 0 判断是否登录...
            Map<String, String> map = getLoginCustInfo(request);
            if (map == null) {
                logger.info("处理扫码充电器二维码..并返回充电器二维码 注册  doScanRlstoFrRegister-未登录，验证扫描二维码失败，请重新登录");
                result.setMessage("未登录，验证扫描二维码失败，请重新登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String scanResult = request.getParameter("scanResult");
            logger.info("处理扫码充电器二维码..并返回充电器二维码 注册  doScanRlstForRegister-scanResult:{} ", scanResult);
            scanResult = (scanResult != null) ? scanResult.toUpperCase() : "";
            String chargerId = scanResult.indexOf("ID=") != -1 ? scanResult.substring(scanResult.indexOf("ID=") + 3)
                    : scanResult;
            // 1.解决二维码失败...
            if (chargerId == null || chargerId.isEmpty()) {
                result.setMessage("解析扫描二维码失败，未找到充电器【" + chargerId + "】，请联系客服!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Long lngChargerId = 0L;
            try {
                lngChargerId = Long.parseLong(chargerId);
            } catch (Exception e) {
                logger.error("处理扫码充电器二维码..并返回充电器二维码 注册  doScanRlstForRegister--- ", e);
                // TODO: handle exception
                result.setMessage("解析扫描二维码失败，未找到充电器【" + chargerId + "】，请联系客服!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 判断充电器在数据库中是否存在.
            ShareChargerModel chrgModel = shareChargerModelMapper.selectByPrimaryKey(lngChargerId);
            if (chrgModel == null) {
                result.setMessage("您扫描的充电器【" + chargerId + "】在系统中不在存在，请联系客服!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (chrgModel.getDeviceId() == null || chrgModel.getDeviceId().equals(0L)) {
                result.setMessage("您扫描的充电器【" + chargerId + "】未绑定设备，请联系客服绑定设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            result.setResponseInfo(chrgModel.getDeviceId());
            logger.info("处理扫码充电器二维码..并返回充电器二维码 注册  doScanRlstForRegister 返回结果为:{}", result);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.error("处理扫码充电器二维码..并返回充电器二维码 注册 系统异常!", e);
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage(e.getMessage());
            logger.info("处理扫码充电器二维码..并返回充电器二维码 注册  失败 doScanRlstForRegister 返回结果为:{}", result);
            return result;

        } catch (Exception e) {
            logger.error("处理扫码充电器二维码..并返回充电器二维码 注册 系统异常!", e);
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("扫描失败,请联系客服!");
            logger.info("处理扫码充电器二维码..并返回充电器二维码 注册  失败  doScanRlstForRegister 返回结果为:{}", result);
            return result;
        }
    }

    /**
     * 终端注册保存...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/registerTerminalForSave")
    @ResponseBody
    public Object registerTerminalForSave(HttpServletRequest request, HttpServletResponse response) {
        // TODO 缺少安全认证
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        result.setResult(ResultCommandBaseObject.SUCCESS);
        String registerFlag = (String) request.getSession().getAttribute("registerFlag");
        try {
            if ("1".equals(registerFlag)) {
                result.setResult(ResultCommandBaseObject.FAILED);
                result.setMessage("您的注册信息已提交，请不要重复提交!");
                return result;
            }
            // 0. 获取参数
            request.getSession().setAttribute("registerFlag", "1");
            // 接收参数...
            String phoneNumber = request.getParameter("telNo");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");
            Long merchantId = null;
            String openId = request.getParameter("openId");
            String comment = request.getParameter("comment");
            Integer merchantType = StringUtils.isEmpty(request.getParameter("merchantType")) ? null
                    : Integer.valueOf(request.getParameter("merchantType"));
            String startShopTime = request.getParameter("startShopTime");
            String endShopTime = request.getParameter("endShopTime");
            String provinceName = request.getParameter("provinceName");
            String cityName = request.getParameter("cityName");
            String districtName = request.getParameter("districtName");
            String perConsume = request.getParameter("perConsume"); // 人均消费
            String storePhoneNo = request.getParameter("storePhoneNo");// 店铺对外电话
            String deviceNoStr = request.getParameter("deviceNo");// 扫充电器对应的设备..
            Integer industryCategoryCode = StringUtils.isEmpty(request.getParameter("industryCategoryCode")) ? null
                    : Integer.valueOf(request.getParameter("industryCategoryCode"));

            logger.info("终端注册保存...openId:{},merchantType{},phoneNumber:{},merchantId:{},deviceNoStr:{}", openId,
                    merchantType, phoneNumber, merchantId, deviceNoStr);
            // 2. 判断是否是登录
            Map<String, String> map = getLoginCustInfo(request);
            if (map == null) {
                logger.info("终端注册保存...  registerTerminalForSave-未登录，验证扫描二维码失败，请重新登录");
                result.setMessage("未登录，终端店铺注册失败，请重新登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = map.get("unionId");
            String custNoForLogin = map.get("custNo");
            openId = map.get("openId");
            // 3. 参数设置。k

            if (merchantId == null && StringUtils.isEmpty(phoneNumber)) {
                result.setMessage("手机号码不能为空!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (StringUtils.isEmpty(request.getParameter("personName"))) {
                result.setMessage("联系人姓名不能为空!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            if (!startShopTime.equals("") && !endShopTime.equals("")) {
                String[] starArray = startShopTime.split(":");
                String[] endArray = endShopTime.split(":");
                Integer start = new Integer(starArray[0]) * 100 + new Integer(starArray[1]);
                Integer end = new Integer(endArray[0]) * 100 + new Integer(endArray[1]);
                if (start >= end) {
                    result.setMessage("抱歉，您的开始营业时间应该小于结束营业时间!");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            }
            ShareDeviceInfoModel di = null;
            // 商户信息...
            MerchantInfoModel merInfoTmpModel = new MerchantInfoModel();
            merInfoTmpModel.setAddr(request.getParameter("addr"));
            merInfoTmpModel.setName(request.getParameter("name"));
            merInfoTmpModel.setRemark(comment);
            merInfoTmpModel.setMerchantType(merchantType.byteValue());
            merInfoTmpModel.setMerchantTypeCn(MerchantTypeEnum.getDesc(merchantType));
            merInfoTmpModel.setPersonName(request.getParameter("personName"));
            merInfoTmpModel.setTelNo(request.getParameter("telNo"));
            merInfoTmpModel.setIndustryCategoryCode(industryCategoryCode.byteValue());
            merInfoTmpModel.setIndustryCategoryCn(IndustryCategoryEnum.getDesc(industryCategoryCode));
            merInfoTmpModel.setStatus(new Integer(MerchantStatusEnum.REGISTED.getCode()).byteValue());

            merInfoTmpModel.setStartShopTime(startShopTime);
            merInfoTmpModel.setEndShopTime(endShopTime);
            merInfoTmpModel.setProvince(provinceName);
            merInfoTmpModel.setCity(cityName);
            merInfoTmpModel.setZone(districtName);

            if (perConsume != null && !"".equals(perConsume)) {
                merInfoTmpModel.setPerConsume(new BigDecimal(perConsume));// 人均消费
            }
            merInfoTmpModel.setStorePhoneNo(storePhoneNo);// 店铺对外电话
            merInfoTmpModel.setCreateTime(new Date());
            // 客户信息。。。
            CustInfoModel ci = registerService.getCustInfo(openId);
            BigDecimal latitudeX = null, longitudeY = null;
            try {
                latitudeX = new BigDecimal(latitude);
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                longitudeY = new BigDecimal(longitude);
            } catch (Exception e) {
                // TODO: handle exception
            }
            /**
             * 注册保存...
             */
            merchantInfoHelperService.registerTerminal(ci, merInfoTmpModel, di, latitudeX, longitudeY);
            result.setResponseInfo(merInfoTmpModel.getId());
            result.setMessage("终端店铺注册成功!");
            try {
                WxMpCustomMessage wm = new WxMpCustomMessage();
                wm.setToUser(openId);
                wm.setTitle("共享充电");
                wm.setContent("注册共享充电成功.");
                wxMpService.customMessageSend(wm);
            } catch (Exception e) {
                // TODO: handle exception
            }

            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage(e.getMessage());
            return result;

        } catch (Exception e) {
            logger.error("系统异常!", e);
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("注册店铺终端失败,系统异常!");
            return result;
        } finally {
            request.getSession().removeAttribute("registerFlag");
        }
    }

    /**
     * 选择商户列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectMerchantList", method = {RequestMethod.POST, RequestMethod.GET})
    public Object selectMerchantList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/selectMerchantList?flag=1";
        try {
            String custNo = request.getParameter("custNo");
            String merchantId = request.getParameter("merchantId");
            urlForBusiness = urlForBusiness + "&custNo=" + custNo + "&merchantId=" + merchantId;
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "selectMerchantList");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 2. 处理登录信息...
            logger.info("商户列表--selectMerchantList,custNo:{}", custNo);
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String openId = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            if (custNo == null || !custNo.equalsIgnoreCase(custNoForLogin)) {
                logger.error("缓存中不存在该客户--selectMerchantList, custNo:{},openId:{},unionId:{},custNoForLogin:{}", custNo,
                        openId, unionId, custNoForLogin);
                model.addAttribute("content", "输入的客户信息和您登录的信息不一至，获取商户信息失败,请重新登录!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3. 得到登录账号的商户...
            List<CustMerchantRefInfoModel> custMerchants = custMerchantRefInfoBySelfModelMapper
                    .selectByCustNo(Long.valueOf(custNo));
            List<CustMerchantRefInfoModelBo> list = new ArrayList<>();
            CustMerchantRefInfoModelBo bo = null;
            for (CustMerchantRefInfoModel custMerchantRefInfoModel : custMerchants) {
                bo = new CustMerchantRefInfoModelBo();
                // BeanUtils.copyProperties(deviceInfoModel,deviceInfoTmp);
                // bo.setOpenId(openId);
                bo.setCreateId(custMerchantRefInfoModel.getCreateId());
                bo.setCreateTime(custMerchantRefInfoModel.getCreateTime());
                bo.setCustNo(custMerchantRefInfoModel.getCustNo());
                bo.setCustType(custMerchantRefInfoModel.getCustType());
                bo.setIsDefault(custMerchantRefInfoModel.getIsDefault());
                bo.setMerchantId(custMerchantRefInfoModel.getMerchantId());
                bo.setMerchantName(custMerchantRefInfoModel.getMerchantName());
                bo.setMerchantType(custMerchantRefInfoModel.getMerchantType());
                bo.setMerchantTypeCn(custMerchantRefInfoModel.getMerchantTypeCn());

                if (merchantId != null
                        && merchantId.equalsIgnoreCase(custMerchantRefInfoModel.getMerchantId().toString())) {
                    // 从session中获取当前登录的商户ID,并设置标识
                    bo.setLastLoginFlag(IsLastLoginEnum.YES.getCode());
                } else {
                    bo.setLastLoginFlag(IsLastLoginEnum.NO.getCode());
                }
                list.add(bo);
            }
            model.addAttribute("reqUrlRoot", rootUrl);
            model.addAttribute("custMerchants", list);
            return getPrefix() + "common/switchAccountList.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("账号业务异常--selectMerchantList", e);
            model.addAttribute("content", "查询账户商户信息列表失败，请联系客户 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("账号业务异常--selectMerchantList", e);
            model.addAttribute("content", "查询账户商户信息列表失败，请联系客户 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 根据用户选跳转.到对应的商户首页...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/goMerchantLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public Object goMerchantLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/goMerchantLogin?flag=1";
        try {
            // 0. 接收参数...
            String custNo = request.getParameter("custNo");
            String merchantId = request.getParameter("merchantId");
            urlForBusiness = urlForBusiness + "&custNo=" + custNo + "&merchantId=" + merchantId;
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "goMerchantLogin");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 2. 判断参数是否正确..
            logger.info("根据用户选跳转..列表--goMerchantLogin,custNo:{},merchantId:{}", custNo, merchantId);
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String openId = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("根据用户选跳转..列表--goMerchantLogin,custNo:{},merchantId:{},custNoForLogin:{}", custNo, merchantId,
                    custNoForLogin);
            if (custNo == null || !custNo.equalsIgnoreCase(custNoForLogin)) {
                logger.error("根据用户选跳转.--goMerchantLogin, custNo:{},openId:{},unionId:{},custNoForLogin:{}", custNo,
                        openId, unionId, custNoForLogin);
                model.addAttribute("content", "选择的商户信息和您登录的信息不一至，切换失败,请重新登录!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            if (merchantId == null) {
                logger.error(
                        "根据用户选跳转.--goMerchantLogin, custNo:{},openId:{},unionId:{},custNoForLogin:{},merchantId:{}",
                        custNo, openId, unionId, custNoForLogin, merchantId);
                model.addAttribute("content", "未选择切换的商户，请重新登录!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3. 跳转..
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNo));
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(Long.valueOf(merchantId));
            CustMerchantRefInfoModel custMerRefModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerRefModel == null) {
                model.addAttribute("content", "选择切换的商户信息未绑定，请联系管理员绑定!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper
                    .selectByPrimaryKey(custMerRefModel.getMerchantId());
            // 设置默认商户
            merchantInfoHelperService.updateCustMerRefDefault(custMerRefModel);
            // 4. 重新获到商户信息..
            List<CustMerchantRefInfoModel> custMerRefForList = custMerchantRefInfoBySelfModelMapper
                    .selectByCustNo(custInfoModel.getCustNo());
            // 5. 跳转..
            return jumpToMerchantMainPage(model, custInfoModel, custMerRefForList, merchantInfoModel);
        } catch (ExgrainBizUncheckedException e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 商户交易查询(查询交易订单) 查询交易订单信息....
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMerchantOrderInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getMerchantOrderInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/getMerchantOrderInfo?flag=1";
        try {
            // 0. 接收参数...
            String custNo = request.getParameter("custNo");
            String merchantId = request.getParameter("merchantId");
            String openId = request.getParameter("openId");
            urlForBusiness = String.format("%s&custNo=%s&merchantId=%s&openId=%s", urlForBusiness, custNo, merchantId,
                    openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getMerchantOrderInfo");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 2. 判断参数是否正确..
            logger.info("查询订单-goMerchantLogin,custNo:{},merchantId:{}", custNo, merchantId);
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("查询订单--getMerchantOrderInfo,custNo:{},merchantId:{},custNoForLogin:{}", custNo, merchantId,
                    custNoForLogin);
            // 3.判断参数是否正确
            if (custNo == null || !custNoForLogin.equals(custNo)) {
                logger.info("登录客户号与提交查询客户号不一至，交易订单查询失败getMerchantOrderInfo,custNo:{},custNoForLogin:{}", custNo,
                        custNoForLogin);
                model.addAttribute("content", "登录客户号与提交查询客户号不一至，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNo));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户号在系统中不存在，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，请联系管理员绑定商户号!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 4 返回
            // 生成操
            model.addAttribute("reqUrlRoot", rootUrl);
            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            model.addAttribute("deviceId", "");
            model.addAttribute("isParent", "false");
            return getPrefix() + "common/merchantOrdersInfoQuery.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 批理操作设备...代理商外批理操作.
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deviceThreeBatchManage", method = {RequestMethod.POST, RequestMethod.GET})
    public Object deviceThreeBatchManage(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/deviceThreeBatchManage?flag=1";
        try {
            // 0. 接收参数...
            String custNo = request.getParameter("custNo");
            String merchantId = request.getParameter("merchantId");
            String openId = request.getParameter("openId");
            urlForBusiness = String.format("%s&custNo=%s&merchantId=%s&openId=%s", urlForBusiness, custNo, merchantId,
                    openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "deviceThreeBatchManage");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 2. 判断参数是否正确..
            logger.info("批理操作设备-deviceThreeBatchManage,custNo:{},merchantId:{}", custNo, merchantId);
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("批理操作设备-deviceThreeBatchManage,custNo:{},merchantId:{},custNoForLogin:{}", custNo, merchantId,
                    custNoForLogin);
            // 3.判断参数是否正确
            if (custNo == null || !custNoForLogin.equals(custNo)) {
                logger.info("登录客户号与提交查询客户号不一至，交易订单查询失败getMerchantOrderInfo,custNo:{},custNoForLogin:{}", custNo,
                        custNoForLogin);
                model.addAttribute("content", "登录客户号与提交查询客户号不一至，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNo));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户号在系统中不存在，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，请联系管理员绑定商户号!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 4 返回
            // 生成操
            model.addAttribute("reqUrlRoot", rootUrl);
            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/deviceThreeBatchManage.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 批理操作设备...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deviceBatchManage", method = {RequestMethod.POST, RequestMethod.GET})
    public Object deviceBatchManage(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/deviceBatchManage?flag=1";
        try {
            // 0. 接收参数...
            String custNo = request.getParameter("custNo");
            String merchantId = request.getParameter("merchantId");
            String openId = request.getParameter("openId");
            urlForBusiness = String.format("%s&custNo=%s&merchantId=%s&openId=%s", urlForBusiness, custNo, merchantId,
                    openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "deviceBatchManage");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 2. 判断参数是否正确..
            logger.info("批理操作设备-deviceBatchManage,custNo:{},merchantId:{}", custNo, merchantId);
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("批理操作设备-deviceBatchManage,custNo:{},merchantId:{},custNoForLogin:{}", custNo, merchantId,
                    custNoForLogin);
            // 3.判断参数是否正确
            if (custNo == null || !custNoForLogin.equals(custNo)) {
                logger.info("登录客户号与提交查询客户号不一至，交易订单查询失败getMerchantOrderInfo,custNo:{},custNoForLogin:{}", custNo,
                        custNoForLogin);
                model.addAttribute("content", "登录客户号与提交查询客户号不一至，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNo));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户号在系统中不存在，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，交易订单查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，请联系管理员绑定商户号!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 4 返回
            // 生成操
            model.addAttribute("reqUrlRoot", rootUrl);
            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            return getPrefix() + "common/deviceBatchManage.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("批量操作--deviceBatchManage", e);
            model.addAttribute("content", e.getMessage());
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("批量操作失败--deviceBatchManage", e);
            model.addAttribute("content", "加载批量操作失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 显示订单详细
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getShowOrderDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getShowOrderDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        result.setResult(ResultCommandBaseObject.SUCCESS);

        // 入参
        String orderId = request.getParameter("orderId");
        String merchantId = request.getParameter("merchantId");
        logger.info("方法getShowOrderDetail入参orderId:{},merchantId:{}", orderId, merchantId);
        if (StringUtils.isEmpty(orderId)) {
            logger.warn("方法getShowOrderDetail orderId is null");
            model.addAttribute("content", "获取不到订单号，请确认操作合法！");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        if (StringUtils.isEmpty(merchantId)) {
            logger.warn("方法getShowOrderDetail merchantId is null");
            model.addAttribute("content", "获取不到当前登陆商户号，请确认操作合法！");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        MerchantOrderRecordInfoBO orderBO = null;
        MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));

        try {
            orderBO = tradeInfoHelperService.getShowOrderDetail(orderId);
        } catch (Exception e) {
            logger.warn("方法getShowOrderDetail 获取订单信息异常;error:{}", e.getMessage());
            model.addAttribute("content", e.getMessage());
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        if (orderBO == null) {
            logger.warn("方法getShowOrderDetail orderBO is null");
            model.addAttribute("content", "系统查询不到此订单信息！");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        model.addAttribute("reqUrlRoot", rootUrl);
        model.addAttribute("orderBO", orderBO);
        model.addAttribute("merchantInfoModel", merchantInfoModel);
        // 出参
        logger.info("方法getShowOrderDetail出参orderBO:{}", orderBO);
        return getPrefix() + "common/MerchantOrderDetailShow.html";

    }

    /**
     * 查询交易订单信息...
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getOrdersInfoByPage", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object getOrdersInfoByPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        result.setResult(ResultCommandBaseObject.SUCCESS);
        try {
            // 0. 结授参数..
            String start = request.getParameter("start");
            String rows = request.getParameter("rows");
            String merchantId = request.getParameter("merchantId");
            String queryStatus = request.getParameter("queryStatus");
            String status = request.getParameter("status");
            String beginDateTime = request.getParameter("beginDateTime");
            String endDateTime = request.getParameter("endDateTime");
            String deviceId = request.getParameter("deviceId");
            String isParent = request.getParameter("isParent");
            logger.info(
                    "查询交易订单信息...getOrdersInfoByPage start:{},rows:{},merchantId:{},queryStatus:{},status:{},beginDateTime:{},endDateTime:{}",
                    start, rows, merchantId, queryStatus, status, beginDateTime, endDateTime);
            // 1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");
            // 2. 判断
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(Long.valueOf(merchantId));
            CustMerchantRefInfoModel refInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (refInfoModel == null) {
                if (StringUtils.isEmpty(isParent) || !isParent.equals("true")) {
                    result.setMessage(
                            String.format("客户(%d)未绑定商户(%d)信息,无权查询商户交易信息!", key.getCustNo(), key.getMerchantId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            }
            // 3. 查询..
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(key.getCustNo());
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(key.getMerchantId());
            MerchantOrderPagesQueryBO merchantOrderPagesQueryBO = new MerchantOrderPagesQueryBO();
            Integer iStart = new Integer("1");
            try {
                iStart = Integer.valueOf(start);
            } catch (Exception e) {
                // TODO: handle exception
            }
            merchantOrderPagesQueryBO.setStart(iStart);
            merchantOrderPagesQueryBO.setBeginDateTime(beginDateTime);
            merchantOrderPagesQueryBO.setEndDateTime(endDateTime);
            merchantOrderPagesQueryBO.setMerchantId(key.getMerchantId());
            Integer iqueryStatus = null;
            try {
                iqueryStatus = Integer.valueOf(queryStatus);
            } catch (Exception e) {
                // TODO: handle exception
            }
            merchantOrderPagesQueryBO.setQueryStatus(iqueryStatus);
            Integer irows = new Integer("8");
            try {
                irows = Integer.valueOf(rows);
            } catch (Exception e) {
                // TODO: handle exception
            }
            merchantOrderPagesQueryBO.setRows(irows);
            Integer istatus = null;
            try {
                if (status != null && !status.isEmpty()) {
                    istatus = Integer.valueOf(status);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            merchantOrderPagesQueryBO.setStatus(istatus);
            Long deviceIdLong = StringUtils.isEmpty(deviceId) ? null : Long.valueOf(deviceId);
            merchantOrderPagesQueryBO.setDeviceId(deviceIdLong);
            MerchantOrderRecordPageInfoBO bo = tradeInfoHelperService.merchantOrderPagesQuery(custInfoModel,
                    merchantInfoModel, merchantOrderPagesQueryBO);
            bo.setResult(ResultCommandBaseObject.SUCCESS);
            return bo;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("查询商户订单分页显示异常:{}", e);
            // 业务异常
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("系统异常!", e);
            result.setMessage("系统异常，查询失败.");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 得到我的设备....
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMyDevices", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getMyDevices(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/getMyDevices?flag=1";
        try {
            // 0. 接收参数...
            String custNo = request.getParameter("custNo");
            String merchantId = request.getParameter("merchantId");
            String openId = request.getParameter("openId");
            String merchantType = request.getParameter("merchantType");
            String isParent = request.getParameter("isParent");
            urlForBusiness = String.format("%s&custNo=%s&merchantId=%s&openId=%s&merchantType=%s", urlForBusiness,
                    custNo, merchantId, openId, merchantType);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getMerchantOrderInfo");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 2. 判断参数是否正确..
            logger.info("我的设备-getMyDevices,custNo:{},merchantId:{}", custNo, merchantId);
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("我的设备-getMyDevices,custNo:{},merchantId:{},custNoForLogin:{}", custNo, merchantId,
                    custNoForLogin);

            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);

            // 3.判断参数是否正确
            if (custNo == null || !custNoForLogin.equals(custNo)) {
                logger.info("登录客户号与提交查询客户号不一至，交易订单查询失败getMerchantOrderInfo,custNo:{},custNoForLogin:{}", custNo,
                        custNoForLogin);
                model.addAttribute("content", "登录客户号与提交查询客户号不一至，查询我的设备失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNo));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户号在系统中不存在，我的设备查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，我的设备查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 4 返回
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null && StringUtils.isEmpty(isParent)) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，请联系管理员绑定商户号!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);

            String operationHtml = "";
            if (MerchantTypeEnum.DAI_LI_SHANG1.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.DAI_LI_SHANG2.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.DAI_LI_SHANG3.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.PUHUO_SHANG.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.JIA_MENG_SHANG.getCode()
                    .equals(merchantInfoModel.getMerchantType().intValue())) {
                operationHtml = String.format("%s<li>.设置费用<a href=\"%s%s\">.设置费用</a></li>", operationHtml, rootUrl,
                        "/wx/setDeviceCost");
            }
            if (MerchantTypeEnum.DAI_LI_SHANG1.getCode().equals(merchantInfoModel.getMerchantType().intValue())) {
                operationHtml = String.format("%s<li>.设置分润<a href=\"%s%s\">.设置分润</a></li>", operationHtml, rootUrl,
                        "/wx/setDeviceProfit");
                operationHtml = String.format("%s<li>.绑定商户<<a href=\"%s%s\">.绑定商户<</a></li>", operationHtml, rootUrl,
                        "/wx/setBindingMerachant");
                operationHtml = String.format("%s<li>.解绑商户<a href=\"%s%s\">.解绑商户</a></li>", operationHtml, rootUrl,
                        "/wx/setUnBindingMerachant");
            }
            if (MerchantTypeEnum.DAI_LI_SHANG1.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.DAI_LI_SHANG2.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.DAI_LI_SHANG3.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.PUHUO_SHANG.getCode().equals(merchantInfoModel.getMerchantType().intValue())
                    || MerchantTypeEnum.JIA_MENG_SHANG.getCode()
                    .equals(merchantInfoModel.getMerchantType().intValue())) {
                operationHtml = String.format("%s<li>.设置上下架<a href=\"%s%s\">.设置上下架</a></li>", operationHtml, rootUrl,
                        "/wx/setStatus");
            }
            model.addAttribute("operationHtml", operationHtml);
            model.addAttribute("reqUrlRoot", rootUrl);
            model.addAttribute("isParent", isParent);
            return getPrefix() + "common/myDevicesInfoQuery.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 得到我的设备列表令版
     *
     * @return
     */
    @RequestMapping("/myDeviceData")
    @ResponseBody
    public Object myDeviceData(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String start = request.getParameter("start");
            String rows = request.getParameter("rows");
            String custNo = request.getParameter("custNo");
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String merchantType = request.getParameter("merchantType");
            String devType = request.getParameter("devType");
            String deviceId = request.getParameter("deviceId");
            String status = request.getParameter("status");
            // 2. 判断是否登录...
            // 1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");
            if (custNo == null || !custNo.equals(custNoForLogin)) {
                result.setMessage("登录的账户与查询账号不一至，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merchantType == null) {
                result.setMessage("商户类型不正确，请传入查询的商户类型!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 组织查询条件...
            DeviceTotalBO dtb = new DeviceTotalBO();
            dtb.setMerchantType(merchantType);
            dtb.setQueryMerchantId(Long.valueOf(merchantId));
            if (null != deviceId && !deviceId.equals("")) {
                dtb.setDeviceId(new Long(deviceId));
            }
            if (null != status && !"".equals(status)) {
                dtb.setStatus(status);
            }
            if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                // 代理商顶级
                dtb.setAgentsId(Long.valueOf(merchantId));
            } else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                // 代理商1
                dtb.setFirstLevelAgentsId(Long.valueOf(merchantId));
            } else if (merchantType.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                // 代理商2
                dtb.setSecondLevelAgentsId(Long.valueOf(merchantId));
            } else if (merchantType.equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                // 铺货商。。。
                dtb.setShopKeeperId(Long.valueOf(merchantId));
            } else if (merchantType.equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                // 加盟商
                dtb.setFranchiseeId(Long.valueOf(merchantId));
            } else if (merchantType.equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
                dtb.setMerchantId(Long.valueOf(merchantId));
            } else {
                // 判断是否超级账户。。。不然不让查询..
                result.setMessage("账户类型不正确!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            dtb.setStartIndex(Integer.parseInt(start));
            dtb.setRows(Integer.parseInt(rows));
            if (null != devType) {
                dtb.setDevType(Integer.parseInt(devType));
            }
            logger.info("myDeviceData--{}", dtb);
            // 4. 数据库查询服务。。。
            return shareDeviceService.getMyDevice(dtb);
        } catch (Exception e) {
            logger.error("系统异常-----myDeviceData!", e);
            result.setMessage("登录的账户与查询账号不一至，请重新刷新登录后查询!");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 修改商户信息。。
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/myMerchantInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public Object myMerchantInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/myMerchantInfo?flag=1";
        try {
            // 0. 接收参数...
            String custNo = request.getParameter("custNo");
            String merchantId = request.getParameter("merchantId");
            String openId = request.getParameter("openId");
            String merchantType = request.getParameter("merchantType");
            urlForBusiness = String.format("%s&custNo=%s&merchantId=%s&openId=%s&merchantType=%s", urlForBusiness,
                    custNo, merchantId, openId, merchantType);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getMerchantOrderInfo");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 2. 判断参数是否正确..
            logger.info("我的设备-getMyDevices,custNo:{},merchantId:{}", custNo, merchantId);
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("我的设备-getMyDevices,custNo:{},merchantId:{},custNoForLogin:{}", custNo, merchantId,
                    custNoForLogin);
            // 3.判断参数是否正确
            if (custNo == null || !custNoForLogin.equals(custNo)) {
                logger.info("登录客户号与提交查询客户号不一至，交易订单查询失败getMerchantOrderInfo,custNo:{},custNoForLogin:{}", custNo,
                        custNoForLogin);
                model.addAttribute("content", "登录客户号与提交查询客户号不一至，查询我的设备失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNo));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户号在系统中不存在，我的设备查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，我的设备查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 4 返回
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，请联系管理员绑定商户号!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 得到商户类型...
            String selectIndustryCodeOptions = "";
            List<IndustryCategoryEnum> listForIndustry = IndustryCategoryEnum.getIndustryCategoryEnumForList();
            IndustryCategoryEnum itemForIndustry;
            for (int i = 0; i < listForIndustry.size(); i++) {
                itemForIndustry = listForIndustry.get(i);
                if (merchantInfoModel != null && merchantInfoModel.getIndustryCategoryCode() != null
                        && merchantInfoModel.getIndustryCategoryCode().equals(itemForIndustry.getCode())) {
                    selectIndustryCodeOptions = String.format(
                            "%s<option value=\"%d\" selected=\"selected\">%s</option>", selectIndustryCodeOptions,
                            itemForIndustry.getCode(), itemForIndustry.getDesc());
                } else {
                    selectIndustryCodeOptions = String.format("%s<option value=\"%d\">%s</option>",
                            selectIndustryCodeOptions, itemForIndustry.getCode(), itemForIndustry.getDesc());
                }
            }
            model.addAttribute("selectIndustryCodeOptions", selectIndustryCodeOptions);
            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            model.addAttribute("rootUrl", rootUrl);
            return getPrefix() + "common/myMerchantInfo.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 跳转到设备详情页中...
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getShowDevicesDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getShowDevicesDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/getShowDevicesDetail?flag=1";
        try {
            // 0. 接收参数.....
            String deviceId = request.getParameter("deviceId");
            String merchantId = request.getParameter("merchantId");
            String openId = request.getParameter("openId");
            logger.info("显示设备详情-getShowDevicesDetail,deviceId:{},merchantId:{}", deviceId, merchantId);
            urlForBusiness = String.format("%s&deviceId=%s&merchantId=%s&openId=%s", urlForBusiness, deviceId,
                    merchantId, openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getShowDevicesDetail");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            if (merchantId == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("显示设备详情-getShowDevicesDetail,deviceId:{},merchantId:{},unionId:{},custNoForLogin:{}", deviceId,
                    merchantId, unionId, custNoForLogin);
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 4 查询数据
            // CustMerchantRefInfoModelKey key = new
            // CustMerchantRefInfoModelKey();
            // key.setCustNo(custInfoModel.getCustNo());
            // key.setMerchantId(merchantInfoModel.getId());
            // CustMerchantRefInfoModel custMerchantRefInfoModel =
            // custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            // if (custMerchantRefInfoModel == null) {
            // model.addAttribute("content",
            // String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(),
            // key.getMerchantId()));
            // model.addAttribute("title", "error");
            // model.addAttribute("icon", "error");
            // return getPrefix() + "error.html";
            // }
            Long iDeviceId = 0L;
            try {
                iDeviceId = Long.parseLong(deviceId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iDeviceId == null || iDeviceId == 0) {
                model.addAttribute("content", String.format("请传入正确的设备id(%s)!", deviceId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            ShareDeviceInfoModel deviceInfoModel = shareDeviceInfoModelMapper.selectByPrimaryKey(iDeviceId);
            if (deviceInfoModel == null) {
                model.addAttribute("content", String.format("设备id(%s)在系统中不存在，请传入正确的设备id!", deviceId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            // 5.1 获取该设备的历史交易次数
            DevTradeGatherInfoModel devTradeGatherInfoModel = devTradeGatherInfoModelBySelfMapper
                    .selectByDeviceId(iDeviceId);

            // 5.2 获取设备对应的收费信息 // 是否可设置费用
            String feeDes = shareFeeService.getFeeDescByDeviceInfoModel(deviceInfoModel);

            // 5.3. 查询设备充电器数据..
            DeviceChargerGroup deviceChargersInfo = shareChargerModelBySelfMapper
                    .getDeviceChargersGroupByDeviceNo(iDeviceId);
            // 6. 返回
            MerchantInfoModel onlineMerchantInfo = new MerchantInfoModel();
            if (deviceInfoModel != null && deviceInfoModel.getOnlineMerchantId() != null) {
                onlineMerchantInfo = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            }
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("onlineMerchantInfo", onlineMerchantInfo);
            model.addAttribute("shareDeviceInfoModel", deviceInfoModel);
            model.addAttribute("devTradeGatherInfoModel", devTradeGatherInfoModel);
            model.addAttribute("feeDes", feeDes);
            model.addAttribute("deviceStatusCn", ShareDeviceStatusEnum.getDesc(deviceInfoModel.getDeviceStatus()));
            model.addAttribute("deviceChargersInfo", deviceChargersInfo);
            model.addAttribute("merchantInfoModel", merchantInfoModel);

            return getPrefix() + "common/devicesDetailShow.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 得到商户信息。。。
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getMerInfoList", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getMerInfoList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/getMerInfoList?flag=1";
        try {
            // 0. 接收参数.....
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            String openId = request.getParameter("openId");
            logger.info(" 得到商户信息。-getMerInfoList,custNo:{},merchantId:{},openId:{}", custNo, merchantId, openId);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s&openId=%s", urlForBusiness, merchantId, custNo,
                    openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getMerInfoList");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            if (merchantId == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            logger.info("得到商户信息-getMerInfoList,merchantId:{},unionId:{},custNoForLogin:{}", merchantId, unionId,
                    custNoForLogin);
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 4 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            return getPrefix() + "common/merInfoList.html";
        } catch (ExgrainBizUncheckedException e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "设置默认商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 得到充电器例表..。
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getChargersListByDeviceId", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getChargersListByDeviceId(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/getChargersListByDeviceId?flag=1";
        try {
            // 0. 接收参数.....
            String deviceId = request.getParameter("deviceId");
            String openId = request.getParameter("openId");
            logger.info("得到充电器例表---getChargersListByDeviceId,deviceId:{}", deviceId);
            urlForBusiness = String.format("%s&deviceId=%s&openId=%s", urlForBusiness, deviceId, openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getChargersListByDeviceId");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iDeviceId = 0L;
            try {
                iDeviceId = Long.parseLong(deviceId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iDeviceId == null || iDeviceId == 0) {
                model.addAttribute("content", String.format("请传入正确的设备id(%s)!", deviceId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3.数据查询...
            ShareChargerModelExample example = new ShareChargerModelExample();
            com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModelExample.Criteria criteria = example
                    .createCriteria();
            criteria.andDeviceIdEqualTo(iDeviceId);
            List<ShareChargerModel> list = shareChargerModelMapper.selectByExample(example);
            // 3. 返回
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("chargersList", list);
            return getPrefix() + "common/myChargers.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "查询充电器信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 得到交易数据...
     *
     * @return
     */
    @RequestMapping("/myTradeDatas")
    @ResponseBody
    public Object myTradeDatas(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String start = request.getParameter("start");
            String rows = request.getParameter("rows");
            String merchantId = request.getParameter("merchantId");
            String merchantType = request.getParameter("merchantType");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            // 2. 判断是否登录...
            // 1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            if (merchantType == null) {
                result.setMessage("商户类型不正确，请传入查询的商户类型!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请输入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            //2.2 判断是否登录
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
        	   result.setMessage("客户信息异常，请重新登录 ！");
               result.setResult(ResultCommandBaseObject.FAILED);
               return result;
            }            
            // 3. 组织查询条件...
            MerchantProfitQueryBO bo = new MerchantProfitQueryBO();
            bo.setBeginDateTime(startTime);
            bo.setEndDateTime(endTime);
            bo.setMerchantId(Long.valueOf(merchantId));
            bo.setRows(new Integer(rows));
            bo.setStart(new Integer(start));
            // 4. 数据库查询服务。。。
            return shareDeviceService.myTradeDatas(bo, merchantInfoModel);
        } catch (Exception e) {
            logger.error("系统异常-----myDeviceData!", e);
            result.setMessage("登录的账户与查询账号不一至，请重新刷新登录后查询!");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 商户信息修改...
     *
     * @return
     */
    @RequestMapping("/modifyMerInfo")
    @ResponseBody
    public Object modifyMerInfo(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String openId = request.getParameter("openId");
            String personName = request.getParameter("personName");
            String addr = request.getParameter("addr");
            String name = request.getParameter("name");
            String merchantId = request.getParameter("id");
            String comment = request.getParameter("comment");
            String industryCategoryCode = request.getParameter("industryCategoryCode");
            String telNo = request.getParameter("telNo");
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            // 判断是否非法修改...
            if (openId != null && !openId.equals(openIdForLogin)) {
                result.setMessage("账户不一至，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请输入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3.构建保存对象..
            MerchantInfoModel merchantInfoModelForUpdate = new MerchantInfoModel();
            merchantInfoModelForUpdate.setId(merchantInfoModel.getId());
            merchantInfoModelForUpdate.setPersonName(personName);
            merchantInfoModelForUpdate.setAddr(addr);
            merchantInfoModelForUpdate.setName(name);
            merchantInfoModelForUpdate.setRemark(comment);
            merchantInfoModelForUpdate.setTelNo(telNo);
            if (industryCategoryCode != null && !industryCategoryCode.isEmpty()) {
                Integer categoryCode = new Integer(industryCategoryCode);
                String desc = IndustryCategoryEnum.getDesc(categoryCode);
                merchantInfoModelForUpdate.setIndustryCategoryCode(categoryCode.byteValue());
                merchantInfoModelForUpdate.setIndustryCategoryCn(desc);
            }
            merchantInfoModelMapper.updateByPrimaryKeySelective(merchantInfoModelForUpdate);
            // 返回成功信息到客户端...
            result.setResult(ResultCommandBaseObject.SUCCESS);
            result.setMessage("修改成功!");
            return result;
        } catch (Exception e) {
            logger.error("系统异常-----myDeviceData!", e);
            result.setMessage("登录的账户与查询账号不一至，请重新刷新登录后查询!");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 我的收益...
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getMyTradeInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getMyTradeInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/getMyTradeInfo?flag=1";
        try {
            // 0. 接收参数.....
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            String openId = request.getParameter("openId");

            logger.info("我的收益...--getMyTradeInfo,merchantId:{},custNo:{},openId:{}", merchantId, custNo, openId);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s&openId=%s", urlForBusiness, merchantId, custNo,
                    openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getMyTradeInfo");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 2 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            // 可以让未绑定的客户查询商户信息
            // CustMerchantRefInfoModel custMerchantRefInfoModel =
            // custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            // if (custMerchantRefInfoModel == null) {
            // model.addAttribute("content",
            // String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(),
            // key.getMerchantId()));
            // model.addAttribute("title", "error");
            // model.addAttribute("icon", "error");
            // return getPrefix() + "error.html";
            // }
            // 3. 返回
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            return getPrefix() + "common/myTradeInfo.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "查询充电器信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 跳转到7天未使用的设备处..
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/beyondSevenDaysNotUse", method = {RequestMethod.POST, RequestMethod.GET})
    public Object beyondSevenDaysNotUse(HttpServletRequest request, HttpServletResponse response, Model model) {
        String urlForBusiness = "/wx/beyondSevenDaysNotUse?flag=1";
        try {
            // 0. 接收参数.....
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            String openId = request.getParameter("openId");

            logger.info("跳转到7天未使用的设备处.-beyondSevenDaysNotUse,merchantId:{},custNo:{},openId:{}", merchantId, custNo,
                    openId);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s&openId=%s", urlForBusiness, merchantId, custNo,
                    openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "getMyTradeInfo");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 2 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3. 返回
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            return getPrefix() + "common/beyondSevenDaysNotUse.html";
        } catch (Exception e) {
            logger.error("根据用户选跳转..列表--selectMerchantList", e);
            model.addAttribute("content", "查询充电器信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 获取指定天数的未使用设备信息。。
     *
     * @return
     */
    @RequestMapping("/beyondSevenDaysNotUseData")
    @ResponseBody
    public Object beyondSevenDaysNotUseData(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String openId = request.getParameter("openId");
            String storeName = request.getParameter("storeName");
            String storeAddress = request.getParameter("storeAddress");
            String merchantId = request.getParameter("merchantId");
            String howLongNotUse = request.getParameter("howLongNotUse");
            String start = request.getParameter("start");// 分页
            String rows = request.getParameter("rows"); // 每页数
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            // 判断是否非法修改...
            if (openId != null && !openId.equals(openIdForLogin)) {
                result.setMessage("账户不一至，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请输入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3.构建保存对象..
            ChargerBeyondSevenDaysNotUseRequestCommand command = new ChargerBeyondSevenDaysNotUseRequestCommand();
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                // 当前商户是代理商
                command.setAgents1Id(merchantInfoModel.getId());
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                // 一级代理商
                command.setAgents2Id(merchantInfoModel.getId());
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                // 二级代理商
                command.setAgents3Id(merchantInfoModel.getId());
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                // 当前商户是铺货商
                command.setShopkeeperId(merchantInfoModel.getId());
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                // 当前商户是加盟商
                command.setAllianceBusinessId(merchantInfoModel.getId());
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
                // 当前商户是终端商户
                command.setMerchantId(merchantInfoModel.getId());
            }
            if (howLongNotUse != null && !howLongNotUse.isEmpty()) {
                command.setHowLongNotUse(new Integer(howLongNotUse));
            }
            if (storeName != null && !storeName.isEmpty()) {
                command.setStoreName(storeName);
            }
            if (storeAddress != null && !storeAddress.isEmpty()) {
                command.setStoreAddress(storeAddress);
            }
            if (start != null && !start.isEmpty()) {
                command.setStart(new Integer(start));
            }
            if (rows != null && !rows.isEmpty()) {
                command.setRows(new Integer(rows));
            }
            // 3.1 查询数据...
            ChargerBeyondSevenDaysNotUsePageInfoBO resultData = shareDeviceService
                    .getBeyondThreeDaysNotUseChargers(command);
            // 返回成功信息到客户端...
            resultData.setResult(ResultCommandBaseObject.SUCCESS);
            return resultData;
        } catch (Exception e) {
            logger.error("系统异常-----myDeviceData!", e);
            result.setMessage("登录的账户与查询账号不一至，请重新刷新登录后查询!");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 跳转到我的钱包..
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/myWallet")
    public Object myWallet(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            String urlForBusiness = "/wx/myWallet?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            String isParent = request.getParameter("isParent");
            logger.info("跳转到我的钱包..-myWallet,merchantId:{},custNo:{},openId:{},queryByOthers:{}", merchantId, custNo,
                    openId);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s&openId=%s", urlForBusiness, merchantId, custNo,
                    openId, isParent);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "myWallet");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                if (StringUtils.isEmpty(isParent) || !isParent.equals("true")) {
                    model.addAttribute("content",
                            String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                    model.addAttribute("title", "error");
                    model.addAttribute("icon", "error");
                    return getPrefix() + "error.html";
                }
            }
            // 3.1 询商户类型的客户信息
            CustInfoModel custIfoModelForMerchant = custInfoModelMapper
                    .selectMerchantCustInfoByMerchantId(merchantInfoModel.getId());
            if (custIfoModelForMerchant == null) {
                model.addAttribute("content", String.format("未找到商户(%d)对应的钱包账号!", merchantInfoModel.getId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3.2 账号...
            CustAccountModel custAccountModel = custAccountModelMapper.selectByCustNo(
                    custIfoModelForMerchant.getCustNo(), CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
            if (custAccountModel == null) {
                model.addAttribute("content", String.format("商户(%d)未建建对应的钱包账号!", merchantInfoModel.getId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            // 获取昨日收益和累计收益
            FrozenCapitaInfoBO fc = new FrozenCapitaInfoBO();
            fc.setMerchantId(merchantInfoModel.getId());
            FrozenCapitaInfoBO fc2 = new FrozenCapitaInfoBO();
            fc2.setMerchantId(merchantInfoModel.getId());
            fc2.setBeginDateTime(DatetimeUtil.yesterdayYyyyMMdd());
            fc2.setEndDateTime(DatetimeUtil.yesterdayYyyyMMdd());
            // 3.2.1 获取昨日收益金额
            ShareTrdTotalInfo yesTti = merchantProfitDayModelBySelfMapper.getMerIncmCntAndAmtNew(fc2);
            // 3.2.2 获取累计收益金额
            ShareTrdTotalInfo allTti = merchantProfitDayModelBySelfMapper.getMerIncmCntAndAmtNew(fc);

            // 获取提现中的金额
            BigDecimal cashWithdrawAmount = withdrawApplyRecordModelMapper
                    .queryCashWithdrawInfoByMerchantId(merchantInfoModel.getId(), WithdrawStatusEnum.SUCCESS.getCode());

            custAccountModel.setFrozenBalance(new BigDecimal("0"));

            // 3.2.3 商户钱包余额（可用余额+提现中金额）
            if (cashWithdrawAmount == null) {
                cashWithdrawAmount = BigDecimal.ZERO;
            }
            BigDecimal walletBalance = cashWithdrawAmount.add(custAccountModel.getAvailableBalance() == null
                    ? BigDecimal.ZERO : custAccountModel.getAvailableBalance());
            MerWithdrawMetadataModel withdrawWay = merWithdrawMetadataModelMapper
                    .selectByPrimaryKey(merchantInfoModel.getWithdrawWayId());
            // 提现方式中配置了T+N结算，并且N=0，则显示 今日收益
            if (withdrawWay.getIsRealTime() != null
                    && IsRealTimeEnum.T_PLUS_0.getCode().intValue() == withdrawWay.getIsRealTime().intValue()) {
                FrozenCapitaInfoBO today = new FrozenCapitaInfoBO();
                today.setMerchantId(merchantInfoModel.getId());
                today.setBeginDateTime(DatetimeUtil.todayYyyyMMdd());
                today.setEndDateTime(DatetimeUtil.todayYyyyMMdd());
                // 获取今日收益金额
                ShareTrdTotalInfo todayTti = merchantProfitDayModelBySelfMapper.getMerIncmCntAndAmtNew(today);
                model.addAttribute("merchantTodayIncomeAmount", todayTti.getHisTradeAmount() == null ? BigDecimal.ZERO
                        : todayTti.getHisTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            // 3. 返回
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("isParent", isParent);
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            model.addAttribute("custAccountModel", custAccountModel);
            model.addAttribute("merchantYesterdayIncomeAmount", yesTti.getHisTradeAmount() == null ? BigDecimal.ZERO
                    : yesTti.getHisTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            model.addAttribute("merchantAllIncomeAmount", allTti.getHisTradeAmount() == null ? BigDecimal.ZERO
                    : allTti.getHisTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            model.addAttribute("cashWithdrawAmount", cashWithdrawAmount == null ? BigDecimal.ZERO : cashWithdrawAmount);
            model.addAttribute("walletBalance",
                    (walletBalance.add(custAccountModel.getFrozenBalance())).setScale(2, BigDecimal.ROUND_HALF_UP));
            // 取配置
            model.addAttribute("merchantYesterdayIncomeAmount", yesTti.getHisTradeAmount() == null ? BigDecimal.ZERO
                    : yesTti.getHisTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            model.addAttribute("merchantAllIncomeAmount", allTti.getHisTradeAmount() == null ? BigDecimal.ZERO
                    : allTti.getHisTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            model.addAttribute("cashWithdrawAmount", cashWithdrawAmount == null ? BigDecimal.ZERO : cashWithdrawAmount);
            model.addAttribute("isRealTime", withdrawWay.getIsRealTime() == null ? IsRealTimeEnum.T_PLUS_1.getCode()
                    : withdrawWay.getIsRealTime());
            return getPrefix() + "common/merchantCoporateAgentsMyWallet.html";
        } catch (Exception e) {
            logger.error("跳转到我的钱包..--myWallet", e);
            model.addAttribute("content", "跳转我的钱包失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 今日收益元
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/yesterdayIncome")
    public Object yesterdayIncome(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            String urlForBusiness = "/wx/yesterdayIncome?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String isRealTime = request.getParameter("isRealTime");
            String searchTime = request.getParameter("searchTime");
            String merchantType = request.getParameter("merchantType");
            String isParent = request.getParameter("isParent");
            logger.info("今日收益元..-yesterdayIncome,merchantId:{},openId:{},isRealTime:{},merchantType:{}", merchantId,
                    openId, isRealTime, merchantType);
            urlForBusiness = String.format("%s&merchantId=%s&openId=%s", urlForBusiness, merchantId, openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "yesterdayIncome");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3 查询数据
            // 不需要判断当前登陆客户号有没有绑定此商户都可以查
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                if (StringUtils.isEmpty(isParent) || !isParent.equals("true")) {
                    model.addAttribute("content",
                            String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                    model.addAttribute("title", "error");
                    model.addAttribute("icon", "error");
                    return getPrefix() + "error.html";
                }
            }
            model.addAttribute("isParent", isParent);
            model.addAttribute("searchTime", searchTime);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/ysdayIncome.html";
        } catch (Exception e) {
            logger.error("今日收益元..--yesterdayIncome", e);
            model.addAttribute("content", "今日收益查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 累计收益
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/incomeDetail")
    public Object incomeDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            String urlForBusiness = "/wx/yesterdayIncome?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String isRealTime = request.getParameter("isRealTime");
            String merchantType = request.getParameter("merchantType");
            String isParent = request.getParameter("isParent");
            logger.info("跳转到我的钱包..-yesterdayIncome,merchantId:{},openId:{},isRealTime:{},merchantType:{}", merchantId,
                    openId, isRealTime, merchantType);
            urlForBusiness = String.format("%s&merchantId=%s&openId=%s", urlForBusiness, merchantId, openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "yesterdayIncome");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                if (StringUtils.isEmpty(isParent) || !isParent.equals("true")) {
                    model.addAttribute("content",
                            String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                    model.addAttribute("title", "error");
                    model.addAttribute("icon", "error");
                    return getPrefix() + "error.html";
                }
            }
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            model.addAttribute("isParent", isParent);
            return getPrefix() + "common/incomeDetail.html";
        } catch (Exception e) {
            logger.error("累计收入查询失败..--incomeDetail", e);
            model.addAttribute("content", "累计收入查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 跳转提现
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/merchantWithdrawApply")
    public Object merchantWithdrawApply(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            String urlForBusiness = "/wx/merchantWithdrawApply?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String merchantType = request.getParameter("merchantType");
            logger.info(" 跳转提现.-merchantWithdrawApply,merchantId:{},merchantType:{},openId:{}", merchantId,
                    merchantType, openId);
            urlForBusiness = String.format("%s&merchantId=%s&openId=%s", urlForBusiness, merchantId, openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "merchantWithdrawApply");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 获取提现账户...
            CustInfoModel custAccountModelForcustInfo = custInfoModelMapper
                    .selectMerchantCustInfoByMerchantId(merchantInfoModel.getId());
            if (custAccountModelForcustInfo == null) {
                model.addAttribute("content",
                        String.format("商户(%d)账号异常，未找到商户对应的的账号，请联系客服!", merchantInfoModel.getId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            //
            CustAccountModel custAccountModel = custAccountModelMapper.selectByCustNo(
                    custAccountModelForcustInfo.getCustNo(), CustAccountTypeEnum.RECHARGERACCOUNT.getCode());

            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("custAccountModel", custAccountModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/merchantWithdrawApply.html";
        } catch (Exception e) {
            logger.error("累计收入查询失败..--merchantWithdrawApply", e);
            model.addAttribute("content", "提现加载失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 跳转到商户编辑中.
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/editMerchantcenterAgent")
    public Object editMerchantcenterAgent(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/editMerchantcenterAgent?flag=1";
            String pid = request.getParameter("pid");
            String merchantId = request.getParameter("merchantId");
            String parentMerchantType = request.getParameter("parentMerchantType");
            logger.info(" 跳转到商户编辑中..-editMerchantcenterAgent,merchantId:{},pid:{},parentMerchantType:{}", merchantId,
                    pid, parentMerchantType);
            urlForBusiness = String.format("%s&merchantId=%s&pid=%s&parentMerchantType=%s", urlForBusiness, merchantId,
                    pid, parentMerchantType);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "merchantWithdrawHistory");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(pid);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModelParent = merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
            if (merchantInfoModelParent == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModelParent.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("传入的商户id(%s)不正确，请传入正确的商户id!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            model.addAttribute("parentMerchantInfo", merchantInfoModelParent);
            return getPrefix() + "common/editMerchantcenterAgent.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "查询商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 跳转到商户编辑中.
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/editMerchantcenterTerminal")
    public Object editMerchantcenterTerminal(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/editMerchantcenterAgent?flag=1";
            String pid = request.getParameter("pid");
            String merchantId = request.getParameter("merchantId");
            String parentMerchantType = request.getParameter("parentMerchantType");
            logger.info(" 跳转到商户编辑中..-editMerchantcenterTerminal,merchantId:{},pid:{},parentMerchantType:{}", merchantId,
                    pid, parentMerchantType);
            urlForBusiness = String.format("%s&merchantId=%s&pid=%s&parentMerchantType=%s", urlForBusiness, merchantId,
                    pid, parentMerchantType);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "merchantWithdrawHistory");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(pid);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModelParent = merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
            if (merchantInfoModelParent == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModelParent.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("传入的商户id(%s)不正确，请传入正确的商户id!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            model.addAttribute("parentMerchantInfo", merchantInfoModelParent);
            return getPrefix() + "common/editMerchantcenterTerminal.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "查询商户信息失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 商户提现记录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/merchantWithdrawHistory")
    public Object merchantWithdrawHistory(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/merchantWithdrawHistory?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String merchantType = request.getParameter("merchantType");
            logger.info(" 跳转提现.-merchantWithdrawHistory,merchantId:{},merchantType:{},openId:{}", merchantId,
                    merchantType, openId);
            urlForBusiness = String.format("%s&merchantId=%s&openId=%s", urlForBusiness, merchantId, openId);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "merchantWithdrawHistory");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            List<WithdrawApplyRecordModel> list = withdrawService
                    .queryMerchantWithdrawRecord(merchantInfoModel.getId());
            DateFormat df = new SimpleDateFormat(DateUtils.NORMAL_FORMAT);
            WithdrawApplyRecordModel warmRecordModel = null;
            for (int i = 0; i < list.size(); i++) {
                warmRecordModel = list.get(i);
                warmRecordModel.setApplyTimeFormat(df.format(warmRecordModel.getApplyTime()));
                list.set(i, warmRecordModel);
            }

            model.addAttribute("recordList", list);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/merchantWithdrawHistory.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "累计收入查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 得到交易数据。
     *
     * @return
     */
    @RequestMapping("/getMyTradeInfoData")
    @ResponseBody
    public Object getMyTradeInfoData(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merchantId");
            String isParent = request.getParameter("isParent");
            String beginDateTime = request.getParameter("beginDateTime");
            String endDateTime = request.getParameter("endDateTime");
            String start = request.getParameter("start");// 分页
            String rows = request.getParameter("rows"); // 每页数
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");

            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请输入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                if (StringUtils.isEmpty(isParent) || !"true".equals(isParent)) {
                    model.addAttribute("content",
                            String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                    model.addAttribute("title", "error");
                    model.addAttribute("icon", "error");
                    return getPrefix() + "error.html";
                }
            }
            // 3. 组织查询条件...
            MerchantProfitQueryBO bo = new MerchantProfitQueryBO();
            bo.setBeginDateTime(beginDateTime);
            bo.setEndDateTime(endDateTime);
            bo.setMerchantId(merchantInfoModel.getId());
            bo.setRows(new Integer(rows));
            bo.setStart(new Integer(start));
            // 4. 数据库查询服务。。。
            return shareDeviceService.myTradeDatas(bo, merchantInfoModel);
        } catch (Exception e) {
            logger.error("系统异常-----myDeviceData!", e);
            result.setMessage("登录的账户与查询账号不一至，请重新刷新登录后查询!");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 得到收益数据。
     *
     * @return
     */
    @RequestMapping("/myIncomeDetailData")
    @ResponseBody
    public Object myIncomeDetailData(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merchantId");
            String beginDateTime = request.getParameter("beginDateTime");
            String endDateTime = request.getParameter("endDateTime");
            String start = request.getParameter("start");// 分页
            String rows = request.getParameter("rows"); // 每页数
            String isParent = request.getParameter("isParent"); // 每页数

            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");

            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请输入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                if (StringUtils.isEmpty(isParent) || !isParent.equals("true")) {
                    model.addAttribute("content",
                            String.format("当前登录客户号(%d)未绑定商户(%d)，查询设备信息失败!", key.getCustNo(), key.getMerchantId()));
                    model.addAttribute("title", "error");
                    model.addAttribute("icon", "error");
                    return getPrefix() + "error.html";
                }
            }

            // 3. 组织查询条件...
            DeviceTradeInfoBO bo = new DeviceTradeInfoBO();
            bo.setBeginDateTime(beginDateTime);
            bo.setEndDateTime(endDateTime);
            bo.setMerchantId(merchantInfoModel.getId());
            bo.setRows(new Integer(rows));
            bo.setStartIndex(new Integer(start));
            // 4. 数据库查询服务。。。
            return shareDeviceService.myIncomeDatas(bo, merchantInfoModel);
        } catch (Exception e) {
            logger.error("系统异常-----myDeviceData!", e);
            result.setMessage("登录的账户与查询账号不一至，请重新刷新登录后查询!");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * <p>
     * 子代理商列表
     * </p>
     *
     * @return
     */
    @RequestMapping("/queryMerchantInfoList")
    @ResponseBody
    public Object queryMerchantInfoList(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merchantId");
            String parentMerchantId = request.getParameter("parentMerchantId");
            String personName = request.getParameter("personName");
            String page = request.getParameter("page");// 分页
            String rows = request.getParameter("rows"); // 每页数
            String merchantType = request.getParameter("merchantType");// 商户类型
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (parentMerchantId == null || parentMerchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");

            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper
                    .selectByPrimaryKey(Long.valueOf(parentMerchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请输入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            // 3. 组织查询条件...
            ChildAgentsQueryCommand command = new ChildAgentsQueryCommand();
            command.setParentMerchantId(key.getMerchantId());
            if (merchantId != null && merchantId != "0" && !merchantId.isEmpty())
                command.setMerchantId(Long.valueOf(merchantId));
            if (merchantType != null && !merchantType.isEmpty()) {
                command.setMerchantType(new Integer(merchantType));
            }
            if (rows != null && !rows.isEmpty()) {
                command.setRows(Integer.parseInt(rows));
            } else {
                command.setRows(5);
            }
            if (page != null && !page.isEmpty()) {
                command.setPage(Integer.parseInt(page));
            } else {
                command.setPage(1);
            }
            if (personName != null && !personName.isEmpty()) {
                command.setPersonName(personName);
            }
            // 4. 数据库查询服务。。。
            CommonPagingQueryBO rslt = merchantService.queryChildAgentsInfoList(command);
            return rslt;
        } catch (Exception e) {
            logger.error("获取子代理商列表的消息[controller层][queryChildMerchantInfoList方法]异常", e);
            result.setMessage("获取子代理商列表异常");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * <p>
     * 更新商户
     * </p>
     *
     * @return
     */
    @RequestMapping("/updateChildAgentsInfo")
    @ResponseBody
    public Object updateChildAgentsInfo(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merchantId");
            String personName = request.getParameter("personName");
            String addr = request.getParameter("addr");// 地址
            String telNo = request.getParameter("telNo"); // 电话
            String name = request.getParameter("name"); // 商户名
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");

            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3.更新数据库...
            MerchantInfoModel record = new MerchantInfoModel();
            record.setId(merchantInfoModel.getId());
            if (addr != null && !addr.isEmpty()) {
                record.setAddr(addr);
            }
            if (telNo != null && !telNo.isEmpty()) {
                record.setTelNo(telNo);
            }
            if (personName != null && !personName.isEmpty()) {
                record.setPersonName(personName);
            }
            if (name != null && !name.isEmpty()) {
                record.setName(name);
            }
            record.setUpdateTime(new Date());
            merchantInfoModelMapper.updateByPrimaryKeySelective(record);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (Exception e) {
            logger.error("updateChildAgentsInfo更新商户异常", e);
            result.setMessage("更新商户异常");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * <p>
     * 根据商户编号，查看当前设备id是否属于该商户
     * </p>
     *
     * @return
     */
    @RequestMapping("/checkDeviceIdIsCorrectForDeviceScan")
    @ResponseBody
    public Object checkDeviceIdIsCorrectForDeviceScan(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merId");
            String codeContent = request.getParameter("codeContent");
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (codeContent == null || codeContent.isEmpty()) {
                result.setMessage("未获取到二维码信息!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            codeContent = codeContent.toLowerCase();
            if (codeContent.indexOf("id=") <= 0) {
                result.setMessage("输入的充电器Id格式不正确，请传入正确的充电器Id!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String deviceId = codeContent.substring(codeContent.indexOf("id="));
            int iIndex = deviceId.indexOf("&");
            if (iIndex > 0) {
                deviceId = deviceId.substring(0, iIndex);
            }
            deviceId = deviceId.replaceAll("id=", "").replaceAll("&", "");
            // 3. 判断设备是否正确.
            if (deviceId == null || deviceId.isEmpty()) {
                result.setMessage(String.format("输入的充电器Id(%s)在系统中不在存，请传入正确的充电器Id!", deviceId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            ShareChargerModel chargModel = shareChargerModelMapper.selectByPrimaryKey(Long.valueOf(deviceId));
            if (chargModel == null) {
                result.setMessage(String.format("输入的充电器Id(%s)在系统中不在存，请传入正确的充电器Id!", deviceId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (chargModel.getDeviceId() == null) {
                result.setMessage(String.format("输入的充电器Id(%s)未绑定设备，请联系管理员绑定!", deviceId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            ShareDeviceInfoModel shareDeviceInfoModel = shareDeviceInfoModelMapper
                    .selectByPrimaryKey(chargModel.getDeviceId());
            if (shareDeviceInfoModel == null) {
                result.setMessage(String.format("输入的充电器对应设备Id(%s)在系统中不在存，请传入正确的设备Id!", chargModel.getDeviceId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                // 当前商户是顶级代理商
                if (shareDeviceInfoModel.getAgents1Id() == null
                        || !shareDeviceInfoModel.getAgents1Id().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前充电器对应 设备Id(%s)对应的代理商不是您，您无权修改!", chargModel.getDeviceId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }

            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                // 当前商户是一级代理
                if (shareDeviceInfoModel.getAgents2Id() == null
                        || !shareDeviceInfoModel.getAgents2Id().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前充电器对应 设备Id(%s)对应的一级代理商不是您，您无权修改!", chargModel.getDeviceId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                // 当前商户是二级代理
                if (shareDeviceInfoModel.getAgents3Id() == null
                        || !shareDeviceInfoModel.getAgents3Id().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前充电器对应 设备Id(%s)对应的二级代理商不是您，您无权修改!", chargModel.getDeviceId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                // 当前商户是铺货商 if(shareDeviceInfoModel.getAgents1Id()==null||
                if (shareDeviceInfoModel.getShopkeeperId() == null
                        || !shareDeviceInfoModel.getShopkeeperId().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前充电器对应的设备Id(%s)对应的铺货商不是您，您无权修改!", chargModel.getDeviceId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                // 当前商户是加盟商 if(shareDeviceInfoModel.getAgents1Id()==null||
                if (shareDeviceInfoModel.getAllianceBusinessId() == null
                        || !shareDeviceInfoModel.getAllianceBusinessId().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前充电器对应的设备Id(%s)对应的加盟商不是您，您无权修改!", chargModel.getDeviceId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            } else {
                // 当前商户是终端商户
                result.setMessage("终端商户无权修改!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            result.setResponseInfo(chargModel.getDeviceId());
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("商户公众号中，查看当前设备id是否属于该商户异常:{}", e);
            // 业务异常
            result.setMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            // 系统异常
            logger.error("商户公众号中，查看当前设备id是否属于该商户异常{}", e);
            result.setMessage("添加失败");
            return result;
        }
    }

    /**
     * <p>
     * 根据商户编号，查看当前设备id是否属于该商户
     * </p>
     *
     * @return
     */
    @RequestMapping("/checkDeviceIdIsCorrect")
    @ResponseBody
    public Object checkDeviceIdIsCorrect(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merId");
            String deviceId = request.getParameter("deviceId");
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 判断设备是否正确.
            if (deviceId == null || deviceId.isEmpty()) {
                result.setMessage(String.format("输入的设备Id(%s)在系统中不在存，请传入正确的设备Id!", deviceId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            ShareDeviceInfoModel shareDeviceInfoModel = shareDeviceInfoModelMapper
                    .selectByPrimaryKey(Long.valueOf(deviceId));
            if (shareDeviceInfoModel == null) {
                result.setMessage(String.format("输入的设备Id(%s)在系统中不在存，请传入正确的设备Id!", deviceId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                // 当前商户是代理商
                if (shareDeviceInfoModel.getAgents1Id() == null
                        || !shareDeviceInfoModel.getAgents1Id().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前设备Id(%s)对应的代理商不是您，您无权修改!", deviceId));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }

            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                // 当前商户是一级代理商
                if (shareDeviceInfoModel.getAgents2Id() == null
                        || !shareDeviceInfoModel.getAgents2Id().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前设备Id(%s)对应的一级代理商不是您，您无权修改!", deviceId));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }

            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                // 当前商户是二级代理商
                if (shareDeviceInfoModel.getAgents3Id() == null
                        || !shareDeviceInfoModel.getAgents3Id().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前设备Id(%s)对应的一级代理商不是您，您无权修改!", deviceId));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }

            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                // 当前商户是铺货商 if(shareDeviceInfoModel.getAgents1Id()==null||
                if (shareDeviceInfoModel.getShopkeeperId() == null
                        || !shareDeviceInfoModel.getShopkeeperId().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前设备Id(%s)对应的铺货商不是您，您无权修改!", deviceId));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            } else if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                // 当前商户是加盟商 if(shareDeviceInfoModel.getAgents1Id()==null||
                if (shareDeviceInfoModel.getAllianceBusinessId() == null
                        || !shareDeviceInfoModel.getAllianceBusinessId().equals(merchantInfoModel.getId())) {
                    result.setMessage(String.format("当前设备Id(%s)对应的加盟商不是您，您无权修改!", deviceId));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            } else {
                // 当前商户是终端商户
                result.setMessage("只有铺货商及顶级代理商可以修改，其它类型的商户无权修改!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            result.setResponseInfo(shareDeviceInfoModel);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("商户公众号中，查看当前设备id是否属于该商户异常:{}", e);
            // 业务异常
            result.setMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            // 系统异常
            logger.error("商户公众号中，查看当前设备id是否属于该商户异常{}", e);
            result.setMessage("添加失败");
            return result;
        }
    }

    /**
     * <p>
     * 根据商户编号，查看当前设备id是否属于该商户
     * </p>
     *
     * @return
     */
    @RequestMapping("/getExistDeviceIds")
    @ResponseBody
    public Object getExistDeviceIds(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merId");
            String deviceStartId = request.getParameter("deviceStartId");
            String deviceEndId = request.getParameter("deviceEndId");
            String deviceIds = request.getParameter("deviceIds");
            String onLineMerchantId = request.getParameter("onLineMerchantId");
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if ((deviceIds == null || deviceIds.isEmpty())
                    && (deviceStartId == null || deviceStartId.isEmpty() || deviceEndId == null
                    || deviceEndId.isEmpty())
                    && (onLineMerchantId == null || onLineMerchantId.isEmpty() || onLineMerchantId.equals("0"))) {
                result.setMessage("未输入获取设备的过虑条件,请输入获取设备的过虑条件!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            /*
             * if (merchantInfoModel.getMerchantType().toString().equals(String.
             * valueOf( MerchantTypeEnum.TERMINAL.getCode()))) { // 当前商户是终端商户
             * result.setMessage("只有铺货商、加盟商及代理商可以修改，其它类型的商户无权修改!");
             * result.setResult(ResultCommandBaseObject.FAILED); return result;
             * }
             */
            // 3.1 代理商id
            HashMap<String, Object> pageFilter = new HashMap<>();
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                pageFilter.put("agents1Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                pageFilter.put("agents2Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                pageFilter.put("agents3Id", merchantInfoModel.getId());
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                pageFilter.put("shopkeeperId", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                pageFilter.put("allianceBusinessId", merchantInfoModel.getId());
            }
            // 3.3获到设备的过虑条1
            if (deviceStartId != null && !deviceStartId.isEmpty()) {
                pageFilter.put("deviceStartId", deviceStartId);
            }
            if (deviceEndId != null && !deviceEndId.isEmpty()) {
                pageFilter.put("deviceEndId", deviceEndId);
            }
            // 获到设备的过虑条2
            if (deviceIds != null) {
                String[] aryDeviceIds = deviceIds.split(",");
                List<Long> listForDeviceIds = new ArrayList<>();
                Long deviceId = 0L;
                for (String id : aryDeviceIds) {
                    if (id == null || id.isEmpty()) {
                        continue;
                    }
                    try {
                        deviceId = Long.valueOf(id);
                        listForDeviceIds.add(deviceId);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                if (listForDeviceIds != null && listForDeviceIds.size() > 0) {
                    pageFilter.put("deviceIds", listForDeviceIds);
                }
            }
            // 获到设备的过虑条3
            if (onLineMerchantId != null && !onLineMerchantId.isEmpty()) {
                try {
                    pageFilter.put("onlineMerchantId", Long.parseLong(onLineMerchantId));
                } catch (Exception eee) {
                }
            }
            // 4. 生成共享设备..
            List<ShareDeviceInfoModel> listForDeviceInfoModel = shareDeviceInfoModelBySelfMapper
                    .getShareDeviceInfoModelByPageFilter(pageFilter);
            List<Long> list = new ArrayList<>();
            for (ShareDeviceInfoModel shareDeviceInfoModel : listForDeviceInfoModel) {
                list.add(shareDeviceInfoModel.getId());
            }
            result.setResponseInfo(list);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("商户公众号中，查看当前设备id是否属于该商户异常:{}", e);
            // 业务异常
            result.setMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            // 系统异常
            logger.error("商户公众号中，查看当前设备id是否属于该商户异常{}", e);
            result.setMessage("获取设备失败");
            return result;
        }
    }

    /**
     * 批理编辑费用
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/batchEditFeeRule")
    @ResponseBody
    public Object batchEditFeeRule(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String merchantId = request.getParameter("merId");
        String feeRuleCommand = request.getParameter("feeRuleCommand");
        String custNo = request.getParameter("custNo");
        try {
            if (feeRuleCommand == null || feeRuleCommand.isEmpty()) {
                result.setMessage("未找到要批量修改费用的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 1.
            List<BatchEditFeeCommand> list = JSON.parseArray(feeRuleCommand, BatchEditFeeCommand.class);
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 判断设备是否正确.
            if (list == null || list.size() <= 0) {
                result.setMessage("未找到要批量修改费用的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 4. 批量修改费用
            // 增加过虑条件
            String filter = "";
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                filter = String.format(" and agents1_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                filter = String.format(" and agents2_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                filter = String.format(" and agents3_id=%d %s", merchantInfoModel.getId(), filter);
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                filter = String.format(" and shopkeeper_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                filter = String.format(" and alliance_business_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (filter == "" || filter.isEmpty()) {
                filter = null;
            }
            shareDeviceService.batchEditFee(list, filter);
            // 5.
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("batchEditFeeRule 批理设备费用", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("修改消息状态异常 batchEditFeeRule！", e);
            result.setMessage("批理设备费用修改失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * <p>
     * 根据设备编号，查看当前设备id是否属于该商户
     * </p>
     *
     * @return
     */
    @RequestMapping("/getEditFeeRuleEntry")
    @ResponseBody
    public Object getEditFeeRuleEntry(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merId");
            String deviceStartId = request.getParameter("deviceStartId");
            String deviceEndId = request.getParameter("deviceEndId");
            String deviceIds = request.getParameter("deviceIds");
            String onLineMerchantId = request.getParameter("onLineMerchantId");
            // 判断过虑条件是否有问题
            if ((deviceIds == null || deviceIds.isEmpty())
                    && (deviceStartId == null || deviceStartId.isEmpty() || deviceEndId == null
                    || deviceEndId.isEmpty())
                    && (onLineMerchantId == null || onLineMerchantId.isEmpty() || onLineMerchantId.equals("0"))) {
                result.setMessage("未输入获取设备的过虑条件,请输入获取设备的过虑条件!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改设备分润信息！", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 判断设备是否正确.
            if ((deviceIds == null || deviceIds.isEmpty())
                    && (deviceStartId == null || deviceStartId.isEmpty() || deviceEndId == null
                    || deviceEndId.isEmpty())
                    && (onLineMerchantId == null || onLineMerchantId.isEmpty() || onLineMerchantId.equals("0"))) {
                result.setMessage("未输入获取设备的过虑条件,请输入获取设备的过虑条件!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            /*
             * if (!(merchantInfoModel.getMerchantType().toString()
             * .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))
             * || (merchantInfoModel.getMerchantType().toString()
             * .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))))
             * ) { // 当前商户是终端商户
             * result.setMessage("只有铺货商及顶级代理商可以修改，其它类型的商户无权修改设备费用!");
             * result.setResult(ResultCommandBaseObject.FAILED); return result;
             * }
             */
            List<ShareDeviceInfoModel> list = null;
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 3.1代理商

            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                pageFilter.put("agents1Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                pageFilter.put("agents2Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                pageFilter.put("agents3Id", merchantInfoModel.getId());
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                pageFilter.put("shopkeeperId", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                pageFilter.put("allianceBusinessId", merchantInfoModel.getId());
            }
            // 根据获到的设备的过虑条件1.
            if (deviceStartId != null && !deviceStartId.isEmpty()) {
                pageFilter.put("deviceStartId", deviceStartId);
            }
            if (deviceEndId != null && !deviceEndId.isEmpty()) {
                pageFilter.put("deviceEndId", deviceEndId);
            }
            // 根据获到的设备的过虑条件2.
            if (deviceIds != null) {
                String[] aryDeviceIds = deviceIds.split(",");
                List<Long> listForDeviceIds = new ArrayList<>();
                Long deviceId = 0L;
                for (String id : aryDeviceIds) {
                    if (id == null || id.isEmpty()) {
                        continue;
                    }
                    try {
                        deviceId = Long.valueOf(id);
                        listForDeviceIds.add(deviceId);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                if (listForDeviceIds != null && listForDeviceIds.size() > 0) {
                    pageFilter.put("deviceIds", listForDeviceIds);
                }
            }
            // 根据获到的设备的过虑条件3.
            if (onLineMerchantId != null && !onLineMerchantId.isEmpty()) {
                try {
                    pageFilter.put("onlineMerchantId", onLineMerchantId);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            // 4. 生成共享设备..
            List<ShareDeviceInfoModel> listForDeviceInfoModel = shareDeviceInfoModelBySelfMapper
                    .getShareDeviceInfoModelByPageFilter(pageFilter);
            // 5.生成费用
            HashMap<Long, List<Long>> feeDevMap = new HashMap<>();
            List<Long> tmpList = new ArrayList<>();
            List<ShareFeeBO> feeBoList = null;
            ShareFeeBO feeBo = null;
            for (ShareDeviceInfoModel tmp : listForDeviceInfoModel) {
                //11,21,31按时间，33按时间和预付费，其它是预付费
                if (feeDevMap.containsKey((long) tmp.getDeviceTypeId())) {
                    tmpList = feeDevMap.get((long) tmp.getDeviceTypeId());
                } else {
                    tmpList = new ArrayList<>();
                }
                tmpList.add(tmp.getId());
                feeDevMap.put((long) tmp.getDeviceTypeId(), tmpList);


//				feeBoList = shareFeeService.getFeeListByDeviceInfoModel(tmp);
//				for (int i = 0; i < feeBoList.size(); i++) {
//					feeBo = feeBoList.get(i);
//					if (feeBo.getFeeTypeId() == ShareFeeTypeEnum.Prepayment.getCode()) {
//						if (feeDevMap.containsKey((long) feeBo.getFeeTypeId())) {
//							tmpList = feeDevMap.get((long) feeBo.getFeeTypeId());
//						} else {
//							tmpList = new ArrayList<>();
//						}
//						tmpList.add(tmp.getId());
//						feeDevMap.put((long) feeBo.getFeeTypeId(), tmpList);
//						continue;
//					}
//					if (feeBo.getFeeTypeId() == ShareFeeTypeEnum.PrepaymentHaveFirstAmount.getCode()) {
//						if (feeDevMap.containsKey((long) feeBo.getFeeTypeId())) {
//							tmpList = feeDevMap.get((long) feeBo.getFeeTypeId());
//						} else {
//							tmpList = new ArrayList<>();
//						}
//						tmpList.add(tmp.getId());
//						feeDevMap.put((long) feeBo.getFeeTypeId(), tmpList);
//						continue;
//					}
//					if (feeBo.getFeeTypeId() >= ShareFeeTypeEnum.Designated_Time_1.getCode()
//							&& feeBo.getFeeTypeId() <= ShareFeeTypeEnum.Designated_Time_24.getCode()) {
//						if (feeDevMap.containsKey((long) ShareFeeTypeEnum.Designated_Time_1.getCode())) {
//							tmpList = feeDevMap.get((long) ShareFeeTypeEnum.Designated_Time_1.getCode());
//						} else {
//							tmpList = new ArrayList<>();
//						}
//						if (!tmpList.contains(tmp.getId())) {
//							tmpList.add(tmp.getId());
//						}
//						feeDevMap.put((long) ShareFeeTypeEnum.Designated_Time_1.getCode(), tmpList);
//						continue;
//					}
//				}
            }
            result.setResult(ResultCommandBaseObject.SUCCESS);
            result.setResponseInfo(feeDevMap);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("editFeeRuleEntry:{}", e);
            // 业务异常
            result.setMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            // 系统异常
            logger.error("获取费用异常{}", e);
            result.setMessage("获取费用异常");
            return result;
        }
    }

    /**
     * 设置费用
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/setBatchCost")
    public Object setBatchCost(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/setBatchCost?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");

            String deviceStartId = request.getParameter("deviceStartId");
            String deviceEndId = request.getParameter("deviceEndId");
            String deviceIds = request.getParameter("deviceIds");
            String onLineMerchantId = request.getParameter("onLineMerchantId");

            logger.info(" 设置费用-setBatchCost,openId:{},merchantId:{},custNo:{}", custNo, merchantId, custNo);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s", urlForBusiness, merchantId, custNo);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "setBatchCost");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，无权修改设备信息!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            // 判断过虑条件是否有问题
            if ((deviceIds == null || deviceIds.isEmpty())
                    && (deviceStartId == null || deviceStartId.isEmpty() || deviceEndId == null
                    || deviceEndId.isEmpty())
                    && (onLineMerchantId == null || onLineMerchantId.isEmpty() || onLineMerchantId.equals("0"))) {
                model.addAttribute("未输入获取设备的过虑条件,请输入获取设备的过虑条件!");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            List<ShareDeviceInfoModel> list = null;
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 3.1代理商

            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                pageFilter.put("agents1Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                pageFilter.put("agents2Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                pageFilter.put("agents3Id", merchantInfoModel.getId());
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                pageFilter.put("shopkeeperId", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                pageFilter.put("allianceBusinessId", merchantInfoModel.getId());
            }
            // 根据获到的设备的过虑条件1.
            if (deviceStartId != null && !deviceStartId.isEmpty()) {
                pageFilter.put("deviceStartId", deviceStartId);
            }
            if (deviceEndId != null && !deviceEndId.isEmpty()) {
                pageFilter.put("deviceEndId", deviceEndId);
            }
            // 根据获到的设备的过虑条件2.
            if (deviceIds != null) {
                String[] aryDeviceIds = deviceIds.split(",");
                List<Long> listForDeviceIds = new ArrayList<>();
                Long deviceId = 0L;
                for (String id : aryDeviceIds) {
                    if (id == null || id.isEmpty()) {
                        continue;
                    }
                    try {
                        deviceId = Long.valueOf(id);
                        listForDeviceIds.add(deviceId);
                    } catch (Exception e) {
                    }
                }
                if (listForDeviceIds != null && listForDeviceIds.size() > 0) {
                    pageFilter.put("deviceIds", listForDeviceIds);
                }
            }
            // 根据获到的设备的过虑条件3.
            if (onLineMerchantId != null && !onLineMerchantId.isEmpty()) {
                try {
                    pageFilter.put("onlineMerchantId", onLineMerchantId);
                } catch (Exception e) {
                }
            }
            String selectFeeTypeOptions = "";
            List<ShareFeeTypeEnum> listForShareFeeTypeEnum = new ArrayList<>();
            listForShareFeeTypeEnum.add(ShareFeeTypeEnum.Prepayment);
            listForShareFeeTypeEnum.add(ShareFeeTypeEnum.PrepaymentHaveFirstAmount);
            ShareFeeTypeEnum itemForFeeType;
            for (int i = 0; i < listForShareFeeTypeEnum.size(); i++) {
                itemForFeeType = listForShareFeeTypeEnum.get(i);
                selectFeeTypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFeeTypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
            }
            model.addAttribute("selectFeeTypeOptions", selectFeeTypeOptions);
            // 处理时间控制充电时间的订单...
            String selectFee1TypeOptions = "";
            String selectFee2TypeOptions = selectFee1TypeOptions;
            String selectFee3TypeOptions = selectFee1TypeOptions;

            List<ShareFeeTypeEnum> listForShareFeeTypeEnumWithHour = ShareFeeTypeEnum.getShareFeeTypeByTimeHours();
            for (int i = 0; i < listForShareFeeTypeEnumWithHour.size(); i++) {
                itemForFeeType = listForShareFeeTypeEnumWithHour.get(i);
                // 选项1
                selectFee1TypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFee1TypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
                // 选项2
                selectFee2TypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFee2TypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
                // 选项3
                selectFee3TypeOptions = String.format("%s<option value=\"%d\">%s</option>", selectFee3TypeOptions,
                        itemForFeeType.getCode(), itemForFeeType.getDesc());
            }
            model.addAttribute("selectFee1TypeOptions", selectFee1TypeOptions);
            model.addAttribute("selectFee2TypeOptions", selectFee2TypeOptions);
            model.addAttribute("selectFee3TypeOptions", selectFee3TypeOptions);

            // 4. 生成共享设备..
            List<ShareDeviceInfoModel> listForDeviceInfoModel = shareDeviceInfoModelBySelfMapper
                    .getShareDeviceInfoModelByPageFilter(pageFilter);

            HashMap<Integer, List<Long>> deviceTypeMap = new HashMap<>();
            List<Long> tmpList = new ArrayList<>();
            Set<Integer> feeTypeList = new HashSet<>();
            for (ShareDeviceInfoModel tmp : listForDeviceInfoModel) {
                //11,31按时间，21,33按时间和预付费，其它是预付费
                if (tmp.getDeviceTypeId().intValue() == 11 || tmp.getDeviceTypeId().intValue() == 31) {
                    feeTypeList.add(1);
                    if (deviceTypeMap.containsKey(1)) {
                        tmpList = deviceTypeMap.get(1);
                    } else {
                        tmpList = new ArrayList<>();
                    }
                    tmpList.add(tmp.getId());
                    deviceTypeMap.put(1, tmpList);
                } else if ( tmp.getDeviceTypeId().intValue() == 21 ||tmp.getDeviceTypeId().intValue() == 33 ) {
                    feeTypeList.add(2);
                    if (deviceTypeMap.containsKey(2)) {
                        tmpList = deviceTypeMap.get(2);
                    } else {
                        tmpList = new ArrayList<>();
                    }
                    tmpList.add(tmp.getId());
                    deviceTypeMap.put(2, tmpList);
                } else {
                    feeTypeList.add(3);
                    if (deviceTypeMap.containsKey(3)) {
                        tmpList = deviceTypeMap.get(3);
                    } else {
                        tmpList = new ArrayList<>();
                    }
                    tmpList.add(tmp.getId());
                    deviceTypeMap.put(3, tmpList);
                }

            }
            model.addAttribute("feeTypeList", feeTypeList);
            model.addAttribute("deviceTypeMap", deviceTypeMap);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfoModel", merchantInfoModel);
            return getPrefix() + "common/setBatchCost.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "累计收入查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * <p>
     * 根据设备编号，查看当前设备对应的分润...
     * </p>
     *
     * @return
     */
    @RequestMapping("/getDeviceProfitScaleEntry")
    @ResponseBody
    public Object getDeviceProfitScaleEntry(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merId");
            String deviceStartId = request.getParameter("deviceStartId");
            String deviceEndId = request.getParameter("deviceEndId");
            String deviceIds = request.getParameter("deviceIds");
            String onLineMerchantId = request.getParameter("onLineMerchantId");
            // 2. 判断是否登录...
            // 2.0 判断过虑条件是否有问题
            if ((deviceIds == null || deviceIds.isEmpty())
                    && (deviceStartId == null || deviceStartId.isEmpty() || deviceEndId == null
                    || deviceEndId.isEmpty())
                    && (onLineMerchantId == null || onLineMerchantId.isEmpty() || onLineMerchantId.equals("0"))) {
                result.setMessage("未输入获取设备的过虑条件,请输入获取设备的过虑条件!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");
            // 商户
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 商户和客户关系
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改设备分润信息！", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 判断设备是否正确.
            // 3. 判断设备是否正确.
            if ((deviceIds == null || deviceIds.isEmpty())
                    && (deviceStartId == null || deviceStartId.isEmpty() || deviceEndId == null
                    || deviceEndId.isEmpty())
                    && (onLineMerchantId == null || onLineMerchantId.isEmpty() || onLineMerchantId.equals("0"))) {
                result.setMessage("未输入获取设备的过虑条件,请输入获取设备的过虑条件!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            /*
             * if (!(merchantInfoModel.getMerchantType().toString()
             * .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))
             * || (merchantInfoModel.getMerchantType().toString()
             * .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))))
             * ) { // 当前商户是终端商户
             * result.setMessage("只有铺货商及顶级代理商可以修改，其它类型的商户无权修改!");
             * result.setResult(ResultCommandBaseObject.FAILED); return result;
             * }
             */
            List<ShareDeviceInfoModel> list = null;
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 3.1代理商
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                pageFilter.put("agents1Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                pageFilter.put("agents2Id", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                pageFilter.put("agents3Id", merchantInfoModel.getId());
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                pageFilter.put("shopkeeperId", merchantInfoModel.getId());
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                pageFilter.put("allianceBusinessId", merchantInfoModel.getId());
            }
            // 3.3.处理过虑条件获到充电器
            if (deviceStartId != null && !deviceStartId.isEmpty()) {
                pageFilter.put("deviceStartId", deviceStartId);
            }
            if (deviceEndId != null && !deviceEndId.isEmpty()) {
                pageFilter.put("deviceEndId", deviceEndId);
            }
            if (deviceIds != null) {
                String[] aryDeviceIds = deviceIds.split(",");
                List<Long> listForDeviceIds = new ArrayList<>();
                Long deviceId = 0L;
                for (String id : aryDeviceIds) {
                    if (id == null || id.isEmpty()) {
                        continue;
                    }
                    try {
                        deviceId = Long.valueOf(id);
                        listForDeviceIds.add(deviceId);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                if (listForDeviceIds != null && listForDeviceIds.size() > 0) {
                    pageFilter.put("deviceIds", listForDeviceIds);
                }
            }
            if (onLineMerchantId != null && !onLineMerchantId.isEmpty()) {
                try {
                    pageFilter.put("onlineMerchantId", onLineMerchantId);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            // 4. 生成共享设备..
            List<ShareDeviceInfoModel> listForDeviceInfoModel = shareDeviceInfoModelBySelfMapper
                    .getShareDeviceInfoModelByPageFilter(pageFilter);
            // 5.生成分润
            HashMap<String, List<Long>> profitDevMap = new HashMap<>();
            List<Long> tmpList = new ArrayList<>();
            String keyMap = "";
            BigDecimal platformTmp = null, agents1Rato = null, agents2Rato = null, agents3Rato = null,
                    shopkeeperRato = null, allianceBusinessRato = null;
            for (ShareDeviceInfoModel tmp : listForDeviceInfoModel) {
                platformTmp = tmp.getPlatformRato() == null ? new BigDecimal("0") : tmp.getPlatformRato();
                agents1Rato = tmp.getAgents1Rato() == null ? new BigDecimal("0") : tmp.getAgents1Rato();
                agents2Rato = tmp.getAgents2Rato() == null ? new BigDecimal("0") : tmp.getAgents2Rato();
                agents3Rato = tmp.getAgents3Rato() == null ? new BigDecimal("0") : tmp.getAgents3Rato();
                shopkeeperRato = tmp.getShopkeeperRato() == null ? new BigDecimal("0") : tmp.getShopkeeperRato();
                allianceBusinessRato = tmp.getAllianceBusinessRate() == null ? new BigDecimal("0")
                        : tmp.getAllianceBusinessRate();
                if (merchantInfoModel.getMerchantType().toString()
                        .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                    // 顶级代理
                    keyMap = String.format("%.2f_%.2f_%.2f_%.2f_%.2f_%.2f", platformTmp, agents1Rato, agents2Rato,
                            agents3Rato, shopkeeperRato, allianceBusinessRato);

                } else if (merchantInfoModel.getMerchantType().toString()
                        .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                    // 一级代理
                    keyMap = String.format("%.2f_%.2f_%.2f_%.2f_%.2f", platformTmp.add(agents1Rato), agents2Rato,
                            agents3Rato, shopkeeperRato, allianceBusinessRato);

                } else if (merchantInfoModel.getMerchantType().toString()
                        .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                    // 二级代理
                    keyMap = String.format("%.2f_%.2f_%.2f_%.2f", platformTmp.add(agents1Rato).add(agents2Rato),
                            agents3Rato, shopkeeperRato, allianceBusinessRato);

                } else if (merchantInfoModel.getMerchantType().toString()
                        .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                    // 铺货商
                    keyMap = String.format("%.2f_%.2f_%.2f",
                            platformTmp.add(agents1Rato).add(agents2Rato).add(agents3Rato), shopkeeperRato,
                            allianceBusinessRato);

                } else {
                    logger.warn("商户类型：" + merchantInfoModel.getMerchantType() + "merchantId:"
                            + merchantInfoModel.getId() + "不支持此商户类型修改分润！");
                    result.setMessage("不支持此商户类型修改分润！");
                    return result;
                }
                if (profitDevMap.containsKey(keyMap)) {
                    tmpList = profitDevMap.get(keyMap);
                    tmpList.add(tmp.getId());
                } else {
                    tmpList = new ArrayList<>();
                    tmpList.add(tmp.getId());
                }
                profitDevMap.put(keyMap, tmpList);
            }
            result.setResult(ResultCommandBaseObject.SUCCESS);
            result.setResponseInfo(profitDevMap);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("getDeviceProfitScaleEntry:{}", e);
            // 业务异常
            result.setMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            // 系统异常
            logger.error("根据设备编号，查看当前设备对应的分润.getDeviceProfitScaleEntry..{}", e);
            result.setMessage("获取分润信息失败!");
            return result;
        }
    }

    /**
     * 设置分润
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/setBatchProfit")
    public Object setBatchProfit(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/setBatchProfit?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            logger.info(" 设置费用-setBatchProfit,openId:{},merchantId:{},custNo:{}", custNo, merchantId, custNo);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s", urlForBusiness, merchantId, custNo);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "setBatchProfit");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 只有顶级代理或铺货商可以批量设置...
            /*
             * if (!((merchantInfoModel.getMerchantType().toString()
             * .equals(MerchantTypeEnum.DAI_LI_SHANG1.getCode().toString())) ||
             * (merchantInfoModel.getMerchantType().toString()
             * .equals(MerchantTypeEnum.PUHUO_SHANG.getCode().toString())))) {
             * model.addAttribute("content", "您无权修改，只有顶级代理商或铺货商可以批量修改 !");
             * model.addAttribute("title", "error"); model.addAttribute("icon",
             * "error"); return getPrefix() + "error.html"; }
             */
            // 3 查询数据
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，无权修改设备信息!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            // 每个商户不一样的页面
            String pageStr = "";
            if (merchantInfoModel.getMerchantType()
                    .byteValue() == (byte) MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()) {
                pageStr =getPrefix()+ WxAppAgentController.PREFIXBASE + "setBatchProfit1.html";
            }
            if (merchantInfoModel.getMerchantType()
                    .byteValue() == (byte) MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()) {
                pageStr =getPrefix()+ WxAppAgentController.PREFIXBASE + "setBatchProfit2.html";
            }
            if (merchantInfoModel.getMerchantType()
                    .byteValue() == (byte) MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
                pageStr = getPrefix()+ WxAppAgentController.PREFIXBASE + "setBatchProfit3.html";
            }
            if (merchantInfoModel.getMerchantType()
                    .byteValue() == (byte) MerchantTypeEnum.PUHUO_SHANG.getCode().intValue()) {
                pageStr = getPrefix()+ WxAppShopkeeperController.PREFIXBASE + "setBatchProfit.html";
            }
            return pageStr;
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "累计收入查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 批理编辑费用
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/batchEditProfitSave")
    @ResponseBody
    public Object batchEditProfitSave(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String merchantId = request.getParameter("merId");
        String editBatchProfitScaleVOsJson = request.getParameter("editBatchProfitScaleVOsJson");
        try {
            if (editBatchProfitScaleVOsJson == null || editBatchProfitScaleVOsJson.isEmpty()) {
                result.setMessage("未找到要批量修改分润的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 1.
            List<BatchEditProfitCommand> list = JSON.parseArray(editBatchProfitScaleVOsJson,
                    BatchEditProfitCommand.class);
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 判断设备是否正确.
            if (list == null || list.size() <= 0) {
                result.setMessage("未找到要批量修改分润的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            BigDecimal total = new BigDecimal("0");
            BigDecimal ratio100 = new BigDecimal("100");
            String oldPlatformRatio;
            BigDecimal oldPlatformRatioBig;
            String[] keyAry;
            int iSizeForKeyAry = 0;
            //
            for (BatchEditProfitCommand batchEditProfitCommand : list) {
                total = new BigDecimal("0");
                if (batchEditProfitCommand.getKey() == null || batchEditProfitCommand.getKey().isEmpty()) {
                    result.setMessage("参数不正确,请重新设置分润!");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                keyAry = batchEditProfitCommand.getKey().split("_");
                oldPlatformRatio = keyAry[0];
                iSizeForKeyAry = keyAry.length;// 6 顶级，5：一级，4：二级，3：铺货，
                oldPlatformRatioBig = new BigDecimal(oldPlatformRatio);
                if (oldPlatformRatioBig.compareTo(batchEditProfitCommand.getPlatformRatio()) != 0) {
                    result.setMessage("您无权修改平台分润!");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                // platformratio包括:
                // size=6:包括平台，5：平台+顶代，4：平强+顶代+一级代，3：平台+顶代+一代+二代
                if (batchEditProfitCommand.getPlatformRatio() != null) {
                    total = total.add(batchEditProfitCommand.getPlatformRatio().abs());
                }
                if (iSizeForKeyAry >= 6) {//顶代修改
                    if (batchEditProfitCommand.getAgents1Ratio() != null) {
                        total = total.add(batchEditProfitCommand.getAgents1Ratio().abs());
                    }
                }
                if (iSizeForKeyAry >= 5) {//一级代理修改..
                    if (batchEditProfitCommand.getAgents2Ratio() != null) {
                        total = total.add(batchEditProfitCommand.getAgents2Ratio().abs());
                    }
                }
                if (iSizeForKeyAry >= 4) {//二级代理修改..
                    if (batchEditProfitCommand.getAgents3Ratio() != null) {
                        total = total.add(batchEditProfitCommand.getAgents3Ratio().abs());
                    }
                }
                if (iSizeForKeyAry >= 3) {//铺货商修改..
                    if (batchEditProfitCommand.getShopkeeperRatio() != null) {
                        total = total.add(batchEditProfitCommand.getShopkeeperRatio().abs());
                    }
                }
                if (batchEditProfitCommand.getAllianceBusinessRatio() != null) {
                    total = total.add(batchEditProfitCommand.getAllianceBusinessRatio().abs());
                }
                if (total.compareTo(ratio100) != 0) {
                    result.setMessage("所有商户分润之和不等于100,请输入正常的分润数据!");
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
            }
            // 4. 批量修改
            String filter = "";
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                filter = String.format(" and agents1_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                filter = String.format(" and agents2_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                filter = String.format(" and agents3_id=%d %s", merchantInfoModel.getId(), filter);
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                filter = String.format(" and shopkeeper_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                filter = String.format(" and alliance_business_id=%d %s", merchantInfoModel.getId(), filter);
            }

            if (filter == "" || filter.isEmpty()) {
                filter = null;
            }
            shareDeviceService.batchEditProfit(list, filter);
            // 5.
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("batchEditFeeRule 批理设备费用", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("修改消息状态异常 batchEditFeeRule！", e);
            result.setMessage("批理设备费用修改失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 去设置上下架
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/setBatchGroundingOrNot")
    public Object setBatchGroundingOrNot(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/setBatchCost?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            logger.info(" 设置费用-setBatchGroundingOrNot,openId:{},merchantId:{},custNo:{}", custNo, merchantId, custNo);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s", urlForBusiness, merchantId, custNo);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "setBatchCost");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // // 只有顶级代理或铺货商可以批量设置...
            // if (merchantInfoModel.getMerchantType().toString()
            // .equals(MerchantTypeEnum.TERMINAL.getCode().toString())) {
            // model.addAttribute("content", "您无权修改，只有代理商或铺货商可以批量修改 !");
            // model.addAttribute("title", "error");
            // model.addAttribute("icon", "error");
            // return getPrefix() + "error.html";
            // }
            // 3 查询数据
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，无权修改设备信息!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/setBatchGroundingOrNot.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "累计收入查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 绑定商家
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/setBatchBindMerchant")
    public Object setBatchBindMerchant(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/setBatchBindMerchant?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            logger.info(" 设置费用-setBatchBindMerchant,openId:{},merchantId:{},custNo:{}", custNo, merchantId, custNo);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s", urlForBusiness, merchantId, custNo);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "setBatchCost");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // 只有终终端不能批量设置
            /*
             * // 只有顶级代理或铺货商可以批量设置... if
             * (!((merchantInfoModel.getMerchantType().toString()
             * .equals(MerchantTypeEnum.DAI_LI_SHANG1.getCode().toString())) ||
             * (merchantInfoModel.getMerchantType().toString()
             * .equals(MerchantTypeEnum.PUHUO_SHANG.getCode().toString())))) {
             * model.addAttribute("content", "您无权修改，只有顶级代理商或铺货商可以批量修改 !");
             * model.addAttribute("title", "error"); model.addAttribute("icon",
             * "error"); return getPrefix() + "error.html"; }
             */
            // 3 查询数据
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，无权修改设备信息!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/setBatchBindMerchant.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "累计收入查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 解除绑定商家
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/setBatchUnBindMerchant")
    public Object setBatchUnBindMerchant(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/setBatchUnBindMerchant?flag=1";
            String openId = request.getParameter("openId");
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            logger.info(" 设置费用-setBatchUnBindMerchant,openId:{},merchantId:{},custNo:{}", custNo, merchantId, custNo);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s", urlForBusiness, merchantId, custNo);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "setBatchCost");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            // // 只有顶级代理或铺货商可以批量设置...
            // if
            // (merchantInfoModel.getMerchantType().toString().equals(MerchantTypeEnum.TERMINAL.getCode().toString()))
            // {
            // model.addAttribute("content", "您无权修改，只有顶级代理商或铺货商可以批量修改 !");
            // model.addAttribute("title", "error");
            // model.addAttribute("icon", "error");
            // return getPrefix() + "error.html";
            // }
            // 3 查询数据
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，无权修改设备信息!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/setBatchUnBindMerchant.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "累计收入查询失败，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 解除绑定商家
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/setBatchSuccess")
    public Object setBatchSuccess(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/setBatchSuccess?flag=1";
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            String state = request.getParameter("state");
            String type = request.getParameter("type");
            logger.info(" 设置费用-setBatchSuccess,state:{},merchantId:{},custNo:{}", state, merchantId, custNo);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s", urlForBusiness, merchantId, custNo);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "setBatchCost");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            model.addAttribute("type", type);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            /*
             * // 只有顶级代理或铺货商可以批量设置... if
             * (merchantInfoModel.getMerchantType().toString().equals(
             * MerchantTypeEnum. TERMINAL.getCode().toString())) {
             * model.addAttribute("content", "您无权修改，只有顶级代理商或铺货商可以批量修改 !");
             * model.addAttribute("title", "error"); model.addAttribute("icon",
             * "error"); return getPrefix() + "error.html"; }
             */
            // 3 查询数据
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，无权修改设备信息!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            model.addAttribute("state", state);
            return getPrefix() + "common/setBatchSuccess.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "跳转批量设备成功，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * <p>
     * 根据商户id得到些商户下的设备列表.
     * </p>
     *
     * @return
     */
    @RequestMapping("/getDeviceIdsByOnlineMarchantId")
    @ResponseBody
    public Object getDeviceIdsByOnlineMarchantId(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            // 1.接收参数
            String merchantId = request.getParameter("merchantId");
            String onLineMerchantId = request.getParameter("onLineMerchantId");
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (onLineMerchantId == null || onLineMerchantId == "") {
                result.setMessage("未输入终端店铺id，请输入终端店铺id!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后操作!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            // 2.2 商户
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            MerchantInfoModel onlineMerchantInfoModel = merchantInfoModelMapper
                    .selectByPrimaryKey(Long.valueOf(onLineMerchantId));
            if (onlineMerchantInfoModel == null) {
                result.setMessage(String.format("输入终端商户Id(%s)在系统中不在存，请传入正确的终端商户Id!", onLineMerchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (onlineMerchantInfoModel.getMerchantType() != null && !(onlineMerchantInfoModel.getMerchantType()
                    .toString().equals(MerchantTypeEnum.TERMINAL.getCode().toString())
                    || onlineMerchantInfoModel.getMerchantType().toString()
                    .equals(MerchantTypeEnum.JIA_MENG_SHANG.getCode().toString()))) {
                result.setMessage(String.format("输入的商户Id(%s)类型不是加盟商或终端，不支持批量操作!", onLineMerchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;

            }
            // 商户和客户关系
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改设备分润信息！", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            /*
             * if (!(merchantInfoModel.getMerchantType().toString()
             * .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))
             * || (merchantInfoModel.getMerchantType().toString()
             * .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))))
             * ) { // 当前商户是终端商户
             * result.setMessage("只有铺货商及顶级代理商可以修改，其它类型的商户无权修改!");
             * result.setResult(ResultCommandBaseObject.FAILED); return result;
             * }
             */
            List<ShareDeviceInfoModel> list = null;
            HashMap<String, Object> pageFilter = new HashMap<>();
            // 3.1代理商

            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                pageFilter.put("agents1Id", merchantInfoModel.getId());
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                pageFilter.put("shopkeeperId", merchantInfoModel.getId());
            }
            // 3.3 加盟商
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                pageFilter.put("allianceBusinessId", merchantInfoModel.getId());
            }
            // 3.4 终端..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
                pageFilter.put("onlineMerchantId", merchantInfoModel.getId());
            }
            // 根据获到的设备的过虑条件3.
            if (onLineMerchantId != null && !onLineMerchantId.isEmpty()) {
                try {
                    pageFilter.put("onlineMerchantId", onLineMerchantId);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            //
            // 4. 生成共享设备..
            List<ShareDeviceInfoModel> listForDeviceInfoModel = shareDeviceInfoModelBySelfMapper
                    .getShareDeviceInfoModelByPageFilter(pageFilter);
            // 5.生成分润
            HashMap<String, Object> profitDevMap = new HashMap<>();
            List<Long> tmpList = new ArrayList<>();
            String keyMap = "";
            for (ShareDeviceInfoModel tmp : listForDeviceInfoModel) {
                tmpList.add(tmp.getId());
            }
            profitDevMap.put("onlineMerchantId", onlineMerchantInfoModel.getId());
            profitDevMap.put("onlineMerchantCn", onlineMerchantInfoModel.getName());
            profitDevMap.put("onlineMerchantType", onlineMerchantInfoModel.getMerchantType());
            profitDevMap.put("devicesId", tmpList);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            result.setResponseInfo(profitDevMap);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.warn("getDeviceProfitScaleEntry:{}", e);
            // 业务异常
            result.setMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            // 系统异常
            logger.error("根据设备编号，查看当前设备对应的分润.getDeviceProfitScaleEntry..{}", e);
            result.setMessage("获取分润信息失败!");
            return result;
        }
    }

    /**
     * 批理修改设备状态
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/batchDeviceStatusSave")
    @ResponseBody
    public Object batchDeviceStatusSave(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String merchantId = request.getParameter("merId");
        String custNo = request.getParameter("custNo");
        String deviceIds = request.getParameter("deviceIds");
        String type = request.getParameter("type");
        try {
            if (deviceIds == null || deviceIds.isEmpty()) {
                result.setMessage("未找到要批量修改状态的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 1.
            List<Long> list = JSON.parseArray(deviceIds, Long.class);
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 判断设备是否正确.
            if (list == null || list.size() <= 0) {
                result.setMessage("未找到要批量修改分润的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            ShareDeviceInfoModel deviceInfoModel = new ShareDeviceInfoModel();
            if (type.equals("13")) {
                deviceInfoModel.setDeviceStatus(ShareDeviceStatusEnum.activation.getCode());
                deviceInfoModel.setOnlineDatetime(new Date());
            } else {
                deviceInfoModel.setDeviceStatus(ShareDeviceStatusEnum.inactivation.getCode());
            }
            deviceInfoModel.setUpdateDatetime(new Date());
            // 4. 批量修改费用
            String filter = "";
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                filter = String.format(" and agents1_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                filter = String.format(" and agents2_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                filter = String.format(" and agents3_id=%d %s", merchantInfoModel.getId(), filter);
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                filter = String.format(" and shopkeeper_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                filter = String.format(" and alliance_business_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (filter == null) {
                filter = "";
            }
            shareDeviceService.batchEditDeviceStatus(deviceInfoModel, list, filter);
            // 5.
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("batchEditFeeRule 批理设备费用", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("修改消息状态异常 batchEditFeeRule！", e);
            result.setMessage("批理设备费用修改失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 查询商户信息.
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryMerchantInfo")
    @ResponseBody
    public Object queryMerchantInfo(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String merchantId = request.getParameter("merchantId"); // 用户输入的
        String merId = request.getParameter("merId");
        String merchantType = request.getParameter("merchantType");
        try {
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (merchantId == null || merchantId == "") {
                result.setMessage("请输入商户id!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merId == null || merId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 查询商户
            MerchantInfoModel merchantInfoModelResult = merchantInfoModelMapper
                    .selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModelResult == null) {
                result.setMessage(String.format("输入的商户ID(%d)在系统中不存在!", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merchantInfoModelResult.getMerchantType() != null
                    && !merchantInfoModelResult.getMerchantType().toString().equals(merchantType)) {
                result.setMessage(String.format("输入的商户(%d)的类型不一至，请输入正确的商户!", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            result.setResponseInfo(merchantInfoModelResult);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("queryMerchantInfo 查询商户类型。。。", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("queryMerchantInfo", e);
            result.setMessage("查询商户失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 批量绑定商户...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/bandingMerchant")
    @ResponseBody
    public Object bandingMerchant(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String merId = request.getParameter("merId");
        String custNo = request.getParameter("custNo");
        String deviceIds = request.getParameter("deviceIds");
        String firstLevelAgentsId = request.getParameter("firstLevelAgentsId");
        String firstLevelAgentsCn = request.getParameter("firstLevelAgentsCn");
        String secondLevelAgentsId = request.getParameter("secondLevelAgentsId");
        String secondLevelAgentsCn = request.getParameter("secondLevelAgentsCn");
        String shopkeeperId = request.getParameter("shopkeeperId");
        String shopkeeperCn = request.getParameter("shopkeeperCn");
        String allianceBusinessId = request.getParameter("allianceBusinessId");
        String allianceBusinessCn = request.getParameter("allianceBusinessCn");
        String merchantId = request.getParameter("merchantId"); // 终端
        try {
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if ((merchantId == null || merchantId == "") && (firstLevelAgentsId == null || firstLevelAgentsId == "")
                    && (secondLevelAgentsId == null || secondLevelAgentsId == "")
                    && (shopkeeperId == null || shopkeeperId == "")
                    && (allianceBusinessId == null || allianceBusinessId == "")) {
                result.setMessage("请输入要绑定的商户id!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merId == null || merId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (deviceIds == null || deviceIds == "") {
                result.setMessage("请选择需要批理绑定的商户的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2.2 设备列表.
            List<Long> listForDeviceIds = JSON.parseArray(deviceIds, Long.class);

            if (listForDeviceIds.size() <= 0) {
                result.setMessage("请选择需要批理绑定的商户的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 查询商户
            ShareDeviceInfoModel deviceInfoModel = new ShareDeviceInfoModel();
            if (merchantId != null && !merchantId.isEmpty() && !merchantId.equals("0")) {// 终端
                MerchantInfoModel merchantInfoTerminal = merchantInfoModelMapper
                        .selectByPrimaryKey(Long.valueOf(merchantId));
                if (merchantInfoTerminal == null) {
                    result.setMessage(String.format("输入的终端商户ID(%d)在系统中不存在!", merchantInfoModel.getId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                deviceInfoModel.setOnlineAddress(merchantInfoTerminal.getAddr());
                deviceInfoModel.setOnlineMerchantCn(merchantInfoTerminal.getName());
                deviceInfoModel.setOnlineMerchantId(merchantInfoTerminal.getId());
            }
            if (firstLevelAgentsId != null && !firstLevelAgentsId.isEmpty() && !firstLevelAgentsId.equals("0")) {// 2级代理
                MerchantInfoModel merchantInfoAgentsId1 = merchantInfoModelMapper
                        .selectByPrimaryKey(Long.valueOf(firstLevelAgentsId));
                if (merchantInfoAgentsId1 == null) {
                    result.setMessage(String.format("输入的二级代理商户ID(%d)在系统中不存在!", merchantInfoAgentsId1.getId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                deviceInfoModel.setAgents2Cn(merchantInfoAgentsId1.getName());
                deviceInfoModel.setAgents2Id(merchantInfoAgentsId1.getId());
            }
            if (secondLevelAgentsId != null && !secondLevelAgentsId.isEmpty() && !secondLevelAgentsId.equals("0")) {// 3级代理
                MerchantInfoModel merchantInfoAgentsId2 = merchantInfoModelMapper
                        .selectByPrimaryKey(Long.valueOf(secondLevelAgentsId));
                if (merchantInfoAgentsId2 == null) {
                    result.setMessage(String.format("输入的三级代理商户ID(%d)在系统中不存在!", merchantInfoAgentsId2.getId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                deviceInfoModel.setAgents3Cn(merchantInfoAgentsId2.getName());
                deviceInfoModel.setAgents3Id(merchantInfoAgentsId2.getId());
            }

            if (shopkeeperId != null && !shopkeeperId.isEmpty() && !shopkeeperId.equals("0")) {// 铺货商
                MerchantInfoModel merchantInfoShopkeeperId = merchantInfoModelMapper
                        .selectByPrimaryKey(Long.valueOf(shopkeeperId));
                if (shopkeeperId == null) {
                    result.setMessage(String.format("输入的铺货商户ID(%d)在系统中不存在!", merchantInfoShopkeeperId.getId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                deviceInfoModel.setShopkeeperCn(merchantInfoShopkeeperId.getName());
                deviceInfoModel.setShopkeeperId(merchantInfoShopkeeperId.getId());
            }
            if (allianceBusinessId != null && !allianceBusinessId.isEmpty() && !allianceBusinessId.equals("0")) {// 加盟商
                MerchantInfoModel merchantInfoAllianceBusinessId = merchantInfoModelMapper
                        .selectByPrimaryKey(Long.valueOf(allianceBusinessId));
                if (merchantInfoAllianceBusinessId == null) {
                    result.setMessage(String.format("输入的加盟商ID(%d)在系统中不存在!", merchantInfoAllianceBusinessId.getId()));
                    result.setResult(ResultCommandBaseObject.FAILED);
                    return result;
                }
                deviceInfoModel.setAllianceBusinessCn(merchantInfoAllianceBusinessId.getName());
                deviceInfoModel.setAllianceBusinessId(merchantInfoAllianceBusinessId.getId());
            }
            boolean isValidateBindMer =  deviceInfoService.isBindMer(listForDeviceIds,deviceInfoModel);
            if(isValidateBindMer){
                result.setMessage("请先把这批设备的商户解绑，再做批量绑定操作！");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 更新...
            String filter = null;
            deviceInfoModel.setUpdateDatetime(new Date());
            shareDeviceInfoModelBySelfMapper.batchBindMerByDeviceNoList(deviceInfoModel, listForDeviceIds, filter);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("queryMerchantInfo 查询商户类型。。。", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("queryMerchantInfo", e);
            result.setMessage("查询商户失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 批量解绑定商户...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/batchUnBandingMerchant")
    @ResponseBody
    public Object batchUnBandingMerchant(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String merId = request.getParameter("merId");
        String custNo = request.getParameter("custNo");
        String deviceIds = request.getParameter("deviceIds");
        String unBandingMerchantEnums = request.getParameter("unBandingMerchantEnums"); // 商户类型
        try {
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            if (unBandingMerchantEnums == null || unBandingMerchantEnums == "") {
                result.setMessage("请选择要绑定的商户类型!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merId == null || merId == "") {
                result.setMessage("参数错误，未传入商户id，请确定是否登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (deviceIds == null || deviceIds == "") {
                result.setMessage("请选择需要批理绑定的商户的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            List<Long> listForMerType = JSON.parseArray(unBandingMerchantEnums, Long.class);
            if (listForMerType == null && listForMerType.size() <= 0) {
                result.setMessage("请选择要绑定的商户类型!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2.2 设备列表.
            List<Long> listForDeviceIds = JSON.parseArray(deviceIds, Long.class);
            if (listForDeviceIds.size() <= 0) {
                result.setMessage("请选择需要批理绑定的商户的设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限修改商户信息", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 查询商户 deviceInfoModel.setUpdateDatetime(new Date());
            // 更新...
            ShareDeviceInfoModel deviceInfoModel = new ShareDeviceInfoModel();
            Map<String, Object> map = new HashMap<>();
            for (Long merType : listForMerType) {
                if (merType == null) {
                    continue;
                }
                if (merType.intValue() == MerchantTypeEnum.TERMINAL.getCode().intValue()) {
                    map.put("terminal", 1L);
                }
                if (merType.intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()) {
                    map.put("daiLiShang2", 1L);
                }
                if (merType.intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
                    map.put("daiLiShang3", 1L);
                }
                if (merType.intValue() == MerchantTypeEnum.PUHUO_SHANG.getCode().intValue()) {
                    map.put("puHuoShang", 1L);
                }
                if (merType.intValue() == MerchantTypeEnum.JIA_MENG_SHANG.getCode().intValue()) {
                    map.put("jiaMengShang", 1L);
                }
            }
            // 3.1 解绑商户
            map.put("list", listForDeviceIds);
            // filter过滤条件
            String filter = "";
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
                filter = String.format(" and agents1_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
                filter = String.format(" and agents2_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
                filter = String.format(" and agents3_id=%d %s", merchantInfoModel.getId(), filter);
            }
            // 3.2铺货商..
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
                filter = String.format(" and shopkeeper_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (merchantInfoModel.getMerchantType().toString()
                    .equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
                filter = String.format(" and alliance_business_id=%d %s", merchantInfoModel.getId(), filter);
            }
            if (filter == "" || filter.isEmpty()) {
                filter = null;
            }
            map.put("filter", filter);
            shareDeviceInfoModelBySelfMapper.batchUnBindMerByDeviceNoList(map);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("queryMerchantInfo 查询商户类型。。。", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("queryMerchantInfo", e);
            result.setMessage("查询商户失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 处理商户提现
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/insertWithdrawApplyRecord")
    @ResponseBody
    public Object insertWithdrawApplyRecord(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String isWithdrawing = (String) request.getSession().getAttribute("withraw");
        try {
            if ("true".equals(isWithdrawing)) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("请不要重复提交!");
                return result;
            }
            request.getSession().setAttribute("withraw", "true");

            // 1. 判断参数是否正确...
            // 发起申请提现的商户ID
            String merchantId = request.getParameter("merchantId");
            // 税前的提现金额
            String preTaxAmountStr = request.getParameter("preTaxAmount");
            // openId
            String custNo = request.getParameter("custNo");
            // 2.判断参数是否正确
            if (merchantId == null || merchantId.isEmpty() || merchantId == "0") {
                result.setMessage("提现商户id不能为空，请传入商户id!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            logger.info("商户:{}发起提现申请,提现金额为:{}", merchantId, preTaxAmountStr);
            if (StringUtils.isEmpty(preTaxAmountStr)) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("提现金额不能为空!");
                return result;
            }
            // 税前提现金额
            BigDecimal preTaxAmount = new BigDecimal(preTaxAmountStr);
            if (preTaxAmount.compareTo(BigDecimal.ZERO) == 0 || preTaxAmount.compareTo(BigDecimal.ZERO) == -1) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("提现金额不能小于等于零!");
                return result;
            }
            // 单笔提现金额超过2万，则超出限额设置
            if (preTaxAmount.compareTo(new BigDecimal(20000)) == 1) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("根据微信官方规定，单笔提现金额不能大于2万，请拆分成多笔进行提现!");
                return result;
            }
            // 3. 判断是否登录..
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请重新刷新登录后查询!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            if (!custNo.equals(custNoForLogin)) {
                result.setMessage("提现对应的的客户id与登录的客户id不一至，请重新登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 4. 判断商户类型是否正确...
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                result.setMessage(String.format("商户Id(%s)在系统中不在存，请传入正确的商户Id!", merchantId));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(Long.valueOf(custNoForLogin));
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                result.setMessage(String.format("当前账户未绑定当前商户(%d)，无权限操作此功能！", merchantInfoModel.getId()));
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 当前登录账
            CustInfoModel custInfo = custInfoModelMapper.selectByPrimaryKey(key.getCustNo());
            if (null == custInfo) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("系统未登录，请重新登录!");
                return result;
            }
            // 根据商户号和客户类型为商户，查到DB中对应的客户号
            Long merSettleCustNo = custMerchantRefInfoModel.getCustNo();
            // 当前登录到商户界面中的客户与要提现商户对应的结算客户号不相等，则没有权限

            MerchantInfoModel mim = merchantInfoModel;
            if (null == mim.getSettlementAccount()) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("未设置提现结算账号(个人中心账号)，请联系客服设置!");
                return result;
            }
            if (mim.getSettlementAccount().equals(merSettleCustNo)) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("结算账户不一至,您没有权限发起提现申请!");
                return result;
            }
            // 5.得到虚拟账户..
            CustInfoModel custInfoForSettle = custInfoModelMapper
                    .selectMerchantCustInfoByMerchantId(merchantInfoModel.getId());
            if (custInfoForSettle == null) {
                result.setMessage("商户未创建虚拟账户，请联系管理员!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            // 根据客户号查看该客户对应的虚户信息
            CustAccountModel custAccountModel = custAccountModelMapper.selectAccountByCustNo(
                    custInfoForSettle.getCustNo(), CustAccountTypeEnum.RECHARGERACCOUNT.getCode(),
                    CustAccountAmtSumEnum.SUMED.getCode());
            if (custAccountModel == null) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("没有查到该客户的账户信息!");
                return result;
            }
            // 判断该客户账户中可用余额是否充足
            if (custAccountModel.getAvailableBalance().compareTo(preTaxAmount) == -1) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("可提现余额不足!");
                return result;
            }

            MerchantInfoBo mib = new MerchantInfoBo();
            // BeanUtils.copyProperties(mim, mib);
            mib.setAddr(mim.getAddr());
            mib.setClientType(mim.getClientType() != null ? mim.getClientType().intValue() : 0);
            mib.setCreateTime(mim.getCreateTime());
            mib.setId(mim.getId());
            mib.setIdNumber(mim.getIdNumber());
            mib.setIndustryCategoryCn(mim.getIndustryCategoryCn());
            mib.setIndustryCategoryCode(
                    mim.getIndustryCategoryCode() != null ? mim.getIndustryCategoryCode().intValue() : 0);
            mib.setInvestMoney(mim.getInvestMoney());
            mib.setLegalRepresentative(mim.getLegalRepresentative());
            mib.setMerchantLevel(mim.getMerchantLevel() != null ? mim.getMerchantLevel().intValue() : 0);
            mib.setMerchantLevelCn(mim.getMerchantLevelCn());
            mib.setMerchantSubType(mim.getMerchantSubType() != null ? mim.getMerchantSubType().intValue() : 0);
            mib.setMerchantType(mim.getMerchantType() != null ? mim.getMerchantType().intValue() : 0);
            mib.setMerchantTypeCn(mim.getMerchantTypeCn());
            mib.setName(mim.getName());
            mib.setParentMerchantId(mim.getParentMerchantId());
            mib.setParentPersonName(mim.getPersonName());
            mib.setPersonName(mim.getPersonName());
            mib.setRemark(mim.getRemark());
            mib.setShopKeeperType(mim.getShopkeeperType() != null ? mim.getShopkeeperType().intValue() : 0);
            mib.setShopKeeperTypeCn(mim.getShopkeeperTypeCn());
            mib.setStatus(mim.getStatus() != null ? mim.getStatus().intValue() : 0);
            mib.setTelNo(mim.getTelNo());
            mib.setUniformSocialCreditCode(mim.getUniformSocialCreditCode());
            String reqIp = SysUtil.getIpAddress(request);
            logger.info("商户提现申请请求主机IP：{}", reqIp);
            merchantService.executeMerchantWithdraw(preTaxAmount, mib, reqIp, custInfo, true, PayWayEnum.AUTO_PAY);
            result.setErrorCode(ResultCommandBaseObject.SUCCESS);
            result.setMessage("申请提现成功！");
            logger.info("提现申请成功！");
            return result;
        } catch (Exception e) {
            if (e instanceof ExgrainBizUncheckedException) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage(e.getMessage());
                return result;
            }
            logger.error("商户申请提现异常:{}", e);
            result.setErrorCode(ResultCommandBaseObject.FAILED);
            result.setMessage("申请提现失败,请确认账户信息是否一致及提现额度是否正确（大于1元小于5000元，每天最多提现10次,每天最多提现金额为5000元),请联系客服处理!");
            return result;
        } finally {
            request.getSession().removeAttribute("withraw");
        }

    }

    /**
     * 解除绑定商家
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/bindDev")
    public Object bindDev(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        try {
            String urlForBusiness = "/wx/bindDev?flag=1";
            String merchantId = request.getParameter("merchantId");
            String custNo = request.getParameter("custNo");
            if (StringUtils.isEmpty(merchantId) || StringUtils.isEmpty(custNo)) {
                result.setErrorCode(ResultCommandBaseObject.FAILED);
                result.setMessage("账号信息有误!");
                return result;
            }
            logger.info(" 设置费用-bindDev,merchantId:{},custNo:{}", merchantId, custNo);
            urlForBusiness = String.format("%s&merchantId=%s&custNo=%s", urlForBusiness, merchantId, custNo);
            // 1. 进行微信授权.
            Object obj = wxMpOAuthHandle(request, urlForBusiness, "bindDev");
            if (obj.getClass() == ModelAndView.class) {
                // 跳转进行微信授权
                return obj;
            }
            // 请求根目录...
            model.addAttribute("reqUrlRoot", rootUrl);
            // 2. 判断参数是否正确..
            Map<String, String> mapUserInfo = (Map<String, String>) obj;
            String custNoForLogin = mapUserInfo.get("custNo");
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNoForLogin));
            if (custInfoModel == null) {
                model.addAttribute("content", "客户信息异常，请重新登录 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            Long iMerchantId = 0L;
            try {
                iMerchantId = Long.parseLong(merchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (iMerchantId == null || iMerchantId == 0) {
                model.addAttribute("content", String.format("请传入正确的商户id(%s)!", iMerchantId));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
            if (merchantInfoModel == null) {
                model.addAttribute("content", "商户号在系统中不存在，设备信息查询失败 !");
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }
            /*
             * // 只有顶级代理或铺货商可以批量设置... if
             * (!((merchantInfoModel.getMerchantType().toString()
             * .equals(MerchantTypeEnum.DAI_LI_SHANG1.getCode().toString())) ||
             * (merchantInfoModel.getMerchantType().toString()
             * .equals(MerchantTypeEnum.PUHUO_SHANG.getCode().toString())))) {
             * model.addAttribute("content", "您无权修改，只有顶级代理商或铺货商可以批量修改 !");
             * model.addAttribute("title", "error"); model.addAttribute("icon",
             * "error"); return getPrefix() + "error.html"; }
             */
            // 3 查询数据
            // 3 查询数据
            CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
            key.setCustNo(custInfoModel.getCustNo());
            key.setMerchantId(merchantInfoModel.getId());
            CustMerchantRefInfoModel custMerchantRefInfoModel = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
            if (custMerchantRefInfoModel == null) {
                model.addAttribute("content",
                        String.format("当前登录客户号(%d)未绑定商户(%d)，无权修改设备信息!", key.getCustNo(), key.getMerchantId()));
                model.addAttribute("title", "error");
                model.addAttribute("icon", "error");
                return getPrefix() + "error.html";
            }

            model.addAttribute("custMerchantRefInfoModel", custMerchantRefInfoModel);
            model.addAttribute("custInfoModel", custInfoModel);
            model.addAttribute("merchantInfo", merchantInfoModel);
            return getPrefix() + "common/bindDev.html";
        } catch (Exception e) {
            logger.error("..--merchantWithdrawHistory", e);
            model.addAttribute("content", "跳转批量设备成功，请联系客服 !");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
    }

    /**
     * 成功
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/successPage")
    public Object successPage(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("content", "提交成功 !");
        model.addAttribute("title", "success");
        model.addAttribute("icon", "success");

        return getPrefix() + "success.html";
    }

    /**
     * 终端商户通过扫码充电器id
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/terminalBindByChargerID")
    @ResponseBody
    public Object terminalBindByChargerID(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String codeContent = request.getParameter("codeContent"); // 扫码内容..
        String custNo = request.getParameter("custNo");// 客户信息
        try {
            // 1. 判断参数是否正确...
            if (codeContent == null || codeContent.isEmpty()) {
                result.setMessage("未获取到用户扫码内容!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (custNo == null || custNo == "") {
                result.setMessage("未获取到扫码客户号，请退出公众号重新进入!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请刷新重新进入公众号!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            if (!custNoForLogin.equals(custNo)) {
                logger.info(
                        String.format("扫码的客户号与登录公众号的客户号不一至,请重新扫码!custNoForLogin:%s,custNo:%s", custNoForLogin, custNo));
                result.setMessage("扫码的客户号与登录公众号的客户号不一至,请重新扫码!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            List<CustMerchantRefInfoModel> listForMerchantRefInfoModel = custMerchantRefInfoBySelfModelMapper
                    .selectByCustNo(Long.parseLong(custNoForLogin));
            int startIndex = codeContent.indexOf("=");
            String chargerId = "";
            if (startIndex < 0) {
                result.setMessage("无法从二维码中获到充电器id,请确认扫的二维码是否正确!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            // 3. 查询数据
            // 3.1 查询充电器
            chargerId = codeContent.substring(startIndex).replaceAll("=", "");
            if (chargerId == null || chargerId.isEmpty()) {
                result.setMessage("无法从二维码中获到充电器id,请确认扫的二维码是否正确!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            ShareChargerModel shareChargerModel = shareChargerModelMapper.selectByPrimaryKey(Long.parseLong(chargerId));
            if (shareChargerModel == null || shareChargerModel.getDeviceId() == null) {
                result.setMessage("充电器未绑定设备，无法激活，请联系管理员绑定设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3.2 查询设备
            ShareDeviceInfoModel shareDeviceInfoModel = shareDeviceInfoModelMapper
                    .selectByPrimaryKey(shareChargerModel.getDeviceId());
            if (shareDeviceInfoModel == null) {
                result.setMessage("充电器未绑定设备或绑定的设备在系统中不存在，无法激活及绑定终端微信，请联系管理员绑定设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (shareDeviceInfoModel.getOnlineMerchantId() == null) {
                result.setMessage("充电器对应设备未绑定终端商户，请联系管理员绑定终端商户!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (shareDeviceInfoModel.getDeviceStatus() != null
                    && shareDeviceInfoModel.getDeviceStatus().intValue() != ShareDeviceStatusEnum.activation.getCode()
                    && shareDeviceInfoModel.getDeviceStatus()
                    .intValue() != ShareDeviceStatusEnum.waitForConfirmationStatus.getCode()) {
                result.setMessage("充电器对应设备未激活，无法激活及绑定终端微信，请联系管理员激活设备!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 商户信息
            MerchantInfoModel bindMerchantInfo = merchantInfoModelMapper
                    .selectByPrimaryKey(shareDeviceInfoModel.getOnlineMerchantId());
            if (bindMerchantInfo == null) {
                result.setMessage("充电器对应设备未绑定终端商户在系统中不存在，请联系管理员绑定重新终端商户!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3.3 判断是否绑定微信了..
            int isBindCustForMer = 0;// 0:未绑定商户，1：已绑定...
            List<CustMerchantRefInfoModel> listForCustMerchantRef = custMerchantRefInfoBySelfModelMapper
                    .selectByMerchantId(shareDeviceInfoModel.getOnlineMerchantId());
            if (listForCustMerchantRef.size() > 0) {
                isBindCustForMer = 1;
            }
            // 3.4 得到得到商户已激活，及未激活的设备..
            HashMap<String, Object> pageFilter = new HashMap<>();
            pageFilter.put("onlineMerchantId", shareDeviceInfoModel.getOnlineMerchantId());
            String limitSql = "LIMIT 300";
            pageFilter.put("limitSql", limitSql);
            String filter = String.format(" and device_status=%d", ShareDeviceStatusEnum.activation.getCode());
            pageFilter.put("filter", filter);
            List<ShareDeviceInfoModel> listForShareDeviceInfoActivation = shareDeviceInfoModelBySelfMapper
                    .getDevicesInfoByCondition(pageFilter);
            filter = String.format(" and device_status=%d", ShareDeviceStatusEnum.waitForConfirmationStatus.getCode());
            pageFilter.put("filter", filter);
            List<ShareDeviceInfoModel> listForShareDeviceInfoWaitConfirm = shareDeviceInfoModelBySelfMapper
                    .getDevicesInfoByCondition(pageFilter);

            // 4. 返回信息绑定的商户信息
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("isBindCustForMer", isBindCustForMer);// 商户是否绑定了微信
            resultMap.put("onlineMerchantId", shareDeviceInfoModel.getOnlineMerchantId());// 绑定的商户
            resultMap.put("bindMerchantInfo", bindMerchantInfo);// 绑定的商户信息
            resultMap.put("shareDeviceInfoModel", shareDeviceInfoModel);// 充电器对应的设备信息
            resultMap.put("devicesForActivation", listForShareDeviceInfoActivation);// 充电器对应已激活设备
            resultMap.put("devicesForWaitConfirm", listForShareDeviceInfoWaitConfirm);// 充电器对应待确认设备
            resultMap.put("chargerId", shareChargerModel.getId());// 充电器id
            result.setResponseInfo(resultMap);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("queryMerchantInfo 查询商户类型。。。", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("queryMerchantInfo", e);
            result.setMessage("查询绑定充电器信息失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 终端店铺激活绑定商户...
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/terminalBindForSave")
    @ResponseBody
    public Object terminalBindForSave(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // 1. 获到参数...
        String merchantName = request.getParameter("merchantName"); // 商户名称
        String merchantPersonName = request.getParameter("merchantPersonName"); // 商户联系人
        String merchantAddr = request.getParameter("merchantAddr"); // 商户地址
        String merchantCategoryCode = request.getParameter("merchantCategoryCode"); // 商户类型
        String merchantRoomCount = request.getParameter("merchantRoomCount"); // 房间数
        String onlineMerchantId = request.getParameter("onlineMerchantId"); // 终端商户id
        String longitude_x = request.getParameter("longitude_x"); // 扫码x
        String latitude_y = request.getParameter("latitude_y"); // 扫码y
        String chargerId = request.getParameter("chargerId"); // 充电器id
        String deviceId = request.getParameter("deviceId"); // 设备id
        String custNo = request.getParameter("custNo"); // 客户id
        String province = request.getParameter("province");// 省
        String city = request.getParameter("city");// 省
        String zone = request.getParameter("zone");// 省

        try {
            // 1. 判断参数是否正确...
            if (onlineMerchantId == null || onlineMerchantId.isEmpty() || onlineMerchantId.equals("0")) {
                result.setMessage("未传入绑定终端商户id!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (custNo == null || custNo == "") {
                result.setMessage("未传入客户id!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (merchantName == null || merchantName.isEmpty()) {
                result.setMessage("未传入商户名称!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (chargerId == null || chargerId.isEmpty()) {
                result.setMessage("未传入充电器!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (deviceId == null || deviceId.isEmpty()) {
                result.setMessage("未传入设备号!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请刷新重新进入公众号!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");
            if (!custNoForLogin.equals(custNo)) {
                result.setMessage("扫码的客户号与登录公众号的客户号不一至,请重新登录!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            Long iOnlineMerchantId = 0L;
            try {
                iOnlineMerchantId = Long.parseLong(onlineMerchantId);
            } catch (Exception e) {
                // TODO: handle exception
            }
            MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(iOnlineMerchantId);
            // 判断商户是否正确
            if (merchantInfoModel == null) {
                result.setMessage("传入的商户(" + onlineMerchantId + ")在系统中不存在!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.parseLong(custNo));
            if (custInfoModel == null) {
                result.setMessage("客户信息在系统中不存在，请刷新重新进入公众号!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3. 查询数据
            // 3.1 查询充电器
            ShareChargerModel shareChargerModel = shareChargerModelMapper.selectByPrimaryKey(Long.parseLong(chargerId));
            ShareDeviceInfoModel shareDeviceInfoModel = shareDeviceInfoModelMapper
                    .selectByPrimaryKey(Long.parseLong(deviceId));
            if (shareChargerModel == null) {
                result.setMessage("传入的充电器在系统中不存在，请传入正确的参数!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (shareDeviceInfoModel == null) {
                result.setMessage("传入的设备在系统中不存在，请传入正确的参数!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            logger.info("sharaeDeviceInfoModel.getId():{},shareChargerModel.getDeviceId():{}",
                    shareDeviceInfoModel.getId(), shareChargerModel.getDeviceId());
            if (!shareDeviceInfoModel.getId().equals(shareChargerModel.getDeviceId())) {
                result.setMessage("充电器绑定的设备和参入的设备不一至，请传入正确的参数!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (shareDeviceInfoModel.getOnlineMerchantId() == null
                    || !shareDeviceInfoModel.getOnlineMerchantId().equals(merchantInfoModel.getId())) {
                // 绑定商户...
                result.setMessage("设备绑定的商户号不一至，请传入正确的商户号!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 3.2 处理参数....
            MerchantInfoModel merchantInfoModelForUpdate = new MerchantInfoModel();
            merchantInfoModelForUpdate.setId(iOnlineMerchantId);
            if (merchantAddr != null && !merchantAddr.isEmpty()) {// 详细地址...
                merchantInfoModelForUpdate.setAddr(merchantAddr);
            }
            if (province != null && !province.isEmpty()) {// 省
                merchantInfoModelForUpdate.setProvince(province);
            }
            if (city != null && !city.isEmpty()) {// 市
                merchantInfoModelForUpdate.setCity(city);
            }
            if (zone != null && !zone.isEmpty()) {// 区
                merchantInfoModelForUpdate.setCity(zone);
            }
            if (merchantCategoryCode != null && !merchantCategoryCode.isEmpty()) {
                // 商户类型
                Integer industryCategory = new Integer(merchantCategoryCode);
                merchantInfoModelForUpdate.setIndustryCategoryCn(IndustryCategoryEnum.getDesc(industryCategory));
                merchantInfoModelForUpdate.setIndustryCategoryCode(industryCategory.byteValue());
            }
            logger.info(" 终端店铺激活绑定商户.terminalBindForSave. merchantInfoModelForUpdate:{}", merchantInfoModelForUpdate);
            ShareDeviceInfoModel shareDeviceInfoModelForUpdate = new ShareDeviceInfoModel();
            shareDeviceInfoModelForUpdate.setOnlineProvince(province);
            shareDeviceInfoModelForUpdate.setOnlineCity(city);
            shareDeviceInfoModelForUpdate.setOnlineZone(zone);
            shareDeviceInfoModelForUpdate.setOnlineAddress(merchantAddr);
            shareDeviceInfoModelForUpdate.setOnlineDatetime(new Date());
            shareDeviceInfoModelForUpdate.setDeviceStatus(ShareDeviceStatusEnum.activation.getCode());
            shareDeviceInfoModelForUpdate.setId(null);
            try {
                shareDeviceInfoModelForUpdate.setOnlineXCoordinate(new BigDecimal(longitude_x));
                shareDeviceInfoModelForUpdate.setOnlineYCoordinate(new BigDecimal(latitude_y));
            } catch (Exception e) {
                // TODO: handle exception
            }
            logger.info(" 终端店铺激活绑定商户.terminalBindForSave. shareDeviceInfoModel:{}", shareDeviceInfoModel);

            Long devNum = shareDeviceInfoModelBySelfMapper
                    .getInactiveDevicesCountByMerchantId(merchantInfoModelForUpdate.getId());

            logger.info(" 终端店铺激活绑定商户.terminalBindForSave. shareDeviceInfoModel:{},devNum:{}", shareDeviceInfoModel,
                    devNum);

            deviceInfoHelperService.batchActiveDeviceByMerchant(custInfoModel, merchantInfoModel,
                    shareDeviceInfoModelForUpdate);
            // 4. 返回信息绑定的商户信息
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("onlineMerchantId", shareDeviceInfoModel.getOnlineMerchantId());// 绑定的商户
            resultMap.put("shareDeviceInfoModel", shareDeviceInfoModel);// 充电器对应的设备信息
            resultMap.put("devNum", devNum);// 充电器id
            result.setResponseInfo(resultMap);
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("终端店铺激活绑定商户.terminalBindForSave.", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("终端店铺激活绑定商户.terminalBindForSave.", e);
            result.setMessage("激活设备失败！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    /**
     * 保存用户代理申请
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/applyAgentForSave")
    @ResponseBody
    public Object applyAgentForSave(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        // 1. 获到参数...
        String personName = request.getParameter("personName"); // 商户名称
        String merchantName = request.getParameter("merchantName");
        String telNo = request.getParameter("telNo"); // 商户联系人
        String agentsZone = request.getParameter("agentsZone"); // 代理区域
        String throwDevChannel = request.getParameter("throwDevChannel"); // 投方渠道
        String city = request.getParameter("city"); // 市
        String province = request.getParameter("province"); // 市
        String zone = request.getParameter("zone"); // 区
        String type = request.getParameter("type"); // 1:申请代理，2：合作

        try {

            if (personName == null || personName.isEmpty()) {
                result.setMessage("未传入商户名称!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            if (telNo == null || telNo.isEmpty()) {
                result.setMessage("请输入商户联系电话!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            // 2. 判断是否登录...
            // 2.1. 判断是否登录了..
            Map<String, String> mapUserInfo = getLoginCustInfo(request);
            if (mapUserInfo == null) {
                result.setMessage("您未登录，请刷新重新进入公众号!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }
            String unionId = mapUserInfo.get("unionId");
            String openIdForLogin = mapUserInfo.get("openId");
            String custNoForLogin = mapUserInfo.get("custNo");

            CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.parseLong(custNoForLogin));
            if (custInfoModel == null) {
                result.setMessage("客户信息在系统中不存在，请刷新重新进入公众号!");
                result.setResult(ResultCommandBaseObject.FAILED);
                return result;
            }

            // 3. 返回信息绑定的商户信息
            MerchantApplyFormTypeEnum typeEnum = MerchantApplyFormTypeEnum.APPLYAGENT;
            if (type.equals("2")) {
                typeEnum = MerchantApplyFormTypeEnum.APPLYCOOPERATE;
            }
            MerchantApplyFormModel applyFormModel = new MerchantApplyFormModel();
            applyFormModel.setAgentsZone(agentsZone);
            applyFormModel.setApplyType(new Long(typeEnum.getCode()));
            applyFormModel.setApplyTypeCn(typeEnum.getDesc());
            applyFormModel.setCreateTime(new Date());
            applyFormModel.setCustId(custInfoModel.getCustNo());
            applyFormModel.setDoStatus(new Long(MerchantApplyFormStatusEnum.noDo.getCode()));
            applyFormModel.setDoStatusCn(MerchantApplyFormStatusEnum.noDo.getDesc());
            applyFormModel.setId(seqService.getMerchantApplyFormSeq());
            applyFormModel.setPersonName(personName);
            applyFormModel.setTelNo(telNo);
            applyFormModel.setThrowDevChannel(throwDevChannel);
            applyFormModel.setUpdateTime(new Date());
            applyFormModel.setMerchantName(merchantName);
            applyFormModel.setCity(city);
            applyFormModel.setZone(zone);
            applyFormModel.setProvince(province);
            applyFormModel.setAgentsZone(agentsZone);
            // 4. 保存....
            merchantApplyFormModelMapper.insert(applyFormModel);
            result.setResponseInfo(applyFormModel.getId());
            result.setResult(ResultCommandBaseObject.SUCCESS);
            return result;
        } catch (ExgrainBizUncheckedException e) {
            logger.info("保存用户代理申请	.applyAgentForSave.", e);
            result.setMessage(e.getMessage());
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        } catch (Exception e) {
            logger.error("保存申请 applyAgentForSave.", e);
            result.setMessage("保存申请失败，请联系官理员！");
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
    }

    // 公众号端，点击快速添加商户功能，显示商户二维码
    // 1.顶级代理商能添加代理商，加盟商，铺货商，终端商户
    // 2.一二级代理商，能添加加盟商，铺货商，终端商户
    // 3.加盟商能添加终端商户
    // 4.铺货商能添加加盟商与终端商户
    @RequestMapping("/addmerchantInfoQRCode")
    public Object addmerchantInfoQRCode(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {
        // 0. 获到参数...
        String merchantId = request.getParameter("merchantId"); // 商户id
        String custNo = request.getParameter("custNo");
        String openId = request.getParameter("openId");
        logger.info("方法addmerchantInfoQRCode入参:merchantId:{},custNo:{},openId:{}", merchantId, custNo, openId);
        String urlForBusiness = "/wx/addmerchantInfoQRCode?flag=1";
        urlForBusiness = String.format("%s&custNo=%s&merchantId=%s&openId=%s", urlForBusiness, custNo, merchantId,
                openId);
        // 1. 进行微信授权.
        Object obj = wxMpOAuthHandle(request, urlForBusiness, "addmerchantInfoQRCode");
        if (obj.getClass() == ModelAndView.class) {
            // 跳转进行微信授权
            return obj;
        }
        ResultCommandBaseObject<Object> result = merchantInfoHelperService.addmerchantInfoQRCode(merchantId, rootUrl);
        logger.info("方法addmerchantInfoQRCode出参:resultMessage:{}", result.getMessage());
        model.addAttribute("responseInfo", result.getResponseInfo());
        return getPrefix() + "common/merchantQrCode.html";
    }

    // 用户通过扫商户二维码，进入注册页面
    // 1.根据商户opType(MerchantQrCodeTypeEnum)跳转对应的代理商申请页面，铺货商申请页面，加盟商申请页面，终端商户页面
    // 2.把商户信息、申请人客户号信息带到注册页面
    @RequestMapping("/addMerchantInfoUI")
    public Object addMerchantInfoUI(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {
        // 0. 获到参数...
        String merchantId = request.getParameter("merchantId"); // 商户id
        String opType = request.getParameter("opType");
        logger.info("方法addMerchantInfoUI入参:merchantId:{},opType:{}", merchantId, opType);
        // 1. 进行微信授权.
        String urlForBusiness = "/wx/addMerchantInfoUI?flag=1&merchantId=" + merchantId + "&opType=" + opType;
        Object obj = wxMpOAuthHandle(request, urlForBusiness, "addMerchantInfoUI");
        if (obj.getClass() == ModelAndView.class) {
            // 跳转进行微信授权
            return obj;
        }
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String openId = request.getParameter("openId");
        String url = this.shareRootUrl + "/wx/addMerchantInfoUI?flag=1&merchantId=" + merchantId + "&opType=" + opType;
        Boolean splitChar = false;
        if (openId != null) {
            if (splitChar) {
                url = url + "?openId=" + openId;
                splitChar = true;
            } else {
                url = url + "&openId=" + openId;
            }
        }
        if (code != null) {
            if (splitChar) {
                url = url + "?code=" + code;
                splitChar = true;
            } else {
                url = url + "&code=" + code;
            }
        }
        if (state != null) {
            if (splitChar) {
                url = url + "?state=" + state;
                splitChar = true;
            } else {
                url = url + "&state=" + state;
            }
        }
        try {
            WxJsapiSignature ws = wxMpService.createJsapiSignature(url);
            model.addAttribute("ws", ws);
        } catch (Exception e) {
            logger.info("微信公众号跳转。url:{}", e);
            // TODO: handle exception
            model.addAttribute("ws", new WxJsapiSignature());
        }
        // 2. 微信授权成功..得到对应的unionid和openid..
        Map<String, String> mapUserInfo = (Map<String, String>) obj;
        String unionId = mapUserInfo.get("unionId");
        openId = mapUserInfo.get("openId");
        // 得到客户信息
        CustInfoModel custInfoModel = (unionId == null || unionId.isEmpty()) ? registerService.getCustInfo(openId)
                : registerService.getCustInfoByUnionId(unionId);
        if (custInfoModel == null) {
            model.addAttribute("content", "您未关注公众号，请先关注公众号!");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        // 得到二维码所属商户信息
        MerchantInfoModel merchantInfo = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
        // 3.根据opType，分别跳转不同的注册页面
        String pageUrl = "";
        String html = "registerByQrCode.html";
        if (MerchantQrCodeTypeEnum.ADD_DAI_LI_SHANG2.getCode().intValue() == Integer.parseInt(opType)
                || MerchantQrCodeTypeEnum.ADD_DAI_LI_SHANG3.getCode().intValue() == Integer.parseInt(opType)) {
            // 代理商申请页面
            pageUrl =getPrefix()+ WxAppAgentController.PREFIXBASE + html;
        }
        if (MerchantQrCodeTypeEnum.ADD_PUHUO_SHANG.getCode().intValue() == Integer.parseInt(opType)) {
            // 铺货商申请页面
            pageUrl =getPrefix()+ WxAppShopkeeperController.PREFIXBASE + html;
        }
        if (MerchantQrCodeTypeEnum.ADD_JIA_MENG_SHANG.getCode().intValue() == Integer.parseInt(opType)) {
            // 加盟商申请页面
            pageUrl =getPrefix()+ WxAppAllianceBusinessController.PREFIXBASE + html;
        }
        if (MerchantQrCodeTypeEnum.ADD_TERMINAL.getCode().intValue() == Integer.parseInt(opType)) {
            // 终端商申请页面
            pageUrl =getPrefix()+ WxAppTerminalController.PREFIXBASE + html;
        }
        // 4 注册页面需要的信息.
        String industryCategoryCodeFrHtmlOptions = getIndustryCategoryCodeForHtmlOptions(0);
        model.addAttribute("industryCategoryCodeFrHtmlOptions", industryCategoryCodeFrHtmlOptions);
        model.addAttribute("reqUrlRoot", rootUrl);
        model.addAttribute("custInfoModel", custInfoModel);
        model.addAttribute("custNO", custInfoModel.getCustNo());
        model.addAttribute("unionId", unionId);
        model.addAttribute("openId", openId);
        model.addAttribute("opType", opType);
        model.addAttribute("merchantInfoModel", merchantInfo);
        initProvinceOptions(model);

        logger.info("方法addMerchantInfoUI出参:pageUrl:{},custInfoModel:{},merchantInfo:{}", pageUrl, custInfoModel,
                merchantInfo);
        return pageUrl;
    }

    // 提交商户注册申请
    // 1.往商户表插入一条记录，parent_merchant_id更新为当前二维码的商户
    // 2.把扫码用户custNo绑定到新建商户
    // 3.返回注册成功页面
    @RequestMapping("/submitMerchantInfo")
    @ResponseBody
    public Object submitMerchantInfo(HttpServletRequest request, HttpServletResponse response, Model model,
                                     RegisterTerminalByCodeBO registerTerminalByCodeBO) throws Exception {
        logger.info("方法submitMerchantInfo入参:RegisterTerminalByCodeBO:{}", registerTerminalByCodeBO);
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        result.setResult(ResultCommandBaseObject.SUCCESS);
        // 防重复提交
        String registerFlag = (String) request.getSession().getAttribute("registerFlag");
        if ("1".equals(registerFlag)) {
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("您的注册信息已提交，请不要重复提交!");
            return result;
        }

        result = merchantInfoService.registerMerchant(registerTerminalByCodeBO);
        logger.info("方法submitMerchantInfo出参:result:{}", result);
        return result;
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
     * 得到省市区
     */
    @RequestMapping(value = "/getProvinceCityZondeOptions")
    @ResponseBody
    public Object getProvinceCityZondeOptions(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<ProvinceCityZoneInfoModel> result = new ResultCommandBaseObject<>();
        String msg = "";
        String parentId = request.getParameter("parentId");
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

    // 跳转到交易信息页面
    @RequestMapping("/redirectTradePage")
    public Object redirectTradePage(HttpServletRequest request, HttpServletResponse response, Model model) {
        String deviceId = request.getParameter("deviceId");
        String merchantId = request.getParameter("merchantId");
        String custNo = request.getParameter("custNo");
        String isParent = request.getParameter("isParent");
        logger.info("方法redirectTradePage入参deviceId:{},merchantId:{},custNo:{}", deviceId, merchantId, custNo);
        if (StringUtils.isEmpty(deviceId)) {
            model.addAttribute("content", "设备信息获取失败！请刷新后重试！");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        if (StringUtils.isEmpty(merchantId)) {
            model.addAttribute("content", "商户信息获取失败！请刷新后重试！");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        if (StringUtils.isEmpty(custNo)) {
            model.addAttribute("content", "用户信息获取失败！请重新登陆！");
            model.addAttribute("title", "error");
            model.addAttribute("icon", "error");
            return getPrefix() + "error.html";
        }
        MerchantInfoModel merchantInfo = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));
        CustInfoModel custInfoModel = custInfoModelMapper.selectByPrimaryKey(Long.valueOf(custNo));
        model.addAttribute("merchantInfo", merchantInfo);
        model.addAttribute("custInfoModel", custInfoModel);
        model.addAttribute("reqUrlRoot", rootUrl);
        model.addAttribute("deviceId", deviceId);
        model.addAttribute("isParent", isParent);
        return getPrefix() + "common/merchantOrdersInfoQuery.html";
    }

    // 使用趋势图表
    @RequestMapping("/usageTrendUI")
    public Object usageTrendUI(HttpServletRequest request, HttpServletResponse response, Model model) {
        String deviceIdStr = request.getParameter("deviceId");
        logger.info("方法usageTrendUI入参：deviceId:{}", deviceIdStr);
        model.addAttribute("deviceId", deviceIdStr);
        model.addAttribute("reqUrlRoot", rootUrl);
        return getPrefix() + "common/deviceUsageTrend.html";
    }

    // 使用趋势图表
    @RequestMapping("/usageTrendData")
    @ResponseBody
    public Object usageTrendData(HttpServletRequest request, HttpServletResponse response) {
        ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
        String deviceIdStr = request.getParameter("deviceId");
        if (StringUtils.isEmpty(deviceIdStr)) {
            result.setResult(ResultCommandBaseObject.FAILED);
            return result;
        }
        Map<String, Object> resultMap = shareDeviceService.getUsageTrendData(deviceIdStr);
        result.setResponseInfo(resultMap);
        result.setResult(ResultCommandBaseObject.SUCCESS);
        return result;
    }

    @RequestMapping("/test")
    public Object test(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        // 得到二维码所属商户信息
        MerchantInfoModel merchantInfo = merchantInfoModelMapper.selectByPrimaryKey(11000100086L);

        String industryCategoryCodeFrHtmlOptions = getIndustryCategoryCodeForHtmlOptions(0);
        model.addAttribute("merchantInfoModel", merchantInfo);
        model.addAttribute("industryCategoryCodeFrHtmlOptions", industryCategoryCodeFrHtmlOptions);
        model.addAttribute("reqUrlRoot", rootUrl);
        CustInfoModel custInfoModel = registerService.getCustInfoByUnionId("oJ-WN5i5sC6NxTesuI17RpCqui3w");
        model.addAttribute("custInfoModel", custInfoModel);
        model.addAttribute("custNO", custInfoModel.getCustNo());
        model.addAttribute("unionId", "oJ-WN5i5sC6NxTesuI17RpCqui3w");
        model.addAttribute("openId", "o9xre0X1wuJ1ApNBr96XtVHeKDrw");
        model.addAttribute("opType", "4");
        initProvinceOptions(model);
        return getPrefix()+ WxAppAllianceBusinessController.PREFIXBASE + "registerByQrCode.html";
    }

}
