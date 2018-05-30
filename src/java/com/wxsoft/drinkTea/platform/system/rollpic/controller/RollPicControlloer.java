package com.wxsoft.drinkTea.platform.system.rollpic.controller;
/**
 * @文件名称: ResponseControlloer.java
 * @类路径: com.wxsoft.drinkTea.platform.system.rollpic
 * @描述: TODO
 * @作者：lzj
 * @时间：2017-3-22 上午10:03:52
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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

import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.controller.BaseController;
import com.wxsoft.drinkTea.framework.utils.KindeditorController;
import com.wxsoft.drinkTea.framework.utils.ThumbNailHelper;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.platform.system.rollpic.mapper.RollPicMapper;
import com.wxsoft.drinkTea.platform.system.rollpic.model.RollPic;



/**
 * @类功能说明：轮播图信息说明
// * @类修改者：lzj
// * @修改日期：2017-03-22
 * @修改说明：
 * @轮播图名称：lzj
 * @作者：lzj
 * @创建时间：2017-03-22 上午10:03:52
 */

@Controller
//@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/rollpic")
public class RollPicControlloer extends BaseController {

	@Autowired
	private RollPicMapper rollPicService;

	/**
	 *
	 * @描述: 查询轮播图列表
	 * @作者: lzj
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/list")
	public ModelAndView listResponse(RollPic response,
			HttpServletRequest request, String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<RollPic> list = rollPicService.listPageByRollPic(response);
		   List<RollPic> responseList = new ArrayList<RollPic>();
	     for (RollPic rollPic : list) {
	    	 System.out.println("-0-0--00:"+rollPic.getIsVisable());
	    	 if(rollPic.getIsVisable()==1){
	    		 responseList.add(rollPic);
	    	   	

	    	 }
		}
		
		mv.addObject("objList", responseList);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/rollpic/list");
		
		return mv;
	}

	/**
	 * @功能：轮播图的下载以及业务逻辑间的处理
	 * @描述: 查询轮播图注表信息，
	 * @作者: lzj
	 * @日期:2017-3-22
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/getone")
	public ModelAndView getResponse(HttpServletRequest request,
			RollPic response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		response = rollPicService.selectByPrimaryKey(Integer.parseInt(request
				.getParameter("id")));
		mv.addObject("obj", response);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/rollpic/info");
		return mv;
	}
	/**
	 * 删除轮播图的信息
	 */
	@RequestMapping("/delone/{id}")
	public ModelAndView delProdeuct(HttpServletRequest request,@PathVariable Integer id,RollPic resp,HttpSession session){
		ModelAndView mv = new ModelAndView();
		RollPic response = new RollPic();
		    response.setId(id);
		    response.setIsVisable(2);
		int  flag = rollPicService.updateByPrimaryKeySelective(response);
	   System.out.println("shuchu:"+ flag);
	   mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/rollpic/list");
        return mv;
	}

	/**
	 *
	 * @描述: 添加轮播图文件
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView add(String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/rollpic/info");
		return mv;
	}

	/**
	 *
	 * @描述: 保存首次关注轮播图
	 * @作者: lzj
	 * @日期:2017-3-22
	 * @修改内容
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpSession session, HttpServletRequest request,
			RollPic rf) {
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
				String savePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "/attached/";
				System.out.println("保存path：" + savePath);
				String saveUrl = "/attached/";
				System.out.println("保存url：" +saveUrl);
				Map<String, String> maps = KindeditorController.mkdir(savePath,
						saveUrl, "catalog");
				System.out.println("map集合：" + maps.toString());
				String endName = originalFilename.substring(
						originalFilename.lastIndexOf("."),
						originalFilename.length());
				System.out.println("后缀名：" + endName);
				String filename = UUID.randomUUID() + endName;
				System.out.println("文件名：" + filename);
				if (endName.equals(".png") || endName.equals(".gif")
						|| endName.equals(".jpg") || endName.equals(".jpeg")) {

					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
							new File(maps.get("savePath"), filename));

					ThumbNailHelper.createThumbnailByRectangle(
							maps.get("savePath") + filename, 800, 600, 100,
							maps.get("savePath") + filename, 800, 600);
					rf.setPicUrl(maps.get("saveUrl") + filename);

					System.out.println(" 路径:"+ rf.getTitle() + "--" + rf.getLinkUrl() + "--" + rf.getTitle() + "--" +rf.getId()+""+rf.getPicUrl());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (null == rf.getId()) {// 保存
			System.out.println("zheshi :" + rf.getId());
			rf.setAddtime(Tools.date2Str(new Date()));
			 rollPicService.insertSelective(rf);
			System.out.println("保存时。。");
		} else {// 更新
			rollPicService.updateByPrimaryKeySelective(rf);
			System.out.println("更新时");
		}
		return "redirect:list";

	}

	/**
	 * 删除轮播图的信息，删除下载的文件信息
	 */
//	@RequestMapping(value = "/delone")
//	public void delete(HttpServletRequest request,
//			ProductSpecification response, HttpServletResponse res,
//			String DATA, HttpSession session) {
//		JSONObject jsonObject = JSONObject.parseObject(DATA);
//
//		String[] listId = jsonObject.getString("CTIDS").split(",");
//		String result = "{'flag':false}";
//		for (String string : listId) {
//			if (null != response) {
//				if (rollPicService.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
//					result = "{'flag':true}";
//				}
//			}
//		}
//		try {
//			responseAjax(result, res);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}