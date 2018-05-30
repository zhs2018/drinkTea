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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.SendMessageToUserThread;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserFriendMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
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
@RequestMapping("shop")
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

	@RequestMapping("/personalCenter")
	public ModelAndView jumpPersonalCenter(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser webUser = (WebUser) session.getAttribute("user");
		if (null != webUser) {
			ProductOrder pOrder = new ProductOrder();
			pOrder.setUserId(webUser.getId());
			List<ProductOrder> pList = productOrderMapper.getListBy(pOrder);
			List<ProductOrder> pList1 = new ArrayList<ProductOrder>();
			List<ProductOrder> pList2 = new ArrayList<ProductOrder>();
			List<ProductOrder> pList3 = new ArrayList<ProductOrder>();
			List<ProductOrder> pList4 = new ArrayList<ProductOrder>();
			List<ProductOrder> pList0 = new ArrayList<ProductOrder>();
			for (ProductOrder productOrder : pList) {
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
			modelAndView.addObject("length1", pList1.size());
			modelAndView.addObject("length2", pList2.size());
			modelAndView.addObject("length3", pList3.size());
			modelAndView.addObject("length4", pList4.size());
			modelAndView.addObject("length0", pList0.size());
			modelAndView.setViewName("weixin/personalCenter");
			return modelAndView;
		} else {
			String appid = WeiXinInfo.APPID;
			String secret = WeiXinInfo.APPSECRET;
			if (session.getAttribute("openid") == null) {
				modelAndView.setViewName(
						"redirect:" + DomainProperties.DOMAIN_WWW + "/shop/focus?path=shop/personalCenter");
				return modelAndView;
			} else {
				String openId = (String) session.getAttribute("openid");
				Map<String, String> result = WeiXinUtils
						.judgeIsFollow(WeiXinUtils.getAccessToken(appid, secret).get("access_token"), openId);
				if (result != null && "0".equals(result.get("subscribe"))) {
					logger.info("以前未关注公众号，请关注公众号");
					return new ModelAndView(
							"redirect:https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1ODY3MzY4Mw==&scene=124#wechat_redirect");
				} else if (result != null && "1".equals(result.get("subscribe"))) {
					WebUser user = new WebUser();
					user.setOpenId(openId);
					user = webUserMapper.selectBy(user);
					// 已关注但是数据库中没有用户信息,需要会员绑定员工工号
					if (user == null || user.getSysUserId() == null) {
						user = new WebUser();
						user.setCreatetime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
						user.setFocusTime(result.get("subscribe_time"));
						user.setOpenId(openId);
						user.setImage(result.get("headimgurl"));
						user.setSex(Integer.parseInt(result.get("sex")));
						user.setUserName(result.get("nickname"));
						session.setAttribute("unboundUser", user);// 未绑定的用户,还需要添加年龄段
						// ?？跳转到绑定工号的界面，将用户信息传递...
						return new ModelAndView(
								"redirect:" + DomainProperties.DOMAIN_WWW + "/shop/relate?path=shop/personalCenter");
					}else if(user!=null && (user.getUserName() == null || "".equals(user.getUserName()))){//通过朋友推荐
						logger.info("没有用户基本信息,添加");
						user.setImage(result.get("headimgurl"));
						user.setSex(Integer.parseInt(result.get("sex")));
						user.setUserName(result.get("nickname"));
						webUserService.updateUser(user);
					}
					logger.info("用户已经合法：" + user.getUserName());
					session.setAttribute("user", user);
					ProductOrder pOrder = new ProductOrder();
					pOrder.setUserId(user.getId());
					List<ProductOrder> pList = productOrderMapper.getListBy(pOrder);
					List<ProductOrder> pList1 = new ArrayList<ProductOrder>();
					List<ProductOrder> pList2 = new ArrayList<ProductOrder>();
					List<ProductOrder> pList3 = new ArrayList<ProductOrder>();
					List<ProductOrder> pList4 = new ArrayList<ProductOrder>();
					List<ProductOrder> pList0 = new ArrayList<ProductOrder>();
					for (ProductOrder productOrder : pList) {
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
					modelAndView.addObject("length1", pList1.size());
					modelAndView.addObject("length2", pList2.size());
					modelAndView.addObject("length3", pList3.size());
					modelAndView.addObject("length4", pList4.size());
					modelAndView.addObject("length0", pList0.size());
					modelAndView.setViewName("weixin/personalCenter");
					return modelAndView;
				}
			}
		}
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
			object.put("message", "系统错误！");
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
					"redirect:" + DomainProperties.DOMAIN_WWW + "/shop/personalCenter");
		}
		return modelAndView;
	}

	/**
	 * 修改用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping("changeUser")
	public ModelAndView changeUser(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user != null) {
			user = webUserMapper.selectByPrimaryKey(user.getId());
			modelAndView.addObject("user", user);
			modelAndView.setViewName("weixin/updateUser");
		}else{
			return new ModelAndView(
					"redirect:" + DomainProperties.DOMAIN_WWW + "/shop/personalCenter");
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
					"redirect:" + DomainProperties.DOMAIN_WWW + "/shop/personalCenter");
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
