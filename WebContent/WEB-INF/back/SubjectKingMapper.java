package com.wxsoft.drinkTea.platform.system.sysconfig.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKing;

/**
 * @文件名称: SubjectKingMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SubjectKingMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SubjectKingMapper {
	// 根据条件全部查询
	List<SubjectKing> getListBy(SubjectKing clssname);

	// 根据条件分页查询
	List<SubjectKing> getPageListBy(SubjectKing clssname);

	// 查询条数
	int getCountBy(SubjectKing clssname);

	// 根据条件模糊查询全部
	List<SubjectKing> getListLike(SubjectKing clssname);

	// 根据条件模糊分页查询
	List<SubjectKing> getPageListLike(SubjectKing clssname);

	// 查询条数
	int getCountLike(SubjectKing clssname);

	// 根据主键查询实例
	SubjectKing selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SubjectKing selectBy(SubjectKing record);

	// 根据所需插入
	int insert(SubjectKing record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SubjectKing record);

	// 根据所需更新
	int updateByPrimaryKey(SubjectKing record);

	//随机查询count条数据（使用rand函数）
	List<SubjectKing> selectSubjectsRandom(Integer count);
}