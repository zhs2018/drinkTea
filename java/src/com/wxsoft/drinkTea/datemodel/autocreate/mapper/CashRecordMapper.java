package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.CashRecord;;

/**
 * @文件名称: CashRecordMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.CashRecordMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  CashRecordMapper {
	// 根据条件全部查询
	List<CashRecord> getListBy(CashRecord clssname);

	// 根据条件分页查询
	List<CashRecord> getPageListBy(CashRecord clssname);

	// 查询条数
	int getCountBy(CashRecord clssname);

	// 根据条件模糊查询全部
	List<CashRecord> getListLike(CashRecord clssname);

	// 根据条件模糊分页查询
	List<CashRecord> getPageListLike(CashRecord clssname);

	// 查询条数
	int getCountLike(CashRecord clssname);

	// 根据主键查询实例
	CashRecord selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	CashRecord selectBy(CashRecord record);

	// 根据所需插入
	int insert(CashRecord record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(CashRecord record);

	// 根据所需更新
	int updateByPrimaryKey(CashRecord record);

}