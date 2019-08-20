/**
 * 提现申请处理状态。。
 */
package com.stylefeng.guns.sharecore.modular.system.constants;

/**
 * 
 * 提现申请状态
 *
 */
public enum WithdrawStatusEnum {
	
	SUCCESS(1,"成功"),
	FAILED(2,"失败"),
	HANDLING(3,"处理中"),
	EXCEPTION(4,"异常"),
	NONE(5,"未知");
	
	private Integer code;
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	WithdrawStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (WithdrawStatusEnum bn : values()) {
			if (code == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
