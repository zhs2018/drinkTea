package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
import com.wxsoft.drinkTea.platform.system.qrcode.mapper.PackageSubjectMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.mapper.SmallPackageMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.model.PackageSubject;
import com.wxsoft.drinkTea.platform.system.qrcode.model.SmallPackage;
import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKuMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubOptionMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;
import com.wxsoft.drinkTea.platform.weixin.model.TwentyRecord;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.service.TeaKingService;
import com.wxsoft.drinkTea.platform.weixin.service.WebUserService;

/**
 * 扫码答题 用户开始扫码答题后会判断用户是否拥有答题权限（是否购买过商品，购买一次拥有五次答题机会），没有需要用户购买商品
 * 用户一经扫码(会预先插入数据)不能中途退出，中途退出也算用户答过该套题目，并且没有任何奖励
 *
 *
 */
@RequestMapping("answer")
@Controller
public class AnswerController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SmallPackageMapper smallPackageMapper;

	@Autowired
	private TeaKingService teaKingService;

	@Autowired
	private WebUserMapper webUserMapper;

	@Autowired
	private WebUserService webUserService;

	@Autowired
	private PackageSubjectMapper packageSubjectMapper;

	@Autowired
	private SubOptionMapper subOptionMapper;

	@Autowired
	private SubjectKuMapper subjectKuMapper;

	/**
	 * 判断扫描后传入的id,为空跳转到购物主页，反之跳转到答题主页（将id保存到session中）
	 *
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(Integer id, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		logger.info("appid:" + appid + "secret:" + secret);
		if (id == null && session.getAttribute("packageId") != null) {
			id = (Integer) session.getAttribute("packageId");
		}
		logger.info("small package id：" + id);
		if (id != null) {
			WebUser user = (WebUser) session.getAttribute("user");
			// 已经登录过
			if (user != null) {
				// 用户已经关注过，并保存了openid信息
				if (user.getOpenId() != null && !"".equals(user.getOpenId().trim())) {
					logger.info("数据库里面有openID存到session中");
					session.setAttribute("openid", user.getOpenId());
					session.removeAttribute("user");
					return index(id, session);
				} else {
					logger.info("数据库里面有用户信息但是没有openid");
					session.removeAttribute("user");
					return index(id, session);
				}
			} else {
				// 获取openid
				String openId = (String) session.getAttribute("openid");
				if (openId == null || "".equals(openId)) {
					modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/focus?path=answer/index?v="
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
						return new ModelAndView(
								"redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=answer/index");
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
									"redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=answer/index");
						}
						session.setAttribute("user", user);
					}
				} else {
					return index(id, session);
				}
			}
			logger.info("user is right：id == " + user.getId());
			session.setAttribute("packageId", id);
		} else {// id为空，跳转到购物主页
			modelAndView.addObject("result", "1");
			modelAndView.addObject("message", "二维码无效！");
		}
		modelAndView.setViewName("weixin/answerSystem/index2");
		return modelAndView;
	}

	@RequestMapping("indexes")
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user != null) {
			mv.setViewName("weixin/answerSystem/index");
		} else {
			mv.setViewName("weixin/answerSystem/index");
		}

		return mv;
	}

	/**
	 * 用户开始答题 判断小包id与用户-》判断题目——》判断是否答题
	 *
	 * @return
	 */
	@RequestMapping("/answer")
	public ModelAndView answer(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Integer id = (Integer) session.getAttribute("packageId");
		String result = "";// 0成功 1失败 2：没有答题机会
		String message = "";
		WebUser user = (WebUser) session.getAttribute("user");
		if (id != null && user != null) {
			user = teaKingService.checkAnswerCount(user);
			user = webUserMapper.selectByPrimaryKey(user.getId());
			session.setAttribute("user", user);
			// 能够答题
			if (user.getAnswerCount() > 0) {
				// 获取小包
				SmallPackage smallPackage = smallPackageMapper.selectByPrimaryKey(id);
				if (smallPackage == null) {
					result = "1";
					message = "二维码已失效！";
					modelAndView.setViewName("weixin/answerSystem/index");
				} else if (smallPackage.getUserId() == null) {
					SubjectKu subjectKus = teaKingService.findSubjects(smallPackage.getId());
					if (subjectKus != null) {
						Boolean flag = teaKingService.saveAnswersResult(subjectKus, user, smallPackage);
						if (flag) {
							session.removeAttribute("packageId");
							session.setAttribute("smallPackage", smallPackage);
							session.setAttribute("subjects", subjectKus);
							session.setAttribute("money", null);
							session.setAttribute("m", 0);
							modelAndView.addObject("subject", subjectKus);// 题目信息
							modelAndView.addObject("smallPackage", smallPackage);
							System.out.println("输出结果为：" + subjectKus);
							modelAndView.setViewName("weixin/answerSystem/answer");
						} else {
							result = "1";
							message = "系统错误！";
							modelAndView.setViewName("weixin/answerSystem/index");
						}
					} else {
						result = "1";
						message = "二维码已失效,请联系管理员！";
						modelAndView.setViewName("weixin/answerSystem/index");
					}
				} else if (smallPackage.getUserId() != null) {
					if (smallPackage.getUserId() == user.getId()) {
						result = "1";
						message = "您已经答过该套题目了哦！";
						modelAndView.setViewName("weixin/answerSystem/index");
					} else {
						result = "1";
						message = "亲，该套题目已经被人答过了！";
						modelAndView.setViewName("weixin/answerSystem/index");
					}
				}
			} else {// 没有答题机会
				result = "2";// 0成功 1失败
				message = "购买商品后才能进行答题赢现金活动哦！";
				modelAndView.setViewName("weixin/answerSystem/index");
			}
		} else {
			result = "1";
			message = "请重新扫码";
			modelAndView.setViewName("weixin/answerSystem/index");
		}
		modelAndView.addObject("result", result);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	/**
	 * 下一题
	 *
	 * @param subject
	 * @param num
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "next", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject next(Integer answer, Integer sign, Integer num, HttpSession session) {
		JSONObject object = new JSONObject();
		WebUser user = (WebUser) session.getAttribute("user");
		SmallPackage smallPackage = (SmallPackage) session.getAttribute("smallPackage");
		SubjectKu subjects = (SubjectKu) session.getAttribute("subjects");
		Integer type = null;
		if (user != null) {
			if (smallPackage != null && answer != null && sign != null) {
				PackageSubject ps = new PackageSubject();
				ps.setSmlPakId(smallPackage.getId());
				ps.setSubId(subjects.getId());
				ps.setSignId(sign);
				ps.setVisable(1);
				PackageSubject pSubject = packageSubjectMapper.selectBy(ps);
				if (sign == 1) {
					System.out.println("输出一下选项的Id" + subjects.getOpList1().get(answer).getId());
					if (subjects.getOpList1().get(answer).getId().equals(pSubject.getOptionId())) {
						// 保存结果
						type = 2;
						Integer m = (Integer) session.getAttribute("m");
						session.setAttribute("m", m + 1);
					} else {
						// 不保存结果
						type = 3;

					}
				} else if (sign == 2) {
					if (subjects.getOpList2().get(answer).getId().equals(pSubject.getOptionId())) {
						// 保存结果
						type = 2;
						Integer m = (Integer) session.getAttribute("m");
						session.setAttribute("m", m + 1);

					} else {
						// 不保存结果
						type = 3;

					}
				} else if (sign == 3) {
					if (subjects.getOpList3().get(answer).getId().equals(pSubject.getOptionId())) {
						// 保存结果
						type = 2;
						Integer m = (Integer) session.getAttribute("m");
						session.setAttribute("m", m + 1);

					} else {
						// 不保存结果
						type = 3;
					}
				} else {
					System.out.println("没有任何操作");
				}
				// 保存答题结果
				TwentyRecord tResult = teaKingService.updateAnswersResults(subjects, user, smallPackage, type, sign,
						answer, num);
				Float money = (Float) session.getAttribute("money");
				if (money != null) {
					money = money + tResult.getNowMoney();
					session.setAttribute("money", money);
				} else {
					session.setAttribute("money", tResult.getNowMoney());
				}
				session.setAttribute("answerResult", tResult);
				session.setAttribute("result", smallPackage.getId());
				object.put("result", "0");
				object.put("num", num + 1);
			} else {
				logger.info("非法访问！");
				object.put("result", "1");
			}
		} else {
			System.out.println("登录主页");
		}

		return object;
	}

	/**
	 * 结果
	 *
	 * @return
	 */
	@RequestMapping("answerSummary")
	public ModelAndView answerSummary(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		TwentyRecord twentyRecord = (TwentyRecord) session.getAttribute("answerResult");
		Float money = (Float) session.getAttribute("money");
		Integer m = (Integer) session.getAttribute("m");
		modelAndView.addObject("count", m);
		modelAndView.addObject("result", money);
		modelAndView.addObject("result", twentyRecord);
		SmallPackage smallPackage = (SmallPackage) session.getAttribute("smallPackage");
		if (smallPackage != null) {
			PackageSubject ps = new PackageSubject();
			ps.setSmlPakId(smallPackage.getId());
			List<PackageSubject> psList = packageSubjectMapper.getListBy(ps);
			for (PackageSubject packageSubject : psList) {
				if (packageSubject.getSignId() == 1) {
					SubOption sk1 = subOptionMapper.selectByPrimaryKey(packageSubject.getOptionId());
					modelAndView.addObject("sk1", sk1);
					SubjectKu sk = subjectKuMapper.selectByPrimaryKey(packageSubject.getSubId());
					modelAndView.addObject("sk", sk);
				} else if (packageSubject.getSignId() == 2) {
					SubOption sk2 = subOptionMapper.selectByPrimaryKey(packageSubject.getOptionId());
					modelAndView.addObject("sk2", sk2);
				} else if (packageSubject.getSignId() == 3) {
					SubOption sk3 = subOptionMapper.selectByPrimaryKey(packageSubject.getOptionId());
					modelAndView.addObject("sk3", sk3);
				} else {
					System.out.println("其他情况");
				}

			}

		}

		modelAndView.setViewName("weixin/answerSystem/answerSummary");
		return modelAndView;
	}

	@RequestMapping("/rule")
	public ModelAndView rule(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("weixin/answerSystem/rule");
		return mv;

	}
}
