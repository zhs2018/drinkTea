package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.Date;
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
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.service.WebUserService;

@RequestMapping("shop")
@Controller
public class FocusController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private WebUserMapper userCommentMapper;

	@Autowired
	private WebUserService webUserService;

	@Autowired
	private SysUserMapper sysUserMapper;

	@RequestMapping("/index")
	public ModelAndView index(HttpSession session) {
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("openid")==null){
			modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/shop/focus?path=shop/index");
		}else{
			String openId = (String) session.getAttribute("openid");
			Map<String, String> result = WeiXinUtils
					.judgeIsFollow(WeiXinUtils.getAccessToken(appid, secret).get("access_token"), openId);
			if (result != null && "0".equals(result.get("subscribe"))) {
				logger.info("以前未关注公众号，请关注公众号");
				return new ModelAndView("redirect:https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1ODY3MzY4Mw==&scene=124#wechat_redirect");
//				return new ModelAndView("weixin/guanzhu");
			} else if (result != null && "1".equals(result.get("subscribe"))) {
				WebUser user = new WebUser();
				user.setOpenId(openId);
				user = userCommentMapper.selectBy(user);
				// 已关注但是数据库中没有用户信息,需要会员绑定员工工号
				if (user == null || user.getSysUserId() == null) {
					user = new WebUser();
					user.setCreatetime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
					user.setFocusTime(result.get("subscribe_time"));
					user.setOpenId(openId);
					user.setImage(result.get("headimgurl"));
					user.setSex(Integer.parseInt(result.get("sex")));
					user.setUserName(result.get("nickname"));
					session.setAttribute("unboundUser", user);//未绑定的用户,还需要添加年龄段
					//?？跳转到绑定工号的界面，将用户信息传递...
					return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/relate?path=shop/index");
				}else if(user!=null && (user.getUserName() == null || "".equals(user.getUserName()))){//通过朋友推荐
					logger.info("没有用户基本信息,添加");
					user.setImage(result.get("headimgurl"));
					user.setSex(Integer.parseInt(result.get("sex")));
					user.setUserName(result.get("nickname"));
					webUserService.updateUser(user);
				}
				logger.info("用户已经合法："+user.getUserName());
				session.setAttribute("user", user);
				return new ModelAndView("redirect:"+DomainProperties.DOMAIN_WWW + "/shop/manageCity");
				// "redirect:"+DomainProperties.DOMAIN_WWW+"/shop/indexnofocus"
			}
		}
		return modelAndView;
	}

	/**
	 * 获取openid
	 * @param code
	 * @param state
	 * @param session
	 * @param path 重定向的地址（DomainProperties.DOMAIN_WWW+/后的内容）
	 * @return
	 */
	@RequestMapping("focus")
	public ModelAndView focus(String code, String state, HttpSession session, String path) {
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		String uri = DomainProperties.DOMAIN_WWW + "/shop/focus?path="+path;
		ModelAndView modelAndView = new ModelAndView();
		// session中是否存在openid
		boolean flag = session.getAttribute("openid") == null ? true : false;
		// state 0:用户授权获取code
		if (flag && code == null) {
			logger.info("获取code");
			if (state == null) {
				String scope = "snsapi_base";
				state = "0";
				modelAndView.setViewName(WeiXinUtils.getCodeRedirectPath(appid, scope, uri, state));
			} else {// 用户未授权
				logger.info("获取不到授权");
			}
		} else if (flag && code != null) {// 获取到code,获取openId
			logger.info("code:" + code);
			String openId = "";
			Map<String, String> map = WeiXinUtils.getAccessTokenAndOpenid(appid, secret, code);
			if (map != null && map.get("openid") != null) {
				openId = map.get("openid");
				session.setAttribute("openid", openId);
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW+"/"+path);
			} else {
				logger.info("获取openid失败");
			}
		} else{
			modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW+"/"+ path);
		}
		return modelAndView;
	}


	/**
	 * 用户绑定员工
	 * @param session
	 * @param path  路径
	 * @return
	 */
	@RequestMapping("relate")
	public ModelAndView relateSysUser(HttpSession session, String path,String id,Integer sex, Integer age){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("填写工号:id"+id+"path："+path+"   sex:"+sex + "age:"+age);
		modelAndView.addObject("path",path);
		modelAndView.addObject("sex",sex);
		modelAndView.addObject("age",age);
		if (id != null) {
			String result = "";
			String message = "";
			SysUser sysUser = new SysUser();
			sysUser.setJobNumber(id);
//			sysUser.setRoleId();
			sysUser = sysUserMapper.selectBy(sysUser);
			if(sysUser == null){
				result = "1";
				message = "该员工不存在！";
			}else{
				WebUser webUser = (WebUser) session.getAttribute("unboundUser");
				if(webUser != null){
					webUser.setAge(age);
					webUser.setSex(sex);
					webUser.setSysUserId(sysUser.getId());
					int count = webUserService.saveUserComment(webUser);
					if(count == 0){
						//插入失败
						result = "1";
						message = "保存失败！";
					}else{
						WebUser user = userCommentMapper.selectBy(webUser);
						session.setAttribute("user",user);
						modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW+"/"+ path);
						return modelAndView;
					}
				}
			}
			modelAndView.addObject("result",result);
			modelAndView.addObject("message",message);
		}
		modelAndView.setViewName("weixin/salesmanInfo");
		return modelAndView;
	}
}
