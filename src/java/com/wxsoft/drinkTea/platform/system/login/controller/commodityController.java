
package com.wxsoft.drinkTea.platform.system.login.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.KindeditorController;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;

@Controller
@RequestMapping("system/commodity")
public class commodityController extends BaseAction {

	@Autowired
	private ManageProductsMapper commodit;
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	/**
	 * @商品列表入口
	 * @xyc
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping("/commodit")
	public ModelAndView mo(ManageProducts man) {
		ModelAndView mm = new ModelAndView("/system/commodity/commodit");
		man.setValiable(1);
		man.setSign(3);
		List<ManageProducts> ll = commodit.getPageListLike(man);
		mm.addObject("commodit", ll);
		mm.addObject("obj", man);
		return mm;
	}

	/**
	 * system/commodity/addcomm
	 *
	 * @添加商品页面
	 * @xyc
	 */
	@RequestMapping("addcommodity")
	public ModelAndView mss() {
		return new ModelAndView("system/commodity/addcommodity");
	}

	@RequestMapping(value = "/addcomm", method = RequestMethod.POST)
	public String updatecomm(HttpSession session, HttpServletRequest request, ManageProducts rf,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		String originalFilename = null;
		originalFilename = myfile.getOriginalFilename();
		System.out.println("文件的其实名字："+originalFilename);
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
			rf.setSign(3);
			int i = commodit.insert(rf);
			System.out.println("shuchuyixiakanyikan " + i);
			if (i == 1) {
				map.put("status", "1");
			} else {
				map.put("status", "0");
			}
		} else {// 更新
			int i = commodit.updateByPrimaryKey(rf);
			if (i == 1) {
				map.put("status", "1");
			} else {
				map.put("status", "0");
			}
		}
		if(rf.getCur()!=null){
			return "redirect:commodit?page.currentPage="+rf.getCur();
		}else{
			return "redirect:commodit";
		}

	}

	/**
	 *
	 * @删除商品信息
	 * @作者：xyc
	 */
	@RequestMapping("/deleteUser")
	public void deleteUse(Integer id, HttpServletResponse response) {

		System.out.println(id);
		Map<String, String> map = new HashMap<String, String>();
		ManageProducts manageProducts = new ManageProducts();
		manageProducts.setId(id);
		manageProducts.setValiable(2);
		manageProducts.setSign(3);
		int result = manageProductsMapper.updateByPrimaryKey(manageProducts);
		System.out.println(result);
		if (result < 0) {
			map.put("status", "0");
			map.put("Message", "删除商品信息失败，请稍后再试");
		} else {
			map.put("status", "1");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @跳转商品编辑界面 system/commodity/deleteUsers/{id}
	 * @:xyc commodit
	 */
	@RequestMapping(value = "/editUsers")
	public ModelAndView editUser(Integer id,Integer cur) {
		// SysUser user = sysUserMapper.selectByPrimaryKey(id);
		System.out.println("输出一下cur:"+cur);
		ModelAndView mav = new ModelAndView("/system/commodity/addcommodity");
		ManageProducts manageProducts = manageProductsMapper.selectByPrimaryKey(id);
		mav.addObject("manageProducts", manageProducts);
		mav.addObject("cur", cur);
		return mav;
	}

	/**
	 * @查看商品
	 * @xyc
	 */
	@RequestMapping(value = "/examine/{id}")
	public ModelAndView examine(@PathVariable Integer id, ManageProducts manage) {
		System.out.println("我是" + id);
		ModelAndView mav = new ModelAndView("/system/commodity/examinecomm");
		if (id != null) {
			manage = commodit.selectByPrimaryKey(id);
			System.out.println(manage.getName());
			mav.addObject("manage", manage);
		} else if (id == null) {
			System.out.println("查看失败");
		}
		return mav;
	}

}

