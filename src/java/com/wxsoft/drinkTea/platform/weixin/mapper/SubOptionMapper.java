package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.SubOption;

/**
 * @文件名称: SubOptionMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SubOptionMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SubOptionMapper {
	// 根据条件全部查询
	List<SubOption> getListBy(SubOption clssname);

	// 根据条件分页查询
	List<SubOption> getPageListBy(SubOption clssname);

	// 查询条数
	int getCountBy(SubOption clssname);

	// 根据条件模糊查询全部
	List<SubOption> getListLike(SubOption clssname);

	// 根据条件模糊分页查询
	List<SubOption> getPageListLike(SubOption clssname);

	// 查询条数
	int getCountLike(SubOption clssname);

	// 根据主键查询实例
	SubOption selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SubOption selectBy(SubOption record);

	// 根据所需插入
	int insert(SubOption record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SubOption record);

	// 根据所需更新
	int updateByPrimaryKey(SubOption record);

//	int insertParam(Map<String, Object> map);
//
//	int insertshiyan(Map<String, Object> map);

}