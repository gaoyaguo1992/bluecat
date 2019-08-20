package com.stylefeng.guns.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 处理 SpringMvc接受特殊符号参数被转义 的问题
 * 
 * @author  Alan.lv
 *
 */
public class SpringMvcParamTransferred {
	/**
	 * 获取field字段对应的get或set文件，
	 * 
	 * @param fieldName
	 * @param type
	 *            如果为get返回get文件，set返回set文件..
	 * @return
	 */
	public static String getFieldMethodName(String fieldName, String type) {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		return String.format("%s%s%s", type, firstLetter, fieldName.substring(1));
	}

	/**
	 * 处理String类型的参数转义的问题.. java中可以使用 org.apache.commons.lang3 包中的
	 * StringEscapeUtils.unescapeHtml4(String str) 方法来进行解码。
	 */
	@SuppressWarnings("deprecation")
	public static void doParamForString(Object obj) {
		if (obj == null) {
			return;
		}
		//1. 获到所有fields...
		Field[] fields = obj.getClass().getDeclaredFields();
		String getMethodName = "", setMethodName = "";
		String val = "";
		Method getMethod, setMethod;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
				getMethodName = SpringMvcParamTransferred.getFieldMethodName(fields[i].getName(), "get");
				setMethodName = SpringMvcParamTransferred.getFieldMethodName(fields[i].getName(), "set");
				try {
					getMethod = obj.getClass().getMethod(getMethodName);
					try {
						val = (String) getMethod.invoke(obj);
						if (val != null && val != "") {
							val=ToolUtil.unescapeHtml4(val);
							setMethod = obj.getClass().getMethod(setMethodName, String.class);
							setMethod.invoke(obj, val);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		// supper cls.getClass().getSuperclass()
		//2. 超级类...
		Class supperCls = obj.getClass().getSuperclass();
		if (supperCls != null) {
			fields=supperCls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
					getMethodName = SpringMvcParamTransferred.getFieldMethodName(fields[i].getName(), "get");
					setMethodName = SpringMvcParamTransferred.getFieldMethodName(fields[i].getName(), "set");
					try {
						getMethod = obj.getClass().getMethod(getMethodName);
						try {
							val = (String) getMethod.invoke(obj);
							if (val != null && val != "") {
								val = val.replaceAll("& ", "&");
								val = StringEscapeUtils.unescapeHtml4(val);
								setMethod = obj.getClass().getMethod(setMethodName, String.class);
								setMethod.invoke(obj, val);
							}
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
