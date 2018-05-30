package com.wxsoft.drinkTea.platform.system.refundtime.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.refundtime.model.RefundTime;


/**
 * @文件名称: RefundTimeMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.RefundTimeMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  RefundTimeMapper {
	// 根据条件全部查询
	List<RefundTime> getListBy(RefundTime clssname);

	// 根据条件分页查询
	List<RefundTime> getPageListBy(RefundTime clssname);

	// 查询条数
	int getCountBy(RefundTime clssname);

	// 根据条件模糊查询全部
	List<RefundTime> getListLike(RefundTime clssname);

	// 根据条件模糊分页查询
	List<RefundTime> getPageListLike(RefundTime clssname);

	// 查询条数
	int getCountLike(RefundTime clssname);

	// 根据主键查询实例
	RefundTime selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	RefundTime selectBy(RefundTime record);

	// 根据所需插入
	int insert(RefundTime record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(RefundTime record);

	// 根据所需更新
	int updateByPrimaryKey(RefundTime record);

}