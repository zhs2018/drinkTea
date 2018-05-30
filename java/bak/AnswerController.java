package com.wxsoft.drinkTea.platform.weixin.controller;

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
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubOptionMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.TwentyRecordMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;
import com.wxsoft.drinkTea.platform.weixin.model.TwentyRecord;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.service.TeaKingService;
import com.wxsoft.drinkTea.platform.weixin.service.WebUserService;

/**
 * 扫码答题
 *用户开始扫码答题后会判断用户是否拥有答题权限（是否购买过商品，购买一次拥有五次答题机会），没有需要用户购买商品
 *用户一经扫码(会预先插入数据)不能中途退出，中途退出也算用户答过该套题目，并且没有任何奖励
 * @author yzy
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
		if (id == null && session.getAttribute("packageId") != null) {
			id = (Integer) session.getAttribute("packageId");
		}
		logger.info("小包id：" + id);
		if (id != null) {
			session.setAttribute("packageId", id);
			// 获取到openid
			if (session.getAttribute("openid") != null) {
				logger.info("获取到了openid");
				String openId = (String) session.getAttribute("openid");
				Map<String, String> result = WeiXinUtils.judgeIsFollow(
						WeiXinUtils.getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET).get("access_token"), openId);
				// 用户没有关注公众号
				if (result != null && "0".equals(result.get("subscribe"))) {
					logger.info("关注公众号");
					return new ModelAndView("redirect:https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1ODY3MzY4Mw==&scene=124#wechat_redirect");
				} else if (result != null && "1".equals(result.get("subscribe"))) {// 用户已经关注
					WebUser user = new WebUser();
					user.setOpenId(openId);
					user = webUserMapper.selectBy(user);
					if (user == null || user.getSysUserId() == null) {// 已关注但是数据库中没有用户信息
						user = new WebUser();
						user.setCreatetime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
						user.setFocusTime(result.get("subscribe_time"));
						user.setOpenId(openId);
						user.setImage(result.get("headimgurl"));
						user.setSex(Integer.parseInt(result.get("sex")));
						user.setUserName(result.get("nickname"));
						session.setAttribute(" ", user);
						logger.info("数据库中没有该用户，绑定");
						return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/relate?path=answerSystem/index");
					}else if(user!=null && (user.getUserName() == null || "".equals(user.getUserName()))){//通过朋友推荐
						logger.info("没有用户基本信息,添加");
						user.setImage(result.get("headimgurl"));
						user.setSex(Integer.parseInt(result.get("sex")));
						user.setUserName(result.get("nickname"));
						webUserService.updateUser(user);
					}
					session.setAttribute("user", user);
					modelAndView.setViewName("weixin/answerSystem/index");
				}
			} else {
				logger.info("重定向到shop/focus获取openid");
				return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/focus?path=answer/index");
			}
		} else {// id为空，跳转到购物主页
			modelAndView.addObject("result","1");
			modelAndView.addObject("message","二维码无效！");
			modelAndView.setViewName("weixin/answerSystem/index");
		}
		return modelAndView;
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
		String result = "";// 0成功 1失败  2：没有答题机会
		String message = "";
		WebUser user = (WebUser) session.getAttribute("user");
		if (id != null && user != null) {
			user  = teaKingService.checkAnswerCount(user);
			user = webUserMapper.selectByPrimaryKey(user.getId());
			session.setAttribute("user", user);
			//能够答题
			if(user.getAnswerCount() >0){
				//获取小包
				SmallPackage smallPackage = smallPackageMapper.selectByPrimaryKey(id);
				if(smallPackage == null){
					result = "1";
					message="二维码已失效！";
					modelAndView.setViewName("weixin/answerSystem/index");
				}else if (smallPackage.getUserId()  == null){
					List<SubjectKu> subjectKus = teaKingService.findSubjects(smallPackage.getId());
					if(subjectKus != null){
						Boolean flag = teaKingService.saveAnswersResult(subjectKus, user, smallPackage);
						if(flag){
							session.removeAttribute("packageId");
							session.setAttribute("smallPackage", smallPackage);
							session.setAttribute("subjects", subjectKus);
							modelAndView.addObject("num", 1);// 题号
							modelAndView.addObject("subject", subjectKus.get(0));// 题目信息
							modelAndView.setViewName("weixin/answerSystem/answer");
						}else{
							result = "1";
							message="系统错误！";
							modelAndView.setViewName("weixin/answerSystem/index");
						}
					}else{
						result = "1";
						message="二维码已失效,请联系管理员！";
						modelAndView.setViewName("weixin/answerSystem/index");
					}
				}else if(smallPackage.getUserId()!=null){
					if(smallPackage.getUserId() == user.getId()){
						result = "1";
						message="您已经答过该套题目了哦！";
						modelAndView.setViewName("weixin/answerSystem/index");
					}else{
						result = "1";
						message="亲，该套题目已经被人答过了！";
						modelAndView.setViewName("weixin/answerSystem/index");
					}
				}
			}else{//没有答题机会
				result = "2";// 0成功 1失败
				message = "购买商品后才能进行答题赢现金活动哦！";
				modelAndView.setViewName("weixin/answerSystem/index");
			}
		} else {
			result = "1";
			message = "二维码无效";
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
	public JSONObject next(Integer answer, Integer num, HttpSession session) {
		JSONObject object = new JSONObject();
		WebUser user = (WebUser) session.getAttribute("user");
		SmallPackage smallPackage = (SmallPackage) session.getAttribute("smallPackage");
		String result = "";// 0 ：ok  1：err 2:答题完成
		String message = "";
		@SuppressWarnings("unchecked")
		List<SubjectKu> subjects = (List<SubjectKu>) session.getAttribute("subjects");
		logger.info("answer"+answer+",num:"+num+",user"+user.getId()+",package:"+smallPackage.getId()+",subje"+subjects);
		if (smallPackage != null && subjects != null && user != null && num != null && num >= 1 && answer != null) {
			//保存用户答题情况
			if(num == subjects.size()){//最后一题
				result = "2";
				message = "ok";
				List<SubOption> options = subjects.get(num-1).getOptions();
				subjects.get(num-1).setUserAnswer(options.get(answer));
				PackageSubject ps = new PackageSubject();
				ps.setSmlPakId(smallPackage.getId());
				ps.setSubId(subjects.get(num-1).getId());
				ps = packageSubjectMapper.selectBy(ps);
				if(ps.getOptionId() == options.get(answer).getId()){//答对
					subjects.get(num-1).setType(0);
					object.put("rightOrWrong", 0);
				}else{
					subjects.get(num-1).setType(1);
					object.put("rightOrWrong", 1);
					object.put("answer",subOptionMapper.selectByPrimaryKey(ps.getOptionId()).getOption());
				}
				//保存答题结果
				TwentyRecord tResult = teaKingService.updateAnswersResult(subjects,user,smallPackage);
				session.setAttribute("answerResult", tResult);
			}else{
				result = "0";
				message = "ok";
				List<SubOption> options = subjects.get(num-1).getOptions();
				subjects.get(num-1).setUserAnswer(options.get(answer));
				PackageSubject ps = new PackageSubject();
				ps.setSmlPakId(smallPackage.getId());
				ps.setSubId(subjects.get(num-1).getId());
				ps = packageSubjectMapper.selectBy(ps);
				if(ps.getOptionId() == options.get(answer).getId()){//答对
					subjects.get(num-1).setType(0);
					object.put("rightOrWrong", 0);
				}else{
					subjects.get(num-1).setType(1);
					object.put("rightOrWrong", 1);
					object.put("answer",subOptionMapper.selectByPrimaryKey(ps.getOptionId()).getOption());
				}
				object.put("subject", subjects.get(num));
				object.put("num", num+1);
			}
		} else {
			logger.info("非法访问！");
			result = "1";
			message = "系统错误，请重新扫码答题！";
		}
		object.put("result", result);
		object.put("message", message);
		return object;
	}
	/**
	 * 结果
	 * @return
	 */
	@RequestMapping("answerSummary")
	public ModelAndView answerSummary(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		TwentyRecord twentyRecord = (TwentyRecord) session.getAttribute("answerResult");
		modelAndView.addObject("result",twentyRecord);
		modelAndView.setViewName("weixin/answerSystem/answerSummary");
		return modelAndView;
	}
}
