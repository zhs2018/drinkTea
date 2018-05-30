package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.WebTemporary;;

/**
 * @文件名称: WebTemporaryMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.WebTemporaryMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  WebTemporaryMapper {
	// 根据条件全部查询
	List<WebTemporary> getListBy(WebTemporary clssname);

	// 根据条件分页查询
	List<WebTemporary> getPageListBy(WebTemporary clssname);

	// 查询条数
	int getCountBy(WebTemporary clssname);

	// 根据条件模糊查询全部
	List<WebTemporary> getListLike(WebTemporary clssname);

	// 根据条件模糊分页查询
	List<WebTemporary> getPageListLike(WebTemporary clssname);

	// 查询条数
	int getCountLike(WebTemporary clssname);

	// 根据主键查询实例
	WebTemporary selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	WebTemporary selectBy(WebTemporary record);

	// 根据所需插入
	int insert(WebTemporary record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(WebTemporary record);

	// 根据所需更新
	int updateByPrimaryKey(WebTemporary record);

}