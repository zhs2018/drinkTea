package com.wxsoft.drinkTea.platform.system.abouts.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.KindeditorController;
import com.wxsoft.drinkTea.framework.utils.ThumbNailHelper;
import com.wxsoft.drinkTea.platform.system.abouts.mapper.AboutUsMapper;
import com.wxsoft.drinkTea.platform.system.abouts.model.AboutUs;

@Controller
@RequestMapping("system/commodity")
public class AboutusController extends BaseAction {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	@Autowired
	private AboutUsMapper abouts;

	/**
	 * @关于我们
	 * @xyc
	 */
	@RequestMapping("/myAbout")
	public ModelAndView modelAn(AboutUs about) {
		ModelAndView modelAndView = new ModelAndView("system/commodity/myAbout");
		List<AboutUs> list = abouts.getListBy(about);
		modelAndView.addObject("list", list);
		for (AboutUs About : list) {
			System.out.println(About.getContent());
		}
		return modelAndView;
	}

	/**
	 * @跳转更新界面
	 * @xyc
	 */
	@RequestMapping(value = "/update/{id}")
	public ModelAndView mos(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("/system/commodity/addmessage");
		AboutUs about = abouts.selectByPrimaryKey(id);
		modelAndView.addObject("about", about);
		return modelAndView;
	}

	/**
	 * @更新关于我们编辑信息
	 * @xyc @2017.4.13
	 *
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletResponse response, AboutUs About, HttpServletRequest request,
			HttpSession session) {
		System.out.println("这是第一次？？》。。");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		System.out.println("请求：" + multipartRequest);
		MultipartFile myfile = multipartRequest.getFile("myfile");
		System.out.println("文件：" + myfile);
		String originalFilename = null;
		originalFilename = myfile.getOriginalFilename();
		System.out.println("原始文件名：" + originalFilename);
		if (null != myfile && !myfile.isEmpty()) {
			try {
				String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/";
				System.out.println("保存path：" + savePath);
				String saveUrl = "/attached/";
				System.out.println("保存url：" + saveUrl);
				Map<String, String> maps = KindeditorController.mkdir(savePath, saveUrl, "catalog");
				System.out.println("map集合：" + maps.toString());
				String endName = originalFilename.substring(originalFilename.lastIndexOf("."),
						originalFilename.length());
				System.out.println("后缀名：" + endName);
				String filename = UUID.randomUUID() + endName;
				System.out.println("文件名：" + filename);
				if (endName.equals(".png") || endName.equals(".gif") || endName.equals(".jpg")
						|| endName.equals(".jpeg")) {

					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(maps.get("savePath"), filename));

					ThumbNailHelper.createThumbnailByRectangle(maps.get("savePath") + filename, 800, 600, 100,
							maps.get("savePath") + filename, 800, 600);
					About.setPicture(maps.get("saveUrl") + filename);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		if (About.getId() != null) {
			int ints = abouts.updateByPrimaryKey(About);
			modelAndView.addObject("ints", ints);
			modelAndView.addObject("result", "2");
			modelAndView.addObject("message", "更新成功！");
			modelAndView.setViewName("system/commodity/addmessage");
		} else if (About.getId() == null) {
			abouts.insert(About);
			modelAndView.addObject("result", "3");
		}
		return modelAndView;
	}

	/**
	 * @跳转到添加字段页面addfield
	 */
	@RequestMapping("/addziduan")
	public ModelAndView addziduan() {
		ModelAndView modelAndView = new ModelAndView("/system/commodity/addfields");
		return modelAndView;
	}

	/**
	 * @添加字段逻辑
	 * @xyc
	 */
	@RequestMapping("/addziduans")
	public ModelAndView addziduans(String name, AboutUs About) {
		ModelAndView modelAndView = new ModelAndView();
		if (About.getFielda() != null) {
			int Abo = abouts.insert(About);
			System.out.println("我是" + Abo);
			modelAndView.setViewName("system/commodity/myAbout");
			modelAndView.addObject("result", "0");
			modelAndView.addObject("message", "添加成功！");
		} else if (name == null) {
			modelAndView.setViewName("system/commodity/addfields");
			modelAndView.addObject("result", "1");
		}
		return modelAndView;
	}

}
