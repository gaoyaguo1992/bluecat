package com.stylefeng.guns.sharecore.modular.system.constants;
/**
 * 交易类型
 * @author Alan.huang
 *
 */
public enum ShareTradeTypeEnum {
	Designated_Time_1(1, "固定一小时充电"),
	Designated_Time_2(2, "固定二小时充电"),
	Designated_Time_3(3, "固定三小时充电"),
	Designated_Time_4(4, "固定四小时充电"),
	Designated_Time_5(5, "固定五小时充电"),
	Designated_Time_6(6, "固定六小时充电"),
	Designated_Time_7(7, "固定七小时充电"),
	Designated_Time_8(8, "固定八小时充电"),
	Designated_Time_9(9, "固定九小时充电"),
	Designated_Time_10(10, "固定十小时充电"),
	Designated_Time_11(11, "固定十一小时充电"),
	Designated_Time_12(12, "固定十二小时充电"),
	Designated_Time_13(13, "固定十三小时充电"),
	Designated_Time_14(14, "固定十四小时充电"),
	Designated_Time_15(15, "固定十五小时充电"),
	Designated_Time_16(16, "固定十六小时充电"),
	Designated_Time_17(17, "固定十七小时充电"),
	Designated_Time_18(18, "固定十八小时充电"),
	Designated_Time_19(19, "固定十九小时充电"),
	Designated_Time_20(20, "固定二十小时充电"),
	Designated_Time_21(21, "固定二十一小时充电"),
	Designated_Time_22(22, "固定二十二小时充电"),
	Designated_Time_23(23, "固定二十三小时充电"),
	Designated_Time_24(24, "固定二十四小时充电"),	
	Prepayment(25, "交预付金充电"),
	PrepaymentHaveFirstAmount(26,"含有首付费用的预付金充电"),
	// 50以上的留开和费用对应..
	ENTERPRISE_PAYMENT(56,"企业付款"),
	RECHARGE(90, "充值"),
	SCAN_QR(91,"扫设备二维码"),
	WITHRAW(92, "提现"),
	Order_Refund(93,"订单退款");
	
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

	ShareTradeTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareTradeTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
}
