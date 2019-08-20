package com.stylefeng.guns.sharecore.modular.system.constants;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 交易类型
 * @author Alan.huang
 *
 */
public enum ShareFeeTypeEnum {
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
	
	
	private Integer code;
	private String desc;
	/**
	 * 充电时长，单
	 */
	private Long useTimesForSecnod;

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

	ShareFeeTypeEnum(Integer code, String desc, Long useTimesForSecnod) {
		this.code = code;
		this.desc = desc;
		this.useTimesForSecnod=useTimesForSecnod;
	}

	public Long getUseTimesForSecnod() {
		return useTimesForSecnod;
	}

	public Long getUseTimesForHour() {
		return useTimesForSecnod/3600;
	}
	
	public void setUseTimesForSecnod(Long useTimesForSecnod) {
		this.useTimesForSecnod = useTimesForSecnod;
	}
	/**
	 * 得到按时间控制的所有费用类型..
	 * @return
	 */
	public static List<ShareFeeTypeEnum> getShareFeeTypeByTimeHours(){
		List<ShareFeeTypeEnum> list= new ArrayList<>();
		list.add(ShareFeeTypeEnum.Designated_Time_1);
		list.add(ShareFeeTypeEnum.Designated_Time_2);
		list.add(ShareFeeTypeEnum.Designated_Time_3);
		list.add(ShareFeeTypeEnum.Designated_Time_4);
		list.add(ShareFeeTypeEnum.Designated_Time_5);
		list.add(ShareFeeTypeEnum.Designated_Time_6);
		list.add(ShareFeeTypeEnum.Designated_Time_7);
		list.add(ShareFeeTypeEnum.Designated_Time_8);
		list.add(ShareFeeTypeEnum.Designated_Time_9);
		list.add(ShareFeeTypeEnum.Designated_Time_10);
		list.add(ShareFeeTypeEnum.Designated_Time_11);
		list.add(ShareFeeTypeEnum.Designated_Time_12);
		list.add(ShareFeeTypeEnum.Designated_Time_13);
		list.add(ShareFeeTypeEnum.Designated_Time_14);
		list.add(ShareFeeTypeEnum.Designated_Time_15);
		list.add(ShareFeeTypeEnum.Designated_Time_24);
		return list;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ShareFeeTypeEnum bn : values()) {
			if(null!=code){
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}}
		}
		return desc;
	}
	/**
	 * 根据费用类型返回费用使用时长..
	 * @param code
	 * @return
	 */
	public static Long getUseTimesForSecnod(Integer code) {
		Long useTimes=0L;
		for (ShareFeeTypeEnum bn : values()) {
			if(null!=code){
				if (code.intValue()==bn.getCode()) {
					useTimes = bn.getUseTimesForSecnod();
					break;
				}
			}
		}
		return useTimes;
	}
	/*
	 * 根据code找到对应的费用类型， 如果转换失败或找不到直返回Prepayment(预付金)...
	 */
	public static ShareFeeTypeEnum getShareFeeTypeEnumByCode(String code){
		if(code==null||StringUtils.isEmpty(code)){
			return ShareFeeTypeEnum.Prepayment;
		}
		try {
			for (ShareFeeTypeEnum bn : values()) {
				if(null!=code){
					if (Integer.parseInt(code)==bn.getCode()) {
						return bn;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		//找不到，或转换失败
		return ShareFeeTypeEnum.Prepayment;
	}
}
