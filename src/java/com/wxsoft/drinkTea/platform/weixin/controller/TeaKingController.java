package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.refundtime.mapper.RefundTimeMapper;
import com.wxsoft.drinkTea.platform.system.refundtime.model.RefundTime;
import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKingMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKing;
import com.wxsoft.drinkTea.platform.weixin.mapper.KingBaseInfoMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.KingUserAnswerMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubOptionMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.KingBaseInfo;
import com.wxsoft.drinkTea.platform.weixin.model.KingUserAnswer;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.service.TeaKingService;
import com.wxsoft.drinkTea.platform.weixin.service.WebUserService;

@RequestMapping({ "teaKing" })
@Controller
public class TeaKingController extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TeaKingService teaKingService;

	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private WebUserService webUserService;

	@Autowired
	private KingBaseInfoMapper kingBaseInfoMapper;

	@Autowired
	private SubjectKingMapper subjectKingMapper;

	@Autowired
	private KingUserAnswerMapper kingUserAnswerMapper;

	@Autowired
	private SubOptionMapper subOptionMapper;

	@Autowired
	private RefundTimeMapper refundTimeMapper;

	@RequestMapping({ "/index" })
	public ModelAndView index(HttpSession session) {
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		logger.info("appid:" + appid + "secret:" + secret);
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		// 已经登录过
		if (user != null) {
			// 用户已经关注过，并保存了openid信息
			if (user.getOpenId() != null && !"".equals(user.getOpenId().trim())) {
				logger.info("数据库里面有openID存到session中");
				session.setAttribute("openid", user.getOpenId());
				session.removeAttribute("user");
				return index(session);
			} else {
				logger.info("数据库里面有用户信息但是没有openid");
				session.removeAttribute("user");
				return index(session);
			}
		} else {
			// 获取openid
			String openId = (String) session.getAttribute("openid");
			if (openId == null || "".equals(openId)) {
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/focus?path=teaKing/index");
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
					return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=teaKing/index");
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
								"redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=teaKing/index");
					}
					session.setAttribute("user", user);
				}
			} else {
				return index(session);
			}
		}
		logger.info("user is right：id == " + user.getId());
		modelAndView.setViewName("weixin/teaKingTournament/index");
		return modelAndView;
	}

	@RequestMapping({ "/answer" })
	public ModelAndView answer(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);// 日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		// 赛事未开始
		String message = "";
		String result = "";// 0正确 1：未在时间段 2：日赛为空 3:管理员忘记添加赛事了 4:用户已经答题
		if (baseInfo == null) {
			result = "2";
			message = "赛事未开始！";
			modelAndView.addObject("result", result);
			modelAndView.addObject("message", message);
			modelAndView.setViewName("weixin/teaKingTournament/index");
			return modelAndView;
		}
		Boolean flag = Boolean.valueOf(DateUtils.checkTimeOnlyHHMM(baseInfo.getStartTime(), baseInfo.getEndTime()));
		if ((flag != null) && (flag.booleanValue())) {
			WebUser user = (WebUser) session.getAttribute("user");
			if (user == null) {
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/index");
				return modelAndView;
			} else {
				session.setAttribute("kingBaseInfo", baseInfo);
				KingUserAnswer userAnswer = new KingUserAnswer();
				userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				userAnswer.setUserId(user.getId());
				userAnswer.setKingId(baseInfo.getId());
				userAnswer = kingUserAnswerMapper.selectBy(userAnswer);
				// 没有答题
				if (userAnswer == null || userAnswer.getId() == null) {
					// 查询题目
					SubjectKing subjectKing = new SubjectKing();
					subjectKing.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
					subjectKing = subjectKingMapper.selectBy(subjectKing);
					if (subjectKing != null && subjectKing.getId() != null) {
						SubOption option = new SubOption();
						option.setType(1);// 茶王争霸
						option.setSubId(subjectKing.getId());
						option.setVisable(1);
						List<SubOption> options = subOptionMapper.getListBy(option);
						subjectKing.setOptions(options);
						modelAndView.addObject("subject", subjectKing);
						RefundTime rt = new RefundTime();
						RefundTime rte = refundTimeMapper.selectBy(rt);
						modelAndView.addObject("times", rte.getTime());
						session.setAttribute("subject", subjectKing);

						modelAndView.setViewName("weixin/teaKingTournament/answer");
					} else {
						result = "2";
						message = "今日没有题目，请联系工作人员！";
						modelAndView.setViewName("weixin/teaKingTournament/index");
					}
				} else {// 已经答题
					result = "4";
					message = "您已经答题，请耐心等待答题结果！";
					modelAndView.setViewName("weixin/teaKingTournament/index");
				}
			}
		} else {
			result = "1";
			message = DateUtils.getDateFormat(baseInfo.getStartTime(), "HH:mm") + "-"
					+ DateUtils.getDateFormat(baseInfo.getEndTime(), "HH:mm");
			modelAndView.setViewName("weixin/teaKingTournament/index");
		}
		modelAndView.addObject("result", result);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping("rule")
	private ModelAndView rule() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("weixin/teaKingTournament/rule");
		return modelAndView;
	}

	/**
	 * 保存题王争霸的结果
	 *
	 * @param session
	 * @param answer
	 *            用户答案
	 * @param time
	 *            剩余时间
	 * @return
	 */
	@RequestMapping("result")
	private ModelAndView savaResult(HttpSession session, Integer answer, Integer time) {
		ModelAndView modelAndView = new ModelAndView();
		String result = "";
		String message = "";
		WebUser user = (WebUser) session.getAttribute("user");
		SubjectKing subjectKing = (SubjectKing) session.getAttribute("subject");
		logger.info("answer:" + answer + ",time:" + time);
		if (user != null || subjectKing != null) {
			// 保存用户答案
			KingBaseInfo baseInfo = new KingBaseInfo();
			baseInfo.setType(1);// 日赛
			baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
			KingUserAnswer userAnswer = new KingUserAnswer();
			userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));//
			userAnswer.setOverTime(time);
			userAnswer.setSubKingId(subjectKing.getId());
			if (answer == -1) {
				userAnswer.setUserAnwser(-1);
			} else {
				userAnswer.setUserAnwser(subjectKing.getOptions().get(answer).getId());
			}
			userAnswer.setUserId(user.getId());
			userAnswer.setKingId(baseInfo.getId());
			int count = teaKingService.saveKingAnswerResult(userAnswer);
			if (count != 1) {
				result = "4";
				message = "答题结果保存失败！";
				modelAndView.setViewName("weixin/teaKingTournament/index");
			} else {
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/achievement");
			}
		} else {
			result = "4";
			message = "登录超时，请重新答题！";
			modelAndView.setViewName("weixin/teaKingTournament/index");
		}
		modelAndView.addObject("result", result);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	/**
	 * 查看答题结果
	 *
	 * @param session
	 * @return
	 */
	@RequestMapping({ "achievement" })
	public ModelAndView achievement(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);// 日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		if (user != null && baseInfo != null) {
			Boolean flag = Boolean.valueOf(DateUtils.checkTimeOnlyHHMM(baseInfo.getStartTime(), baseInfo.getEndTime()));
			if (flag) {// 在答题时间段
				// 判断用户是否答题
				KingUserAnswer userAnswer = new KingUserAnswer();
				userAnswer.setUserId(user.getId());
				userAnswer.setKingId(baseInfo.getId());
				userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				userAnswer = kingUserAnswerMapper.selectBy(userAnswer);
				if (userAnswer != null) {
					RefundTime rt = new RefundTime();
					RefundTime rts = refundTimeMapper.selectBy(rt);
					Integer time = rts.getTime();
					modelAndView.addObject("time", time - userAnswer.getOverTime());
					modelAndView.addObject("endTime", DateUtils.getDateFormat(baseInfo.getEndTime(), "HH:mm"));
					modelAndView.setViewName("weixin/teaKingTournament/achievement");
					return modelAndView;
				}
			}

		}
		modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/index");
		return modelAndView;
	}

	/**
	 * 排行榜
	 *
	 * @param session
	 * @return
	 */
	@RequestMapping({ "rankingList" })
	public ModelAndView rankingList(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);// 日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		if (user != null && baseInfo != null) {
			boolean flag = DateUtils.compareTimeOnlyHHmm(new Date(), baseInfo.getEndTime());
			SubjectKing subjectKing = new SubjectKing();
			if (flag) {
				subjectKing.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				subjectKing = subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time", "本期");
			} else {
				subjectKing.setCreateTime(DateUtils.getCurrentDayBeforeDate(-1, "yyyyMMdd"));
				subjectKing = subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time", "上期");
			}
			if (subjectKing != null) {
				if (subjectKing.getAnswer() != null) {
					KingUserAnswer userAnswer = new KingUserAnswer();
					userAnswer.setAnswerTime(subjectKing.getCreateTime().substring(0, 8));
					userAnswer.setSubKingId(subjectKing.getId());
					userAnswer.setUserAnwser(subjectKing.getAnswer());
					// 查询所有排名
					userAnswer.setOrderBy("over_time desc ,answer_time");
					List<KingUserAnswer> answers = teaKingService.getRankingList(userAnswer);

					userAnswer.setUserId(user.getId());
					// 查询用户排名
					Integer ranking = kingUserAnswerMapper.getRankingBy(userAnswer);
					KingUserAnswer myAnswer = kingUserAnswerMapper.selectBy(userAnswer);
					RefundTime rt = new RefundTime();
					RefundTime rts = refundTimeMapper.selectBy(rt);
					Integer time = rts.getTime();
					modelAndView.addObject("time", time);
					modelAndView.addObject("answers", answers);
					modelAndView.addObject("myAnswer", myAnswer);
					modelAndView.addObject("user", user);
					modelAndView.addObject("ranking", ranking);
					modelAndView.setViewName("weixin/teaKingTournament/rankingList");
				} else {
					modelAndView.addObject("result", 1);
					modelAndView.setViewName("weixin/teaKingTournament/viewTopic");
				}
			} else {
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/index");
			}

		} else {
			modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/index");
		}
		return modelAndView;
	}

	@RequestMapping("viewTopic")
	private ModelAndView viewTopic(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);// 日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		String result = "";
		String message = "";
		if (user != null && baseInfo != null) {
			// 判断当前时间是否在比赛时间之后
			boolean flag = DateUtils.compareTimeOnlyHHmm(new Date(), baseInfo.getEndTime());
			SubjectKing subjectKing = new SubjectKing();
			if (flag) {
				subjectKing.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				subjectKing = subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time", "本期");
			} else {
				subjectKing.setCreateTime(DateUtils.getCurrentDayBeforeDate(-1, "yyyyMMdd"));
				subjectKing = subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time", "上期");
			}
			if (subjectKing != null) {
				result = "0";
				message = "ok";
				SubOption option = subjectKing.getAnswer() == null ? null
						: subOptionMapper.selectByPrimaryKey(subjectKing.getAnswer());
				if (option == null) {
					modelAndView.addObject("time", "上期");
				}
				modelAndView.addObject("subject", subjectKing);
				modelAndView.addObject("answer", option);
				modelAndView.setViewName("weixin/teaKingTournament/viewTopic");
			} else {
				result = "4";
				message = "系统出错！";
				modelAndView.setViewName("weixin/teaKingTournament/index");
			}
		} else {
			result = "4";
			message = "系统出错！";
			modelAndView.setViewName("weixin/teaKingTournament/index");
		}
		modelAndView.addObject("result", result);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping("ranking")
	public ModelAndView rank(HttpSession session,String ttime){
		ModelAndView mv = new ModelAndView();
		WebUser web = new WebUser();
	    WebUser user  = (WebUser) session.getAttribute("user");
	    mv.addObject("user",user);
	    KingUserAnswer userAnswer = new KingUserAnswer();
	    if (ttime != null && !"".equals(ttime)) {
			String[] sTime = ttime.split("~");
			web.setBeginTime(DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[0]), "yyyyMMdd"));
			web.setEndTime(DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[1]), "yyyyMMdd"));
			List<WebUser> webUsers = webUserMapper.getPageListByKingAnswerAndTime(web);
			mv.setViewName("weixin/teaKingTournament/rankingList2");
			mv.addObject("users", webUsers);
			mv.addObject("sTime", ttime);
		} else {
			userAnswer.setUserId(user.getId());
//			Integer ranking = kingUserAnswerMapper.getLikeRankingBy(userAnswer);
			List<WebUser> webUsers = webUserMapper.getPageListByKingAnswer(web);
			mv.addObject("users", webUsers);
			mv.addObject("userId",user.getId());

			mv.setViewName("weixin/teaKingTournament/rankingList2");

		}
		return mv;
	}

}