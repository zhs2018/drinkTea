package com.wxsoft.drinkTea.platform.system.company.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.platform.system.company.model.CompanyPro;


/**
 * @文件名称: CompanyProMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.CompanyProMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  CompanyProMapper {
	// 根据条件全部查询
	List<CompanyPro> getListBy(CompanyPro clssname);

	// 根据条件分页查询
	List<CompanyPro> getPageListBy(CompanyPro clssname);

	// 查询条数
	int getCountBy(CompanyPro clssname);

	// 根据条件模糊查询全部
	List<CompanyPro> getListLike(CompanyPro clssname);

	// 根据条件模糊分页查询
	List<CompanyPro> getPageListLike(CompanyPro clssname);

	// 查询条数
	int getCountLike(CompanyPro clssname);

	// 根据主键查询实例
	CompanyPro selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	CompanyPro selectBy(CompanyPro record);

	// 根据所需插入
	int insert(CompanyPro record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(CompanyPro record);

	// 根据所需更新
	int updateByPrimaryKey(CompanyPro record);
}