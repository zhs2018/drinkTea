package com.wxsoft.drinkTea.platform.system.login.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.login.model.SysMenu;

/**
 * @文件名称: SysMenuMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SysMenuMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SysMenuMapper {
	int deleteByPrimaryKey(Integer id);

	SysMenu selectBy(SysMenu param);

	int insert(SysMenu record);

	/**
	 * 获取一级菜单
	 * @param example
	 * @return
	 */
	List<SysMenu> getAllFirstLevelSysMenu(SysMenu example);

	/**
	 * 获取二级菜单
	 * @param example
	 * @return
	 */
	List<SysMenu> getAllSecondLevelSysMenu(Map<String,Object> map);

	SysMenu selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysMenu record);

	int updateByPrimaryKey(SysMenu record);

	List<SysMenu> getListBy(SysMenu example);

	//删除一个菜单
	int deleteMenu(Integer id);

}