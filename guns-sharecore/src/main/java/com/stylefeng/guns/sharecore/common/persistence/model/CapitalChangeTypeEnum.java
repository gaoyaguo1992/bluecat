/**
 * 
 */
package com.stylefeng.guns.sharecore.common.persistence.model;

/**
 *	FREEZED("01", "冻结"), UNFREEZED("09", "解冻"), WITHDRAW("02", "提现"), WITHDRAW_BACK("08", "提现退回"),RECHARGE("03", "充值"),PAY("04","付款"),
	CARD_FREEZED("05", "优惠券冻结"),CARD_PAY("07","优惠券消费"),CARD_UNFREEZED("06", "优惠券解冻"),TRANSFER("99","转账");

 */
public enum CapitalChangeTypeEnum {
	// 00:未发布;01：已发布；02：交易完成；99撤销
	FREEZED(1, "冻结"), 	
	WITHDRAW(2, "提现"),	
	RECHARGE(3, "充值"),
	PAY(4,"付款"),
	WITHDRAW_BACK(8, "提现退回"),
	UNFREEZED(9, "解冻"),
	JIFEN_IN(10,"计入积分"),
	CREDIT_IN(11,"信用租借入金"),
	TRANSFER_IN(98,"账入"),
	TRANSFER_OUT(97,"账出"),
	TRANSFER(99,"转账");
	
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

	CapitalChangeTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (CapitalChangeTypeEnum bn : values()) {
			if (code.intValue() == bn.getCode() ) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
