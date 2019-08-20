package com.stylefeng.guns.core.lambda;

@FunctionalInterface
public interface ListOperator<T> {
	/**
	 * 适用于集合等有下标的操作
	 * @param t
	 * @param index
	 * return 当返回false时终止遍历
	 */
    boolean operator(T t, int index);
}