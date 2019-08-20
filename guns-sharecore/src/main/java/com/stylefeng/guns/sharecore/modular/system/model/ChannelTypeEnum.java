package com.stylefeng.guns.sharecore.modular.system.model;

public enum ChannelTypeEnum {
	WEIXIN_APP_CHARGE(11, "微信小程序充值"),
	WEIXIN_GZH_CHARGE(13, "微信公众号充值"),	
	ZFB_SHH_CHARGE(12, "支付宝生活号充值"),
	ZFB_APP_CHARGE(17, "支付宝小程序充值"),
	WEIXIN_APP_WITHDRAW(14, "微信小程序提现"),
	WEIXIN_GZH_WITHDRAW(15, "微信公众号提现"),	
	ZFB_SHH_WITHDRAW(16, "支付宝生活号提现"),
	ZFB_APP_WITHDRAW(18, "支付宝小程序提现"),
	MERCHANT_GZH_CHARGE(19,"商户公众号充值");
	private Integer code;
	private String desc;

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	ChannelTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ChannelTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
	
	/**
	 * 
	 * <p>充值/提现</p>
	 * @param channelType
	 * @return
	 */
	public static boolean isRecharge (int channelType){
        return ChannelTypeEnum.MERCHANT_GZH_CHARGE.getCode() == channelType
                || ChannelTypeEnum.WEIXIN_GZH_CHARGE.getCode() == channelType
                || ChannelTypeEnum.WEIXIN_APP_CHARGE.getCode() == channelType
                || ChannelTypeEnum.ZFB_APP_CHARGE.getCode() == channelType
                || ChannelTypeEnum.ZFB_SHH_CHARGE.getCode() == channelType;
	}
}
