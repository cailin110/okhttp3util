package com.zhishen.soft.happy.util;

public class StringUtil {

	public static boolean isEmptyOrNull(String str) {
		return str == null || str.trim().length() <= 0
				|| str.trim().toLowerCase().equals("null")
				|| str.trim().equals("");
	}

	public static boolean isEmptyOrZero(String str) {
		return isEmptyOrNull(str) || "0".equals(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmptyOrNull(str);
	}

}
