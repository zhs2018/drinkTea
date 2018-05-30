package com.wxsoft.drinkTea.platform.weixin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.platform.system.recommendfriend.mapper.RecommendFriendMapper;
import com.wxsoft.drinkTea.platform.system.recommendfriend.model.RecommendFriend;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@Service
public class RecommendFriendService {

	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private RecommendFriendMapper recommendFriendMapper;
	/**
	 * 保存用户user2（新关注的用户）,并生成好友
	 * @param user
	 * @param user2
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer saveUserAndRecommendFriend(WebUser user, WebUser user2) {
		int count = 1;
		user2 = webUserMapper.selectBy(user2);
		if(count <=0 || user2 == null || user2.getId() == null){
			return 0;
		}else{
   if(user!=null){
	   RecommendFriend rf1 = new RecommendFriend();
		rf1.setRecommendedId(user2.getId());
		rf1.setType(1);
	Integer ct =  recommendFriendMapper.getCountBy(rf1);
	   if(ct>5){
		   RecommendFriend rf2 = new RecommendFriend();
		   rf2.setRecommendedId(user2.getId());
		   rf2.setRecommendId(user.getId());
		   rf2.setCreateTime(new Date());
		   rf2.setVisible(1);
		   rf2.setType(2);
		 count =  recommendFriendMapper.insert(rf2);
	   }else{
		   RecommendFriend rf2 = new RecommendFriend();
		   rf2.setRecommendedId(user2.getId());
		   rf2.setRecommendId(user.getId());
		   rf2.setCreateTime(new Date());
		   rf2.setVisible(1);
		   rf2.setType(1);
		 count =  recommendFriendMapper.insert(rf2);
	   }

	   RecommendFriend rf3 = new RecommendFriend();
			rf3.setRecommendedId(user.getId());
			rf3.setType(1);
		Integer ct1 =  recommendFriendMapper.getCountBy(rf3);
		   if(ct1>5){
			   RecommendFriend rf4 = new RecommendFriend();
			   rf4.setRecommendedId(user.getId());
			   rf4.setRecommendId(user2.getId());
			   rf4.setCreateTime(new Date());
			   rf4.setVisible(1);
			   rf4.setType(2);
			 count  =  recommendFriendMapper.insert(rf4);
		   }else{
			   RecommendFriend rf4 = new RecommendFriend();
			   rf4.setRecommendedId(user.getId());
			   rf4.setRecommendId(user2.getId());
			   rf4.setCreateTime(new Date());
			   rf4.setVisible(1);
			   rf4.setType(1);
			 count =  recommendFriendMapper.insert(rf4);
		   }
		   if(count==1){
			   return 1;
		   }

	   if(count==1){
		   return 1;
	   }

   }

			return 0 ;
		}
	}

}
