package com.wxsoft.drinkTea.platform.system.userRole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMapper;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRole;
import com.wxsoft.drinkTea.platform.system.userRole.service.SysUserService;

@Controller
@RequestMapping("system/achievement")
public class AchievementController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysUserService sysUserService;


	/**
	 * 查看普通员工的业绩
	 * @param user
	 * @param ttime
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView jumpToAchievement(SysUser user,String ttime){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("时间范围："+ttime);
		modelAndView.setViewName("system/UserRole/achievement");
		//查询业绩
		SysRole role = new SysRole();
		role.setRoleName("普通员工");
		role = sysRoleMapper.selectBy(role);
		if(user == null){
			user = new SysUser();
		}
		user.setVisable(1);
		if(role != null){
			user.setRoleId(role.getId());
		}
		List<SysUser> users = sysUserMapper.getPageListBy(user);
		users = sysUserService.setWebUserCount(users,ttime);
		modelAndView.addObject("page",user);
		modelAndView.addObject("users",users);
		modelAndView.addObject("time",ttime);
		return modelAndView;
	}
}
