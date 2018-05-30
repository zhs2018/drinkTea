/**
 * @文件名称: BaseController.java
 * @类路径: com.wxltsoft.framework.controller
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-15 上午10:07:47
 */

package com.wxsoft.drinkTea.framework.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.utils.ExceptionProcessUtils;
import com.wxsoft.drinkTea.framework.utils.Tools;


/**
 * @类功能说明：controller基类
 * @类修改者：kasiait
 * @修改日期：2013-3-15
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-15 上午10:07:47
 */

public class BaseController {
	public final static String SYS_DEFAULT_CHARSET            = "utf-8";
    public final static String SYS_RESPONSE_CONTENT_TYPE      = "text/html;charset=UTF-8";
    public final static String SYS_RESPONSE_CONTENT_JSON_TYPE = "application/json;charset=UTF-8";
    public final static byte[] EMPTY_BYTES = new byte[0];

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
     * 反馈没有这个这个Event
     * @param response
     * @throws UnsupportedEncodingException
     */
    protected void responseNoEvent(HttpServletResponse response) throws UnsupportedEncodingException {
        responseAjax(getNoEventResult() , response);
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

    /**
     * 获取一个通用result
     * @param result
     * @param isSuccess
     * @param count
     * @return
     */
    protected JSONObject getResult(Object result , boolean isSuccess , Integer count) {
        return isSuccess ? getSuccessResult(result , count) : getFailureResult(result , count);
    }

    /**
     * 获取一个通用result
     * @param result
     * @param isSuccess
     * @return
     */
    protected JSONObject getResult(Object result , boolean isSuccess) {
        return isSuccess ? getSuccessResult(result) : getFailureResult(result);
    }

    /**
     * 重载方法
     * @param result
     * @param count
     * @return
     */
    protected JSONObject getSuccessResult(Object result , Integer count) {
        JSONObject jsonObject = getSuccessResult(result);
        jsonObject.put("count" , count);
        return jsonObject;
    }

    /**
     * 获取成功的Result
     * @param result
     * @return
     */
    protected JSONObject getSuccessResult(Object result) {
        JSONObject object = createResult(true);
        object.put("root" , result);
        return object;
    }

    /**
     * 获取失败的Reuslt
     * @param result
     * @return
     */
    protected JSONObject getFailureResult(Object result) {
        JSONObject object = createResult(false);
        object.put("root" , result);
        return object;
    }

    /**
     * 获取失败的Reuslt
     * @param result
     * @param count
     * @return
     */
    protected JSONObject getFailureResult(Object result , Integer count) {
        JSONObject jsonObject = getFailureResult(result);
        jsonObject.put("count" , count);
        return jsonObject;
    }

    /**
     * 无相关event的结果
     * @return
     */
    protected JSONObject getNoEventResult() {
        return getFailureResult("请求的参数无效，被服务器端拒绝");
    }

    /**
     * 根据参数得到一个返回值
     * @param isSuccess
     * @return
     */
    private JSONObject createResult(boolean isSuccess) {
        JSONObject object = new JSONObject();
        if(isSuccess) {
            object.put("success" , true);
            object.put("failure" , false);
        }else {
            object.put("success" , false);
            object.put("failure" , true);
        }
        return object;
    }

    /**
     * deode encoded params, return null , if string is blank.
     * @param str
     * @return
     */
    public String decode(String str) {
        String result = null;
        if (Tools.notEmpty(str)) {
            try {
                result = URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }

    /**
     *
     * @描述: java转义html form表单提交的数据
     * @作者: kasiaits
     * @日期:2013-3-22
     * @修改内容
     * @参数： @param str
     * @参数： @return
     * @return String
     * @throws
     */
    public String htmlspecialchars(String str) {
    	str = str.replaceAll("&", "&amp;");
    	str = str.replaceAll("<", "&lt;");
    	str = str.replaceAll(">", "&gt;");
    	str = str.replaceAll("\"", "&quot;");
    	return str;
    }
}
