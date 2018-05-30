package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.CashDesc;;

/**
 * @文件名称: CashDescMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.CashDescMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  CashDescMapper {
	// 根据条件全部查询
	List<CashDesc> getListBy(CashDesc clssname);

	// 根据条件分页查询
	List<CashDesc> getPageListBy(CashDesc clssname);

	// 查询条数
	int getCountBy(CashDesc clssname);

	// 根据条件模糊查询全部
	List<CashDesc> getListLike(CashDesc clssname);

	// 根据条件模糊分页查询
	List<CashDesc> getPageListLike(CashDesc clssname);

	// 查询条数
	int getCountLike(CashDesc clssname);

	// 根据主键查询实例
	CashDesc selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	CashDesc selectBy(CashDesc record);

	// 根据所需插入
	int insert(CashDesc record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(CashDesc record);

	// 根据所需更新
	int updateByPrimaryKey(CashDesc record);

}