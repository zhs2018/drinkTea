package com.wxsoft.drinkTea.platform.system.publicWelfare.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.publicWelfare.model.PublicWelfare;


/**
 * @文件名称: PublicWelfareMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.PublicWelfareMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  PublicWelfareMapper {
	// 根据条件全部查询
	List<PublicWelfare> getListBy(PublicWelfare clssname);

	// 根据条件分页查询
	List<PublicWelfare> getPageListBy(PublicWelfare clssname);

	// 查询条数
	int getCountBy(PublicWelfare clssname);

	// 根据条件模糊查询全部
	List<PublicWelfare> getListLike(PublicWelfare clssname);

	// 根据条件模糊分页查询
	List<PublicWelfare> getPageListLike(PublicWelfare clssname);

	// 查询条数
	int getCountLike(PublicWelfare clssname);

	// 根据主键查询实例
	PublicWelfare selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	PublicWelfare selectBy(PublicWelfare record);

	// 根据所需插入
	int insert(PublicWelfare record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(PublicWelfare record);

	// 根据所需更新
	int updateByPrimaryKey(PublicWelfare record);

}