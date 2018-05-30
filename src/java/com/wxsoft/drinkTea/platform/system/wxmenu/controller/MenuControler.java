package com.wxsoft.drinkTea.platform.system.wxmenu.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;
import com.wxsoft.drinkTea.framework.utils.WeiXinUtils;
import com.wxsoft.drinkTea.platform.system.wxmenu.model.WxMenu;
import com.wxsoft.drinkTea.platform.system.wxmenu.service.MenuService;

/**
 * 自定义（微信）菜单
 * @author yzy
 *
 */

@RequestMapping("system/menu")
@Controller
public class MenuControler extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MenuService menuService;

	/**
	 * 转向自定义菜单
	 *
	 * @param model
	 * @return
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView menu(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("sessionUser") != null){
			WxMenu menu = new WxMenu();
			menu.setParentId(0);
			List<WxMenu> menus = menuService.selectAllMenuByMenu(menu);
			mv.addObject("menus",menus);
		}
		mv.setViewName("system/wxmenu/menuset");
		return mv;
	}

	/*
	 * 保存自定义菜单到数据库
	 */
	@RequestMapping(value = "/savemenu")
	public void savemenu(String menus, String delmenus, HttpSession session,
			HttpServletResponse response) {
		List<WxMenu> menuList = null;
		if (Tools.notEmpty(menus)) {
			menuList = new ArrayList<WxMenu>();
			JSONArray jsonMenus = JSONArray.parseArray(menus);
			for (int i = 0; i < jsonMenus.size(); i++) {
				WxMenu wxMenu = menuService
						.jsonToMenu(JSONObject.parseObject(jsonMenus.get(i).toString()));
				menuList.add(wxMenu);
			}
		}
		try {
			menuService.updateCompanyMenus(menuList, delmenus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.responseAjax("保存成功", response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 更新微信的自定义菜单
	 */
	@RequestMapping(value = "/updatewxmenu")
	public void updatewxmenu(HttpSession session, HttpServletResponse response) {
		String flag = "";
		if (session.getAttribute("sessionUser") != null) {
			String appid = WeiXinInfo.APPID;
			String secret = WeiXinInfo.APPSECRET;
			WxMenu wxMenu = new WxMenu();
			// 获取账号的access_token，首先检查appid和appsecret是否完整
			if (Tools.notEmpty(appid)
					&& Tools.notEmpty(secret)) {
				// 获取账号的access_token
				try {
					Map<String, String> map = WeiXinUtils.getAccessToken(appid, secret);
					if (Tools.notEmpty(map.get("access_token"))) {
						wxMenu.setParentId(0);
						List<WxMenu> menus = menuService.selectAllMenuByMenu(wxMenu);
						// 获取账号的menu设置，并转成json
						JSONArray jsonArray = new JSONArray();
						for (int i = 0; i < menus.size(); i++) {
							WxMenu menu = menus.get(i);
							if (menu.getChildMenu() == null || menu.getChildMenu().size() == 0) {
								jsonArray.add(menuService.transJson(menu));
							} else {
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("name", menu.getName());
								// 添加子菜单的json样式
								JSONArray array = new JSONArray();
								for (int j = 0; j < menu.getChildMenu().size(); j++) {
									array.add(menuService.transJson(menu.getChildMenu().get(j)));
								}
								jsonObject.put("sub_button", array);
								jsonArray.add(jsonObject);
							}
						}
						JSONObject jsonObject = new JSONObject();
						if (jsonArray.size() > 0) {
							jsonObject.put("button", jsonArray);
						}

						if (jsonObject.isEmpty()) {
							logger.info("删除自定义菜单");
							Map<String, String> map2 = WeiXinUtils.delMenu(map.get("access_token"));
							if (map2!=null && map2.get("errcode").equals("0")) {
								flag = "菜单更新成功!";
							} else {
								flag = "删除菜单失败，请重试";
							}
						} else {
							System.out.println("自定义菜单："+jsonObject.toString());
							WeiXinUtils.delMenu(map.get("access_token"));
							Map<String, String> map2 = WeiXinUtils.createMenu(map.get("access_token"),jsonObject.toString());
							if (map2.get("errcode").equals("0")) {
								flag = "菜单更新成功!";
							} else {
								flag = map2.get("errmsg");
							}
						}

					} else {
						flag = map.get("errmsg");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					flag = "生成失败，请检查账号的appid和appsecret";
					e.printStackTrace();
				}

			} else {
				flag = "生成失败，请检查账号的appid和appsecret";
			}
			// 根据appid和secret获取access_token
			try {
				this.responseAjax(flag, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
