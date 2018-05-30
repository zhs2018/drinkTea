package com.wxsoft.drinkTea.framework.utils;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * 向用户发送推广二维码的线程
 * @author Administrator
 *
 */
public class SendMessageToUserThread implements Runnable {
	private WebUser user;
	//二维码链接
	private String QRCode;
	public SendMessageToUserThread() {
	}
	public SendMessageToUserThread(String QRCode, WebUser user) {
		this.QRCode = QRCode;
		this.user = user;
	}
	@Override
	public void run() {
		System.out.println("二维码链接："+QRCode);
		try {
			String urlpath = PictureUtils.getPic(QRCode,
					user.getUserName(), "茶客茶道",
					DomainProperties.DOMAIN_WWW+"/image/fenxiangbg.png",
					user.getImage());
//			String sendUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="
//					+ HTTPSClient.getAccessToken(cc.getAppid(),
//							cc.getAppsecret(), cc.getCompanyId()).get(
//							"access_token") + "&type=image";
			String result = WeiXinUtils.uploadMedia(DomainProperties.WEIXIN_APPID, DomainProperties.WEIXIN_SECRET, "image", urlpath);
			System.out.println("更新素材："+result);
			JSONObject obj = JSONObject.parseObject(result);
			String media_id = obj.getString("media_id");
			WeiXinUtils.sendKeFuMessage(DomainProperties.WEIXIN_APPID, DomainProperties.WEIXIN_SECRET,
					"{\"touser\":\""
							+ user.getOpenId()
							+ "\",\"msgtype\":\"image\",\"image\":{\"media_id\":\""
							+ media_id + "\"}}"
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public WebUser getUser() {
		return user;
	}
	public void setUser(WebUser user) {
		this.user = user;
	}
}
