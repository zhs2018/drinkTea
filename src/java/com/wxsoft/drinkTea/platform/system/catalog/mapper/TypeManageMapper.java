package com.wxsoft.drinkTea.platform.system.catalog.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.catalog.model.TypeManage;

/**
 * @文件名称: TypeManageMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.TypeManageMapper; @作者： @公司：
 */
@Repository
public interface TypeManageMapper {
	// 根据条件全部查询
	List<TypeManage> getListBy(TypeManage clssname);

	// 根据条件分页查询
	List<TypeManage> getPageListBy(TypeManage clssname);

	// 查询条数
	int getCountBy(TypeManage clssname);

	// 根据条件模糊查询全部
	List<TypeManage> getListLike(TypeManage clssname);

	// 根据条件模糊分页查询
	List<TypeManage> getPageListLike(TypeManage clssname);

	// 查询条数
	int getCountLike(TypeManage clssname);

	// 根据主键查询实例
	TypeManage selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	TypeManage selectBy(TypeManage record);

	// 根据所需插入
	int insert(TypeManage record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(TypeManage record);

	// 根据所需更新
	int updateByPrimaryKey(TypeManage record);
	/* int updateByPrimaryKey(@Param(value="id")Integer id); */

}