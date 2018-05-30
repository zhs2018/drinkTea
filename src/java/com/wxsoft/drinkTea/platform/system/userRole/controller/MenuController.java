package com.wxsoft.drinkTea.platform.system.userRole.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysMenuMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysMenu;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMenuMapper;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRoleMenu;

/**
 * 主菜单管理
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.userRole.controller
 * 2017年3月25日上午9:26:02
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseAction{

	/** */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	/**
	 * @Title: insertMenu
	 * @date 时间：2017年3月25日上午9:29:17
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description: 添加菜单
	 */
	@RequestMapping(value="/insertMenu", method=RequestMethod.POST)
	public void insertMenu(SysMenu menu,HttpServletResponse response,HttpSession session){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		//先做参数验证
		if(menu == null || StringUtils.isEmpty(menu.getName()) || menu.getParentId() < 0 || StringUtils.isEmpty(menu.getLinkUrl()) || menu.getSort() < 0){
			result.put("status", "0");
			result.put("Message", "添加菜单的信息不全，请添加完全再次尝试");
		}else{
			int a = sysMenuMapper.insert(menu);
			SysMenu p = sysMenuMapper.selectBy(menu);
			//还得向菜单角色表里添加数据
			SysUser user = (SysUser)session.getAttribute("sessionUser");
			Integer roleId = user.getRoleId();
			SysRoleMenu srm = new SysRoleMenu();
			srm.setRoleId(roleId);
			srm.setMenuId(p.getId());
			int b = sysRoleMenuMapper.insert(srm);
			if(a < 1 || b < 1){
				result.put("status", "0");
				result.put("Message", "添加菜单失败，请稍后再试");
			}else{
				result.put("status", "1");
			}
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: deleteMenu
	 * @date 时间：2017年3月25日上午10:17:53
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:删除一个菜单
	 */
	@RequestMapping(value="/deleteMenu", method=RequestMethod.POST)
	public void deleteMenu(@RequestBody SysMenu menu,HttpServletResponse response){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		//先做参数验证
		if(menu == null || menu.getId() < 0){
			result.put("status", "0");
			result.put("Message", "请选择要删除的菜单");
		}else{
			int a = sysMenuMapper.deleteMenu(menu.getId());
			SysRoleMenu record = new SysRoleMenu();
			record.setMenuId(menu.getId());
			int b = sysRoleMenuMapper.delete(record);
			if(a < 1 || b < 1){
				result.put("status", "0");
				result.put("Message", "删除菜单失败，请稍后再试");
			}else{
				result.put("status", "1");
			}
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: updateMenu
	 * @date 时间：2017年3月25日上午10:59:28
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:修改菜单
	 */
	@RequestMapping(value="/updateMenu", method=RequestMethod.POST)
	public void updateMenu(SysMenu menu,HttpServletResponse response){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		//先做参数验证
		if(menu == null || StringUtils.isEmpty(menu.getName()) || menu.getId() < 0  || menu.getSort() < 0){
			result.put("status", "0");
			result.put("Message", "修改菜单的信息不全，请添加完全再次尝试");
		}else{
			int a = sysMenuMapper.updateByPrimaryKey(menu);
			if(a < 1){
				result.put("status", "0");
				result.put("Message", "修改菜单失败，请稍后再试");
			}else{
				result.put("status", "1");
			}
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: insertFirstMenu
	 * @date 时间：2017年3月25日下午12:04:05
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:添加根目录下的一级菜单
	 */
	@RequestMapping(value="/insertFirstMenu", method=RequestMethod.POST)
	public void insertFirstMenu(SysMenu menu,HttpServletResponse response,HttpSession session){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		//先做参数验证
		if(menu == null || StringUtils.isEmpty(menu.getName()) || menu.getSort() < 0){
			result.put("status", "0");
			result.put("Message", "添加菜单的信息不全，请添加完全再次尝试");
		}else{
			menu.setParentId(0);
			//向菜单表里插入数据
			int a = sysMenuMapper.insert(menu);
			SysMenu p = sysMenuMapper.selectBy(menu);
			//还得向菜单角色表里添加数据
			SysUser user = (SysUser)session.getAttribute("sessionUser");
			Integer roleId = user.getRoleId();
			SysRoleMenu srm = new SysRoleMenu();
			srm.setRoleId(roleId);
			srm.setMenuId(p.getId());
			int b = sysRoleMenuMapper.insert(srm);
			if(a < 1 || b < 1){
				result.put("status", "0");
				result.put("Message", "添加菜单失败，请稍后再试");
			}else{
				result.put("status", "1");
			}
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
