package com.june.controller;

import com.june.dto.sys.Menu;
import com.june.service.sys.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private IMenuService menuService;

	@RequestMapping("/main")
	public String home() {
		return "master";
	}

	@RequestMapping("/menu")
	public List<Menu> getMenu(HttpServletRequest request){
		//根据业务类型，获取菜单（后期添加用户，角色，角色菜单，然后根据业务类型和角色获取菜单）
		return menuService.getMenuList();
	}

	@RequestMapping("/navbar")
	public ModelAndView getNavbar(){
		List<Menu> lst = menuService.getChildList("0");
		return new ModelAndView("base/navbar","menus",lst) ;
	}
}
