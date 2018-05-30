package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.RefundMessage;;

/**
 * @文件名称: RefundMessageMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.RefundMessageMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  RefundMessageMapper {
	// 根据条件全部查询
	List<RefundMessage> getListBy(RefundMessage clssname);

	// 根据条件分页查询
	List<RefundMessage> getPageListBy(RefundMessage clssname);

	// 查询条数
	int getCountBy(RefundMessage clssname);

	// 根据条件模糊查询全部
	List<RefundMessage> getListLike(RefundMessage clssname);

	// 根据条件模糊分页查询
	List<RefundMessage> getPageListLike(RefundMessage clssname);

	// 查询条数
	int getCountLike(RefundMessage clssname);

	// 根据主键查询实例
	RefundMessage selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	RefundMessage selectBy(RefundMessage record);

	// 根据所需插入
	int insert(RefundMessage record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(RefundMessage record);

	// 根据所需更新
	int updateByPrimaryKey(RefundMessage record);

}