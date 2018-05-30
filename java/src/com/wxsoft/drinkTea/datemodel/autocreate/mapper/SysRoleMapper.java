package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.SysRole;;

/**
 * @文件名称: SysRoleMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SysRoleMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SysRoleMapper {
	// 根据条件全部查询
	List<SysRole> getListBy(SysRole clssname);

	// 根据条件分页查询
	List<SysRole> getPageListBy(SysRole clssname);

	// 查询条数
	int getCountBy(SysRole clssname);

	// 根据条件模糊查询全部
	List<SysRole> getListLike(SysRole clssname);

	// 根据条件模糊分页查询
	List<SysRole> getPageListLike(SysRole clssname);

	// 查询条数
	int getCountLike(SysRole clssname);

	// 根据主键查询实例
	SysRole selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SysRole selectBy(SysRole record);

	// 根据所需插入
	int insert(SysRole record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SysRole record);

	// 根据所需更新
	int updateByPrimaryKey(SysRole record);

}