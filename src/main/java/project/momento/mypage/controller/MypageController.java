package project.momento.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.menu.service.MenuService;
import project.momento.page.Criteria;

@Controller
public class MypageController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private LoginService loginService;

	/*
	 * 사용자 정보 화면 이동
	 * return: "redirect:/"+userDivn+"/profile/profile"
	 */
	@RequestMapping(value="/{userDivn}/profile", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String profileMain(@PathVariable String userDivn, Model model, HttpServletRequest request) { 
		return "redirect:/"+userDivn+"/profile/profile";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getmenu.com", produces="application/json;charset=utf-8", method=RequestMethod.POST) /* value주소 이름*/
	public List<MenuDto> GetMenu(Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		List<MenuDto> menuList = menuService.getMenuList(loginDto.getPkAuthSeq());
		model.addAttribute("menuList", menuList);
		return menuList;			
	}
	
	
}
