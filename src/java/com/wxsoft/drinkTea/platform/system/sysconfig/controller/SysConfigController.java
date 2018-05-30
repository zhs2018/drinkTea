package com.wxsoft.drinkTea.platform.system.sysconfig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.utils.ConvertPassword;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRole;

@Controller
@RequestMapping("system/sysconfig")
public class SysConfigController {
	@Autowired
	private SysUserMapper sysUserMapper;

	Log log = LogFactory.getLog(SysConfigController.class);

	/**
	 * @Title 配置管理-修改密码
	 * @date 2017-3-22 11:18
	 * @author yzy
	 * @return ModelAndView
	 */
	@RequestMapping(value="/updatesecret")
	public ModelAndView updatesecret(SysRole userRole,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/users/updatesecret");
		return mv;
	}

	/**
	 * @Title 配置管理-修改密码   保存用户密码
	 * @date 2017-3-22 11:18
	 * @author yzy
	 * @return ModelAndView
	 * @Description 跳转到修改密码页面
	 */	@RequestMapping("/saveupdatesecret")
	public ModelAndView saveUpdatesecret(String oldpassword,
			String newpassword, String renewpassword,
			HttpServletRequest request, String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/users/updatesecret");
		SysUser su = (SysUser) session.getAttribute("sessionUser");
		if (ConvertPassword.getMyPassword(oldpassword).equals(su.getPassword())) {
			su.setPassword((ConvertPassword.getMyPassword(newpassword)));
			sysUserMapper.updateByPrimaryKey(su);
			mv.addObject("result", "success");
		} else {
			mv.addObject("result", "faile");
		}
		return mv;
	}
}
