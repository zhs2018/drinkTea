package com.wxsoft.drinkTea.framework.autocreate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wxsoft.drinkTea.framework.utils.DateUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AutoCreate {
	private DataSource defaultDataSource;

	/**
	 * 通过表创建实体类、mapper.java、mapper.xml、service、serviceImpl,model
	 *
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) {

//		String packAge = "com/wxsoft/datamodel/system/";
		//String[] tables = { "sys_menu", "sys_role", "sys_role_resource", "sys_user", "sys_user_role" };
//		String packAge = "com/wxsoft/datamodel/user/";
//		String[] tables = { "user", "user_login_token" };
//		String packAge = "com/wxsoft/datamodel/street/";
//		String[] tables = { "street", "street_park_basic_info","street_park_economy_info" };
		String packAge = "com/wxsoft/drinkTea/datemodel/autocreate/";
//		String[] tables = { "company_contacts", "company_economy","company_overview","company_overview_energy","company_overview_energy_power","company_overview_environment","company_overview_environment_vocs","company_overview_safe","company_overview_safe_chemical","company_problem","company_project","company_project_illega" };
//		String packAge = "com/wxsoft/datamodel/sysconst/";
//		String[] tables = { "const_def" };
//		String packAge = "com/wxsoft/datamodel/notice/";
//		String[] tables = { "notice" };
//		String packAge = "com/wxsoft/datamodel/policy/";
//		String[] tables = { "policy" };
//		String packAge = "com/wxsoft/datamodel/news/";
//		String[] tables = { "news" };
		String[] tables = { "cash_desc"};
		try {
			for (String table : tables) {
				new AutoCreate().createAllThing(packAge, table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 母体类
	 *
	 * @param packAge
	 * @param table
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createAllThing(String packAge, String table) throws SQLException, IOException, URISyntaxException {
		Logger logger = Logger.getLogger(this.getClass());
		String path = System.getProperty("user.dir");// 获取项目根目录
		logger.info("获取项目目录：" + path);
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/spring-context.xml");
		defaultDataSource = (DataSource) context.getBean("defaultDataSource");
		Connection conn = defaultDataSource.getConnection();
		Statement stmt = conn.createStatement();
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet pkRSet = dbMeta.getPrimaryKeys(null, null, table);
		String pk_cloum = "";
		while (pkRSet.next()) {
			System.err.println("****** Comment ******");
			System.err.println("TABLE_CAT : " + pkRSet.getObject(1));
			System.err.println("TABLE_SCHEM: " + pkRSet.getObject(2));
			System.err.println("TABLE_NAME : " + pkRSet.getObject(3));
			System.err.println("COLUMN_NAME: " + pkRSet.getObject(4));
			System.err.println("KEY_SEQ : " + pkRSet.getObject(5));
			System.err.println("PK_NAME : " + pkRSet.getObject(6));
			System.err.println("****** ******* ******");
			try {
				pk_cloum = pkRSet.getObject(4).toString();

			} catch (Exception e) {
				// Auto-generated catch block
				logger.info("------error:请设置主键------");

				e.printStackTrace();
			}
		}

		ResultSet rs2 = stmt.executeQuery("show full COLUMNS from " + table);
		List<String> comments = new ArrayList<String>();
		while (rs2.next()) {
			comments.add(rs2.getString("Comment"));
		}

		ResultSet rs = stmt.executeQuery("select * from " + table + " where 0=1");
		ResultSetMetaData md = rs.getMetaData();
		logger.info("初始化表结构");

		String className = toUpperCaseFirstOne(toProperty(table));// 把表明转成类名

		logger.info("开始生成Class");
		createClass(path, packAge, table, className, md, comments, true);// 生成实体类
		logger.info("开始生成mapper.xml");
		createMapperFile(path, packAge, table, className, md, pk_cloum);//
		// 生成mapperx.xml
		logger.info("开始生成MapperClass");
		createMapperClass(path, packAge, table, className);// 生成MapperClass
		//logger.info("开始生成ServiceClass");
		//createServiceClass(path, packAge, table, className);// 生成ServiceClass
		//logger.info("开始生成createServiceImplClass");
		//createServiceImplClass(path, packAge, table, className);//
		// 生成createServiceImplClass
		//
		// DOM4JForXml handleXml = new DOM4JForXml();
		// try {
		// logger.info("为Sql-config生成配置文件");
		// handleXml.paseMyBatitsXml(
		// path,
		// className,
		// packAge.replaceAll("\\/", ".").substring(0,
		// packAge.length() - 1)
		// + ".model");
		// logger.info("为ApplicationContext生成配置文件");
		// handleXml.paseSpringXml(
		// path,
		// className,
		// packAge.replaceAll("\\/", ".").substring(0,
		// packAge.length() - 1)
		// + ".service.impl");
		// } catch (TransformerException e) {
		// e.printStackTrace();
		// }
		System.out.println("chenggong");
		logger.info("成功，请刷新项目");
		// rs.close();
		// stmt.close();
		// conn.close();
	}

	/**
	 * 创建MapperClass
	 *
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createMapperClass(String path, String packAge, String table, String className)
			throws SQLException, IOException, URISyntaxException {
		// String path = Tool.class.getResource("/").toString().l;
		path += Common.SRC_JAVA;
		packAge += Common.MAPPER;
    System.out.println("1555468");
		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(AutoCreate.class.getResource("").toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("mapper.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put("PACKAGE_URL", packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1));

		// 类注释
		root.put("CLASS", className);
		root.put("PACKAGE", packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1).replace("mapper", "model")
				+ "." + className + ";");
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", DateUtils.toChar(new Date()));
		root.put("CLASSPATH",
				packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1) + "." + className + "Mapper;");

		fileWrite(temp, path + packAge + className + "Mapper.java", root);
	}

	/**
	 * 创建createServiceImplClass
	 *
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */

public void createServiceImplClass(String path, String packAge, String table, String className)
			throws SQLException, IOException, URISyntaxException {

		path += Common.SRC_JAVA;
		packAge += Common.SERVICE_IMPL;
		String pageNow = packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1);


		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(AutoCreate.class.getResource("").toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template temp = cfg.getTemplate("serviceImpl.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put("PACKAGE_URL", packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1));
            System.out.println("閙i擦我");
		// 类注释
		root.put("CLASS", className);
		root.put("PACKAGE",
				packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1).replace("service", "model") + "."
						+ className + ";");
		root.put("PACKAGE1", pageNow.replace("service", "model").replace(".impl", "") + "." + className + ";");
		root.put("PACKAGE2", pageNow.replace("service", "mapper").replace(".impl", "") + "." + className + "Mapper;");
		root.put("PACKAGE3", pageNow.replace(".impl", "") + "." + className + "Service;");
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", DateUtils.toChar(new Date()));
		root.put("CLASSPATH",
				packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1) + "." + className + "ServiceImpl;");

		// service和serviceimpl文件只有不存在的情况下才创建，否则是不覆盖的
		File file = new File(path + packAge + className + "ServiceImpl.java");
		// if (!file.exists()) {
		fileWrite(temp, path + packAge + className + "ServiceImpl.java", root);
		// }
	}


	/**
	 * 创建Mapper
	 *
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createServiceClass(String path, String packAge, String table, String className)
			throws SQLException, IOException, URISyntaxException {

		// String path = Tool.class.getResource("/").toString().l;
		path += Common.SRC_JAVA;
		packAge += Common.SERVICE;
		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(AutoCreate.class.getResource("").toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("service.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put("PACKAGE_URL", packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1));

		// 类注释
		root.put("CLASS", className);
		root.put("PACKAGE",
				packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1).replace("service", "model") + "."
						+ className + ";");
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", DateUtils.toChar(new Date()));
		root.put("CLASSPATH",
				packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1) + "." + className + "Service;");
		// service和serviceimpl文件只有不存在的情况下才创建，否则是不覆盖的
		File file = new File(path + packAge + className + "Service.java");
		// if (!file.exists()) {
		fileWrite(temp, path + packAge + className + "Service.java", root);
		// }
	}

	/**
	 * 创建实体类
	 *
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createClass(String path, String packAge, String table, String className, ResultSetMetaData md,
			List<String> comments, boolean CommonPage) throws SQLException, IOException, URISyntaxException {
		path += Common.SRC_JAVA;
		packAge += Common.MODEL;

		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(AutoCreate.class.getResource("").toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("class.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put("PACKAGE_URL", packAge.replaceAll("\\/", ".").substring(0, packAge.length() - 1));

		// 类注释
		root.put("CLASS", className);
		root.put("PACKAGE", packAge);
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", DateUtils.toChar(new Date()));

		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();

		int count = md.getColumnCount();
		for (int i = 1; i <= count; i++) {
			Map<String, String> currency = new HashMap<String, String>();
			String name = toProperty(md.getColumnName(i));
			currency.put("name", name);
			currency.put("type", endP(md.getColumnClassName(i)));
			currency.put("comment", comments.get(i - 1));
			properties.add(currency);
			// 添加in用到的字段
			if (name.toLowerCase().indexOf("status") > -1) {
				currency = new HashMap<String, String>();
				currency.put("name", name + "ForIn");
				currency.put("type", "String");
				currency.put("comment", comments.get(i - 1) + "（用于in条件）");
				properties.add(currency);
			}
		}
		if (CommonPage) {
			Map<String, String> currency = new HashMap<String, String>();
			currency.put("name", "page");
			currency.put("type", "CommonPage");
			currency.put("comment", "普通分页");
			properties.add(currency);
			// 引包
			root.put("import_URL",
					"import java.util.Date;\nimport com.wxsoft.framework.base.BaseBean;\nimport com.wxsoft.framework.mybatis.plugin.CommonPage;\nimport java.math.BigDecimal;\n");
		}
		// if (AjaxPage) {
		// Map<String, String> currency = new HashMap<String, String>();
		// currency.put("name", "ajaxPage");
		// currency.put("type", "AjaxPage");
		// currency.put("comment", "ajax分页");
		// properties.add(currency);
		// // 引包
		// root.put("import_URL",
		// "import java.util.Date;\nimport
		// com.wxsoft.framework.base.BaseBean;\nimport
		// com.wxsoft.framework.mybatis.plugin.CommonPage;\nimport
		// com.wxsoft.framework.mybatis.plugin.AjaxPage;\nimport
		// java.math.BigDecimal;\n");
		// }
		root.put("properties", properties);

		fileWrite(temp, path + packAge + className + ".java", root);

	}

	/**
	 * 创建映射实体文件Mapp.xml
	 *
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	// @Test
	public void createMapperFile(String path, String packAge, String table, String className, ResultSetMetaData md,
			String PK_colum) throws SQLException, IOException, URISyntaxException {
		path += Common.SRC_JAVA;
		packAge += Common.MAPPER;

		String selectALl = getSelectAll(table, md);

		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(AutoCreate.class.getResource("").toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("mybatis.ftl");

		Map<String, Object> root = new HashMap<String, Object>();

		root.put("selectALl", selectALl);
		root.put("namespace", packAge.replaceAll("\\/", ".").substring(0, packAge.length()) + className);
		root.put("modelpath", (packAge.replace(Common.MAPPER, "") + Common.MODEL).replaceAll("\\/", "."));
		root.put("table", table.toLowerCase());
		root.put("className", className);
		root.put("PK_colum", PK_colum.toUpperCase());
		root.put("PRIMARY", toProperty(PK_colum));

		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();
		int count = md.getColumnCount();
		for (int i = 1; i <= count; i++) {
			Map<String, String> currency = new HashMap<String, String>();
			String name = toProperty(md.getColumnName(i));
			currency.put("property", name);
			currency.put("column", md.getColumnName(i).toUpperCase());
			properties.add(currency);

			// 添加in用到的字段
			if (name.toLowerCase().indexOf("status") > -1) {
				currency = new HashMap<String, String>();
				currency.put("property", name + "ForIn");
				currency.put("column", md.getColumnName(i).toUpperCase());
				properties.add(currency);
			}
		}

		root.put("properties", properties);
		fileWrite(temp, path + packAge + className + "Mapper.xml", root);
		// fileWrite(temp, path + className + ".xml", root);
	}

	/**
	 * 返回查询所有咧
	 *
	 * @param table
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getSelectAll(String table, ResultSetMetaData md) throws SQLException, IOException {
		String result = "";
		int count = md.getColumnCount();
		for (int i = 1; i <= count; i++) {
			result += "tmp.`" + md.getColumnName(i).toUpperCase() + "`" + ",";
		}
		return result.substring(0, result.length() - 1);

	}

	public static String firstLow(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public String toProperty(String col) {
		col = col.toLowerCase();
		String[] ss = col.split("_");
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (String s : ss) {
			if (first)
				sb.append(s);
			else
				sb.append(s.substring(0, 1).toUpperCase() + s.substring(1));
			first = false;
		}
		return sb.toString();
	}

	public String endP(String type) {
		if (type.substring(type.lastIndexOf(".") + 1).equals("Timestamp")) {
			return "Date";
		} else {
			return type.substring(type.lastIndexOf(".") + 1);
		}
	}

	public static String toLowerCaseFirstOne(String s) {
		System.out.println("dno9ah");
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public void fileWrite(Template temp, String filename, Map<String, Object> root) {
		File file = new File(filename);

		FileOutputStream out;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new FileOutputStream(filename);
			try {
				temp.process(root, new OutputStreamWriter(out));
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

