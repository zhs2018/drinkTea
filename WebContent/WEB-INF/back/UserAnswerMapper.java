package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.UserAnswer;

/**
 * @文件名称: UserAnswerMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.UserAnswerMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  UserAnswerMapper {
	// 根据条件全部查询
	List<UserAnswer> getListBy(UserAnswer clssname);

	// 根据条件分页查询
	List<UserAnswer> getPageListBy(UserAnswer clssname);

	// 查询条数
	int getCountBy(UserAnswer clssname);

	// 根据条件模糊查询全部
	List<UserAnswer> getListLike(UserAnswer clssname);

	// 根据条件模糊分页查询
	List<UserAnswer> getPageListLike(UserAnswer clssname);

	// 查询条数
	int getCountLike(UserAnswer clssname);

	// 根据主键查询实例
	UserAnswer selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	UserAnswer selectBy(UserAnswer record);

	// 根据所需插入
	int insert(UserAnswer record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(UserAnswer record);

	// 根据所需更新
	int updateByPrimaryKey(UserAnswer record);

	int setAnswerType(UserAnswer record);

}