package com.wxsoft.drinkTea.platform.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@Service
public class PersonalCenterService {
	@Autowired
	private WebUserMapper webUserMapper;

	@Transactional(rollbackFor=Exception.class)
	public boolean updateUser(WebUser user){
		int count = webUserMapper.updateByPrimaryKey(user);
		if(count == 1){
			return true;
		}
		return false;
	}
}
