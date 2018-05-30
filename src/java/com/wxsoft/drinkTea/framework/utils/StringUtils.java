package com.wxsoft.drinkTea.framework.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字符串处理类
 *
 * @author ycb
 *
 */
public class StringUtils {
	/**
	 * 转换成字符串
	 *
	 * @param object
	 */
	public static String toString(Object object) {
		if (object == null || String.valueOf(object).isEmpty() || "null".equals(object)) {
			return "";
		}
		return object.toString();
	}

	public static String trim(String str) {
		if (str == null || String.valueOf(str).isEmpty()) {
			return str;
		}
		return str.trim();
	}

	public static String toStrNum(Object object) {
		if (object == null || String.valueOf(object).isEmpty() || "null".equals(object) || "".equals(object)) {
			return "0";
		}
		return object.toString();
	}

	/**
	 * 对象转换为字符串，当对象为 null对象、null值、空时，则使用默认值
	 *
	 * @param object
	 *            需要转换的对象
	 * @param defaultValue
	 *            默认值
	 * @return String 转换后的字符串
	 * @author 滕开良
	 */
	public static String toString(Object object, final String defaultValue) {
		if (object == null || String.valueOf(object).isEmpty() || "null".equals(object)) {
			return defaultValue;
		}
		return object.toString();
	}

	/**
	 * 比较字符串
	 *
	 * @param object
	 */
	public static int compare(Object object1, Object object2) {
		if (object1 == null && object2 == null) {
			return 0;
		}
		return toString(object1).compareTo(toString(object2));
	}

	/**
	 *
	 * @param html
	 * @return
	 */
	public static String protectltAndGt(String html) {

		String text = null;
		if (html != null && !html.equals("")) {
			text = html.replace("<", "&lt").replace(">", "&gt");
		}
		return text;
	}

	public static String subString(String str, int length) {
		String return_str = str;// 返回的字符串
		int temp_int = 0;// 将汉字转换成两个字符后的字符串长度
		int cut_int = 0;// 对原始字符串截取的长度
		byte[] b = str.getBytes();// 将字符串转换成字符数组

		for (int i = 0; i < b.length; i++) {
			if (b[i] >= 0) {
				temp_int = temp_int + 1;
			} else {
				temp_int = temp_int + 2;// 一个汉字等于两个字符
				i++;
			}
			cut_int++;

			if (temp_int >= length) {
				if (temp_int % 2 != 0 && b[temp_int - 1] < 0) {
					cut_int--;
				}
				return_str = return_str.substring(0, cut_int);
				break;
			}
		}
		return return_str;
	}

	public static String formatMoney(Object obj) {
		if (!ValidatorUtils.isFloat(obj)) {
			return toString(obj);
		}
		if (ValidatorUtils.isEmpty(obj)) {
			return toString(obj);
		}
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("##,###.00");
		String result = myformat.format(Float.parseFloat(toString(obj)));
		if (".00".equals(result)) {
			result = "0.00";
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(formatMoney(28420.55555));
	}

	/**
	 * 把unicode转换为中文
	 *
	 * @param ascii
	 * @return
	 */
	public static String ascii2native(String ascii) {
		int n = ascii.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = ascii.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
	}

	public static List<Integer> getIdsForStr(String str) {
		List<Integer> ids = new ArrayList<Integer>();
		if (!ValidatorUtils.isEmpty(str)) {
			String[] strs = str.split(",");
			try {
				for (int i = 0; i < strs.length; i++) {
					ids.add(Integer.parseInt(strs[i]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ids;
		} else {
			return null;
		}
	}

	public static Map<String, String> getIdsValueForStr(String str) {
		Map<String, String> map = new HashMap<String, String>();
		if (!ValidatorUtils.isEmpty(str)) {
			String[] strs = str.split(",");
			try {
				for (int i = 0; i < strs.length; i++) {
					String s = strs[i];
					if (!ValidatorUtils.isEmpty(s)) {
						String[] keyAndValue = s.split(":");
						if (keyAndValue.length == 2) {
							map.put(keyAndValue[0], keyAndValue[1]);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		} else {
			return null;
		}

	}

	public static String[] arrayUnique(String[] a) {
		// array_unique
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < a.length; i++) {
			if (!list.contains(a[i])) {
				list.add(a[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String getStringNum(String str) {
		str = str.trim();
		String str2 = "";
		if (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					str2 += str.charAt(i);
				}
			}

		}
		return str2;
	}

}
