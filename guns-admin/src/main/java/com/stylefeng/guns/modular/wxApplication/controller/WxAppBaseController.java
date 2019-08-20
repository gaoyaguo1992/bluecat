package com.stylefeng.guns.modular.wxApplication.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustAccountBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountSourceEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.IndustryCategoryEnum;
import com.stylefeng.guns.sharecore.common.service.RegisterService;
import com.stylefeng.guns.sharecore.modular.handler.WxCustServiceHandler;
import com.stylefeng.guns.sharecore.modular.handler.WxMpSubcribeHandler;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoBySelfModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class WxAppBaseController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(WxAppBaseController.class);

	/**
	 * 提现对应的模板信息。。
	 */
	@Value("${sharehelper.url}")
	protected String shareRootUrl;
	/**
     * 平台命名空间
     */
    @Value("${sharehelper.platformSpace}")
    public String platformSpace="/wx/";
    
    /**
     * 对应前端主业。。
     */
    private  String prefix = "/wx/";
    public String getPrefix() {
    	if(platformSpace==null||platformSpace.isEmpty()){
    		return prefix;
    	}else{
    		return platformSpace;
    	}
	}
    /**
     * 显示设备数量..
     */
    @Value("${sharehelper.isShowDeviceNumForTerminal}")
    private String isShowDeviceNumForTerminal="true";

	/**
	 * 数据库操作
	 */
	@Autowired
	@Qualifier("common.SequenceService")
	protected SequenceService seqService;
	/**
	 * 微信公众号接口
	 */
	@Autowired
	protected WxMpService wxMpService;
	/**
	 * 微信公众号消息处理..
	 */
	@Autowired
	protected WxMpMessageRouter router;
	/**
	 * 处理消息关注
	 */
	@Autowired
	protected WxMpSubcribeHandler wxMpSubcribeHandler;
	/**
	 * 处理微信客户信息...
	 */
	@Autowired
	protected WxCustServiceHandler wxCustServiceHandler;
	/**
	 * 处理微信上
	 */
	@Autowired
	protected WxMpConfigStorage wxMpConfigStorage;
	/**
	 * 注册客户..
	 */
	@Autowired
	protected RegisterService registerService;
	/**
	 * 得到商户信息...
	 */
	@Autowired
	protected CustMerchantRefInfoBySelfModelMapper custMerchantRefInfoBySelfModelMapper;
	/**
	 * 客户商户关联表...
	 */
	@Autowired
	protected CustMerchantRefInfoModelMapper custMerchantRefInfoModelMapper;
	/**
	 * 客户信息。。。
	 */
	@Autowired
	protected CustInfoModelMapper custInfoModelMapper;
	/**
	 * 
	 */
	@Autowired
	private CustInfoBySelfMapper custInfoBySelfMapper;
	/**
	 * 账户....
	 */
	@Autowired
	private CustAccountBySelfMapper custAccountBySelfMapper;
	/**
	 * 充电器处理...
	 */
	@Autowired
	private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;

	/**
	 * 得到登录的客户信息... 如果登录了，返回map map["unionId"],map["openId"],map["custNo"]
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, String> getLoginCustInfo(HttpServletRequest request) {
		/*Map<String,String> mp=new HashMap<>();
		mp.put("unionId", "ofwbx0_zLyak0w3kiUsehLcgRD3g");
		mp.put("openId", "ofwbx0_zLyak0w3kiUsehLcgRD3g");
		mp.put("custNo", "1000100084");
		return mp;*/
		Object objSession = request.getSession().getAttribute("wxUserInfo");
		if (objSession != null && objSession.getClass() == HashMap.class) {
			// 已经登录过了，保存了session...
			return (Map<String, String>) objSession;
		} else {
			return null;
		}
	}

	/**
	 * 微信权限认证...如果访问的链接没有通过微信认证，选微信认证
	 * 
	 * @param request
	 * @param redirectUrl
	 * @param entryName
	 * @return
	 * @throws Exception
	 */
	public Object wxMpOAuthHandle(HttpServletRequest request, String redirectUrl, String entryName) throws Exception {
		// 因为现在公众号还没有认证..
		Map<String, String> result = new HashMap<String, String>();
		// 0. 先判断是否session中否保存了相关数据.如果有直接从session中得到..
		Object objSession = request.getSession().getAttribute("wxUserInfo");
		if (objSession != null && objSession.getClass() == HashMap.class) {
			// 已经登录过了，保存了session...
			return objSession;
		}
		/*
		if (true) {
			result.put("unionId", "oJ-WN5i5sC6NxTesuI17RpCqui3w");
			result.put("openId", "o9xre0X1wuJ1ApNBr96XtVHeKDrw");
			result.put("custNo", "1000100109");
			request.getSession().setAttribute("wxUserInfo", result);
			return result;
		}
		*/
		// 1. 获到参数
		String openId = request.getParameter("openId");
		String unionId = request.getParameter("unionId");
		String flag = request.getParameter("flag");
		String code = request.getParameter("code");
		logger.info("微信权限认证...如果访问的链接没有通过微信认证，选微信认证{}, wxMpOAuthHandle openid:{},unionId:{},Flag:{},code:{}", entryName,
				openId, unionId, flag, code);
		WxMpUser wxMpUser = null;
		try {
			// 2. 没有code或者code为空，说明没有通过微信网络授权...进行网页授权.
			if (code == null || code.isEmpty()) {
				String oauthUrl = wxMpService.oauth2buildAuthorizationUrl(shareRootUrl + redirectUrl,
						WxConsts.OAUTH2_SCOPE_USER_INFO, null);
				return new ModelAndView("redirect:" + oauthUrl);
			}
			// 3. 根据code 得到用户unionId等信息
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			openId = wxMpOAuth2AccessToken.getOpenId();
			if (StringUtils.isEmpty(flag)) {
				wxMpUser = wxMpService.userInfo(openId, "");
				unionId = wxMpUser.getUnionId();
			} else {
				wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "");
				unionId = wxMpUser.getUnionId();
			}
			// 3.1 没有获数据..且是第一次，重新组装授权,
			if (StringUtils.isEmpty(flag) && StringUtils.isEmpty(unionId)) {
				if (redirectUrl.indexOf("?flag=") < 0 && redirectUrl.indexOf("&flag=") < 0) {
					redirectUrl = (redirectUrl.indexOf("?") < 0) ? redirectUrl + "?flag=1" : redirectUrl + "&flag=1";
				}
				String oauthUrl = wxMpService.oauth2buildAuthorizationUrl(shareRootUrl + redirectUrl,
						WxConsts.OAUTH2_SCOPE_USER_INFO, null);
				return new ModelAndView("redirect:" + oauthUrl);
			}

		} catch (WxErrorException e) {
			logger.info(entryName + "code been used:{}{}", e.getError().getErrorCode());
			if (40163 == e.getError().getErrorCode() || 40029 == e.getError().getErrorCode()) {
				// 把标字位也组装进去.
				if (redirectUrl.indexOf("?flag=") < 0 && redirectUrl.indexOf("&flag=") < 0) {
					redirectUrl = (redirectUrl.indexOf("?") < 0) ? redirectUrl + "?flag=1" : redirectUrl + "&flag=1";
				}
				String merchantJumpUrl = wxMpService.oauth2buildAuthorizationUrl(shareRootUrl + redirectUrl,
						WxConsts.OAUTH2_SCOPE_USER_INFO, null);
				return new ModelAndView("redirect:" + merchantJumpUrl);
			}
		}
		// 4. 创建用户..
		CustInfoModel cim = null;
		if (StringUtils.isNotEmpty(unionId)) {
			cim = registerService.getCustInfoByUnionId(unionId);
			if (cim != null) {
				if (StringUtils.isEmpty(cim.getOpenId())) {
					cim.setOpenId(openId);
					if (wxMpUser != null) {
						cim.setNickName(wxMpUser.getNickname());
						cim.setHeadImgUrl(wxMpUser.getHeadImgUrl());
						cim.setUpdateTime(new Date());
					}
					registerService.updateCustInfo(cim);
					logger.info("更新custInfoModel:{}openidIs-->{},unionId-->{}", openId, unionId);
				}
			} else {
				// 判断openId是否有存在的...
				cim = registerService.getCustInfo(openId);
				if (cim != null) {
					cim.setUnionId(unionId);
					if (wxMpUser != null) {
						cim.setNickName(wxMpUser.getNickname());
						cim.setHeadImgUrl(wxMpUser.getHeadImgUrl());
						cim.setUpdateTime(new Date());
					}
					registerService.updateCustInfo(cim);
					logger.info("更新custInfoModel:{}openidIs-->{},unionId-->{}", openId, unionId);
				} else {
					if (wxMpUser != null) {
						cim = registerService.registeCust(wxMpUser);
						logger.info("创建openId:{}", wxMpUser.getOpenId());
					}
				}
			}
		} else {
			cim = registerService.getCustInfo(openId);
			if (cim != null) {
				cim.setUnionId(unionId);
				if (wxMpUser != null) {
					cim.setNickName(wxMpUser.getNickname());
					cim.setHeadImgUrl(wxMpUser.getHeadImgUrl());
					cim.setUpdateTime(new Date());
				}
				registerService.updateCustInfo(cim);
				logger.info("更新custInfoModel:{}openidIs-->{},unionId-->{}", openId, unionId);
			} else {
				if (wxMpUser != null) {
					cim = registerService.registeCust(wxMpUser);
					logger.info("创建openId:{}", wxMpUser.getOpenId());
				}
			}
		}
		// 5. 返回授权后unionId, openId
		// Map<String, String> result = new HashMap<String, String>();
		result.put("unionId", unionId);
		result.put("openId", openId);
		result.put("custNo", cim != null ? cim.getCustNo().toString() : "0");
		// 保存session...
		try {
			logger.info(String.format("微信权限认证...如果访问的链接没有通过微信认证，选微信认证-wxMpOAuthHandlew 保存session-unionId:{},openId:{}",
					unionId, openId));
			request.getSession().setAttribute("wxUserInfo", result);
		} catch (Exception e) {
			logger.error(String.format("微信权限认证...如果访问的链接没有通过微信认证，选微信认证-wxMpOAuthHandlew 保存session-unionId:%s,openId:%s",
					unionId, openId), e);
		}
		return result;

	}

	/**
	 * 得到行业类型的html options...
	 * 
	 * @param industryCategoryCod
	 * @return
	 */
	protected String getIndustryCategoryCodeForHtmlOptions(int industryCategoryCod) {
		String htmlOptions = "";
		List<IndustryCategoryEnum> list = IndustryCategoryEnum.getIndustryCategoryEnumForList();
		for (IndustryCategoryEnum industryCategoryEnum : list) {
			if (industryCategoryEnum.getCode() == industryCategoryCod) {
				htmlOptions = String.format("%s<option value=%d selected=\"selected\">%s</option>", htmlOptions,
						industryCategoryEnum.getCode(), industryCategoryEnum.getDesc());
			} else {
				htmlOptions = String.format("%s<option value=%d>%s</option>", htmlOptions,
						industryCategoryEnum.getCode(), industryCategoryEnum.getDesc());
			}
		}
		return htmlOptions;
	}

	/**
	 * 处理跳转到商户首面的相关业务逻辑。。
	 * 
	 * @param model
	 * @param custInfoModel
	 * @param custMerRefForList
	 * @param merchantInfo
	 * @return
	 */
	protected Object jumpToMerchantMainPage(Model model, CustInfoModel custInfoModel,
			List<CustMerchantRefInfoModel> custMerRefForList, MerchantInfoModel merchantInfo) {
		// 1 初始化商户参数...
		model.addAttribute("custInfoModel", custInfoModel);
		model.addAttribute("custNO", custInfoModel.getCustNo());
		model.addAttribute("unionId", custInfoModel.getUnionId());
		model.addAttribute("openId", custInfoModel.getOpenId());
		model.addAttribute("custMerRefList", custMerRefForList);
		model.addAttribute("merchantInfo", merchantInfo);
		model.addAttribute("merchantId", merchantInfo.getId());
		model.addAttribute("selectedMerchantType", merchantInfo.getMerchantType());
		
		HashMap<String, Object> pageFilter = new HashMap<>();
		//显示设备数量..
		String isShowDevicesNum="true";
		if (merchantInfo != null && merchantInfo.getMerchantType() != null) {
			if (merchantInfo.getMerchantType().toString()
					.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG1.getCode()))) {
				// 代理商顶级
				pageFilter.put("agents1Id", merchantInfo.getId());
			} else if (merchantInfo.getMerchantType().toString()
					.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG2.getCode()))) {
				// 代理商1
				pageFilter.put("agents2Id", merchantInfo.getId());
			} else if (merchantInfo.getMerchantType().toString()
					.equals(String.valueOf(MerchantTypeEnum.DAI_LI_SHANG3.getCode()))) {
				// 代理商2
				pageFilter.put("agents3Id", merchantInfo.getId());
			} else if (merchantInfo.getMerchantType().toString()
					.equals(String.valueOf(MerchantTypeEnum.PUHUO_SHANG.getCode()))) {
				// 铺货商。。。
				pageFilter.put("shopkeeperId", merchantInfo.getId());
			} else if (merchantInfo.getMerchantType().toString()
					.equals(String.valueOf(MerchantTypeEnum.JIA_MENG_SHANG.getCode()))) {
				// 加盟商
				pageFilter.put("allianceBusinessId", merchantInfo.getId());
			} else if (merchantInfo.getMerchantType().toString()
					.equals(String.valueOf(MerchantTypeEnum.TERMINAL.getCode()))) {
				// 终端...
				pageFilter.put("onlineMerchantId", merchantInfo.getId());
				if(isShowDeviceNumForTerminal.equalsIgnoreCase("false")){
					//显示设备数量..
					isShowDevicesNum="true";
				}
			}
		}
		logger.info("处理跳转到商户首面的相关业务逻辑--jumpToMerchantMainPage pageFilter:{}", pageFilter);
		Long devNum = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
		//七天
		pageFilter.put("days", 7);
		Long noUseChargerNum = shareDeviceInfoModelBySelfMapper.countBeyondDaysNotUseCharger(pageFilter);
		//是否显示设备数量...
		model.addAttribute("isShowDevicesNum", isShowDevicesNum);
		model.addAttribute("devNum", devNum);
		model.addAttribute("beyondThreeDaysNotUseChargersNum", 0);
		model.addAttribute("beyondSevenDaysNotUseChargersNum", noUseChargerNum);
		model.addAttribute("yesstodayTradeNum", 0);
		logger.info("处理跳转到商户首面的相关业务逻辑--jumpToMerchantMainPage model:{}", model);

		// 2 计算商户余额...
		BigDecimal availableBalance = new BigDecimal("0");
		if (!MerchantTypeEnum.TERMINAL.getCode().equals(merchantInfo.getMerchantType())) {
			// getMerchantCustInfoByMerchantId
			CustInfoModel custInfo = custInfoBySelfMapper.getMerchantCustInfoByMerchantId(merchantInfo.getId());
			CustAccountModel custAccountModel = new CustAccountModel();
			if (custInfo == null) {
				custAccountModel = new CustAccountModel();
			} else {
				custAccountModel = custAccountBySelfMapper.selectAccountByCustNo(custInfo.getCustNo(),
						CustAccountTypeEnum.RECHARGERACCOUNT.getCode(), CustAccountSourceEnum.WEIXIN.getCode());
				if (custAccountModel == null) {
					custAccountModel = new CustAccountModel();
				}
			}
			availableBalance = custAccountModel.getAvailableBalance();
			model.addAttribute("custAccountModel", custAccountModel);
		}
		model.addAttribute("availableBalance", availableBalance);
		// 3 进行跳转
		if (merchantInfo.getMerchantType() == null) {
			return getPrefix()+ WxAppTerminalController.PREFIXBASE + "index.html";
		}
		if (MerchantTypeEnum.DAI_LI_SHANG1.getCode().equals(merchantInfo.getMerchantType().intValue())) {
			// 顶级代理商..
			return getPrefix()+ WxAppAgentController.PREFIXBASE + "index.html";
		} else if (MerchantTypeEnum.DAI_LI_SHANG2.getCode().equals(merchantInfo.getMerchantType().intValue())
				|| MerchantTypeEnum.DAI_LI_SHANG3.getCode().equals(merchantInfo.getMerchantType().intValue())
				|| MerchantTypeEnum.PUHUO_SHANG.getCode().equals(merchantInfo.getMerchantType().intValue())) {
			model.addAttribute("beyondThreeDaysNotUseChargersNum", 0);
			// 一级，二级，铺货商
			return getPrefix()+WxAppShopkeeperController.PREFIXBASE + "index.html";
		} else if (MerchantTypeEnum.JIA_MENG_SHANG.getCode().equals(merchantInfo.getMerchantType().intValue())) {
			// 加盟商..
			return getPrefix()+WxAppAllianceBusinessController.PREFIXBASE + "index.html";
		} else {
			// 终端店铺...
			return getPrefix()+WxAppTerminalController.PREFIXBASE + "index.html";
		}
	}
}
