package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.ManageProducts;;

/**
 * @文件名称: ManageProductsMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.ManageProductsMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  ManageProductsMapper {
	// 根据条件全部查询
	List<ManageProducts> getListBy(ManageProducts clssname);

	// 根据条件分页查询
	List<ManageProducts> getPageListBy(ManageProducts clssname);

	// 查询条数
	int getCountBy(ManageProducts clssname);

	// 根据条件模糊查询全部
	List<ManageProducts> getListLike(ManageProducts clssname);

	// 根据条件模糊分页查询
	List<ManageProducts> getPageListLike(ManageProducts clssname);

	// 查询条数
	int getCountLike(ManageProducts clssname);

	// 根据主键查询实例
	ManageProducts selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	ManageProducts selectBy(ManageProducts record);

	// 根据所需插入
	int insert(ManageProducts record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(ManageProducts record);

	// 根据所需更新
	int updateByPrimaryKey(ManageProducts record);

}