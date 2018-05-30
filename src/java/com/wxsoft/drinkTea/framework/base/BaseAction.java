package com.wxsoft.drinkTea.framework.base;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.utils.ExceptionProcessUtils;
import com.wxsoft.drinkTea.framework.constant.SessionDef;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.FileUtils;
import com.wxsoft.drinkTea.framework.utils.StringUtils;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;

/**
 * <p>
 * action层基础类 提供共通方法和变量，统一规范化使用。
 * </p>
 */
public abstract class BaseAction implements Serializable {
	private static final long serialVersionUID = -1692998413485224912L;

	public final static String SYS_DEFAULT_CHARSET            = "utf-8";
    public final static String SYS_RESPONSE_CONTENT_TYPE      = "text/html;charset=UTF-8";
    public final static String SYS_RESPONSE_CONTENT_JSON_TYPE = "application/json;charset=UTF-8";
    public final static byte[] EMPTY_BYTES = new byte[0];


	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	protected MessageSource message;

	/**
	 * 获取CustomParamFilter对应的自适应配置参数（根据二级域名不同自动匹配）
	 *
	 * @return
	 */
	public void setMessage(MessageSource message) {
		this.message = message;
	}

	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public String getMessage(String key, String... param) {
		return message.getMessage(key, param, key, null);
	}

	public String getWebRootPath() {
		// String path = request.getContextPath();
		// String basePath =
		// request.getScheme() + "://" + request.getServerName() + ":" +
		// request.getServerPort() + path + "/";
		// return basePath;
		return DomainProperties.DOMAIN_DEFAULT + "/";
	}

	/**
	 * 获取当前访问的URL地址
	 *
	 * @return
	 */
	public String getCurrentUrl() {
		String url = request.getScheme() + "://" + request.getServerName();

		if (80 != request.getServerPort()) {

			url += ":" + request.getServerPort();
		}
		url += request.getContextPath() + request.getServletPath() + "?" + request.getQueryString();
		return url;
	}

	/**
	 * 获取当前访问的URL地址
	 *
	 * @return
	 */
	public String getVisitUrl() {
		return request.getHeader("referer");
	}

	public HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	public HttpServletRequest getRequest() {
		// return ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	public String getVisitIp() {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 转换成字符串
	 *
	 * @param object
	 */
	public static String toString(Object object) {
		return StringUtils.toString(object);
	}

	/**
	 * 去空格
	 *
	 * @param object
	 */
	public static String trim(String object) {
		return StringUtils.trim(object);
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
		return StringUtils.toString(object, defaultValue);
	}

	/**
	 * 获取当前登陆用户ID
	 *
	 * @return
	 */
	public Integer getLoginUserId() {

		SysUser logininfo = getLoginUser();
		if (logininfo != null) {
			int loginUserId = logininfo.getId();
			return loginUserId;
		}
		return null;
	}

	/**
	 * 获取当前登陆用户信息
	 *
	 * @return
	 */
	public SysUser getLoginUser() {

		return (SysUser) this.getSession().getAttribute(SessionDef.LOGIN_USER_INFO);
	}

	/**
	 * 获取当前系统管理登陆用户ID
	 *
	 * @return
	 */
	public int getSystemLoginUserId() {

		SysUser logininfo = getSystemLoginUser();
		if (logininfo != null) {
			int loginUserId = logininfo.getId();
			return loginUserId;
		}
		return -1;
	}

	/**
	 * 获取当前系统管理登陆用户信息
	 *
	 * @return
	 */
	public SysUser getSystemLoginUser() {

		return (SysUser) this.getSession().getAttribute(SessionDef.SYS_LOGIN_USER_INFO);
	}

	protected JSONObject jsonNormal(Object obj) {
		JSONObject json = new JSONObject();
		json.put("result", "0");
		json.put("message", obj);
		return json;
	}

	protected JSONObject jsonNormal() {
		return this.jsonNormal("0");
	}

	protected JSONObject jsonError(Object obj) {
		JSONObject json = new JSONObject();
		json.put("result", "1");
		json.put("message", obj);
		return json;
	}

	/**
	 * 保存文件
	 *
	 * @param fileList
	 * @param pathKey
	 * @return
	 * @throws Exception
	 */
	protected String saveFiles(List<FileItem> fileList) throws Exception {
		String imger;
		String imgName = null;

		if (fileList != null && !fileList.isEmpty()) {

			for (FileItem item : fileList) {
				if (item.getSize() != 0) {
					imger = FileUtils.getFileNameByPath(item.getName());
					String fileName = imger.substring(0, imger.lastIndexOf("."));
					String str2 = imger.substring(imger.lastIndexOf("."));
					imgName = fileName + DateUtils.getCurrentDateFormat("yyyyMMddhhmmss") + str2;
					item.write(new File(FileUtils.getAbsolutePath(FileUtils.getImgRelativePath()) + "/" + imgName));
				}
			}
		}
		if (imgName != null) {
			imgName = FileUtils.getAbsolutePath(FileUtils.getImgRelativePath()) + "/" + imgName;

		} else {
			imgName = "";
		}
		return imgName;
	}


	 /**
     * response二进制数据
     * @param bytes
     * @param response
     */
    protected void responseByte(byte []bytes , HttpServletResponse response) {
        try {
            if(bytes == null || bytes.length == 0) return;
            response.setContentType(SYS_RESPONSE_CONTENT_TYPE);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        }catch(Exception e) {}
    }

    /**
     * 输出字符集设置
     * @param string 输出字符串
     * @param response 输出对象
     * @throws UnsupportedEncodingException 编码异常输出
     */
    protected void responseString(String string , HttpServletResponse response) throws UnsupportedEncodingException {
        if(string == null) responseByte(EMPTY_BYTES , response);
        else responseByte(string.getBytes(SYS_DEFAULT_CHARSET) , response);
    }

    /**
     * 输出Ajax请求
     * @param object
     * @param response
     * @throws UnsupportedEncodingException
     */
	protected void responseAjax(Object object , HttpServletResponse response) throws UnsupportedEncodingException {
        if(object == null) responseByte(EMPTY_BYTES , response);
        else if(object instanceof JSONObject || object instanceof JSONArray) responseAjax(object.toString() , response);
        else if(object instanceof Map) responseString(JSONObject.toJSONString(object) , response);
        else if(object instanceof List) responseString(JSONArray.toJSONString(object) , response);
        else if(object instanceof String) responseString((String)object , response);
        else if(object instanceof Throwable) responseString(ExceptionProcessUtils.getStack((Throwable)object) , response);
        else responseString(JSONObject.toJSONString(object) , response);
    }
}
