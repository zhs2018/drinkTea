package com.wxsoft.drinkTea.platform.system.login.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.login.model.SysUser;

/**
 * @文件名称: SysUserMapper.java
 * @类路径: com.wxsoft.datamodel.autocreate.mapper.SysUserMapper;
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

	// 根据主键更新
	int updateByPrimaryKey(SysUser record);

    //根据员工编号查询
	List<SysUser> getPageListBysql(SysUser recode);
    //根据员工编号查询并分不同的状态
	List<SysUser> getPageListByOrderState(SysUser recode);
    //根据员工编号查询根据时间查询
    List<SysUser> getPageListByTime(SysUser recode);
	//根据员工编号查询根据不同时间不同状态查询
	List<SysUser> getPageListByTimeOrState(SysUser recode);

}