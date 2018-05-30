package com.wxsoft.drinkTea.platform.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@Service
public class WebUserService {
	@Autowired
	private WebUserMapper webUserMapper;

	@Transactional(rollbackFor=Exception.class)
	public int saveUserComment(WebUser user){
		return webUserMapper.insert(user);
	}

	@Transactional(rollbackFor=Exception.class)
	public int updateUser(WebUser user){
		return webUserMapper.updateByPrimaryKey(user);
	}
}
