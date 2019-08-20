package com.stylefeng.guns.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public final class DecimalUtil {
	private DecimalUtil() {
		/* EMPTY */
	}
	private static final String PATTERN = "0.00";
	private static final String PATTERN_WITH_ONE = "0.0";
	private static final String PATTERN_WITHOUT_SCALE = "0";
	private static final DecimalFormat FORMAT_WITHOUT_SCALE = new DecimalFormat(PATTERN_WITHOUT_SCALE);
	private static final DecimalFormat FORMAT = new DecimalFormat(PATTERN);
	private static final DecimalFormat FORMAT_WITH_ONE = new DecimalFormat(PATTERN_WITH_ONE);
	private static final String PATTERN_MAP = "0.000000";
	public static final int PROFIT_SCALE = 4;//金额计算保留位数
	public static final BigDecimal NEGATIVE_ONE = new BigDecimal(-1);//-1
	private static final DecimalFormat FORMAT_MAP = new DecimalFormat(PATTERN_MAP);
	public final static BigDecimal ONE_HUNDRED = new BigDecimal(100);
	static {
		FORMAT.setRoundingMode(RoundingMode.HALF_UP);
		FORMAT_WITHOUT_SCALE.setRoundingMode(RoundingMode.HALF_UP);
		FORMAT_WITH_ONE.setRoundingMode(RoundingMode.HALF_UP);
	}

	/**
	 * 小数点后位数
	 */
	private static final int SCALE = 2;

	/**
	 * <p>转换double类型数据到BigDecimal类型.</p>
	 * 
	 * @param value Double类型的数据
	 * @return BigDecimal类型数据
	 */
	public static BigDecimal format(final double value) {
		return (new BigDecimal(FORMAT.format(value)));
	}

	/**
	 * <p>转换double类型数据到BigDecimal类型.</p>
	 * 
	 * @param value Double类型的数据
	 * @return BigDecimal类型数据
	 */
	public static BigDecimal formatMap(final double value) {
		return (new BigDecimal(FORMAT_MAP.format(value)));
	}
	/**
	 * <p>转换long类型数据到BigDecimal类型.</p>
	 * 
	 * @param value Double类型的数据
	 * @return BigDecimal类型数据
	 */
	public static BigDecimal format(final long value) {
		return (new BigDecimal(FORMAT.format(value)));
	}

	public static BigDecimal formatWithoutScale(final BigDecimal value) {
		return (new BigDecimal(FORMAT_WITHOUT_SCALE.format(value)));
	}
	
	public static BigDecimal formatWithOne(final BigDecimal value) {
		return (new BigDecimal(FORMAT_WITH_ONE.format(value)));
	}

	/**
	 * <p>格式化BigDecimal类型的数值.</p>
	 * 
	 * @param value BigDecimal类型的数据
	 * @return BigDecimal类型数据
	 */
	public static BigDecimal format(final BigDecimal value) {
		return value.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * <p>BigDecimal加法运算.</p>
	 * 
	 * @param d1 加数
	 * @param d2 加数
	 * @return BigDecimal类型
	 * @author Leon.Qiu 2011-11-17 下午2:26:52
	 */
	public static BigDecimal add(final BigDecimal d1, final BigDecimal d2) {
		return format(d1.add(d2));
	}

	/**
	 * <p>多个BigDecimal类型的数据相加.</p>
	 * 
	 * @param v1
	 * @param vs
	 * @return
	 */
	public static BigDecimal add(final BigDecimal v1, final BigDecimal... vs) {
		BigDecimal sum = BigDecimal.ZERO;
		for (final BigDecimal v : vs) {
			sum = add(sum, v);
		}
		return add(v1, sum);
	}

	/**
	 * <p>多个BigDecimal类型的数据连减.</p>
	 * 
	 * @param v1
	 * @param vs
	 * @return
	 */
	public static BigDecimal subtract(final BigDecimal v1, final BigDecimal... vs) {
		final BigDecimal sum = add(BigDecimal.ZERO, vs);
		return subtract(v1, sum);
	}

	/**
	 * <p>BigDecimal减法运算.</p>
	 * 
	 * @param d1 运算数
	 * @param d2 运算数
	 * @return BigDecimal类型
	 * @author Leon.Qiu 2011-11-16 上午9:32:32
	 */
	public static BigDecimal subtract(final BigDecimal d1, final BigDecimal d2) {
		return format(d1.subtract(d2));
	}

	private static int compareBigDecimal(final BigDecimal v1, final BigDecimal v2) {
		return (format(v1).compareTo(format(v2)));
	}

	/**
	 * 
	 * <p>大于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean gt(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) > 0;
	}

	/**
	 * <p>大于等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean ge(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) >= 0;
	}

	/**
	 * <p>等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean eq(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) == 0;
	}

	/**
	 * <p>小于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean lt(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) < 0;
	}

	/**
	 * <p>小于等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean le(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) <= 0;
	}

	/**
	 * <p>不等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean ne(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) != 0;
	}

	/**
	 * <p>除法操作.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal divide(final BigDecimal v1, final BigDecimal v2) {
		return v1.divide(v2, SCALE,BigDecimal.ROUND_HALF_UP);
		//return format(v1.divide(v2));
	}

	/** 乘法操作 */
	public static BigDecimal multiply(final BigDecimal v1, final BigDecimal v2) {
		return format(v1.multiply(v2));
	}
        /**
	 * <p>多个BigDecimal类型的数据相乘.</p>
	 * 
	 * @param v1
	 * @param vs
	 * @return
	 */
	public static BigDecimal multiply(final BigDecimal v1, final BigDecimal... vs) {
		BigDecimal sum = v1;
		for (final BigDecimal v : vs) {
			sum = multiply(sum, v);
		}
		return sum;
	}
	/*=========新增带位数的格式化================*/
	public static BigDecimal format(BigDecimal value,int num){
		return value.setScale(num, BigDecimal.ROUND_HALF_UP);
	}

    /**
	 * <p>BigDecimal加法运算.</p>
	 * 
	 * @param d1 加数
	 * @param d2 加数
	 * @return BigDecimal类型
	 * @author Leon.Qiu 2011-11-17 下午2:26:52
	 */
	public static BigDecimal add(final BigDecimal d1, final BigDecimal d2,final int num) {
		return format(d1.add(d2),num);
	}
	/**
	 * <p>BigDecimal减法运算.</p>
	 * 
	 * @param d1 运算数
	 * @param d2 运算数
	 * @return BigDecimal类型
	 * @author Leon.Qiu 2011-11-16 上午9:32:32
	 */
	public static BigDecimal subtract(final BigDecimal d1, final BigDecimal d2,final int num) {
		return format(d1.subtract(d2),num);
	}

	private static int compareBigDecimal(final BigDecimal v1, final BigDecimal v2,final int num) {
		return (format(v1,num).compareTo(format(v2,num)));
	}
	
	/** 乘法操作 */
	public static BigDecimal multiply(final BigDecimal v1, final BigDecimal v2,final int num) {
		return format(v1.multiply(v2),num);
	}
	/**
	 * <p>除法操作.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal divide(final BigDecimal v1, final BigDecimal v2,final int num) {
		return v1.divide(v2, num,BigDecimal.ROUND_HALF_UP);
	}
	//=====================比较相关==================================//
	/**
	 * 
	 * <p>大于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean gt(final BigDecimal v1, final BigDecimal v2,final int num) {
		return compareBigDecimal(v1, v2,num) > 0;
	}
	/**
	 * <p>大于等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean ge(final BigDecimal v1, final BigDecimal v2,final int num) {
		return compareBigDecimal(v1, v2,num) >= 0;
	}

	/**
	 * <p>等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean eq(final BigDecimal v1, final BigDecimal v2,final int num) {
		return compareBigDecimal(v1, v2,num) == 0;
	}

	/**
	 * <p>小于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean lt(final BigDecimal v1, final BigDecimal v2,final int num) {
		return compareBigDecimal(v1, v2,num) < 0;
	}

	/**
	 * <p>小于等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean le(final BigDecimal v1, final BigDecimal v2,final int num) {
		return compareBigDecimal(v1, v2,num) <= 0;
	}

	/**
	 * <p>不等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean ne(final BigDecimal v1, final BigDecimal v2,final int num) {
		return compareBigDecimal(v1, v2,num) != 0;
	}


}
