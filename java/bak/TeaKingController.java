package com.wxsoft.drinkTea.platform.weixin.controller;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({ "teaKing" })
@Controller
public class TeaKingController extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TeaKingService teaKingService;

	@Autowired
	private WebUserMapper userCommentMapper;
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


	@RequestMapping({ "/index" })
	public ModelAndView index(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("openid") != null) {
			String openId = (String) session.getAttribute("openid");
			Map<String,String> result = WeiXinUtils.judgeIsFollow(
					(String) WeiXinUtils.getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET).get("access_token"),
					openId);
			if ((result != null) && ("0".equals(result.get("subscribe")))) {
				this.logger.info("关注公众号");
				return new ModelAndView("redirect:https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1ODY3MzY4Mw==&scene=124#wechat_redirect");
			}
			if ((result != null) && ("1".equals(result.get("subscribe")))) {
				WebUser user = new WebUser();
				user.setOpenId(openId);
				user = this.userCommentMapper.selectBy(user);
				if (user == null || user.getSysUserId() == null) {
					user = new WebUser();
					user.setCreatetime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
					user.setFocusTime(result.get("subscribe_time"));
					user.setOpenId(openId);
					user.setImage(result.get("headimgurl"));
					user.setSex(Integer.parseInt(result.get("sex")));
					user.setUserName(result.get("nickname"));
					session.setAttribute("unboundUser", user);
					logger.info("数据库中没有该用户，绑定");
					return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/relate?path=teaKing/index");
				}else if(user!=null && (user.getUserName() == null || "".equals(user.getUserName()))){//通过朋友推荐
					logger.info("没有用户基本信息,添加");
					user.setImage(result.get("headimgurl"));
					user.setSex(Integer.parseInt(result.get("sex")));
					user.setUserName(result.get("nickname"));
					webUserService.updateUser(user);
				}
				session.setAttribute("user", user);
				modelAndView.setViewName("weixin/teaKingTournament/index");
			}
		} else {
			this.logger.info("重定向到shop/focus获取openid");
			return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/focus?path=teaKing/index");
		}
		return modelAndView;
	}

	@RequestMapping({ "/answer" })
	public ModelAndView answer(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);//日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		//赛事未开始
		String message = "";
		String result = "";//0正确  1：未在时间段  2：日赛为空  3:管理员忘记添加赛事了 4:用户已经答题
		if(baseInfo == null){
			result = "2";
			message = "赛事未开始！";
			modelAndView.addObject("result",result);
			modelAndView.addObject("message",message);
			modelAndView.setViewName("weixin/teaKingTournament/index");
			return modelAndView;
		}
		Boolean flag = Boolean.valueOf(DateUtils.checkTimeOnlyHHMM(baseInfo.getStartTime(), baseInfo.getEndTime()));
		if ((flag != null) && (flag.booleanValue())) {
			WebUser user = (WebUser) session.getAttribute("user");
			if (user == null) {
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/index");
				return modelAndView;
			}else {
				session.setAttribute("kingBaseInfo", baseInfo);
				KingUserAnswer userAnswer = new KingUserAnswer();
				userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				userAnswer.setUserId(user.getId());
				userAnswer.setKingId(baseInfo.getId());
				userAnswer = kingUserAnswerMapper.selectBy(userAnswer);
				//没有答题
				if (userAnswer == null || userAnswer.getId() == null) {
					//查询题目
					SubjectKing subjectKing = new SubjectKing();
					subjectKing.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
					subjectKing = subjectKingMapper.selectBy(subjectKing);
					if(subjectKing != null && subjectKing.getId()!=null){
						SubOption option = new SubOption();
						option.setType(1);//扫码答题
						option.setSubId(subjectKing.getId());
						option.setVisable(1);
						List<SubOption> options = subOptionMapper.getListBy(option);
						subjectKing.setOptions(options);
						modelAndView.addObject("subject",subjectKing);
						session.setAttribute("subject", subjectKing);
						modelAndView.setViewName("weixin/teaKingTournament/answer");
					}else{
						result = "2";
						message = "今日没有题目，请联系工作人员！";
						modelAndView.setViewName("weixin/teaKingTournament/index");
					}
				} else {//已经答题
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
		modelAndView.addObject("result",result);
		modelAndView.addObject("message",message);
		return modelAndView;
	}

	@RequestMapping("rule")
	private ModelAndView rule(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("weixin/teaKingTournament/rule");
		return modelAndView;
	}

	/**
	 * 保存题王争霸的结果
	 * @param session
	 * @param answer  用户答案
	 * @param time  剩余时间
	 * @return
	 */
	@RequestMapping("result")
	private ModelAndView savaResult(HttpSession session,Integer answer,Integer time){
		ModelAndView modelAndView = new ModelAndView();
		String result = "";
		String message = "";
		WebUser user = (WebUser) session.getAttribute("user");
		SubjectKing subjectKing = (SubjectKing) session.getAttribute("subject");
		logger.info("answer:"+answer+",time:"+time);
		if(user!=null || subjectKing != null){
			//保存用户答案
			KingBaseInfo baseInfo = new KingBaseInfo();
			baseInfo.setType(1);//日赛
			baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
			KingUserAnswer userAnswer = new KingUserAnswer();
			userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
			userAnswer.setOverTime(time);
			userAnswer.setSubKingId(subjectKing.getId());
			if(answer == -1){
				userAnswer.setUserAnwser(-1);
			}else{
				userAnswer.setUserAnwser(subjectKing.getOptions().get(answer).getId());
			}
			userAnswer.setUserId(user.getId());
			userAnswer.setKingId(baseInfo.getId());
			int count = teaKingService.saveKingAnswerResult(userAnswer);
			if(count != 1){
				result = "4";
				message = "答题结果保存失败！";
				modelAndView.setViewName("weixin/teaKingTournament/index");
			}else{
				modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW + "/teaKing/achievement");
			}
		}else{
			result = "4";
			message = "登录超时，请重新答题！";
			modelAndView.setViewName("weixin/teaKingTournament/index");
		}
		modelAndView.addObject("result",result);
		modelAndView.addObject("message",message);
		return modelAndView;
	}

//	@RequestMapping(value = { "next" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
//	public ModelAndView next(SubjectKing subject, Integer num, Integer time, HttpSession session) {
//		logger.info("第"+(num+1)+"次答题开始加载时间："+new Date().toString());
//		ModelAndView modelAndView = new ModelAndView();
//		WebUser user = (WebUser) session.getAttribute("user");
////		WebUser user = userCommentMapper.selectByPrimaryKey(34);
//		@SuppressWarnings("unchecked")
//		List<SubjectKing> subjects = (List<SubjectKing>) session.getAttribute("subjects");
//		if ((subjects != null) && (user != null) && (num != null) && (num.intValue() >= 1) && (time != null)) {
//			this.logger.info("题目id" + subject.getId() + "题号：" + num);
//			this.logger.info("用户答案：" + subject.getAnswer() + "实际答案："
//					+ ((SubjectKing) subjects.get(num.intValue() - 1)).getAnswer());
//			this.logger.info("剩余时间：" + subject.getTime());
//			Integer count = Integer.valueOf(subjects.size());
//
//			((SubjectKing) subjects.get(num.intValue() - 1)).setUserAnswer(subject.getAnswer());
//			((SubjectKing) subjects.get(num.intValue() - 1)).setTime(subject.getTime());
//
//			if (num != count) {
//				subject = (SubjectKing) subjects.get(num.intValue());
//				num = Integer.valueOf(num.intValue() + 1);
//				modelAndView.addObject("num", num);
//				modelAndView.addObject("count", count);
//				modelAndView.addObject("subject", subject);
//				modelAndView.setViewName("weixin/teaKingTournament/answer");
//			} else {
//				KingBaseInfo baseInfo = (KingBaseInfo) session.getAttribute("kingBaseInfo");
//				boolean isSave = this.teaKingService.saveKingAnswers(subjects, user.getId(), baseInfo.getId());
//				if (isSave) {
//					modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/achievement/today");
//				} else {
//					modelAndView.addObject("result", "1");
//					modelAndView.addObject("message", "保存结果失败，请重新答题");
//					modelAndView.setViewName("weixin/teaKingTournament/index");
//				}
//			}
//		} else {
//			this.logger.info("超时--跳转到题王争霸首页");
//			modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/teaKing/index");
//		}
//		logger.info("第"+(num+1)+"次答题加载结束时间："+new Date().toString());
//		return modelAndView;
//	}

	/**
	 * 查看答题结果
	 * @param session
	 * @return
	 */
	@RequestMapping({ "achievement" })
	public ModelAndView achievement(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);//日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		if(user != null && baseInfo!=null){
			Boolean flag = Boolean.valueOf(DateUtils.checkTimeOnlyHHMM(baseInfo.getStartTime(), baseInfo.getEndTime()));
			if(flag){//在答题时间段
				//判断用户是否答题
				KingUserAnswer userAnswer = new  KingUserAnswer();
				userAnswer.setUserId(user.getId());
				userAnswer.setKingId(baseInfo.getId());
				userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				userAnswer = kingUserAnswerMapper.selectBy(userAnswer);
				if(userAnswer!=null){
					modelAndView.addObject("time",5000-userAnswer.getOverTime());
					modelAndView.addObject("endTime",DateUtils.getDateFormat(baseInfo.getEndTime(), "HH:mm"));
					modelAndView.setViewName("weixin/teaKingTournament/achievement");
					return modelAndView;
				}
			}
			/*if("today".equals(flag.trim())){
				//session.setAttribute("kingBaseInfo", baseInfo);
				KingUserAnswer userAnswer = new KingUserAnswer();
				userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				userAnswer.setKingId(baseInfo.getId());
				userAnswer.setUserId(user.getId());
				Integer ranking = kingUserAnswerMapper.getRankingBy(userAnswer);
				logger.info("排名："+ranking);
				userAnswer = kingUserAnswerMapper.selectBy(userAnswer);
				modelAndView.addObject("score",MoneyUtils.getScoreByUserAnser(userAnswer));
				modelAndView.addObject("right",userAnswer.getRightCount());
				modelAndView.addObject("ranking",ranking);
				modelAndView.setViewName("weixin/teaKingTournament/achievement");
			}else{
				logger.info("flag！=today");
				modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/teaKing/index");
			}*/
		}
		modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/teaKing/index");
		return modelAndView;
	}
//
//	/**
//	 * 检查错误
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping({ "checkError" })
//	public ModelAndView checkError(HttpSession session) {
//		ModelAndView modelAndView = new ModelAndView();
//		WebUser user = (WebUser) session.getAttribute("user");
////		WebUser user = userCommentMapper.selectByPrimaryKey(34);
//		KingBaseInfo baseInfo = (KingBaseInfo) session.getAttribute("kingBaseInfo");
//		if(user != null && baseInfo!=null){
//			KingUserAnswer userAnswer = new KingUserAnswer();
//			userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
//			userAnswer.setKingId(baseInfo.getId());
//			userAnswer.setUserId(user.getId());
//			userAnswer = kingUserAnswerMapper.selectBy(userAnswer);
//			List<KingAnswerDetail> details = teaKingService.getAnswerDetail(userAnswer);
//			JSONObject object = new JSONObject();
//			object.put("subjects", details);
//			modelAndView.addObject("subjects",object);
//			modelAndView.setViewName("weixin/teaKingTournament/checkError");
//		}else{
//			modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/teaKing/index");
//		}
//		return modelAndView;
//	}
	/**
	 * yzy
	 * 排行榜
	 * @param session
	 * @return
	 */
	@RequestMapping({ "rankingList" })
	public ModelAndView rankingList(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);//日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		if(user != null && baseInfo!=null){
			boolean flag = DateUtils.compareTimeOnlyHHmm(new Date(), baseInfo.getEndTime());
			SubjectKing subjectKing = new SubjectKing();
			if(flag){
				subjectKing.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				subjectKing =subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time","本期");
			}else{
				subjectKing.setCreateTime(DateUtils.getCurrentDayBeforeDate(-1, "yyyyMMdd"));
				subjectKing =subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time","上期");
			}
			if(subjectKing !=null){
				if(subjectKing.getAnswer() != null){
					KingUserAnswer userAnswer = new KingUserAnswer();
					userAnswer.setAnswerTime(subjectKing.getCreateTime().substring(0,8));
					userAnswer.setSubKingId(subjectKing.getId());
					userAnswer.setUserAnwser(subjectKing.getAnswer());
					//查询所有排名
					userAnswer.setOrderBy("over_time desc ,answer_time");
					List<KingUserAnswer> answers  = teaKingService.getRankingList(userAnswer);
					userAnswer.setUserId(user.getId());
					//查询用户排名
					Integer ranking = kingUserAnswerMapper.getRankingBy(userAnswer);
					KingUserAnswer myAnswer = kingUserAnswerMapper.selectBy(userAnswer);
					modelAndView.addObject("answers",answers);
					modelAndView.addObject("myAnswer",myAnswer);
					modelAndView.addObject("user",user);
					modelAndView.addObject("ranking",ranking);
					modelAndView.setViewName("weixin/teaKingTournament/rankingList");
				}else{
					modelAndView.addObject("result",1);
					modelAndView.setViewName("weixin/teaKingTournament/viewTopic");
				}
			}else{
				modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/teaKing/index");
			}
//			userAnswer.setAnswerTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
//			userAnswer.setKingId(baseInfo.getId());
//			List<KingUserAnswer> answers = teaKingService.getRankingList(userAnswer);
//			userAnswer.setUserId(user.getId());
//			Integer ranking = kingUserAnswerMapper.getRankingBy(userAnswer);
//			userAnswer = kingUserAnswerMapper.selectBy(userAnswer);
//			modelAndView.addObject("headImg",user.getImage());
//			modelAndView.addObject("answers",answers);
//			modelAndView.addObject("ranking",ranking);
//			modelAndView.addObject("myscore",userAnswer.getScore());
//			modelAndView.setViewName("weixin/teaKingTournament/rankingList");
		}else{
			modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/teaKing/index");
		}
		return modelAndView;
	}

	@RequestMapping("viewTopic")
	private ModelAndView viewTopic(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		KingBaseInfo baseInfo = new KingBaseInfo();
		baseInfo.setType(1);//日赛
		baseInfo = kingBaseInfoMapper.selectBy(baseInfo);
		String result = "";
		String message = "";
		if(user!=null && baseInfo != null){
			//判断当前时间是否在比赛时间之后
			boolean flag = DateUtils.compareTimeOnlyHHmm(new Date(), baseInfo.getEndTime());
			SubjectKing subjectKing = new SubjectKing();
			if(flag){
				subjectKing.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMdd"));
				subjectKing =subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time","本期");
			}else{
				subjectKing.setCreateTime(DateUtils.getCurrentDayBeforeDate(-1, "yyyyMMdd"));
				subjectKing =subjectKingMapper.selectBy(subjectKing);
				modelAndView.addObject("time","上期");
			}
			if(subjectKing !=null){
				result = "0";
				message = "ok";
				SubOption option = subjectKing.getAnswer() == null ? null : subOptionMapper.selectByPrimaryKey(subjectKing.getAnswer());
				if(option == null){
					modelAndView.addObject("time","上期");
				}
				modelAndView.addObject("subject",subjectKing);
				modelAndView.addObject("answer",option);
				modelAndView.setViewName("weixin/teaKingTournament/viewTopic");
			}else{
				result = "4";
				message = "系统出错！";
				modelAndView.setViewName("weixin/teaKingTournament/index");
			}
		}else{
			result = "4";
			message = "系统出错！";
			modelAndView.setViewName("weixin/teaKingTournament/index");
		}
		modelAndView.addObject("result",result);
		modelAndView.addObject("message",message);
		return modelAndView;
	}

}