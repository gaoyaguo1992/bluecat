package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 交易类型
 * @author Alan.huang
 *
 */
public enum ShareTradeFinishFlagEnum {
	USER_FINISH(10, "用户扫码结束"),
	SYSTEM_AUTO_FINISH(11,"系统自动归还结束"),
	SYSTEM_FORCE_FINISH(12, "平台强制归还结束"),
	UNKOWN(13, "未知归还结束");
	
	
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

	ShareTradeFinishFlagEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareTradeFinishFlagEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
