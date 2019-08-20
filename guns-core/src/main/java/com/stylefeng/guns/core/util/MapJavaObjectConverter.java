package com.stylefeng.guns.core.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * <P>Map Java object转换类</P>
 * 
 */
public abstract class MapJavaObjectConverter {

	private static String convertToJavaObjectError = "map 转换成 java object错误！";
	private static String convertToMapError = "java object  转换成 错误！map";
	private static String classStr = "class";
	private final static Logger logger = LoggerFactory.getLogger(MapJavaObjectConverter.class);
	private static String AMOUNT_PATTERN = "(?!0)\\d{1,16}";

	/**
	 * 
	 * <p>Map to Java object转换</p>
	 * 
	 * @param javaObjectType
	 * @param map
	 * @return
	 */
	public static <T> T mapToObject(final Class<T> javaObjectType, final Map<String, Object> map) {
		T javaObject = null;
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(javaObjectType);
			// 获取类属性 　
			javaObject = javaObjectType.newInstance();
			for (final PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
				final String propertyName = descriptor.getName();
				if (classStr.equals(propertyName)) {
					continue;
				}
				if (map.containsKey(propertyName)) {
					final Object value = map.get(propertyName);
					if (value == null||"".equals(value)) {
						continue;
					}
					 if (java.lang.Integer.class == descriptor.getPropertyType()) {
						descriptor.getWriteMethod().invoke(javaObject, new Integer((String) value));
					} else if (java.lang.Long.class == descriptor.getPropertyType()) {
						descriptor.getWriteMethod().invoke(javaObject, new Long((String) value));
					} else {
						descriptor.getWriteMethod().invoke(javaObject, value);
					}
				}
			}
		} catch (final IntrospectionException e) {
			logger.error(convertToJavaObjectError, e);
		} catch (final IllegalArgumentException e) {
			logger.error(convertToJavaObjectError, e);
		} catch (final IllegalAccessException e) {
			logger.error(convertToJavaObjectError, e);
		} catch (final InvocationTargetException e) {
			logger.error(convertToJavaObjectError, e);
		} catch (final InstantiationException e) {
			logger.error(convertToJavaObjectError, e);
		}
		return javaObject;
	}

	/**
	 * 
	 * <p>Java object to Map 转换</p>
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(final Object o) {
		try {
			final Map<String, Object> beanMap = BeanUtils.describe(o);
			beanMap.remove(classStr);
			return beanMap;
		} catch (final IllegalAccessException e) {
			logger.error(convertToMapError, e);
		} catch (final InvocationTargetException e) {
			logger.error(convertToMapError, e);
		} catch (final NoSuchMethodException e) {
			logger.error(convertToMapError, e);
		}
		return null;
	}

}
