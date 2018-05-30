package com.wxsoft.drinkTea.platform.system.userRole.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMapper;
import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMenuMapper;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRole;

@Service
public class RoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Transactional(rollbackFor=Exception.class)
	public Map<String,String> saveRole(String roleName,String menuId,HttpServletResponse response){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		SysRole sysRole = new SysRole();
		sysRole.setRoleName(roleName);
		int a = sysRoleMapper.insert(sysRole);
		SysRole role2 = sysRoleMapper.selectBy(sysRole);
		//将此map对象传到sql里
		Map<String,Object> map = new HashMap<String, Object>();
		String[] menuIds = menuId.split(",");//先将传过来的菜单id拆分，组成一个数组
		map.put("roleId", role2.getId());
		map.put("menuIds", menuIds);
		int b = sysRoleMenuMapper.inByRNameMId(map);
		if(a < 1 || b < 1){
			result.put("status", "0");
			result.put("Message", "添加角色失败，请稍后再试");
		}else if(a > 0 && b > 0){
			result.put("status", "1");
		}
		return result;
	}
}
