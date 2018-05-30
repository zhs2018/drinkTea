package com.wxsoft.drinkTea.platform.system.rollpic.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wxsoft.drinkTea.platform.system.rollpic.mapper.RollPicMapper;
import com.wxsoft.drinkTea.platform.system.rollpic.model.RollPic;
import com.wxsoft.drinkTea.platform.system.rollpic.service.RollPicService;

public class RollPicServiceImp implements RollPicService {
     @Autowired
	private RollPicMapper rollPicMapper;

	public RollPicMapper getRollPicMapper() {
		return rollPicMapper;
	}

	public void setRollPicMapper(RollPicMapper rollPicMapper) {
		this.rollPicMapper = rollPicMapper;
	}

//	@Override
//	public int deleteByPrimaryKey(Integer id) {
//		return rollPicMapper.deleteByPrimaryKey(id);
//	}

	@Override
	public int insert(RollPic record) {
		return rollPicMapper.insert(record);
	}

	@Override
	public int insertSelective(RollPic record) {
		return rollPicMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(RollPic record) {
		return rollPicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public RollPic selectByPrimaryKey(Integer id) {
		return rollPicMapper.selectByPrimaryKey(id);
	}

	@Override
	public RollPic selectByRollPic(RollPic record) {
		return rollPicMapper.selectByRollPic(record);
	}

	@Override
	public List<RollPic> listPageByRollPic(RollPic clssname) {
		return rollPicMapper.listPageByRollPic(clssname);
	}

	@Override
	public List<RollPic> getAllByRollPic(RollPic clssname) {
	  return rollPicMapper.getAllByRollPic(clssname);
	}
}
