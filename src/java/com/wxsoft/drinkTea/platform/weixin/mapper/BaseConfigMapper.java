package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.BaseConfig;


/**
 * @文件名称: BaseConfigMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.BaseConfigMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  BaseConfigMapper {
	// 根据条件全部查询
	List<BaseConfig> getListBy(BaseConfig clssname);

	// 根据条件分页查询
	List<BaseConfig> getPageListBy(BaseConfig clssname);

	// 查询条数
	int getCountBy(BaseConfig clssname);

	// 根据条件模糊查询全部
	List<BaseConfig> getListLike(BaseConfig clssname);

	// 根据条件模糊分页查询
	List<BaseConfig> getPageListLike(BaseConfig clssname);

	// 查询条数
	int getCountLike(BaseConfig clssname);

	// 根据主键查询实例
	BaseConfig selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	BaseConfig selectBy(BaseConfig record);

	// 根据所需插入
	int insert(BaseConfig record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(BaseConfig record);

	// 根据所需更新
	int updateByPrimaryKey(BaseConfig record);

}