package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.SysRegion;;

/**
 * @文件名称: SysRegionMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.SysRegionMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  SysRegionMapper {
	// 根据条件全部查询
	List<SysRegion> getListBy(SysRegion clssname);

	// 根据条件分页查询
	List<SysRegion> getPageListBy(SysRegion clssname);

	// 查询条数
	int getCountBy(SysRegion clssname);

	// 根据条件模糊查询全部
	List<SysRegion> getListLike(SysRegion clssname);

	// 根据条件模糊分页查询
	List<SysRegion> getPageListLike(SysRegion clssname);

	// 查询条数
	int getCountLike(SysRegion clssname);

	// 根据主键查询实例
	SysRegion selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	SysRegion selectBy(SysRegion record);

	// 根据所需插入
	int insert(SysRegion record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(SysRegion record);

	// 根据所需更新
	int updateByPrimaryKey(SysRegion record);

}