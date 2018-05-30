package com.wxsoft.drinkTea.platform.system.userRole.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysMenuMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysMenu;
import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMapper;
import com.wxsoft.drinkTea.platform.system.userRole.mapper.SysRoleMenuMapper;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRole;
import com.wxsoft.drinkTea.platform.system.userRole.model.SysRoleMenu;
import com.wxsoft.drinkTea.platform.system.userRole.service.RoleService;

/**
 * 权限管理
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.userRole.controller
 * 2017年3月23日上午11:06:44
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseAction {

	/** */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	/**
	 * @Title: roleList
	 * @date 时间：2017年3月23日上午11:18:11
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description: 查询所有权限列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView roleList(HttpSession session,SysRole role){
		ModelAndView mav = new ModelAndView("/system/UserRole/rolelist");
		List<SysRole> list = sysRoleMapper.getPageListBy(role);
		mav.addObject("uc",role);
		session.setAttribute("roleList", list);
		return mav;
	}

	/**
	 * @Title: addRole
	 * @date 时间：2017年3月23日下午1:22:38
	 * @author 作者： 王桐睿
	 * @return 返回类型：String
	 * @Description: 跳转到添加角色页面
	 */
	@RequestMapping(value = "/addRole")
	public ModelAndView addRole(HttpSession session){
		ModelAndView mav = new ModelAndView("/system/UserRole/addRole");
		mav.addObject("sysMenu", getJsTreeMenu(session));
		return mav;
	}


	/**
	 * @Title: saveRole
	 * @date 时间：2017年3月24日上午8:54:31
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description: 保存新增的角色
	 * 用的@RequestParam方式接收参数，因为只需要接收新增角色名称和菜单id即可，没有实体类能同时接收这两个参数，接收过来自行处理即可
	 * 添加一个角色，需要保存角色名称以及相对应能看到的菜单id，所有需要两步：
	 * 												第一步：在sys_role表里添加角色名称，并获取到该角色的id；
	 * 												第二部：将获得的menuId拆成单个的id和roleId组合插入到sys_role_menu表里
	 * 这里需要添加事物处理，该项目没有service层，这里手动调用事务处理，for循环里最好不要调用数据库，把集合传到sql里执行
	 */
	@RequestMapping(value = "/saveRole")
	public void saveRole(@RequestParam("roleName") String roleName,@RequestParam("menuId") String menuId,HttpServletResponse response){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		result = roleService.saveRole(roleName, menuId, response);
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: checkOut
	 * @date 时间：2017年3月24日上午10:56:19
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:在后台添加角色的时候校验一下角色名称是否已经存在，如果已经存在在添加的时候会报错，所有提前校验一下
	 */
	@RequestMapping("/checkout")
	public void checkOut(SysRole sysRole,HttpServletResponse response){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		SysRole param = sysRoleMapper.selectBy(sysRole);
		if(param == null){
			result.put("status","1");
		}else{
			result.put("status", "0");
			result.put("Message", "该角色已经存在，请更换角色名称");
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: deleteRole
	 * @date 时间：2017年3月24日下午2:34:10
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description: 删除一个角色
	 */
	@RequestMapping(value = "/deleteRole/{id}")
	public void deleteRole(HttpServletResponse response,@PathVariable Integer id){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		if(id > 0){
			//删除角色表里的数据
			int a = sysRoleMapper.deleteByPrimaryKey(id);
			//删除角色菜单表里的数据
			SysRoleMenu param = new SysRoleMenu();
			param.setRoleId(id);
			int b = sysRoleMenuMapper.delete(param);
			if(a > 0 && b > 0){
				result.put("status", "1");
			}else{
				result.put("status", "0");
				result.put("Message", "删除失败");
			}
		}else{
			result.put("status", "0");
			result.put("Message", "角色id不对");
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: editRole
	 * @date 时间：2017年3月24日下午3:23:08
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description: 跳转到编辑角色页面
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editRole(@PathVariable Integer id,HttpSession session){
		SysRole role = sysRoleMapper.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/system/UserRole/editRole");
		mav.addObject("role", role);
		mav.addObject("sysMenu", getJsTreeMenu(session));
		//找到该角色在添加的时候的菜单id
		SysRoleMenu param = new SysRoleMenu();
		param.setRoleId(id);
		List<SysRoleMenu> list = sysRoleMenuMapper.getListBy(param);
		StringBuffer s = new StringBuffer();
		for(SysRoleMenu one : list){
			s.append(one.getMenuId()+",");
		}
		mav.addObject("menuId",s);
		return mav;
	}

	/**
	 * @Title: updateRoleData
	 * @date 时间：2017年3月24日下午4:07:07
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description: 修改角色可看的菜单，就是先把原来的权限删掉，然后添加新的
	 */
	@RequestMapping(value = "/update/{id}")
	public void updateRoleData(@PathVariable Integer id,HttpServletResponse response,
			@RequestParam("roleName") String roleName,@RequestParam("menuId") String menuId){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		try {
			//先删掉
			deleteRole(response, id);
			//再添加
			saveRole(roleName, menuId, response);
			result.put("status", "1");
		} catch (Exception e) {
			result.put("status", "0");
			result.put("Message", "修改角色失败，请稍后再试");
			e.printStackTrace();
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @Title: getJsTreeMenu
	 * @date 时间：2017年3月24日上午11:40:58
	 * @author 作者： 王桐睿
	 * @return 返回类型：String
	 * @Description: 获取树形菜单如果这个菜单是在session里获取，那么每次对菜单修改都要注销之后再重新登录才能看到最新的菜单，用户体验不是很好，应该直接从
	 *			               数据库里查一遍，这样再修改菜单之后只要刷新一下就行了
	 */
	public String getJsTreeMenu(HttpSession session) {
		StringBuffer sb = new StringBuffer("[");
		SysMenu sysMenuParam = new SysMenu();
		sysMenuParam.setParentId(0);
		Integer roleId = (Integer)session.getAttribute("sessionRoleId");
		SysRoleMenu roleMenu = new SysRoleMenu();
		roleMenu.setRoleId(roleId);
		List<SysMenu> list = sysRoleMenuMapper.getMenuByRoleId(roleMenu);
		for(SysMenu one : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sysMenu", one);
			map.put("roleId", roleId);
			//加入二级菜单
			List<SysMenu> second = sysMenuMapper.getAllSecondLevelSysMenu(map);
			one.setSysMenuList(second);
		}
		for (SysMenu menuinfo : list) {
			sb.append("{\"id\":\"" + menuinfo.getId() + "\",\"text\": \"").append(menuinfo.getName()).append("\",\"state\": ");
					sb.append("{\"opened\": true}");
				sysMenuParam.setParentId(menuinfo.getId());
				List<SysMenu> list2 = sysMenuMapper.getListBy(sysMenuParam);
				if (list2 != null && !list2.isEmpty()) {
					sb.append(", \"children\": [");
					for (SysMenu menuinfo2 : list2) {
							sb.append("{\"id\":\"" + menuinfo2.getId() + "\",\"text\": \"").append(menuinfo2.getName())
									.append("\",\"state\":");
								sb.append("{\"opened\": true}");
							sysMenuParam.setParentId(menuinfo2.getId());
							List<SysMenu> list3 = sysMenuMapper.getListBy(sysMenuParam);
							if (list3 != null && !list3.isEmpty()) {
								sb.append(", \"children\": [");
								for (SysMenu menuinfo3 : list3) {
										sb.append("{\"text\": \"").append(menuinfo3.getName())
												.append("\",\"id\":\"" + menuinfo3.getId() + "\"");
											sb.append(",\"state\": {\"opened\": true}");
										sb.append("},");
								}
								sb.append("]");

							}
							sb.append("},");
					}
					sb.append("]");

				}
				sb.append("},");
		}
		sb.append("]");
		return sb.toString().replace("},]", "}]");
	}
}
