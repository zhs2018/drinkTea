package com.wxsoft.drinkTea.platform.system.rollpic.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.rollpic.model.RollPic;

/**
 * @文件名称: RollPicMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.RollPicMapper; @作者： @公司：
 */
@Repository
public interface RollPicMapper {
	//RollPic response
	// 根据主键删除
	Integer deleteByPrimaryKey(RollPic response);
	// 全部插入
	int insert(RollPic record);

	// 根据所需插入
	int insertSelective(RollPic record);

	// 根据所需更新
	int updateByPrimaryKeySelective(RollPic record);

	// 根据主键查询
	RollPic selectByPrimaryKey(Integer id);

	// 根据所需查询
	RollPic selectByRollPic(RollPic record);

	// 分页获取
	List<RollPic> listPageByRollPic(RollPic clssname);

	// 获取全部
	List<RollPic> getAllByRollPic(RollPic clssname);
}