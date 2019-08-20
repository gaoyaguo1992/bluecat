package com.stylefeng.guns.sharecore.modular.system.model;

/**
 * 
 * <P>提示消息类型</P>
 */
public enum NotifyMessageTypeEnum {
	LENT_MESSAGE(1, "租借消息"),
	BAK_MESSAGE(2, "归还消息"),
	WITHDRAW_MESSAGE(3, "提现消息"),
	BUY_MEMBER_MESSAGE(4, "购买会员消息"),
	MEMBER_LENT_MESSAGE(5, "会员租借消息"),
	REFUND_MESSAGE(6, "退款消息"),
	TIMEOUT_BAK_MESSAGE(7, "超时归还消息");
	
	
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

	NotifyMessageTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (NotifyMessageTypeEnum bn : values()) {
			if (null != code && code.intValue()==bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
	
}
