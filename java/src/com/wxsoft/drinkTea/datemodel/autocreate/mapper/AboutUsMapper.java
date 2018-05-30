package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.AboutUs;;

/**
 * @文件名称: AboutUsMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.AboutUsMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  AboutUsMapper {
	// 根据条件全部查询
	List<AboutUs> getListBy(AboutUs clssname);

	// 根据条件分页查询
	List<AboutUs> getPageListBy(AboutUs clssname);

	// 查询条数
	int getCountBy(AboutUs clssname);

	// 根据条件模糊查询全部
	List<AboutUs> getListLike(AboutUs clssname);

	// 根据条件模糊分页查询
	List<AboutUs> getPageListLike(AboutUs clssname);

	// 查询条数
	int getCountLike(AboutUs clssname);

	// 根据主键查询实例
	AboutUs selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	AboutUs selectBy(AboutUs record);

	// 根据所需插入
	int insert(AboutUs record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(AboutUs record);

	// 根据所需更新
	int updateByPrimaryKey(AboutUs record);

}