package com.wxsoft.drinkTea.framework.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

public class SCHOOLMGRDEF {
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * 上传文件合法后缀名
	 */
	public static List<String> fileSuffix = new ArrayList<String>();	
	static
	{
		fileSuffix.add("jpg");
		fileSuffix.add("png");
	}
	
	/**
	 * 上传文件合法文件头
	 */
	public static List<String> fileMagicNumber = new ArrayList<String>();	
	static
	{
		fileMagicNumber.add("ffd8ffe0");
		fileMagicNumber.add("89504E47");
	}
	
	

}
