package com.stylefeng.guns.rest.modular.auth.controller.dto;

import java.io.Serializable;

/**
 * 微信小程登录
 * @author Alan.huang
 *
 */
public class WxAppLoginResponse implements Serializable {

    private static final long serialVersionUID = 1450166508152480001L;

    /**
     * jwt token
     */
    private final String token;

    /**
     * 用于客户端混淆md5加密
     */
    private final String randomKey;    
    /**
     * 用户名
     */
    private final String userName;
    /**
     * 电话
     */
    private final String telNo;

    public WxAppLoginResponse(String token, String randomKey,String userName,String telNo) {
        this.token = token;
        this.randomKey = randomKey;
        this.userName=userName;
        this.telNo=telNo;
    }    

    public String getUserName() {
		return userName;
	}

	public String getTelNo() {
		return telNo;
	}

	public String getToken() {
        return this.token;
    }

    public String getRandomKey() {
        return randomKey;
    }
}
