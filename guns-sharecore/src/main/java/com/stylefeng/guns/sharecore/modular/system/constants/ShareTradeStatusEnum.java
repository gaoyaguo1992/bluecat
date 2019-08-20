package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 交易类型
 * @author Alan.huang
 *
 */
public enum ShareTradeStatusEnum {
	TradingUsing(10, "使用中"),
	Finish(11, "已归还"),
	SCANQR(12,"扫充电器二维码"),
	REFUNDSUCCESS(13, "退款成功"),
	REFUND(-11,"退款失败"),
	UNKNOW(-1,"未知状态");
	
	
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

	ShareTradeStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareTradeStatusEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
