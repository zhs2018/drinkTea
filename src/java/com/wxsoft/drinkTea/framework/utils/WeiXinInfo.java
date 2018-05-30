package com.wxsoft.drinkTea.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yzy
 *存放微信公众号的信息
 */
public class WeiXinInfo {
	public static String APPID;//微信公众号id
	public static String APPSECRET;//密钥
	public static String TOKEN = "tealeaf";//微信配置信息Token
	public static String GZ_HF = "hi!感谢您关注“采茶去茶客茶道”官方微信平台，我们在此已经等候多时！\n\n"+
			"最有韵味的茶道文化我们定点推送，全球行业牛人牛事都藏在劳哥的爆款文章里，等你来寻宝。\n\n"+
			"如果你有任何宝贵的建议和咨询，请别不好意思，我们之间随时交流！我们等您来\ue409";//微信关注回复消息

	public static Map<String,String> ACCESS_TOKEN = new HashMap<>(0);//包含三个字段：access_token(String)、expires_in、create_time
	public static Map<String, String> JSAPI_TICKET = new HashMap<>(0);//包含三个字段：jsapi_ticket、expires_in、create_time;
}
