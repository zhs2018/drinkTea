package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.SubUserAnswer;;

/**
 * @文件名称: SubUserAnswerMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SubUserAnswerMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SubUserAnswerMapper {
	// 根据条件全部查询
	List<SubUserAnswer> getListBy(SubUserAnswer clssname);

	// 根据条件分页查询
	List<SubUserAnswer> getPageListBy(SubUserAnswer clssname);

	// 查询条数
	int getCountBy(SubUserAnswer clssname);

	// 根据条件模糊查询全部
	List<SubUserAnswer> getListLike(SubUserAnswer clssname);

	// 根据条件模糊分页查询
	List<SubUserAnswer> getPageListLike(SubUserAnswer clssname);

	// 查询条数
	int getCountLike(SubUserAnswer clssname);

	// 根据主键查询实例
	SubUserAnswer selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SubUserAnswer selectBy(SubUserAnswer record);

	// 根据所需插入
	int insert(SubUserAnswer record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SubUserAnswer record);

	// 根据所需更新
	int updateByPrimaryKey(SubUserAnswer record);

}