package com.wxsoft.drinkTea.platform.system.userRole.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.login.model.SysMenu;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRoleMenu;

/**
 * 角色和菜单关联的mapper文件
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.userRole.mapper
 * 2017年3月23日下午4:30:39
 */
@Repository
public interface  SysRoleMenuMapper {
	// 根据条件全部查询
	List<SysRoleMenu> getListBy(SysRoleMenu clssname);

	// 根据条件分页查询
	List<SysRoleMenu> getPageListBy(SysRoleMenu clssname);

	// 查询条数
	int getCountBy(SysRoleMenu clssname);

	// 根据条件模糊查询全部
	List<SysRoleMenu> getListLike(SysRoleMenu clssname);

	// 根据条件模糊分页查询
	List<SysRoleMenu> getPageListLike(SysRoleMenu clssname);

	// 查询条数
	int getCountLike(SysRoleMenu clssname);

	// 根据主键查询实例
	SysRoleMenu selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SysRoleMenu selectBy(SysRoleMenu record);

	// 根据所需插入
	int insert(SysRoleMenu record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SysRoleMenu record);

	// 根据所需更新
	int updateByPrimaryKey(SysRoleMenu record);

	//根据权限id查询所有的相应的菜单
	List<SysMenu> getMenuByRoleId(SysRoleMenu param);

	//根据roleId和许多个menuId插入
	int inByRNameMId(Map<String,Object> map);

}