package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.SysUser;;

/**
 * @文件名称: SysUserMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SysUserMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SysUserMapper {
	// 根据条件全部查询
	List<SysUser> getListBy(SysUser clssname);

	// 根据条件分页查询
	List<SysUser> getPageListBy(SysUser clssname);

	// 查询条数
	int getCountBy(SysUser clssname);

	// 根据条件模糊查询全部
	List<SysUser> getListLike(SysUser clssname);

	// 根据条件模糊分页查询
	List<SysUser> getPageListLike(SysUser clssname);

	// 查询条数
	int getCountLike(SysUser clssname);

	// 根据主键查询实例
	SysUser selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SysUser selectBy(SysUser record);

	// 根据所需插入
	int insert(SysUser record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SysUser record);

	// 根据所需更新
	int updateByPrimaryKey(SysUser record);

}