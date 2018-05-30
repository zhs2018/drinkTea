package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * @文件名称: WebUserMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.WebUserMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  WebUserMapper {
	// 根据条件全部查询
	List<WebUser> getListBy(WebUser clssname);

	// 根据条件分页查询
	List<WebUser> getPageListBy(WebUser clssname);

	// 查询条数
	int getCountBy(WebUser clssname);

	// 根据条件模糊查询全部
	List<WebUser> getListLike(WebUser clssname);

	// 根据条件模糊分页查询
	List<WebUser> getPageListLike(WebUser clssname);

	// 查询条数
	int getCountLike(WebUser clssname);

	// 根据主键查询实例
	WebUser selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	WebUser selectBy(WebUser record);

	// 根据所需插入
	int insert(WebUser record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(WebUser record);

	// 根据所需更新
	int updateByPrimaryKey(WebUser record);

}