package com.wxsoft.drinkTea.platform.system.userRole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.recommendfriend.mapper.RecommendFriendMapper;
import com.wxsoft.drinkTea.platform.system.recommendfriend.model.RecommendFriend;
import com.wxsoft.drinkTea.platform.system.userRole.service.SysUserService;
import com.wxsoft.drinkTea.platform.weixin.mapper.RedEnvelopeMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 *
 * @会员管理入口
 * @作者：xyc
 */
@Controller
@RequestMapping("system/user")
public class WebUserController extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private WebUserMapper webUserMapper;

	@Autowired
	private RedEnvelopeMapper redEnvelopeMapper;
	@Autowired
	private RecommendFriendMapper recommendFriendMapper;

	/*
	 * @跳转到会员列表并携带参数wa system/membermanagement/memberUser
	 */
	/**
	 * @修改日期 2017-4-18
	 * @修改人 yzy
	 * @param user
	 * @return
	 */
	@RequestMapping("/memberUser")
	public ModelAndView modelAndView(WebUser user, String num, String time, String userId) {
		ModelAndView modelAndView = new ModelAndView("system/membermanagement/memberUser");
	/*	if (user == null) {
			user = new WebUser();
		}*/
		List<WebUser> list = null;
		if (userId != null && !"".equals(userId)) {
			try {
				Integer id = Integer.parseInt(userId);
				SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
				if (sysUser == null) {
					modelAndView.addObject("result", "1");
					modelAndView.addObject("message", "该员工不存在或已经被删除！");
				}
				user.setSysUserId(sysUser.getId());
				list = sysUserService.setWebUser(user, time);
				user = list.get(list.size() - 1);
				list.remove(list.size() - 1);
				modelAndView.addObject("users", list);
			} catch (NumberFormatException e) {
				modelAndView.addObject("result", "1");
				modelAndView.addObject("message", "员工工号错误！");
				e.printStackTrace();
			}
		} else {
			if (num != null && !"".equals(num)) {
				SysUser sysUser = new SysUser();
				sysUser.setJobNumber(num);
				sysUser = sysUserMapper.selectBy(sysUser);
				if (sysUser == null) {
					modelAndView.addObject("result", "1");
					modelAndView.addObject("message", "该工号不存在");
				} else {
					user.setSysUserId(sysUser.getId());
					list = sysUserService.setWebUser(user, time);
					user = list.get(list.size() - 1);
					list.remove(list.size() - 1);
					modelAndView.addObject("users", list);
				}
			} else {
				list = sysUserService.setWebUser(user, time);
				user = list.get(list.size() - 1);
				list.remove(list.size() - 1);
				modelAndView.addObject("users", list);
			}
		}
		modelAndView.addObject("page", user);
		modelAndView.addObject("time", time);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("num", num);
		return modelAndView;
	}

	@RequestMapping("bonus")
	public ModelAndView bonus(WebUser user, String userName) {
		ModelAndView mv = new ModelAndView();
		if (userName != null) {
			/*user = new WebUser();*/
			user.setUserName(userName);
			List<WebUser> wList = webUserMapper.getPageListBy(user);
			for (WebUser webUser : wList) {
				RedEnvelope re = new RedEnvelope();
				re.setUserId(webUser.getId());
				Double money = 0.0;
				List<RedEnvelope> list = redEnvelopeMapper.getListBy(re);
				for (RedEnvelope redEnvelope : list) {
					money += redEnvelope.getMoney();
				}
				webUser.setMoneyXY(money);// 红包+分红

				RedEnvelope r = new RedEnvelope();
				r.setUserId(webUser.getId());
				r.setType(0);
				Double moneyX = 0.0;
				List<RedEnvelope> list1 = redEnvelopeMapper.getListBy(r);
				for (RedEnvelope redEnvelope : list1) {
					moneyX += redEnvelope.getMoney();
				}
				webUser.setMoneyX(moneyX);// 红包
				RedEnvelope e = new RedEnvelope();
				e.setUserId(webUser.getId());
				e.setType(1);
				Double moneyY = 0.0;
				List<RedEnvelope> list2 = redEnvelopeMapper.getListBy(e);
				for (RedEnvelope redEnvelope : list2) {
					moneyY += redEnvelope.getMoney();
				}
				webUser.setMoneyY(moneyY);// 分红
			}
			mv.addObject("page", user);
			mv.addObject("list", wList);
		} else {
			/*if (user == null) {
				user = new WebUser();
			}*/
			List<WebUser> wList = webUserMapper.getPageListBy(user);
			for (WebUser webUser : wList) {
				RedEnvelope re = new RedEnvelope();
				re.setUserId(webUser.getId());
				Double money = 0.0;
				List<RedEnvelope> list = redEnvelopeMapper.getListBy(re);
				for (RedEnvelope redEnvelope : list) {
					money += redEnvelope.getMoney();
				}
				webUser.setMoneyXY(money);// 红包+分红
				RedEnvelope r = new RedEnvelope();
				r.setUserId(webUser.getId());
				r.setType(0);
				Double moneyX = 0.0;
				List<RedEnvelope> list1 = redEnvelopeMapper.getListBy(r);
				for (RedEnvelope redEnvelope : list1) {
					moneyX += redEnvelope.getMoney();
				}
				webUser.setMoneyX(moneyX);// 红包

				RedEnvelope e = new RedEnvelope();
				e.setUserId(webUser.getId());
				e.setType(1);
				Double moneyY = 0.0;
				List<RedEnvelope> list2 = redEnvelopeMapper.getListBy(e);
				for (RedEnvelope redEnvelope : list2) {
					moneyY += redEnvelope.getMoney();
				}
				webUser.setMoneyY(moneyY);// 分红
			}
			mv.addObject("list", wList);
		}

		mv.addObject("page", user);
		mv.setViewName("/system/membermanagement/memberbonus");
		return mv;
	}

	@RequestMapping("/seePeople/{id}")
	public ModelAndView see(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView();
        if(id!=null){
        	WebUser wUser =  webUserMapper.selectByPrimaryKey(id);
        	 mv.addObject("user",wUser);
        	RecommendFriend fr  = new RecommendFriend();
            fr.setRecommendId(id);
            List<RecommendFriend> frList = recommendFriendMapper.getPageListBy(fr);
            if(frList!=null&&frList.size()>0){
             for (RecommendFriend recommendFriend : frList) {
            	 WebUser wu = webUserMapper.selectByPrimaryKey(recommendFriend.getRecommendedId());
            	 if(wu!=null){
            		 recommendFriend.setName(wu.getUserName());
            	 }
             }
             mv.addObject("frList",frList);
            }
             RecommendFriend rf = new RecommendFriend();
             rf.setRecommendedId(id);
             rf.setType(1);
           List<RecommendFriend> rf1= recommendFriendMapper.getListLike(rf);
           if(rf1!=null&&rf1.size()>0){
               for (RecommendFriend recommendFriend : rf1) {
              	 WebUser wu = webUserMapper.selectByPrimaryKey(recommendFriend.getRecommendId());
              	 if(wu!=null){
              		 recommendFriend.setName(wu.getUserName());
              	 }
               }
               mv.addObject("rfList",rf1);
              }
           mv.setViewName("/system/membermanagement/seemember");
        }else{
        	mv.setViewName("/system/membermanagement/memberbonus");
        }
		return mv;

	}

}
