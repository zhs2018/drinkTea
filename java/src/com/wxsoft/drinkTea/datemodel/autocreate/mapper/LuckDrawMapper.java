package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.LuckDraw;;

/**
 * @文件名称: LuckDrawMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.LuckDrawMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  LuckDrawMapper {
	// 根据条件全部查询
	List<LuckDraw> getListBy(LuckDraw clssname);

	// 根据条件分页查询
	List<LuckDraw> getPageListBy(LuckDraw clssname);

	// 查询条数
	int getCountBy(LuckDraw clssname);

	// 根据条件模糊查询全部
	List<LuckDraw> getListLike(LuckDraw clssname);

	// 根据条件模糊分页查询
	List<LuckDraw> getPageListLike(LuckDraw clssname);

	// 查询条数
	int getCountLike(LuckDraw clssname);

	// 根据主键查询实例
	LuckDraw selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	LuckDraw selectBy(LuckDraw record);

	// 根据所需插入
	int insert(LuckDraw record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(LuckDraw record);

	// 根据所需更新
	int updateByPrimaryKey(LuckDraw record);

}