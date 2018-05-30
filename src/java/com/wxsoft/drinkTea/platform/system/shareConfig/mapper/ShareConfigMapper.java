package com.wxsoft.drinkTea.platform.system.shareConfig.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.shareConfig.model.ShareConfig;


/**
 * @文件名称: ShareConfigMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.ShareConfigMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  ShareConfigMapper {
	// 根据条件全部查询
	List<ShareConfig> getListBy(ShareConfig clssname);

	// 根据条件分页查询
	List<ShareConfig> getPageListBy(ShareConfig clssname);

	// 查询条数
	int getCountBy(ShareConfig clssname);

	// 根据条件模糊查询全部
	List<ShareConfig> getListLike(ShareConfig clssname);

	// 根据条件模糊分页查询
	List<ShareConfig> getPageListLike(ShareConfig clssname);

	// 查询条数
	int getCountLike(ShareConfig clssname);

	// 根据主键查询实例
	ShareConfig selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	ShareConfig selectBy(ShareConfig record);

	// 根据所需插入
	int insert(ShareConfig record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(ShareConfig record);

	// 根据所需更新
	int updateByPrimaryKey(ShareConfig record);

}