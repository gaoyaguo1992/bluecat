package com.stylefeng.guns.core.lambda;

@FunctionalInterface
public interface BeanOperator<T> {
	/**
	 * 适用于普通对象
	 * @param t
	 * return 当返回false时终止遍历
	 */
    boolean operator(T t);
}