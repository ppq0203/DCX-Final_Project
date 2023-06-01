package project.momento.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 사이트에서 이동
	 */
	@RequestMapping(value="/mypage.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String Mypage(Criteria cri, Model model, HttpServletRequest request) { 
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		if (!(loginDto == null)) {
			if(loginDto.getPkAuthSeq() == 1) {
				// 관리자 권한인 경우
				return "redirect:/signManage.com";
			}
			// 관리자 권한이 아닌 경우
			return "redirect:/studentScreen.com";
		} else {
			return "redirect:/login.com";
		}
	}
	
	@RequestMapping(value="/menumanagement.com", produces="application/text;charset=utf-8")
	public String MenuManagement(Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		// 로그인이 되어있는지 확인
		if(!(loginDto == null)) {
			// 로그인이 되어 있을 경우
			// 관리자 권한이 맞는지 확인
			if(loginDto.getPkAuthSeq() == 1) {
				// 관리자 권한인 경우
				menuService.menuManagement((MenuDto)model.getAttribute("menuManageDto"));
				return "/mypage.com";
			}
			// 관리자 권한이 아닌 경우
			return "/mypage.com";
		}
		// 로그인이 되어있지 않은 경우
		return "/main.com";
	}
	
//	@RequestMapping(value="/getmenu.com", produces="application/json;charset=utf-8") /* value주소 이름*/
//	public String GetMenu(Model model, HttpServletRequest request) {
////		// 세션에서 내 정보를 가져온다
////		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
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
