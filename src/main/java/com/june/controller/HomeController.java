package com.june.controller;

import com.june.core.utils.PageUtils;
import com.june.dto.sys.Menu;
import com.june.service.sys.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
//@RequestMapping("/home")
public class HomeController {

	@Autowired
	private IMenuService menuService;

	@RequestMapping("/main")
	public String home() {
		return "master";
	}

	@RequestMapping("/menu")
	@ResponseBody
	public List<Map<String,Object>> getMenu(HttpServletRequest request){
		//根据业务类型，获取菜单（后期添加用户，角色，角色菜单，然后根据业务类型和角色获取菜单）
		//return menuService.getMenuList();
		Map<String,Object> params  = PageUtils.getParameters(request);
		String menuid = PageUtils.getString(params.get("menuid"));
		if (!"".equals(menuid)){
			return  menuService.getAllChildList(menuid);
		}else{
			return new ArrayList<Map<String, Object>>();
		}
	}

	@RequestMapping("/navbar")
	public ModelAndView getNavbar(){
		List<Menu> lst = menuService.getChildList("0");
		return new ModelAndView("base/navbar","menus",lst) ;
	}
}
