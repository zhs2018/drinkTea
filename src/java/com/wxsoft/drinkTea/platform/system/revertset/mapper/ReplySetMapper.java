package com.wxsoft.drinkTea.platform.system.revertset.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.revertset.model.ReplySet;

/**
 * @文件名称: ReplySetMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.ReplySetMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  ReplySetMapper {
	// 根据条件全部查询
	List<ReplySet> getListBy(ReplySet clssname);

	// 根据条件分页查询
	List<ReplySet> getPageListBy(ReplySet clssname);

	// 查询条数
	int getCountBy(ReplySet clssname);

	// 根据条件模糊查询全部
	List<ReplySet> getListLike(ReplySet clssname);

	// 根据条件模糊分页查询
	List<ReplySet> getPageListLike(ReplySet clssname);

	// 查询条数
	int getCountLike(ReplySet clssname);

	// 根据主键查询实例
	ReplySet selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	ReplySet selectBy(ReplySet record);

	// 根据所需插入
	int insert(ReplySet record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(ReplySet record);

	// 根据所需更新
	int updateByPrimaryKey(ReplySet record);

}