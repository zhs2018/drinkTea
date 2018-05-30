package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.WebsTeb;;

/**
 * @文件名称: WebsTebMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.WebsTebMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  WebsTebMapper {
	// 根据条件全部查询
	List<WebsTeb> getListBy(WebsTeb clssname);

	// 根据条件分页查询
	List<WebsTeb> getPageListBy(WebsTeb clssname);

	// 查询条数
	int getCountBy(WebsTeb clssname);

	// 根据条件模糊查询全部
	List<WebsTeb> getListLike(WebsTeb clssname);

	// 根据条件模糊分页查询
	List<WebsTeb> getPageListLike(WebsTeb clssname);

	// 查询条数
	int getCountLike(WebsTeb clssname);

	// 根据主键查询实例
	WebsTeb selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	WebsTeb selectBy(WebsTeb record);

	// 根据所需插入
	int insert(WebsTeb record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(WebsTeb record);

	// 根据所需更新
	int updateByPrimaryKey(WebsTeb record);

}