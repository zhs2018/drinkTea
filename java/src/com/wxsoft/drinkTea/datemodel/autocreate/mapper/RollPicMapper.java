package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.RollPic;;

/**
 * @文件名称: RollPicMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.RollPicMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  RollPicMapper {
	// 根据条件全部查询
	List<RollPic> getListBy(RollPic clssname);

	// 根据条件分页查询
	List<RollPic> getPageListBy(RollPic clssname);

	// 查询条数
	int getCountBy(RollPic clssname);

	// 根据条件模糊查询全部
	List<RollPic> getListLike(RollPic clssname);

	// 根据条件模糊分页查询
	List<RollPic> getPageListLike(RollPic clssname);

	// 查询条数
	int getCountLike(RollPic clssname);

	// 根据主键查询实例
	RollPic selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	RollPic selectBy(RollPic record);

	// 根据所需插入
	int insert(RollPic record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(RollPic record);

	// 根据所需更新
	int updateByPrimaryKey(RollPic record);

}