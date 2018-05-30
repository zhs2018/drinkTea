package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.UserFriend;

/**
 * @文件名称: UserFriendMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.UserFriendMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  UserFriendMapper {
	// 根据条件全部查询
	List<UserFriend> getListBy(UserFriend clssname);

	// 根据条件分页查询
	List<UserFriend> getPageListBy(UserFriend clssname);

	// 查询条数
	int getCountBy(UserFriend clssname);

	// 根据条件模糊查询全部
	List<UserFriend> getListLike(UserFriend clssname);

	// 根据条件模糊分页查询
	List<UserFriend> getPageListLike(UserFriend clssname);

	// 查询条数
	int getCountLike(UserFriend clssname);

	// 根据主键查询实例
	UserFriend selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	UserFriend selectBy(UserFriend record);

	// 根据所需插入
	int insert(UserFriend record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(UserFriend record);

	// 根据所需更新
	int updateByPrimaryKey(UserFriend record);

}