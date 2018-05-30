package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.SmallPackage;;

/**
 * @文件名称: SmallPackageMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SmallPackageMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SmallPackageMapper {
	// 根据条件全部查询
	List<SmallPackage> getListBy(SmallPackage clssname);

	// 根据条件分页查询
	List<SmallPackage> getPageListBy(SmallPackage clssname);

	// 查询条数
	int getCountBy(SmallPackage clssname);

	// 根据条件模糊查询全部
	List<SmallPackage> getListLike(SmallPackage clssname);

	// 根据条件模糊分页查询
	List<SmallPackage> getPageListLike(SmallPackage clssname);

	// 查询条数
	int getCountLike(SmallPackage clssname);

	// 根据主键查询实例
	SmallPackage selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SmallPackage selectBy(SmallPackage record);

	// 根据所需插入
	int insert(SmallPackage record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SmallPackage record);

	// 根据所需更新
	int updateByPrimaryKey(SmallPackage record);

}