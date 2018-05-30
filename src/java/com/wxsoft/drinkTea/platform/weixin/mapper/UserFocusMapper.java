package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.UserFocus;

/**
 * @文件名称: UserFocusMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.UserFocusMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  UserFocusMapper {
	// 根据条件全部查询
	List<UserFocus> getListBy(UserFocus clssname);

	// 根据条件分页查询
	List<UserFocus> getPageListBy(UserFocus clssname);

	// 查询条数
	int getCountBy(UserFocus clssname);

	// 根据条件模糊查询全部
	List<UserFocus> getListLike(UserFocus clssname);

	// 根据条件模糊分页查询
	List<UserFocus> getPageListLike(UserFocus clssname);

	// 查询条数
	int getCountLike(UserFocus clssname);

	// 根据主键查询实例
	UserFocus selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	UserFocus selectBy(UserFocus record);

	// 根据所需插入
	int insert(UserFocus record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(UserFocus record);

	// 根据所需更新
	int updateByPrimaryKey(UserFocus record);

}