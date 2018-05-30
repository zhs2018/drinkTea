package com.wxsoft.drinkTea.framework.utils;

public class ConvertPassword {
	
	private final static String KEY_START = "52@$#&(*@!$%$^$^%&^%$";
	
	private final static String KEY_END = "dfdsfqwewq23$!#$@$#^$%^@@!!#$$";
	
	private final static String USER_KEY_START = "rewq3";
	
	private final static String USER_KEY_END = "22%@@!!$$";
	
	
	/**
	 * 传入明文password，返回加密后信息
	 * @param password
	 * @return
	 */
	public static String getMyPassword(String password) {
		String key = KEY_START + password.trim() + KEY_END;
		return Md5Encrypt.md5(key);
	}
	
	/**
	 * 获取用户信息被转换后的内容
	 * @param userName
	 * @return
	 */
	public static String getLoginUserConvert(String userName) {
		String key = USER_KEY_START + userName.trim() + USER_KEY_END + System.currentTimeMillis();
		return Md5Encrypt.md5(key);
	}
	
	public static void main(String []args) {
		System.out.println(getMyPassword("admin"));
		System.out.println(getLoginUserConvert("admin"));
	}

}
