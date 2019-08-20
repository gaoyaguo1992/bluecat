
package com.stylefeng.guns.modular.wxApplication.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.modular.wxApplication.model.WxMenuConstant;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 
 * <P>
 * 初始化微信公众号菜单服务类
 * </P>
 */
@Service
public class InitWxMenuService {
	/**
	 * 写日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(InitWxMenuService.class);
	/**
	 * 
	 */
	@Autowired
	protected WxMpService wxMpService;

	@Value("${sharehelper.url}")
	private String wexinwebUrl = "";
	// 初始他菜单
	@Value("${wechat.InitMenu}")
	private String InitMenu = "true";

	// 小程序路径
	@Value("${sharehelper.wxapp.url}")
	private String wxappUrl = "";

	// 小程序appId
	@Value("${sharehelper.wxapp.appid}")
	private String wxappId = "";

	// 小程序index
	@Value("${sharehelper.wxapp.index}")
	private String wxappIndex = "";

	// 小程序index
	@Value("${sharehelper.wxapp.personalCenter}")
	private String wxappPersonalCenter = "";

	@PostConstruct
	public void init() {
		logger.info("创建菜单 ...ini InitWxMenuService menu..");
		try {
			if (InitMenu != null && InitMenu.equals("true")) {
				// 删除掉已有的菜单
				wxMpService.menuDelete();
				// 商家中心
				WxMenuButton button1 = new WxMenuButton();
				button1.setType(WxMenuConstant.VIEW);
				button1.setName(WxMenuConstant.MERCHANT_CENTER);
				// 商家中心
				WxMenuButton button11 = new WxMenuButton();
				button11.setType(WxMenuConstant.VIEW);
				button11.setName(WxMenuConstant.MERCHANT_LOGIN);
				//商家登录跳转链接
				String defendApplyUrl = wxMpService.oauth2buildAuthorizationUrl(
						wexinwebUrl + "/wx/registerApplyJump",WxConsts.OAUTH2_SCOPE_BASE, null);

				logger.info("创建菜单 ...ini InitWxMenuService menu.defendApplyUrl.{}", defendApplyUrl);
				button11.setUrl(defendApplyUrl);
				button1.getSubButtons().add(button11);
				// 设备绑定
				WxMenuButton button12 = new WxMenuButton();
				button12.setType(WxMenuConstant.VIEW);
				button12.setName(WxMenuConstant.DEVICE_BINDING);
				// 设备绑定跳转链接
				String deviceBindingUrl = wxMpService.oauth2buildAuthorizationUrl(
						wexinwebUrl + "/wx/terminalBindDevices",WxConsts.OAUTH2_SCOPE_BASE, null);
				button12.setUrl(deviceBindingUrl);
				button1.getSubButtons().add(button12);

				// 扫码充电..
				WxMenuButton button2 = null;
				try {
					button2 = new WxMenuButton();
					button2.setType(WxMenuConstant.VIEW);
					button2.setName(WxMenuConstant.QR_RECHARGER);
					//button2.setUrl(wexinwebUrl+"/wx/qrRecharger");
					button2.setType("miniprogram");
					button2.setName("扫码充电");
					button2.setUrl(wxappUrl);
					button2.setAppid(wxappId);
					button2.setPagepath(wxappIndex);
				} catch (Exception e) {
					// TODO: handle exception
					button2 = null;
					logger.info("创建菜单 --扫码充电菜单失败 ", e);
				}
				/*	
				*/
				// 个人中心
				WxMenuButton button3 = new WxMenuButton();
				button3.setType(WxMenuConstant.VIEW);
				button3.setName(WxMenuConstant.MORE);
				
				/*WxMenuButton button30 = new WxMenuButton();
				try {
					button30 = new WxMenuButton();
					button30.setType("miniprogram");
					button30.setName("余额提现");
					button30.setUrl(wxappUrl);
					button30.setAppid(wxappId);
					button30.setPagepath(wxappPersonalCenter);
				} catch (Exception e) {
					// TODO: handle exception
					button30 = null;
					logger.info("创建菜单 --个人中心菜单失败 ", e);
				}*/
				// 商家合作
				WxMenuButton button31 = new WxMenuButton();
				button31.setType(WxMenuConstant.VIEW);
				button31.setName(WxMenuConstant.MERCHANT_COOPERATION);
				// 商家合作跳转链接
				String applyMerchantFormUrl = wxMpService.oauth2buildAuthorizationUrl(
						wexinwebUrl + "/wx/applyMerchantForm?type=2",WxConsts.OAUTH2_SCOPE_BASE, null);
				button31.setUrl(applyMerchantFormUrl);
				
				// 申请代理
				WxMenuButton button32 = new WxMenuButton();
				button32.setType(WxMenuConstant.VIEW);
				button32.setName(WxMenuConstant.AGENTS_APPLY);
				// 申请代理跳转链接
				applyMerchantFormUrl = wxMpService.oauth2buildAuthorizationUrl(
						wexinwebUrl + "/wx/applyMerchantForm?type=1",WxConsts.OAUTH2_SCOPE_BASE, null);
				button32.setUrl(applyMerchantFormUrl);
				/*//供应商合作
				WxMenuButton button33 = new WxMenuButton();
				button33.setType(WxMenuConstant.VIEW);
				button33.setName(WxMenuConstant.AGENTS_APPLY);
				// 申请代理跳转链接
				applyMerchantFormUrl = wxMpService.oauth2buildAuthorizationUrl(
						wexinwebUrl + "/wx/applyMerchantForm?type=3",WxConsts.OAUTH2_SCOPE_BASE, null);
				button33.setUrl(applyMerchantFormUrl);*/
				// 扫码充电..
				WxMenuButton button33 = null;
				try {
					button33 = new WxMenuButton();
					button33.setType(WxMenuConstant.VIEW);
					// button2.setUrl(wexinwebUrl+"/wx/qrRecharger");
					button33.setType("miniprogram");
					button33.setName("我的账户");
					button33.setUrl(wxappUrl);
					button33.setAppid(wxappId);
					button33.setPagepath(wxappPersonalCenter);
				} catch (Exception e) {
					// TODO: handle exception
					button2 = null;
					logger.info("创建菜单 --扫码充电菜单失败 ", e);
				}
				
				button3.getSubButtons().add(button31);
				button3.getSubButtons().add(button32);
				button3.getSubButtons().add(button33);
				/*if(button30!=null){
					button3.getSubButtons().add(button30);					
				}*/
				
				WxMenu menu = new WxMenu();
				menu.getButtons().add(button1);
				if (button2 != null) {
					menu.getButtons().add(button2);
				}
				if (button3 != null) {
					menu.getButtons().add(button3);
				}
				wxMpService.menuCreate(menu);
				logger.info("初始化菜单成功");
			}else{
				logger.info("无需生成菜单成功");
			}
		} catch (Exception e) {
			logger.info("初始化菜单出错", e);
		}
	}

}
