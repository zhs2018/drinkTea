package com.wxsoft.drinkTea.platform.system.sysconfig.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;

/**
 * @文件名称: SubjectKuMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SubjectKuMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SubjectKuMapper {
	// 根据条件全部查询
	List<SubjectKu> getListBy(SubjectKu clssname);

	// 根据条件分页查询
	List<SubjectKu> getPageListBy(SubjectKu clssname);

	// 查询条数
	int getCountBy(SubjectKu clssname);

	// 根据条件模糊查询全部
	List<SubjectKu> getListLike(SubjectKu clssname);

	// 根据条件模糊分页查询
	List<SubjectKu> getPageListLike(SubjectKu clssname);

	// 查询条数
	int getCountLike(SubjectKu clssname);

	// 根据主键查询实例
	SubjectKu selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SubjectKu selectBy(SubjectKu record);

	// 根据所需插入
	int insert(SubjectKu record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SubjectKu record);

	// 根据所需更新
	int updateByPrimaryKey(SubjectKu record);

}