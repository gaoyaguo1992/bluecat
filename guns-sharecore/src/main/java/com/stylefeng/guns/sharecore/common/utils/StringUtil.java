package com.stylefeng.guns.sharecore.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 去字符串中所有空格和分行字符；
	 * 如："just do it!" > "justdoit!"
	 * 如："1000107225,\r\n1000107224" 	> 1000107225,1000107224
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
