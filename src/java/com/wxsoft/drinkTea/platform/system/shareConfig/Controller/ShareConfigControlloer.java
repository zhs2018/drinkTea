/**
 * @文件名称: ResponseControlloer.java
 * @类路径: com.wxltsoft.fxshop.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52
 */

package com.wxsoft.drinkTea.platform.system.shareConfig.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.wxsoft.drinkTea.framework.utils.ThumbNailHelper;
import com.wxsoft.drinkTea.platform.system.shareConfig.mapper.ShareConfigMapper;
import com.wxsoft.drinkTea.platform.system.shareConfig.model.ShareConfig;

/**
 * @类功能说明：商品管理
 * @类修改者：lzj @修改日期：2017-03-30
 *
 * 	@创建时间：2017-03-30 上午10:03:52
 */

@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/shareConfig")
public class ShareConfigControlloer extends BaseController {
	@Autowired
	private ShareConfigMapper shareConfigService;

	/**
	 *
	 * @描述: 商城首页信息 @作者: lzj @日期:2017-3-30 @修改内容 @参数： @param
	 * response @参数： @return @return ModelAndView @throws
	 */
	@RequestMapping("/info1")
	public ModelAndView headInfo(HttpServletRequest request, ShareConfig shareConfig, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		ShareConfig shareC = shareConfigService.selectBy(shareConfig);
		mv.addObject("obj", shareC);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/shareConfig/headinfo");
		return mv;
	}

	/**
	 *
	 * @描述:保存首页信息 @作者: lzj @日期:2017-3-30 @修改内容 @参数： @param
	 * response @参数： @return @return ModelAndView @throws
	 */
	@RequestMapping("/saveheadinfo")
	public String saveHeadInfo(ShareConfig shareConfig, HttpServletRequest request, HttpSession session) {
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
					shareConfig.setSharePic(maps.get("saveUrl") + filename);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null == shareConfig.getId()) {// 保存
			shareConfigService.insert(shareConfig);
		} else {// 更新
			shareConfigService.updateByPrimaryKey(shareConfig);
		}
		return "redirect:info1";

	}

}