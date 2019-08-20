package com.stylefeng.guns.core.lambda;

@FunctionalInterface
public interface MapOperator<K,V> {
	/**
	 * 适用于Map等键值对的操作
	 * @param t
	 * @param key
	 * return 当返回false时终止遍历
	 */
    boolean operator(K key, V value);
}