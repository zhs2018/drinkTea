package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.KingUserAnswer;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * @文件名称: KingUserAnswerMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.KingUserAnswerMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  KingUserAnswerMapper {
	// 根据条件全部查询
	List<KingUserAnswer> getListBy(KingUserAnswer clssname);

	// 根据条件分页查询
	List<KingUserAnswer> getPageListBy(KingUserAnswer clssname);

	// 查询条数
	int getCountBy(KingUserAnswer clssname);

	// 根据条件模糊查询全部
	List<KingUserAnswer> getListLike(KingUserAnswer clssname);

	// 根据条件模糊分页查询
	List<KingUserAnswer> getPageListLike(KingUserAnswer clssname);

	// 查询条数
	int getCountLike(KingUserAnswer clssname);

	// 根据主键查询实例
	KingUserAnswer selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	KingUserAnswer selectBy(KingUserAnswer record);

	// 根据所需插入
	int insert(KingUserAnswer record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(KingUserAnswer record);

	// 根据所需更新
	int updateByPrimaryKey(KingUserAnswer record);

	//查询排名
	Integer getRankingBy(KingUserAnswer record);

	Integer getLikeRankingBy(KingUserAnswer userAnswer);



}