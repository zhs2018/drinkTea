package com.wxsoft.drinkTea.platform.system.shareConfig.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.shareConfig.model.ModelMessage;


/**
 * @文件名称: ModelMessageMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.ModelMessageMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  ModelMessageMapper {
	// 根据条件全部查询
	List<ModelMessage> getListBy(ModelMessage clssname);

	// 根据条件分页查询
	List<ModelMessage> getPageListBy(ModelMessage clssname);

	// 查询条数
	int getCountBy(ModelMessage clssname);

	// 根据条件模糊查询全部
	List<ModelMessage> getListLike(ModelMessage clssname);

	// 根据条件模糊分页查询
	List<ModelMessage> getPageListLike(ModelMessage clssname);

	// 查询条数
	int getCountLike(ModelMessage clssname);

	// 根据主键查询实例
	ModelMessage selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	ModelMessage selectBy(ModelMessage record);

	// 根据所需插入
	int insert(ModelMessage record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(ModelMessage record);

	// 根据所需更新
	int updateByPrimaryKey(ModelMessage record);

}