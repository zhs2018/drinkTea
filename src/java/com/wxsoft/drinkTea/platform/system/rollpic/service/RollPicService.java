package com.wxsoft.drinkTea.platform.system.rollpic.service;

import java.util.List;

import com.wxsoft.drinkTea.platform.system.rollpic.model.RollPic;

public interface RollPicService {
//	int deleteByPrimaryKey(Integer id);

	int insert(RollPic record);

	int insertSelective(RollPic record);

	int updateByPrimaryKeySelective(RollPic record);

	RollPic selectByPrimaryKey(Integer id);

	RollPic selectByRollPic(RollPic record);

	List<RollPic> listPageByRollPic(RollPic clssname);

	List<RollPic> getAllByRollPic(RollPic clssname);
}
