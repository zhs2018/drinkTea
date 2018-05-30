package com.wxsoft.drinkTea.datemodel.autocreate.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.datemodel.autocreate.model.MessageCenter;;

/**
 * @文件名称: MessageCenterMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.MessageCenterMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  MessageCenterMapper {
	// 根据条件全部查询
	List<MessageCenter> getListBy(MessageCenter clssname);

	// 根据条件分页查询
	List<MessageCenter> getPageListBy(MessageCenter clssname);

	// 查询条数
	int getCountBy(MessageCenter clssname);

	// 根据条件模糊查询全部
	List<MessageCenter> getListLike(MessageCenter clssname);

	// 根据条件模糊分页查询
	List<MessageCenter> getPageListLike(MessageCenter clssname);

	// 查询条数
	int getCountLike(MessageCenter clssname);

	// 根据主键查询实例
	MessageCenter selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	MessageCenter selectBy(MessageCenter record);

	// 根据所需插入
	int insert(MessageCenter record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(MessageCenter record);

	// 根据所需更新
	int updateByPrimaryKey(MessageCenter record);

}