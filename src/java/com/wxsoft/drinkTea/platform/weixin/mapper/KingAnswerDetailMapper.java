package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.KingAnswerDetail;

/**
 * @文件名称: KingAnswerDetailMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.KingAnswerDetailMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  KingAnswerDetailMapper {
	// 根据条件全部查询
	List<KingAnswerDetail> getListBy(KingAnswerDetail clssname);

	// 根据条件分页查询
	List<KingAnswerDetail> getPageListBy(KingAnswerDetail clssname);

	// 查询条数
	int getCountBy(KingAnswerDetail clssname);

	// 根据条件模糊查询全部
	List<KingAnswerDetail> getListLike(KingAnswerDetail clssname);

	// 根据条件模糊分页查询
	List<KingAnswerDetail> getPageListLike(KingAnswerDetail clssname);

	// 查询条数
	int getCountLike(KingAnswerDetail clssname);

	// 根据主键查询实例
	KingAnswerDetail selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	KingAnswerDetail selectBy(KingAnswerDetail record);

	// 根据所需插入
	int insert(KingAnswerDetail record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(KingAnswerDetail record);

	// 根据所需更新
	int updateByPrimaryKey(KingAnswerDetail record);

}