package com.wxsoft.drinkTea.platform.system.cash.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.controller.BaseController;
import com.wxsoft.drinkTea.framework.utils.KindeditorController;
import com.wxsoft.drinkTea.platform.system.cash.mapper.CashDescMapper;
import com.wxsoft.drinkTea.platform.system.cash.model.CashDesc;
@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/cashdesc")
public class CashDescController extends BaseController{

	@Autowired
	private CashDescMapper cashDescMapper;

	@RequestMapping("/desclist")
	public ModelAndView descList(CashDesc cashDesc){
		ModelAndView mv = new ModelAndView();
	List<CashDesc> cDesc = 	cashDescMapper.getPageListBy(cashDesc);
	 mv.addObject("cashDesc",cDesc);
	 mv.setViewName("system/cash/cashdesclist");
	return mv;
	}


	@RequestMapping("/editDesc")
	public ModelAndView editDesc(CashDesc cashDesc){
		ModelAndView mv = new ModelAndView();
		 cashDesc = cashDescMapper.selectBy(cashDesc);
		 mv.addObject("cashDesc",cashDesc);
		 mv.setViewName("system/cash/cashdescedit");
		return mv;
	}


	@RequestMapping("/saveDesc")
	public String updatecomm(HttpSession session, HttpServletRequest request, CashDesc cashDesc,
			HttpServletResponse response) {
		//Map<String, String> map = new HashMap<String, String>();
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
					cashDesc.setCashImage(maps.get("saveUrl") + filename);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(cashDesc.getId()!=null){
			cashDescMapper.updateByPrimaryKey(cashDesc);
		}
		return "redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/cashdesc/desclist";
	}



}
