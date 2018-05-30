package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.ProvinceManage;;

/**
 * @文件名称: ProvinceManageMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.ProvinceManageMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  ProvinceManageMapper {
	// 根据条件全部查询
	List<ProvinceManage> getListBy(ProvinceManage clssname);

	// 根据条件分页查询
	List<ProvinceManage> getPageListBy(ProvinceManage clssname);

	// 查询条数
	int getCountBy(ProvinceManage clssname);

	// 根据条件模糊查询全部
	List<ProvinceManage> getListLike(ProvinceManage clssname);

	// 根据条件模糊分页查询
	List<ProvinceManage> getPageListLike(ProvinceManage clssname);

	// 查询条数
	int getCountLike(ProvinceManage clssname);

	// 根据主键查询实例
	ProvinceManage selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	ProvinceManage selectBy(ProvinceManage record);

	// 根据所需插入
	int insert(ProvinceManage record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(ProvinceManage record);

	// 根据所需更新
	int updateByPrimaryKey(ProvinceManage record);

}