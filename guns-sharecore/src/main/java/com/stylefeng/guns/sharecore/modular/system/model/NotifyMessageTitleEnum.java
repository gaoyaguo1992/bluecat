package com.stylefeng.guns.sharecore.modular.system.model;

/**
 * 
 * <P>通知消息标题</P>
 */
public enum NotifyMessageTitleEnum {
	LENT_MESSAGE(1, "充电器借入凭证通知"),
	BAK_MESSAGE(2, "充电器归还通知"),
	WITHDRAW_MESSAGE(3, "提现申请通知"),
	BUY_MEMBER_MESSAGE(4, "购买会员通知"),
	MEMBER_LENT_MESSAGE(5, "会员租借通知"),
	REFUND_MESSAGE(6, "退款通知"),
	TIMEOUT_BAK_MESSAGE(7, "超时归还通知");
	
	private Integer code;
	private String desc;

	public Integer getCode() {
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

	NotifyMessageTitleEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (NotifyMessageTitleEnum bn : values()) {
			if (null != code && code.intValue()==bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
	
}
