package com.stylefeng.guns.core.lambda;

import java.util.Date;

@FunctionalInterface
public interface DateOperator {
	/**
	 * 适用于普通对象
	 * @param t
	 * return 当返回false时终止遍历
	 */
    boolean operator(Date t);
}