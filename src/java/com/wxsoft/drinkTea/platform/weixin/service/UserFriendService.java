package com.wxsoft.drinkTea.platform.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserFriendMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserFriend;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@Service
public class UserFriendService {

	@Autowired
	private WebUserMapper webUserMapper;

	@Autowired
	private UserFriendMapper userFriendMapper;

	/**
	 * 两个用户都已经关注过了（仅仅添加好友）
	 * @param user
	 * @param user2
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer save2User(WebUser user, WebUser user2) {
		if (user !=null && user2 !=null) {
			Integer count = 0;
			UserFriend friend = new UserFriend();
			friend.setUserId(user.getId());
			friend.setFriendId(user2.getId());
			friend = userFriendMapper.selectBy(friend);
			if(friend == null){
				friend = new UserFriend();
				friend.setUserId(user.getId());
				friend.setFriendId(user2.getId());
				friend.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
				friend.setType(1);
				 count += userFriendMapper.insert(friend);
			}
			friend = new UserFriend();
			friend.setUserId(user2.getId());
			friend.setFriendId(user.getId());
			friend = userFriendMapper.selectBy(friend);
			if(friend == null){
				friend = new UserFriend();
				friend.setUserId(user2.getId());
				friend.setFriendId(user.getId());
				friend.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
				friend.setType(2);
				count += userFriendMapper.insert(friend);
			}
			
			 
			
			return 1;
		}
		return 0;
	}

	/**
	 * 保存用户user2（新关注的用户）,并生成好友
	 * @param user
	 * @param user2
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer saveUserAndFriend(WebUser user, WebUser user2) {
		int count = webUserMapper.insert(user2);
		user2 = webUserMapper.selectBy(user2);
		if(count <=0 || user2 == null || user2.getId() == null){
			return 0;
		}else{
			if (user !=null) {
				UserFriend friend = new UserFriend();
				friend.setUserId(user.getId());
				friend.setFriendId(user2.getId());
				friend.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
				friend.setType(2);
				count += userFriendMapper.insert(friend);
				friend = new UserFriend();
				friend.setCreateTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
				friend.setUserId(user2.getId());
				friend.setFriendId(user.getId());
				friend.setType(1);
				count+=userFriendMapper.insert(friend);
				if(count == 3){
					return 1;
				}
			}
			return 0 ;
		}
	}

	/**
	 * 查询好友信息
	 * @param friends
	 * @return
	 */
	public List<UserFriend> getUsersByFriendList(List<UserFriend> friends) {
		if(friends != null && friends.size() >0){
			for(int i = 0;i<friends.size();i++){
				friends.get(i).setUser(webUserMapper.selectByPrimaryKey(friends.get(i).getFriendId()));
			}
		}
		return friends;
	}
}
