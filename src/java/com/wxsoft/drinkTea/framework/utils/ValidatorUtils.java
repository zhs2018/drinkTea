package com.wxsoft.drinkTea.framework.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <p>
* 数据验证工具类。
* </p>
*
* @author [作者]
* @version [版本号, 20130320]
*         修改记录 修改日期  修改者 修改内容 </br>
*         0        2013/03/25 yangcb 初稿
*/
public class ValidatorUtils {

    /**
     * <p>
     * 校验数据是否为空。
     * 空的情况下返回false。
     * </p>
     *
     * @param value 需要校验的数据
     * @return 校验结果
     */
    public static boolean isEmpty(Object value) {
    	if ( value instanceof List) {
    		if (((List<?>)value).isEmpty()) {
    			return true;
    		}
    	}
        if (value == null || String.valueOf(value).isEmpty() || "null".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 校验数据是否为整数。
     * 空的情况下返回true。
     * </p>
     *
     * @param value 需要校验的数据
     * @return 校验结果
     */
    public static boolean isInt(Object value) {
        if (isEmpty(value)) {
            return true;
        }
        String eL = "^-?\\d+$";
        boolean b = matcher(value, eL);
        return b;
    }
    /*
     * 验证固定电话为7到12位数字
     * */
    public static boolean isTelephone(Object value) {
        if (isEmpty(value)) {
            return true;
        }
        String eL = "^\\d{7,12}$";
        boolean b = matcher(value, eL);
        return b;
    }
    /**
     * <p>
     * 校验数据是否为整数。
     * 空的情况下返回true。
     * </p>
     * @param value 需要校验的数据
     * @return 校验结果
     */
    public static boolean isFloat(Object value) {
        if (isEmpty(value)) {
            return true;
        }
        String eL = "^(-?\\d+)(\\.\\d+)?$";
        boolean b = matcher(value, eL);
        return b;
    }

    /**
     * <p>
     * 校验数据是否为IP。
     * 空的情况下返回true。
     * </p>
     * @param value 需要校验的数据
     * @return 校验结果
     */
    public static boolean isIP(Object value) {
        if (isEmpty(value)) {
            return true;
        }
        String eL = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\."
                + "(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
        boolean b = matcher(value, eL);
        return b;
    }

    /**
     * 正则表达式匹配方法
     *
     * @param value 需要校验的数据
     * @param eL 正则表达式
     * @return 是否匹配
     */
    public static boolean matcher(Object value, String eL) {
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(String.valueOf(value));
        boolean b = m.matches();
        return b;
    }

    /**
     * <p>
     * 校验字符串最大长度。
     * </p>
     * @param value 字符串
     * @param maxlength 最大长度
     * @return 超出最大长度时返回false
     */
    public static boolean checkMaxLength(Object value, int maxlength) {
        if (isEmpty(value)) {
            return true;
        }

        byte[] bytes= value.toString().getBytes();
        return bytes.length > maxlength ? false: true;
    }

    /**
     * <p>
     * 校验字符串最小长度。
     * </p>
     * @param value 字符串
     * @param maxlength 最大长度
     * @return 超出最大长度时返回false
     */
    public static boolean checkMinLength(Object value, int maxlength) {
        if (isEmpty(value)) {
            return false;
        }
        byte[] bytes= value.toString().getBytes();
        return bytes.length < maxlength ? false: true;
    }

    /**
     * 手机号码校验
     * 空的情况下返回true
     * @param value 需要校验的数据
     * @return 校验结果
     */
    public static boolean isMobile(Object value) {
        if (isEmpty(value)) {
            return true;
        }
        String eL = "^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        boolean b = matcher(value, eL);
        return b;
    }
    /**
     * 身份证号码校验
     * 空的情况下返回true
     * @param value 需要校验的数据
     * @return 校验结果
     */
    public static boolean isIDCard(String value) {
        if (isEmpty(value)) {
            return true;
        }
        return IDCardUtil.isIDCard(value);
    }

    /**
     * 邮箱地址校验
     * 空的情况下返回true
     * @param value 需要校验的数据
     * @return 校验结果
     */
    public static boolean isEmail(Object value) {
        if (isEmpty(value)) {
            return true;
        }
        String eL = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        boolean b = matcher(value, eL);
        return b;
    }
    /**
     * 验证字符串str长度是否超过数据库中字段的指定长度len
     * @param str
     * @param len
     * @return
     */
    public static boolean checkLength(String str, int len) {

        if ("".equals(str.trim()) || str == null) {
            return true;
        }

        byte[] tempCharArray = str.getBytes();

        if (tempCharArray.length > len) {

            return false;
        }
        return true;
    }
    /**
     * 验证字符串str长度是否超过数据库中字段的指定长度len
     * @param str
     * @param len
     * @return
     */
    public static boolean checkLogin(String str) {
    	if ("".equals(str.trim()) || str == null) {
    		return true;
    	}
    	String reg="^[a-zA-Z0-9]{6,16}$";
    	return str.matches(reg);
    }

    /*   public static void main(String[] args) {
        System.out.println(checkLength("哈哈sadfasd1", 2));
        System.out.println(isNull(null));
        System.out.println(isNull(""));
        System.out.println(isInt(-1));
        System.out.println(isInt(null));
        System.out.println(isInt("ad"));
        System.out.println(isInt(-1.1));
        System.out.println(isFloat("ab"));
        System.out.println(isFloat("-1.1"));
        System.out.println(isFloat(1));
        System.out.println(isIP("1.1.1.1"));
        System.out.println(isIP("123.23.23.43"));
        System.out.println(isIP("123.23.23."));
        System.out.println(isIP("123.23.23"));
        System.out.println(isIP("123.23.231233@"));
    }*/
}
