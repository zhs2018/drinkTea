package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.SysMenu;;

/**
 * @文件名称: SysMenuMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SysMenuMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SysMenuMapper {
	// 根据条件全部查询
	List<SysMenu> getListBy(SysMenu clssname);

	// 根据条件分页查询
	List<SysMenu> getPageListBy(SysMenu clssname);

	// 查询条数
	int getCountBy(SysMenu clssname);

	// 根据条件模糊查询全部
	List<SysMenu> getListLike(SysMenu clssname);

	// 根据条件模糊分页查询
	List<SysMenu> getPageListLike(SysMenu clssname);

	// 查询条数
	int getCountLike(SysMenu clssname);

	// 根据主键查询实例
	SysMenu selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SysMenu selectBy(SysMenu record);

	// 根据所需插入
	int insert(SysMenu record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SysMenu record);

	// 根据所需更新
	int updateByPrimaryKey(SysMenu record);

}