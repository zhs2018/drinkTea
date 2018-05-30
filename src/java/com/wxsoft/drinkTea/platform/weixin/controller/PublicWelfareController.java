package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.publicWelfare.mapper.PublicWelfareMapper;
import com.wxsoft.drinkTea.platform.system.publicWelfare.model.PublicWelfare;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.service.WebUserService;


@Controller
@RequestMapping("company")
public class PublicWelfareController extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private WebUserService webUserService;
	@Autowired
	private PublicWelfareMapper publicWelfareMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;

	@RequestMapping(value = "/publicWelfare")
	public ModelAndView publicWelfare(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String appid = WeiXinInfo.APPID;
		String secret = WeiXinInfo.APPSECRET;
		logger.info("appid:"+appid+"secret:"+secret);
			WebUser user = (WebUser) session.getAttribute("user");
			//已经登录过
			if(user != null){
				//用户已经关注过，并保存了openid信息
				if(user.getOpenId()!=null && !"".equals(user.getOpenId().trim())){
					logger.info("数据库里面有openID存到session中");
					session.setAttribute("openid", user.getOpenId());
					session.removeAttribute("user");
					return publicWelfare(session);
				}else {
					logger.info("数据库里面有用户信息但是没有openid");
					session.removeAttribute("user");
					return publicWelfare(session);
				}
			}else{
				//获取openid
				String openId = (String) session.getAttribute("openid");
				if(openId == null || "".equals(openId)){
					modelAndView.setViewName("redirect:"+DomainProperties.DOMAIN_WWW+"/focus?path=company/publicWelfare?v="+(new Date()).getTime());
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
						webUserService.saveUserComment(user);
						session.removeAttribute("user");
						session.setAttribute("unboundUser", user);//未绑定的用户,还需要添加年龄段
						//?？跳转到绑定工号的界面，将用户信息传递...
						return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=company/publicWelfare");
					}else{
						if(user.getUserName() == null || user.getFocusTime() == null || user.getImage() == null){
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
							return new ModelAndView("redirect:" + DomainProperties.DOMAIN_WWW + "/relate?path=company/publicWelfare");
						}
						session.setAttribute("user", user);
					}
		      } else {
		    	  return publicWelfare(session);
		}
			}
         PublicWelfare pwf= new PublicWelfare();
         pwf.setSign(2);
        PublicWelfare pwfs =  publicWelfareMapper.selectBy(pwf);
          if(pwfs!=null){
        	  ManageProducts mps = new ManageProducts();
        	  mps.setValiable(1);
        	  mps.setSign(1);
        	List<ManageProducts> mList =  manageProductsMapper.getListBy(mps);
        	modelAndView.addObject("mList",mList);
          }
         modelAndView.addObject("pwfs",pwfs);
		modelAndView.setViewName("weixin/publicWelfare/welfare");
		return modelAndView;
	}

}
