package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.KingBaseInfo;

/**
 * @文件名称: KingBaseInfoMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.KingBaseInfoMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  KingBaseInfoMapper {
	// 根据条件全部查询
	List<KingBaseInfo> getListBy(KingBaseInfo clssname);

	// 根据条件分页查询
	List<KingBaseInfo> getPageListBy(KingBaseInfo clssname);

	// 查询条数
	int getCountBy(KingBaseInfo clssname);

	// 根据条件模糊查询全部
	List<KingBaseInfo> getListLike(KingBaseInfo clssname);

	// 根据条件模糊分页查询
	List<KingBaseInfo> getPageListLike(KingBaseInfo clssname);

	// 查询条数
	int getCountLike(KingBaseInfo clssname);

	// 根据主键查询实例
	KingBaseInfo selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	KingBaseInfo selectBy(KingBaseInfo record);

	// 根据所需插入
	int insert(KingBaseInfo record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(KingBaseInfo record);

	// 根据所需更新
	int updateByPrimaryKey(KingBaseInfo record);

}