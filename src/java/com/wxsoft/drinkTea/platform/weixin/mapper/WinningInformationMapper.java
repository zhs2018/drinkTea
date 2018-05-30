package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.WinningInformation;

/**
 * @文件名称: WinningInformationMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.WinningInformationMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  WinningInformationMapper {
	// 根据条件全部查询
	List<WinningInformation> getListBy(WinningInformation clssname);

	// 根据条件分页查询
	List<WinningInformation> getPageListBy(WinningInformation clssname);

	// 查询条数
	int getCountBy(WinningInformation clssname);

	// 根据条件模糊查询全部
	List<WinningInformation> getListLike(WinningInformation clssname);

	// 根据条件模糊分页查询
	List<WinningInformation> getPageListLike(WinningInformation clssname);

	// 查询条数
	int getCountLike(WinningInformation clssname);

	// 根据主键查询实例
	WinningInformation selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	WinningInformation selectBy(WinningInformation record);

	// 根据所需插入
	int insert(WinningInformation record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(WinningInformation record);

	// 根据所需更新
	int updateByPrimaryKey(WinningInformation record);

}