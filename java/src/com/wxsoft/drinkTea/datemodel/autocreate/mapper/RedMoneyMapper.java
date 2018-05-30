package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.RedMoney;;

/**
 * @文件名称: RedMoneyMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.RedMoneyMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  RedMoneyMapper {
	// 根据条件全部查询
	List<RedMoney> getListBy(RedMoney clssname);

	// 根据条件分页查询
	List<RedMoney> getPageListBy(RedMoney clssname);

	// 查询条数
	int getCountBy(RedMoney clssname);

	// 根据条件模糊查询全部
	List<RedMoney> getListLike(RedMoney clssname);

	// 根据条件模糊分页查询
	List<RedMoney> getPageListLike(RedMoney clssname);

	// 查询条数
	int getCountLike(RedMoney clssname);

	// 根据主键查询实例
	RedMoney selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	RedMoney selectBy(RedMoney record);

	// 根据所需插入
	int insert(RedMoney record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(RedMoney record);

	// 根据所需更新
	int updateByPrimaryKey(RedMoney record);

}