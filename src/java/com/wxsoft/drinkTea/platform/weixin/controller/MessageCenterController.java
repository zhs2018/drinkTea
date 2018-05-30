package com.wxsoft.drinkTea.platform.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.platform.system.messageCenter.mapper.MessageCenterMapper;
import com.wxsoft.drinkTea.platform.system.messageCenter.model.MessageCenter;
import com.wxsoft.drinkTea.platform.weixin.mapper.MessageReadingMapper;
import com.wxsoft.drinkTea.platform.weixin.model.MessageReading;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 *
 * @author lzj
 * @描述：消息中心
 *
 */
@Controller
@RequestMapping()
public class MessageCenterController extends BaseAction {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MessageCenterMapper messageCenterMapper;

	@Autowired
	private MessageReadingMapper messageReadingMapper;

	@RequestMapping("/perCenter")
	public ModelAndView messageCenter(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if(user!=null){
			MessageCenter mCenter = new MessageCenter();
			if (user.getAge() != null) {
				mCenter.setUserCreateTime(user.getCreatetime());
				if (user.getAge() == 1) {
					mCenter.setUserType(1);
				} else if (user.getAge() == 2) {
					mCenter.setUserType(2);
				} else if (user.getAge() == 3) {
					mCenter.setUserType(3);
				} else if (user.getAge() == 4) {
					mCenter.setUserType(4);
				} else if (user.getAge() == 5) {
					mCenter.setUserType(5);
				} else if (user.getAge() == 6) {
					mCenter.setUserType(6);
				} else if (user.getAge() == 7) {
					mCenter.setUserType(7);
				} else {
					logger.info("userType为空");
				}
			}else{
				MessageCenter mter = new MessageCenter();
				mter.setUserCreateTime(user.getCreatetime());
				List<MessageCenter> msList = messageCenterMapper.getListByTime(mter);
				for (MessageCenter messageCenter : msList) {
					MessageReading messageReading = new MessageReading();
					messageReading.setMessageId(messageCenter.getId());
					messageReading.setUserId(user.getId());
					MessageReading messageR = messageReadingMapper.selectBy(messageReading);
					if (messageR != null) {
						messageCenter.setReadSign(1);
					} else {
						messageCenter.setReadSign(2);
					}
					messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
				}
			}
			List<MessageCenter> mList = messageCenterMapper.getListByTime(mCenter);
			MessageCenter mCent = new MessageCenter();
			if (user.getSex() != null){
				mCent.setUserCreateTime(user.getCreatetime());
				    if (user.getSex() == 1) {
						mCent.setUserType(8);
					} else if (user.getSex() == 2) {
						mCent.setUserType(9);
					}
			}
			List<MessageCenter> mLists  = messageCenterMapper.getListBySex(mCent);
			  if(mLists!=null&&mLists.size()>0){
				  for (MessageCenter messageCenter : mLists) {
					mList.add(messageCenter);
				}
			  }
			  if(mList!=null&&mList.size()>0){
					for (MessageCenter messageCenter : mList) {
						MessageReading messageReading = new MessageReading();
						messageReading.setMessageId(messageCenter.getId());
						messageReading.setUserId(user.getId());
						MessageReading messageR = messageReadingMapper.selectBy(messageReading);
						if (messageR == null) {
							messageCenter.setReadSign(1);
						} else {
							messageCenter.setReadSign(2);
						}
						messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
					}
					mv.addObject("ObjList", mList);
					mv.setViewName("weixin/messageCenter");
			  }
		}else{
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/index");
		}
		return mv;
	}

	@RequestMapping("/content")
	public ModelAndView messageContent(HttpSession session, Integer id) {
		ModelAndView mv = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user != null) {
			MessageReading messageReading = new MessageReading();
			messageReading.setMessageId(id);
			messageReading.setUserId(user.getId());
			MessageReading messageR = messageReadingMapper.selectBy(messageReading);
			if (messageR == null) {
				MessageReading messageRead = new MessageReading();
				messageRead.setMessageId(id);
				SimpleDateFormat sdf = new SimpleDateFormat();
				String time = sdf.format(new Date());
				messageReading.setReadTime(time);
				messageRead.setUserId(user.getId());
				messageReading.setReadSign(1);
				messageReadingMapper.insert(messageRead);
				MessageCenter messageCenter = messageCenterMapper.selectByPrimaryKey(id);
				messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
				mv.addObject("ob", messageCenter);
			} else {
				MessageCenter messageCenter = messageCenterMapper.selectByPrimaryKey(id);
				messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
				mv.addObject("ob", messageCenter);
			}
			mv.setViewName("weixin/messageContent");
		}else{
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/index");
		}

		return mv;

	}
}
