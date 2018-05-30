package com.wxsoft.drinkTea.framework.utils;

import java.io.*;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.*;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信公众号工具类
 * @author Yzy
 *
 */
public class WeiXinUtils {
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

//	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
//		public boolean verify(String hostname, SSLSession session) {
//			return true;
//		}
//	}
	private static X509TrustManager myX509TrustManager = new TrustAnyTrustManager();
	/**
	 * 获取access_token
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	 * @param appid  id
	 * @param appsecret  密钥
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getAccessToken(String appid,String secret){
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> accessInfo = WeiXinInfo.ACCESS_TOKEN;
		long time = new Date().getTime();
		//检查已经存在的access_token是否过期
		if (accessInfo!=null && accessInfo.get("create_time") != null) {
			long createTime = Long.parseLong(accessInfo.get("create_time").trim());
			int sub = (int) ((time - createTime) / 1000);
			long life = Long.parseLong(accessInfo.get("expires_in").trim());
			if (sub < life-300) {
				return accessInfo;
			}
		}
		String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appid + "&secret=" + secret;
		String str_return = SendHttpsPOST(path, "");
		if(str_return.contains("access_token")){
			JSONObject obj = JSONObject.parseObject(str_return);
			String access_token = obj.getString("access_token");
			String expires_in = obj.getString("expires_in");
			map.put("access_token", access_token);
			map.put("expires_in", expires_in);
			map.put("create_time", time+"");
			WeiXinInfo.ACCESS_TOKEN = map;
		}
		System.out.println("获取到access_token:"+str_return);
		return map;
	}

	/**
	 *
	 * @param appid
	 * @param scope 获取用户信息的类型：snsapi_base  snsapi_userinfo
	 * @param path 重定向后的路径
	 * @param state 重定向后携带的参数
	 * @return code 如果所有传入参数正确返回code与state否则只返回state
	 */
	public static String getCodeRedirectPath(String appid, String scope, String path, String state){
		if(scope != null || "snsapi_base".equals(scope) || "snsapi_userinfo".equals(scope)){
		}else{
			scope="snsapi_base";
		}
		System.out.println("获取code的重定向地址:"+scope+"   path:"+path);
		@SuppressWarnings("deprecation")
		String uri = URLEncoder.encode(path);
		String redirectPath = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appid+"&redirect_uri="
						+uri+"&response_type=code&scope="
								+ scope+"&state="
								+ state	+ "#wechat_redirect";
		return "redirect:"+redirectPath;
	}
	/**
	 * 获取access_token与openid(网页授权时)
	 * @param appid  id
	 * @param asecret  密钥
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getAccessTokenAndOpenid(String appid,String secret,String code){
		Map<String, String> map = null;
		String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appid+"&secret="
						+ secret+"&code="
								+ code+"&grant_type=authorization_code";
		String str_return = SendHttpsPOST(path, "");
		System.out.println("获取openid："+str_return);
//		{
//		   "access_token":"ACCESS_TOKEN",
//		   "expires_in":7200,
//		   "refresh_token":"REFRESH_TOKEN",
//		   "openid":"OPENID",
//		   "scope":"SCOPE"
//		}
		if(str_return.contains("access_token")){
			JSONObject obj = JSONObject.parseObject(str_return);
			String access_token = obj.getString("access_token");
			String openid = obj.getString("openid");
			// String errcode = obj.getString("errcode");
			// String errmsg = obj.getString("errmsg");
			map = new HashMap<String, String>();
			map.put("access_token", access_token);
			map.put("openid", openid);
		}
		return map;
	}
	/**
	 * 获取jsapi_ticket
	 * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getJsAPITicket(String appid, String secret) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> ticketInfo = WeiXinInfo.JSAPI_TICKET;
		long time = new Date().getTime();
		//检查已经存在的jsapi_ticket是否过期
		if (ticketInfo.get("create_time") != null) {
			long createTime = Long.parseLong(ticketInfo.get("create_time").trim());
			int sub = (int) ((time - createTime) / 1000);
			long life = Long.parseLong(ticketInfo.get("expires_in").trim());
			if (sub >= life) {
				return ticketInfo;
			}
		}
		String access_token = getAccessToken(appid, secret).get("access_token");
		String path = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
				+ access_token + "&type=jsapi";
		String str_return = SendHttpsPOST(path, "");
		System.out.println(str_return);
		//判断AccessToken是否过时
		if(judgeFlushAccessToken(str_return)){
			return getJsAPITicket(appid, secret);
		}
		JSONObject obj = JSONObject.parseObject(str_return);
		// String errcode = obj.getString("errcode");
		// String errmsg = obj.getString("errmsg");
		String ticket = obj.getString("ticket");
		String expires_in = obj.getString("expires_in");
		// map.put("errcode", errcode);
		// map.put("errmsg", errmsg);
		map.put("ticket", ticket);
		map.put("expires_in", expires_in);
		map.put("create_time", time+"");
		WeiXinInfo.JSAPI_TICKET = map;
		return map;
	}
	/**
	 * 判断用户是否关注了公众号
	 * @param token
	 * @param openid
	 * @return
	 *
	 * 格式:
	 * {
	 *"subscribe":1,    1:关注    0  未关注
	 *"openid":"ojVGN1GwfCkwS9FZiNpa1VRs0ccI",
	 *"nickname":"----",
	 *"sex":1,
	 *"language":"zh_CN",
	 *"city":"济南",
	 *"province":"山东",
	 *"country":"中国",
	 *"headimgurl":"http://wx.qlogo.cn/mmopen/MPYKuKxB19zHgkriaGMMZQVoxJEzaa3KTOcQENnMkdc7FvUrUNnhCicQsBgwliaAW22eMJu4ZKdww5Axibc2gIVvEhvCDavM1VnY/0",
	 *"subscribe_time":1490586763,
	 *"remark":"",
	 *"groupid":0,
	 *"tagid_list":[

    ]
}
	 *
	 */
	public static Map<String, String> judgeIsFollow(String token,String openid){
			Map<String, String> map = null;
	        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openid+"&lang=zh_CN";
	        String jsonData = SendHttpsGet(url);
	        System.out.println(jsonData);
	        if(judgeFlushAccessToken(jsonData)){
				return judgeIsFollow(getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET).get("access_token"), openid);
			}
	        if(jsonData.contains("subscribe")){
		        JSONObject object = JSONObject.parseObject(jsonData);
		        map = new HashMap<>();
		        map.put("subscribe", object.getString("subscribe"));
		        map.put("openid", object.getString("openid"));
		        if(map.get("subscribe").equals("1")){
		        	map.put("nickname", object.getString("nickname"));
			        map.put("sex", object.getString("sex"));
			        map.put("headimgurl", object.getString("headimgurl"));
			        map.put("subscribe_time", object.getString("subscribe_time"));
		        }
	        }
	       return map;
	}

	/**
	 * 发送请求 Post
	 * @param path
	 *            请求的地址
	 * @param data
	 *            请求的数据
	 */

	public static String SendHttpsPOST(String path, String data) {
		StringBuffer sb = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, new TrustManager[] { myX509TrustManager },
					null);
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(path);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			// 当有数据需要提交时
			if (null != data) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(data.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/**
	 * 发送请求.Get
	 *
	 * @param path
	 *            请求的地址
	 * @param xmlStr
	 *            请求的数据
	 */

	public static String SendHttpsGet(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, new TrustManager[] { myX509TrustManager },
					null);
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(path);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("GET");

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 删除微信自定义菜单
	 * https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @return  {"errcode":0,"errmsg":"ok"}
	 */
	public static Map<String, String> delMenu(String accessToken) {
		String path = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accessToken;
		Map<String, String> map = null;
		String result = SendHttpsGet(path);
		System.out.println("删除自定义菜单：accessToken"+accessToken);
		System.out.println("返回结果："+result);
		if(judgeFlushAccessToken(result)){
			delMenu(getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET).get("access_token"));
		}
		if(result.contains("errcode")&&result.contains("errmsg")){
			map = new HashMap<>();
			JSONObject object = JSONObject.parseObject(result);
			String errcode = object.getString("errcode");
			String errmsg = object.getString("errmsg");
			map.put("errcode", errcode);
			map.put("errmsg", errmsg);
		}
		return map;
	}
	/**
	 * 创建微信自定义菜单
	 * http请求方式：POST（请使用https协议） https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
	click和view的请求示例
 	{
     "button":[
     {
          "type":"click",
          "name":"今日歌曲",
          "key":"V1001_TODAY_MUSIC"
      },
      {
           "name":"菜单",
           "sub_button":[
           {
               "type":"view",
               "name":"搜索",
               "url":"http://www.soso.com/"
            },
            {
               "type":"view",
               "name":"视频",
               "url":"http://v.qq.com/"
            },
            {
               "type":"click",
               "name":"赞一下我们",
               "key":"V1001_GOOD"
            }]
       }]
 }
	 * @param access_token
	 * @return  {"errcode":0,"errmsg":"ok"}
	 */
	public static Map<String, String> createMenu(String accessToken,String data) {
		String path = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
		Map<String, String> map = null;
		String result = SendHttpsPOST(path, data);
		System.out.println("生成自定义菜单accessToken:"+accessToken);
		System.out.println("修改内容："+data);
		System.out.println("返回结果："+result);
		if(judgeFlushAccessToken(result)){
			createMenu(getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET).get("access_token"),data);
		}
		if(result.contains("errcode")&&result.contains("errmsg")){
			map = new HashMap<>();
			JSONObject object = JSONObject.parseObject(result);
			String errcode = object.getString("errcode");
			String errmsg = object.getString("errmsg");
			map.put("errcode", errcode);
			map.put("errmsg", errmsg);
		}
		return map;
	}

	/**
	 * URL加密
	 * @param path
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String pathEncode(String path){
		try {
			return URLEncoder.encode(path,"hehe");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return URLEncoder.encode(path);
	}
	/**
	 * URL解密
	 * @param path
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String pathDecode(String path){
		try {
			return URLDecoder.decode(path, "hehe");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return URLDecoder.decode(path);
	}


	/**
	 * 发送客服消息
	 * 消息发送链接：https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
	 * 微信客服接口API：https://mp.weixin.qq.com/wiki/11/c88c270ae8935291626538f9c64bd123.html#.E5.AE.A2.E6.9C.8D.E6.8E.A5.E5.8F.A3-.E5.8F.91.E6.B6.88.E6.81.AF
	 * 例如发送图片消息：{
		    "touser":"OPENID",
		    "msgtype":"image",
		    "image":
		    {
		      "media_id":"MEDIA_ID"
		    }
		}
	 * @param accessToken 指定的accessToken
	 * @param data
	 * @return
	 */
	public static String sendKeFuMessage(String accessToken,String data){
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;
		return SendHttpsPOST(url, data);
	}

	public static String sendKeFuMessage(String appid,String secret,String data){
		Map<String, String> map = getAccessToken(appid, secret);
		if(map!=null){
			return sendKeFuMessage(map.get("access_token"), data);
		}else{
			return "error";
		}
	}


	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 *
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @return String url的响应信息返回值
	 * @throws IOException
	 */
	public String sendFileAsForm(String url, String filePath) throws IOException {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		 //设置关键值
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\";filelength=\"" + file.length() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}
		return result;
	}


	/**
	 * 上传临时素材
	 * 请求路径：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
	 * @param accessToken 凭证
	 * @param type 素材类型：媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
	 * @param path 路径
	 * @return
	 */
	public static String uploadMedia(String accessToken,String type,String path){
		String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type="+type;
		WeiXinUtils utils = new WeiXinUtils();
		try {
			String result =  utils.sendFileAsForm(url, path);
			if(judgeFlushAccessToken(result)){
				return uploadMedia(getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET).get("access_token"),type,path);
			}else{
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	/**
	 * 上传素材
	 * @param accessToken 凭证：通过appid与secret获取
	 * @param type 素材类型：媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
	 * @param path 路径
	 * @return
	 */
	public static String uploadMedia(String appid, String secret,String type,String path){
		Map<String, String> map = getAccessToken(appid, secret);
		if(map!=null){
			String result =  uploadMedia(map.get("access_token"), type, path);
			if(judgeFlushAccessToken(result)){
				return uploadMedia(getAccessToken(appid, secret).get("access_token"),type,path);
			}else{
				return result;
			}
		}else{
			return "error";
		}
	}

	/**
	 * 获取永久二维码
//	 * 临时：
//	 * 	http请求方式: POST
//		URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
//		POST数据格式：json
//		POST数据例子：{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
	 * 永久：
	 * http请求方式: POST
		URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
		POST数据格式：json
		POST数据例子：{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
		或者也可以使用以下POST数据创建字符串形式的二维码参数：
		{"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "123"}}}
	 * @param ACCESS_TOKEN
	 * @param data  scene_str对应的内容
	 * @return
	 *  正确的返回结果：{"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","expire_seconds":60,"url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
	 * 错误的返回结果：{"errcode":40013,"errmsg":"invalid appid"}
	 * @throws Exception
	 */
	public static Map<String, String> getQRCodePerpetual(String accessToken, String data) {
		Map<String, String> map = new HashMap<>();
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
		String postData = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+data
				+ "\"}}}";
		String result = SendHttpsPOST(url, postData);
		if(judgeFlushAccessToken(result)){
			return getQRCodePerpetual(WeiXinInfo.APPID, WeiXinInfo.APPSECRET,data);
		}
		System.out.println("获取二维码结果："+result);
		JSONObject object = JSONObject.parseObject(result);
		if(!result.contains("errcode")){
			map.put("ticket", object.getString("ticket"));
			map.put("expire_seconds", object.getString("expire_seconds"));
			map.put("url", object.getString("url"));
			return map;
		}
		return null;
	}
	public static Map<String, String> getQRCodePerpetual(String appid, String secret, String data) {
		Map<String, String> map = getAccessToken(appid, secret);
		if(map!=null){
			return getQRCodePerpetual(map.get("access_token"), data);
		}else{
			return null;
		}
	}

//	public static void main(String[] args) {
////		String appid = "wxba1e70ff8203476e";
////		String secret = "e57ebae3c297fa9abdd5ff687b0f7aae";
////		String openid = "ojVGN1GwfCkwS9FZiNpa1VRs0ccI";
////		System.out.println("开始");
////			String access = getAccessToken(appid, secret).get("access_token");
////			System.out.println("开始2");
////			Map<String, String> map= judgeIsFollow(access, openid);
////			Set<String> set = map.keySet();
////			for (String string : set) {
////				System.out.println(map.get(string));
////			}
//		System.out.println(judgeFlushAccessToken("{\"errcode\":42001,\"errmsg\":\"invalid appid\"}"));
//	}
	/**
	 * 判断微信返回结果对否是因为AccessToken的问题导致出错
	 * 传入数据格式：{"errcode":40013,"errmsg":"invalid appid"}
	 * 40014	不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
	 * 42001	access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明
	 * @param rersultStr
	 * @return
	 */
	private static boolean judgeFlushAccessToken(String rersultStr){
		if(rersultStr.contains("errcode") && rersultStr.contains("errmsg")){
			try {
				JSONObject object = JSONObject.parseObject(rersultStr);
				if(object.get("errcode") != null){
					String errcode = String.valueOf(object.get("errcode")).trim();
					if("40014".equals(errcode) || "42001".equals(errcode) ||"40001".equals(errcode)){
						WeiXinInfo.ACCESS_TOKEN = new HashMap<>(0);
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}



