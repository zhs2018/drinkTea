package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.CashOut;;

/**
 * @文件名称: CashOutMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.CashOutMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  CashOutMapper {
	// 根据条件全部查询
	List<CashOut> getListBy(CashOut clssname);

	// 根据条件分页查询
	List<CashOut> getPageListBy(CashOut clssname);

	// 查询条数
	int getCountBy(CashOut clssname);

	// 根据条件模糊查询全部
	List<CashOut> getListLike(CashOut clssname);

	// 根据条件模糊分页查询
	List<CashOut> getPageListLike(CashOut clssname);

	// 查询条数
	int getCountLike(CashOut clssname);

	// 根据主键查询实例
	CashOut selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	CashOut selectBy(CashOut record);

	// 根据所需插入
	int insert(CashOut record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(CashOut record);

	// 根据所需更新
	int updateByPrimaryKey(CashOut record);

}