package com.wxsoft.drinkTea.platform.system.userRole.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 *
 * @author yzy
 * @功能 主要为了开启事物
 *
 */
@Service
public class SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private WebUserMapper webUserMapper;

	/**
	 * 删除员工并将所有会员的绑定工号改为制定的工号
	 * @param sysUser 要删除的员工
	 * @param user  重新绑定的员工
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteUser(SysUser sysUser, SysUser user) {
		WebUser webUser = new WebUser();
		webUser.setSysUserId(user.getId());
		webUser.setId(sysUser.getId()); //要删除的员工id保存到webuser中的id中^_^
		webUserMapper.updateAll(webUser);
		sysUser.setVisable(2);
		int count = sysUserMapper.updateByPrimaryKey(sysUser);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据员工获取会员数量
	 * @param users
	 * @return
	 */
	public List<SysUser> setWebUserCount(List<SysUser> users, String time) {
		if(users != null || users.size() > 0){
			if(time != null && !"".equals(time)){
				String[] sTime = time.split("~");
				Map<String, String> map = new HashMap<>();
				map.put("beginTime", DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[0]), "yyyyMMdd"));
				map.put("endTime", DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[1]), "yyyyMMdd"));
				for(int i = 0;i<users.size();i++){
					map.put("userId", String.valueOf(users.get(i).getId()));
					users.get(i).setCount(webUserMapper.getCountByMap(map));
				}
			}else{
				for(int i = 0;i<users.size();i++){
					WebUser webUser = new WebUser();
					webUser.setSysUserId(users.get(i).getId());
					users.get(i).setCount(webUserMapper.getCountBy(webUser));
				}
			}
			return users;
		}
		return null;
	}

	/**
	 * 根据员工id与时间联合查询webUser
	 * @param users
	 * @param time
	 * @return
	 */
	public List<WebUser> setWebUser(WebUser webUser, String time) {
		List<WebUser> webUsers = null;
		if(webUser != null){
			if(time != null && !"".equals(time)){
				String[] sTime = time.split("~");
				webUser.setBeginTime(DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[0]), "yyyyMMdd"));
				webUser.setEndTime(DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[1]), "yyyyMMdd"));
				webUsers = webUserMapper.getPageListByMap(webUser);
				if(webUsers!=null && webUsers.size() >0){
    				for(int i =0;i<webUsers.size();i++){
    					if(webUsers.get(i).getSysUserId()!=null){
    						webUsers.get(i).setSysUser(sysUserMapper.selectByPrimaryKey(webUsers.get(i).getSysUserId()));
    					}
    				}
    			}
			}else{
				webUsers=webUserMapper.getPageListBy(webUser);
				if(webUsers!=null && webUsers.size() >0){
    				for(int i =0;i<webUsers.size();i++){
    					if(webUsers.get(i).getSysUserId()!=null){
    						webUsers.get(i).setSysUser(sysUserMapper.selectByPrimaryKey(webUsers.get(i).getSysUserId()));
    					}
    				}
    			}
			}
			webUsers.add(webUser);
		}
		return webUsers;
	}
}
