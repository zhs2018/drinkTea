package com.wxsoft.drinkTea.platform.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.luckdraw.mapper.DrawTimeMapper;
import com.wxsoft.drinkTea.platform.system.luckdraw.mapper.LuckDrawMapper;
import com.wxsoft.drinkTea.platform.system.luckdraw.mapper.PrizeProMapper;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.DrawTime;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.LuckDraw;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.PrizePro;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WinningInformationMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.model.WinningInformation;
import com.wxsoft.drinkTea.platform.weixin.service.WebUserService;

@Controller
@RequestMapping("luckdraw")
public class DrawluckController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private WebUserService webUserService;
	@Autowired
	private LuckDrawMapper luckDrawMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private PrizeProMapper prizeProMapper;
	@Autowired
	private WinningInformationMapper winningInformationMapper;
	@Autowired
	private DrawTimeMapper drawTimeMapper;

	@RequestMapping(value = "/prizes")
	public ModelAndView publicWelfare(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		logger.info("appid:" + appid + "secret:" + secret);
		WebUser user = (WebUser) session.getAttribute("user");
		// 已经登录过
		if (user != null) {
			// 用户已经关注过，并保存了openid信息
			if (user.getOpenId() != null && !"".equals(user.getOpenId().trim())) {
				logger.info("数据库里面有openID存到session中");
				session.setAttribute("openid", user.getOpenId());
				session.removeAttribute("user");
				return publicWelfare(session);
			} else {
				logger.info("数据库里面有用户信息但是没有openid");
				session.removeAttribute("user");
				return publicWelfare(session);
			}
		} else {
			// 获取openid
			String openId = (String) session.getAttribute("openid");
			if (openId == null || "".equals(openId)) {
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/focus?path=luckdraw/prizes?v="
						+ (new Date()).getTime());
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
					return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=luckdraw/prizes");
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
					if (user.getSysUserId() == null) {
						session.setAttribute("unboundUser", user);// 未绑定的用户,还需要添加年龄段
						return new ModelAndView(
								"redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=luckdraw/prizes");
					}
					session.setAttribute("user", user);
				}
			} else {
				return publicWelfare(session);
			}
		}
		LuckDraw pwf = new LuckDraw();
		pwf.setSign(2);
		LuckDraw pwfs = luckDrawMapper.selectBy(pwf);
		if (pwfs != null) {
			ManageProducts mps = new ManageProducts();
			mps.setValiable(1);
			mps.setSign(2);
			List<ManageProducts> mList = manageProductsMapper.getListBy(mps);
			modelAndView.addObject("mList", mList);
		}
		modelAndView.addObject("pwfs", pwfs);
		modelAndView.setViewName("weixin/luckDraw/draw");
		return modelAndView;
	}

	// 用户进入抽奖页面（前台需要传递哪些参数？）
	@RequestMapping("/drawinfo")
	public ModelAndView frawInfo(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser webUser = (WebUser) session.getAttribute("user");
		if (webUser != null) {
			DrawTime drawTime = new DrawTime();
			DrawTime time = drawTimeMapper.selectBy(drawTime);
			String t2 = time.getStartIme().replace(":", "");
			String t3 = t2.replace("-", "");
			String t4 = t3.substring(0, 8);
			String t5 = t3.substring(9, 13);
			String t6 = t4 + t5;
			String tt2 = time.getEndTime().replace(":", "");
			String tt3 = tt2.replace("-", "");
			String tt4 = tt3.substring(0, 8);
			String tt5 = tt3.substring(9, 13);
			String tt6 = tt4 + tt5;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String date = sdf.format(new Date());
			int m1 = tt6.compareTo(date);
			int m2 = t6.compareTo(date);
			System.out.println("shdbvia ");
			if (m1 == -1 || m1 == 0) {
				mv.addObject("mess", "活动已结束，不在进行抽奖！");
			} else {
				if (m1 == 1 && m2 == -1 || m1 == 1 && m2 == 0) {
					// SELECT * FROM T_USER ORDER BY RAND() LIMIT 10
					if (webUser.getDrawCount() != null && webUser.getDrawCount() != 0) {
						List<PrizePro> pList = prizeProMapper.getListPrize();
						List<PrizePro> ppList = new ArrayList<>();
						for(int i =0;i<9;i++){
							ppList.add(pList.get(i));
						}
						System.out.println("长度："+pList.size());
						mv.addObject("user", webUser);
						mv.addObject("draw", ppList);
						mv.addObject("count", webUser.getDrawCount());
					} else {
						mv.addObject("message", "您的抽奖次数已用完");
					}

				} else {
					mv.addObject("mks", "抽奖时间不正确，请在规定时间抽奖！");
				}
			}

			mv.setViewName("weixin/luckDraw/index2");
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/manageCity");
		}

		return mv;
	}

	// 用户进行抽奖（前台抽奖页面未完成）
	// 需要传回获奖信息（奖品信息）
	// 抽奖次数
	@RequestMapping("/drawPrizes")
	@ResponseBody
	public JSONObject luckdraw(HttpSession session, Integer id) {
		JSONObject json = new JSONObject();
		WebUser webUser = (WebUser) session.getAttribute("user");
		if (webUser != null) {
			WinningInformation win = new WinningInformation();
			win.setPrizeid(id);
			win.setTime(new Date());
			win.setVisible(1);
			win.setSendSign(1);
			win.setUserid(webUser.getId());
			winningInformationMapper.insert(win);
			if (webUser.getDrawCount() != null) {
				webUser.setDrawCount(webUser.getDrawCount() - 1);
				webUserMapper.updateByPrimaryKey(webUser);
			}
			json.put("result", 1);
		} else {
			json.put("result", 2);
		}
		return json;
	}

	// 查看我的奖品
	@RequestMapping("myLuckdraw")
	public ModelAndView mydraw(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser webUser = (WebUser) session.getAttribute("user");
		if (webUser != null) {
			WinningInformation wif = new WinningInformation();
			wif.setVisible(1);
			wif.setUserid(webUser.getId());
			List<WinningInformation> wList = winningInformationMapper.getListBy(wif);
			for (WinningInformation winningInformation : wList) {
				PrizePro prizePro = prizeProMapper.selectByPrimaryKey(winningInformation.getPrizeid());
				winningInformation.setPrizePro(prizePro);
			}
			mv.addObject("wList", wList);
			mv.setViewName("weixin/luckDraw/myLuck");
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/index");
		}
		return mv;
	}
}
