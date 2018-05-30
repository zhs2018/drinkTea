package com.wxsoft.drinkTea.platform.system.shouhou.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.shouhou.model.ShouhouBean;

/**
 * @文件名称: ShouhouServiceMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.ShouhouServiceMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  ShouhouServiceMapper {
	// 根据条件全部查询
	List<ShouhouBean> getListBy(ShouhouBean clssname);

	// 根据条件分页查询
	List<ShouhouBean> getPageListBy(ShouhouBean clssname);

	// 查询条数
	int getCountBy(ShouhouBean clssname);

	// 根据条件模糊查询全部
	List<ShouhouBean> getListLike(ShouhouBean clssname);

	// 根据条件模糊分页查询
	List<ShouhouBean> getPageListLike(ShouhouBean clssname);

	// 查询条数
	int getCountLike(ShouhouBean clssname);

	// 根据主键查询实例
	ShouhouBean selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	ShouhouBean selectBy(ShouhouBean record);

	// 根据所需插入
	int insert(ShouhouBean record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(ShouhouBean record);
	//自定义方法
	ShouhouBean getListById(Integer id);
	// 根据所需更新
	int updateByPrimaryKey(ShouhouBean record);
	//退货用查询
	List<ShouhouBean> getPageListReturn(ShouhouBean record);
	//财务用查询退货的order_state 50、60
	Double getReturnOrderNumber();

	ProductOrder selectByOrderState(ProductOrder record);
}