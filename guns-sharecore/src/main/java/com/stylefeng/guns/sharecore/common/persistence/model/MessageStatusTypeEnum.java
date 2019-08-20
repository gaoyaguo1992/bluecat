package com.stylefeng.guns.sharecore.common.persistence.model;
/**
 * 提示消息状态
 * @author Alan.huang
 *
 */
public enum MessageStatusTypeEnum {

	READ_MESSAGE(1, "已读"),
	UNREAD_MESSAGE(2, "未读");
	
	
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

	MessageStatusTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (MessageStatusTypeEnum bn : values()) {
			if (null != code && code.intValue()==bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
	
}