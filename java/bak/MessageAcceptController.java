package com.wxsoft.drinkTea.platform.weixin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.SHA1Utils;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.XMLUtils;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserFriend;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.service.UserFriendService;

@Controller
@RequestMapping("weixin")
public class MessageAcceptController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private UserFriendService userFriendSwervice;

	/**
	 * 验证微信
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param response
	 */
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public void mainGet(@RequestParam String signature,
			@RequestParam String timestamp, @RequestParam String nonce,
			@RequestParam String echostr, HttpServletResponse response) {
		// signature ; 微信加密签名 timestamp; 时间戳 nonce 随机数
		// 验证
		logger.info("验证微信配置：/n signature="+signature + "--nonce==" + nonce + "--echostr==" + echostr + "--timestamp=="
				+ timestamp);
		String token = WeiXinInfo.TOKEN;
		String[] str = { token, timestamp, nonce };
		Arrays.sort(str);
		String bigStr = str[0] + str[1] + str[2];
		String digest = new SHA1Utils().getDigestOfString(bigStr.getBytes())
				.toLowerCase();
		logger.info("自己加密算法后的signature："+digest);
		if (digest.equals(signature)) {
			try {
				response.getWriter().write(echostr);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public void mainPost(HttpServletResponse response,HttpServletRequest request){
		logger.info("微信发来消息");
		response.setContentType("text/xml");
		Map<String, String> map = null;
		StringBuffer sb = new StringBuffer();
		String path = request.getScheme() + "://" + request.getServerName();// 获取服务器路径
		try {
			String line;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			logger.info("获取到内容为："+sb.toString());
			map = XMLUtils.toMap(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String MsgType = map.get("xml.MsgType");//消息类型
		sb  = new StringBuffer();
		switch (MsgType) {
		case "event"://事件推送
			sb = handleEvent(map);
			break;
		}
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(sb.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//处理事件类型的消息
	private StringBuffer handleEvent(Map<String, String> map){
		StringBuffer sb = new StringBuffer();
		//关注事件
		if("subscribe".equals(map.get("xml.Event"))){
			//扫描带参数的二维码关注的
			if(map.get("xml.EventKey")!=null && map.get("xml.EventKey").contains("qrscene_")){
				String sId = map.get("xml.EventKey").split("_")[1];
				try {
					Integer id = Integer.parseInt(sId);
					WebUser user = webUserMapper.selectByPrimaryKey(id);
					WebUser user2 = new WebUser();
					user2.setOpenId(map.get("xml.FromUserName"));
					user2 = webUserMapper.selectBy(user2);
					Integer count = null;
					if(user != null){
						if(user2!=null){//两个用户都已经关注过
							 count = userFriendSwervice.save2User(user,user2);
						}else{
							user2 = new WebUser();
							user2.setOpenId(map.get("xml.FromUserName"));
							user2.setSysUserId(user.getSysUserId());
							user2.setCreatetime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
							count = userFriendSwervice.saveUserAndFriend(user,user2);
						}
					}
					if(count!=null && count == 1){
						logger.info("用户保存成功，好友添加成功");
					}else{
						logger.info("好友添加失败");
					}
				} catch (NumberFormatException e) {
					logger.info("用户的id转换异常："+sId);
					e.printStackTrace();
				}
			}
		}
		String str = WeiXinInfo.GZ_HF;
		sb.append(
				"<xml><ToUserName><![CDATA[" + map.get("xml.FromUserName")
						+ "]]></ToUserName>")
				.append("<FromUserName><![CDATA[" + map.get("xml.ToUserName")
						+ "]]></FromUserName>")
				.append("<CreateTime>" + System.currentTimeMillis()
						+ "</CreateTime>")
				.append("<MsgType><![CDATA[text]]></MsgType>")
				.append("<Content><![CDATA[" + str + "]]></Content>")
				.append("</xml> ");
		return sb;
	}
}
