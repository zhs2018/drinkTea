package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.MessageReading;;

/**
 * @文件名称: MessageReadingMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.MessageReadingMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  MessageReadingMapper {
	// 根据条件全部查询
	List<MessageReading> getListBy(MessageReading clssname);

	// 根据条件分页查询
	List<MessageReading> getPageListBy(MessageReading clssname);

	// 查询条数
	int getCountBy(MessageReading clssname);

	// 根据条件模糊查询全部
	List<MessageReading> getListLike(MessageReading clssname);

	// 根据条件模糊分页查询
	List<MessageReading> getPageListLike(MessageReading clssname);

	// 查询条数
	int getCountLike(MessageReading clssname);

	// 根据主键查询实例
	MessageReading selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	MessageReading selectBy(MessageReading record);

	// 根据所需插入
	int insert(MessageReading record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(MessageReading record);

	// 根据所需更新
	int updateByPrimaryKey(MessageReading record);

}