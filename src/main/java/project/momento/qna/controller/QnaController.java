package project.momento.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.menu.service.MenuService;
import project.momento.page.Criteria;

@Controller
public class QnaController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private LoginService loginService;

	
//	@RequestMapping(value="/getmenu.com", produces="application/json;charset=utf-8") /* value주소 이름*/
//	public String GetMenu(Model model, HttpServletRequest request) {
//		// 세션에서 내 정보를 가져온다
//		// LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
//		LoginDto testDto = new LoginDto();
//		testDto.setUserId("test");
//		testDto.setPassword("asdf");
//		LoginDto loginDto = loginService.checkLogin(testDto);
//		// 로그인이 되어있는지 확인
//		if(!(loginDto == null)) {
//			// 로그인이 되어있는 경우
//			List<MenuDto> menuList = menuService.getMenuList(loginDto.getPkAuthSeq());
//			System.out.println(menuList);
//			model.addAttribute("menuList", menuList);
//			return "redirect:/mypage.com";
//		} else {
//			// 로그인이 되어있지 않은 경우
//			return "/login.com";
//		}
//	}
	
}
