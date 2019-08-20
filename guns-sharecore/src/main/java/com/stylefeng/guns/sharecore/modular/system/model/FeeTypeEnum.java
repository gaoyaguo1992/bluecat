package com.stylefeng.guns.sharecore.modular.system.model;

public enum FeeTypeEnum {
	UNKNOWN(0," ",0L),
	Designated_Time_1(1, "固定一小时充电",1*3600L),
	Designated_Time_2(2, "固定二小时充电",2*3600L),
	Designated_Time_3(3, "固定三小时充电",3*3600L),
	Designated_Time_4(4, "固定四小时充电",4*3600L),
	Designated_Time_5(5, "固定五小时充电",5*3600L),
	Designated_Time_6(6, "固定六小时充电",6*3600L),
	Designated_Time_7(7, "固定七小时充电",7*3600L),
	Designated_Time_8(8, "固定八小时充电",8*3600L),
	Designated_Time_9(9, "固定九小时充电",9*3600L),
	Designated_Time_10(10, "固定十小时充电",10*3600L),
	Designated_Time_11(11, "固定十一小时充电",11*3600L),
	Designated_Time_12(12, "固定十二小时充电",12*3600L),
	Designated_Time_13(13, "固定十三小时充电",13*3600L),
	Designated_Time_14(14, "固定十四小时充电",14*3600L),
	Designated_Time_15(15, "固定十五小时充电",15*3600L),
	Designated_Time_16(16, "固定十六小时充电",16*3600L),
	Designated_Time_17(17, "固定十七小时充电",17*3600L),
	Designated_Time_18(18, "固定十八小时充电",18*3600L),
	Designated_Time_19(19, "固定十九小时充电",19*3600L),
	Designated_Time_20(20, "固定二十小时充电",20*3600L),
	Designated_Time_21(21, "固定二十一小时充电",21*3600L),
	Designated_Time_22(22, "固定二十二小时充电",22*3600L),
	Designated_Time_23(23, "固定二十三小时充电",23*3600L),
	Designated_Time_24(24, "固定二十四小时充电",24*3600L),
	Prepayment(25,"预付费费用模式",0L),
	PrepaymentHaveFirstAmount(26,"含有首付费用的预付金充电",0L);
	
	private Integer id;
	private String name;
	/**
	 * 
	 */
	private Long useMinutes;

	FeeTypeEnum(Integer id, String name, Long useMinutes) {
		this.id = id;
		this.name = name;
		this.useMinutes=useMinutes;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getUseMinutes() {
		return useMinutes;
	}

	public void setUseMinutes(Long useMinutes) {
		this.useMinutes = useMinutes;
	}

	/**
	 * 根据Feetype code返回费用描述..
	 * @param code
	 * @return
	 */
	public static FeeTypeEnum getFeetypeEnum(Integer code) {		
		for (FeeTypeEnum bn : values()) {
			if (code.intValue() == bn.getId()) {
				return bn;
			}
		}
		return null;
	}
	/**
	 * 根据Feetype code返回费用描述..
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (FeeTypeEnum bn : values()) {
			if (code.intValue() == bn.getId()) {
				desc = bn.getName();
				break;
			}
		}
		return desc;
	}
	
}
