package com.stylefeng.guns.modular.wxApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.service.RegisterService;
import com.stylefeng.guns.sharecore.modular.handler.WxCustServiceHandler;
import com.stylefeng.guns.sharecore.modular.handler.WxMpSubcribeHandler;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoBySelfModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 处理微信公众号相关业务控制器 --加盟商 
 *
 * @author fengshuonan
 * @Date 2019-01-20 02:37:34
 */
@Controller
@RequestMapping("/wxAllianceBusiness")
public class WxAppAllianceBusinessController extends BaseController {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(WxAppAllianceBusinessController.class);
	/**
	 * 对应前端主业。。
	 */
	public static String PREFIXBASE = "wxAllianceBusiness/";
	
}
