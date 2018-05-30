package com.wxsoft.drinkTea.platform.system.catalog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.catalog.mapper.ProvinceManageMapper;
import com.wxsoft.drinkTea.platform.system.catalog.model.ProvinceManage;

@Controller
@RequestMapping("system/province")
public class provinceManageController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProvinceManageMapper provinceManageMapper;

	@RequestMapping("/list")
	public ModelAndView provinceList(ProvinceManage pm) {
		ModelAndView mv = new ModelAndView();
		pm.setIsVisable(1);
		List<ProvinceManage> pmList = provinceManageMapper.getPageListBy(pm);
		mv.addObject("pmList", pmList);
		mv.addObject("page", pm);
		mv.setViewName("system/catalog/provincelist");
		return mv;
	}

	@RequestMapping("/provinceinfo")
	public ModelAndView provinceinfo() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/provinceinfo");
		return mv;
	}

	@RequestMapping("/save")
	public ModelAndView save(ProvinceManage pm, Integer cur) {
		ModelAndView mv = new ModelAndView();
		int flag = 0;
		if (pm.getId() != null) {

			flag = provinceManageMapper.updateByPrimaryKey(pm);

		} else {
			pm.setAddTime(new Date());
			pm.setIsVisable(1);
			flag = provinceManageMapper.insert(pm);
		}

		if (flag == 1) {
			if (cur != null) {
				mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/province/list?page.currentPage=" + cur);
			} else {
				mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/province/list");
			}

		} else {
			mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/provinceinfo");
		}
		return mv;
	}

	@RequestMapping("/editorProvince")
	public ModelAndView save(Integer id, Integer cur) {
		ModelAndView mv = new ModelAndView();
		ProvinceManage pm = provinceManageMapper.selectByPrimaryKey(id);
		if (pm != null) {
			mv.addObject("pm", pm);
			mv.addObject("cur", cur);
			mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/provinceinfo");
		} else {
			mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/province/list");

		}
		return mv;
	}

	@RequestMapping("/delProvince")
	public ModelAndView delSeason(Integer id) {
		ModelAndView mv = new ModelAndView();
		if (id != null) {
			ProvinceManage pm = new ProvinceManage();
			pm.setId(id);
			pm.setIsVisable(2);
			provinceManageMapper.updateByPrimaryKey(pm);
		}
		mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/province/list");
		return mv;
	}

}
