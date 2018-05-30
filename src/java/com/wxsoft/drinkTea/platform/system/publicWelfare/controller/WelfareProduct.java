package com.wxsoft.drinkTea.platform.system.publicWelfare.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.utils.KindeditorController;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;

@Controller
@RequestMapping("system/welfare")
public class WelfareProduct extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ManageProductsMapper manageProductsMapper;

	/**
	 * 添加公益商品
	 *
	 * @return
	 */
	@RequestMapping("/product/info")
	public ModelAndView productAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/publicWelfare/addproduct");
		return mv;
	}

	/**
	 * 到公益商品列表
	 *
	 * @param mp
	 * @return
	 */
	@RequestMapping("/product/list")
	public ModelAndView productList(ManageProducts mp) {
		ModelAndView mv = new ModelAndView();
		mp.setSign(1);
		mp.setValiable(1);
		List<ManageProducts> manageList = manageProductsMapper.getPageListBy(mp);
		mv.addObject("manageList", manageList);
		mv.addObject("obj", mp);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/publicWelfare/productList");
		return mv;
	}

	/**
	 * 保存、更新
	 *
	 * @param session
	 * @param request
	 * @param rf
	 * @param response
	 * @return
	 */
	@RequestMapping("/product/save")
	public String updatecomm(HttpSession session, HttpServletRequest request, ManageProducts rf,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		String originalFilename = null;
		originalFilename = myfile.getOriginalFilename();
		System.out.println("文件的其实名字：" + originalFilename);
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
					rf.setPicture(maps.get("saveUrl") + filename);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		myfile = multipartRequest.getFile("myfile1");
		originalFilename = myfile.getOriginalFilename();
		if (null != myfile && !myfile.isEmpty()) {
			try {
				String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/";
				String saveUrl = "/attached/";
				Map<String, String> maps = KindeditorController.mkdir(savePath, saveUrl, "catalog");
				String endName = originalFilename.substring(originalFilename.lastIndexOf("."),
						originalFilename.length());
				String filename = UUID.randomUUID() + endName;
				if (endName.equals(".png") || endName.equals(".gif") || endName.equals(".jpg")
						|| endName.equals(".jpeg")) {
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(maps.get("savePath"), filename));
					rf.setPictureOne(maps.get("saveUrl") + filename);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		myfile = multipartRequest.getFile("myfile2");
		originalFilename = myfile.getOriginalFilename();
		if (null != myfile && !myfile.isEmpty()) {
			try {
				String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/";
				String saveUrl = "/attached/";
				Map<String, String> maps = KindeditorController.mkdir(savePath, saveUrl, "catalog");
				String endName = originalFilename.substring(originalFilename.lastIndexOf("."),
						originalFilename.length());
				String filename = UUID.randomUUID() + endName;
				if (endName.equals(".png") || endName.equals(".gif") || endName.equals(".jpg")
						|| endName.equals(".jpeg")) {
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(maps.get("savePath"), filename));
					rf.setPictureTwo(maps.get("saveUrl") + filename);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null == rf.getId()) {// 保存
			rf.setValiable(1);
			rf.setSign(1);
			int i = manageProductsMapper.insert(rf);
			System.out.println("shuchuyixiakanyikan " + i);
			if (i == 1) {
				map.put("status", "1");
			} else {
				map.put("status", "0");
			}
		} else {// 更新
			int i = manageProductsMapper.updateByPrimaryKey(rf);
			if (i == 1) {
				map.put("status", "1");
			} else {
				map.put("status", "0");
			}
		}
		return "redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/welfare/product/list";
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @param response
	 */
	@RequestMapping("/product/del/{id}")
	public ModelAndView deleteUse(@PathVariable Integer id, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		ManageProducts manageProducts = new ManageProducts();
		manageProducts.setId(id);
		manageProducts.setValiable(2);
	    manageProductsMapper.updateByPrimaryKey(manageProducts);
	    mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/welfare/product/list");
		return mv;
	}

	/**
	 * 到商品編輯
	 */
	@RequestMapping(value = "/product/editor/{id}")
	public ModelAndView editUser(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/publicWelfare/addproduct");
		ManageProducts manageProducts = manageProductsMapper.selectByPrimaryKey(id);
		mv.addObject("manageProducts", manageProducts);
		return mv;
	}
}
