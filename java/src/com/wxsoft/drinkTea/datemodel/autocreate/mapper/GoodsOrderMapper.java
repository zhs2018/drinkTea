package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.GoodsOrder;;

/**
 * @文件名称: GoodsOrderMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.GoodsOrderMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  GoodsOrderMapper {
	// 根据条件全部查询
	List<GoodsOrder> getListBy(GoodsOrder clssname);

	// 根据条件分页查询
	List<GoodsOrder> getPageListBy(GoodsOrder clssname);

	// 查询条数
	int getCountBy(GoodsOrder clssname);

	// 根据条件模糊查询全部
	List<GoodsOrder> getListLike(GoodsOrder clssname);

	// 根据条件模糊分页查询
	List<GoodsOrder> getPageListLike(GoodsOrder clssname);

	// 查询条数
	int getCountLike(GoodsOrder clssname);

	// 根据主键查询实例
	GoodsOrder selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	GoodsOrder selectBy(GoodsOrder record);

	// 根据所需插入
	int insert(GoodsOrder record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(GoodsOrder record);

	// 根据所需更新
	int updateByPrimaryKey(GoodsOrder record);

}