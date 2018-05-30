package com.wxsoft.drinkTea.platform.system.wxmenu.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.wxmenu.model.WxMenu;

/**
 * @文件名称: WxMenuMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.WxMenuMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  WxMenuMapper {
	// 根据条件全部查询
	List<WxMenu> getListBy(WxMenu clssname);

	// 根据条件分页查询
	List<WxMenu> getPageListBy(WxMenu clssname);

	// 查询条数
	int getCountBy(WxMenu clssname);

	// 根据条件模糊查询全部
	List<WxMenu> getListLike(WxMenu clssname);

	// 根据条件模糊分页查询
	List<WxMenu> getPageListLike(WxMenu clssname);

	// 查询条数
	int getCountLike(WxMenu clssname);

	// 根据主键查询实例
	WxMenu selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	WxMenu selectBy(WxMenu record);

	// 根据所需插入
	int insert(WxMenu record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(WxMenu record);

	// 根据所需更新
	int updateByPrimaryKey(WxMenu record);

}