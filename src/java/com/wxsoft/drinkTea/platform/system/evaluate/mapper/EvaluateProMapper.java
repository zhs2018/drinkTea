package com.wxsoft.drinkTea.platform.system.evaluate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.evaluate.model.EvaluatePro;

/**
 * @文件名称: EvaluateProMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.EvaluateProMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  EvaluateProMapper {
	// 根据条件全部查询
	List<EvaluatePro> getListBy(EvaluatePro clssname);

	// 根据条件分页查询
	List<EvaluatePro> getPageListBy(EvaluatePro clssname);

	// 查询条数
	int getCountBy(EvaluatePro clssname);

	// 根据条件模糊查询全部
	List<EvaluatePro> getListLike(EvaluatePro clssname);

	// 根据条件模糊分页查询
	List<EvaluatePro> getPageListLike(EvaluatePro clssname);

	// 查询条数
	int getCountLike(EvaluatePro clssname);

	// 根据主键查询实例
	EvaluatePro selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	EvaluatePro selectBy(EvaluatePro record);

	// 根据所需插入
	int insert(EvaluatePro record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(EvaluatePro record);

	// 根据所需更新
	int updateByPrimaryKey(EvaluatePro record);

}