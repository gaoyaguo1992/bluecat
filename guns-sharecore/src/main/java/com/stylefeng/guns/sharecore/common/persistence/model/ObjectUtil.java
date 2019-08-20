package com.stylefeng.guns.sharecore.common.persistence.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectUtil {

	private static Logger logger = LoggerFactory.getLogger(ObjectUtil.class);    // 日志记录

	/**
	 *
	 * <p>根据属性名，获取属性值</p>
	 * @param fieldName 对象属性名
	 * @param o 操作对象
	 * @return
	 * @author #{谢文非-13620991931}
	 */
	public static Object getFieldValueByName(String fieldName, Object obj) {
		try {
			//获取两个下标之间的字符
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = obj.getClass().getMethod(getter);
			Object value = method.invoke(obj);
			return value;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return null;
		}
	}

	 /**
	  *
	  * <p>获取属性名数组 </p>
	  * @param o 操作对象
	  * @return
	  * @author #{谢文非-13620991931}
	  */
	public static String[] getFiledName(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 *
	 * <p>获取非空对象字段属性，属性值
	 * 注：忽略父类属性
	 * </p>
	 * @param obj
	 * @return
	 * @author #{谢文非-13620991931}
	 */
	public static Map<String, Object> getNotNullFiledsInfo(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> objectMap = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			//如果属性值不为空，保存到Map中
			if(getFieldValueByName(fields[i].getName(), obj) != null){
				objectMap.put(fields[i].getName(), getFieldValueByName(fields[i].getName(), obj));
			}
		}
		return objectMap;
	}
}
