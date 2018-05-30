package com.wxsoft.drinkTea.platform.system.userRole.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.ConvertPassword;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMapper;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRole;
import com.wxsoft.drinkTea.platform.system.userRole.service.SysUserService;

/**
 * 用户管理
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.userRole.controller
 * 2017年3月22日上午10:09:44
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseAction{

	/** */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleMapper sysRoleMapper;


	/**
	 * @Title: userList
	 * @date 时间：2017年3月22日上午10:34:34
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:跳转到用户列表页面
	 */
	@RequestMapping(value = "/list")
	public ModelAndView userList(HttpSession session,SysUser user){
		ModelAndView mav = new ModelAndView();
		//跳转到用户列表页面
		mav.setViewName("/system/UserRole/UserList");
		//携带用户列表的数据
		List<SysUser> userList = sysUserMapper.getPageListBy(user);
		session.setAttribute("sysUserList", userList);
		mav.addObject("su",user);
		return mav;
	}

	/**
	 * @Title: addUser
	 * @date 时间：2017年3月22日下午3:29:31
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:跳转到添加员工的页面
	 */
	@RequestMapping(value = "/addUser")
	public ModelAndView  addUser(){
   ModelAndView mv = new ModelAndView();
   SysRole sr = new SysRole();
   List<SysRole> roleList = sysRoleMapper.getListBy(sr);
    mv.addObject("roleList",roleList);
    mv.setViewName("/system/UserRole/addUser");
		return mv;
	}

	/**
	 * @Title: saveUser
	 * @date 时间：2017年3月22日下午4:01:40
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:保存添加的员工信息，在和前台交互的时候，方法的成功与否靠responseAjax方法来传递。提交的状态值成功就是1，
	 * 				不成功就是0，再把错误信息传过去
	 *
	 * 2017-4-18 yzy修改
	 * @修改内容 增加员工工号
	 */
	@RequestMapping(value = "/saveUser")
	public void saveUser(HttpServletResponse response,HttpServletRequest request,SysUser user){
		Map<String,String> map = new HashMap<String,String>();
		logger.info(user.toString());
		if(user == null || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getJobNumber()) || StringUtils.isEmpty(user.getRoleId().toString())){
			map.put("status", "0");
			map.put("Message", "提交的员工信息不全");
		}else{
			try {
				SysRole sysRole = sysRoleMapper.selectByPrimaryKey(user.getRoleId());
				user.setRoleName(sysRole.getRoleName());
				Long.parseLong(user.getJobNumber());
				String password = ConvertPassword.getMyPassword(user.getPassword());
				user.setPassword(password);
				int result = sysUserMapper.insert(user);
				if(!(result > 0)){
					map.put("status", "0");
					map.put("Message", "插入员工信息失败，请稍后再试");
				}else{
					map.put("status", "1");
				}
			} catch (NumberFormatException e) {
				map.put("status", "0");
				map.put("Message", "输入的员工工号不正确！");
				logger.info("员工工号不合法");
			}
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: editUser
	 * @date 时间：2017年3月23日上午8:44:10
	 * @author 作者： 王桐睿
	 * @return 返回类型：String
	 * @Description:跳转到编辑页面
	 */
	@RequestMapping(value = "/editUser/{id}")
	public String editUser(@PathVariable Integer id,Model model){
		SysUser user = sysUserMapper.selectByPrimaryKey(id);
		model.addAttribute("user", user);
		 SysRole sr = new SysRole();
		   List<SysRole> roleList = sysRoleMapper.getListBy(sr);
		   model.addAttribute("roleList",roleList);
		return "/system/UserRole/editUser";
	}

	/**
	 * @Title: updateUser
	 * @date 时间：2017年3月23日上午9:19:02
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description: 保存修改员工的信息
	 */
	@RequestMapping(value = "/updateUser")
	public void updateUser(HttpServletResponse response,HttpServletRequest request,SysUser user){
		Map<String,String> map = new HashMap<String,String>();
		if(user == null || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getRoleId().toString())){
			map.put("status", "0");
			map.put("Message", "提交的员工信息不全");
		}else{
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(user.getRoleId());
			user.setRoleName(sysRole.getRoleName());
			String password = ConvertPassword.getMyPassword(user.getPassword());
			user.setPassword(password);
			int result = sysUserMapper.updateByPrimaryKey(user);
			if(!(result > 0)){
				map.put("status", "0");
				map.put("Message", "修改员工信息失败，请稍后再试");
			}else{
				map.put("status", "1");
			}
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: deleteUser
	 * @date 时间：2017年3月23日上午9:54:52
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description: 删除一个员工。这里是逻辑删除，没有物理删除
	 *
	 * @修改时间:2017.4.18
	 * @修改内容:添加会员转移
	 */
	@RequestMapping(value = "/deleteUser/{id}")
	public void deleteUser(@PathVariable Integer id,String jobNumber , HttpServletResponse response){
		Map<String,String> map = new HashMap<String,String>();
		logger.info("转移工号："+jobNumber);
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		if(StringUtils.isEmpty(jobNumber) || sysUser==null || sysUser.getJobNumber().equals(jobNumber)){
			map.put("status", "0");
			map.put("Message", "请输入正确的员工工号！");
		}else{
			SysUser user = new SysUser();
			user.setJobNumber(jobNumber);
			user = sysUserMapper.selectBy(user);
			if(user == null){
				map.put("status", "0");
				map.put("Message", "该工号不存在！");
			}else{
				boolean flag = sysUserService.deleteUser(sysUser,user);
				if(!flag){
					map.put("status", "0");
					map.put("Message", "删除员工信息失败，请稍后再试");
				}else{
					map.put("status", "1");
				}
			}
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
