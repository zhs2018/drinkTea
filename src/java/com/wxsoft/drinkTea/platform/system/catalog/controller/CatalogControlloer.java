package com.wxsoft.drinkTea.platform.system.catalog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.controller.BaseController;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.platform.system.catalog.mapper.TypeManageMapper;
import com.wxsoft.drinkTea.platform.system.catalog.model.TypeManage;

/**
 * @类功能说明：分类信息说明 @修改日期：2017-03-23 @修改说明：
 * @作者：lzj @创建时间：2017-03-23 上午10:03:52
 */

@Controller
// @Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/catalog")
public class CatalogControlloer extends BaseController {

	@Autowired
	private TypeManageMapper TypeManageService;

	/**
	 *
	 * @描述: 查询分类列表 @作者: lzj @日期:2017-3-23 @修改内容 @参数： @param
	 * response @参数： @return @return ModelAndView @throws
	 */
	@RequestMapping("/list")
	public ModelAndView listResponse(TypeManage response) {
		ModelAndView mv = new ModelAndView();
		List<TypeManage> list = TypeManageService.getPageListLike(response);
		List<TypeManage> responseList = new ArrayList<TypeManage>();
		for (TypeManage rollPic : list) {
			System.out.println("-0-0--00:" + rollPic.getIsVisable());
			if (rollPic.getIsVisable() == 1) {
				responseList.add(rollPic);
			}
		}
		mv.addObject("objList", responseList);
		mv.addObject("obj", response);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/list");
		return mv;
	}

	/**
	 * @功能：分类的下载以及业务逻辑间的处理 @描述: 查询分类注表信息， @作者:
	 * lzj @日期:2017-3-23 @修改内容 @参数： @param response @参数： @return @return
	 * ModelAndView @throws
	 */
	@RequestMapping("/getone")
	public ModelAndView getResponse(HttpServletRequest request, TypeManage response, HttpSession session, Integer cur) {
		ModelAndView mv = new ModelAndView();
		System.out.println("输出一次：" + cur);
		response = TypeManageService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		mv.addObject("obj", response);
		mv.addObject("cur", cur);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/info");
		return mv;
	}

	/**
	 *
	 * @描述: 添加分类文件 @作者: lzj @日期:2017-3-24 @修改内容 @参数： @return @return
	 * String @throws
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView add(String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/info");
		return mv;
	}

	/**
	 *
	 * @描述: 保存首次关注规格 @作者: lzj @日期:2017-3-24 @修改内容 @参数： @return @return
	 * String @throws
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpSession session, HttpServletRequest request, TypeManage rf) {
		if (null == rf.getId()) {// 保存
			rf.setAddTime(Tools.date2Str(new Date()));
			TypeManageService.insert(rf);
		} else {// 更新
			TypeManageService.updateByPrimaryKey(rf);
			System.out.println("4588");
		}
		// ?page.currentPage=2
		return "redirect:list?page.currentPage=" + rf.getCur();

	}

	/**
	 * @描述: 删除分类
	 * @作者: lzj
	 * @日期:2017-3-24
	 * @修改内容
	 */
	@RequestMapping("/delones/{id}")
	public ModelAndView delProdeuct(HttpServletRequest request, @PathVariable Integer id, TypeManage resp,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		TypeManage response = new TypeManage();
		response.setId(id);
		TypeManageService.delete(response);
		mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/list");
		return mv;
	}

	/**
	 * 删除轮播图的信息，删除下载的文件信息
	 */
	/*
	 * @RequestMapping(value = "/delone") public void delete(HttpServletRequest
	 * request, TypeManage response, HttpServletResponse res, String DATA,
	 * HttpSession session) { JSONObject jsonObject =
	 * JSONObject.parseObject(DATA);
	 *
	 * String[] listId = jsonObject.getString("CTIDS").split(","); String result
	 * = "{'flag':false}"; for (String string : listId) { if (null != response)
	 * { if(TypeManageService.deleteByPrimaryKey(Integer.parseInt(string)) > 0){
	 * result = "{'flag':true}"; } } } try { responseAjax(result, res); } catch
	 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

}