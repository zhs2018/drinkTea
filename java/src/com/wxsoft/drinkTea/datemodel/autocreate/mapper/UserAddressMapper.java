package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.UserAddress;;

/**
 * @文件名称: UserAddressMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.UserAddressMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  UserAddressMapper {
	// 根据条件全部查询
	List<UserAddress> getListBy(UserAddress clssname);

	// 根据条件分页查询
	List<UserAddress> getPageListBy(UserAddress clssname);

	// 查询条数
	int getCountBy(UserAddress clssname);

	// 根据条件模糊查询全部
	List<UserAddress> getListLike(UserAddress clssname);

	// 根据条件模糊分页查询
	List<UserAddress> getPageListLike(UserAddress clssname);

	// 查询条数
	int getCountLike(UserAddress clssname);

	// 根据主键查询实例
	UserAddress selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	UserAddress selectBy(UserAddress record);

	// 根据所需插入
	int insert(UserAddress record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(UserAddress record);

	// 根据所需更新
	int updateByPrimaryKey(UserAddress record);

}