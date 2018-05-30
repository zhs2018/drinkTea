package com.wxsoft.drinkTea.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import com.wxsoft.drinkTea.framework.config.SCHOOLMGRDEF;

public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 *
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	public static String getRandomCharAndNumr(Integer length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串
				// int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
				str += (char) (65 + random.nextInt(26));// 取得大写字母
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 *
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 *
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 *
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 *
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 *
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 字符型日期转化util.Date型日期
	 *
	 * @param p_strDate
	 *            字符型日期
	 * @param p_format
	 *            格式:"yyyy-MM-dd" / "yyyy-MM-dd hh:mm:ss"
	 * @return java.util.Date util.Date型日期
	 * @throws ParseException
	 */
	public static java.util.Date toUtilDateFromStrDateByFormat(
			String p_strDate, String p_format) throws ParseException {
		java.util.Date l_date = null;
		java.text.DateFormat df = new java.text.SimpleDateFormat(p_format);
		if (p_strDate != null && (!"".equals(p_strDate)) && p_format != null
				&& (!"".equals(p_format))) {
			l_date = df.parse(p_strDate);
		}
		return l_date;
	}

	/**
	 * 获取指定日期的毫秒
	 *
	 * @param p_date
	 *            util.Date日期
	 * @return long 毫秒
	 */
	public static long getMillisOfDate(java.util.Date p_date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(p_date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取2个字符日期的天数差
	 *
	 * @param p_startDate
	 * @param p_endDate
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(String p_startDate, String p_endDate)
			throws ParseException {

		Date l_startDate = toUtilDateFromStrDateByFormat(p_startDate,
				"yyyy-MM-dd");
		Date l_endDate = toUtilDateFromStrDateByFormat(p_endDate, "yyyy-MM-dd");
		long l_startTime = getMillisOfDate(l_startDate);
		long l_endTime = getMillisOfDate(l_endDate);
		long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 *
	 * @描述: 校验上传文件后缀名是否合法
	 * @作者: kasiaits
	 * @日期:2013-3-29
	 * @修改内容
	 * @参数： @param fileName
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	public static boolean checkUploadFile(String fileName) {
		String suffix = getFileSuffix(fileName);

		if (SCHOOLMGRDEF.fileSuffix.contains(suffix)
				&& checkMagicNumber(fileName))
			return true;

		return false;
	}

	/**
	 *
	 * @描述: 检查上传文件头是否合法
	 * @作者: kasiaits
	 * @日期:2013-3-29
	 * @修改内容
	 * @参数： @param fileName
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	public static boolean checkMagicNumber(String fileName) {
		byte[] magic = getMagicNumber(fileName);

		if (magic == null)
			return false;

		String magicStr = bytesToHexString(magic);
		if (SCHOOLMGRDEF.fileMagicNumber.contains(magicStr.toLowerCase()))
			return true;
		return false;
	}

	/**
	 *
	 * @描述: 获取文件扩展名称
	 * @作者: kasiaits
	 * @日期:2013-3-28
	 * @修改内容
	 * @参数： @param fileName
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	public static String getFileSuffix(String fileName) {
		String extention = "";
		if (fileName.length() > 0 && fileName != null) { // --截取文件名
			int i = fileName.lastIndexOf(".");
			if (i > -1 && i < fileName.length()) {
				extention = fileName.substring(i + 1); // --扩展名
			}
		}

		return extention;
	}

	/**
	 *
	 * @描述: 字节数组转化成字符串
	 * @作者: kasiaits
	 * @日期:2013-3-29
	 * @修改内容
	 * @参数： @param src
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 *
	 * @描述: 获取文件头内容
	 * @作者: kasiaits
	 * @日期:2013-3-29
	 * @修改内容
	 * @参数： @param fileName
	 * @参数： @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] getMagicNumber(String fileName) {
		try {
			FileInputStream is = new FileInputStream(fileName);
			byte[] b = new byte[4];
			is.read(b, 0, b.length);
			is.close();
			return b;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getAllDayByMonth(int maxDay) {
		List<String> result = new ArrayList<String>();
		for (int i = 1; i < maxDay + 1; i++) {
			if (i < 10) {
				result.add("0" + i);
			} else {
				result.add(String.valueOf(i));
			}

		}
		return result;
	}

	/**
	 * 获取可变日期内的所有天数
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getVariableDaysList(String startDate,
			String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int checkday = 0; // 开始结束相差天数
		try {
			checkday = (int) ((formatter.parse(endDate).getTime() - formatter
					.parse(startDate).getTime()) / (1000 * 24 * 60 * 60));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -checkday);// 把日期往后增加一天.整数往后推,负数往前移动
		if (checkday > 31) {
			for (int i = 0; i < checkday; i = i + 2) {
				calendar.add(calendar.DATE, 2);// 把日期往后增加一天.整数往后推,负数往前移动
				list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
			}
			if (checkday % 2 == 0) {
				calendar.add(calendar.DATE, 2);// 把日期往后增加一天.整数往后推,负数往前移动
				list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
			}
		} else {
			for (int i = 0; i < checkday; i = i + 1) {
				calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
			}
		}

		return list;
	}

	/**
	 * 获取可变日期内的所有天数
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getVariableDaysListUserJishu(String startDate,
			String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int checkday = 0; // 开始结束相差天数
		Calendar calendar = new GregorianCalendar();
		try {
			checkday = (int) ((formatter.parse(endDate).getTime() - formatter
					.parse(startDate).getTime()) / (1000 * 24 * 60 * 60));
			calendar.setTime(formatter.parse(startDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < checkday; i = i + 1) {
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}

		return list;
	}

	/**
	 * 获取可变日期内的所有天数
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getVariableDays(String startDate, String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int checkday = 0; // 开始结束相差天数
		Calendar calendar = new GregorianCalendar();
		try {
			checkday = (int) ((formatter.parse(endDate).getTime() - formatter
					.parse(startDate).getTime()) / (1000 * 24 * 60 * 60));
			calendar.setTime(formatter.parse(startDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < checkday - 1; i = i + 1) {
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}

		return list;
	}

	/**
	 * 返回最近7天内的所有日期
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getLastSevenDaysList() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -7);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 7; i++) {
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}
		return list;
	}

	/**
	 * 获取最近一个月内的所有日期
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getLastMonthDaysList() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 30; i = i + 2) {
			calendar.add(calendar.DATE, 2);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}
		return list;
	}

	/**
	 * 获取最近一个月内的所有日期
	 *
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getLastMonthDaysListUserJishu() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 30; i = i + 1) {
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}
		return list;
	}

	public static List<String> get24Hours() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				list.add("0" + String.valueOf(i) + ":00");
			} else {
				list.add(String.valueOf(i) + ":00");
			}
		}
		return list;

	}

	/**
	 * 判断给定时间在否在给定两个时间之前
	 */
	public static boolean do7(String star, String end) {
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		String daynow = day.format(new Date());
		SimpleDateFormat localTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date sdate = localTime.parse(daynow + " " + star);
			Date edate = localTime.parse(daynow + " " + end);
			long time = System.currentTimeMillis();
			if (time >= sdate.getTime() && time <= edate.getTime()) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

}
