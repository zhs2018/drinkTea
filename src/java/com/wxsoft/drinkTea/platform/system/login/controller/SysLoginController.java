package com.wxsoft.drinkTea.platform.system.login.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.ConvertPassword;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysMenuMapper;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysMenu;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMenuMapper;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRoleMenu;


/**
 *
 * @类功能说明：系统管理入口
 */
@Controller
@RequestMapping("/system")
public class SysLoginController extends BaseAction {
	private static final long serialVersionUID = -4546498186701093857L;

	@Autowired
	private SysUserMapper sysUserMapper;

	//菜单接口
	@Autowired
	private SysMenuMapper sysMenuMapper;

	//角色和菜单关联的接口
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	/**
	 * @Title: loginGet
	 * @date 时间：2017年3月20日上午11:31:11
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:访问登录页面
	 */
	@RequestMapping(value = "/login")
	public ModelAndView loginGet() {
		return new ModelAndView("system/login");
	}

	/**
	 * @Title: loginPost
	 * @date 时间：2017年3月20日上午11:17:41
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:请求登录，验证用户，这里要体现出当前登录用户的权限问题。
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost(HttpSession session, HttpServletResponse response,String DATA) {

		logger.debug("admin login the system manager login page");

		JSONObject param = JSONObject.parseObject(DATA);//前台传过来的登录数据
		if(param != null){
			//新建一个用户对象
			SysUser user = new SysUser();
			user.setUserName(param.getString("loginname"));
			//密码经过md5加密之后和数据库比较
			user.setPassword(ConvertPassword.getMyPassword(param.getString("password")));
			SysUser u  = sysUserMapper.selectBy(user);
			String result = "{'flag':false}";
			if(u != null){
				session.setAttribute("sessionUser", u);
				/*session.removeAttribute("sessionSecCode");*/
				//菜单id，用于区分不同的用户显示不同的菜单
				session.setAttribute("sessionRoleId", u.getRoleId());
				result = "{'flag':true}";
			}
			try {
				responseAjax(result, response);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Title: index
	 * @date 时间：2017年3月20日下午1:37:04
	 * @author 作者： 王桐睿
	 * @return 返回类型：String
	 * @Description:登录成功之后访问系统首页，根据用户各自的权限来分别显示不同的菜单列表
	 */
	@RequestMapping(value = "/index")
	public String index(HttpSession session, Model model,HttpServletResponse response, HttpServletRequest request) {
		SysUser user = (SysUser)session.getAttribute("sessionUser");
		//model.addAttribute("user", user);
		SysRoleMenu roleMenu = new SysRoleMenu();
		Integer roleId = user.getRoleId();//获取角色id
		//根据角色id找到对应的一级菜单
		roleMenu.setRoleId(roleId);
		List<SysMenu> list = sysRoleMenuMapper.getMenuByRoleId(roleMenu);
		for(SysMenu one : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sysMenu", one);
			map.put("roleId", roleId);
			//加入二级菜单
			List<SysMenu> second = sysMenuMapper.getAllSecondLevelSysMenu(map);
			one.setSysMenuList(second);
		}
		session.setAttribute("ROLEWIXIN", list);
		return "redirect:/system/main";
	}

	/**
	 * @Title: main
	 * @date 时间：2017年3月20日下午3:28:58
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:进入首页后的默认页面
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/main");
		return mv;
	}

	/**
	 * @Title: logout
	 * @date 时间：2017年3月20日下午3:13:05
	 * @author 作者： 王桐睿
	 * @return 返回类型：String
	 * @Description:用户注销
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionUser");
		session.removeAttribute("sessionRoleId");
		session.removeAttribute("ROLEWIXIN");
		session.removeAttribute("industryMenuSystem");// 系统管理
		return "/system/login";
	}

}
