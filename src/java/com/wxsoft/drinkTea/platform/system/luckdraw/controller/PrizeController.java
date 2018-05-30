package com.wxsoft.drinkTea.platform.system.luckdraw.controller;

/**
 * @文件名称: ResponseControlloer.java
 * @类路径: com.wxsoft.drinkTea.platform.system.rollpic
 * @描述: TODO
 * @作者：lzj
 * @时间：2017-3-22 上午10:03:52
 */
import java.io.File;
import java.io.IOException;
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
import com.wxsoft.drinkTea.platform.system.luckdraw.mapper.PrizeProMapper;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.PrizePro;

/**
 * 奖品Controller
 *
 * @author a
 *
 */

@Controller
// @Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/prizes")
public class PrizeController extends BaseController {

	@Autowired
	private PrizeProMapper prizeProService;

	/**
	 * 奖品列表
	 *
	 * @param response
	 * @return
	 */
	// HttpServletRequest request, String mstat, HttpSession session
	@RequestMapping("/list")
	public ModelAndView listResponse(PrizePro prizePro) {
		ModelAndView mv = new ModelAndView();
		List<PrizePro> list = prizeProService.getPageListLike(prizePro);
		mv.addObject("objList", list);
		mv.addObject("obj",prizePro);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/list");

		return mv;
	}

	/**
	 * @功能：分类的下载以及业务逻辑间的处理 @描述: 查询分类注表信息， @作者:
	 * lzj @日期:2017-3-23 @修改内容 @参数： @param response @参数： @return @return
	 * ModelAndView @throws
	 */
	@RequestMapping("/getone{id}")
	public ModelAndView getResponse(HttpServletRequest request, PrizePro response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		response = prizeProService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		mv.addObject("obj", response);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/info");
		return mv;
	}

	/**
	 * 添加奖品
	 *
	 * @param mstat
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView add(String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/info");
		return mv;
	}

	/**
	 * 保存奖品、更新奖品
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpSession session, HttpServletRequest request, PrizePro rf) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		String originalFilename = null;
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
					ThumbNailHelper.createThumbnailByRectangle(maps.get("savePath") + filename, 200, 200, 100,
							maps.get("savePath") + filename, 200, 200);
					rf.setPrizeImg(maps.get("saveUrl") + filename);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null == rf.getRestNum()) {
			rf.setRestNum(Integer.parseInt(rf.getPrizeNum()));
		}
		if (null == rf.getId()) {// 保存
			// rf.setAddTime(Tools.date2Str(new Date()));
			prizeProService.insert(rf);
		} else {// 更新
			prizeProService.updateByPrimaryKey(rf);
			System.out.println("4588");
		}
		return "redirect:list";

	}

	/**
	 * 删除奖品(直接删除)
	 */
	@RequestMapping("/deloned/{id}")
	public ModelAndView delProdeuct( @PathVariable Integer id,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		PrizePro response = new PrizePro();
		response.setId(id);
		int flag = prizeProService.delete(response);
		mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/list");
		return mv;
	}

}