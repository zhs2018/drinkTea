package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;

/**
 * @文件名称: RedEnvelopeMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.RedEnvelopeMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  RedEnvelopeMapper {
	// 根据条件全部查询
	List<RedEnvelope> getListBy(RedEnvelope clssname);

	// 根据条件分页查询
	List<RedEnvelope> getPageListBy(RedEnvelope clssname);

	// 查询条数
	int getCountBy(RedEnvelope clssname);

	// 根据条件模糊查询全部
	List<RedEnvelope> getListLike(RedEnvelope clssname);

	// 根据条件模糊分页查询
	List<RedEnvelope> getPageListLike(RedEnvelope clssname);

	// 查询条数
	int getCountLike(RedEnvelope clssname);

	// 根据主键查询实例
	RedEnvelope selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	RedEnvelope selectBy(RedEnvelope record);

	// 根据所需插入
	int insert(RedEnvelope record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(RedEnvelope record);

	// 根据所需更新
	int updateByPrimaryKey(RedEnvelope record);

}