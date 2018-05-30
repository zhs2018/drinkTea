package com.wxsoft.drinkTea.platform.system.recommendfriend.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.recommendfriend.model.RecommendFriend;


/**
 * @文件名称: RecommendFriendMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.RecommendFriendMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  RecommendFriendMapper {
	// 根据条件全部查询
	List<RecommendFriend> getListBy(RecommendFriend clssname);

	// 根据条件分页查询
	List<RecommendFriend> getPageListBy(RecommendFriend clssname);

	// 查询条数
	int getCountBy(RecommendFriend clssname);

	// 根据条件模糊查询全部
	List<RecommendFriend> getListLike(RecommendFriend clssname);

	// 根据条件模糊分页查询
	List<RecommendFriend> getPageListLike(RecommendFriend clssname);

	// 查询条数
	int getCountLike(RecommendFriend clssname);

	// 根据主键查询实例
	RecommendFriend selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	RecommendFriend selectBy(RecommendFriend record);

	// 根据所需插入
	int insert(RecommendFriend record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(RecommendFriend record);

	// 根据所需更新
	int updateByPrimaryKey(RecommendFriend record);

}