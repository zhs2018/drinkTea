package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.IdeaTickling;


/**
 * @文件名称: IdeaTicklingMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.IdeaTicklingMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  IdeaTicklingMapper {
	// 根据条件全部查询
	List<IdeaTickling> getListBy(IdeaTickling clssname);

	// 根据条件分页查询
	List<IdeaTickling> getPageListBy(IdeaTickling clssname);

	// 查询条数
	int getCountBy(IdeaTickling clssname);

	// 根据条件模糊查询全部
	List<IdeaTickling> getListLike(IdeaTickling clssname);

	// 根据条件模糊分页查询
	List<IdeaTickling> getPageListLike(IdeaTickling clssname);

	// 查询条数
	int getCountLike(IdeaTickling clssname);

	// 根据主键查询实例
	IdeaTickling selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	IdeaTickling selectBy(IdeaTickling record);

	// 根据所需插入
	int insert(IdeaTickling record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(IdeaTickling record);

	// 根据所需更新
	int updateByPrimaryKey(IdeaTickling record);

}