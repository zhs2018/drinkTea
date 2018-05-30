package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.DrawTime;;

/**
 * @文件名称: DrawTimeMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.DrawTimeMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  DrawTimeMapper {
	// 根据条件全部查询
	List<DrawTime> getListBy(DrawTime clssname);

	// 根据条件分页查询
	List<DrawTime> getPageListBy(DrawTime clssname);

	// 查询条数
	int getCountBy(DrawTime clssname);

	// 根据条件模糊查询全部
	List<DrawTime> getListLike(DrawTime clssname);

	// 根据条件模糊分页查询
	List<DrawTime> getPageListLike(DrawTime clssname);

	// 查询条数
	int getCountLike(DrawTime clssname);

	// 根据主键查询实例
	DrawTime selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	DrawTime selectBy(DrawTime record);

	// 根据所需插入
	int insert(DrawTime record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(DrawTime record);

	// 根据所需更新
	int updateByPrimaryKey(DrawTime record);

}