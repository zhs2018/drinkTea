package com.wxsoft.drinkTea.framework.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * <p>
 * 日期时间处理类。
 * </p>
 *
 * @author [作者]
 * @version [版本号, 20130320] 修改记录 修改日期 修改者 修改内容 </br>
 *          0 2013/03/20 yangcb 初稿
 */
public class DateUtils {
    public DateUtils() {
    }

    /**
     * 计算指定月份的天数
     *
     * @param month
     *            月份
     * @return 天数
     */
    public static int getDayNumberOfMonth(String month) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date date = sdf.parse(month, pos);
        if (pos.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month + "无效");
        }
        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date);
        curCal.add(Calendar.MONTH, 1);
        int dd = curCal.get(Calendar.DATE);
        curCal.add(Calendar.DATE, -dd);
        dd = curCal.get(Calendar.DATE);
        return dd;
    }

    /**
     * 比较给定的两个日期月份是否相同
     *
     * @param month1
     *            月份1
     * @param month2
     *            月份2
     * @return 相同为true，否则为falst
     */
    public static boolean isEqual(String month1, String month2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month1, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month1 + "无效");
        }

        ParsePosition pos2 = new ParsePosition(0);
        Date date2 = sdf.parse(month2, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month2 + "无效");
        }

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date1);
        int yy1 = curCal.get(Calendar.YEAR);
        int mm1 = curCal.get(Calendar.MONTH);
        curCal.setTime(date2);
        int yy2 = curCal.get(Calendar.YEAR);
        int mm2 = curCal.get(Calendar.MONTH);
        if (yy1 == yy2 && mm1 == mm2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较month1是否小于month2
     *
     * @param month1
     *            月份1
     * @param month2
     *            月份2
     * @return 小于为true，否则为falst
     */
    public static boolean isLess(String month1, String month2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month1, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month1 + "无效");
        }

        ParsePosition pos2 = new ParsePosition(0);
        Date date2 = sdf.parse(month2, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month2 + "无效");
        }

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date1);
        int yy1 = curCal.get(Calendar.YEAR);
        int mm1 = curCal.get(Calendar.MONTH);
        curCal.setTime(date2);
        int yy2 = curCal.get(Calendar.YEAR);
        int mm2 = curCal.get(Calendar.MONTH);
        if (yy1 < yy2 || (yy1 == yy2 && mm1 < mm2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较date1是否小于date1
     *
     * @param month1
     *            月份1
     * @param month2
     *            月份2
     * @return 小于为true，否则为falst
     */
    public static boolean isLessDate(String month1, String month2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month1, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month1 + "无效");
        }

        ParsePosition pos2 = new ParsePosition(0);
        Date date2 = sdf.parse(month2, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month2 + "无效");
        }

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date1);
        int dd1 = curCal.get(Calendar.DATE);
        int mm1 = curCal.get(Calendar.MONTH);
        curCal.setTime(date2);
        int dd2 = curCal.get(Calendar.DATE);
        int mm2 = curCal.get(Calendar.MONTH);
        if (mm1 < mm2 || (mm1 == mm2 && dd1 < dd2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较month1是否小于等于month2
     *
     * @param month1
     *            月份1
     * @param month2
     *            月份2
     * @return 小于为true，否则为falst
     */
    public static boolean isLessEqual(String month1, String month2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month1, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month1 + "无效");
        }

        ParsePosition pos2 = new ParsePosition(0);
        Date date2 = sdf.parse(month2, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month2 + "无效");
        }

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(date1);
        int yy1 = curCal.get(Calendar.YEAR);
        int mm1 = curCal.get(Calendar.MONTH);
        curCal.setTime(date2);
        int yy2 = curCal.get(Calendar.YEAR);
        int mm2 = curCal.get(Calendar.MONTH);
        if (yy1 < yy2 || (yy1 == yy2 && mm1 <= mm2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算从begin到end之间在month所指月份的天数
     *
     * @param startMonth
     * @param endMonth
     * @param month
     * @return
     */
    public static int getDayNumberOfMonth(String begin, String end, String month) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date beginDate = sdf.parse(begin, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + begin + "无效");
        }
        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(beginDate);

        ParsePosition pos2 = new ParsePosition(0);
        Date endDate = sdf.parse(end, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + end + "无效");
        }
        if (endDate.before(getMinDate())) {
            endDate = new Date();
        }
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        ParsePosition pos3 = new ParsePosition(0);
        Date monthDate = sdf.parse(month, pos3);
        if (pos3.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month + "无效");
        }
        Calendar monthCal = Calendar.getInstance();
        monthCal.setTime(monthDate);

        Calendar monthFirstDate = Calendar.getInstance();
        monthFirstDate.setTime(monthDate);
        monthFirstDate.set(Calendar.DATE, 1);
        Calendar monthLastDate = Calendar.getInstance();
        monthLastDate.setTime(monthFirstDate.getTime());
        monthLastDate.add(Calendar.MONTH, 1);
        monthLastDate.add(Calendar.DATE, -1);

        if (beginCal.after(monthLastDate)) {
            return 0;
        }
        if (endCal.before(monthFirstDate)) {
            return 0;
        }
        if (beginCal.after(monthFirstDate)) {
            monthFirstDate = beginCal;
        }
        if (endCal.before(monthLastDate)) {
            monthLastDate = endCal;
        }

        int dd1 = monthFirstDate.get(Calendar.DATE);
        int dd2 = monthLastDate.get(Calendar.DATE);
        return dd2 - dd1 + 1;
    }

    /**
     * 计算从startMonth到endMonth之间小于month所指日期的天数
     *
     * @param startMonth
     * @param endMonth
     * @param month
     * @return
     */
    public static int getDayNumberToMonth(String startMonth, String endMonth, String month) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date startDate = sdf.parse(startMonth, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + startMonth + "无效");
        }
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        ParsePosition pos2 = new ParsePosition(0);
        Date endDate = sdf.parse(endMonth, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + endMonth + "无效");
        }
        if (endDate.before(getMinDate())) {
            endDate = new Date();
        }
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        ParsePosition pos3 = new ParsePosition(0);
        Date monthDate = sdf.parse(month, pos3);
        if (pos3.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month + "无效");
        }
        Calendar monthCal = Calendar.getInstance();
        monthCal.setTime(monthDate);

        // 若month<begin ，返回0
        if (startCal.after(monthCal)) {
            return 0;
        }
        // 若月份大于end date ,则按end date 算
        if (endCal.before(monthCal)) {
            monthCal = endCal;
        }

        int num = 1;
        while (startCal.before(monthCal)) {
            startCal.add(Calendar.DATE, 1);
            num++;
        }
        return num;
    }

    /**
     * 给定两个日期，返回日期的天数差
     *
     * @param startMonth
     * @param endMonth
     * @return
     * @throws java.lang.Exception
     */
    public static int getDayNumberBetweenMonth(String startMonth, String endMonth) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date startDate = sdf.parse(startMonth, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + startMonth + "无效");
        }
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        ParsePosition pos2 = new ParsePosition(0);
        Date endDate = sdf.parse(endMonth, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + endMonth + "无效");
        }
        if (endDate.before(getMinDate())) {
            endDate = new Date();
        }
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        if (startCal.after(endCal)) {
            return 0;
        }
        int num = 1;
        while (startCal.before(endCal)) {
            startCal.add(Calendar.DATE, 1);
            num++;
        }
        return num;
    }

    /**
     * 给定的两个日期，返回介于这两个日期的月份（包括这两个日期月份）之间的月份的列表
     *
     * @param month1
     *            月份1
     * @param month2
     *            月份2
     * @return 介于两个日期之间的所有月份
     */
    public static List monthList(String month1, String month2) throws Exception {
        ArrayList list = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month1, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month1 + "无效");
        }

        ParsePosition pos2 = new ParsePosition(0);
        Date date2 = sdf.parse(month2, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month2 + "无效");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.set(Calendar.DATE, 1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.set(Calendar.DATE, 2);

        while (cal1.before(cal2)) {
            list.add(cal1.get(Calendar.YEAR) + "-" + (cal1.get(Calendar.MONTH) + 1) + "-" + cal1.get(Calendar.DATE));
            cal1.add(Calendar.MONTH, 1);
        }
        return list;
    }

    /**
     *
     * 给定两个日期，返回其中较小的一个
     *
     * @param month1
     * @param month2
     * @return
     */
    public static String minMonth(String month1, String month2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month1, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month1 + "无效");
        }

        ParsePosition pos2 = new ParsePosition(0);
        Date date2 = sdf.parse(month1, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month2 + "无效");
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        if (cal1.after(cal2)) {
            return month2;
        } else {
            return month1;
        }
    }

    /**
     *
     * 给定两个日期，返回相差月份值
     *
     * @param month1
     * @param month2
     * @return
     */
    public static int subMonth(Date date1, Date date2) throws Exception {
        int result = 0;
        int subyear = 0;
        int submonth = 0;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        subyear = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        submonth = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        result = subyear * 12 + submonth;
        return Math.abs(result);
    }

    /**
     *
     * 给定初始日期，及一个整数，返回初始日期＋整数的日期
     *
     * @param month1
     * @param dd
     * @return
     */
    public static String addDate(String date, int field, int dd) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(date, pos1);

        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + date + "无效");
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.add(field, dd);
        return cal1.get(Calendar.YEAR) + "-" + (cal1.get(Calendar.MONTH) + 1) + "-" + cal1.get(Calendar.DATE);

    }

    /**
     * 获取指定月份的第一天的日期
     *
     * @param month1
     * @return
     */
    public static String getFirstDayOfMonth(String month) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month, pos1);

        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month + "无效");
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        return cal1.get(Calendar.YEAR) + "-" + cal1.get(Calendar.MONTH) + "-1";
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当月第一天
     */
    public static Date getCurMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }

    /**
     * 获取下月第一天
     */
    public static Date getNextMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);//
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }

    /**
     * 获取当前是星期几
     */
    public static int getWeekNumOfDate(Date dt) {
        int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    private static Date getMinDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date dd = sdf.parse("1990-1-1", pos1);
        return dd;
    }

    public static String getCurrentDateStr() {
        return getCurrentDateFormat("yyyyMMdd");
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getCurrentDateFormat(String format) {
    	Date date = new Date();
        String formatDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        formatDate = sdf.format(date);
        System.out.println("当前时间"+date.toString()+"格式化后的时间："+formatDate);
        return formatDate;
    }

    public static String getDateFormat(Date date, String format) {
    	if (date == null) {
    		return null;
    	}
        String formatDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        formatDate = sdf.format(date);
        return formatDate;
    }

    public static Date getDateByFormat(String dateStr, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException ex) {
            return null;
        }
        return date;
    }

    public static Date parseDate(String year, String month, String day) {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            if (month.length() < 2) {
                month = "0" + month;
            }
            if (day.length() < 2) {
                day = "0" + day;
            }
            date = sdf.parse(year + month + day);
        } catch (ParseException ex) {
            return null;
        }
        return date;
    }

    public static Date getLastMonth() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 根据时间获取上一月份
     * @param month
     * @param format
     * @return
     */
    public static String getPreviousMonth(String month, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.getDateByFormat(month, format));
        cal.add(Calendar.MONTH, -1);
        return DateUtils.getDateFormat(cal.getTime(), format);
    }
    /**
     * 根据时间获取下一月份
     * @param month
     * @param format
     * @return
     */
    public static String getNextMonth(String month, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.getDateByFormat(month, format));
        cal.add(Calendar.MONTH, 1);
        return DateUtils.getDateFormat(cal.getTime(), format);
    }

    /**
     * 获取指定月份的日期
     *
     * @param type
     *            0:之前；1：之后 num :月份数
     *
     */
    public static Date getMonthByNum(String type, int num) {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        if ("next".equals(type)) {
            cal.add(Calendar.MONTH, num);
        } else if ("last".equals(type)) {
            cal.add(Calendar.MONTH, 0-num);
        }
        return cal.getTime();
    }

    public static Date parseDate(String dateStr) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHssmm");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException ex) {
            return null;
        }
        return date;
    }

    public static Date getNextMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getLastMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date parseFormatDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date parseSimpleFormatDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static java.sql.Date parseSQLDate(String dateStr) {
        Date d = parseDate(dateStr);
        if (d != null) {
            java.sql.Date sq = new java.sql.Date(d.getTime());
            return sq;
        }
        return null;
    }

    public static java.sql.Timestamp string2Time(String dateString) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date timeDate;
        try {
            timeDate = sdf.parse(dateString);
            java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
            return dateTime;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * 格式化输出日期
     *
     * @param date
     *            Date
     * @return String
     */

    public static String toChar(Date date) {
        String formatDate = null;
        if (date != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            formatDate = sdf.format(date);
        } else {
            formatDate = "";
        }
        return formatDate;
    }

    public static String toCharStr(Date date) {
        String formatDate = null;
        if (date != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
            formatDate = sdf.format(date);
        } else {
            formatDate = "";
        }
        return formatDate;
    }

    public static String toDateTime(Date date) {
        String formatDate = null;
        if (date != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            formatDate = sdf.format(date);
        } else {
            formatDate = "";
        }
        return formatDate;
    }

    /**
     * 给定初始日期，及一个整数，返回初始日期＋整数的日期
     *
     * @param date
     *            Date
     * @param field
     *            String
     * @param dd
     *            int
     * @return Date
     */
    public static Date addDate(Date date, int field, int dd) throws Exception {
        return parseDate(addDate(toChar(date), field, dd), "yyyy-MM-dd");
    }

    public static List getBetweenDates(Date start, Date end) throws Exception {
        List list = new ArrayList();
        for (Date date = start; date.compareTo(end) <= 0; date = addDate(date, Calendar.DATE, 1)) {
            list.add(date);
        }
        return list;
    }

    // 获取月区间时间序列
    public static List getMonths(Date start, Date end) {
        List list = new ArrayList();
        Calendar c = Calendar.getInstance();
        Date endFirDay = DateUtils.parseDate(DateUtils.toChar(end).substring(0, 7) + "-01");
        Date endLastDay = null;
        try {
            endLastDay = DateUtils.addDate(DateUtils.addDate(endFirDay, Calendar.MONTH, 1), Calendar.DATE, -1);
            for (Date i = start; i.compareTo(endLastDay) <= 0; i = DateUtils.addDate(i, Calendar.MONTH, 1)) {
                list.add(DateUtils.toChar(i).substring(0, 7));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List getYears(Date start, Date end) {
        List list = new ArrayList();
        Calendar c = Calendar.getInstance();
        Date endFirDay = DateUtils.parseDate(DateUtils.toChar(end).substring(0, 7) + "-01");
        Date endLastDay = null;
        try {
            endLastDay = DateUtils.addDate(DateUtils.addDate(endFirDay, Calendar.MONTH, 1), Calendar.DATE, -1);
            for (Date i = start; i.compareTo(endLastDay) <= 0; i = DateUtils.addDate(i, Calendar.YEAR, 1)) {
                list.add(DateUtils.toChar(i).substring(0, 4));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List monthListDesc(String month1, String month2) throws Exception {
        ArrayList list = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1 = sdf.parse(month1, pos1);
        if (pos1.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month1 + "无效");
        }

        ParsePosition pos2 = new ParsePosition(0);
        Date date2 = sdf.parse(month2, pos2);
        if (pos2.getIndex() <= 0) {
            throw new Exception("指定的日期：" + month2 + "无效");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.set(Calendar.DATE, 1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.set(Calendar.DATE, 2);

        while (cal1.before(cal2)) {
            list.add(cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.DATE));
            cal2.add(Calendar.MONTH, -1);
        }
        return list;
    }

    private static Map short_months = null;

    static {
        short_months = new HashMap();
        short_months.put("Jan", "01");
        short_months.put("Feb", "02");
        short_months.put("Mar", "03");
        short_months.put("Apr", "04");
        short_months.put("May", "05");
        short_months.put("Jun", "06");
        short_months.put("Jul", "07");
        short_months.put("Aug", "08");
        short_months.put("Sep", "09");
        short_months.put("Oct", "10");
        short_months.put("Nov", "11");
        short_months.put("Dec", "12");
    }

    public static String parseYYYYMMDD(String cstDate) {
        if (cstDate == null || cstDate.equals("")) {
            // end by pp 2008/01/17
            return "";
        }
        String[] dff = cstDate.split(" ");

        String day = dff[2];
        String year = dff[5];
        String month_s = dff[1];

        String month = "";
        if (short_months.containsKey(month_s)) {
            month = short_months.get(month_s).toString();
        }

        String date = year + "-" + month + "-" + day;

        return date;
    }

    /**
     * 比较两个日期的大小 如果date1>date2,返回大于 0 的值 如果date1<date2,返回小于 0 的值
     * 如果date1=date2,返回等于 0 的值
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return -1;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.compareTo(cal2);
    }

    // 得到当前时间的long值，并同步
    public static synchronized long getTimeNum() {
        return System.currentTimeMillis();
    }

    // 是否闰年
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 获取指定账期的结束日期
    public static String getLastDayByCycle(String cycle) {
        int year = Integer.parseInt(cycle.substring(0, 4));
        int month = Integer.parseInt(cycle.substring(4));
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return cycle + 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return cycle + 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return cycle + 29;
            } else {
                return cycle + 28;
            }
        }
        return "输入账期格式错误，正确格式：200901";
    }

    /**
     * @methodName: getContinuousDay
     * @description 得到两日期相隔天数的数组
     * @date: 2010-5-18 下午02:56:49
     * @param startDate
     * @param endDate
     * @return
     */
    public static String[] getContinuousDay(Date startDate, Date endDate) {
        int days = daysBetween(startDate, endDate);
        String[] arr = new String[days + 1];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        arr[0] = dateFormat.format(calendar.getTime());
        for (int i = 1; i < days + 1; i++) {
            calendar.add(Calendar.DATE, 1);
            arr[i] = dateFormat.format(calendar.getTime());
        }
        return arr;
    }

    public static String millisecond() {
        Date dt = new Date();
        Long time = dt.getTime();
        String times = Long.toString(time);
        return times;
    }

    /**
     * @methodName: daysBetween
     * @description 得到两日期相差天数
     * @date: 2010-5-18 下午02:57:08
     * @param early
     * @param late
     * @return
     */
    public static int daysBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
     *
     * @param i
     * @return
     */
    public static Date getdate(int i) {
        Date dat = null;
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, i);
        dat = cd.getTime();
        return dat;
    }

    /**
     * @methodName: timeDiff
     * @description 得到两日期相差
     * @date: 2015-12-12 下午02:20:08
     * @param early
     * @param late
     * @return
     */
    public static String timeDiff(String time) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR) + "";

        long millionSeconds;
        try {
            long oldSeconds = sdf.parse(time).getTime();
            long nowSeconds = System.currentTimeMillis();
            millionSeconds = (nowSeconds - oldSeconds) / 60000;// 分钟
            if (millionSeconds < 5) {// 5分钟
                return "刚刚";
            } else if (millionSeconds <= 60) {// 1小时
                return millionSeconds + "分钟前";
            } else if (millionSeconds <= 60 * 24) {
                return millionSeconds / 60 + "小时前";
            } else if (millionSeconds <= 60 * 72) {
                return millionSeconds / 60 / 24 + "天前";
            } else if (millionSeconds > 60 * 72 && year.equals(time.substring(0, 4))) {
                return time.substring(5, 7) + "月" + time.substring(8, 10) + "日";
            } else {
                return time.substring(0, 10);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return time.substring(0, 10);
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getCurrentDayBeforeDate(-1, "yyyyMMdd"));
    }

    /*
     * 按日加
     *
     * @param value
     *
     * @return
     */
    public static Date addDay(int value) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_YEAR, value);
        return now.getTime();
    }

    /**
     * 返回最近7天内的所有日期
     *
     * @return
     */
    public static List<String> getLastSevenDaysList() {
        List<String> list = new ArrayList<String>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -8);// 把日期往后增加一天.整数往后推,负数往前移动
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
            list.add(DateUtils.getDateFormat(calendar.getTime(), "yyyy-MM-dd"));
        }
        return list;
    }

    /**
     * 返回最近7天内的所有日期
     *
     * @return
     */
    public static List<String> getLastSevenDaysListNoYears() {
        List<String> list = new ArrayList<String>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -8);// 把日期往后增加一天.整数往后推,负数往前移动
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
            list.add(DateUtils.getDateFormat(calendar.getTime(), "MM-dd"));
        }
        return list;
    }

    /**
     * 获取最近一个月内的所有日期
     *
     * @return
     */
    public static List<String> getLastMonthDaysList() {
        List<String> list = new ArrayList<String>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
        for (int i = 0; i < 30; i++) {
            System.out.println(i);
            calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
            list.add(DateUtils.getDateFormat(calendar.getTime(), "yyyy-MM-dd"));
        }
        return list;
    }

    /**
     * 获取最近一个月内的所有日期
     *
     * @return
     */
    public static List<String> getLastMonthDaysListNoYears() {
        List<String> list = new ArrayList<String>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
        for (int i = 0; i < 30; i++) {
            System.out.println(i);
            calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
            list.add(DateUtils.getDateFormat(calendar.getTime(), "MM-dd"));
        }
        return list;
    }

    /**
     * 返回指定月份内的所有日期
     *
     * @return
     */
    public static List<String> getMonthDaysList(String time) {
        List<String> list = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(time.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(time.substring(5, 7)) - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            list.add(time + "-" + (i < 10 ? "0" + i : i));
        }
        return list;
    }

    /**
     * 返回指定月份内的所有日期
     *
     * @return
     */
    public static List<String> getMonthDaysListNoYears(String time) {
        List<String> list = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        String tempMonth = time.substring(5, 7);
        calendar.set(Calendar.YEAR, Integer.parseInt(time.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(time.substring(5, 7)) - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            list.add(tempMonth + "-" + (i < 10 ? "0" + i : i));
        }
        return list;
    }

    /**
     * yzy
     * 判断当前时间是否在指定时间之内
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean checkTime(Date beginTime,Date endTime){
    	Date nowDate = new Date();
    	if(compareDate(beginTime,nowDate) <= 0 && compareDate(endTime, nowDate) >= 0){
    		return true;
    	}
		return false;
    }
    /**
     * yzy
     * 判断当前时间是否在指定时间之内(只比较时分秒)
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean checkTimeOnlyHHMM(Date beginTime,Date endTime){
    	Calendar nowCal = Calendar.getInstance();
    	nowCal.setTime(new Date());
    	Calendar beginCal = Calendar.getInstance();
    	beginCal.setTime(beginTime);
    	beginCal.set(Calendar.YEAR,nowCal.get(Calendar.YEAR));
    	beginCal.set(Calendar.MONTH,nowCal.get(Calendar.MONTH));
    	beginCal.set(Calendar.DAY_OF_MONTH,nowCal.get(Calendar.DAY_OF_MONTH));
    	Calendar endCal = Calendar.getInstance();
    	endCal.setTime(endTime);
    	endCal.set(Calendar.YEAR,nowCal.get(Calendar.YEAR));
    	endCal.set(Calendar.MONTH,nowCal.get(Calendar.MONTH));
    	endCal.set(Calendar.DAY_OF_MONTH,nowCal.get(Calendar.DAY_OF_MONTH));
    	if(beginCal.compareTo(nowCal) <=0 && endCal.compareTo(nowCal) >=0){
    		return true;
    	}
		return false;
    }

    /**
     * yzy
     * 比较beginTime是否大于endTime（只比较HHmmss）
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean compareTimeOnlyHHmm(Date beginTime,Date endTime){
    	Calendar nowCal = Calendar.getInstance();
    	nowCal.setTime(new Date());
    	Calendar beginCal = Calendar.getInstance();
    	beginCal.setTime(beginTime);
    	beginCal.set(Calendar.YEAR,nowCal.get(Calendar.YEAR));
    	beginCal.set(Calendar.MONTH,nowCal.get(Calendar.MONTH));
    	beginCal.set(Calendar.DAY_OF_MONTH,nowCal.get(Calendar.DAY_OF_MONTH));
    	Calendar endCal = Calendar.getInstance();
    	endCal.setTime(endTime);
    	endCal.set(Calendar.YEAR,nowCal.get(Calendar.YEAR));
    	endCal.set(Calendar.MONTH,nowCal.get(Calendar.MONTH));
    	endCal.set(Calendar.DAY_OF_MONTH,nowCal.get(Calendar.DAY_OF_MONTH));
    	//如果beginCal>endCal 返回true
    	if(beginCal.compareTo(endCal)>0){
    		return true;
    	}
		return false;
    }

//    /**
//     * yzy
//     * 将Date转为指定格式的String
//     * @param date
//     * @param format
//     * @return
//     */
//    public static String getFormatDate(Date date, String format) {
//    	System.out.println("要转换的时间："+date.toString());
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        System.out.println("转换后的时间："+sdf.format(date));
//        return sdf.format(date);
//    }
    /**
     * 获取当前日期的前一天的日期
     * @return
     */
    public static String getCurrentDayBeforeDate(int day,String format){
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, day);    //得到前一天
//		String  yestedayDate
//		= new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())
		return new SimpleDateFormat(format).format(calendar.getTime());
    }

}
