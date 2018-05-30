package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.PrizePro;;

/**
 * @文件名称: PrizeProMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.PrizeProMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  PrizeProMapper {
	// 根据条件全部查询
	List<PrizePro> getListBy(PrizePro clssname);

	// 根据条件分页查询
	List<PrizePro> getPageListBy(PrizePro clssname);

	// 查询条数
	int getCountBy(PrizePro clssname);

	// 根据条件模糊查询全部
	List<PrizePro> getListLike(PrizePro clssname);

	// 根据条件模糊分页查询
	List<PrizePro> getPageListLike(PrizePro clssname);

	// 查询条数
	int getCountLike(PrizePro clssname);

	// 根据主键查询实例
	PrizePro selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	PrizePro selectBy(PrizePro record);

	// 根据所需插入
	int insert(PrizePro record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(PrizePro record);

	// 根据所需更新
	int updateByPrimaryKey(PrizePro record);

}