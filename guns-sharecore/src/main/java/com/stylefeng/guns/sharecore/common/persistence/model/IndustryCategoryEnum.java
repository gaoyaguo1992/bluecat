package com.stylefeng.guns.sharecore.common.persistence.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alan.huang
 *
 */
public enum IndustryCategoryEnum {
	STAR_HOTEL(30, "星级酒店"),
	JINJI_LIANSUO_HOTEL(31, "经济连锁酒店"),
	BEAUTY_INDUSTRY(32, "丽人行业"),
	WU_LE(33, "足疗养生"),
	LY_DB(34, "洗浴按摩"),
	LG(35, "旅馆宾馆"),
	ZHONG_CAN(40, "中餐茶馆"),
	XI_CAN(41, "西餐咖啡"),
	KTV_DIAN(42, "KTV"),
	KTV_JBA(43, "电影酒吧"),
	CHE_ZHAN(46, "景点乐园"),
	TRAINING_CHANG(47, "培训机构"),
	CAR_SERVICE(48, "汽车服务"),
	TEA_MALL(49, "购物商城"),
	SHOPPING_MALL(50, "便利超市"),
	QI_TA(51, "其他"),
	EXCLUSIVE(60, "平台服务"),
	MEDICAL_CARE(38, "医疗保健");

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

	IndustryCategoryEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	/**
	 * 得到所有行业类型
	 * @return
	 */
	public static List<IndustryCategoryEnum> getIndustryCategoryEnumForList() {
		List<IndustryCategoryEnum> list=new ArrayList<>();
		for (IndustryCategoryEnum bn : values()) {
			list.add(bn);
		}
		return list;
	}
	/**
	 * 得到描述信息
	 * @param code
	 * @return
	 */
	public static String getDesc(Integer code) {
		String desc = "";
		for (IndustryCategoryEnum bn : values()) {
			if (code.intValue()==bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}
}
