package com.wxsoft.drinkTea.platform.system.shouhou.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.weixin.mapper.IdeaTicklingMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.IdeaTickling;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 *
 * @author lzj
 *
 */
@Controller
@RequestMapping("system/ideaTicking")
public class IdeaTicklingController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IdeaTicklingMapper ideaTicklingMapper;
	@Autowired
	private WebUserMapper webUserMapper;

	@RequestMapping("idea")
	public ModelAndView getIdeaTickling(HttpSession session, IdeaTickling idea) {
		ModelAndView mv = new ModelAndView();
		List<IdeaTickling> iTickling = ideaTicklingMapper.getPageListBy(idea);
		List<IdeaTickling> iList = new ArrayList<IdeaTickling>();
		if (iTickling != null && iTickling.size()>0) {
			for (IdeaTickling ideaTickling : iTickling) {
				IdeaTickling tickling = new IdeaTickling();
				WebUser wUser = webUserMapper.selectByPrimaryKey(ideaTickling.getCommodityId());
				tickling.setUserName(wUser.getUserName());
				tickling.setId(ideaTickling.getId());
				tickling.setPhone(ideaTickling.getPhone());
				tickling.setState(ideaTickling.getState());
				tickling.setContents(ideaTickling.getContents());
				iList.add(tickling);
			}
		}
		mv.addObject("objList", iList);
		mv.addObject("obj", idea);
		mv.setViewName("/system/ideaTicking/list");
		return mv;

	}

	@RequestMapping("ideaTickingState")
	public ModelAndView getTickling(IdeaTickling ideaTickling, Integer ty) {
		ModelAndView mv = new ModelAndView();
		if (ty != null) {
			if (ty == -1) {
				List<IdeaTickling> iTickling = ideaTicklingMapper.getPageListBy(ideaTickling);
				List<IdeaTickling> iList = new ArrayList<IdeaTickling>();
				if (iTickling != null && iTickling.size()>0) {
					for (IdeaTickling ideaTickling1 : iTickling) {
						IdeaTickling tickling = new IdeaTickling();
						WebUser wUser = webUserMapper.selectByPrimaryKey(ideaTickling1.getCommodityId());
						tickling.setUserName(wUser.getUserName());
						tickling.setId(ideaTickling1.getId());
						tickling.setPhone(ideaTickling1.getPhone());
						tickling.setState(ideaTickling1.getState());
						tickling.setContents(ideaTickling1.getContents());
						iList.add(tickling);
					}
				}
				mv.addObject("objList", iList);
				mv.addObject("obj", ideaTickling);
			} else if (ty == 20) {
				ideaTickling.setState(20);
				List<IdeaTickling> iTickling = ideaTicklingMapper.getPageListBy(ideaTickling);
				List<IdeaTickling> iList = new ArrayList<IdeaTickling>();
				if (iTickling != null && iTickling.size()>0) {
					for (IdeaTickling ideaTickling1 : iTickling) {
						IdeaTickling tickling = new IdeaTickling();
						WebUser wUser = webUserMapper.selectByPrimaryKey(ideaTickling1.getCommodityId());
						tickling.setUserName(wUser.getUserName());
						tickling.setId(ideaTickling1.getId());
						tickling.setPhone(ideaTickling1.getPhone());
						tickling.setState(ideaTickling1.getState());
						tickling.setContents(ideaTickling1.getContents());
						iList.add(tickling);
					}
				}
				mv.addObject("objList", iList);
				mv.addObject("obj", ideaTickling);
				mv.addObject("ty",ty);

			} else if (ty == 30) {
				ideaTickling.setState(30);
				List<IdeaTickling> iTickling = ideaTicklingMapper.getPageListBy(ideaTickling);
				List<IdeaTickling> iList = new ArrayList<IdeaTickling>();
				if (iTickling != null && iTickling.size()>0) {
					for (IdeaTickling ideaTickling1 : iTickling) {
						IdeaTickling tickling = new IdeaTickling();
						WebUser wUser = webUserMapper.selectByPrimaryKey(ideaTickling1.getCommodityId());
						tickling.setUserName(wUser.getUserName());
						tickling.setId(ideaTickling1.getId());
						tickling.setPhone(ideaTickling1.getPhone());
						tickling.setState(ideaTickling1.getState());
						tickling.setContents(ideaTickling1.getContents());
						iList.add(tickling);
					}
				}
				mv.addObject("objList", iList);
				mv.addObject("obj", ideaTickling);
				mv.addObject("ty",ty);
			}
		}
		mv.setViewName("/system/ideaTicking/list");
		return mv;
	}

	@RequestMapping("changeState")
	public void getChangeState(Integer id,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		IdeaTickling ideaTickling = new IdeaTickling();
		ideaTickling.setState(30);
		ideaTickling.setId(id);
		int flag = ideaTicklingMapper.updateByPrimaryKey(ideaTickling);
		if(flag==1){
			map.put("status", "1");
			map.put("ty",20);
		}else{
			map.put("status","0");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("deleteIdeaTicking")
	public void  jsonObject(Integer id,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		int flag = ideaTicklingMapper.deleteByPrimaryKey(id);
   System.out.println(flag);
		if (flag == 1) {
			map.put("status", "1");
			map.put("ty",30);
		} else {
			map.put("status", "0");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
