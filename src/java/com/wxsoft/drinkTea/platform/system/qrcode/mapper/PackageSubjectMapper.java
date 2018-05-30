package com.wxsoft.drinkTea.platform.system.qrcode.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.qrcode.model.PackageSubject;

/**
 * @文件名称: PackageSubjectMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.PackageSubjectMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  PackageSubjectMapper {
	// 根据条件全部查询
	List<PackageSubject> getListBy(PackageSubject clssname);

	// 根据条件分页查询
	List<PackageSubject> getPageListBy(PackageSubject clssname);

	// 查询条数
	int getCountBy(PackageSubject clssname);

	// 根据条件模糊查询全部
	List<PackageSubject> getListLike(PackageSubject clssname);

	// 根据条件模糊分页查询
	List<PackageSubject> getPageListLike(PackageSubject clssname);

	// 查询条数
	int getCountLike(PackageSubject clssname);

	// 根据主键查询实例
	PackageSubject selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	PackageSubject selectBy(PackageSubject record);

	// 根据所需插入
	int insert(PackageSubject record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(PackageSubject record);

	// 根据所需更新
	int updateByPrimaryKey(PackageSubject record);

}