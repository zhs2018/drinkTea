package com.wxsoft.drinkTea.platform.weixin.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * @文件名称: WebUserMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.WebUserMapper;
 * @作者：
 * @公司：
 */
@Repository
public interface  WebUserMapper {
	// 根据条件全部查询
	List<WebUser> getListBy(WebUser clssname);

	// 根据条件分页查询
	List<WebUser> getPageListBy(WebUser clssname);

	// 查询条数
	int getCountBy(WebUser clssname);

	// 根据条件模糊查询全部
	List<WebUser> getListLike(WebUser clssname);

	// 根据条件模糊分页查询
	List<WebUser> getPageListLike(WebUser clssname);

	// 查询条数
	int getCountLike(WebUser clssname);

	// 根据主键查询实例
	WebUser selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	WebUser selectBy(WebUser record);

	// 根据所需插入
	int insert(WebUser record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(WebUser record);

	// 根据所需更新
	int updateByPrimaryKey(WebUser record);

	// 更改绑定员工
	int updateAll(WebUser record);

	//获取指定时间范围中的员工数量
	Integer getCountByMap(Map<String, String> map);
	//获取指定时间范围中的员工信息
	List<WebUser> getPageListByMap(WebUser webUser);

	//获取答过题王争霸题目的人的信息
	List<WebUser> getPageListByKingAnswer(WebUser webUser);
	//获取指定时间范围并且答过题王争霸题目的人的信息
	List<WebUser> getPageListByKingAnswerAndTime(WebUser webUser);
    //扫码答题
	List<WebUser> getPageListByQrecord(WebUser webUser);
   //前台计算排名
	List<WebUser> getListByKingAnswer(WebUser web);
}