package com.stylefeng.guns.sharecore.common.persistence.model;

/**
 * 微信小程序，基础结果类
 * <P>
 * TODO
 * </P>
 * 
 */
public class WxAppBaseResult implements java.io.Serializable {
	private static final long serialVersionUID = 634567686053891L;

	private String errcode; // 返回码

	private String errmsg; // 返回信息

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
