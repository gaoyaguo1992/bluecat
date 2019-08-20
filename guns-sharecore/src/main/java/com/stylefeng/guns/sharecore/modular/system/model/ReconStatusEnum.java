
package com.stylefeng.guns.sharecore.modular.system.model;


public enum ReconStatusEnum {
	SUCCESS(10, "成功"),
	PARTIAL_SUCCESS(15, "部分提现成功"),
	FAIL(11, "失败"),
	MANUAL_FAIL(14, "失败(手动对账)"),
	PROCESSING(12, "退款处理中"),
	CHANGE(13, "转入代发"),
	CHANNEL_FAIL_BANK_SUCCESS(16, "渠道失败银行成功")
	,CHANNEL_SUCCESS_BANK_FAIL(17, "渠道成功银行失败"),
	NOT_RECONCILED(18,"未对账");

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

	ReconStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ReconStatusEnum bn : values()) {
			if (null != code) {
				if (code.intValue()==bn.getCode()) {
					desc = bn.getDesc();
					break;
				}
			}
		}
		return desc;
	}
}
