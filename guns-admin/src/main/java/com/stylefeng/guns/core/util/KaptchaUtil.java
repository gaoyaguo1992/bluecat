package com.stylefeng.guns.core.util;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.config.properties.WechatAccountConfig;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(GunsProperties.class).getKaptchaOpen();
    }
    /**
     * 系统名字
     * @return
     */
    public static String getSystemName(){
    	return SpringContextHolder.getBean(WechatAccountConfig.class).getSystemName();
    }
    /**
     * log的文件..
     * @return
     */
    public static String getLogImgFileName(){
    	return SpringContextHolder.getBean(WechatAccountConfig.class).getLogImgFileName();
    }
}