package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.SendMessageToUserThread;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
import com.wxsoft.drinkTea.platform.system.messageCenter.mapper.MessageCenterMapper;
import com.wxsoft.drinkTea.platform.system.messageCenter.model.MessageCenter;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.weixin.mapper.MessageReadingMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserFriendMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.MessageReading;
import com.wxsoft.drinkTea.platform.weixin.model.UserFriend;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.service.PersonalCenterService;
import com.wxsoft.drinkTea.platform.weixin.service.UserFriendService;
import com.wxsoft.drinkTea.platform.weixin.service.WebUserService;

/**
 *
 * @author yzy 用户中心
 *
 */
@RequestMapping("/")
@Controller
public class PersonalCenter extends BaseAction {

	/**
	 * @author lzj @描述：跳转到个人中心、 @时间：2017-4-18 14:03
	 * @修改时间 2017.4.24
	 * @修改内容 在个人中心判断用户是否关注（用户可直接访问用户中心）
	 * @修改人 yzy
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductOrderMapper productOrderMapper;

	@Autowired
	private WebUserMapper webUserMapper;

	@Autowired
	private WebUserService webUserService;

	@Autowired
	private UserFriendService userFriendService;
	@Autowired
	private UserFriendMapper userFriendMapper;

	@Autowired
	private PersonalCenterService personalCenterService;

	@Autowired
	private MessageReadingMapper messageReadingMapper;

	@Autowired
	private MessageCenterMapper messageCenterMapper;



	@RequestMapping("/personalCenter")
	public ModelAndView jumpPersonalCenter(HttpSession session) {
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		// 已经登录过
		if (user != null) {
			// 用户已经关注过，并保存了openid信息
			if (user.getOpenId() != null && !"".equals(user.getOpenId().trim())) {
				logger.info("数据库里面有openID存到session中");
				session.setAttribute("openid", user.getOpenId());
				session.removeAttribute("user");
				return jumpPersonalCenter(session);
			} else {
				logger.info("数据库里面有用户信息但是没有openid");
				session.removeAttribute("user");
				return jumpPersonalCenter(session);
			}
		} else {
			// 获取openid
			String openId = (String) session.getAttribute("openid");
			if (openId == null || "".equals(openId)) {
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/focus?path=personalCenter");
				return modelAndView;
			}
			// 判断 用户是否关注
			Map<String, String> result = WeiXinUtils
					.judgeIsFollow(WeiXinUtils.getAccessToken(appid, secret).get("access_token"), openId);
			if (result != null && "0".equals(result.get("subscribe"))) {
				logger.info("this user is  not focus weixin now");
				return new ModelAndView(
						"redirect:https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1ODY3MzY4Mw==&scene=124#wechat_redirect");
			} else if (result != null && "1".equals(result.get("subscribe"))) {
				// 已经关注 判断数据库中是否有信息
				user = new WebUser();
				user.setOpenId(openId);
				user = webUserMapper.selectBy(user);
				if (user == null) {// 数据库库中没有用户信息(没有绑定)
					logger.info("this user has not info in database");
					user = new WebUser();
					user.setCreatetime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
					user.setFocusTime(result.get("subscribe_time"));
					user.setOpenId(openId);
					user.setImage(result.get("headimgurl"));
					user.setSex(Integer.parseInt(result.get("sex")));
					user.setUserName(result.get("nickname"));
					webUserService.saveUserComment(user);
					session.removeAttribute("user");
					session.setAttribute("unboundUser", user);// 未绑定的用户,还需要添加年龄段
					// ?？跳转到绑定工号的界面，将用户信息传递...
					return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=personalCenter");
				} else {
					if (user.getUserName() == null || user.getFocusTime() == null || user.getImage() == null) {
						logger.info("updata user info like headimgurl ..sex");
						WebUser webUser = new WebUser();
						webUser.setId(user.getId());
						webUser.setFocusTime(result.get("subscribe_time"));
						webUser.setImage(result.get("headimgurl"));
						webUser.setSex(Integer.parseInt(result.get("sex")));
						webUser.setUserName(result.get("nickname"));
						webUserService.updateUser(webUser);
						user = webUserMapper.selectByPrimaryKey(user.getId());
					}
					if(user.getSysUserId() == null){
						session.setAttribute("unboundUser", user);//未绑定的用户,还需要添加年龄段
						return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=personalCenter");
					}
					session.setAttribute("user", user);
				}
			} else {
				return jumpPersonalCenter(session);
			}
		}

	//	WebUser user = (WebUser) session.getAttribute("user");
		MessageCenter mCenter = new MessageCenter();
		if (user != null && user.getAge() != null) {
			mCenter.setUserCreateTime(user.getCreatetime());
			if (user.getAge() == 1) {
				mCenter.setUserType(1);
			} else if (user.getAge() == 2) {
				mCenter.setUserType(2);
			} else if (user.getAge() == 3) {
				mCenter.setUserType(3);
			} else if (user.getAge() == 4) {
				mCenter.setUserType(4);
			} else if (user.getAge() == 5) {
				mCenter.setUserType(5);
			} else if (user.getAge() == 6) {
				mCenter.setUserType(6);
			} else if (user.getAge() == 7) {
				mCenter.setUserType(7);
			} else {
				System.out.println("输出为空。");
			}
		}else{
			MessageCenter mter = new MessageCenter();
			mter.setUserCreateTime(user.getCreatetime());
			List<MessageCenter> msList = messageCenterMapper.getListByTime(mter);
			for (MessageCenter messageCenter : msList) {
				MessageReading messageReading = new MessageReading();
				messageReading.setMessageId(messageCenter.getId());
				messageReading.setUserId(user.getId());
				MessageReading messageR = messageReadingMapper.selectBy(messageReading);
				if (messageR != null) {
					 modelAndView.addObject("rs",1);
					  break;
				} else {
					 modelAndView.addObject("rs",2);
				}
				messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
			}
		}
		List<MessageCenter> mList = messageCenterMapper.getListByTime(mCenter);
		MessageCenter mCent = new MessageCenter();
		if (user != null && user.getSex() != null){
			mCent.setUserCreateTime(user.getCreatetime());
			    if (user.getSex() == 1) {
					mCent.setUserType(8);
				} else if (user.getSex() == 2) {
					mCent.setUserType(9);
				}
		}
		List<MessageCenter> mLists  = messageCenterMapper.getListBySex(mCent);
		  if(mLists!=null&&mLists.size()>0){
			  for (MessageCenter messageCenter : mLists) {
				mList.add(messageCenter);
			}
		  }
		  if(mList!=null&&mList.size()>0){
				for (MessageCenter messageCenter : mList) {
					MessageReading messageReading = new MessageReading();
					messageReading.setMessageId(messageCenter.getId());
					messageReading.setUserId(user.getId());
					MessageReading messageR = messageReadingMapper.selectBy(messageReading);
					if (messageR == null) {
						 modelAndView.addObject("rs",1);
						 break;
					} else {
						 modelAndView.addObject("rs",2);
					}
					messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
				}
				modelAndView.addObject("ObjList", mList);
		  }else{
			  modelAndView.addObject("rs",2);
		  }


//		MessageCenter mCenter = new MessageCenter();
//		mCenter.setUserType(user.getAge());
//		List<MessageCenter> mList = messageCenterMapper.getListBy(mCenter);
//		MessageCenter mCent = new MessageCenter();
//		mCent.setUserType(user.getSex());
//		List<MessageCenter> mLists = messageCenterMapper.getListBySex(mCent);
//		for (MessageCenter messageCenter : mList) {
//			if(messageCenter.getUserType()==10||messageCenter.getUserType().equals(user.getAge())||messageCenter.getUserType().equals(user.getSex())){
//				MessageReading messageReading = new MessageReading();
//				messageReading.setMessageId( messageCenter.getId());
//				messageReading.setUserId(user.getId());
//				  MessageReading messageR = messageReadingMapper.selectBy(messageReading);
//				  if(messageR==null){
//					  modelAndView.addObject("rs",1);
//					  break;
//				  }else{
//					  modelAndView.addObject("rs",2);
//				  }
//			}else{
//				 modelAndView.addObject("rs",2);
//			}
//		}

		logger.info("user is right：id == " + user.getId());
		ProductOrder pOrder = new ProductOrder();
		pOrder.setUserId(user.getId());
		List<ProductOrder> pList = productOrderMapper.getListBy(pOrder);
		List<ProductOrder> pList1 = new ArrayList<ProductOrder>();
		List<ProductOrder> pList2 = new ArrayList<ProductOrder>();
		List<ProductOrder> pList3 = new ArrayList<ProductOrder>();
		List<ProductOrder> pList4 = new ArrayList<ProductOrder>();
		List<ProductOrder> pList0 = new ArrayList<ProductOrder>();
		if (pList != null && pList.size() > 0) {
			for (ProductOrder productOrder : pList) {
				if(productOrder.getOrderState()!=null){
					if (20 == productOrder.getOrderState()) {
						pList1.add(productOrder);
					}
					if (30 == productOrder.getOrderState()) {
						pList2.add(productOrder);
					}
					if (40 == productOrder.getOrderState()) {
						pList3.add(productOrder);
					}
					if (50 == productOrder.getOrderState()) {
						pList4.add(productOrder);
					}
					if (60 == productOrder.getOrderState() || 70 == productOrder.getOrderState()
							|| 80 == productOrder.getOrderState()) {
		 				pList0.add(productOrder);
					}
				}

			}
		}
		modelAndView.addObject("length1", pList1.size());
		modelAndView.addObject("length2", pList2.size());
		modelAndView.addObject("length3", pList3.size());
		modelAndView.addObject("length4", pList4.size());
		modelAndView.addObject("length0", pList0.size());
		modelAndView.setViewName("weixin/personalCenter");
		return modelAndView;
	}

	/**
	 * 发送推广码
	 */
	@RequestMapping("getFriend")
	@ResponseBody
	public JSONObject sendQrcode(HttpSession session) {
		WebUser user = (WebUser) session.getAttribute("user");
		JSONObject object = new JSONObject();
		if (user != null) {
			if (user.getQrcode() == null || "".equals(user.getQrcode())) {
				Map<String, String> map = WeiXinUtils.getQRCodePerpetual(DomainProperties.WEIXIN_APPID,
						DomainProperties.WEIXIN_SECRET, user.getId() + "");
				if (map != null) {
					String QRCode = map.get("url").toString();
					System.out.println("输出QRCode："+QRCode);
					user.setQrcode(QRCode);
					session.setAttribute("user", user);
					WebUser webUser = new WebUser();
					webUser.setId(user.getId());
					webUser.setQrcode(QRCode);
					personalCenterService.updateUser(webUser);
				}
			}
			logger.info(user.getUserName()+"获取qrcode:" + user.getQrcode());
			SendMessageToUserThread thread = new SendMessageToUserThread(user.getQrcode(), user);
			Thread t = new Thread(thread);
			t.start();
			object.put("result", "ok");
			object.put("message", "二维码已经通过公众号发送给您，请注意查收！");
		}else{
			object.put("result", "1");
			object.put("message", "获取二维码失败，请重试！！");
		}
		return object;
	}

	@RequestMapping("myFriend")
	public ModelAndView myfriend(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user != null) {
			UserFriend friend = new UserFriend();
			friend.setUserId(user.getId());
			List<UserFriend> friends = userFriendMapper.getListBy(friend);
			friends = userFriendService.getUsersByFriendList(friends);
			modelAndView.addObject("friends",friends);
			modelAndView.setViewName("weixin/myFriend");
		}else{
			return new ModelAndView(
					"redirect:" + DomainProperties.DOMAIN_WWW + "/personalCenter");
		}
		return modelAndView;
	}

	/**
	 * 修改用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value= "changeUser")
	public ModelAndView changeUser(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user != null) {
			user = webUserMapper.selectByPrimaryKey(user.getId());
			modelAndView.addObject("user", user);
			modelAndView.setViewName("weixin/updateUser");
		}else{
			return new ModelAndView(
					"redirect:" + DomainProperties.DOMAIN_WWW + "/personalCenter");
		}
		return modelAndView;
	}
	@RequestMapping("updateUserInfo")
	public ModelAndView updateUserInfo(HttpSession session,WebUser my) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user != null) {
			if(my == null){
				logger.info("用户为空");
				modelAndView.addObject("result","1");
				modelAndView.addObject("message","修改失败，请重试！");
				modelAndView.setViewName("weixin/updateUser");
			}else{
				int mId = my.getId();
				int uId = user.getId();
				if(mId != uId){
					logger.info("用户的id："+my.getId());
					logger.info("实际的id："+user.getId());
					my = user;
					modelAndView.addObject("user", user);
					modelAndView.addObject("result","1");
					modelAndView.addObject("message","保存失败，请重试！");
					modelAndView.setViewName("weixin/updateUser");
				}else if(my.getUserName() == null || "".equals(my.getUserName().trim())){
					modelAndView.addObject("result","1");
					modelAndView.addObject("user", my);
					modelAndView.addObject("message","保存失败，请检查您的昵称！");
					modelAndView.setViewName("weixin/updateUser");
				}else if(my.getName() == null || "".equals(my.getName().trim())){
					modelAndView.addObject("user", my);
					modelAndView.addObject("result","1");
					modelAndView.addObject("message","保存失败，请检查您的真实姓名！");
					modelAndView.setViewName("weixin/updateUser");
				}else if(!isMobile(my.getPhone())){
					modelAndView.addObject("user", my);
					modelAndView.addObject("result","1");
					modelAndView.addObject("message","保存失败，请检查您的手机格式！");
					modelAndView.setViewName("weixin/updateUser");
				}else{
					webUserService.updateUser(my);
					modelAndView.addObject("user", my);
					modelAndView.addObject("result","0");
					modelAndView.addObject("message","修改成功");
					modelAndView.setViewName("weixin/updateUser");
				}
			}
		}else{
			return new ModelAndView(
					"redirect:" + DomainProperties.DOMAIN_WWW + "/personalCenter");
		}
		return modelAndView;
	}

	public boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
       if(str != null){
    	   p = Pattern.compile("^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$"); // 验证手机号
           m = p.matcher(str);
           b = m.matches();
       }
        return b;
    }
}
