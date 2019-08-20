package com.stylefeng.guns.core.base.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全局的控制器
 *
 * @author fengshuonan
 * @date 2016年11月13日 下午11:04:45
 */
@Controller
@RequestMapping("/global")
public class GlobalController {

    /**
     * 系统名称
     */
	@Value("${wechat.systemName}")
	private String systemName="GS";
    /**
     * log
     */
	@Value("${wechat.logImgFileName}")
	private String logImgFileName="xbs.png";
	
    /**
     * 跳转到404页面
     *
     * @author fengshuonan
     */
    @RequestMapping(path = "/error")
    public String errorPage() {
        return "/404.html";
    }

    /**
     * 跳转到session超时页面
     *
     * @author fengshuonan
     */
    @RequestMapping(path = "/sessionError")
    public String errorPageInfo(Model model) {
        model.addAttribute("tips", "session超时");
        model.addAttribute("systemName",systemName);
        model.addAttribute("logImgFileName", logImgFileName);
        return "/login.html";
    }
}