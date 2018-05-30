 package com.wxsoft.drinkTea.platform.weixin.controller;

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

@RequestMapping()
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
	private WebUserMapper webUserMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@RequestMapping("/index")
	public ModelAndView index(HttpSession session) {
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		logger.info("appid:"+appid+"secret:"+secret);
		ModelAndView modelAndView = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		//已经登录过
		if(user != null){
			//用户已经关注过，并保存了openid信息
			if(user.getOpenId()!=null && !"".equals(user.getOpenId().trim())){
				logger.info("数据库里面有openID存到session中");
				session.setAttribute("openid", user.getOpenId());
				session.removeAttribute("user");
				return index(session);
			}else {
				logger.info("数据库里面有用户信息但是没有openid");
				session.removeAttribute("user");
				return index(session);
			}
		}else{
			//获取openid
			String openId = (String) session.getAttribute("openid");
			if(openId == null || "".equals(openId)){
				modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/focus?path=index");
				return modelAndView;
			}
			//判断 用户是否关注
			Map<String, String> result = WeiXinUtils
					.judgeIsFollow(WeiXinUtils.getAccessToken(appid, secret).get("access_token"), openId);
			if (result != null && "0".equals(result.get("subscribe"))) {
				logger.info("this user is  not focus weixin now");
				return new ModelAndView("redirect:https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1ODY3MzY4Mw==&scene=124#wechat_redirect");
			}else if(result != null && "1".equals(result.get("subscribe"))){
				//已经关注 判断数据库中是否有信息
				user = new WebUser();
				user.setOpenId(openId);
				user = webUserMapper.selectBy(user);
				if(user == null){//数据库库中没有用户信息(没有绑定)
					logger.info("this user has not info in database");
					user = new WebUser();
					user.setCreatetime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
					user.setFocusTime(result.get("subscribe_time"));
					user.setOpenId(openId);
					user.setImage(result.get("headimgurl"));
					user.setSex(Integer.parseInt(result.get("sex")));
					user.setUserName(result.get("nickname"));
					user.setRederSign(1);
					webUserService.saveUserComment(user);
					session.removeAttribute("user");
					session.setAttribute("unboundUser", user);//未绑定的用户,还需要添加年龄段
					//?？跳转到绑定工号的界面，将用户信息传递...
					return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=index");
				}else{
					if(user.getUserName() == null || user.getFocusTime() == null || user.getImage() == null){
						logger.info("updata user info like headimgurl ..sex");
						WebUser webUser = new WebUser();
						webUser.setId(user.getId());
						webUser.setFocusTime(result.get("subscribe_time"));
						webUser.setImage(result.get("headimgurl"));
						webUser.setSex(Integer.parseInt(result.get("sex")));
						webUser.setUserName(result.get("nickname"));
						user.setRederSign(1);
						webUserService.updateUser(webUser);
						user = webUserMapper.selectByPrimaryKey(user.getId());
					}
					if(user.getSysUserId() == null){
						session.setAttribute("unboundUser", user);//未绑定的用户,还需要添加年龄段
						return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=index");
					}
					session.setAttribute("user", user);
				}
			}else{
				return index(session);
			}
		}
		logger.info("user is right：id == "+user.getId());
		return new ModelAndView("redirect:"+DomainProperties.DOMAIN_WWW + "/manageCity");
	}

	/**
	 * 获取openid
	 * @param code
	 * @param state
	 * @param session
	 * @param path 重定向的地址（DomainProperties.DOMAIN_WWW+/后的内容）
	 * @return
	 */
	@RequestMapping("/focus")
	public ModelAndView focus(String code, String state, HttpSession session, String path) {
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		String uri = DomainProperties.DOMAIN_WWW + "/focus?path="+path;
		ModelAndView modelAndView = new ModelAndView();
		// session中是否存在openid
		boolean flag = session.getAttribute("openid") == null ? true : false;
		// state 0:用户授权获取code
		if (flag && code == null) {
			logger.info("to get code");
			if (state == null) {
				String scope = "snsapi_base";
				state = "0";
				modelAndView.setViewName(WeiXinUtils.getCodeRedirectPath(appid, scope, uri, state));
			} else {// 用户未授权 --- 正常情况下不会走到这
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW+"/"+ path);
			}
		} else if (flag && code != null) {// 获取到code,获取openId
			logger.info("is get code");
			String openId = "";
			Map<String, String> map = WeiXinUtils.getAccessTokenAndOpenid(appid, secret, code);
			if (map != null && map.get("openid") != null) {
				openId = map.get("openid");
				session.setAttribute("openid", openId);
				logger.info("code:" + code+"--openid:"+openId);
				modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW+"/"+path);
			} else {// 配置问题出错----
				logger.info("get  openid  is fail ,please check weixinconfig");
//				modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/focus?path="+path);
			}
		} else{
			logger.info("session is contain openid");
			modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW+"/"+ path);
		}
		return modelAndView;
	}


	/**
	 * 用户绑定员工
	 * @param session
	 * @param path  jsp路径
	 * @return
	 */
	@RequestMapping("/relate")
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
				WebUser unUser = (WebUser) session.getAttribute("unboundUser");
				if(unUser != null){
					WebUser webUser = new WebUser();
					webUser.setId(unUser.getId());
					webUser.setAge(age);
					webUser.setSex(sex);
					webUser.setRederSign(1);
					webUser.setSysUserId(sysUser.getId());
					int count = webUserService.updateUser(webUser);
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
				}else{
					modelAndView.setViewName("redirect:" + DomainProperties.DOMAIN_WWW+"/"+ path);
					return modelAndView;
				}
			}
			modelAndView.addObject("result",result);
			modelAndView.addObject("message",message);
		}
		modelAndView.setViewName("weixin/salesmanInfo");
		return modelAndView;
	}
}
