package com.wxsoft.drinkTea.platform.system.wxmenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.platform.system.wxmenu.mapper.WxMenuMapper;
import com.wxsoft.drinkTea.platform.system.wxmenu.model.WxMenu;

@Service
public class MenuService {

	@Autowired
	private WxMenuMapper wxMenuMapper;

	/**
	 *
	 * 获取所有的菜单信息(将子菜单放到主菜单中)
	 * @return
	 */
	public List<WxMenu> selectAllMenuByMenu(WxMenu menu) {
		menu.setOrderBy("sort");
		List<WxMenu> menus = wxMenuMapper.getListBy(menu);
		if(menus != null && menus.size()>0){
			for (WxMenu wxMenu : menus) {
				WxMenu temp = new WxMenu();
				temp.setParentId(wxMenu.getId());
				temp.setOrderBy("sort");
				wxMenu.setChildMenu(wxMenuMapper.getListBy(temp));
			}
		}
		return menus;
	}

	public WxMenu jsonToMenu(JSONObject jsonObject) {
		WxMenu menu = new WxMenu();
		if (Tools.notEmpty(jsonObject.getString("id"))) {
			menu.setId(jsonObject.getInteger("id"));
		}
		if (Tools.notEmpty(jsonObject.getString("parentId"))) {
			menu.setParentId(jsonObject.getInteger("parentId"));
		}
		if (Tools.notEmpty(jsonObject.getString("sort"))) {
			menu.setSort(jsonObject.getInteger("sort"));
		}

		menu.setContent(jsonObject.getString("content"));

		menu.setName(jsonObject.getString("name"));

		menu.setType(jsonObject.getString("type"));
		return menu;
	}

	public void updateCompanyMenus(List<WxMenu> menuList, String delmenus) throws Exception {
		if (menuList != null && menuList.size() > 0) {
			for (int i = 0; i < menuList.size(); i++) {
				if (menuList.get(i).getId() != null) {
					wxMenuMapper.updateByPrimaryKey(menuList.get(i));
				} else {
					wxMenuMapper.insert(menuList.get(i));
				}
			}
		}
		if (Tools.notEmpty(delmenus)) {
			String[] dels = delmenus.split(",");
			for (int i = 0; i < dels.length; i++) {
				if (Tools.notEmpty(dels[i]) && !"undefined".equals(dels[i].trim())) {
					wxMenuMapper.deleteByPrimaryKey(Integer.parseInt(dels[i]));
				}
			}
		}
	}

	/**
	 * 转化为微信平台需要的格式
	 * @param menu
	 * @return
	 */
	public JSONObject transJson(WxMenu menu) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		if (menu != null) {
			jsonObject.put("name", menu.getName());
			jsonObject.put("type", menu.getType());
			if (menu.getType().equals("view")) {
				jsonObject.put("url", menu.getContent());
			} else if (menu.getType().equals("scancode_push")) {
				jsonObject.put("key", menu.getContent());
			}
		}
		return jsonObject;
	}
}
