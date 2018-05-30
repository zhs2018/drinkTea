package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.SeasonManage;;

/**
 * @文件名称: SeasonManageMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SeasonManageMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SeasonManageMapper {
	// 根据条件全部查询
	List<SeasonManage> getListBy(SeasonManage clssname);

	// 根据条件分页查询
	List<SeasonManage> getPageListBy(SeasonManage clssname);

	// 查询条数
	int getCountBy(SeasonManage clssname);

	// 根据条件模糊查询全部
	List<SeasonManage> getListLike(SeasonManage clssname);

	// 根据条件模糊分页查询
	List<SeasonManage> getPageListLike(SeasonManage clssname);

	// 查询条数
	int getCountLike(SeasonManage clssname);

	// 根据主键查询实例
	SeasonManage selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SeasonManage selectBy(SeasonManage record);

	// 根据所需插入
	int insert(SeasonManage record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SeasonManage record);

	// 根据所需更新
	int updateByPrimaryKey(SeasonManage record);

}